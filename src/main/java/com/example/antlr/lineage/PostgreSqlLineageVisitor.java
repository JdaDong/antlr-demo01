package com.example.antlr.lineage;

import com.example.antlr.lineage.LineageGraph.ColumnNode;
import com.example.antlr.lineage.LineageGraph.TransformType;
import com.example.antlr.lineage.LineageGraph.TableRole;
import com.example.antlr.postgresql.PostgreSqlParser;
import com.example.antlr.postgresql.PostgreSqlParserBaseVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * PostgreSQL SQL 血缘分析 Visitor
 *
 * 基于 ANTLR4 Visitor 模式，深度遍历 PostgreSQL 语法树，提取列级数据血缘。
 *
 * 支持：SELECT、INSERT...SELECT、INSERT...VALUES、UPDATE SET、DELETE、CTE (WITH...AS)
 */
public class PostgreSqlLineageVisitor extends PostgreSqlParserBaseVisitor<Void> {

    private final LineageGraph graph = new LineageGraph();
    private final Map<String, String> tableAliases = new LinkedHashMap<>();
    private final List<List<ColumnNode>> selectOutputColumns = new ArrayList<>();
    private int selectDepth = 0;
    private final List<String> currentFromTables = new ArrayList<>();

    public LineageGraph getLineageGraph() {
        return graph;
    }

    // ============================================================
    // CTE (WITH ... AS)
    // ============================================================

    @Override
    public Void visitCteDefinition(PostgreSqlParser.CteDefinitionContext ctx) {
        // CTE 作为虚拟表别名
        String cteName = ctx.cteName() != null ? ctx.cteName().getText() : "_cte_";
        // 遍历 CTE 内部的 SELECT
        visit(ctx.selectStatement());
        // CTE 名称注册为可引用的表
        tableAliases.put(cteName, cteName);
        return null;
    }

    // ============================================================
    // SELECT
    // ============================================================

    @Override
    public Void visitSelectStatement(PostgreSqlParser.SelectStatementContext ctx) {
        selectDepth++;

        if (graph.getStatementType() == null) {
            graph.setStatementType("SELECT");
        }

        // CTE: withClause lives on dmlStatement, not selectStatement

        List<String> previousFromTables = new ArrayList<>(currentFromTables);
        currentFromTables.clear();

        // FROM
        for (PostgreSqlParser.TableSourceContext ts : ctx.tableSource()) {
            visitTableSource(ts);
        }
        // JOIN
        for (PostgreSqlParser.JoinClauseContext jc : ctx.joinClause()) {
            if (jc.tableSource() != null) visitTableSource(jc.tableSource());
        }

        boolean isTopLevel = selectDepth == 1
                && !(ctx.getParent() instanceof PostgreSqlParser.InsertStatementContext);
        boolean isInsertSelect = ctx.getParent() instanceof PostgreSqlParser.InsertStatementContext;

        List<ColumnNode> outputCols = new ArrayList<>();
        if (ctx.selectElements() != null) {
            PostgreSqlParser.SelectElementsContext elems = ctx.selectElements();
            if (elems.STAR() != null) {
                for (String tbl : currentFromTables) {
                    String realTable = resolveTableAlias(tbl);
                    ColumnNode src = new ColumnNode(realTable, "*");
                    ColumnNode tgt = new ColumnNode(null, "*");
                    tgt.setExpression(tbl + ".*");
                    if (isTopLevel) {
                        graph.addEdge(src, tgt, TransformType.WILDCARD, "SELECT * FROM " + realTable);
                    }
                    outputCols.add(src);
                }
            } else {
                for (PostgreSqlParser.SelectElementContext elem : elems.selectElement()) {
                    List<ColumnNode> sources = extractColumnsFromExpression(elem.expression());
                    TransformType transformType = detectTransformType(elem.expression());
                    String alias = elem.alias() != null ? elem.alias().getText() : null;
                    String exprText = elem.expression().getText();

                    if (isTopLevel) {
                        ColumnNode target;
                        if (alias != null) {
                            target = new ColumnNode(null, alias);
                            target.setAlias(alias);
                        } else if (sources.size() == 1) {
                            target = new ColumnNode(null, sources.get(0).getColumn());
                        } else {
                            target = new ColumnNode(null, exprText);
                        }
                        target.setExpression(exprText);
                        for (ColumnNode src : sources) {
                            graph.addEdge(src, target, transformType, exprText);
                        }
                        if (sources.isEmpty()) {
                            ColumnNode constSrc = new ColumnNode(null, exprText);
                            constSrc.setExpression(exprText);
                            graph.addEdge(constSrc, target, TransformType.CONSTANT, exprText);
                        }
                    }

                    if (!sources.isEmpty()) {
                        outputCols.addAll(sources);
                    } else {
                        ColumnNode constNode = new ColumnNode(null, exprText);
                        constNode.setExpression(exprText);
                        outputCols.add(constNode);
                    }
                }
            }
        }

        if (isInsertSelect) {
            selectOutputColumns.add(outputCols);
        }

        for (String tbl : currentFromTables) {
            String realTable = resolveTableAlias(tbl);
            if (!realTable.equals(tbl)) {
                graph.addTable(realTable, tbl, TableRole.SOURCE);
            } else {
                graph.addTable(realTable, TableRole.SOURCE);
            }
        }

        // UNION
        if (ctx.UNION() != null && ctx.selectStatement() != null) {
            visitSelectStatement(ctx.selectStatement());
        }

        currentFromTables.clear();
        currentFromTables.addAll(previousFromTables);
        selectDepth--;
        return null;
    }

    // ============================================================
    // INSERT
    // ============================================================

    @Override
    public Void visitInsertStatement(PostgreSqlParser.InsertStatementContext ctx) {
        graph.setStatementType("INSERT");

        // CTE: withClause lives on dmlStatement, not insertStatement

        String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
        graph.addTable(targetTable, TableRole.TARGET);

        List<String> targetColNames = new ArrayList<>();
        if (ctx.columnNameList() != null) {
            for (PostgreSqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
                targetColNames.add(col.getText());
            }
        }

        if (ctx.selectStatement() != null) {
            selectOutputColumns.clear();
            visit(ctx.selectStatement());
            if (!selectOutputColumns.isEmpty()) {
                List<ColumnNode> srcCols = selectOutputColumns.get(selectOutputColumns.size() - 1);
                int colCount = Math.min(targetColNames.size(), srcCols.size());
                if (targetColNames.isEmpty()) {
                    for (int i = 0; i < srcCols.size(); i++) targetColNames.add("col_" + i);
                    colCount = srcCols.size();
                }
                for (int i = 0; i < colCount; i++) {
                    ColumnNode src = srcCols.get(i);
                    ColumnNode tgt = new ColumnNode(targetTable, targetColNames.get(i));
                    graph.addEdge(src, tgt, TransformType.DIRECT,
                            src.getId() + " → " + targetTable + "." + targetColNames.get(i));
                }
            }
        } else if (ctx.VALUES() != null) {
            List<PostgreSqlParser.ExpressionListContext> valueLists = ctx.expressionList();
            if (!valueLists.isEmpty() && !targetColNames.isEmpty()) {
                PostgreSqlParser.ExpressionListContext firstRow = valueLists.get(0);
                int colCount = Math.min(targetColNames.size(), firstRow.expression().size());
                for (int i = 0; i < colCount; i++) {
                    String valText = firstRow.expression(i).getText();
                    ColumnNode src = new ColumnNode(null, valText);
                    src.setExpression(valText);
                    ColumnNode tgt = new ColumnNode(targetTable, targetColNames.get(i));
                    graph.addEdge(src, tgt, TransformType.VALUE_ASSIGN, valText + " → " + targetColNames.get(i));
                }
            }
        }
        return null;
    }

    // ============================================================
    // UPDATE
    // ============================================================

    @Override
    public Void visitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx) {
        graph.setStatementType("UPDATE");
        // CTE: withClause lives on dmlStatement, not updateStatement

        String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
        graph.addTable(targetTable, TableRole.SOURCE_TARGET);

        for (PostgreSqlParser.UpdateAssignmentContext assign : ctx.updateAssignment()) {
            if (assign.columnRef() != null) {
                String targetCol = assign.columnRef().getText();
                if (targetCol.contains(".")) targetCol = targetCol.substring(targetCol.lastIndexOf('.') + 1);
                ColumnNode target = new ColumnNode(targetTable, targetCol);

                if (assign.expression() != null) {
                    List<ColumnNode> sources = extractColumnsFromExpression(assign.expression());
                    TransformType type = detectTransformType(assign.expression());
                    String exprText = assign.expression().getText();
                    if (!sources.isEmpty()) {
                        for (ColumnNode src : sources) {
                            if (src.getTable() == null || src.getTable().isEmpty()) src.setTable(targetTable);
                            graph.addEdge(src, target,
                                    type == TransformType.DIRECT ? TransformType.UPDATE_ASSIGN : type, exprText);
                        }
                    } else {
                        ColumnNode constSrc = new ColumnNode(null, exprText);
                        constSrc.setExpression(exprText);
                        graph.addEdge(constSrc, target, TransformType.VALUE_ASSIGN, exprText);
                    }
                }
            }
        }
        return null;
    }

    // ============================================================
    // DELETE
    // ============================================================

    @Override
    public Void visitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx) {
        graph.setStatementType("DELETE");
        if (ctx.tableName() != null) graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        return null;
    }

    // ============================================================
    // DDL
    // ============================================================

    @Override
    public Void visitCreateTable(PostgreSqlParser.CreateTableContext ctx) {
        graph.setStatementType("CREATE_TABLE");
        if (ctx.tableName() != null && !ctx.tableName().isEmpty()) graph.addTable(ctx.tableName(0).getText(), TableRole.TARGET);
        return null;
    }

    @Override
    public Void visitDropTable(PostgreSqlParser.DropTableContext ctx) {
        graph.setStatementType("DROP_TABLE");
        if (ctx.tableName() != null) graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        return null;
    }

    @Override
    public Void visitAlterTable(PostgreSqlParser.AlterTableContext ctx) {
        graph.setStatementType("ALTER_TABLE");
        if (ctx.tableName() != null) graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        return null;
    }

    // ============================================================
    // FROM
    // ============================================================

    @Override
    public Void visitTableSource(PostgreSqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) {
            String tableName = ctx.tableName().getText();
            String alias = ctx.alias() != null ? ctx.alias().getText() : null;
            if (alias != null) {
                tableAliases.put(alias, tableName);
                currentFromTables.add(alias);
            } else {
                currentFromTables.add(tableName);
            }
        }
        if (ctx.selectStatement() != null) {
            String alias = ctx.alias() != null ? ctx.alias().getText() : "_subquery_";
            currentFromTables.add(alias);
            visit(ctx.selectStatement());
        }
        return null;
    }

    // ============================================================
    // 工具方法
    // ============================================================

    private List<ColumnNode> extractColumnsFromExpression(PostgreSqlParser.ExpressionContext expr) {
        List<ColumnNode> result = new ArrayList<>();
        if (expr == null) return result;
        collectColumnRefs(expr, result);
        return result;
    }

    private void collectColumnRefs(ParseTree tree, List<ColumnNode> result) {
        if (tree instanceof PostgreSqlParser.ColumnRefExpressionContext) {
            PostgreSqlParser.ColumnRefExpressionContext colExpr = (PostgreSqlParser.ColumnRefExpressionContext) tree;
            PostgreSqlParser.ColumnRefContext colRef = colExpr.columnRef();
            if (colRef != null) {
                String table = null;
                String column;
                // columnRef uses identifier() list, not tableName()/columnName()
                // formats: identifier (column), identifier.identifier (table.column),
                //          identifier.identifier.identifier (schema.table.column), identifier.* (table.*)
                List<PostgreSqlParser.IdentifierContext> ids = colRef.identifier();
                if (colRef.STAR() != null) {
                    // table.* pattern
                    if (ids.size() >= 1) {
                        table = ids.get(0).getText();
                        table = resolveTableAlias(table);
                    }
                    column = "*";
                } else if (ids.size() == 3) {
                    // schema.table.column
                    table = ids.get(1).getText();
                    table = resolveTableAlias(table);
                    column = ids.get(2).getText();
                } else if (ids.size() == 2) {
                    // table.column
                    table = ids.get(0).getText();
                    table = resolveTableAlias(table);
                    column = ids.get(1).getText();
                } else if (ids.size() == 1) {
                    // just column
                    column = ids.get(0).getText();
                } else {
                    column = colRef.getText();
                }

                if (table == null && !currentFromTables.isEmpty()) {
                    table = resolveTableAlias(currentFromTables.get(0));
                    if (currentFromTables.size() > 1) table = null;
                }
                result.add(new ColumnNode(table, column));
            }
            return;
        }
        if (tree instanceof PostgreSqlParser.StarExpressionContext) {
            result.add(new ColumnNode(null, "*"));
            return;
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            collectColumnRefs(tree.getChild(i), result);
        }
    }

    private TransformType detectTransformType(PostgreSqlParser.ExpressionContext expr) {
        if (expr == null) return TransformType.DIRECT;
        return detectTransformTypeRecursive(expr);
    }

    private TransformType detectTransformTypeRecursive(ParseTree tree) {
        if (tree instanceof PostgreSqlParser.FunctionCallExpressionContext) {
            PostgreSqlParser.FunctionCallExpressionContext funcExpr = (PostgreSqlParser.FunctionCallExpressionContext) tree;
            PostgreSqlParser.FunctionCallContext fc = funcExpr.functionCall();
            if (fc != null && fc.functionName() != null) {
                String funcName = fc.functionName().getText().toUpperCase();
                if (isAggregateFunction(funcName)) return TransformType.AGGREGATION;
                return TransformType.FUNCTION;
            }
        }
        if (tree instanceof PostgreSqlParser.CaseExprContext) return TransformType.CONDITIONAL;
        if (tree instanceof PostgreSqlParser.MulDivModExpressionContext
                || tree instanceof PostgreSqlParser.AddSubExpressionContext
                || tree instanceof PostgreSqlParser.ConcatExpressionContext) return TransformType.COMPUTATION;
        if (tree instanceof PostgreSqlParser.LiteralExpressionContext) return TransformType.CONSTANT;
        if (tree instanceof PostgreSqlParser.ColumnRefExpressionContext) return TransformType.DIRECT;

        TransformType maxType = TransformType.DIRECT;
        for (int i = 0; i < tree.getChildCount(); i++) {
            TransformType childType = detectTransformTypeRecursive(tree.getChild(i));
            if (childType.ordinal() > maxType.ordinal()) maxType = childType;
        }
        return maxType;
    }

    private boolean isAggregateFunction(String name) {
        return "COUNT".equals(name) || "SUM".equals(name) || "AVG".equals(name)
                || "MAX".equals(name) || "MIN".equals(name) || "ARRAY_AGG".equals(name)
                || "STRING_AGG".equals(name);
    }

    private String resolveTableAlias(String nameOrAlias) {
        return tableAliases.getOrDefault(nameOrAlias, nameOrAlias);
    }
}
