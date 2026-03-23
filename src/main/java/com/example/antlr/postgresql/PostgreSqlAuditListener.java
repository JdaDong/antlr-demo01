package com.example.antlr.postgresql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;

/**
 * PostgreSQL Listener 实现：SQL 审计收集器
 *
 * 通过 Listener 模式遍历语法树，自动收集 SQL 执行审计信息。
 * 支持 PostgreSQL 特有审计：Schema 操作、索引管理、RETURNING 追踪、CTE 检查等。
 */
public class PostgreSqlAuditListener extends PostgreSqlParserBaseListener {

    private final List<AuditEvent> events = new ArrayList<>();
    private final Map<String, Set<String>> tableAccess = new LinkedHashMap<>();
    private final List<String> warnings = new ArrayList<>();
    private String currentStatementType;
    private int selectDepth = 0;
    private final List<String> errors = new ArrayList<>();

    public static class AuditEvent {
        private final String type;
        private final String detail;
        private final int line;
        private final int column;

        public AuditEvent(String type, String detail, int line, int column) {
            this.type = type;
            this.detail = detail;
            this.line = line;
            this.column = column;
        }

        public String getType() { return type; }
        public String getDetail() { return detail; }
        public int getLine() { return line; }
        public int getColumn() { return column; }

        @Override
        public String toString() {
            return String.format("[%s] %s (行:%d, 列:%d)", type, detail, line, column);
        }
    }

    // ============ DDL ============

    @Override
    public void enterCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx) {
        currentStatementType = "CREATE_DATABASE";
        addEvent("DDL", "创建数据库: " + ctx.databaseName().getText(), ctx);
    }

    @Override
    public void enterDropDatabase(PostgreSqlParser.DropDatabaseContext ctx) {
        currentStatementType = "DROP_DATABASE";
        String dbName = ctx.databaseName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除数据库: " + dbName, ctx);
        warnings.add("危险操作：DROP DATABASE " + dbName);
    }

    @Override
    public void enterCreateSchema(PostgreSqlParser.CreateSchemaContext ctx) {
        currentStatementType = "CREATE_SCHEMA";
        addEvent("DDL", "创建 Schema: " + ctx.schemaName().getText(), ctx);
    }

    @Override
    public void enterCreateTable(PostgreSqlParser.CreateTableContext ctx) {
        currentStatementType = "CREATE_TABLE";
        String tableName = (ctx.tableName() != null && !ctx.tableName().isEmpty()) ? ctx.tableName(0).getText() : "unknown";
        addEvent("DDL", "创建表: " + tableName, ctx);
        addTableAccess(tableName, "CREATE");
    }

    @Override
    public void exitCreateTable(PostgreSqlParser.CreateTableContext ctx) {
        int elementCount = ctx.tableElement().size();
        String tableName = (ctx.tableName() != null && !ctx.tableName().isEmpty()) ? ctx.tableName(0).getText() : "unknown";
        addEvent("DDL_INFO", "表 " + tableName + " 共 " + elementCount + " 个元素", ctx);
    }

    @Override
    public void enterDropTable(PostgreSqlParser.DropTableContext ctx) {
        currentStatementType = "DROP_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除表: " + tableName, ctx);
        addTableAccess(tableName, "DROP");
        warnings.add("危险操作：DROP TABLE " + tableName);
    }

    @Override
    public void enterAlterTable(PostgreSqlParser.AlterTableContext ctx) {
        currentStatementType = "ALTER_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "修改表: " + tableName, ctx);
        addTableAccess(tableName, "ALTER");
    }

    @Override
    public void enterCreateIndex(PostgreSqlParser.CreateIndexContext ctx) {
        currentStatementType = "CREATE_INDEX";
        String indexName = ctx.indexName().getText();
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "创建索引: " + indexName + " ON " + tableName, ctx);
        addTableAccess(tableName, "CREATE_INDEX");
    }

    @Override
    public void enterDropIndex(PostgreSqlParser.DropIndexContext ctx) {
        currentStatementType = "DROP_INDEX";
        addEvent("DDL", "删除索引: " + ctx.indexName().getText(), ctx);
    }

    // ============ DML ============

    @Override
    public void enterSelectStatement(PostgreSqlParser.SelectStatementContext ctx) {
        selectDepth++;
        if (selectDepth == 1) {
            currentStatementType = "SELECT";
            addEvent("DML", "查询语句", ctx);
        } else {
            addEvent("DML_INFO", "子查询 (深度: " + selectDepth + ")", ctx);
        }
        if (ctx.selectElements() != null && ctx.selectElements().STAR() != null) {
            warnings.add("建议：避免使用 SELECT *，请明确指定列名");
        }
    }

    @Override
    public void exitSelectStatement(PostgreSqlParser.SelectStatementContext ctx) {
        selectDepth--;
    }

    @Override
    public void enterInsertStatement(PostgreSqlParser.InsertStatementContext ctx) {
        currentStatementType = "INSERT";
        String tableName = ctx.tableName().getText();
        addEvent("DML", "插入数据: " + tableName, ctx);
        addTableAccess(tableName, "INSERT");
    }

    @Override
    public void enterUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx) {
        currentStatementType = "UPDATE";
        String tableName = ctx.tableName().getText();
        addEvent("DML", "更新数据: " + tableName, ctx);
        addTableAccess(tableName, "UPDATE");
    }

    @Override
    public void exitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx) {
        if (ctx.whereClause() == null) {
            warnings.add("危险操作：UPDATE " + ctx.tableName().getText() + " 没有 WHERE 条件！");
        }
    }

    @Override
    public void enterDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx) {
        currentStatementType = "DELETE";
        String tableName = ctx.tableName().getText();
        addEvent("DML_DANGER", "⚠️ 删除数据: " + tableName, ctx);
        addTableAccess(tableName, "DELETE");
    }

    @Override
    public void exitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx) {
        if (ctx.whereClause() == null) {
            warnings.add("危险操作：DELETE FROM " + ctx.tableName().getText() + " 没有 WHERE 条件！");
        }
    }

    // ============ 表访问 ============

    @Override
    public void enterTableSource(PostgreSqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) {
            addTableAccess(ctx.tableName().getText(), "READ");
        }
    }

    @Override
    public void enterJoinClause(PostgreSqlParser.JoinClauseContext ctx) {
        String joinType = ctx.joinType() != null ? ctx.joinType().getText().toUpperCase() : "INNER";
        addEvent("DML_INFO", joinType + " JOIN", ctx);
    }

    @Override
    public void enterLimitClause(PostgreSqlParser.LimitClauseContext ctx) {
        addEvent("DML_INFO", "LIMIT 子句: " + ctx.getText(), ctx);
    }

    @Override
    public void enterWithClause(PostgreSqlParser.WithClauseContext ctx) {
        addEvent("DML_INFO", "CTE (WITH) 子句", ctx);
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        errors.add("语法错误: " + node.getText());
    }

    // ============ 辅助 ============

    private void addEvent(String type, String detail, ParserRuleContext ctx) {
        int line = ctx.getStart() != null ? ctx.getStart().getLine() : 0;
        int column = ctx.getStart() != null ? ctx.getStart().getCharPositionInLine() : 0;
        events.add(new AuditEvent(type, detail, line, column));
    }

    private void addTableAccess(String tableName, String operation) {
        tableAccess.computeIfAbsent(tableName, k -> new LinkedHashSet<>()).add(operation);
    }

    // ============ Getters ============

    public List<AuditEvent> getEvents() { return ImmutableList.copyOf(events); }
    public Map<String, Set<String>> getTableAccess() { return ImmutableMap.copyOf(tableAccess); }
    public List<String> getWarnings() { return ImmutableList.copyOf(warnings); }
    public String getCurrentStatementType() { return currentStatementType; }
    public List<String> getErrors() { return ImmutableList.copyOf(errors); }

    public boolean hasDangerousOperations() {
        return events.stream().anyMatch(e -> e.getType().contains("DANGER"));
    }

    public String getAuditReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║        PostgreSQL SQL 审计报告             ║\n");
        sb.append("╚══════════════════════════════════════════╝\n\n");

        sb.append("📋 事件日志 (").append(events.size()).append(" 条):\n");
        for (AuditEvent event : events) sb.append("  ").append(event).append("\n");

        if (!tableAccess.isEmpty()) {
            sb.append("\n📊 表访问统计:\n");
            tableAccess.forEach((table, ops) ->
                sb.append("  ").append(table).append(": ").append(String.join(", ", ops)).append("\n"));
        }

        if (!warnings.isEmpty()) {
            sb.append("\n⚠️ 风险告警 (").append(warnings.size()).append(" 条):\n");
            for (String warning : warnings) sb.append("  🔸 ").append(warning).append("\n");
        }

        if (!errors.isEmpty()) {
            sb.append("\n❌ 错误 (").append(errors.size()).append(" 条):\n");
            for (String error : errors) sb.append("  ").append(error).append("\n");
        }

        sb.append("\n🔒 安全评估: ");
        if (hasDangerousOperations()) sb.append("⛔ 包含危险操作，请审核！\n");
        else if (!warnings.isEmpty()) sb.append("⚠️ 有优化建议\n");
        else sb.append("✅ 安全\n");

        return sb.toString();
    }

    @Override
    public String toString() { return getAuditReport(); }
}
