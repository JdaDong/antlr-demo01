package com.example.antlr.api.dto;

/**
 * SQL 验证结果
 */
public class ValidateResult {

    private String sql;
    private String dialect;
    private boolean valid;

    public ValidateResult() {}

    public ValidateResult(String sql, String dialect, boolean valid) {
        this.sql = sql;
        this.dialect = dialect;
        this.valid = valid;
    }

    public String getSql() { return sql; }
    public void setSql(String sql) { this.sql = sql; }

    public String getDialect() { return dialect; }
    public void setDialect(String dialect) { this.dialect = dialect; }

    public boolean isValid() { return valid; }
    public void setValid(boolean valid) { this.valid = valid; }
}
