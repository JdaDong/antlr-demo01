package com.example.antlr.api.dto;

/**
 * SQL 格式化结果
 */
public class FormatResult {

    private String originalSql;
    private String formattedSql;

    public FormatResult() {}

    public FormatResult(String originalSql, String formattedSql) {
        this.originalSql = originalSql;
        this.formattedSql = formattedSql;
    }

    public String getOriginalSql() { return originalSql; }
    public void setOriginalSql(String originalSql) { this.originalSql = originalSql; }

    public String getFormattedSql() { return formattedSql; }
    public void setFormattedSql(String formattedSql) { this.formattedSql = formattedSql; }
}
