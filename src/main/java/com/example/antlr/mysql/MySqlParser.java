// Generated from /Users/jiangdadong/CodeBuddy/antlr-demo01/src/main/antlr4/mysql/MySqlParser.g4 by ANTLR 4.13.1
package com.example.antlr.mysql;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MySqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SELECT=1, FROM=2, WHERE=3, AND=4, OR=5, NOT=6, INSERT=7, INTO=8, VALUES=9, 
		UPDATE=10, SET=11, DELETE=12, CREATE=13, TABLE=14, DROP=15, ALTER=16, 
		ADD=17, COLUMN=18, INDEX=19, PRIMARY=20, KEY=21, FOREIGN=22, REFERENCES=23, 
		UNIQUE=24, NULL_=25, DEFAULT=26, AUTO_INCREMENT=27, IF_=28, EXISTS=29, 
		DATABASE=30, USE_=31, SHOW=32, DATABASES=33, TABLES=34, DESCRIBE=35, DESC=36, 
		ASC=37, ORDER=38, BY=39, GROUP=40, HAVING=41, LIMIT=42, OFFSET=43, JOIN=44, 
		INNER=45, LEFT=46, RIGHT=47, OUTER=48, CROSS=49, ON=50, AS=51, IN=52, 
		BETWEEN=53, LIKE=54, IS=55, DISTINCT=56, UNION=57, ALL=58, CASE=59, WHEN=60, 
		THEN=61, ELSE=62, END=63, COUNT=64, SUM=65, AVG=66, MIN=67, MAX=68, CAST=69, 
		REPLACE=70, TRUNCATE=71, COMMENT=72, ENGINE=73, CHARSET=74, CHARACTER=75, 
		COLLATE=76, UNSIGNED=77, SIGNED=78, TINYINT=79, SMALLINT=80, MEDIUMINT=81, 
		INT=82, INTEGER=83, BIGINT=84, FLOAT=85, DOUBLE=86, DECIMAL=87, NUMERIC=88, 
		CHAR=89, VARCHAR=90, TEXT=91, TINYTEXT=92, MEDIUMTEXT=93, LONGTEXT=94, 
		BLOB=95, DATE=96, DATETIME=97, TIMESTAMP=98, TIME=99, BOOLEAN=100, BOOL=101, 
		ENUM=102, TRUE=103, FALSE=104, STAR=105, COMMA=106, DOT=107, LPAREN=108, 
		RPAREN=109, SEMICOLON=110, EQ=111, NEQ=112, LT=113, GT=114, LTE=115, GTE=116, 
		PLUS=117, MINUS=118, DIVIDE=119, MOD=120, BACKTICK=121, INTEGER_LITERAL=122, 
		DECIMAL_LITERAL=123, STRING_LITERAL=124, BACKTICK_QUOTED_ID=125, IDENTIFIER=126, 
		WS=127, LINE_COMMENT=128, BLOCK_COMMENT=129;
	public static final int
		RULE_root = 0, RULE_sqlStatements = 1, RULE_sqlStatement = 2, RULE_ddlStatement = 3, 
		RULE_createDatabase = 4, RULE_dropDatabase = 5, RULE_createTable = 6, 
		RULE_dropTable = 7, RULE_alterTable = 8, RULE_alterTableAction = 9, RULE_truncateTable = 10, 
		RULE_createIndex = 11, RULE_dropIndex = 12, RULE_dmlStatement = 13, RULE_selectStatement = 14, 
		RULE_insertStatement = 15, RULE_updateStatement = 16, RULE_deleteStatement = 17, 
		RULE_showStatement = 18, RULE_useStatement = 19, RULE_describeStatement = 20, 
		RULE_selectElements = 21, RULE_selectElement = 22, RULE_tableSource = 23, 
		RULE_joinClause = 24, RULE_joinType = 25, RULE_whereClause = 26, RULE_groupByClause = 27, 
		RULE_havingClause = 28, RULE_orderByClause = 29, RULE_orderByElement = 30, 
		RULE_limitClause = 31, RULE_expression = 32, RULE_comparisonOperator = 33, 
		RULE_caseExpression = 34, RULE_functionCall = 35, RULE_functionName = 36, 
		RULE_columnDefinition = 37, RULE_dataType = 38, RULE_columnConstraint = 39, 
		RULE_tableConstraint = 40, RULE_tableOptions = 41, RULE_tableOption = 42, 
		RULE_updateAssignment = 43, RULE_columnNameList = 44, RULE_indexColumnList = 45, 
		RULE_expressionList = 46, RULE_columnRef = 47, RULE_tableName = 48, RULE_databaseName = 49, 
		RULE_columnName = 50, RULE_indexName = 51, RULE_alias = 52, RULE_identifier = 53, 
		RULE_literal = 54;
	private static String[] makeRuleNames() {
		return new String[] {
			"root", "sqlStatements", "sqlStatement", "ddlStatement", "createDatabase", 
			"dropDatabase", "createTable", "dropTable", "alterTable", "alterTableAction", 
			"truncateTable", "createIndex", "dropIndex", "dmlStatement", "selectStatement", 
			"insertStatement", "updateStatement", "deleteStatement", "showStatement", 
			"useStatement", "describeStatement", "selectElements", "selectElement", 
			"tableSource", "joinClause", "joinType", "whereClause", "groupByClause", 
			"havingClause", "orderByClause", "orderByElement", "limitClause", "expression", 
			"comparisonOperator", "caseExpression", "functionCall", "functionName", 
			"columnDefinition", "dataType", "columnConstraint", "tableConstraint", 
			"tableOptions", "tableOption", "updateAssignment", "columnNameList", 
			"indexColumnList", "expressionList", "columnRef", "tableName", "databaseName", 
			"columnName", "indexName", "alias", "identifier", "literal"
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
			null, null, null, null, null, null, null, null, null, "'*'", "','", "'.'", 
			"'('", "')'", "';'", "'='", null, "'<'", "'>'", "'<='", "'>='", "'+'", 
			"'-'", "'/'", "'%'", "'`'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SELECT", "FROM", "WHERE", "AND", "OR", "NOT", "INSERT", "INTO", 
			"VALUES", "UPDATE", "SET", "DELETE", "CREATE", "TABLE", "DROP", "ALTER", 
			"ADD", "COLUMN", "INDEX", "PRIMARY", "KEY", "FOREIGN", "REFERENCES", 
			"UNIQUE", "NULL_", "DEFAULT", "AUTO_INCREMENT", "IF_", "EXISTS", "DATABASE", 
			"USE_", "SHOW", "DATABASES", "TABLES", "DESCRIBE", "DESC", "ASC", "ORDER", 
			"BY", "GROUP", "HAVING", "LIMIT", "OFFSET", "JOIN", "INNER", "LEFT", 
			"RIGHT", "OUTER", "CROSS", "ON", "AS", "IN", "BETWEEN", "LIKE", "IS", 
			"DISTINCT", "UNION", "ALL", "CASE", "WHEN", "THEN", "ELSE", "END", "COUNT", 
			"SUM", "AVG", "MIN", "MAX", "CAST", "REPLACE", "TRUNCATE", "COMMENT", 
			"ENGINE", "CHARSET", "CHARACTER", "COLLATE", "UNSIGNED", "SIGNED", "TINYINT", 
			"SMALLINT", "MEDIUMINT", "INT", "INTEGER", "BIGINT", "FLOAT", "DOUBLE", 
			"DECIMAL", "NUMERIC", "CHAR", "VARCHAR", "TEXT", "TINYTEXT", "MEDIUMTEXT", 
			"LONGTEXT", "BLOB", "DATE", "DATETIME", "TIMESTAMP", "TIME", "BOOLEAN", 
			"BOOL", "ENUM", "TRUE", "FALSE", "STAR", "COMMA", "DOT", "LPAREN", "RPAREN", 
			"SEMICOLON", "EQ", "NEQ", "LT", "GT", "LTE", "GTE", "PLUS", "MINUS", 
			"DIVIDE", "MOD", "BACKTICK", "INTEGER_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", 
			"BACKTICK_QUOTED_ID", "IDENTIFIER", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
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
	public String getGrammarFileName() { return "MySqlParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MySqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RootContext extends ParserRuleContext {
		public SqlStatementsContext sqlStatements() {
			return getRuleContext(SqlStatementsContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MySqlParser.EOF, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			sqlStatements();
			setState(111);
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
		public List<TerminalNode> SEMICOLON() { return getTokens(MySqlParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MySqlParser.SEMICOLON, i);
		}
		public SqlStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterSqlStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitSqlStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitSqlStatements(this);
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
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 109521777794L) != 0) || _la==TRUNCATE) {
				{
				{
				setState(113);
				sqlStatement();
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SEMICOLON) {
					{
					setState(114);
					match(SEMICOLON);
					}
				}

				}
				}
				setState(121);
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
		public ShowStatementContext showStatement() {
			return getRuleContext(ShowStatementContext.class,0);
		}
		public UseStatementContext useStatement() {
			return getRuleContext(UseStatementContext.class,0);
		}
		public DescribeStatementContext describeStatement() {
			return getRuleContext(DescribeStatementContext.class,0);
		}
		public SqlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterSqlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitSqlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitSqlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlStatementContext sqlStatement() throws RecognitionException {
		SqlStatementContext _localctx = new SqlStatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sqlStatement);
		try {
			setState(127);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CREATE:
			case DROP:
			case ALTER:
			case TRUNCATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(122);
				ddlStatement();
				}
				break;
			case SELECT:
			case INSERT:
			case UPDATE:
			case DELETE:
				enterOuterAlt(_localctx, 2);
				{
				setState(123);
				dmlStatement();
				}
				break;
			case SHOW:
				enterOuterAlt(_localctx, 3);
				{
				setState(124);
				showStatement();
				}
				break;
			case USE_:
				enterOuterAlt(_localctx, 4);
				{
				setState(125);
				useStatement();
				}
				break;
			case DESCRIBE:
			case DESC:
				enterOuterAlt(_localctx, 5);
				{
				setState(126);
				describeStatement();
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
		public TruncateTableContext truncateTable() {
			return getRuleContext(TruncateTableContext.class,0);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDdlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDdlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDdlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DdlStatementContext ddlStatement() throws RecognitionException {
		DdlStatementContext _localctx = new DdlStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ddlStatement);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				createDatabase();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				createTable();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				dropDatabase();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(132);
				dropTable();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(133);
				alterTable();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(134);
				truncateTable();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(135);
				createIndex();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(136);
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
		public TerminalNode CREATE() { return getToken(MySqlParser.CREATE, 0); }
		public TerminalNode DATABASE() { return getToken(MySqlParser.DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(MySqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(MySqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(MySqlParser.EXISTS, 0); }
		public CreateDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createDatabase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterCreateDatabase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitCreateDatabase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitCreateDatabase(this);
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
			setState(139);
			match(CREATE);
			setState(140);
			match(DATABASE);
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(141);
				match(IF_);
				setState(142);
				match(NOT);
				setState(143);
				match(EXISTS);
				}
			}

			setState(146);
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
	public static class DropDatabaseContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(MySqlParser.DROP, 0); }
		public TerminalNode DATABASE() { return getToken(MySqlParser.DATABASE, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(MySqlParser.IF_, 0); }
		public TerminalNode EXISTS() { return getToken(MySqlParser.EXISTS, 0); }
		public DropDatabaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropDatabase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDropDatabase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDropDatabase(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDropDatabase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropDatabaseContext dropDatabase() throws RecognitionException {
		DropDatabaseContext _localctx = new DropDatabaseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_dropDatabase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(DROP);
			setState(149);
			match(DATABASE);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(150);
				match(IF_);
				setState(151);
				match(EXISTS);
				}
			}

			setState(154);
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
		public TerminalNode CREATE() { return getToken(MySqlParser.CREATE, 0); }
		public TerminalNode TABLE() { return getToken(MySqlParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public List<ColumnDefinitionContext> columnDefinition() {
			return getRuleContexts(ColumnDefinitionContext.class);
		}
		public ColumnDefinitionContext columnDefinition(int i) {
			return getRuleContext(ColumnDefinitionContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public TerminalNode IF_() { return getToken(MySqlParser.IF_, 0); }
		public TerminalNode NOT() { return getToken(MySqlParser.NOT, 0); }
		public TerminalNode EXISTS() { return getToken(MySqlParser.EXISTS, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public List<TableConstraintContext> tableConstraint() {
			return getRuleContexts(TableConstraintContext.class);
		}
		public TableConstraintContext tableConstraint(int i) {
			return getRuleContext(TableConstraintContext.class,i);
		}
		public TableOptionsContext tableOptions() {
			return getRuleContext(TableOptionsContext.class,0);
		}
		public CreateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterCreateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitCreateTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitCreateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateTableContext createTable() throws RecognitionException {
		CreateTableContext _localctx = new CreateTableContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_createTable);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(CREATE);
			setState(157);
			match(TABLE);
			setState(161);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(158);
				match(IF_);
				setState(159);
				match(NOT);
				setState(160);
				match(EXISTS);
				}
			}

			setState(163);
			tableName();
			setState(164);
			match(LPAREN);
			setState(165);
			columnDefinition();
			setState(170);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(166);
					match(COMMA);
					setState(167);
					columnDefinition();
					}
					} 
				}
				setState(172);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(173);
				match(COMMA);
				setState(174);
				tableConstraint();
				}
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(180);
			match(RPAREN);
			setState(182);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 26)) & ~0x3f) == 0 && ((1L << (_la - 26)) & 2181431069507587L) != 0)) {
				{
				setState(181);
				tableOptions();
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
	public static class DropTableContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(MySqlParser.DROP, 0); }
		public TerminalNode TABLE() { return getToken(MySqlParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode IF_() { return getToken(MySqlParser.IF_, 0); }
		public TerminalNode EXISTS() { return getToken(MySqlParser.EXISTS, 0); }
		public DropTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDropTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDropTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDropTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropTableContext dropTable() throws RecognitionException {
		DropTableContext _localctx = new DropTableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dropTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(DROP);
			setState(185);
			match(TABLE);
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF_) {
				{
				setState(186);
				match(IF_);
				setState(187);
				match(EXISTS);
				}
			}

			setState(190);
			tableName();
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
		public TerminalNode ALTER() { return getToken(MySqlParser.ALTER, 0); }
		public TerminalNode TABLE() { return getToken(MySqlParser.TABLE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<AlterTableActionContext> alterTableAction() {
			return getRuleContexts(AlterTableActionContext.class);
		}
		public AlterTableActionContext alterTableAction(int i) {
			return getRuleContext(AlterTableActionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public AlterTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterAlterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitAlterTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitAlterTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableContext alterTable() throws RecognitionException {
		AlterTableContext _localctx = new AlterTableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_alterTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			match(ALTER);
			setState(193);
			match(TABLE);
			setState(194);
			tableName();
			setState(195);
			alterTableAction();
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(196);
				match(COMMA);
				setState(197);
				alterTableAction();
				}
				}
				setState(202);
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
		public TerminalNode ADD() { return getToken(MySqlParser.ADD, 0); }
		public ColumnDefinitionContext columnDefinition() {
			return getRuleContext(ColumnDefinitionContext.class,0);
		}
		public TerminalNode COLUMN() { return getToken(MySqlParser.COLUMN, 0); }
		public TerminalNode DROP() { return getToken(MySqlParser.DROP, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TerminalNode ALTER() { return getToken(MySqlParser.ALTER, 0); }
		public TerminalNode SET() { return getToken(MySqlParser.SET, 0); }
		public TerminalNode DEFAULT() { return getToken(MySqlParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AlterTableActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterTableAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterAlterTableAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitAlterTableAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitAlterTableAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterTableActionContext alterTableAction() throws RecognitionException {
		AlterTableActionContext _localctx = new AlterTableActionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_alterTableAction);
		int _la;
		try {
			setState(222);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				match(ADD);
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLUMN) {
					{
					setState(204);
					match(COLUMN);
					}
				}

				setState(207);
				columnDefinition();
				}
				break;
			case DROP:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				match(DROP);
				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLUMN) {
					{
					setState(209);
					match(COLUMN);
					}
				}

				setState(212);
				columnName();
				}
				break;
			case ALTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(213);
				match(ALTER);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLUMN) {
					{
					setState(214);
					match(COLUMN);
					}
				}

				setState(217);
				columnName();
				setState(218);
				match(SET);
				setState(219);
				match(DEFAULT);
				setState(220);
				expression(0);
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
	public static class TruncateTableContext extends ParserRuleContext {
		public TerminalNode TRUNCATE() { return getToken(MySqlParser.TRUNCATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode TABLE() { return getToken(MySqlParser.TABLE, 0); }
		public TruncateTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truncateTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTruncateTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTruncateTable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTruncateTable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TruncateTableContext truncateTable() throws RecognitionException {
		TruncateTableContext _localctx = new TruncateTableContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_truncateTable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(TRUNCATE);
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TABLE) {
				{
				setState(225);
				match(TABLE);
				}
			}

			setState(228);
			tableName();
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
		public TerminalNode CREATE() { return getToken(MySqlParser.CREATE, 0); }
		public TerminalNode INDEX() { return getToken(MySqlParser.INDEX, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode ON() { return getToken(MySqlParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public IndexColumnListContext indexColumnList() {
			return getRuleContext(IndexColumnListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public TerminalNode UNIQUE() { return getToken(MySqlParser.UNIQUE, 0); }
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterCreateIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitCreateIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitCreateIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_createIndex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			match(CREATE);
			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNIQUE) {
				{
				setState(231);
				match(UNIQUE);
				}
			}

			setState(234);
			match(INDEX);
			setState(235);
			indexName();
			setState(236);
			match(ON);
			setState(237);
			tableName();
			setState(238);
			match(LPAREN);
			setState(239);
			indexColumnList();
			setState(240);
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
		public TerminalNode DROP() { return getToken(MySqlParser.DROP, 0); }
		public TerminalNode INDEX() { return getToken(MySqlParser.INDEX, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode ON() { return getToken(MySqlParser.ON, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public DropIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDropIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDropIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDropIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropIndexContext dropIndex() throws RecognitionException {
		DropIndexContext _localctx = new DropIndexContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_dropIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(DROP);
			setState(243);
			match(INDEX);
			setState(244);
			indexName();
			setState(245);
			match(ON);
			setState(246);
			tableName();
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDmlStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDmlStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDmlStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DmlStatementContext dmlStatement() throws RecognitionException {
		DmlStatementContext _localctx = new DmlStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_dmlStatement);
		try {
			setState(252);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				selectStatement();
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				insertStatement();
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				updateStatement();
				}
				break;
			case DELETE:
				enterOuterAlt(_localctx, 4);
				{
				setState(251);
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
	public static class SelectStatementContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(MySqlParser.SELECT, 0); }
		public SelectElementsContext selectElements() {
			return getRuleContext(SelectElementsContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(MySqlParser.DISTINCT, 0); }
		public TerminalNode FROM() { return getToken(MySqlParser.FROM, 0); }
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
		public TerminalNode UNION() { return getToken(MySqlParser.UNION, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public TerminalNode ALL() { return getToken(MySqlParser.ALL, 0); }
		public SelectStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterSelectStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitSelectStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitSelectStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectStatementContext selectStatement() throws RecognitionException {
		SelectStatementContext _localctx = new SelectStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_selectStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(SELECT);
			setState(256);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DISTINCT) {
				{
				setState(255);
				match(DISTINCT);
				}
			}

			setState(258);
			selectElements();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FROM) {
				{
				setState(259);
				match(FROM);
				setState(260);
				tableSource();
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(261);
					match(COMMA);
					setState(262);
					tableSource();
					}
					}
					setState(267);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 826832744087552L) != 0)) {
				{
				{
				setState(270);
				joinClause();
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(277);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(276);
				whereClause();
				}
			}

			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(279);
				groupByClause();
				}
			}

			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(282);
				havingClause();
				}
			}

			setState(286);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(285);
				orderByClause();
				}
			}

			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LIMIT) {
				{
				setState(288);
				limitClause();
				}
			}

			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==UNION) {
				{
				setState(291);
				match(UNION);
				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ALL) {
					{
					setState(292);
					match(ALL);
					}
				}

				setState(295);
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
		public TerminalNode INSERT() { return getToken(MySqlParser.INSERT, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode VALUES() { return getToken(MySqlParser.VALUES, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(MySqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(MySqlParser.LPAREN, i);
		}
		public List<ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(MySqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(MySqlParser.RPAREN, i);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode INTO() { return getToken(MySqlParser.INTO, 0); }
		public ColumnNameListContext columnNameList() {
			return getRuleContext(ColumnNameListContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public InsertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterInsertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitInsertStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitInsertStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InsertStatementContext insertStatement() throws RecognitionException {
		InsertStatementContext _localctx = new InsertStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_insertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(INSERT);
			setState(300);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(299);
				match(INTO);
				}
			}

			setState(302);
			tableName();
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(303);
				match(LPAREN);
				setState(304);
				columnNameList();
				setState(305);
				match(RPAREN);
				}
			}

			setState(324);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VALUES:
				{
				setState(309);
				match(VALUES);
				setState(310);
				match(LPAREN);
				setState(311);
				expressionList();
				setState(312);
				match(RPAREN);
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(313);
					match(COMMA);
					setState(314);
					match(LPAREN);
					setState(315);
					expressionList();
					setState(316);
					match(RPAREN);
					}
					}
					setState(322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case SELECT:
				{
				setState(323);
				selectStatement();
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
	public static class UpdateStatementContext extends ParserRuleContext {
		public TerminalNode UPDATE() { return getToken(MySqlParser.UPDATE, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode SET() { return getToken(MySqlParser.SET, 0); }
		public List<UpdateAssignmentContext> updateAssignment() {
			return getRuleContexts(UpdateAssignmentContext.class);
		}
		public UpdateAssignmentContext updateAssignment(int i) {
			return getRuleContext(UpdateAssignmentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public UpdateStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterUpdateStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitUpdateStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitUpdateStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateStatementContext updateStatement() throws RecognitionException {
		UpdateStatementContext _localctx = new UpdateStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_updateStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(UPDATE);
			setState(327);
			tableName();
			setState(328);
			match(SET);
			setState(329);
			updateAssignment();
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(330);
				match(COMMA);
				setState(331);
				updateAssignment();
				}
				}
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(337);
				whereClause();
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
		public TerminalNode DELETE() { return getToken(MySqlParser.DELETE, 0); }
		public TerminalNode FROM() { return getToken(MySqlParser.FROM, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public WhereClauseContext whereClause() {
			return getRuleContext(WhereClauseContext.class,0);
		}
		public DeleteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deleteStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDeleteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDeleteStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDeleteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteStatementContext deleteStatement() throws RecognitionException {
		DeleteStatementContext _localctx = new DeleteStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_deleteStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			match(DELETE);
			setState(341);
			match(FROM);
			setState(342);
			tableName();
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(343);
				whereClause();
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
	public static class ShowStatementContext extends ParserRuleContext {
		public TerminalNode SHOW() { return getToken(MySqlParser.SHOW, 0); }
		public TerminalNode DATABASES() { return getToken(MySqlParser.DATABASES, 0); }
		public TerminalNode TABLES() { return getToken(MySqlParser.TABLES, 0); }
		public ShowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_showStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterShowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitShowStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitShowStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShowStatementContext showStatement() throws RecognitionException {
		ShowStatementContext _localctx = new ShowStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_showStatement);
		try {
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(346);
				match(SHOW);
				setState(347);
				match(DATABASES);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
				match(SHOW);
				setState(349);
				match(TABLES);
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
	public static class UseStatementContext extends ParserRuleContext {
		public TerminalNode USE_() { return getToken(MySqlParser.USE_, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public UseStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_useStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterUseStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitUseStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitUseStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseStatementContext useStatement() throws RecognitionException {
		UseStatementContext _localctx = new UseStatementContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_useStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			match(USE_);
			setState(353);
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
	public static class DescribeStatementContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode DESCRIBE() { return getToken(MySqlParser.DESCRIBE, 0); }
		public TerminalNode DESC() { return getToken(MySqlParser.DESC, 0); }
		public DescribeStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_describeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDescribeStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDescribeStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDescribeStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescribeStatementContext describeStatement() throws RecognitionException {
		DescribeStatementContext _localctx = new DescribeStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_describeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(355);
			_la = _input.LA(1);
			if ( !(_la==DESCRIBE || _la==DESC) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(356);
			tableName();
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
		public TerminalNode STAR() { return getToken(MySqlParser.STAR, 0); }
		public List<SelectElementContext> selectElement() {
			return getRuleContexts(SelectElementContext.class);
		}
		public SelectElementContext selectElement(int i) {
			return getRuleContext(SelectElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public SelectElementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterSelectElements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitSelectElements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitSelectElements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectElementsContext selectElements() throws RecognitionException {
		SelectElementsContext _localctx = new SelectElementsContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selectElements);
		int _la;
		try {
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				match(STAR);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				selectElement();
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(360);
					match(COMMA);
					setState(361);
					selectElement();
					}
					}
					setState(366);
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
		public TerminalNode AS() { return getToken(MySqlParser.AS, 0); }
		public SelectElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selectElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterSelectElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitSelectElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitSelectElement(this);
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
			setState(369);
			expression(0);
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS || ((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & 7L) != 0)) {
				{
				setState(371);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(370);
					match(AS);
					}
				}

				setState(373);
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
		public TerminalNode AS() { return getToken(MySqlParser.AS, 0); }
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public TableSourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableSource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTableSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTableSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTableSource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableSourceContext tableSource() throws RecognitionException {
		TableSourceContext _localctx = new TableSourceContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_tableSource);
		int _la;
		try {
			setState(392);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BACKTICK_QUOTED_ID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				tableName();
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || ((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & 7L) != 0)) {
					{
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(377);
						match(AS);
						}
					}

					setState(380);
					alias();
					}
				}

				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(383);
				match(LPAREN);
				setState(384);
				selectStatement();
				setState(385);
				match(RPAREN);
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==AS || ((((_la - 124)) & ~0x3f) == 0 && ((1L << (_la - 124)) & 7L) != 0)) {
					{
					setState(387);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(386);
						match(AS);
						}
					}

					setState(389);
					alias();
					}
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
		public TerminalNode JOIN() { return getToken(MySqlParser.JOIN, 0); }
		public TableSourceContext tableSource() {
			return getRuleContext(TableSourceContext.class,0);
		}
		public JoinTypeContext joinType() {
			return getRuleContext(JoinTypeContext.class,0);
		}
		public TerminalNode ON() { return getToken(MySqlParser.ON, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public JoinClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterJoinClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitJoinClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitJoinClause(this);
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
			setState(395);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 809240558043136L) != 0)) {
				{
				setState(394);
				joinType();
				}
			}

			setState(397);
			match(JOIN);
			setState(398);
			tableSource();
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ON) {
				{
				setState(399);
				match(ON);
				setState(400);
				expression(0);
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
	public static class JoinTypeContext extends ParserRuleContext {
		public TerminalNode INNER() { return getToken(MySqlParser.INNER, 0); }
		public TerminalNode LEFT() { return getToken(MySqlParser.LEFT, 0); }
		public TerminalNode OUTER() { return getToken(MySqlParser.OUTER, 0); }
		public TerminalNode RIGHT() { return getToken(MySqlParser.RIGHT, 0); }
		public TerminalNode CROSS() { return getToken(MySqlParser.CROSS, 0); }
		public JoinTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterJoinType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitJoinType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitJoinType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JoinTypeContext joinType() throws RecognitionException {
		JoinTypeContext _localctx = new JoinTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_joinType);
		int _la;
		try {
			setState(413);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INNER:
				enterOuterAlt(_localctx, 1);
				{
				setState(403);
				match(INNER);
				}
				break;
			case LEFT:
				enterOuterAlt(_localctx, 2);
				{
				setState(404);
				match(LEFT);
				setState(406);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(405);
					match(OUTER);
					}
				}

				}
				break;
			case RIGHT:
				enterOuterAlt(_localctx, 3);
				{
				setState(408);
				match(RIGHT);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OUTER) {
					{
					setState(409);
					match(OUTER);
					}
				}

				}
				break;
			case CROSS:
				enterOuterAlt(_localctx, 4);
				{
				setState(412);
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
		public TerminalNode WHERE() { return getToken(MySqlParser.WHERE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterWhereClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitWhereClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitWhereClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereClauseContext whereClause() throws RecognitionException {
		WhereClauseContext _localctx = new WhereClauseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_whereClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(WHERE);
			setState(416);
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
		public TerminalNode GROUP() { return getToken(MySqlParser.GROUP, 0); }
		public TerminalNode BY() { return getToken(MySqlParser.BY, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public GroupByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterGroupByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitGroupByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitGroupByClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GroupByClauseContext groupByClause() throws RecognitionException {
		GroupByClauseContext _localctx = new GroupByClauseContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_groupByClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(GROUP);
			setState(419);
			match(BY);
			setState(420);
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
		public TerminalNode HAVING() { return getToken(MySqlParser.HAVING, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public HavingClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_havingClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterHavingClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitHavingClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitHavingClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HavingClauseContext havingClause() throws RecognitionException {
		HavingClauseContext _localctx = new HavingClauseContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_havingClause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			match(HAVING);
			setState(423);
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
		public TerminalNode ORDER() { return getToken(MySqlParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(MySqlParser.BY, 0); }
		public List<OrderByElementContext> orderByElement() {
			return getRuleContexts(OrderByElementContext.class);
		}
		public OrderByElementContext orderByElement(int i) {
			return getRuleContext(OrderByElementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public OrderByClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterOrderByClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitOrderByClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitOrderByClause(this);
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
			setState(425);
			match(ORDER);
			setState(426);
			match(BY);
			setState(427);
			orderByElement();
			setState(432);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(428);
				match(COMMA);
				setState(429);
				orderByElement();
				}
				}
				setState(434);
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
		public TerminalNode ASC() { return getToken(MySqlParser.ASC, 0); }
		public TerminalNode DESC() { return getToken(MySqlParser.DESC, 0); }
		public OrderByElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterOrderByElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitOrderByElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitOrderByElement(this);
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
			setState(435);
			expression(0);
			setState(437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(436);
				_la = _input.LA(1);
				if ( !(_la==DESC || _la==ASC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
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
	public static class LimitClauseContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(MySqlParser.LIMIT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode COMMA() { return getToken(MySqlParser.COMMA, 0); }
		public TerminalNode OFFSET() { return getToken(MySqlParser.OFFSET, 0); }
		public LimitClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limitClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterLimitClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitLimitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitLimitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitClauseContext limitClause() throws RecognitionException {
		LimitClauseContext _localctx = new LimitClauseContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_limitClause);
		int _la;
		try {
			setState(450);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(439);
				match(LIMIT);
				setState(440);
				expression(0);
				setState(443);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(441);
					match(COMMA);
					setState(442);
					expression(0);
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(445);
				match(LIMIT);
				setState(446);
				expression(0);
				setState(447);
				match(OFFSET);
				setState(448);
				expression(0);
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
		public TerminalNode STAR() { return getToken(MySqlParser.STAR, 0); }
		public TerminalNode DIVIDE() { return getToken(MySqlParser.DIVIDE, 0); }
		public TerminalNode MOD() { return getToken(MySqlParser.MOD, 0); }
		public MulDivModExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterMulDivModExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitMulDivModExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitMulDivModExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotExpressionContext extends ExpressionContext {
		public TerminalNode NOT() { return getToken(MySqlParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterNotExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitNotExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitNotExpression(this);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExpressionContext extends ExpressionContext {
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public ParenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterParenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitParenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitParenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsNotNullExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(MySqlParser.IS, 0); }
		public TerminalNode NOT() { return getToken(MySqlParser.NOT, 0); }
		public TerminalNode NULL_() { return getToken(MySqlParser.NULL_, 0); }
		public IsNotNullExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterIsNotNullExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitIsNotNullExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitIsNotNullExpression(this);
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
		public TerminalNode OR() { return getToken(MySqlParser.OR, 0); }
		public OrExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterOrExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitOrExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitOrExpression(this);
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
		public TerminalNode AND() { return getToken(MySqlParser.AND, 0); }
		public AndExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterAndExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitAndExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitAndExpression(this);
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
		public TerminalNode BETWEEN() { return getToken(MySqlParser.BETWEEN, 0); }
		public TerminalNode AND() { return getToken(MySqlParser.AND, 0); }
		public BetweenExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterBetweenExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitBetweenExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitBetweenExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IN() { return getToken(MySqlParser.IN, 0); }
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public SelectStatementContext selectStatement() {
			return getRuleContext(SelectStatementContext.class,0);
		}
		public InExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterInExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitInExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitInExpression(this);
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
		public TerminalNode PLUS() { return getToken(MySqlParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MySqlParser.MINUS, 0); }
		public AddSubExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterAddSubExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitAddSubExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitAddSubExpression(this);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterFunctionCallExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitFunctionCallExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitFunctionCallExpression(this);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnRefExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnRefExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnRefExpression(this);
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
		public TerminalNode LIKE() { return getToken(MySqlParser.LIKE, 0); }
		public LikeExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterLikeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitLikeExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitLikeExpression(this);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterCaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitCaseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitCaseExpr(this);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterLiteralExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitLiteralExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitLiteralExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsNullExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(MySqlParser.IS, 0); }
		public TerminalNode NULL_() { return getToken(MySqlParser.NULL_, 0); }
		public IsNullExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterIsNullExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitIsNullExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitIsNullExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StarExpressionContext extends ExpressionContext {
		public TerminalNode STAR() { return getToken(MySqlParser.STAR, 0); }
		public StarExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterStarExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitStarExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitStarExpression(this);
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
		int _startState = 64;
		enterRecursionRule(_localctx, 64, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(464);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				_localctx = new ParenExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(453);
				match(LPAREN);
				setState(454);
				expression(0);
				setState(455);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new NotExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(457);
				match(NOT);
				setState(458);
				expression(16);
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(459);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(460);
				caseExpression();
				}
				break;
			case 5:
				{
				_localctx = new LiteralExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(461);
				literal();
				}
				break;
			case 6:
				{
				_localctx = new ColumnRefExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(462);
				columnRef();
				}
				break;
			case 7:
				{
				_localctx = new StarExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(463);
				match(STAR);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(509);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(507);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
					case 1:
						{
						_localctx = new AndExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(466);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(467);
						match(AND);
						setState(468);
						expression(16);
						}
						break;
					case 2:
						{
						_localctx = new OrExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(469);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(470);
						match(OR);
						setState(471);
						expression(15);
						}
						break;
					case 3:
						{
						_localctx = new ComparisonExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(472);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(473);
						comparisonOperator();
						setState(474);
						expression(14);
						}
						break;
					case 4:
						{
						_localctx = new BetweenExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(476);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(477);
						match(BETWEEN);
						setState(478);
						expression(0);
						setState(479);
						match(AND);
						setState(480);
						expression(12);
						}
						break;
					case 5:
						{
						_localctx = new LikeExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(482);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(483);
						match(LIKE);
						setState(484);
						expression(11);
						}
						break;
					case 6:
						{
						_localctx = new MulDivModExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(485);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(486);
						_la = _input.LA(1);
						if ( !(((((_la - 105)) & ~0x3f) == 0 && ((1L << (_la - 105)) & 49153L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(487);
						expression(6);
						}
						break;
					case 7:
						{
						_localctx = new AddSubExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(488);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(489);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(490);
						expression(5);
						}
						break;
					case 8:
						{
						_localctx = new InExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(491);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(492);
						match(IN);
						setState(493);
						match(LPAREN);
						setState(496);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case NOT:
						case NULL_:
						case CASE:
						case COUNT:
						case SUM:
						case AVG:
						case MIN:
						case MAX:
						case CAST:
						case REPLACE:
						case TRUE:
						case FALSE:
						case STAR:
						case LPAREN:
						case INTEGER_LITERAL:
						case DECIMAL_LITERAL:
						case STRING_LITERAL:
						case BACKTICK_QUOTED_ID:
						case IDENTIFIER:
							{
							setState(494);
							expressionList();
							}
							break;
						case SELECT:
							{
							setState(495);
							selectStatement();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(498);
						match(RPAREN);
						}
						break;
					case 9:
						{
						_localctx = new IsNullExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(500);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(501);
						match(IS);
						setState(502);
						match(NULL_);
						}
						break;
					case 10:
						{
						_localctx = new IsNotNullExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(503);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(504);
						match(IS);
						setState(505);
						match(NOT);
						setState(506);
						match(NULL_);
						}
						break;
					}
					} 
				}
				setState(511);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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
		public TerminalNode EQ() { return getToken(MySqlParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(MySqlParser.NEQ, 0); }
		public TerminalNode LT() { return getToken(MySqlParser.LT, 0); }
		public TerminalNode GT() { return getToken(MySqlParser.GT, 0); }
		public TerminalNode LTE() { return getToken(MySqlParser.LTE, 0); }
		public TerminalNode GTE() { return getToken(MySqlParser.GTE, 0); }
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterComparisonOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitComparisonOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(512);
			_la = _input.LA(1);
			if ( !(((((_la - 111)) & ~0x3f) == 0 && ((1L << (_la - 111)) & 63L) != 0)) ) {
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
		public TerminalNode CASE() { return getToken(MySqlParser.CASE, 0); }
		public TerminalNode END() { return getToken(MySqlParser.END, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> WHEN() { return getTokens(MySqlParser.WHEN); }
		public TerminalNode WHEN(int i) {
			return getToken(MySqlParser.WHEN, i);
		}
		public List<TerminalNode> THEN() { return getTokens(MySqlParser.THEN); }
		public TerminalNode THEN(int i) {
			return getToken(MySqlParser.THEN, i);
		}
		public TerminalNode ELSE() { return getToken(MySqlParser.ELSE, 0); }
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterCaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitCaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_caseExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			match(CASE);
			setState(516);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 576460752336977984L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8935163101179805823L) != 0)) {
				{
				setState(515);
				expression(0);
				}
			}

			setState(523); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(518);
				match(WHEN);
				setState(519);
				expression(0);
				setState(520);
				match(THEN);
				setState(521);
				expression(0);
				}
				}
				setState(525); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WHEN );
			setState(529);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ELSE) {
				{
				setState(527);
				match(ELSE);
				setState(528);
				expression(0);
				}
			}

			setState(531);
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
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public TerminalNode STAR() { return getToken(MySqlParser.STAR, 0); }
		public TerminalNode DISTINCT() { return getToken(MySqlParser.DISTINCT, 0); }
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			functionName();
			setState(534);
			match(LPAREN);
			setState(540);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				{
				setState(536);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DISTINCT) {
					{
					setState(535);
					match(DISTINCT);
					}
				}

				setState(538);
				expressionList();
				}
				break;
			case 2:
				{
				setState(539);
				match(STAR);
				}
				break;
			}
			setState(542);
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
		public TerminalNode COUNT() { return getToken(MySqlParser.COUNT, 0); }
		public TerminalNode SUM() { return getToken(MySqlParser.SUM, 0); }
		public TerminalNode AVG() { return getToken(MySqlParser.AVG, 0); }
		public TerminalNode MIN() { return getToken(MySqlParser.MIN, 0); }
		public TerminalNode MAX() { return getToken(MySqlParser.MAX, 0); }
		public TerminalNode CAST() { return getToken(MySqlParser.CAST, 0); }
		public TerminalNode REPLACE() { return getToken(MySqlParser.REPLACE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MySqlParser.IDENTIFIER, 0); }
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_functionName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 4611686018427388031L) != 0)) ) {
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnDefinitionContext columnDefinition() throws RecognitionException {
		ColumnDefinitionContext _localctx = new ColumnDefinitionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_columnDefinition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			columnName();
			setState(547);
			dataType();
			setState(551);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 252706880L) != 0) || _la==COMMENT) {
				{
				{
				setState(548);
				columnConstraint();
				}
				}
				setState(553);
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
		public TerminalNode TINYINT() { return getToken(MySqlParser.TINYINT, 0); }
		public TerminalNode LPAREN() { return getToken(MySqlParser.LPAREN, 0); }
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(MySqlParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(MySqlParser.INTEGER_LITERAL, i);
		}
		public TerminalNode RPAREN() { return getToken(MySqlParser.RPAREN, 0); }
		public TerminalNode UNSIGNED() { return getToken(MySqlParser.UNSIGNED, 0); }
		public TerminalNode SMALLINT() { return getToken(MySqlParser.SMALLINT, 0); }
		public TerminalNode MEDIUMINT() { return getToken(MySqlParser.MEDIUMINT, 0); }
		public TerminalNode INT() { return getToken(MySqlParser.INT, 0); }
		public TerminalNode INTEGER() { return getToken(MySqlParser.INTEGER, 0); }
		public TerminalNode BIGINT() { return getToken(MySqlParser.BIGINT, 0); }
		public TerminalNode FLOAT() { return getToken(MySqlParser.FLOAT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public TerminalNode DOUBLE() { return getToken(MySqlParser.DOUBLE, 0); }
		public TerminalNode DECIMAL() { return getToken(MySqlParser.DECIMAL, 0); }
		public TerminalNode NUMERIC() { return getToken(MySqlParser.NUMERIC, 0); }
		public TerminalNode CHAR() { return getToken(MySqlParser.CHAR, 0); }
		public TerminalNode VARCHAR() { return getToken(MySqlParser.VARCHAR, 0); }
		public TerminalNode TEXT() { return getToken(MySqlParser.TEXT, 0); }
		public TerminalNode TINYTEXT() { return getToken(MySqlParser.TINYTEXT, 0); }
		public TerminalNode MEDIUMTEXT() { return getToken(MySqlParser.MEDIUMTEXT, 0); }
		public TerminalNode LONGTEXT() { return getToken(MySqlParser.LONGTEXT, 0); }
		public TerminalNode BLOB() { return getToken(MySqlParser.BLOB, 0); }
		public TerminalNode DATE() { return getToken(MySqlParser.DATE, 0); }
		public TerminalNode DATETIME() { return getToken(MySqlParser.DATETIME, 0); }
		public TerminalNode TIMESTAMP() { return getToken(MySqlParser.TIMESTAMP, 0); }
		public TerminalNode TIME() { return getToken(MySqlParser.TIME, 0); }
		public TerminalNode BOOLEAN() { return getToken(MySqlParser.BOOLEAN, 0); }
		public TerminalNode BOOL() { return getToken(MySqlParser.BOOL, 0); }
		public TerminalNode ENUM() { return getToken(MySqlParser.ENUM, 0); }
		public List<TerminalNode> STRING_LITERAL() { return getTokens(MySqlParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(MySqlParser.STRING_LITERAL, i);
		}
		public DataTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDataType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDataType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDataType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataTypeContext dataType() throws RecognitionException {
		DataTypeContext _localctx = new DataTypeContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_dataType);
		int _la;
		try {
			setState(682);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TINYINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(554);
				match(TINYINT);
				setState(558);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(555);
					match(LPAREN);
					setState(556);
					match(INTEGER_LITERAL);
					setState(557);
					match(RPAREN);
					}
				}

				setState(561);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(560);
					match(UNSIGNED);
					}
				}

				}
				break;
			case SMALLINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(563);
				match(SMALLINT);
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(564);
					match(LPAREN);
					setState(565);
					match(INTEGER_LITERAL);
					setState(566);
					match(RPAREN);
					}
				}

				setState(570);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(569);
					match(UNSIGNED);
					}
				}

				}
				break;
			case MEDIUMINT:
				enterOuterAlt(_localctx, 3);
				{
				setState(572);
				match(MEDIUMINT);
				setState(576);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(573);
					match(LPAREN);
					setState(574);
					match(INTEGER_LITERAL);
					setState(575);
					match(RPAREN);
					}
				}

				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(578);
					match(UNSIGNED);
					}
				}

				}
				break;
			case INT:
				enterOuterAlt(_localctx, 4);
				{
				setState(581);
				match(INT);
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(582);
					match(LPAREN);
					setState(583);
					match(INTEGER_LITERAL);
					setState(584);
					match(RPAREN);
					}
				}

				setState(588);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(587);
					match(UNSIGNED);
					}
				}

				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 5);
				{
				setState(590);
				match(INTEGER);
				setState(594);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(591);
					match(LPAREN);
					setState(592);
					match(INTEGER_LITERAL);
					setState(593);
					match(RPAREN);
					}
				}

				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(596);
					match(UNSIGNED);
					}
				}

				}
				break;
			case BIGINT:
				enterOuterAlt(_localctx, 6);
				{
				setState(599);
				match(BIGINT);
				setState(603);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(600);
					match(LPAREN);
					setState(601);
					match(INTEGER_LITERAL);
					setState(602);
					match(RPAREN);
					}
				}

				setState(606);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(605);
					match(UNSIGNED);
					}
				}

				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 7);
				{
				setState(608);
				match(FLOAT);
				setState(616);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(609);
					match(LPAREN);
					setState(610);
					match(INTEGER_LITERAL);
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(611);
						match(COMMA);
						setState(612);
						match(INTEGER_LITERAL);
						}
					}

					setState(615);
					match(RPAREN);
					}
				}

				}
				break;
			case DOUBLE:
				enterOuterAlt(_localctx, 8);
				{
				setState(618);
				match(DOUBLE);
				setState(626);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(619);
					match(LPAREN);
					setState(620);
					match(INTEGER_LITERAL);
					setState(623);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(621);
						match(COMMA);
						setState(622);
						match(INTEGER_LITERAL);
						}
					}

					setState(625);
					match(RPAREN);
					}
				}

				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 9);
				{
				setState(628);
				match(DECIMAL);
				setState(636);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(629);
					match(LPAREN);
					setState(630);
					match(INTEGER_LITERAL);
					setState(633);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(631);
						match(COMMA);
						setState(632);
						match(INTEGER_LITERAL);
						}
					}

					setState(635);
					match(RPAREN);
					}
				}

				}
				break;
			case NUMERIC:
				enterOuterAlt(_localctx, 10);
				{
				setState(638);
				match(NUMERIC);
				setState(646);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(639);
					match(LPAREN);
					setState(640);
					match(INTEGER_LITERAL);
					setState(643);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==COMMA) {
						{
						setState(641);
						match(COMMA);
						setState(642);
						match(INTEGER_LITERAL);
						}
					}

					setState(645);
					match(RPAREN);
					}
				}

				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 11);
				{
				setState(648);
				match(CHAR);
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(649);
					match(LPAREN);
					setState(650);
					match(INTEGER_LITERAL);
					setState(651);
					match(RPAREN);
					}
				}

				}
				break;
			case VARCHAR:
				enterOuterAlt(_localctx, 12);
				{
				setState(654);
				match(VARCHAR);
				setState(658);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(655);
					match(LPAREN);
					setState(656);
					match(INTEGER_LITERAL);
					setState(657);
					match(RPAREN);
					}
				}

				}
				break;
			case TEXT:
				enterOuterAlt(_localctx, 13);
				{
				setState(660);
				match(TEXT);
				}
				break;
			case TINYTEXT:
				enterOuterAlt(_localctx, 14);
				{
				setState(661);
				match(TINYTEXT);
				}
				break;
			case MEDIUMTEXT:
				enterOuterAlt(_localctx, 15);
				{
				setState(662);
				match(MEDIUMTEXT);
				}
				break;
			case LONGTEXT:
				enterOuterAlt(_localctx, 16);
				{
				setState(663);
				match(LONGTEXT);
				}
				break;
			case BLOB:
				enterOuterAlt(_localctx, 17);
				{
				setState(664);
				match(BLOB);
				}
				break;
			case DATE:
				enterOuterAlt(_localctx, 18);
				{
				setState(665);
				match(DATE);
				}
				break;
			case DATETIME:
				enterOuterAlt(_localctx, 19);
				{
				setState(666);
				match(DATETIME);
				}
				break;
			case TIMESTAMP:
				enterOuterAlt(_localctx, 20);
				{
				setState(667);
				match(TIMESTAMP);
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 21);
				{
				setState(668);
				match(TIME);
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 22);
				{
				setState(669);
				match(BOOLEAN);
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 23);
				{
				setState(670);
				match(BOOL);
				}
				break;
			case ENUM:
				enterOuterAlt(_localctx, 24);
				{
				setState(671);
				match(ENUM);
				setState(672);
				match(LPAREN);
				setState(673);
				match(STRING_LITERAL);
				setState(678);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(674);
					match(COMMA);
					setState(675);
					match(STRING_LITERAL);
					}
					}
					setState(680);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(681);
				match(RPAREN);
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
		public TerminalNode NULL_() { return getToken(MySqlParser.NULL_, 0); }
		public TerminalNode NOT() { return getToken(MySqlParser.NOT, 0); }
		public TerminalNode DEFAULT() { return getToken(MySqlParser.DEFAULT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PRIMARY() { return getToken(MySqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(MySqlParser.KEY, 0); }
		public TerminalNode UNIQUE() { return getToken(MySqlParser.UNIQUE, 0); }
		public TerminalNode AUTO_INCREMENT() { return getToken(MySqlParser.AUTO_INCREMENT, 0); }
		public TerminalNode COMMENT() { return getToken(MySqlParser.COMMENT, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MySqlParser.STRING_LITERAL, 0); }
		public ColumnConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnConstraintContext columnConstraint() throws RecognitionException {
		ColumnConstraintContext _localctx = new ColumnConstraintContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_columnConstraint);
		int _la;
		try {
			setState(696);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case NULL_:
				enterOuterAlt(_localctx, 1);
				{
				setState(685);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(684);
					match(NOT);
					}
				}

				setState(687);
				match(NULL_);
				}
				break;
			case DEFAULT:
				enterOuterAlt(_localctx, 2);
				{
				setState(688);
				match(DEFAULT);
				setState(689);
				expression(0);
				}
				break;
			case PRIMARY:
				enterOuterAlt(_localctx, 3);
				{
				setState(690);
				match(PRIMARY);
				setState(691);
				match(KEY);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(692);
				match(UNIQUE);
				}
				break;
			case AUTO_INCREMENT:
				enterOuterAlt(_localctx, 5);
				{
				setState(693);
				match(AUTO_INCREMENT);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 6);
				{
				setState(694);
				match(COMMENT);
				setState(695);
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
	public static class TableConstraintContext extends ParserRuleContext {
		public TerminalNode PRIMARY() { return getToken(MySqlParser.PRIMARY, 0); }
		public TerminalNode KEY() { return getToken(MySqlParser.KEY, 0); }
		public List<TerminalNode> LPAREN() { return getTokens(MySqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(MySqlParser.LPAREN, i);
		}
		public IndexColumnListContext indexColumnList() {
			return getRuleContext(IndexColumnListContext.class,0);
		}
		public List<TerminalNode> RPAREN() { return getTokens(MySqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(MySqlParser.RPAREN, i);
		}
		public TerminalNode UNIQUE() { return getToken(MySqlParser.UNIQUE, 0); }
		public IndexNameContext indexName() {
			return getRuleContext(IndexNameContext.class,0);
		}
		public TerminalNode FOREIGN() { return getToken(MySqlParser.FOREIGN, 0); }
		public List<ColumnNameListContext> columnNameList() {
			return getRuleContexts(ColumnNameListContext.class);
		}
		public ColumnNameListContext columnNameList(int i) {
			return getRuleContext(ColumnNameListContext.class,i);
		}
		public TerminalNode REFERENCES() { return getToken(MySqlParser.REFERENCES, 0); }
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode INDEX() { return getToken(MySqlParser.INDEX, 0); }
		public TableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTableConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTableConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableConstraintContext tableConstraint() throws RecognitionException {
		TableConstraintContext _localctx = new TableConstraintContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_tableConstraint);
		int _la;
		try {
			setState(742);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRIMARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(698);
				match(PRIMARY);
				setState(699);
				match(KEY);
				setState(700);
				match(LPAREN);
				setState(701);
				indexColumnList();
				setState(702);
				match(RPAREN);
				}
				break;
			case UNIQUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(704);
				match(UNIQUE);
				setState(706);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==KEY) {
					{
					setState(705);
					match(KEY);
					}
				}

				setState(709);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BACKTICK_QUOTED_ID || _la==IDENTIFIER) {
					{
					setState(708);
					indexName();
					}
				}

				setState(711);
				match(LPAREN);
				setState(712);
				indexColumnList();
				setState(713);
				match(RPAREN);
				}
				break;
			case FOREIGN:
				enterOuterAlt(_localctx, 3);
				{
				setState(715);
				match(FOREIGN);
				setState(716);
				match(KEY);
				setState(717);
				match(LPAREN);
				setState(718);
				columnNameList();
				setState(719);
				match(RPAREN);
				setState(720);
				match(REFERENCES);
				setState(721);
				tableName();
				setState(722);
				match(LPAREN);
				setState(723);
				columnNameList();
				setState(724);
				match(RPAREN);
				}
				break;
			case INDEX:
				enterOuterAlt(_localctx, 4);
				{
				setState(726);
				match(INDEX);
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BACKTICK_QUOTED_ID || _la==IDENTIFIER) {
					{
					setState(727);
					indexName();
					}
				}

				setState(730);
				match(LPAREN);
				setState(731);
				indexColumnList();
				setState(732);
				match(RPAREN);
				}
				break;
			case KEY:
				enterOuterAlt(_localctx, 5);
				{
				setState(734);
				match(KEY);
				setState(736);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==BACKTICK_QUOTED_ID || _la==IDENTIFIER) {
					{
					setState(735);
					indexName();
					}
				}

				setState(738);
				match(LPAREN);
				setState(739);
				indexColumnList();
				setState(740);
				match(RPAREN);
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
	public static class TableOptionsContext extends ParserRuleContext {
		public List<TableOptionContext> tableOption() {
			return getRuleContexts(TableOptionContext.class);
		}
		public TableOptionContext tableOption(int i) {
			return getRuleContext(TableOptionContext.class,i);
		}
		public TableOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTableOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTableOptions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTableOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableOptionsContext tableOptions() throws RecognitionException {
		TableOptionsContext _localctx = new TableOptionsContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_tableOptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(745); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(744);
				tableOption();
				}
				}
				setState(747); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( ((((_la - 26)) & ~0x3f) == 0 && ((1L << (_la - 26)) & 2181431069507587L) != 0) );
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
	public static class TableOptionContext extends ParserRuleContext {
		public TerminalNode ENGINE() { return getToken(MySqlParser.ENGINE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(MySqlParser.IDENTIFIER, 0); }
		public TerminalNode EQ() { return getToken(MySqlParser.EQ, 0); }
		public TerminalNode CHARSET() { return getToken(MySqlParser.CHARSET, 0); }
		public TerminalNode DEFAULT() { return getToken(MySqlParser.DEFAULT, 0); }
		public TerminalNode CHARACTER() { return getToken(MySqlParser.CHARACTER, 0); }
		public TerminalNode SET() { return getToken(MySqlParser.SET, 0); }
		public TerminalNode COLLATE() { return getToken(MySqlParser.COLLATE, 0); }
		public TerminalNode COMMENT() { return getToken(MySqlParser.COMMENT, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MySqlParser.STRING_LITERAL, 0); }
		public TerminalNode AUTO_INCREMENT() { return getToken(MySqlParser.AUTO_INCREMENT, 0); }
		public TerminalNode INTEGER_LITERAL() { return getToken(MySqlParser.INTEGER_LITERAL, 0); }
		public TableOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTableOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTableOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTableOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableOptionContext tableOption() throws RecognitionException {
		TableOptionContext _localctx = new TableOptionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tableOption);
		int _la;
		try {
			setState(786);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(749);
				match(ENGINE);
				setState(751);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(750);
					match(EQ);
					}
				}

				setState(753);
				match(IDENTIFIER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(755);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DEFAULT) {
					{
					setState(754);
					match(DEFAULT);
					}
				}

				setState(757);
				match(CHARSET);
				setState(759);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(758);
					match(EQ);
					}
				}

				setState(761);
				match(IDENTIFIER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DEFAULT) {
					{
					setState(762);
					match(DEFAULT);
					}
				}

				setState(765);
				match(CHARACTER);
				setState(766);
				match(SET);
				setState(768);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(767);
					match(EQ);
					}
				}

				setState(770);
				match(IDENTIFIER);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(771);
				match(COLLATE);
				setState(773);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(772);
					match(EQ);
					}
				}

				setState(775);
				match(IDENTIFIER);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(776);
				match(COMMENT);
				setState(778);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(777);
					match(EQ);
					}
				}

				setState(780);
				match(STRING_LITERAL);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(781);
				match(AUTO_INCREMENT);
				setState(783);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EQ) {
					{
					setState(782);
					match(EQ);
					}
				}

				setState(785);
				match(INTEGER_LITERAL);
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
	public static class UpdateAssignmentContext extends ParserRuleContext {
		public ColumnRefContext columnRef() {
			return getRuleContext(ColumnRefContext.class,0);
		}
		public TerminalNode EQ() { return getToken(MySqlParser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UpdateAssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_updateAssignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterUpdateAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitUpdateAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitUpdateAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UpdateAssignmentContext updateAssignment() throws RecognitionException {
		UpdateAssignmentContext _localctx = new UpdateAssignmentContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_updateAssignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(788);
			columnRef();
			setState(789);
			match(EQ);
			setState(790);
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
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public ColumnNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnNameList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnNameList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameListContext columnNameList() throws RecognitionException {
		ColumnNameListContext _localctx = new ColumnNameListContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_columnNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			columnName();
			setState(797);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(793);
				match(COMMA);
				setState(794);
				columnName();
				}
				}
				setState(799);
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
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<TerminalNode> LPAREN() { return getTokens(MySqlParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(MySqlParser.LPAREN, i);
		}
		public List<TerminalNode> INTEGER_LITERAL() { return getTokens(MySqlParser.INTEGER_LITERAL); }
		public TerminalNode INTEGER_LITERAL(int i) {
			return getToken(MySqlParser.INTEGER_LITERAL, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(MySqlParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(MySqlParser.RPAREN, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public List<TerminalNode> ASC() { return getTokens(MySqlParser.ASC); }
		public TerminalNode ASC(int i) {
			return getToken(MySqlParser.ASC, i);
		}
		public List<TerminalNode> DESC() { return getTokens(MySqlParser.DESC); }
		public TerminalNode DESC(int i) {
			return getToken(MySqlParser.DESC, i);
		}
		public IndexColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexColumnList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterIndexColumnList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitIndexColumnList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitIndexColumnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexColumnListContext indexColumnList() throws RecognitionException {
		IndexColumnListContext _localctx = new IndexColumnListContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_indexColumnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(800);
			columnName();
			setState(804);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(801);
				match(LPAREN);
				setState(802);
				match(INTEGER_LITERAL);
				setState(803);
				match(RPAREN);
				}
			}

			setState(807);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DESC || _la==ASC) {
				{
				setState(806);
				_la = _input.LA(1);
				if ( !(_la==DESC || _la==ASC) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(821);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(809);
				match(COMMA);
				setState(810);
				columnName();
				setState(814);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAREN) {
					{
					setState(811);
					match(LPAREN);
					setState(812);
					match(INTEGER_LITERAL);
					setState(813);
					match(RPAREN);
					}
				}

				setState(817);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DESC || _la==ASC) {
					{
					setState(816);
					_la = _input.LA(1);
					if ( !(_la==DESC || _la==ASC) ) {
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
				setState(823);
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
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MySqlParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MySqlParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(824);
			expression(0);
			setState(829);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(825);
				match(COMMA);
				setState(826);
				expression(0);
				}
				}
				setState(831);
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
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MySqlParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(MySqlParser.STAR, 0); }
		public ColumnRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnRefContext columnRef() throws RecognitionException {
		ColumnRefContext _localctx = new ColumnRefContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_columnRef);
		try {
			setState(842);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(835);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(832);
					tableName();
					setState(833);
					match(DOT);
					}
					break;
				}
				setState(837);
				columnName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(838);
				tableName();
				setState(839);
				match(DOT);
				setState(840);
				match(STAR);
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
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MySqlParser.DOT, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(847);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
			case 1:
				{
				setState(844);
				databaseName();
				setState(845);
				match(DOT);
				}
				break;
			}
			setState(849);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterDatabaseName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitDatabaseName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitDatabaseName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DatabaseNameContext databaseName() throws RecognitionException {
		DatabaseNameContext _localctx = new DatabaseNameContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_databaseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(851);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterColumnName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitColumnName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitColumnName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(853);
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
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterIndexName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitIndexName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitIndexName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexNameContext indexName() throws RecognitionException {
		IndexNameContext _localctx = new IndexNameContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_indexName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(855);
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
		public TerminalNode STRING_LITERAL() { return getToken(MySqlParser.STRING_LITERAL, 0); }
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterAlias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitAlias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitAlias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_alias);
		try {
			setState(859);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BACKTICK_QUOTED_ID:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(857);
				identifier();
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(858);
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
		public TerminalNode IDENTIFIER() { return getToken(MySqlParser.IDENTIFIER, 0); }
		public TerminalNode BACKTICK_QUOTED_ID() { return getToken(MySqlParser.BACKTICK_QUOTED_ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(861);
			_la = _input.LA(1);
			if ( !(_la==BACKTICK_QUOTED_ID || _la==IDENTIFIER) ) {
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
		public TerminalNode INTEGER_LITERAL() { return getToken(MySqlParser.INTEGER_LITERAL, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(MySqlParser.DECIMAL_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(MySqlParser.STRING_LITERAL, 0); }
		public TerminalNode NULL_() { return getToken(MySqlParser.NULL_, 0); }
		public TerminalNode TRUE() { return getToken(MySqlParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MySqlParser.FALSE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MySqlParserListener ) ((MySqlParserListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MySqlParserVisitor ) return ((MySqlParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(863);
			_la = _input.LA(1);
			if ( !(_la==NULL_ || ((((_la - 103)) & ~0x3f) == 0 && ((1L << (_la - 103)) & 3670019L) != 0)) ) {
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
		case 32:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 11);
		case 4:
			return precpred(_ctx, 10);
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 12);
		case 8:
			return precpred(_ctx, 9);
		case 9:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0081\u0362\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
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
		"6\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u0001"+
		"t\b\u0001\u0005\u0001v\b\u0001\n\u0001\f\u0001y\t\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0080\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0003\u0003\u008a\b\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0091\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u0099"+
		"\b\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0003\u0006\u00a2\b\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00a9\b\u0006\n\u0006\f\u0006"+
		"\u00ac\t\u0006\u0001\u0006\u0001\u0006\u0005\u0006\u00b0\b\u0006\n\u0006"+
		"\f\u0006\u00b3\t\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00b7\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00bd\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0005\b\u00c7\b\b\n\b\f\b\u00ca\t\b\u0001\t\u0001\t\u0003\t\u00ce\b"+
		"\t\u0001\t\u0001\t\u0001\t\u0003\t\u00d3\b\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00d8\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00df\b\t"+
		"\u0001\n\u0001\n\u0003\n\u00e3\b\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u00e9\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00fd"+
		"\b\r\u0001\u000e\u0001\u000e\u0003\u000e\u0101\b\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0108\b\u000e\n"+
		"\u000e\f\u000e\u010b\t\u000e\u0003\u000e\u010d\b\u000e\u0001\u000e\u0005"+
		"\u000e\u0110\b\u000e\n\u000e\f\u000e\u0113\t\u000e\u0001\u000e\u0003\u000e"+
		"\u0116\b\u000e\u0001\u000e\u0003\u000e\u0119\b\u000e\u0001\u000e\u0003"+
		"\u000e\u011c\b\u000e\u0001\u000e\u0003\u000e\u011f\b\u000e\u0001\u000e"+
		"\u0003\u000e\u0122\b\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0126\b"+
		"\u000e\u0001\u000e\u0003\u000e\u0129\b\u000e\u0001\u000f\u0001\u000f\u0003"+
		"\u000f\u012d\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0003\u000f\u0134\b\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005"+
		"\u000f\u013f\b\u000f\n\u000f\f\u000f\u0142\t\u000f\u0001\u000f\u0003\u000f"+
		"\u0145\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010\u014d\b\u0010\n\u0010\f\u0010\u0150\t\u0010\u0001"+
		"\u0010\u0003\u0010\u0153\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0003\u0011\u0159\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0003\u0012\u015f\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0005\u0015\u016b\b\u0015\n\u0015\f\u0015\u016e\t\u0015\u0003\u0015"+
		"\u0170\b\u0015\u0001\u0016\u0001\u0016\u0003\u0016\u0174\b\u0016\u0001"+
		"\u0016\u0003\u0016\u0177\b\u0016\u0001\u0017\u0001\u0017\u0003\u0017\u017b"+
		"\b\u0017\u0001\u0017\u0003\u0017\u017e\b\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0003\u0017\u0184\b\u0017\u0001\u0017\u0003\u0017"+
		"\u0187\b\u0017\u0003\u0017\u0189\b\u0017\u0001\u0018\u0003\u0018\u018c"+
		"\b\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0192"+
		"\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0197\b\u0019"+
		"\u0001\u0019\u0001\u0019\u0003\u0019\u019b\b\u0019\u0001\u0019\u0003\u0019"+
		"\u019e\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0005\u001d\u01af\b\u001d"+
		"\n\u001d\f\u001d\u01b2\t\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u01b6"+
		"\b\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003\u001f\u01bc"+
		"\b\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0003"+
		"\u001f\u01c3\b\u001f\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 "+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u01d1\b \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0003 \u01f1\b \u0001"+
		" \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0005 \u01fc"+
		"\b \n \f \u01ff\t \u0001!\u0001!\u0001\"\u0001\"\u0003\"\u0205\b\"\u0001"+
		"\"\u0001\"\u0001\"\u0001\"\u0001\"\u0004\"\u020c\b\"\u000b\"\f\"\u020d"+
		"\u0001\"\u0001\"\u0003\"\u0212\b\"\u0001\"\u0001\"\u0001#\u0001#\u0001"+
		"#\u0003#\u0219\b#\u0001#\u0001#\u0003#\u021d\b#\u0001#\u0001#\u0001$\u0001"+
		"$\u0001%\u0001%\u0001%\u0005%\u0226\b%\n%\f%\u0229\t%\u0001&\u0001&\u0001"+
		"&\u0001&\u0003&\u022f\b&\u0001&\u0003&\u0232\b&\u0001&\u0001&\u0001&\u0001"+
		"&\u0003&\u0238\b&\u0001&\u0003&\u023b\b&\u0001&\u0001&\u0001&\u0001&\u0003"+
		"&\u0241\b&\u0001&\u0003&\u0244\b&\u0001&\u0001&\u0001&\u0001&\u0003&\u024a"+
		"\b&\u0001&\u0003&\u024d\b&\u0001&\u0001&\u0001&\u0001&\u0003&\u0253\b"+
		"&\u0001&\u0003&\u0256\b&\u0001&\u0001&\u0001&\u0001&\u0003&\u025c\b&\u0001"+
		"&\u0003&\u025f\b&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u0266\b&\u0001"+
		"&\u0003&\u0269\b&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u0270\b&\u0001"+
		"&\u0003&\u0273\b&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u027a\b&\u0001"+
		"&\u0003&\u027d\b&\u0001&\u0001&\u0001&\u0001&\u0001&\u0003&\u0284\b&\u0001"+
		"&\u0003&\u0287\b&\u0001&\u0001&\u0001&\u0001&\u0003&\u028d\b&\u0001&\u0001"+
		"&\u0001&\u0001&\u0003&\u0293\b&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"&\u0005&\u02a5\b&\n&\f&\u02a8\t&\u0001&\u0003&\u02ab\b&\u0001\'\u0003"+
		"\'\u02ae\b\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0003\'\u02b9\b\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0003(\u02c3\b(\u0001(\u0003(\u02c6\b(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0003(\u02d9\b(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0003(\u02e1\b(\u0001(\u0001(\u0001(\u0001(\u0003(\u02e7"+
		"\b(\u0001)\u0004)\u02ea\b)\u000b)\f)\u02eb\u0001*\u0001*\u0003*\u02f0"+
		"\b*\u0001*\u0001*\u0003*\u02f4\b*\u0001*\u0001*\u0003*\u02f8\b*\u0001"+
		"*\u0001*\u0003*\u02fc\b*\u0001*\u0001*\u0001*\u0003*\u0301\b*\u0001*\u0001"+
		"*\u0001*\u0003*\u0306\b*\u0001*\u0001*\u0001*\u0003*\u030b\b*\u0001*\u0001"+
		"*\u0001*\u0003*\u0310\b*\u0001*\u0003*\u0313\b*\u0001+\u0001+\u0001+\u0001"+
		"+\u0001,\u0001,\u0001,\u0005,\u031c\b,\n,\f,\u031f\t,\u0001-\u0001-\u0001"+
		"-\u0001-\u0003-\u0325\b-\u0001-\u0003-\u0328\b-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0003-\u032f\b-\u0001-\u0003-\u0332\b-\u0005-\u0334\b-\n-\f-"+
		"\u0337\t-\u0001.\u0001.\u0001.\u0005.\u033c\b.\n.\f.\u033f\t.\u0001/\u0001"+
		"/\u0001/\u0003/\u0344\b/\u0001/\u0001/\u0001/\u0001/\u0001/\u0003/\u034b"+
		"\b/\u00010\u00010\u00010\u00030\u0350\b0\u00010\u00010\u00011\u00011\u0001"+
		"2\u00012\u00013\u00013\u00014\u00014\u00034\u035c\b4\u00015\u00015\u0001"+
		"6\u00016\u00016\u0000\u0001@7\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPR"+
		"TVXZ\\^`bdfhjl\u0000\b\u0001\u0000#$\u0001\u0000$%\u0002\u0000iiwx\u0001"+
		"\u0000uv\u0001\u0000ot\u0002\u0000@F~~\u0001\u0000}~\u0003\u0000\u0019"+
		"\u0019ghz|\u03dd\u0000n\u0001\u0000\u0000\u0000\u0002w\u0001\u0000\u0000"+
		"\u0000\u0004\u007f\u0001\u0000\u0000\u0000\u0006\u0089\u0001\u0000\u0000"+
		"\u0000\b\u008b\u0001\u0000\u0000\u0000\n\u0094\u0001\u0000\u0000\u0000"+
		"\f\u009c\u0001\u0000\u0000\u0000\u000e\u00b8\u0001\u0000\u0000\u0000\u0010"+
		"\u00c0\u0001\u0000\u0000\u0000\u0012\u00de\u0001\u0000\u0000\u0000\u0014"+
		"\u00e0\u0001\u0000\u0000\u0000\u0016\u00e6\u0001\u0000\u0000\u0000\u0018"+
		"\u00f2\u0001\u0000\u0000\u0000\u001a\u00fc\u0001\u0000\u0000\u0000\u001c"+
		"\u00fe\u0001\u0000\u0000\u0000\u001e\u012a\u0001\u0000\u0000\u0000 \u0146"+
		"\u0001\u0000\u0000\u0000\"\u0154\u0001\u0000\u0000\u0000$\u015e\u0001"+
		"\u0000\u0000\u0000&\u0160\u0001\u0000\u0000\u0000(\u0163\u0001\u0000\u0000"+
		"\u0000*\u016f\u0001\u0000\u0000\u0000,\u0171\u0001\u0000\u0000\u0000."+
		"\u0188\u0001\u0000\u0000\u00000\u018b\u0001\u0000\u0000\u00002\u019d\u0001"+
		"\u0000\u0000\u00004\u019f\u0001\u0000\u0000\u00006\u01a2\u0001\u0000\u0000"+
		"\u00008\u01a6\u0001\u0000\u0000\u0000:\u01a9\u0001\u0000\u0000\u0000<"+
		"\u01b3\u0001\u0000\u0000\u0000>\u01c2\u0001\u0000\u0000\u0000@\u01d0\u0001"+
		"\u0000\u0000\u0000B\u0200\u0001\u0000\u0000\u0000D\u0202\u0001\u0000\u0000"+
		"\u0000F\u0215\u0001\u0000\u0000\u0000H\u0220\u0001\u0000\u0000\u0000J"+
		"\u0222\u0001\u0000\u0000\u0000L\u02aa\u0001\u0000\u0000\u0000N\u02b8\u0001"+
		"\u0000\u0000\u0000P\u02e6\u0001\u0000\u0000\u0000R\u02e9\u0001\u0000\u0000"+
		"\u0000T\u0312\u0001\u0000\u0000\u0000V\u0314\u0001\u0000\u0000\u0000X"+
		"\u0318\u0001\u0000\u0000\u0000Z\u0320\u0001\u0000\u0000\u0000\\\u0338"+
		"\u0001\u0000\u0000\u0000^\u034a\u0001\u0000\u0000\u0000`\u034f\u0001\u0000"+
		"\u0000\u0000b\u0353\u0001\u0000\u0000\u0000d\u0355\u0001\u0000\u0000\u0000"+
		"f\u0357\u0001\u0000\u0000\u0000h\u035b\u0001\u0000\u0000\u0000j\u035d"+
		"\u0001\u0000\u0000\u0000l\u035f\u0001\u0000\u0000\u0000no\u0003\u0002"+
		"\u0001\u0000op\u0005\u0000\u0000\u0001p\u0001\u0001\u0000\u0000\u0000"+
		"qs\u0003\u0004\u0002\u0000rt\u0005n\u0000\u0000sr\u0001\u0000\u0000\u0000"+
		"st\u0001\u0000\u0000\u0000tv\u0001\u0000\u0000\u0000uq\u0001\u0000\u0000"+
		"\u0000vy\u0001\u0000\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000"+
		"\u0000\u0000x\u0003\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000"+
		"z\u0080\u0003\u0006\u0003\u0000{\u0080\u0003\u001a\r\u0000|\u0080\u0003"+
		"$\u0012\u0000}\u0080\u0003&\u0013\u0000~\u0080\u0003(\u0014\u0000\u007f"+
		"z\u0001\u0000\u0000\u0000\u007f{\u0001\u0000\u0000\u0000\u007f|\u0001"+
		"\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u007f~\u0001\u0000\u0000"+
		"\u0000\u0080\u0005\u0001\u0000\u0000\u0000\u0081\u008a\u0003\b\u0004\u0000"+
		"\u0082\u008a\u0003\f\u0006\u0000\u0083\u008a\u0003\n\u0005\u0000\u0084"+
		"\u008a\u0003\u000e\u0007\u0000\u0085\u008a\u0003\u0010\b\u0000\u0086\u008a"+
		"\u0003\u0014\n\u0000\u0087\u008a\u0003\u0016\u000b\u0000\u0088\u008a\u0003"+
		"\u0018\f\u0000\u0089\u0081\u0001\u0000\u0000\u0000\u0089\u0082\u0001\u0000"+
		"\u0000\u0000\u0089\u0083\u0001\u0000\u0000\u0000\u0089\u0084\u0001\u0000"+
		"\u0000\u0000\u0089\u0085\u0001\u0000\u0000\u0000\u0089\u0086\u0001\u0000"+
		"\u0000\u0000\u0089\u0087\u0001\u0000\u0000\u0000\u0089\u0088\u0001\u0000"+
		"\u0000\u0000\u008a\u0007\u0001\u0000\u0000\u0000\u008b\u008c\u0005\r\u0000"+
		"\u0000\u008c\u0090\u0005\u001e\u0000\u0000\u008d\u008e\u0005\u001c\u0000"+
		"\u0000\u008e\u008f\u0005\u0006\u0000\u0000\u008f\u0091\u0005\u001d\u0000"+
		"\u0000\u0090\u008d\u0001\u0000\u0000\u0000\u0090\u0091\u0001\u0000\u0000"+
		"\u0000\u0091\u0092\u0001\u0000\u0000\u0000\u0092\u0093\u0003b1\u0000\u0093"+
		"\t\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u000f\u0000\u0000\u0095\u0098"+
		"\u0005\u001e\u0000\u0000\u0096\u0097\u0005\u001c\u0000\u0000\u0097\u0099"+
		"\u0005\u001d\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0098\u0099"+
		"\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000\u0000\u009a\u009b"+
		"\u0003b1\u0000\u009b\u000b\u0001\u0000\u0000\u0000\u009c\u009d\u0005\r"+
		"\u0000\u0000\u009d\u00a1\u0005\u000e\u0000\u0000\u009e\u009f\u0005\u001c"+
		"\u0000\u0000\u009f\u00a0\u0005\u0006\u0000\u0000\u00a0\u00a2\u0005\u001d"+
		"\u0000\u0000\u00a1\u009e\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a4\u0003`0\u0000"+
		"\u00a4\u00a5\u0005l\u0000\u0000\u00a5\u00aa\u0003J%\u0000\u00a6\u00a7"+
		"\u0005j\u0000\u0000\u00a7\u00a9\u0003J%\u0000\u00a8\u00a6\u0001\u0000"+
		"\u0000\u0000\u00a9\u00ac\u0001\u0000\u0000\u0000\u00aa\u00a8\u0001\u0000"+
		"\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00b1\u0001\u0000"+
		"\u0000\u0000\u00ac\u00aa\u0001\u0000\u0000\u0000\u00ad\u00ae\u0005j\u0000"+
		"\u0000\u00ae\u00b0\u0003P(\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1"+
		"\u00b2\u0001\u0000\u0000\u0000\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3"+
		"\u00b1\u0001\u0000\u0000\u0000\u00b4\u00b6\u0005m\u0000\u0000\u00b5\u00b7"+
		"\u0003R)\u0000\u00b6\u00b5\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\r\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005\u000f\u0000"+
		"\u0000\u00b9\u00bc\u0005\u000e\u0000\u0000\u00ba\u00bb\u0005\u001c\u0000"+
		"\u0000\u00bb\u00bd\u0005\u001d\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0001\u0000\u0000\u0000\u00bd\u00be\u0001\u0000\u0000"+
		"\u0000\u00be\u00bf\u0003`0\u0000\u00bf\u000f\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0005\u0010\u0000\u0000\u00c1\u00c2\u0005\u000e\u0000\u0000\u00c2"+
		"\u00c3\u0003`0\u0000\u00c3\u00c8\u0003\u0012\t\u0000\u00c4\u00c5\u0005"+
		"j\u0000\u0000\u00c5\u00c7\u0003\u0012\t\u0000\u00c6\u00c4\u0001\u0000"+
		"\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9\u0011\u0001\u0000"+
		"\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00cd\u0005\u0011"+
		"\u0000\u0000\u00cc\u00ce\u0005\u0012\u0000\u0000\u00cd\u00cc\u0001\u0000"+
		"\u0000\u0000\u00cd\u00ce\u0001\u0000\u0000\u0000\u00ce\u00cf\u0001\u0000"+
		"\u0000\u0000\u00cf\u00df\u0003J%\u0000\u00d0\u00d2\u0005\u000f\u0000\u0000"+
		"\u00d1\u00d3\u0005\u0012\u0000\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d4\u00df\u0003d2\u0000\u00d5\u00d7\u0005\u0010\u0000\u0000\u00d6\u00d8"+
		"\u0005\u0012\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0003d2\u0000\u00da\u00db\u0005\u000b\u0000\u0000\u00db\u00dc\u0005\u001a"+
		"\u0000\u0000\u00dc\u00dd\u0003@ \u0000\u00dd\u00df\u0001\u0000\u0000\u0000"+
		"\u00de\u00cb\u0001\u0000\u0000\u0000\u00de\u00d0\u0001\u0000\u0000\u0000"+
		"\u00de\u00d5\u0001\u0000\u0000\u0000\u00df\u0013\u0001\u0000\u0000\u0000"+
		"\u00e0\u00e2\u0005G\u0000\u0000\u00e1\u00e3\u0005\u000e\u0000\u0000\u00e2"+
		"\u00e1\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3"+
		"\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e5\u0003`0\u0000\u00e5\u0015\u0001"+
		"\u0000\u0000\u0000\u00e6\u00e8\u0005\r\u0000\u0000\u00e7\u00e9\u0005\u0018"+
		"\u0000\u0000\u00e8\u00e7\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000"+
		"\u0000\u0000\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0005\u0013"+
		"\u0000\u0000\u00eb\u00ec\u0003f3\u0000\u00ec\u00ed\u00052\u0000\u0000"+
		"\u00ed\u00ee\u0003`0\u0000\u00ee\u00ef\u0005l\u0000\u0000\u00ef\u00f0"+
		"\u0003Z-\u0000\u00f0\u00f1\u0005m\u0000\u0000\u00f1\u0017\u0001\u0000"+
		"\u0000\u0000\u00f2\u00f3\u0005\u000f\u0000\u0000\u00f3\u00f4\u0005\u0013"+
		"\u0000\u0000\u00f4\u00f5\u0003f3\u0000\u00f5\u00f6\u00052\u0000\u0000"+
		"\u00f6\u00f7\u0003`0\u0000\u00f7\u0019\u0001\u0000\u0000\u0000\u00f8\u00fd"+
		"\u0003\u001c\u000e\u0000\u00f9\u00fd\u0003\u001e\u000f\u0000\u00fa\u00fd"+
		"\u0003 \u0010\u0000\u00fb\u00fd\u0003\"\u0011\u0000\u00fc\u00f8\u0001"+
		"\u0000\u0000\u0000\u00fc\u00f9\u0001\u0000\u0000\u0000\u00fc\u00fa\u0001"+
		"\u0000\u0000\u0000\u00fc\u00fb\u0001\u0000\u0000\u0000\u00fd\u001b\u0001"+
		"\u0000\u0000\u0000\u00fe\u0100\u0005\u0001\u0000\u0000\u00ff\u0101\u0005"+
		"8\u0000\u0000\u0100\u00ff\u0001\u0000\u0000\u0000\u0100\u0101\u0001\u0000"+
		"\u0000\u0000\u0101\u0102\u0001\u0000\u0000\u0000\u0102\u010c\u0003*\u0015"+
		"\u0000\u0103\u0104\u0005\u0002\u0000\u0000\u0104\u0109\u0003.\u0017\u0000"+
		"\u0105\u0106\u0005j\u0000\u0000\u0106\u0108\u0003.\u0017\u0000\u0107\u0105"+
		"\u0001\u0000\u0000\u0000\u0108\u010b\u0001\u0000\u0000\u0000\u0109\u0107"+
		"\u0001\u0000\u0000\u0000\u0109\u010a\u0001\u0000\u0000\u0000\u010a\u010d"+
		"\u0001\u0000\u0000\u0000\u010b\u0109\u0001\u0000\u0000\u0000\u010c\u0103"+
		"\u0001\u0000\u0000\u0000\u010c\u010d\u0001\u0000\u0000\u0000\u010d\u0111"+
		"\u0001\u0000\u0000\u0000\u010e\u0110\u00030\u0018\u0000\u010f\u010e\u0001"+
		"\u0000\u0000\u0000\u0110\u0113\u0001\u0000\u0000\u0000\u0111\u010f\u0001"+
		"\u0000\u0000\u0000\u0111\u0112\u0001\u0000\u0000\u0000\u0112\u0115\u0001"+
		"\u0000\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0114\u0116\u0003"+
		"4\u001a\u0000\u0115\u0114\u0001\u0000\u0000\u0000\u0115\u0116\u0001\u0000"+
		"\u0000\u0000\u0116\u0118\u0001\u0000\u0000\u0000\u0117\u0119\u00036\u001b"+
		"\u0000\u0118\u0117\u0001\u0000\u0000\u0000\u0118\u0119\u0001\u0000\u0000"+
		"\u0000\u0119\u011b\u0001\u0000\u0000\u0000\u011a\u011c\u00038\u001c\u0000"+
		"\u011b\u011a\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000"+
		"\u011c\u011e\u0001\u0000\u0000\u0000\u011d\u011f\u0003:\u001d\u0000\u011e"+
		"\u011d\u0001\u0000\u0000\u0000\u011e\u011f\u0001\u0000\u0000\u0000\u011f"+
		"\u0121\u0001\u0000\u0000\u0000\u0120\u0122\u0003>\u001f\u0000\u0121\u0120"+
		"\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u0128"+
		"\u0001\u0000\u0000\u0000\u0123\u0125\u00059\u0000\u0000\u0124\u0126\u0005"+
		":\u0000\u0000\u0125\u0124\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000"+
		"\u0000\u0000\u0126\u0127\u0001\u0000\u0000\u0000\u0127\u0129\u0003\u001c"+
		"\u000e\u0000\u0128\u0123\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129\u001d\u0001\u0000\u0000\u0000\u012a\u012c\u0005\u0007"+
		"\u0000\u0000\u012b\u012d\u0005\b\u0000\u0000\u012c\u012b\u0001\u0000\u0000"+
		"\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d\u012e\u0001\u0000\u0000"+
		"\u0000\u012e\u0133\u0003`0\u0000\u012f\u0130\u0005l\u0000\u0000\u0130"+
		"\u0131\u0003X,\u0000\u0131\u0132\u0005m\u0000\u0000\u0132\u0134\u0001"+
		"\u0000\u0000\u0000\u0133\u012f\u0001\u0000\u0000\u0000\u0133\u0134\u0001"+
		"\u0000\u0000\u0000\u0134\u0144\u0001\u0000\u0000\u0000\u0135\u0136\u0005"+
		"\t\u0000\u0000\u0136\u0137\u0005l\u0000\u0000\u0137\u0138\u0003\\.\u0000"+
		"\u0138\u0140\u0005m\u0000\u0000\u0139\u013a\u0005j\u0000\u0000\u013a\u013b"+
		"\u0005l\u0000\u0000\u013b\u013c\u0003\\.\u0000\u013c\u013d\u0005m\u0000"+
		"\u0000\u013d\u013f\u0001\u0000\u0000\u0000\u013e\u0139\u0001\u0000\u0000"+
		"\u0000\u013f\u0142\u0001\u0000\u0000\u0000\u0140\u013e\u0001\u0000\u0000"+
		"\u0000\u0140\u0141\u0001\u0000\u0000\u0000\u0141\u0145\u0001\u0000\u0000"+
		"\u0000\u0142\u0140\u0001\u0000\u0000\u0000\u0143\u0145\u0003\u001c\u000e"+
		"\u0000\u0144\u0135\u0001\u0000\u0000\u0000\u0144\u0143\u0001\u0000\u0000"+
		"\u0000\u0145\u001f\u0001\u0000\u0000\u0000\u0146\u0147\u0005\n\u0000\u0000"+
		"\u0147\u0148\u0003`0\u0000\u0148\u0149\u0005\u000b\u0000\u0000\u0149\u014e"+
		"\u0003V+\u0000\u014a\u014b\u0005j\u0000\u0000\u014b\u014d\u0003V+\u0000"+
		"\u014c\u014a\u0001\u0000\u0000\u0000\u014d\u0150\u0001\u0000\u0000\u0000"+
		"\u014e\u014c\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000"+
		"\u014f\u0152\u0001\u0000\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000"+
		"\u0151\u0153\u00034\u001a\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0152"+
		"\u0153\u0001\u0000\u0000\u0000\u0153!\u0001\u0000\u0000\u0000\u0154\u0155"+
		"\u0005\f\u0000\u0000\u0155\u0156\u0005\u0002\u0000\u0000\u0156\u0158\u0003"+
		"`0\u0000\u0157\u0159\u00034\u001a\u0000\u0158\u0157\u0001\u0000\u0000"+
		"\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159#\u0001\u0000\u0000\u0000"+
		"\u015a\u015b\u0005 \u0000\u0000\u015b\u015f\u0005!\u0000\u0000\u015c\u015d"+
		"\u0005 \u0000\u0000\u015d\u015f\u0005\"\u0000\u0000\u015e\u015a\u0001"+
		"\u0000\u0000\u0000\u015e\u015c\u0001\u0000\u0000\u0000\u015f%\u0001\u0000"+
		"\u0000\u0000\u0160\u0161\u0005\u001f\u0000\u0000\u0161\u0162\u0003b1\u0000"+
		"\u0162\'\u0001\u0000\u0000\u0000\u0163\u0164\u0007\u0000\u0000\u0000\u0164"+
		"\u0165\u0003`0\u0000\u0165)\u0001\u0000\u0000\u0000\u0166\u0170\u0005"+
		"i\u0000\u0000\u0167\u016c\u0003,\u0016\u0000\u0168\u0169\u0005j\u0000"+
		"\u0000\u0169\u016b\u0003,\u0016\u0000\u016a\u0168\u0001\u0000\u0000\u0000"+
		"\u016b\u016e\u0001\u0000\u0000\u0000\u016c\u016a\u0001\u0000\u0000\u0000"+
		"\u016c\u016d\u0001\u0000\u0000\u0000\u016d\u0170\u0001\u0000\u0000\u0000"+
		"\u016e\u016c\u0001\u0000\u0000\u0000\u016f\u0166\u0001\u0000\u0000\u0000"+
		"\u016f\u0167\u0001\u0000\u0000\u0000\u0170+\u0001\u0000\u0000\u0000\u0171"+
		"\u0176\u0003@ \u0000\u0172\u0174\u00053\u0000\u0000\u0173\u0172\u0001"+
		"\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174\u0175\u0001"+
		"\u0000\u0000\u0000\u0175\u0177\u0003h4\u0000\u0176\u0173\u0001\u0000\u0000"+
		"\u0000\u0176\u0177\u0001\u0000\u0000\u0000\u0177-\u0001\u0000\u0000\u0000"+
		"\u0178\u017d\u0003`0\u0000\u0179\u017b\u00053\u0000\u0000\u017a\u0179"+
		"\u0001\u0000\u0000\u0000\u017a\u017b\u0001\u0000\u0000\u0000\u017b\u017c"+
		"\u0001\u0000\u0000\u0000\u017c\u017e\u0003h4\u0000\u017d\u017a\u0001\u0000"+
		"\u0000\u0000\u017d\u017e\u0001\u0000\u0000\u0000\u017e\u0189\u0001\u0000"+
		"\u0000\u0000\u017f\u0180\u0005l\u0000\u0000\u0180\u0181\u0003\u001c\u000e"+
		"\u0000\u0181\u0186\u0005m\u0000\u0000\u0182\u0184\u00053\u0000\u0000\u0183"+
		"\u0182\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000\u0000\u0000\u0184"+
		"\u0185\u0001\u0000\u0000\u0000\u0185\u0187\u0003h4\u0000\u0186\u0183\u0001"+
		"\u0000\u0000\u0000\u0186\u0187\u0001\u0000\u0000\u0000\u0187\u0189\u0001"+
		"\u0000\u0000\u0000\u0188\u0178\u0001\u0000\u0000\u0000\u0188\u017f\u0001"+
		"\u0000\u0000\u0000\u0189/\u0001\u0000\u0000\u0000\u018a\u018c\u00032\u0019"+
		"\u0000\u018b\u018a\u0001\u0000\u0000\u0000\u018b\u018c\u0001\u0000\u0000"+
		"\u0000\u018c\u018d\u0001\u0000\u0000\u0000\u018d\u018e\u0005,\u0000\u0000"+
		"\u018e\u0191\u0003.\u0017\u0000\u018f\u0190\u00052\u0000\u0000\u0190\u0192"+
		"\u0003@ \u0000\u0191\u018f\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000"+
		"\u0000\u0000\u01921\u0001\u0000\u0000\u0000\u0193\u019e\u0005-\u0000\u0000"+
		"\u0194\u0196\u0005.\u0000\u0000\u0195\u0197\u00050\u0000\u0000\u0196\u0195"+
		"\u0001\u0000\u0000\u0000\u0196\u0197\u0001\u0000\u0000\u0000\u0197\u019e"+
		"\u0001\u0000\u0000\u0000\u0198\u019a\u0005/\u0000\u0000\u0199\u019b\u0005"+
		"0\u0000\u0000\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000"+
		"\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019e\u00051\u0000"+
		"\u0000\u019d\u0193\u0001\u0000\u0000\u0000\u019d\u0194\u0001\u0000\u0000"+
		"\u0000\u019d\u0198\u0001\u0000\u0000\u0000\u019d\u019c\u0001\u0000\u0000"+
		"\u0000\u019e3\u0001\u0000\u0000\u0000\u019f\u01a0\u0005\u0003\u0000\u0000"+
		"\u01a0\u01a1\u0003@ \u0000\u01a15\u0001\u0000\u0000\u0000\u01a2\u01a3"+
		"\u0005(\u0000\u0000\u01a3\u01a4\u0005\'\u0000\u0000\u01a4\u01a5\u0003"+
		"\\.\u0000\u01a57\u0001\u0000\u0000\u0000\u01a6\u01a7\u0005)\u0000\u0000"+
		"\u01a7\u01a8\u0003@ \u0000\u01a89\u0001\u0000\u0000\u0000\u01a9\u01aa"+
		"\u0005&\u0000\u0000\u01aa\u01ab\u0005\'\u0000\u0000\u01ab\u01b0\u0003"+
		"<\u001e\u0000\u01ac\u01ad\u0005j\u0000\u0000\u01ad\u01af\u0003<\u001e"+
		"\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01af\u01b2\u0001\u0000\u0000"+
		"\u0000\u01b0\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000"+
		"\u0000\u01b1;\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b5\u0003@ \u0000\u01b4\u01b6\u0007\u0001\u0000\u0000\u01b5\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b5\u01b6\u0001\u0000\u0000\u0000\u01b6=\u0001"+
		"\u0000\u0000\u0000\u01b7\u01b8\u0005*\u0000\u0000\u01b8\u01bb\u0003@ "+
		"\u0000\u01b9\u01ba\u0005j\u0000\u0000\u01ba\u01bc\u0003@ \u0000\u01bb"+
		"\u01b9\u0001\u0000\u0000\u0000\u01bb\u01bc\u0001\u0000\u0000\u0000\u01bc"+
		"\u01c3\u0001\u0000\u0000\u0000\u01bd\u01be\u0005*\u0000\u0000\u01be\u01bf"+
		"\u0003@ \u0000\u01bf\u01c0\u0005+\u0000\u0000\u01c0\u01c1\u0003@ \u0000"+
		"\u01c1\u01c3\u0001\u0000\u0000\u0000\u01c2\u01b7\u0001\u0000\u0000\u0000"+
		"\u01c2\u01bd\u0001\u0000\u0000\u0000\u01c3?\u0001\u0000\u0000\u0000\u01c4"+
		"\u01c5\u0006 \uffff\uffff\u0000\u01c5\u01c6\u0005l\u0000\u0000\u01c6\u01c7"+
		"\u0003@ \u0000\u01c7\u01c8\u0005m\u0000\u0000\u01c8\u01d1\u0001\u0000"+
		"\u0000\u0000\u01c9\u01ca\u0005\u0006\u0000\u0000\u01ca\u01d1\u0003@ \u0010"+
		"\u01cb\u01d1\u0003F#\u0000\u01cc\u01d1\u0003D\"\u0000\u01cd\u01d1\u0003"+
		"l6\u0000\u01ce\u01d1\u0003^/\u0000\u01cf\u01d1\u0005i\u0000\u0000\u01d0"+
		"\u01c4\u0001\u0000\u0000\u0000\u01d0\u01c9\u0001\u0000\u0000\u0000\u01d0"+
		"\u01cb\u0001\u0000\u0000\u0000\u01d0\u01cc\u0001\u0000\u0000\u0000\u01d0"+
		"\u01cd\u0001\u0000\u0000\u0000\u01d0\u01ce\u0001\u0000\u0000\u0000\u01d0"+
		"\u01cf\u0001\u0000\u0000\u0000\u01d1\u01fd\u0001\u0000\u0000\u0000\u01d2"+
		"\u01d3\n\u000f\u0000\u0000\u01d3\u01d4\u0005\u0004\u0000\u0000\u01d4\u01fc"+
		"\u0003@ \u0010\u01d5\u01d6\n\u000e\u0000\u0000\u01d6\u01d7\u0005\u0005"+
		"\u0000\u0000\u01d7\u01fc\u0003@ \u000f\u01d8\u01d9\n\r\u0000\u0000\u01d9"+
		"\u01da\u0003B!\u0000\u01da\u01db\u0003@ \u000e\u01db\u01fc\u0001\u0000"+
		"\u0000\u0000\u01dc\u01dd\n\u000b\u0000\u0000\u01dd\u01de\u00055\u0000"+
		"\u0000\u01de\u01df\u0003@ \u0000\u01df\u01e0\u0005\u0004\u0000\u0000\u01e0"+
		"\u01e1\u0003@ \f\u01e1\u01fc\u0001\u0000\u0000\u0000\u01e2\u01e3\n\n\u0000"+
		"\u0000\u01e3\u01e4\u00056\u0000\u0000\u01e4\u01fc\u0003@ \u000b\u01e5"+
		"\u01e6\n\u0005\u0000\u0000\u01e6\u01e7\u0007\u0002\u0000\u0000\u01e7\u01fc"+
		"\u0003@ \u0006\u01e8\u01e9\n\u0004\u0000\u0000\u01e9\u01ea\u0007\u0003"+
		"\u0000\u0000\u01ea\u01fc\u0003@ \u0005\u01eb\u01ec\n\f\u0000\u0000\u01ec"+
		"\u01ed\u00054\u0000\u0000\u01ed\u01f0\u0005l\u0000\u0000\u01ee\u01f1\u0003"+
		"\\.\u0000\u01ef\u01f1\u0003\u001c\u000e\u0000\u01f0\u01ee\u0001\u0000"+
		"\u0000\u0000\u01f0\u01ef\u0001\u0000\u0000\u0000\u01f1\u01f2\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f3\u0005m\u0000\u0000\u01f3\u01fc\u0001\u0000\u0000"+
		"\u0000\u01f4\u01f5\n\t\u0000\u0000\u01f5\u01f6\u00057\u0000\u0000\u01f6"+
		"\u01fc\u0005\u0019\u0000\u0000\u01f7\u01f8\n\b\u0000\u0000\u01f8\u01f9"+
		"\u00057\u0000\u0000\u01f9\u01fa\u0005\u0006\u0000\u0000\u01fa\u01fc\u0005"+
		"\u0019\u0000\u0000\u01fb\u01d2\u0001\u0000\u0000\u0000\u01fb\u01d5\u0001"+
		"\u0000\u0000\u0000\u01fb\u01d8\u0001\u0000\u0000\u0000\u01fb\u01dc\u0001"+
		"\u0000\u0000\u0000\u01fb\u01e2\u0001\u0000\u0000\u0000\u01fb\u01e5\u0001"+
		"\u0000\u0000\u0000\u01fb\u01e8\u0001\u0000\u0000\u0000\u01fb\u01eb\u0001"+
		"\u0000\u0000\u0000\u01fb\u01f4\u0001\u0000\u0000\u0000\u01fb\u01f7\u0001"+
		"\u0000\u0000\u0000\u01fc\u01ff\u0001\u0000\u0000\u0000\u01fd\u01fb\u0001"+
		"\u0000\u0000\u0000\u01fd\u01fe\u0001\u0000\u0000\u0000\u01feA\u0001\u0000"+
		"\u0000\u0000\u01ff\u01fd\u0001\u0000\u0000\u0000\u0200\u0201\u0007\u0004"+
		"\u0000\u0000\u0201C\u0001\u0000\u0000\u0000\u0202\u0204\u0005;\u0000\u0000"+
		"\u0203\u0205\u0003@ \u0000\u0204\u0203\u0001\u0000\u0000\u0000\u0204\u0205"+
		"\u0001\u0000\u0000\u0000\u0205\u020b\u0001\u0000\u0000\u0000\u0206\u0207"+
		"\u0005<\u0000\u0000\u0207\u0208\u0003@ \u0000\u0208\u0209\u0005=\u0000"+
		"\u0000\u0209\u020a\u0003@ \u0000\u020a\u020c\u0001\u0000\u0000\u0000\u020b"+
		"\u0206\u0001\u0000\u0000\u0000\u020c\u020d\u0001\u0000\u0000\u0000\u020d"+
		"\u020b\u0001\u0000\u0000\u0000\u020d\u020e\u0001\u0000\u0000\u0000\u020e"+
		"\u0211\u0001\u0000\u0000\u0000\u020f\u0210\u0005>\u0000\u0000\u0210\u0212"+
		"\u0003@ \u0000\u0211\u020f\u0001\u0000\u0000\u0000\u0211\u0212\u0001\u0000"+
		"\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213\u0214\u0005?\u0000"+
		"\u0000\u0214E\u0001\u0000\u0000\u0000\u0215\u0216\u0003H$\u0000\u0216"+
		"\u021c\u0005l\u0000\u0000\u0217\u0219\u00058\u0000\u0000\u0218\u0217\u0001"+
		"\u0000\u0000\u0000\u0218\u0219\u0001\u0000\u0000\u0000\u0219\u021a\u0001"+
		"\u0000\u0000\u0000\u021a\u021d\u0003\\.\u0000\u021b\u021d\u0005i\u0000"+
		"\u0000\u021c\u0218\u0001\u0000\u0000\u0000\u021c\u021b\u0001\u0000\u0000"+
		"\u0000\u021c\u021d\u0001\u0000\u0000\u0000\u021d\u021e\u0001\u0000\u0000"+
		"\u0000\u021e\u021f\u0005m\u0000\u0000\u021fG\u0001\u0000\u0000\u0000\u0220"+
		"\u0221\u0007\u0005\u0000\u0000\u0221I\u0001\u0000\u0000\u0000\u0222\u0223"+
		"\u0003d2\u0000\u0223\u0227\u0003L&\u0000\u0224\u0226\u0003N\'\u0000\u0225"+
		"\u0224\u0001\u0000\u0000\u0000\u0226\u0229\u0001\u0000\u0000\u0000\u0227"+
		"\u0225\u0001\u0000\u0000\u0000\u0227\u0228\u0001\u0000\u0000\u0000\u0228"+
		"K\u0001\u0000\u0000\u0000\u0229\u0227\u0001\u0000\u0000\u0000\u022a\u022e"+
		"\u0005O\u0000\u0000\u022b\u022c\u0005l\u0000\u0000\u022c\u022d\u0005z"+
		"\u0000\u0000\u022d\u022f\u0005m\u0000\u0000\u022e\u022b\u0001\u0000\u0000"+
		"\u0000\u022e\u022f\u0001\u0000\u0000\u0000\u022f\u0231\u0001\u0000\u0000"+
		"\u0000\u0230\u0232\u0005M\u0000\u0000\u0231\u0230\u0001\u0000\u0000\u0000"+
		"\u0231\u0232\u0001\u0000\u0000\u0000\u0232\u02ab\u0001\u0000\u0000\u0000"+
		"\u0233\u0237\u0005P\u0000\u0000\u0234\u0235\u0005l\u0000\u0000\u0235\u0236"+
		"\u0005z\u0000\u0000\u0236\u0238\u0005m\u0000\u0000\u0237\u0234\u0001\u0000"+
		"\u0000\u0000\u0237\u0238\u0001\u0000\u0000\u0000\u0238\u023a\u0001\u0000"+
		"\u0000\u0000\u0239\u023b\u0005M\u0000\u0000\u023a\u0239\u0001\u0000\u0000"+
		"\u0000\u023a\u023b\u0001\u0000\u0000\u0000\u023b\u02ab\u0001\u0000\u0000"+
		"\u0000\u023c\u0240\u0005Q\u0000\u0000\u023d\u023e\u0005l\u0000\u0000\u023e"+
		"\u023f\u0005z\u0000\u0000\u023f\u0241\u0005m\u0000\u0000\u0240\u023d\u0001"+
		"\u0000\u0000\u0000\u0240\u0241\u0001\u0000\u0000\u0000\u0241\u0243\u0001"+
		"\u0000\u0000\u0000\u0242\u0244\u0005M\u0000\u0000\u0243\u0242\u0001\u0000"+
		"\u0000\u0000\u0243\u0244\u0001\u0000\u0000\u0000\u0244\u02ab\u0001\u0000"+
		"\u0000\u0000\u0245\u0249\u0005R\u0000\u0000\u0246\u0247\u0005l\u0000\u0000"+
		"\u0247\u0248\u0005z\u0000\u0000\u0248\u024a\u0005m\u0000\u0000\u0249\u0246"+
		"\u0001\u0000\u0000\u0000\u0249\u024a\u0001\u0000\u0000\u0000\u024a\u024c"+
		"\u0001\u0000\u0000\u0000\u024b\u024d\u0005M\u0000\u0000\u024c\u024b\u0001"+
		"\u0000\u0000\u0000\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u02ab\u0001"+
		"\u0000\u0000\u0000\u024e\u0252\u0005S\u0000\u0000\u024f\u0250\u0005l\u0000"+
		"\u0000\u0250\u0251\u0005z\u0000\u0000\u0251\u0253\u0005m\u0000\u0000\u0252"+
		"\u024f\u0001\u0000\u0000\u0000\u0252\u0253\u0001\u0000\u0000\u0000\u0253"+
		"\u0255\u0001\u0000\u0000\u0000\u0254\u0256\u0005M\u0000\u0000\u0255\u0254"+
		"\u0001\u0000\u0000\u0000\u0255\u0256\u0001\u0000\u0000\u0000\u0256\u02ab"+
		"\u0001\u0000\u0000\u0000\u0257\u025b\u0005T\u0000\u0000\u0258\u0259\u0005"+
		"l\u0000\u0000\u0259\u025a\u0005z\u0000\u0000\u025a\u025c\u0005m\u0000"+
		"\u0000\u025b\u0258\u0001\u0000\u0000\u0000\u025b\u025c\u0001\u0000\u0000"+
		"\u0000\u025c\u025e\u0001\u0000\u0000\u0000\u025d\u025f\u0005M\u0000\u0000"+
		"\u025e\u025d\u0001\u0000\u0000\u0000\u025e\u025f\u0001\u0000\u0000\u0000"+
		"\u025f\u02ab\u0001\u0000\u0000\u0000\u0260\u0268\u0005U\u0000\u0000\u0261"+
		"\u0262\u0005l\u0000\u0000\u0262\u0265\u0005z\u0000\u0000\u0263\u0264\u0005"+
		"j\u0000\u0000\u0264\u0266\u0005z\u0000\u0000\u0265\u0263\u0001\u0000\u0000"+
		"\u0000\u0265\u0266\u0001\u0000\u0000\u0000\u0266\u0267\u0001\u0000\u0000"+
		"\u0000\u0267\u0269\u0005m\u0000\u0000\u0268\u0261\u0001\u0000\u0000\u0000"+
		"\u0268\u0269\u0001\u0000\u0000\u0000\u0269\u02ab\u0001\u0000\u0000\u0000"+
		"\u026a\u0272\u0005V\u0000\u0000\u026b\u026c\u0005l\u0000\u0000\u026c\u026f"+
		"\u0005z\u0000\u0000\u026d\u026e\u0005j\u0000\u0000\u026e\u0270\u0005z"+
		"\u0000\u0000\u026f\u026d\u0001\u0000\u0000\u0000\u026f\u0270\u0001\u0000"+
		"\u0000\u0000\u0270\u0271\u0001\u0000\u0000\u0000\u0271\u0273\u0005m\u0000"+
		"\u0000\u0272\u026b\u0001\u0000\u0000\u0000\u0272\u0273\u0001\u0000\u0000"+
		"\u0000\u0273\u02ab\u0001\u0000\u0000\u0000\u0274\u027c\u0005W\u0000\u0000"+
		"\u0275\u0276\u0005l\u0000\u0000\u0276\u0279\u0005z\u0000\u0000\u0277\u0278"+
		"\u0005j\u0000\u0000\u0278\u027a\u0005z\u0000\u0000\u0279\u0277\u0001\u0000"+
		"\u0000\u0000\u0279\u027a\u0001\u0000\u0000\u0000\u027a\u027b\u0001\u0000"+
		"\u0000\u0000\u027b\u027d\u0005m\u0000\u0000\u027c\u0275\u0001\u0000\u0000"+
		"\u0000\u027c\u027d\u0001\u0000\u0000\u0000\u027d\u02ab\u0001\u0000\u0000"+
		"\u0000\u027e\u0286\u0005X\u0000\u0000\u027f\u0280\u0005l\u0000\u0000\u0280"+
		"\u0283\u0005z\u0000\u0000\u0281\u0282\u0005j\u0000\u0000\u0282\u0284\u0005"+
		"z\u0000\u0000\u0283\u0281\u0001\u0000\u0000\u0000\u0283\u0284\u0001\u0000"+
		"\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285\u0287\u0005m\u0000"+
		"\u0000\u0286\u027f\u0001\u0000\u0000\u0000\u0286\u0287\u0001\u0000\u0000"+
		"\u0000\u0287\u02ab\u0001\u0000\u0000\u0000\u0288\u028c\u0005Y\u0000\u0000"+
		"\u0289\u028a\u0005l\u0000\u0000\u028a\u028b\u0005z\u0000\u0000\u028b\u028d"+
		"\u0005m\u0000\u0000\u028c\u0289\u0001\u0000\u0000\u0000\u028c\u028d\u0001"+
		"\u0000\u0000\u0000\u028d\u02ab\u0001\u0000\u0000\u0000\u028e\u0292\u0005"+
		"Z\u0000\u0000\u028f\u0290\u0005l\u0000\u0000\u0290\u0291\u0005z\u0000"+
		"\u0000\u0291\u0293\u0005m\u0000\u0000\u0292\u028f\u0001\u0000\u0000\u0000"+
		"\u0292\u0293\u0001\u0000\u0000\u0000\u0293\u02ab\u0001\u0000\u0000\u0000"+
		"\u0294\u02ab\u0005[\u0000\u0000\u0295\u02ab\u0005\\\u0000\u0000\u0296"+
		"\u02ab\u0005]\u0000\u0000\u0297\u02ab\u0005^\u0000\u0000\u0298\u02ab\u0005"+
		"_\u0000\u0000\u0299\u02ab\u0005`\u0000\u0000\u029a\u02ab\u0005a\u0000"+
		"\u0000\u029b\u02ab\u0005b\u0000\u0000\u029c\u02ab\u0005c\u0000\u0000\u029d"+
		"\u02ab\u0005d\u0000\u0000\u029e\u02ab\u0005e\u0000\u0000\u029f\u02a0\u0005"+
		"f\u0000\u0000\u02a0\u02a1\u0005l\u0000\u0000\u02a1\u02a6\u0005|\u0000"+
		"\u0000\u02a2\u02a3\u0005j\u0000\u0000\u02a3\u02a5\u0005|\u0000\u0000\u02a4"+
		"\u02a2\u0001\u0000\u0000\u0000\u02a5\u02a8\u0001\u0000\u0000\u0000\u02a6"+
		"\u02a4\u0001\u0000\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7"+
		"\u02a9\u0001\u0000\u0000\u0000\u02a8\u02a6\u0001\u0000\u0000\u0000\u02a9"+
		"\u02ab\u0005m\u0000\u0000\u02aa\u022a\u0001\u0000\u0000\u0000\u02aa\u0233"+
		"\u0001\u0000\u0000\u0000\u02aa\u023c\u0001\u0000\u0000\u0000\u02aa\u0245"+
		"\u0001\u0000\u0000\u0000\u02aa\u024e\u0001\u0000\u0000\u0000\u02aa\u0257"+
		"\u0001\u0000\u0000\u0000\u02aa\u0260\u0001\u0000\u0000\u0000\u02aa\u026a"+
		"\u0001\u0000\u0000\u0000\u02aa\u0274\u0001\u0000\u0000\u0000\u02aa\u027e"+
		"\u0001\u0000\u0000\u0000\u02aa\u0288\u0001\u0000\u0000\u0000\u02aa\u028e"+
		"\u0001\u0000\u0000\u0000\u02aa\u0294\u0001\u0000\u0000\u0000\u02aa\u0295"+
		"\u0001\u0000\u0000\u0000\u02aa\u0296\u0001\u0000\u0000\u0000\u02aa\u0297"+
		"\u0001\u0000\u0000\u0000\u02aa\u0298\u0001\u0000\u0000\u0000\u02aa\u0299"+
		"\u0001\u0000\u0000\u0000\u02aa\u029a\u0001\u0000\u0000\u0000\u02aa\u029b"+
		"\u0001\u0000\u0000\u0000\u02aa\u029c\u0001\u0000\u0000\u0000\u02aa\u029d"+
		"\u0001\u0000\u0000\u0000\u02aa\u029e\u0001\u0000\u0000\u0000\u02aa\u029f"+
		"\u0001\u0000\u0000\u0000\u02abM\u0001\u0000\u0000\u0000\u02ac\u02ae\u0005"+
		"\u0006\u0000\u0000\u02ad\u02ac\u0001\u0000\u0000\u0000\u02ad\u02ae\u0001"+
		"\u0000\u0000\u0000\u02ae\u02af\u0001\u0000\u0000\u0000\u02af\u02b9\u0005"+
		"\u0019\u0000\u0000\u02b0\u02b1\u0005\u001a\u0000\u0000\u02b1\u02b9\u0003"+
		"@ \u0000\u02b2\u02b3\u0005\u0014\u0000\u0000\u02b3\u02b9\u0005\u0015\u0000"+
		"\u0000\u02b4\u02b9\u0005\u0018\u0000\u0000\u02b5\u02b9\u0005\u001b\u0000"+
		"\u0000\u02b6\u02b7\u0005H\u0000\u0000\u02b7\u02b9\u0005|\u0000\u0000\u02b8"+
		"\u02ad\u0001\u0000\u0000\u0000\u02b8\u02b0\u0001\u0000\u0000\u0000\u02b8"+
		"\u02b2\u0001\u0000\u0000\u0000\u02b8\u02b4\u0001\u0000\u0000\u0000\u02b8"+
		"\u02b5\u0001\u0000\u0000\u0000\u02b8\u02b6\u0001\u0000\u0000\u0000\u02b9"+
		"O\u0001\u0000\u0000\u0000\u02ba\u02bb\u0005\u0014\u0000\u0000\u02bb\u02bc"+
		"\u0005\u0015\u0000\u0000\u02bc\u02bd\u0005l\u0000\u0000\u02bd\u02be\u0003"+
		"Z-\u0000\u02be\u02bf\u0005m\u0000\u0000\u02bf\u02e7\u0001\u0000\u0000"+
		"\u0000\u02c0\u02c2\u0005\u0018\u0000\u0000\u02c1\u02c3\u0005\u0015\u0000"+
		"\u0000\u02c2\u02c1\u0001\u0000\u0000\u0000\u02c2\u02c3\u0001\u0000\u0000"+
		"\u0000\u02c3\u02c5\u0001\u0000\u0000\u0000\u02c4\u02c6\u0003f3\u0000\u02c5"+
		"\u02c4\u0001\u0000\u0000\u0000\u02c5\u02c6\u0001\u0000\u0000\u0000\u02c6"+
		"\u02c7\u0001\u0000\u0000\u0000\u02c7\u02c8\u0005l\u0000\u0000\u02c8\u02c9"+
		"\u0003Z-\u0000\u02c9\u02ca\u0005m\u0000\u0000\u02ca\u02e7\u0001\u0000"+
		"\u0000\u0000\u02cb\u02cc\u0005\u0016\u0000\u0000\u02cc\u02cd\u0005\u0015"+
		"\u0000\u0000\u02cd\u02ce\u0005l\u0000\u0000\u02ce\u02cf\u0003X,\u0000"+
		"\u02cf\u02d0\u0005m\u0000\u0000\u02d0\u02d1\u0005\u0017\u0000\u0000\u02d1"+
		"\u02d2\u0003`0\u0000\u02d2\u02d3\u0005l\u0000\u0000\u02d3\u02d4\u0003"+
		"X,\u0000\u02d4\u02d5\u0005m\u0000\u0000\u02d5\u02e7\u0001\u0000\u0000"+
		"\u0000\u02d6\u02d8\u0005\u0013\u0000\u0000\u02d7\u02d9\u0003f3\u0000\u02d8"+
		"\u02d7\u0001\u0000\u0000\u0000\u02d8\u02d9\u0001\u0000\u0000\u0000\u02d9"+
		"\u02da\u0001\u0000\u0000\u0000\u02da\u02db\u0005l\u0000\u0000\u02db\u02dc"+
		"\u0003Z-\u0000\u02dc\u02dd\u0005m\u0000\u0000\u02dd\u02e7\u0001\u0000"+
		"\u0000\u0000\u02de\u02e0\u0005\u0015\u0000\u0000\u02df\u02e1\u0003f3\u0000"+
		"\u02e0\u02df\u0001\u0000\u0000\u0000\u02e0\u02e1\u0001\u0000\u0000\u0000"+
		"\u02e1\u02e2\u0001\u0000\u0000\u0000\u02e2\u02e3\u0005l\u0000\u0000\u02e3"+
		"\u02e4\u0003Z-\u0000\u02e4\u02e5\u0005m\u0000\u0000\u02e5\u02e7\u0001"+
		"\u0000\u0000\u0000\u02e6\u02ba\u0001\u0000\u0000\u0000\u02e6\u02c0\u0001"+
		"\u0000\u0000\u0000\u02e6\u02cb\u0001\u0000\u0000\u0000\u02e6\u02d6\u0001"+
		"\u0000\u0000\u0000\u02e6\u02de\u0001\u0000\u0000\u0000\u02e7Q\u0001\u0000"+
		"\u0000\u0000\u02e8\u02ea\u0003T*\u0000\u02e9\u02e8\u0001\u0000\u0000\u0000"+
		"\u02ea\u02eb\u0001\u0000\u0000\u0000\u02eb\u02e9\u0001\u0000\u0000\u0000"+
		"\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ecS\u0001\u0000\u0000\u0000\u02ed"+
		"\u02ef\u0005I\u0000\u0000\u02ee\u02f0\u0005o\u0000\u0000\u02ef\u02ee\u0001"+
		"\u0000\u0000\u0000\u02ef\u02f0\u0001\u0000\u0000\u0000\u02f0\u02f1\u0001"+
		"\u0000\u0000\u0000\u02f1\u0313\u0005~\u0000\u0000\u02f2\u02f4\u0005\u001a"+
		"\u0000\u0000\u02f3\u02f2\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000"+
		"\u0000\u0000\u02f4\u02f5\u0001\u0000\u0000\u0000\u02f5\u02f7\u0005J\u0000"+
		"\u0000\u02f6\u02f8\u0005o\u0000\u0000\u02f7\u02f6\u0001\u0000\u0000\u0000"+
		"\u02f7\u02f8\u0001\u0000\u0000\u0000\u02f8\u02f9\u0001\u0000\u0000\u0000"+
		"\u02f9\u0313\u0005~\u0000\u0000\u02fa\u02fc\u0005\u001a\u0000\u0000\u02fb"+
		"\u02fa\u0001\u0000\u0000\u0000\u02fb\u02fc\u0001\u0000\u0000\u0000\u02fc"+
		"\u02fd\u0001\u0000\u0000\u0000\u02fd\u02fe\u0005K\u0000\u0000\u02fe\u0300"+
		"\u0005\u000b\u0000\u0000\u02ff\u0301\u0005o\u0000\u0000\u0300\u02ff\u0001"+
		"\u0000\u0000\u0000\u0300\u0301\u0001\u0000\u0000\u0000\u0301\u0302\u0001"+
		"\u0000\u0000\u0000\u0302\u0313\u0005~\u0000\u0000\u0303\u0305\u0005L\u0000"+
		"\u0000\u0304\u0306\u0005o\u0000\u0000\u0305\u0304\u0001\u0000\u0000\u0000"+
		"\u0305\u0306\u0001\u0000\u0000\u0000\u0306\u0307\u0001\u0000\u0000\u0000"+
		"\u0307\u0313\u0005~\u0000\u0000\u0308\u030a\u0005H\u0000\u0000\u0309\u030b"+
		"\u0005o\u0000\u0000\u030a\u0309\u0001\u0000\u0000\u0000\u030a\u030b\u0001"+
		"\u0000\u0000\u0000\u030b\u030c\u0001\u0000\u0000\u0000\u030c\u0313\u0005"+
		"|\u0000\u0000\u030d\u030f\u0005\u001b\u0000\u0000\u030e\u0310\u0005o\u0000"+
		"\u0000\u030f\u030e\u0001\u0000\u0000\u0000\u030f\u0310\u0001\u0000\u0000"+
		"\u0000\u0310\u0311\u0001\u0000\u0000\u0000\u0311\u0313\u0005z\u0000\u0000"+
		"\u0312\u02ed\u0001\u0000\u0000\u0000\u0312\u02f3\u0001\u0000\u0000\u0000"+
		"\u0312\u02fb\u0001\u0000\u0000\u0000\u0312\u0303\u0001\u0000\u0000\u0000"+
		"\u0312\u0308\u0001\u0000\u0000\u0000\u0312\u030d\u0001\u0000\u0000\u0000"+
		"\u0313U\u0001\u0000\u0000\u0000\u0314\u0315\u0003^/\u0000\u0315\u0316"+
		"\u0005o\u0000\u0000\u0316\u0317\u0003@ \u0000\u0317W\u0001\u0000\u0000"+
		"\u0000\u0318\u031d\u0003d2\u0000\u0319\u031a\u0005j\u0000\u0000\u031a"+
		"\u031c\u0003d2\u0000\u031b\u0319\u0001\u0000\u0000\u0000\u031c\u031f\u0001"+
		"\u0000\u0000\u0000\u031d\u031b\u0001\u0000\u0000\u0000\u031d\u031e\u0001"+
		"\u0000\u0000\u0000\u031eY\u0001\u0000\u0000\u0000\u031f\u031d\u0001\u0000"+
		"\u0000\u0000\u0320\u0324\u0003d2\u0000\u0321\u0322\u0005l\u0000\u0000"+
		"\u0322\u0323\u0005z\u0000\u0000\u0323\u0325\u0005m\u0000\u0000\u0324\u0321"+
		"\u0001\u0000\u0000\u0000\u0324\u0325\u0001\u0000\u0000\u0000\u0325\u0327"+
		"\u0001\u0000\u0000\u0000\u0326\u0328\u0007\u0001\u0000\u0000\u0327\u0326"+
		"\u0001\u0000\u0000\u0000\u0327\u0328\u0001\u0000\u0000\u0000\u0328\u0335"+
		"\u0001\u0000\u0000\u0000\u0329\u032a\u0005j\u0000\u0000\u032a\u032e\u0003"+
		"d2\u0000\u032b\u032c\u0005l\u0000\u0000\u032c\u032d\u0005z\u0000\u0000"+
		"\u032d\u032f\u0005m\u0000\u0000\u032e\u032b\u0001\u0000\u0000\u0000\u032e"+
		"\u032f\u0001\u0000\u0000\u0000\u032f\u0331\u0001\u0000\u0000\u0000\u0330"+
		"\u0332\u0007\u0001\u0000\u0000\u0331\u0330\u0001\u0000\u0000\u0000\u0331"+
		"\u0332\u0001\u0000\u0000\u0000\u0332\u0334\u0001\u0000\u0000\u0000\u0333"+
		"\u0329\u0001\u0000\u0000\u0000\u0334\u0337\u0001\u0000\u0000\u0000\u0335"+
		"\u0333\u0001\u0000\u0000\u0000\u0335\u0336\u0001\u0000\u0000\u0000\u0336"+
		"[\u0001\u0000\u0000\u0000\u0337\u0335\u0001\u0000\u0000\u0000\u0338\u033d"+
		"\u0003@ \u0000\u0339\u033a\u0005j\u0000\u0000\u033a\u033c\u0003@ \u0000"+
		"\u033b\u0339\u0001\u0000\u0000\u0000\u033c\u033f\u0001\u0000\u0000\u0000"+
		"\u033d\u033b\u0001\u0000\u0000\u0000\u033d\u033e\u0001\u0000\u0000\u0000"+
		"\u033e]\u0001\u0000\u0000\u0000\u033f\u033d\u0001\u0000\u0000\u0000\u0340"+
		"\u0341\u0003`0\u0000\u0341\u0342\u0005k\u0000\u0000\u0342\u0344\u0001"+
		"\u0000\u0000\u0000\u0343\u0340\u0001\u0000\u0000\u0000\u0343\u0344\u0001"+
		"\u0000\u0000\u0000\u0344\u0345\u0001\u0000\u0000\u0000\u0345\u034b\u0003"+
		"d2\u0000\u0346\u0347\u0003`0\u0000\u0347\u0348\u0005k\u0000\u0000\u0348"+
		"\u0349\u0005i\u0000\u0000\u0349\u034b\u0001\u0000\u0000\u0000\u034a\u0343"+
		"\u0001\u0000\u0000\u0000\u034a\u0346\u0001\u0000\u0000\u0000\u034b_\u0001"+
		"\u0000\u0000\u0000\u034c\u034d\u0003b1\u0000\u034d\u034e\u0005k\u0000"+
		"\u0000\u034e\u0350\u0001\u0000\u0000\u0000\u034f\u034c\u0001\u0000\u0000"+
		"\u0000\u034f\u0350\u0001\u0000\u0000\u0000\u0350\u0351\u0001\u0000\u0000"+
		"\u0000\u0351\u0352\u0003j5\u0000\u0352a\u0001\u0000\u0000\u0000\u0353"+
		"\u0354\u0003j5\u0000\u0354c\u0001\u0000\u0000\u0000\u0355\u0356\u0003"+
		"j5\u0000\u0356e\u0001\u0000\u0000\u0000\u0357\u0358\u0003j5\u0000\u0358"+
		"g\u0001\u0000\u0000\u0000\u0359\u035c\u0003j5\u0000\u035a\u035c\u0005"+
		"|\u0000\u0000\u035b\u0359\u0001\u0000\u0000\u0000\u035b\u035a\u0001\u0000"+
		"\u0000\u0000\u035ci\u0001\u0000\u0000\u0000\u035d\u035e\u0007\u0006\u0000"+
		"\u0000\u035ek\u0001\u0000\u0000\u0000\u035f\u0360\u0007\u0007\u0000\u0000"+
		"\u0360m\u0001\u0000\u0000\u0000vsw\u007f\u0089\u0090\u0098\u00a1\u00aa"+
		"\u00b1\u00b6\u00bc\u00c8\u00cd\u00d2\u00d7\u00de\u00e2\u00e8\u00fc\u0100"+
		"\u0109\u010c\u0111\u0115\u0118\u011b\u011e\u0121\u0125\u0128\u012c\u0133"+
		"\u0140\u0144\u014e\u0152\u0158\u015e\u016c\u016f\u0173\u0176\u017a\u017d"+
		"\u0183\u0186\u0188\u018b\u0191\u0196\u019a\u019d\u01b0\u01b5\u01bb\u01c2"+
		"\u01d0\u01f0\u01fb\u01fd\u0204\u020d\u0211\u0218\u021c\u0227\u022e\u0231"+
		"\u0237\u023a\u0240\u0243\u0249\u024c\u0252\u0255\u025b\u025e\u0265\u0268"+
		"\u026f\u0272\u0279\u027c\u0283\u0286\u028c\u0292\u02a6\u02aa\u02ad\u02b8"+
		"\u02c2\u02c5\u02d8\u02e0\u02e6\u02eb\u02ef\u02f3\u02f7\u02fb\u0300\u0305"+
		"\u030a\u030f\u0312\u031d\u0324\u0327\u032e\u0331\u0335\u033d\u0343\u034a"+
		"\u034f\u035b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}