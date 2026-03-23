package com.example.antlr.cli;

import com.example.antlr.SqlDialect;
import com.example.antlr.SqlParserEngine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * visualize 子命令 — 语法树可视化（ASCII/DOT/JSON/HTML）
 */
@Command(
    name = "visualize",
    description = "将 SQL 语法树可视化输出（ASCII/DOT/JSON/HTML）",
    mixinStandardHelpOptions = true
)
public class VisualizeCommand implements Runnable {

    @Mixin
    DialectMixin dialectMixin;

    @Parameters(index = "0", description = "SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Option(
        names = {"-f", "--format"},
        description = "输出格式: ascii (默认), dot, json, html, png, svg",
        defaultValue = "ascii"
    )
    String format;

    @Option(names = {"-o", "--output"}, description = "输出文件路径")
    String outputFile;

    @Option(names = {"-t", "--title"}, description = "HTML 页面标题", defaultValue = "SQL 语法树")
    String title;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();

        // 当前仅 MySQL 方言支持完整可视化
        if (dialectMixin.getDialect() != SqlDialect.MYSQL) {
            System.err.println("⚠️  语法树可视化目前仅支持 MySQL 方言");
            System.exit(1);
        }

        try {
            String result;
            switch (format.toLowerCase()) {
                case "png": {
                    String pngFile = outputFile != null ? outputFile : "parse-tree.png";
                    engine.renderMySqlTreeToPng(sql, pngFile);
                    System.out.println("✅ 语法树 PNG 已写入: " + pngFile);
                    return;
                }
                case "svg": {
                    String svgFile = outputFile != null ? outputFile : "parse-tree.svg";
                    engine.renderMySqlTreeToSvg(sql, svgFile);
                    System.out.println("✅ 语法树 SVG 已写入: " + svgFile);
                    return;
                }
                case "dot":
                    result = engine.visualizeMySqlToDot(sql, null);
                    break;
                case "json":
                    result = engine.visualizeMySqlToJson(sql, null);
                    break;
                case "html":
                    // HTML 需要输出到文件
                    String htmlFile = outputFile != null ? outputFile : "parse-tree.html";
                    engine.visualizeMySqlToHtml(sql, htmlFile, title);
                    System.out.println("✅ HTML 语法树已写入: " + htmlFile);
                    return;
                case "ascii":
                default:
                    result = engine.visualizeMySqlToAscii(sql);
                    break;
            }

            if (outputFile != null) {
                Files.writeString(Path.of(outputFile), result);
                System.out.println("✅ 语法树已写入: " + outputFile);
            } else {
                System.out.println(result);
            }
        } catch (IOException e) {
            throw new RuntimeException("文件操作失败: " + e.getMessage());
        }
    }
}
