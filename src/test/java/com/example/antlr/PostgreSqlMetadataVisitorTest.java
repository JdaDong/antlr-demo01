package com.example.antlr;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.postgresql.PostgreSqlMetadataVisitor;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * PostgreSQL MetadataVisitor 单元测试
 */
class PostgreSqlMetadataVisitorTest {

    private final SqlParserEngine engine = new SqlParserEngine();

    // ================================================================
    // SELECT
    // ================================================================

    @Nested
    @DisplayName("SELECT 元数据提取")
    class SelectTests {

        @Test
        @DisplayName("简单 SELECT 应提取表名和列名")
        void testSimpleSelect() {
            var meta = engine.extractPostgreSqlMetadata("SELECT id, name FROM users");

            assertThat(meta.getStatementType()).isEqualTo("SELECT");
            assertThat(meta.getTables()).contains("users");
            assertThat(meta.getColumns()).contains("id", "name");
        }

        @Test
        @DisplayName("SELECT * 应识别")
        void testSelectStar() {
            var meta = engine.extractPostgreSqlMetadata("SELECT * FROM users");

            assertThat(meta.getStatementType()).isEqualTo("SELECT");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("SELECT DISTINCT 应标记")
        void testSelectDistinct() {
            var meta = engine.extractPostgreSqlMetadata("SELECT DISTINCT name FROM users");

            assertThat(meta.isHasDistinct()).isTrue();
        }

        @Test
        @DisplayName("SELECT 带别名应提取别名")
        void testSelectWithAlias() {
            var meta = engine.extractPostgreSqlMetadata("SELECT name AS user_name FROM users");

            assertThat(meta.getAliases()).containsKey("user_name");
        }

        @Test
        @DisplayName("WHERE 条件列应提取")
        void testSelectWithWhere() {
            var meta = engine.extractPostgreSqlMetadata("SELECT id FROM users WHERE age > 18");

            assertThat(meta.getWhereColumns()).isNotEmpty();
        }

        @Test
        @DisplayName("ORDER BY 应提取")
        void testSelectWithOrderBy() {
            var meta = engine.extractPostgreSqlMetadata("SELECT id, name FROM users ORDER BY name DESC");

            assertThat(meta.getOrderByColumns()).isNotEmpty();
        }

        @Test
        @DisplayName("ORDER BY NULLS FIRST 应提取")
        void testOrderByNulls() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT id FROM users ORDER BY name ASC NULLS FIRST");

            assertThat(meta.getOrderByColumns()).anyMatch(s -> s.contains("NULLS FIRST"));
        }

        @Test
        @DisplayName("GROUP BY 应提取")
        void testSelectWithGroupBy() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT dept, COUNT(id) FROM users GROUP BY dept");

            assertThat(meta.getGroupByColumns()).contains("dept");
            assertThat(meta.getFunctions()).contains("COUNT");
        }

        @Test
        @DisplayName("JOIN 应提取")
        void testJoinQuery() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id");

            assertThat(meta.getTables()).contains("users", "orders");
            assertThat(meta.getJoinConditions()).isNotEmpty();
        }

        @Test
        @DisplayName("子查询应标记")
        void testSubQuery() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders)");

            assertThat(meta.isHasSubQuery()).isTrue();
        }

        @Test
        @DisplayName("UNION 应标记")
        void testUnionQuery() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT id, name FROM users UNION SELECT id, name FROM admins");

            assertThat(meta.isHasUnion()).isTrue();
        }

        @Test
        @DisplayName("聚合函数应提取")
        void testFunctionCalls() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT COUNT(id), MAX(age), MIN(age), AVG(age) FROM users");

            assertThat(meta.getFunctions()).contains("COUNT", "MAX", "MIN", "AVG");
        }
    }

    // ================================================================
    // CTE
    // ================================================================

    @Nested
    @DisplayName("CTE 元数据提取")
    class CteTests {

        @Test
        @DisplayName("CTE 应识别 hasCte 标记和 CTE 名称")
        void testCte() {
            var meta = engine.extractPostgreSqlMetadata(
                    "WITH active AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active");

            assertThat(meta.isHasCte()).isTrue();
            assertThat(meta.getCteNames()).contains("active");
        }
    }

    // ================================================================
    // INSERT
    // ================================================================

    @Nested
    @DisplayName("INSERT 元数据提取")
    class InsertTests {

        @Test
        @DisplayName("INSERT VALUES 应提取表名和列名")
        void testInsertValues() {
            var meta = engine.extractPostgreSqlMetadata(
                    "INSERT INTO users (id, name, age) VALUES (1, 'Alice', 30)");

            assertThat(meta.getStatementType()).isEqualTo("INSERT");
            assertThat(meta.getTables()).contains("users");
            assertThat(meta.getColumns()).contains("id", "name", "age");
        }

        @Test
        @DisplayName("INSERT SELECT 应提取表名")
        void testInsertSelect() {
            var meta = engine.extractPostgreSqlMetadata(
                    "INSERT INTO archive (id, name) SELECT id, name FROM users WHERE status = 'inactive'");

            assertThat(meta.getStatementType()).isEqualTo("INSERT");
            assertThat(meta.getTables()).contains("archive", "users");
        }

        @Test
        @DisplayName("INSERT RETURNING 应标记")
        void testInsertReturning() {
            var meta = engine.extractPostgreSqlMetadata(
                    "INSERT INTO users (name) VALUES ('Alice') RETURNING id");

            assertThat(meta.isHasReturning()).isTrue();
        }
    }

    // ================================================================
    // UPDATE
    // ================================================================

    @Nested
    @DisplayName("UPDATE 元数据提取")
    class UpdateTests {

        @Test
        @DisplayName("简单 UPDATE 应提取")
        void testSimpleUpdate() {
            var meta = engine.extractPostgreSqlMetadata(
                    "UPDATE users SET name = 'Bob' WHERE id = 1");

            assertThat(meta.getStatementType()).isEqualTo("UPDATE");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("UPDATE RETURNING 应标记")
        void testUpdateReturning() {
            var meta = engine.extractPostgreSqlMetadata(
                    "UPDATE users SET status = 'active' WHERE id = 1 RETURNING id, status");

            assertThat(meta.isHasReturning()).isTrue();
        }
    }

    // ================================================================
    // DELETE
    // ================================================================

    @Nested
    @DisplayName("DELETE 元数据提取")
    class DeleteTests {

        @Test
        @DisplayName("DELETE 应提取表名")
        void testSimpleDelete() {
            var meta = engine.extractPostgreSqlMetadata(
                    "DELETE FROM users WHERE status = 'inactive'");

            assertThat(meta.getStatementType()).isEqualTo("DELETE");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("DELETE RETURNING 应标记")
        void testDeleteReturning() {
            var meta = engine.extractPostgreSqlMetadata(
                    "DELETE FROM users WHERE id = 1 RETURNING id, name");

            assertThat(meta.isHasReturning()).isTrue();
        }
    }

    // ================================================================
    // DDL
    // ================================================================

    @Nested
    @DisplayName("DDL 元数据提取")
    class DdlTests {

        @Test
        @DisplayName("CREATE TABLE 应提取")
        void testCreateTable() {
            var meta = engine.extractPostgreSqlMetadata(
                    "CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL)");

            assertThat(meta.getStatementType()).isEqualTo("CREATE_TABLE");
            assertThat(meta.getTables()).contains("users");
            assertThat(meta.getColumns()).contains("id", "name");
        }

        @Test
        @DisplayName("DROP TABLE 应提取")
        void testDropTable() {
            var meta = engine.extractPostgreSqlMetadata("DROP TABLE IF EXISTS users");

            assertThat(meta.getStatementType()).isEqualTo("DROP_TABLE");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("ALTER TABLE 应提取")
        void testAlterTable() {
            var meta = engine.extractPostgreSqlMetadata(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(200)");

            assertThat(meta.getStatementType()).isEqualTo("ALTER_TABLE");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("CREATE INDEX 应提取")
        void testCreateIndex() {
            var meta = engine.extractPostgreSqlMetadata(
                    "CREATE INDEX idx_users_name ON users (name)");

            assertThat(meta.getStatementType()).isEqualTo("CREATE_INDEX");
            assertThat(meta.getTables()).contains("users");
        }

        @Test
        @DisplayName("DROP INDEX 应提取")
        void testDropIndex() {
            var meta = engine.extractPostgreSqlMetadata("DROP INDEX IF EXISTS idx_users_name");

            assertThat(meta.getStatementType()).isEqualTo("DROP_INDEX");
        }

        @Test
        @DisplayName("CREATE DATABASE 应提取")
        void testCreateDatabase() {
            var meta = engine.extractPostgreSqlMetadata("CREATE DATABASE IF NOT EXISTS mydb");

            assertThat(meta.getStatementType()).isEqualTo("CREATE_DATABASE");
        }

        @Test
        @DisplayName("DROP DATABASE 应提取")
        void testDropDatabase() {
            var meta = engine.extractPostgreSqlMetadata("DROP DATABASE IF EXISTS mydb");

            assertThat(meta.getStatementType()).isEqualTo("DROP_DATABASE");
        }

        @Test
        @DisplayName("CREATE SCHEMA 应提取")
        void testCreateSchema() {
            var meta = engine.extractPostgreSqlMetadata("CREATE SCHEMA IF NOT EXISTS myschema");

            assertThat(meta.getStatementType()).isEqualTo("CREATE_SCHEMA");
            assertThat(meta.getSchemas()).contains("myschema");
        }
    }

    // ================================================================
    // 复杂查询
    // ================================================================

    @Nested
    @DisplayName("复杂查询")
    class ComplexTests {

        @Test
        @DisplayName("复杂 JOIN 查询应全面提取")
        void testComplexQuery() {
            var meta = engine.extractPostgreSqlMetadata(
                    "SELECT u.name, COUNT(o.id) AS order_count " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "WHERE o.status = 'completed' " +
                    "GROUP BY u.name " +
                    "ORDER BY order_count DESC");

            assertThat(meta.getTables()).contains("users", "orders");
            assertThat(meta.getJoinConditions()).isNotEmpty();
            assertThat(meta.getGroupByColumns()).isNotEmpty();
            assertThat(meta.getOrderByColumns()).isNotEmpty();
            assertThat(meta.getFunctions()).contains("COUNT");
        }

        @Test
        @DisplayName("getSummary 应返回非空")
        void testGetSummary() {
            var meta = engine.extractPostgreSqlMetadata("SELECT id FROM users");

            assertThat(meta.getSummary()).isNotNull().isNotEmpty();
            assertThat(meta.getSummary()).contains("PostgreSQL");
        }

        @Test
        @DisplayName("toString 应等于 getSummary")
        void testToString() {
            var meta = engine.extractPostgreSqlMetadata("SELECT id FROM users");

            assertThat(meta.toString()).isEqualTo(meta.getSummary());
        }
    }
}
