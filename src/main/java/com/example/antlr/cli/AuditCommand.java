package com.example.antlr.cli;

import com.example.antlr.SqlParserEngine;
import com.example.antlr.mysql.MySqlAuditListener;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

/**
 * audit 子命令 — SQL 审计分析（危险操作检测、表访问追踪）
 */
@Command(
    name = "audit",
    description = "对 MySQL SQL 进行审计分析（危险操作检测/表访问追踪/安全评估）",
    mixinStandardHelpOptions = true
)
public class AuditCommand implements Runnable {

    @Parameters(index = "0", description = "MySQL SQL 语句或 .sql 文件路径")
    String sqlOrFile;

    @Override
    public void run() {
        String sql = ParseCommand.resolveSql(sqlOrFile);
        SqlParserEngine engine = new SqlParserEngine();
        MySqlAuditListener listener = engine.auditMySql(sql);
        System.out.println(listener.getAuditReport());

        // 如果有危险操作，返回非零退出码（可用于 CI/CD 集成）
        if (listener.hasDangerousOperations()) {
            System.exit(2);
        }
    }
}
