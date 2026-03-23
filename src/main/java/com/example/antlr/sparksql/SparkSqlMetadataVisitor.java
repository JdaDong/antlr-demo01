package com.example.antlr.sparksql;

import org.antlr.v4.runtime.tree.ParseTree;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.*;

/**
 * SparkSQL Visitor 实现：SQL 元数据提取器
 *
 * 通过 Visitor 模式遍历语法树，提取 SQL 中的元数据信息。
 * 支持 SparkSQL 特有语法：CTE、VIEW、PARTITION、RLIKE、LATERAL VIEW、EXPLAIN 等。
 */
public class SparkSqlMetadataVisitor extends SparkSqlParserBaseVisitor<Void> {

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
    private final Set<String> views = new LinkedHashSet<>();
    private final Set<String> partitions = new LinkedHashSet<>();
    private boolean hasSubQuery = false;
    private boolean hasDistinct = false;
    private boolean hasUnion = false;
    private boolean hasCte = false;
    private boolean hasOverwrite = false;
    private boolean inWhereClause = false;

    // ============ CTE ============

    @Override
    public Void visitWithClause(SparkSqlParser.WithClauseContext ctx) {
        hasCte = true;
        return visitChildren(ctx);
    }

    @Override
    public Void visitCteDefinition(SparkSqlParser.CteDefinitionContext ctx) {
        if (ctx.cteName() != null) cteNames.add(ctx.cteName().getText());
        return visitChildren(ctx);
    }

    // ============ DDL ============

    @Override
    public Void visitCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx) {
        statementType = "CREATE_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropDatabase(SparkSqlParser.DropDatabaseContext ctx) {
        statementType = "DROP_DATABASE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateTable(SparkSqlParser.CreateTableContext ctx) {
        statementType = "CREATE_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        for (SparkSqlParser.ColumnDefinitionContext colDef : ctx.columnDefinition()) {
            columns.add(colDef.columnName().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropTable(SparkSqlParser.DropTableContext ctx) {
        statementType = "DROP_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitCreateView(SparkSqlParser.CreateViewContext ctx) {
        statementType = "CREATE_VIEW";
        if (ctx.viewName() != null) views.add(ctx.viewName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropView(SparkSqlParser.DropViewContext ctx) {
        statementType = "DROP_VIEW";
        if (ctx.viewName() != null) views.add(ctx.viewName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitRenameTable(SparkSqlParser.RenameTableContext ctx) {
        statementType = "RENAME_TABLE";
        for (SparkSqlParser.TableNameContext tn : ctx.tableName()) {
            tables.add(tn.getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitAddColumns(SparkSqlParser.AddColumnsContext ctx) {
        statementType = "ADD_COLUMNS";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitDropColumn(SparkSqlParser.DropColumnContext ctx) {
        statementType = "DROP_COLUMN";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    // ============ DML ============

    @Override
    public Void visitSelectStatement(SparkSqlParser.SelectStatementContext ctx) {
        if (statementType == null) statementType = "SELECT";
        if (ctx.DISTINCT() != null) hasDistinct = true;
        if (ctx.UNION() != null) hasUnion = true;
        if (ctx.getParent() instanceof SparkSqlParser.TableSourceContext
                || ctx.getParent() instanceof SparkSqlParser.InExpressionContext) {
            hasSubQuery = true;
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitInsertStatement(SparkSqlParser.InsertStatementContext ctx) {
        statementType = "INSERT";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        if (ctx.OVERWRITE() != null) hasOverwrite = true;
        if (ctx.columnNameList() != null) {
            for (SparkSqlParser.ColumnNameContext col : ctx.columnNameList().columnName()) {
                columns.add(col.getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitPartitionSpec(SparkSqlParser.PartitionSpecContext ctx) {
        for (SparkSqlParser.PartitionValContext pv : ctx.partitionVal()) {
            partitions.add(pv.getText());
        }
        return visitChildren(ctx);
    }

    // ============ Utility ============

    @Override
    public Void visitUseStatement(SparkSqlParser.UseStatementContext ctx) {
        statementType = "USE";
        return visitChildren(ctx);
    }

    @Override
    public Void visitShowStatement(SparkSqlParser.ShowStatementContext ctx) {
        statementType = "SHOW";
        return visitChildren(ctx);
    }

    @Override
    public Void visitDescribeStatement(SparkSqlParser.DescribeStatementContext ctx) {
        statementType = "DESCRIBE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitExplainStatement(SparkSqlParser.ExplainStatementContext ctx) {
        statementType = "EXPLAIN";
        return visitChildren(ctx);
    }

    @Override
    public Void visitCacheStatement(SparkSqlParser.CacheStatementContext ctx) {
        statementType = "CACHE_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    @Override
    public Void visitUncacheStatement(SparkSqlParser.UncacheStatementContext ctx) {
        statementType = "UNCACHE_TABLE";
        if (ctx.tableName() != null) tables.add(ctx.tableName().getText());
        return visitChildren(ctx);
    }

    // ============ FROM / JOIN ============

    @Override
    public Void visitTableSource(SparkSqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) {
            String tableName = ctx.tableName().getText();
            tables.add(tableName);
            if (ctx.alias() != null) aliases.put(ctx.alias().getText(), tableName);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitJoinClause(SparkSqlParser.JoinClauseContext ctx) {
        if (ctx.ON() != null) {
            String joinType = ctx.joinType() != null ? ctx.joinType().getText() : "INNER";
            String joinTable = ctx.tableSource() != null ? ctx.tableSource().getText() : "?";
            joinConditions.add(joinType + " JOIN " + joinTable);
        }
        return visitChildren(ctx);
    }

    // ============ Column ============

    @Override
    public Void visitColumnRefExpression(SparkSqlParser.ColumnRefExpressionContext ctx) {
        SparkSqlParser.ColumnRefContext colRef = ctx.columnRef();
        if (colRef != null) {
            String colText = colRef.getText();
            columns.add(colText);
            if (inWhereClause) whereColumns.add(colText);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitSelectElement(SparkSqlParser.SelectElementContext ctx) {
        if (ctx.alias() != null) {
            aliases.put(ctx.alias().getText(), ctx.expression().getText());
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitWhereClause(SparkSqlParser.WhereClauseContext ctx) {
        inWhereClause = true;
        visitChildren(ctx);
        inWhereClause = false;
        return null;
    }

    @Override
    public Void visitGroupByClause(SparkSqlParser.GroupByClauseContext ctx) {
        if (ctx.expressionList() != null) {
            for (SparkSqlParser.ExpressionContext expr : ctx.expressionList().expression()) {
                groupByColumns.add(expr.getText());
            }
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitOrderByElement(SparkSqlParser.OrderByElementContext ctx) {
        String col = ctx.expression().getText();
        String dir = ctx.ASC() != null ? "ASC" : (ctx.DESC() != null ? "DESC" : "ASC");
        orderByColumns.add(col + " " + dir);
        return visitChildren(ctx);
    }

    @Override
    public Void visitFunctionCall(SparkSqlParser.FunctionCallContext ctx) {
        if (ctx.functionName() != null) functions.add(ctx.functionName().getText().toUpperCase());
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
    public Set<String> getViews() { return ImmutableSet.copyOf(views); }
    public Set<String> getPartitions() { return ImmutableSet.copyOf(partitions); }
    public boolean isHasSubQuery() { return hasSubQuery; }
    public boolean isHasDistinct() { return hasDistinct; }
    public boolean isHasUnion() { return hasUnion; }
    public boolean isHasCte() { return hasCte; }
    public boolean isHasOverwrite() { return hasOverwrite; }

    public String getSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SparkSQL SQL 元数据 ===\n");
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
        if (!views.isEmpty()) sb.append("视图: ").append(String.join(", ", views)).append("\n");
        if (!partitions.isEmpty()) sb.append("分区: ").append(String.join(", ", partitions)).append("\n");

        List<String> flags = new ArrayList<>();
        if (hasDistinct) flags.add("DISTINCT");
        if (hasSubQuery) flags.add("子查询");
        if (hasUnion) flags.add("UNION");
        if (hasCte) flags.add("CTE");
        if (hasOverwrite) flags.add("OVERWRITE");
        if (!flags.isEmpty()) sb.append("特性: ").append(String.join(", ", flags)).append("\n");

        return sb.toString();
    }

    @Override
    public String toString() { return getSummary(); }
}
