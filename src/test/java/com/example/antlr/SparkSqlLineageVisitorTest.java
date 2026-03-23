package com.example.antlr;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

import com.example.antlr.lineage.LineageGraph;
import com.example.antlr.lineage.LineageGraph.TransformType;
import com.example.antlr.lineage.LineageGraph.TableRole;

/**
 * SparkSqlLineageVisitor 单元测试
 * 测试 SparkSQL 列级血缘分析能力
 */
@DisplayName("SparkSqlLineageVisitor - 列级血缘分析")
class SparkSqlLineageVisitorTest {

    private SqlParserEngine engine;

    @BeforeEach
    void setUp() {
        engine = new SqlParserEngine();
    }

    // ================================================================
    // SELECT 血缘
    // ================================================================

    @Nested
    @DisplayName("SELECT 血缘")
    class SelectLineageTests {

        @Test
        @DisplayName("简单 SELECT - 直接映射")
        void testSimpleSelectLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT id, name FROM users");

            assertThat(graph.getStatementType()).isEqualTo("SELECT");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users").contains("users");
        }

        @Test
        @DisplayName("SELECT * - 通配符展开")
        void testSelectStarLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT * FROM users");

            assertThat(graph.getStatementType()).isEqualTo("SELECT");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 WILDCARD 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.WILDCARD);
        }

        @Test
        @DisplayName("SELECT 带别名")
        void testSelectWithAliasLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT u.name AS user_name FROM users u");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users").contains("users");
        }

        @Test
        @DisplayName("多表 JOIN - 多来源表")
        void testJoinLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT u.name, o.amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users 和 orders")
                    .contains("users", "orders");
        }

        @Test
        @DisplayName("聚合函数 - AGGREGATION 类型")
        void testAggregationLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT COUNT(id) AS cnt, SUM(amount) AS total FROM orders");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 AGGREGATION 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.AGGREGATION);
        }

        @Test
        @DisplayName("函数调用 - FUNCTION 类型")
        void testFunctionLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT UPPER(name) AS upper_name FROM users");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 FUNCTION 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.FUNCTION);
        }

        @Test
        @DisplayName("算术运算 - COMPUTATION 类型")
        void testComputationLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT price * quantity AS total FROM orders");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 COMPUTATION 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.COMPUTATION);
        }

        @Test
        @DisplayName("常量 - CONSTANT 类型")
        void testConstantLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT 1 AS one, 'hello' AS greeting FROM users");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 CONSTANT 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.CONSTANT);
        }
    }

    // ================================================================
    // INSERT 血缘
    // ================================================================

    @Nested
    @DisplayName("INSERT 血缘")
    class InsertLineageTests {

        @Test
        @DisplayName("INSERT INTO SELECT - 列级映射")
        void testInsertSelectLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "INSERT INTO target_table (name, age) SELECT name, age FROM source_table");

            assertThat(graph.getStatementType()).isEqualTo("INSERT");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 target_table").contains("target_table");
            assertThat(graph.getSourceTableNames()).as("来源表应包含 source_table").contains("source_table");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
        }

        @Test
        @DisplayName("INSERT OVERWRITE - 语句类型检测")
        void testInsertOverwriteLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "INSERT OVERWRITE TABLE target SELECT id, name FROM source");

            assertThat(graph.getStatementType()).isEqualTo("INSERT_OVERWRITE");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 target").contains("target");
        }

        @Test
        @DisplayName("INSERT INTO VALUES - VALUE_ASSIGN 类型")
        void testInsertValuesLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "INSERT INTO users (name, age) VALUES ('Alice', 25)");

            assertThat(graph.getStatementType()).isEqualTo("INSERT");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 users").contains("users");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
            assertThat(graph.getEdges()).as("应有 VALUE_ASSIGN 类型边")
                    .anyMatch(e -> e.getTransform() == TransformType.VALUE_ASSIGN);
        }
    }

    // ================================================================
    // CTE 血缘
    // ================================================================

    @Nested
    @DisplayName("CTE 血缘")
    class CteLineageTests {

        @Test
        @DisplayName("CTE + SELECT - 应解析血缘")
        void testCteSelectLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "WITH active AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active");

            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
        }

        @Test
        @DisplayName("CTE + INSERT - 应解析血缘")
        void testCteInsertLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "WITH src AS (SELECT id, name FROM users) " +
                    "INSERT INTO target (id, name) SELECT id, name FROM src");

            assertThat(graph.getTargetTableNames()).as("目标表应包含 target").contains("target");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
        }
    }

    // ================================================================
    // CTAS 和 CREATE VIEW 血缘
    // ================================================================

    @Nested
    @DisplayName("CTAS / CREATE VIEW 血缘")
    class CtasAndViewLineageTests {

        @Test
        @DisplayName("CREATE TABLE AS SELECT (CTAS)")
        void testCtasLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "CREATE TABLE new_table USING parquet AS SELECT id, name FROM users");

            assertThat(graph.getStatementType()).isEqualTo("CREATE_TABLE_AS_SELECT");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 new_table").contains("new_table");
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users").contains("users");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
        }

        @Test
        @DisplayName("CREATE VIEW AS SELECT")
        void testCreateViewLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "CREATE VIEW active_users AS SELECT id, name FROM users WHERE status = 'active'");

            assertThat(graph.getStatementType()).isEqualTo("CREATE_VIEW");
            assertThat(graph.getTargetTableNames()).as("目标应包含 active_users").contains("active_users");
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users").contains("users");
            assertThat(graph.getEdges()).as("应有血缘边").isNotEmpty();
        }
    }

    // ================================================================
    // DDL 血缘（无列级血缘）
    // ================================================================

    @Nested
    @DisplayName("DDL 血缘")
    class DdlLineageTests {

        @Test
        @DisplayName("CREATE TABLE 无列级血缘")
        void testCreateTableNoColumnLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "CREATE TABLE users (id INT, name STRING)");

            assertThat(graph.getStatementType()).isEqualTo("CREATE_TABLE");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 users").contains("users");
            assertThat(graph.getEdges()).as("DDL 不应有列级血缘边").isEmpty();
        }

        @Test
        @DisplayName("DROP TABLE")
        void testDropTableLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "DROP TABLE IF EXISTS users");

            assertThat(graph.getStatementType()).isEqualTo("DROP_TABLE");
            assertThat(graph.getTargetTableNames()).as("目标表应包含 users").contains("users");
        }

        @Test
        @DisplayName("DROP VIEW")
        void testDropViewLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "DROP VIEW IF EXISTS active_users");

            assertThat(graph.getStatementType()).isEqualTo("DROP_VIEW");
        }

        @Test
        @DisplayName("RENAME TABLE")
        void testRenameTableLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "ALTER TABLE old_table RENAME TO new_table");

            assertThat(graph.getStatementType()).isEqualTo("RENAME_TABLE");
        }
    }

    // ================================================================
    // 综合场景
    // ================================================================

    @Nested
    @DisplayName("综合场景")
    class ComplexTests {

        @Test
        @DisplayName("复杂查询 - JOIN + 聚合 + 别名")
        void testComplexQueryLineage() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT u.name, COUNT(o.id) AS order_count, SUM(o.amount) AS total_amount " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "GROUP BY u.name");

            assertThat(graph.getEdges()).as("应有多条血缘边").hasSizeGreaterThanOrEqualTo(2);
            assertThat(graph.getSourceTableNames()).as("来源表应包含 users 和 orders")
                    .contains("users", "orders");
        }

        @Test
        @DisplayName("toAsciiReport 不应抛异常")
        void testToAsciiReport() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT id, name FROM users");

            assertThatCode(() -> graph.toAsciiReport())
                    .as("toAsciiReport 不应抛异常").doesNotThrowAnyException();
            assertThat(graph.toAsciiReport()).as("报告不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("toDot 不应抛异常")
        void testToDot() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "INSERT INTO target (id, name) SELECT id, name FROM source");

            assertThatCode(() -> graph.toDot())
                    .as("toDot 不应抛异常").doesNotThrowAnyException();
            assertThat(graph.toDot()).as("DOT 不应为空").isNotEmpty();
            assertThat(graph.toDot()).as("DOT 应包含 digraph").contains("digraph");
        }

        @Test
        @DisplayName("toGsonJson 不应抛异常")
        void testToGsonJson() {
            LineageGraph graph = engine.analyzeSparkSqlLineage(
                    "SELECT name FROM users");

            assertThatCode(() -> graph.toGsonJson())
                    .as("toGsonJson 不应抛异常").doesNotThrowAnyException();
            assertThat(graph.toGsonJson()).as("JSON 不应为空").isNotEmpty();
        }
    }
}
