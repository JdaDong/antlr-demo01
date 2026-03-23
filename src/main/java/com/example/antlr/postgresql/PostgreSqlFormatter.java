package com.example.antlr.postgresql;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import com.google.common.base.Strings;

import java.util.List;

/**
 * PostgreSQL SQL 格式化/美化器
 *
 * 继承 PostgreSqlParserBaseVisitor，通过 Visitor 模式遍历语法树，
 * 将杂乱的 SQL 重新格式化为可读性强的标准缩进格式。
 *
 * 支持 PostgreSQL 特有语法：
 * - CTE (WITH ... AS)
 * - ILIKE
 * - :: 类型转换
 * - ->/->>/JSON 操作符
 * - INSERT ... RETURNING
 * - LIMIT ... OFFSET
 * - FULL OUTER JOIN
 * - CREATE SCHEMA
 * - PARTITION BY (RANGE/LIST/HASH)
 */
public class PostgreSqlFormatter extends PostgreSqlParserBaseVisitor<String> {

    private final String indentUnit;
    private final boolean uppercaseKeywords;
    private int indentLevel = 0;

    public PostgreSqlFormatter() {
        this(4, true);
    }

    public PostgreSqlFormatter(int indentSize, boolean uppercaseKeywords) {
        this.indentUnit = Strings.repeat(" ", indentSize);
        this.uppercaseKeywords = uppercaseKeywords;
    }

    private String indent() {
        return Strings.repeat(indentUnit, indentLevel);
    }

    private String kw(String keyword) {
        return uppercaseKeywords ? keyword.toUpperCase() : keyword.toLowerCase();
    }

    // ============================================================
    // Root & Statements
    // ============================================================

    @Override
    public String visitRoot(PostgreSqlParser.RootContext ctx) {
        return visitSqlStatements(ctx.sqlStatements());
    }

    @Override
    public String visitSqlStatements(PostgreSqlParser.SqlStatementsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (PostgreSqlParser.SqlStatementContext stmt : ctx.sqlStatement()) {
            if (sb.length() > 0) sb.append("\n\n");
            sb.append(visit(stmt));
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String visitSqlStatement(PostgreSqlParser.SqlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDdlStatement(PostgreSqlParser.DdlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDmlStatement(PostgreSqlParser.DmlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    // ============================================================
    // CTE (WITH ... AS)
    // ============================================================

    @Override
    public String visitWithClause(PostgreSqlParser.WithClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("WITH"));
        if (ctx.RECURSIVE() != null) {
            sb.append(" ").append(kw("RECURSIVE"));
        }
        sb.append("\n");

        indentLevel++;
        List<PostgreSqlParser.CteDefinitionContext> ctes = ctx.cteDefinition();
        for (int i = 0; i < ctes.size(); i++) {
            if (i > 0) sb.append(",\n");
            sb.append(visitCteDefinition(ctes.get(i)));
        }
        indentLevel--;
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String visitCteDefinition(PostgreSqlParser.CteDefinitionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(ctx.cteName().getText()).append(" ").append(kw("AS")).append(" (\n");
        indentLevel++;
        sb.append(visit(ctx.selectStatement()));
        indentLevel--;
        sb.append("\n").append(indent()).append(")");
        return sb.toString();
    }

    // ============================================================
    // SELECT Statement
    // ============================================================

    @Override
    public String visitSelectStatement(PostgreSqlParser.SelectStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        // SELECT [DISTINCT]
        sb.append(indent()).append(kw("SELECT"));
        if (ctx.DISTINCT() != null) {
            sb.append(" ").append(kw("DISTINCT"));
        }
        sb.append("\n");

        // 列列表
        indentLevel++;
        sb.append(visitSelectElements(ctx.selectElements()));
        indentLevel--;

        // FROM
        if (ctx.FROM() != null && ctx.tableSource() != null && !ctx.tableSource().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("FROM")).append(" ");
            List<PostgreSqlParser.TableSourceContext> sources = ctx.tableSource();
            for (int i = 0; i < sources.size(); i++) {
                if (i > 0) sb.append(",\n").append(indent()).append("     ");
                sb.append(visitTableSource(sources.get(i)));
            }
        }

        // JOIN
        for (PostgreSqlParser.JoinClauseContext join : ctx.joinClause()) {
            sb.append("\n").append(visitJoinClause(join));
        }

        // WHERE
        if (ctx.whereClause() != null) {
            sb.append("\n").append(visitWhereClause(ctx.whereClause()));
        }

        // GROUP BY
        if (ctx.groupByClause() != null) {
            sb.append("\n").append(visitGroupByClause(ctx.groupByClause()));
        }

        // HAVING
        if (ctx.havingClause() != null) {
            sb.append("\n").append(visitHavingClause(ctx.havingClause()));
        }

        // ORDER BY
        if (ctx.orderByClause() != null) {
            sb.append("\n").append(visitOrderByClause(ctx.orderByClause()));
        }

        // LIMIT
        if (ctx.limitClause() != null) {
            sb.append("\n").append(visitLimitClause(ctx.limitClause()));
        }

        // OFFSET
        if (ctx.offsetClause() != null) {
            sb.append("\n").append(visitOffsetClause(ctx.offsetClause()));
        }

        // UNION
        if (ctx.UNION() != null) {
            sb.append("\n\n").append(indent()).append(kw("UNION"));
            if (ctx.ALL() != null) sb.append(" ").append(kw("ALL"));
            sb.append("\n\n");
            sb.append(visit(ctx.selectStatement()));
        }

        return sb.toString();
    }

    // ============================================================
    // Select Elements
    // ============================================================

    @Override
    public String visitSelectElements(PostgreSqlParser.SelectElementsContext ctx) {
        if (ctx.STAR() != null) {
            return indent() + "*";
        }
        StringBuilder sb = new StringBuilder();
        List<PostgreSqlParser.SelectElementContext> elements = ctx.selectElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(",\n");
            sb.append(indent()).append(visitSelectElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitSelectElement(PostgreSqlParser.SelectElementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.alias() != null) {
            sb.append(" ").append(kw("AS")).append(" ").append(visitAlias(ctx.alias()));
        }
        return sb.toString();
    }

    // ============================================================
    // Table Source
    // ============================================================

    @Override
    public String visitTableSource(PostgreSqlParser.TableSourceContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.tableName() != null) {
            sb.append(visitTableName(ctx.tableName()));
            if (ctx.alias() != null) {
                sb.append(" ").append(visitAlias(ctx.alias()));
            }
        } else if (ctx.selectStatement() != null) {
            sb.append("(\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
            sb.append("\n").append(indent()).append(")");
            if (ctx.alias() != null) {
                sb.append(" ").append(visitAlias(ctx.alias()));
            }
        }
        return sb.toString();
    }

    // ============================================================
    // JOIN
    // ============================================================

    @Override
    public String visitJoinClause(PostgreSqlParser.JoinClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent());
        if (ctx.joinType() != null) {
            sb.append(visitJoinType(ctx.joinType())).append(" ");
        }
        sb.append(kw("JOIN")).append(" ");
        sb.append(visitTableSource(ctx.tableSource()));

        if (ctx.ON() != null) {
            sb.append("\n");
            indentLevel++;
            sb.append(indent()).append(kw("ON")).append(" ").append(visit(ctx.expression()));
            indentLevel--;
        }
        return sb.toString();
    }

    @Override
    public String visitJoinType(PostgreSqlParser.JoinTypeContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.INNER() != null) sb.append(kw("INNER"));
        else if (ctx.LEFT() != null) {
            sb.append(kw("LEFT"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
        } else if (ctx.RIGHT() != null) {
            sb.append(kw("RIGHT"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
        } else if (ctx.FULL() != null) {
            sb.append(kw("FULL"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
        } else if (ctx.CROSS() != null) sb.append(kw("CROSS"));
        return sb.toString();
    }

    // ============================================================
    // WHERE / GROUP BY / HAVING / ORDER BY / LIMIT / OFFSET
    // ============================================================

    @Override
    public String visitWhereClause(PostgreSqlParser.WhereClauseContext ctx) {
        return indent() + kw("WHERE") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitGroupByClause(PostgreSqlParser.GroupByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("GROUP BY")).append(" ");
        List<PostgreSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitHavingClause(PostgreSqlParser.HavingClauseContext ctx) {
        return indent() + kw("HAVING") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitOrderByClause(PostgreSqlParser.OrderByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ORDER BY")).append(" ");
        List<PostgreSqlParser.OrderByElementContext> elements = ctx.orderByElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitOrderByElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitOrderByElement(PostgreSqlParser.OrderByElementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.ASC() != null) sb.append(" ").append(kw("ASC"));
        if (ctx.DESC() != null) sb.append(" ").append(kw("DESC"));
        if (ctx.NULLS() != null) {
            sb.append(" ").append(kw("NULLS"));
            if (ctx.FIRST() != null) sb.append(" ").append(kw("FIRST"));
            else if (ctx.LAST() != null) sb.append(" ").append(kw("LAST"));
        }
        return sb.toString();
    }

    @Override
    public String visitLimitClause(PostgreSqlParser.LimitClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("LIMIT")).append(" ");
        if (ctx.ALL() != null) {
            sb.append(kw("ALL"));
        } else {
            sb.append(visit(ctx.expression()));
        }
        return sb.toString();
    }

    @Override
    public String visitOffsetClause(PostgreSqlParser.OffsetClauseContext ctx) {
        return indent() + kw("OFFSET") + " " + visit(ctx.expression());
    }

    // ============================================================
    // INSERT Statement
    // ============================================================

    @Override
    public String visitInsertStatement(PostgreSqlParser.InsertStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("INSERT INTO")).append(" ");
        sb.append(visitTableName(ctx.tableName()));

        // 列名列表
        if (ctx.columnNameList() != null) {
            sb.append(" (");
            List<PostgreSqlParser.ColumnNameContext> cols = ctx.columnNameList().columnName();
            for (int i = 0; i < cols.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(cols.get(i).getText());
            }
            sb.append(")");
        }

        // VALUES 或子查询
        if (ctx.VALUES() != null) {
            sb.append("\n").append(indent()).append(kw("VALUES"));
            List<PostgreSqlParser.ExpressionListContext> rows = ctx.expressionList();
            for (int i = 0; i < rows.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append("\n");
                indentLevel++;
                sb.append(indent()).append("(");
                List<PostgreSqlParser.ExpressionContext> vals = rows.get(i).expression();
                for (int j = 0; j < vals.size(); j++) {
                    if (j > 0) sb.append(", ");
                    sb.append(visit(vals.get(j)));
                }
                sb.append(")");
                indentLevel--;
            }
        } else if (ctx.selectStatement() != null) {
            sb.append("\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
        }

        // RETURNING
        if (ctx.RETURNING() != null) {
            sb.append("\n").append(indent()).append(kw("RETURNING")).append(" ");
            if (ctx.selectElements() != null) {
                sb.append(visitSelectElements_inline(ctx.selectElements()));
            }
        }

        return sb.toString();
    }

    private String visitSelectElements_inline(PostgreSqlParser.SelectElementsContext ctx) {
        if (ctx.STAR() != null) return "*";
        StringBuilder sb = new StringBuilder();
        List<PostgreSqlParser.SelectElementContext> elements = ctx.selectElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitSelectElement(elements.get(i)));
        }
        return sb.toString();
    }

    // ============================================================
    // UPDATE Statement
    // ============================================================

    @Override
    public String visitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("UPDATE")).append(" ");
        sb.append(visitTableName(ctx.tableName()));
        if (ctx.alias() != null) {
            sb.append(" ").append(visitAlias(ctx.alias()));
        }
        sb.append("\n").append(indent()).append(kw("SET"));

        List<PostgreSqlParser.UpdateAssignmentContext> assignments = ctx.updateAssignment();
        indentLevel++;
        for (int i = 0; i < assignments.size(); i++) {
            sb.append("\n").append(indent());
            sb.append(visitUpdateAssignment(assignments.get(i)));
            if (i < assignments.size() - 1) sb.append(",");
        }
        indentLevel--;

        if (ctx.whereClause() != null) {
            sb.append("\n").append(visitWhereClause(ctx.whereClause()));
        }

        if (ctx.RETURNING() != null && ctx.selectElements() != null) {
            sb.append("\n").append(indent()).append(kw("RETURNING")).append(" ");
            sb.append(visitSelectElements_inline(ctx.selectElements()));
        }

        return sb.toString();
    }

    @Override
    public String visitUpdateAssignment(PostgreSqlParser.UpdateAssignmentContext ctx) {
        return visit(ctx.columnRef()) + " = " + visit(ctx.expression());
    }

    // ============================================================
    // DELETE Statement
    // ============================================================

    @Override
    public String visitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("DELETE FROM")).append(" ");
        sb.append(visitTableName(ctx.tableName()));
        if (ctx.alias() != null) {
            sb.append(" ").append(visitAlias(ctx.alias()));
        }

        if (ctx.whereClause() != null) {
            sb.append("\n").append(visitWhereClause(ctx.whereClause()));
        }

        if (ctx.RETURNING() != null && ctx.selectElements() != null) {
            sb.append("\n").append(indent()).append(kw("RETURNING")).append(" ");
            sb.append(visitSelectElements_inline(ctx.selectElements()));
        }

        return sb.toString();
    }

    // ============================================================
    // DDL: CREATE TABLE
    // ============================================================

    @Override
    public String visitCreateTable(PostgreSqlParser.CreateTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE TABLE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(visitTableName(ctx.tableName(0))).append(" (\n");

        indentLevel++;
        List<PostgreSqlParser.TableElementContext> elements = ctx.tableElement();
        for (int i = 0; i < elements.size(); i++) {
            sb.append(indent()).append(visitTableElement(elements.get(i)));
            if (i < elements.size() - 1) sb.append(",");
            sb.append("\n");
        }
        indentLevel--;
        sb.append(indent()).append(")");

        if (ctx.partitionStrategy() != null) {
            sb.append("\n").append(indent()).append(kw("PARTITION BY")).append(" ");
            sb.append(visitPartitionStrategy(ctx.partitionStrategy()));
        }

        return sb.toString();
    }

    @Override
    public String visitTableElement(PostgreSqlParser.TableElementContext ctx) {
        if (ctx.columnDefinition() != null) {
            return visitColumnDefinition(ctx.columnDefinition());
        } else if (ctx.tableConstraint() != null) {
            return visitTableConstraint(ctx.tableConstraint());
        }
        return ctx.getText();
    }

    @Override
    public String visitColumnDefinition(PostgreSqlParser.ColumnDefinitionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.columnName().getText());
        sb.append(" ").append(visitDataType(ctx.dataType()));
        for (PostgreSqlParser.ColumnConstraintContext cc : ctx.columnConstraint()) {
            sb.append(" ").append(visitColumnConstraint(cc));
        }
        return sb.toString();
    }

    @Override
    public String visitDataType(PostgreSqlParser.DataTypeContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i > 0) {
                String prev = ctx.getChild(i - 1).getText();
                String curr = ctx.getChild(i).getText();
                if (!curr.equals("(") && !curr.equals(")") && !curr.equals(",") && !prev.equals("(")) {
                    sb.append(" ");
                }
            }
            String text = ctx.getChild(i).getText();
            sb.append(uppercaseKeywords ? text.toUpperCase() : text.toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public String visitColumnConstraint(PostgreSqlParser.ColumnConstraintContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.PRIMARY() != null) sb.append(kw("PRIMARY KEY"));
        else if (ctx.UNIQUE() != null) sb.append(kw("UNIQUE"));
        else if (ctx.NOT() != null && ctx.NULL_() != null) sb.append(kw("NOT NULL"));
        else if (ctx.NULL_() != null) sb.append(kw("NULL"));
        else if (ctx.DEFAULT() != null) sb.append(kw("DEFAULT")).append(" ").append(visit(ctx.expression()));
        else if (ctx.CHECK() != null) sb.append(kw("CHECK")).append(" (").append(visit(ctx.expression())).append(")");
        else if (ctx.REFERENCES() != null) {
            sb.append(kw("REFERENCES")).append(" ").append(visitTableName(ctx.tableName()));
            if (ctx.columnName() != null) {
                sb.append(" (").append(ctx.columnName().getText()).append(")");
            }
        }
        return sb.toString();
    }

    @Override
    public String visitTableConstraint(PostgreSqlParser.TableConstraintContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.constraintName() != null) {
            sb.append(kw("CONSTRAINT")).append(" ").append(ctx.constraintName().getText()).append(" ");
        }
        if (ctx.PRIMARY() != null) {
            sb.append(kw("PRIMARY KEY")).append(" (").append(visitColumnNameList_inline(ctx.columnNameList(0))).append(")");
        } else if (ctx.UNIQUE() != null) {
            sb.append(kw("UNIQUE")).append(" (").append(visitColumnNameList_inline(ctx.columnNameList(0))).append(")");
        } else if (ctx.FOREIGN() != null) {
            sb.append(kw("FOREIGN KEY")).append(" (").append(visitColumnNameList_inline(ctx.columnNameList(0))).append(") ");
            sb.append(kw("REFERENCES")).append(" ").append(visitTableName(ctx.tableName()));
            if (ctx.columnNameList().size() > 1) {
                sb.append(" (").append(visitColumnNameList_inline(ctx.columnNameList(1))).append(")");
            }
        } else if (ctx.CHECK() != null) {
            sb.append(kw("CHECK")).append(" (").append(visit(ctx.expression())).append(")");
        }
        return sb.toString();
    }

    private String visitColumnNameList_inline(PostgreSqlParser.ColumnNameListContext ctx) {
        StringBuilder sb = new StringBuilder();
        List<PostgreSqlParser.ColumnNameContext> cols = ctx.columnName();
        for (int i = 0; i < cols.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(cols.get(i).getText());
        }
        return sb.toString();
    }

    @Override
    public String visitPartitionStrategy(PostgreSqlParser.PartitionStrategyContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.RANGE() != null) sb.append(kw("RANGE"));
        else if (ctx.LIST() != null) sb.append(kw("LIST"));
        else if (ctx.HASH() != null) sb.append(kw("HASH"));
        return sb.toString();
    }

    // ============================================================
    // DDL: 其他
    // ============================================================

    @Override
    public String visitCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        return sb.toString();
    }

    @Override
    public String visitCreateSchema(PostgreSqlParser.CreateSchemaContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE SCHEMA"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.schemaName().getText());
        return sb.toString();
    }

    @Override
    public String visitDropDatabase(PostgreSqlParser.DropDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        return sb.toString();
    }

    @Override
    public String visitDropTable(PostgreSqlParser.DropTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP TABLE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    @Override
    public String visitAlterTable(PostgreSqlParser.AlterTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ALTER TABLE")).append(" ");
        sb.append(visitTableName(ctx.tableName()));
        List<PostgreSqlParser.AlterTableActionContext> actions = ctx.alterTableAction();
        indentLevel++;
        for (int i = 0; i < actions.size(); i++) {
            sb.append("\n").append(indent());
            sb.append(visitAlterTableAction(actions.get(i)));
            if (i < actions.size() - 1) sb.append(",");
        }
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitAlterTableAction(PostgreSqlParser.AlterTableActionContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.ADD() != null && ctx.COLUMN() != null) {
            sb.append(kw("ADD COLUMN")).append(" ").append(visitColumnDefinition(ctx.columnDefinition()));
        } else if (ctx.DROP() != null && ctx.COLUMN() != null) {
            sb.append(kw("DROP COLUMN")).append(" ").append(ctx.columnName().getText());
        } else if (ctx.ALTER() != null && ctx.COLUMN() != null) {
            sb.append(kw("ALTER COLUMN")).append(" ").append(ctx.columnName().getText());
            if (ctx.SET() != null && ctx.DEFAULT() != null) {
                sb.append(" ").append(kw("SET DEFAULT")).append(" ").append(visit(ctx.expression()));
            } else if (ctx.DROP() != null && ctx.DEFAULT() != null) {
                sb.append(" ").append(kw("DROP DEFAULT"));
            }
        } else if (ctx.ADD() != null && ctx.tableConstraint() != null) {
            sb.append(kw("ADD")).append(" ").append(visitTableConstraint(ctx.tableConstraint()));
        } else if (ctx.DROP() != null && ctx.CONSTRAINT() != null) {
            sb.append(kw("DROP CONSTRAINT")).append(" ").append(ctx.constraintName().getText());
            if (ctx.CASCADE() != null) sb.append(" ").append(kw("CASCADE"));
            if (ctx.RESTRICT() != null) sb.append(" ").append(kw("RESTRICT"));
        }
        return sb.toString();
    }

    @Override
    public String visitCreateIndex(PostgreSqlParser.CreateIndexContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE"));
        if (ctx.UNIQUE() != null) sb.append(" ").append(kw("UNIQUE"));
        sb.append(" ").append(kw("INDEX"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.indexName().getText());
        sb.append("\n");
        indentLevel++;
        sb.append(indent()).append(kw("ON")).append(" ").append(visitTableName(ctx.tableName()));
        if (ctx.indexMethod() != null) {
            sb.append(" ").append(kw("USING")).append(" ").append(visitIndexMethod(ctx.indexMethod()));
        }
        sb.append(" (").append(visitIndexColumnList(ctx.indexColumnList())).append(")");
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitIndexMethod(PostgreSqlParser.IndexMethodContext ctx) {
        return ctx.getText().toLowerCase();
    }

    @Override
    public String visitIndexColumnList(PostgreSqlParser.IndexColumnListContext ctx) {
        StringBuilder sb = new StringBuilder();
        List<PostgreSqlParser.IndexColumnContext> cols = ctx.indexColumn();
        for (int i = 0; i < cols.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitIndexColumn(cols.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitIndexColumn(PostgreSqlParser.IndexColumnContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.columnName().getText());
        if (ctx.ASC() != null) sb.append(" ").append(kw("ASC"));
        if (ctx.DESC() != null) sb.append(" ").append(kw("DESC"));
        return sb.toString();
    }

    @Override
    public String visitDropIndex(PostgreSqlParser.DropIndexContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP INDEX"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(ctx.indexName().getText());
        return sb.toString();
    }

    // ============================================================
    // Expressions
    // ============================================================

    @Override
    public String visitAndExpression(PostgreSqlParser.AndExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("AND") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitOrExpression(PostgreSqlParser.OrExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("OR") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonExpression(PostgreSqlParser.ComparisonExpressionContext ctx) {
        String op = visitComparisonOperator(ctx.comparisonOperator());
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonOperator(PostgreSqlParser.ComparisonOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitNotExpression(PostgreSqlParser.NotExpressionContext ctx) {
        return kw("NOT") + " " + visit(ctx.expression());
    }

    @Override
    public String visitParenExpression(PostgreSqlParser.ParenExpressionContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitInExpression(PostgreSqlParser.InExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression())).append(" ").append(kw("IN")).append(" (");
        if (ctx.expressionList() != null) {
            List<PostgreSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
            for (int i = 0; i < exprs.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visit(exprs.get(i)));
            }
        } else if (ctx.selectStatement() != null) {
            sb.append("\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
            sb.append("\n").append(indent());
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitBetweenExpression(PostgreSqlParser.BetweenExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("BETWEEN") + " " +
               visit(ctx.expression(1)) + " " + kw("AND") + " " + visit(ctx.expression(2));
    }

    @Override
    public String visitLikeExpression(PostgreSqlParser.LikeExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("LIKE") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitIlikeExpression(PostgreSqlParser.IlikeExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("ILIKE") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitIsNullExpression(PostgreSqlParser.IsNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NULL");
    }

    @Override
    public String visitIsNotNullExpression(PostgreSqlParser.IsNotNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NOT NULL");
    }

    @Override
    public String visitConcatExpression(PostgreSqlParser.ConcatExpressionContext ctx) {
        return visit(ctx.expression(0)) + " || " + visit(ctx.expression(1));
    }

    @Override
    public String visitPgCastExpression(PostgreSqlParser.PgCastExpressionContext ctx) {
        return visit(ctx.expression()) + "::" + visitDataType(ctx.dataType());
    }

    @Override
    public String visitJsonAccessExpression(PostgreSqlParser.JsonAccessExpressionContext ctx) {
        return visit(ctx.expression(0)) + " -> " + visit(ctx.expression(1));
    }

    @Override
    public String visitJsonTextAccessExpression(PostgreSqlParser.JsonTextAccessExpressionContext ctx) {
        return visit(ctx.expression(0)) + " ->> " + visit(ctx.expression(1));
    }

    @Override
    public String visitArrayAccessExpression(PostgreSqlParser.ArrayAccessExpressionContext ctx) {
        return visit(ctx.expression(0)) + "[" + visit(ctx.expression(1)) + "]";
    }

    @Override
    public String visitCastExpression(PostgreSqlParser.CastExpressionContext ctx) {
        return kw("CAST") + "(" + visit(ctx.expression()) + " " + kw("AS") + " " + visitDataType(ctx.dataType()) + ")";
    }

    @Override
    public String visitMulDivModExpression(PostgreSqlParser.MulDivModExpressionContext ctx) {
        String op;
        if (ctx.STAR() != null) op = "*";
        else if (ctx.DIVIDE() != null) op = "/";
        else op = "%";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitAddSubExpression(PostgreSqlParser.AddSubExpressionContext ctx) {
        String op = ctx.PLUS() != null ? "+" : "-";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitStarExpression(PostgreSqlParser.StarExpressionContext ctx) {
        return "*";
    }

    @Override
    public String visitCaseExpr(PostgreSqlParser.CaseExprContext ctx) {
        return visitCaseExpression(ctx.caseExpression());
    }

    @Override
    public String visitCaseExpression(PostgreSqlParser.CaseExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("CASE"));
        List<PostgreSqlParser.ExpressionContext> allExprs = ctx.expression();
        int exprIdx = 0;
        int whenCount = ctx.WHEN().size();
        boolean hasSimpleCase = allExprs.size() > whenCount * 2 + (ctx.ELSE() != null ? 1 : 0);
        if (hasSimpleCase) {
            sb.append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
        }
        indentLevel++;
        for (int i = 0; i < whenCount; i++) {
            sb.append("\n").append(indent());
            sb.append(kw("WHEN")).append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
            sb.append(" ").append(kw("THEN")).append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
        }
        if (ctx.ELSE() != null) {
            sb.append("\n").append(indent());
            sb.append(kw("ELSE")).append(" ").append(visit(allExprs.get(exprIdx)));
        }
        indentLevel--;
        sb.append("\n").append(indent()).append(kw("END"));
        return sb.toString();
    }

    @Override
    public String visitFunctionCallExpression(PostgreSqlParser.FunctionCallExpressionContext ctx) {
        return visitFunctionCall(ctx.functionCall());
    }

    @Override
    public String visitFunctionCall(PostgreSqlParser.FunctionCallContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.functionName().getText().toUpperCase());
        sb.append("(");
        if (ctx.STAR() != null) {
            sb.append("*");
        } else if (ctx.expressionList() != null) {
            if (ctx.DISTINCT() != null) sb.append(kw("DISTINCT")).append(" ");
            List<PostgreSqlParser.ExpressionContext> args = ctx.expressionList().expression();
            for (int i = 0; i < args.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visit(args.get(i)));
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // ============================================================
    // Literals & References
    // ============================================================

    @Override
    public String visitLiteralExpression(PostgreSqlParser.LiteralExpressionContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public String visitLiteral(PostgreSqlParser.LiteralContext ctx) {
        if (ctx.NULL_() != null) return kw("NULL");
        if (ctx.TRUE() != null) return kw("TRUE");
        if (ctx.FALSE() != null) return kw("FALSE");
        return ctx.getText();
    }

    @Override
    public String visitColumnRefExpression(PostgreSqlParser.ColumnRefExpressionContext ctx) {
        return visitColumnRef(ctx.columnRef());
    }

    @Override
    public String visitColumnRef(PostgreSqlParser.ColumnRefContext ctx) {
        StringBuilder sb = new StringBuilder();
        List<PostgreSqlParser.IdentifierContext> ids = ctx.identifier();
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) sb.append(".");
            sb.append(ids.get(i).getText());
        }
        if (ctx.STAR() != null) {
            if (!ids.isEmpty()) sb.append(".");
            sb.append("*");
        }
        return sb.toString();
    }

    @Override
    public String visitTableName(PostgreSqlParser.TableNameContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.schemaName() != null) {
            sb.append(ctx.schemaName().getText()).append(".");
        }
        sb.append(ctx.identifier().getText());
        return sb.toString();
    }

    @Override
    public String visitAlias(PostgreSqlParser.AliasContext ctx) {
        return ctx.getText();
    }

    // ============================================================
    // 辅助方法
    // ============================================================

    private String formatExpression(PostgreSqlParser.ExpressionContext ctx) {
        indentLevel++;
        String result = visit(ctx);
        indentLevel--;
        return result;
    }

    private String visitChildren_concat(ParseTree ctx) {
        if (ctx.getChildCount() == 0) return ctx.getText();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            String childResult = visit(ctx.getChild(i));
            if (childResult != null) sb.append(childResult);
        }
        return sb.toString();
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        return node.getText();
    }

    @Override
    protected String defaultResult() {
        return "";
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if (aggregate == null || aggregate.isEmpty()) return nextResult;
        if (nextResult == null || nextResult.isEmpty()) return aggregate;
        return aggregate + nextResult;
    }
}
