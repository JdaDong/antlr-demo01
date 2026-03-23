package com.example.antlr.lineage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.JsonIgnore;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Factory;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SQL 血缘分析图 — 核心数据模型
 *
 * 包含三个内部类：
 * - {@link ColumnNode}   — 列节点（表名.列名）
 * - {@link LineageEdge}  — 列之间的血缘边（来源 → 目标，附带转换类型）
 * - {@link TableNode}    — 表级节点（聚合用）
 *
 * 支持 Gson / Jackson 双格式序列化，以及 DOT / HTML 可视化输出。
 */
public class LineageGraph {

    // ============================================================
    // 内部类：ColumnNode（列节点）
    // ============================================================

    /**
     * 列节点 — 代表一个表中的列（或表达式）
     */
    public static class ColumnNode {
        private String table;       // 表名（可能为 null 表示未解析或子查询虚拟表）
        private String column;      // 列名或表达式文本
        private String alias;       // 别名（可选）
        private String expression;  // 原始表达式文本（如 SUM(amount)）

        public ColumnNode() {
        }

        public ColumnNode(String table, String column) {
            this.table = table;
            this.column = column;
        }

        public ColumnNode(String table, String column, String alias) {
            this.table = table;
            this.column = column;
            this.alias = alias;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getColumn() {
            return column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        /**
         * 唯一标识：table.column
         */
        @JsonIgnore
        public String getId() {
            if (table != null && !table.isEmpty()) {
                return table + "." + column;
            }
            return column;
        }

        /**
         * 显示名称
         */
        @JsonIgnore
        public String getDisplayName() {
            String name = getId();
            if (alias != null && !alias.isEmpty()) {
                name += " (AS " + alias + ")";
            }
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ColumnNode that = (ColumnNode) o;
            return Objects.equals(table, that.table) && Objects.equals(column, that.column);
        }

        @Override
        public int hashCode() {
            return Objects.hash(table, column);
        }

        @Override
        public String toString() {
            return getId();
        }
    }

    // ============================================================
    // 内部类：LineageEdge（血缘边）
    // ============================================================

    /**
     * 血缘边 — 表示一个列如何从来源变换到目标
     */
    public static class LineageEdge {
        private ColumnNode source;       // 来源列
        private ColumnNode target;       // 目标列
        private TransformType transform; // 转换类型
        private String description;      // 转换描述

        public LineageEdge() {
        }

        public LineageEdge(ColumnNode source, ColumnNode target, TransformType transform) {
            this.source = source;
            this.target = target;
            this.transform = transform;
        }

        public LineageEdge(ColumnNode source, ColumnNode target, TransformType transform, String description) {
            this.source = source;
            this.target = target;
            this.transform = transform;
            this.description = description;
        }

        public ColumnNode getSource() {
            return source;
        }

        public void setSource(ColumnNode source) {
            this.source = source;
        }

        public ColumnNode getTarget() {
            return target;
        }

        public void setTarget(ColumnNode target) {
            this.target = target;
        }

        public TransformType getTransform() {
            return transform;
        }

        public void setTransform(TransformType transform) {
            this.transform = transform;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return source + " --[" + transform + "]--> " + target;
        }
    }

    // ============================================================
    // 枚举：TransformType（转换类型）
    // ============================================================

    /**
     * 列血缘的转换类型
     */
    public enum TransformType {
        /** 直接传递（无变换） */
        DIRECT("直接映射"),
        /** 聚合函数（SUM/COUNT/AVG/MAX/MIN） */
        AGGREGATION("聚合"),
        /** 普通函数调用（UPPER/LOWER/CONCAT 等） */
        FUNCTION("函数转换"),
        /** 算术运算 */
        COMPUTATION("计算"),
        /** CASE WHEN 条件转换 */
        CONDITIONAL("条件转换"),
        /** 子查询 */
        SUBQUERY("子查询"),
        /** 常量 */
        CONSTANT("常量"),
        /** 通配符 SELECT * */
        WILDCARD("通配符展开"),
        /** INSERT ... VALUES */
        VALUE_ASSIGN("值赋予"),
        /** UPDATE SET */
        UPDATE_ASSIGN("更新赋值");

        private final String displayName;

        TransformType(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return displayName;
        }
    }

    // ============================================================
    // 内部类：TableNode（表级节点 — 用于表级血缘汇总）
    // ============================================================

    public static class TableNode {
        private String name;
        private String alias;
        private TableRole role;

        public TableNode() {
        }

        public TableNode(String name, TableRole role) {
            this.name = name;
            this.role = role;
        }

        public TableNode(String name, String alias, TableRole role) {
            this.name = name;
            this.alias = alias;
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public TableRole getRole() {
            return role;
        }

        public void setRole(TableRole role) {
            this.role = role;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TableNode that = (TableNode) o;
            return Objects.equals(name, that.name) && role == that.role;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, role);
        }

        @Override
        public String toString() {
            String s = name;
            if (alias != null) s += " (" + alias + ")";
            return s + " [" + role + "]";
        }
    }

    public enum TableRole {
        SOURCE, TARGET, SOURCE_TARGET
    }

    // ============================================================
    // LineageGraph 主体
    // ============================================================

    private String sql;
    private String statementType;
    private final List<LineageEdge> edges = new ArrayList<>();
    private final Set<TableNode> tables = new LinkedHashSet<>();
    private final Set<ColumnNode> sourceColumns = new LinkedHashSet<>();
    private final Set<ColumnNode> targetColumns = new LinkedHashSet<>();

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType(String statementType) {
        this.statementType = statementType;
    }

    public List<LineageEdge> getEdges() {
        return Collections.unmodifiableList(edges);
    }

    public Set<TableNode> getTables() {
        return Collections.unmodifiableSet(tables);
    }

    public Set<ColumnNode> getSourceColumns() {
        return Collections.unmodifiableSet(sourceColumns);
    }

    public Set<ColumnNode> getTargetColumns() {
        return Collections.unmodifiableSet(targetColumns);
    }

    /**
     * 添加一条血缘边
     */
    public void addEdge(ColumnNode source, ColumnNode target, TransformType transform) {
        edges.add(new LineageEdge(source, target, transform));
        sourceColumns.add(source);
        targetColumns.add(target);
    }

    public void addEdge(ColumnNode source, ColumnNode target, TransformType transform, String description) {
        edges.add(new LineageEdge(source, target, transform, description));
        sourceColumns.add(source);
        targetColumns.add(target);
    }

    public void addTable(String name, String alias, TableRole role) {
        tables.add(new TableNode(name, alias, role));
    }

    public void addTable(String name, TableRole role) {
        tables.add(new TableNode(name, role));
    }

    // ============================================================
    // 查询接口
    // ============================================================

    /**
     * 获取指定目标列的所有来源
     */
    public List<LineageEdge> getSourcesOf(String table, String column) {
        return edges.stream()
                .filter(e -> Objects.equals(e.target.table, table) && Objects.equals(e.target.column, column))
                .collect(Collectors.toList());
    }

    /**
     * 获取指定来源列影响的所有目标
     */
    public List<LineageEdge> getTargetsOf(String table, String column) {
        return edges.stream()
                .filter(e -> Objects.equals(e.source.table, table) && Objects.equals(e.source.column, column))
                .collect(Collectors.toList());
    }

    /**
     * 获取所有来源表名
     */
    @JsonIgnore
    public Set<String> getSourceTableNames() {
        return tables.stream()
                .filter(t -> t.role == TableRole.SOURCE || t.role == TableRole.SOURCE_TARGET)
                .map(TableNode::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * 获取所有目标表名
     */
    @JsonIgnore
    public Set<String> getTargetTableNames() {
        return tables.stream()
                .filter(t -> t.role == TableRole.TARGET || t.role == TableRole.SOURCE_TARGET)
                .map(TableNode::getName)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    // ============================================================
    // Gson 序列化
    // ============================================================

    /**
     * 使用 Gson 序列化为 JSON
     */
    public String toGsonJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        return gson.toJson(buildSerializableMap());
    }

    /**
     * 使用 Gson 写入文件
     */
    public void toGsonJsonFile(String filePath) throws IOException {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(toGsonJson());
        }
    }

    // ============================================================
    // Jackson 序列化
    // ============================================================

    /**
     * 使用 Jackson 序列化为 JSON
     */
    public String toJacksonJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(buildSerializableMap());
    }

    /**
     * 使用 Jackson 写入文件
     */
    public void toJacksonJsonFile(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filePath), buildSerializableMap());
    }

    /**
     * 构建可序列化的 Map 结构
     */
    private Map<String, Object> buildSerializableMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("sql", sql);
        result.put("statementType", statementType);

        // 表信息
        List<Map<String, String>> tableList = new ArrayList<>();
        for (TableNode t : tables) {
            Map<String, String> m = new LinkedHashMap<>();
            m.put("name", t.name);
            if (t.alias != null) m.put("alias", t.alias);
            m.put("role", t.role.name());
            tableList.add(m);
        }
        result.put("tables", tableList);

        // 血缘边
        List<Map<String, Object>> edgeList = new ArrayList<>();
        for (LineageEdge e : edges) {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("source", e.source.getId());
            m.put("target", e.target.getId());
            m.put("transform", e.transform.name());
            m.put("transformName", e.transform.getDisplayName());
            if (e.description != null) m.put("description", e.description);
            edgeList.add(m);
        }
        result.put("edges", edgeList);

        // 列信息
        List<Map<String, String>> srcCols = new ArrayList<>();
        for (ColumnNode c : sourceColumns) {
            Map<String, String> m = new LinkedHashMap<>();
            m.put("id", c.getId());
            if (c.table != null) m.put("table", c.table);
            m.put("column", c.column);
            if (c.alias != null) m.put("alias", c.alias);
            if (c.expression != null) m.put("expression", c.expression);
            srcCols.add(m);
        }
        result.put("sourceColumns", srcCols);

        List<Map<String, String>> tgtCols = new ArrayList<>();
        for (ColumnNode c : targetColumns) {
            Map<String, String> m = new LinkedHashMap<>();
            m.put("id", c.getId());
            if (c.table != null) m.put("table", c.table);
            m.put("column", c.column);
            if (c.alias != null) m.put("alias", c.alias);
            if (c.expression != null) m.put("expression", c.expression);
            tgtCols.add(m);
        }
        result.put("targetColumns", tgtCols);

        return result;
    }

    // ============================================================
    // DOT 格式输出（Graphviz）
    // ============================================================

    /**
     * 生成 DOT 格式的血缘图
     */
    public String toDot() {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph SQLLineage {\n");
        sb.append("    rankdir=LR;\n");
        sb.append("    node [shape=record, style=filled, fontname=\"Helvetica\"];\n");
        sb.append("    edge [fontname=\"Helvetica\", fontsize=10];\n\n");

        // 按表分组绘制节点
        Map<String, List<ColumnNode>> sourceByTable = new LinkedHashMap<>();
        for (ColumnNode c : sourceColumns) {
            String tbl = c.table != null ? c.table : "_unknown_";
            sourceByTable.computeIfAbsent(tbl, k -> new ArrayList<>()).add(c);
        }

        Map<String, List<ColumnNode>> targetByTable = new LinkedHashMap<>();
        for (ColumnNode c : targetColumns) {
            String tbl = c.table != null ? c.table : "_result_";
            targetByTable.computeIfAbsent(tbl, k -> new ArrayList<>()).add(c);
        }

        // 来源表（蓝色）
        int clusterIdx = 0;
        for (Map.Entry<String, List<ColumnNode>> entry : sourceByTable.entrySet()) {
            sb.append("    subgraph cluster_src_").append(clusterIdx++).append(" {\n");
            sb.append("        label=\"").append(escDot(entry.getKey())).append(" (来源)\";\n");
            sb.append("        style=filled; color=\"#e3f2fd\"; fontcolor=\"#1565c0\";\n");
            for (ColumnNode c : entry.getValue()) {
                String nodeId = dotNodeId(c, "src");
                sb.append("        ").append(nodeId)
                        .append(" [label=\"").append(escDot(c.column)).append("\"")
                        .append(", fillcolor=\"#bbdefb\", fontcolor=\"#0d47a1\"];\n");
            }
            sb.append("    }\n\n");
        }

        // 目标表（绿色）
        for (Map.Entry<String, List<ColumnNode>> entry : targetByTable.entrySet()) {
            sb.append("    subgraph cluster_tgt_").append(clusterIdx++).append(" {\n");
            sb.append("        label=\"").append(escDot(entry.getKey())).append(" (目标)\";\n");
            sb.append("        style=filled; color=\"#e8f5e9\"; fontcolor=\"#2e7d32\";\n");
            for (ColumnNode c : entry.getValue()) {
                String nodeId = dotNodeId(c, "tgt");
                sb.append("        ").append(nodeId)
                        .append(" [label=\"").append(escDot(c.column)).append("\"")
                        .append(", fillcolor=\"#c8e6c9\", fontcolor=\"#1b5e20\"];\n");
            }
            sb.append("    }\n\n");
        }

        // 边
        for (LineageEdge e : edges) {
            String srcId = dotNodeId(e.source, "src");
            String tgtId = dotNodeId(e.target, "tgt");
            String label = e.transform.getDisplayName();
            String color = getEdgeColor(e.transform);
            sb.append("    ").append(srcId).append(" -> ").append(tgtId)
                    .append(" [label=\"").append(escDot(label)).append("\"")
                    .append(", color=\"").append(color).append("\"")
                    .append(", fontcolor=\"").append(color).append("\"];\n");
        }

        sb.append("}\n");
        return sb.toString();
    }

    public void toDotFile(String filePath) throws IOException {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(toDot());
        }
    }

    // ============================================================
    // Graphviz-java 渲染（直接生成 PNG/SVG，无需系统安装 Graphviz）
    // ============================================================

    /**
     * 使用 graphviz-java 将血缘图渲染为 PNG 文件
     *
     * @param filePath 输出 PNG 文件路径
     */
    public void renderToPng(String filePath) throws IOException {
        Graphviz.fromString(toDot())
                .width(1200)
                .render(Format.PNG)
                .toFile(new File(filePath));
    }

    /**
     * 使用 graphviz-java 将血缘图渲染为 SVG 文件
     *
     * @param filePath 输出 SVG 文件路径
     */
    public void renderToSvg(String filePath) throws IOException {
        Graphviz.fromString(toDot())
                .width(1200)
                .render(Format.SVG)
                .toFile(new File(filePath));
    }

    /**
     * 使用 graphviz-java 将血缘图渲染为 SVG 字符串
     *
     * @return SVG 格式字符串
     */
    public String renderToSvgString() {
        return Graphviz.fromString(toDot())
                .width(1200)
                .render(Format.SVG)
                .toString();
    }

    /**
     * 使用 graphviz-java 将血缘图渲染为 PNG 字节数组
     *
     * @return PNG 图片字节数组
     */
    public byte[] renderToPngBytes() {
        return Graphviz.fromString(toDot())
                .width(1200)
                .render(Format.PNG)
                .toImage()
                .toString()
                .getBytes(StandardCharsets.UTF_8);
    }

    private String dotNodeId(ColumnNode c, String prefix) {
        String id = prefix + "_" + (c.table != null ? c.table : "x") + "_" + c.column;
        return id.replaceAll("[^a-zA-Z0-9_]", "_");
    }

    private String escDot(String s) {
        return s.replace("\"", "\\\"").replace("\n", "\\n");
    }

    private String getEdgeColor(TransformType t) {
        switch (t) {
            case DIRECT:
                return "#1976d2";
            case AGGREGATION:
                return "#e65100";
            case FUNCTION:
                return "#7b1fa2";
            case COMPUTATION:
                return "#f57f17";
            case CONDITIONAL:
                return "#00838f";
            case SUBQUERY:
                return "#c62828";
            case CONSTANT:
                return "#757575";
            case WILDCARD:
                return "#2e7d32";
            case VALUE_ASSIGN:
                return "#4527a0";
            case UPDATE_ASSIGN:
                return "#bf360c";
            default:
                return "#424242";
        }
    }

    // ============================================================
    // HTML 交互式可视化
    // ============================================================

    /**
     * 生成自包含的交互式 HTML 血缘图
     */
    public String toHtml(String title) {
        String jsonData;
        try {
            jsonData = toJacksonJson();
        } catch (IOException e) {
            jsonData = toGsonJson();
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>" + escHtml(title) + "</title>\n" +
                "    <style>\n" +
                "        * { margin: 0; padding: 0; box-sizing: border-box; }\n" +
                "        body { font-family: -apple-system, 'Segoe UI', Roboto, sans-serif; background: #0d1117; color: #e6edf3; }\n" +
                "        .header { padding: 16px 24px; background: #161b22; border-bottom: 1px solid #30363d; display: flex; align-items: center; gap: 16px; }\n" +
                "        .header h1 { font-size: 18px; font-weight: 600; }\n" +
                "        .header .badge { padding: 2px 8px; border-radius: 12px; font-size: 12px; font-weight: 500; }\n" +
                "        .badge.type { background: #1f6feb33; color: #58a6ff; border: 1px solid #1f6feb; }\n" +
                "        .info-bar { padding: 8px 24px; background: #161b22; border-bottom: 1px solid #30363d; font-size: 13px; color: #8b949e; display: flex; gap: 24px; }\n" +
                "        .info-bar span { display: flex; align-items: center; gap: 4px; }\n" +
                "        .sql-box { padding: 12px 24px; background: #0d1117; border-bottom: 1px solid #30363d; }\n" +
                "        .sql-box pre { font-family: 'Fira Code', monospace; font-size: 13px; color: #79c0ff; white-space: pre-wrap; word-break: break-all; }\n" +
                "        .container { display: flex; height: calc(100vh - 140px); }\n" +
                "        .sidebar { width: 280px; background: #161b22; border-right: 1px solid #30363d; overflow-y: auto; padding: 16px; }\n" +
                "        .sidebar h3 { font-size: 13px; color: #8b949e; text-transform: uppercase; margin-bottom: 8px; letter-spacing: 0.5px; }\n" +
                "        .table-group { margin-bottom: 16px; }\n" +
                "        .table-name { font-size: 14px; font-weight: 600; padding: 6px 8px; border-radius: 6px; cursor: pointer; }\n" +
                "        .table-name.source { background: #1f6feb22; color: #58a6ff; }\n" +
                "        .table-name.target { background: #23863622; color: #3fb950; }\n" +
                "        .col-list { padding-left: 16px; margin-top: 4px; }\n" +
                "        .col-item { font-size: 13px; padding: 3px 8px; color: #c9d1d9; border-radius: 4px; cursor: pointer; }\n" +
                "        .col-item:hover { background: #30363d; }\n" +
                "        .col-item.highlight { background: #1f6feb44; color: #58a6ff; }\n" +
                "        .canvas-area { flex: 1; position: relative; overflow: hidden; }\n" +
                "        svg { width: 100%; height: 100%; }\n" +
                "        .edge-line { stroke-width: 2; fill: none; opacity: 0.6; }\n" +
                "        .edge-line:hover { opacity: 1; stroke-width: 3; }\n" +
                "        .edge-line.highlight { opacity: 1; stroke-width: 3; }\n" +
                "        .node-group rect { rx: 6; ry: 6; stroke-width: 1.5; cursor: pointer; }\n" +
                "        .node-group:hover rect { stroke-width: 2.5; filter: brightness(1.2); }\n" +
                "        .node-group.highlight rect { stroke-width: 2.5; filter: brightness(1.3); }\n" +
                "        .node-label { font-size: 12px; font-family: -apple-system, sans-serif; pointer-events: none; }\n" +
                "        .table-header { font-size: 13px; font-weight: 600; font-family: -apple-system, sans-serif; }\n" +
                "        .transform-label { font-size: 10px; fill: #8b949e; font-family: -apple-system, sans-serif; pointer-events: none; }\n" +
                "        .tooltip { position: absolute; background: #1c2128; border: 1px solid #30363d; border-radius: 8px; padding: 12px; font-size: 13px; pointer-events: none; display: none; z-index: 100; max-width: 350px; box-shadow: 0 4px 12px rgba(0,0,0,0.4); }\n" +
                "        .tooltip .tt-title { font-weight: 600; margin-bottom: 4px; color: #58a6ff; }\n" +
                "        .tooltip .tt-row { color: #8b949e; margin: 2px 0; }\n" +
                "        .tooltip .tt-value { color: #e6edf3; }\n" +
                "        .legend { position: absolute; bottom: 16px; right: 16px; background: #161b22; border: 1px solid #30363d; border-radius: 8px; padding: 12px; font-size: 12px; }\n" +
                "        .legend-item { display: flex; align-items: center; gap: 8px; margin: 4px 0; }\n" +
                "        .legend-color { width: 20px; height: 3px; border-radius: 2px; }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"header\">\n" +
                "        <h1>\uD83D\uDD17 " + escHtml(title) + "</h1>\n" +
                "        <span class=\"badge type\" id=\"stmt-type\"></span>\n" +
                "    </div>\n" +
                "    <div class=\"info-bar\" id=\"info-bar\"></div>\n" +
                "    <div class=\"sql-box\"><pre id=\"sql-text\"></pre></div>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"sidebar\" id=\"sidebar\"></div>\n" +
                "        <div class=\"canvas-area\" id=\"canvas-area\">\n" +
                "            <svg id=\"lineage-svg\"></svg>\n" +
                "            <div class=\"tooltip\" id=\"tooltip\"></div>\n" +
                "            <div class=\"legend\" id=\"legend\"></div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "<script>\n" +
                "const DATA = " + jsonData + ";\n" +
                "\n" +
                "// 初始化\n" +
                "document.getElementById('stmt-type').textContent = DATA.statementType;\n" +
                "document.getElementById('sql-text').textContent = DATA.sql;\n" +
                "\n" +
                "// 信息栏\n" +
                "const infoBar = document.getElementById('info-bar');\n" +
                "const srcTables = DATA.tables.filter(t => t.role === 'SOURCE' || t.role === 'SOURCE_TARGET').map(t => t.name);\n" +
                "const tgtTables = DATA.tables.filter(t => t.role === 'TARGET' || t.role === 'SOURCE_TARGET').map(t => t.name);\n" +
                "infoBar.innerHTML = `<span>\uD83D\uDCCA 来源表: ${srcTables.join(', ') || '-'}</span>` +\n" +
                "    `<span>\uD83C\uDFAF 目标表: ${tgtTables.join(', ') || '-'}</span>` +\n" +
                "    `<span>\uD83D\uDD17 血缘边: ${DATA.edges.length}</span>` +\n" +
                "    `<span>\uD83D\uDCDD 来源列: ${DATA.sourceColumns.length}</span>` +\n" +
                "    `<span>\uD83D\uDCCC 目标列: ${DATA.targetColumns.length}</span>`;\n" +
                "\n" +
                "// 侧边栏\n" +
                "const sidebar = document.getElementById('sidebar');\n" +
                "let sideHtml = '<h3>\uD83D\uDCE5 来源表</h3>';\n" +
                "const srcByTable = {};\n" +
                "DATA.sourceColumns.forEach(c => {\n" +
                "    const t = c.table || '_unknown_';\n" +
                "    if (!srcByTable[t]) srcByTable[t] = [];\n" +
                "    srcByTable[t].push(c);\n" +
                "});\n" +
                "for (const [tbl, cols] of Object.entries(srcByTable)) {\n" +
                "    sideHtml += `<div class=\"table-group\"><div class=\"table-name source\">\uD83D\uDDC2 ${tbl}</div><div class=\"col-list\">`;\n" +
                "    cols.forEach(c => sideHtml += `<div class=\"col-item\" data-id=\"${c.id}\">${c.column}${c.alias ? ' ('+c.alias+')' : ''}</div>`);\n" +
                "    sideHtml += '</div></div>';\n" +
                "}\n" +
                "sideHtml += '<h3 style=\"margin-top:16px\">\uD83C\uDFAF 目标表</h3>';\n" +
                "const tgtByTable = {};\n" +
                "DATA.targetColumns.forEach(c => {\n" +
                "    const t = c.table || '_result_';\n" +
                "    if (!tgtByTable[t]) tgtByTable[t] = [];\n" +
                "    tgtByTable[t].push(c);\n" +
                "});\n" +
                "for (const [tbl, cols] of Object.entries(tgtByTable)) {\n" +
                "    sideHtml += `<div class=\"table-group\"><div class=\"table-name target\">\uD83D\uDDC2 ${tbl}</div><div class=\"col-list\">`;\n" +
                "    cols.forEach(c => sideHtml += `<div class=\"col-item\" data-id=\"${c.id}\">${c.column}${c.alias ? ' ('+c.alias+')' : ''}</div>`);\n" +
                "    sideHtml += '</div></div>';\n" +
                "}\n" +
                "sidebar.innerHTML = sideHtml;\n" +
                "\n" +
                "// 图例\n" +
                "const transforms = [...new Set(DATA.edges.map(e => e.transform))];\n" +
                "const colorMap = {\n" +
                "    DIRECT: '#58a6ff', AGGREGATION: '#f0883e', FUNCTION: '#bc8cff',\n" +
                "    COMPUTATION: '#d29922', CONDITIONAL: '#39d2c0', SUBQUERY: '#f85149',\n" +
                "    CONSTANT: '#8b949e', WILDCARD: '#3fb950', VALUE_ASSIGN: '#a371f7', UPDATE_ASSIGN: '#f47067'\n" +
                "};\n" +
                "const nameMap = {\n" +
                "    DIRECT: '直接映射', AGGREGATION: '聚合', FUNCTION: '函数转换',\n" +
                "    COMPUTATION: '计算', CONDITIONAL: '条件转换', SUBQUERY: '子查询',\n" +
                "    CONSTANT: '常量', WILDCARD: '通配符展开', VALUE_ASSIGN: '值赋予', UPDATE_ASSIGN: '更新赋值'\n" +
                "};\n" +
                "document.getElementById('legend').innerHTML = '<div style=\"font-weight:600;margin-bottom:6px\">血缘类型</div>' +\n" +
                "    transforms.map(t => `<div class=\"legend-item\"><div class=\"legend-color\" style=\"background:${colorMap[t]||'#8b949e'}\"></div>${nameMap[t]||t}</div>`).join('');\n" +
                "\n" +
                "// ===================== SVG 绘制 =====================\n" +
                "const svg = document.getElementById('lineage-svg');\n" +
                "const ns = 'http://www.w3.org/2000/svg';\n" +
                "const area = document.getElementById('canvas-area');\n" +
                "const W = area.clientWidth, H = area.clientHeight;\n" +
                "\n" +
                "// 布局参数\n" +
                "const MARGIN = 60, NODE_W = 160, NODE_H = 28, TABLE_PAD = 8, TABLE_HEADER_H = 32, GAP_Y = 6;\n" +
                "\n" +
                "// 计算来源/目标表位置\n" +
                "function layoutTable(columns, startX, startY) {\n" +
                "    const byTable = {};\n" +
                "    columns.forEach(c => { const t = c.table || '_'; if (!byTable[t]) byTable[t]=[];byTable[t].push(c); });\n" +
                "    const positions = {};\n" +
                "    let y = startY;\n" +
                "    for (const [tbl, cols] of Object.entries(byTable)) {\n" +
                "        const tableY = y;\n" +
                "        y += TABLE_HEADER_H;\n" +
                "        cols.forEach((c, i) => {\n" +
                "            positions[c.id] = { x: startX, y: y, w: NODE_W, h: NODE_H, table: tbl, col: c };\n" +
                "            y += NODE_H + GAP_Y;\n" +
                "        });\n" +
                "        positions['_table_' + tbl] = { x: startX - TABLE_PAD, y: tableY, w: NODE_W + TABLE_PAD * 2, h: y - tableY + TABLE_PAD };\n" +
                "        y += 24;\n" +
                "    }\n" +
                "    return positions;\n" +
                "}\n" +
                "\n" +
                "const srcPos = layoutTable(DATA.sourceColumns, MARGIN, MARGIN);\n" +
                "const tgtPos = layoutTable(DATA.targetColumns, W - MARGIN - NODE_W, MARGIN);\n" +
                "\n" +
                "// 绘制表背景\n" +
                "function drawTableBg(pos, type) {\n" +
                "    for (const [key, p] of Object.entries(pos)) {\n" +
                "        if (!key.startsWith('_table_')) continue;\n" +
                "        const rect = document.createElementNS(ns, 'rect');\n" +
                "        rect.setAttribute('x', p.x); rect.setAttribute('y', p.y);\n" +
                "        rect.setAttribute('width', p.w); rect.setAttribute('height', p.h);\n" +
                "        rect.setAttribute('rx', 8);\n" +
                "        rect.setAttribute('fill', type === 'src' ? '#1f6feb11' : '#23863611');\n" +
                "        rect.setAttribute('stroke', type === 'src' ? '#1f6feb44' : '#23863644');\n" +
                "        svg.appendChild(rect);\n" +
                "        const txt = document.createElementNS(ns, 'text');\n" +
                "        txt.setAttribute('x', p.x + TABLE_PAD + 4); txt.setAttribute('y', p.y + 22);\n" +
                "        txt.setAttribute('class', 'table-header');\n" +
                "        txt.setAttribute('fill', type === 'src' ? '#58a6ff' : '#3fb950');\n" +
                "        txt.textContent = key.replace('_table_', '') + (type === 'src' ? ' (来源)' : ' (目标)');\n" +
                "        svg.appendChild(txt);\n" +
                "    }\n" +
                "}\n" +
                "drawTableBg(srcPos, 'src');\n" +
                "drawTableBg(tgtPos, 'tgt');\n" +
                "\n" +
                "// 绘制列节点\n" +
                "const nodeElements = {};\n" +
                "function drawNodes(pos, type) {\n" +
                "    for (const [key, p] of Object.entries(pos)) {\n" +
                "        if (key.startsWith('_table_')) continue;\n" +
                "        const g = document.createElementNS(ns, 'g');\n" +
                "        g.setAttribute('class', 'node-group');\n" +
                "        g.setAttribute('data-id', key);\n" +
                "        g.setAttribute('data-type', type);\n" +
                "        const rect = document.createElementNS(ns, 'rect');\n" +
                "        rect.setAttribute('x', p.x); rect.setAttribute('y', p.y);\n" +
                "        rect.setAttribute('width', p.w); rect.setAttribute('height', p.h);\n" +
                "        rect.setAttribute('fill', type === 'src' ? '#1f6feb22' : '#23863622');\n" +
                "        rect.setAttribute('stroke', type === 'src' ? '#1f6feb' : '#238636');\n" +
                "        g.appendChild(rect);\n" +
                "        const txt = document.createElementNS(ns, 'text');\n" +
                "        txt.setAttribute('x', p.x + 10); txt.setAttribute('y', p.y + 18);\n" +
                "        txt.setAttribute('class', 'node-label');\n" +
                "        txt.setAttribute('fill', type === 'src' ? '#79c0ff' : '#7ee787');\n" +
                "        let label = p.col.column;\n" +
                "        if (p.col.alias) label += ' (' + p.col.alias + ')';\n" +
                "        txt.textContent = label.length > 18 ? label.substring(0,16)+'...' : label;\n" +
                "        g.appendChild(txt);\n" +
                "        svg.appendChild(g);\n" +
                "        nodeElements[type + ':' + key] = g;\n" +
                "    }\n" +
                "}\n" +
                "drawNodes(srcPos, 'src');\n" +
                "drawNodes(tgtPos, 'tgt');\n" +
                "\n" +
                "// 绘制血缘边（贝塞尔曲线）\n" +
                "const edgeElements = [];\n" +
                "DATA.edges.forEach((e, i) => {\n" +
                "    const sp = srcPos[e.source], tp = tgtPos[e.target];\n" +
                "    if (!sp || !tp) return;\n" +
                "    const x1 = sp.x + sp.w, y1 = sp.y + sp.h / 2;\n" +
                "    const x2 = tp.x, y2 = tp.y + tp.h / 2;\n" +
                "    const cx = (x1 + x2) / 2;\n" +
                "    const path = document.createElementNS(ns, 'path');\n" +
                "    path.setAttribute('d', `M${x1},${y1} C${cx},${y1} ${cx},${y2} ${x2},${y2}`);\n" +
                "    path.setAttribute('class', 'edge-line');\n" +
                "    path.setAttribute('stroke', colorMap[e.transform] || '#8b949e');\n" +
                "    path.setAttribute('data-idx', i);\n" +
                "    svg.appendChild(path);\n" +
                "    // 转换类型标签\n" +
                "    const mid = document.createElementNS(ns, 'text');\n" +
                "    mid.setAttribute('x', cx); mid.setAttribute('y', (y1+y2)/2 - 6);\n" +
                "    mid.setAttribute('class', 'transform-label');\n" +
                "    mid.setAttribute('text-anchor', 'middle');\n" +
                "    mid.textContent = e.transformName;\n" +
                "    svg.appendChild(mid);\n" +
                "    edgeElements.push({ path, edge: e, srcId: e.source, tgtId: e.target });\n" +
                "});\n" +
                "\n" +
                "// ===================== 交互 =====================\n" +
                "const tooltip = document.getElementById('tooltip');\n" +
                "\n" +
                "// 节点 hover\n" +
                "svg.querySelectorAll('.node-group').forEach(g => {\n" +
                "    g.addEventListener('mouseenter', ev => {\n" +
                "        const id = g.getAttribute('data-id');\n" +
                "        const type = g.getAttribute('data-type');\n" +
                "        // 高亮相关边\n" +
                "        edgeElements.forEach(ee => {\n" +
                "            if ((type === 'src' && ee.srcId === id) || (type === 'tgt' && ee.tgtId === id)) {\n" +
                "                ee.path.classList.add('highlight');\n" +
                "            }\n" +
                "        });\n" +
                "        // tooltip\n" +
                "        const p = type === 'src' ? srcPos[id] : tgtPos[id];\n" +
                "        if (p) {\n" +
                "            let html = `<div class=\"tt-title\">${p.col.column}</div>`;\n" +
                "            html += `<div class=\"tt-row\">表: <span class=\"tt-value\">${p.table}</span></div>`;\n" +
                "            if (p.col.alias) html += `<div class=\"tt-row\">别名: <span class=\"tt-value\">${p.col.alias}</span></div>`;\n" +
                "            if (p.col.expression) html += `<div class=\"tt-row\">表达式: <span class=\"tt-value\">${p.col.expression}</span></div>`;\n" +
                "            tooltip.innerHTML = html;\n" +
                "            tooltip.style.display = 'block';\n" +
                "            tooltip.style.left = (ev.clientX + 12) + 'px';\n" +
                "            tooltip.style.top = (ev.clientY - 12) + 'px';\n" +
                "        }\n" +
                "    });\n" +
                "    g.addEventListener('mouseleave', () => {\n" +
                "        edgeElements.forEach(ee => ee.path.classList.remove('highlight'));\n" +
                "        tooltip.style.display = 'none';\n" +
                "    });\n" +
                "});\n" +
                "\n" +
                "// 边 hover\n" +
                "svg.querySelectorAll('.edge-line').forEach(path => {\n" +
                "    path.addEventListener('mouseenter', ev => {\n" +
                "        const idx = +path.getAttribute('data-idx');\n" +
                "        const e = DATA.edges[idx];\n" +
                "        let html = `<div class=\"tt-title\">${e.transformName}</div>`;\n" +
                "        html += `<div class=\"tt-row\">来源: <span class=\"tt-value\">${e.source}</span></div>`;\n" +
                "        html += `<div class=\"tt-row\">目标: <span class=\"tt-value\">${e.target}</span></div>`;\n" +
                "        if (e.description) html += `<div class=\"tt-row\">描述: <span class=\"tt-value\">${e.description}</span></div>`;\n" +
                "        tooltip.innerHTML = html;\n" +
                "        tooltip.style.display = 'block';\n" +
                "        tooltip.style.left = (ev.clientX + 12) + 'px';\n" +
                "        tooltip.style.top = (ev.clientY - 12) + 'px';\n" +
                "    });\n" +
                "    path.addEventListener('mouseleave', () => tooltip.style.display = 'none');\n" +
                "});\n" +
                "\n" +
                "// 侧边栏点击高亮\n" +
                "sidebar.querySelectorAll('.col-item').forEach(item => {\n" +
                "    item.addEventListener('click', () => {\n" +
                "        sidebar.querySelectorAll('.col-item').forEach(i => i.classList.remove('highlight'));\n" +
                "        item.classList.add('highlight');\n" +
                "        const id = item.getAttribute('data-id');\n" +
                "        edgeElements.forEach(ee => {\n" +
                "            ee.path.classList.toggle('highlight', ee.srcId === id || ee.tgtId === id);\n" +
                "        });\n" +
                "        svg.querySelectorAll('.node-group').forEach(g => {\n" +
                "            g.classList.toggle('highlight', g.getAttribute('data-id') === id);\n" +
                "        });\n" +
                "    });\n" +
                "});\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>\n";
    }

    public void toHtmlFile(String filePath, String title) throws IOException {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            writer.write(toHtml(title));
        }
    }

    private String escHtml(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    }

    // ============================================================
    // ASCII 文本输出
    // ============================================================

    /**
     * 生成 ASCII 文本格式的血缘报告
     */
    public String toAsciiReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════════════════╗\n");
        sb.append("║           SQL 血缘分析报告 (Data Lineage)            ║\n");
        sb.append("╚══════════════════════════════════════════════════════╝\n\n");

        sb.append("📋 SQL: ").append(sql).append("\n");
        sb.append("📌 语句类型: ").append(statementType).append("\n\n");

        // 表信息
        sb.append("┌─ 📊 表信息 ─────────────────────────────────────────\n");
        for (TableNode t : tables) {
            String icon = t.role == TableRole.SOURCE ? "📥" : (t.role == TableRole.TARGET ? "🎯" : "🔄");
            sb.append("│  ").append(icon).append(" ").append(t.name);
            if (t.alias != null) sb.append(" (").append(t.alias).append(")");
            sb.append(" [").append(t.role).append("]\n");
        }
        sb.append("└────────────────────────────────────────────────────\n\n");

        // 列级血缘
        sb.append("┌─ 🔗 列级血缘 ───────────────────────────────────────\n");
        if (edges.isEmpty()) {
            sb.append("│  (无列级血缘关系)\n");
        } else {
            for (LineageEdge e : edges) {
                sb.append("│  ").append(e.source.getId())
                        .append("  ──[").append(e.transform.getDisplayName()).append("]──▶  ")
                        .append(e.target.getId()).append("\n");
                if (e.description != null) {
                    sb.append("│     └─ ").append(e.description).append("\n");
                }
            }
        }
        sb.append("└────────────────────────────────────────────────────\n\n");

        // 统计
        sb.append("📈 统计: ").append(tables.size()).append(" 个表, ")
                .append(sourceColumns.size()).append(" 个来源列, ")
                .append(targetColumns.size()).append(" 个目标列, ")
                .append(edges.size()).append(" 条血缘边\n");

        return sb.toString();
    }

    @Override
    public String toString() {
        return toAsciiReport();
    }
}
