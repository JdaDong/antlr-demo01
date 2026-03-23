package com.example.antlr.sparksql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.*;

/**
 * SparkSQL Listener 实现：SQL 审计收集器
 *
 * 通过 Listener 模式遍历语法树，自动收集 SQL 执行审计信息。
 * 支持 SparkSQL 特有审计：VIEW 管理、OVERWRITE 检测、CACHE/UNCACHE、分区操作等。
 */
public class SparkSqlAuditListener extends SparkSqlParserBaseListener {

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
            this.type = type; this.detail = detail; this.line = line; this.column = column;
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
    public void enterCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx) {
        currentStatementType = "CREATE_DATABASE";
        addEvent("DDL", "创建数据库: " + ctx.databaseName().getText(), ctx);
    }

    @Override
    public void enterDropDatabase(SparkSqlParser.DropDatabaseContext ctx) {
        currentStatementType = "DROP_DATABASE";
        String dbName = ctx.databaseName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除数据库: " + dbName, ctx);
        warnings.add("危险操作：DROP DATABASE " + dbName);
        if (ctx.CASCADE() != null) {
            warnings.add("危险操作：DROP DATABASE CASCADE 将删除所有表！");
        }
    }

    @Override
    public void enterCreateTable(SparkSqlParser.CreateTableContext ctx) {
        currentStatementType = "CREATE_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "创建表: " + tableName, ctx);
        addTableAccess(tableName, "CREATE");
    }

    @Override
    public void exitCreateTable(SparkSqlParser.CreateTableContext ctx) {
        int colCount = ctx.columnDefinition().size();
        if (colCount > 0) {
            addEvent("DDL_INFO", "表 " + ctx.tableName().getText() + " 共 " + colCount + " 列", ctx);
        }
    }

    @Override
    public void enterDropTable(SparkSqlParser.DropTableContext ctx) {
        currentStatementType = "DROP_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除表: " + tableName, ctx);
        addTableAccess(tableName, "DROP");
        warnings.add("危险操作：DROP TABLE " + tableName);
    }

    @Override
    public void enterCreateView(SparkSqlParser.CreateViewContext ctx) {
        currentStatementType = "CREATE_VIEW";
        addEvent("DDL", "创建视图: " + ctx.viewName().getText(), ctx);
    }

    @Override
    public void enterDropView(SparkSqlParser.DropViewContext ctx) {
        currentStatementType = "DROP_VIEW";
        addEvent("DDL", "删除视图: " + ctx.viewName().getText(), ctx);
    }

    @Override
    public void enterRenameTable(SparkSqlParser.RenameTableContext ctx) {
        currentStatementType = "RENAME_TABLE";
        addEvent("DDL", "重命名表: " + ctx.tableName(0).getText() + " → " + ctx.tableName(1).getText(), ctx);
    }

    @Override
    public void enterAddColumns(SparkSqlParser.AddColumnsContext ctx) {
        currentStatementType = "ADD_COLUMNS";
        addEvent("DDL", "添加列: " + ctx.tableName().getText(), ctx);
        addTableAccess(ctx.tableName().getText(), "ALTER");
    }

    @Override
    public void enterDropColumn(SparkSqlParser.DropColumnContext ctx) {
        currentStatementType = "DROP_COLUMN";
        addEvent("DDL", "删除列: " + ctx.tableName().getText() + "." + ctx.columnName().getText(), ctx);
        addTableAccess(ctx.tableName().getText(), "ALTER");
    }

    // ============ DML ============

    @Override
    public void enterSelectStatement(SparkSqlParser.SelectStatementContext ctx) {
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
    public void exitSelectStatement(SparkSqlParser.SelectStatementContext ctx) {
        selectDepth--;
    }

    @Override
    public void enterInsertStatement(SparkSqlParser.InsertStatementContext ctx) {
        currentStatementType = "INSERT";
        String tableName = ctx.tableName().getText();
        if (ctx.OVERWRITE() != null) {
            addEvent("DML_DANGER", "⚠️ INSERT OVERWRITE: " + tableName, ctx);
            addTableAccess(tableName, "OVERWRITE");
            warnings.add("INSERT OVERWRITE 将覆盖表 " + tableName + " 的数据");
        } else {
            addEvent("DML", "插入数据: " + tableName, ctx);
            addTableAccess(tableName, "INSERT");
        }
    }

    // ============ Utility ============

    @Override
    public void enterExplainStatement(SparkSqlParser.ExplainStatementContext ctx) {
        currentStatementType = "EXPLAIN";
        addEvent("UTILITY", "EXPLAIN 分析", ctx);
    }

    @Override
    public void enterCacheStatement(SparkSqlParser.CacheStatementContext ctx) {
        currentStatementType = "CACHE";
        addEvent("UTILITY", "CACHE TABLE: " + ctx.tableName().getText(), ctx);
    }

    @Override
    public void enterUncacheStatement(SparkSqlParser.UncacheStatementContext ctx) {
        currentStatementType = "UNCACHE";
        addEvent("UTILITY", "UNCACHE TABLE: " + ctx.tableName().getText(), ctx);
    }

    @Override
    public void enterRefreshStatement(SparkSqlParser.RefreshStatementContext ctx) {
        currentStatementType = "REFRESH";
        addEvent("UTILITY", "REFRESH TABLE: " + ctx.tableName().getText(), ctx);
    }

    // ============ 表访问 ============

    @Override
    public void enterTableSource(SparkSqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) addTableAccess(ctx.tableName().getText(), "READ");
    }

    @Override
    public void enterJoinClause(SparkSqlParser.JoinClauseContext ctx) {
        String joinType = ctx.joinType() != null ? ctx.joinType().getText().toUpperCase() : "INNER";
        addEvent("DML_INFO", joinType + " JOIN", ctx);
    }

    @Override
    public void enterLimitClause(SparkSqlParser.LimitClauseContext ctx) {
        addEvent("DML_INFO", "LIMIT 子句: " + ctx.getText(), ctx);
    }

    @Override
    public void enterWithClause(SparkSqlParser.WithClauseContext ctx) {
        addEvent("DML_INFO", "CTE (WITH) 子句", ctx);
    }

    @Override
    public void enterLateralViewClause(SparkSqlParser.LateralViewClauseContext ctx) {
        addEvent("DML_INFO", "LATERAL VIEW", ctx);
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
        sb.append("║          SparkSQL SQL 审计报告             ║\n");
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
