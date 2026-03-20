package com.example.antlr.api.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SQL 元数据分析结果
 */
public class MetadataResult {

    private String statementType;
    private Set<String> tables;
    private Set<String> columns;
    private Map<String, String> aliases;
    private List<String> functions;
    private Set<String> whereColumns;
    private List<String> joinConditions;
    private List<String> orderByColumns;
    private List<String> groupByColumns;
    private boolean hasSubQuery;
    private boolean hasDistinct;
    private boolean hasUnion;
    private String summary;

    public MetadataResult() {}

    public String getStatementType() { return statementType; }
    public void setStatementType(String statementType) { this.statementType = statementType; }

    public Set<String> getTables() { return tables; }
    public void setTables(Set<String> tables) { this.tables = tables; }

    public Set<String> getColumns() { return columns; }
    public void setColumns(Set<String> columns) { this.columns = columns; }

    public Map<String, String> getAliases() { return aliases; }
    public void setAliases(Map<String, String> aliases) { this.aliases = aliases; }

    public List<String> getFunctions() { return functions; }
    public void setFunctions(List<String> functions) { this.functions = functions; }

    public Set<String> getWhereColumns() { return whereColumns; }
    public void setWhereColumns(Set<String> whereColumns) { this.whereColumns = whereColumns; }

    public List<String> getJoinConditions() { return joinConditions; }
    public void setJoinConditions(List<String> joinConditions) { this.joinConditions = joinConditions; }

    public List<String> getOrderByColumns() { return orderByColumns; }
    public void setOrderByColumns(List<String> orderByColumns) { this.orderByColumns = orderByColumns; }

    public List<String> getGroupByColumns() { return groupByColumns; }
    public void setGroupByColumns(List<String> groupByColumns) { this.groupByColumns = groupByColumns; }

    public boolean isHasSubQuery() { return hasSubQuery; }
    public void setHasSubQuery(boolean hasSubQuery) { this.hasSubQuery = hasSubQuery; }

    public boolean isHasDistinct() { return hasDistinct; }
    public void setHasDistinct(boolean hasDistinct) { this.hasDistinct = hasDistinct; }

    public boolean isHasUnion() { return hasUnion; }
    public void setHasUnion(boolean hasUnion) { this.hasUnion = hasUnion; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
