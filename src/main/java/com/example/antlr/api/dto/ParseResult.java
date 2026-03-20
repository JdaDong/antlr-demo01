package com.example.antlr.api.dto;

/**
 * SQL 解析结果
 */
public class ParseResult {

    private String dialect;
    private String originalSql;
    private String parseTree;
    private boolean success;
    private String errorMessage;
    private long parseTimeMs;

    public ParseResult() {}

    public ParseResult(String dialect, String originalSql, String parseTree,
                       boolean success, String errorMessage, long parseTimeMs) {
        this.dialect = dialect;
        this.originalSql = originalSql;
        this.parseTree = parseTree;
        this.success = success;
        this.errorMessage = errorMessage;
        this.parseTimeMs = parseTimeMs;
    }

    public String getDialect() { return dialect; }
    public void setDialect(String dialect) { this.dialect = dialect; }

    public String getOriginalSql() { return originalSql; }
    public void setOriginalSql(String originalSql) { this.originalSql = originalSql; }

    public String getParseTree() { return parseTree; }
    public void setParseTree(String parseTree) { this.parseTree = parseTree; }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public long getParseTimeMs() { return parseTimeMs; }
    public void setParseTimeMs(long parseTimeMs) { this.parseTimeMs = parseTimeMs; }
}
