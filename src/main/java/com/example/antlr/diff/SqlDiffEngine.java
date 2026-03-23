package com.example.antlr.diff;

import com.example.antlr.SqlDialect;
import com.example.antlr.SqlParserEngine;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Trees;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.antlr.mysql.MySqlLexer;
import com.example.antlr.mysql.MySqlParser;
import com.example.antlr.postgresql.PostgreSqlLexer;
import com.example.antlr.postgresql.PostgreSqlParser;
import com.example.antlr.sparksql.SparkSqlLexer;
import com.example.antlr.sparksql.SparkSqlParser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SQL Diff 引擎 — 比较两条 SQL 语句的语法树差异
 *
 * <p>支持三种比较模式：</p>
 * <ul>
 *   <li><b>结构比较</b> — 比较语法树的结构（规则节点类型）</li>
 *   <li><b>完整比较</b> — 比较语法树的结构 + 叶子节点内容</li>
 *   <li><b>语义摘要比较</b> — 比较关键语义信息（表、列、WHERE 条件等）</li>
 * </ul>
 */
public class SqlDiffEngine {

    private static final Logger log = LoggerFactory.getLogger(SqlDiffEngine.class);

    /**
     * 比较两条 SQL 语句，返回差异报告
     */
    public SqlDiffResult diff(String sql1, String sql2, SqlDialect dialect) {
        return diff(sql1, sql2, dialect, DiffMode.FULL);
    }

    public SqlDiffResult diff(String sql1, String sql2, SqlDialect dialect, DiffMode mode) {
        log.debug("开始 SQL Diff [方言={}, 模式={}]", dialect, mode);
        ParseTree tree1 = parseToTree(sql1, dialect);
        ParseTree tree2 = parseToTree(sql2, dialect);
        String[] ruleNames = getRuleNames(sql1, dialect);

        SqlDiffResult result = new SqlDiffResult(sql1, sql2, dialect);

        switch (mode) {
            case STRUCTURE:
                compareStructure(tree1, tree2, ruleNames, "", result);
                break;
            case FULL:
                compareFull(tree1, tree2, ruleNames, "", result);
                break;
            case SEMANTIC:
                compareSemantic(sql1, sql2, dialect, result);
                break;
        }

        log.info("SQL Diff 完成: {} 处差异 [方言={}, 模式={}]", result.getDiffCount(), dialect, mode);
        return result;
    }

    // ============================================================
    // 结构比较
    // ============================================================

    private void compareStructure(ParseTree t1, ParseTree t2, String[] ruleNames, String path, SqlDiffResult result) {
        String type1 = getNodeType(t1, ruleNames);
        String type2 = getNodeType(t2, ruleNames);

        if (!type1.equals(type2)) {
            result.addDiff(new DiffEntry(DiffType.MODIFIED, path,
                    "节点类型不同: " + type1 + " → " + type2));
            return;
        }

        int count1 = t1.getChildCount();
        int count2 = t2.getChildCount();
        int minCount = Math.min(count1, count2);

        for (int i = 0; i < minCount; i++) {
            compareStructure(t1.getChild(i), t2.getChild(i), ruleNames, path + "/" + type1 + "[" + i + "]", result);
        }

        if (count1 > count2) {
            for (int i = minCount; i < count1; i++) {
                result.addDiff(new DiffEntry(DiffType.REMOVED, path + "/" + type1 + "[" + i + "]",
                        "SQL1 多出节点: " + getNodeType(t1.getChild(i), ruleNames) + " = " + truncate(t1.getChild(i).getText(), 60)));
            }
        } else if (count2 > count1) {
            for (int i = minCount; i < count2; i++) {
                result.addDiff(new DiffEntry(DiffType.ADDED, path + "/" + type1 + "[" + i + "]",
                        "SQL2 多出节点: " + getNodeType(t2.getChild(i), ruleNames) + " = " + truncate(t2.getChild(i).getText(), 60)));
            }
        }
    }

    // ============================================================
    // 完整比较（结构 + 内容）
    // ============================================================

    private void compareFull(ParseTree t1, ParseTree t2, String[] ruleNames, String path, SqlDiffResult result) {
        String type1 = getNodeType(t1, ruleNames);
        String type2 = getNodeType(t2, ruleNames);

        // 叶子节点直接比较文本
        if (t1.getChildCount() == 0 && t2.getChildCount() == 0) {
            String text1 = t1.getText().trim();
            String text2 = t2.getText().trim();
            if (!text1.equalsIgnoreCase(text2)) {
                result.addDiff(new DiffEntry(DiffType.MODIFIED, path,
                        "值不同: \"" + truncate(text1, 40) + "\" → \"" + truncate(text2, 40) + "\""));
            }
            return;
        }

        if (!type1.equals(type2)) {
            result.addDiff(new DiffEntry(DiffType.MODIFIED, path,
                    "节点类型不同: " + type1 + " → " + type2));
            return;
        }

        int count1 = t1.getChildCount();
        int count2 = t2.getChildCount();
        int minCount = Math.min(count1, count2);

        for (int i = 0; i < minCount; i++) {
            compareFull(t1.getChild(i), t2.getChild(i), ruleNames, path + "/" + type1 + "[" + i + "]", result);
        }

        if (count1 > count2) {
            for (int i = minCount; i < count1; i++) {
                result.addDiff(new DiffEntry(DiffType.REMOVED, path + "/" + type1 + "[" + i + "]",
                        "SQL1 多出: " + truncate(t1.getChild(i).getText(), 60)));
            }
        } else if (count2 > count1) {
            for (int i = minCount; i < count2; i++) {
                result.addDiff(new DiffEntry(DiffType.ADDED, path + "/" + type1 + "[" + i + "]",
                        "SQL2 多出: " + truncate(t2.getChild(i).getText(), 60)));
            }
        }
    }

    // ============================================================
    // 语义摘要比较
    // ============================================================

    private void compareSemantic(String sql1, String sql2, SqlDialect dialect, SqlDiffResult result) {
        SqlParserEngine engine = new SqlParserEngine();

        Map<String, String> meta1 = extractSemanticInfo(sql1, dialect, engine);
        Map<String, String> meta2 = extractSemanticInfo(sql2, dialect, engine);

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(meta1.keySet());
        allKeys.addAll(meta2.keySet());

        for (String key : allKeys) {
            String v1 = meta1.getOrDefault(key, "(无)");
            String v2 = meta2.getOrDefault(key, "(无)");
            if (!v1.equals(v2)) {
                result.addDiff(new DiffEntry(DiffType.MODIFIED, key,
                        "SQL1: " + truncate(v1, 80) + "\nSQL2: " + truncate(v2, 80)));
            }
        }
    }

    private Map<String, String> extractSemanticInfo(String sql, SqlDialect dialect, SqlParserEngine engine) {
        Map<String, String> info = new LinkedHashMap<>();
        switch (dialect) {
            case MYSQL: {
                var meta = engine.extractMySqlMetadata(sql);
                info.put("语句类型", String.valueOf(meta.getStatementType()));
                info.put("表", String.join(", ", meta.getTables()));
                info.put("列", String.join(", ", meta.getColumns()));
                info.put("函数", String.join(", ", meta.getFunctions()));
                info.put("WHERE 列", String.join(", ", meta.getWhereColumns()));
                info.put("JOIN", String.join("; ", meta.getJoinConditions()));
                info.put("ORDER BY", String.join(", ", meta.getOrderByColumns()));
                info.put("GROUP BY", String.join(", ", meta.getGroupByColumns()));
                info.put("特性", buildFlags(meta.isHasSubQuery(), meta.isHasDistinct(), meta.isHasUnion(), false));
                break;
            }
            case POSTGRESQL: {
                var meta = engine.extractPostgreSqlMetadata(sql);
                info.put("语句类型", String.valueOf(meta.getStatementType()));
                info.put("表", String.join(", ", meta.getTables()));
                info.put("列", String.join(", ", meta.getColumns()));
                info.put("函数", String.join(", ", meta.getFunctions()));
                info.put("WHERE 列", String.join(", ", meta.getWhereColumns()));
                info.put("JOIN", String.join("; ", meta.getJoinConditions()));
                info.put("ORDER BY", String.join(", ", meta.getOrderByColumns()));
                info.put("GROUP BY", String.join(", ", meta.getGroupByColumns()));
                info.put("CTE", String.join(", ", meta.getCteNames()));
                info.put("特性", buildFlags(meta.isHasSubQuery(), meta.isHasDistinct(), meta.isHasUnion(), meta.isHasCte()));
                break;
            }
            case SPARKSQL: {
                var meta = engine.extractSparkSqlMetadata(sql);
                info.put("语句类型", String.valueOf(meta.getStatementType()));
                info.put("表", String.join(", ", meta.getTables()));
                info.put("列", String.join(", ", meta.getColumns()));
                info.put("函数", String.join(", ", meta.getFunctions()));
                info.put("WHERE 列", String.join(", ", meta.getWhereColumns()));
                info.put("JOIN", String.join("; ", meta.getJoinConditions()));
                info.put("ORDER BY", String.join(", ", meta.getOrderByColumns()));
                info.put("GROUP BY", String.join(", ", meta.getGroupByColumns()));
                info.put("CTE", String.join(", ", meta.getCteNames()));
                info.put("特性", buildFlags(meta.isHasSubQuery(), meta.isHasDistinct(), meta.isHasUnion(), meta.isHasCte()));
                break;
            }
        }
        // 移除空值
        info.entrySet().removeIf(e -> e.getValue() == null || e.getValue().isEmpty() || e.getValue().equals("null"));
        return info;
    }

    private String buildFlags(boolean hasSubQuery, boolean hasDistinct, boolean hasUnion, boolean hasCte) {
        List<String> flags = new ArrayList<>();
        if (hasSubQuery) flags.add("子查询");
        if (hasDistinct) flags.add("DISTINCT");
        if (hasUnion) flags.add("UNION");
        if (hasCte) flags.add("CTE");
        return flags.isEmpty() ? "" : String.join(", ", flags);
    }

    // ============================================================
    // 解析工具
    // ============================================================

    private ParseTree parseToTree(String sql, SqlDialect dialect) {
        switch (dialect) {
            case MYSQL: {
                MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
                lexer.removeErrorListeners();
                MySqlParser parser = new MySqlParser(new CommonTokenStream(lexer));
                parser.removeErrorListeners();
                return parser.root();
            }
            case POSTGRESQL: {
                PostgreSqlLexer lexer = new PostgreSqlLexer(CharStreams.fromString(sql));
                lexer.removeErrorListeners();
                PostgreSqlParser parser = new PostgreSqlParser(new CommonTokenStream(lexer));
                parser.removeErrorListeners();
                return parser.root();
            }
            case SPARKSQL: {
                SparkSqlLexer lexer = new SparkSqlLexer(CharStreams.fromString(sql));
                lexer.removeErrorListeners();
                SparkSqlParser parser = new SparkSqlParser(new CommonTokenStream(lexer));
                parser.removeErrorListeners();
                return parser.root();
            }
            default:
                throw new IllegalArgumentException("不支持的方言: " + dialect);
        }
    }

    private String[] getRuleNames(String sql, SqlDialect dialect) {
        switch (dialect) {
            case MYSQL: {
                MySqlLexer lexer = new MySqlLexer(CharStreams.fromString(sql));
                MySqlParser parser = new MySqlParser(new CommonTokenStream(lexer));
                return parser.getRuleNames();
            }
            case POSTGRESQL: {
                PostgreSqlLexer lexer = new PostgreSqlLexer(CharStreams.fromString(sql));
                PostgreSqlParser parser = new PostgreSqlParser(new CommonTokenStream(lexer));
                return parser.getRuleNames();
            }
            case SPARKSQL: {
                SparkSqlLexer lexer = new SparkSqlLexer(CharStreams.fromString(sql));
                SparkSqlParser parser = new SparkSqlParser(new CommonTokenStream(lexer));
                return parser.getRuleNames();
            }
            default:
                return new String[0];
        }
    }

    private String getNodeType(ParseTree tree, String[] ruleNames) {
        if (tree instanceof ParserRuleContext) {
            int ruleIndex = ((ParserRuleContext) tree).getRuleIndex();
            if (ruleIndex >= 0 && ruleIndex < ruleNames.length) {
                return ruleNames[ruleIndex];
            }
        }
        return tree.getClass().getSimpleName();
    }

    private String truncate(String s, int maxLen) {
        if (s == null) return "";
        s = s.replace("\n", " ").replace("\r", "");
        return s.length() <= maxLen ? s : s.substring(0, maxLen) + "...";
    }

    // ============================================================
    // 内部类
    // ============================================================

    public enum DiffMode {
        /** 只比较语法树结构（规则节点类型） */
        STRUCTURE,
        /** 比较结构 + 叶子节点内容 */
        FULL,
        /** 比较语义摘要（表、列、条件等） */
        SEMANTIC
    }

    public enum DiffType {
        ADDED("新增"),
        REMOVED("删除"),
        MODIFIED("修改");

        private final String label;
        DiffType(String label) { this.label = label; }
        public String getLabel() { return label; }
    }

    public static class DiffEntry {
        private final DiffType type;
        private final String path;
        private final String description;

        public DiffEntry(DiffType type, String path, String description) {
            this.type = type;
            this.path = path;
            this.description = description;
        }

        public DiffType getType() { return type; }
        public String getPath() { return path; }
        public String getDescription() { return description; }

        @Override
        public String toString() {
            String icon = type == DiffType.ADDED ? "➕" : (type == DiffType.REMOVED ? "➖" : "✏️");
            return icon + " [" + type.getLabel() + "] " + path + "\n   " + description;
        }
    }

    public static class SqlDiffResult {
        private final String sql1;
        private final String sql2;
        private final SqlDialect dialect;
        private final List<DiffEntry> diffs = new ArrayList<>();

        public SqlDiffResult(String sql1, String sql2, SqlDialect dialect) {
            this.sql1 = sql1;
            this.sql2 = sql2;
            this.dialect = dialect;
        }

        public void addDiff(DiffEntry entry) {
            diffs.add(entry);
        }

        public List<DiffEntry> getDiffs() {
            return Collections.unmodifiableList(diffs);
        }

        public boolean isIdentical() {
            return diffs.isEmpty();
        }

        public int getDiffCount() {
            return diffs.size();
        }

        public long getAddedCount() {
            return diffs.stream().filter(d -> d.type == DiffType.ADDED).count();
        }

        public long getRemovedCount() {
            return diffs.stream().filter(d -> d.type == DiffType.REMOVED).count();
        }

        public long getModifiedCount() {
            return diffs.stream().filter(d -> d.type == DiffType.MODIFIED).count();
        }

        public String getReport() {
            StringBuilder sb = new StringBuilder();
            sb.append("╔══════════════════════════════════════════════════════╗\n");
            sb.append("║              SQL Diff 比较报告                       ║\n");
            sb.append("╚══════════════════════════════════════════════════════╝\n\n");

            sb.append("📝 SQL 1: ").append(truncateStr(sql1, 100)).append("\n");
            sb.append("📝 SQL 2: ").append(truncateStr(sql2, 100)).append("\n");
            sb.append("🔧 方言: ").append(dialect).append("\n\n");

            if (isIdentical()) {
                sb.append("✅ 两条 SQL 语句完全一致（无差异）\n");
            } else {
                sb.append("📊 差异统计: 共 ").append(getDiffCount()).append(" 处差异");
                sb.append(" (➕新增: ").append(getAddedCount());
                sb.append(", ➖删除: ").append(getRemovedCount());
                sb.append(", ✏️修改: ").append(getModifiedCount()).append(")\n\n");

                sb.append("┌─ 差异详情 ──────────────────────────────────────────\n");
                for (DiffEntry diff : diffs) {
                    sb.append("│ ").append(diff).append("\n");
                }
                sb.append("└────────────────────────────────────────────────────\n");
            }

            return sb.toString();
        }

        private String truncateStr(String s, int maxLen) {
            if (s == null) return "";
            s = s.replace("\n", " ").replace("\r", "");
            return s.length() <= maxLen ? s : s.substring(0, maxLen) + "...";
        }

        @Override
        public String toString() {
            return getReport();
        }
    }
}
