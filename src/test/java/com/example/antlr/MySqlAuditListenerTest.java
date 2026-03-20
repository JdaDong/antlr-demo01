package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.example.antlr.mysql.MySqlAuditListener;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MySqlAuditListener 单元测试
 * 测试 Listener 模式下的 SQL 审计能力
 */
@DisplayName("MySqlAuditListener - Listener 模式 SQL 审计")
class MySqlAuditListenerTest {

    private SqlParserEngine engine;

    @BeforeEach
    void setUp() {
        engine = new SqlParserEngine();
    }

    // ================================================================
    // 表访问追踪
    // ================================================================

    @Nested
    @DisplayName("表访问追踪")
    class TableAccessTests {

        @Test
        @DisplayName("SELECT 应记录 READ 操作")
        void testSelectTracksRead() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT id, name FROM users WHERE age > 18");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("READ"), "users 应有 READ 操作");
        }

        @Test
        @DisplayName("INSERT 应记录 INSERT 操作")
        void testInsertTracksInsert() {
            MySqlAuditListener listener = engine.auditMySql(
                    "INSERT INTO users (name, email) VALUES ('Alice', 'alice@test.com')");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("INSERT"), "users 应有 INSERT 操作");
        }

        @Test
        @DisplayName("UPDATE 应记录 UPDATE 操作")
        void testUpdateTracksUpdate() {
            MySqlAuditListener listener = engine.auditMySql(
                    "UPDATE users SET name = 'Bob' WHERE id = 1");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("UPDATE"), "users 应有 UPDATE 操作");
        }

        @Test
        @DisplayName("DELETE 应记录 DELETE 操作")
        void testDeleteTracksDelete() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DELETE FROM users WHERE id = 1");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("DELETE"), "users 应有 DELETE 操作");
        }

        @Test
        @DisplayName("CREATE TABLE 应记录 CREATE 操作")
        void testCreateTableTracksCreate() {
            MySqlAuditListener listener = engine.auditMySql(
                    "CREATE TABLE orders (id INT PRIMARY KEY, amount DECIMAL(10,2))");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("orders"), "应追踪到 orders 表");
            assertTrue(access.get("orders").contains("CREATE"), "orders 应有 CREATE 操作");
        }

        @Test
        @DisplayName("DROP TABLE 应记录 DROP 操作")
        void testDropTableTracksDrop() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DROP TABLE IF EXISTS users");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("DROP"), "users 应有 DROP 操作");
        }

        @Test
        @DisplayName("ALTER TABLE 应记录 ALTER 操作")
        void testAlterTableTracksAlter() {
            MySqlAuditListener listener = engine.auditMySql(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(255)");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("ALTER"), "users 应有 ALTER 操作");
        }

        @Test
        @DisplayName("TRUNCATE 应记录 TRUNCATE 操作")
        void testTruncateTracksTruncate() {
            MySqlAuditListener listener = engine.auditMySql(
                    "TRUNCATE TABLE users");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.get("users").contains("TRUNCATE"), "users 应有 TRUNCATE 操作");
        }

        @Test
        @DisplayName("多表 JOIN 应追踪所有表")
        void testJoinTracksAllTables() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT u.name, o.amount FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.containsKey("users"), "应追踪到 users 表");
            assertTrue(access.containsKey("orders"), "应追踪到 orders 表");
        }
    }

    // ================================================================
    // 危险操作检测
    // ================================================================

    @Nested
    @DisplayName("危险操作检测")
    class DangerousOperationsTests {

        @Test
        @DisplayName("DROP TABLE 应触发危险告警")
        void testDropTableDanger() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DROP TABLE users");

            assertTrue(listener.hasDangerousOperations(), "DROP TABLE 应标记为危险操作");
            assertFalse(listener.getWarnings().isEmpty(), "应有告警信息");
            assertTrue(listener.getWarnings().stream()
                            .anyMatch(w -> w.toLowerCase().contains("drop")),
                    "告警应包含 DROP 相关信息");
        }

        @Test
        @DisplayName("DROP DATABASE 应触发危险告警")
        void testDropDatabaseDanger() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DROP DATABASE mydb");

            assertTrue(listener.hasDangerousOperations(), "DROP DATABASE 应标记为危险操作");
        }

        @Test
        @DisplayName("TRUNCATE TABLE 应触发危险告警")
        void testTruncateTableDanger() {
            MySqlAuditListener listener = engine.auditMySql(
                    "TRUNCATE TABLE users");

            assertTrue(listener.hasDangerousOperations(), "TRUNCATE 应标记为危险操作");
        }

        @Test
        @DisplayName("DELETE 无 WHERE 应有告警")
        void testDeleteWithoutWhere() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DELETE FROM users");

            assertFalse(listener.getWarnings().isEmpty(), "DELETE 无 WHERE 应有告警");
            assertTrue(listener.getWarnings().stream()
                            .anyMatch(w -> w.contains("WHERE")),
                    "告警应提及缺少 WHERE 条件");
        }

        @Test
        @DisplayName("UPDATE 无 WHERE 应有告警")
        void testUpdateWithoutWhere() {
            MySqlAuditListener listener = engine.auditMySql(
                    "UPDATE users SET status = 'inactive'");

            assertFalse(listener.getWarnings().isEmpty(), "UPDATE 无 WHERE 应有告警");
            assertTrue(listener.getWarnings().stream()
                            .anyMatch(w -> w.contains("WHERE")),
                    "告警应提及缺少 WHERE 条件");
        }

        @Test
        @DisplayName("DELETE 有 WHERE 不应有缺 WHERE 告警")
        void testDeleteWithWhere() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DELETE FROM users WHERE id = 1");

            boolean hasNoWhereWarning = listener.getWarnings().stream()
                    .anyMatch(w -> w.contains("WHERE"));
            assertFalse(hasNoWhereWarning, "DELETE 有 WHERE 不应有缺 WHERE 告警");
        }

        @Test
        @DisplayName("SELECT * 应有优化建议")
        void testSelectStarWarning() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT * FROM users");

            assertFalse(listener.getWarnings().isEmpty(), "SELECT * 应有告警/建议");
        }

        @Test
        @DisplayName("安全的 SELECT 不应有危险标记")
        void testSafeSelectNoDanger() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT id, name FROM users WHERE id = 1");

            assertFalse(listener.hasDangerousOperations(), "简单 SELECT 不应标记为危险");
        }
    }

    // ================================================================
    // 审计事件日志
    // ================================================================

    @Nested
    @DisplayName("审计事件日志")
    class AuditEventsTests {

        @Test
        @DisplayName("每条 SQL 都应产生审计事件")
        void testEventsGenerated() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT id FROM users WHERE age > 18");

            assertFalse(listener.getEvents().isEmpty(), "应产生审计事件");
        }

        @Test
        @DisplayName("事件应包含类型和详情")
        void testEventContent() {
            MySqlAuditListener listener = engine.auditMySql(
                    "INSERT INTO users (name) VALUES ('test')");

            List<MySqlAuditListener.AuditEvent> events = listener.getEvents();
            assertFalse(events.isEmpty());

            MySqlAuditListener.AuditEvent firstEvent = events.get(0);
            assertNotNull(firstEvent.getType(), "事件类型不应为 null");
            assertNotNull(firstEvent.getDetail(), "事件详情不应为 null");
        }

        @Test
        @DisplayName("危险操作应有 DANGER 类型事件")
        void testDangerEventType() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DROP TABLE users");

            boolean hasDangerEvent = listener.getEvents().stream()
                    .anyMatch(e -> e.getType().contains("DANGER"));
            assertTrue(hasDangerEvent, "DROP TABLE 应产生 DANGER 类型事件");
        }
    }

    // ================================================================
    // 审计报告
    // ================================================================

    @Nested
    @DisplayName("审计报告")
    class AuditReportTests {

        @Test
        @DisplayName("getAuditReport 不应为空")
        void testAuditReportNotEmpty() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT * FROM users");

            String report = listener.getAuditReport();
            assertNotNull(report, "审计报告不应为 null");
            assertFalse(report.isEmpty(), "审计报告不应为空");
        }

        @Test
        @DisplayName("toString 与 getAuditReport 一致")
        void testToString() {
            MySqlAuditListener listener = engine.auditMySql(
                    "UPDATE users SET name = 'test' WHERE id = 1");

            assertEquals(listener.getAuditReport(), listener.toString(),
                    "toString 应与 getAuditReport 一致");
        }

        @Test
        @DisplayName("审计报告应包含表访问信息")
        void testReportContainsTableAccess() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT id FROM users");

            String report = listener.getAuditReport();
            assertTrue(report.contains("users"), "审计报告应包含表名 users");
        }

        @Test
        @DisplayName("有告警时报告应包含告警信息")
        void testReportContainsWarnings() {
            MySqlAuditListener listener = engine.auditMySql(
                    "DROP TABLE users");

            String report = listener.getAuditReport();
            assertTrue(report.toLowerCase().contains("drop") || report.contains("告警") || report.contains("警告"),
                    "审计报告应包含危险操作相关信息");
        }
    }

    // ================================================================
    // 综合场景
    // ================================================================

    @Nested
    @DisplayName("综合场景")
    class ComplexTests {

        @Test
        @DisplayName("复杂 JOIN 查询的审计")
        void testComplexJoinAudit() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT u.name, COUNT(o.id) " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY u.name");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertTrue(access.size() >= 3, "应追踪到至少 3 个表");
            assertFalse(listener.hasDangerousOperations(), "查询不应标记为危险");
        }

        @Test
        @DisplayName("子查询的审计")
        void testSubQueryAudit() {
            MySqlAuditListener listener = engine.auditMySql(
                    "SELECT name FROM users WHERE id IN " +
                    "(SELECT user_id FROM orders WHERE amount > " +
                    "(SELECT AVG(amount) FROM orders))");

            assertFalse(listener.getEvents().isEmpty(), "应产生审计事件");
            assertFalse(listener.hasDangerousOperations(), "子查询不应标记为危险");
        }
    }
}
