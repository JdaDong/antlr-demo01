package com.example.antlr.sparksql;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import com.google.common.base.Strings;

import java.util.List;

/**
 * SparkSQL SQL 格式化/美化器
 *
 * 继承 SparkSqlParserBaseVisitor，通过 Visitor 模式遍历语法树，
 * 将杂乱的 SQL 重新格式化为可读性强的标准缩进格式。
 *
 * 支持 SparkSQL 特有语法：
 * - CTE (WITH ... AS)
 * - CREATE TABLE USING ... OPTIONS ... PARTITIONED BY
 * - INSERT OVERWRITE TABLE ... PARTITION
 * - RLIKE / REGEXP
 * - LEFT SEMI JOIN / LEFT ANTI JOIN
 * - LATERAL VIEW
 * - DISTRIBUTE BY / SORT BY / CLUSTER BY
 * - WINDOW 函数
 * - EXPLAIN / CACHE / UNCACHE / REFRESH
 * - CREATE/DROP VIEW
 * - STRUCT / ARRAY / MAP 类型构造
 */
public class SparkSqlFormatter extends SparkSqlParserBaseVisitor<String> {

    private final String indentUnit;
    private final boolean uppercaseKeywords;
    private int indentLevel = 0;

    public SparkSqlFormatter() {
        this(4, true);
    }

    public SparkSqlFormatter(int indentSize, boolean uppercaseKeywords) {
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
    public String visitRoot(SparkSqlParser.RootContext ctx) {
        return visitSqlStatements(ctx.sqlStatements());
    }

    @Override
    public String visitSqlStatements(SparkSqlParser.SqlStatementsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (SparkSqlParser.SqlStatementContext stmt : ctx.sqlStatement()) {
            if (sb.length() > 0) sb.append("\n\n");
            sb.append(visit(stmt));
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String visitSqlStatement(SparkSqlParser.SqlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDdlStatement(SparkSqlParser.DdlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDmlStatement(SparkSqlParser.DmlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitUtilityStatement(SparkSqlParser.UtilityStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    // ============================================================
    // CTE
    // ============================================================

    @Override
    public String visitWithClause(SparkSqlParser.WithClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("WITH")).append("\n");
        indentLevel++;
        List<SparkSqlParser.CteDefinitionContext> ctes = ctx.cteDefinition();
        for (int i = 0; i < ctes.size(); i++) {
            if (i > 0) sb.append(",\n");
            sb.append(visitCteDefinition(ctes.get(i)));
        }
        indentLevel--;
        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String visitCteDefinition(SparkSqlParser.CteDefinitionContext ctx) {
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
    public String visitSelectStatement(SparkSqlParser.SelectStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        // withClause is on dmlStatement level, not selectStatement

        sb.append(indent()).append(kw("SELECT"));
        if (ctx.DISTINCT() != null) sb.append(" ").append(kw("DISTINCT"));
        sb.append("\n");

        indentLevel++;
        sb.append(visitSelectElements(ctx.selectElements()));
        indentLevel--;

        // FROM
        if (ctx.FROM() != null && ctx.tableSource() != null && !ctx.tableSource().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("FROM")).append(" ");
            List<SparkSqlParser.TableSourceContext> sources = ctx.tableSource();
            for (int i = 0; i < sources.size(); i++) {
                if (i > 0) sb.append(",\n").append(indent()).append("     ");
                sb.append(visitTableSource(sources.get(i)));
            }
        }

        // JOIN
        for (SparkSqlParser.JoinClauseContext join : ctx.joinClause()) {
            sb.append("\n").append(visitJoinClause(join));
        }

        // LATERAL VIEW
        for (SparkSqlParser.LateralViewClauseContext lv : ctx.lateralViewClause()) {
            sb.append("\n").append(visitLateralViewClause(lv));
        }

        // WHERE
        if (ctx.whereClause() != null) sb.append("\n").append(visitWhereClause(ctx.whereClause()));

        // GROUP BY
        if (ctx.groupByClause() != null) sb.append("\n").append(visitGroupByClause(ctx.groupByClause()));

        // HAVING
        if (ctx.havingClause() != null) sb.append("\n").append(visitHavingClause(ctx.havingClause()));

        // WINDOW
        if (ctx.windowClause() != null) sb.append("\n").append(visitWindowClause(ctx.windowClause()));

        // ORDER BY
        if (ctx.orderByClause() != null) sb.append("\n").append(visitOrderByClause(ctx.orderByClause()));

        // SORT BY
        if (ctx.sortByClause() != null) sb.append("\n").append(visitSortByClause(ctx.sortByClause()));

        // DISTRIBUTE BY
        if (ctx.distributeByClause() != null) sb.append("\n").append(visitDistributeByClause(ctx.distributeByClause()));

        // CLUSTER BY
        if (ctx.clusterByClause() != null) sb.append("\n").append(visitClusterByClause(ctx.clusterByClause()));

        // LIMIT
        if (ctx.limitClause() != null) sb.append("\n").append(visitLimitClause(ctx.limitClause()));

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
    public String visitSelectElements(SparkSqlParser.SelectElementsContext ctx) {
        if (ctx.STAR() != null) return indent() + "*";
        StringBuilder sb = new StringBuilder();
        List<SparkSqlParser.SelectElementContext> elements = ctx.selectElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(",\n");
            sb.append(indent()).append(visitSelectElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitSelectElement(SparkSqlParser.SelectElementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.alias() != null) sb.append(" ").append(kw("AS")).append(" ").append(visitAlias(ctx.alias()));
        return sb.toString();
    }

    // ============================================================
    // Table Source
    // ============================================================

    @Override
    public String visitTableSource(SparkSqlParser.TableSourceContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.tableName() != null) {
            sb.append(visitTableName(ctx.tableName()));
            if (ctx.tableSample() != null) sb.append(" ").append(visitTableSample(ctx.tableSample()));
            if (ctx.alias() != null) sb.append(" ").append(visitAlias(ctx.alias()));
        } else if (ctx.selectStatement() != null) {
            sb.append("(\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
            sb.append("\n").append(indent()).append(")");
            if (ctx.alias() != null) sb.append(" ").append(visitAlias(ctx.alias()));
        }
        return sb.toString();
    }

    @Override
    public String visitTableSample(SparkSqlParser.TableSampleContext ctx) {
        return kw("TABLESAMPLE") + " (" + ctx.getText().replaceAll("(?i)tablesample\\s*\\(", "").replaceAll("\\)$", "") + ")";
    }

    // ============================================================
    // JOIN
    // ============================================================

    @Override
    public String visitJoinClause(SparkSqlParser.JoinClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent());
        if (ctx.joinType() != null) sb.append(visitJoinType(ctx.joinType())).append(" ");
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
    public String visitJoinType(SparkSqlParser.JoinTypeContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.INNER() != null) sb.append(kw("INNER"));
        else if (ctx.LEFT() != null) {
            sb.append(kw("LEFT"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
            if (ctx.SEMI() != null) sb.append(" ").append(kw("SEMI"));
            if (ctx.ANTI() != null) sb.append(" ").append(kw("ANTI"));
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
    // LATERAL VIEW
    // ============================================================

    @Override
    public String visitLateralViewClause(SparkSqlParser.LateralViewClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("LATERAL VIEW"));
        sb.append(" ").append(ctx.getText().replaceAll("(?i)lateral\\s+view\\s*", ""));
        return sb.toString();
    }

    // ============================================================
    // WHERE / GROUP BY / HAVING / ORDER BY / SORT BY / DISTRIBUTE BY / CLUSTER BY / LIMIT
    // ============================================================

    @Override
    public String visitWhereClause(SparkSqlParser.WhereClauseContext ctx) {
        return indent() + kw("WHERE") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitGroupByClause(SparkSqlParser.GroupByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("GROUP BY")).append(" ");
        List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitHavingClause(SparkSqlParser.HavingClauseContext ctx) {
        return indent() + kw("HAVING") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitWindowClause(SparkSqlParser.WindowClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("WINDOW")).append(" ");
        List<SparkSqlParser.NamedWindowContext> windows = ctx.namedWindow();
        for (int i = 0; i < windows.size(); i++) {
            if (i > 0) sb.append(",\n").append(indent()).append("       ");
            sb.append(visitNamedWindow(windows.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitNamedWindow(SparkSqlParser.NamedWindowContext ctx) {
        return ctx.identifier().getText() + " " + kw("AS") + " (" + visitWindowSpec(ctx.windowSpec()) + ")";
    }

    @Override
    public String visitWindowSpec(SparkSqlParser.WindowSpecContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.PARTITION() != null) {
            sb.append(kw("PARTITION BY")).append(" ");
            if (ctx.expressionList() != null) {
                List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
                for (int i = 0; i < exprs.size(); i++) {
                    if (i > 0) sb.append(", ");
                    sb.append(visit(exprs.get(i)));
                }
            }
        }
        if (ctx.orderByElement() != null && !ctx.orderByElement().isEmpty()) {
            if (sb.length() > 0) sb.append(" ");
            sb.append(kw("ORDER BY")).append(" ");
            List<SparkSqlParser.OrderByElementContext> elements = ctx.orderByElement();
            for (int i = 0; i < elements.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visitOrderByElement(elements.get(i)));
            }
        }
        if (ctx.windowFrame() != null) {
            sb.append(" ").append(visitWindowFrame(ctx.windowFrame()));
        }
        return sb.toString();
    }

    @Override
    public String visitWindowFrame(SparkSqlParser.WindowFrameContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.ROWS() != null) sb.append(kw("ROWS"));
        else if (ctx.RANGE() != null) sb.append(kw("RANGE"));
        sb.append(" ").append(kw("BETWEEN")).append(" ");
        sb.append(visitFrameBound(ctx.frameBound(0)));
        sb.append(" ").append(kw("AND")).append(" ");
        sb.append(visitFrameBound(ctx.frameBound(1)));
        return sb.toString();
    }

    @Override
    public String visitFrameBound(SparkSqlParser.FrameBoundContext ctx) {
        if (ctx.UNBOUNDED() != null && ctx.PRECEDING() != null) {
            return kw("UNBOUNDED") + " " + kw("PRECEDING");
        } else if (ctx.UNBOUNDED() != null && ctx.FOLLOWING() != null) {
            return kw("UNBOUNDED") + " " + kw("FOLLOWING");
        } else if (ctx.CURRENT() != null) {
            return kw("CURRENT") + " " + kw("ROW");
        } else if (ctx.PRECEDING() != null && ctx.expression() != null) {
            return visit(ctx.expression()) + " " + kw("PRECEDING");
        } else if (ctx.FOLLOWING() != null && ctx.expression() != null) {
            return visit(ctx.expression()) + " " + kw("FOLLOWING");
        }
        return ctx.getText().toUpperCase();
    }

    @Override
    public String visitOrderByClause(SparkSqlParser.OrderByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ORDER BY")).append(" ");
        List<SparkSqlParser.OrderByElementContext> elements = ctx.orderByElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitOrderByElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitOrderByElement(SparkSqlParser.OrderByElementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.ASC() != null) sb.append(" ").append(kw("ASC"));
        if (ctx.DESC() != null) sb.append(" ").append(kw("DESC"));
        return sb.toString();
    }

    @Override
    public String visitSortByClause(SparkSqlParser.SortByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("SORT BY")).append(" ");
        List<SparkSqlParser.OrderByElementContext> elements = ctx.orderByElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitOrderByElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitDistributeByClause(SparkSqlParser.DistributeByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DISTRIBUTE BY")).append(" ");
        List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitClusterByClause(SparkSqlParser.ClusterByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CLUSTER BY")).append(" ");
        List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitLimitClause(SparkSqlParser.LimitClauseContext ctx) {
        return indent() + kw("LIMIT") + " " + visit(ctx.expression());
    }

    // ============================================================
    // INSERT Statement
    // ============================================================

    @Override
    public String visitInsertStatement(SparkSqlParser.InsertStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        // withClause is on dmlStatement, not insertStatement; handled by dmlStatement visit

        sb.append(indent());
        if (ctx.OVERWRITE() != null) {
            sb.append(kw("INSERT OVERWRITE TABLE")).append(" ");
        } else {
            sb.append(kw("INSERT INTO")).append(" ");
            if (ctx.TABLE() != null) sb.append(kw("TABLE")).append(" ");
        }
        sb.append(visitTableName(ctx.tableName()));

        if (ctx.partitionSpec() != null) {
            sb.append("\n");
            indentLevel++;
            sb.append(indent()).append(visitPartitionSpec(ctx.partitionSpec()));
            indentLevel--;
        }

        if (ctx.columnNameList() != null) {
            sb.append(" (");
            List<SparkSqlParser.ColumnNameContext> cols = ctx.columnNameList().columnName();
            for (int i = 0; i < cols.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(cols.get(i).getText());
            }
            sb.append(")");
        }

        if (ctx.VALUES() != null) {
            sb.append("\n").append(indent()).append(kw("VALUES"));
            List<SparkSqlParser.ExpressionListContext> rows = ctx.expressionList();
            for (int i = 0; i < rows.size(); i++) {
                if (i > 0) sb.append(",");
                sb.append("\n");
                indentLevel++;
                sb.append(indent()).append("(");
                List<SparkSqlParser.ExpressionContext> vals = rows.get(i).expression();
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

        return sb.toString();
    }

    @Override
    public String visitPartitionSpec(SparkSqlParser.PartitionSpecContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("PARTITION")).append(" (");
        List<SparkSqlParser.PartitionValContext> vals = ctx.partitionVal();
        for (int i = 0; i < vals.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitPartitionVal(vals.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitPartitionVal(SparkSqlParser.PartitionValContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.identifier().getText());
        if (ctx.expression() != null) {
            sb.append(" = ").append(visit(ctx.expression()));
        }
        return sb.toString();
    }

    // ============================================================
    // DDL: CREATE TABLE
    // ============================================================

    @Override
    public String visitCreateTable(SparkSqlParser.CreateTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE TABLE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(visitTableName(ctx.tableName()));

        // 列定义
        if (ctx.columnDefinition() != null && !ctx.columnDefinition().isEmpty()) {
            sb.append(" (\n");
            indentLevel++;
            List<SparkSqlParser.ColumnDefinitionContext> colDefs = ctx.columnDefinition();
            for (int i = 0; i < colDefs.size(); i++) {
                sb.append(indent()).append(visitColumnDefinition(colDefs.get(i)));
                if (i < colDefs.size() - 1) sb.append(",");
                sb.append("\n");
            }
            indentLevel--;
            sb.append(indent()).append(")");
        }

        // USING
        if (ctx.USING() != null && ctx.dataSourceFormat() != null && !ctx.dataSourceFormat().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("USING")).append(" ").append(visitDataSourceFormat(ctx.dataSourceFormat(0)));
        }

        // OPTIONS
        if (ctx.OPTIONS() != null && ctx.propertyList() != null && !ctx.propertyList().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("OPTIONS")).append(" ");
            sb.append(visitPropertyList(ctx.propertyList(0)));
        }

        // PARTITIONED BY
        if (ctx.PARTITIONED() != null && ctx.partitionColumnList() != null) {
            sb.append("\n").append(indent()).append(kw("PARTITIONED BY")).append(" (");
            sb.append(visitPartitionColumnList(ctx.partitionColumnList()));
            sb.append(")");
        }

        // ROW FORMAT
        if (ctx.rowFormat() != null) {
            sb.append("\n").append(indent()).append(visitRowFormat(ctx.rowFormat()));
        }

        // COMMENT
        if (ctx.COMMENT() != null && ctx.STRING_LITERAL() != null && !ctx.STRING_LITERAL().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("COMMENT")).append(" ").append(ctx.STRING_LITERAL(0).getText());
        }

        // AS SELECT
        if (ctx.AS() != null && ctx.selectStatement() != null) {
            sb.append("\n").append(indent()).append(kw("AS")).append("\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
        }

        return sb.toString();
    }

    @Override
    public String visitColumnDefinition(SparkSqlParser.ColumnDefinitionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.columnName().getText());
        sb.append(" ").append(visitDataType(ctx.dataType()));
        if (ctx.NOT() != null && ctx.NULL_() != null) sb.append(" ").append(kw("NOT NULL"));
        if (ctx.COMMENT() != null && ctx.STRING_LITERAL() != null) {
            sb.append(" ").append(kw("COMMENT")).append(" ").append(ctx.STRING_LITERAL().getText());
        }
        return sb.toString();
    }

    @Override
    public String visitDataType(SparkSqlParser.DataTypeContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i > 0) {
                String prev = ctx.getChild(i - 1).getText();
                String curr = ctx.getChild(i).getText();
                if (!curr.equals("(") && !curr.equals(")") && !curr.equals(",") && !curr.equals("<")
                        && !curr.equals(">") && !prev.equals("(") && !prev.equals("<")) {
                    sb.append(" ");
                }
            }
            String text = ctx.getChild(i).getText();
            sb.append(uppercaseKeywords ? text.toUpperCase() : text.toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public String visitDataSourceFormat(SparkSqlParser.DataSourceFormatContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitPropertyList(SparkSqlParser.PropertyListContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        List<SparkSqlParser.PropertyContext> props = ctx.property();
        for (int i = 0; i < props.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitProperty(props.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitProperty(SparkSqlParser.PropertyContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitPartitionColumnList(SparkSqlParser.PartitionColumnListContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitRowFormat(SparkSqlParser.RowFormatContext ctx) {
        return kw("ROW FORMAT") + " " + ctx.getText().replaceAll("(?i)row\\s+format\\s+", "").toUpperCase();
    }

    // ============================================================
    // DDL: 其他
    // ============================================================

    @Override
    public String visitCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        if (ctx.COMMENT() != null && !ctx.STRING_LITERAL().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("COMMENT")).append(" ").append(ctx.STRING_LITERAL(0).getText());
        }
        return sb.toString();
    }

    @Override
    public String visitDropDatabase(SparkSqlParser.DropDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        if (ctx.CASCADE() != null) sb.append(" ").append(kw("CASCADE"));
        return sb.toString();
    }

    @Override
    public String visitDropTable(SparkSqlParser.DropTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP TABLE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    @Override
    public String visitCreateView(SparkSqlParser.CreateViewContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE"));
        if (ctx.REPLACE() != null) sb.append(" ").append(kw("OR REPLACE"));
        if (ctx.TEMPORARY() != null) sb.append(" ").append(kw("TEMPORARY"));
        if (ctx.GLOBAL() != null) sb.append(" ").append(kw("GLOBAL"));
        sb.append(" ").append(kw("VIEW"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.viewName().getText());
        if (ctx.COMMENT() != null && ctx.STRING_LITERAL() != null) {
            sb.append("\n").append(indent()).append(kw("COMMENT")).append(" ").append(ctx.STRING_LITERAL().getText());
        }
        sb.append("\n").append(indent()).append(kw("AS")).append("\n");
        indentLevel++;
        sb.append(visit(ctx.selectStatement()));
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitDropView(SparkSqlParser.DropViewContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP VIEW"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(ctx.viewName().getText());
        return sb.toString();
    }

    // ============================================================
    // ALTER TABLE
    // ============================================================

    @Override
    public String visitRenameTable(SparkSqlParser.RenameTableContext ctx) {
        return indent() + kw("ALTER TABLE") + " " + visitTableName(ctx.tableName(0)) +
               "\n" + indent() + "    " + kw("RENAME TO") + " " + visitTableName(ctx.tableName(1));
    }

    @Override
    public String visitAddColumns(SparkSqlParser.AddColumnsContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ALTER TABLE")).append(" ").append(visitTableName(ctx.tableName()));
        sb.append("\n");
        indentLevel++;
        sb.append(indent()).append(kw("ADD COLUMNS")).append(" (");
        List<SparkSqlParser.ColumnDefinitionContext> cols = ctx.columnDefinition();
        for (int i = 0; i < cols.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitColumnDefinition(cols.get(i)));
        }
        sb.append(")");
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitDropColumn(SparkSqlParser.DropColumnContext ctx) {
        return indent() + kw("ALTER TABLE") + " " + visitTableName(ctx.tableName()) +
               "\n" + indent() + "    " + kw("DROP COLUMN") + " " + ctx.columnName().getText();
    }

    @Override
    public String visitSetTableProperties(SparkSqlParser.SetTablePropertiesContext ctx) {
        return indent() + kw("ALTER TABLE") + " " + visitTableName(ctx.tableName()) +
               "\n" + indent() + "    " + kw("SET TBLPROPERTIES") + " " + visitPropertyList(ctx.propertyList());
    }

    // ============================================================
    // Utility 语句
    // ============================================================

    @Override
    public String visitUseStatement(SparkSqlParser.UseStatementContext ctx) {
        return indent() + kw("USE") + " " + ctx.databaseName().getText();
    }

    @Override
    public String visitShowStatement(SparkSqlParser.ShowStatementContext ctx) {
        if (ctx.DATABASES() != null) return indent() + kw("SHOW DATABASES");
        if (ctx.TABLES() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(indent()).append(kw("SHOW TABLES"));
            if (ctx.databaseName() != null) sb.append(" ").append(kw("IN")).append(" ").append(ctx.databaseName().getText());
            return sb.toString();
        }
        if (ctx.COLUMNS() != null) {
            return indent() + kw("SHOW COLUMNS") + " " + kw("IN") + " " + visitTableName(ctx.tableName());
        }
        if (ctx.PARTITIONS() != null) {
            return indent() + kw("SHOW PARTITIONS") + " " + visitTableName(ctx.tableName());
        }
        return indent() + ctx.getText();
    }

    @Override
    public String visitDescribeStatement(SparkSqlParser.DescribeStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DESCRIBE"));
        if (ctx.EXTENDED() != null) sb.append(" ").append(kw("EXTENDED"));
        if (ctx.FORMATTED() != null) sb.append(" ").append(kw("FORMATTED"));
        sb.append(" ").append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    @Override
    public String visitExplainStatement(SparkSqlParser.ExplainStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("EXPLAIN"));
        if (ctx.EXTENDED() != null) sb.append(" ").append(kw("EXTENDED"));
        sb.append("\n");
        indentLevel++;
        sb.append(visit(ctx.sqlStatement()));
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitCacheStatement(SparkSqlParser.CacheStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CACHE"));
        if (ctx.LAZY() != null) sb.append(" ").append(kw("LAZY"));
        sb.append(" ").append(kw("TABLE")).append(" ").append(visitTableName(ctx.tableName()));
        if (ctx.AS() != null && ctx.selectStatement() != null) {
            sb.append("\n").append(indent()).append(kw("AS")).append("\n");
            indentLevel++;
            sb.append(visit(ctx.selectStatement()));
            indentLevel--;
        }
        return sb.toString();
    }

    @Override
    public String visitUncacheStatement(SparkSqlParser.UncacheStatementContext ctx) {
        return indent() + kw("UNCACHE TABLE") + " " + visitTableName(ctx.tableName());
    }

    @Override
    public String visitRefreshStatement(SparkSqlParser.RefreshStatementContext ctx) {
        return indent() + kw("REFRESH TABLE") + " " + visitTableName(ctx.tableName());
    }

    // ============================================================
    // Expressions
    // ============================================================

    @Override
    public String visitAndExpression(SparkSqlParser.AndExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("AND") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitOrExpression(SparkSqlParser.OrExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("OR") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonExpression(SparkSqlParser.ComparisonExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + visitComparisonOperator(ctx.comparisonOperator()) + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonOperator(SparkSqlParser.ComparisonOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitNotExpression(SparkSqlParser.NotExpressionContext ctx) {
        return kw("NOT") + " " + visit(ctx.expression());
    }

    @Override
    public String visitParenExpression(SparkSqlParser.ParenExpressionContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitInExpression(SparkSqlParser.InExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression())).append(" ").append(kw("IN")).append(" (");
        if (ctx.expressionList() != null) {
            List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
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
    public String visitBetweenExpression(SparkSqlParser.BetweenExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("BETWEEN") + " " +
               visit(ctx.expression(1)) + " " + kw("AND") + " " + visit(ctx.expression(2));
    }

    @Override
    public String visitLikeExpression(SparkSqlParser.LikeExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("LIKE") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitRlikeExpression(SparkSqlParser.RlikeExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("RLIKE") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitRegexpExpression(SparkSqlParser.RegexpExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("REGEXP") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitIsNullExpression(SparkSqlParser.IsNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NULL");
    }

    @Override
    public String visitIsNotNullExpression(SparkSqlParser.IsNotNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NOT NULL");
    }

    @Override
    public String visitConcatExpression(SparkSqlParser.ConcatExpressionContext ctx) {
        return visit(ctx.expression(0)) + " || " + visit(ctx.expression(1));
    }

    @Override
    public String visitCastExpression(SparkSqlParser.CastExpressionContext ctx) {
        return kw("CAST") + "(" + visit(ctx.expression()) + " " + kw("AS") + " " + visitDataType(ctx.dataType()) + ")";
    }

    @Override
    public String visitMulDivModExpression(SparkSqlParser.MulDivModExpressionContext ctx) {
        String op;
        if (ctx.STAR() != null) op = "*";
        else if (ctx.DIVIDE() != null) op = "/";
        else op = "%";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitAddSubExpression(SparkSqlParser.AddSubExpressionContext ctx) {
        String op = ctx.PLUS() != null ? "+" : "-";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitBitwiseExpression(SparkSqlParser.BitwiseExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitStarExpression(SparkSqlParser.StarExpressionContext ctx) {
        return "*";
    }

    @Override
    public String visitDotExpression(SparkSqlParser.DotExpressionContext ctx) {
        return visit(ctx.expression(0)) + "." + visit(ctx.expression(1));
    }

    @Override
    public String visitArrayAccessExpression(SparkSqlParser.ArrayAccessExpressionContext ctx) {
        return visit(ctx.expression(0)) + "[" + visit(ctx.expression(1)) + "]";
    }

    @Override
    public String visitCaseExpr(SparkSqlParser.CaseExprContext ctx) {
        return visitCaseExpression(ctx.caseExpression());
    }

    @Override
    public String visitCaseExpression(SparkSqlParser.CaseExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("CASE"));
        List<SparkSqlParser.ExpressionContext> allExprs = ctx.expression();
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
    public String visitFunctionCallExpression(SparkSqlParser.FunctionCallExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visitFunctionCall(ctx.functionCall()));

        // OVER window clause is on FunctionCallExpressionContext, not FunctionCallContext
        if (ctx.OVER() != null) {
            sb.append(" ").append(kw("OVER")).append(" (");
            if (ctx.windowSpec() != null) sb.append(visitWindowSpec(ctx.windowSpec()));
            sb.append(")");
        }
        return sb.toString();
    }

    @Override
    public String visitFunctionCall(SparkSqlParser.FunctionCallContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.functionName().getText().toUpperCase());
        sb.append("(");
        if (ctx.STAR() != null) {
            sb.append("*");
        } else if (ctx.expressionList() != null) {
            if (ctx.DISTINCT() != null) sb.append(kw("DISTINCT")).append(" ");
            List<SparkSqlParser.ExpressionContext> args = ctx.expressionList().expression();
            for (int i = 0; i < args.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visit(args.get(i)));
            }
        }
        sb.append(")");
        return sb.toString();
    }

    // ============================================================
    // Complex type constructors
    // ============================================================

    @Override
    public String visitStructExpr(SparkSqlParser.StructExprContext ctx) {
        return visitStructExpression(ctx.structExpression());
    }

    @Override
    public String visitStructExpression(SparkSqlParser.StructExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("STRUCT")).append("(");
        List<SparkSqlParser.NamedExpressionContext> named = ctx.namedExpression();
        for (int i = 0; i < named.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitNamedExpression(named.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitNamedExpression(SparkSqlParser.NamedExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.alias() != null) sb.append(" ").append(kw("AS")).append(" ").append(ctx.alias().getText());
        return sb.toString();
    }

    @Override
    public String visitArrayExpr(SparkSqlParser.ArrayExprContext ctx) {
        return visitArrayExpression(ctx.arrayExpression());
    }

    @Override
    public String visitArrayExpression(SparkSqlParser.ArrayExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("ARRAY")).append("(");
        if (ctx.expressionList() != null) {
            List<SparkSqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
            for (int i = 0; i < exprs.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(visit(exprs.get(i)));
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String visitMapExpr(SparkSqlParser.MapExprContext ctx) {
        return visitMapExpression(ctx.mapExpression());
    }

    @Override
    public String visitMapExpression(SparkSqlParser.MapExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("MAP")).append("(");
        List<SparkSqlParser.ExpressionContext> exprs = ctx.expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        sb.append(")");
        return sb.toString();
    }

    // ============================================================
    // Literals & References
    // ============================================================

    @Override
    public String visitLiteralExpression(SparkSqlParser.LiteralExpressionContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public String visitLiteral(SparkSqlParser.LiteralContext ctx) {
        if (ctx.NULL_() != null) return kw("NULL");
        if (ctx.TRUE() != null) return kw("TRUE");
        if (ctx.FALSE() != null) return kw("FALSE");
        return ctx.getText();
    }

    @Override
    public String visitColumnRefExpression(SparkSqlParser.ColumnRefExpressionContext ctx) {
        return visitColumnRef(ctx.columnRef());
    }

    @Override
    public String visitColumnRef(SparkSqlParser.ColumnRefContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.tableName() != null) sb.append(visitTableName(ctx.tableName())).append(".");
        if (ctx.STAR() != null) sb.append("*");
        else if (ctx.columnName() != null) sb.append(ctx.columnName().getText());
        return sb.toString();
    }

    @Override
    public String visitTableName(SparkSqlParser.TableNameContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.databaseName() != null) sb.append(ctx.databaseName().getText()).append(".");
        sb.append(ctx.identifier().getText());
        return sb.toString();
    }

    @Override
    public String visitAlias(SparkSqlParser.AliasContext ctx) {
        return ctx.getText();
    }

    // ============================================================
    // 辅助方法
    // ============================================================

    private String formatExpression(SparkSqlParser.ExpressionContext ctx) {
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
