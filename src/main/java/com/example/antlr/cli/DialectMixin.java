package com.example.antlr.cli;

import com.example.antlr.SqlDialect;
import picocli.CommandLine.Option;

/**
 * 方言选项 Mixin — 供子命令复用
 */
public class DialectMixin {

    @Option(
        names = {"-d", "--dialect"},
        description = "SQL 方言: mysql (默认), postgresql/pg, sparksql/spark",
        defaultValue = "mysql"
    )
    String dialect = "mysql";

    public SqlDialect getDialect() {
        return SqlDialect.fromString(dialect);
    }
}
