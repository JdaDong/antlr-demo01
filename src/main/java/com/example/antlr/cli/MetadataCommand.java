package com.example.antlr.cli;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.mysql.MySqlMetadataVisitor;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * metadata 子命令 — 提取 SQL 元数据（表名、列名、别名、函数等）
 */
@Command(
    name = "metadata",
    description = "提取 MySQL SQL 的元数据信息（表名/列名/别名/函数/SQL类型等）",
    mixinStandardHelpOptions = true
)
public class MetadataCommand implements Runnable {

    @Parameters(index = "0", description = "MySQL SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(sql);
        System.out.println(visitor.getSummary());
    }
}
