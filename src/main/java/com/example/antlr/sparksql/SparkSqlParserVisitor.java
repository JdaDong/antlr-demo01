// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/sparksql/SparkSqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.sparksql;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SparkSqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SparkSqlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(SparkSqlParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(SparkSqlParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(SparkSqlParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(SparkSqlParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(SparkSqlParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(SparkSqlParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(SparkSqlParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(SparkSqlParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code renameTable}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameTable(SparkSqlParser.RenameTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addColumns}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddColumns(SparkSqlParser.AddColumnsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropColumn}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropColumn(SparkSqlParser.DropColumnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code setTableProperties}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetTableProperties(SparkSqlParser.SetTablePropertiesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddPartition(SparkSqlParser.AddPartitionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dropPartition}
	 * labeled alternative in {@link SparkSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropPartition(SparkSqlParser.DropPartitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#createView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateView(SparkSqlParser.CreateViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dropView}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropView(SparkSqlParser.DropViewContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(SparkSqlParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#withClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithClause(SparkSqlParser.WithClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteDefinition(SparkSqlParser.CteDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(SparkSqlParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(SparkSqlParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#utilityStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUtilityStatement(SparkSqlParser.UtilityStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#useStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUseStatement(SparkSqlParser.UseStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#showStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShowStatement(SparkSqlParser.ShowStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#describeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescribeStatement(SparkSqlParser.DescribeStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#explainStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplainStatement(SparkSqlParser.ExplainStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#cacheStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCacheStatement(SparkSqlParser.CacheStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#uncacheStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUncacheStatement(SparkSqlParser.UncacheStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#refreshStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRefreshStatement(SparkSqlParser.RefreshStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(SparkSqlParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElement(SparkSqlParser.SelectElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSource(SparkSqlParser.TableSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#tableSample}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSample(SparkSqlParser.TableSampleContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#joinClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinClause(SparkSqlParser.JoinClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#joinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinType(SparkSqlParser.JoinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#lateralViewClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLateralViewClause(SparkSqlParser.LateralViewClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(SparkSqlParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(SparkSqlParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#havingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingClause(SparkSqlParser.HavingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(SparkSqlParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#sortByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortByClause(SparkSqlParser.SortByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#distributeByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDistributeByClause(SparkSqlParser.DistributeByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#clusterByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClusterByClause(SparkSqlParser.ClusterByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByElement(SparkSqlParser.OrderByElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(SparkSqlParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#windowClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowClause(SparkSqlParser.WindowClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#namedWindow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedWindow(SparkSqlParser.NamedWindowContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#windowSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowSpec(SparkSqlParser.WindowSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#windowFrame}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWindowFrame(SparkSqlParser.WindowFrameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#frameBound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrameBound(SparkSqlParser.FrameBoundContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(SparkSqlParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(SparkSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(SparkSqlParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rlikeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRlikeExpression(SparkSqlParser.RlikeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatExpression(SparkSqlParser.ConcatExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(SparkSqlParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(SparkSqlParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexpExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexpExpression(SparkSqlParser.RegexpExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code structExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructExpr(SparkSqlParser.StructExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(SparkSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeExpression(SparkSqlParser.LikeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bitwiseExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitwiseExpression(SparkSqlParser.BitwiseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullExpression(SparkSqlParser.IsNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpression(SparkSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dotExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDotExpression(SparkSqlParser.DotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpr(SparkSqlParser.ArrayExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mapExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapExpr(SparkSqlParser.MapExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNotNullExpression(SparkSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(SparkSqlParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenExpression(SparkSqlParser.BetweenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpression(SparkSqlParser.InExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpression(SparkSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(SparkSqlParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRefExpression(SparkSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(SparkSqlParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(SparkSqlParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link SparkSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarExpression(SparkSqlParser.StarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#structExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructExpression(SparkSqlParser.StructExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#arrayExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(SparkSqlParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#mapExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapExpression(SparkSqlParser.MapExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#namedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamedExpression(SparkSqlParser.NamedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(SparkSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(SparkSqlParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(SparkSqlParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(SparkSqlParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(SparkSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(SparkSqlParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#structField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructField(SparkSqlParser.StructFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#partitionSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionSpec(SparkSqlParser.PartitionSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#partitionVal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionVal(SparkSqlParser.PartitionValContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#partitionColumnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionColumnList(SparkSqlParser.PartitionColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#sortColumnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortColumnList(SparkSqlParser.SortColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#propertyList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyList(SparkSqlParser.PropertyListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperty(SparkSqlParser.PropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#dataSourceFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataSourceFormat(SparkSqlParser.DataSourceFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#rowFormat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRowFormat(SparkSqlParser.RowFormatContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnNameList(SparkSqlParser.ColumnNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(SparkSqlParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#columnRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRef(SparkSqlParser.ColumnRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(SparkSqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#databaseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabaseName(SparkSqlParser.DatabaseNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#viewName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitViewName(SparkSqlParser.ViewNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(SparkSqlParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#indexName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexName(SparkSqlParser.IndexNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#cteName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteName(SparkSqlParser.CteNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(SparkSqlParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(SparkSqlParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link SparkSqlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(SparkSqlParser.LiteralContext ctx);
}