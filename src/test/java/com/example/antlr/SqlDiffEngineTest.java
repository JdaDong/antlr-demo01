package com.example.antlr;

import com.example.antlr.diff.SqlDiffEngine;
import com.example.antlr.diff.SqlDiffEngine.*;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

/**
 * SqlDiffEngine 单元测试
 *
 * 覆盖三种比较模式（STRUCTURE/FULL/SEMANTIC）× 三种方言（MySQL/PostgreSQL/SparkSQL）
 */
class SqlDiffEngineTest {

    private final SqlDiffEngine diffEngine = new SqlDiffEngine();

    // ================================================================
    // 完整比较模式 (FULL)
    // ================================================================

    @Nested
    @DisplayName("FULL 比较模式")
    class FullModeTests {

        @Test
        @DisplayName("相同 SQL 应无差异")
        void testIdenticalSql() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id, name FROM users",
                    "SELECT id, name FROM users",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isTrue();
            assertThat(result.getDiffCount()).isZero();
        }

        @Test
        @DisplayName("不同 WHERE 条件应检测到差异")
        void testDifferentWhere() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users WHERE age > 18",
                    "SELECT id FROM users WHERE age > 25",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isFalse();
            assertThat(result.getDiffCount()).isGreaterThan(0);
        }

        @Test
        @DisplayName("不同列应检测到差异")
        void testDifferentColumns() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id, name FROM users",
                    "SELECT id, email FROM users",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isFalse();
            assertThat(result.getModifiedCount()).isGreaterThan(0);
        }

        @Test
        @DisplayName("新增 ORDER BY 应检测为新增")
        void testAddedClause() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users ORDER BY id",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isFalse();
        }

        @Test
        @DisplayName("不同表名应检测到差异")
        void testDifferentTable() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM admins",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isFalse();
        }
    }

    // ================================================================
    // 结构比较模式 (STRUCTURE)
    // ================================================================

    @Nested
    @DisplayName("STRUCTURE 比较模式")
    class StructureModeTests {

        @Test
        @DisplayName("结构相同但值不同应无差异")
        void testSameStructureDifferentValues() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users WHERE age > 18",
                    "SELECT name FROM orders WHERE price > 100",
                    SqlDialect.MYSQL, DiffMode.STRUCTURE);

            // 结构完全相同（SELECT + FROM + WHERE），只是值不同
            assertThat(result.isIdentical()).isTrue();
        }

        @Test
        @DisplayName("结构不同应检测到差异")
        void testDifferentStructure() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users ORDER BY id",
                    SqlDialect.MYSQL, DiffMode.STRUCTURE);

            assertThat(result.isIdentical()).isFalse();
        }
    }

    // ================================================================
    // 语义比较模式 (SEMANTIC)
    // ================================================================

    @Nested
    @DisplayName("SEMANTIC 比较模式")
    class SemanticModeTests {

        @Test
        @DisplayName("语义相同的 SQL 应无差异")
        void testSameSemantics() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id, name FROM users",
                    "SELECT id, name FROM users",
                    SqlDialect.MYSQL, DiffMode.SEMANTIC);

            assertThat(result.isIdentical()).isTrue();
        }

        @Test
        @DisplayName("不同表的 SQL 应有语义差异")
        void testDifferentTableSemantics() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM orders",
                    SqlDialect.MYSQL, DiffMode.SEMANTIC);

            assertThat(result.isIdentical()).isFalse();
        }

        @Test
        @DisplayName("MySQL 语义比较应工作")
        void testMySqlSemantic() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users WHERE age > 18",
                    "SELECT id FROM users WHERE age > 25",
                    SqlDialect.MYSQL, DiffMode.SEMANTIC);

            // WHERE 条件不同，语义上可能有差异
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("PostgreSQL 语义比较应工作")
        void testPostgreSqlSemantic() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id, name FROM users",
                    SqlDialect.POSTGRESQL, DiffMode.SEMANTIC);

            assertThat(result).isNotNull();
            assertThat(result.isIdentical()).isFalse();
        }

        @Test
        @DisplayName("SparkSQL 语义比较应工作")
        void testSparkSqlSemantic() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users ORDER BY id",
                    SqlDialect.SPARKSQL, DiffMode.SEMANTIC);

            assertThat(result).isNotNull();
        }
    }

    // ================================================================
    // 默认模式（不传 DiffMode）
    // ================================================================

    @Nested
    @DisplayName("默认比较模式")
    class DefaultModeTests {

        @Test
        @DisplayName("默认模式应使用 FULL")
        void testDefaultMode() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users",
                    SqlDialect.MYSQL);

            assertThat(result.isIdentical()).isTrue();
        }

        @Test
        @DisplayName("默认模式有差异时应检测到")
        void testDefaultModeWithDiff() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT name FROM orders",
                    SqlDialect.MYSQL);

            assertThat(result.isIdentical()).isFalse();
        }
    }

    // ================================================================
    // 多方言支持
    // ================================================================

    @Nested
    @DisplayName("多方言支持")
    class MultiDialectTests {

        @Test
        @DisplayName("MySQL Diff 应工作")
        void testMySqlDiff() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users WHERE id > 0",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result).isNotNull();
            assertThat(result.isIdentical()).isFalse();
        }

        @Test
        @DisplayName("PostgreSQL Diff 应工作")
        void testPostgreSqlDiff() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users WHERE id > 0",
                    SqlDialect.POSTGRESQL, DiffMode.FULL);

            assertThat(result).isNotNull();
            assertThat(result.isIdentical()).isFalse();
        }

        @Test
        @DisplayName("SparkSQL Diff 应工作")
        void testSparkSqlDiff() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users WHERE id > 0",
                    SqlDialect.SPARKSQL, DiffMode.FULL);

            assertThat(result).isNotNull();
            assertThat(result.isIdentical()).isFalse();
        }
    }

    // ================================================================
    // DiffResult API
    // ================================================================

    @Nested
    @DisplayName("DiffResult API")
    class DiffResultTests {

        @Test
        @DisplayName("getDiffCount 应正确统计")
        void testGetDiffCount() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT name, age FROM orders WHERE id > 0",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.getDiffCount()).isGreaterThan(0);
            assertThat(result.getDiffCount())
                    .isEqualTo(result.getAddedCount() + result.getRemovedCount() + result.getModifiedCount());
        }

        @Test
        @DisplayName("getDiffs 应返回不可变列表")
        void testGetDiffsImmutable() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT name FROM orders",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThatThrownBy(() -> result.getDiffs().add(
                    new DiffEntry(DiffType.ADDED, "/test", "test")))
                    .isInstanceOf(UnsupportedOperationException.class);
        }

        @Test
        @DisplayName("DiffEntry toString 应有图标")
        void testDiffEntryToString() {
            DiffEntry added = new DiffEntry(DiffType.ADDED, "/path", "desc");
            DiffEntry removed = new DiffEntry(DiffType.REMOVED, "/path", "desc");
            DiffEntry modified = new DiffEntry(DiffType.MODIFIED, "/path", "desc");

            assertThat(added.toString()).contains("➕");
            assertThat(removed.toString()).contains("➖");
            assertThat(modified.toString()).contains("✏️");
        }

        @Test
        @DisplayName("DiffType 应有标签")
        void testDiffTypeLabels() {
            assertThat(DiffType.ADDED.getLabel()).isEqualTo("新增");
            assertThat(DiffType.REMOVED.getLabel()).isEqualTo("删除");
            assertThat(DiffType.MODIFIED.getLabel()).isEqualTo("修改");
        }
    }

    // ================================================================
    // 报告输出
    // ================================================================

    @Nested
    @DisplayName("报告输出")
    class ReportTests {

        @Test
        @DisplayName("报告应包含 SQL 信息")
        void testReportContainsSqlInfo() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT name FROM orders",
                    SqlDialect.MYSQL, DiffMode.FULL);

            String report = result.getReport();
            assertThat(report).contains("SQL Diff");
            assertThat(report).contains("差异统计");
        }

        @Test
        @DisplayName("无差异报告应显示一致")
        void testReportIdentical() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT id FROM users",
                    SqlDialect.MYSQL, DiffMode.FULL);

            String report = result.getReport();
            assertThat(report).contains("完全一致");
        }

        @Test
        @DisplayName("toString 应等于 getReport")
        void testToStringEqualsReport() {
            SqlDiffResult result = diffEngine.diff(
                    "SELECT id FROM users",
                    "SELECT name FROM users",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.toString()).isEqualTo(result.getReport());
        }
    }

    // ================================================================
    // DDL Diff
    // ================================================================

    @Nested
    @DisplayName("DDL Diff")
    class DdlDiffTests {

        @Test
        @DisplayName("相同 CREATE TABLE 应无差异")
        void testIdenticalCreateTable() {
            SqlDiffResult result = diffEngine.diff(
                    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(100))",
                    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(100))",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isTrue();
        }

        @Test
        @DisplayName("不同 CREATE TABLE 应有差异")
        void testDifferentCreateTable() {
            SqlDiffResult result = diffEngine.diff(
                    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(100))",
                    "CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(200), age INT)",
                    SqlDialect.MYSQL, DiffMode.FULL);

            assertThat(result.isIdentical()).isFalse();
        }
    }
}
