package com.example.antlr.mysql;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * MySQL SQL 格式化/美化器
 *
 * 继承 MySqlParserBaseVisitor，通过 Visitor 模式遍历语法树，
 * 将杂乱的 SQL 重新格式化为可读性强的标准缩进格式。
 *
 * 示例：
 *   输入: select a,b,count(*) from users u join orders o on u.id=o.uid where a>1 group by a order by b limit 10
 *   输出:
 *     SELECT
 *         a,
 *         b,
 *         COUNT(*)
 *     FROM users u
 *     JOIN orders o
 *         ON u.id = o.uid
 *     WHERE a > 1
 *     GROUP BY a
 *     ORDER BY b
 *     LIMIT 10
 */
public class MySqlFormatter extends MySqlParserBaseVisitor<String> {

    /** 缩进单位（默认 4 个空格） */
    private final String indentUnit;

    /** 关键字是否大写 */
    private final boolean uppercaseKeywords;

    /** 当前缩进层级 */
    private int indentLevel = 0;

    // ============================================================
    // 构造器 & 配置
    // ============================================================

    public MySqlFormatter() {
        this(4, true);
    }

    /**
     * @param indentSize        缩进空格数
     * @param uppercaseKeywords 关键字是否大写
     */
    public MySqlFormatter(int indentSize, boolean uppercaseKeywords) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentSize; i++) sb.append(' ');
        this.indentUnit = sb.toString();
        this.uppercaseKeywords = uppercaseKeywords;
    }

    private String indent() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append(indentUnit);
        }
        return sb.toString();
    }

    private String kw(String keyword) {
        return uppercaseKeywords ? keyword.toUpperCase() : keyword.toLowerCase();
    }

    // ============================================================
    // Root & Statements
    // ============================================================

    @Override
    public String visitRoot(MySqlParser.RootContext ctx) {
        return visitSqlStatements(ctx.sqlStatements());
    }

    @Override
    public String visitSqlStatements(MySqlParser.SqlStatementsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (MySqlParser.SqlStatementContext stmt : ctx.sqlStatement()) {
            if (sb.length() > 0) {
                sb.append("\n\n");
            }
            sb.append(visit(stmt));
            sb.append(";");
        }
        return sb.toString();
    }

    @Override
    public String visitSqlStatement(MySqlParser.SqlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDdlStatement(MySqlParser.DdlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    @Override
    public String visitDmlStatement(MySqlParser.DmlStatementContext ctx) {
        return visitChildren_concat(ctx);
    }

    // ============================================================
    // SELECT Statement（核心格式化逻辑）
    // ============================================================

    @Override
    public String visitSelectStatement(MySqlParser.SelectStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        // SELECT [DISTINCT]
        sb.append(indent()).append(kw("SELECT"));
        if (ctx.DISTINCT() != null) {
            sb.append(" ").append(kw("DISTINCT"));
        }
        sb.append("\n");

        // 列列表（每列一行，缩进）
        indentLevel++;
        sb.append(visitSelectElements(ctx.selectElements()));
        indentLevel--;

        // FROM
        if (ctx.FROM() != null && ctx.tableSource() != null && !ctx.tableSource().isEmpty()) {
            sb.append("\n").append(indent()).append(kw("FROM")).append(" ");
            List<MySqlParser.TableSourceContext> sources = ctx.tableSource();
            for (int i = 0; i < sources.size(); i++) {
                if (i > 0) {
                    sb.append(",\n").append(indent()).append("     ");
                }
                sb.append(visitTableSource(sources.get(i)));
            }
        }

        // JOIN
        for (MySqlParser.JoinClauseContext join : ctx.joinClause()) {
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

        // UNION
        if (ctx.UNION() != null) {
            sb.append("\n\n").append(indent()).append(kw("UNION"));
            if (ctx.ALL() != null) {
                sb.append(" ").append(kw("ALL"));
            }
            sb.append("\n\n");
            // 递归格式化 UNION 后面的 SELECT
            sb.append(visit(ctx.selectStatement()));
        }

        return sb.toString();
    }

    // ============================================================
    // Select Elements
    // ============================================================

    @Override
    public String visitSelectElements(MySqlParser.SelectElementsContext ctx) {
        if (ctx.STAR() != null) {
            return indent() + "*";
        }

        StringBuilder sb = new StringBuilder();
        List<MySqlParser.SelectElementContext> elements = ctx.selectElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(indent()).append(visitSelectElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitSelectElement(MySqlParser.SelectElementContext ctx) {
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
    public String visitTableSource(MySqlParser.TableSourceContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.tableName() != null) {
            sb.append(visitTableName(ctx.tableName()));
            if (ctx.alias() != null) {
                sb.append(" ").append(visitAlias(ctx.alias()));
            }
        } else if (ctx.selectStatement() != null) {
            // 子查询
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
    public String visitJoinClause(MySqlParser.JoinClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent());

        // JOIN 类型
        if (ctx.joinType() != null) {
            sb.append(visitJoinType(ctx.joinType())).append(" ");
        }
        sb.append(kw("JOIN")).append(" ");
        sb.append(visitTableSource(ctx.tableSource()));

        // ON 条件（缩进到下一行）
        if (ctx.ON() != null) {
            sb.append("\n");
            indentLevel++;
            sb.append(indent()).append(kw("ON")).append(" ").append(visit(ctx.expression()));
            indentLevel--;
        }

        return sb.toString();
    }

    @Override
    public String visitJoinType(MySqlParser.JoinTypeContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.INNER() != null) sb.append(kw("INNER"));
        else if (ctx.LEFT() != null) {
            sb.append(kw("LEFT"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
        } else if (ctx.RIGHT() != null) {
            sb.append(kw("RIGHT"));
            if (ctx.OUTER() != null) sb.append(" ").append(kw("OUTER"));
        } else if (ctx.CROSS() != null) sb.append(kw("CROSS"));
        return sb.toString();
    }

    // ============================================================
    // WHERE / GROUP BY / HAVING / ORDER BY / LIMIT
    // ============================================================

    @Override
    public String visitWhereClause(MySqlParser.WhereClauseContext ctx) {
        return indent() + kw("WHERE") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitGroupByClause(MySqlParser.GroupByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("GROUP BY")).append(" ");
        List<MySqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
        for (int i = 0; i < exprs.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visit(exprs.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitHavingClause(MySqlParser.HavingClauseContext ctx) {
        return indent() + kw("HAVING") + " " + formatExpression(ctx.expression());
    }

    @Override
    public String visitOrderByClause(MySqlParser.OrderByClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ORDER BY")).append(" ");
        List<MySqlParser.OrderByElementContext> elements = ctx.orderByElement();
        for (int i = 0; i < elements.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(visitOrderByElement(elements.get(i)));
        }
        return sb.toString();
    }

    @Override
    public String visitOrderByElement(MySqlParser.OrderByElementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression()));
        if (ctx.ASC() != null) sb.append(" ").append(kw("ASC"));
        if (ctx.DESC() != null) sb.append(" ").append(kw("DESC"));
        return sb.toString();
    }

    @Override
    public String visitLimitClause(MySqlParser.LimitClauseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("LIMIT")).append(" ");
        List<MySqlParser.ExpressionContext> exprs = ctx.expression();
        sb.append(visit(exprs.get(0)));
        if (ctx.OFFSET() != null && exprs.size() > 1) {
            sb.append(" ").append(kw("OFFSET")).append(" ").append(visit(exprs.get(1)));
        } else if (exprs.size() > 1) {
            sb.append(", ").append(visit(exprs.get(1)));
        }
        return sb.toString();
    }

    // ============================================================
    // INSERT Statement
    // ============================================================

    @Override
    public String visitInsertStatement(MySqlParser.InsertStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("INSERT INTO")).append(" ");
        sb.append(visitTableName(ctx.tableName()));

        // 列名列表
        if (ctx.columnNameList() != null) {
            sb.append(" (");
            List<MySqlParser.ColumnNameContext> cols = ctx.columnNameList().columnName();
            for (int i = 0; i < cols.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(cols.get(i).getText());
            }
            sb.append(")");
        }

        // VALUES 或子查询
        if (ctx.VALUES() != null) {
            sb.append("\n").append(indent()).append(kw("VALUES"));

            List<MySqlParser.ExpressionListContext> rows = ctx.expressionList();
            for (int i = 0; i < rows.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append("\n");
                indentLevel++;
                sb.append(indent()).append("(");
                List<MySqlParser.ExpressionContext> vals = rows.get(i).expression();
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

    // ============================================================
    // UPDATE Statement
    // ============================================================

    @Override
    public String visitUpdateStatement(MySqlParser.UpdateStatementContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("UPDATE")).append(" ");
        sb.append(visitTableName(ctx.tableName()));
        sb.append("\n").append(indent()).append(kw("SET"));

        // 赋值列表（每个一行）
        List<MySqlParser.UpdateAssignmentContext> assignments = ctx.updateAssignment();
        indentLevel++;
        for (int i = 0; i < assignments.size(); i++) {
            sb.append("\n").append(indent());
            sb.append(visitUpdateAssignment(assignments.get(i)));
            if (i < assignments.size() - 1) {
                sb.append(",");
            }
        }
        indentLevel--;

        // WHERE
        if (ctx.whereClause() != null) {
            sb.append("\n").append(visitWhereClause(ctx.whereClause()));
        }

        return sb.toString();
    }

    @Override
    public String visitUpdateAssignment(MySqlParser.UpdateAssignmentContext ctx) {
        return visit(ctx.columnRef()) + " = " + visit(ctx.expression());
    }

    // ============================================================
    // DELETE Statement
    // ============================================================

    @Override
    public String visitDeleteStatement(MySqlParser.DeleteStatementContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DELETE FROM")).append(" ");
        sb.append(visitTableName(ctx.tableName()));

        if (ctx.whereClause() != null) {
            sb.append("\n").append(visitWhereClause(ctx.whereClause()));
        }

        return sb.toString();
    }

    // ============================================================
    // DDL: CREATE TABLE
    // ============================================================

    @Override
    public String visitCreateTable(MySqlParser.CreateTableContext ctx) {
        StringBuilder sb = new StringBuilder();

        sb.append(indent()).append(kw("CREATE TABLE"));
        if (ctx.IF_() != null) {
            sb.append(" ").append(kw("IF NOT EXISTS"));
        }
        sb.append(" ").append(visitTableName(ctx.tableName())).append(" (\n");

        indentLevel++;

        // 列定义
        List<MySqlParser.ColumnDefinitionContext> colDefs = ctx.columnDefinition();
        List<MySqlParser.TableConstraintContext> constraints = ctx.tableConstraint();
        int totalItems = colDefs.size() + constraints.size();
        int index = 0;

        for (MySqlParser.ColumnDefinitionContext colDef : colDefs) {
            sb.append(indent()).append(visitColumnDefinition(colDef));
            index++;
            if (index < totalItems) sb.append(",");
            sb.append("\n");
        }

        // 表约束
        for (MySqlParser.TableConstraintContext constraint : constraints) {
            sb.append(indent()).append(visitTableConstraint(constraint));
            index++;
            if (index < totalItems) sb.append(",");
            sb.append("\n");
        }

        indentLevel--;
        sb.append(indent()).append(")");

        // 表选项
        if (ctx.tableOptions() != null) {
            sb.append("\n").append(visitTableOptions(ctx.tableOptions()));
        }

        return sb.toString();
    }

    @Override
    public String visitColumnDefinition(MySqlParser.ColumnDefinitionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.columnName().getText());
        sb.append(" ").append(visitDataType(ctx.dataType()));

        for (MySqlParser.ColumnConstraintContext cc : ctx.columnConstraint()) {
            sb.append(" ").append(visitColumnConstraint(cc));
        }
        return sb.toString();
    }

    @Override
    public String visitDataType(MySqlParser.DataTypeContext ctx) {
        // 逐个 token 拼接，保留空格（避免 BIGINT UNSIGNED → BIGINTUNSIGNED）
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i > 0) {
                String prev = ctx.getChild(i - 1).getText();
                String curr = ctx.getChild(i).getText();
                // 左括号前、右括号、逗号前不加空格
                if (!curr.equals("(") && !curr.equals(")") && !curr.equals(",")
                        && !prev.equals("(")) {
                    sb.append(" ");
                }
            }
            String text = ctx.getChild(i).getText();
            sb.append(uppercaseKeywords ? text.toUpperCase() : text.toLowerCase());
        }
        return sb.toString();
    }

    @Override
    public String visitColumnConstraint(MySqlParser.ColumnConstraintContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.PRIMARY() != null) {
            sb.append(kw("PRIMARY KEY"));
        } else if (ctx.UNIQUE() != null) {
            sb.append(kw("UNIQUE"));
        } else if (ctx.AUTO_INCREMENT() != null) {
            sb.append(kw("AUTO_INCREMENT"));
        } else if (ctx.NOT() != null && ctx.NULL_() != null) {
            sb.append(kw("NOT NULL"));
        } else if (ctx.NULL_() != null) {
            sb.append(kw("NULL"));
        } else if (ctx.DEFAULT() != null) {
            sb.append(kw("DEFAULT")).append(" ").append(visit(ctx.expression()));
        } else if (ctx.COMMENT() != null) {
            sb.append(kw("COMMENT")).append(" ").append(ctx.STRING_LITERAL().getText());
        }
        return sb.toString();
    }

    @Override
    public String visitTableConstraint(MySqlParser.TableConstraintContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.PRIMARY() != null) {
            sb.append(kw("PRIMARY KEY")).append(" (").append(visitIndexColumnList(ctx.indexColumnList())).append(")");
        } else if (ctx.UNIQUE() != null) {
            sb.append(kw("UNIQUE"));
            if (ctx.KEY() != null) sb.append(" ").append(kw("KEY"));
            if (ctx.indexName() != null) sb.append(" ").append(ctx.indexName().getText());
            sb.append(" (").append(visitIndexColumnList(ctx.indexColumnList())).append(")");
        } else if (ctx.FOREIGN() != null) {
            sb.append(kw("FOREIGN KEY")).append(" (");
            sb.append(ctx.columnNameList(0).getText());
            sb.append(") ").append(kw("REFERENCES")).append(" ");
            sb.append(visitTableName(ctx.tableName()));
            sb.append(" (").append(ctx.columnNameList(1).getText()).append(")");
        } else if (ctx.INDEX() != null || ctx.KEY() != null) {
            sb.append(ctx.INDEX() != null ? kw("INDEX") : kw("KEY"));
            if (ctx.indexName() != null) sb.append(" ").append(ctx.indexName().getText());
            sb.append(" (").append(visitIndexColumnList(ctx.indexColumnList())).append(")");
        }
        return sb.toString();
    }

    @Override
    public String visitIndexColumnList(MySqlParser.IndexColumnListContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitTableOptions(MySqlParser.TableOptionsContext ctx) {
        StringBuilder sb = new StringBuilder();
        for (MySqlParser.TableOptionContext opt : ctx.tableOption()) {
            sb.append(indent()).append(visitTableOption(opt)).append("\n");
        }
        return sb.toString().stripTrailing();
    }

    @Override
    public String visitTableOption(MySqlParser.TableOptionContext ctx) {
        return ctx.getText().toUpperCase();
    }

    // ============================================================
    // DDL: 其他
    // ============================================================

    @Override
    public String visitCreateDatabase(MySqlParser.CreateDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF NOT EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        return sb.toString();
    }

    @Override
    public String visitDropDatabase(MySqlParser.DropDatabaseContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP DATABASE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(ctx.databaseName().getText());
        return sb.toString();
    }

    @Override
    public String visitDropTable(MySqlParser.DropTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP TABLE"));
        if (ctx.IF_() != null) sb.append(" ").append(kw("IF EXISTS"));
        sb.append(" ").append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    @Override
    public String visitAlterTable(MySqlParser.AlterTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("ALTER TABLE")).append(" ");
        sb.append(visitTableName(ctx.tableName()));

        List<MySqlParser.AlterTableActionContext> actions = ctx.alterTableAction();
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
    public String visitAlterTableAction(MySqlParser.AlterTableActionContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.ADD() != null) {
            sb.append(kw("ADD"));
            if (ctx.COLUMN() != null) sb.append(" ").append(kw("COLUMN"));
            sb.append(" ").append(visitColumnDefinition(ctx.columnDefinition()));
        } else if (ctx.DROP() != null) {
            sb.append(kw("DROP"));
            if (ctx.COLUMN() != null) sb.append(" ").append(kw("COLUMN"));
            sb.append(" ").append(ctx.columnName().getText());
        } else if (ctx.ALTER() != null) {
            sb.append(kw("ALTER COLUMN")).append(" ").append(ctx.columnName().getText());
            sb.append(" ").append(kw("SET DEFAULT")).append(" ").append(visit(ctx.expression()));
        }
        return sb.toString();
    }

    @Override
    public String visitTruncateTable(MySqlParser.TruncateTableContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("TRUNCATE TABLE")).append(" ");
        sb.append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    @Override
    public String visitCreateIndex(MySqlParser.CreateIndexContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("CREATE"));
        if (ctx.UNIQUE() != null) sb.append(" ").append(kw("UNIQUE"));
        sb.append(" ").append(kw("INDEX")).append(" ").append(ctx.indexName().getText());
        sb.append("\n");
        indentLevel++;
        sb.append(indent()).append(kw("ON")).append(" ").append(visitTableName(ctx.tableName()));
        sb.append(" (").append(visitIndexColumnList(ctx.indexColumnList())).append(")");
        indentLevel--;
        return sb.toString();
    }

    @Override
    public String visitDropIndex(MySqlParser.DropIndexContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(indent()).append(kw("DROP INDEX")).append(" ").append(ctx.indexName().getText());
        sb.append(" ").append(kw("ON")).append(" ").append(visitTableName(ctx.tableName()));
        return sb.toString();
    }

    // ============================================================
    // Show / Use / Describe
    // ============================================================

    @Override
    public String visitShowStatement(MySqlParser.ShowStatementContext ctx) {
        if (ctx.DATABASES() != null) return indent() + kw("SHOW DATABASES");
        if (ctx.TABLES() != null) return indent() + kw("SHOW TABLES");
        return indent() + ctx.getText();
    }

    @Override
    public String visitUseStatement(MySqlParser.UseStatementContext ctx) {
        return indent() + kw("USE") + " " + ctx.databaseName().getText();
    }

    @Override
    public String visitDescribeStatement(MySqlParser.DescribeStatementContext ctx) {
        return indent() + kw("DESCRIBE") + " " + visitTableName(ctx.tableName());
    }

    // ============================================================
    // Expressions（格式化运算符两侧的空格）
    // ============================================================

    @Override
    public String visitAndExpression(MySqlParser.AndExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("AND") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitOrExpression(MySqlParser.OrExpressionContext ctx) {
        return visit(ctx.expression(0)) + "\n" + indent() + "  " + kw("OR") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonExpression(MySqlParser.ComparisonExpressionContext ctx) {
        String op = visitComparisonOperator(ctx.comparisonOperator());
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitComparisonOperator(MySqlParser.ComparisonOperatorContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitNotExpression(MySqlParser.NotExpressionContext ctx) {
        return kw("NOT") + " " + visit(ctx.expression());
    }

    @Override
    public String visitParenExpression(MySqlParser.ParenExpressionContext ctx) {
        return "(" + visit(ctx.expression()) + ")";
    }

    @Override
    public String visitInExpression(MySqlParser.InExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(visit(ctx.expression())).append(" ").append(kw("IN")).append(" (");
        if (ctx.expressionList() != null) {
            List<MySqlParser.ExpressionContext> exprs = ctx.expressionList().expression();
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
    public String visitBetweenExpression(MySqlParser.BetweenExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("BETWEEN") + " " +
               visit(ctx.expression(1)) + " " + kw("AND") + " " + visit(ctx.expression(2));
    }

    @Override
    public String visitLikeExpression(MySqlParser.LikeExpressionContext ctx) {
        return visit(ctx.expression(0)) + " " + kw("LIKE") + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitIsNullExpression(MySqlParser.IsNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NULL");
    }

    @Override
    public String visitIsNotNullExpression(MySqlParser.IsNotNullExpressionContext ctx) {
        return visit(ctx.expression()) + " " + kw("IS NOT NULL");
    }

    @Override
    public String visitMulDivModExpression(MySqlParser.MulDivModExpressionContext ctx) {
        String op;
        if (ctx.STAR() != null) op = "*";
        else if (ctx.DIVIDE() != null) op = "/";
        else op = "%";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitAddSubExpression(MySqlParser.AddSubExpressionContext ctx) {
        String op = ctx.PLUS() != null ? "+" : "-";
        return visit(ctx.expression(0)) + " " + op + " " + visit(ctx.expression(1));
    }

    @Override
    public String visitStarExpression(MySqlParser.StarExpressionContext ctx) {
        return "*";
    }

    // ============================================================
    // CASE Expression
    // ============================================================

    @Override
    public String visitCaseExpr(MySqlParser.CaseExprContext ctx) {
        return visitCaseExpression(ctx.caseExpression());
    }

    @Override
    public String visitCaseExpression(MySqlParser.CaseExpressionContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(kw("CASE"));

        // CASE <expr>?
        List<MySqlParser.ExpressionContext> allExprs = ctx.expression();
        int exprIdx = 0;

        // 如果第一个表达式不跟在 WHEN/THEN/ELSE 后面，就是 CASE <expr>
        int whenCount = ctx.WHEN().size();
        boolean hasSimpleCase = allExprs.size() > whenCount * 2 + (ctx.ELSE() != null ? 1 : 0);
        if (hasSimpleCase) {
            sb.append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
        }

        // WHEN ... THEN ...
        indentLevel++;
        for (int i = 0; i < whenCount; i++) {
            sb.append("\n").append(indent());
            sb.append(kw("WHEN")).append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
            sb.append(" ").append(kw("THEN")).append(" ").append(visit(allExprs.get(exprIdx)));
            exprIdx++;
        }

        // ELSE
        if (ctx.ELSE() != null) {
            sb.append("\n").append(indent());
            sb.append(kw("ELSE")).append(" ").append(visit(allExprs.get(exprIdx)));
        }
        indentLevel--;

        sb.append("\n").append(indent()).append(kw("END"));
        return sb.toString();
    }

    // ============================================================
    // Function Call
    // ============================================================

    @Override
    public String visitFunctionCallExpression(MySqlParser.FunctionCallExpressionContext ctx) {
        return visitFunctionCall(ctx.functionCall());
    }

    @Override
    public String visitFunctionCall(MySqlParser.FunctionCallContext ctx) {
        StringBuilder sb = new StringBuilder();
        sb.append(ctx.functionName().getText().toUpperCase());
        sb.append("(");
        if (ctx.STAR() != null) {
            sb.append("*");
        } else if (ctx.expressionList() != null) {
            if (ctx.DISTINCT() != null) {
                sb.append(kw("DISTINCT")).append(" ");
            }
            List<MySqlParser.ExpressionContext> args = ctx.expressionList().expression();
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
    public String visitLiteralExpression(MySqlParser.LiteralExpressionContext ctx) {
        return visitLiteral(ctx.literal());
    }

    @Override
    public String visitLiteral(MySqlParser.LiteralContext ctx) {
        if (ctx.NULL_() != null) return kw("NULL");
        if (ctx.TRUE() != null) return kw("TRUE");
        if (ctx.FALSE() != null) return kw("FALSE");
        return ctx.getText();
    }

    @Override
    public String visitColumnRefExpression(MySqlParser.ColumnRefExpressionContext ctx) {
        return visitColumnRef(ctx.columnRef());
    }

    @Override
    public String visitColumnRef(MySqlParser.ColumnRefContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.tableName() != null) {
            sb.append(visitTableName(ctx.tableName())).append(".");
        }
        if (ctx.STAR() != null) {
            sb.append("*");
        } else if (ctx.columnName() != null) {
            sb.append(ctx.columnName().getText());
        }
        return sb.toString();
    }

    @Override
    public String visitTableName(MySqlParser.TableNameContext ctx) {
        StringBuilder sb = new StringBuilder();
        if (ctx.databaseName() != null) {
            sb.append(ctx.databaseName().getText()).append(".");
        }
        sb.append(ctx.identifier().getText());
        return sb.toString();
    }

    @Override
    public String visitAlias(MySqlParser.AliasContext ctx) {
        return ctx.getText();
    }

    // ============================================================
    // 辅助方法
    // ============================================================

    /**
     * 格式化 WHERE/HAVING 中的复合表达式
     * AND/OR 自动换行缩进
     */
    private String formatExpression(MySqlParser.ExpressionContext ctx) {
        indentLevel++;
        String result = visit(ctx);
        indentLevel--;
        return result;
    }

    /**
     * 默认回退：拼接所有子节点的文本
     */
    private String visitChildren_concat(ParseTree ctx) {
        if (ctx.getChildCount() == 0) {
            return ctx.getText();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            String childResult = visit(ctx.getChild(i));
            if (childResult != null) {
                sb.append(childResult);
            }
        }
        return sb.toString();
    }

    /**
     * 终端节点回退
     */
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
