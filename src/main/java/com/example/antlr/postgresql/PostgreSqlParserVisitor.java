// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/postgresql/PostgreSqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.postgresql;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PostgreSqlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PostgreSqlParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#root}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoot(PostgreSqlParser.RootContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#sqlStatements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatements(PostgreSqlParser.SqlStatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#sqlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqlStatement(PostgreSqlParser.SqlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#ddlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDdlStatement(PostgreSqlParser.DdlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#createDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateDatabase(PostgreSqlParser.CreateDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#createSchema}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateSchema(PostgreSqlParser.CreateSchemaContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#dropDatabase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropDatabase(PostgreSqlParser.DropDatabaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#createTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateTable(PostgreSqlParser.CreateTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#tableElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableElement(PostgreSqlParser.TableElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#dropTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropTable(PostgreSqlParser.DropTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#alterTable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTable(PostgreSqlParser.AlterTableContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#alterTableAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterTableAction(PostgreSqlParser.AlterTableActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#createIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreateIndex(PostgreSqlParser.CreateIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#dropIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDropIndex(PostgreSqlParser.DropIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#dmlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDmlStatement(PostgreSqlParser.DmlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#withClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWithClause(PostgreSqlParser.WithClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#cteDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteDefinition(PostgreSqlParser.CteDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#selectStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectStatement(PostgreSqlParser.SelectStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#insertStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsertStatement(PostgreSqlParser.InsertStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#updateStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateStatement(PostgreSqlParser.UpdateStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#deleteStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeleteStatement(PostgreSqlParser.DeleteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#selectElements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElements(PostgreSqlParser.SelectElementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#selectElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectElement(PostgreSqlParser.SelectElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#tableSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableSource(PostgreSqlParser.TableSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#joinClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinClause(PostgreSqlParser.JoinClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#joinType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinType(PostgreSqlParser.JoinTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#whereClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereClause(PostgreSqlParser.WhereClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#groupByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupByClause(PostgreSqlParser.GroupByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#havingClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHavingClause(PostgreSqlParser.HavingClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#orderByClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByClause(PostgreSqlParser.OrderByClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#orderByElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByElement(PostgreSqlParser.OrderByElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#limitClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimitClause(PostgreSqlParser.LimitClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#offsetClause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOffsetClause(PostgreSqlParser.OffsetClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mulDivModExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDivModExpression(PostgreSqlParser.MulDivModExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpression(PostgreSqlParser.NotExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparisonExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(PostgreSqlParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpression(PostgreSqlParser.ParenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pgCastExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPgCastExpression(PostgreSqlParser.PgCastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ilikeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIlikeExpression(PostgreSqlParser.IlikeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concatExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcatExpression(PostgreSqlParser.ConcatExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNotNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNotNullExpression(PostgreSqlParser.IsNotNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(PostgreSqlParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code castExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpression(PostgreSqlParser.CastExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonTextAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonTextAccessExpression(PostgreSqlParser.JsonTextAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(PostgreSqlParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code betweenExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetweenExpression(PostgreSqlParser.BetweenExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code inExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInExpression(PostgreSqlParser.InExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayAccessExpression(PostgreSqlParser.ArrayAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addSubExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddSubExpression(PostgreSqlParser.AddSubExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code jsonAccessExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJsonAccessExpression(PostgreSqlParser.JsonAccessExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code functionCallExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCallExpression(PostgreSqlParser.FunctionCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code columnRefExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRefExpression(PostgreSqlParser.ColumnRefExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code likeExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLikeExpression(PostgreSqlParser.LikeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(PostgreSqlParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpression(PostgreSqlParser.LiteralExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isNullExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNullExpression(PostgreSqlParser.IsNullExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code starExpression}
	 * labeled alternative in {@link PostgreSqlParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStarExpression(PostgreSqlParser.StarExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(PostgreSqlParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(PostgreSqlParser.CaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(PostgreSqlParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(PostgreSqlParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#columnDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnDefinition(PostgreSqlParser.ColumnDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#dataType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDataType(PostgreSqlParser.DataTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#columnConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnConstraint(PostgreSqlParser.ColumnConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#tableConstraint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableConstraint(PostgreSqlParser.TableConstraintContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#partitionStrategy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartitionStrategy(PostgreSqlParser.PartitionStrategyContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#indexMethod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexMethod(PostgreSqlParser.IndexMethodContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#updateAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdateAssignment(PostgreSqlParser.UpdateAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#columnNameList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnNameList(PostgreSqlParser.ColumnNameListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#indexColumnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumnList(PostgreSqlParser.IndexColumnListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#indexColumn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexColumn(PostgreSqlParser.IndexColumnContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(PostgreSqlParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#columnRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRef(PostgreSqlParser.ColumnRefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(PostgreSqlParser.TableNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#databaseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabaseName(PostgreSqlParser.DatabaseNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#schemaName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSchemaName(PostgreSqlParser.SchemaNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(PostgreSqlParser.ColumnNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#indexName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexName(PostgreSqlParser.IndexNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#constraintName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstraintName(PostgreSqlParser.ConstraintNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#cteName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCteName(PostgreSqlParser.CteNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(PostgreSqlParser.AliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(PostgreSqlParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#nonReservedKeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonReservedKeyword(PostgreSqlParser.NonReservedKeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link PostgreSqlParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(PostgreSqlParser.LiteralContext ctx);
}