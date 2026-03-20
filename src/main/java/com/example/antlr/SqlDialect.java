package com.example.antlr;

/**
 * 支持的 SQL 方言类型枚举
 */
public enum SqlDialect {
    MYSQL("MySQL"),
    POSTGRESQL("PostgreSQL"),
    SPARKSQL("SparkSQL");

    private final String displayName;

    SqlDialect(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * 从字符串解析 SQL 方言
     */
    public static SqlDialect fromString(String dialect) {
        if (dialect == null) {
            throw new IllegalArgumentException("方言不能为空");
        }
        switch (dialect.toLowerCase().trim()) {
            case "mysql":
                return MYSQL;
            case "postgresql":
            case "postgres":
            case "pg":
                return POSTGRESQL;
            case "sparksql":
            case "spark":
                return SPARKSQL;
            default:
                throw new IllegalArgumentException("不支持的 SQL 方言: " + dialect + "，支持: mysql, postgresql/pg, sparksql/spark");
        }
    }
}
