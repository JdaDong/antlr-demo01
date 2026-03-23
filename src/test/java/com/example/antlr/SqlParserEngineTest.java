package com.example.antlr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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
            assertThat(result.isSuccess()).as("MySQL SELECT 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("带 JOIN 的复杂查询")
        void testJoinQuery() {
            SqlParseResult result = engine.parse(
                    "SELECT u.name, o.amount FROM users u LEFT JOIN orders o ON u.id = o.user_id WHERE o.amount > 100 ORDER BY o.amount DESC LIMIT 10",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL JOIN 查询应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("CREATE TABLE")
        void testCreateTable() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, email VARCHAR(255) UNIQUE, age INT DEFAULT 0)",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL CREATE TABLE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("INSERT 语句")
        void testInsert() {
            SqlParseResult result = engine.parse(
                    "INSERT INTO users (name, email) VALUES ('test', 'test@test.com')",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL INSERT 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("UPDATE 语句")
        void testUpdate() {
            SqlParseResult result = engine.parse(
                    "UPDATE users SET name = 'new_name', age = 30 WHERE id = 1",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL UPDATE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("DELETE 语句")
        void testDelete() {
            SqlParseResult result = engine.parse(
                    "DELETE FROM users WHERE id = 1",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL DELETE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("聚合查询 GROUP BY + HAVING")
        void testAggregation() {
            SqlParseResult result = engine.parse(
                    "SELECT department, COUNT(*) AS cnt, AVG(salary) AS avg_salary FROM employees GROUP BY department HAVING COUNT(*) > 5",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL 聚合查询应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("子查询")
        void testSubquery() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)",
                    SqlDialect.MYSQL);
            assertThat(result.isSuccess()).as("MySQL 子查询应解析成功: %s", result.getErrorMessage()).isTrue();
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
            assertThat(result.isSuccess()).as("PostgreSQL SELECT 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("ILIKE 查询 (PG特有)")
        void testIlike() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE email ILIKE '%@example.com'",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL ILIKE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("CREATE TABLE 带 PG 特有类型")
        void testCreateTableWithPgTypes() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE products (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, price NUMERIC(10,2), metadata JSONB, ip_addr INET)",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL CREATE TABLE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("CTE WITH 查询")
        void testCTE() {
            SqlParseResult result = engine.parse(
                    "WITH recent AS (SELECT * FROM orders WHERE created_at > '2024-01-01') SELECT * FROM recent WHERE amount > 100",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL CTE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("INSERT RETURNING")
        void testInsertReturning() {
            SqlParseResult result = engine.parse(
                    "INSERT INTO users (name, email) VALUES ('test', 'test@test.com') RETURNING id, name",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL INSERT RETURNING 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("FULL OUTER JOIN")
        void testFullOuterJoin() {
            SqlParseResult result = engine.parse(
                    "SELECT a.id, b.id FROM table_a a FULL OUTER JOIN table_b b ON a.key = b.key",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL FULL OUTER JOIN 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("ORDER BY NULLS FIRST/LAST")
        void testOrderByNulls() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users ORDER BY name ASC NULLS FIRST",
                    SqlDialect.POSTGRESQL);
            assertThat(result.isSuccess()).as("PostgreSQL ORDER BY NULLS 应解析成功: %s", result.getErrorMessage()).isTrue();
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
            assertThat(result.isSuccess()).as("SparkSQL SELECT 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("CREATE TABLE USING")
        void testCreateTableUsing() {
            SqlParseResult result = engine.parse(
                    "CREATE TABLE IF NOT EXISTS events USING parquet OPTIONS ('path' '/data/events') PARTITIONED BY (dt) COMMENT 'Event table'",
                    SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL CREATE TABLE USING 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("INSERT OVERWRITE 带分区")
        void testInsertOverwrite() {
            SqlParseResult result = engine.parse(
                    "INSERT OVERWRITE TABLE summary PARTITION (dt='2024-01-01') SELECT user_id, COUNT(*) FROM events GROUP BY user_id",
                    SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL INSERT OVERWRITE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("RLIKE 正则匹配 (Spark特有)")
        void testRlike() {
            SqlParseResult result = engine.parse(
                    "SELECT * FROM users WHERE name RLIKE '^[A-Z]'",
                    SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL RLIKE 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("LEFT SEMI JOIN (Spark特有)")
        void testLeftSemiJoin() {
            SqlParseResult result = engine.parse(
                    "SELECT a.* FROM table_a a LEFT SEMI JOIN table_b b ON a.id = b.id",
                    SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL LEFT SEMI JOIN 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("SHOW 语句")
        void testShow() {
            SqlParseResult result = engine.parse("SHOW DATABASES", SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL SHOW 应解析成功: %s", result.getErrorMessage()).isTrue();
        }

        @Test
        @DisplayName("EXPLAIN 语句")
        void testExplain() {
            SqlParseResult result = engine.parse(
                    "EXPLAIN EXTENDED SELECT * FROM users WHERE id = 1",
                    SqlDialect.SPARKSQL);
            assertThat(result.isSuccess()).as("SparkSQL EXPLAIN 应解析成功: %s", result.getErrorMessage()).isTrue();
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
            assertThat(SqlDialect.fromString("mysql")).isEqualTo(SqlDialect.MYSQL);
            assertThat(SqlDialect.fromString("pg")).isEqualTo(SqlDialect.POSTGRESQL);
            assertThat(SqlDialect.fromString("postgresql")).isEqualTo(SqlDialect.POSTGRESQL);
            assertThat(SqlDialect.fromString("spark")).isEqualTo(SqlDialect.SPARKSQL);
            assertThat(SqlDialect.fromString("sparksql")).isEqualTo(SqlDialect.SPARKSQL);
        }

        @Test
        @DisplayName("不支持的方言应抛出异常")
        void testInvalidDialect() {
            assertThatThrownBy(() -> SqlDialect.fromString("oracle"))
                    .isInstanceOf(IllegalArgumentException.class);
        }

        @Test
        @DisplayName("SQL 验证方法")
        void testValidate() {
            assertThat(engine.validate("SELECT 1", SqlDialect.MYSQL)).isTrue();
            assertThat(engine.validate("SELECT 1", SqlDialect.POSTGRESQL)).isTrue();
            assertThat(engine.validate("SELECT 1", SqlDialect.SPARKSQL)).isTrue();
        }

        @Test
        @DisplayName("解析结果包含方言信息")
        void testResultMetadata() {
            SqlParseResult result = engine.parse("SELECT 1", SqlDialect.MYSQL);
            assertThat(result.getDialect()).isEqualTo(SqlDialect.MYSQL);
            assertThat(result.getOriginalSql()).isEqualTo("SELECT 1");
            assertThat(result.getParseTimeMs()).isGreaterThanOrEqualTo(0);
        }
    }
}
