package com.example.antlr.cli;

import com.example.antlr.SqlParserEngine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;

/**
 * validate 子命令 — 验证 SQL 语法正确性
 */
@Command(
    name = "validate",
    description = "验证 SQL 语句的语法正确性",
    mixinStandardHelpOptions = true
)
public class ValidateCommand implements Runnable {

    @Mixin
    DialectMixin dialectMixin;

    @Parameters(index = "0", description = "SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        boolean valid = engine.validate(sql, dialectMixin.getDialect());

        if (valid) {
            System.out.println("✅ SQL 语法正确 [" + dialectMixin.getDialect().getDisplayName() + "]");
        } else {
            System.out.println("❌ SQL 语法错误 [" + dialectMixin.getDialect().getDisplayName() + "]");
            // 重新 parse 获取错误信息
            var result = engine.parse(sql, dialectMixin.getDialect());
            if (result.getErrorMessage() != null) {
                System.out.println("   " + result.getErrorMessage());
            }
            System.exit(1);
        }
    }
}
