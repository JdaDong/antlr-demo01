package com.example.antlr;

import com.example.antlr.visual.ParseTreeVisualizer;
import com.example.antlr.mysql.MySqlLexer;
import com.example.antlr.mysql.MySqlParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 语法树可视化演示
 *
 * 运行方式：
 *   mvn -q exec:java -Dexec.mainClass="com.example.antlr.TreeVisualizerDemo"
 *
 * 输出文件位于 output/ 目录下：
 *   - parse-tree.html  → 浏览器打开，交互式树形图
 *   - parse-tree.dot   → Graphviz 渲染: dot -Tpng parse-tree.dot -o parse-tree.png
 *   - parse-tree.json  → D3.js 等前端可视化
 */
public class TreeVisualizerDemo {

    public static void main(String[] args) throws IOException {
        String outputDir = "output";
        Paths.get(outputDir).toFile().mkdirs();

        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║           🌳 ANTLR 语法树可视化演示                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");

        // ============================================================
        // 示例 1：简单 SELECT
        // ============================================================
        String sql1 = "SELECT id, name, age FROM users WHERE age > 18 ORDER BY name LIMIT 10";
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📌 示例 1: 简单 SELECT");
        System.out.println("SQL: " + sql1);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        visualize(sql1, outputDir + "/select-simple", "简单 SELECT 查询");

        // ============================================================
        // 示例 2：多表 JOIN
        // ============================================================
        String sql2 = "SELECT u.name, o.order_id, p.product_name " +
                "FROM users u " +
                "INNER JOIN orders o ON u.id = o.user_id " +
                "LEFT JOIN products p ON o.product_id = p.id " +
                "WHERE o.amount > 100 AND u.status = 'active'";
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📌 示例 2: 多表 JOIN");
        System.out.println("SQL: " + sql2);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        visualize(sql2, outputDir + "/select-join", "多表 JOIN 查询");

        // ============================================================
        // 示例 3：子查询
        // ============================================================
        String sql3 = "SELECT name FROM users WHERE id IN " +
                "(SELECT user_id FROM orders WHERE amount > " +
                "(SELECT AVG(amount) FROM orders))";
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📌 示例 3: 嵌套子查询");
        System.out.println("SQL: " + sql3);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        visualize(sql3, outputDir + "/select-subquery", "嵌套子查询");

        // ============================================================
        // 示例 4：CREATE TABLE
        // ============================================================
        String sql4 = "CREATE TABLE IF NOT EXISTS orders (" +
                "id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "user_id INT NOT NULL, " +
                "amount DECIMAL(10,2) DEFAULT 0.00, " +
                "status ENUM('pending','paid','shipped') DEFAULT 'pending', " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "PRIMARY KEY (id), " +
                "INDEX idx_user_id (user_id), " +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📌 示例 4: CREATE TABLE");
        System.out.println("SQL: " + sql4);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        visualize(sql4, outputDir + "/create-table", "CREATE TABLE 建表语句");

        // ============================================================
        // 示例 5：INSERT + UPDATE（综合 HTML 页面包含所有示例）
        // ============================================================
        String sql5 = "INSERT INTO users (name, email, age) VALUES ('Alice', 'alice@example.com', 25), ('Bob', 'bob@example.com', 30)";
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("📌 示例 5: INSERT");
        System.out.println("SQL: " + sql5);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");

        visualize(sql5, outputDir + "/insert", "INSERT 插入语句");

        // ============================================================
        // 汇总
        // ============================================================
        System.out.println("\n╔══════════════════════════════════════════════════════════╗");
        System.out.println("║  ✅ 全部生成完毕！                                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.println("║  📁 输出目录: output/                                    ║");
        System.out.println("║                                                          ║");
        System.out.println("║  📊 HTML 文件 (浏览器打开):                                ║");
        System.out.println("║     open output/select-simple.html                       ║");
        System.out.println("║     open output/select-join.html                         ║");
        System.out.println("║     open output/select-subquery.html                     ║");
        System.out.println("║     open output/create-table.html                        ║");
        System.out.println("║     open output/insert.html                              ║");
        System.out.println("║                                                          ║");
        System.out.println("║  🔧 DOT 文件 (Graphviz 渲染):                             ║");
        System.out.println("║     dot -Tpng output/select-simple.dot -o tree.png       ║");
        System.out.println("║                                                          ║");
        System.out.println("║  📝 JSON 文件 (D3.js 等前端可视化):                         ║");
        System.out.println("║     cat output/select-simple.json                        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    /**
     * 对指定 SQL 生成全部可视化格式
     */
    private static void visualize(String sql, String basePath, String title) throws IOException {
        // 解析
        MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
        lexer.removeErrorListeners();
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MySqlParser parser = new MySqlParser(tokens);
        parser.removeErrorListeners();
        ParseTree tree = parser.root();

        ParseTreeVisualizer visualizer = new ParseTreeVisualizer(parser);

        // 1. ASCII 树（直接打印到终端）
        System.out.println("🌲 ASCII 语法树:");
        System.out.println(visualizer.toAsciiTree(tree));

        // 2. HTML 文件
        String htmlPath = basePath + ".html";
        visualizer.toHtmlFile(tree, htmlPath, title);
        System.out.println("✅ HTML: " + htmlPath);

        // 3. DOT 文件
        String dotPath = basePath + ".dot";
        visualizer.toDotFile(tree, dotPath);
        System.out.println("✅ DOT:  " + dotPath);

        // 4. JSON 文件
        String jsonPath = basePath + ".json";
        visualizer.toJsonFile(tree, jsonPath);
        System.out.println("✅ JSON: " + jsonPath);

        System.out.println();
    }
}
