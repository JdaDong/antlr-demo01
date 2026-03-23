package com.example.antlr.visual;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * ANTLR 语法树可视化工具
 * <p>
 * 支持三种输出格式：
 * <ul>
 *     <li><b>DOT</b> — Graphviz 格式，可用 dot 命令渲染为 PNG/SVG</li>
 *     <li><b>JSON</b> — 树形 JSON 结构，适合 D3.js 等前端可视化</li>
 *     <li><b>HTML</b> — 自包含的交互式 HTML 页面，浏览器直接打开</li>
 * </ul>
 */
public class ParseTreeVisualizer {

    private final Parser parser;
    private final List<String> ruleNames;

    /**
     * @param parser ANTLR 解析器实例（用于获取规则名称）
     */
    public ParseTreeVisualizer(Parser parser) {
        this.parser = parser;
        this.ruleNames = Arrays.asList(parser.getRuleNames());
    }

    // ================================================================
    // DOT 格式（Graphviz）
    // ================================================================

    /**
     * 将语法树导出为 DOT 格式字符串
     */
    public String toDot(ParseTree tree) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph ParseTree {\n");
        sb.append("    rankdir=TB;\n");
        sb.append("    node [shape=box, style=\"rounded,filled\", fontname=\"Consolas, monospace\", fontsize=12];\n");
        sb.append("    edge [color=\"#666666\"];\n");
        sb.append("\n");

        int[] nodeId = {0};
        buildDot(tree, sb, nodeId);

        sb.append("}\n");
        return sb.toString();
    }

    /**
     * 将语法树导出为 DOT 文件
     */
    public void toDotFile(ParseTree tree, String filePath) throws IOException {
        String dot = toDot(tree);
        writeFile(filePath, dot);
    }

    // ================================================================
    // Graphviz-java 渲染（直接生成 PNG/SVG，无需系统安装 Graphviz）
    // ================================================================

    /**
     * 使用 graphviz-java 将语法树渲染为 PNG 文件
     *
     * @param tree     语法树
     * @param filePath 输出 PNG 文件路径
     */
    public void renderToPng(ParseTree tree, String filePath) throws IOException {
        String dot = toDot(tree);
        Graphviz.fromString(dot)
                .width(1600)
                .render(Format.PNG)
                .toFile(new File(filePath));
    }

    /**
     * 使用 graphviz-java 将语法树渲染为 SVG 文件
     *
     * @param tree     语法树
     * @param filePath 输出 SVG 文件路径
     */
    public void renderToSvg(ParseTree tree, String filePath) throws IOException {
        String dot = toDot(tree);
        Graphviz.fromString(dot)
                .width(1600)
                .render(Format.SVG)
                .toFile(new File(filePath));
    }

    /**
     * 使用 graphviz-java 将语法树渲染为 SVG 字符串
     *
     * @param tree 语法树
     * @return SVG 格式字符串
     */
    public String renderToSvgString(ParseTree tree) {
        String dot = toDot(tree);
        return Graphviz.fromString(dot)
                .width(1600)
                .render(Format.SVG)
                .toString();
    }

    private int buildDot(ParseTree node, StringBuilder sb, int[] idCounter) {
        int currentId = idCounter[0]++;

        if (node instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode) node;
            String text = escapeGraphviz(terminal.getText());
            if (terminal instanceof ErrorNode) {
                sb.append(String.format("    n%d [label=\"❌ %s\", fillcolor=\"#FFCCCC\"];\n",
                        currentId, text));
            } else if ("<EOF>".equals(terminal.getText())) {
                sb.append(String.format("    n%d [label=\"EOF\", fillcolor=\"#CCCCCC\", fontcolor=\"#999999\"];\n",
                        currentId));
            } else {
                sb.append(String.format("    n%d [label=\"'%s'\", fillcolor=\"#D4EDDA\", fontcolor=\"#155724\"];\n",
                        currentId, text));
            }
        } else if (node instanceof RuleContext) {
            RuleContext ruleCtx = (RuleContext) node;
            String ruleName = ruleNames.get(ruleCtx.getRuleIndex());
            sb.append(String.format("    n%d [label=\"%s\", fillcolor=\"#CCE5FF\", fontcolor=\"#004085\"];\n",
                    currentId, ruleName));
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            int childId = buildDot(node.getChild(i), sb, idCounter);
            sb.append(String.format("    n%d -> n%d;\n", currentId, childId));
        }

        return currentId;
    }

    // ================================================================
    // JSON 格式
    // ================================================================

    /**
     * 将语法树导出为 JSON 字符串
     */
    public String toJson(ParseTree tree) {
        StringBuilder sb = new StringBuilder();
        buildJson(tree, sb, 0);
        return sb.toString();
    }

    /**
     * 将语法树导出为 JSON 文件
     */
    public void toJsonFile(ParseTree tree, String filePath) throws IOException {
        String json = toJson(tree);
        writeFile(filePath, json);
    }

    private void buildJson(ParseTree node, StringBuilder sb, int indent) {
        String pad = repeat("  ", indent);

        sb.append(pad).append("{\n");

        if (node instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode) node;
            sb.append(pad).append("  \"type\": \"terminal\",\n");
            sb.append(pad).append("  \"text\": ").append(jsonStr(terminal.getText())).append(",\n");
            sb.append(pad).append("  \"tokenType\": ").append(terminal.getSymbol().getType()).append(",\n");
            sb.append(pad).append("  \"line\": ").append(terminal.getSymbol().getLine()).append(",\n");
            sb.append(pad).append("  \"column\": ").append(terminal.getSymbol().getCharPositionInLine()).append(",\n");
            sb.append(pad).append("  \"error\": ").append(terminal instanceof ErrorNode).append("\n");
        } else if (node instanceof RuleContext) {
            RuleContext ruleCtx = (RuleContext) node;
            String ruleName = ruleNames.get(ruleCtx.getRuleIndex());
            sb.append(pad).append("  \"type\": \"rule\",\n");
            sb.append(pad).append("  \"name\": ").append(jsonStr(ruleName)).append(",\n");
            sb.append(pad).append("  \"ruleIndex\": ").append(ruleCtx.getRuleIndex()).append(",\n");

            sb.append(pad).append("  \"children\": [\n");
            for (int i = 0; i < node.getChildCount(); i++) {
                if (i > 0) sb.append(",\n");
                buildJson(node.getChild(i), sb, indent + 2);
            }
            sb.append("\n").append(pad).append("  ]\n");
        }

        sb.append(pad).append("}");
    }

    // ================================================================
    // HTML 格式（交互式可视化）
    // ================================================================

    /**
     * 将语法树导出为可交互的 HTML 页面
     */
    public String toHtml(ParseTree tree, String title) {
        String jsonData = toJson(tree);
        return buildHtml(jsonData, title);
    }

    /**
     * 将语法树导出为 HTML 文件
     */
    public void toHtmlFile(ParseTree tree, String filePath, String title) throws IOException {
        String html = toHtml(tree, title);
        writeFile(filePath, html);
    }

    private String buildHtml(String jsonData, String title) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>" + escapeHtml(title) + "</title>\n" +
                "    <style>\n" +
                "        * { margin: 0; padding: 0; box-sizing: border-box; }\n" +
                "        body {\n" +
                "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;\n" +
                "            background: #0f172a;\n" +
                "            color: #e2e8f0;\n" +
                "            overflow: hidden;\n" +
                "        }\n" +
                "        .toolbar {\n" +
                "            position: fixed; top: 0; left: 0; right: 0; z-index: 100;\n" +
                "            background: #1e293b;\n" +
                "            border-bottom: 1px solid #334155;\n" +
                "            padding: 10px 20px;\n" +
                "            display: flex; align-items: center; gap: 16px;\n" +
                "        }\n" +
                "        .toolbar h1 {\n" +
                "            font-size: 16px; color: #60a5fa; flex: 1;\n" +
                "        }\n" +
                "        .toolbar button {\n" +
                "            background: #334155; color: #e2e8f0; border: 1px solid #475569;\n" +
                "            padding: 6px 14px; border-radius: 6px; cursor: pointer;\n" +
                "            font-size: 13px; transition: all 0.2s;\n" +
                "        }\n" +
                "        .toolbar button:hover { background: #475569; }\n" +
                "        .toolbar .stats {\n" +
                "            font-size: 12px; color: #94a3b8;\n" +
                "        }\n" +
                "        #canvas-container {\n" +
                "            position: fixed; top: 50px; left: 0; right: 0; bottom: 0;\n" +
                "            overflow: hidden; cursor: grab;\n" +
                "        }\n" +
                "        #canvas-container.dragging { cursor: grabbing; }\n" +
                "        svg { width: 100%; height: 100%; }\n" +
                "        .node-group { cursor: pointer; }\n" +
                "        .node-rule rect {\n" +
                "            fill: #1e3a5f; stroke: #3b82f6; stroke-width: 1.5;\n" +
                "            rx: 6; ry: 6;\n" +
                "        }\n" +
                "        .node-rule:hover rect { fill: #1e40af; stroke: #60a5fa; stroke-width: 2; }\n" +
                "        .node-rule text { fill: #93c5fd; font-size: 12px; font-weight: 600; }\n" +
                "        .node-terminal rect {\n" +
                "            fill: #14532d; stroke: #22c55e; stroke-width: 1.5;\n" +
                "            rx: 6; ry: 6;\n" +
                "        }\n" +
                "        .node-terminal:hover rect { fill: #166534; stroke: #4ade80; stroke-width: 2; }\n" +
                "        .node-terminal text { fill: #86efac; font-size: 12px; font-family: 'Consolas', monospace; }\n" +
                "        .node-eof rect {\n" +
                "            fill: #1e293b; stroke: #475569; stroke-width: 1;\n" +
                "            rx: 6; ry: 6;\n" +
                "        }\n" +
                "        .node-eof text { fill: #64748b; font-size: 11px; }\n" +
                "        .node-error rect {\n" +
                "            fill: #7f1d1d; stroke: #ef4444; stroke-width: 2;\n" +
                "            rx: 6; ry: 6;\n" +
                "        }\n" +
                "        .node-error text { fill: #fca5a5; font-size: 12px; }\n" +
                "        .node-collapsed rect { stroke-dasharray: 5,3; opacity: 0.7; }\n" +
                "        .link {\n" +
                "            fill: none; stroke: #475569; stroke-width: 1.2;\n" +
                "        }\n" +
                "        .link:hover { stroke: #60a5fa; stroke-width: 2; }\n" +
                "        .tooltip {\n" +
                "            position: fixed; background: #1e293b; border: 1px solid #475569;\n" +
                "            border-radius: 8px; padding: 10px 14px;\n" +
                "            font-size: 12px; pointer-events: none;\n" +
                "            opacity: 0; transition: opacity 0.15s;\n" +
                "            box-shadow: 0 4px 12px rgba(0,0,0,0.4);\n" +
                "            max-width: 300px; z-index: 200;\n" +
                "        }\n" +
                "        .tooltip.show { opacity: 1; }\n" +
                "        .tooltip .tt-label { color: #94a3b8; margin-bottom: 2px; }\n" +
                "        .tooltip .tt-value { color: #f1f5f9; font-weight: 600; }\n" +
                "        .search-box {\n" +
                "            background: #0f172a; color: #e2e8f0; border: 1px solid #475569;\n" +
                "            padding: 6px 10px; border-radius: 6px; font-size: 13px; width: 180px;\n" +
                "        }\n" +
                "        .search-box:focus { outline: none; border-color: #3b82f6; }\n" +
                "        .highlight rect { stroke: #f59e0b !important; stroke-width: 3 !important; }\n" +
                "        .minimap {\n" +
                "            position: fixed; bottom: 16px; right: 16px;\n" +
                "            width: 180px; height: 120px;\n" +
                "            background: #1e293b; border: 1px solid #334155;\n" +
                "            border-radius: 8px; overflow: hidden; z-index: 100;\n" +
                "        }\n" +
                "        .minimap .viewport {\n" +
                "            position: absolute; border: 1.5px solid #3b82f6;\n" +
                "            background: rgba(59,130,246,0.1);\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"toolbar\">\n" +
                "        <h1>🌳 " + escapeHtml(title) + "</h1>\n" +
                "        <input type=\"text\" class=\"search-box\" id=\"searchInput\" placeholder=\"搜索节点...\">\n" +
                "        <button onclick=\"zoomIn()\">➕ 放大</button>\n" +
                "        <button onclick=\"zoomOut()\">➖ 缩小</button>\n" +
                "        <button onclick=\"resetView()\">🔄 重置</button>\n" +
                "        <button onclick=\"expandAll()\">📂 全部展开</button>\n" +
                "        <button onclick=\"collapseLevel(2)\">📁 折叠到2层</button>\n" +
                "        <button onclick=\"exportSvg()\">💾 导出SVG</button>\n" +
                "        <span class=\"stats\" id=\"stats\"></span>\n" +
                "    </div>\n" +
                "    <div id=\"canvas-container\">\n" +
                "        <svg id=\"treeSvg\">\n" +
                "            <defs>\n" +
                "                <marker id=\"arrowhead\" markerWidth=\"6\" markerHeight=\"4\" refX=\"6\" refY=\"2\" orient=\"auto\">\n" +
                "                    <polygon points=\"0 0, 6 2, 0 4\" fill=\"#475569\"/>\n" +
                "                </marker>\n" +
                "            </defs>\n" +
                "            <g id=\"treeGroup\"></g>\n" +
                "        </svg>\n" +
                "    </div>\n" +
                "    <div class=\"minimap\" id=\"minimap\">\n" +
                "        <svg id=\"minimapSvg\" width=\"180\" height=\"120\"></svg>\n" +
                "        <div class=\"viewport\" id=\"minimapViewport\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"tooltip\" id=\"tooltip\"></div>\n" +
                "\n" +
                "<script>\n" +
                "// ============ 数据 ============\n" +
                "const treeData = " + jsonData + ";\n" +
                "\n" +
                "// ============ 布局计算 ============\n" +
                "const NODE_H = 32, NODE_PAD_X = 16, V_GAP = 50, H_GAP = 12;\n" +
                "let nodeCount = 0, maxDepth = 0;\n" +
                "\n" +
                "function getLabel(node) {\n" +
                "    if (node.type === 'terminal') {\n" +
                "        return node.text === '<EOF>' ? 'EOF' : node.text;\n" +
                "    }\n" +
                "    return node.name || 'unknown';\n" +
                "}\n" +
                "\n" +
                "function getNodeClass(node) {\n" +
                "    if (node.type === 'terminal') {\n" +
                "        if (node.error) return 'node-error';\n" +
                "        if (node.text === '<EOF>') return 'node-eof';\n" +
                "        return 'node-terminal';\n" +
                "    }\n" +
                "    return 'node-rule';\n" +
                "}\n" +
                "\n" +
                "// 测量文字宽度\n" +
                "const measureCanvas = document.createElement('canvas').getContext('2d');\n" +
                "measureCanvas.font = '12px Consolas, monospace';\n" +
                "function textWidth(str) {\n" +
                "    return measureCanvas.measureText(str).width;\n" +
                "}\n" +
                "\n" +
                "// 分配 id，计算节点宽度\n" +
                "function prepareNode(node, depth) {\n" +
                "    node._id = nodeCount++;\n" +
                "    node._depth = depth;\n" +
                "    node._collapsed = false;\n" +
                "    maxDepth = Math.max(maxDepth, depth);\n" +
                "    const label = getLabel(node);\n" +
                "    node._label = label;\n" +
                "    node._w = Math.max(60, textWidth(label) + NODE_PAD_X * 2);\n" +
                "    node._h = NODE_H;\n" +
                "    if (node.children) {\n" +
                "        node.children.forEach(c => prepareNode(c, depth + 1));\n" +
                "    }\n" +
                "}\n" +
                "prepareNode(treeData, 0);\n" +
                "\n" +
                "// Reingold-Tilford 风格的树布局\n" +
                "function layoutTree(node) {\n" +
                "    if (node._collapsed || !node.children || node.children.length === 0) {\n" +
                "        node._subtreeW = node._w;\n" +
                "        return;\n" +
                "    }\n" +
                "    let totalW = 0;\n" +
                "    node.children.forEach(c => {\n" +
                "        layoutTree(c);\n" +
                "        totalW += c._subtreeW;\n" +
                "    });\n" +
                "    totalW += (node.children.length - 1) * H_GAP;\n" +
                "    node._subtreeW = Math.max(node._w, totalW);\n" +
                "}\n" +
                "\n" +
                "function positionTree(node, x, y) {\n" +
                "    node._x = x + node._subtreeW / 2 - node._w / 2;\n" +
                "    node._y = y;\n" +
                "    if (node._collapsed || !node.children || node.children.length === 0) return;\n" +
                "    let totalW = 0;\n" +
                "    node.children.forEach(c => totalW += c._subtreeW);\n" +
                "    totalW += (node.children.length - 1) * H_GAP;\n" +
                "    let cx = x + (node._subtreeW - totalW) / 2;\n" +
                "    node.children.forEach(c => {\n" +
                "        positionTree(c, cx, y + NODE_H + V_GAP);\n" +
                "        cx += c._subtreeW + H_GAP;\n" +
                "    });\n" +
                "}\n" +
                "\n" +
                "function doLayout() {\n" +
                "    layoutTree(treeData);\n" +
                "    positionTree(treeData, 0, 0);\n" +
                "}\n" +
                "doLayout();\n" +
                "\n" +
                "// ============ SVG 渲染 ============\n" +
                "const svg = document.getElementById('treeSvg');\n" +
                "const treeGroup = document.getElementById('treeGroup');\n" +
                "const tooltip = document.getElementById('tooltip');\n" +
                "\n" +
                "function renderTree() {\n" +
                "    treeGroup.innerHTML = '';\n" +
                "    const links = [];\n" +
                "    const nodes = [];\n" +
                "    collectNodes(treeData, nodes, links);\n" +
                "\n" +
                "    // 画连线\n" +
                "    links.forEach(({from, to}) => {\n" +
                "        const x1 = from._x + from._w / 2;\n" +
                "        const y1 = from._y + from._h;\n" +
                "        const x2 = to._x + to._w / 2;\n" +
                "        const y2 = to._y;\n" +
                "        const my = (y1 + y2) / 2;\n" +
                "        const path = document.createElementNS('http://www.w3.org/2000/svg', 'path');\n" +
                "        path.setAttribute('d', `M${x1},${y1} C${x1},${my} ${x2},${my} ${x2},${y2}`);\n" +
                "        path.setAttribute('class', 'link');\n" +
                "        treeGroup.appendChild(path);\n" +
                "    });\n" +
                "\n" +
                "    // 画节点\n" +
                "    nodes.forEach(node => {\n" +
                "        const g = document.createElementNS('http://www.w3.org/2000/svg', 'g');\n" +
                "        g.setAttribute('class', `node-group ${getNodeClass(node)}${node._collapsed ? ' node-collapsed' : ''}`);\n" +
                "        g.setAttribute('transform', `translate(${node._x}, ${node._y})`);\n" +
                "        g.setAttribute('data-id', node._id);\n" +
                "\n" +
                "        const rect = document.createElementNS('http://www.w3.org/2000/svg', 'rect');\n" +
                "        rect.setAttribute('width', node._w);\n" +
                "        rect.setAttribute('height', node._h);\n" +
                "        g.appendChild(rect);\n" +
                "\n" +
                "        const text = document.createElementNS('http://www.w3.org/2000/svg', 'text');\n" +
                "        text.setAttribute('x', node._w / 2);\n" +
                "        text.setAttribute('y', node._h / 2 + 4);\n" +
                "        text.setAttribute('text-anchor', 'middle');\n" +
                "        let label = node._label;\n" +
                "        if (label.length > 30) label = label.substring(0, 27) + '...';\n" +
                "        text.textContent = label;\n" +
                "        g.appendChild(text);\n" +
                "\n" +
                "        // 折叠指示器\n" +
                "        if (node.children && node.children.length > 0) {\n" +
                "            const indicator = document.createElementNS('http://www.w3.org/2000/svg', 'text');\n" +
                "            indicator.setAttribute('x', node._w - 8);\n" +
                "            indicator.setAttribute('y', 12);\n" +
                "            indicator.setAttribute('font-size', '10');\n" +
                "            indicator.setAttribute('fill', '#94a3b8');\n" +
                "            indicator.textContent = node._collapsed ? '▶' : '▼';\n" +
                "            g.appendChild(indicator);\n" +
                "        }\n" +
                "\n" +
                "        // 点击折叠/展开\n" +
                "        g.addEventListener('click', (e) => {\n" +
                "            e.stopPropagation();\n" +
                "            if (node.children && node.children.length > 0) {\n" +
                "                node._collapsed = !node._collapsed;\n" +
                "                doLayout();\n" +
                "                renderTree();\n" +
                "                updateMinimap();\n" +
                "            }\n" +
                "        });\n" +
                "\n" +
                "        // Tooltip\n" +
                "        g.addEventListener('mouseenter', (e) => {\n" +
                "            let info = '';\n" +
                "            if (node.type === 'rule') {\n" +
                "                const childCount = node.children ? node.children.length : 0;\n" +
                "                info = `<div class=\"tt-label\">规则节点</div>`\n" +
                "                     + `<div class=\"tt-value\">${node.name}</div>`\n" +
                "                     + `<div class=\"tt-label\">规则索引: ${node.ruleIndex} | 子节点: ${childCount}</div>`;\n" +
                "            } else {\n" +
                "                info = `<div class=\"tt-label\">终端节点</div>`\n" +
                "                     + `<div class=\"tt-value\">${escapeHtmlJs(node.text)}</div>`\n" +
                "                     + `<div class=\"tt-label\">Token类型: ${node.tokenType} | 行:${node.line} 列:${node.column}</div>`;\n" +
                "            }\n" +
                "            tooltip.innerHTML = info;\n" +
                "            tooltip.classList.add('show');\n" +
                "        });\n" +
                "        g.addEventListener('mousemove', (e) => {\n" +
                "            tooltip.style.left = (e.clientX + 12) + 'px';\n" +
                "            tooltip.style.top = (e.clientY + 12) + 'px';\n" +
                "        });\n" +
                "        g.addEventListener('mouseleave', () => {\n" +
                "            tooltip.classList.remove('show');\n" +
                "        });\n" +
                "\n" +
                "        treeGroup.appendChild(g);\n" +
                "    });\n" +
                "\n" +
                "    document.getElementById('stats').textContent =\n" +
                "        `节点: ${nodes.length} | 最大深度: ${maxDepth}`;\n" +
                "}\n" +
                "\n" +
                "function collectNodes(node, nodes, links) {\n" +
                "    nodes.push(node);\n" +
                "    if (!node._collapsed && node.children) {\n" +
                "        node.children.forEach(c => {\n" +
                "            links.push({from: node, to: c});\n" +
                "            collectNodes(c, nodes, links);\n" +
                "        });\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "renderTree();\n" +
                "\n" +
                "// ============ 平移 & 缩放 ============\n" +
                "let scale = 1, tx = 50, ty = 70;\n" +
                "let isDragging = false, dragStartX, dragStartY, startTx, startTy;\n" +
                "const container = document.getElementById('canvas-container');\n" +
                "\n" +
                "function applyTransform() {\n" +
                "    treeGroup.setAttribute('transform', `translate(${tx},${ty}) scale(${scale})`);\n" +
                "    updateMinimap();\n" +
                "}\n" +
                "applyTransform();\n" +
                "\n" +
                "container.addEventListener('mousedown', (e) => {\n" +
                "    if (e.target.closest('.node-group')) return;\n" +
                "    isDragging = true;\n" +
                "    container.classList.add('dragging');\n" +
                "    dragStartX = e.clientX; dragStartY = e.clientY;\n" +
                "    startTx = tx; startTy = ty;\n" +
                "});\n" +
                "window.addEventListener('mousemove', (e) => {\n" +
                "    if (!isDragging) return;\n" +
                "    tx = startTx + (e.clientX - dragStartX);\n" +
                "    ty = startTy + (e.clientY - dragStartY);\n" +
                "    applyTransform();\n" +
                "});\n" +
                "window.addEventListener('mouseup', () => {\n" +
                "    isDragging = false;\n" +
                "    container.classList.remove('dragging');\n" +
                "});\n" +
                "container.addEventListener('wheel', (e) => {\n" +
                "    e.preventDefault();\n" +
                "    const factor = e.deltaY > 0 ? 0.92 : 1.08;\n" +
                "    const rect = container.getBoundingClientRect();\n" +
                "    const mx = e.clientX - rect.left;\n" +
                "    const my = e.clientY - rect.top;\n" +
                "    tx = mx - (mx - tx) * factor;\n" +
                "    ty = my - (my - ty) * factor;\n" +
                "    scale *= factor;\n" +
                "    scale = Math.max(0.05, Math.min(3, scale));\n" +
                "    applyTransform();\n" +
                "}, {passive: false});\n" +
                "\n" +
                "function zoomIn() { scale = Math.min(3, scale * 1.2); applyTransform(); }\n" +
                "function zoomOut() { scale = Math.max(0.05, scale / 1.2); applyTransform(); }\n" +
                "function resetView() { scale = 1; tx = 50; ty = 70; applyTransform(); }\n" +
                "\n" +
                "// ============ 折叠控制 ============\n" +
                "function setCollapsed(node, collapsed, maxLevel, currentLevel) {\n" +
                "    if (!node.children || node.children.length === 0) return;\n" +
                "    node._collapsed = (currentLevel >= maxLevel) ? true : collapsed;\n" +
                "    if (!node._collapsed) {\n" +
                "        node.children.forEach(c => setCollapsed(c, collapsed, maxLevel, currentLevel + 1));\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "function expandAll() {\n" +
                "    setCollapsed(treeData, false, 9999, 0);\n" +
                "    doLayout(); renderTree(); applyTransform();\n" +
                "}\n" +
                "\n" +
                "function collapseLevel(level) {\n" +
                "    setCollapsed(treeData, false, level, 0);\n" +
                "    doLayout(); renderTree(); applyTransform();\n" +
                "}\n" +
                "\n" +
                "// ============ 搜索 ============\n" +
                "document.getElementById('searchInput').addEventListener('input', (e) => {\n" +
                "    const q = e.target.value.toLowerCase().trim();\n" +
                "    document.querySelectorAll('.node-group').forEach(g => {\n" +
                "        g.classList.remove('highlight');\n" +
                "    });\n" +
                "    if (!q) return;\n" +
                "    const allNodes = [];\n" +
                "    collectAllNodes(treeData, allNodes);\n" +
                "    allNodes.forEach(n => {\n" +
                "        if (n._label.toLowerCase().includes(q)) {\n" +
                "            const el = document.querySelector(`[data-id='${n._id}']`);\n" +
                "            if (el) el.classList.add('highlight');\n" +
                "        }\n" +
                "    });\n" +
                "});\n" +
                "\n" +
                "function collectAllNodes(node, arr) {\n" +
                "    arr.push(node);\n" +
                "    if (node.children) node.children.forEach(c => collectAllNodes(c, arr));\n" +
                "}\n" +
                "\n" +
                "function escapeHtmlJs(str) {\n" +
                "    return str.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\"/g,'&quot;');\n" +
                "}\n" +
                "\n" +
                "// ============ 小地图 ============\n" +
                "function updateMinimap() {\n" +
                "    const mmSvg = document.getElementById('minimapSvg');\n" +
                "    const vp = document.getElementById('minimapViewport');\n" +
                "    const allNodes = [];\n" +
                "    collectNodes(treeData, allNodes, []);\n" +
                "    if (allNodes.length === 0) return;\n" +
                "\n" +
                "    let minX = Infinity, minY = Infinity, maxX = -Infinity, maxY = -Infinity;\n" +
                "    allNodes.forEach(n => {\n" +
                "        minX = Math.min(minX, n._x);\n" +
                "        minY = Math.min(minY, n._y);\n" +
                "        maxX = Math.max(maxX, n._x + n._w);\n" +
                "        maxY = Math.max(maxY, n._y + n._h);\n" +
                "    });\n" +
                "\n" +
                "    const treeW = maxX - minX + 40, treeH = maxY - minY + 40;\n" +
                "    const mmW = 180, mmH = 120;\n" +
                "    const mmScale = Math.min(mmW / treeW, mmH / treeH);\n" +
                "\n" +
                "    let dots = '';\n" +
                "    allNodes.forEach(n => {\n" +
                "        const x = (n._x - minX + 20) * mmScale;\n" +
                "        const y = (n._y - minY + 20) * mmScale;\n" +
                "        const color = n.type === 'rule' ? '#3b82f6' : '#22c55e';\n" +
                "        dots += `<circle cx=\"${x}\" cy=\"${y}\" r=\"1.5\" fill=\"${color}\"/>`;\n" +
                "    });\n" +
                "    mmSvg.innerHTML = dots;\n" +
                "\n" +
                "    const cRect = container.getBoundingClientRect();\n" +
                "    const vpLeft = (-tx / scale - minX + 20) * mmScale;\n" +
                "    const vpTop = ((-ty + 50) / scale - minY + 20) * mmScale;\n" +
                "    const vpW = (cRect.width / scale) * mmScale;\n" +
                "    const vpH = (cRect.height / scale) * mmScale;\n" +
                "    vp.style.left = Math.max(0, vpLeft) + 'px';\n" +
                "    vp.style.top = Math.max(0, vpTop) + 'px';\n" +
                "    vp.style.width = Math.min(mmW, vpW) + 'px';\n" +
                "    vp.style.height = Math.min(mmH, vpH) + 'px';\n" +
                "}\n" +
                "updateMinimap();\n" +
                "\n" +
                "// ============ 导出 SVG ============\n" +
                "function exportSvg() {\n" +
                "    const clone = svg.cloneNode(true);\n" +
                "    clone.querySelector('#treeGroup').setAttribute('transform', 'translate(20,20) scale(1)');\n" +
                "    const blob = new Blob([clone.outerHTML], {type: 'image/svg+xml'});\n" +
                "    const url = URL.createObjectURL(blob);\n" +
                "    const a = document.createElement('a');\n" +
                "    a.href = url; a.download = 'parse-tree.svg'; a.click();\n" +
                "    URL.revokeObjectURL(url);\n" +
                "}\n" +
                "\n" +
                "// ============ 自适应 ============\n" +
                "function fitView() {\n" +
                "    const allNodes = [];\n" +
                "    collectNodes(treeData, allNodes, []);\n" +
                "    if (allNodes.length === 0) return;\n" +
                "    let minX = Infinity, minY = Infinity, maxX = -Infinity, maxY = -Infinity;\n" +
                "    allNodes.forEach(n => {\n" +
                "        minX = Math.min(minX, n._x);\n" +
                "        minY = Math.min(minY, n._y);\n" +
                "        maxX = Math.max(maxX, n._x + n._w);\n" +
                "        maxY = Math.max(maxY, n._y + n._h);\n" +
                "    });\n" +
                "    const cRect = container.getBoundingClientRect();\n" +
                "    const treeW = maxX - minX + 100;\n" +
                "    const treeH = maxY - minY + 100;\n" +
                "    scale = Math.min(cRect.width / treeW, cRect.height / treeH, 1.2);\n" +
                "    tx = (cRect.width - treeW * scale) / 2 - minX * scale + 50;\n" +
                "    ty = 70 - minY * scale + 20;\n" +
                "    applyTransform();\n" +
                "}\n" +
                "fitView();\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>";
    }

    // ================================================================
    // ASCII 树形文本（终端友好）
    // ================================================================

    /**
     * 将语法树导出为 ASCII 树形文本，适合在终端/日志中查看
     */
    public String toAsciiTree(ParseTree tree) {
        StringBuilder sb = new StringBuilder();
        buildAscii(tree, sb, "", true);
        return sb.toString();
    }

    private void buildAscii(ParseTree node, StringBuilder sb, String prefix, boolean isLast) {
        sb.append(prefix);
        sb.append(isLast ? "└── " : "├── ");

        if (node instanceof TerminalNode) {
            TerminalNode terminal = (TerminalNode) node;
            if (terminal instanceof ErrorNode) {
                sb.append("❌ ").append(terminal.getText());
            } else if ("<EOF>".equals(terminal.getText())) {
                sb.append("<EOF>");
            } else {
                sb.append("'").append(terminal.getText()).append("'");
            }
        } else if (node instanceof RuleContext) {
            RuleContext ruleCtx = (RuleContext) node;
            String ruleName = ruleNames.get(ruleCtx.getRuleIndex());
            sb.append(ruleName);
            if (node.getChildCount() > 0) {
                sb.append(" (").append(node.getChildCount()).append(")");
            }
        }
        sb.append("\n");

        String childPrefix = prefix + (isLast ? "    " : "│   ");
        for (int i = 0; i < node.getChildCount(); i++) {
            buildAscii(node.getChild(i), sb, childPrefix, i == node.getChildCount() - 1);
        }
    }

    // ================================================================
    // 工具方法
    // ================================================================

    private String escapeGraphviz(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "")
                .replace("<", "\\<")
                .replace(">", "\\>");
    }

    private String escapeHtml(String s) {
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }

    private String jsonStr(String s) {
        return "\"" + s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t") + "\"";
    }

    private String repeat(String s, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) sb.append(s);
        return sb.toString();
    }

    private void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Path parent = path.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (Writer writer = new OutputStreamWriter(
                new FileOutputStream(path.toFile()), StandardCharsets.UTF_8)) {
            writer.write(content);
        }
    }
}
