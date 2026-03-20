// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/mysql/MySqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.mysql;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MySqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MySqlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MySqlParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(MySqlParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(MySqlParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(MySqlParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(MySqlParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(MySqlParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(MySqlParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(MySqlParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(MySqlParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTable(MySqlParser.AlterTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTableAction(MySqlParser.AlterTableActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#truncateTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruncateTable(MySqlParser.TruncateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(MySqlParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#dropIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropIndex(MySqlParser.DropIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(MySqlParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(MySqlParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(MySqlParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#updateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStatement(MySqlParser.UpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStatement(MySqlParser.DeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatement(MySqlParser.ShowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(MySqlParser.UseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#describeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeStatement(MySqlParser.DescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(MySqlParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElement(MySqlParser.SelectElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSource(MySqlParser.TableSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#joinClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinClause(MySqlParser.JoinClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#joinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinType(MySqlParser.JoinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(MySqlParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(MySqlParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#havingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingClause(MySqlParser.HavingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(MySqlParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#orderByElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByElement(MySqlParser.OrderByElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(MySqlParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpression(MySqlParser.MulDivModExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(MySqlParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(MySqlParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(MySqlParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNotNullExpression(MySqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(MySqlParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(MySqlParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenExpression(MySqlParser.BetweenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpression(MySqlParser.InExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(MySqlParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(MySqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRefExpression(MySqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeExpression(MySqlParser.LikeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(MySqlParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(MySqlParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullExpression(MySqlParser.IsNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarExpression(MySqlParser.StarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(MySqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(MySqlParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(MySqlParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(MySqlParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(MySqlParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(MySqlParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnConstraint(MySqlParser.ColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableConstraint(MySqlParser.TableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#tableOptions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOptions(MySqlParser.TableOptionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#tableOption}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableOption(MySqlParser.TableOptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateAssignment(MySqlParser.UpdateAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#columnNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnNameList(MySqlParser.ColumnNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnList(MySqlParser.IndexColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MySqlParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#columnRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRef(MySqlParser.ColumnRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(MySqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#databaseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabaseName(MySqlParser.DatabaseNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(MySqlParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#indexName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexName(MySqlParser.IndexNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(MySqlParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MySqlParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MySqlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(MySqlParser.LiteralContext ctx);
}