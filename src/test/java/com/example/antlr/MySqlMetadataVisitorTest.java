package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import com.example.antlr.mysql.MySqlMetadataVisitor;

/**
 * MySqlMetadataVisitor 单元测试
 * 测试 Visitor 模式下的 SQL 元数据提取能力
 */
@DisplayName("MySqlMetadataVisitor - Visitor 模式元数据提取")
class MySqlMetadataVisitorTest {

    private SqlParserEngine engine;

    @BeforeEach
    void setUp() {
        engine = new SqlParserEngine();
    }

    // ================================================================
    // SELECT 语句元数据提取
    // ================================================================

    @Nested
    @DisplayName("SELECT 语句")
    class SelectTests {

        @Test
        @DisplayName("简单 SELECT - 提取表名和列名")
        void testSimpleSelect() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT id, name, age FROM users");

            assertEquals("SELECT", visitor.getStatementType(), "语句类型应为 SELECT");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
            assertTrue(visitor.getColumns().contains("id"), "应提取到列名 id");
            assertTrue(visitor.getColumns().contains("name"), "应提取到列名 name");
            assertTrue(visitor.getColumns().contains("age"), "应提取到列名 age");
        }

        @Test
        @DisplayName("SELECT * - 通配符")
        void testSelectStar() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT * FROM products");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("products"), "应提取到表名 products");
        }

        @Test
        @DisplayName("SELECT DISTINCT")
        void testSelectDistinct() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT DISTINCT city FROM users");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.isHasDistinct(), "应检测到 DISTINCT");
        }

        @Test
        @DisplayName("SELECT 带别名")
        void testSelectWithAlias() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT u.name AS user_name, u.age AS user_age FROM users u");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
            // 检查别名映射
            assertFalse(visitor.getAliases().isEmpty(), "应提取到别名");
        }

        @Test
        @DisplayName("SELECT 带 WHERE 条件")
        void testSelectWithWhere() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT id, name FROM users WHERE age > 18 AND status = 'active'");

            assertEquals("SELECT", visitor.getStatementType());
            assertFalse(visitor.getWhereColumns().isEmpty(), "WHERE 条件中应有列名");
        }

        @Test
        @DisplayName("SELECT 带 ORDER BY")
        void testSelectWithOrderBy() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT id, name FROM users ORDER BY name ASC, age DESC");

            assertEquals("SELECT", visitor.getStatementType());
            assertFalse(visitor.getOrderByColumns().isEmpty(), "应提取到 ORDER BY 列");
            assertTrue(visitor.getOrderByColumns().size() >= 2, "应有至少 2 个 ORDER BY 列");
        }

        @Test
        @DisplayName("SELECT 带 GROUP BY")
        void testSelectWithGroupBy() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT city, COUNT(*) FROM users GROUP BY city");

            assertEquals("SELECT", visitor.getStatementType());
            assertFalse(visitor.getGroupByColumns().isEmpty(), "应提取到 GROUP BY 列");
            assertTrue(visitor.getFunctions().stream()
                    .anyMatch(f -> f.contains("COUNT")), "应提取到 COUNT 函数");
        }

        @Test
        @DisplayName("多表 JOIN 查询")
        void testJoinQuery() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT u.name, o.order_id, o.amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > 100");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("users"), "应提取到表 users");
            assertTrue(visitor.getTables().contains("orders"), "应提取到表 orders");
            assertTrue(visitor.getTables().contains("products"), "应提取到表 products");
            assertTrue(visitor.getTables().size() >= 3, "应有 3 个表");
            assertFalse(visitor.getJoinConditions().isEmpty(), "应提取到 JOIN 条件");
        }

        @Test
        @DisplayName("子查询检测")
        void testSubQuery() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT name FROM users WHERE id IN " +
                    "(SELECT user_id FROM orders WHERE amount > 100)");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.isHasSubQuery(), "应检测到子查询");
            assertTrue(visitor.getTables().contains("users"), "应提取到外层表 users");
            assertTrue(visitor.getTables().contains("orders"), "应提取到子查询表 orders");
        }

        @Test
        @DisplayName("UNION 查询")
        void testUnionQuery() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT name FROM users UNION SELECT product_name FROM products");

            assertTrue(visitor.isHasUnion(), "应检测到 UNION");
        }

        @Test
        @DisplayName("带函数调用的查询")
        void testFunctionCalls() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT COUNT(*), MAX(age), MIN(age), AVG(salary), SUM(amount) FROM users");

            assertEquals("SELECT", visitor.getStatementType());
            assertFalse(visitor.getFunctions().isEmpty(), "应提取到函数调用");
            assertTrue(visitor.getFunctions().size() >= 4, "应至少有 4 个函数");
        }
    }

    // ================================================================
    // INSERT 语句
    // ================================================================

    @Nested
    @DisplayName("INSERT 语句")
    class InsertTests {

        @Test
        @DisplayName("INSERT INTO VALUES")
        void testInsertValues() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "INSERT INTO users (name, email, age) VALUES ('Alice', 'alice@test.com', 25)");

            assertEquals("INSERT", visitor.getStatementType(), "语句类型应为 INSERT");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("INSERT INTO 多行 VALUES")
        void testInsertMultipleValues() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "INSERT INTO users (name, age) VALUES ('Alice', 25), ('Bob', 30), ('Charlie', 35)");

            assertEquals("INSERT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("users"));
        }

        @Test
        @DisplayName("INSERT INTO SELECT")
        void testInsertSelect() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "INSERT INTO archive_users SELECT * FROM users WHERE created_at < '2020-01-01'");

            assertEquals("INSERT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("archive_users"), "应提取到目标表");
        }
    }

    // ================================================================
    // UPDATE 语句
    // ================================================================

    @Nested
    @DisplayName("UPDATE 语句")
    class UpdateTests {

        @Test
        @DisplayName("简单 UPDATE")
        void testSimpleUpdate() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "UPDATE users SET name = 'Bob', age = 30 WHERE id = 1");

            assertEquals("UPDATE", visitor.getStatementType(), "语句类型应为 UPDATE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("多表 UPDATE")
        void testMultiTableUpdate() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "UPDATE users u INNER JOIN orders o ON u.id = o.user_id SET u.total = o.amount WHERE o.status = 'paid'");

            assertEquals("UPDATE", visitor.getStatementType());
            // UPDATE JOIN 中主表通过 updateStatement 提取
            assertTrue(visitor.getTables().contains("users"), "应提取到主表 users");
        }
    }

    // ================================================================
    // DELETE 语句
    // ================================================================

    @Nested
    @DisplayName("DELETE 语句")
    class DeleteTests {

        @Test
        @DisplayName("简单 DELETE")
        void testSimpleDelete() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "DELETE FROM users WHERE id = 1");

            assertEquals("DELETE", visitor.getStatementType(), "语句类型应为 DELETE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }
    }

    // ================================================================
    // DDL 语句
    // ================================================================

    @Nested
    @DisplayName("DDL 语句")
    class DdlTests {

        @Test
        @DisplayName("CREATE TABLE")
        void testCreateTable() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "CREATE TABLE users (" +
                    "id INT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(100), " +
                    "PRIMARY KEY (id))");

            assertEquals("CREATE_TABLE", visitor.getStatementType(), "语句类型应为 CREATE_TABLE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("DROP TABLE")
        void testDropTable() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "DROP TABLE IF EXISTS users");

            assertEquals("DROP_TABLE", visitor.getStatementType(), "语句类型应为 DROP_TABLE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("ALTER TABLE")
        void testAlterTable() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(255)");

            assertEquals("ALTER_TABLE", visitor.getStatementType(), "语句类型应为 ALTER_TABLE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("TRUNCATE TABLE")
        void testTruncateTable() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "TRUNCATE TABLE users");

            assertEquals("TRUNCATE_TABLE", visitor.getStatementType(), "语句类型应为 TRUNCATE_TABLE");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("CREATE INDEX")
        void testCreateIndex() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "CREATE INDEX idx_name ON users (name)");

            assertEquals("CREATE_INDEX", visitor.getStatementType(), "语句类型应为 CREATE_INDEX");
            assertTrue(visitor.getTables().contains("users"), "应提取到表名 users");
        }

        @Test
        @DisplayName("CREATE DATABASE")
        void testCreateDatabase() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "CREATE DATABASE IF NOT EXISTS mydb");

            assertEquals("CREATE_DATABASE", visitor.getStatementType(), "语句类型应为 CREATE_DATABASE");
        }

        @Test
        @DisplayName("DROP DATABASE")
        void testDropDatabase() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "DROP DATABASE IF EXISTS mydb");

            assertEquals("DROP_DATABASE", visitor.getStatementType(), "语句类型应为 DROP_DATABASE");
        }
    }

    // ================================================================
    // 其他语句
    // ================================================================

    @Nested
    @DisplayName("其他语句类型")
    class OtherStatementsTests {

        @Test
        @DisplayName("SHOW TABLES")
        void testShowTables() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SHOW TABLES");

            assertEquals("SHOW", visitor.getStatementType(), "语句类型应为 SHOW");
        }

        @Test
        @DisplayName("USE 数据库")
        void testUseDatabase() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "USE mydb");

            assertEquals("USE", visitor.getStatementType(), "语句类型应为 USE");
        }

        @Test
        @DisplayName("DESCRIBE 表")
        void testDescribeTable() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "DESCRIBE users");

            assertEquals("DESCRIBE", visitor.getStatementType(), "语句类型应为 DESCRIBE");
        }
    }

    // ================================================================
    // 综合场景
    // ================================================================

    @Nested
    @DisplayName("综合场景")
    class ComplexTests {

        @Test
        @DisplayName("复杂查询 - 同时具有 JOIN + 函数 + GROUP BY + ORDER BY")
        void testComplexQuery() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT u.name, COUNT(o.id) AS order_count, MAX(o.amount) AS max_amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY u.name " +
                    "ORDER BY order_count DESC");

            assertEquals("SELECT", visitor.getStatementType());
            assertTrue(visitor.getTables().contains("users"), "应提取到表 users");
            assertTrue(visitor.getTables().contains("orders"), "应提取到表 orders");
            assertFalse(visitor.getJoinConditions().isEmpty(), "应有 JOIN 条件");
            assertFalse(visitor.getGroupByColumns().isEmpty(), "应有 GROUP BY 列");
            assertFalse(visitor.getOrderByColumns().isEmpty(), "应有 ORDER BY 列");
            assertFalse(visitor.getFunctions().isEmpty(), "应有函数调用");
        }

        @Test
        @DisplayName("IN 子查询应被检测到")
        void testInSubQuery() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT name FROM users WHERE id IN " +
                    "(SELECT user_id FROM orders WHERE amount > 100)");

            assertTrue(visitor.isHasSubQuery(), "IN 子查询应被检测到");
        }

        @Test
        @DisplayName("getSummary 不应抛异常且不为空")
        void testGetSummary() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT id, name FROM users WHERE age > 18");

            String summary = visitor.getSummary();
            assertNotNull(summary, "摘要不应为 null");
            assertFalse(summary.isEmpty(), "摘要不应为空");
            assertTrue(summary.contains("SELECT"), "摘要应包含语句类型");
        }

        @Test
        @DisplayName("toString 与 getSummary 一致")
        void testToString() {
            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(
                    "SELECT * FROM users");

            assertEquals(visitor.getSummary(), visitor.toString(),
                    "toString 应与 getSummary 返回相同内容");
        }
    }
}
