package com.example.antlr.cli;

import com.example.antlr.SqlDialect;
import com.example.antlr.diff.SqlDiffEngine;
import com.example.antlr.diff.SqlDiffEngine.DiffMode;
import com.example.antlr.diff.SqlDiffEngine.SqlDiffResult;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * diff 子命令 — 比较两条 SQL 语句的语法树差异
 *
 * 使用方式:
 *   sql-parser diff "SELECT * FROM a" "SELECT * FROM b"
 *   sql-parser diff -d postgresql -m semantic "SELECT a.id FROM t1" "SELECT b.id FROM t2"
 *   sql-parser diff -m structure "SELECT 1" "SELECT 2"
 */
@Command(
    name = "diff",
    description = "比较两条 SQL 语句的语法树差异",
    mixinStandardHelpOptions = true
)
public class DiffCommand implements Runnable {

    @Mixin
    DialectMixin dialectMixin;

    @Parameters(index = "0", description = "第一条 SQL 语句或 .sql 文件路径")
    String sqlOrFile1;

    @Parameters(index = "1", description = "第二条 SQL 语句或 .sql 文件路径")
    String sqlOrFile2;

    @Option(names = {"-m", "--mode"}, description = "比较模式: structure/full/semantic (默认: full)", defaultValue = "full")
    String mode;

    @Option(names = {"-o", "--output"}, description = "输出文件路径")
    String outputFile;

    @Override
    public void run() {
        String sql1 = ParseCommand.resolveSql(sqlOrFile1);
        String sql2 = ParseCommand.resolveSql(sqlOrFile2);
        SqlDialect dialect = dialectMixin.getDialect();

        DiffMode diffMode;
        switch (mode.toLowerCase()) {
            case "structure": diffMode = DiffMode.STRUCTURE; break;
            case "semantic": diffMode = DiffMode.SEMANTIC; break;
            default: diffMode = DiffMode.FULL; break;
        }

        SqlDiffEngine diffEngine = new SqlDiffEngine();
        SqlDiffResult result = diffEngine.diff(sql1, sql2, dialect, diffMode);

        String report = result.getReport();

        if (outputFile != null) {
            try {
                Files.writeString(Path.of(outputFile), report);
                System.out.println("✅ Diff 报告已写入: " + outputFile);
            } catch (IOException e) {
                throw new RuntimeException("无法写入文件: " + outputFile + " — " + e.getMessage());
            }
        } else {
            System.out.println(report);
        }
    }
}
