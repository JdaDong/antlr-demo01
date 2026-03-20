package com.example.antlr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SQL 解析器综合测试
 */
class SqlParserEngineTest {

    private SqlParserEngine engine;

    @BeforeEach
    void setUp() {
        engine = new SqlParserEngine();
    }

    // ============================================================
    // MySQL 测试
    // ============================================================

    @Nested
    @DisplayName("MySQL 方言测试")
    class MySqlTests {

        @Test
        @DisplayName("SELECT 查询")
        void testSelect() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE id = 1", SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL SELECT 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("带 JOIN 的复杂查询")
        void testJoinQuery() {
            SqlParseResult result = engine.parse(
                    "SELECT u.name, o.amount FROM users u LEFT JOIN orders o ON u.id = o.user_id WHERE o.amount > 100 ORDER BY o.amount DESC LIMIT 10",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL JOIN 查询应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("CREATE TABLE")
        void testCreateTable() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(255) UNIQUE, age INT DEFAULT 0)",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL CREATE TABLE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("INSERT 语句")
        void testInsert() {
            SqlParseResult result = engine.parse(
                    "INSERT INTO users (name, email) VALUES ('test', 'test@test.com')",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL INSERT 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("UPDATE 语句")
        void testUpdate() {
            SqlParseResult result = engine.parse(
                    "UPDATE users SET name = 'new_name', age = 30 WHERE id = 1",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL UPDATE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("DELETE 语句")
        void testDelete() {
            SqlParseResult result = engine.parse(
                    "DELETE FROM users WHERE id = 1",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL DELETE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("聚合查询 GROUP BY + HAVING")
        void testAggregation() {
            SqlParseResult result = engine.parse(
                    "SELECT department, COUNT(*) AS cnt, AVG(salary) AS avg_salary FROM employees GROUP BY department HAVING COUNT(*) > 5",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL 聚合查询应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("子查询")
        void testSubquery() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)",
                    SqlDialect.MYSQL);
            assertTrue(result.isSuccess(), "MySQL 子查询应解析成功: " + result.getErrorMessage());
        }
    }

    // ============================================================
    // PostgreSQL 测试
    // ============================================================

    @Nested
    @DisplayName("PostgreSQL 方言测试")
    class PostgreSqlTests {

        @Test
        @DisplayName("SELECT 查询")
        void testSelect() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE id = 1", SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL SELECT 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("ILIKE 查询 (PG特有)")
        void testIlike() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE email ILIKE '%@example.com'",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL ILIKE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("CREATE TABLE 带 PG 特有类型")
        void testCreateTableWithPgTypes() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE products (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, price NUMERIC(10,2), metadata JSONB, ip_addr INET)",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL CREATE TABLE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("CTE WITH 查询")
        void testCTE() {
            SqlParseResult result = engine.parse(
                    "WITH recent AS (SELECT * FROM orders WHERE created_at > '2024-01-01') SELECT * FROM recent WHERE amount > 100",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL CTE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("INSERT RETURNING")
        void testInsertReturning() {
            SqlParseResult result = engine.parse(
                    "INSERT INTO users (name, email) VALUES ('test', 'test@test.com') RETURNING id, name",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL INSERT RETURNING 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("FULL OUTER JOIN")
        void testFullOuterJoin() {
            SqlParseResult result = engine.parse(
                    "SELECT a.id, b.id FROM table_a a FULL OUTER JOIN table_b b ON a.key = b.key",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL FULL OUTER JOIN 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("ORDER BY NULLS FIRST/LAST")
        void testOrderByNulls() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users ORDER BY name ASC NULLS FIRST",
                    SqlDialect.POSTGRESQL);
            assertTrue(result.isSuccess(), "PostgreSQL ORDER BY NULLS 应解析成功: " + result.getErrorMessage());
        }
    }

    // ============================================================
    // SparkSQL 测试
    // ============================================================

    @Nested
    @DisplayName("SparkSQL 方言测试")
    class SparkSqlTests {

        @Test
        @DisplayName("SELECT 查询")
        void testSelect() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM db.users WHERE age > 18", SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL SELECT 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("CREATE TABLE USING")
        void testCreateTableUsing() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE IF NOT EXISTS events USING parquet OPTIONS ('path' '/data/events') PARTITIONED BY (dt) COMMENT 'Event table'",
                    SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL CREATE TABLE USING 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("INSERT OVERWRITE 带分区")
        void testInsertOverwrite() {
            SqlParseResult result = engine.parse(
                    "INSERT OVERWRITE TABLE summary PARTITION (dt='2024-01-01') SELECT user_id, COUNT(*) FROM events GROUP BY user_id",
                    SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL INSERT OVERWRITE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("RLIKE 正则匹配 (Spark特有)")
        void testRlike() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE name RLIKE '^[A-Z]'",
                    SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL RLIKE 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("LEFT SEMI JOIN (Spark特有)")
        void testLeftSemiJoin() {
            SqlParseResult result = engine.parse(
                    "SELECT a.* FROM table_a a LEFT SEMI JOIN table_b b ON a.id = b.id",
                    SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL LEFT SEMI JOIN 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("SHOW 语句")
        void testShow() {
            SqlParseResult result = engine.parse("SHOW DATABASES", SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL SHOW 应解析成功: " + result.getErrorMessage());
        }

        @Test
        @DisplayName("EXPLAIN 语句")
        void testExplain() {
            SqlParseResult result = engine.parse(
                    "EXPLAIN EXTENDED SELECT * FROM users WHERE id = 1",
                    SqlDialect.SPARKSQL);
            assertTrue(result.isSuccess(), "SparkSQL EXPLAIN 应解析成功: " + result.getErrorMessage());
        }
    }

    // ============================================================
    // 通用测试
    // ============================================================

    @Nested
    @DisplayName("通用测试")
    class CommonTests {

        @Test
        @DisplayName("方言枚举解析")
        void testDialectParsing() {
            assertEquals(SqlDialect.MYSQL, SqlDialect.fromString("mysql"));
            assertEquals(SqlDialect.POSTGRESQL, SqlDialect.fromString("pg"));
            assertEquals(SqlDialect.POSTGRESQL, SqlDialect.fromString("postgresql"));
            assertEquals(SqlDialect.SPARKSQL, SqlDialect.fromString("spark"));
            assertEquals(SqlDialect.SPARKSQL, SqlDialect.fromString("sparksql"));
        }

        @Test
        @DisplayName("不支持的方言应抛出异常")
        void testInvalidDialect() {
            assertThrows(IllegalArgumentException.class, () -> SqlDialect.fromString("oracle"));
        }

        @Test
        @DisplayName("SQL 验证方法")
        void testValidate() {
            assertTrue(engine.validate("SELECT 1", SqlDialect.MYSQL));
            assertTrue(engine.validate("SELECT 1", SqlDialect.POSTGRESQL));
            assertTrue(engine.validate("SELECT 1", SqlDialect.SPARKSQL));
        }

        @Test
        @DisplayName("解析结果包含方言信息")
        void testResultMetadata() {
            SqlParseResult result = engine.parse("SELECT 1", SqlDialect.MYSQL);
            assertEquals(SqlDialect.MYSQL, result.getDialect());
            assertEquals("SELECT 1", result.getOriginalSql());
            assertTrue(result.getParseTimeMs() >= 0);
        }
    }
}
