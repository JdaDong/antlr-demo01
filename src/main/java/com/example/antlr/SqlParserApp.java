package com.example.antlr;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * SQL 解析器应用主入口
 * 支持交互式模式和命令行模式
 *
 * 使用方式:
 *   交互模式: java -jar antlr-sql-parser.jar
 *   命令行:   java -jar antlr-sql-parser.jar <dialect> "<sql>"
 */
public class SqlParserApp {

    private static final String BANNER = "\n" +
            "╔═══════════════════════════════════════════════════╗\n" +
            "║         ANTLR SQL Parser (多方言支持)             ║\n" +
            "║   支持: MySQL | PostgreSQL | SparkSQL             ║\n" +
            "╚═══════════════════════════════════════════════════╝\n";

    private static final String HELP = "命令说明:\n" +
            "  mysql    <sql>  - 以 MySQL 方言解析\n" +
            "  pg       <sql>  - 以 PostgreSQL 方言解析\n" +
            "  spark    <sql>  - 以 SparkSQL 方言解析\n" +
            "  help            - 显示帮助\n" +
            "  quit / exit     - 退出\n";

    public static void main(String[] args) {
        SqlParserEngine engine = new SqlParserEngine();

        // 命令行模式
        if (args.length >= 2) {
            String dialect = args[0];
            String sql = args[1];
            try {
                SqlDialect d = SqlDialect.fromString(dialect);
                SqlParseResult result = engine.parse(sql, d);
                System.out.println(result);
            } catch (Exception e) {
                System.err.println("错误: " + e.getMessage());
                System.exit(1);
            }
            return;
        }

        // 交互模式
        System.out.println(BANNER);
        System.out.println(HELP);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                System.out.print("\nsql-parser> ");
                System.out.flush();
                line = reader.readLine();
                if (line == null) break;
                line = line.trim();

                if (line.isEmpty()) continue;
                if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
                    System.out.println("再见! 👋");
                    break;
                }
                if (line.equalsIgnoreCase("help")) {
                    System.out.println(HELP);
                    continue;
                }

                // 解析命令
                int spaceIdx = line.indexOf(' ');
                if (spaceIdx <= 0) {
                    System.out.println("格式: <dialect> <sql>，输入 help 查看帮助");
                    continue;
                }

                String dialectStr = line.substring(0, spaceIdx);
                String sql = line.substring(spaceIdx + 1).trim();

                try {
                    SqlDialect dialect = SqlDialect.fromString(dialectStr);
                    SqlParseResult result = engine.parse(sql, dialect);
                    System.out.println(result);
                } catch (IllegalArgumentException e) {
                    System.out.println("错误: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("异常: " + e.getMessage());
        }
    }

    /**
     * 演示方法 — 展示各方言的解析能力
     */
    public static void demo() {
        SqlParserEngine engine = new SqlParserEngine();

        System.out.println(BANNER);
        System.out.println("========== 演示模式 ==========\n");

        // MySQL 示例
        String[] mysqlExamples = {
                "SELECT * FROM users WHERE id = 1",
                "CREATE TABLE orders (id INT AUTO_INCREMENT PRIMARY KEY, user_id INT NOT NULL, amount DECIMAL(10,2), created_at DATETIME DEFAULT CURRENT_TIMESTAMP) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4",
                "INSERT INTO users (name, email) VALUES ('张三', 'zhangsan@example.com')",
                "SELECT u.name, COUNT(o.id) AS order_count FROM users u LEFT JOIN orders o ON u.id = o.user_id GROUP BY u.name HAVING COUNT(o.id) > 5 ORDER BY order_count DESC LIMIT 10"
        };

        System.out.println("--- MySQL 示例 ---");
        for (String sql : mysqlExamples) {
            System.out.println(engine.parse(sql, SqlDialect.MYSQL));
        }

        // PostgreSQL 示例
        String[] pgExamples = {
                "SELECT * FROM users WHERE email ILIKE '%@example.com'",
                "CREATE TABLE products (id SERIAL PRIMARY KEY, name VARCHAR(255) NOT NULL, price NUMERIC(10,2), metadata JSONB, tags TEXT[])",
                "WITH recent_orders AS (SELECT * FROM orders WHERE created_at > '2024-01-01') SELECT u.name, r.total FROM users u JOIN recent_orders r ON u.id = r.user_id",
                "INSERT INTO logs (data) VALUES ('{\"action\": \"login\"}'::jsonb) RETURNING id"
        };

        System.out.println("\n--- PostgreSQL 示例 ---");
        for (String sql : pgExamples) {
            System.out.println(engine.parse(sql, SqlDialect.POSTGRESQL));
        }

        // SparkSQL 示例
        String[] sparkExamples = {
                "SELECT * FROM db.users WHERE age > 18",
                "CREATE TABLE IF NOT EXISTS events (id BIGINT, event_type STRING, event_data MAP<STRING, STRING>, tags ARRAY<STRING>, created_at TIMESTAMP) USING parquet PARTITIONED BY (dt STRING) LOCATION '/data/events'",
                "SELECT user_id, SUM(amount) OVER (PARTITION BY user_id ORDER BY created_at ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS running_total FROM orders",
                "INSERT OVERWRITE TABLE summary PARTITION (dt='2024-01-01') SELECT user_id, COUNT(*) as cnt FROM events GROUP BY user_id"
        };

        System.out.println("\n--- SparkSQL 示例 ---");
        for (String sql : sparkExamples) {
            System.out.println(engine.parse(sql, SqlDialect.SPARKSQL));
        }
    }
}
