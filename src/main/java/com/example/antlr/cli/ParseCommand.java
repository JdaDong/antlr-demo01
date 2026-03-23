package com.example.antlr.cli;

import com.example.antlr.SqlParseResult;
import com.example.antlr.SqlParserEngine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * parse 子命令 — 解析 SQL 并输出语法树
 */
@Command(
    name = "parse",
    description = "解析 SQL 语句，输出语法树",
    mixinStandardHelpOptions = true
)
public class ParseCommand implements Runnable {

    @Mixin
    DialectMixin dialectMixin;

    @Parameters(
        index = "0",
        description = "SQL 语句或 SQL 文件路径（以 .sql 结尾则视为文件）"
    )
    String sqlOrFile;

    @Override
    public void run() {
        String sql = resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        SqlParseResult result = engine.parse(sql, dialectMixin.getDialect());
        System.out.println(result);
    }

    static String resolveSql(String sqlOrFile) {
        if (sqlOrFile.endsWith(".sql")) {
            try {
                return Files.readString(Path.of(sqlOrFile));
            } catch (IOException e) {
                throw new RuntimeException("无法读取文件: " + sqlOrFile + " — " + e.getMessage());
            }
        }
        return sqlOrFile;
    }
}
