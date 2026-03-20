package com.example.antlr.lineage;

import com.example.antlr.lineage.LineageGraph.ColumnNode;
import com.example.antlr.lineage.LineageGraph.TransformType;
import com.example.antlr.lineage.LineageGraph.TableRole;
import com.example.antlr.mysql.MySqlParser;
import com.example.antlr.mysql.MySqlParserBaseVisitor;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * MySQL SQL 血缘分析 Visitor
 *
 * <p>基于 ANTLR4 Visitor 模式，深度遍历 MySQL 语法树，提取列级数据血缘（Column-level Data Lineage）。</p>
 *
 * <h3>支持的语句类型：</h3>
 * <ul>
 *   <li>SELECT — 列映射、表达式、函数、聚合、CASE WHEN、子查询、UNION</li>
 *   <li>INSERT ... SELECT — 目标表列 ← SELECT 来源列（按位置对应）</li>
 *   <li>INSERT ... VALUES — 目标表列 ← 常量值</li>
 *   <li>CREATE TABLE AS SELECT (CTAS) — 概念上与 INSERT SELECT 类似</li>
 *   <li>UPDATE SET — 更新列 ← 来源列/表达式</li>
 * </ul>
 *
 * <h3>核心设计：</h3>
 * <ul>
 *   <li>别名作用域管理 — 维护 tableAlias → realTable 和 columnAlias → sourceColumn 映射</li>
 *   <li>表达式递归解析 — 从复杂表达式中提取所有来源列引用</li>
 *   <li>转换类型识别 — 自动识别直接映射/聚合/函数/计算/条件等转换类型</li>
 * </ul>
 */
public class MySqlLineageVisitor extends MySqlParserBaseVisitor<Void> {

    private final LineageGraph graph = new LineageGraph();

    // ============================================================
    // 别名作用域
    // ============================================================

    /** 表别名 → 真实表名 */
    private final Map<String, String> tableAliases = new LinkedHashMap<>();

    /** 当前 SELECT 上下文中收集的来源列列表（用于 INSERT...SELECT 位置对应） */
    private final List<List<ColumnNode>> selectOutputColumns = new ArrayList<>();

    /** 当前 SELECT 深度（用于区分主查询和子查询） */
    private int selectDepth = 0;

    /** 当前解析上下文中涉及的 FROM 表 */
    private final List<String> currentFromTables = new ArrayList<>();

    // ============================================================
    // 公共 API
    // ============================================================

    /**
     * 获取构建完成的血缘图
     */
    public LineageGraph getLineageGraph() {
        return graph;
    }

    // ============================================================
    // SELECT 语句
    // ============================================================

    @Override
    public Void visitSelectStatement(MySqlParser.SelectStatementContext ctx) {
        selectDepth++;

        if (graph.getStatementType() == null) {
            graph.setStatementType("SELECT");
        }

        // 先遍历 FROM + JOIN，收集表别名
        List<String> previousFromTables = new ArrayList<>(currentFromTables);
        currentFromTables.clear();

        // 遍历 FROM 表
        for (MySqlParser.TableSourceContext ts : ctx.tableSource()) {
            visitTableSource(ts);
        }

        // 遍历 JOIN
        for (MySqlParser.JoinClauseContext jc : ctx.joinClause()) {
            if (jc.tableSource() != null) {
                visitTableSource(jc.tableSource());
            }
        }

        // 如果是顶层 SELECT（非 INSERT...SELECT 内嵌），收集输出列作为目标
        boolean isTopLevel = selectDepth == 1
                && !(ctx.getParent() instanceof MySqlParser.InsertStatementContext);
        boolean isInsertSelect = ctx.getParent() instanceof MySqlParser.InsertStatementContext;

        // 处理 SELECT 元素
        List<ColumnNode> outputCols = new ArrayList<>();
        if (ctx.selectElements() != null) {
            MySqlParser.SelectElementsContext elems = ctx.selectElements();

            // SELECT *
            if (elems.STAR() != null) {
                // 通配符 — 所有来源表的所有列
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
                // 逐个处理 SELECT 元素
                for (MySqlParser.SelectElementContext elem : elems.selectElement()) {
                    List<ColumnNode> sources = extractColumnsFromExpression(elem.expression());
                    TransformType transformType = detectTransformType(elem.expression());

                    String alias = elem.alias() != null ? elem.alias().getText() : null;
                    String exprText = elem.expression().getText();

                    if (isTopLevel) {
                        // 顶层 SELECT：来源 → 输出列
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
                            // 常量表达式
                            ColumnNode constSrc = new ColumnNode(null, exprText);
                            constSrc.setExpression(exprText);
                            graph.addEdge(constSrc, target, TransformType.CONSTANT, exprText);
                        }
                    }

                    // 记录输出列（用于 INSERT...SELECT）
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

        // 将输出列压入栈（用于 INSERT...SELECT 匹配）
        if (isInsertSelect) {
            selectOutputColumns.add(outputCols);
        }

        // 标记来源表
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

        // 恢复上下文
        currentFromTables.clear();
        currentFromTables.addAll(previousFromTables);
        selectDepth--;

        return null;
    }

    // ============================================================
    // INSERT 语句
    // ============================================================

    @Override
    public Void visitInsertStatement(MySqlParser.InsertStatementContext ctx) {
        graph.setStatementType("INSERT");

        String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
        graph.addTable(targetTable, TableRole.TARGET);

        // 提取目标列名
        List<String> targetColNames = new ArrayList<>();
        if (ctx.columnNameList() != null) {
            for (MySqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
                targetColNames.add(col.getText());
            }
        }

        // INSERT ... SELECT
        if (ctx.selectStatement() != null) {
            // 先遍历子 SELECT 收集输出列
            selectOutputColumns.clear();
            visit(ctx.selectStatement());

            if (!selectOutputColumns.isEmpty()) {
                List<ColumnNode> srcCols = selectOutputColumns.get(selectOutputColumns.size() - 1);
                int colCount = Math.min(targetColNames.size(), srcCols.size());

                // 如果未指定目标列，按位置用 col_0, col_1 ...
                if (targetColNames.isEmpty()) {
                    for (int i = 0; i < srcCols.size(); i++) {
                        targetColNames.add("col_" + i);
                    }
                    colCount = srcCols.size();
                }

                for (int i = 0; i < colCount; i++) {
                    ColumnNode src = srcCols.get(i);
                    ColumnNode tgt = new ColumnNode(targetTable, targetColNames.get(i));
                    graph.addEdge(src, tgt, TransformType.DIRECT,
                            src.getId() + " → " + targetTable + "." + targetColNames.get(i));
                }
            }
        }
        // INSERT ... VALUES
        else if (ctx.VALUES() != null) {
            // VALUES 中每一行的第一组为列赋值
            List<MySqlParser.ExpressionListContext> valueLists = ctx.expressionList();
            if (!valueLists.isEmpty() && !targetColNames.isEmpty()) {
                MySqlParser.ExpressionListContext firstRow = valueLists.get(0);
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
    // UPDATE 语句
    // ============================================================

    @Override
    public Void visitUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        graph.setStatementType("UPDATE");

        String targetTable = ctx.tableName() != null ? ctx.tableName().getText() : "_unknown_";
        graph.addTable(targetTable, TableRole.SOURCE_TARGET);

        // 处理 SET 赋值
        for (MySqlParser.UpdateAssignmentContext assign : ctx.updateAssignment()) {
            if (assign.columnRef() != null) {
                String targetCol = assign.columnRef().getText();
                // 去掉表前缀
                if (targetCol.contains(".")) {
                    targetCol = targetCol.substring(targetCol.lastIndexOf('.') + 1);
                }
                ColumnNode target = new ColumnNode(targetTable, targetCol);

                if (assign.expression() != null) {
                    List<ColumnNode> sources = extractColumnsFromExpression(assign.expression());
                    TransformType type = detectTransformType(assign.expression());
                    String exprText = assign.expression().getText();

                    if (!sources.isEmpty()) {
                        for (ColumnNode src : sources) {
                            // 如果来源列没有表名，默认归属当前表
                            if (src.getTable() == null || src.getTable().isEmpty()) {
                                src.setTable(targetTable);
                            }
                            graph.addEdge(src, target, type == TransformType.DIRECT ? TransformType.UPDATE_ASSIGN : type, exprText);
                        }
                    } else {
                        // 常量赋值
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
    // DELETE 语句
    // ============================================================

    @Override
    public Void visitDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        graph.setStatementType("DELETE");
        if (ctx.tableName() != null) {
            graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        }
        return null;
    }

    // ============================================================
    // DDL 语句（不产生列级血缘，只记录表信息）
    // ============================================================

    @Override
    public Void visitCreateTable(MySqlParser.CreateTableContext ctx) {
        graph.setStatementType("CREATE_TABLE");
        if (ctx.tableName() != null) {
            graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        }
        return null;
    }

    @Override
    public Void visitDropTable(MySqlParser.DropTableContext ctx) {
        graph.setStatementType("DROP_TABLE");
        if (ctx.tableName() != null) {
            graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        }
        return null;
    }

    @Override
    public Void visitAlterTable(MySqlParser.AlterTableContext ctx) {
        graph.setStatementType("ALTER_TABLE");
        if (ctx.tableName() != null) {
            graph.addTable(ctx.tableName().getText(), TableRole.TARGET);
        }
        return null;
    }

    // ============================================================
    // FROM 表源
    // ============================================================

    @Override
    public Void visitTableSource(MySqlParser.TableSourceContext ctx) {
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

        // 子查询表源
        if (ctx.selectStatement() != null) {
            String alias = ctx.alias() != null ? ctx.alias().getText() : "_subquery_";
            currentFromTables.add(alias);
            // 子查询内部遍历
            visit(ctx.selectStatement());
        }

        return null;
    }

    // ============================================================
    // 表达式分析工具方法
    // ============================================================

    /**
     * 从表达式中递归提取所有列引用
     */
    private List<ColumnNode> extractColumnsFromExpression(MySqlParser.ExpressionContext expr) {
        List<ColumnNode> result = new ArrayList<>();
        if (expr == null) return result;
        collectColumnRefs(expr, result);
        return result;
    }

    /**
     * 递归收集列引用
     */
    private void collectColumnRefs(ParseTree tree, List<ColumnNode> result) {
        if (tree instanceof MySqlParser.ColumnRefExpressionContext) {
            MySqlParser.ColumnRefExpressionContext colExpr = (MySqlParser.ColumnRefExpressionContext) tree;
            MySqlParser.ColumnRefContext colRef = colExpr.columnRef();
            if (colRef != null) {
                String table = null;
                String column;

                if (colRef.tableName() != null) {
                    table = colRef.tableName().getText();
                    table = resolveTableAlias(table);
                }
                if (colRef.columnName() != null) {
                    column = colRef.columnName().getText();
                } else if (colRef.STAR() != null) {
                    column = "*";
                } else {
                    column = colRef.getText();
                }

                // 如果没有表前缀，尝试从当前 FROM 表推断
                if (table == null && !currentFromTables.isEmpty()) {
                    table = resolveTableAlias(currentFromTables.get(0));
                    if (currentFromTables.size() > 1) {
                        // 多表场景下无法确定归属，标记为 null
                        table = null;
                    }
                }

                ColumnNode node = new ColumnNode(table, column);
                result.add(node);
            }
            return;
        }

        if (tree instanceof MySqlParser.StarExpressionContext) {
            // SELECT * 中的 STAR 表达式
            ColumnNode node = new ColumnNode(null, "*");
            result.add(node);
            return;
        }

        for (int i = 0; i < tree.getChildCount(); i++) {
            collectColumnRefs(tree.getChild(i), result);
        }
    }

    /**
     * 检测表达式的转换类型
     */
    private TransformType detectTransformType(MySqlParser.ExpressionContext expr) {
        if (expr == null) return TransformType.DIRECT;

        // 递归检测
        return detectTransformTypeRecursive(expr);
    }

    private TransformType detectTransformTypeRecursive(ParseTree tree) {
        if (tree instanceof MySqlParser.FunctionCallExpressionContext) {
            MySqlParser.FunctionCallExpressionContext funcExpr = (MySqlParser.FunctionCallExpressionContext) tree;
            MySqlParser.FunctionCallContext fc = funcExpr.functionCall();
            if (fc != null && fc.functionName() != null) {
                String funcName = fc.functionName().getText().toUpperCase();
                if (isAggregateFunction(funcName)) {
                    return TransformType.AGGREGATION;
                }
                return TransformType.FUNCTION;
            }
        }

        if (tree instanceof MySqlParser.CaseExprContext) {
            return TransformType.CONDITIONAL;
        }

        if (tree instanceof MySqlParser.MulDivModExpressionContext
                || tree instanceof MySqlParser.AddSubExpressionContext) {
            return TransformType.COMPUTATION;
        }

        if (tree instanceof MySqlParser.LiteralExpressionContext) {
            return TransformType.CONSTANT;
        }

        if (tree instanceof MySqlParser.ColumnRefExpressionContext) {
            return TransformType.DIRECT;
        }

        // 递归检查子节点（返回最"复杂"的类型）
        TransformType maxType = TransformType.DIRECT;
        for (int i = 0; i < tree.getChildCount(); i++) {
            TransformType childType = detectTransformTypeRecursive(tree.getChild(i));
            if (childType.ordinal() > maxType.ordinal()) {
                maxType = childType;
            }
        }
        return maxType;
    }

    private boolean isAggregateFunction(String name) {
        return "COUNT".equals(name) || "SUM".equals(name) || "AVG".equals(name)
                || "MAX".equals(name) || "MIN".equals(name);
    }

    /**
     * 解析表别名，返回真实表名
     */
    private String resolveTableAlias(String nameOrAlias) {
        return tableAliases.getOrDefault(nameOrAlias, nameOrAlias);
    }
}
