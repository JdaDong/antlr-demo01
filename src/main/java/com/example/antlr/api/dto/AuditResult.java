package com.example.antlr.api.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * SQL 审计分析结果
 */
public class AuditResult {

    private String statementType;
    private List<AuditEventDto> events;
    private Map<String, Set<String>> tableAccess;
    private List<String> warnings;
    private List<String> errors;
    private boolean hasDangerousOperations;
    private String report;

    public AuditResult() {}

    public String getStatementType() { return statementType; }
    public void setStatementType(String statementType) { this.statementType = statementType; }

    public List<AuditEventDto> getEvents() { return events; }
    public void setEvents(List<AuditEventDto> events) { this.events = events; }

    public Map<String, Set<String>> getTableAccess() { return tableAccess; }
    public void setTableAccess(Map<String, Set<String>> tableAccess) { this.tableAccess = tableAccess; }

    public List<String> getWarnings() { return warnings; }
    public void setWarnings(List<String> warnings) { this.warnings = warnings; }

    public List<String> getErrors() { return errors; }
    public void setErrors(List<String> errors) { this.errors = errors; }

    public boolean isHasDangerousOperations() { return hasDangerousOperations; }
    public void setHasDangerousOperations(boolean hasDangerousOperations) { this.hasDangerousOperations = hasDangerousOperations; }

    public String getReport() { return report; }
    public void setReport(String report) { this.report = report; }

    public static class AuditEventDto {
        private String type;
        private String detail;
        private int line;
        private int column;

        public AuditEventDto() {}

        public AuditEventDto(String type, String detail, int line, int column) {
            this.type = type;
            this.detail = detail;
            this.line = line;
            this.column = column;
        }

        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

        public String getDetail() { return detail; }
        public void setDetail(String detail) { this.detail = detail; }

        public int getLine() { return line; }
        public void setLine(int line) { this.line = line; }

        public int getColumn() { return column; }
        public void setColumn(int column) { this.column = column; }
    }
}
