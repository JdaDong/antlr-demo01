package com.example.antlr.mysql;

import org.antlr.v4.runtime.tree.ParseTree;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.*;

/**
 * MySQL Visitor 实现：SQL 元数据提取器
 * 
 * 继承 MySqlParserBaseVisitor，通过 Visitor 模式遍历语法树，
 * 提取 SQL 中的表名、列名、别名、SQL 类型、函数调用等元数据信息。
 * 
 * Visitor 模式的特点：
 * - 每个 visit 方法有返回值（这里用 Void，通过成员变量收集结果）
 * - 你可以手动控制遍历哪些子树（可以跳过不关心的部分）
 * - 适合做信息提取、表达式求值、SQL 转换等
 */
public class MySqlMetadataVisitor extends MySqlParserBaseVisitor<Void> {

    // ============ 提取结果 ============

    /** SQL 语句类型（SELECT, INSERT, UPDATE, DELETE, CREATE_TABLE 等） */
    private String statementType;

    /** 引用到的表名集合（包括 FROM、JOIN、INSERT INTO 等） */
    private final Set<String> tables = new LinkedHashSet<>();

    /** 引用到的列名集合 */
    private final Set<String> columns = new LinkedHashSet<>();

    /** 别名映射：alias -> 原始名 */
    private final Map<String, String> aliases = new LinkedHashMap<>();

    /** 函数调用列表 */
    private final List<String> functions = new ArrayList<>();

    /** WHERE 条件中引用的列 */
    private final Set<String> whereColumns = new LinkedHashSet<>();

    /** JOIN 条件 */
    private final List<String> joinConditions = new ArrayList<>();

    /** ORDER BY 列 */
    private final List<String> orderByColumns = new ArrayList<>();

    /** GROUP BY 列 */
    private final List<String> groupByColumns = new ArrayList<>();

    /** 是否有子查询 */
    private boolean hasSubQuery = false;

    /** 是否有 DISTINCT */
    private boolean hasDistinct = false;

    /** 是否有 UNION */
    private boolean hasUnion = false;

    // 上下文标记：当前是否在 WHERE 子句中
    private boolean inWhereClause = false;

    // ============ DDL 语句 ============

    @Override
    public Void visitCreateDatabase(MySqlParser.CreateDatabaseContext ctx) {
        statementType = "CREATE_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropDatabase(MySqlParser.DropDatabaseContext ctx) {
        statementType = "DROP_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateTable(MySqlParser.CreateTableContext ctx) {
        statementType = "CREATE_TABLE";
        // 提取表名
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        // 提取列定义
        for (MySqlParser.ColumnDefinitionContext colDef : ctx.columnDefinition()) {
            columns.add(colDef.columnName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropTable(MySqlParser.DropTableContext ctx) {
        statementType = "DROP_TABLE";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitAlterTable(MySqlParser.AlterTableContext ctx) {
        statementType = "ALTER_TABLE";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitTruncateTable(MySqlParser.TruncateTableContext ctx) {
        statementType = "TRUNCATE_TABLE";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateIndex(MySqlParser.CreateIndexContext ctx) {
        statementType = "CREATE_INDEX";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropIndex(MySqlParser.DropIndexContext ctx) {
        statementType = "DROP_INDEX";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    // ============ DML 语句 ============

    @Override
    public Void visitSelectStatement(MySqlParser.SelectStatementContext ctx) {
        if (statementType == null) {
            statementType = "SELECT";
        }

        // 检查 DISTINCT
        if (ctx.DISTINCT() != null) {
            hasDistinct = true;
        }

        // 检查 UNION
        if (ctx.UNION() != null) {
            hasUnion = true;
        }

        // 检查子查询：如果 selectStatement 出现在 tableSource 内部，就是子查询
        if (ctx.getParent() instanceof MySqlParser.TableSourceContext
                || ctx.getParent() instanceof MySqlParser.InExpressionContext) {
            hasSubQuery = true;
        }

        return visitChildren(ctx);
    }

    @Override
    public Void visitInsertStatement(MySqlParser.InsertStatementContext ctx) {
        statementType = "INSERT";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        // 提取指定的列名
        if (ctx.columnNameList() != null) {
            for (MySqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
                columns.add(col.getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        statementType = "UPDATE";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        statementType = "DELETE";
        if (ctx.tableName() != null) {
            tables.add(ctx.tableName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitShowStatement(MySqlParser.ShowStatementContext ctx) {
        statementType = "SHOW";
        return visitChildren(ctx);
    }

    @Override
    public Void visitUseStatement(MySqlParser.UseStatementContext ctx) {
        statementType = "USE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitDescribeStatement(MySqlParser.DescribeStatementContext ctx) {
        statementType = "DESCRIBE";
        return visitChildren(ctx);
    }

    // ============ FROM / JOIN 中的表名 ============

    @Override
    public Void visitTableSource(MySqlParser.TableSourceContext ctx) {
        // 提取 FROM 子句中的表名
        if (ctx.tableName() != null) {
            String tableName = ctx.tableName().getText();
            tables.add(tableName);

            // 提取别名
            if (ctx.alias() != null) {
                String aliasName = ctx.alias().getText();
                aliases.put(aliasName, tableName);
            }
        }

        // 子查询中的 selectStatement 由 visitSelectStatement 处理
        return visitChildren(ctx);
        
    }

    @Override
    public Void visitJoinClause(MySqlParser.JoinClauseContext ctx) {
        // 收集 JOIN 条件文本
        if (ctx.ON() != null) {
            String joinType = ctx.joinType() != null ? ctx.joinType().getText() : "INNER";
            String joinTable = ctx.tableSource() != null ? ctx.tableSource().getText() : "?";
            joinConditions.add(joinType + " JOIN " + joinTable);
        }
        return visitChildren(ctx);
    }

    // ============ 列引用 ============

    @Override
    public Void visitColumnRefExpression(MySqlParser.ColumnRefExpressionContext ctx) {
        MySqlParser.ColumnRefContext colRef = ctx.columnRef();
        if (colRef != null) {
            String colText = colRef.getText();
            columns.add(colText);

            if (inWhereClause) {
                whereColumns.add(colText);
            }
        }
        return visitChildren(ctx);
    }

    // ============ SELECT 元素中的别名 ============

    @Override
    public Void visitSelectElement(MySqlParser.SelectElementContext ctx) {
        if (ctx.alias() != null) {
            String aliasName = ctx.alias().getText();
            String original = ctx.expression().getText();
            aliases.put(aliasName, original);
        }
        return visitChildren(ctx);
    }

    // ============ WHERE 子句（设置上下文标记） ============

    @Override
    public Void visitWhereClause(MySqlParser.WhereClauseContext ctx) {
        inWhereClause = true;
        visitChildren(ctx);
        inWhereClause = false;
        return null;
    }

    // ============ GROUP BY / ORDER BY ============

    @Override
    public Void visitGroupByClause(MySqlParser.GroupByClauseContext ctx) {
        if (ctx.expressionList() != null) {
            for (MySqlParser.ExpressionContext expr : ctx.expressionList().expression()) {
                groupByColumns.add(expr.getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitOrderByElement(MySqlParser.OrderByElementContext ctx) {
        String col = ctx.expression().getText();
        String dir = ctx.ASC() != null ? "ASC" : (ctx.DESC() != null ? "DESC" : "ASC");
        orderByColumns.add(col + " " + dir);
        return visitChildren(ctx);
    }

    // ============ 函数调用 ============

    @Override
    public Void visitFunctionCall(MySqlParser.FunctionCallContext ctx) {
        if (ctx.functionName() != null) {
            functions.add(ctx.functionName().getText().toUpperCase());
        }
        return visitChildren(ctx);
    }

    // ============ UPDATE 赋值 ============

    @Override
    public Void visitUpdateAssignment(MySqlParser.UpdateAssignmentContext ctx) {
        if (ctx.columnRef() != null) {
            columns.add(ctx.columnRef().getText());
        }
        return visitChildren(ctx);
    }

    // ============ 结果获取方法 ============

    public String getStatementType() {
        return statementType;
    }

    public Set<String> getTables() {
        return ImmutableSet.copyOf(tables);
    }

    public Set<String> getColumns() {
        return ImmutableSet.copyOf(columns);
    }

    public Map<String, String> getAliases() {
        return ImmutableMap.copyOf(aliases);
    }

    public List<String> getFunctions() {
        return ImmutableList.copyOf(functions);
    }

    public Set<String> getWhereColumns() {
        return ImmutableSet.copyOf(whereColumns);
    }

    public List<String> getJoinConditions() {
        return ImmutableList.copyOf(joinConditions);
    }

    public List<String> getOrderByColumns() {
        return ImmutableList.copyOf(orderByColumns);
    }

    public List<String> getGroupByColumns() {
        return ImmutableList.copyOf(groupByColumns);
    }

    public boolean isHasSubQuery() {
        return hasSubQuery;
    }

    public boolean isHasDistinct() {
        return hasDistinct;
    }

    public boolean isHasUnion() {
        return hasUnion;
    }

    /**
     * 获取格式化的元数据摘要
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== MySQL SQL 元数据 ===\n");
        sb.append("语句类型: ").append(statementType).append("\n");

        if (!tables.isEmpty()) {
            sb.append("表: ").append(String.join(", ", tables)).append("\n");
        }
        if (!columns.isEmpty()) {
            sb.append("列: ").append(String.join(", ", columns)).append("\n");
        }
        if (!aliases.isEmpty()) {
            sb.append("别名: ");
            aliases.forEach((k, v) -> sb.append(k).append(" → ").append(v).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        if (!functions.isEmpty()) {
            sb.append("函数: ").append(String.join(", ", functions)).append("\n");
        }
        if (!whereColumns.isEmpty()) {
            sb.append("WHERE 列: ").append(String.join(", ", whereColumns)).append("\n");
        }
        if (!joinConditions.isEmpty()) {
            sb.append("JOIN: ").append(String.join("; ", joinConditions)).append("\n");
        }
        if (!groupByColumns.isEmpty()) {
            sb.append("GROUP BY: ").append(String.join(", ", groupByColumns)).append("\n");
        }
        if (!orderByColumns.isEmpty()) {
            sb.append("ORDER BY: ").append(String.join(", ", orderByColumns)).append("\n");
        }

        List<String> flags = new ArrayList<>();
        if (hasDistinct) flags.add("DISTINCT");
        if (hasSubQuery) flags.add("子查询");
        if (hasUnion) flags.add("UNION");
        if (!flags.isEmpty()) {
            sb.append("特性: ").append(String.join(", ", flags)).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return getSummary();
    }
}
