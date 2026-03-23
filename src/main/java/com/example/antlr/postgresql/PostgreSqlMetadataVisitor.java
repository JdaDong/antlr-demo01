package com.example.antlr.postgresql;

import org.antlr.v4.runtime.tree.ParseTree;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.*;

/**
 * PostgreSQL Visitor 实现：SQL 元数据提取器
 *
 * 通过 Visitor 模式遍历语法树，提取 SQL 中的元数据信息。
 * 支持 PostgreSQL 特有语法：CTE、Schema、ILIKE、::类型转换、RETURNING 等。
 */
public class PostgreSqlMetadataVisitor extends PostgreSqlParserBaseVisitor<Void> {

    private String statementType;
    private final Set<String> tables = new LinkedHashSet<>();
    private final Set<String> columns = new LinkedHashSet<>();
    private final Map<String, String> aliases = new LinkedHashMap<>();
    private final List<String> functions = new ArrayList<>();
    private final Set<String> whereColumns = new LinkedHashSet<>();
    private final List<String> joinConditions = new ArrayList<>();
    private final List<String> orderByColumns = new ArrayList<>();
    private final List<String> groupByColumns = new ArrayList<>();
    private final List<String> cteNames = new ArrayList<>();
    private final Set<String> schemas = new LinkedHashSet<>();
    private boolean hasSubQuery = false;
    private boolean hasDistinct = false;
    private boolean hasUnion = false;
    private boolean hasCte = false;
    private boolean hasReturning = false;
    private boolean inWhereClause = false;

    // ============ CTE ============

    @Override
    public Void visitWithClause(PostgreSqlParser.WithClauseContext ctx) {
        hasCte = true;
        return visitChildren(ctx);
    }

    @Override
    public Void visitCteDefinition(PostgreSqlParser.CteDefinitionContext ctx) {
        if (ctx.cteName() != null) {
            cteNames.add(ctx.cteName().getText());
        }
        return visitChildren(ctx);
    }

    // ============ DDL ============

    @Override
    public Void visitCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx) {
        statementType = "CREATE_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateSchema(PostgreSqlParser.CreateSchemaContext ctx) {
        statementType = "CREATE_SCHEMA";
        if (ctx.schemaName() != null) {
            schemas.add(ctx.schemaName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropDatabase(PostgreSqlParser.DropDatabaseContext ctx) {
        statementType = "DROP_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateTable(PostgreSqlParser.CreateTableContext ctx) {
        statementType = "CREATE_TABLE";
        if (ctx.tableName() != null && !ctx.tableName().isEmpty()) tables.add(ctx.tableName(0).getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropTable(PostgreSqlParser.DropTableContext ctx) {
        statementType = "DROP_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitAlterTable(PostgreSqlParser.AlterTableContext ctx) {
        statementType = "ALTER_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateIndex(PostgreSqlParser.CreateIndexContext ctx) {
        statementType = "CREATE_INDEX";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropIndex(PostgreSqlParser.DropIndexContext ctx) {
        statementType = "DROP_INDEX";
        return visitChildren(ctx);
    }

    // ============ DML ============

    @Override
    public Void visitSelectStatement(PostgreSqlParser.SelectStatementContext ctx) {
        if (statementType == null) statementType = "SELECT";
        if (ctx.DISTINCT() != null) hasDistinct = true;
        if (ctx.UNION() != null) hasUnion = true;
        if (ctx.getParent() instanceof PostgreSqlParser.TableSourceContext
                || ctx.getParent() instanceof PostgreSqlParser.InExpressionContext) {
            hasSubQuery = true;
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitInsertStatement(PostgreSqlParser.InsertStatementContext ctx) {
        statementType = "INSERT";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        if (ctx.columnNameList() != null) {
            for (PostgreSqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
                columns.add(col.getText());
            }
        }
        if (ctx.RETURNING() != null) hasReturning = true;
        return visitChildren(ctx);
    }

    @Override
    public Void visitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx) {
        statementType = "UPDATE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        if (ctx.RETURNING() != null) hasReturning = true;
        return visitChildren(ctx);
    }

    @Override
    public Void visitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx) {
        statementType = "DELETE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        if (ctx.RETURNING() != null) hasReturning = true;
        return visitChildren(ctx);
    }

    // ============ FROM / JOIN ============

    @Override
    public Void visitTableSource(PostgreSqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) {
            String tableName = ctx.tableName().getText();
            tables.add(tableName);
            if (ctx.alias() != null) {
                aliases.put(ctx.alias().getText(), tableName);
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitJoinClause(PostgreSqlParser.JoinClauseContext ctx) {
        if (ctx.ON() != null) {
            String joinType = ctx.joinType() != null ? ctx.joinType().getText() : "INNER";
            String joinTable = ctx.tableSource() != null ? ctx.tableSource().getText() : "?";
            joinConditions.add(joinType + " JOIN " + joinTable);
        }
        return visitChildren(ctx);
    }

    // ============ Column ============

    @Override
    public Void visitColumnRefExpression(PostgreSqlParser.ColumnRefExpressionContext ctx) {
        PostgreSqlParser.ColumnRefContext colRef = ctx.columnRef();
        if (colRef != null) {
            String colText = colRef.getText();
            columns.add(colText);
            if (inWhereClause) whereColumns.add(colText);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitColumnDefinition(PostgreSqlParser.ColumnDefinitionContext ctx) {
        if (ctx.columnName() != null) columns.add(ctx.columnName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitSelectElement(PostgreSqlParser.SelectElementContext ctx) {
        if (ctx.alias() != null) {
            aliases.put(ctx.alias().getText(), ctx.expression().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitWhereClause(PostgreSqlParser.WhereClauseContext ctx) {
        inWhereClause = true;
        visitChildren(ctx);
        inWhereClause = false;
        return null;
    }

    @Override
    public Void visitGroupByClause(PostgreSqlParser.GroupByClauseContext ctx) {
        if (ctx.expressionList() != null) {
            for (PostgreSqlParser.ExpressionContext expr : ctx.expressionList().expression()) {
                groupByColumns.add(expr.getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitOrderByElement(PostgreSqlParser.OrderByElementContext ctx) {
        String col = ctx.expression().getText();
        String dir = ctx.ASC() != null ? "ASC" : (ctx.DESC() != null ? "DESC" : "ASC");
        String nulls = "";
        if (ctx.NULLS() != null) {
            nulls = ctx.FIRST() != null ? " NULLS FIRST" : " NULLS LAST";
        }
        orderByColumns.add(col + " " + dir + nulls);
        return visitChildren(ctx);
    }

    @Override
    public Void visitFunctionCall(PostgreSqlParser.FunctionCallContext ctx) {
        if (ctx.functionName() != null) {
            functions.add(ctx.functionName().getText().toUpperCase());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitUpdateAssignment(PostgreSqlParser.UpdateAssignmentContext ctx) {
        if (ctx.columnRef() != null) columns.add(ctx.columnRef().getText());
        return visitChildren(ctx);
    }

    // ============ Getters ============

    public String getStatementType() { return statementType; }
    public Set<String> getTables() { return ImmutableSet.copyOf(tables); }
    public Set<String> getColumns() { return ImmutableSet.copyOf(columns); }
    public Map<String, String> getAliases() { return ImmutableMap.copyOf(aliases); }
    public List<String> getFunctions() { return ImmutableList.copyOf(functions); }
    public Set<String> getWhereColumns() { return ImmutableSet.copyOf(whereColumns); }
    public List<String> getJoinConditions() { return ImmutableList.copyOf(joinConditions); }
    public List<String> getOrderByColumns() { return ImmutableList.copyOf(orderByColumns); }
    public List<String> getGroupByColumns() { return ImmutableList.copyOf(groupByColumns); }
    public List<String> getCteNames() { return ImmutableList.copyOf(cteNames); }
    public Set<String> getSchemas() { return ImmutableSet.copyOf(schemas); }
    public boolean isHasSubQuery() { return hasSubQuery; }
    public boolean isHasDistinct() { return hasDistinct; }
    public boolean isHasUnion() { return hasUnion; }
    public boolean isHasCte() { return hasCte; }
    public boolean isHasReturning() { return hasReturning; }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== PostgreSQL SQL 元数据 ===\n");
        sb.append("语句类型: ").append(statementType).append("\n");
        if (!tables.isEmpty()) sb.append("表: ").append(String.join(", ", tables)).append("\n");
        if (!columns.isEmpty()) sb.append("列: ").append(String.join(", ", columns)).append("\n");
        if (!aliases.isEmpty()) {
            sb.append("别名: ");
            aliases.forEach((k, v) -> sb.append(k).append(" → ").append(v).append(", "));
            sb.setLength(sb.length() - 2);
            sb.append("\n");
        }
        if (!functions.isEmpty()) sb.append("函数: ").append(String.join(", ", functions)).append("\n");
        if (!whereColumns.isEmpty()) sb.append("WHERE 列: ").append(String.join(", ", whereColumns)).append("\n");
        if (!joinConditions.isEmpty()) sb.append("JOIN: ").append(String.join("; ", joinConditions)).append("\n");
        if (!groupByColumns.isEmpty()) sb.append("GROUP BY: ").append(String.join(", ", groupByColumns)).append("\n");
        if (!orderByColumns.isEmpty()) sb.append("ORDER BY: ").append(String.join(", ", orderByColumns)).append("\n");
        if (!cteNames.isEmpty()) sb.append("CTE: ").append(String.join(", ", cteNames)).append("\n");
        if (!schemas.isEmpty()) sb.append("Schema: ").append(String.join(", ", schemas)).append("\n");

        List<String> flags = new ArrayList<>();
        if (hasDistinct) flags.add("DISTINCT");
        if (hasSubQuery) flags.add("子查询");
        if (hasUnion) flags.add("UNION");
        if (hasCte) flags.add("CTE");
        if (hasReturning) flags.add("RETURNING");
        if (!flags.isEmpty()) sb.append("特性: ").append(String.join(", ", flags)).append("\n");

        return sb.toString();
    }

    @Override
    public String toString() { return getSummary(); }
}
