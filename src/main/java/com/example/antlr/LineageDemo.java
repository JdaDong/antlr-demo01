package com.example.antlr;

import com.example.antlr.lineage.LineageGraph;

import java.io.IOException;

/**
 * SQL 血缘分析演示
 *
 * 展示 6 种典型 SQL 场景的列级数据血缘分析结果：
 * 1. 简单 SELECT（直接列映射）
 * 2. 聚合函数 + GROUP BY
 * 3. 多表 JOIN
 * 4. INSERT ... SELECT（跨表血缘）
 * 5. INSERT ... VALUES（常量赋值）
 * 6. UPDATE SET（更新赋值）
 *
 * 每个场景输出：
 * - ASCII 文本报告（终端）
 * - Gson JSON 文件
 * - Jackson JSON 文件
 * - DOT 文件（Graphviz）
 * - HTML 交互式血缘图
 */
public class LineageDemo {

    public static void main(String[] args) throws IOException {
        SqlParserEngine engine = new SqlParserEngine();

        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              SQL 血缘分析演示 (Data Lineage Demo)              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝\n");

        // ================================================================
        // 1. 简单 SELECT — 直接列映射
        // ================================================================
        String sql1 = "SELECT id, name, email FROM users WHERE status = 'active'";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 1: 简单 SELECT");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g1 = engine.analyzeMySqlLineage(sql1);
        System.out.println(g1.toAsciiReport());
        exportAll(g1, "lineage-select-simple", "简单 SELECT 血缘");

        // ================================================================
        // 2. 聚合函数 + GROUP BY
        // ================================================================
        String sql2 = "SELECT department, COUNT(id) AS emp_count, AVG(salary) AS avg_salary " +
                "FROM employees GROUP BY department ORDER BY avg_salary DESC";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 2: 聚合函数 + GROUP BY");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g2 = engine.analyzeMySqlLineage(sql2);
        System.out.println(g2.toAsciiReport());
        exportAll(g2, "lineage-aggregation", "聚合函数血缘");

        // ================================================================
        // 3. 多表 JOIN
        // ================================================================
        String sql3 = "SELECT u.name, o.order_id, o.amount, p.product_name " +
                "FROM users u " +
                "INNER JOIN orders o ON u.id = o.user_id " +
                "LEFT JOIN products p ON o.product_id = p.id " +
                "WHERE o.amount > 100";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 3: 多表 JOIN");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g3 = engine.analyzeMySqlLineage(sql3);
        System.out.println(g3.toAsciiReport());
        exportAll(g3, "lineage-join", "多表 JOIN 血缘");

        // ================================================================
        // 4. INSERT ... SELECT（跨表血缘 — 最典型的血缘场景）
        // ================================================================
        String sql4 = "INSERT INTO user_summary (user_name, total_orders, total_amount) " +
                "SELECT u.name, COUNT(o.id), SUM(o.amount) " +
                "FROM users u INNER JOIN orders o ON u.id = o.user_id " +
                "GROUP BY u.name";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 4: INSERT ... SELECT（跨表血缘）");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g4 = engine.analyzeMySqlLineage(sql4);
        System.out.println(g4.toAsciiReport());
        exportAll(g4, "lineage-insert-select", "INSERT SELECT 血缘");

        // ================================================================
        // 5. INSERT ... VALUES（常量赋值）
        // ================================================================
        String sql5 = "INSERT INTO users (id, name, email) VALUES (1, 'Alice', 'alice@example.com')";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 5: INSERT ... VALUES");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g5 = engine.analyzeMySqlLineage(sql5);
        System.out.println(g5.toAsciiReport());
        exportAll(g5, "lineage-insert-values", "INSERT VALUES 血缘");

        // ================================================================
        // 6. UPDATE SET（更新赋值）
        // ================================================================
        String sql6 = "UPDATE employees SET salary = salary * 1.1, updated_at = '2026-01-01' WHERE department = 'Engineering'";
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 场景 6: UPDATE SET");
        System.out.println("═══════════════════════════════════════════════════════════════");
        LineageGraph g6 = engine.analyzeMySqlLineage(sql6);
        System.out.println(g6.toAsciiReport());
        exportAll(g6, "lineage-update", "UPDATE 血缘");

        // ================================================================
        // Gson vs Jackson 对比演示
        // ================================================================
        System.out.println("═══════════════════════════════════════════════════════════════");
        System.out.println("📌 Gson vs Jackson 序列化对比");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
        System.out.println("--- Gson 输出 (前 500 字符) ---");
        String gsonJson = g4.toGsonJson();
        System.out.println(gsonJson.substring(0, Math.min(500, gsonJson.length())));
        System.out.println("...\n");
        System.out.println("--- Jackson 输出 (前 500 字符) ---");
        String jacksonJson = g4.toJacksonJson();
        System.out.println(jacksonJson.substring(0, Math.min(500, jacksonJson.length())));
        System.out.println("...\n");
        System.out.println("✅ Gson JSON 长度: " + gsonJson.length() + " 字符");
        System.out.println("✅ Jackson JSON 长度: " + jacksonJson.length() + " 字符");

        System.out.println("\n═══════════════════════════════════════════════════════════════");
        System.out.println("✅ 全部完成！文件输出在 output/ 目录下");
        System.out.println("💡 推荐命令: open output/lineage-insert-select.html");
        System.out.println("═══════════════════════════════════════════════════════════════");
    }

    /**
     * 导出所有格式的血缘文件
     */
    private static void exportAll(LineageGraph graph, String baseName, String title) throws IOException {
        String dir = "output/";

        // Gson JSON
        graph.toGsonJsonFile(dir + baseName + "-gson.json");
        System.out.println("  ✅ Gson JSON  → " + dir + baseName + "-gson.json");

        // Jackson JSON
        graph.toJacksonJsonFile(dir + baseName + "-jackson.json");
        System.out.println("  ✅ Jackson JSON → " + dir + baseName + "-jackson.json");

        // DOT
        graph.toDotFile(dir + baseName + ".dot");
        System.out.println("  ✅ DOT         → " + dir + baseName + ".dot");

        // HTML
        graph.toHtmlFile(dir + baseName + ".html", title);
        System.out.println("  ✅ HTML        → " + dir + baseName + ".html");

        System.out.println();
    }
}
