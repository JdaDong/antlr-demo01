package com.example.antlr;

import com.example.antlr.calcite.CalciteSqlEngine;
import com.example.antlr.calcite.CalciteSqlEngine.CalciteDialect;
import com.example.antlr.calcite.CalciteSqlEngine.ValidationResult;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.type.SqlTypeName;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * CalciteSqlEngine 单元测试
 * 测试 Apache Calcite 集成的 SQL 解析、验证、方言转换、格式化、元数据提取等能力
 */
@DisplayName("CalciteSqlEngine - Apache Calcite SQL 引擎")
class CalciteSqlEngineTest {

    private CalciteSqlEngine engine;

    @BeforeEach
    void setUp() {
        engine = new CalciteSqlEngine();
    }

    // ================================================================
    // SQL 解析
    // ================================================================

    @Nested
    @DisplayName("SQL 解析")
    class ParseTests {

        @Test
        @DisplayName("简单 SELECT 应成功解析")
        void testParseSimpleSelect() throws SqlParseException {
            SqlNode node = engine.parse("SELECT id, name FROM users WHERE age > 18", CalciteDialect.ANSI);
            assertThat(node).as("解析结果不应为 null").isNotNull();
            assertThat(node.toString()).as("AST 应包含 SELECT").containsIgnoringCase("SELECT");
        }

        @Test
        @DisplayName("带 JOIN 的 SELECT 应成功解析")
        void testParseJoinSelect() throws SqlParseException {
            SqlNode node = engine.parse(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id",
                    CalciteDialect.ANSI);
            assertThat(node).isNotNull();
        }

        @Test
        @DisplayName("INSERT 语句应成功解析")
        void testParseInsert() throws SqlParseException {
            SqlNode node = engine.parse(
                    "INSERT INTO users (name, age) VALUES ('Alice', 25)",
                    CalciteDialect.ANSI);
            assertThat(node).isNotNull();
        }

        @Test
        @DisplayName("UPDATE 语句应成功解析")
        void testParseUpdate() throws SqlParseException {
            SqlNode node = engine.parse(
                    "UPDATE users SET age = 26 WHERE name = 'Alice'",
                    CalciteDialect.ANSI);
            assertThat(node).isNotNull();
        }

        @Test
        @DisplayName("DELETE 语句应成功解析")
        void testParseDelete() throws SqlParseException {
            SqlNode node = engine.parse(
                    "DELETE FROM users WHERE age < 18",
                    CalciteDialect.ANSI);
            assertThat(node).isNotNull();
        }

        @Test
        @DisplayName("MySQL 方言解析（反引号标识符）")
        void testParseMySqlDialect() throws SqlParseException {
            SqlNode node = engine.parse(
                    "SELECT `id`, `name` FROM `users`",
                    CalciteDialect.MYSQL);
            assertThat(node).isNotNull();
        }

        @Test
        @DisplayName("非法 SQL 应抛出 SqlParseException")
        void testParseInvalidSql() {
            assertThatThrownBy(() -> engine.parse("SELECTT * FROMM", CalciteDialect.ANSI))
                    .isInstanceOf(SqlParseException.class);
        }

        @Test
        @DisplayName("多条 SQL 语句应成功解析")
        void testParseMulti() throws SqlParseException {
            var nodes = engine.parseMulti(
                    "SELECT 1; SELECT 2",
                    CalciteDialect.ANSI);
            assertThat(nodes).as("应解析出 2 条语句").hasSize(2);
        }
    }

    // ================================================================
    // tryParse 验证
    // ================================================================

    @Nested
    @DisplayName("语法验证 (tryParse)")
    class TryParseTests {

        @Test
        @DisplayName("合法 SQL 应返回 true")
        void testTryParseValid() {
            boolean result = engine.tryParse("SELECT 1", CalciteDialect.ANSI);
            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("非法 SQL 应返回 false")
        void testTryParseInvalid() {
            boolean result = engine.tryParse("THIS IS NOT SQL", CalciteDialect.ANSI);
            assertThat(result).isFalse();
        }

        @Test
        @DisplayName("空 SQL 应返回 false")
        void testTryParseEmpty() {
            boolean result = engine.tryParse("", CalciteDialect.ANSI);
            assertThat(result).isFalse();
        }
    }

    // ================================================================
    // 方言转换
    // ================================================================

    @Nested
    @DisplayName("方言转换")
    class DialectConversionTests {

        @Test
        @DisplayName("ANSI → MySQL 转换")
        void testConvertAnsiToMySql() throws SqlParseException {
            String mysql = engine.convert(
                    "SELECT \"id\", \"name\" FROM \"users\"",
                    CalciteDialect.ANSI,
                    CalciteDialect.MYSQL);
            assertThat(mysql).as("转换结果不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("ANSI → PostgreSQL 转换")
        void testConvertAnsiToPostgres() throws SqlParseException {
            String pg = engine.convert(
                    "SELECT id, name FROM users WHERE age > 18",
                    CalciteDialect.ANSI,
                    CalciteDialect.POSTGRESQL);
            assertThat(pg).as("应包含 SELECT").containsIgnoringCase("SELECT");
        }

        @Test
        @DisplayName("ANSI → Spark 转换")
        void testConvertAnsiToSpark() throws SqlParseException {
            String spark = engine.convert(
                    "SELECT id, name FROM users",
                    CalciteDialect.ANSI,
                    CalciteDialect.SPARK);
            assertThat(spark).isNotEmpty();
        }

        @Test
        @DisplayName("ANSI → Oracle 转换")
        void testConvertAnsiToOracle() throws SqlParseException {
            String oracle = engine.convert(
                    "SELECT id FROM users FETCH FIRST 10 ROWS ONLY",
                    CalciteDialect.ANSI,
                    CalciteDialect.ORACLE);
            assertThat(oracle).isNotEmpty();
        }

        @Test
        @DisplayName("批量转换：ANSI → 多方言")
        void testConvertToMultiple() throws SqlParseException {
            Map<CalciteDialect, String> results = engine.convertToMultiple(
                    "SELECT id, name FROM users WHERE age > 18",
                    CalciteDialect.ANSI,
                    CalciteDialect.MYSQL, CalciteDialect.POSTGRESQL, CalciteDialect.SPARK);

            assertThat(results).as("应有 3 种方言的结果").hasSize(3);
            assertThat(results.get(CalciteDialect.MYSQL)).as("MySQL 结果不为空").isNotEmpty();
            assertThat(results.get(CalciteDialect.POSTGRESQL)).as("PostgreSQL 结果不为空").isNotEmpty();
            assertThat(results.get(CalciteDialect.SPARK)).as("Spark 结果不为空").isNotEmpty();
        }

        @Test
        @DisplayName("ANSI → Hive 转换")
        void testConvertAnsiToHive() throws SqlParseException {
            String hive = engine.convert(
                    "SELECT id, name FROM users",
                    CalciteDialect.ANSI,
                    CalciteDialect.HIVE);
            assertThat(hive).isNotEmpty();
        }
    }

    // ================================================================
    // SQL 格式化
    // ================================================================

    @Nested
    @DisplayName("SQL 格式化 (Pretty Print)")
    class FormatTests {

        @Test
        @DisplayName("默认格式化应包含换行")
        void testDefaultFormat() throws SqlParseException {
            String formatted = engine.format(
                    "SELECT id, name, age FROM users WHERE age > 18 ORDER BY name",
                    CalciteDialect.ANSI);

            assertThat(formatted).as("格式化结果应包含换行").contains("\n");
            assertThat(formatted).as("应包含 SELECT").containsIgnoringCase("SELECT");
        }

        @Test
        @DisplayName("关键字大写格式化")
        void testUppercaseKeywords() throws SqlParseException {
            String formatted = engine.format(
                    "select id from users where age > 18",
                    CalciteDialect.ANSI, 4, true);

            assertThat(formatted).as("关键字应大写").contains("SELECT");
        }

        @Test
        @DisplayName("关键字小写格式化")
        void testLowercaseKeywords() throws SqlParseException {
            String formatted = engine.format(
                    "SELECT id FROM users WHERE age > 18",
                    CalciteDialect.ANSI, 4, false);

            assertThat(formatted).as("关键字应小写").contains("select");
        }

        @Test
        @DisplayName("自定义缩进格式化")
        void testCustomIndent() throws SqlParseException {
            String formatted = engine.format(
                    "SELECT id, name FROM users",
                    CalciteDialect.ANSI, 2, true);

            assertThat(formatted).isNotEmpty();
        }
    }

    // ================================================================
    // AST 分析（元数据提取）
    // ================================================================

    @Nested
    @DisplayName("AST 分析")
    class AstAnalysisTests {

        @Test
        @DisplayName("提取单表表名")
        void testExtractSingleTable() throws SqlParseException {
            Set<String> tables = engine.extractTableNames(
                    "SELECT id FROM users WHERE age > 18",
                    CalciteDialect.ANSI);

            assertThat(tables).as("应包含 users 表")
                    .anyMatch(t -> t.equalsIgnoreCase("users") || t.equalsIgnoreCase("USERS"));
        }

        @Test
        @DisplayName("提取 JOIN 多表表名")
        void testExtractJoinTables() throws SqlParseException {
            Set<String> tables = engine.extractTableNames(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id",
                    CalciteDialect.ANSI);

            assertThat(tables).as("应至少包含 2 个表").hasSizeGreaterThanOrEqualTo(2);
        }

        @Test
        @DisplayName("提取子查询中的表名")
        void testExtractSubqueryTables() throws SqlParseException {
            Set<String> tables = engine.extractTableNames(
                    "SELECT name FROM users WHERE id IN (SELECT user_id FROM orders)",
                    CalciteDialect.ANSI);

            assertThat(tables).as("应至少包含 2 个表").hasSizeGreaterThanOrEqualTo(2);
        }

        @Test
        @DisplayName("提取 INSERT 目标表名")
        void testExtractInsertTable() throws SqlParseException {
            Set<String> tables = engine.extractTableNames(
                    "INSERT INTO archive SELECT * FROM users WHERE status = 'inactive'",
                    CalciteDialect.ANSI);

            assertThat(tables).as("应至少包含 archive 表")
                    .anyMatch(t -> t.equalsIgnoreCase("archive") || t.equalsIgnoreCase("ARCHIVE"));
        }

        @Test
        @DisplayName("获取 SELECT 语句类型")
        void testStatementTypeSelect() throws SqlParseException {
            String type = engine.getStatementType("SELECT 1", CalciteDialect.ANSI);
            assertThat(type).as("应为 SELECT").isEqualTo("SELECT");
        }

        @Test
        @DisplayName("获取 INSERT 语句类型")
        void testStatementTypeInsert() throws SqlParseException {
            String type = engine.getStatementType(
                    "INSERT INTO users (name) VALUES ('test')",
                    CalciteDialect.ANSI);
            assertThat(type).as("应为 INSERT").isEqualTo("INSERT");
        }

        @Test
        @DisplayName("获取 DELETE 语句类型")
        void testStatementTypeDelete() throws SqlParseException {
            String type = engine.getStatementType("DELETE FROM users", CalciteDialect.ANSI);
            assertThat(type).as("应为 DELETE").isEqualTo("DELETE");
        }

        @Test
        @DisplayName("提取列引用")
        void testExtractColumns() throws SqlParseException {
            List<String> columns = engine.extractColumnRefs(
                    "SELECT id, name, age FROM users",
                    CalciteDialect.ANSI);

            assertThat(columns).as("应提取到列名").isNotEmpty();
        }

        @Test
        @DisplayName("AST dump 应有内容")
        void testDumpAst() throws SqlParseException {
            String ast = engine.dumpAst("SELECT id FROM users", CalciteDialect.ANSI);
            assertThat(ast).as("AST dump 不应为空").isNotEmpty();
        }
    }

    // ================================================================
    // Schema 验证
    // ================================================================

    @Nested
    @DisplayName("Schema 验证")
    class ValidationTests {

        private Map<String, Map<String, SqlTypeName>> schema;

        @BeforeEach
        void setUpSchema() {
            schema = new LinkedHashMap<>();
            Map<String, SqlTypeName> usersColumns = new LinkedHashMap<>();
            usersColumns.put("ID", SqlTypeName.INTEGER);
            usersColumns.put("NAME", SqlTypeName.VARCHAR);
            usersColumns.put("AGE", SqlTypeName.INTEGER);
            usersColumns.put("EMAIL", SqlTypeName.VARCHAR);
            schema.put("USERS", usersColumns);

            Map<String, SqlTypeName> ordersColumns = new LinkedHashMap<>();
            ordersColumns.put("ID", SqlTypeName.INTEGER);
            ordersColumns.put("USER_ID", SqlTypeName.INTEGER);
            ordersColumns.put("AMOUNT", SqlTypeName.DECIMAL);
            ordersColumns.put("STATUS", SqlTypeName.VARCHAR);
            schema.put("ORDERS", ordersColumns);
        }

        @Test
        @DisplayName("合法查询应验证通过")
        void testValidQuery() {
            ValidationResult result = engine.tryValidate(
                    "SELECT id, name FROM users WHERE age > 18",
                    CalciteDialect.ANSI, schema);

            assertThat(result.isValid()).as("合法查询应通过验证").isTrue();
        }

        @Test
        @DisplayName("不存在的表应验证失败")
        void testInvalidTable() {
            ValidationResult result = engine.tryValidate(
                    "SELECT id FROM nonexistent_table",
                    CalciteDialect.ANSI, schema);

            assertThat(result.isValid()).as("不存在的表应验证失败").isFalse();
            assertThat(result.getErrorMessage()).as("错误信息应提到表不存在").isNotEmpty();
        }

        @Test
        @DisplayName("不存在的列应验证失败")
        void testInvalidColumn() {
            ValidationResult result = engine.tryValidate(
                    "SELECT nonexistent_col FROM users",
                    CalciteDialect.ANSI, schema);

            assertThat(result.isValid()).as("不存在的列应验证失败").isFalse();
        }

        @Test
        @DisplayName("JOIN 查询应验证通过")
        void testValidJoinQuery() {
            ValidationResult result = engine.tryValidate(
                    "SELECT u.name, o.amount FROM users u INNER JOIN orders o ON u.id = o.user_id",
                    CalciteDialect.ANSI, schema);

            assertThat(result.isValid()).as("JOIN 查询应验证通过").isTrue();
        }
    }

    // ================================================================
    // 关系代数 (EXPLAIN)
    // ================================================================

    @Nested
    @DisplayName("关系代数 / EXPLAIN")
    class RelAlgebraTests {

        private Map<String, Map<String, SqlTypeName>> schema;

        @BeforeEach
        void setUpSchema() {
            schema = new LinkedHashMap<>();
            Map<String, SqlTypeName> usersColumns = new LinkedHashMap<>();
            usersColumns.put("ID", SqlTypeName.INTEGER);
            usersColumns.put("NAME", SqlTypeName.VARCHAR);
            usersColumns.put("AGE", SqlTypeName.INTEGER);
            schema.put("USERS", usersColumns);
        }

        @Test
        @DisplayName("EXPLAIN 应返回逻辑计划")
        void testExplain() throws Exception {
            String plan = engine.explain(
                    "SELECT id, name FROM users WHERE age > 18",
                    CalciteDialect.ANSI, schema);

            assertThat(plan).as("逻辑计划不应为空").isNotEmpty();
            assertThat(plan).as("应包含 LogicalProject 或 LogicalFilter")
                    .satisfiesAnyOf(
                        p -> assertThat(p).contains("LogicalProject"),
                        p -> assertThat(p).contains("LogicalFilter"),
                        p -> assertThat(p).contains("LogicalTableScan")
                    );
        }

        @Test
        @DisplayName("toRelNode 应返回有效 RelRoot")
        void testToRelNode() throws Exception {
            var relRoot = engine.toRelNode(
                    "SELECT id FROM users",
                    CalciteDialect.ANSI, schema);

            assertThat(relRoot).as("RelRoot 不应为 null").isNotNull();
            assertThat(relRoot.rel).as("RelNode 不应为 null").isNotNull();
        }
    }

    // ================================================================
    // CalciteDialect 枚举
    // ================================================================

    @Nested
    @DisplayName("CalciteDialect 枚举")
    class DialectEnumTests {

        @Test
        @DisplayName("fromString 应解析常见方言名")
        void testFromString() {
            assertThat(CalciteDialect.fromString("mysql")).isEqualTo(CalciteDialect.MYSQL);
            assertThat(CalciteDialect.fromString("postgresql")).isEqualTo(CalciteDialect.POSTGRESQL);
            assertThat(CalciteDialect.fromString("postgres")).isEqualTo(CalciteDialect.POSTGRESQL);
            assertThat(CalciteDialect.fromString("pg")).isEqualTo(CalciteDialect.POSTGRESQL);
            assertThat(CalciteDialect.fromString("spark")).isEqualTo(CalciteDialect.SPARK);
            assertThat(CalciteDialect.fromString("sparksql")).isEqualTo(CalciteDialect.SPARK);
            assertThat(CalciteDialect.fromString("oracle")).isEqualTo(CalciteDialect.ORACLE);
            assertThat(CalciteDialect.fromString("hive")).isEqualTo(CalciteDialect.HIVE);
            assertThat(CalciteDialect.fromString("bigquery")).isEqualTo(CalciteDialect.BIG_QUERY);
            assertThat(CalciteDialect.fromString("presto")).isEqualTo(CalciteDialect.PRESTO);
            assertThat(CalciteDialect.fromString("clickhouse")).isEqualTo(CalciteDialect.CLICKHOUSE);
        }

        @Test
        @DisplayName("fromString 不区分大小写")
        void testFromStringCaseInsensitive() {
            assertThat(CalciteDialect.fromString("MYSQL")).isEqualTo(CalciteDialect.MYSQL);
            assertThat(CalciteDialect.fromString("PostgreSQL")).isEqualTo(CalciteDialect.POSTGRESQL);
        }

        @Test
        @DisplayName("不支持的方言应抛异常")
        void testFromStringInvalid() {
            assertThatThrownBy(() -> CalciteDialect.fromString("unknown_dialect"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("Unsupported dialect");
        }

        @Test
        @DisplayName("null 方言应抛 NullPointerException")
        void testFromStringNull() {
            assertThatThrownBy(() -> CalciteDialect.fromString(null))
                    .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("每种方言都应有有效的 SqlDialect 实例")
        void testAllDialectsHaveSqlDialect() {
            for (CalciteDialect dialect : CalciteDialect.values()) {
                assertThat(dialect.toSqlDialect())
                        .as("方言 %s 应有有效的 SqlDialect", dialect)
                        .isNotNull();
            }
        }

        @Test
        @DisplayName("每种方言都有显示名")
        void testDisplayNames() {
            for (CalciteDialect dialect : CalciteDialect.values()) {
                assertThat(dialect.getDisplayName())
                        .as("方言 %s 应有显示名", dialect)
                        .isNotEmpty();
            }
        }
    }

    // ================================================================
    // SqlParserEngine 集成
    // ================================================================

    @Nested
    @DisplayName("SqlParserEngine 集成")
    class IntegrationTests {

        @Test
        @DisplayName("getCalciteEngine() 应返回非空实例")
        void testGetCalciteEngine() {
            SqlParserEngine parserEngine = new SqlParserEngine();
            CalciteSqlEngine calcite = parserEngine.getCalciteEngine();

            assertThat(calcite).as("Calcite 引擎不应为 null").isNotNull();
        }

        @Test
        @DisplayName("getCalciteEngine() 多次调用应返回同一实例（懒加载）")
        void testGetCalciteEngineSingleton() {
            SqlParserEngine parserEngine = new SqlParserEngine();
            CalciteSqlEngine first = parserEngine.getCalciteEngine();
            CalciteSqlEngine second = parserEngine.getCalciteEngine();

            assertThat(first).as("两次调用应返回同一实例").isSameAs(second);
        }

        @Test
        @DisplayName("通过 SqlParserEngine 使用 Calcite 方言转换")
        void testCalciteViaEngine() throws SqlParseException {
            SqlParserEngine parserEngine = new SqlParserEngine();
            CalciteSqlEngine calcite = parserEngine.getCalciteEngine();

            String result = calcite.convert(
                    "SELECT id, name FROM users WHERE age > 18",
                    CalciteDialect.ANSI,
                    CalciteDialect.MYSQL);

            assertThat(result).as("转换结果不应为空").isNotEmpty();
        }
    }
}
