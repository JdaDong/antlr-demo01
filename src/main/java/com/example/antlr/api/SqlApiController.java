package com.example.antlr.api;

import com.example.antlr.SqlDialect;
import com.example.antlr.SqlParseResult;
import com.example.antlr.SqlParserEngine;
import com.example.antlr.api.dto.*;
import com.example.antlr.lineage.LineageGraph;
import com.example.antlr.mysql.MySqlAuditListener;
import com.example.antlr.mysql.MySqlMetadataVisitor;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.ContentType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SQL 分析 REST API 控制器
 *
 * 基于 Javalin 框架暴露以下端点：
 * <pre>
 * POST /api/sql/parse       — 解析 SQL 语法树
 * POST /api/sql/validate    — 验证 SQL 语法
 * POST /api/sql/metadata    — 提取 SQL 元数据
 * POST /api/sql/audit       — SQL 审计分析
 * POST /api/sql/format      — SQL 格式化/美化
 * POST /api/sql/lineage     — 列级数据血缘分析（JSON）
 * POST /api/sql/lineage/dot — 血缘图 DOT 格式
 * POST /api/sql/lineage/html— 血缘图 HTML 格式
 * POST /api/sql/lineage/ascii— 血缘图 ASCII 文本
 * POST /api/sql/visualize/ascii — 语法树 ASCII 格式
 * POST /api/sql/visualize/json  — 语法树 JSON 格式
 * POST /api/sql/visualize/dot   — 语法树 DOT 格式
 * GET  /api/health          — 健康检查
 * </pre>
 */
public class SqlApiController {

    private static final Logger log = LoggerFactory.getLogger(SqlApiController.class);

    private final SqlParserEngine engine = new SqlParserEngine();

    /**
     * 注册所有路由
     */
    public void registerRoutes(Javalin app) {
        // ======== 健康检查 ========
        app.get("/api/health", this::health);

        // ======== SQL 解析 ========
        app.post("/api/sql/parse", this::parseSql);
        app.post("/api/sql/validate", this::validateSql);

        // ======== MySQL 专属分析 ========
        app.post("/api/sql/metadata", this::extractMetadata);
        app.post("/api/sql/audit", this::auditSql);
        app.post("/api/sql/format", this::formatSql);

        // ======== 数据血缘 ========
        app.post("/api/sql/lineage", this::analyzeLineage);
        app.post("/api/sql/lineage/dot", this::lineageDot);
        app.post("/api/sql/lineage/html", this::lineageHtml);
        app.post("/api/sql/lineage/ascii", this::lineageAscii);

        // ======== 语法树可视化 ========
        app.post("/api/sql/visualize/ascii", this::visualizeAscii);
        app.post("/api/sql/visualize/json", this::visualizeJson);
        app.post("/api/sql/visualize/dot", this::visualizeDot);

        log.info("✅ 已注册 13 个 REST API 端点");
    }

    // ================================================================
    // 健康检查
    // ================================================================

    private void health(Context ctx) {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("status", "UP");
        info.put("service", "ANTLR SQL Parser API");
        info.put("version", "1.0.0");
        info.put("dialects", new String[]{"mysql", "postgresql", "sparksql"});
        info.put("features", new String[]{
                "parse", "validate", "metadata", "audit",
                "format", "lineage", "visualize"
        });
        info.put("timestamp", System.currentTimeMillis());
        ctx.json(ApiResponse.ok(info));
    }

    // ================================================================
    // SQL 解析
    // ================================================================

    /**
     * POST /api/sql/parse — 解析 SQL 语法树
     */
    private void parseSql(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            SqlDialect dialect = SqlDialect.fromString(req.getDialect());
            SqlParseResult result = engine.parse(req.getSql(), dialect);

            ParseResult dto = new ParseResult(
                    dialect.getDisplayName(), req.getSql(), result.getParseTree(),
                    result.isSuccess(), result.getErrorMessage(), result.getParseTimeMs()
            );

            long cost = System.currentTimeMillis() - start;
            log.info("解析 SQL [{}] 耗时 {}ms, 成功={}", dialect, cost, result.isSuccess());
            ctx.json(ApiResponse.ok(dto, cost));

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/validate — 验证 SQL 语法
     */
    private void validateSql(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            SqlDialect dialect = SqlDialect.fromString(req.getDialect());
            boolean valid = engine.validate(req.getSql(), dialect);

            ValidateResult dto = new ValidateResult(req.getSql(), dialect.getDisplayName(), valid);

            long cost = System.currentTimeMillis() - start;
            log.info("验证 SQL [{}] 耗时 {}ms, valid={}", dialect, cost, valid);
            ctx.json(ApiResponse.ok(dto, cost));

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // MySQL 元数据提取
    // ================================================================

    /**
     * POST /api/sql/metadata — 提取 MySQL SQL 元数据
     */
    private void extractMetadata(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            MySqlMetadataVisitor visitor = engine.extractMySqlMetadata(req.getSql());

            MetadataResult dto = new MetadataResult();
            dto.setStatementType(visitor.getStatementType());
            dto.setTables(visitor.getTables());
            dto.setColumns(visitor.getColumns());
            dto.setAliases(visitor.getAliases());
            dto.setFunctions(visitor.getFunctions());
            dto.setWhereColumns(visitor.getWhereColumns());
            dto.setJoinConditions(visitor.getJoinConditions());
            dto.setOrderByColumns(visitor.getOrderByColumns());
            dto.setGroupByColumns(visitor.getGroupByColumns());
            dto.setHasSubQuery(visitor.isHasSubQuery());
            dto.setHasDistinct(visitor.isHasDistinct());
            dto.setHasUnion(visitor.isHasUnion());
            dto.setSummary(visitor.getSummary());

            long cost = System.currentTimeMillis() - start;
            log.info("元数据提取 [{}] 耗时 {}ms, 表={}, 列={}",
                    visitor.getStatementType(), cost,
                    visitor.getTables().size(), visitor.getColumns().size());
            ctx.json(ApiResponse.ok(dto, cost));

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // MySQL 审计
    // ================================================================

    /**
     * POST /api/sql/audit — SQL 审计分析
     */
    private void auditSql(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            MySqlAuditListener listener = engine.auditMySql(req.getSql());

            AuditResult dto = new AuditResult();
            dto.setStatementType(listener.getCurrentStatementType());
            dto.setEvents(listener.getEvents().stream()
                    .map(e -> new AuditResult.AuditEventDto(
                            e.getType(), e.getDetail(), e.getLine(), e.getColumn()))
                    .collect(Collectors.toList()));
            dto.setTableAccess(listener.getTableAccess());
            dto.setWarnings(listener.getWarnings());
            dto.setErrors(listener.getErrors());
            dto.setHasDangerousOperations(listener.hasDangerousOperations());
            dto.setReport(listener.getAuditReport());

            long cost = System.currentTimeMillis() - start;
            log.info("审计 SQL [{}] 耗时 {}ms, 事件={}, 告警={}",
                    listener.getCurrentStatementType(), cost,
                    listener.getEvents().size(), listener.getWarnings().size());
            ctx.json(ApiResponse.ok(dto, cost));

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // MySQL 格式化
    // ================================================================

    /**
     * POST /api/sql/format — SQL 格式化/美化
     */
    private void formatSql(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            String formatted = engine.formatMySql(
                    req.getSql(),
                    req.getIndentSize(),
                    req.isUppercaseKeywords()
            );

            FormatResult dto = new FormatResult(req.getSql(), formatted);

            long cost = System.currentTimeMillis() - start;
            log.info("格式化 SQL 耗时 {}ms", cost);
            ctx.json(ApiResponse.ok(dto, cost));

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // 数据血缘
    // ================================================================

    /**
     * POST /api/sql/lineage — 列级数据血缘分析（JSON）
     */
    private void analyzeLineage(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            LineageGraph graph = engine.analyzeMySqlLineage(req.getSql());

            // 使用 LineageGraph 内置的 JSON 序列化
            String json = graph.toGsonJson();

            long cost = System.currentTimeMillis() - start;
            log.info("血缘分析 [{}] 耗时 {}ms, 边={}, 表={}",
                    graph.getStatementType(), cost,
                    graph.getEdges().size(), graph.getTables().size());
            ctx.contentType(ContentType.APPLICATION_JSON).result(
                    "{\"success\":true,\"costMs\":" + cost +
                    ",\"timestamp\":" + System.currentTimeMillis() +
                    ",\"data\":" + json + "}"
            );

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/lineage/dot — 血缘 DOT 格式（Graphviz）
     */
    private void lineageDot(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            LineageGraph graph = engine.analyzeMySqlLineage(req.getSql());
            String dot = graph.toDot();

            long cost = System.currentTimeMillis() - start;
            log.info("血缘 DOT 生成 耗时 {}ms", cost);
            ctx.contentType("text/vnd.graphviz").result(dot);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/lineage/html — 血缘 HTML 交互式可视化
     */
    private void lineageHtml(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            LineageGraph graph = engine.analyzeMySqlLineage(req.getSql());
            String html = graph.toHtml(req.getTitle());

            long cost = System.currentTimeMillis() - start;
            log.info("血缘 HTML 生成 耗时 {}ms", cost);
            ctx.contentType(ContentType.TEXT_HTML).result(html);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/lineage/ascii — 血缘 ASCII 文本报告
     */
    private void lineageAscii(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            LineageGraph graph = engine.analyzeMySqlLineage(req.getSql());
            String ascii = graph.toAsciiReport();

            long cost = System.currentTimeMillis() - start;
            log.info("血缘 ASCII 生成 耗时 {}ms", cost);
            ctx.contentType(ContentType.TEXT_PLAIN).result(ascii);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // 语法树可视化
    // ================================================================

    /**
     * POST /api/sql/visualize/ascii — 语法树 ASCII 格式
     */
    private void visualizeAscii(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            String ascii = engine.visualizeMySqlToAscii(req.getSql());

            long cost = System.currentTimeMillis() - start;
            log.info("语法树 ASCII 耗时 {}ms", cost);
            ctx.contentType(ContentType.TEXT_PLAIN).result(ascii);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/visualize/json — 语法树 JSON 格式
     */
    private void visualizeJson(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            String json = engine.visualizeMySqlToJson(req.getSql(), null);

            long cost = System.currentTimeMillis() - start;
            log.info("语法树 JSON 耗时 {}ms", cost);
            ctx.contentType(ContentType.APPLICATION_JSON).result(json);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    /**
     * POST /api/sql/visualize/dot — 语法树 DOT 格式
     */
    private void visualizeDot(Context ctx) {
        long start = System.currentTimeMillis();
        try {
            SqlRequest req = ctx.bodyAsClass(SqlRequest.class);
            validateRequest(req);

            String dot = engine.visualizeMySqlToDot(req.getSql(), null);

            long cost = System.currentTimeMillis() - start;
            log.info("语法树 DOT 耗时 {}ms", cost);
            ctx.contentType("text/vnd.graphviz").result(dot);

        } catch (Exception e) {
            handleError(ctx, e, start);
        }
    }

    // ================================================================
    // 工具方法
    // ================================================================

    private void validateRequest(SqlRequest req) {
        if (req == null || req.getSql() == null || req.getSql().trim().isEmpty()) {
            throw new IllegalArgumentException("SQL 不能为空");
        }
    }

    private void handleError(Context ctx, Exception e, long start) {
        long cost = System.currentTimeMillis() - start;
        String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName();
        log.error("API 处理失败: {} ({}ms)", msg, cost, e);
        ctx.status(400).json(ApiResponse.fail(msg, cost));
    }
}
