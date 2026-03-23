package com.example.antlr;

import com.example.antlr.visual.ParseTreeVisualizer;
import com.example.antlr.mysql.MySqlLexer;
import com.example.antlr.mysql.MySqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.*;

/**
 * ParseTreeVisualizer 单元测试
 * 测试语法树可视化的四种输出格式
 */
@DisplayName("ParseTreeVisualizer - 语法树可视化")
class ParseTreeVisualizerTest {

    private ParseTreeVisualizer visualizer;
    private ParseTree tree;
    private Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // 解析一条典型 SQL
        String sql = "SELECT u.name, COUNT(o.id) FROM users u INNER JOIN orders o ON u.id = o.user_id WHERE o.amount > 100 GROUP BY u.name";
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        tree = parser.root();
        visualizer = new ParseTreeVisualizer(parser);

        // 创建临时目录
        tempDir = Files.createTempDirectory("visualizer-test");
    }

    @AfterEach
    void tearDown() throws IOException {
        // 清理临时文件
        if (tempDir != null && Files.exists(tempDir)) {
            Files.walk(tempDir)
                    .sorted((a, b) -> b.compareTo(a)) // 逆序：先删文件再删目录
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException ignored) {
                        }
                    });
        }
    }

    // ================================================================
    // DOT 格式
    // ================================================================

    @Nested
    @DisplayName("DOT (Graphviz) 格式")
    class DotFormatTests {

        @Test
        @DisplayName("toDot 应生成有效的 DOT 字符串")
        void testToDot() {
            String dot = visualizer.toDot(tree);

            assertThat(dot).as("DOT 输出不应为 null").isNotNull();
            assertThat(dot).as("DOT 输出不应为空").isNotEmpty();
            assertThat(dot).as("DOT 应以 digraph 开头").startsWith("digraph");
            assertThat(dot).as("DOT 应包含边（->）").contains("->");
            assertThat(dot).as("DOT 应以 } 结尾").endsWith("}\n");
        }

        @Test
        @DisplayName("DOT 应包含规则节点")
        void testDotContainsRuleNodes() {
            String dot = visualizer.toDot(tree);

            assertThat(dot.contains("selectStatement") || dot.contains("root"))
                    .as("DOT 应包含规则节点名称").isTrue();
        }

        @Test
        @DisplayName("DOT 应包含终端节点")
        void testDotContainsTerminalNodes() {
            String dot = visualizer.toDot(tree);

            assertThat(dot.contains("SELECT") || dot.contains("FROM"))
                    .as("DOT 应包含终端节点文本").isTrue();
        }

        @Test
        @DisplayName("DOT 应包含颜色属性")
        void testDotContainsColors() {
            String dot = visualizer.toDot(tree);

            assertThat(dot).as("DOT 应包含 fillcolor 属性").contains("fillcolor");
            assertThat(dot.contains("#CCE5FF") || dot.contains("#D4EDDA"))
                    .as("DOT 应包含规则/终端节点的颜色").isTrue();
        }

        @Test
        @DisplayName("toDotFile 应正确写入文件")
        void testToDotFile() throws IOException {
            String filePath = tempDir.resolve("test.dot").toString();
            visualizer.toDotFile(tree, filePath);

            Path path = Paths.get(filePath);
            assertThat(Files.exists(path)).as("DOT 文件应已创建").isTrue();
            String content = Files.readString(path);
            assertThat(content).as("文件内容应以 digraph 开头").startsWith("digraph");
            assertThat(content.length()).as("DOT 文件应有足够内容").isGreaterThan(100);
        }
    }

    // ================================================================
    // JSON 格式
    // ================================================================

    @Nested
    @DisplayName("JSON 格式")
    class JsonFormatTests {

        @Test
        @DisplayName("toJson 应生成有效的 JSON 字符串")
        void testToJson() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 输出不应为 null").isNotNull();
            assertThat(json).as("JSON 输出不应为空").isNotEmpty();
            assertThat(json.trim()).as("JSON 应以 { 开头").startsWith("{");
            assertThat(json.trim()).as("JSON 应以 } 结尾").endsWith("}");
        }

        @Test
        @DisplayName("JSON 应包含 type 字段")
        void testJsonContainsType() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 应包含 type 字段").contains("\"type\"");
            assertThat(json).as("JSON 应包含 rule 类型").contains("\"rule\"");
            assertThat(json).as("JSON 应包含 terminal 类型").contains("\"terminal\"");
        }

        @Test
        @DisplayName("JSON 应包含规则名称")
        void testJsonContainsRuleNames() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 应包含 name 字段").contains("\"name\"");
            assertThat(json.contains("root") || json.contains("selectStatement"))
                    .as("JSON 应包含规则名称").isTrue();
        }

        @Test
        @DisplayName("JSON 应包含终端节点文本")
        void testJsonContainsTerminalText() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 应包含 text 字段").contains("\"text\"");
        }

        @Test
        @DisplayName("JSON 应包含 children 字段")
        void testJsonContainsChildren() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 应包含 children 字段").contains("\"children\"");
        }

        @Test
        @DisplayName("JSON 应包含位置信息")
        void testJsonContainsPositionInfo() {
            String json = visualizer.toJson(tree);

            assertThat(json).as("JSON 应包含 line 字段").contains("\"line\"");
            assertThat(json).as("JSON 应包含 column 字段").contains("\"column\"");
            assertThat(json).as("JSON 应包含 tokenType 字段").contains("\"tokenType\"");
        }

        @Test
        @DisplayName("toJsonFile 应正确写入文件")
        void testToJsonFile() throws IOException {
            String filePath = tempDir.resolve("test.json").toString();
            visualizer.toJsonFile(tree, filePath);

            Path path = Paths.get(filePath);
            assertThat(Files.exists(path)).as("JSON 文件应已创建").isTrue();
            String content = Files.readString(path);
            assertThat(content.trim()).as("文件内容应以 { 开头").startsWith("{");
            assertThat(content.length()).as("JSON 文件应有足够内容").isGreaterThan(100);
        }
    }

    // ================================================================
    // HTML 格式
    // ================================================================

    @Nested
    @DisplayName("HTML 交互式页面")
    class HtmlFormatTests {

        @Test
        @DisplayName("toHtml 应生成有效的 HTML 字符串")
        void testToHtml() {
            String html = visualizer.toHtml(tree, "测试页面");

            assertThat(html).as("HTML 输出不应为 null").isNotNull();
            assertThat(html).as("HTML 输出不应为空").isNotEmpty();
            assertThat(html).as("应包含 DOCTYPE 声明").contains("<!DOCTYPE html>");
            assertThat(html).as("应包含 HTML 闭合标签").contains("</html>");
        }

        @Test
        @DisplayName("HTML 应包含标题")
        void testHtmlContainsTitle() {
            String html = visualizer.toHtml(tree, "My Parse Tree");

            assertThat(html).as("HTML 应包含自定义标题").contains("My Parse Tree");
        }

        @Test
        @DisplayName("HTML 应包含嵌入的 JSON 数据")
        void testHtmlContainsJsonData() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含嵌入的树数据").contains("treeData");
            assertThat(html).as("HTML 应包含 JSON 数据结构").contains("\"type\"");
        }

        @Test
        @DisplayName("HTML 应包含 SVG 元素")
        void testHtmlContainsSvg() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含 SVG 元素").contains("<svg");
            assertThat(html).as("HTML 应包含 treeSvg").contains("treeSvg");
        }

        @Test
        @DisplayName("HTML 应包含交互式控件")
        void testHtmlContainsControls() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含放大功能").contains("zoomIn");
            assertThat(html).as("HTML 应包含缩小功能").contains("zoomOut");
            assertThat(html).as("HTML 应包含重置功能").contains("resetView");
            assertThat(html).as("HTML 应包含全部展开功能").contains("expandAll");
            assertThat(html).as("HTML 应包含搜索功能").contains("searchInput");
            assertThat(html).as("HTML 应包含导出 SVG 功能").contains("exportSvg");
        }

        @Test
        @DisplayName("HTML 应包含小地图")
        void testHtmlContainsMinimap() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含小地图").contains("minimap");
        }

        @Test
        @DisplayName("HTML 应包含 Tooltip")
        void testHtmlContainsTooltip() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含 tooltip").contains("tooltip");
        }

        @Test
        @DisplayName("HTML 应包含 CSS 样式")
        void testHtmlContainsStyles() {
            String html = visualizer.toHtml(tree, "测试");

            assertThat(html).as("HTML 应包含内联样式").contains("<style>");
            assertThat(html).as("HTML 应有规则节点样式").contains("node-rule");
            assertThat(html).as("HTML 应有终端节点样式").contains("node-terminal");
        }

        @Test
        @DisplayName("toHtmlFile 应正确写入文件")
        void testToHtmlFile() throws IOException {
            String filePath = tempDir.resolve("test.html").toString();
            visualizer.toHtmlFile(tree, filePath, "单元测试页面");

            Path path = Paths.get(filePath);
            assertThat(Files.exists(path)).as("HTML 文件应已创建").isTrue();
            String content = Files.readString(path);
            assertThat(content).as("文件应包含 DOCTYPE").contains("<!DOCTYPE html>");
            assertThat(content).as("文件应包含自定义标题").contains("单元测试页面");
            assertThat(content.length()).as("HTML 文件应有足够内容").isGreaterThan(1000);
        }
    }

    // ================================================================
    // ASCII 树形文本
    // ================================================================

    @Nested
    @DisplayName("ASCII 树形文本")
    class AsciiFormatTests {

        @Test
        @DisplayName("toAsciiTree 应生成有效的文本")
        void testToAsciiTree() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii).as("ASCII 输出不应为 null").isNotNull();
            assertThat(ascii).as("ASCII 输出不应为空").isNotEmpty();
        }

        @Test
        @DisplayName("ASCII 应包含树形连接符")
        void testAsciiContainsTreeChars() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii.contains("├── ") || ascii.contains("└── "))
                    .as("ASCII 应包含树形连接符 ├── 或 └──").isTrue();
        }

        @Test
        @DisplayName("ASCII 应包含规则节点名称")
        void testAsciiContainsRuleNames() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii).as("ASCII 应包含 root 节点").contains("root");
        }

        @Test
        @DisplayName("ASCII 终端节点应有引号")
        void testAsciiTerminalQuotes() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii.contains("'SELECT'") || ascii.contains("'FROM'"))
                    .as("ASCII 终端节点应有引号包围").isTrue();
        }

        @Test
        @DisplayName("ASCII 应包含 EOF")
        void testAsciiContainsEof() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii).as("ASCII 应包含 <EOF>").contains("<EOF>");
        }

        @Test
        @DisplayName("ASCII 规则节点应显示子节点数量")
        void testAsciiShowsChildCount() {
            String ascii = visualizer.toAsciiTree(tree);

            assertThat(ascii).as("ASCII 规则节点应显示子节点数量如 (2)")
                    .matches("(?s).*\\(\\d+\\).*");
        }

        @Test
        @DisplayName("ASCII 应有多行输出")
        void testAsciiMultipleLines() {
            String ascii = visualizer.toAsciiTree(tree);

            String[] lines = ascii.split("\n");
            assertThat(lines.length).as("ASCII 应有多行输出（复杂 SQL 至少 10 行）").isGreaterThan(10);
        }
    }

    // ================================================================
    // SqlParserEngine 集成测试
    // ================================================================

    @Nested
    @DisplayName("SqlParserEngine 集成")
    class EngineIntegrationTests {

        private SqlParserEngine engine;

        @BeforeEach
        void setUp() {
            engine = new SqlParserEngine();
        }

        @Test
        @DisplayName("visualizeMySqlToAscii 应正常工作")
        void testEngineAscii() {
            String ascii = engine.visualizeMySqlToAscii(
                    "SELECT id, name FROM users WHERE age > 18");

            assertThat(ascii).isNotNull();
            assertThat(ascii).as("应包含 root 节点").contains("root");
            assertThat(ascii.contains("├── ") || ascii.contains("└── "))
                    .as("应包含树形连接符").isTrue();
        }

        @Test
        @DisplayName("visualizeMySqlToHtml 应生成文件")
        void testEngineHtml() throws IOException {
            String filePath = tempDir.resolve("engine-test.html").toString();
            engine.visualizeMySqlToHtml(
                    "SELECT * FROM users", filePath, "Engine Test");

            assertThat(Files.exists(Paths.get(filePath))).as("HTML 文件应已创建").isTrue();
        }

        @Test
        @DisplayName("visualizeMySqlToDot 应返回字符串并写文件")
        void testEngineDot() throws IOException {
            String filePath = tempDir.resolve("engine-test.dot").toString();
            String dot = engine.visualizeMySqlToDot(
                    "SELECT * FROM users", filePath);

            assertThat(dot).isNotNull();
            assertThat(dot).as("DOT 应以 digraph 开头").contains("digraph");
            assertThat(Files.exists(Paths.get(filePath))).as("DOT 文件应已创建").isTrue();
        }

        @Test
        @DisplayName("visualizeMySqlToDot filePath 为 null 时只返回字符串")
        void testEngineDotNullPath() throws IOException {
            String dot = engine.visualizeMySqlToDot(
                    "SELECT * FROM users", null);

            assertThat(dot).isNotNull();
            assertThat(dot).contains("digraph");
        }

        @Test
        @DisplayName("visualizeMySqlToJson 应返回字符串并写文件")
        void testEngineJson() throws IOException {
            String filePath = tempDir.resolve("engine-test.json").toString();
            String json = engine.visualizeMySqlToJson(
                    "SELECT * FROM users", filePath);

            assertThat(json).isNotNull();
            assertThat(json.trim()).as("JSON 应以 { 开头").startsWith("{");
            assertThat(Files.exists(Paths.get(filePath))).as("JSON 文件应已创建").isTrue();
        }
    }

    // ================================================================
    // 边界情况
    // ================================================================

    @Nested
    @DisplayName("边界情况")
    class EdgeCaseTests {

        @Test
        @DisplayName("简单 SQL 不应抛异常")
        void testSimpleSql() {
            String sql = "SELECT 1";
            MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
            lexer.removeErrorListeners();
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokens);
            parser.removeErrorListeners();
            ParseTree simpleTree = parser.root();
            ParseTreeVisualizer vis = new ParseTreeVisualizer(parser);

            assertThatCode(() -> vis.toDot(simpleTree)).as("简单 SQL 的 DOT 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toJson(simpleTree)).as("简单 SQL 的 JSON 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toHtml(simpleTree, "test")).as("简单 SQL 的 HTML 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toAsciiTree(simpleTree)).as("简单 SQL 的 ASCII 不应抛异常").doesNotThrowAnyException();
        }

        @Test
        @DisplayName("复杂 SQL 不应抛异常")
        void testComplexSql() {
            String sql = "SELECT u.name, COUNT(o.id) AS cnt " +
                    "FROM users u " +
                    "INNER JOIN orders o ON u.id = o.user_id " +
                    "LEFT JOIN products p ON o.product_id = p.id " +
                    "WHERE o.amount > (SELECT AVG(amount) FROM orders) " +
                    "AND u.status IN ('active', 'vip') " +
                    "GROUP BY u.name " +
                    "HAVING cnt > 5 " +
                    "ORDER BY cnt DESC " +
                    "LIMIT 20";

            MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
            lexer.removeErrorListeners();
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokens);
            parser.removeErrorListeners();
            ParseTree complexTree = parser.root();
            ParseTreeVisualizer vis = new ParseTreeVisualizer(parser);

            assertThatCode(() -> vis.toDot(complexTree)).as("复杂 SQL 的 DOT 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toJson(complexTree)).as("复杂 SQL 的 JSON 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toHtml(complexTree, "complex")).as("复杂 SQL 的 HTML 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toAsciiTree(complexTree)).as("复杂 SQL 的 ASCII 不应抛异常").doesNotThrowAnyException();
        }

        @Test
        @DisplayName("DDL SQL 可视化不应抛异常")
        void testDdlSql() {
            String sql = "CREATE TABLE orders (id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "user_id INT NOT NULL, amount DECIMAL(10,2), PRIMARY KEY (id))";

            MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
            lexer.removeErrorListeners();
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MySqlParser parser = new MySqlParser(tokens);
            parser.removeErrorListeners();
            ParseTree ddlTree = parser.root();
            ParseTreeVisualizer vis = new ParseTreeVisualizer(parser);

            assertThatCode(() -> vis.toAsciiTree(ddlTree)).as("DDL 的 ASCII 不应抛异常").doesNotThrowAnyException();
            assertThatCode(() -> vis.toDot(ddlTree)).as("DDL 的 DOT 不应抛异常").doesNotThrowAnyException();
        }
    }
}
