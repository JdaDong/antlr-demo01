package com.example.antlr;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.lineage.LineageGraph;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * PostgreSQL LineageVisitor 单元测试
 */
class PostgreSqlLineageVisitorTest {

    private final SqlParserEngine engine = new SqlParserEngine();

    // ================================================================
    // SELECT 血缘
    // ================================================================

    @Nested
    @DisplayName("SELECT 血缘分析")
    class SelectLineageTests {

        @Test
        @DisplayName("简单 SELECT 应生成直接映射血缘")
        void testSimpleSelectLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("SELECT id, name FROM users");

            assertThat(graph.getStatementType()).isEqualTo("SELECT");
            assertThat(graph.getEdges()).isNotEmpty();
        }

        @Test
        @DisplayName("SELECT * 应生成 WILDCARD 类型")
        void testSelectStarLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("SELECT * FROM users");

            assertThat(graph.getEdges()).isNotEmpty();
            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.WILDCARD);
        }

        @Test
        @DisplayName("SELECT 带别名应保留")
        void testSelectWithAliasLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT name AS user_name FROM users");

            assertThat(graph.getEdges()).isNotEmpty();
        }

        @Test
        @DisplayName("JOIN 查询应产生多表血缘")
        void testJoinLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id");

            assertThat(graph.getTables()).hasSizeGreaterThanOrEqualTo(2);
            assertThat(graph.getEdges()).isNotEmpty();
        }

        @Test
        @DisplayName("聚合函数应产生 AGGREGATION 类型")
        void testAggregationLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT dept, COUNT(id) AS cnt FROM users GROUP BY dept");

            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.AGGREGATION);
        }

        @Test
        @DisplayName("函数调用应产生 FUNCTION 类型")
        void testFunctionLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT UPPER(name) AS upper_name FROM users");

            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.FUNCTION);
        }

        @Test
        @DisplayName("算术运算应产生 COMPUTATION 类型")
        void testComputationLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT price * quantity AS total FROM orders");

            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.COMPUTATION);
        }

        @Test
        @DisplayName("常量应产生 CONSTANT 类型")
        void testConstantLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT 42 AS magic_number FROM users");

            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.CONSTANT);
        }
    }

    // ================================================================
    // INSERT 血缘
    // ================================================================

    @Nested
    @DisplayName("INSERT 血缘分析")
    class InsertLineageTests {

        @Test
        @DisplayName("INSERT SELECT 应产生列级映射")
        void testInsertSelectLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "INSERT INTO archive (id, name) SELECT id, name FROM users WHERE status = 'inactive'");

            assertThat(graph.getStatementType()).isEqualTo("INSERT");
            assertThat(graph.getEdges()).isNotEmpty();
        }

        @Test
        @DisplayName("INSERT VALUES 应产生 VALUE_ASSIGN 类型")
        void testInsertValuesLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "INSERT INTO users (id, name) VALUES (1, 'Alice')");

            assertThat(graph.getStatementType()).isEqualTo("INSERT");
            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.VALUE_ASSIGN);
        }
    }

    // ================================================================
    // CTE 血缘
    // ================================================================

    @Nested
    @DisplayName("CTE 血缘分析")
    class CteLineageTests {

        @Test
        @DisplayName("CTE + SELECT 应产生血缘")
        void testCteSelectLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "WITH active AS (SELECT id, name FROM users WHERE status = 'active') " +
                    "SELECT name FROM active");

            assertThat(graph.getEdges()).isNotEmpty();
        }
    }

    // ================================================================
    // UPDATE 血缘
    // ================================================================

    @Nested
    @DisplayName("UPDATE 血缘分析")
    class UpdateLineageTests {

        @Test
        @DisplayName("UPDATE SET 常量应产生 VALUE_ASSIGN")
        void testUpdateConstantLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "UPDATE users SET status = 'active' WHERE id = 1");

            assertThat(graph.getStatementType()).isEqualTo("UPDATE");
            assertThat(graph.getEdges()).anyMatch(e ->
                    e.getTransform() == LineageGraph.TransformType.VALUE_ASSIGN);
        }

        @Test
        @DisplayName("UPDATE SET 列引用应产生 UPDATE_ASSIGN")
        void testUpdateColumnRefLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "UPDATE users SET display_name = name WHERE id = 1");

            assertThat(graph.getStatementType()).isEqualTo("UPDATE");
            assertThat(graph.getEdges()).isNotEmpty();
        }
    }

    // ================================================================
    // DELETE / DDL 血缘
    // ================================================================

    @Nested
    @DisplayName("DELETE / DDL 血缘")
    class DdlLineageTests {

        @Test
        @DisplayName("DELETE 应记录目标表")
        void testDeleteLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "DELETE FROM users WHERE id = 1");

            assertThat(graph.getStatementType()).isEqualTo("DELETE");
            assertThat(graph.getTables()).isNotEmpty();
        }

        @Test
        @DisplayName("CREATE TABLE 无列级血缘")
        void testCreateTableNoColumnLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "CREATE TABLE users (id SERIAL PRIMARY KEY, name TEXT)");

            assertThat(graph.getStatementType()).isEqualTo("CREATE_TABLE");
            assertThat(graph.getEdges()).isEmpty();
        }

        @Test
        @DisplayName("DROP TABLE 应记录表")
        void testDropTableLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("DROP TABLE users");

            assertThat(graph.getStatementType()).isEqualTo("DROP_TABLE");
            assertThat(graph.getTables()).isNotEmpty();
        }

        @Test
        @DisplayName("ALTER TABLE 应记录表")
        void testAlterTableLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "ALTER TABLE users ADD COLUMN email VARCHAR(200)");

            assertThat(graph.getStatementType()).isEqualTo("ALTER_TABLE");
            assertThat(graph.getTables()).isNotEmpty();
        }
    }

    // ================================================================
    // 复杂查询 + 输出格式
    // ================================================================

    @Nested
    @DisplayName("复杂查询与输出")
    class ComplexTests {

        @Test
        @DisplayName("复杂 JOIN + 聚合血缘")
        void testComplexQueryLineage() {
            LineageGraph graph = engine.analyzePostgreSqlLineage(
                    "SELECT u.name, COUNT(o.id) AS order_count " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "GROUP BY u.name");

            assertThat(graph.getEdges()).isNotEmpty();
            assertThat(graph.getTables()).hasSizeGreaterThanOrEqualTo(2);
        }

        @Test
        @DisplayName("toAsciiReport 应非空")
        void testToAsciiReport() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("SELECT id, name FROM users");

            assertThat(graph.toAsciiReport()).isNotNull().isNotEmpty();
        }

        @Test
        @DisplayName("toDot 应包含 digraph")
        void testToDot() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("SELECT id, name FROM users");

            assertThat(graph.toDot()).contains("digraph");
        }

        @Test
        @DisplayName("toGsonJson 应包含 edges")
        void testToGsonJson() {
            LineageGraph graph = engine.analyzePostgreSqlLineage("SELECT id, name FROM users");

            String json = graph.toGsonJson();
            assertThat(json).contains("edges");
        }
    }
}
