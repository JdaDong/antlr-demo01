// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/sparksql/SparkSqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.sparksql;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SparkSqlParser}.
 */
public interface SparkSqlParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(SparkSqlParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(SparkSqlParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatements(SparkSqlParser.SqlStatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatements(SparkSqlParser.SqlStatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void enterSqlStatement(SparkSqlParser.SqlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 */
	void exitSqlStatement(SparkSqlParser.SqlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDdlStatement(SparkSqlParser.DdlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDdlStatement(SparkSqlParser.DdlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void enterCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 */
	void exitCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void enterDropDatabase(SparkSqlParser.DropDatabaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 */
	void exitDropDatabase(SparkSqlParser.DropDatabaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(SparkSqlParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(SparkSqlParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void enterDropTable(SparkSqlParser.DropTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dropTable}.
	 * @param ctx the parse tree
	 */
	void exitDropTable(SparkSqlParser.DropTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterRenameTable(SparkSqlParser.RenameTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitRenameTable(SparkSqlParser.RenameTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addColumns}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAddColumns(SparkSqlParser.AddColumnsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addColumns}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAddColumns(SparkSqlParser.AddColumnsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterDropColumn(SparkSqlParser.DropColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitDropColumn(SparkSqlParser.DropColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code setTableProperties}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterSetTableProperties(SparkSqlParser.SetTablePropertiesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code setTableProperties}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitSetTableProperties(SparkSqlParser.SetTablePropertiesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterAddPartition(SparkSqlParser.AddPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitAddPartition(SparkSqlParser.AddPartitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dropPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void enterDropPartition(SparkSqlParser.DropPartitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dropPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 */
	void exitDropPartition(SparkSqlParser.DropPartitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#createView}.
	 * @param ctx the parse tree
	 */
	void enterCreateView(SparkSqlParser.CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#createView}.
	 * @param ctx the parse tree
	 */
	void exitCreateView(SparkSqlParser.CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dropView}.
	 * @param ctx the parse tree
	 */
	void enterDropView(SparkSqlParser.DropViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dropView}.
	 * @param ctx the parse tree
	 */
	void exitDropView(SparkSqlParser.DropViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void enterDmlStatement(SparkSqlParser.DmlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 */
	void exitDmlStatement(SparkSqlParser.DmlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#withClause}.
	 * @param ctx the parse tree
	 */
	void enterWithClause(SparkSqlParser.WithClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#withClause}.
	 * @param ctx the parse tree
	 */
	void exitWithClause(SparkSqlParser.WithClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 */
	void enterCteDefinition(SparkSqlParser.CteDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 */
	void exitCteDefinition(SparkSqlParser.CteDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void enterSelectStatement(SparkSqlParser.SelectStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 */
	void exitSelectStatement(SparkSqlParser.SelectStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void enterInsertStatement(SparkSqlParser.InsertStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 */
	void exitInsertStatement(SparkSqlParser.InsertStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void enterUtilityStatement(SparkSqlParser.UtilityStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 */
	void exitUtilityStatement(SparkSqlParser.UtilityStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void enterUseStatement(SparkSqlParser.UseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#useStatement}.
	 * @param ctx the parse tree
	 */
	void exitUseStatement(SparkSqlParser.UseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void enterShowStatement(SparkSqlParser.ShowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#showStatement}.
	 * @param ctx the parse tree
	 */
	void exitShowStatement(SparkSqlParser.ShowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#describeStatement}.
	 * @param ctx the parse tree
	 */
	void enterDescribeStatement(SparkSqlParser.DescribeStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#describeStatement}.
	 * @param ctx the parse tree
	 */
	void exitDescribeStatement(SparkSqlParser.DescribeStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#explainStatement}.
	 * @param ctx the parse tree
	 */
	void enterExplainStatement(SparkSqlParser.ExplainStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#explainStatement}.
	 * @param ctx the parse tree
	 */
	void exitExplainStatement(SparkSqlParser.ExplainStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#cacheStatement}.
	 * @param ctx the parse tree
	 */
	void enterCacheStatement(SparkSqlParser.CacheStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#cacheStatement}.
	 * @param ctx the parse tree
	 */
	void exitCacheStatement(SparkSqlParser.CacheStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#uncacheStatement}.
	 * @param ctx the parse tree
	 */
	void enterUncacheStatement(SparkSqlParser.UncacheStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#uncacheStatement}.
	 * @param ctx the parse tree
	 */
	void exitUncacheStatement(SparkSqlParser.UncacheStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#refreshStatement}.
	 * @param ctx the parse tree
	 */
	void enterRefreshStatement(SparkSqlParser.RefreshStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#refreshStatement}.
	 * @param ctx the parse tree
	 */
	void exitRefreshStatement(SparkSqlParser.RefreshStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void enterSelectElements(SparkSqlParser.SelectElementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#selectElements}.
	 * @param ctx the parse tree
	 */
	void exitSelectElements(SparkSqlParser.SelectElementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void enterSelectElement(SparkSqlParser.SelectElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#selectElement}.
	 * @param ctx the parse tree
	 */
	void exitSelectElement(SparkSqlParser.SelectElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void enterTableSource(SparkSqlParser.TableSourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#tableSource}.
	 * @param ctx the parse tree
	 */
	void exitTableSource(SparkSqlParser.TableSourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#tableSample}.
	 * @param ctx the parse tree
	 */
	void enterTableSample(SparkSqlParser.TableSampleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#tableSample}.
	 * @param ctx the parse tree
	 */
	void exitTableSample(SparkSqlParser.TableSampleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void enterJoinClause(SparkSqlParser.JoinClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#joinClause}.
	 * @param ctx the parse tree
	 */
	void exitJoinClause(SparkSqlParser.JoinClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void enterJoinType(SparkSqlParser.JoinTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#joinType}.
	 * @param ctx the parse tree
	 */
	void exitJoinType(SparkSqlParser.JoinTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#lateralViewClause}.
	 * @param ctx the parse tree
	 */
	void enterLateralViewClause(SparkSqlParser.LateralViewClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#lateralViewClause}.
	 * @param ctx the parse tree
	 */
	void exitLateralViewClause(SparkSqlParser.LateralViewClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void enterWhereClause(SparkSqlParser.WhereClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#whereClause}.
	 * @param ctx the parse tree
	 */
	void exitWhereClause(SparkSqlParser.WhereClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void enterGroupByClause(SparkSqlParser.GroupByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 */
	void exitGroupByClause(SparkSqlParser.GroupByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void enterHavingClause(SparkSqlParser.HavingClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#havingClause}.
	 * @param ctx the parse tree
	 */
	void exitHavingClause(SparkSqlParser.HavingClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void enterOrderByClause(SparkSqlParser.OrderByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 */
	void exitOrderByClause(SparkSqlParser.OrderByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#sortByClause}.
	 * @param ctx the parse tree
	 */
	void enterSortByClause(SparkSqlParser.SortByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#sortByClause}.
	 * @param ctx the parse tree
	 */
	void exitSortByClause(SparkSqlParser.SortByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#distributeByClause}.
	 * @param ctx the parse tree
	 */
	void enterDistributeByClause(SparkSqlParser.DistributeByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#distributeByClause}.
	 * @param ctx the parse tree
	 */
	void exitDistributeByClause(SparkSqlParser.DistributeByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#clusterByClause}.
	 * @param ctx the parse tree
	 */
	void enterClusterByClause(SparkSqlParser.ClusterByClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#clusterByClause}.
	 * @param ctx the parse tree
	 */
	void exitClusterByClause(SparkSqlParser.ClusterByClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void enterOrderByElement(SparkSqlParser.OrderByElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 */
	void exitOrderByElement(SparkSqlParser.OrderByElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void enterLimitClause(SparkSqlParser.LimitClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#limitClause}.
	 * @param ctx the parse tree
	 */
	void exitLimitClause(SparkSqlParser.LimitClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void enterWindowClause(SparkSqlParser.WindowClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#windowClause}.
	 * @param ctx the parse tree
	 */
	void exitWindowClause(SparkSqlParser.WindowClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#namedWindow}.
	 * @param ctx the parse tree
	 */
	void enterNamedWindow(SparkSqlParser.NamedWindowContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#namedWindow}.
	 * @param ctx the parse tree
	 */
	void exitNamedWindow(SparkSqlParser.NamedWindowContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void enterWindowSpec(SparkSqlParser.WindowSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#windowSpec}.
	 * @param ctx the parse tree
	 */
	void exitWindowSpec(SparkSqlParser.WindowSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void enterWindowFrame(SparkSqlParser.WindowFrameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void exitWindowFrame(SparkSqlParser.WindowFrameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void enterFrameBound(SparkSqlParser.FrameBoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#frameBound}.
	 * @param ctx the parse tree
	 */
	void exitFrameBound(SparkSqlParser.FrameBoundContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpression(SparkSqlParser.NotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpression(SparkSqlParser.NotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(SparkSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(SparkSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(SparkSqlParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(SparkSqlParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rlikeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRlikeExpression(SparkSqlParser.RlikeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rlikeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRlikeExpression(SparkSqlParser.RlikeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConcatExpression(SparkSqlParser.ConcatExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConcatExpression(SparkSqlParser.ConcatExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCastExpression(SparkSqlParser.CastExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCastExpression(SparkSqlParser.CastExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(SparkSqlParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(SparkSqlParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexpExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRegexpExpression(SparkSqlParser.RegexpExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexpExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRegexpExpression(SparkSqlParser.RegexpExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStructExpr(SparkSqlParser.StructExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStructExpr(SparkSqlParser.StructExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCallExpression(SparkSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCallExpression(SparkSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLikeExpression(SparkSqlParser.LikeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLikeExpression(SparkSqlParser.LikeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bitwiseExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitwiseExpression(SparkSqlParser.BitwiseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bitwiseExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitwiseExpression(SparkSqlParser.BitwiseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNullExpression(SparkSqlParser.IsNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNullExpression(SparkSqlParser.IsNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMulDivModExpression(SparkSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMulDivModExpression(SparkSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dotExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDotExpression(SparkSqlParser.DotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dotExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDotExpression(SparkSqlParser.DotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpr(SparkSqlParser.ArrayExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpr(SparkSqlParser.ArrayExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mapExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMapExpr(SparkSqlParser.MapExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mapExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMapExpr(SparkSqlParser.MapExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsNotNullExpression(SparkSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsNotNullExpression(SparkSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(SparkSqlParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(SparkSqlParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBetweenExpression(SparkSqlParser.BetweenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBetweenExpression(SparkSqlParser.BetweenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInExpression(SparkSqlParser.InExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInExpression(SparkSqlParser.InExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccessExpression(SparkSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccessExpression(SparkSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddSubExpression(SparkSqlParser.AddSubExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddSubExpression(SparkSqlParser.AddSubExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterColumnRefExpression(SparkSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitColumnRefExpression(SparkSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(SparkSqlParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(SparkSqlParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpression(SparkSqlParser.LiteralExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpression(SparkSqlParser.LiteralExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterStarExpression(SparkSqlParser.StarExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitStarExpression(SparkSqlParser.StarExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#structExpression}.
	 * @param ctx the parse tree
	 */
	void enterStructExpression(SparkSqlParser.StructExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#structExpression}.
	 * @param ctx the parse tree
	 */
	void exitStructExpression(SparkSqlParser.StructExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(SparkSqlParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(SparkSqlParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void enterMapExpression(SparkSqlParser.MapExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void exitMapExpression(SparkSqlParser.MapExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#namedExpression}.
	 * @param ctx the parse tree
	 */
	void enterNamedExpression(SparkSqlParser.NamedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#namedExpression}.
	 * @param ctx the parse tree
	 */
	void exitNamedExpression(SparkSqlParser.NamedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterComparisonOperator(SparkSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitComparisonOperator(SparkSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(SparkSqlParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(SparkSqlParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(SparkSqlParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(SparkSqlParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(SparkSqlParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(SparkSqlParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void enterColumnDefinition(SparkSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 */
	void exitColumnDefinition(SparkSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void enterDataType(SparkSqlParser.DataTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dataType}.
	 * @param ctx the parse tree
	 */
	void exitDataType(SparkSqlParser.DataTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#structField}.
	 * @param ctx the parse tree
	 */
	void enterStructField(SparkSqlParser.StructFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#structField}.
	 * @param ctx the parse tree
	 */
	void exitStructField(SparkSqlParser.StructFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 */
	void enterPartitionSpec(SparkSqlParser.PartitionSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 */
	void exitPartitionSpec(SparkSqlParser.PartitionSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#partitionVal}.
	 * @param ctx the parse tree
	 */
	void enterPartitionVal(SparkSqlParser.PartitionValContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#partitionVal}.
	 * @param ctx the parse tree
	 */
	void exitPartitionVal(SparkSqlParser.PartitionValContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#partitionColumnList}.
	 * @param ctx the parse tree
	 */
	void enterPartitionColumnList(SparkSqlParser.PartitionColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#partitionColumnList}.
	 * @param ctx the parse tree
	 */
	void exitPartitionColumnList(SparkSqlParser.PartitionColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#sortColumnList}.
	 * @param ctx the parse tree
	 */
	void enterSortColumnList(SparkSqlParser.SortColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#sortColumnList}.
	 * @param ctx the parse tree
	 */
	void exitSortColumnList(SparkSqlParser.SortColumnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void enterPropertyList(SparkSqlParser.PropertyListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#propertyList}.
	 * @param ctx the parse tree
	 */
	void exitPropertyList(SparkSqlParser.PropertyListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(SparkSqlParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(SparkSqlParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#dataSourceFormat}.
	 * @param ctx the parse tree
	 */
	void enterDataSourceFormat(SparkSqlParser.DataSourceFormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#dataSourceFormat}.
	 * @param ctx the parse tree
	 */
	void exitDataSourceFormat(SparkSqlParser.DataSourceFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 */
	void enterRowFormat(SparkSqlParser.RowFormatContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 */
	void exitRowFormat(SparkSqlParser.RowFormatContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void enterColumnNameList(SparkSqlParser.ColumnNameListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 */
	void exitColumnNameList(SparkSqlParser.ColumnNameListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(SparkSqlParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(SparkSqlParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void enterColumnRef(SparkSqlParser.ColumnRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void exitColumnRef(SparkSqlParser.ColumnRefContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(SparkSqlParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(SparkSqlParser.TableNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void enterDatabaseName(SparkSqlParser.DatabaseNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void exitDatabaseName(SparkSqlParser.DatabaseNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#viewName}.
	 * @param ctx the parse tree
	 */
	void enterViewName(SparkSqlParser.ViewNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#viewName}.
	 * @param ctx the parse tree
	 */
	void exitViewName(SparkSqlParser.ViewNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(SparkSqlParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(SparkSqlParser.ColumnNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void enterIndexName(SparkSqlParser.IndexNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#indexName}.
	 * @param ctx the parse tree
	 */
	void exitIndexName(SparkSqlParser.IndexNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#cteName}.
	 * @param ctx the parse tree
	 */
	void enterCteName(SparkSqlParser.CteNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#cteName}.
	 * @param ctx the parse tree
	 */
	void exitCteName(SparkSqlParser.CteNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(SparkSqlParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(SparkSqlParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(SparkSqlParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(SparkSqlParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link SparkSqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(SparkSqlParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SparkSqlParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(SparkSqlParser.LiteralContext ctx);
}