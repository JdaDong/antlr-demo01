package com.example.antlr.api.dto;

/**
 * 通用 SQL 请求体
 */
public class SqlRequest {

    /** SQL 语句（必填） */
    private String sql;

    /** SQL 方言: mysql / postgresql / sparksql（可选，默认 mysql） */
    private String dialect = "mysql";

    /** 格式化缩进大小（可选，默认 4） */
    private int indentSize = 4;

    /** 关键字是否大写（可选，默认 true） */
    private boolean uppercaseKeywords = true;

    /** 血缘分析 HTML 标题（可选） */
    private String title = "SQL 血缘分析";

    public SqlRequest() {}

    public String getSql() { return sql; }
    public void setSql(String sql) { this.sql = sql; }

    public String getDialect() { return dialect; }
    public void setDialect(String dialect) { this.dialect = dialect; }

    public int getIndentSize() { return indentSize; }
    public void setIndentSize(int indentSize) { this.indentSize = indentSize; }

    public boolean isUppercaseKeywords() { return uppercaseKeywords; }
    public void setUppercaseKeywords(boolean uppercaseKeywords) { this.uppercaseKeywords = uppercaseKeywords; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
