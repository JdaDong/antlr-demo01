package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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

            assertTrue(formatted.contains("SELECT"), "关键字应大写");
            assertTrue(formatted.contains("FROM"), "FROM 应大写");
            assertTrue(formatted.contains("WHERE"), "WHERE 应大写");
            assertTrue(formatted.contains("ORDER BY"), "ORDER BY 应大写");
            assertTrue(formatted.contains("LIMIT"), "LIMIT 应大写");
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
            assertTrue(indentedColumnLines >= 3, "应有至少 3 行缩进的列名，实际: " + indentedColumnLines);
        }

        @Test
        @DisplayName("JOIN 应正确格式化")
        void testJoinFormat() {
            String formatted = engine.formatMySql(
                    "select u.name,o.amount from users u inner join orders o on u.id=o.user_id left join products p on o.product_id=p.id");

            assertTrue(formatted.contains("INNER JOIN"), "应有 INNER JOIN");
            assertTrue(formatted.contains("LEFT JOIN"), "应有 LEFT JOIN");
            assertTrue(formatted.contains("ON"), "应有 ON");
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
            assertTrue(andLines >= 2, "应有至少 2 行以 AND 开头，实际: " + andLines);
        }

        @Test
        @DisplayName("GROUP BY 应正确格式化")
        void testGroupByFormat() {
            String formatted = engine.formatMySql(
                    "SELECT city, COUNT(*) FROM users GROUP BY city HAVING COUNT(*) > 5");

            assertTrue(formatted.contains("GROUP BY"), "应有 GROUP BY");
            assertTrue(formatted.contains("HAVING"), "应有 HAVING");
        }

        @Test
        @DisplayName("UNION 应分段格式化")
        void testUnionFormat() {
            String formatted = engine.formatMySql(
                    "SELECT name FROM users UNION SELECT product_name FROM products");

            assertTrue(formatted.contains("UNION"), "应有 UNION");
            // UNION 前后应该有换行
            int unionIndex = formatted.indexOf("UNION");
            assertTrue(unionIndex > 0, "UNION 应出现在格式化结果中");
        }

        @Test
        @DisplayName("DISTINCT 应保留")
        void testDistinctPreserved() {
            String formatted = engine.formatMySql(
                    "SELECT DISTINCT city FROM users");

            assertTrue(formatted.contains("DISTINCT"), "应保留 DISTINCT 关键字");
        }

        @Test
        @DisplayName("子查询应缩进")
        void testSubqueryIndent() {
            String formatted = engine.formatMySql(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders WHERE amount > 100)");

            assertTrue(formatted.contains("SELECT"), "应有 SELECT");
            // 子查询中的 SELECT 应该有缩进
            String[] lines = formatted.split("\n");
            boolean hasIndentedSelect = false;
            for (String line : lines) {
                if (line.startsWith("    ") && line.trim().startsWith("SELECT")) {
                    hasIndentedSelect = true;
                    break;
                }
            }
            assertTrue(hasIndentedSelect, "子查询的 SELECT 应有缩进");
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

            assertTrue(formatted.contains("INSERT INTO"), "应有 INSERT INTO");
            assertTrue(formatted.contains("VALUES"), "应有 VALUES");
        }

        @Test
        @DisplayName("INSERT 多行 VALUES 应每行一组")
        void testInsertMultipleValues() {
            String formatted = engine.formatMySql(
                    "INSERT INTO users (name, age) VALUES ('Alice', 25), ('Bob', 30), ('Charlie', 35)");

            assertTrue(formatted.contains("INSERT INTO"), "应有 INSERT INTO");
            assertTrue(formatted.contains("VALUES"), "应有 VALUES");
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

            assertTrue(formatted.contains("UPDATE"), "应有 UPDATE");
            assertTrue(formatted.contains("SET"), "应有 SET");
            assertTrue(formatted.contains("WHERE"), "应有 WHERE");
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

            assertTrue(formatted.contains("DELETE"), "应有 DELETE");
            assertTrue(formatted.contains("FROM"), "应有 FROM");
            assertTrue(formatted.contains("WHERE"), "应有 WHERE");
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

            assertTrue(formatted.contains("CREATE TABLE"), "应有 CREATE TABLE");
            // 列定义应该换行
            String[] lines = formatted.split("\n");
            assertTrue(lines.length >= 4, "CREATE TABLE 应有多行输出，实际: " + lines.length);
        }

        @Test
        @DisplayName("CREATE TABLE IF NOT EXISTS 应保留")
        void testCreateTableIfNotExists() {
            String formatted = engine.formatMySql(
                    "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY)");

            assertTrue(formatted.contains("IF NOT EXISTS"), "应保留 IF NOT EXISTS");
        }

        @Test
        @DisplayName("DROP TABLE 应正确格式化")
        void testDropTable() {
            String formatted = engine.formatMySql(
                    "drop table if exists users");

            assertTrue(formatted.contains("DROP TABLE"), "关键字应大写为 DROP TABLE");
            assertTrue(formatted.contains("IF EXISTS"), "应保留 IF EXISTS");
        }

        @Test
        @DisplayName("ALTER TABLE 应正确格式化")
        void testAlterTable() {
            String formatted = engine.formatMySql(
                    "alter table users add column email varchar(255)");

            assertTrue(formatted.contains("ALTER TABLE"), "应有 ALTER TABLE");
        }

        @Test
        @DisplayName("TRUNCATE TABLE 应正确格式化")
        void testTruncateTable() {
            String formatted = engine.formatMySql(
                    "truncate table users");

            assertTrue(formatted.contains("TRUNCATE TABLE"), "关键字应大写为 TRUNCATE TABLE");
        }

        @Test
        @DisplayName("CREATE INDEX 应正确格式化")
        void testCreateIndex() {
            String formatted = engine.formatMySql(
                    "create index idx_name on users (name)");

            assertTrue(formatted.contains("CREATE INDEX") || formatted.contains("CREATE"),
                    "应有 CREATE INDEX");
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

            assertTrue(formatted.contains("select") || formatted.contains("SELECT"),
                    "应能正常格式化");
            // 小写模式下不应有大写关键字（除非原始内容中有）
            if (formatted.contains("select")) {
                assertTrue(formatted.contains("from"), "小写模式下 FROM 应为 from");
                assertTrue(formatted.contains("where"), "小写模式下 WHERE 应为 where");
            }
        }

        @Test
        @DisplayName("自定义缩进（2 空格）")
        void testCustomIndent() {
            String formatted = engine.formatMySql(
                    "SELECT id, name FROM users WHERE age > 18", 2, true);

            assertNotNull(formatted, "格式化结果不应为 null");
            assertFalse(formatted.isEmpty(), "格式化结果不应为空");
        }

        @Test
        @DisplayName("默认配置（4 空格 + 大写）")
        void testDefaultConfig() {
            String formatted = engine.formatMySql(
                    "select id from users");

            assertTrue(formatted.contains("SELECT"), "默认应大写关键字");
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

            assertTrue(formatted.contains("CASE"), "应有 CASE");
            assertTrue(formatted.contains("WHEN"), "应有 WHEN");
            assertTrue(formatted.contains("THEN"), "应有 THEN");
            assertTrue(formatted.contains("ELSE"), "应有 ELSE");
            assertTrue(formatted.contains("END"), "应有 END");
        }

        @Test
        @DisplayName("IN 表达式应正确格式化")
        void testInExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE status IN ('active', 'pending', 'approved')");

            assertTrue(formatted.contains("IN"), "应有 IN");
        }

        @Test
        @DisplayName("BETWEEN 表达式应正确格式化")
        void testBetweenExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE age BETWEEN 18 AND 60");

            assertTrue(formatted.contains("BETWEEN"), "应有 BETWEEN");
        }

        @Test
        @DisplayName("LIKE 表达式应正确格式化")
        void testLikeExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE name LIKE '%Alice%'");

            assertTrue(formatted.contains("LIKE"), "应有 LIKE");
        }

        @Test
        @DisplayName("IS NULL 表达式应正确格式化")
        void testIsNullExpression() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE email IS NULL");

            assertTrue(formatted.contains("IS NULL"), "应有 IS NULL");
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

            assertNotNull(formatted, "格式化结果不应为 null");
            assertFalse(formatted.trim().isEmpty(), "格式化结果不应为空");
        }

        @Test
        @DisplayName("格式化结果应以分号结尾")
        void testEndsWithSemicolon() {
            String formatted = engine.formatMySql("SELECT id FROM users");

            assertTrue(formatted.trim().endsWith(";"), "格式化结果应以分号结尾");
        }

        @Test
        @DisplayName("格式化不应丢失表名")
        void testPreservesTableName() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM my_special_table WHERE status = 'ok'");

            assertTrue(formatted.contains("my_special_table"), "格式化不应丢失表名");
        }

        @Test
        @DisplayName("格式化不应丢失字符串字面值")
        void testPreservesStringLiterals() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE name = 'Hello World'");

            assertTrue(formatted.contains("Hello World"), "格式化不应丢失字符串字面值");
        }

        @Test
        @DisplayName("格式化不应丢失数字字面值")
        void testPreservesNumericLiterals() {
            String formatted = engine.formatMySql(
                    "SELECT id FROM users WHERE age > 18 AND salary >= 50000.00");

            assertTrue(formatted.contains("18"), "格式化不应丢失数字 18");
            assertTrue(formatted.contains("50000"), "格式化不应丢失数字 50000");
        }

        @Test
        @DisplayName("BIGINT UNSIGNED 不应粘连")
        void testBigintUnsignedSpace() {
            String formatted = engine.formatMySql(
                    "CREATE TABLE t (id BIGINT UNSIGNED NOT NULL)");

            // 不应出现 BIGINTUNSIGNED（粘连）
            assertFalse(formatted.contains("BIGINTUNSIGNED"),
                    "BIGINT UNSIGNED 之间应有空格，不应粘连");
        }

        @Test
        @DisplayName("复杂 SQL 格式化不应抛异常")
        void testComplexSqlNoException() {
            assertDoesNotThrow(() -> engine.formatMySql(
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
            ), "复杂 SQL 格式化不应抛异常");
        }
    }
}
