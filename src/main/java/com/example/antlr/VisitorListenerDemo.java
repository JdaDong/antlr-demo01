package com.example.antlr;

import com.example.antlr.mysql.MySqlMetadataVisitor;
import com.example.antlr.mysql.MySqlAuditListener;

/**
 * Visitor 和 Listener 使用示例
 * 
 * 演示如何使用：
 * 1. MySqlMetadataVisitor（Visitor 模式）— 提取 SQL 元数据
 * 2. MySqlAuditListener（Listener 模式）— SQL 审计分析
 */
public class VisitorListenerDemo {

    public static void main(String[] args) {
        SqlParserEngine engine = new SqlParserEngine();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║   ANTLR4 Visitor & Listener 模式演示          ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // ============================
        // 示例 1：Visitor 提取 SELECT 元数据
        // ============================
        String sql1 = "SELECT u.id, u.name, COUNT(o.id) AS order_count " +
                       "FROM users u " +
                       "LEFT JOIN orders o ON u.id = o.user_id " +
                       "WHERE u.status = 'active' AND u.age > 18 " +
                       "GROUP BY u.id, u.name " +
                       "ORDER BY order_count DESC " +
                       "LIMIT 10";

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("【Visitor 模式】提取 SELECT 语句元数据");
        System.out.println("SQL: " + sql1);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        MySqlMetadataVisitor metadata1 = engine.extractMySqlMetadata(sql1);
        System.out.println(metadata1.getSummary());

        // ============================
        // 示例 2：Visitor 提取 CREATE TABLE 元数据
        // ============================
        String sql2 = "CREATE TABLE IF NOT EXISTS products (" +
                       "  id INT AUTO_INCREMENT PRIMARY KEY," +
                       "  name VARCHAR(200) NOT NULL," +
                       "  price DECIMAL(10,2) DEFAULT 0.00," +
                       "  category_id INT," +
                       "  created_at TIMESTAMP," +
                       "  FOREIGN KEY (category_id) REFERENCES categories(id)" +
                       ") ENGINE=InnoDB";

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("【Visitor 模式】提取 CREATE TABLE 元数据");
        System.out.println("SQL: " + sql2);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        MySqlMetadataVisitor metadata2 = engine.extractMySqlMetadata(sql2);
        System.out.println(metadata2.getSummary());

        // ============================
        // 示例 3：Listener 审计安全的 SQL
        // ============================
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("【Listener 模式】审计安全 SQL");
        System.out.println("SQL: " + sql1);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        MySqlAuditListener audit1 = engine.auditMySql(sql1);
        System.out.println(audit1.getAuditReport());

        // ============================
        // 示例 4：Listener 审计危险 SQL
        // ============================
        String sql3 = "DELETE FROM users; " +
                       "DROP TABLE orders; " +
                       "UPDATE accounts SET balance = 0; " +
                       "TRUNCATE TABLE logs";

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("【Listener 模式】审计危险 SQL ⚠️");
        System.out.println("SQL: " + sql3);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        MySqlAuditListener audit2 = engine.auditMySql(sql3);
        System.out.println(audit2.getAuditReport());

        // ============================
        // 示例 5：对比两种模式
        // ============================
        String sql5 = "SELECT * FROM employees WHERE department = 'engineering'";

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("【对比】同一条 SQL 分别用 Visitor 和 Listener 处理");
        System.out.println("SQL: " + sql5);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        System.out.println(">>> Visitor 结果（元数据提取）:");
        MySqlMetadataVisitor vis = engine.extractMySqlMetadata(sql5);
        System.out.println("  类型: " + vis.getStatementType());
        System.out.println("  表: " + vis.getTables());
        System.out.println("  WHERE列: " + vis.getWhereColumns());
        System.out.println("  DISTINCT: " + vis.isHasDistinct());

        System.out.println("\n>>> Listener 结果（审计分析）:");
        MySqlAuditListener lis = engine.auditMySql(sql5);
        System.out.println("  事件数: " + lis.getEvents().size());
        System.out.println("  告警数: " + lis.getWarnings().size());
        if (!lis.getWarnings().isEmpty()) {
            lis.getWarnings().forEach(w -> System.out.println("    ⚠️ " + w));
        }
        System.out.println("  危险操作: " + lis.hasDangerousOperations());
        System.out.println();
    }
}
