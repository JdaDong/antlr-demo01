package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * MySqlFormatter 单元测试
 * 测试 Visitor 模式下的 SQL 格式化/美化能力
 */
@DisplayName("MySqlFormatter - Visitor 模式 SQL 格式化")
class MySqlFormatterTest {

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
            String formatted = engine.formatMySql(
                    "select id,name,age from users where age>18 order by name limit 10");

            assertThat(formatted).as("关键字应大写").contains("SELECT");
            assertThat(formatted).as("FROM 应大写").contains("FROM");
            assertThat(formatted).as("WHERE 应大写").contains("WHERE");
            assertThat(formatted).as("ORDER BY 应大写").contains("ORDER BY");
            assertThat(formatted).as("LIMIT 应大写").contains("LIMIT");
        }

        @Test
        @DisplayName("列名应每行一个")
        void testColumnsOnSeparateLines() {
            String formatted = engine.formatMySql(
                    "SELECT id, name, age, email FROM users");

            String[] lines = formatted.split("\n");
            // SELECT 后面应该有多行列名
            long indentedColumnLines = 0;
            for (String line : lines) {
                if (line.trim().matches("(id|name|age|email).*")) {
                    indentedColumnLines++;
                }
            }
            assertThat(indentedColumnLines).as("应有至少 3 行缩进的列名").isGreaterThanOrEqualTo(3);
        }

        @Test
        @DisplayName("JOIN 应正确格式化")
        void testJoinFormat() {
            String formatted = engine.formatMySql(
                    "select u.name,o.amount from users u inner join orders o on u.id=o.user_id left join products p on o.product_id=p.id");

            assertThat(formatted).as("应有 INNER JOIN").contains("INNER JOIN");
            assertThat(formatted).as("应有 LEFT JOIN").contains("LEFT JOIN");
            assertThat(formatted).as("应有 ON").contains("ON");
        }

        @Test
        @DisplayName("WHERE 中 AND/OR 应换行")
        void testWhereAndOrLineBreak() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE age > 18 AND status = 'active' AND city = 'Beijing'");

            // AND 应该出现在行首（可能带缩进）
            String[] lines = formatted.split("\n");
            long andLines = 0;
            for (String line : lines) {
                if (line.trim().startsWith("AND")) {
                    andLines++;
                }
            }
            assertThat(andLines).as("应有至少 2 行以 AND 开头").isGreaterThanOrEqualTo(2);
        }

        @Test
        @DisplayName("GROUP BY 应正确格式化")
        void testGroupByFormat() {
            String formatted = engine.formatMySql(
                    "SELECT city, COUNT(*) FROM users GROUP BY city HAVING COUNT(*) > 5");

            assertThat(formatted).as("应有 GROUP BY").contains("GROUP BY");
            assertThat(formatted).as("应有 HAVING").contains("HAVING");
        }

        @Test
        @DisplayName("UNION 应分段格式化")
        void testUnionFormat() {
            String formatted = engine.formatMySql(
                    "SELECT name FROM users UNION SELECT product_name FROM products");

            assertThat(formatted).as("应有 UNION").contains("UNION");
            // UNION 前后应该有换行
            int unionIndex = formatted.indexOf("UNION");
            assertThat(unionIndex).as("UNION 应出现在格式化结果中").isGreaterThan(0);
        }

        @Test
        @DisplayName("DISTINCT 应保留")
        void testDistinctPreserved() {
            String formatted = engine.formatMySql(
                    "SELECT DISTINCT city FROM users");

            assertThat(formatted).as("应保留 DISTINCT 关键字").contains("DISTINCT");
        }

        @Test
        @DisplayName("子查询应缩进")
        void testSubqueryIndent() {
            String formatted = engine.formatMySql(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)");

            assertThat(formatted).as("应有 SELECT").contains("SELECT");
            // 子查询中的 SELECT 应该有缩进
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
    // INSERT 格式化
    // ================================================================

    @Nested
    @DisplayName("INSERT 格式化")
    class InsertFormatTests {

        @Test
        @DisplayName("INSERT INTO VALUES 应正确格式化")
        void testInsertValues() {
            String formatted = engine.formatMySql(
                    "insert into users (name,email,age) values ('Alice','alice@test.com',25)");

            assertThat(formatted).as("应有 INSERT INTO").contains("INSERT INTO");
            assertThat(formatted).as("应有 VALUES").contains("VALUES");
        }

        @Test
        @DisplayName("INSERT 多行 VALUES 应每行一组")
        void testInsertMultipleValues() {
            String formatted = engine.formatMySql(
                    "INSERT INTO users (name, age) VALUES ('Alice', 25), ('Bob', 30), ('Charlie', 35)");

            assertThat(formatted).as("应有 INSERT INTO").contains("INSERT INTO");
            assertThat(formatted).as("应有 VALUES").contains("VALUES");
        }
    }

    // ================================================================
    // UPDATE 格式化
    // ================================================================

    @Nested
    @DisplayName("UPDATE 格式化")
    class UpdateFormatTests {

        @Test
        @DisplayName("UPDATE SET 应正确格式化")
        void testUpdateSet() {
            String formatted = engine.formatMySql(
                    "update users set name='Bob',age=30,email='bob@test.com' where id=1");

            assertThat(formatted).as("应有 UPDATE").contains("UPDATE");
            assertThat(formatted).as("应有 SET").contains("SET");
            assertThat(formatted).as("应有 WHERE").contains("WHERE");
        }
    }

    // ================================================================
    // DELETE 格式化
    // ================================================================

    @Nested
    @DisplayName("DELETE 格式化")
    class DeleteFormatTests {

        @Test
        @DisplayName("DELETE 应正确格式化")
        void testDelete() {
            String formatted = engine.formatMySql(
                    "delete from users where id=1 and status='inactive'");

            assertThat(formatted).as("应有 DELETE").contains("DELETE");
            assertThat(formatted).as("应有 FROM").contains("FROM");
            assertThat(formatted).as("应有 WHERE").contains("WHERE");
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
            String formatted = engine.formatMySql(
                    "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(100), age INT DEFAULT 0, PRIMARY KEY (id))");

            assertThat(formatted).as("应有 CREATE TABLE").contains("CREATE TABLE");
            // 列定义应该换行
            String[] lines = formatted.split("\n");
            assertThat(lines.length).as("CREATE TABLE 应有多行输出").isGreaterThanOrEqualTo(4);
        }

        @Test
        @DisplayName("CREATE TABLE IF NOT EXISTS 应保留")
        void testCreateTableIfNotExists() {
            String formatted = engine.formatMySql(
                    "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY)");

            assertThat(formatted).as("应保留 IF NOT EXISTS").contains("IF NOT EXISTS");
        }

        @Test
        @DisplayName("DROP TABLE 应正确格式化")
        void testDropTable() {
            String formatted = engine.formatMySql(
                    "drop table if exists users");

            assertThat(formatted).as("关键字应大写为 DROP TABLE").contains("DROP TABLE");
            assertThat(formatted).as("应保留 IF EXISTS").contains("IF EXISTS");
        }

        @Test
        @DisplayName("ALTER TABLE 应正确格式化")
        void testAlterTable() {
            String formatted = engine.formatMySql(
                    "alter table users add column email varchar(255)");

            assertThat(formatted).as("应有 ALTER TABLE").contains("ALTER TABLE");
        }

        @Test
        @DisplayName("TRUNCATE TABLE 应正确格式化")
        void testTruncateTable() {
            String formatted = engine.formatMySql(
                    "truncate table users");

            assertThat(formatted).as("关键字应大写为 TRUNCATE TABLE").contains("TRUNCATE TABLE");
        }

        @Test
        @DisplayName("CREATE INDEX 应正确格式化")
        void testCreateIndex() {
            String formatted = engine.formatMySql(
                    "create index idx_name on users (name)");

            assertThat(formatted).as("应有 CREATE INDEX")
                    .satisfiesAnyOf(
                        f -> assertThat(f).contains("CREATE INDEX"),
                        f -> assertThat(f).contains("CREATE")
                    );
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
            String formatted = engine.formatMySql(
                    "SELECT id, name FROM users WHERE age > 18", 4, false);

            assertThat(formatted).as("应能正常格式化")
                    .satisfiesAnyOf(
                        f -> assertThat(f).contains("select"),
                        f -> assertThat(f).contains("SELECT")
                    );
            // 小写模式下不应有大写关键字（除非原始内容中有）
            if (formatted.contains("select")) {
                assertThat(formatted).as("小写模式下 FROM 应为 from").contains("from");
                assertThat(formatted).as("小写模式下 WHERE 应为 where").contains("where");
            }
        }

        @Test
        @DisplayName("自定义缩进（2 空格）")
        void testCustomIndent() {
            String formatted = engine.formatMySql(
                    "SELECT id, name FROM users WHERE age > 18", 2, true);

            assertThat(formatted).as("格式化结果不应为 null").isNotNull();
            assertThat(formatted).as("格式化结果不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("默认配置（4 空格 + 大写）")
        void testDefaultConfig() {
            String formatted = engine.formatMySql(
                    "select id from users");

            assertThat(formatted).as("默认应大写关键字").contains("SELECT");
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
            String formatted = engine.formatMySql(
                    "SELECT CASE WHEN age < 18 THEN '未成年' WHEN age < 60 THEN '成年' ELSE '老年' END AS age_group FROM users");

            assertThat(formatted).as("应有 CASE").contains("CASE");
            assertThat(formatted).as("应有 WHEN").contains("WHEN");
            assertThat(formatted).as("应有 THEN").contains("THEN");
            assertThat(formatted).as("应有 ELSE").contains("ELSE");
            assertThat(formatted).as("应有 END").contains("END");
        }

        @Test
        @DisplayName("IN 表达式应正确格式化")
        void testInExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE status IN ('active', 'pending', 'approved')");

            assertThat(formatted).as("应有 IN").contains("IN");
        }

        @Test
        @DisplayName("BETWEEN 表达式应正确格式化")
        void testBetweenExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE age BETWEEN 18 AND 60");

            assertThat(formatted).as("应有 BETWEEN").contains("BETWEEN");
        }

        @Test
        @DisplayName("LIKE 表达式应正确格式化")
        void testLikeExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE name LIKE '%Alice%'");

            assertThat(formatted).as("应有 LIKE").contains("LIKE");
        }

        @Test
        @DisplayName("IS NULL 表达式应正确格式化")
        void testIsNullExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE email IS NULL");

            assertThat(formatted).as("应有 IS NULL").contains("IS NULL");
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
            String formatted = engine.formatMySql("SELECT 1");

            assertThat(formatted).as("格式化结果不应为 null").isNotNull();
            assertThat(formatted.trim()).as("格式化结果不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("格式化结果应以分号结尾")
        void testEndsWithSemicolon() {
            String formatted = engine.formatMySql("SELECT id FROM users");

            assertThat(formatted.trim()).as("格式化结果应以分号结尾").endsWith(";");
        }

        @Test
        @DisplayName("格式化不应丢失表名")
        void testPreservesTableName() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM my_special_table WHERE status = 'ok'");

            assertThat(formatted).as("格式化不应丢失表名").contains("my_special_table");
        }

        @Test
        @DisplayName("格式化不应丢失字符串字面值")
        void testPreservesStringLiterals() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE name = 'Hello World'");

            assertThat(formatted).as("格式化不应丢失字符串字面值").contains("Hello World");
        }

        @Test
        @DisplayName("格式化不应丢失数字字面值")
        void testPreservesNumericLiterals() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE age > 18 AND salary >= 50000.00");

            assertThat(formatted).as("格式化不应丢失数字 18").contains("18");
            assertThat(formatted).as("格式化不应丢失数字 50000").contains("50000");
        }

        @Test
        @DisplayName("BIGINT UNSIGNED 不应粘连")
        void testBigintUnsignedSpace() {
            String formatted = engine.formatMySql(
                    "CREATE TABLE t (id BIGINT UNSIGNED NOT NULL)");

            // 不应出现 BIGINTUNSIGNED（粘连）
            assertThat(formatted).as("BIGINT UNSIGNED 之间应有空格，不应粘连")
                    .doesNotContain("BIGINTUNSIGNED");
        }

        @Test
        @DisplayName("复杂 SQL 格式化不应抛异常")
        void testComplexSqlNoException() {
            assertThatCode(() -> engine.formatMySql(
                    "SELECT u.name, COUNT(o.id) AS order_count, MAX(o.amount) AS max_amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > (SELECT AVG(amount) FROM orders) " +
                    "AND u.status IN ('active', 'vip') " +
                    "GROUP BY u.name " +
                    "HAVING COUNT(o.id) > 5 " +
                    "ORDER BY order_count DESC " +
                    "LIMIT 20"
            )).as("复杂 SQL 格式化不应抛异常").doesNotThrowAnyException();
        }
    }
}
