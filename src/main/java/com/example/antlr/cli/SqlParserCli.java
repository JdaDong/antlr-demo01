package com.example.antlr.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * ANTLR SQL Parser 命令行工具（基于 Picocli）
 *
 * 使用方式:
 *   sql-parser parse   -d mysql "SELECT * FROM users"
 *   sql-parser format  -d mysql "select * from users"
 *   sql-parser validate -d mysql "SELECT * FROM users"
 *   sql-parser metadata "SELECT u.name FROM users u"
 *   sql-parser audit   "DELETE FROM users"
 *   sql-parser lineage "SELECT a.id, b.name FROM t1 a JOIN t2 b ON a.id=b.id"
 *   sql-parser visualize -f ascii "SELECT * FROM users"
 *   sql-parser server  -p 7070
 */
@Command(
    name = "sql-parser",
    mixinStandardHelpOptions = true,
    version = "ANTLR SQL Parser 1.0",
    description = "多方言 SQL 解析器命令行工具 — 支持 MySQL / PostgreSQL / SparkSQL",
    subcommands = {
        ParseCommand.class,
        ValidateCommand.class,
        MetadataCommand.class,
        AuditCommand.class,
        FormatCommand.class,
        LineageCommand.class,
        VisualizeCommand.class,
        DiffCommand.class,
        BatchCommand.class,
        ServerCommand.class,
        CommandLine.HelpCommand.class
    },
    footer = {
        "",
        "示例:",
        "  sql-parser parse -d mysql \"SELECT * FROM users WHERE id = 1\"",
        "  sql-parser format \"select id,name from users where age>18\"",
        "  sql-parser audit \"DELETE FROM users\"",
        "  sql-parser lineage -f html -o lineage.html \"SELECT a.id FROM t1 a JOIN t2 b ON a.id=b.id\"",
        "  sql-parser visualize -f ascii \"SELECT * FROM users\"",
        "  sql-parser server -p 8080",
        ""
    }
)
public class SqlParserCli implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "显示详细输出")
    boolean verbose;

    @Override
    public void run() {
        // 未指定子命令时，显示帮助信息和 banner
        System.out.println();
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║         ANTLR SQL Parser (多方言支持) v1.0            ║");
        System.out.println("║   支持: MySQL | PostgreSQL | SparkSQL                 ║");
        System.out.println("║   基于 Picocli 命令行框架                              ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();
        System.out.println("使用 sql-parser help 查看所有命令");
        System.out.println("使用 sql-parser <command> --help 查看具体命令帮助");
        System.out.println();

        new CommandLine(this).usage(System.out);
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new SqlParserCli())
                .setExecutionExceptionHandler((ex, cmd, parseResult) -> {
                    System.err.println("❌ 错误: " + ex.getMessage());
                    if (cmd.getParent() != null) {
                        // 查找是否有 verbose 标志
                        SqlParserCli parent = cmd.getParent().getCommand();
                        if (parent.verbose) {
                            ex.printStackTrace(System.err);
                        }
                    }
                    return 1;
                })
                .execute(args);
        System.exit(exitCode);
    }
}
