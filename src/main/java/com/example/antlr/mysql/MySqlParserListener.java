// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/mysql/MySqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.mysql;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MySqlParser}.
 */
public interface MySqlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MySqlParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(MySqlParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(MySqlParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatements(MySqlParser.SqlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatements(MySqlParser.SqlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(MySqlParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(MySqlParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(MySqlParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(MySqlParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(MySqlParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(MySqlParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(MySqlParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(MySqlParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(MySqlParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(MySqlParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(MySqlParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(MySqlParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAlterTable(MySqlParser.AlterTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAlterTable(MySqlParser.AlterTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 */
	void enterAlterTableAction(MySqlParser.AlterTableActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 */
	void exitAlterTableAction(MySqlParser.AlterTableActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void enterTruncateTable(MySqlParser.TruncateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#truncateTable}.
	 * @param ctx the parse tree
	 */
	void exitTruncateTable(MySqlParser.TruncateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(MySqlParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(MySqlParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(MySqlParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(MySqlParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(MySqlParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(MySqlParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(MySqlParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(MySqlParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(MySqlParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(MySqlParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void enterUpdateStatement(MySqlParser.UpdateStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#updateStatement}.
	 * @param ctx the parse tree
	 */
	void exitUpdateStatement(MySqlParser.UpdateStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void enterDeleteStatement(MySqlParser.DeleteStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 */
	void exitDeleteStatement(MySqlParser.DeleteStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatement(MySqlParser.ShowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatement(MySqlParser.ShowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(MySqlParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(MySqlParser.UseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#describeStatement}.
	 * @param ctx the parse tree
	 */
	void enterDescribeStatement(MySqlParser.DescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#describeStatement}.
	 * @param ctx the parse tree
	 */
	void exitDescribeStatement(MySqlParser.DescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(MySqlParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(MySqlParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectElement(MySqlParser.SelectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectElement(MySqlParser.SelectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSource(MySqlParser.TableSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSource(MySqlParser.TableSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(MySqlParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(MySqlParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(MySqlParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(MySqlParser.JoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(MySqlParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(MySqlParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(MySqlParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(MySqlParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void enterHavingClause(MySqlParser.HavingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void exitHavingClause(MySqlParser.HavingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(MySqlParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(MySqlParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void enterOrderByElement(MySqlParser.OrderByElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void exitOrderByElement(MySqlParser.OrderByElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(MySqlParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(MySqlParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpression(MySqlParser.MulDivModExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpression(MySqlParser.MulDivModExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(MySqlParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(MySqlParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(MySqlParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(MySqlParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(MySqlParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(MySqlParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNotNullExpression(MySqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNotNullExpression(MySqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(MySqlParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(MySqlParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(MySqlParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(MySqlParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBetweenExpression(MySqlParser.BetweenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBetweenExpression(MySqlParser.BetweenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(MySqlParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(MySqlParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(MySqlParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(MySqlParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(MySqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(MySqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterColumnRefExpression(MySqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitColumnRefExpression(MySqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLikeExpression(MySqlParser.LikeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLikeExpression(MySqlParser.LikeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(MySqlParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(MySqlParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(MySqlParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(MySqlParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNullExpression(MySqlParser.IsNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNullExpression(MySqlParser.IsNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStarExpression(MySqlParser.StarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link MySqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStarExpression(MySqlParser.StarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(MySqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(MySqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(MySqlParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(MySqlParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(MySqlParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(MySqlParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(MySqlParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(MySqlParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(MySqlParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(MySqlParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(MySqlParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(MySqlParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void enterColumnConstraint(MySqlParser.ColumnConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 */
	void exitColumnConstraint(MySqlParser.ColumnConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void enterTableConstraint(MySqlParser.TableConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 */
	void exitTableConstraint(MySqlParser.TableConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#tableOptions}.
	 * @param ctx the parse tree
	 */
	void enterTableOptions(MySqlParser.TableOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#tableOptions}.
	 * @param ctx the parse tree
	 */
	void exitTableOptions(MySqlParser.TableOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void enterTableOption(MySqlParser.TableOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#tableOption}.
	 * @param ctx the parse tree
	 */
	void exitTableOption(MySqlParser.TableOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 */
	void enterUpdateAssignment(MySqlParser.UpdateAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 */
	void exitUpdateAssignment(MySqlParser.UpdateAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void enterColumnNameList(MySqlParser.ColumnNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void exitColumnNameList(MySqlParser.ColumnNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 */
	void enterIndexColumnList(MySqlParser.IndexColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 */
	void exitIndexColumnList(MySqlParser.IndexColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MySqlParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MySqlParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void enterColumnRef(MySqlParser.ColumnRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void exitColumnRef(MySqlParser.ColumnRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(MySqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(MySqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void enterDatabaseName(MySqlParser.DatabaseNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void exitDatabaseName(MySqlParser.DatabaseNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(MySqlParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(MySqlParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void enterIndexName(MySqlParser.IndexNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void exitIndexName(MySqlParser.IndexNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(MySqlParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(MySqlParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(MySqlParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(MySqlParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link MySqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(MySqlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link MySqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(MySqlParser.LiteralContext ctx);
}