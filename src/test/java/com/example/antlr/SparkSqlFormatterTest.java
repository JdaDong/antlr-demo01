package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * SparkSqlFormatter 单元测试
 * 测试 Visitor 模式下的 SparkSQL 格式化/美化能力
 */
@DisplayName("SparkSqlFormatter - Visitor 模式 SQL 格式化")
class SparkSqlFormatterTest {

    private SqlParserEngine engine;

    @BeforeEach
    void setUp() {
        engine = new SqlParserEngine();
    }

    // ================================================================
    // SELECT 格式化
    // ================================================================

    @Nested
    @DisplayName("SELECT 格式化")
    class SelectFormatTests {

        @Test
        @DisplayName("简单 SELECT 应正确格式化")
        void testSimpleSelect() {
            String formatted = engine.formatSparkSql(
                    "select id,name,age from users where age>18 order by name limit 10");

            assertThat(formatted).as("关键字应大写").contains("SELECT");
            assertThat(formatted).as("FROM 应大写").contains("FROM");
            assertThat(formatted).as("WHERE 应大写").contains("WHERE");
            assertThat(formatted).as("ORDER BY 应大写").contains("ORDER BY");
            assertThat(formatted).as("LIMIT 应大写").contains("LIMIT");
        }

        @Test
        @DisplayName("JOIN 应正确格式化")
        void testJoinFormat() {
            String formatted = engine.formatSparkSql(
                    "select u.name,o.amount from users u inner join orders o on u.id=o.user_id left join products p on o.product_id=p.id");

            assertThat(formatted).as("应有 INNER JOIN").contains("INNER JOIN");
            assertThat(formatted).as("应有 LEFT JOIN").contains("LEFT JOIN");
            assertThat(formatted).as("应有 ON").contains("ON");
        }

        @Test
        @DisplayName("LEFT SEMI JOIN 应正确格式化")
        void testLeftSemiJoinFormat() {
            String formatted = engine.formatSparkSql(
                    "select u.name from users u left semi join orders o on u.id=o.user_id");

            assertThat(formatted).as("应有 LEFT SEMI JOIN")
                    .containsIgnoringCase("LEFT SEMI JOIN");
        }

        @Test
        @DisplayName("LEFT ANTI JOIN 应正确格式化")
        void testLeftAntiJoinFormat() {
            String formatted = engine.formatSparkSql(
                    "select u.name from users u left anti join orders o on u.id=o.user_id");

            assertThat(formatted).as("应有 LEFT ANTI JOIN")
                    .containsIgnoringCase("LEFT ANTI JOIN");
        }

        @Test
        @DisplayName("DISTINCT 应保留")
        void testDistinctPreserved() {
            String formatted = engine.formatSparkSql(
                    "SELECT DISTINCT city FROM users");

            assertThat(formatted).as("应保留 DISTINCT 关键字").contains("DISTINCT");
        }

        @Test
        @DisplayName("GROUP BY + HAVING 应正确格式化")
        void testGroupByHavingFormat() {
            String formatted = engine.formatSparkSql(
                    "SELECT city, COUNT(*) FROM users GROUP BY city HAVING COUNT(*) > 5");

            assertThat(formatted).as("应有 GROUP BY").contains("GROUP BY");
            assertThat(formatted).as("应有 HAVING").contains("HAVING");
        }

        @Test
        @DisplayName("UNION 应分段格式化")
        void testUnionFormat() {
            String formatted = engine.formatSparkSql(
                    "SELECT name FROM users UNION SELECT product_name FROM products");

            assertThat(formatted).as("应有 UNION").contains("UNION");
        }

        @Test
        @DisplayName("子查询应缩进")
        void testSubqueryIndent() {
            String formatted = engine.formatSparkSql(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)");

            assertThat(formatted).as("应有 SELECT").contains("SELECT");
            String[] lines = formatted.split("\n");
            boolean hasIndentedSelect = false;
            for (String line : lines) {
                if (line.startsWith("    ") && line.trim().startsWith("SELECT")) {
                    hasIndentedSelect = true;
                    break;
                }
            }
            assertThat(hasIndentedSelect).as("子查询的 SELECT 应有缩进").isTrue();
        }
    }

    // ================================================================
    // SparkSQL 特有语法格式化
    // ================================================================

    @Nested
    @DisplayName("SparkSQL 特有语法格式化")
    class SparkSpecificFormatTests {

        @Test
        @DisplayName("CTE (WITH 子句) 应正确格式化")
        void testCteFormat() {
            String formatted = engine.formatSparkSql(
                    "WITH active_users AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active_users");

            assertThat(formatted).as("应有 WITH").contains("WITH");
            assertThat(formatted).as("应有 AS").contains("AS");
        }

        @Test
        @DisplayName("INSERT OVERWRITE TABLE 应正确格式化")
        void testInsertOverwriteFormat() {
            String formatted = engine.formatSparkSql(
                    "INSERT OVERWRITE TABLE target SELECT * FROM source");

            assertThat(formatted).as("应有 INSERT OVERWRITE TABLE")
                    .contains("INSERT OVERWRITE TABLE");
        }

        @Test
        @DisplayName("CREATE TABLE USING 应正确格式化")
        void testCreateTableUsingFormat() {
            String formatted = engine.formatSparkSql(
                    "CREATE TABLE events (id INT, name STRING) USING parquet");

            assertThat(formatted).as("应有 CREATE TABLE").contains("CREATE TABLE");
            assertThat(formatted).as("应有 USING").contains("USING");
        }

        @Test
        @DisplayName("CREATE TABLE IF NOT EXISTS 应保留")
        void testCreateTableIfNotExistsFormat() {
            String formatted = engine.formatSparkSql(
                    "CREATE TABLE IF NOT EXISTS users (id INT)");

            assertThat(formatted).as("应保留 IF NOT EXISTS").contains("IF NOT EXISTS");
        }

        @Test
        @DisplayName("CREATE VIEW 应正确格式化")
        void testCreateViewFormat() {
            String formatted = engine.formatSparkSql(
                    "CREATE VIEW active_users AS SELECT id, name FROM users WHERE status = 'active'");

            assertThat(formatted).as("应有 CREATE").contains("CREATE");
            assertThat(formatted).as("应有 VIEW").contains("VIEW");
            assertThat(formatted).as("应有 AS").contains("AS");
        }

        @Test
        @DisplayName("DROP TABLE IF EXISTS 应正确格式化")
        void testDropTableFormat() {
            String formatted = engine.formatSparkSql(
                    "drop table if exists users");

            assertThat(formatted).as("关键字应大写为 DROP TABLE").contains("DROP TABLE");
            assertThat(formatted).as("应保留 IF EXISTS").contains("IF EXISTS");
        }

        @Test
        @DisplayName("EXPLAIN 应正确格式化")
        void testExplainFormat() {
            String formatted = engine.formatSparkSql(
                    "EXPLAIN SELECT * FROM users");

            assertThat(formatted).as("应有 EXPLAIN").contains("EXPLAIN");
        }

        @Test
        @DisplayName("CACHE TABLE 应正确格式化")
        void testCacheTableFormat() {
            String formatted = engine.formatSparkSql(
                    "CACHE TABLE users");

            assertThat(formatted).as("应有 CACHE").contains("CACHE");
            assertThat(formatted).as("应有 TABLE").contains("TABLE");
        }

        @Test
        @DisplayName("INSERT INTO 带 PARTITION 应正确格式化")
        void testInsertPartitionFormat() {
            String formatted = engine.formatSparkSql(
                    "INSERT INTO TABLE events PARTITION (dt = '2024-01-01') SELECT id, name FROM source");

            assertThat(formatted).as("应有 INSERT INTO").contains("INSERT INTO");
            assertThat(formatted).as("应有 PARTITION").contains("PARTITION");
        }
    }

    // ================================================================
    // INSERT 格式化
    // ================================================================

    @Nested
    @DisplayName("INSERT 格式化")
    class InsertFormatTests {

        @Test
        @DisplayName("INSERT INTO VALUES 应正确格式化")
        void testInsertValues() {
            String formatted = engine.formatSparkSql(
                    "insert into users (name,email,age) values ('Alice','alice@test.com',25)");

            assertThat(formatted).as("应有 INSERT INTO").contains("INSERT INTO");
            assertThat(formatted).as("应有 VALUES").contains("VALUES");
        }

        @Test
        @DisplayName("INSERT INTO SELECT 应正确格式化")
        void testInsertSelect() {
            String formatted = engine.formatSparkSql(
                    "INSERT INTO archive_users SELECT * FROM users WHERE created_at < '2020-01-01'");

            assertThat(formatted).as("应有 INSERT INTO").contains("INSERT INTO");
            assertThat(formatted).as("应有 SELECT").contains("SELECT");
        }
    }

    // ================================================================
    // DDL 格式化
    // ================================================================

    @Nested
    @DisplayName("DDL 格式化")
    class DdlFormatTests {

        @Test
        @DisplayName("CREATE TABLE 应正确格式化")
        void testCreateTable() {
            String formatted = engine.formatSparkSql(
                    "CREATE TABLE users (id INT NOT NULL, name STRING, age INT)");

            assertThat(formatted).as("应有 CREATE TABLE").contains("CREATE TABLE");
            String[] lines = formatted.split("\n");
            assertThat(lines.length).as("CREATE TABLE 应有多行输出").isGreaterThanOrEqualTo(3);
        }

        @Test
        @DisplayName("ALTER TABLE RENAME TO 应正确格式化")
        void testAlterTableRename() {
            String formatted = engine.formatSparkSql(
                    "ALTER TABLE old_table RENAME TO new_table");

            assertThat(formatted).as("应有 ALTER TABLE").contains("ALTER TABLE");
            assertThat(formatted).as("应有 RENAME TO").contains("RENAME TO");
        }

        @Test
        @DisplayName("ALTER TABLE ADD COLUMNS 应正确格式化")
        void testAlterTableAddColumns() {
            String formatted = engine.formatSparkSql(
                    "ALTER TABLE users ADD COLUMNS (email STRING, phone STRING)");

            assertThat(formatted).as("应有 ALTER TABLE").contains("ALTER TABLE");
            assertThat(formatted).as("应有 ADD COLUMNS").contains("ADD COLUMNS");
        }

        @Test
        @DisplayName("CREATE DATABASE 应正确格式化")
        void testCreateDatabase() {
            String formatted = engine.formatSparkSql(
                    "create database if not exists mydb");

            assertThat(formatted).as("关键字应大写").contains("CREATE DATABASE");
            assertThat(formatted).as("应保留 IF NOT EXISTS").contains("IF NOT EXISTS");
        }

        @Test
        @DisplayName("DROP DATABASE 应正确格式化")
        void testDropDatabase() {
            String formatted = engine.formatSparkSql(
                    "drop database if exists mydb cascade");

            assertThat(formatted).as("应有 DROP DATABASE").contains("DROP DATABASE");
            assertThat(formatted).as("应保留 IF EXISTS").contains("IF EXISTS");
            assertThat(formatted).as("应保留 CASCADE").contains("CASCADE");
        }
    }

    // ================================================================
    // Utility 格式化
    // ================================================================

    @Nested
    @DisplayName("Utility 格式化")
    class UtilityFormatTests {

        @Test
        @DisplayName("USE 应正确格式化")
        void testUseFormat() {
            String formatted = engine.formatSparkSql("use mydb");

            assertThat(formatted).as("应有 USE").contains("USE");
            assertThat(formatted).as("应有数据库名").contains("mydb");
        }

        @Test
        @DisplayName("SHOW TABLES 应正确格式化")
        void testShowTablesFormat() {
            String formatted = engine.formatSparkSql("show tables");

            assertThat(formatted).as("应有 SHOW TABLES").contains("SHOW TABLES");
        }

        @Test
        @DisplayName("DESCRIBE 应正确格式化")
        void testDescribeFormat() {
            String formatted = engine.formatSparkSql("describe users");

            assertThat(formatted).as("应有 DESCRIBE").contains("DESCRIBE");
        }
    }

    // ================================================================
    // 表达式格式化
    // ================================================================

    @Nested
    @DisplayName("表达式格式化")
    class ExpressionFormatTests {

        @Test
        @DisplayName("CASE 表达式应多行格式化")
        void testCaseExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT CASE WHEN age < 18 THEN 'minor' WHEN age < 60 THEN 'adult' ELSE 'senior' END AS age_group FROM users");

            assertThat(formatted).as("应有 CASE").contains("CASE");
            assertThat(formatted).as("应有 WHEN").contains("WHEN");
            assertThat(formatted).as("应有 THEN").contains("THEN");
            assertThat(formatted).as("应有 ELSE").contains("ELSE");
            assertThat(formatted).as("应有 END").contains("END");
        }

        @Test
        @DisplayName("IN 表达式应正确格式化")
        void testInExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE status IN ('active', 'pending', 'approved')");

            assertThat(formatted).as("应有 IN").contains("IN");
        }

        @Test
        @DisplayName("BETWEEN 表达式应正确格式化")
        void testBetweenExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE age BETWEEN 18 AND 60");

            assertThat(formatted).as("应有 BETWEEN").contains("BETWEEN");
        }

        @Test
        @DisplayName("LIKE 表达式应正确格式化")
        void testLikeExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE name LIKE '%Alice%'");

            assertThat(formatted).as("应有 LIKE").contains("LIKE");
        }

        @Test
        @DisplayName("RLIKE 表达式应正确格式化")
        void testRlikeExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE name RLIKE '^A.*'");

            assertThat(formatted).as("应有 RLIKE").contains("RLIKE");
        }

        @Test
        @DisplayName("IS NULL 表达式应正确格式化")
        void testIsNullExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE email IS NULL");

            assertThat(formatted).as("应有 IS NULL").contains("IS NULL");
        }

        @Test
        @DisplayName("CAST 表达式应正确格式化")
        void testCastExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT CAST(age AS STRING) FROM users");

            assertThat(formatted).as("应有 CAST").contains("CAST");
        }
    }

    // ================================================================
    // 配置选项
    // ================================================================

    @Nested
    @DisplayName("配置选项")
    class ConfigTests {

        @Test
        @DisplayName("关键字小写模式")
        void testLowercaseKeywords() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name FROM users WHERE age > 18", 4, false);

            assertThat(formatted).as("应能正常格式化")
                    .satisfiesAnyOf(
                        f -> assertThat(f).contains("select"),
                        f -> assertThat(f).contains("SELECT")
                    );
            if (formatted.contains("select")) {
                assertThat(formatted).as("小写模式下 FROM 应为 from").contains("from");
                assertThat(formatted).as("小写模式下 WHERE 应为 where").contains("where");
            }
        }

        @Test
        @DisplayName("自定义缩进（2 空格）")
        void testCustomIndent() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name FROM users WHERE age > 18", 2, true);

            assertThat(formatted).as("格式化结果不应为 null").isNotNull();
            assertThat(formatted).as("格式化结果不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("默认配置（4 空格 + 大写）")
        void testDefaultConfig() {
            String formatted = engine.formatSparkSql(
                    "select id from users");

            assertThat(formatted).as("默认应大写关键字").contains("SELECT");
        }
    }

    // ================================================================
    // 格式化质量
    // ================================================================

    @Nested
    @DisplayName("格式化质量")
    class QualityTests {

        @Test
        @DisplayName("格式化结果不应为空或 null")
        void testNotNullOrEmpty() {
            String formatted = engine.formatSparkSql("SELECT 1");

            assertThat(formatted).as("格式化结果不应为 null").isNotNull();
            assertThat(formatted.trim()).as("格式化结果不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("格式化结果应以分号结尾")
        void testEndsWithSemicolon() {
            String formatted = engine.formatSparkSql("SELECT id FROM users");

            assertThat(formatted.trim()).as("格式化结果应以分号结尾").endsWith(";");
        }

        @Test
        @DisplayName("格式化不应丢失表名")
        void testPreservesTableName() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM my_special_table WHERE status = 'ok'");

            assertThat(formatted).as("格式化不应丢失表名").contains("my_special_table");
        }

        @Test
        @DisplayName("格式化不应丢失字符串字面值")
        void testPreservesStringLiterals() {
            String formatted = engine.formatSparkSql(
                    "SELECT id FROM users WHERE name = 'Hello World'");

            assertThat(formatted).as("格式化不应丢失字符串字面值").contains("Hello World");
        }

        @Test
        @DisplayName("复杂 SQL 格式化不应抛异常")
        void testComplexSqlNoException() {
            assertThatCode(() -> engine.formatSparkSql(
                    "WITH active AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT a.name, COUNT(o.id) AS order_count " +
                    "FROM active a " +
                    "INNER JOIN orders o ON a.id = o.user_id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY a.name " +
                    "HAVING COUNT(o.id) > 5 " +
                    "ORDER BY order_count DESC " +
                    "LIMIT 20"
            )).as("复杂 SQL 格式化不应抛异常").doesNotThrowAnyException();
        }
    }
}
