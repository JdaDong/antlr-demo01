package com.example.antlr.lineage;

import com.example.antlr.lineage.LineageGraph.ColumnNode;
import com.example.antlr.lineage.LineageGraph.TransformType;
import com.example.antlr.lineage.LineageGraph.TableRole;
import com.example.antlr.sparksql.SparkSqlParser;
import com.example.antlr.sparksql.SparkSqlParserBaseVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * SparkSQL SQL 血缘分析 Visitor
 *
 * 基于 ANTLR4 Visitor 模式，深度遍历 SparkSQL 语法树，提取列级数据血缘。
 *
 * 支持：SELECT、INSERT...SELECT、INSERT OVERWRITE、INSERT...VALUES、
 *       CTE (WITH...AS)、CREATE TABLE AS SELECT (CTAS)、CREATE VIEW AS SELECT
 */
public class SparkSqlLineageVisitor extends SparkSqlParserBaseVisitor<Void> {

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
    public Void visitCteDefinition(SparkSqlParser.CteDefinitionContext ctx) {
        String cteName = ctx.cteName() != null ? ctx.cteName().getText() : "_cte_";
        visit(ctx.selectStatement());
        tableAliases.put(cteName, cteName);
        return null;
    }

    // ============================================================
    // SELECT
    // ============================================================

    @Override
    public Void visitSelectStatement(SparkSqlParser.SelectStatementContext ctx) {
        selectDepth++;

        if (graph.getStatementType() == null) {
            graph.setStatementType("SELECT");
        }

        // CTE: withClause is on dmlStatement, not selectStatement
        // handled by parent dmlStatement visit

        List<String> previousFromTables = new ArrayList<>(currentFromTables);
        currentFromTables.clear();

        // FROM
        for (SparkSqlParser.TableSourceContext ts : ctx.tableSource()) {
            visitTableSource(ts);
        }
        // JOIN
        for (SparkSqlParser.JoinClauseContext jc : ctx.joinClause()) {
            if (jc.tableSource() != null) visitTableSource(jc.tableSource());
        }

        boolean isTopLevel = selectDepth == 1
                && !(ctx.getParent() instanceof SparkSqlParser.InsertStatementContext)
                && !(ctx.getParent() instanceof SparkSqlParser.CreateTableContext)
                && !(ctx.getParent() instanceof SparkSqlParser.CreateViewContext);
        boolean isInsertSelect = ctx.getParent() instanceof SparkSqlParser.InsertStatementContext
                || ctx.getParent() instanceof SparkSqlParser.CreateTableContext
                || ctx.getParent() instanceof SparkSqlParser.CreateViewContext;

        List<ColumnNode> outputCols = new ArrayList<>();
        if (ctx.selectElements() != null) {
            SparkSqlParser.SelectElementsContext elems = ctx.selectElements();
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
                for (SparkSqlParser.SelectElementContext elem : elems.selectElement()) {
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
    public Void visitInsertStatement(SparkSqlParser.InsertStatementContext ctx) {
        if (ctx.OVERWRITE() != null) {
            graph.setStatementType("INSERT_OVERWRITE");
        } else {
            graph.setStatementType("INSERT");
        }

        // CTE: withClause is on dmlStatement, not insertStatement
        // handled by parent dmlStatement visit

        String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
        graph.addTable(targetTable, TableRole.TARGET);

        List<String> targetColNames = new ArrayList<>();
        if (ctx.columnNameList() != null) {
            for (SparkSqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
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
            List<SparkSqlParser.ExpressionListContext> valueLists = ctx.expressionList();
            if (!valueLists.isEmpty() && !targetColNames.isEmpty()) {
                SparkSqlParser.ExpressionListContext firstRow = valueLists.get(0);
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
    // CREATE TABLE AS SELECT (CTAS)
    // ============================================================

    @Override
    public Void visitCreateTable(SparkSqlParser.CreateTableContext ctx) {
        if (ctx.AS() != null && ctx.selectStatement() != null) {
            graph.setStatementType("CREATE_TABLE_AS_SELECT");
            String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
            graph.addTable(targetTable, TableRole.TARGET);

            selectOutputColumns.clear();
            visit(ctx.selectStatement());
            if (!selectOutputColumns.isEmpty()) {
                List<ColumnNode> srcCols = selectOutputColumns.get(selectOutputColumns.size() - 1);
                for (int i = 0; i < srcCols.size(); i++) {
                    ColumnNode src = srcCols.get(i);
                    String colName = src.getAlias() != null ? src.getAlias() : src.getColumn();
                    ColumnNode tgt = new ColumnNode(targetTable, colName);
                    graph.addEdge(src, tgt, TransformType.DIRECT,
                            src.getId() + " → " + targetTable + "." + colName);
                }
            }
        } else {
            graph.setStatementType("CREATE_TABLE");
            if (ctx.tableName() != null) graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        }
        return null;
    }

    // ============================================================
    // CREATE VIEW AS SELECT
    // ============================================================

    @Override
    public Void visitCreateView(SparkSqlParser.CreateViewContext ctx) {
        graph.setStatementType("CREATE_VIEW");
        String viewName = ctx.viewName() != null ? ctx.viewName().getText() : "_unknown_view_";
        graph.addTable(viewName, TableRole.TARGET);

        if (ctx.selectStatement() != null) {
            selectOutputColumns.clear();
            visit(ctx.selectStatement());
            if (!selectOutputColumns.isEmpty()) {
                List<ColumnNode> srcCols = selectOutputColumns.get(selectOutputColumns.size() - 1);
                for (int i = 0; i < srcCols.size(); i++) {
                    ColumnNode src = srcCols.get(i);
                    String colName = src.getAlias() != null ? src.getAlias() : src.getColumn();
                    ColumnNode tgt = new ColumnNode(viewName, colName);
                    graph.addEdge(src, tgt, TransformType.DIRECT,
                            src.getId() + " → " + viewName + "." + colName);
                }
            }
        }
        return null;
    }

    // ============================================================
    // DDL (无列级血缘，只记录表信息)
    // ============================================================

    @Override
    public Void visitDropTable(SparkSqlParser.DropTableContext ctx) {
        graph.setStatementType("DROP_TABLE");
        if (ctx.tableName() != null) graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        return null;
    }

    @Override
    public Void visitDropView(SparkSqlParser.DropViewContext ctx) {
        graph.setStatementType("DROP_VIEW");
        if (ctx.viewName() != null) graph.addTable(ctx.viewName().getText(), TableRole.TARGET);
        return null;
    }

    @Override
    public Void visitRenameTable(SparkSqlParser.RenameTableContext ctx) {
        graph.setStatementType("RENAME_TABLE");
        for (SparkSqlParser.TableNameContext tn : ctx.tableName()) {
            graph.addTable(tn.getText(), TableRole.TARGET);
        }
        return null;
    }

    // ============================================================
    // FROM
    // ============================================================

    @Override
    public Void visitTableSource(SparkSqlParser.TableSourceContext ctx) {
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

    private List<ColumnNode> extractColumnsFromExpression(SparkSqlParser.ExpressionContext expr) {
        List<ColumnNode> result = new ArrayList<>();
        if (expr == null) return result;
        collectColumnRefs(expr, result);
        return result;
    }

    private void collectColumnRefs(ParseTree tree, List<ColumnNode> result) {
        if (tree instanceof SparkSqlParser.ColumnRefExpressionContext) {
            SparkSqlParser.ColumnRefExpressionContext colExpr = (SparkSqlParser.ColumnRefExpressionContext) tree;
            SparkSqlParser.ColumnRefContext colRef = colExpr.columnRef();
            if (colRef != null) {
                String table = null;
                String column;
                if (colRef.tableName() != null) {
                    table = colRef.tableName().getText();
                    table = resolveTableAlias(table);
                }
                if (colRef.columnName() != null) column = colRef.columnName().getText();
                else if (colRef.STAR() != null) column = "*";
                else column = colRef.getText();

                if (table == null && !currentFromTables.isEmpty()) {
                    table = resolveTableAlias(currentFromTables.get(0));
                    if (currentFromTables.size() > 1) table = null;
                }
                result.add(new ColumnNode(table, column));
            }
            return;
        }
        if (tree instanceof SparkSqlParser.StarExpressionContext) {
            result.add(new ColumnNode(null, "*"));
            return;
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            collectColumnRefs(tree.getChild(i), result);
        }
    }

    private TransformType detectTransformType(SparkSqlParser.ExpressionContext expr) {
        if (expr == null) return TransformType.DIRECT;
        return detectTransformTypeRecursive(expr);
    }

    private TransformType detectTransformTypeRecursive(ParseTree tree) {
        if (tree instanceof SparkSqlParser.FunctionCallExpressionContext) {
            SparkSqlParser.FunctionCallExpressionContext funcExpr = (SparkSqlParser.FunctionCallExpressionContext) tree;
            SparkSqlParser.FunctionCallContext fc = funcExpr.functionCall();
            if (fc != null && fc.functionName() != null) {
                String funcName = fc.functionName().getText().toUpperCase();
                if (isAggregateFunction(funcName)) return TransformType.AGGREGATION;
                return TransformType.FUNCTION;
            }
        }
        if (tree instanceof SparkSqlParser.CaseExprContext) return TransformType.CONDITIONAL;
        if (tree instanceof SparkSqlParser.MulDivModExpressionContext
                || tree instanceof SparkSqlParser.AddSubExpressionContext
                || tree instanceof SparkSqlParser.ConcatExpressionContext
                || tree instanceof SparkSqlParser.BitwiseExpressionContext) return TransformType.COMPUTATION;
        if (tree instanceof SparkSqlParser.LiteralExpressionContext) return TransformType.CONSTANT;
        if (tree instanceof SparkSqlParser.ColumnRefExpressionContext) return TransformType.DIRECT;

        TransformType maxType = TransformType.DIRECT;
        for (int i = 0; i < tree.getChildCount(); i++) {
            TransformType childType = detectTransformTypeRecursive(tree.getChild(i));
            if (childType.ordinal() > maxType.ordinal()) maxType = childType;
        }
        return maxType;
    }

    private boolean isAggregateFunction(String name) {
        return "COUNT".equals(name) || "SUM".equals(name) || "AVG".equals(name)
                || "MAX".equals(name) || "MIN".equals(name)
                || "COLLECT_LIST".equals(name) || "COLLECT_SET".equals(name)
                || "FIRST".equals(name) || "LAST".equals(name);
    }

    private String resolveTableAlias(String nameOrAlias) {
        return tableAliases.getOrDefault(nameOrAlias, nameOrAlias);
    }
}
