package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

import com.example.antlr.sparksql.SparkSqlAuditListener;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SparkSqlAuditListener 单元测试
 * 测试 Listener 模式下的 SparkSQL 审计能力
 */
@DisplayName("SparkSqlAuditListener - Listener 模式 SQL 审计")
class SparkSqlAuditListenerTest {

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
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT id, name FROM users WHERE age > 18");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 表").containsKey("users");
            assertThat(access.get("users")).as("users 应有 READ 操作").contains("READ");
        }

        @Test
        @DisplayName("INSERT 应记录 INSERT 操作")
        void testInsertTracksInsert() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "INSERT INTO users (name, email) VALUES ('Alice', 'alice@test.com')");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 表").containsKey("users");
            assertThat(access.get("users")).as("users 应有 INSERT 操作").contains("INSERT");
        }

        @Test
        @DisplayName("INSERT OVERWRITE 应记录 OVERWRITE 操作")
        void testInsertOverwriteTracksOverwrite() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "INSERT OVERWRITE TABLE users SELECT * FROM staging_users");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 表").containsKey("users");
            assertThat(access.get("users")).as("users 应有 OVERWRITE 操作").contains("OVERWRITE");
        }

        @Test
        @DisplayName("CREATE TABLE 应记录 CREATE 操作")
        void testCreateTableTracksCreate() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "CREATE TABLE orders (id INT, amount DECIMAL(10, 2))");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 orders 表").containsKey("orders");
            assertThat(access.get("orders")).as("orders 应有 CREATE 操作").contains("CREATE");
        }

        @Test
        @DisplayName("DROP TABLE 应记录 DROP 操作")
        void testDropTableTracksDrop() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "DROP TABLE IF EXISTS users");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 表").containsKey("users");
            assertThat(access.get("users")).as("users 应有 DROP 操作").contains("DROP");
        }

        @Test
        @DisplayName("ALTER TABLE ADD COLUMNS 应记录 ALTER 操作")
        void testAlterTableTracksAlter() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "ALTER TABLE users ADD COLUMNS (email STRING)");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 表").containsKey("users");
            assertThat(access.get("users")).as("users 应有 ALTER 操作").contains("ALTER");
        }

        @Test
        @DisplayName("多表 JOIN 应追踪所有表")
        void testJoinTracksAllTables() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT u.name, o.amount FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到 users 和 orders 表")
                    .containsKey("users")
                    .containsKey("orders");
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
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "DROP TABLE users");

            assertThat(listener.hasDangerousOperations()).as("DROP TABLE 应标记为危险操作").isTrue();
            assertThat(listener.getWarnings()).as("应有告警信息").isNotEmpty();
            assertThat(listener.getWarnings()).as("告警应包含 DROP 相关信息")
                    .anyMatch(w -> w.toLowerCase().contains("drop"));
        }

        @Test
        @DisplayName("DROP DATABASE 应触发危险告警")
        void testDropDatabaseDanger() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "DROP DATABASE mydb");

            assertThat(listener.hasDangerousOperations()).as("DROP DATABASE 应标记为危险操作").isTrue();
        }

        @Test
        @DisplayName("INSERT OVERWRITE 应有告警")
        void testInsertOverwriteWarning() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "INSERT OVERWRITE TABLE users SELECT * FROM staging_users");

            assertThat(listener.hasDangerousOperations()).as("INSERT OVERWRITE 应标记为危险操作").isTrue();
            assertThat(listener.getWarnings()).as("告警应提及 OVERWRITE")
                    .anyMatch(w -> w.contains("OVERWRITE"));
        }

        @Test
        @DisplayName("SELECT * 应有优化建议")
        void testSelectStarWarning() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT * FROM users");

            assertThat(listener.getWarnings()).as("SELECT * 应有告警/建议").isNotEmpty();
        }

        @Test
        @DisplayName("安全的 SELECT 不应有危险标记")
        void testSafeSelectNoDanger() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT id, name FROM users WHERE id = 1");

            assertThat(listener.hasDangerousOperations()).as("简单 SELECT 不应标记为危险").isFalse();
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
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT id FROM users WHERE age > 18");

            assertThat(listener.getEvents()).as("应产生审计事件").isNotEmpty();
        }

        @Test
        @DisplayName("事件应包含类型和详情")
        void testEventContent() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "INSERT INTO users (name) VALUES ('test')");

            List<SparkSqlAuditListener.AuditEvent> events = listener.getEvents();
            assertThat(events).isNotEmpty();

            SparkSqlAuditListener.AuditEvent firstEvent = events.get(0);
            assertThat(firstEvent.getType()).as("事件类型不应为 null").isNotNull();
            assertThat(firstEvent.getDetail()).as("事件详情不应为 null").isNotNull();
        }

        @Test
        @DisplayName("危险操作应有 DANGER 类型事件")
        void testDangerEventType() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "DROP TABLE users");

            assertThat(listener.getEvents()).as("DROP TABLE 应产生 DANGER 类型事件")
                    .anyMatch(e -> e.getType().contains("DANGER"));
        }

        @Test
        @DisplayName("CTE WITH 子句应产生事件")
        void testCteEvent() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "WITH tmp AS (SELECT id FROM users) SELECT * FROM tmp");

            assertThat(listener.getEvents()).as("CTE 应产生事件")
                    .anyMatch(e -> e.getDetail().contains("CTE") || e.getDetail().contains("WITH"));
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
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT * FROM users");

            String report = listener.getAuditReport();
            assertThat(report).as("审计报告不应为 null").isNotNull();
            assertThat(report).as("审计报告不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("toString 与 getAuditReport 一致")
        void testToString() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "INSERT INTO users (name) VALUES ('test')");

            assertThat(listener.toString()).as("toString 应与 getAuditReport 一致")
                    .isEqualTo(listener.getAuditReport());
        }

        @Test
        @DisplayName("审计报告应包含表访问信息")
        void testReportContainsTableAccess() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT id FROM users");

            String report = listener.getAuditReport();
            assertThat(report).as("审计报告应包含表名 users").contains("users");
        }

        @Test
        @DisplayName("有告警时报告应包含告警信息")
        void testReportContainsWarnings() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "DROP TABLE users");

            String report = listener.getAuditReport();
            assertThat(report.toLowerCase().contains("drop") || report.contains("告警") || report.contains("危险"))
                    .as("审计报告应包含危险操作相关信息").isTrue();
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
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT u.name, COUNT(o.id) " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY u.name");

            Map<String, Set<String>> access = listener.getTableAccess();
            assertThat(access).as("应追踪到至少 3 个表").hasSizeGreaterThanOrEqualTo(3);
            assertThat(listener.hasDangerousOperations()).as("查询不应标记为危险").isFalse();
        }

        @Test
        @DisplayName("SparkSQL 特有语法审计 - LEFT SEMI JOIN")
        void testLeftSemiJoinAudit() {
            SparkSqlAuditListener listener = engine.auditSparkSql(
                    "SELECT u.name FROM users u LEFT SEMI JOIN orders o ON u.id = o.user_id");

            assertThat(listener.getEvents()).as("应产生 JOIN 相关事件")
                    .anyMatch(e -> e.getDetail().toUpperCase().contains("JOIN"));
        }
    }
}
