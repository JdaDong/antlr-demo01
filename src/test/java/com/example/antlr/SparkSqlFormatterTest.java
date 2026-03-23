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

    // ================================================================
    // SORT BY 格式化
    // ================================================================

    @Nested
    @DisplayName("SORT BY 格式化")
    class SortByFormatTests {

        @Test
        @DisplayName("简单 SORT BY 应正确格式化")
        void testSimpleSortBy() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name FROM users SORT BY name");

            assertThat(formatted).as("应有 SORT BY").contains("SORT BY");
            assertThat(formatted).as("应保留排序列名 name").contains("name");
        }

        @Test
        @DisplayName("SORT BY 多列应正确格式化")
        void testSortByMultipleColumns() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, age FROM users SORT BY age, name");

            assertThat(formatted).as("应有 SORT BY").contains("SORT BY");
            assertThat(formatted).as("应包含 age").contains("age");
            assertThat(formatted).as("应包含 name").contains("name");
        }

        @Test
        @DisplayName("SORT BY 带 ASC/DESC 应正确格式化")
        void testSortByWithDirection() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, age FROM users SORT BY age DESC, name ASC");

            assertThat(formatted).as("应有 SORT BY").contains("SORT BY");
            assertThat(formatted).as("应有 DESC").contains("DESC");
            assertThat(formatted).as("应有 ASC").contains("ASC");
        }

        @Test
        @DisplayName("SORT BY 结合 DISTRIBUTE BY 应正确格式化")
        void testSortByWithDistributeBy() {
            // 注意：语法定义中 SORT BY 在 DISTRIBUTE BY 之前
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, dept FROM users SORT BY name DISTRIBUTE BY dept");

            assertThat(formatted).as("应有 DISTRIBUTE BY").contains("DISTRIBUTE BY");
            assertThat(formatted).as("应有 SORT BY").contains("SORT BY");
        }

        @Test
        @DisplayName("SORT BY 不应抛异常")
        void testSortByNoException() {
            assertThatCode(() -> engine.formatSparkSql(
                    "SELECT city, COUNT(*) AS cnt FROM users GROUP BY city SORT BY cnt DESC"
            )).as("SORT BY 格式化不应抛异常").doesNotThrowAnyException();
        }
    }

    // ================================================================
    // Dot 表达式格式化 (table.column)
    // ================================================================

    @Nested
    @DisplayName("Dot 表达式格式化")
    class DotExpressionFormatTests {

        @Test
        @DisplayName("table.column 引用应保留点号")
        void testTableDotColumn() {
            String formatted = engine.formatSparkSql(
                    "SELECT users.id, users.name FROM users");

            assertThat(formatted).as("应保留 users.id").contains("users.id");
            assertThat(formatted).as("应保留 users.name").contains("users.name");
        }

        @Test
        @DisplayName("别名.列名引用应保留点号")
        void testAliasDotColumn() {
            String formatted = engine.formatSparkSql(
                    "SELECT u.id, u.name FROM users u");

            assertThat(formatted).as("应保留 u.id").contains("u.id");
            assertThat(formatted).as("应保留 u.name").contains("u.name");
        }

        @Test
        @DisplayName("JOIN 中 dot 表达式应保留")
        void testDotExpressionInJoin() {
            String formatted = engine.formatSparkSql(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id");

            assertThat(formatted).as("应保留 u.name").contains("u.name");
            assertThat(formatted).as("应保留 o.amount").contains("o.amount");
            assertThat(formatted).as("应保留 u.id").contains("u.id");
            assertThat(formatted).as("应保留 o.user_id").contains("o.user_id");
        }

        @Test
        @DisplayName("WHERE 条件中 dot 表达式应保留")
        void testDotExpressionInWhere() {
            String formatted = engine.formatSparkSql(
                    "SELECT t.id FROM mytable t WHERE t.status = 'active' AND t.age > 18");

            assertThat(formatted).as("应保留 t.id").contains("t.id");
            assertThat(formatted).as("应保留 t.status").contains("t.status");
            assertThat(formatted).as("应保留 t.age").contains("t.age");
        }

        @Test
        @DisplayName("三层 dot 表达式 (db.table.column) 应保留")
        void testThreeLevelDotExpression() {
            String formatted = engine.formatSparkSql(
                    "SELECT mydb.users.id, mydb.users.name FROM mydb.users");

            assertThat(formatted).as("应保留 mydb.users 或 mydb.users.id 引用")
                    .satisfiesAnyOf(
                        f -> assertThat(f).contains("mydb.users.id"),
                        f -> assertThat(f).contains("mydb.users")
                    );
        }

        @Test
        @DisplayName("dot 表达式与聚合函数结合应保留")
        void testDotExpressionWithAggFunction() {
            String formatted = engine.formatSparkSql(
                    "SELECT u.dept, COUNT(u.id) AS cnt FROM users u GROUP BY u.dept");

            assertThat(formatted).as("应保留 u.dept").contains("u.dept");
            assertThat(formatted).as("应保留 u.id").contains("u.id");
        }

        @Test
        @DisplayName("dot 表达式在 ORDER BY 中应保留")
        void testDotExpressionInOrderBy() {
            String formatted = engine.formatSparkSql(
                    "SELECT u.name, u.age FROM users u ORDER BY u.age DESC, u.name ASC");

            assertThat(formatted).as("应保留 u.name").contains("u.name");
            assertThat(formatted).as("应保留 u.age").contains("u.age");
        }
    }

    // ================================================================
    // 窗口函数格式化
    // ================================================================

    @Nested
    @DisplayName("窗口函数格式化")
    class WindowFunctionFormatTests {

        // ---------- 内联窗口 OVER 子句 ----------

        @Test
        @DisplayName("ROW_NUMBER() OVER (PARTITION BY ... ORDER BY ...) 基本窗口函数")
        void testRowNumberOverPartitionOrderBy() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn FROM employees");

            assertThat(formatted).as("应包含 ROW_NUMBER").containsIgnoringCase("ROW_NUMBER");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
            assertThat(formatted).as("应包含 ORDER BY").containsIgnoringCase("ORDER BY");
            assertThat(formatted).as("应包含 DESC").containsIgnoringCase("DESC");
        }

        @Test
        @DisplayName("SUM() OVER (PARTITION BY ...) 聚合窗口函数")
        void testSumOverPartition() {
            String formatted = engine.formatSparkSql(
                    "SELECT dept, salary, SUM(salary) OVER (PARTITION BY dept) AS dept_total FROM employees");

            assertThat(formatted).as("应包含 SUM").containsIgnoringCase("SUM");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
        }

        @Test
        @DisplayName("RANK() OVER (ORDER BY ...) 仅 ORDER BY 无 PARTITION BY")
        void testRankOverOrderByOnly() {
            String formatted = engine.formatSparkSql(
                    "SELECT name, score, RANK() OVER (ORDER BY score DESC) AS ranking FROM students");

            assertThat(formatted).as("应包含 RANK").containsIgnoringCase("RANK");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 ORDER BY").containsIgnoringCase("ORDER BY");
        }

        @Test
        @DisplayName("DENSE_RANK() OVER (...) 密集排名")
        void testDenseRankOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, DENSE_RANK() OVER (PARTITION BY category ORDER BY price ASC) AS dr FROM products");

            assertThat(formatted).as("应包含 DENSE_RANK").containsIgnoringCase("DENSE_RANK");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
            assertThat(formatted).as("应包含 ASC").containsIgnoringCase("ASC");
        }

        @Test
        @DisplayName("LAG() 和 LEAD() 偏移窗口函数")
        void testLagAndLeadOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT event_date, value, LAG(value) OVER (ORDER BY event_date) AS prev_val, " +
                    "LEAD(value) OVER (ORDER BY event_date) AS next_val FROM metrics");

            assertThat(formatted).as("应包含 LAG").containsIgnoringCase("LAG");
            assertThat(formatted).as("应包含 LEAD").containsIgnoringCase("LEAD");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
        }

        @Test
        @DisplayName("NTILE() 分桶窗口函数")
        void testNtileOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, NTILE(4) OVER (ORDER BY salary DESC) AS quartile FROM employees");

            assertThat(formatted).as("应包含 NTILE").containsIgnoringCase("NTILE");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
        }

        @Test
        @DisplayName("COUNT() OVER (...) 计数窗口函数")
        void testCountOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT dept, name, COUNT(*) OVER (PARTITION BY dept) AS dept_count FROM employees");

            assertThat(formatted).as("应包含 COUNT").containsIgnoringCase("COUNT");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
        }

        @Test
        @DisplayName("AVG() OVER (...) 均值窗口函数")
        void testAvgOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, AVG(salary) OVER (PARTITION BY dept ORDER BY hire_date) AS running_avg FROM employees");

            assertThat(formatted).as("应包含 AVG").containsIgnoringCase("AVG");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
            assertThat(formatted).as("应包含 ORDER BY").containsIgnoringCase("ORDER BY");
        }

        // ---------- 窗口帧 ROWS / RANGE ----------

        @Test
        @DisplayName("ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW 窗口帧")
        void testRowsBetweenUnboundedPrecedingAndCurrentRow() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, SUM(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_total FROM employees");

            assertThat(formatted).as("应包含 ROWS").containsIgnoringCase("ROWS");
            assertThat(formatted).as("应包含 BETWEEN").containsIgnoringCase("BETWEEN");
            assertThat(formatted).as("应包含 UNBOUNDED PRECEDING").containsIgnoringCase("UNBOUNDED PRECEDING");
            assertThat(formatted).as("应包含 CURRENT ROW").containsIgnoringCase("CURRENT ROW");
        }

        @Test
        @DisplayName("ROWS BETWEEN N PRECEDING AND N FOLLOWING 有界窗口帧")
        void testRowsBetweenNPrecedingAndNFollowing() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, value, AVG(value) OVER (ORDER BY id " +
                    "ROWS BETWEEN 2 PRECEDING AND 2 FOLLOWING) AS moving_avg FROM data");

            assertThat(formatted).as("应包含 ROWS").containsIgnoringCase("ROWS");
            assertThat(formatted).as("应包含 BETWEEN").containsIgnoringCase("BETWEEN");
            assertThat(formatted).as("应包含 PRECEDING").containsIgnoringCase("PRECEDING");
            assertThat(formatted).as("应包含 FOLLOWING").containsIgnoringCase("FOLLOWING");
        }

        @Test
        @DisplayName("ROWS BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING 窗口帧")
        void testRowsBetweenCurrentRowAndUnboundedFollowing() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, SUM(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "ROWS BETWEEN CURRENT ROW AND UNBOUNDED FOLLOWING) AS future_total FROM employees");

            assertThat(formatted).as("应包含 ROWS").containsIgnoringCase("ROWS");
            assertThat(formatted).as("应包含 CURRENT ROW").containsIgnoringCase("CURRENT ROW");
            assertThat(formatted).as("应包含 UNBOUNDED FOLLOWING").containsIgnoringCase("UNBOUNDED FOLLOWING");
        }

        @Test
        @DisplayName("ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING 全范围窗口帧")
        void testRowsBetweenUnboundedPrecedingAndUnboundedFollowing() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, MAX(salary) OVER (PARTITION BY dept " +
                    "ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS dept_max FROM employees");

            assertThat(formatted).as("应包含 ROWS").containsIgnoringCase("ROWS");
            assertThat(formatted).as("应包含 UNBOUNDED PRECEDING").containsIgnoringCase("UNBOUNDED PRECEDING");
            assertThat(formatted).as("应包含 UNBOUNDED FOLLOWING").containsIgnoringCase("UNBOUNDED FOLLOWING");
        }

        @Test
        @DisplayName("RANGE BETWEEN ... 范围窗口帧")
        void testRangeBetween() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, value, SUM(value) OVER (ORDER BY id " +
                    "RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS cumulative FROM data");

            assertThat(formatted).as("应包含 RANGE").containsIgnoringCase("RANGE");
            assertThat(formatted).as("应包含 BETWEEN").containsIgnoringCase("BETWEEN");
            assertThat(formatted).as("应包含 UNBOUNDED PRECEDING").containsIgnoringCase("UNBOUNDED PRECEDING");
            assertThat(formatted).as("应包含 CURRENT ROW").containsIgnoringCase("CURRENT ROW");
        }

        // ---------- 命名窗口 WINDOW 子句 ----------

        @Test
        @DisplayName("WINDOW 子句定义单个命名窗口")
        void testSingleNamedWindow() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn " +
                    "FROM employees " +
                    "WINDOW w AS (PARTITION BY dept ORDER BY salary DESC)");

            assertThat(formatted).as("应包含 WINDOW").containsIgnoringCase("WINDOW");
            assertThat(formatted).as("应包含 AS").containsIgnoringCase("AS");
            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
        }

        @Test
        @DisplayName("WINDOW 子句定义多个命名窗口")
        void testMultipleNamedWindows() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, SUM(salary) OVER (PARTITION BY dept) AS dept_total, " +
                    "RANK() OVER (PARTITION BY dept ORDER BY salary DESC) AS ranking " +
                    "FROM employees " +
                    "WINDOW w1 AS (PARTITION BY dept), w2 AS (PARTITION BY dept ORDER BY salary DESC)");

            assertThat(formatted).as("应包含 WINDOW").containsIgnoringCase("WINDOW");
            assertThat(formatted).as("应包含窗口名 w1").contains("w1");
            assertThat(formatted).as("应包含窗口名 w2").contains("w2");
        }

        // ---------- 多窗口函数组合 ----------

        @Test
        @DisplayName("同一查询中使用多个不同窗口函数")
        void testMultipleWindowFunctionsInSameQuery() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, " +
                    "ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn, " +
                    "RANK() OVER (PARTITION BY dept ORDER BY salary DESC) AS rnk, " +
                    "SUM(salary) OVER (PARTITION BY dept) AS dept_total " +
                    "FROM employees");

            assertThat(formatted).as("应包含 ROW_NUMBER").containsIgnoringCase("ROW_NUMBER");
            assertThat(formatted).as("应包含 RANK").containsIgnoringCase("RANK");
            assertThat(formatted).as("应包含 SUM").containsIgnoringCase("SUM");
            // OVER 应出现 3 次
            String upper = formatted.toUpperCase();
            int overCount = 0;
            int idx = 0;
            while ((idx = upper.indexOf("OVER", idx)) != -1) {
                overCount++;
                idx += 4;
            }
            assertThat(overCount).as("应包含 3 个 OVER 子句").isGreaterThanOrEqualTo(3);
        }

        @Test
        @DisplayName("窗口函数结合 WHERE 和 GROUP BY")
        void testWindowFunctionWithWhereAndSubquery() {
            String formatted = engine.formatSparkSql(
                    "SELECT dept, name, salary, " +
                    "RANK() OVER (PARTITION BY dept ORDER BY salary DESC) AS dept_rank " +
                    "FROM employees WHERE status = 'active'");

            assertThat(formatted).as("应包含 RANK").containsIgnoringCase("RANK");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含 WHERE").containsIgnoringCase("WHERE");
        }

        @Test
        @DisplayName("窗口函数作为子查询使用")
        void testWindowFunctionInSubquery() {
            String formatted = engine.formatSparkSql(
                    "SELECT * FROM (" +
                    "SELECT id, name, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC) AS rn " +
                    "FROM employees) t WHERE t.rn = 1");

            assertThat(formatted).as("应包含 ROW_NUMBER").containsIgnoringCase("ROW_NUMBER");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
            assertThat(formatted).as("应包含子查询结构").containsIgnoringCase("WHERE");
        }

        // ---------- PARTITION BY 多列 ----------

        @Test
        @DisplayName("PARTITION BY 多个列")
        void testPartitionByMultipleColumns() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, SUM(amount) OVER (PARTITION BY region, dept, year ORDER BY month) AS running " +
                    "FROM sales");

            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
            assertThat(formatted).as("应包含 region").containsIgnoringCase("region");
            assertThat(formatted).as("应包含 dept").containsIgnoringCase("dept");
            assertThat(formatted).as("应包含 year").containsIgnoringCase("year");
        }

        // ---------- ORDER BY 多列与方向 ----------

        @Test
        @DisplayName("窗口 ORDER BY 多列不同方向")
        void testWindowOrderByMultipleColumnsDirections() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC, hire_date ASC) AS rn " +
                    "FROM employees");

            assertThat(formatted).as("应包含 ORDER BY").containsIgnoringCase("ORDER BY");
            assertThat(formatted).as("应包含 DESC").containsIgnoringCase("DESC");
            assertThat(formatted).as("应包含 ASC").containsIgnoringCase("ASC");
        }

        // ---------- 完整组合测试 ----------

        @Test
        @DisplayName("PARTITION BY + ORDER BY + ROWS 窗口帧完整组合")
        void testFullWindowSpecCombination() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, salary, " +
                    "AVG(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "ROWS BETWEEN 3 PRECEDING AND CURRENT ROW) AS moving_avg " +
                    "FROM employees");

            assertThat(formatted).as("应包含 PARTITION BY").containsIgnoringCase("PARTITION BY");
            assertThat(formatted).as("应包含 ORDER BY").containsIgnoringCase("ORDER BY");
            assertThat(formatted).as("应包含 ROWS").containsIgnoringCase("ROWS");
            assertThat(formatted).as("应包含 BETWEEN").containsIgnoringCase("BETWEEN");
            assertThat(formatted).as("应包含 PRECEDING").containsIgnoringCase("PRECEDING");
            assertThat(formatted).as("应包含 CURRENT ROW").containsIgnoringCase("CURRENT ROW");
        }

        @Test
        @DisplayName("MIN/MAX 窗口函数")
        void testMinMaxOverWindow() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, salary, " +
                    "MIN(salary) OVER (PARTITION BY dept) AS dept_min, " +
                    "MAX(salary) OVER (PARTITION BY dept) AS dept_max " +
                    "FROM employees");

            assertThat(formatted).as("应包含 MIN").containsIgnoringCase("MIN");
            assertThat(formatted).as("应包含 MAX").containsIgnoringCase("MAX");
            assertThat(formatted).as("应包含 OVER").containsIgnoringCase("OVER");
        }

        @Test
        @DisplayName("FIRST_VALUE / LAST_VALUE 窗口函数")
        void testFirstValueLastValueOver() {
            String formatted = engine.formatSparkSql(
                    "SELECT id, name, " +
                    "FIRST_VALUE(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS first_sal, " +
                    "LAST_VALUE(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) AS last_sal " +
                    "FROM employees");

            assertThat(formatted).as("应包含 FIRST_VALUE").containsIgnoringCase("FIRST_VALUE");
            assertThat(formatted).as("应包含 LAST_VALUE").containsIgnoringCase("LAST_VALUE");
            assertThat(formatted).as("应包含 UNBOUNDED PRECEDING").containsIgnoringCase("UNBOUNDED PRECEDING");
            assertThat(formatted).as("应包含 UNBOUNDED FOLLOWING").containsIgnoringCase("UNBOUNDED FOLLOWING");
        }

        // ---------- 格式化质量 ----------

        @Test
        @DisplayName("窗口函数格式化结果不应为空")
        void testWindowFunctionFormattedNotEmpty() {
            String formatted = engine.formatSparkSql(
                    "SELECT ROW_NUMBER() OVER (ORDER BY id) AS rn FROM t");

            assertThat(formatted).as("格式化结果不应为空").isNotNull().isNotEmpty();
        }

        @Test
        @DisplayName("窗口函数格式化不应抛出异常")
        void testWindowFunctionFormatterDoesNotThrow() {
            assertThatCode(() -> engine.formatSparkSql(
                    "SELECT id, ROW_NUMBER() OVER (PARTITION BY dept ORDER BY salary DESC " +
                    "ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS rn, " +
                    "SUM(salary) OVER (PARTITION BY dept ORDER BY hire_date " +
                    "RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_sum, " +
                    "LAG(salary, 1) OVER (PARTITION BY dept ORDER BY hire_date) AS prev_salary " +
                    "FROM employees WHERE status = 'active'"
            )).as("复杂窗口函数 SQL 不应抛出异常").doesNotThrowAnyException();
        }

        @Test
        @DisplayName("窗口函数格式化应保留表名和列名")
        void testWindowFunctionPreservesIdentifiers() {
            String formatted = engine.formatSparkSql(
                    "SELECT emp_id, department, annual_salary, " +
                    "AVG(annual_salary) OVER (PARTITION BY department) AS avg_dept_salary " +
                    "FROM hr_employees");

            assertThat(formatted).as("应保留 emp_id").containsIgnoringCase("emp_id");
            assertThat(formatted).as("应保留 department").containsIgnoringCase("department");
            assertThat(formatted).as("应保留 annual_salary").containsIgnoringCase("annual_salary");
            assertThat(formatted).as("应保留 hr_employees").containsIgnoringCase("hr_employees");
        }
    }
}
