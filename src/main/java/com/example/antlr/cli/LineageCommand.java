package com.example.antlr.cli;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.lineage.LineageGraph;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * lineage 子命令 — 列级数据血缘分析
 */
@Command(
    name = "lineage",
    description = "分析 MySQL SQL 的列级数据血缘关系",
    mixinStandardHelpOptions = true
)
public class LineageCommand implements Runnable {

    @Parameters(index = "0", description = "MySQL SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Option(
        names = {"-f", "--format"},
        description = "输出格式: ascii (默认), json, dot, html, png, svg",
        defaultValue = "ascii"
    )
    String format;

    @Option(names = {"-o", "--output"}, description = "输出文件路径（不指定则输出到 stdout，png/svg 格式必须指定）")
    String outputFile;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        LineageGraph graph = engine.analyzeMySqlLineage(sql);

        try {
            switch (format.toLowerCase()) {
                case "png": {
                    String file = outputFile != null ? outputFile : "lineage.png";
                    graph.renderToPng(file);
                    System.out.println("✅ 血缘图 PNG 已写入: " + file);
                    return;
                }
                case "svg": {
                    String file = outputFile != null ? outputFile : "lineage.svg";
                    graph.renderToSvg(file);
                    System.out.println("✅ 血缘图 SVG 已写入: " + file);
                    return;
                }
                default:
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException("渲染失败: " + e.getMessage());
        }

        // 文本格式输出
        String result;
        switch (format.toLowerCase()) {
            case "json":
                result = graph.toGsonJson();
                break;
            case "dot":
                result = graph.toDot();
                break;
            case "html":
                result = graph.toHtml("SQL 血缘分析");
                break;
            case "ascii":
            default:
                result = graph.toAsciiReport();
                break;
        }

        if (outputFile != null) {
            try {
                Files.writeString(Path.of(outputFile), result);
                System.out.println("✅ 血缘分析结果已写入: " + outputFile);
            } catch (IOException e) {
                throw new RuntimeException("无法写入文件: " + outputFile + " — " + e.getMessage());
            }
        } else {
            System.out.println(result);
        }
    }
}
