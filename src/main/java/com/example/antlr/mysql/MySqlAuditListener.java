package com.example.antlr.mysql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

/**
 * MySQL Listener 实现：SQL 审计收集器
 *
 * 继承 MySqlParserBaseListener，通过 Listener 模式遍历语法树，
 * 自动收集 SQL 执行审计信息：表访问、数据操作、风险检查等。
 *
 * Listener 模式的特点：
 * - ParseTreeWalker 自动遍历整棵树，你不需要手动递归
 * - 每个规则有 enter（进入）和 exit（离开）两个回调
 * - 没有返回值，通过成员变量收集结果
 * - 适合做审计日志、格式化、统计分析等"全树扫描"的工作
 */
public class MySqlAuditListener extends MySqlParserBaseListener {

    // ============ 审计信息收集 ============

    /** 审计事件日志 */
    private final List<AuditEvent> events = new ArrayList<>();

    /** 访问的表及操作类型 */
    private final Map<String, Set<String>> tableAccess = new LinkedHashMap<>();

    /** 风险告警 */
    private final List<String> warnings = new ArrayList<>();

    /** 当前语句类型 */
    private String currentStatementType;

    /** 嵌套深度（用于跟踪子查询） */
    private int selectDepth = 0;

    /** 解析错误 */
    private final List<String> errors = new ArrayList<>();

    // ============ 审计事件类 ============

    public static class AuditEvent {
        private final String type;       // 事件类型
        private final String detail;     // 详细信息
        private final int line;          // 行号
        private final int column;        // 列号

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

    // ============ DDL 审计 ============

    @Override
    public void enterCreateDatabase(MySqlParser.CreateDatabaseContext ctx) {
        currentStatementType = "CREATE_DATABASE";
        String dbName = ctx.databaseName().getText();
        addEvent("DDL", "创建数据库: " + dbName, ctx);
    }

    @Override
    public void enterDropDatabase(MySqlParser.DropDatabaseContext ctx) {
        currentStatementType = "DROP_DATABASE";
        String dbName = ctx.databaseName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除数据库: " + dbName, ctx);
        warnings.add("危险操作：DROP DATABASE " + dbName);
    }

    @Override
    public void enterCreateTable(MySqlParser.CreateTableContext ctx) {
        currentStatementType = "CREATE_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "创建表: " + tableName, ctx);
        addTableAccess(tableName, "CREATE");
    }

    @Override
    public void exitCreateTable(MySqlParser.CreateTableContext ctx) {
        // 表创建完成后，统计列数
        int columnCount = ctx.columnDefinition().size();
        addEvent("DDL_INFO", "表 " + ctx.tableName().getText() + " 共 " + columnCount + " 列", ctx);
    }

    @Override
    public void enterDropTable(MySqlParser.DropTableContext ctx) {
        currentStatementType = "DROP_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL_DANGER", "⚠️ 删除表: " + tableName, ctx);
        addTableAccess(tableName, "DROP");
        warnings.add("危险操作：DROP TABLE " + tableName);
    }

    @Override
    public void enterAlterTable(MySqlParser.AlterTableContext ctx) {
        currentStatementType = "ALTER_TABLE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "修改表: " + tableName, ctx);
        addTableAccess(tableName, "ALTER");
    }

    @Override
    public void enterTruncateTable(MySqlParser.TruncateTableContext ctx) {
        currentStatementType = "TRUNCATE";
        String tableName = ctx.tableName().getText();
        addEvent("DDL_DANGER", "⚠️ 清空表: " + tableName, ctx);
        addTableAccess(tableName, "TRUNCATE");
        warnings.add("危险操作：TRUNCATE TABLE " + tableName);
    }

    @Override
    public void enterCreateIndex(MySqlParser.CreateIndexContext ctx) {
        currentStatementType = "CREATE_INDEX";
        String indexName = ctx.indexName().getText();
        String tableName = ctx.tableName().getText();
        addEvent("DDL", "创建索引: " + indexName + " ON " + tableName, ctx);
        addTableAccess(tableName, "CREATE_INDEX");
    }

    // ============ DML 审计 ============

    @Override
    public void enterSelectStatement(MySqlParser.SelectStatementContext ctx) {
        selectDepth++;

        if (selectDepth == 1) {
            currentStatementType = "SELECT";
            addEvent("DML", "查询语句", ctx);
        } else {
            addEvent("DML_INFO", "子查询 (深度: " + selectDepth + ")", ctx);
        }

        // 检查 SELECT *
        if (ctx.selectElements() != null && ctx.selectElements().STAR() != null) {
            warnings.add("建议：避免使用 SELECT *，请明确指定列名");
        }
    }

    @Override
    public void exitSelectStatement(MySqlParser.SelectStatementContext ctx) {
        selectDepth--;
    }

    @Override
    public void enterInsertStatement(MySqlParser.InsertStatementContext ctx) {
        currentStatementType = "INSERT";
        String tableName = ctx.tableName().getText();
        addEvent("DML", "插入数据: " + tableName, ctx);
        addTableAccess(tableName, "INSERT");
    }

    @Override
    public void enterUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        currentStatementType = "UPDATE";
        String tableName = ctx.tableName().getText();
        addEvent("DML", "更新数据: " + tableName, ctx);
        addTableAccess(tableName, "UPDATE");
    }

    @Override
    public void exitUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        // 检查 UPDATE 是否有 WHERE 条件
        if (ctx.whereClause() == null) {
            warnings.add("危险操作：UPDATE " + ctx.tableName().getText() + " 没有 WHERE 条件！");
        }
    }

    @Override
    public void enterDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        currentStatementType = "DELETE";
        String tableName = ctx.tableName().getText();
        addEvent("DML_DANGER", "⚠️ 删除数据: " + tableName, ctx);
        addTableAccess(tableName, "DELETE");
    }

    @Override
    public void exitDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        // 检查 DELETE 是否有 WHERE 条件
        if (ctx.whereClause() == null) {
            warnings.add("危险操作：DELETE FROM " + ctx.tableName().getText() + " 没有 WHERE 条件！");
        }
    }

    // ============ 表访问追踪 ============

    @Override
    public void enterTableSource(MySqlParser.TableSourceContext ctx) {
        if (ctx.tableName() != null) {
            String tableName = ctx.tableName().getText();
            addTableAccess(tableName, "READ");
        }
    }

    // ============ JOIN 追踪 ============

    @Override
    public void enterJoinClause(MySqlParser.JoinClauseContext ctx) {
        String joinType = ctx.joinType() != null ? ctx.joinType().getText().toUpperCase() : "INNER";
        addEvent("DML_INFO", joinType + " JOIN", ctx);
    }

    // ============ LIMIT 检查 ============

    @Override
    public void enterLimitClause(MySqlParser.LimitClauseContext ctx) {
        addEvent("DML_INFO", "LIMIT 子句: " + ctx.getText(), ctx);
    }

    // ============ 错误节点 ============

    @Override
    public void visitErrorNode(ErrorNode node) {
        errors.add("语法错误: " + node.getText());
    }

    // ============ 辅助方法 ============

    private void addEvent(String type, String detail, ParserRuleContext ctx) {
        int line = ctx.getStart() != null ? ctx.getStart().getLine() : 0;
        int column = ctx.getStart() != null ? ctx.getStart().getCharPositionInLine() : 0;
        events.add(new AuditEvent(type, detail, line, column));
    }

    private void addTableAccess(String tableName, String operation) {
        tableAccess.computeIfAbsent(tableName, k -> new LinkedHashSet<>()).add(operation);
    }

    // ============ 结果获取 ============

    public List<AuditEvent> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public Map<String, Set<String>> getTableAccess() {
        return Collections.unmodifiableMap(tableAccess);
    }

    public List<String> getWarnings() {
        return Collections.unmodifiableList(warnings);
    }

    public String getCurrentStatementType() {
        return currentStatementType;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean hasDangerousOperations() {
        return events.stream().anyMatch(e -> e.getType().contains("DANGER"));
    }

    /**
     * 获取格式化的审计报告
     */
    public String getAuditReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║           MySQL SQL 审计报告              ║\n");
        sb.append("╚══════════════════════════════════════════╝\n\n");

        // 1. 事件日志
        sb.append("📋 事件日志 (").append(events.size()).append(" 条):\n");
        for (AuditEvent event : events) {
            sb.append("  ").append(event).append("\n");
        }

        // 2. 表访问情况
        if (!tableAccess.isEmpty()) {
            sb.append("\n📊 表访问统计:\n");
            tableAccess.forEach((table, ops) -> {
                sb.append("  ").append(table).append(": ")
                  .append(String.join(", ", ops)).append("\n");
            });
        }

        // 3. 风险告警
        if (!warnings.isEmpty()) {
            sb.append("\n⚠️ 风险告警 (").append(warnings.size()).append(" 条):\n");
            for (String warning : warnings) {
                sb.append("  🔸 ").append(warning).append("\n");
            }
        }

        // 4. 错误
        if (!errors.isEmpty()) {
            sb.append("\n❌ 错误 (").append(errors.size()).append(" 条):\n");
            for (String error : errors) {
                sb.append("  ").append(error).append("\n");
            }
        }

        // 5. 安全评估
        sb.append("\n🔒 安全评估: ");
        if (hasDangerousOperations()) {
            sb.append("⛔ 包含危险操作，请审核！\n");
        } else if (!warnings.isEmpty()) {
            sb.append("⚠️ 有优化建议\n");
        } else {
            sb.append("✅ 安全\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return getAuditReport();
    }
}
