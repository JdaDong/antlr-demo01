// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/postgresql/PostgreSqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.postgresql;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PostgreSqlParser}.
 */
public interface PostgreSqlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(PostgreSqlParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(PostgreSqlParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatements(PostgreSqlParser.SqlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatements(PostgreSqlParser.SqlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(PostgreSqlParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(PostgreSqlParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(PostgreSqlParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(PostgreSqlParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#createSchema}.
	 * @param ctx the parse tree
	 */
	void enterCreateSchema(PostgreSqlParser.CreateSchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#createSchema}.
	 * @param ctx the parse tree
	 */
	void exitCreateSchema(PostgreSqlParser.CreateSchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(PostgreSqlParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(PostgreSqlParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(PostgreSqlParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(PostgreSqlParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#tableElement}.
	 * @param ctx the parse tree
	 */
	void enterTableElement(PostgreSqlParser.TableElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#tableElement}.
	 * @param ctx the parse tree
	 */
	void exitTableElement(PostgreSqlParser.TableElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(PostgreSqlParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(PostgreSqlParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAlterTable(PostgreSqlParser.AlterTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAlterTable(PostgreSqlParser.AlterTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 */
	void enterAlterTableAction(PostgreSqlParser.AlterTableActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 */
	void exitAlterTableAction(PostgreSqlParser.AlterTableActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(PostgreSqlParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(PostgreSqlParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(PostgreSqlParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(PostgreSqlParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(PostgreSqlParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(PostgreSqlParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#withClause}.
	 * @param ctx the parse tree
	 */
	void enterWithClause(PostgreSqlParser.WithClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#withClause}.
	 * @param ctx the parse tree
	 */
	void exitWithClause(PostgreSqlParser.WithClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 */
	void enterCteDefinition(PostgreSqlParser.CteDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 */
	void exitCteDefinition(PostgreSqlParser.CteDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(PostgreSqlParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(PostgreSqlParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(PostgreSqlParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(PostgreSqlParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(PostgreSqlParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(PostgreSqlParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectElement(PostgreSqlParser.SelectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectElement(PostgreSqlParser.SelectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSource(PostgreSqlParser.TableSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSource(PostgreSqlParser.TableSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(PostgreSqlParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(PostgreSqlParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(PostgreSqlParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(PostgreSqlParser.JoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(PostgreSqlParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(PostgreSqlParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(PostgreSqlParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(PostgreSqlParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void enterHavingClause(PostgreSqlParser.HavingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void exitHavingClause(PostgreSqlParser.HavingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(PostgreSqlParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(PostgreSqlParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void enterOrderByElement(PostgreSqlParser.OrderByElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void exitOrderByElement(PostgreSqlParser.OrderByElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(PostgreSqlParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(PostgreSqlParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#offsetClause}.
	 * @param ctx the parse tree
	 */
	void enterOffsetClause(PostgreSqlParser.OffsetClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#offsetClause}.
	 * @param ctx the parse tree
	 */
	void exitOffsetClause(PostgreSqlParser.OffsetClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpression(PostgreSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpression(PostgreSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(PostgreSqlParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(PostgreSqlParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(PostgreSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(PostgreSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(PostgreSqlParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(PostgreSqlParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pgCastExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPgCastExpression(PostgreSqlParser.PgCastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pgCastExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPgCastExpression(PostgreSqlParser.PgCastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ilikeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIlikeExpression(PostgreSqlParser.IlikeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ilikeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIlikeExpression(PostgreSqlParser.IlikeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConcatExpression(PostgreSqlParser.ConcatExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConcatExpression(PostgreSqlParser.ConcatExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNotNullExpression(PostgreSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNotNullExpression(PostgreSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(PostgreSqlParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(PostgreSqlParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(PostgreSqlParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(PostgreSqlParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonTextAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterJsonTextAccessExpression(PostgreSqlParser.JsonTextAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonTextAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitJsonTextAccessExpression(PostgreSqlParser.JsonTextAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(PostgreSqlParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(PostgreSqlParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBetweenExpression(PostgreSqlParser.BetweenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBetweenExpression(PostgreSqlParser.BetweenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(PostgreSqlParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(PostgreSqlParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(PostgreSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(PostgreSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(PostgreSqlParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(PostgreSqlParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code jsonAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterJsonAccessExpression(PostgreSqlParser.JsonAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code jsonAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitJsonAccessExpression(PostgreSqlParser.JsonAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(PostgreSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(PostgreSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterColumnRefExpression(PostgreSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitColumnRefExpression(PostgreSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLikeExpression(PostgreSqlParser.LikeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLikeExpression(PostgreSqlParser.LikeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(PostgreSqlParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(PostgreSqlParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(PostgreSqlParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(PostgreSqlParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNullExpression(PostgreSqlParser.IsNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNullExpression(PostgreSqlParser.IsNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStarExpression(PostgreSqlParser.StarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStarExpression(PostgreSqlParser.StarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(PostgreSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(PostgreSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(PostgreSqlParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(PostgreSqlParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(PostgreSqlParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(PostgreSqlParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(PostgreSqlParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(PostgreSqlParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(PostgreSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(PostgreSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(PostgreSqlParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(PostgreSqlParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterColumnConstraint(PostgreSqlParser.ColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitColumnConstraint(PostgreSqlParser.ColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterTableConstraint(PostgreSqlParser.TableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitTableConstraint(PostgreSqlParser.TableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#partitionStrategy}.
	 * @param ctx the parse tree
	 */
	void enterPartitionStrategy(PostgreSqlParser.PartitionStrategyContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#partitionStrategy}.
	 * @param ctx the parse tree
	 */
	void exitPartitionStrategy(PostgreSqlParser.PartitionStrategyContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#indexMethod}.
	 * @param ctx the parse tree
	 */
	void enterIndexMethod(PostgreSqlParser.IndexMethodContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#indexMethod}.
	 * @param ctx the parse tree
	 */
	void exitIndexMethod(PostgreSqlParser.IndexMethodContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 */
	void enterUpdateAssignment(PostgreSqlParser.UpdateAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 */
	void exitUpdateAssignment(PostgreSqlParser.UpdateAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void enterColumnNameList(PostgreSqlParser.ColumnNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void exitColumnNameList(PostgreSqlParser.ColumnNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnList(PostgreSqlParser.IndexColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnList(PostgreSqlParser.IndexColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#indexColumn}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumn(PostgreSqlParser.IndexColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#indexColumn}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumn(PostgreSqlParser.IndexColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(PostgreSqlParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(PostgreSqlParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void enterColumnRef(PostgreSqlParser.ColumnRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void exitColumnRef(PostgreSqlParser.ColumnRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(PostgreSqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(PostgreSqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void enterDatabaseName(PostgreSqlParser.DatabaseNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void exitDatabaseName(PostgreSqlParser.DatabaseNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void enterSchemaName(PostgreSqlParser.SchemaNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#schemaName}.
	 * @param ctx the parse tree
	 */
	void exitSchemaName(PostgreSqlParser.SchemaNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(PostgreSqlParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(PostgreSqlParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void enterIndexName(PostgreSqlParser.IndexNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void exitIndexName(PostgreSqlParser.IndexNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#constraintName}.
	 * @param ctx the parse tree
	 */
	void enterConstraintName(PostgreSqlParser.ConstraintNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#constraintName}.
	 * @param ctx the parse tree
	 */
	void exitConstraintName(PostgreSqlParser.ConstraintNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#cteName}.
	 * @param ctx the parse tree
	 */
	void enterCteName(PostgreSqlParser.CteNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#cteName}.
	 * @param ctx the parse tree
	 */
	void exitCteName(PostgreSqlParser.CteNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(PostgreSqlParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(PostgreSqlParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(PostgreSqlParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(PostgreSqlParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#nonReservedKeyword}.
	 * @param ctx the parse tree
	 */
	void enterNonReservedKeyword(PostgreSqlParser.NonReservedKeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#nonReservedKeyword}.
	 * @param ctx the parse tree
	 */
	void exitNonReservedKeyword(PostgreSqlParser.NonReservedKeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link PostgreSqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(PostgreSqlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PostgreSqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(PostgreSqlParser.LiteralContext ctx);
}