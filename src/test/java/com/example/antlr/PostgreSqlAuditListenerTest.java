package com.example.antlr;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.postgresql.PostgreSqlAuditListener;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * PostgreSQL AuditListener 单元测试
 */
class PostgreSqlAuditListenerTest {

    private final SqlParserEngine engine = new SqlParserEngine();

    // ================================================================
    // 表访问追踪
    // ================================================================

    @Nested
    @DisplayName("表访问追踪")
    class TableAccessTests {

        @Test
        @DisplayName("SELECT 应记录 READ 操作")
        void testSelectTracksRead() {
            var audit = engine.auditPostgreSql("SELECT id, name FROM users");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("READ");
        }

        @Test
        @DisplayName("INSERT 应记录 INSERT 操作")
        void testInsertTracksInsert() {
            var audit = engine.auditPostgreSql(
                    "INSERT INTO users (name, age) VALUES ('Alice', 30)");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("INSERT");
        }

        @Test
        @DisplayName("UPDATE 应记录 UPDATE 操作")
        void testUpdateTracksUpdate() {
            var audit = engine.auditPostgreSql(
                    "UPDATE users SET name = 'Bob' WHERE id = 1");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("UPDATE");
        }

        @Test
        @DisplayName("DELETE 应记录 DELETE 操作")
        void testDeleteTracksDelete() {
            var audit = engine.auditPostgreSql(
                    "DELETE FROM users WHERE id = 1");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("DELETE");
        }

        @Test
        @DisplayName("CREATE TABLE 应记录 CREATE 操作")
        void testCreateTableTracksCreate() {
            var audit = engine.auditPostgreSql(
                    "CREATE TABLE users (id SERIAL PRIMARY KEY, name TEXT)");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("CREATE");
        }

        @Test
        @DisplayName("DROP TABLE 应记录 DROP 操作")
        void testDropTableTracksDrop() {
            var audit = engine.auditPostgreSql("DROP TABLE users");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("DROP");
        }

        @Test
        @DisplayName("ALTER TABLE 应记录 ALTER 操作")
        void testAlterTableTracksAlter() {
            var audit = engine.auditPostgreSql(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(200)");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("ALTER");
        }

        @Test
        @DisplayName("JOIN 应追踪所有表")
        void testJoinTracksAllTables() {
            var audit = engine.auditPostgreSql(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess()).containsKey("orders");
        }

        @Test
        @DisplayName("CREATE INDEX 应追踪表")
        void testCreateIndexTracksTable() {
            var audit = engine.auditPostgreSql(
                    "CREATE INDEX idx_users_name ON users (name)");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess().get("users")).contains("CREATE_INDEX");
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
            var audit = engine.auditPostgreSql("DROP TABLE users");

            assertThat(audit.hasDangerousOperations()).isTrue();
            assertThat(audit.getWarnings()).anyMatch(w -> w.contains("DROP TABLE"));
        }

        @Test
        @DisplayName("DROP DATABASE 应触发危险告警")
        void testDropDatabaseDanger() {
            var audit = engine.auditPostgreSql("DROP DATABASE mydb");

            assertThat(audit.hasDangerousOperations()).isTrue();
            assertThat(audit.getWarnings()).anyMatch(w -> w.contains("DROP DATABASE"));
        }

        @Test
        @DisplayName("DELETE 无 WHERE 应触发告警")
        void testDeleteWithoutWhere() {
            var audit = engine.auditPostgreSql("DELETE FROM users");

            assertThat(audit.getWarnings()).anyMatch(w -> w.contains("DELETE") && w.contains("WHERE"));
        }

        @Test
        @DisplayName("UPDATE 无 WHERE 应触发告警")
        void testUpdateWithoutWhere() {
            var audit = engine.auditPostgreSql("UPDATE users SET status = 'inactive'");

            assertThat(audit.getWarnings()).anyMatch(w -> w.contains("UPDATE") && w.contains("WHERE"));
        }

        @Test
        @DisplayName("DELETE 有 WHERE 不应有无 WHERE 告警")
        void testDeleteWithWhere() {
            var audit = engine.auditPostgreSql("DELETE FROM users WHERE id = 1");

            assertThat(audit.getWarnings()).noneMatch(w -> w.contains("没有 WHERE 条件"));
        }

        @Test
        @DisplayName("SELECT * 应触发优化建议")
        void testSelectStarWarning() {
            var audit = engine.auditPostgreSql("SELECT * FROM users");

            assertThat(audit.getWarnings()).anyMatch(w -> w.contains("SELECT *"));
        }

        @Test
        @DisplayName("安全 SELECT 不应有危险标记")
        void testSafeSelectNoDanger() {
            var audit = engine.auditPostgreSql("SELECT id, name FROM users WHERE id = 1");

            assertThat(audit.hasDangerousOperations()).isFalse();
        }
    }

    // ================================================================
    // 审计事件
    // ================================================================

    @Nested
    @DisplayName("审计事件")
    class AuditEventsTests {

        @Test
        @DisplayName("SELECT 应生成事件")
        void testEventsGenerated() {
            var audit = engine.auditPostgreSql("SELECT id FROM users");

            assertThat(audit.getEvents()).isNotEmpty();
        }

        @Test
        @DisplayName("INSERT 事件应有正确类型")
        void testEventContent() {
            var audit = engine.auditPostgreSql(
                    "INSERT INTO users (name) VALUES ('Alice')");

            assertThat(audit.getEvents()).anyMatch(e ->
                    e.getType().equals("DML") && e.getDetail().contains("users"));
        }

        @Test
        @DisplayName("DROP TABLE 应生成 DANGER 事件")
        void testDangerEventType() {
            var audit = engine.auditPostgreSql("DROP TABLE users");

            assertThat(audit.getEvents()).anyMatch(e -> e.getType().contains("DANGER"));
        }

        @Test
        @DisplayName("CTE 应生成 CTE 事件")
        void testCteEvent() {
            var audit = engine.auditPostgreSql(
                    "WITH active AS (SELECT id FROM users WHERE status = 'active') SELECT id FROM active");

            assertThat(audit.getEvents()).anyMatch(e ->
                    e.getDetail().contains("CTE") || e.getDetail().contains("WITH"));
        }

        @Test
        @DisplayName("CREATE SCHEMA 应生成事件")
        void testCreateSchemaEvent() {
            var audit = engine.auditPostgreSql("CREATE SCHEMA myschema");

            assertThat(audit.getEvents()).anyMatch(e -> e.getDetail().contains("Schema"));
        }
    }

    // ================================================================
    // 审计报告
    // ================================================================

    @Nested
    @DisplayName("审计报告")
    class AuditReportTests {

        @Test
        @DisplayName("审计报告应非空")
        void testAuditReportNotEmpty() {
            var audit = engine.auditPostgreSql("SELECT id FROM users");

            assertThat(audit.getAuditReport()).isNotNull().isNotEmpty();
        }

        @Test
        @DisplayName("toString 应等于 getAuditReport")
        void testToString() {
            var audit = engine.auditPostgreSql("SELECT id FROM users");

            assertThat(audit.toString()).isEqualTo(audit.getAuditReport());
        }

        @Test
        @DisplayName("报告应包含表访问信息")
        void testReportContainsTableAccess() {
            var audit = engine.auditPostgreSql("SELECT id FROM users");

            assertThat(audit.getAuditReport()).contains("表访问");
        }

        @Test
        @DisplayName("危险操作报告应包含告警")
        void testReportContainsWarnings() {
            var audit = engine.auditPostgreSql("DROP TABLE users");

            assertThat(audit.getAuditReport()).contains("风险告警");
        }
    }

    // ================================================================
    // 复杂查询
    // ================================================================

    @Nested
    @DisplayName("复杂查询审计")
    class ComplexTests {

        @Test
        @DisplayName("复杂 JOIN 查询审计")
        void testComplexJoinAudit() {
            var audit = engine.auditPostgreSql(
                    "SELECT u.name, o.amount FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN payments p ON o.id = p.order_id");

            assertThat(audit.getTableAccess()).containsKey("users");
            assertThat(audit.getTableAccess()).containsKey("orders");
            assertThat(audit.getTableAccess()).containsKey("payments");
        }

        @Test
        @DisplayName("子查询审计")
        void testSubQueryAudit() {
            var audit = engine.auditPostgreSql(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)");

            assertThat(audit.getEvents()).isNotEmpty();
            assertThat(audit.getTableAccess()).containsKey("users");
        }
    }
}
