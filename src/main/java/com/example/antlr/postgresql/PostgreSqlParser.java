// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/postgresql/PostgreSqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.postgresql;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class PostgreSqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, FROM=2, WHERE=3, AND=4, OR=5, NOT=6, INSERT=7, INTO=8, VALUES=9, 
		UPDATE=10, SET=11, DELETE=12, CREATE=13, TABLE=14, DROP=15, ALTER=16, 
		ADD=17, COLUMN=18, INDEX=19, PRIMARY=20, KEY=21, FOREIGN=22, REFERENCES=23, 
		UNIQUE=24, NULL_=25, DEFAULT=26, IF_=27, EXISTS=28, DATABASE=29, SCHEMA=30, 
		ORDER=31, BY=32, GROUP=33, HAVING=34, LIMIT=35, OFFSET=36, JOIN=37, INNER=38, 
		LEFT=39, RIGHT=40, OUTER=41, CROSS=42, FULL=43, ON=44, AS=45, IN=46, BETWEEN=47, 
		LIKE=48, ILIKE=49, IS=50, DISTINCT=51, UNION=52, INTERSECT=53, EXCEPT=54, 
		ALL=55, CASE=56, WHEN=57, THEN=58, ELSE=59, END=60, ASC=61, DESC=62, RETURNING=63, 
		WITH=64, RECURSIVE=65, CASCADE=66, RESTRICT=67, CONSTRAINT=68, CHECK=69, 
		NULLS=70, FIRST=71, LAST=72, COUNT=73, SUM=74, AVG=75, MIN=76, MAX=77, 
		CAST=78, COALESCE=79, TRUE=80, FALSE=81, SERIAL=82, BIGSERIAL=83, SMALLSERIAL=84, 
		RETURNING_KW=85, CONCURRENTLY=86, USING=87, TABLESPACE=88, INHERITS=89, 
		PARTITION=90, RANGE=91, LIST=92, HASH=93, TEMP=94, TEMPORARY=95, UNLOGGED=96, 
		ONLY=97, SMALLINT=98, INT=99, INTEGER=100, BIGINT=101, REAL=102, FLOAT=103, 
		DOUBLE=104, PRECISION=105, DECIMAL=106, NUMERIC=107, CHAR=108, VARCHAR=109, 
		TEXT=110, BOOLEAN=111, BOOL=112, DATE=113, TIMESTAMP=114, TIME=115, INTERVAL=116, 
		JSON=117, JSONB=118, UUID=119, ARRAY=120, BYTEA=121, INET=122, CIDR=123, 
		MACADDR=124, MONEY=125, XML=126, STAR=127, COMMA=128, DOT=129, LPAREN=130, 
		RPAREN=131, LBRACKET=132, RBRACKET=133, SEMICOLON=134, EQ=135, NEQ=136, 
		LT=137, GT=138, LTE=139, GTE=140, PLUS=141, MINUS=142, DIVIDE=143, MOD=144, 
		CONCAT=145, CAST_OP=146, ARROW=147, DOUBLE_ARROW=148, INTEGER_LITERAL=149, 
		DECIMAL_LITERAL=150, STRING_LITERAL=151, DOLLAR_STRING=152, DOUBLE_QUOTED_ID=153, 
		IDENTIFIER=154, WS=155, LINE_COMMENT=156, BLOCK_COMMENT=157, COMMENT=158;
	public static final int
		RULE_root = 0, RULE_sqlStatements = 1, RULE_sqlStatement = 2, RULE_ddlStatement = 3, 
		RULE_createDatabase = 4, RULE_createSchema = 5, RULE_dropDatabase = 6, 
		RULE_createTable = 7, RULE_tableElement = 8, RULE_dropTable = 9, RULE_alterTable = 10, 
		RULE_alterTableAction = 11, RULE_createIndex = 12, RULE_dropIndex = 13, 
		RULE_dmlStatement = 14, RULE_withClause = 15, RULE_cteDefinition = 16, 
		RULE_selectStatement = 17, RULE_insertStatement = 18, RULE_updateStatement = 19, 
		RULE_deleteStatement = 20, RULE_selectElements = 21, RULE_selectElement = 22, 
		RULE_tableSource = 23, RULE_joinClause = 24, RULE_joinType = 25, RULE_whereClause = 26, 
		RULE_groupByClause = 27, RULE_havingClause = 28, RULE_orderByClause = 29, 
		RULE_orderByElement = 30, RULE_limitClause = 31, RULE_offsetClause = 32, 
		RULE_expression = 33, RULE_comparisonOperator = 34, RULE_caseExpression = 35, 
		RULE_functionCall = 36, RULE_functionName = 37, RULE_columnDefinition = 38, 
		RULE_dataType = 39, RULE_columnConstraint = 40, RULE_tableConstraint = 41, 
		RULE_partitionStrategy = 42, RULE_indexMethod = 43, RULE_updateAssignment = 44, 
		RULE_columnNameList = 45, RULE_indexColumnList = 46, RULE_indexColumn = 47, 
		RULE_expressionList = 48, RULE_columnRef = 49, RULE_tableName = 50, RULE_databaseName = 51, 
		RULE_schemaName = 52, RULE_columnName = 53, RULE_indexName = 54, RULE_constraintName = 55, 
		RULE_cteName = 56, RULE_alias = 57, RULE_identifier = 58, RULE_nonReservedKeyword = 59, 
		RULE_literal = 60;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "sqlStatements", "sqlStatement", "ddlStatement", "createDatabase", 
			"createSchema", "dropDatabase", "createTable", "tableElement", "dropTable", 
			"alterTable", "alterTableAction", "createIndex", "dropIndex", "dmlStatement", 
			"withClause", "cteDefinition", "selectStatement", "insertStatement", 
			"updateStatement", "deleteStatement", "selectElements", "selectElement", 
			"tableSource", "joinClause", "joinType", "whereClause", "groupByClause", 
			"havingClause", "orderByClause", "orderByElement", "limitClause", "offsetClause", 
			"expression", "comparisonOperator", "caseExpression", "functionCall", 
			"functionName", "columnDefinition", "dataType", "columnConstraint", "tableConstraint", 
			"partitionStrategy", "indexMethod", "updateAssignment", "columnNameList", 
			"indexColumnList", "indexColumn", "expressionList", "columnRef", "tableName", 
			"databaseName", "schemaName", "columnName", "indexName", "constraintName", 
			"cteName", "alias", "identifier", "nonReservedKeyword", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'*'", "','", "'.'", "'('", 
			"')'", "'['", "']'", "';'", "'='", null, "'<'", "'>'", "'<='", "'>='", 
			"'+'", "'-'", "'/'", "'%'", "'||'", "'::'", "'->'", "'->>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SELECT", "FROM", "WHERE", "AND", "OR", "NOT", "INSERT", "INTO", 
			"VALUES", "UPDATE", "SET", "DELETE", "CREATE", "TABLE", "DROP", "ALTER", 
			"ADD", "COLUMN", "INDEX", "PRIMARY", "KEY", "FOREIGN", "REFERENCES", 
			"UNIQUE", "NULL_", "DEFAULT", "IF_", "EXISTS", "DATABASE", "SCHEMA", 
			"ORDER", "BY", "GROUP", "HAVING", "LIMIT", "OFFSET", "JOIN", "INNER", 
			"LEFT", "RIGHT", "OUTER", "CROSS", "FULL", "ON", "AS", "IN", "BETWEEN", 
			"LIKE", "ILIKE", "IS", "DISTINCT", "UNION", "INTERSECT", "EXCEPT", "ALL", 
			"CASE", "WHEN", "THEN", "ELSE", "END", "ASC", "DESC", "RETURNING", "WITH", 
			"RECURSIVE", "CASCADE", "RESTRICT", "CONSTRAINT", "CHECK", "NULLS", "FIRST", 
			"LAST", "COUNT", "SUM", "AVG", "MIN", "MAX", "CAST", "COALESCE", "TRUE", 
			"FALSE", "SERIAL", "BIGSERIAL", "SMALLSERIAL", "RETURNING_KW", "CONCURRENTLY", 
			"USING", "TABLESPACE", "INHERITS", "PARTITION", "RANGE", "LIST", "HASH", 
			"TEMP", "TEMPORARY", "UNLOGGED", "ONLY", "SMALLINT", "INT", "INTEGER", 
			"BIGINT", "REAL", "FLOAT", "DOUBLE", "PRECISION", "DECIMAL", "NUMERIC", 
			"CHAR", "VARCHAR", "TEXT", "BOOLEAN", "BOOL", "DATE", "TIMESTAMP", "TIME", 
			"INTERVAL", "JSON", "JSONB", "UUID", "ARRAY", "BYTEA", "INET", "CIDR", 
			"MACADDR", "MONEY", "XML", "STAR", "COMMA", "DOT", "LPAREN", "RPAREN", 
			"LBRACKET", "RBRACKET", "SEMICOLON", "EQ", "NEQ", "LT", "GT", "LTE", 
			"GTE", "PLUS", "MINUS", "DIVIDE", "MOD", "CONCAT", "CAST_OP", "ARROW", 
			"DOUBLE_ARROW", "INTEGER_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", 
			"DOLLAR_STRING", "DOUBLE_QUOTED_ID", "IDENTIFIER", "WS", "LINE_COMMENT", 
			"BLOCK_COMMENT", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PostgreSqlParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PostgreSqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public SqlStatementsContext sqlStatements() {
			return getRuleContext(SqlStatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PostgreSqlParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			sqlStatements();
			setState(123);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SqlStatementsContext extends ParserRuleContext {
		public List<SqlStatementContext> sqlStatement() {
			return getRuleContexts(SqlStatementContext.class);
		}
		public SqlStatementContext sqlStatement(int i) {
			return getRuleContext(SqlStatementContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(PostgreSqlParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(PostgreSqlParser.SEMICOLON, i);
		}
		public SqlStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSqlStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSqlStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSqlStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementsContext sqlStatements() throws RecognitionException {
		SqlStatementsContext _localctx = new SqlStatementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sqlStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 1)) & ~0x3f) == 0 && ((1L << (_la - 1)) & -9223372036854719935L) != 0)) {
				{
				{
				setState(125);
				sqlStatement();
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(126);
					match(SEMICOLON);
					}
				}

				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SqlStatementContext extends ParserRuleContext {
		public DdlStatementContext ddlStatement() {
			return getRuleContext(DdlStatementContext.class,0);
		}
		public DmlStatementContext dmlStatement() {
			return getRuleContext(DmlStatementContext.class,0);
		}
		public SqlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSqlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSqlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSqlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementContext sqlStatement() throws RecognitionException {
		SqlStatementContext _localctx = new SqlStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlStatement);
		try {
			setState(136);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case ALTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				ddlStatement();
				}
				break;
			case SELECT:
			case INSERT:
			case UPDATE:
			case DELETE:
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				dmlStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DdlStatementContext extends ParserRuleContext {
		public CreateDatabaseContext createDatabase() {
			return getRuleContext(CreateDatabaseContext.class,0);
		}
		public CreateSchemaContext createSchema() {
			return getRuleContext(CreateSchemaContext.class,0);
		}
		public CreateTableContext createTable() {
			return getRuleContext(CreateTableContext.class,0);
		}
		public DropDatabaseContext dropDatabase() {
			return getRuleContext(DropDatabaseContext.class,0);
		}
		public DropTableContext dropTable() {
			return getRuleContext(DropTableContext.class,0);
		}
		public AlterTableContext alterTable() {
			return getRuleContext(AlterTableContext.class,0);
		}
		public CreateIndexContext createIndex() {
			return getRuleContext(CreateIndexContext.class,0);
		}
		public DropIndexContext dropIndex() {
			return getRuleContext(DropIndexContext.class,0);
		}
		public DdlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ddlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDdlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDdlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDdlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DdlStatementContext ddlStatement() throws RecognitionException {
		DdlStatementContext _localctx = new DdlStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ddlStatement);
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				createDatabase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				createSchema();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				createTable();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				dropDatabase();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(142);
				dropTable();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(143);
				alterTable();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(144);
				createIndex();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(145);
				dropIndex();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateDatabaseContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSqlParser.CREATE, 0); }
		public TerminalNode DATABASE() { return getToken(PostgreSqlParser.DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public CreateDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDatabase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCreateDatabase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCreateDatabase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCreateDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateDatabaseContext createDatabase() throws RecognitionException {
		CreateDatabaseContext _localctx = new CreateDatabaseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_createDatabase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(CREATE);
			setState(149);
			match(DATABASE);
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(150);
				match(IF_);
				setState(151);
				match(NOT);
				setState(152);
				match(EXISTS);
				}
			}

			setState(155);
			databaseName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateSchemaContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSqlParser.CREATE, 0); }
		public TerminalNode SCHEMA() { return getToken(PostgreSqlParser.SCHEMA, 0); }
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public CreateSchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createSchema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCreateSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCreateSchema(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCreateSchema(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateSchemaContext createSchema() throws RecognitionException {
		CreateSchemaContext _localctx = new CreateSchemaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_createSchema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(CREATE);
			setState(158);
			match(SCHEMA);
			setState(162);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(159);
				match(IF_);
				setState(160);
				match(NOT);
				setState(161);
				match(EXISTS);
				}
			}

			setState(164);
			schemaName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropDatabaseContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(PostgreSqlParser.DROP, 0); }
		public TerminalNode DATABASE() { return getToken(PostgreSqlParser.DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public DropDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDatabase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDropDatabase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDropDatabase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDropDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDatabaseContext dropDatabase() throws RecognitionException {
		DropDatabaseContext _localctx = new DropDatabaseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dropDatabase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			match(DROP);
			setState(167);
			match(DATABASE);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(168);
				match(IF_);
				setState(169);
				match(EXISTS);
				}
			}

			setState(172);
			databaseName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateTableContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSqlParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(PostgreSqlParser.TABLE, 0); }
		public List<TableNameContext> tableName() {
			return getRuleContexts(TableNameContext.class);
		}
		public TableNameContext tableName(int i) {
			return getRuleContext(TableNameContext.class,i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSqlParser.LPAREN, i);
		}
		public List<TableElementContext> tableElement() {
			return getRuleContexts(TableElementContext.class);
		}
		public TableElementContext tableElement(int i) {
			return getRuleContext(TableElementContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSqlParser.RPAREN, i);
		}
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public TerminalNode INHERITS() { return getToken(PostgreSqlParser.INHERITS, 0); }
		public TerminalNode PARTITION() { return getToken(PostgreSqlParser.PARTITION, 0); }
		public TerminalNode BY() { return getToken(PostgreSqlParser.BY, 0); }
		public PartitionStrategyContext partitionStrategy() {
			return getRuleContext(PartitionStrategyContext.class,0);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public TerminalNode TEMP() { return getToken(PostgreSqlParser.TEMP, 0); }
		public TerminalNode TEMPORARY() { return getToken(PostgreSqlParser.TEMPORARY, 0); }
		public TerminalNode UNLOGGED() { return getToken(PostgreSqlParser.UNLOGGED, 0); }
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCreateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCreateTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCreateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_createTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(CREATE);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & 7L) != 0)) {
				{
				setState(175);
				_la = _input.LA(1);
				if ( !(((((_la - 94)) & ~0x3f) == 0 && ((1L << (_la - 94)) & 7L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(178);
			match(TABLE);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(179);
				match(IF_);
				setState(180);
				match(NOT);
				setState(181);
				match(EXISTS);
				}
			}

			setState(184);
			tableName();
			setState(185);
			match(LPAREN);
			setState(186);
			tableElement();
			setState(191);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(187);
				match(COMMA);
				setState(188);
				tableElement();
				}
				}
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(194);
			match(RPAREN);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(195);
				match(INHERITS);
				setState(196);
				match(LPAREN);
				setState(197);
				tableName();
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(198);
					match(COMMA);
					setState(199);
					tableName();
					}
					}
					setState(204);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(205);
				match(RPAREN);
				}
			}

			setState(223);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PARTITION) {
				{
				setState(209);
				match(PARTITION);
				setState(210);
				match(BY);
				setState(211);
				partitionStrategy();
				setState(212);
				match(LPAREN);
				setState(213);
				columnName();
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(214);
					match(COMMA);
					setState(215);
					columnName();
					}
					}
					setState(220);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221);
				match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableElementContext extends ParserRuleContext {
		public ColumnDefinitionContext columnDefinition() {
			return getRuleContext(ColumnDefinitionContext.class,0);
		}
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public TableElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterTableElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitTableElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitTableElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableElementContext tableElement() throws RecognitionException {
		TableElementContext _localctx = new TableElementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tableElement);
		try {
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225);
				columnDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(226);
				tableConstraint();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropTableContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(PostgreSqlParser.DROP, 0); }
		public TerminalNode TABLE() { return getToken(PostgreSqlParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public TerminalNode CASCADE() { return getToken(PostgreSqlParser.CASCADE, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSqlParser.RESTRICT, 0); }
		public DropTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDropTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDropTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDropTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableContext dropTable() throws RecognitionException {
		DropTableContext _localctx = new DropTableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_dropTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(DROP);
			setState(230);
			match(TABLE);
			setState(233);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(231);
				match(IF_);
				setState(232);
				match(EXISTS);
				}
			}

			setState(235);
			tableName();
			setState(237);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CASCADE || _la==RESTRICT) {
				{
				setState(236);
				_la = _input.LA(1);
				if ( !(_la==CASCADE || _la==RESTRICT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterTableContext extends ParserRuleContext {
		public TerminalNode ALTER() { return getToken(PostgreSqlParser.ALTER, 0); }
		public TerminalNode TABLE() { return getToken(PostgreSqlParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<AlterTableActionContext> alterTableAction() {
			return getRuleContexts(AlterTableActionContext.class);
		}
		public AlterTableActionContext alterTableAction(int i) {
			return getRuleContext(AlterTableActionContext.class,i);
		}
		public TerminalNode ONLY() { return getToken(PostgreSqlParser.ONLY, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public AlterTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterAlterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitAlterTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitAlterTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableContext alterTable() throws RecognitionException {
		AlterTableContext _localctx = new AlterTableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_alterTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(ALTER);
			setState(240);
			match(TABLE);
			setState(242);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(241);
				match(ONLY);
				}
				break;
			}
			setState(244);
			tableName();
			setState(245);
			alterTableAction();
			setState(250);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(246);
				match(COMMA);
				setState(247);
				alterTableAction();
				}
				}
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AlterTableActionContext extends ParserRuleContext {
		public TerminalNode ADD() { return getToken(PostgreSqlParser.ADD, 0); }
		public ColumnDefinitionContext columnDefinition() {
			return getRuleContext(ColumnDefinitionContext.class,0);
		}
		public TerminalNode COLUMN() { return getToken(PostgreSqlParser.COLUMN, 0); }
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public TerminalNode DROP() { return getToken(PostgreSqlParser.DROP, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode CASCADE() { return getToken(PostgreSqlParser.CASCADE, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSqlParser.RESTRICT, 0); }
		public TerminalNode ALTER() { return getToken(PostgreSqlParser.ALTER, 0); }
		public TerminalNode SET() { return getToken(PostgreSqlParser.SET, 0); }
		public TerminalNode DEFAULT() { return getToken(PostgreSqlParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TableConstraintContext tableConstraint() {
			return getRuleContext(TableConstraintContext.class,0);
		}
		public TerminalNode CONSTRAINT() { return getToken(PostgreSqlParser.CONSTRAINT, 0); }
		public ConstraintNameContext constraintName() {
			return getRuleContext(ConstraintNameContext.class,0);
		}
		public AlterTableActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTableAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterAlterTableAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitAlterTableAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitAlterTableAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableActionContext alterTableAction() throws RecognitionException {
		AlterTableActionContext _localctx = new AlterTableActionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_alterTableAction);
		int _la;
		try {
			setState(300);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				match(ADD);
				setState(255);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(254);
					match(COLUMN);
					}
					break;
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF_) {
					{
					setState(257);
					match(IF_);
					setState(258);
					match(NOT);
					setState(259);
					match(EXISTS);
					}
				}

				setState(262);
				columnDefinition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(263);
				match(DROP);
				setState(265);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(264);
					match(COLUMN);
					}
					break;
				}
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IF_) {
					{
					setState(267);
					match(IF_);
					setState(268);
					match(EXISTS);
					}
				}

				setState(271);
				columnName();
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CASCADE || _la==RESTRICT) {
					{
					setState(272);
					_la = _input.LA(1);
					if ( !(_la==CASCADE || _la==RESTRICT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				match(ALTER);
				setState(277);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
				case 1:
					{
					setState(276);
					match(COLUMN);
					}
					break;
				}
				setState(279);
				columnName();
				setState(280);
				match(SET);
				setState(281);
				match(DEFAULT);
				setState(282);
				expression(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(284);
				match(ALTER);
				setState(286);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
				case 1:
					{
					setState(285);
					match(COLUMN);
					}
					break;
				}
				setState(288);
				columnName();
				setState(289);
				match(DROP);
				setState(290);
				match(DEFAULT);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(292);
				match(ADD);
				setState(293);
				tableConstraint();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(294);
				match(DROP);
				setState(295);
				match(CONSTRAINT);
				setState(296);
				constraintName();
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CASCADE || _la==RESTRICT) {
					{
					setState(297);
					_la = _input.LA(1);
					if ( !(_la==CASCADE || _la==RESTRICT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CreateIndexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(PostgreSqlParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(PostgreSqlParser.INDEX, 0); }
		public TerminalNode ON() { return getToken(PostgreSqlParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public IndexColumnListContext indexColumnList() {
			return getRuleContext(IndexColumnListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public TerminalNode UNIQUE() { return getToken(PostgreSqlParser.UNIQUE, 0); }
		public TerminalNode CONCURRENTLY() { return getToken(PostgreSqlParser.CONCURRENTLY, 0); }
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode USING() { return getToken(PostgreSqlParser.USING, 0); }
		public IndexMethodContext indexMethod() {
			return getRuleContext(IndexMethodContext.class,0);
		}
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCreateIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCreateIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCreateIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_createIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(CREATE);
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNIQUE) {
				{
				setState(303);
				match(UNIQUE);
				}
			}

			setState(306);
			match(INDEX);
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(307);
				match(CONCURRENTLY);
				}
				break;
			}
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(310);
				match(IF_);
				setState(311);
				match(NOT);
				setState(312);
				match(EXISTS);
				}
			}

			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2883584L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 4571153630352113871L) != 0) || ((((_la - 153)) & ~0x3f) == 0 && ((1L << (_la - 153)) & 35L) != 0)) {
				{
				setState(315);
				indexName();
				}
			}

			setState(318);
			match(ON);
			setState(319);
			tableName();
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(320);
				match(USING);
				setState(321);
				indexMethod();
				}
			}

			setState(324);
			match(LPAREN);
			setState(325);
			indexColumnList();
			setState(326);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DropIndexContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(PostgreSqlParser.DROP, 0); }
		public TerminalNode INDEX() { return getToken(PostgreSqlParser.INDEX, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode CONCURRENTLY() { return getToken(PostgreSqlParser.CONCURRENTLY, 0); }
		public TerminalNode IF_() { return getToken(PostgreSqlParser.IF_, 0); }
		public TerminalNode EXISTS() { return getToken(PostgreSqlParser.EXISTS, 0); }
		public TerminalNode CASCADE() { return getToken(PostgreSqlParser.CASCADE, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSqlParser.RESTRICT, 0); }
		public DropIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDropIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDropIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDropIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropIndexContext dropIndex() throws RecognitionException {
		DropIndexContext _localctx = new DropIndexContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dropIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328);
			match(DROP);
			setState(329);
			match(INDEX);
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(330);
				match(CONCURRENTLY);
				}
				break;
			}
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(333);
				match(IF_);
				setState(334);
				match(EXISTS);
				}
			}

			setState(337);
			indexName();
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CASCADE || _la==RESTRICT) {
				{
				setState(338);
				_la = _input.LA(1);
				if ( !(_la==CASCADE || _la==RESTRICT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DmlStatementContext extends ParserRuleContext {
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public WithClauseContext withClause() {
			return getRuleContext(WithClauseContext.class,0);
		}
		public InsertStatementContext insertStatement() {
			return getRuleContext(InsertStatementContext.class,0);
		}
		public UpdateStatementContext updateStatement() {
			return getRuleContext(UpdateStatementContext.class,0);
		}
		public DeleteStatementContext deleteStatement() {
			return getRuleContext(DeleteStatementContext.class,0);
		}
		public DmlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dmlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDmlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDmlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDmlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DmlStatementContext dmlStatement() throws RecognitionException {
		DmlStatementContext _localctx = new DmlStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_dmlStatement);
		int _la;
		try {
			setState(348);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
			case WITH:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WITH) {
					{
					setState(341);
					withClause();
					}
				}

				setState(344);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(345);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(346);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(347);
				deleteStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WithClauseContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(PostgreSqlParser.WITH, 0); }
		public List<CteDefinitionContext> cteDefinition() {
			return getRuleContexts(CteDefinitionContext.class);
		}
		public CteDefinitionContext cteDefinition(int i) {
			return getRuleContext(CteDefinitionContext.class,i);
		}
		public TerminalNode RECURSIVE() { return getToken(PostgreSqlParser.RECURSIVE, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public WithClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_withClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterWithClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitWithClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitWithClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithClauseContext withClause() throws RecognitionException {
		WithClauseContext _localctx = new WithClauseContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_withClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(WITH);
			setState(352);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(351);
				match(RECURSIVE);
				}
				break;
			}
			setState(354);
			cteDefinition();
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(355);
				match(COMMA);
				setState(356);
				cteDefinition();
				}
				}
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CteDefinitionContext extends ParserRuleContext {
		public CteNameContext cteName() {
			return getRuleContext(CteNameContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSqlParser.LPAREN, i);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSqlParser.RPAREN, i);
		}
		public ColumnNameListContext columnNameList() {
			return getRuleContext(ColumnNameListContext.class,0);
		}
		public CteDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cteDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCteDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCteDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCteDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CteDefinitionContext cteDefinition() throws RecognitionException {
		CteDefinitionContext _localctx = new CteDefinitionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_cteDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			cteName();
			setState(367);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(363);
				match(LPAREN);
				setState(364);
				columnNameList();
				setState(365);
				match(RPAREN);
				}
			}

			setState(369);
			match(AS);
			setState(370);
			match(LPAREN);
			setState(371);
			selectStatement();
			setState(372);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(PostgreSqlParser.SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(PostgreSqlParser.DISTINCT, 0); }
		public TerminalNode FROM() { return getToken(PostgreSqlParser.FROM, 0); }
		public List<TableSourceContext> tableSource() {
			return getRuleContexts(TableSourceContext.class);
		}
		public TableSourceContext tableSource(int i) {
			return getRuleContext(TableSourceContext.class,i);
		}
		public List<JoinClauseContext> joinClause() {
			return getRuleContexts(JoinClauseContext.class);
		}
		public JoinClauseContext joinClause(int i) {
			return getRuleContext(JoinClauseContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public GroupByClauseContext groupByClause() {
			return getRuleContext(GroupByClauseContext.class,0);
		}
		public HavingClauseContext havingClause() {
			return getRuleContext(HavingClauseContext.class,0);
		}
		public OrderByClauseContext orderByClause() {
			return getRuleContext(OrderByClauseContext.class,0);
		}
		public LimitClauseContext limitClause() {
			return getRuleContext(LimitClauseContext.class,0);
		}
		public OffsetClauseContext offsetClause() {
			return getRuleContext(OffsetClauseContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode UNION() { return getToken(PostgreSqlParser.UNION, 0); }
		public TerminalNode INTERSECT() { return getToken(PostgreSqlParser.INTERSECT, 0); }
		public TerminalNode EXCEPT() { return getToken(PostgreSqlParser.EXCEPT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public TerminalNode ALL() { return getToken(PostgreSqlParser.ALL, 0); }
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(SELECT);
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(375);
				match(DISTINCT);
				}
			}

			setState(378);
			selectElements();
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(379);
				match(FROM);
				setState(380);
				tableSource();
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(381);
					match(COMMA);
					setState(382);
					tableSource();
					}
					}
					setState(387);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(393);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 15255723835392L) != 0)) {
				{
				{
				setState(390);
				joinClause();
				}
				}
				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(397);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(396);
				whereClause();
				}
			}

			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(399);
				groupByClause();
				}
			}

			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(402);
				havingClause();
				}
			}

			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(405);
				orderByClause();
				}
			}

			setState(409);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(408);
				limitClause();
				}
			}

			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==OFFSET) {
				{
				setState(411);
				offsetClause();
				}
			}

			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 31525197391593472L) != 0)) {
				{
				setState(414);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 31525197391593472L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(415);
					match(ALL);
					}
				}

				setState(418);
				selectStatement();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InsertStatementContext extends ParserRuleContext {
		public TerminalNode INSERT() { return getToken(PostgreSqlParser.INSERT, 0); }
		public TerminalNode INTO() { return getToken(PostgreSqlParser.INTO, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(PostgreSqlParser.VALUES, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSqlParser.LPAREN, i);
		}
		public List<ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSqlParser.RPAREN, i);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public ColumnNameListContext columnNameList() {
			return getRuleContext(ColumnNameListContext.class,0);
		}
		public TerminalNode RETURNING() { return getToken(PostgreSqlParser.RETURNING, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterInsertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitInsertStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitInsertStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(INSERT);
			setState(422);
			match(INTO);
			setState(423);
			tableName();
			setState(428);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(424);
				match(LPAREN);
				setState(425);
				columnNameList();
				setState(426);
				match(RPAREN);
				}
			}

			setState(445);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUES:
				{
				setState(430);
				match(VALUES);
				setState(431);
				match(LPAREN);
				setState(432);
				expressionList();
				setState(433);
				match(RPAREN);
				setState(441);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(434);
					match(COMMA);
					setState(435);
					match(LPAREN);
					setState(436);
					expressionList();
					setState(437);
					match(RPAREN);
					}
					}
					setState(443);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case SELECT:
				{
				setState(444);
				selectStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNING) {
				{
				setState(447);
				match(RETURNING);
				setState(448);
				selectElements();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateStatementContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(PostgreSqlParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(PostgreSqlParser.SET, 0); }
		public List<UpdateAssignmentContext> updateAssignment() {
			return getRuleContexts(UpdateAssignmentContext.class);
		}
		public UpdateAssignmentContext updateAssignment(int i) {
			return getRuleContext(UpdateAssignmentContext.class,i);
		}
		public TerminalNode ONLY() { return getToken(PostgreSqlParser.ONLY, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public TerminalNode FROM() { return getToken(PostgreSqlParser.FROM, 0); }
		public List<TableSourceContext> tableSource() {
			return getRuleContexts(TableSourceContext.class);
		}
		public TableSourceContext tableSource(int i) {
			return getRuleContext(TableSourceContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode RETURNING() { return getToken(PostgreSqlParser.RETURNING, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public UpdateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterUpdateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitUpdateStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitUpdateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateStatementContext updateStatement() throws RecognitionException {
		UpdateStatementContext _localctx = new UpdateStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(451);
			match(UPDATE);
			setState(453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(452);
				match(ONLY);
				}
				break;
			}
			setState(455);
			tableName();
			setState(460);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35184374972416L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 4571153630352113871L) != 0) || ((((_la - 151)) & ~0x3f) == 0 && ((1L << (_la - 151)) & 141L) != 0)) {
				{
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(456);
					match(AS);
					}
				}

				setState(459);
				alias();
				}
			}

			setState(462);
			match(SET);
			setState(463);
			updateAssignment();
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(464);
				match(COMMA);
				setState(465);
				updateAssignment();
				}
				}
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(471);
				match(FROM);
				setState(472);
				tableSource();
				setState(477);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(473);
					match(COMMA);
					setState(474);
					tableSource();
					}
					}
					setState(479);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(483);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(482);
				whereClause();
				}
			}

			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNING) {
				{
				setState(485);
				match(RETURNING);
				setState(486);
				selectElements();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeleteStatementContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(PostgreSqlParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(PostgreSqlParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode ONLY() { return getToken(PostgreSqlParser.ONLY, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public TerminalNode USING() { return getToken(PostgreSqlParser.USING, 0); }
		public List<TableSourceContext> tableSource() {
			return getRuleContexts(TableSourceContext.class);
		}
		public TableSourceContext tableSource(int i) {
			return getRuleContext(TableSourceContext.class,i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public TerminalNode RETURNING() { return getToken(PostgreSqlParser.RETURNING, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDeleteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDeleteStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDeleteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(489);
			match(DELETE);
			setState(490);
			match(FROM);
			setState(492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(491);
				match(ONLY);
				}
				break;
			}
			setState(494);
			tableName();
			setState(499);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				setState(496);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(495);
					match(AS);
					}
				}

				setState(498);
				alias();
				}
				break;
			}
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==USING) {
				{
				setState(501);
				match(USING);
				setState(502);
				tableSource();
				setState(507);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(503);
					match(COMMA);
					setState(504);
					tableSource();
					}
					}
					setState(509);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(512);
				whereClause();
				}
			}

			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==RETURNING) {
				{
				setState(515);
				match(RETURNING);
				setState(516);
				selectElements();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementsContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(PostgreSqlParser.STAR, 0); }
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selectElements);
		int _la;
		try {
			setState(528);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(519);
				match(STAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(520);
				selectElement();
				setState(525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(521);
					match(COMMA);
					setState(522);
					selectElement();
					}
					}
					setState(527);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SelectElementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSelectElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementContext selectElement() throws RecognitionException {
		SelectElementContext _localctx = new SelectElementContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_selectElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			expression(0);
			setState(535);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 35184374972416L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 4571153630352113871L) != 0) || ((((_la - 151)) & ~0x3f) == 0 && ((1L << (_la - 151)) & 141L) != 0)) {
				{
				setState(532);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(531);
					match(AS);
					}
				}

				setState(534);
				alias();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableSourceContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public TableSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterTableSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitTableSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitTableSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceContext tableSource() throws RecognitionException {
		TableSourceContext _localctx = new TableSourceContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tableSource);
		int _la;
		try {
			setState(553);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLUMN:
			case INDEX:
			case KEY:
			case RECURSIVE:
			case CASCADE:
			case RESTRICT:
			case CONSTRAINT:
			case FIRST:
			case LAST:
			case CONCURRENTLY:
			case USING:
			case TABLESPACE:
			case PARTITION:
			case RANGE:
			case LIST:
			case HASH:
			case TEMP:
			case TEMPORARY:
			case UNLOGGED:
			case ONLY:
			case JSON:
			case JSONB:
			case UUID:
			case BYTEA:
			case INET:
			case CIDR:
			case MACADDR:
			case MONEY:
			case XML:
			case DOUBLE_QUOTED_ID:
			case IDENTIFIER:
			case COMMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(537);
				tableName();
				setState(542);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(539);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(538);
						match(AS);
						}
					}

					setState(541);
					alias();
					}
					break;
				}
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(544);
				match(LPAREN);
				setState(545);
				selectStatement();
				setState(546);
				match(RPAREN);
				setState(551);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
				case 1:
					{
					setState(548);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(547);
						match(AS);
						}
					}

					setState(550);
					alias();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JoinClauseContext extends ParserRuleContext {
		public TerminalNode JOIN() { return getToken(PostgreSqlParser.JOIN, 0); }
		public TableSourceContext tableSource() {
			return getRuleContext(TableSourceContext.class,0);
		}
		public JoinTypeContext joinType() {
			return getRuleContext(JoinTypeContext.class,0);
		}
		public TerminalNode ON() { return getToken(PostgreSqlParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode USING() { return getToken(PostgreSqlParser.USING, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public ColumnNameListContext columnNameList() {
			return getRuleContext(ColumnNameListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public JoinClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterJoinClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitJoinClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitJoinClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinClauseContext joinClause() throws RecognitionException {
		JoinClauseContext _localctx = new JoinClauseContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_joinClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 15118284881920L) != 0)) {
				{
				setState(555);
				joinType();
				}
			}

			setState(558);
			match(JOIN);
			setState(559);
			tableSource();
			setState(567);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ON:
				{
				setState(560);
				match(ON);
				setState(561);
				expression(0);
				}
				break;
			case USING:
				{
				setState(562);
				match(USING);
				setState(563);
				match(LPAREN);
				setState(564);
				columnNameList();
				setState(565);
				match(RPAREN);
				}
				break;
			case EOF:
			case SELECT:
			case WHERE:
			case INSERT:
			case UPDATE:
			case DELETE:
			case CREATE:
			case DROP:
			case ALTER:
			case ORDER:
			case GROUP:
			case HAVING:
			case LIMIT:
			case OFFSET:
			case JOIN:
			case INNER:
			case LEFT:
			case RIGHT:
			case CROSS:
			case FULL:
			case UNION:
			case INTERSECT:
			case EXCEPT:
			case RETURNING:
			case WITH:
			case RPAREN:
			case SEMICOLON:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class JoinTypeContext extends ParserRuleContext {
		public TerminalNode INNER() { return getToken(PostgreSqlParser.INNER, 0); }
		public TerminalNode LEFT() { return getToken(PostgreSqlParser.LEFT, 0); }
		public TerminalNode OUTER() { return getToken(PostgreSqlParser.OUTER, 0); }
		public TerminalNode RIGHT() { return getToken(PostgreSqlParser.RIGHT, 0); }
		public TerminalNode FULL() { return getToken(PostgreSqlParser.FULL, 0); }
		public TerminalNode CROSS() { return getToken(PostgreSqlParser.CROSS, 0); }
		public JoinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterJoinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitJoinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitJoinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinTypeContext joinType() throws RecognitionException {
		JoinTypeContext _localctx = new JoinTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_joinType);
		int _la;
		try {
			setState(583);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INNER:
				enterOuterAlt(_localctx, 1);
				{
				setState(569);
				match(INNER);
				}
				break;
			case LEFT:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				match(LEFT);
				setState(572);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(571);
					match(OUTER);
					}
				}

				}
				break;
			case RIGHT:
				enterOuterAlt(_localctx, 3);
				{
				setState(574);
				match(RIGHT);
				setState(576);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(575);
					match(OUTER);
					}
				}

				}
				break;
			case FULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(578);
				match(FULL);
				setState(580);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(579);
					match(OUTER);
					}
				}

				}
				break;
			case CROSS:
				enterOuterAlt(_localctx, 5);
				{
				setState(582);
				match(CROSS);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhereClauseContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(PostgreSqlParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585);
			match(WHERE);
			setState(586);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GroupByClauseContext extends ParserRuleContext {
		public TerminalNode GROUP() { return getToken(PostgreSqlParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(PostgreSqlParser.BY, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public GroupByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterGroupByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitGroupByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitGroupByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByClauseContext groupByClause() throws RecognitionException {
		GroupByClauseContext _localctx = new GroupByClauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_groupByClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588);
			match(GROUP);
			setState(589);
			match(BY);
			setState(590);
			expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HavingClauseContext extends ParserRuleContext {
		public TerminalNode HAVING() { return getToken(PostgreSqlParser.HAVING, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public HavingClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_havingClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterHavingClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitHavingClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitHavingClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HavingClauseContext havingClause() throws RecognitionException {
		HavingClauseContext _localctx = new HavingClauseContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_havingClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592);
			match(HAVING);
			setState(593);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByClauseContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(PostgreSqlParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(PostgreSqlParser.BY, 0); }
		public List<OrderByElementContext> orderByElement() {
			return getRuleContexts(OrderByElementContext.class);
		}
		public OrderByElementContext orderByElement(int i) {
			return getRuleContext(OrderByElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public OrderByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterOrderByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitOrderByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitOrderByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByClauseContext orderByClause() throws RecognitionException {
		OrderByClauseContext _localctx = new OrderByClauseContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_orderByClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(595);
			match(ORDER);
			setState(596);
			match(BY);
			setState(597);
			orderByElement();
			setState(602);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(598);
				match(COMMA);
				setState(599);
				orderByElement();
				}
				}
				setState(604);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OrderByElementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode NULLS() { return getToken(PostgreSqlParser.NULLS, 0); }
		public TerminalNode ASC() { return getToken(PostgreSqlParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(PostgreSqlParser.DESC, 0); }
		public TerminalNode FIRST() { return getToken(PostgreSqlParser.FIRST, 0); }
		public TerminalNode LAST() { return getToken(PostgreSqlParser.LAST, 0); }
		public OrderByElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterOrderByElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitOrderByElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitOrderByElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderByElementContext orderByElement() throws RecognitionException {
		OrderByElementContext _localctx = new OrderByElementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_orderByElement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(605);
			expression(0);
			setState(607);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(606);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(611);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NULLS) {
				{
				setState(609);
				match(NULLS);
				setState(610);
				_la = _input.LA(1);
				if ( !(_la==FIRST || _la==LAST) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LimitClauseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(PostgreSqlParser.LIMIT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ALL() { return getToken(PostgreSqlParser.ALL, 0); }
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitLimitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitLimitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_limitClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			match(LIMIT);
			setState(616);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case COLUMN:
			case INDEX:
			case KEY:
			case NULL_:
			case CASE:
			case RECURSIVE:
			case CASCADE:
			case RESTRICT:
			case CONSTRAINT:
			case FIRST:
			case LAST:
			case COUNT:
			case SUM:
			case AVG:
			case MIN:
			case MAX:
			case CAST:
			case COALESCE:
			case TRUE:
			case FALSE:
			case CONCURRENTLY:
			case USING:
			case TABLESPACE:
			case PARTITION:
			case RANGE:
			case LIST:
			case HASH:
			case TEMP:
			case TEMPORARY:
			case UNLOGGED:
			case ONLY:
			case JSON:
			case JSONB:
			case UUID:
			case BYTEA:
			case INET:
			case CIDR:
			case MACADDR:
			case MONEY:
			case XML:
			case STAR:
			case LPAREN:
			case INTEGER_LITERAL:
			case DECIMAL_LITERAL:
			case STRING_LITERAL:
			case DOLLAR_STRING:
			case DOUBLE_QUOTED_ID:
			case IDENTIFIER:
			case COMMENT:
				{
				setState(614);
				expression(0);
				}
				break;
			case ALL:
				{
				setState(615);
				match(ALL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OffsetClauseContext extends ParserRuleContext {
		public TerminalNode OFFSET() { return getToken(PostgreSqlParser.OFFSET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OffsetClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_offsetClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterOffsetClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitOffsetClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitOffsetClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OffsetClauseContext offsetClause() throws RecognitionException {
		OffsetClauseContext _localctx = new OffsetClauseContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_offsetClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			match(OFFSET);
			setState(619);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulDivModExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode STAR() { return getToken(PostgreSqlParser.STAR, 0); }
		public TerminalNode DIVIDE() { return getToken(PostgreSqlParser.DIVIDE, 0); }
		public TerminalNode MOD() { return getToken(PostgreSqlParser.MOD, 0); }
		public MulDivModExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterMulDivModExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitMulDivModExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitMulDivModExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitNotExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ComparisonExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public ParenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitParenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitParenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PgCastExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CAST_OP() { return getToken(PostgreSqlParser.CAST_OP, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public PgCastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterPgCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitPgCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitPgCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IlikeExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ILIKE() { return getToken(PostgreSqlParser.ILIKE, 0); }
		public IlikeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIlikeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIlikeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIlikeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ConcatExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CONCAT() { return getToken(PostgreSqlParser.CONCAT, 0); }
		public ConcatExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterConcatExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitConcatExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitConcatExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsNotNullExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PostgreSqlParser.IS, 0); }
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public TerminalNode NULL_() { return getToken(PostgreSqlParser.NULL_, 0); }
		public IsNotNullExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIsNotNullExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIsNotNullExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIsNotNullExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class OrExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode OR() { return getToken(PostgreSqlParser.OR, 0); }
		public OrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitOrExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ExpressionContext {
		public TerminalNode CAST() { return getToken(PostgreSqlParser.CAST, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(PostgreSqlParser.AS, 0); }
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCastExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCastExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCastExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JsonTextAccessExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DOUBLE_ARROW() { return getToken(PostgreSqlParser.DOUBLE_ARROW, 0); }
		public JsonTextAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterJsonTextAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitJsonTextAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitJsonTextAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AndExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(PostgreSqlParser.AND, 0); }
		public AndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitAndExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BetweenExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode BETWEEN() { return getToken(PostgreSqlParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(PostgreSqlParser.AND, 0); }
		public BetweenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterBetweenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitBetweenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitBetweenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IN() { return getToken(PostgreSqlParser.IN, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public InExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitInExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LBRACKET() { return getToken(PostgreSqlParser.LBRACKET, 0); }
		public TerminalNode RBRACKET() { return getToken(PostgreSqlParser.RBRACKET, 0); }
		public ArrayAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterArrayAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitArrayAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitArrayAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddSubExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(PostgreSqlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(PostgreSqlParser.MINUS, 0); }
		public AddSubExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterAddSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitAddSubExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitAddSubExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class JsonAccessExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ARROW() { return getToken(PostgreSqlParser.ARROW, 0); }
		public JsonAccessExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterJsonAccessExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitJsonAccessExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitJsonAccessExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExpressionContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitFunctionCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ColumnRefExpressionContext extends ExpressionContext {
		public ColumnRefContext columnRef() {
			return getRuleContext(ColumnRefContext.class,0);
		}
		public ColumnRefExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnRefExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnRefExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnRefExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LikeExpressionContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LIKE() { return getToken(PostgreSqlParser.LIKE, 0); }
		public LikeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterLikeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitLikeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitLikeExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CaseExprContext extends ExpressionContext {
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public CaseExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCaseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCaseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExpressionContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsNullExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(PostgreSqlParser.IS, 0); }
		public TerminalNode NULL_() { return getToken(PostgreSqlParser.NULL_, 0); }
		public IsNullExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIsNullExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIsNullExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIsNullExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StarExpressionContext extends ExpressionContext {
		public TerminalNode STAR() { return getToken(PostgreSqlParser.STAR, 0); }
		public StarExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterStarExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitStarExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitStarExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 66;
		enterRecursionRule(_localctx, 66, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(622);
				match(LPAREN);
				setState(623);
				expression(0);
				setState(624);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(626);
				match(NOT);
				setState(627);
				expression(23);
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(628);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(629);
				caseExpression();
				}
				break;
			case 5:
				{
				_localctx = new CastExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(630);
				match(CAST);
				setState(631);
				match(LPAREN);
				setState(632);
				expression(0);
				setState(633);
				match(AS);
				setState(634);
				dataType();
				setState(635);
				match(RPAREN);
				}
				break;
			case 6:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(637);
				literal();
				}
				break;
			case 7:
				{
				_localctx = new ColumnRefExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(638);
				columnRef();
				}
				break;
			case 8:
				{
				_localctx = new StarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(639);
				match(STAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(705);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(703);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
					case 1:
						{
						_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(642);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(643);
						match(AND);
						setState(644);
						expression(23);
						}
						break;
					case 2:
						{
						_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(645);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(646);
						match(OR);
						setState(647);
						expression(22);
						}
						break;
					case 3:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(648);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(649);
						comparisonOperator();
						setState(650);
						expression(21);
						}
						break;
					case 4:
						{
						_localctx = new BetweenExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(652);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(653);
						match(BETWEEN);
						setState(654);
						expression(0);
						setState(655);
						match(AND);
						setState(656);
						expression(19);
						}
						break;
					case 5:
						{
						_localctx = new LikeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(658);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(659);
						match(LIKE);
						setState(660);
						expression(18);
						}
						break;
					case 6:
						{
						_localctx = new IlikeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(661);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(662);
						match(ILIKE);
						setState(663);
						expression(17);
						}
						break;
					case 7:
						{
						_localctx = new ConcatExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(664);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(665);
						match(CONCAT);
						setState(666);
						expression(14);
						}
						break;
					case 8:
						{
						_localctx = new JsonAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(667);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(668);
						match(ARROW);
						setState(669);
						expression(12);
						}
						break;
					case 9:
						{
						_localctx = new JsonTextAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(670);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(671);
						match(DOUBLE_ARROW);
						setState(672);
						expression(11);
						}
						break;
					case 10:
						{
						_localctx = new MulDivModExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(673);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(674);
						_la = _input.LA(1);
						if ( !(((((_la - 127)) & ~0x3f) == 0 && ((1L << (_la - 127)) & 196609L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(675);
						expression(7);
						}
						break;
					case 11:
						{
						_localctx = new AddSubExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(676);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(677);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(678);
						expression(6);
						}
						break;
					case 12:
						{
						_localctx = new InExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(679);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(680);
						match(IN);
						setState(681);
						match(LPAREN);
						setState(684);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case NOT:
						case COLUMN:
						case INDEX:
						case KEY:
						case NULL_:
						case CASE:
						case RECURSIVE:
						case CASCADE:
						case RESTRICT:
						case CONSTRAINT:
						case FIRST:
						case LAST:
						case COUNT:
						case SUM:
						case AVG:
						case MIN:
						case MAX:
						case CAST:
						case COALESCE:
						case TRUE:
						case FALSE:
						case CONCURRENTLY:
						case USING:
						case TABLESPACE:
						case PARTITION:
						case RANGE:
						case LIST:
						case HASH:
						case TEMP:
						case TEMPORARY:
						case UNLOGGED:
						case ONLY:
						case JSON:
						case JSONB:
						case UUID:
						case BYTEA:
						case INET:
						case CIDR:
						case MACADDR:
						case MONEY:
						case XML:
						case STAR:
						case LPAREN:
						case INTEGER_LITERAL:
						case DECIMAL_LITERAL:
						case STRING_LITERAL:
						case DOLLAR_STRING:
						case DOUBLE_QUOTED_ID:
						case IDENTIFIER:
						case COMMENT:
							{
							setState(682);
							expressionList();
							}
							break;
						case SELECT:
							{
							setState(683);
							selectStatement();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(686);
						match(RPAREN);
						}
						break;
					case 13:
						{
						_localctx = new IsNullExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(688);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(689);
						match(IS);
						setState(690);
						match(NULL_);
						}
						break;
					case 14:
						{
						_localctx = new IsNotNullExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(691);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(692);
						match(IS);
						setState(693);
						match(NOT);
						setState(694);
						match(NULL_);
						}
						break;
					case 15:
						{
						_localctx = new PgCastExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(695);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(696);
						match(CAST_OP);
						setState(697);
						dataType();
						}
						break;
					case 16:
						{
						_localctx = new ArrayAccessExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(698);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(699);
						match(LBRACKET);
						setState(700);
						expression(0);
						setState(701);
						match(RBRACKET);
						}
						break;
					}
					} 
				}
				setState(707);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(PostgreSqlParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(PostgreSqlParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(PostgreSqlParser.LT, 0); }
		public TerminalNode GT() { return getToken(PostgreSqlParser.GT, 0); }
		public TerminalNode LTE() { return getToken(PostgreSqlParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(PostgreSqlParser.GTE, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			_la = _input.LA(1);
			if ( !(((((_la - 135)) & ~0x3f) == 0 && ((1L << (_la - 135)) & 63L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseExpressionContext extends ParserRuleContext {
		public TerminalNode CASE() { return getToken(PostgreSqlParser.CASE, 0); }
		public TerminalNode END() { return getToken(PostgreSqlParser.END, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> WHEN() { return getTokens(PostgreSqlParser.WHEN); }
		public TerminalNode WHEN(int i) {
			return getToken(PostgreSqlParser.WHEN, i);
		}
		public List<TerminalNode> THEN() { return getTokens(PostgreSqlParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(PostgreSqlParser.THEN, i);
		}
		public TerminalNode ELSE() { return getToken(PostgreSqlParser.ELSE, 0); }
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_caseExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(710);
			match(CASE);
			setState(712);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 72057594074366016L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 9182839648779632591L) != 0) || ((((_la - 130)) & ~0x3f) == 0 && ((1L << (_la - 130)) & 301465601L) != 0)) {
				{
				setState(711);
				expression(0);
				}
			}

			setState(719); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(714);
				match(WHEN);
				setState(715);
				expression(0);
				setState(716);
				match(THEN);
				setState(717);
				expression(0);
				}
				}
				setState(721); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHEN );
			setState(725);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(723);
				match(ELSE);
				setState(724);
				expression(0);
				}
			}

			setState(727);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode STAR() { return getToken(PostgreSqlParser.STAR, 0); }
		public TerminalNode DISTINCT() { return getToken(PostgreSqlParser.DISTINCT, 0); }
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(729);
			functionName();
			setState(730);
			match(LPAREN);
			setState(736);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
			case 1:
				{
				setState(732);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DISTINCT) {
					{
					setState(731);
					match(DISTINCT);
					}
				}

				setState(734);
				expressionList();
				}
				break;
			case 2:
				{
				setState(735);
				match(STAR);
				}
				break;
			}
			setState(738);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionNameContext extends ParserRuleContext {
		public TerminalNode COUNT() { return getToken(PostgreSqlParser.COUNT, 0); }
		public TerminalNode SUM() { return getToken(PostgreSqlParser.SUM, 0); }
		public TerminalNode AVG() { return getToken(PostgreSqlParser.AVG, 0); }
		public TerminalNode MIN() { return getToken(PostgreSqlParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(PostgreSqlParser.MAX, 0); }
		public TerminalNode CAST() { return getToken(PostgreSqlParser.CAST, 0); }
		public TerminalNode COALESCE() { return getToken(PostgreSqlParser.COALESCE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSqlParser.IDENTIFIER, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(740);
			_la = _input.LA(1);
			if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 127L) != 0) || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnDefinitionContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public DataTypeContext dataType() {
			return getRuleContext(DataTypeContext.class,0);
		}
		public List<ColumnConstraintContext> columnConstraint() {
			return getRuleContexts(ColumnConstraintContext.class);
		}
		public ColumnConstraintContext columnConstraint(int i) {
			return getRuleContext(ColumnConstraintContext.class,i);
		}
		public ColumnDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefinitionContext columnDefinition() throws RecognitionException {
		ColumnDefinitionContext _localctx = new ColumnDefinitionContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_columnDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(742);
			columnName();
			setState(743);
			dataType();
			setState(747);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 6)) & ~0x3f) == 0 && ((1L << (_la - 6)) & -4611686018425405439L) != 0)) {
				{
				{
				setState(744);
				columnConstraint();
				}
				}
				setState(749);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DataTypeContext extends ParserRuleContext {
		public TerminalNode SMALLINT() { return getToken(PostgreSqlParser.SMALLINT, 0); }
		public TerminalNode INT() { return getToken(PostgreSqlParser.INT, 0); }
		public TerminalNode INTEGER() { return getToken(PostgreSqlParser.INTEGER, 0); }
		public TerminalNode BIGINT() { return getToken(PostgreSqlParser.BIGINT, 0); }
		public TerminalNode SERIAL() { return getToken(PostgreSqlParser.SERIAL, 0); }
		public TerminalNode BIGSERIAL() { return getToken(PostgreSqlParser.BIGSERIAL, 0); }
		public TerminalNode SMALLSERIAL() { return getToken(PostgreSqlParser.SMALLSERIAL, 0); }
		public TerminalNode REAL() { return getToken(PostgreSqlParser.REAL, 0); }
		public TerminalNode FLOAT() { return getToken(PostgreSqlParser.FLOAT, 0); }
		public TerminalNode DOUBLE() { return getToken(PostgreSqlParser.DOUBLE, 0); }
		public TerminalNode PRECISION() { return getToken(PostgreSqlParser.PRECISION, 0); }
		public TerminalNode DECIMAL() { return getToken(PostgreSqlParser.DECIMAL, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(PostgreSqlParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(PostgreSqlParser.INTEGER_LITERAL, i);
		}
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public TerminalNode COMMA() { return getToken(PostgreSqlParser.COMMA, 0); }
		public TerminalNode NUMERIC() { return getToken(PostgreSqlParser.NUMERIC, 0); }
		public TerminalNode CHAR() { return getToken(PostgreSqlParser.CHAR, 0); }
		public TerminalNode VARCHAR() { return getToken(PostgreSqlParser.VARCHAR, 0); }
		public TerminalNode TEXT() { return getToken(PostgreSqlParser.TEXT, 0); }
		public TerminalNode BOOLEAN() { return getToken(PostgreSqlParser.BOOLEAN, 0); }
		public TerminalNode BOOL() { return getToken(PostgreSqlParser.BOOL, 0); }
		public TerminalNode DATE() { return getToken(PostgreSqlParser.DATE, 0); }
		public TerminalNode TIMESTAMP() { return getToken(PostgreSqlParser.TIMESTAMP, 0); }
		public TerminalNode TIME() { return getToken(PostgreSqlParser.TIME, 0); }
		public TerminalNode INTERVAL() { return getToken(PostgreSqlParser.INTERVAL, 0); }
		public TerminalNode JSON() { return getToken(PostgreSqlParser.JSON, 0); }
		public TerminalNode JSONB() { return getToken(PostgreSqlParser.JSONB, 0); }
		public TerminalNode UUID() { return getToken(PostgreSqlParser.UUID, 0); }
		public TerminalNode BYTEA() { return getToken(PostgreSqlParser.BYTEA, 0); }
		public TerminalNode INET() { return getToken(PostgreSqlParser.INET, 0); }
		public TerminalNode CIDR() { return getToken(PostgreSqlParser.CIDR, 0); }
		public TerminalNode MACADDR() { return getToken(PostgreSqlParser.MACADDR, 0); }
		public TerminalNode MONEY() { return getToken(PostgreSqlParser.MONEY, 0); }
		public TerminalNode XML() { return getToken(PostgreSqlParser.XML, 0); }
		public TerminalNode ARRAY() { return getToken(PostgreSqlParser.ARRAY, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PostgreSqlParser.IDENTIFIER, 0); }
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_dataType);
		int _la;
		try {
			setState(820);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SMALLINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(750);
				match(SMALLINT);
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(751);
				match(INT);
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(752);
				match(INTEGER);
				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 4);
				{
				setState(753);
				match(BIGINT);
				}
				break;
			case SERIAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(754);
				match(SERIAL);
				}
				break;
			case BIGSERIAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(755);
				match(BIGSERIAL);
				}
				break;
			case SMALLSERIAL:
				enterOuterAlt(_localctx, 7);
				{
				setState(756);
				match(SMALLSERIAL);
				}
				break;
			case REAL:
				enterOuterAlt(_localctx, 8);
				{
				setState(757);
				match(REAL);
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 9);
				{
				setState(758);
				match(FLOAT);
				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 10);
				{
				setState(759);
				match(DOUBLE);
				setState(760);
				match(PRECISION);
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 11);
				{
				setState(761);
				match(DECIMAL);
				setState(769);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
				case 1:
					{
					setState(762);
					match(LPAREN);
					setState(763);
					match(INTEGER_LITERAL);
					setState(766);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(764);
						match(COMMA);
						setState(765);
						match(INTEGER_LITERAL);
						}
					}

					setState(768);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case NUMERIC:
				enterOuterAlt(_localctx, 12);
				{
				setState(771);
				match(NUMERIC);
				setState(779);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
				case 1:
					{
					setState(772);
					match(LPAREN);
					setState(773);
					match(INTEGER_LITERAL);
					setState(776);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(774);
						match(COMMA);
						setState(775);
						match(INTEGER_LITERAL);
						}
					}

					setState(778);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 13);
				{
				setState(781);
				match(CHAR);
				setState(785);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
				case 1:
					{
					setState(782);
					match(LPAREN);
					setState(783);
					match(INTEGER_LITERAL);
					setState(784);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 14);
				{
				setState(787);
				match(VARCHAR);
				setState(791);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
				case 1:
					{
					setState(788);
					match(LPAREN);
					setState(789);
					match(INTEGER_LITERAL);
					setState(790);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 15);
				{
				setState(793);
				match(TEXT);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 16);
				{
				setState(794);
				match(BOOLEAN);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 17);
				{
				setState(795);
				match(BOOL);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 18);
				{
				setState(796);
				match(DATE);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 19);
				{
				setState(797);
				match(TIMESTAMP);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 20);
				{
				setState(798);
				match(TIME);
				}
				break;
			case INTERVAL:
				enterOuterAlt(_localctx, 21);
				{
				setState(799);
				match(INTERVAL);
				}
				break;
			case JSON:
				enterOuterAlt(_localctx, 22);
				{
				setState(800);
				match(JSON);
				}
				break;
			case JSONB:
				enterOuterAlt(_localctx, 23);
				{
				setState(801);
				match(JSONB);
				}
				break;
			case UUID:
				enterOuterAlt(_localctx, 24);
				{
				setState(802);
				match(UUID);
				}
				break;
			case BYTEA:
				enterOuterAlt(_localctx, 25);
				{
				setState(803);
				match(BYTEA);
				}
				break;
			case INET:
				enterOuterAlt(_localctx, 26);
				{
				setState(804);
				match(INET);
				}
				break;
			case CIDR:
				enterOuterAlt(_localctx, 27);
				{
				setState(805);
				match(CIDR);
				}
				break;
			case MACADDR:
				enterOuterAlt(_localctx, 28);
				{
				setState(806);
				match(MACADDR);
				}
				break;
			case MONEY:
				enterOuterAlt(_localctx, 29);
				{
				setState(807);
				match(MONEY);
				}
				break;
			case XML:
				enterOuterAlt(_localctx, 30);
				{
				setState(808);
				match(XML);
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 31);
				{
				setState(809);
				match(ARRAY);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 32);
				{
				setState(810);
				match(IDENTIFIER);
				setState(818);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
				case 1:
					{
					setState(811);
					match(LPAREN);
					setState(812);
					match(INTEGER_LITERAL);
					setState(815);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(813);
						match(COMMA);
						setState(814);
						match(INTEGER_LITERAL);
						}
					}

					setState(817);
					match(RPAREN);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnConstraintContext extends ParserRuleContext {
		public TerminalNode NULL_() { return getToken(PostgreSqlParser.NULL_, 0); }
		public TerminalNode DEFAULT() { return getToken(PostgreSqlParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(PostgreSqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(PostgreSqlParser.KEY, 0); }
		public TerminalNode UNIQUE() { return getToken(PostgreSqlParser.UNIQUE, 0); }
		public TerminalNode CHECK() { return getToken(PostgreSqlParser.CHECK, 0); }
		public TerminalNode LPAREN() { return getToken(PostgreSqlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(PostgreSqlParser.RPAREN, 0); }
		public TerminalNode REFERENCES() { return getToken(PostgreSqlParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode CONSTRAINT() { return getToken(PostgreSqlParser.CONSTRAINT, 0); }
		public ConstraintNameContext constraintName() {
			return getRuleContext(ConstraintNameContext.class,0);
		}
		public TerminalNode NOT() { return getToken(PostgreSqlParser.NOT, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public ColumnConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnConstraintContext columnConstraint() throws RecognitionException {
		ColumnConstraintContext _localctx = new ColumnConstraintContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_columnConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(824);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONSTRAINT) {
				{
				setState(822);
				match(CONSTRAINT);
				setState(823);
				constraintName();
				}
			}

			setState(848);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case NULL_:
				{
				setState(827);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(826);
					match(NOT);
					}
				}

				setState(829);
				match(NULL_);
				}
				break;
			case DEFAULT:
				{
				setState(830);
				match(DEFAULT);
				setState(831);
				expression(0);
				}
				break;
			case PRIMARY:
				{
				setState(832);
				match(PRIMARY);
				setState(833);
				match(KEY);
				}
				break;
			case UNIQUE:
				{
				setState(834);
				match(UNIQUE);
				}
				break;
			case CHECK:
				{
				setState(835);
				match(CHECK);
				setState(836);
				match(LPAREN);
				setState(837);
				expression(0);
				setState(838);
				match(RPAREN);
				}
				break;
			case REFERENCES:
				{
				setState(840);
				match(REFERENCES);
				setState(841);
				tableName();
				setState(846);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(842);
					match(LPAREN);
					setState(843);
					columnName();
					setState(844);
					match(RPAREN);
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode PRIMARY() { return getToken(PostgreSqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(PostgreSqlParser.KEY, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(PostgreSqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(PostgreSqlParser.LPAREN, i);
		}
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(PostgreSqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(PostgreSqlParser.RPAREN, i);
		}
		public TerminalNode UNIQUE() { return getToken(PostgreSqlParser.UNIQUE, 0); }
		public TerminalNode FOREIGN() { return getToken(PostgreSqlParser.FOREIGN, 0); }
		public TerminalNode REFERENCES() { return getToken(PostgreSqlParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode CHECK() { return getToken(PostgreSqlParser.CHECK, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode CONSTRAINT() { return getToken(PostgreSqlParser.CONSTRAINT, 0); }
		public ConstraintNameContext constraintName() {
			return getRuleContext(ConstraintNameContext.class,0);
		}
		public TableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterTableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitTableConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitTableConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableConstraintContext tableConstraint() throws RecognitionException {
		TableConstraintContext _localctx = new TableConstraintContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_tableConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(852);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CONSTRAINT) {
				{
				setState(850);
				match(CONSTRAINT);
				setState(851);
				constraintName();
				}
			}

			setState(881);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRIMARY:
				{
				setState(854);
				match(PRIMARY);
				setState(855);
				match(KEY);
				setState(856);
				match(LPAREN);
				setState(857);
				columnNameList();
				setState(858);
				match(RPAREN);
				}
				break;
			case UNIQUE:
				{
				setState(860);
				match(UNIQUE);
				setState(861);
				match(LPAREN);
				setState(862);
				columnNameList();
				setState(863);
				match(RPAREN);
				}
				break;
			case FOREIGN:
				{
				setState(865);
				match(FOREIGN);
				setState(866);
				match(KEY);
				setState(867);
				match(LPAREN);
				setState(868);
				columnNameList();
				setState(869);
				match(RPAREN);
				setState(870);
				match(REFERENCES);
				setState(871);
				tableName();
				setState(872);
				match(LPAREN);
				setState(873);
				columnNameList();
				setState(874);
				match(RPAREN);
				}
				break;
			case CHECK:
				{
				setState(876);
				match(CHECK);
				setState(877);
				match(LPAREN);
				setState(878);
				expression(0);
				setState(879);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PartitionStrategyContext extends ParserRuleContext {
		public TerminalNode RANGE() { return getToken(PostgreSqlParser.RANGE, 0); }
		public TerminalNode LIST() { return getToken(PostgreSqlParser.LIST, 0); }
		public TerminalNode HASH() { return getToken(PostgreSqlParser.HASH, 0); }
		public PartitionStrategyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partitionStrategy; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterPartitionStrategy(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitPartitionStrategy(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitPartitionStrategy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartitionStrategyContext partitionStrategy() throws RecognitionException {
		PartitionStrategyContext _localctx = new PartitionStrategyContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_partitionStrategy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(883);
			_la = _input.LA(1);
			if ( !(((((_la - 91)) & ~0x3f) == 0 && ((1L << (_la - 91)) & 7L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexMethodContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSqlParser.IDENTIFIER, 0); }
		public IndexMethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIndexMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIndexMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIndexMethod(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexMethodContext indexMethod() throws RecognitionException {
		IndexMethodContext _localctx = new IndexMethodContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_indexMethod);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(885);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UpdateAssignmentContext extends ParserRuleContext {
		public ColumnRefContext columnRef() {
			return getRuleContext(ColumnRefContext.class,0);
		}
		public TerminalNode EQ() { return getToken(PostgreSqlParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UpdateAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterUpdateAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitUpdateAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitUpdateAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateAssignmentContext updateAssignment() throws RecognitionException {
		UpdateAssignmentContext _localctx = new UpdateAssignmentContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_updateAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(887);
			columnRef();
			setState(888);
			match(EQ);
			setState(889);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnNameListContext extends ParserRuleContext {
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public ColumnNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameListContext columnNameList() throws RecognitionException {
		ColumnNameListContext _localctx = new ColumnNameListContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_columnNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(891);
			columnName();
			setState(896);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(892);
				match(COMMA);
				setState(893);
				columnName();
				}
				}
				setState(898);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexColumnListContext extends ParserRuleContext {
		public List<IndexColumnContext> indexColumn() {
			return getRuleContexts(IndexColumnContext.class);
		}
		public IndexColumnContext indexColumn(int i) {
			return getRuleContext(IndexColumnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public IndexColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumnList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIndexColumnList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIndexColumnList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIndexColumnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnListContext indexColumnList() throws RecognitionException {
		IndexColumnListContext _localctx = new IndexColumnListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_indexColumnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(899);
			indexColumn();
			setState(904);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(900);
				match(COMMA);
				setState(901);
				indexColumn();
				}
				}
				setState(906);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexColumnContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode NULLS() { return getToken(PostgreSqlParser.NULLS, 0); }
		public TerminalNode ASC() { return getToken(PostgreSqlParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(PostgreSqlParser.DESC, 0); }
		public TerminalNode FIRST() { return getToken(PostgreSqlParser.FIRST, 0); }
		public TerminalNode LAST() { return getToken(PostgreSqlParser.LAST, 0); }
		public IndexColumnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumn; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIndexColumn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIndexColumn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIndexColumn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnContext indexColumn() throws RecognitionException {
		IndexColumnContext _localctx = new IndexColumnContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_indexColumn);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			columnName();
			setState(909);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(908);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(913);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NULLS) {
				{
				setState(911);
				match(NULLS);
				setState(912);
				_la = _input.LA(1);
				if ( !(_la==FIRST || _la==LAST) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PostgreSqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PostgreSqlParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(915);
			expression(0);
			setState(920);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(916);
				match(COMMA);
				setState(917);
				expression(0);
				}
				}
				setState(922);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnRefContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> DOT() { return getTokens(PostgreSqlParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(PostgreSqlParser.DOT, i);
		}
		public TerminalNode STAR() { return getToken(PostgreSqlParser.STAR, 0); }
		public ColumnRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnRefContext columnRef() throws RecognitionException {
		ColumnRefContext _localctx = new ColumnRefContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_columnRef);
		try {
			setState(938);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(923);
				identifier();
				setState(924);
				match(DOT);
				setState(925);
				identifier();
				setState(926);
				match(DOT);
				setState(927);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(929);
				identifier();
				setState(930);
				match(DOT);
				setState(931);
				identifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(933);
				identifier();
				setState(934);
				match(DOT);
				setState(935);
				match(STAR);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(937);
				identifier();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TableNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SchemaNameContext schemaName() {
			return getRuleContext(SchemaNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(PostgreSqlParser.DOT, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(943);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
			case 1:
				{
				setState(940);
				schemaName();
				setState(941);
				match(DOT);
				}
				break;
			}
			setState(945);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DatabaseNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public DatabaseNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_databaseName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterDatabaseName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitDatabaseName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitDatabaseName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatabaseNameContext databaseName() throws RecognitionException {
		DatabaseNameContext _localctx = new DatabaseNameContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_databaseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SchemaNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public SchemaNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schemaName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterSchemaName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitSchemaName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitSchemaName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SchemaNameContext schemaName() throws RecognitionException {
		SchemaNameContext _localctx = new SchemaNameContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_schemaName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(949);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ColumnNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(951);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public IndexNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIndexName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIndexName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIndexName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexNameContext indexName() throws RecognitionException {
		IndexNameContext _localctx = new IndexNameContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_indexName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(953);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstraintNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ConstraintNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraintName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterConstraintName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitConstraintName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitConstraintName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstraintNameContext constraintName() throws RecognitionException {
		ConstraintNameContext _localctx = new ConstraintNameContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_constraintName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(955);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CteNameContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public CteNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cteName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterCteName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitCteName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitCteName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CteNameContext cteName() throws RecognitionException {
		CteNameContext _localctx = new CteNameContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_cteName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(957);
			identifier();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AliasContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode STRING_LITERAL() { return getToken(PostgreSqlParser.STRING_LITERAL, 0); }
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_alias);
		try {
			setState(961);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case COLUMN:
			case INDEX:
			case KEY:
			case RECURSIVE:
			case CASCADE:
			case RESTRICT:
			case CONSTRAINT:
			case FIRST:
			case LAST:
			case CONCURRENTLY:
			case USING:
			case TABLESPACE:
			case PARTITION:
			case RANGE:
			case LIST:
			case HASH:
			case TEMP:
			case TEMPORARY:
			case UNLOGGED:
			case ONLY:
			case JSON:
			case JSONB:
			case UUID:
			case BYTEA:
			case INET:
			case CIDR:
			case MACADDR:
			case MONEY:
			case XML:
			case DOUBLE_QUOTED_ID:
			case IDENTIFIER:
			case COMMENT:
				enterOuterAlt(_localctx, 1);
				{
				setState(959);
				identifier();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(960);
				match(STRING_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(PostgreSqlParser.IDENTIFIER, 0); }
		public TerminalNode DOUBLE_QUOTED_ID() { return getToken(PostgreSqlParser.DOUBLE_QUOTED_ID, 0); }
		public NonReservedKeywordContext nonReservedKeyword() {
			return getRuleContext(NonReservedKeywordContext.class,0);
		}
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_identifier);
		try {
			setState(966);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(963);
				match(IDENTIFIER);
				}
				break;
			case DOUBLE_QUOTED_ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(964);
				match(DOUBLE_QUOTED_ID);
				}
				break;
			case COLUMN:
			case INDEX:
			case KEY:
			case RECURSIVE:
			case CASCADE:
			case RESTRICT:
			case CONSTRAINT:
			case FIRST:
			case LAST:
			case CONCURRENTLY:
			case USING:
			case TABLESPACE:
			case PARTITION:
			case RANGE:
			case LIST:
			case HASH:
			case TEMP:
			case TEMPORARY:
			case UNLOGGED:
			case ONLY:
			case JSON:
			case JSONB:
			case UUID:
			case BYTEA:
			case INET:
			case CIDR:
			case MACADDR:
			case MONEY:
			case XML:
			case COMMENT:
				enterOuterAlt(_localctx, 3);
				{
				setState(965);
				nonReservedKeyword();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NonReservedKeywordContext extends ParserRuleContext {
		public TerminalNode KEY() { return getToken(PostgreSqlParser.KEY, 0); }
		public TerminalNode INDEX() { return getToken(PostgreSqlParser.INDEX, 0); }
		public TerminalNode COLUMN() { return getToken(PostgreSqlParser.COLUMN, 0); }
		public TerminalNode COMMENT() { return getToken(PostgreSqlParser.COMMENT, 0); }
		public TerminalNode CASCADE() { return getToken(PostgreSqlParser.CASCADE, 0); }
		public TerminalNode RESTRICT() { return getToken(PostgreSqlParser.RESTRICT, 0); }
		public TerminalNode CONSTRAINT() { return getToken(PostgreSqlParser.CONSTRAINT, 0); }
		public TerminalNode FIRST() { return getToken(PostgreSqlParser.FIRST, 0); }
		public TerminalNode LAST() { return getToken(PostgreSqlParser.LAST, 0); }
		public TerminalNode ONLY() { return getToken(PostgreSqlParser.ONLY, 0); }
		public TerminalNode RANGE() { return getToken(PostgreSqlParser.RANGE, 0); }
		public TerminalNode LIST() { return getToken(PostgreSqlParser.LIST, 0); }
		public TerminalNode HASH() { return getToken(PostgreSqlParser.HASH, 0); }
		public TerminalNode PARTITION() { return getToken(PostgreSqlParser.PARTITION, 0); }
		public TerminalNode USING() { return getToken(PostgreSqlParser.USING, 0); }
		public TerminalNode TABLESPACE() { return getToken(PostgreSqlParser.TABLESPACE, 0); }
		public TerminalNode CONCURRENTLY() { return getToken(PostgreSqlParser.CONCURRENTLY, 0); }
		public TerminalNode UNLOGGED() { return getToken(PostgreSqlParser.UNLOGGED, 0); }
		public TerminalNode TEMP() { return getToken(PostgreSqlParser.TEMP, 0); }
		public TerminalNode TEMPORARY() { return getToken(PostgreSqlParser.TEMPORARY, 0); }
		public TerminalNode RECURSIVE() { return getToken(PostgreSqlParser.RECURSIVE, 0); }
		public TerminalNode JSON() { return getToken(PostgreSqlParser.JSON, 0); }
		public TerminalNode JSONB() { return getToken(PostgreSqlParser.JSONB, 0); }
		public TerminalNode UUID() { return getToken(PostgreSqlParser.UUID, 0); }
		public TerminalNode BYTEA() { return getToken(PostgreSqlParser.BYTEA, 0); }
		public TerminalNode INET() { return getToken(PostgreSqlParser.INET, 0); }
		public TerminalNode CIDR() { return getToken(PostgreSqlParser.CIDR, 0); }
		public TerminalNode MACADDR() { return getToken(PostgreSqlParser.MACADDR, 0); }
		public TerminalNode MONEY() { return getToken(PostgreSqlParser.MONEY, 0); }
		public TerminalNode XML() { return getToken(PostgreSqlParser.XML, 0); }
		public NonReservedKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonReservedKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterNonReservedKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitNonReservedKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitNonReservedKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonReservedKeywordContext nonReservedKeyword() throws RecognitionException {
		NonReservedKeywordContext _localctx = new NonReservedKeywordContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_nonReservedKeyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(968);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2883584L) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 4571153630352113871L) != 0) || _la==COMMENT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode INTEGER_LITERAL() { return getToken(PostgreSqlParser.INTEGER_LITERAL, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(PostgreSqlParser.DECIMAL_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(PostgreSqlParser.STRING_LITERAL, 0); }
		public TerminalNode DOLLAR_STRING() { return getToken(PostgreSqlParser.DOLLAR_STRING, 0); }
		public TerminalNode NULL_() { return getToken(PostgreSqlParser.NULL_, 0); }
		public TerminalNode TRUE() { return getToken(PostgreSqlParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(PostgreSqlParser.FALSE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PostgreSqlParserListener ) ((PostgreSqlParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PostgreSqlParserVisitor ) return ((PostgreSqlParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(970);
			_la = _input.LA(1);
			if ( !(((((_la - 25)) & ~0x3f) == 0 && ((1L << (_la - 25)) & 108086391056891905L) != 0) || ((((_la - 149)) & ~0x3f) == 0 && ((1L << (_la - 149)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 33:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 22);
		case 1:
			return precpred(_ctx, 21);
		case 2:
			return precpred(_ctx, 20);
		case 3:
			return precpred(_ctx, 18);
		case 4:
			return precpred(_ctx, 17);
		case 5:
			return precpred(_ctx, 16);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 11);
		case 8:
			return precpred(_ctx, 10);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 19);
		case 12:
			return precpred(_ctx, 15);
		case 13:
			return precpred(_ctx, 14);
		case 14:
			return precpred(_ctx, 12);
		case 15:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u009e\u03cd\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007"+
		"\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007"+
		"\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007"+
		"\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007"+
		"\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007"+
		"\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007"+
		",\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u0007"+
		"1\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u0007"+
		"6\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007"+
		";\u0002<\u0007<\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u0080\b\u0001\u0005\u0001\u0082\b\u0001\n\u0001\f\u0001"+
		"\u0085\t\u0001\u0001\u0002\u0001\u0002\u0003\u0002\u0089\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003\u0093\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u009a\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003"+
		"\u0005\u00a3\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00ab\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00b1\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00b7\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00be\b\u0007\n\u0007\f\u0007"+
		"\u00c1\t\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0005\u0007\u00c9\b\u0007\n\u0007\f\u0007\u00cc\t\u0007\u0001"+
		"\u0007\u0001\u0007\u0003\u0007\u00d0\b\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00d9"+
		"\b\u0007\n\u0007\f\u0007\u00dc\t\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"\u00e0\b\u0007\u0001\b\u0001\b\u0003\b\u00e4\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u00ea\b\t\u0001\t\u0001\t\u0003\t\u00ee\b\t\u0001\n"+
		"\u0001\n\u0001\n\u0003\n\u00f3\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u00f9\b\n\n\n\f\n\u00fc\t\n\u0001\u000b\u0001\u000b\u0003\u000b\u0100"+
		"\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0105\b\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u010a\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u010e\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b"+
		"\u0112\b\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0116\b\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u011f\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u012b\b\u000b\u0003\u000b\u012d\b\u000b\u0001\f\u0001"+
		"\f\u0003\f\u0131\b\f\u0001\f\u0001\f\u0003\f\u0135\b\f\u0001\f\u0001\f"+
		"\u0001\f\u0003\f\u013a\b\f\u0001\f\u0003\f\u013d\b\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0143\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0003\r\u014c\b\r\u0001\r\u0001\r\u0003\r\u0150\b\r\u0001\r"+
		"\u0001\r\u0003\r\u0154\b\r\u0001\u000e\u0003\u000e\u0157\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u015d\b\u000e\u0001"+
		"\u000f\u0001\u000f\u0003\u000f\u0161\b\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0005\u000f\u0166\b\u000f\n\u000f\f\u000f\u0169\t\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u0170\b\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0179\b\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u0180\b\u0011\n\u0011\f\u0011\u0183"+
		"\t\u0011\u0003\u0011\u0185\b\u0011\u0001\u0011\u0005\u0011\u0188\b\u0011"+
		"\n\u0011\f\u0011\u018b\t\u0011\u0001\u0011\u0003\u0011\u018e\b\u0011\u0001"+
		"\u0011\u0003\u0011\u0191\b\u0011\u0001\u0011\u0003\u0011\u0194\b\u0011"+
		"\u0001\u0011\u0003\u0011\u0197\b\u0011\u0001\u0011\u0003\u0011\u019a\b"+
		"\u0011\u0001\u0011\u0003\u0011\u019d\b\u0011\u0001\u0011\u0001\u0011\u0003"+
		"\u0011\u01a1\b\u0011\u0001\u0011\u0003\u0011\u01a4\b\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0003\u0012\u01ad\b\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u01b8\b\u0012\n\u0012\f\u0012\u01bb\t\u0012\u0001\u0012\u0003\u0012\u01be"+
		"\b\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u01c2\b\u0012\u0001\u0013"+
		"\u0001\u0013\u0003\u0013\u01c6\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u01ca\b\u0013\u0001\u0013\u0003\u0013\u01cd\b\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u01d3\b\u0013\n\u0013\f\u0013"+
		"\u01d6\t\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u01dc\b\u0013\n\u0013\f\u0013\u01df\t\u0013\u0003\u0013\u01e1\b\u0013"+
		"\u0001\u0013\u0003\u0013\u01e4\b\u0013\u0001\u0013\u0001\u0013\u0003\u0013"+
		"\u01e8\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01ed\b"+
		"\u0014\u0001\u0014\u0001\u0014\u0003\u0014\u01f1\b\u0014\u0001\u0014\u0003"+
		"\u0014\u01f4\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u01fa\b\u0014\n\u0014\f\u0014\u01fd\t\u0014\u0003\u0014\u01ff\b"+
		"\u0014\u0001\u0014\u0003\u0014\u0202\b\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u0206\b\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0005"+
		"\u0015\u020c\b\u0015\n\u0015\f\u0015\u020f\t\u0015\u0003\u0015\u0211\b"+
		"\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u0215\b\u0016\u0001\u0016\u0003"+
		"\u0016\u0218\b\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u021c\b\u0017"+
		"\u0001\u0017\u0003\u0017\u021f\b\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0003\u0017\u0225\b\u0017\u0001\u0017\u0003\u0017\u0228\b"+
		"\u0017\u0003\u0017\u022a\b\u0017\u0001\u0018\u0003\u0018\u022d\b\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0238\b\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u023d\b\u0019\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u0241\b\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0245\b"+
		"\u0019\u0001\u0019\u0003\u0019\u0248\b\u0019\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001d\u0005\u001d\u0259\b\u001d\n\u001d\f\u001d\u025c\t\u001d\u0001\u001e"+
		"\u0001\u001e\u0003\u001e\u0260\b\u001e\u0001\u001e\u0001\u001e\u0003\u001e"+
		"\u0264\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u0269\b"+
		"\u001f\u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!"+
		"\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0003!\u0281\b!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003!\u02ad\b!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0005!\u02c0\b!\n!\f!\u02c3"+
		"\t!\u0001\"\u0001\"\u0001#\u0001#\u0003#\u02c9\b#\u0001#\u0001#\u0001"+
		"#\u0001#\u0001#\u0004#\u02d0\b#\u000b#\f#\u02d1\u0001#\u0001#\u0003#\u02d6"+
		"\b#\u0001#\u0001#\u0001$\u0001$\u0001$\u0003$\u02dd\b$\u0001$\u0001$\u0003"+
		"$\u02e1\b$\u0001$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001&\u0005&\u02ea"+
		"\b&\n&\f&\u02ed\t&\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0003\'\u02ff\b\'\u0001\'\u0003\'\u0302\b\'\u0001\'\u0001\'\u0001\'"+
		"\u0001\'\u0001\'\u0003\'\u0309\b\'\u0001\'\u0003\'\u030c\b\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0003\'\u0312\b\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003"+
		"\'\u0318\b\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0003\'\u0330\b\'\u0001\'\u0003"+
		"\'\u0333\b\'\u0003\'\u0335\b\'\u0001(\u0001(\u0003(\u0339\b(\u0001(\u0003"+
		"(\u033c\b(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0003(\u034f"+
		"\b(\u0003(\u0351\b(\u0001)\u0001)\u0003)\u0355\b)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0003)\u0372\b)\u0001*\u0001*\u0001+\u0001"+
		"+\u0001,\u0001,\u0001,\u0001,\u0001-\u0001-\u0001-\u0005-\u037f\b-\n-"+
		"\f-\u0382\t-\u0001.\u0001.\u0001.\u0005.\u0387\b.\n.\f.\u038a\t.\u0001"+
		"/\u0001/\u0003/\u038e\b/\u0001/\u0001/\u0003/\u0392\b/\u00010\u00010\u0001"+
		"0\u00050\u0397\b0\n0\f0\u039a\t0\u00011\u00011\u00011\u00011\u00011\u0001"+
		"1\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u00011\u0003"+
		"1\u03ab\b1\u00012\u00012\u00012\u00032\u03b0\b2\u00012\u00012\u00013\u0001"+
		"3\u00014\u00014\u00015\u00015\u00016\u00016\u00017\u00017\u00018\u0001"+
		"8\u00019\u00019\u00039\u03c2\b9\u0001:\u0001:\u0001:\u0003:\u03c7\b:\u0001"+
		";\u0001;\u0001<\u0001<\u0001<\u0000\u0001B=\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02"+
		"468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvx\u0000\f\u0001\u0000^`\u0001\u0000"+
		"BC\u0001\u000046\u0001\u0000=>\u0001\u0000GH\u0002\u0000\u007f\u007f\u008f"+
		"\u0090\u0001\u0000\u008d\u008e\u0001\u0000\u0087\u008c\u0002\u0000IO\u009a"+
		"\u009a\u0001\u0000[]\t\u0000\u0012\u0013\u0015\u0015ADGHVXZauwy~\u009e"+
		"\u009e\u0003\u0000\u0019\u0019PQ\u0095\u0098\u0458\u0000z\u0001\u0000"+
		"\u0000\u0000\u0002\u0083\u0001\u0000\u0000\u0000\u0004\u0088\u0001\u0000"+
		"\u0000\u0000\u0006\u0092\u0001\u0000\u0000\u0000\b\u0094\u0001\u0000\u0000"+
		"\u0000\n\u009d\u0001\u0000\u0000\u0000\f\u00a6\u0001\u0000\u0000\u0000"+
		"\u000e\u00ae\u0001\u0000\u0000\u0000\u0010\u00e3\u0001\u0000\u0000\u0000"+
		"\u0012\u00e5\u0001\u0000\u0000\u0000\u0014\u00ef\u0001\u0000\u0000\u0000"+
		"\u0016\u012c\u0001\u0000\u0000\u0000\u0018\u012e\u0001\u0000\u0000\u0000"+
		"\u001a\u0148\u0001\u0000\u0000\u0000\u001c\u015c\u0001\u0000\u0000\u0000"+
		"\u001e\u015e\u0001\u0000\u0000\u0000 \u016a\u0001\u0000\u0000\u0000\""+
		"\u0176\u0001\u0000\u0000\u0000$\u01a5\u0001\u0000\u0000\u0000&\u01c3\u0001"+
		"\u0000\u0000\u0000(\u01e9\u0001\u0000\u0000\u0000*\u0210\u0001\u0000\u0000"+
		"\u0000,\u0212\u0001\u0000\u0000\u0000.\u0229\u0001\u0000\u0000\u00000"+
		"\u022c\u0001\u0000\u0000\u00002\u0247\u0001\u0000\u0000\u00004\u0249\u0001"+
		"\u0000\u0000\u00006\u024c\u0001\u0000\u0000\u00008\u0250\u0001\u0000\u0000"+
		"\u0000:\u0253\u0001\u0000\u0000\u0000<\u025d\u0001\u0000\u0000\u0000>"+
		"\u0265\u0001\u0000\u0000\u0000@\u026a\u0001\u0000\u0000\u0000B\u0280\u0001"+
		"\u0000\u0000\u0000D\u02c4\u0001\u0000\u0000\u0000F\u02c6\u0001\u0000\u0000"+
		"\u0000H\u02d9\u0001\u0000\u0000\u0000J\u02e4\u0001\u0000\u0000\u0000L"+
		"\u02e6\u0001\u0000\u0000\u0000N\u0334\u0001\u0000\u0000\u0000P\u0338\u0001"+
		"\u0000\u0000\u0000R\u0354\u0001\u0000\u0000\u0000T\u0373\u0001\u0000\u0000"+
		"\u0000V\u0375\u0001\u0000\u0000\u0000X\u0377\u0001\u0000\u0000\u0000Z"+
		"\u037b\u0001\u0000\u0000\u0000\\\u0383\u0001\u0000\u0000\u0000^\u038b"+
		"\u0001\u0000\u0000\u0000`\u0393\u0001\u0000\u0000\u0000b\u03aa\u0001\u0000"+
		"\u0000\u0000d\u03af\u0001\u0000\u0000\u0000f\u03b3\u0001\u0000\u0000\u0000"+
		"h\u03b5\u0001\u0000\u0000\u0000j\u03b7\u0001\u0000\u0000\u0000l\u03b9"+
		"\u0001\u0000\u0000\u0000n\u03bb\u0001\u0000\u0000\u0000p\u03bd\u0001\u0000"+
		"\u0000\u0000r\u03c1\u0001\u0000\u0000\u0000t\u03c6\u0001\u0000\u0000\u0000"+
		"v\u03c8\u0001\u0000\u0000\u0000x\u03ca\u0001\u0000\u0000\u0000z{\u0003"+
		"\u0002\u0001\u0000{|\u0005\u0000\u0000\u0001|\u0001\u0001\u0000\u0000"+
		"\u0000}\u007f\u0003\u0004\u0002\u0000~\u0080\u0005\u0086\u0000\u0000\u007f"+
		"~\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080\u0082"+
		"\u0001\u0000\u0000\u0000\u0081}\u0001\u0000\u0000\u0000\u0082\u0085\u0001"+
		"\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001"+
		"\u0000\u0000\u0000\u0084\u0003\u0001\u0000\u0000\u0000\u0085\u0083\u0001"+
		"\u0000\u0000\u0000\u0086\u0089\u0003\u0006\u0003\u0000\u0087\u0089\u0003"+
		"\u001c\u000e\u0000\u0088\u0086\u0001\u0000\u0000\u0000\u0088\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u0005\u0001\u0000\u0000\u0000\u008a\u0093\u0003"+
		"\b\u0004\u0000\u008b\u0093\u0003\n\u0005\u0000\u008c\u0093\u0003\u000e"+
		"\u0007\u0000\u008d\u0093\u0003\f\u0006\u0000\u008e\u0093\u0003\u0012\t"+
		"\u0000\u008f\u0093\u0003\u0014\n\u0000\u0090\u0093\u0003\u0018\f\u0000"+
		"\u0091\u0093\u0003\u001a\r\u0000\u0092\u008a\u0001\u0000\u0000\u0000\u0092"+
		"\u008b\u0001\u0000\u0000\u0000\u0092\u008c\u0001\u0000\u0000\u0000\u0092"+
		"\u008d\u0001\u0000\u0000\u0000\u0092\u008e\u0001\u0000\u0000\u0000\u0092"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092"+
		"\u0091\u0001\u0000\u0000\u0000\u0093\u0007\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\r\u0000\u0000\u0095\u0099\u0005\u001d\u0000\u0000\u0096\u0097"+
		"\u0005\u001b\u0000\u0000\u0097\u0098\u0005\u0006\u0000\u0000\u0098\u009a"+
		"\u0005\u001c\u0000\u0000\u0099\u0096\u0001\u0000\u0000\u0000\u0099\u009a"+
		"\u0001\u0000\u0000\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c"+
		"\u0003f3\u0000\u009c\t\u0001\u0000\u0000\u0000\u009d\u009e\u0005\r\u0000"+
		"\u0000\u009e\u00a2\u0005\u001e\u0000\u0000\u009f\u00a0\u0005\u001b\u0000"+
		"\u0000\u00a0\u00a1\u0005\u0006\u0000\u0000\u00a1\u00a3\u0005\u001c\u0000"+
		"\u0000\u00a2\u009f\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0001\u0000\u0000\u0000\u00a4\u00a5\u0003h4\u0000\u00a5"+
		"\u000b\u0001\u0000\u0000\u0000\u00a6\u00a7\u0005\u000f\u0000\u0000\u00a7"+
		"\u00aa\u0005\u001d\u0000\u0000\u00a8\u00a9\u0005\u001b\u0000\u0000\u00a9"+
		"\u00ab\u0005\u001c\u0000\u0000\u00aa\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000\u00ac"+
		"\u00ad\u0003f3\u0000\u00ad\r\u0001\u0000\u0000\u0000\u00ae\u00b0\u0005"+
		"\r\u0000\u0000\u00af\u00b1\u0007\u0000\u0000\u0000\u00b0\u00af\u0001\u0000"+
		"\u0000\u0000\u00b0\u00b1\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001\u0000"+
		"\u0000\u0000\u00b2\u00b6\u0005\u000e\u0000\u0000\u00b3\u00b4\u0005\u001b"+
		"\u0000\u0000\u00b4\u00b5\u0005\u0006\u0000\u0000\u00b5\u00b7\u0005\u001c"+
		"\u0000\u0000\u00b6\u00b3\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000\u00b8\u00b9\u0003d2\u0000"+
		"\u00b9\u00ba\u0005\u0082\u0000\u0000\u00ba\u00bf\u0003\u0010\b\u0000\u00bb"+
		"\u00bc\u0005\u0080\u0000\u0000\u00bc\u00be\u0003\u0010\b\u0000\u00bd\u00bb"+
		"\u0001\u0000\u0000\u0000\u00be\u00c1\u0001\u0000\u0000\u0000\u00bf\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00c2"+
		"\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000\u00c2\u00cf"+
		"\u0005\u0083\u0000\u0000\u00c3\u00c4\u0005Y\u0000\u0000\u00c4\u00c5\u0005"+
		"\u0082\u0000\u0000\u00c5\u00ca\u0003d2\u0000\u00c6\u00c7\u0005\u0080\u0000"+
		"\u0000\u00c7\u00c9\u0003d2\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9"+
		"\u00cc\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0001\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000\u0000\u00cc"+
		"\u00ca\u0001\u0000\u0000\u0000\u00cd\u00ce\u0005\u0083\u0000\u0000\u00ce"+
		"\u00d0\u0001\u0000\u0000\u0000\u00cf\u00c3\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d0\u00df\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u0005Z\u0000\u0000\u00d2\u00d3\u0005 \u0000\u0000\u00d3\u00d4\u0003"+
		"T*\u0000\u00d4\u00d5\u0005\u0082\u0000\u0000\u00d5\u00da\u0003j5\u0000"+
		"\u00d6\u00d7\u0005\u0080\u0000\u0000\u00d7\u00d9\u0003j5\u0000\u00d8\u00d6"+
		"\u0001\u0000\u0000\u0000\u00d9\u00dc\u0001\u0000\u0000\u0000\u00da\u00d8"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dd"+
		"\u0001\u0000\u0000\u0000\u00dc\u00da\u0001\u0000\u0000\u0000\u00dd\u00de"+
		"\u0005\u0083\u0000\u0000\u00de\u00e0\u0001\u0000\u0000\u0000\u00df\u00d1"+
		"\u0001\u0000\u0000\u0000\u00df\u00e0\u0001\u0000\u0000\u0000\u00e0\u000f"+
		"\u0001\u0000\u0000\u0000\u00e1\u00e4\u0003L&\u0000\u00e2\u00e4\u0003R"+
		")\u0000\u00e3\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e4\u0011\u0001\u0000\u0000\u0000\u00e5\u00e6\u0005\u000f\u0000"+
		"\u0000\u00e6\u00e9\u0005\u000e\u0000\u0000\u00e7\u00e8\u0005\u001b\u0000"+
		"\u0000\u00e8\u00ea\u0005\u001c\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0001\u0000\u0000"+
		"\u0000\u00eb\u00ed\u0003d2\u0000\u00ec\u00ee\u0007\u0001\u0000\u0000\u00ed"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee"+
		"\u0013\u0001\u0000\u0000\u0000\u00ef\u00f0\u0005\u0010\u0000\u0000\u00f0"+
		"\u00f2\u0005\u000e\u0000\u0000\u00f1\u00f3\u0005a\u0000\u0000\u00f2\u00f1"+
		"\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001\u0000\u0000\u0000\u00f3\u00f4"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u0003d2\u0000\u00f5\u00fa\u0003\u0016"+
		"\u000b\u0000\u00f6\u00f7\u0005\u0080\u0000\u0000\u00f7\u00f9\u0003\u0016"+
		"\u000b\u0000\u00f8\u00f6\u0001\u0000\u0000\u0000\u00f9\u00fc\u0001\u0000"+
		"\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fa\u00fb\u0001\u0000"+
		"\u0000\u0000\u00fb\u0015\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001\u0000"+
		"\u0000\u0000\u00fd\u00ff\u0005\u0011\u0000\u0000\u00fe\u0100\u0005\u0012"+
		"\u0000\u0000\u00ff\u00fe\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000"+
		"\u0000\u0000\u0100\u0104\u0001\u0000\u0000\u0000\u0101\u0102\u0005\u001b"+
		"\u0000\u0000\u0102\u0103\u0005\u0006\u0000\u0000\u0103\u0105\u0005\u001c"+
		"\u0000\u0000\u0104\u0101\u0001\u0000\u0000\u0000\u0104\u0105\u0001\u0000"+
		"\u0000\u0000\u0105\u0106\u0001\u0000\u0000\u0000\u0106\u012d\u0003L&\u0000"+
		"\u0107\u0109\u0005\u000f\u0000\u0000\u0108\u010a\u0005\u0012\u0000\u0000"+
		"\u0109\u0108\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000"+
		"\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u010c\u0005\u001b\u0000\u0000"+
		"\u010c\u010e\u0005\u001c\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000"+
		"\u010d\u010e\u0001\u0000\u0000\u0000\u010e\u010f\u0001\u0000\u0000\u0000"+
		"\u010f\u0111\u0003j5\u0000\u0110\u0112\u0007\u0001\u0000\u0000\u0111\u0110"+
		"\u0001\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112\u012d"+
		"\u0001\u0000\u0000\u0000\u0113\u0115\u0005\u0010\u0000\u0000\u0114\u0116"+
		"\u0005\u0012\u0000\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0115\u0116"+
		"\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117\u0118"+
		"\u0003j5\u0000\u0118\u0119\u0005\u000b\u0000\u0000\u0119\u011a\u0005\u001a"+
		"\u0000\u0000\u011a\u011b\u0003B!\u0000\u011b\u012d\u0001\u0000\u0000\u0000"+
		"\u011c\u011e\u0005\u0010\u0000\u0000\u011d\u011f\u0005\u0012\u0000\u0000"+
		"\u011e\u011d\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000"+
		"\u011f\u0120\u0001\u0000\u0000\u0000\u0120\u0121\u0003j5\u0000\u0121\u0122"+
		"\u0005\u000f\u0000\u0000\u0122\u0123\u0005\u001a\u0000\u0000\u0123\u012d"+
		"\u0001\u0000\u0000\u0000\u0124\u0125\u0005\u0011\u0000\u0000\u0125\u012d"+
		"\u0003R)\u0000\u0126\u0127\u0005\u000f\u0000\u0000\u0127\u0128\u0005D"+
		"\u0000\u0000\u0128\u012a\u0003n7\u0000\u0129\u012b\u0007\u0001\u0000\u0000"+
		"\u012a\u0129\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000\u0000\u0000"+
		"\u012b\u012d\u0001\u0000\u0000\u0000\u012c\u00fd\u0001\u0000\u0000\u0000"+
		"\u012c\u0107\u0001\u0000\u0000\u0000\u012c\u0113\u0001\u0000\u0000\u0000"+
		"\u012c\u011c\u0001\u0000\u0000\u0000\u012c\u0124\u0001\u0000\u0000\u0000"+
		"\u012c\u0126\u0001\u0000\u0000\u0000\u012d\u0017\u0001\u0000\u0000\u0000"+
		"\u012e\u0130\u0005\r\u0000\u0000\u012f\u0131\u0005\u0018\u0000\u0000\u0130"+
		"\u012f\u0001\u0000\u0000\u0000\u0130\u0131\u0001\u0000\u0000\u0000\u0131"+
		"\u0132\u0001\u0000\u0000\u0000\u0132\u0134\u0005\u0013\u0000\u0000\u0133"+
		"\u0135\u0005V\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0134\u0135"+
		"\u0001\u0000\u0000\u0000\u0135\u0139\u0001\u0000\u0000\u0000\u0136\u0137"+
		"\u0005\u001b\u0000\u0000\u0137\u0138\u0005\u0006\u0000\u0000\u0138\u013a"+
		"\u0005\u001c\u0000\u0000\u0139\u0136\u0001\u0000\u0000\u0000\u0139\u013a"+
		"\u0001\u0000\u0000\u0000\u013a\u013c\u0001\u0000\u0000\u0000\u013b\u013d"+
		"\u0003l6\u0000\u013c\u013b\u0001\u0000\u0000\u0000\u013c\u013d\u0001\u0000"+
		"\u0000\u0000\u013d\u013e\u0001\u0000\u0000\u0000\u013e\u013f\u0005,\u0000"+
		"\u0000\u013f\u0142\u0003d2\u0000\u0140\u0141\u0005W\u0000\u0000\u0141"+
		"\u0143\u0003V+\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0142\u0143\u0001"+
		"\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0145\u0005"+
		"\u0082\u0000\u0000\u0145\u0146\u0003\\.\u0000\u0146\u0147\u0005\u0083"+
		"\u0000\u0000\u0147\u0019\u0001\u0000\u0000\u0000\u0148\u0149\u0005\u000f"+
		"\u0000\u0000\u0149\u014b\u0005\u0013\u0000\u0000\u014a\u014c\u0005V\u0000"+
		"\u0000\u014b\u014a\u0001\u0000\u0000\u0000\u014b\u014c\u0001\u0000\u0000"+
		"\u0000\u014c\u014f\u0001\u0000\u0000\u0000\u014d\u014e\u0005\u001b\u0000"+
		"\u0000\u014e\u0150\u0005\u001c\u0000\u0000\u014f\u014d\u0001\u0000\u0000"+
		"\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000\u0000"+
		"\u0000\u0151\u0153\u0003l6\u0000\u0152\u0154\u0007\u0001\u0000\u0000\u0153"+
		"\u0152\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000\u0000\u0000\u0154"+
		"\u001b\u0001\u0000\u0000\u0000\u0155\u0157\u0003\u001e\u000f\u0000\u0156"+
		"\u0155\u0001\u0000\u0000\u0000\u0156\u0157\u0001\u0000\u0000\u0000\u0157"+
		"\u0158\u0001\u0000\u0000\u0000\u0158\u015d\u0003\"\u0011\u0000\u0159\u015d"+
		"\u0003$\u0012\u0000\u015a\u015d\u0003&\u0013\u0000\u015b\u015d\u0003("+
		"\u0014\u0000\u015c\u0156\u0001\u0000\u0000\u0000\u015c\u0159\u0001\u0000"+
		"\u0000\u0000\u015c\u015a\u0001\u0000\u0000\u0000\u015c\u015b\u0001\u0000"+
		"\u0000\u0000\u015d\u001d\u0001\u0000\u0000\u0000\u015e\u0160\u0005@\u0000"+
		"\u0000\u015f\u0161\u0005A\u0000\u0000\u0160\u015f\u0001\u0000\u0000\u0000"+
		"\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0162\u0001\u0000\u0000\u0000"+
		"\u0162\u0167\u0003 \u0010\u0000\u0163\u0164\u0005\u0080\u0000\u0000\u0164"+
		"\u0166\u0003 \u0010\u0000\u0165\u0163\u0001\u0000\u0000\u0000\u0166\u0169"+
		"\u0001\u0000\u0000\u0000\u0167\u0165\u0001\u0000\u0000\u0000\u0167\u0168"+
		"\u0001\u0000\u0000\u0000\u0168\u001f\u0001\u0000\u0000\u0000\u0169\u0167"+
		"\u0001\u0000\u0000\u0000\u016a\u016f\u0003p8\u0000\u016b\u016c\u0005\u0082"+
		"\u0000\u0000\u016c\u016d\u0003Z-\u0000\u016d\u016e\u0005\u0083\u0000\u0000"+
		"\u016e\u0170\u0001\u0000\u0000\u0000\u016f\u016b\u0001\u0000\u0000\u0000"+
		"\u016f\u0170\u0001\u0000\u0000\u0000\u0170\u0171\u0001\u0000\u0000\u0000"+
		"\u0171\u0172\u0005-\u0000\u0000\u0172\u0173\u0005\u0082\u0000\u0000\u0173"+
		"\u0174\u0003\"\u0011\u0000\u0174\u0175\u0005\u0083\u0000\u0000\u0175!"+
		"\u0001\u0000\u0000\u0000\u0176\u0178\u0005\u0001\u0000\u0000\u0177\u0179"+
		"\u00053\u0000\u0000\u0178\u0177\u0001\u0000\u0000\u0000\u0178\u0179\u0001"+
		"\u0000\u0000\u0000\u0179\u017a\u0001\u0000\u0000\u0000\u017a\u0184\u0003"+
		"*\u0015\u0000\u017b\u017c\u0005\u0002\u0000\u0000\u017c\u0181\u0003.\u0017"+
		"\u0000\u017d\u017e\u0005\u0080\u0000\u0000\u017e\u0180\u0003.\u0017\u0000"+
		"\u017f\u017d\u0001\u0000\u0000\u0000\u0180\u0183\u0001\u0000\u0000\u0000"+
		"\u0181\u017f\u0001\u0000\u0000\u0000\u0181\u0182\u0001\u0000\u0000\u0000"+
		"\u0182\u0185\u0001\u0000\u0000\u0000\u0183\u0181\u0001\u0000\u0000\u0000"+
		"\u0184\u017b\u0001\u0000\u0000\u0000\u0184\u0185\u0001\u0000\u0000\u0000"+
		"\u0185\u0189\u0001\u0000\u0000\u0000\u0186\u0188\u00030\u0018\u0000\u0187"+
		"\u0186\u0001\u0000\u0000\u0000\u0188\u018b\u0001\u0000\u0000\u0000\u0189"+
		"\u0187\u0001\u0000\u0000\u0000\u0189\u018a\u0001\u0000\u0000\u0000\u018a"+
		"\u018d\u0001\u0000\u0000\u0000\u018b\u0189\u0001\u0000\u0000\u0000\u018c"+
		"\u018e\u00034\u001a\u0000\u018d\u018c\u0001\u0000\u0000\u0000\u018d\u018e"+
		"\u0001\u0000\u0000\u0000\u018e\u0190\u0001\u0000\u0000\u0000\u018f\u0191"+
		"\u00036\u001b\u0000\u0190\u018f\u0001\u0000\u0000\u0000\u0190\u0191\u0001"+
		"\u0000\u0000\u0000\u0191\u0193\u0001\u0000\u0000\u0000\u0192\u0194\u0003"+
		"8\u001c\u0000\u0193\u0192\u0001\u0000\u0000\u0000\u0193\u0194\u0001\u0000"+
		"\u0000\u0000\u0194\u0196\u0001\u0000\u0000\u0000\u0195\u0197\u0003:\u001d"+
		"\u0000\u0196\u0195\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000"+
		"\u0000\u0197\u0199\u0001\u0000\u0000\u0000\u0198\u019a\u0003>\u001f\u0000"+
		"\u0199\u0198\u0001\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000"+
		"\u019a\u019c\u0001\u0000\u0000\u0000\u019b\u019d\u0003@ \u0000\u019c\u019b"+
		"\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u01a3"+
		"\u0001\u0000\u0000\u0000\u019e\u01a0\u0007\u0002\u0000\u0000\u019f\u01a1"+
		"\u00057\u0000\u0000\u01a0\u019f\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2\u01a4\u0003"+
		"\"\u0011\u0000\u01a3\u019e\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000"+
		"\u0000\u0000\u01a4#\u0001\u0000\u0000\u0000\u01a5\u01a6\u0005\u0007\u0000"+
		"\u0000\u01a6\u01a7\u0005\b\u0000\u0000\u01a7\u01ac\u0003d2\u0000\u01a8"+
		"\u01a9\u0005\u0082\u0000\u0000\u01a9\u01aa\u0003Z-\u0000\u01aa\u01ab\u0005"+
		"\u0083\u0000\u0000\u01ab\u01ad\u0001\u0000\u0000\u0000\u01ac\u01a8\u0001"+
		"\u0000\u0000\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01bd\u0001"+
		"\u0000\u0000\u0000\u01ae\u01af\u0005\t\u0000\u0000\u01af\u01b0\u0005\u0082"+
		"\u0000\u0000\u01b0\u01b1\u0003`0\u0000\u01b1\u01b9\u0005\u0083\u0000\u0000"+
		"\u01b2\u01b3\u0005\u0080\u0000\u0000\u01b3\u01b4\u0005\u0082\u0000\u0000"+
		"\u01b4\u01b5\u0003`0\u0000\u01b5\u01b6\u0005\u0083\u0000\u0000\u01b6\u01b8"+
		"\u0001\u0000\u0000\u0000\u01b7\u01b2\u0001\u0000\u0000\u0000\u01b8\u01bb"+
		"\u0001\u0000\u0000\u0000\u01b9\u01b7\u0001\u0000\u0000\u0000\u01b9\u01ba"+
		"\u0001\u0000\u0000\u0000\u01ba\u01be\u0001\u0000\u0000\u0000\u01bb\u01b9"+
		"\u0001\u0000\u0000\u0000\u01bc\u01be\u0003\"\u0011\u0000\u01bd\u01ae\u0001"+
		"\u0000\u0000\u0000\u01bd\u01bc\u0001\u0000\u0000\u0000\u01be\u01c1\u0001"+
		"\u0000\u0000\u0000\u01bf\u01c0\u0005?\u0000\u0000\u01c0\u01c2\u0003*\u0015"+
		"\u0000\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000"+
		"\u0000\u01c2%\u0001\u0000\u0000\u0000\u01c3\u01c5\u0005\n\u0000\u0000"+
		"\u01c4\u01c6\u0005a\u0000\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000\u01c5"+
		"\u01c6\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7"+
		"\u01cc\u0003d2\u0000\u01c8\u01ca\u0005-\u0000\u0000\u01c9\u01c8\u0001"+
		"\u0000\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001"+
		"\u0000\u0000\u0000\u01cb\u01cd\u0003r9\u0000\u01cc\u01c9\u0001\u0000\u0000"+
		"\u0000\u01cc\u01cd\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000"+
		"\u0000\u01ce\u01cf\u0005\u000b\u0000\u0000\u01cf\u01d4\u0003X,\u0000\u01d0"+
		"\u01d1\u0005\u0080\u0000\u0000\u01d1\u01d3\u0003X,\u0000\u01d2\u01d0\u0001"+
		"\u0000\u0000\u0000\u01d3\u01d6\u0001\u0000\u0000\u0000\u01d4\u01d2\u0001"+
		"\u0000\u0000\u0000\u01d4\u01d5\u0001\u0000\u0000\u0000\u01d5\u01e0\u0001"+
		"\u0000\u0000\u0000\u01d6\u01d4\u0001\u0000\u0000\u0000\u01d7\u01d8\u0005"+
		"\u0002\u0000\u0000\u01d8\u01dd\u0003.\u0017\u0000\u01d9\u01da\u0005\u0080"+
		"\u0000\u0000\u01da\u01dc\u0003.\u0017\u0000\u01db\u01d9\u0001\u0000\u0000"+
		"\u0000\u01dc\u01df\u0001\u0000\u0000\u0000\u01dd\u01db\u0001\u0000\u0000"+
		"\u0000\u01dd\u01de\u0001\u0000\u0000\u0000\u01de\u01e1\u0001\u0000\u0000"+
		"\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01e0\u01d7\u0001\u0000\u0000"+
		"\u0000\u01e0\u01e1\u0001\u0000\u0000\u0000\u01e1\u01e3\u0001\u0000\u0000"+
		"\u0000\u01e2\u01e4\u00034\u001a\u0000\u01e3\u01e2\u0001\u0000\u0000\u0000"+
		"\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4\u01e7\u0001\u0000\u0000\u0000"+
		"\u01e5\u01e6\u0005?\u0000\u0000\u01e6\u01e8\u0003*\u0015\u0000\u01e7\u01e5"+
		"\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\'\u0001"+
		"\u0000\u0000\u0000\u01e9\u01ea\u0005\f\u0000\u0000\u01ea\u01ec\u0005\u0002"+
		"\u0000\u0000\u01eb\u01ed\u0005a\u0000\u0000\u01ec\u01eb\u0001\u0000\u0000"+
		"\u0000\u01ec\u01ed\u0001\u0000\u0000\u0000\u01ed\u01ee\u0001\u0000\u0000"+
		"\u0000\u01ee\u01f3\u0003d2\u0000\u01ef\u01f1\u0005-\u0000\u0000\u01f0"+
		"\u01ef\u0001\u0000\u0000\u0000\u01f0\u01f1\u0001\u0000\u0000\u0000\u01f1"+
		"\u01f2\u0001\u0000\u0000\u0000\u01f2\u01f4\u0003r9\u0000\u01f3\u01f0\u0001"+
		"\u0000\u0000\u0000\u01f3\u01f4\u0001\u0000\u0000\u0000\u01f4\u01fe\u0001"+
		"\u0000\u0000\u0000\u01f5\u01f6\u0005W\u0000\u0000\u01f6\u01fb\u0003.\u0017"+
		"\u0000\u01f7\u01f8\u0005\u0080\u0000\u0000\u01f8\u01fa\u0003.\u0017\u0000"+
		"\u01f9\u01f7\u0001\u0000\u0000\u0000\u01fa\u01fd\u0001\u0000\u0000\u0000"+
		"\u01fb\u01f9\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000"+
		"\u01fc\u01ff\u0001\u0000\u0000\u0000\u01fd\u01fb\u0001\u0000\u0000\u0000"+
		"\u01fe\u01f5\u0001\u0000\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000"+
		"\u01ff\u0201\u0001\u0000\u0000\u0000\u0200\u0202\u00034\u001a\u0000\u0201"+
		"\u0200\u0001\u0000\u0000\u0000\u0201\u0202\u0001\u0000\u0000\u0000\u0202"+
		"\u0205\u0001\u0000\u0000\u0000\u0203\u0204\u0005?\u0000\u0000\u0204\u0206"+
		"\u0003*\u0015\u0000\u0205\u0203\u0001\u0000\u0000\u0000\u0205\u0206\u0001"+
		"\u0000\u0000\u0000\u0206)\u0001\u0000\u0000\u0000\u0207\u0211\u0005\u007f"+
		"\u0000\u0000\u0208\u020d\u0003,\u0016\u0000\u0209\u020a\u0005\u0080\u0000"+
		"\u0000\u020a\u020c\u0003,\u0016\u0000\u020b\u0209\u0001\u0000\u0000\u0000"+
		"\u020c\u020f\u0001\u0000\u0000\u0000\u020d\u020b\u0001\u0000\u0000\u0000"+
		"\u020d\u020e\u0001\u0000\u0000\u0000\u020e\u0211\u0001\u0000\u0000\u0000"+
		"\u020f\u020d\u0001\u0000\u0000\u0000\u0210\u0207\u0001\u0000\u0000\u0000"+
		"\u0210\u0208\u0001\u0000\u0000\u0000\u0211+\u0001\u0000\u0000\u0000\u0212"+
		"\u0217\u0003B!\u0000\u0213\u0215\u0005-\u0000\u0000\u0214\u0213\u0001"+
		"\u0000\u0000\u0000\u0214\u0215\u0001\u0000\u0000\u0000\u0215\u0216\u0001"+
		"\u0000\u0000\u0000\u0216\u0218\u0003r9\u0000\u0217\u0214\u0001\u0000\u0000"+
		"\u0000\u0217\u0218\u0001\u0000\u0000\u0000\u0218-\u0001\u0000\u0000\u0000"+
		"\u0219\u021e\u0003d2\u0000\u021a\u021c\u0005-\u0000\u0000\u021b\u021a"+
		"\u0001\u0000\u0000\u0000\u021b\u021c\u0001\u0000\u0000\u0000\u021c\u021d"+
		"\u0001\u0000\u0000\u0000\u021d\u021f\u0003r9\u0000\u021e\u021b\u0001\u0000"+
		"\u0000\u0000\u021e\u021f\u0001\u0000\u0000\u0000\u021f\u022a\u0001\u0000"+
		"\u0000\u0000\u0220\u0221\u0005\u0082\u0000\u0000\u0221\u0222\u0003\"\u0011"+
		"\u0000\u0222\u0227\u0005\u0083\u0000\u0000\u0223\u0225\u0005-\u0000\u0000"+
		"\u0224\u0223\u0001\u0000\u0000\u0000\u0224\u0225\u0001\u0000\u0000\u0000"+
		"\u0225\u0226\u0001\u0000\u0000\u0000\u0226\u0228\u0003r9\u0000\u0227\u0224"+
		"\u0001\u0000\u0000\u0000\u0227\u0228\u0001\u0000\u0000\u0000\u0228\u022a"+
		"\u0001\u0000\u0000\u0000\u0229\u0219\u0001\u0000\u0000\u0000\u0229\u0220"+
		"\u0001\u0000\u0000\u0000\u022a/\u0001\u0000\u0000\u0000\u022b\u022d\u0003"+
		"2\u0019\u0000\u022c\u022b\u0001\u0000\u0000\u0000\u022c\u022d\u0001\u0000"+
		"\u0000\u0000\u022d\u022e\u0001\u0000\u0000\u0000\u022e\u022f\u0005%\u0000"+
		"\u0000\u022f\u0237\u0003.\u0017\u0000\u0230\u0231\u0005,\u0000\u0000\u0231"+
		"\u0238\u0003B!\u0000\u0232\u0233\u0005W\u0000\u0000\u0233\u0234\u0005"+
		"\u0082\u0000\u0000\u0234\u0235\u0003Z-\u0000\u0235\u0236\u0005\u0083\u0000"+
		"\u0000\u0236\u0238\u0001\u0000\u0000\u0000\u0237\u0230\u0001\u0000\u0000"+
		"\u0000\u0237\u0232\u0001\u0000\u0000\u0000\u0237\u0238\u0001\u0000\u0000"+
		"\u0000\u02381\u0001\u0000\u0000\u0000\u0239\u0248\u0005&\u0000\u0000\u023a"+
		"\u023c\u0005\'\u0000\u0000\u023b\u023d\u0005)\u0000\u0000\u023c\u023b"+
		"\u0001\u0000\u0000\u0000\u023c\u023d\u0001\u0000\u0000\u0000\u023d\u0248"+
		"\u0001\u0000\u0000\u0000\u023e\u0240\u0005(\u0000\u0000\u023f\u0241\u0005"+
		")\u0000\u0000\u0240\u023f\u0001\u0000\u0000\u0000\u0240\u0241\u0001\u0000"+
		"\u0000\u0000\u0241\u0248\u0001\u0000\u0000\u0000\u0242\u0244\u0005+\u0000"+
		"\u0000\u0243\u0245\u0005)\u0000\u0000\u0244\u0243\u0001\u0000\u0000\u0000"+
		"\u0244\u0245\u0001\u0000\u0000\u0000\u0245\u0248\u0001\u0000\u0000\u0000"+
		"\u0246\u0248\u0005*\u0000\u0000\u0247\u0239\u0001\u0000\u0000\u0000\u0247"+
		"\u023a\u0001\u0000\u0000\u0000\u0247\u023e\u0001\u0000\u0000\u0000\u0247"+
		"\u0242\u0001\u0000\u0000\u0000\u0247\u0246\u0001\u0000\u0000\u0000\u0248"+
		"3\u0001\u0000\u0000\u0000\u0249\u024a\u0005\u0003\u0000\u0000\u024a\u024b"+
		"\u0003B!\u0000\u024b5\u0001\u0000\u0000\u0000\u024c\u024d\u0005!\u0000"+
		"\u0000\u024d\u024e\u0005 \u0000\u0000\u024e\u024f\u0003`0\u0000\u024f"+
		"7\u0001\u0000\u0000\u0000\u0250\u0251\u0005\"\u0000\u0000\u0251\u0252"+
		"\u0003B!\u0000\u02529\u0001\u0000\u0000\u0000\u0253\u0254\u0005\u001f"+
		"\u0000\u0000\u0254\u0255\u0005 \u0000\u0000\u0255\u025a\u0003<\u001e\u0000"+
		"\u0256\u0257\u0005\u0080\u0000\u0000\u0257\u0259\u0003<\u001e\u0000\u0258"+
		"\u0256\u0001\u0000\u0000\u0000\u0259\u025c\u0001\u0000\u0000\u0000\u025a"+
		"\u0258\u0001\u0000\u0000\u0000\u025a\u025b\u0001\u0000\u0000\u0000\u025b"+
		";\u0001\u0000\u0000\u0000\u025c\u025a\u0001\u0000\u0000\u0000\u025d\u025f"+
		"\u0003B!\u0000\u025e\u0260\u0007\u0003\u0000\u0000\u025f\u025e\u0001\u0000"+
		"\u0000\u0000\u025f\u0260\u0001\u0000\u0000\u0000\u0260\u0263\u0001\u0000"+
		"\u0000\u0000\u0261\u0262\u0005F\u0000\u0000\u0262\u0264\u0007\u0004\u0000"+
		"\u0000\u0263\u0261\u0001\u0000\u0000\u0000\u0263\u0264\u0001\u0000\u0000"+
		"\u0000\u0264=\u0001\u0000\u0000\u0000\u0265\u0268\u0005#\u0000\u0000\u0266"+
		"\u0269\u0003B!\u0000\u0267\u0269\u00057\u0000\u0000\u0268\u0266\u0001"+
		"\u0000\u0000\u0000\u0268\u0267\u0001\u0000\u0000\u0000\u0269?\u0001\u0000"+
		"\u0000\u0000\u026a\u026b\u0005$\u0000\u0000\u026b\u026c\u0003B!\u0000"+
		"\u026cA\u0001\u0000\u0000\u0000\u026d\u026e\u0006!\uffff\uffff\u0000\u026e"+
		"\u026f\u0005\u0082\u0000\u0000\u026f\u0270\u0003B!\u0000\u0270\u0271\u0005"+
		"\u0083\u0000\u0000\u0271\u0281\u0001\u0000\u0000\u0000\u0272\u0273\u0005"+
		"\u0006\u0000\u0000\u0273\u0281\u0003B!\u0017\u0274\u0281\u0003H$\u0000"+
		"\u0275\u0281\u0003F#\u0000\u0276\u0277\u0005N\u0000\u0000\u0277\u0278"+
		"\u0005\u0082\u0000\u0000\u0278\u0279\u0003B!\u0000\u0279\u027a\u0005-"+
		"\u0000\u0000\u027a\u027b\u0003N\'\u0000\u027b\u027c\u0005\u0083\u0000"+
		"\u0000\u027c\u0281\u0001\u0000\u0000\u0000\u027d\u0281\u0003x<\u0000\u027e"+
		"\u0281\u0003b1\u0000\u027f\u0281\u0005\u007f\u0000\u0000\u0280\u026d\u0001"+
		"\u0000\u0000\u0000\u0280\u0272\u0001\u0000\u0000\u0000\u0280\u0274\u0001"+
		"\u0000\u0000\u0000\u0280\u0275\u0001\u0000\u0000\u0000\u0280\u0276\u0001"+
		"\u0000\u0000\u0000\u0280\u027d\u0001\u0000\u0000\u0000\u0280\u027e\u0001"+
		"\u0000\u0000\u0000\u0280\u027f\u0001\u0000\u0000\u0000\u0281\u02c1\u0001"+
		"\u0000\u0000\u0000\u0282\u0283\n\u0016\u0000\u0000\u0283\u0284\u0005\u0004"+
		"\u0000\u0000\u0284\u02c0\u0003B!\u0017\u0285\u0286\n\u0015\u0000\u0000"+
		"\u0286\u0287\u0005\u0005\u0000\u0000\u0287\u02c0\u0003B!\u0016\u0288\u0289"+
		"\n\u0014\u0000\u0000\u0289\u028a\u0003D\"\u0000\u028a\u028b\u0003B!\u0015"+
		"\u028b\u02c0\u0001\u0000\u0000\u0000\u028c\u028d\n\u0012\u0000\u0000\u028d"+
		"\u028e\u0005/\u0000\u0000\u028e\u028f\u0003B!\u0000\u028f\u0290\u0005"+
		"\u0004\u0000\u0000\u0290\u0291\u0003B!\u0013\u0291\u02c0\u0001\u0000\u0000"+
		"\u0000\u0292\u0293\n\u0011\u0000\u0000\u0293\u0294\u00050\u0000\u0000"+
		"\u0294\u02c0\u0003B!\u0012\u0295\u0296\n\u0010\u0000\u0000\u0296\u0297"+
		"\u00051\u0000\u0000\u0297\u02c0\u0003B!\u0011\u0298\u0299\n\r\u0000\u0000"+
		"\u0299\u029a\u0005\u0091\u0000\u0000\u029a\u02c0\u0003B!\u000e\u029b\u029c"+
		"\n\u000b\u0000\u0000\u029c\u029d\u0005\u0093\u0000\u0000\u029d\u02c0\u0003"+
		"B!\f\u029e\u029f\n\n\u0000\u0000\u029f\u02a0\u0005\u0094\u0000\u0000\u02a0"+
		"\u02c0\u0003B!\u000b\u02a1\u02a2\n\u0006\u0000\u0000\u02a2\u02a3\u0007"+
		"\u0005\u0000\u0000\u02a3\u02c0\u0003B!\u0007\u02a4\u02a5\n\u0005\u0000"+
		"\u0000\u02a5\u02a6\u0007\u0006\u0000\u0000\u02a6\u02c0\u0003B!\u0006\u02a7"+
		"\u02a8\n\u0013\u0000\u0000\u02a8\u02a9\u0005.\u0000\u0000\u02a9\u02ac"+
		"\u0005\u0082\u0000\u0000\u02aa\u02ad\u0003`0\u0000\u02ab\u02ad\u0003\""+
		"\u0011\u0000\u02ac\u02aa\u0001\u0000\u0000\u0000\u02ac\u02ab\u0001\u0000"+
		"\u0000\u0000\u02ad\u02ae\u0001\u0000\u0000\u0000\u02ae\u02af\u0005\u0083"+
		"\u0000\u0000\u02af\u02c0\u0001\u0000\u0000\u0000\u02b0\u02b1\n\u000f\u0000"+
		"\u0000\u02b1\u02b2\u00052\u0000\u0000\u02b2\u02c0\u0005\u0019\u0000\u0000"+
		"\u02b3\u02b4\n\u000e\u0000\u0000\u02b4\u02b5\u00052\u0000\u0000\u02b5"+
		"\u02b6\u0005\u0006\u0000\u0000\u02b6\u02c0\u0005\u0019\u0000\u0000\u02b7"+
		"\u02b8\n\f\u0000\u0000\u02b8\u02b9\u0005\u0092\u0000\u0000\u02b9\u02c0"+
		"\u0003N\'\u0000\u02ba\u02bb\n\t\u0000\u0000\u02bb\u02bc\u0005\u0084\u0000"+
		"\u0000\u02bc\u02bd\u0003B!\u0000\u02bd\u02be\u0005\u0085\u0000\u0000\u02be"+
		"\u02c0\u0001\u0000\u0000\u0000\u02bf\u0282\u0001\u0000\u0000\u0000\u02bf"+
		"\u0285\u0001\u0000\u0000\u0000\u02bf\u0288\u0001\u0000\u0000\u0000\u02bf"+
		"\u028c\u0001\u0000\u0000\u0000\u02bf\u0292\u0001\u0000\u0000\u0000\u02bf"+
		"\u0295\u0001\u0000\u0000\u0000\u02bf\u0298\u0001\u0000\u0000\u0000\u02bf"+
		"\u029b\u0001\u0000\u0000\u0000\u02bf\u029e\u0001\u0000\u0000\u0000\u02bf"+
		"\u02a1\u0001\u0000\u0000\u0000\u02bf\u02a4\u0001\u0000\u0000\u0000\u02bf"+
		"\u02a7\u0001\u0000\u0000\u0000\u02bf\u02b0\u0001\u0000\u0000\u0000\u02bf"+
		"\u02b3\u0001\u0000\u0000\u0000\u02bf\u02b7\u0001\u0000\u0000\u0000\u02bf"+
		"\u02ba\u0001\u0000\u0000\u0000\u02c0\u02c3\u0001\u0000\u0000\u0000\u02c1"+
		"\u02bf\u0001\u0000\u0000\u0000\u02c1\u02c2\u0001\u0000\u0000\u0000\u02c2"+
		"C\u0001\u0000\u0000\u0000\u02c3\u02c1\u0001\u0000\u0000\u0000\u02c4\u02c5"+
		"\u0007\u0007\u0000\u0000\u02c5E\u0001\u0000\u0000\u0000\u02c6\u02c8\u0005"+
		"8\u0000\u0000\u02c7\u02c9\u0003B!\u0000\u02c8\u02c7\u0001\u0000\u0000"+
		"\u0000\u02c8\u02c9\u0001\u0000\u0000\u0000\u02c9\u02cf\u0001\u0000\u0000"+
		"\u0000\u02ca\u02cb\u00059\u0000\u0000\u02cb\u02cc\u0003B!\u0000\u02cc"+
		"\u02cd\u0005:\u0000\u0000\u02cd\u02ce\u0003B!\u0000\u02ce\u02d0\u0001"+
		"\u0000\u0000\u0000\u02cf\u02ca\u0001\u0000\u0000\u0000\u02d0\u02d1\u0001"+
		"\u0000\u0000\u0000\u02d1\u02cf\u0001\u0000\u0000\u0000\u02d1\u02d2\u0001"+
		"\u0000\u0000\u0000\u02d2\u02d5\u0001\u0000\u0000\u0000\u02d3\u02d4\u0005"+
		";\u0000\u0000\u02d4\u02d6\u0003B!\u0000\u02d5\u02d3\u0001\u0000\u0000"+
		"\u0000\u02d5\u02d6\u0001\u0000\u0000\u0000\u02d6\u02d7\u0001\u0000\u0000"+
		"\u0000\u02d7\u02d8\u0005<\u0000\u0000\u02d8G\u0001\u0000\u0000\u0000\u02d9"+
		"\u02da\u0003J%\u0000\u02da\u02e0\u0005\u0082\u0000\u0000\u02db\u02dd\u0005"+
		"3\u0000\u0000\u02dc\u02db\u0001\u0000\u0000\u0000\u02dc\u02dd\u0001\u0000"+
		"\u0000\u0000\u02dd\u02de\u0001\u0000\u0000\u0000\u02de\u02e1\u0003`0\u0000"+
		"\u02df\u02e1\u0005\u007f\u0000\u0000\u02e0\u02dc\u0001\u0000\u0000\u0000"+
		"\u02e0\u02df\u0001\u0000\u0000\u0000\u02e0\u02e1\u0001\u0000\u0000\u0000"+
		"\u02e1\u02e2\u0001\u0000\u0000\u0000\u02e2\u02e3\u0005\u0083\u0000\u0000"+
		"\u02e3I\u0001\u0000\u0000\u0000\u02e4\u02e5\u0007\b\u0000\u0000\u02e5"+
		"K\u0001\u0000\u0000\u0000\u02e6\u02e7\u0003j5\u0000\u02e7\u02eb\u0003"+
		"N\'\u0000\u02e8\u02ea\u0003P(\u0000\u02e9\u02e8\u0001\u0000\u0000\u0000"+
		"\u02ea\u02ed\u0001\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000\u0000\u0000"+
		"\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ecM\u0001\u0000\u0000\u0000\u02ed"+
		"\u02eb\u0001\u0000\u0000\u0000\u02ee\u0335\u0005b\u0000\u0000\u02ef\u0335"+
		"\u0005c\u0000\u0000\u02f0\u0335\u0005d\u0000\u0000\u02f1\u0335\u0005e"+
		"\u0000\u0000\u02f2\u0335\u0005R\u0000\u0000\u02f3\u0335\u0005S\u0000\u0000"+
		"\u02f4\u0335\u0005T\u0000\u0000\u02f5\u0335\u0005f\u0000\u0000\u02f6\u0335"+
		"\u0005g\u0000\u0000\u02f7\u02f8\u0005h\u0000\u0000\u02f8\u0335\u0005i"+
		"\u0000\u0000\u02f9\u0301\u0005j\u0000\u0000\u02fa\u02fb\u0005\u0082\u0000"+
		"\u0000\u02fb\u02fe\u0005\u0095\u0000\u0000\u02fc\u02fd\u0005\u0080\u0000"+
		"\u0000\u02fd\u02ff\u0005\u0095\u0000\u0000\u02fe\u02fc\u0001\u0000\u0000"+
		"\u0000\u02fe\u02ff\u0001\u0000\u0000\u0000\u02ff\u0300\u0001\u0000\u0000"+
		"\u0000\u0300\u0302\u0005\u0083\u0000\u0000\u0301\u02fa\u0001\u0000\u0000"+
		"\u0000\u0301\u0302\u0001\u0000\u0000\u0000\u0302\u0335\u0001\u0000\u0000"+
		"\u0000\u0303\u030b\u0005k\u0000\u0000\u0304\u0305\u0005\u0082\u0000\u0000"+
		"\u0305\u0308\u0005\u0095\u0000\u0000\u0306\u0307\u0005\u0080\u0000\u0000"+
		"\u0307\u0309\u0005\u0095\u0000\u0000\u0308\u0306\u0001\u0000\u0000\u0000"+
		"\u0308\u0309\u0001\u0000\u0000\u0000\u0309\u030a\u0001\u0000\u0000\u0000"+
		"\u030a\u030c\u0005\u0083\u0000\u0000\u030b\u0304\u0001\u0000\u0000\u0000"+
		"\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u0335\u0001\u0000\u0000\u0000"+
		"\u030d\u0311\u0005l\u0000\u0000\u030e\u030f\u0005\u0082\u0000\u0000\u030f"+
		"\u0310\u0005\u0095\u0000\u0000\u0310\u0312\u0005\u0083\u0000\u0000\u0311"+
		"\u030e\u0001\u0000\u0000\u0000\u0311\u0312\u0001\u0000\u0000\u0000\u0312"+
		"\u0335\u0001\u0000\u0000\u0000\u0313\u0317\u0005m\u0000\u0000\u0314\u0315"+
		"\u0005\u0082\u0000\u0000\u0315\u0316\u0005\u0095\u0000\u0000\u0316\u0318"+
		"\u0005\u0083\u0000\u0000\u0317\u0314\u0001\u0000\u0000\u0000\u0317\u0318"+
		"\u0001\u0000\u0000\u0000\u0318\u0335\u0001\u0000\u0000\u0000\u0319\u0335"+
		"\u0005n\u0000\u0000\u031a\u0335\u0005o\u0000\u0000\u031b\u0335\u0005p"+
		"\u0000\u0000\u031c\u0335\u0005q\u0000\u0000\u031d\u0335\u0005r\u0000\u0000"+
		"\u031e\u0335\u0005s\u0000\u0000\u031f\u0335\u0005t\u0000\u0000\u0320\u0335"+
		"\u0005u\u0000\u0000\u0321\u0335\u0005v\u0000\u0000\u0322\u0335\u0005w"+
		"\u0000\u0000\u0323\u0335\u0005y\u0000\u0000\u0324\u0335\u0005z\u0000\u0000"+
		"\u0325\u0335\u0005{\u0000\u0000\u0326\u0335\u0005|\u0000\u0000\u0327\u0335"+
		"\u0005}\u0000\u0000\u0328\u0335\u0005~\u0000\u0000\u0329\u0335\u0005x"+
		"\u0000\u0000\u032a\u0332\u0005\u009a\u0000\u0000\u032b\u032c\u0005\u0082"+
		"\u0000\u0000\u032c\u032f\u0005\u0095\u0000\u0000\u032d\u032e\u0005\u0080"+
		"\u0000\u0000\u032e\u0330\u0005\u0095\u0000\u0000\u032f\u032d\u0001\u0000"+
		"\u0000\u0000\u032f\u0330\u0001\u0000\u0000\u0000\u0330\u0331\u0001\u0000"+
		"\u0000\u0000\u0331\u0333\u0005\u0083\u0000\u0000\u0332\u032b\u0001\u0000"+
		"\u0000\u0000\u0332\u0333\u0001\u0000\u0000\u0000\u0333\u0335\u0001\u0000"+
		"\u0000\u0000\u0334\u02ee\u0001\u0000\u0000\u0000\u0334\u02ef\u0001\u0000"+
		"\u0000\u0000\u0334\u02f0\u0001\u0000\u0000\u0000\u0334\u02f1\u0001\u0000"+
		"\u0000\u0000\u0334\u02f2\u0001\u0000\u0000\u0000\u0334\u02f3\u0001\u0000"+
		"\u0000\u0000\u0334\u02f4\u0001\u0000\u0000\u0000\u0334\u02f5\u0001\u0000"+
		"\u0000\u0000\u0334\u02f6\u0001\u0000\u0000\u0000\u0334\u02f7\u0001\u0000"+
		"\u0000\u0000\u0334\u02f9\u0001\u0000\u0000\u0000\u0334\u0303\u0001\u0000"+
		"\u0000\u0000\u0334\u030d\u0001\u0000\u0000\u0000\u0334\u0313\u0001\u0000"+
		"\u0000\u0000\u0334\u0319\u0001\u0000\u0000\u0000\u0334\u031a\u0001\u0000"+
		"\u0000\u0000\u0334\u031b\u0001\u0000\u0000\u0000\u0334\u031c\u0001\u0000"+
		"\u0000\u0000\u0334\u031d\u0001\u0000\u0000\u0000\u0334\u031e\u0001\u0000"+
		"\u0000\u0000\u0334\u031f\u0001\u0000\u0000\u0000\u0334\u0320\u0001\u0000"+
		"\u0000\u0000\u0334\u0321\u0001\u0000\u0000\u0000\u0334\u0322\u0001\u0000"+
		"\u0000\u0000\u0334\u0323\u0001\u0000\u0000\u0000\u0334\u0324\u0001\u0000"+
		"\u0000\u0000\u0334\u0325\u0001\u0000\u0000\u0000\u0334\u0326\u0001\u0000"+
		"\u0000\u0000\u0334\u0327\u0001\u0000\u0000\u0000\u0334\u0328\u0001\u0000"+
		"\u0000\u0000\u0334\u0329\u0001\u0000\u0000\u0000\u0334\u032a\u0001\u0000"+
		"\u0000\u0000\u0335O\u0001\u0000\u0000\u0000\u0336\u0337\u0005D\u0000\u0000"+
		"\u0337\u0339\u0003n7\u0000\u0338\u0336\u0001\u0000\u0000\u0000\u0338\u0339"+
		"\u0001\u0000\u0000\u0000\u0339\u0350\u0001\u0000\u0000\u0000\u033a\u033c"+
		"\u0005\u0006\u0000\u0000\u033b\u033a\u0001\u0000\u0000\u0000\u033b\u033c"+
		"\u0001\u0000\u0000\u0000\u033c\u033d\u0001\u0000\u0000\u0000\u033d\u0351"+
		"\u0005\u0019\u0000\u0000\u033e\u033f\u0005\u001a\u0000\u0000\u033f\u0351"+
		"\u0003B!\u0000\u0340\u0341\u0005\u0014\u0000\u0000\u0341\u0351\u0005\u0015"+
		"\u0000\u0000\u0342\u0351\u0005\u0018\u0000\u0000\u0343\u0344\u0005E\u0000"+
		"\u0000\u0344\u0345\u0005\u0082\u0000\u0000\u0345\u0346\u0003B!\u0000\u0346"+
		"\u0347\u0005\u0083\u0000\u0000\u0347\u0351\u0001\u0000\u0000\u0000\u0348"+
		"\u0349\u0005\u0017\u0000\u0000\u0349\u034e\u0003d2\u0000\u034a\u034b\u0005"+
		"\u0082\u0000\u0000\u034b\u034c\u0003j5\u0000\u034c\u034d\u0005\u0083\u0000"+
		"\u0000\u034d\u034f\u0001\u0000\u0000\u0000\u034e\u034a\u0001\u0000\u0000"+
		"\u0000\u034e\u034f\u0001\u0000\u0000\u0000\u034f\u0351\u0001\u0000\u0000"+
		"\u0000\u0350\u033b\u0001\u0000\u0000\u0000\u0350\u033e\u0001\u0000\u0000"+
		"\u0000\u0350\u0340\u0001\u0000\u0000\u0000\u0350\u0342\u0001\u0000\u0000"+
		"\u0000\u0350\u0343\u0001\u0000\u0000\u0000\u0350\u0348\u0001\u0000\u0000"+
		"\u0000\u0351Q\u0001\u0000\u0000\u0000\u0352\u0353\u0005D\u0000\u0000\u0353"+
		"\u0355\u0003n7\u0000\u0354\u0352\u0001\u0000\u0000\u0000\u0354\u0355\u0001"+
		"\u0000\u0000\u0000\u0355\u0371\u0001\u0000\u0000\u0000\u0356\u0357\u0005"+
		"\u0014\u0000\u0000\u0357\u0358\u0005\u0015\u0000\u0000\u0358\u0359\u0005"+
		"\u0082\u0000\u0000\u0359\u035a\u0003Z-\u0000\u035a\u035b\u0005\u0083\u0000"+
		"\u0000\u035b\u0372\u0001\u0000\u0000\u0000\u035c\u035d\u0005\u0018\u0000"+
		"\u0000\u035d\u035e\u0005\u0082\u0000\u0000\u035e\u035f\u0003Z-\u0000\u035f"+
		"\u0360\u0005\u0083\u0000\u0000\u0360\u0372\u0001\u0000\u0000\u0000\u0361"+
		"\u0362\u0005\u0016\u0000\u0000\u0362\u0363\u0005\u0015\u0000\u0000\u0363"+
		"\u0364\u0005\u0082\u0000\u0000\u0364\u0365\u0003Z-\u0000\u0365\u0366\u0005"+
		"\u0083\u0000\u0000\u0366\u0367\u0005\u0017\u0000\u0000\u0367\u0368\u0003"+
		"d2\u0000\u0368\u0369\u0005\u0082\u0000\u0000\u0369\u036a\u0003Z-\u0000"+
		"\u036a\u036b\u0005\u0083\u0000\u0000\u036b\u0372\u0001\u0000\u0000\u0000"+
		"\u036c\u036d\u0005E\u0000\u0000\u036d\u036e\u0005\u0082\u0000\u0000\u036e"+
		"\u036f\u0003B!\u0000\u036f\u0370\u0005\u0083\u0000\u0000\u0370\u0372\u0001"+
		"\u0000\u0000\u0000\u0371\u0356\u0001\u0000\u0000\u0000\u0371\u035c\u0001"+
		"\u0000\u0000\u0000\u0371\u0361\u0001\u0000\u0000\u0000\u0371\u036c\u0001"+
		"\u0000\u0000\u0000\u0372S\u0001\u0000\u0000\u0000\u0373\u0374\u0007\t"+
		"\u0000\u0000\u0374U\u0001\u0000\u0000\u0000\u0375\u0376\u0005\u009a\u0000"+
		"\u0000\u0376W\u0001\u0000\u0000\u0000\u0377\u0378\u0003b1\u0000\u0378"+
		"\u0379\u0005\u0087\u0000\u0000\u0379\u037a\u0003B!\u0000\u037aY\u0001"+
		"\u0000\u0000\u0000\u037b\u0380\u0003j5\u0000\u037c\u037d\u0005\u0080\u0000"+
		"\u0000\u037d\u037f\u0003j5\u0000\u037e\u037c\u0001\u0000\u0000\u0000\u037f"+
		"\u0382\u0001\u0000\u0000\u0000\u0380\u037e\u0001\u0000\u0000\u0000\u0380"+
		"\u0381\u0001\u0000\u0000\u0000\u0381[\u0001\u0000\u0000\u0000\u0382\u0380"+
		"\u0001\u0000\u0000\u0000\u0383\u0388\u0003^/\u0000\u0384\u0385\u0005\u0080"+
		"\u0000\u0000\u0385\u0387\u0003^/\u0000\u0386\u0384\u0001\u0000\u0000\u0000"+
		"\u0387\u038a\u0001\u0000\u0000\u0000\u0388\u0386\u0001\u0000\u0000\u0000"+
		"\u0388\u0389\u0001\u0000\u0000\u0000\u0389]\u0001\u0000\u0000\u0000\u038a"+
		"\u0388\u0001\u0000\u0000\u0000\u038b\u038d\u0003j5\u0000\u038c\u038e\u0007"+
		"\u0003\u0000\u0000\u038d\u038c\u0001\u0000\u0000\u0000\u038d\u038e\u0001"+
		"\u0000\u0000\u0000\u038e\u0391\u0001\u0000\u0000\u0000\u038f\u0390\u0005"+
		"F\u0000\u0000\u0390\u0392\u0007\u0004\u0000\u0000\u0391\u038f\u0001\u0000"+
		"\u0000\u0000\u0391\u0392\u0001\u0000\u0000\u0000\u0392_\u0001\u0000\u0000"+
		"\u0000\u0393\u0398\u0003B!\u0000\u0394\u0395\u0005\u0080\u0000\u0000\u0395"+
		"\u0397\u0003B!\u0000\u0396\u0394\u0001\u0000\u0000\u0000\u0397\u039a\u0001"+
		"\u0000\u0000\u0000\u0398\u0396\u0001\u0000\u0000\u0000\u0398\u0399\u0001"+
		"\u0000\u0000\u0000\u0399a\u0001\u0000\u0000\u0000\u039a\u0398\u0001\u0000"+
		"\u0000\u0000\u039b\u039c\u0003t:\u0000\u039c\u039d\u0005\u0081\u0000\u0000"+
		"\u039d\u039e\u0003t:\u0000\u039e\u039f\u0005\u0081\u0000\u0000\u039f\u03a0"+
		"\u0003t:\u0000\u03a0\u03ab\u0001\u0000\u0000\u0000\u03a1\u03a2\u0003t"+
		":\u0000\u03a2\u03a3\u0005\u0081\u0000\u0000\u03a3\u03a4\u0003t:\u0000"+
		"\u03a4\u03ab\u0001\u0000\u0000\u0000\u03a5\u03a6\u0003t:\u0000\u03a6\u03a7"+
		"\u0005\u0081\u0000\u0000\u03a7\u03a8\u0005\u007f\u0000\u0000\u03a8\u03ab"+
		"\u0001\u0000\u0000\u0000\u03a9\u03ab\u0003t:\u0000\u03aa\u039b\u0001\u0000"+
		"\u0000\u0000\u03aa\u03a1\u0001\u0000\u0000\u0000\u03aa\u03a5\u0001\u0000"+
		"\u0000\u0000\u03aa\u03a9\u0001\u0000\u0000\u0000\u03abc\u0001\u0000\u0000"+
		"\u0000\u03ac\u03ad\u0003h4\u0000\u03ad\u03ae\u0005\u0081\u0000\u0000\u03ae"+
		"\u03b0\u0001\u0000\u0000\u0000\u03af\u03ac\u0001\u0000\u0000\u0000\u03af"+
		"\u03b0\u0001\u0000\u0000\u0000\u03b0\u03b1\u0001\u0000\u0000\u0000\u03b1"+
		"\u03b2\u0003t:\u0000\u03b2e\u0001\u0000\u0000\u0000\u03b3\u03b4\u0003"+
		"t:\u0000\u03b4g\u0001\u0000\u0000\u0000\u03b5\u03b6\u0003t:\u0000\u03b6"+
		"i\u0001\u0000\u0000\u0000\u03b7\u03b8\u0003t:\u0000\u03b8k\u0001\u0000"+
		"\u0000\u0000\u03b9\u03ba\u0003t:\u0000\u03bam\u0001\u0000\u0000\u0000"+
		"\u03bb\u03bc\u0003t:\u0000\u03bco\u0001\u0000\u0000\u0000\u03bd\u03be"+
		"\u0003t:\u0000\u03beq\u0001\u0000\u0000\u0000\u03bf\u03c2\u0003t:\u0000"+
		"\u03c0\u03c2\u0005\u0097\u0000\u0000\u03c1\u03bf\u0001\u0000\u0000\u0000"+
		"\u03c1\u03c0\u0001\u0000\u0000\u0000\u03c2s\u0001\u0000\u0000\u0000\u03c3"+
		"\u03c7\u0005\u009a\u0000\u0000\u03c4\u03c7\u0005\u0099\u0000\u0000\u03c5"+
		"\u03c7\u0003v;\u0000\u03c6\u03c3\u0001\u0000\u0000\u0000\u03c6\u03c4\u0001"+
		"\u0000\u0000\u0000\u03c6\u03c5\u0001\u0000\u0000\u0000\u03c7u\u0001\u0000"+
		"\u0000\u0000\u03c8\u03c9\u0007\n\u0000\u0000\u03c9w\u0001\u0000\u0000"+
		"\u0000\u03ca\u03cb\u0007\u000b\u0000\u0000\u03cby\u0001\u0000\u0000\u0000"+
		"}\u007f\u0083\u0088\u0092\u0099\u00a2\u00aa\u00b0\u00b6\u00bf\u00ca\u00cf"+
		"\u00da\u00df\u00e3\u00e9\u00ed\u00f2\u00fa\u00ff\u0104\u0109\u010d\u0111"+
		"\u0115\u011e\u012a\u012c\u0130\u0134\u0139\u013c\u0142\u014b\u014f\u0153"+
		"\u0156\u015c\u0160\u0167\u016f\u0178\u0181\u0184\u0189\u018d\u0190\u0193"+
		"\u0196\u0199\u019c\u01a0\u01a3\u01ac\u01b9\u01bd\u01c1\u01c5\u01c9\u01cc"+
		"\u01d4\u01dd\u01e0\u01e3\u01e7\u01ec\u01f0\u01f3\u01fb\u01fe\u0201\u0205"+
		"\u020d\u0210\u0214\u0217\u021b\u021e\u0224\u0227\u0229\u022c\u0237\u023c"+
		"\u0240\u0244\u0247\u025a\u025f\u0263\u0268\u0280\u02ac\u02bf\u02c1\u02c8"+
		"\u02d1\u02d5\u02dc\u02e0\u02eb\u02fe\u0301\u0308\u030b\u0311\u0317\u032f"+
		"\u0332\u0334\u0338\u033b\u034e\u0350\u0354\u0371\u0380\u0388\u038d\u0391"+
		"\u0398\u03aa\u03af\u03c1\u03c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}