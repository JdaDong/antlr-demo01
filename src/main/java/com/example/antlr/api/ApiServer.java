package com.example.antlr.api;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SQL 分析 REST API 服务器
 *
 * 基于 Javalin 的轻量级 HTTP 服务，暴露 SQL 分析能力为 REST API。
 *
 * <pre>
 * 启动方式:
 *   mvn exec:java -Dexec.mainClass="com.example.antlr.api.ApiServer"
 *
 * 默认端口: 7070
 * 自定义端口: mvn exec:java -Dexec.mainClass="com.example.antlr.api.ApiServer" -Dexec.args="8080"
 *
 * API 文档: http://localhost:7070/
 * 健康检查: http://localhost:7070/api/health
 * </pre>
 */
public class ApiServer {

    private static final Logger log = LoggerFactory.getLogger(ApiServer.class);

    private static final int DEFAULT_PORT = 7070;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                log.warn("无效的端口号 '{}', 使用默认端口 {}", args[0], DEFAULT_PORT);
            }
        }

        start(port);
    }

    /**
     * 启动 API 服务器
     */
    public static Javalin start(int port) {
        Javalin app = Javalin.create(config -> {
            // 启用 CORS
            config.plugins.enableCors(cors -> cors.add(it -> it.anyHost()));

            // 请求/响应日志
            config.plugins.enableDevLogging();

            // JSON 序列化配置（使用 Jackson）
            config.jsonMapper(new io.javalin.json.JavalinJackson());

            // 静态文件（API 文档页面）
            config.staticFiles.add(staticFiles -> {
                staticFiles.directory = "/static";
                staticFiles.location = Location.CLASSPATH;
                staticFiles.hostedPath = "/";
            });
        });

        // 全局异常处理
        app.exception(Exception.class, (e, ctx) -> {
            log.error("未处理异常: {}", e.getMessage(), e);
            ctx.status(500).json(java.util.Map.of(
                    "success", false,
                    "error", e.getMessage() != null ? e.getMessage() : "Internal Server Error"
            ));
        });

        // 注册 API 路由
        SqlApiController controller = new SqlApiController();
        controller.registerRoutes(app);

        // 启动服务器
        app.start(port);

        log.info("╔══════════════════════════════════════════════════════╗");
        log.info("║       🚀 ANTLR SQL Parser API 已启动                ║");
        log.info("╠══════════════════════════════════════════════════════╣");
        log.info("║  📡 API 地址:   http://localhost:{}              ║", String.format("%-5d", port));
        log.info("║  📖 API 文档:   http://localhost:{}/              ║", String.format("%-5d", port));
        log.info("║  ❤️  健康检查:   http://localhost:{}/api/health    ║", String.format("%-5d", port));
        log.info("╠══════════════════════════════════════════════════════╣");
        log.info("║  📋 可用端点:                                       ║");
        log.info("║    POST /api/sql/parse           解析语法树          ║");
        log.info("║    POST /api/sql/validate        验证语法            ║");
        log.info("║    POST /api/sql/metadata        元数据提取          ║");
        log.info("║    POST /api/sql/audit           审计分析            ║");
        log.info("║    POST /api/sql/format          SQL 格式化          ║");
        log.info("║    POST /api/sql/lineage         血缘分析(JSON)      ║");
        log.info("║    POST /api/sql/lineage/dot     血缘图(DOT)         ║");
        log.info("║    POST /api/sql/lineage/html    血缘图(HTML)        ║");
        log.info("║    POST /api/sql/lineage/ascii   血缘图(ASCII)       ║");
        log.info("║    POST /api/sql/visualize/ascii 语法树(ASCII)       ║");
        log.info("║    POST /api/sql/visualize/json  语法树(JSON)        ║");
        log.info("║    POST /api/sql/visualize/dot   语法树(DOT)         ║");
        log.info("║    GET  /api/health              健康检查            ║");
        log.info("╚══════════════════════════════════════════════════════╝");

        return app;
    }
}
