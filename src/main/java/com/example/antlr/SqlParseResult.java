package com.example.antlr;

/**
 * SQL 解析结果封装
 */
public class SqlParseResult {

    private final SqlDialect dialect;
    private final String originalSql;
    private final String parseTree;
    private final boolean success;
    private final String errorMessage;
    private final long parseTimeMs;

    private SqlParseResult(Builder builder) {
        this.dialect = builder.dialect;
        this.originalSql = builder.originalSql;
        this.parseTree = builder.parseTree;
        this.success = builder.success;
        this.errorMessage = builder.errorMessage;
        this.parseTimeMs = builder.parseTimeMs;
    }

    public SqlDialect getDialect() {
        return dialect;
    }

    public String getOriginalSql() {
        return originalSql;
    }

    public String getParseTree() {
        return parseTree;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getParseTimeMs() {
        return parseTimeMs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== SQL 解析结果 ===\n");
        sb.append("方言: ").append(dialect.getDisplayName()).append("\n");
        sb.append("SQL: ").append(originalSql).append("\n");
        sb.append("状态: ").append(success ? "✅ 成功" : "❌ 失败").append("\n");
        sb.append("耗时: ").append(parseTimeMs).append(" ms\n");
        if (!success) {
            sb.append("错误: ").append(errorMessage).append("\n");
        }
        if (parseTree != null) {
            sb.append("语法树:\n").append(parseTree).append("\n");
        }
        return sb.toString();
    }

    public static class Builder {
        private SqlDialect dialect;
        private String originalSql;
        private String parseTree;
        private boolean success;
        private String errorMessage;
        private long parseTimeMs;

        public Builder dialect(SqlDialect dialect) {
            this.dialect = dialect;
            return this;
        }

        public Builder originalSql(String originalSql) {
            this.originalSql = originalSql;
            return this;
        }

        public Builder parseTree(String parseTree) {
            this.parseTree = parseTree;
            return this;
        }

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder parseTimeMs(long parseTimeMs) {
            this.parseTimeMs = parseTimeMs;
            return this;
        }

        public SqlParseResult build() {
            return new SqlParseResult(this);
        }
    }
}
