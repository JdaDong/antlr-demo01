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

import static org.junit.jupiter.api.Assertions.*;

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

            assertNotNull(dot, "DOT 输出不应为 null");
            assertFalse(dot.isEmpty(), "DOT 输出不应为空");
            assertTrue(dot.startsWith("digraph"), "DOT 应以 digraph 开头");
            assertTrue(dot.contains("->"), "DOT 应包含边（->）");
            assertTrue(dot.endsWith("}\n"), "DOT 应以 } 结尾");
        }

        @Test
        @DisplayName("DOT 应包含规则节点")
        void testDotContainsRuleNodes() {
            String dot = visualizer.toDot(tree);

            assertTrue(dot.contains("selectStatement") || dot.contains("root"),
                    "DOT 应包含规则节点名称");
        }

        @Test
        @DisplayName("DOT 应包含终端节点")
        void testDotContainsTerminalNodes() {
            String dot = visualizer.toDot(tree);

            assertTrue(dot.contains("SELECT") || dot.contains("FROM"),
                    "DOT 应包含终端节点文本");
        }

        @Test
        @DisplayName("DOT 应包含颜色属性")
        void testDotContainsColors() {
            String dot = visualizer.toDot(tree);

            assertTrue(dot.contains("fillcolor"), "DOT 应包含 fillcolor 属性");
            assertTrue(dot.contains("#CCE5FF") || dot.contains("#D4EDDA"),
                    "DOT 应包含规则/终端节点的颜色");
        }

        @Test
        @DisplayName("toDotFile 应正确写入文件")
        void testToDotFile() throws IOException {
            String filePath = tempDir.resolve("test.dot").toString();
            visualizer.toDotFile(tree, filePath);

            Path path = Paths.get(filePath);
            assertTrue(Files.exists(path), "DOT 文件应已创建");
            String content = Files.readString(path);
            assertTrue(content.startsWith("digraph"), "文件内容应以 digraph 开头");
            assertTrue(content.length() > 100, "DOT 文件应有足够内容");
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

            assertNotNull(json, "JSON 输出不应为 null");
            assertFalse(json.isEmpty(), "JSON 输出不应为空");
            assertTrue(json.trim().startsWith("{"), "JSON 应以 { 开头");
            assertTrue(json.trim().endsWith("}"), "JSON 应以 } 结尾");
        }

        @Test
        @DisplayName("JSON 应包含 type 字段")
        void testJsonContainsType() {
            String json = visualizer.toJson(tree);

            assertTrue(json.contains("\"type\""), "JSON 应包含 type 字段");
            assertTrue(json.contains("\"rule\""), "JSON 应包含 rule 类型");
            assertTrue(json.contains("\"terminal\""), "JSON 应包含 terminal 类型");
        }

        @Test
        @DisplayName("JSON 应包含规则名称")
        void testJsonContainsRuleNames() {
            String json = visualizer.toJson(tree);

            assertTrue(json.contains("\"name\""), "JSON 应包含 name 字段");
            assertTrue(json.contains("root") || json.contains("selectStatement"),
                    "JSON 应包含规则名称");
        }

        @Test
        @DisplayName("JSON 应包含终端节点文本")
        void testJsonContainsTerminalText() {
            String json = visualizer.toJson(tree);

            assertTrue(json.contains("\"text\""), "JSON 应包含 text 字段");
        }

        @Test
        @DisplayName("JSON 应包含 children 字段")
        void testJsonContainsChildren() {
            String json = visualizer.toJson(tree);

            assertTrue(json.contains("\"children\""), "JSON 应包含 children 字段");
        }

        @Test
        @DisplayName("JSON 应包含位置信息")
        void testJsonContainsPositionInfo() {
            String json = visualizer.toJson(tree);

            assertTrue(json.contains("\"line\""), "JSON 应包含 line 字段");
            assertTrue(json.contains("\"column\""), "JSON 应包含 column 字段");
            assertTrue(json.contains("\"tokenType\""), "JSON 应包含 tokenType 字段");
        }

        @Test
        @DisplayName("toJsonFile 应正确写入文件")
        void testToJsonFile() throws IOException {
            String filePath = tempDir.resolve("test.json").toString();
            visualizer.toJsonFile(tree, filePath);

            Path path = Paths.get(filePath);
            assertTrue(Files.exists(path), "JSON 文件应已创建");
            String content = Files.readString(path);
            assertTrue(content.trim().startsWith("{"), "文件内容应以 { 开头");
            assertTrue(content.length() > 100, "JSON 文件应有足够内容");
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

            assertNotNull(html, "HTML 输出不应为 null");
            assertFalse(html.isEmpty(), "HTML 输出不应为空");
            assertTrue(html.contains("<!DOCTYPE html>"), "应包含 DOCTYPE 声明");
            assertTrue(html.contains("</html>"), "应包含 HTML 闭合标签");
        }

        @Test
        @DisplayName("HTML 应包含标题")
        void testHtmlContainsTitle() {
            String html = visualizer.toHtml(tree, "My Parse Tree");

            assertTrue(html.contains("My Parse Tree"), "HTML 应包含自定义标题");
        }

        @Test
        @DisplayName("HTML 应包含嵌入的 JSON 数据")
        void testHtmlContainsJsonData() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("treeData"), "HTML 应包含嵌入的树数据");
            assertTrue(html.contains("\"type\""), "HTML 应包含 JSON 数据结构");
        }

        @Test
        @DisplayName("HTML 应包含 SVG 元素")
        void testHtmlContainsSvg() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("<svg"), "HTML 应包含 SVG 元素");
            assertTrue(html.contains("treeSvg"), "HTML 应包含 treeSvg");
        }

        @Test
        @DisplayName("HTML 应包含交互式控件")
        void testHtmlContainsControls() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("zoomIn"), "HTML 应包含放大功能");
            assertTrue(html.contains("zoomOut"), "HTML 应包含缩小功能");
            assertTrue(html.contains("resetView"), "HTML 应包含重置功能");
            assertTrue(html.contains("expandAll"), "HTML 应包含全部展开功能");
            assertTrue(html.contains("searchInput"), "HTML 应包含搜索功能");
            assertTrue(html.contains("exportSvg"), "HTML 应包含导出 SVG 功能");
        }

        @Test
        @DisplayName("HTML 应包含小地图")
        void testHtmlContainsMinimap() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("minimap"), "HTML 应包含小地图");
        }

        @Test
        @DisplayName("HTML 应包含 Tooltip")
        void testHtmlContainsTooltip() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("tooltip"), "HTML 应包含 tooltip");
        }

        @Test
        @DisplayName("HTML 应包含 CSS 样式")
        void testHtmlContainsStyles() {
            String html = visualizer.toHtml(tree, "测试");

            assertTrue(html.contains("<style>"), "HTML 应包含内联样式");
            assertTrue(html.contains("node-rule"), "HTML 应有规则节点样式");
            assertTrue(html.contains("node-terminal"), "HTML 应有终端节点样式");
        }

        @Test
        @DisplayName("toHtmlFile 应正确写入文件")
        void testToHtmlFile() throws IOException {
            String filePath = tempDir.resolve("test.html").toString();
            visualizer.toHtmlFile(tree, filePath, "单元测试页面");

            Path path = Paths.get(filePath);
            assertTrue(Files.exists(path), "HTML 文件应已创建");
            String content = Files.readString(path);
            assertTrue(content.contains("<!DOCTYPE html>"), "文件应包含 DOCTYPE");
            assertTrue(content.contains("单元测试页面"), "文件应包含自定义标题");
            assertTrue(content.length() > 1000, "HTML 文件应有足够内容");
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

            assertNotNull(ascii, "ASCII 输出不应为 null");
            assertFalse(ascii.isEmpty(), "ASCII 输出不应为空");
        }

        @Test
        @DisplayName("ASCII 应包含树形连接符")
        void testAsciiContainsTreeChars() {
            String ascii = visualizer.toAsciiTree(tree);

            assertTrue(ascii.contains("├── ") || ascii.contains("└── "),
                    "ASCII 应包含树形连接符 ├── 或 └──");
        }

        @Test
        @DisplayName("ASCII 应包含规则节点名称")
        void testAsciiContainsRuleNames() {
            String ascii = visualizer.toAsciiTree(tree);

            assertTrue(ascii.contains("root"), "ASCII 应包含 root 节点");
        }

        @Test
        @DisplayName("ASCII 终端节点应有引号")
        void testAsciiTerminalQuotes() {
            String ascii = visualizer.toAsciiTree(tree);

            assertTrue(ascii.contains("'SELECT'") || ascii.contains("'FROM'"),
                    "ASCII 终端节点应有引号包围");
        }

        @Test
        @DisplayName("ASCII 应包含 EOF")
        void testAsciiContainsEof() {
            String ascii = visualizer.toAsciiTree(tree);

            assertTrue(ascii.contains("<EOF>"), "ASCII 应包含 <EOF>");
        }

        @Test
        @DisplayName("ASCII 规则节点应显示子节点数量")
        void testAsciiShowsChildCount() {
            String ascii = visualizer.toAsciiTree(tree);

            // 规则节点后面应有 (N) 格式的子节点数量
            assertTrue(ascii.matches("(?s).*\\(\\d+\\).*"),
                    "ASCII 规则节点应显示子节点数量如 (2)");
        }

        @Test
        @DisplayName("ASCII 应有多行输出")
        void testAsciiMultipleLines() {
            String ascii = visualizer.toAsciiTree(tree);

            String[] lines = ascii.split("\n");
            assertTrue(lines.length > 10, "ASCII 应有多行输出（复杂 SQL 至少 10 行），实际: " + lines.length);
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

            assertNotNull(ascii);
            assertTrue(ascii.contains("root"), "应包含 root 节点");
            assertTrue(ascii.contains("├── ") || ascii.contains("└── "),
                    "应包含树形连接符");
        }

        @Test
        @DisplayName("visualizeMySqlToHtml 应生成文件")
        void testEngineHtml() throws IOException {
            String filePath = tempDir.resolve("engine-test.html").toString();
            engine.visualizeMySqlToHtml(
                    "SELECT * FROM users", filePath, "Engine Test");

            assertTrue(Files.exists(Paths.get(filePath)), "HTML 文件应已创建");
        }

        @Test
        @DisplayName("visualizeMySqlToDot 应返回字符串并写文件")
        void testEngineDot() throws IOException {
            String filePath = tempDir.resolve("engine-test.dot").toString();
            String dot = engine.visualizeMySqlToDot(
                    "SELECT * FROM users", filePath);

            assertNotNull(dot);
            assertTrue(dot.contains("digraph"), "DOT 应以 digraph 开头");
            assertTrue(Files.exists(Paths.get(filePath)), "DOT 文件应已创建");
        }

        @Test
        @DisplayName("visualizeMySqlToDot filePath 为 null 时只返回字符串")
        void testEngineDotNullPath() throws IOException {
            String dot = engine.visualizeMySqlToDot(
                    "SELECT * FROM users", null);

            assertNotNull(dot);
            assertTrue(dot.contains("digraph"));
        }

        @Test
        @DisplayName("visualizeMySqlToJson 应返回字符串并写文件")
        void testEngineJson() throws IOException {
            String filePath = tempDir.resolve("engine-test.json").toString();
            String json = engine.visualizeMySqlToJson(
                    "SELECT * FROM users", filePath);

            assertNotNull(json);
            assertTrue(json.trim().startsWith("{"), "JSON 应以 { 开头");
            assertTrue(Files.exists(Paths.get(filePath)), "JSON 文件应已创建");
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

            assertDoesNotThrow(() -> vis.toDot(simpleTree), "简单 SQL 的 DOT 不应抛异常");
            assertDoesNotThrow(() -> vis.toJson(simpleTree), "简单 SQL 的 JSON 不应抛异常");
            assertDoesNotThrow(() -> vis.toHtml(simpleTree, "test"), "简单 SQL 的 HTML 不应抛异常");
            assertDoesNotThrow(() -> vis.toAsciiTree(simpleTree), "简单 SQL 的 ASCII 不应抛异常");
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

            assertDoesNotThrow(() -> vis.toDot(complexTree), "复杂 SQL 的 DOT 不应抛异常");
            assertDoesNotThrow(() -> vis.toJson(complexTree), "复杂 SQL 的 JSON 不应抛异常");
            assertDoesNotThrow(() -> vis.toHtml(complexTree, "complex"), "复杂 SQL 的 HTML 不应抛异常");
            assertDoesNotThrow(() -> vis.toAsciiTree(complexTree), "复杂 SQL 的 ASCII 不应抛异常");
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

            assertDoesNotThrow(() -> vis.toAsciiTree(ddlTree), "DDL 的 ASCII 不应抛异常");
            assertDoesNotThrow(() -> vis.toDot(ddlTree), "DDL 的 DOT 不应抛异常");
        }
    }
}
