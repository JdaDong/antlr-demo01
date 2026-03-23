package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

import com.example.antlr.sparksql.SparkSqlMetadataVisitor;

/**
 * SparkSqlMetadataVisitor 单元测试
 * 测试 Visitor 模式下的 SparkSQL 元数据提取能力
 */
@DisplayName("SparkSqlMetadataVisitor - Visitor 模式元数据提取")
class SparkSqlMetadataVisitorTest {

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
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT id, name, age FROM users");

            assertThat(visitor.getStatementType()).as("语句类型应为 SELECT").isEqualTo("SELECT");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
            assertThat(visitor.getColumns()).as("应提取到列名").isNotEmpty();
        }

        @Test
        @DisplayName("SELECT * - 通配符")
        void testSelectStar() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT * FROM products");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getTables()).as("应提取到表名 products").contains("products");
        }

        @Test
        @DisplayName("SELECT DISTINCT")
        void testSelectDistinct() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT DISTINCT city FROM users");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.isHasDistinct()).as("应检测到 DISTINCT").isTrue();
        }

        @Test
        @DisplayName("SELECT 带别名")
        void testSelectWithAlias() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT u.name AS user_name, u.age AS user_age FROM users u");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
            assertThat(visitor.getAliases()).as("应提取到别名").isNotEmpty();
        }

        @Test
        @DisplayName("SELECT 带 WHERE 条件")
        void testSelectWithWhere() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT id, name FROM users WHERE age > 18 AND status = 'active'");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getWhereColumns()).as("WHERE 条件中应有列名").isNotEmpty();
        }

        @Test
        @DisplayName("SELECT 带 ORDER BY")
        void testSelectWithOrderBy() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT id, name FROM users ORDER BY name ASC, age DESC");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getOrderByColumns()).as("应提取到 ORDER BY 列").isNotEmpty();
            assertThat(visitor.getOrderByColumns()).as("应有至少 2 个 ORDER BY 列").hasSizeGreaterThanOrEqualTo(2);
        }

        @Test
        @DisplayName("SELECT 带 GROUP BY")
        void testSelectWithGroupBy() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT city, COUNT(*) FROM users GROUP BY city");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getGroupByColumns()).as("应提取到 GROUP BY 列").isNotEmpty();
            assertThat(visitor.getFunctions()).as("应提取到 COUNT 函数")
                    .anyMatch(f -> f.contains("COUNT"));
        }

        @Test
        @DisplayName("多表 JOIN 查询")
        void testJoinQuery() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT u.name, o.order_id, o.amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > 100");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getTables()).as("应提取到表 users, orders, products")
                    .contains("users", "orders", "products");
            assertThat(visitor.getJoinConditions()).as("应提取到 JOIN 条件").isNotEmpty();
        }

        @Test
        @DisplayName("子查询检测")
        void testSubQuery() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT name FROM users WHERE id IN " +
                    "(SELECT user_id FROM orders WHERE amount > 100)");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.isHasSubQuery()).as("应检测到子查询").isTrue();
        }

        @Test
        @DisplayName("UNION 查询")
        void testUnionQuery() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT name FROM users UNION SELECT product_name FROM products");

            assertThat(visitor.isHasUnion()).as("应检测到 UNION").isTrue();
        }

        @Test
        @DisplayName("带函数调用的查询")
        void testFunctionCalls() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT COUNT(*), MAX(age), MIN(age), AVG(salary), SUM(amount) FROM users");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getFunctions()).as("应提取到函数调用").isNotEmpty();
            assertThat(visitor.getFunctions()).as("应至少有 4 个函数").hasSizeGreaterThanOrEqualTo(4);
        }
    }

    // ================================================================
    // SparkSQL 特有语法
    // ================================================================

    @Nested
    @DisplayName("SparkSQL 特有语法")
    class SparkSpecificTests {

        @Test
        @DisplayName("CTE (WITH 子句)")
        void testCte() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "WITH active_users AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active_users");

            assertThat(visitor.isHasCte()).as("应检测到 CTE").isTrue();
            assertThat(visitor.getCteNames()).as("应提取到 CTE 名称").contains("active_users");
        }

        @Test
        @DisplayName("INSERT OVERWRITE 检测")
        void testInsertOverwrite() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "INSERT OVERWRITE TABLE target_table SELECT * FROM source_table");

            assertThat(visitor.getStatementType()).isEqualTo("INSERT");
            assertThat(visitor.isHasOverwrite()).as("应检测到 OVERWRITE").isTrue();
            assertThat(visitor.getTables()).as("应提取到表名").contains("target_table");
        }

        @Test
        @DisplayName("分区提取")
        void testPartitionExtract() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "INSERT INTO TABLE events PARTITION (dt = '2024-01-01', country = 'CN') " +
                    "SELECT id, name FROM source");

            assertThat(visitor.getStatementType()).isEqualTo("INSERT");
            assertThat(visitor.getPartitions()).as("应提取到分区信息").isNotEmpty();
        }

        @Test
        @DisplayName("CREATE VIEW")
        void testCreateView() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "CREATE VIEW active_users AS SELECT id, name FROM users WHERE status = 'active'");

            assertThat(visitor.getStatementType()).isEqualTo("CREATE_VIEW");
            assertThat(visitor.getViews()).as("应提取到视图名").contains("active_users");
        }

        @Test
        @DisplayName("DROP VIEW")
        void testDropView() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "DROP VIEW IF EXISTS active_users");

            assertThat(visitor.getStatementType()).isEqualTo("DROP_VIEW");
            assertThat(visitor.getViews()).as("应提取到视图名").contains("active_users");
        }

        @Test
        @DisplayName("RENAME TABLE")
        void testRenameTable() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "ALTER TABLE old_table RENAME TO new_table");

            assertThat(visitor.getStatementType()).isEqualTo("RENAME_TABLE");
            assertThat(visitor.getTables()).as("应提取到新旧表名")
                    .contains("old_table", "new_table");
        }

        @Test
        @DisplayName("EXPLAIN 语句")
        void testExplain() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "EXPLAIN SELECT * FROM users");

            assertThat(visitor.getStatementType()).isEqualTo("EXPLAIN");
        }

        @Test
        @DisplayName("CACHE TABLE")
        void testCacheTable() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "CACHE TABLE users");

            assertThat(visitor.getStatementType()).isEqualTo("CACHE_TABLE");
            assertThat(visitor.getTables()).as("应提取到表名").contains("users");
        }

        @Test
        @DisplayName("UNCACHE TABLE")
        void testUncacheTable() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "UNCACHE TABLE users");

            assertThat(visitor.getStatementType()).isEqualTo("UNCACHE_TABLE");
            assertThat(visitor.getTables()).as("应提取到表名").contains("users");
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
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "INSERT INTO users (name, email, age) VALUES ('Alice', 'alice@test.com', 25)");

            assertThat(visitor.getStatementType()).as("语句类型应为 INSERT").isEqualTo("INSERT");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
        }

        @Test
        @DisplayName("INSERT INTO SELECT")
        void testInsertSelect() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "INSERT INTO archive_users SELECT * FROM users WHERE created_at < '2020-01-01'");

            assertThat(visitor.getStatementType()).isEqualTo("INSERT");
            assertThat(visitor.getTables()).as("应提取到目标表").contains("archive_users");
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
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "CREATE TABLE users (id INT, name STRING, age INT)");

            assertThat(visitor.getStatementType()).as("语句类型应为 CREATE_TABLE").isEqualTo("CREATE_TABLE");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
        }

        @Test
        @DisplayName("CREATE TABLE USING")
        void testCreateTableUsing() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "CREATE TABLE events (id INT, name STRING) USING parquet");

            assertThat(visitor.getStatementType()).isEqualTo("CREATE_TABLE");
            assertThat(visitor.getTables()).as("应提取到表名 events").contains("events");
        }

        @Test
        @DisplayName("DROP TABLE")
        void testDropTable() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "DROP TABLE IF EXISTS users");

            assertThat(visitor.getStatementType()).as("语句类型应为 DROP_TABLE").isEqualTo("DROP_TABLE");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
        }

        @Test
        @DisplayName("CREATE DATABASE")
        void testCreateDatabase() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "CREATE DATABASE IF NOT EXISTS mydb");

            assertThat(visitor.getStatementType()).as("语句类型应为 CREATE_DATABASE").isEqualTo("CREATE_DATABASE");
        }

        @Test
        @DisplayName("DROP DATABASE")
        void testDropDatabase() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "DROP DATABASE IF EXISTS mydb");

            assertThat(visitor.getStatementType()).as("语句类型应为 DROP_DATABASE").isEqualTo("DROP_DATABASE");
        }

        @Test
        @DisplayName("ADD COLUMNS")
        void testAddColumns() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "ALTER TABLE users ADD COLUMNS (email STRING, phone STRING)");

            assertThat(visitor.getStatementType()).isEqualTo("ADD_COLUMNS");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
        }

        @Test
        @DisplayName("DROP COLUMN")
        void testDropColumn() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "ALTER TABLE users DROP COLUMN email");

            assertThat(visitor.getStatementType()).isEqualTo("DROP_COLUMN");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
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
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SHOW TABLES");

            assertThat(visitor.getStatementType()).as("语句类型应为 SHOW").isEqualTo("SHOW");
        }

        @Test
        @DisplayName("USE 数据库")
        void testUseDatabase() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "USE mydb");

            assertThat(visitor.getStatementType()).as("语句类型应为 USE").isEqualTo("USE");
        }

        @Test
        @DisplayName("DESCRIBE 表")
        void testDescribeTable() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "DESCRIBE users");

            assertThat(visitor.getStatementType()).as("语句类型应为 DESCRIBE").isEqualTo("DESCRIBE");
            assertThat(visitor.getTables()).as("应提取到表名 users").contains("users");
        }
    }

    // ================================================================
    // 综合场景
    // ================================================================

    @Nested
    @DisplayName("综合场景")
    class ComplexTests {

        @Test
        @DisplayName("复杂查询 - JOIN + 函数 + GROUP BY + ORDER BY")
        void testComplexQuery() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT u.name, COUNT(o.id) AS order_count, MAX(o.amount) AS max_amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "WHERE o.amount > 100 " +
                    "GROUP BY u.name " +
                    "ORDER BY order_count DESC");

            assertThat(visitor.getStatementType()).isEqualTo("SELECT");
            assertThat(visitor.getTables()).as("应提取到表 users 和 orders").contains("users", "orders");
            assertThat(visitor.getJoinConditions()).as("应有 JOIN 条件").isNotEmpty();
            assertThat(visitor.getGroupByColumns()).as("应有 GROUP BY 列").isNotEmpty();
            assertThat(visitor.getOrderByColumns()).as("应有 ORDER BY 列").isNotEmpty();
            assertThat(visitor.getFunctions()).as("应有函数调用").isNotEmpty();
        }

        @Test
        @DisplayName("getSummary 不应抛异常且不为空")
        void testGetSummary() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT id, name FROM users WHERE age > 18");

            String summary = visitor.getSummary();
            assertThat(summary).as("摘要不应为 null").isNotNull();
            assertThat(summary).as("摘要不应为空").isNotEmpty();
            assertThat(summary).as("摘要应包含语句类型").contains("SELECT");
        }

        @Test
        @DisplayName("toString 与 getSummary 一致")
        void testToString() {
            SparkSqlMetadataVisitor visitor = engine.extractSparkSqlMetadata(
                    "SELECT * FROM users");

            assertThat(visitor.toString()).as("toString 应与 getSummary 返回相同内容")
                    .isEqualTo(visitor.getSummary());
        }
    }
}
