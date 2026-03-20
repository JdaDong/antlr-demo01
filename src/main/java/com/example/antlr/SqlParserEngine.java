package com.example.antlr;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Trees;

import com.example.antlr.mysql.MySqlLexer;
import com.example.antlr.mysql.MySqlParser;
import com.example.antlr.mysql.MySqlMetadataVisitor;
import com.example.antlr.mysql.MySqlAuditListener;
import com.example.antlr.mysql.MySqlFormatter;
import com.example.antlr.lineage.LineageGraph;
import com.example.antlr.lineage.MySqlLineageVisitor;
import com.example.antlr.visual.ParseTreeVisualizer;
import com.example.antlr.postgresql.PostgreSqlLexer;
import com.example.antlr.postgresql.PostgreSqlParser;
import com.example.antlr.sparksql.SparkSqlLexer;
import com.example.antlr.sparksql.SparkSqlParser;

import java.io.IOException;
import java.util.Arrays;

/**
 * 统一的 SQL 解析引擎
 * 支持 MySQL、PostgreSQL、SparkSQL 三种方言
 */
public class SqlParserEngine {

    /**
     * 解析 SQL 语句
     *
     * @param sql     SQL 语句
     * @param dialect SQL 方言
     * @return 解析结果
     */
    public SqlParseResult parse(String sql, SqlDialect dialect) {
        long start = System.currentTimeMillis();
        try {
            ParseTree tree;
            Parser parser;
            SqlErrorListener errorListener = new SqlErrorListener();

            switch (dialect) {
                case MYSQL:
                    MySqlLexer mysqlLexer = new MySqlLexer(CharStreams.fromString(sql));
                    mysqlLexer.removeErrorListeners();
                    mysqlLexer.addErrorListener(errorListener);
                    CommonTokenStream mysqlTokens = new CommonTokenStream(mysqlLexer);
                    MySqlParser mysqlParser = new MySqlParser(mysqlTokens);
                    mysqlParser.removeErrorListeners();
                    mysqlParser.addErrorListener(errorListener);
                    tree = mysqlParser.root();
                    parser = mysqlParser;
                    break;

                case POSTGRESQL:
                    PostgreSqlLexer pgLexer = new PostgreSqlLexer(CharStreams.fromString(sql));
                    pgLexer.removeErrorListeners();
                    pgLexer.addErrorListener(errorListener);
                    CommonTokenStream pgTokens = new CommonTokenStream(pgLexer);
                    PostgreSqlParser pgParser = new PostgreSqlParser(pgTokens);
                    pgParser.removeErrorListeners();
                    pgParser.addErrorListener(errorListener);
                    tree = pgParser.root();
                    parser = pgParser;
                    break;

                case SPARKSQL:
                    SparkSqlLexer sparkLexer = new SparkSqlLexer(CharStreams.fromString(sql));
                    sparkLexer.removeErrorListeners();
                    sparkLexer.addErrorListener(errorListener);
                    CommonTokenStream sparkTokens = new CommonTokenStream(sparkLexer);
                    SparkSqlParser sparkParser = new SparkSqlParser(sparkTokens);
                    sparkParser.removeErrorListeners();
                    sparkParser.addErrorListener(errorListener);
                    tree = sparkParser.root();
                    parser = sparkParser;
                    break;

                default:
                    throw new IllegalArgumentException("不支持的 SQL 方言: " + dialect);
            }

            long elapsed = System.currentTimeMillis() - start;

            if (errorListener.hasErrors()) {
                return new SqlParseResult.Builder()
                        .dialect(dialect)
                        .originalSql(sql)
                        .success(false)
                        .errorMessage(errorListener.getErrorsAsString())
                        .parseTimeMs(elapsed)
                        .build();
            }

            String treeStr = Trees.toStringTree(tree, Arrays.asList(parser.getRuleNames()));
            String prettyTree = prettyPrintTree(treeStr);

            return new SqlParseResult.Builder()
                    .dialect(dialect)
                    .originalSql(sql)
                    .parseTree(prettyTree)
                    .success(true)
                    .parseTimeMs(elapsed)
                    .build();

        } catch (Exception e) {
            long elapsed = System.currentTimeMillis() - start;
            return new SqlParseResult.Builder()
                    .dialect(dialect)
                    .originalSql(sql)
                    .success(false)
                    .errorMessage(e.getMessage())
                    .parseTimeMs(elapsed)
                    .build();
        }
    }

    /**
     * 验证 SQL 语句是否合法
     */
    public boolean validate(String sql, SqlDialect dialect) {
        return parse(sql, dialect).isSuccess();
    }

    // ============================================================
    // Visitor 模式：提取 MySQL SQL 元数据
    // ============================================================

    /**
     * 使用 Visitor 模式提取 MySQL SQL 的元数据信息
     * （表名、列名、别名、SQL 类型、函数调用等）
     *
     * @param sql MySQL SQL 语句
     * @return MySqlMetadataVisitor 包含提取到的所有元数据
     */
    public MySqlMetadataVisitor extractMySqlMetadata(String sql) {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();

        ParseTree tree = parser.root();

        // Visitor 模式：手动调用 visit()，由你控制遍历
        MySqlMetadataVisitor visitor = new MySqlMetadataVisitor();
        visitor.visit(tree);

        return visitor;
    }

    // ============================================================
    // Listener 模式：MySQL SQL 审计
    // ============================================================

    /**
     * 使用 Listener 模式对 MySQL SQL 进行审计分析
     * （表访问追踪、危险操作检测、优化建议等）
     *
     * @param sql MySQL SQL 语句
     * @return MySqlAuditListener 包含审计事件和告警信息
     */
    public MySqlAuditListener auditMySql(String sql) {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();

        ParseTree tree = parser.root();

        // Listener 模式：Walker 自动遍历整棵树，你只需要实现 enter/exit 回调
        MySqlAuditListener listener = new MySqlAuditListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        return listener;
    }

    // ============================================================
    // Visitor 模式：MySQL SQL 格式化/美化
    // ============================================================

    /**
     * 使用 Visitor 模式将 MySQL SQL 格式化为美观的标准缩进格式
     *
     * @param sql MySQL SQL 语句（可以是杂乱无章的）
     * @return 格式化后的 SQL 字符串
     */
    public String formatMySql(String sql) {
        return formatMySql(sql, 4, true);
    }

    /**
     * 使用 Visitor 模式将 MySQL SQL 格式化为美观的标准缩进格式
     *
     * @param sql               MySQL SQL 语句
     * @param indentSize        缩进空格数
     * @param uppercaseKeywords 关键字是否大写
     * @return 格式化后的 SQL 字符串
     */
    public String formatMySql(String sql, int indentSize, boolean uppercaseKeywords) {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();

        ParseTree tree = parser.root();

        MySqlFormatter formatter = new MySqlFormatter(indentSize, uppercaseKeywords);
        return formatter.visit(tree);
    }

    // ============================================================
    // 语法树可视化
    // ============================================================

    /**
     * 使用 Visitor 模式分析 MySQL SQL 的数据血缘（列级 Data Lineage）
     *
     * @param sql MySQL SQL 语句
     * @return LineageGraph 包含完整的列级血缘关系图
     */
    public LineageGraph analyzeMySqlLineage(String sql) {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();

        ParseTree tree = parser.root();

        MySqlLineageVisitor visitor = new MySqlLineageVisitor();
        visitor.visit(tree);

        LineageGraph graph = visitor.getLineageGraph();
        graph.setSql(sql);
        return graph;
    }

    // ============================================================
    // 语法树可视化（已有）
    // ============================================================

    /**
     * 将 MySQL SQL 的语法树导出为交互式 HTML 文件
     *
     * @param sql      MySQL SQL 语句
     * @param filePath 输出 HTML 文件路径
     * @param title    页面标题
     */
    public void visualizeMySqlToHtml(String sql, String filePath, String title) throws IOException {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        ParseTree tree = parser.root();

        ParseTreeVisualizer visualizer = new ParseTreeVisualizer(parser);
        visualizer.toHtmlFile(tree, filePath, title);
    }

    /**
     * 将 MySQL SQL 的语法树导出为 DOT 格式（Graphviz）
     *
     * @param sql      MySQL SQL 语句
     * @param filePath 输出 .dot 文件路径（可选，null 则只返回字符串）
     * @return DOT 格式字符串
     */
    public String visualizeMySqlToDot(String sql, String filePath) throws IOException {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        ParseTree tree = parser.root();

        ParseTreeVisualizer visualizer = new ParseTreeVisualizer(parser);
        if (filePath != null) {
            visualizer.toDotFile(tree, filePath);
        }
        return visualizer.toDot(tree);
    }

    /**
     * 将 MySQL SQL 的语法树导出为 JSON 格式
     *
     * @param sql      MySQL SQL 语句
     * @param filePath 输出 .json 文件路径（可选，null 则只返回字符串）
     * @return JSON 格式字符串
     */
    public String visualizeMySqlToJson(String sql, String filePath) throws IOException {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        ParseTree tree = parser.root();

        ParseTreeVisualizer visualizer = new ParseTreeVisualizer(parser);
        if (filePath != null) {
            visualizer.toJsonFile(tree, filePath);
        }
        return visualizer.toJson(tree);
    }

    /**
     * 将 MySQL SQL 的语法树导出为 ASCII 树形文本（终端友好）
     *
     * @param sql MySQL SQL 语句
     * @return ASCII 树形文本
     */
    public String visualizeMySqlToAscii(String sql) {
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        ParseTree tree = parser.root();

        ParseTreeVisualizer visualizer = new ParseTreeVisualizer(parser);
        return visualizer.toAsciiTree(tree);
    }

    /**
     * 格式化语法树输出
     */
    private String prettyPrintTree(String tree) {
        StringBuilder sb = new StringBuilder();
        int indent = 0;
        for (char c : tree.toCharArray()) {
            if (c == '(') {
                sb.append('\n');
                for (int i = 0; i < indent; i++) sb.append("  ");
                sb.append(c);
                indent++;
            } else if (c == ')') {
                indent--;
                sb.append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().trim();
    }
}
