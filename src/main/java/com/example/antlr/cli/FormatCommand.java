package com.example.antlr.cli;

import com.example.antlr.SqlParserEngine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * format 子命令 — SQL 格式化/美化
 */
@Command(
    name = "format",
    description = "格式化/美化 MySQL SQL 语句",
    mixinStandardHelpOptions = true
)
public class FormatCommand implements Runnable {

    @Parameters(index = "0", description = "MySQL SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Option(names = {"-i", "--indent"}, description = "缩进空格数 (默认: 4)", defaultValue = "4")
    int indentSize;

    @Option(names = {"-l", "--lowercase"}, description = "关键字小写 (默认大写)")
    boolean lowercaseKeywords;

    @Option(names = {"-o", "--output"}, description = "输出文件路径（不指定则输出到 stdout）")
    String outputFile;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        String formatted = engine.formatMySql(sql, indentSize, !lowercaseKeywords);

        if (outputFile != null) {
            try {
                Files.writeString(Path.of(outputFile), formatted);
                System.out.println("✅ 格式化结果已写入: " + outputFile);
            } catch (IOException e) {
                throw new RuntimeException("无法写入文件: " + outputFile + " — " + e.getMessage());
            }
        } else {
            System.out.println(formatted);
        }
    }
}
