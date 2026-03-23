package com.example.antlr.cli;

import com.example.antlr.SqlDialect;
import com.example.antlr.SqlParseResult;
import com.example.antlr.SqlParserEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * batch 子命令 — 批量解析 .sql 文件
 *
 * 支持功能：
 * - 解析单个 .sql 文件中的多条语句（分号分割）
 * - 递归扫描目录中的所有 .sql 文件
 * - 批量验证、格式化、元数据提取
 * - 汇总报告输出
 *
 * 使用方式:
 *   sql-parser batch /path/to/file.sql
 *   sql-parser batch /path/to/sql/dir
 *   sql-parser batch -d postgresql --action format /path/to/dir
 *   sql-parser batch --action validate /path/to/file.sql
 */
@Command(
    name = "batch",
    description = "批量解析 .sql 文件（支持文件或目录）",
    mixinStandardHelpOptions = true
)
public class BatchCommand implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(BatchCommand.class);

    @Mixin
    DialectMixin dialectMixin;

    @Parameters(index = "0", description = ".sql 文件路径或包含 .sql 文件的目录")
    String pathArg;

    @Option(names = {"-a", "--action"}, description = "批处理动作: parse/validate/format/metadata/audit (默认: validate)", defaultValue = "validate")
    String action;

    @Option(names = {"-r", "--recursive"}, description = "递归扫描子目录", defaultValue = "true")
    boolean recursive;

    @Option(names = {"-o", "--output"}, description = "输出目录（格式化结果写入）")
    String outputDir;

    @Option(names = {"--stop-on-error"}, description = "遇到错误时停止")
    boolean stopOnError;

    @Option(names = {"--summary-only"}, description = "只输出汇总报告（不显示单条结果）")
    boolean summaryOnly;

    @Override
    public void run() {
        Path inputPath = Path.of(pathArg);

        if (!Files.exists(inputPath)) {
            log.error("路径不存在: {}", pathArg);
            System.err.println("❌ 路径不存在: " + pathArg);
            return;
        }

        List<Path> sqlFiles = collectSqlFiles(inputPath);
        if (sqlFiles.isEmpty()) {
            log.warn("未找到 .sql 文件: {}", pathArg);
            System.err.println("⚠️ 未找到 .sql 文件: " + pathArg);
            return;
        }

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║            SQL 批量处理报告                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
        System.out.println("📂 输入路径: " + pathArg);
        System.out.println("📁 .sql 文件数: " + sqlFiles.size());
        System.out.println("🔧 方言: " + dialectMixin.getDialect());
        System.out.println("🎯 动作: " + action);
        System.out.println();

        SqlParserEngine engine = new SqlParserEngine();
        SqlDialect dialect = dialectMixin.getDialect();

        int totalStatements = 0;
        int successCount = 0;
        int errorCount = 0;
        List<String> errorMessages = new ArrayList<>();
        long totalTime = 0;

        for (Path sqlFile : sqlFiles) {
            if (!summaryOnly) {
                System.out.println("━━━ 📄 " + sqlFile.getFileName() + " ━━━");
            }

            String content;
            try {
                content = Files.readString(sqlFile);
            } catch (IOException e) {
                String msg = "❌ 无法读取文件: " + sqlFile + " — " + e.getMessage();
                errorMessages.add(msg);
                if (!summaryOnly) System.out.println(msg);
                errorCount++;
                if (stopOnError) break;
                continue;
            }

            // 按分号分割语句（简单分割，忽略空语句）
            List<String> statements = splitStatements(content);
            totalStatements += statements.size();

            for (int i = 0; i < statements.size(); i++) {
                String sql = statements.get(i).trim();
                if (sql.isEmpty()) continue;

                long start = System.currentTimeMillis();
                try {
                    String result = processStatement(engine, dialect, sql);
                    long elapsed = System.currentTimeMillis() - start;
                    totalTime += elapsed;
                    successCount++;

                    if (!summaryOnly) {
                        System.out.println("  ✅ 语句 #" + (i + 1) + " (" + elapsed + "ms)");
                        if (result != null && !result.isEmpty()) {
                            // 缩进输出
                            for (String line : result.split("\n")) {
                                System.out.println("     " + line);
                            }
                        }
                    }
                } catch (Exception e) {
                    long elapsed = System.currentTimeMillis() - start;
                    totalTime += elapsed;
                    errorCount++;
                    String truncSql = sql.length() > 80 ? sql.substring(0, 80) + "..." : sql;
                    String errMsg = sqlFile.getFileName() + " 语句 #" + (i + 1) + ": " + e.getMessage() + " — SQL: " + truncSql;
                    errorMessages.add(errMsg);

                    if (!summaryOnly) {
                        System.out.println("  ❌ 语句 #" + (i + 1) + " — " + e.getMessage());
                    }

                    if (stopOnError) break;
                }
            }

            if (!summaryOnly) System.out.println();
            if (stopOnError && errorCount > 0) break;
        }

        // 汇总报告
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                    汇总报告                           ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println("📁 文件数: " + sqlFiles.size());
        System.out.println("📝 语句总数: " + totalStatements);
        System.out.println("✅ 成功: " + successCount);
        System.out.println("❌ 失败: " + errorCount);
        System.out.println("⏱️ 总耗时: " + totalTime + "ms");
        if (totalStatements > 0) {
            System.out.println("📊 成功率: " + String.format("%.1f%%", successCount * 100.0 / (successCount + errorCount)));
        }

        if (!errorMessages.isEmpty()) {
            System.out.println("\n⚠️ 错误列表:");
            for (int i = 0; i < errorMessages.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + errorMessages.get(i));
            }
        }
    }

    /**
     * 处理单条 SQL 语句
     */
    private String processStatement(SqlParserEngine engine, SqlDialect dialect, String sql) {
        switch (action.toLowerCase()) {
            case "parse": {
                SqlParseResult result = engine.parse(sql, dialect);
                if (!result.isSuccess()) {
                    throw new RuntimeException(result.getErrorMessage());
                }
                return result.getParseTree();
            }
            case "validate": {
                boolean valid = engine.validate(sql, dialect);
                if (!valid) {
                    throw new RuntimeException("验证失败");
                }
                return null;
            }
            case "format": {
                String formatted;
                switch (dialect) {
                    case MYSQL: formatted = engine.formatMySql(sql); break;
                    case POSTGRESQL: formatted = engine.formatPostgreSql(sql); break;
                    case SPARKSQL: formatted = engine.formatSparkSql(sql); break;
                    default: throw new IllegalArgumentException("不支持的方言: " + dialect);
                }
                if (outputDir != null) {
                    // 写入输出目录的逻辑由调用方处理
                    return formatted;
                }
                return formatted;
            }
            case "metadata": {
                switch (dialect) {
                    case MYSQL: return engine.extractMySqlMetadata(sql).toString();
                    case POSTGRESQL: return engine.extractPostgreSqlMetadata(sql).toString();
                    case SPARKSQL: return engine.extractSparkSqlMetadata(sql).toString();
                    default: throw new IllegalArgumentException("不支持的方言: " + dialect);
                }
            }
            case "audit": {
                switch (dialect) {
                    case MYSQL: return engine.auditMySql(sql).toString();
                    case POSTGRESQL: return engine.auditPostgreSql(sql).toString();
                    case SPARKSQL: return engine.auditSparkSql(sql).toString();
                    default: throw new IllegalArgumentException("不支持的方言: " + dialect);
                }
            }
            default:
                throw new IllegalArgumentException("不支持的动作: " + action);
        }
    }

    /**
     * 收集所有 .sql 文件
     */
    private List<Path> collectSqlFiles(Path path) {
        if (Files.isRegularFile(path) && path.toString().endsWith(".sql")) {
            return Collections.singletonList(path);
        }

        if (Files.isDirectory(path)) {
            try (Stream<Path> stream = recursive ? Files.walk(path) : Files.list(path)) {
                return stream
                        .filter(p -> Files.isRegularFile(p) && p.toString().endsWith(".sql"))
                        .sorted()
                        .collect(Collectors.toList());
            } catch (IOException e) {
                System.err.println("⚠️ 扫描目录失败: " + e.getMessage());
                return Collections.emptyList();
            }
        }

        return Collections.emptyList();
    }

    /**
     * 按分号分割 SQL 语句
     * 简单实现：忽略字符串内的分号
     */
    static List<String> splitStatements(String content) {
        List<String> statements = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inSingleQuote = false;
        boolean inDoubleQuote = false;
        boolean inLineComment = false;
        boolean inBlockComment = false;

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            char next = i + 1 < content.length() ? content.charAt(i + 1) : 0;

            // 行注释
            if (!inSingleQuote && !inDoubleQuote && !inBlockComment && c == '-' && next == '-') {
                inLineComment = true;
                current.append(c);
                continue;
            }
            if (inLineComment) {
                if (c == '\n') inLineComment = false;
                current.append(c);
                continue;
            }

            // 块注释
            if (!inSingleQuote && !inDoubleQuote && !inLineComment && c == '/' && next == '*') {
                inBlockComment = true;
                current.append(c);
                continue;
            }
            if (inBlockComment) {
                current.append(c);
                if (c == '*' && next == '/') {
                    current.append(next);
                    i++;
                    inBlockComment = false;
                }
                continue;
            }

            // 字符串
            if (!inDoubleQuote && c == '\'') {
                inSingleQuote = !inSingleQuote;
            }
            if (!inSingleQuote && c == '"') {
                inDoubleQuote = !inDoubleQuote;
            }

            if (!inSingleQuote && !inDoubleQuote && c == ';') {
                String stmt = current.toString().trim();
                if (!stmt.isEmpty()) statements.add(stmt);
                current.setLength(0);
            } else {
                current.append(c);
            }
        }

        // 最后一条语句（可能没有分号结尾）
        String lastStmt = current.toString().trim();
        if (!lastStmt.isEmpty()) statements.add(lastStmt);

        return statements;
    }
}
