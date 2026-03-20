parser grammar SparkSqlParser;

options { tokenVocab=SparkSqlLexer; }

@header {
package com.example.antlr.sparksql;
}

// ============================================================
// Root rule
// ============================================================

root
    : sqlStatements EOF
    ;

sqlStatements
    : (sqlStatement SEMICOLON?)*
    ;

sqlStatement
    : ddlStatement
    | dmlStatement
    | utilityStatement
    ;

// ============================================================
// DDL Statements
// ============================================================

ddlStatement
    : createDatabase
    | dropDatabase
    | createTable
    | dropTable
    | alterTable
    | createView
    | dropView
    ;

createDatabase
    : CREATE (DATABASE | SCHEMA) (IF_ NOT EXISTS)? databaseName
      (COMMENT STRING_LITERAL)?
      (LOCATION STRING_LITERAL)?
      (WITH TBLPROPERTIES LPAREN propertyList RPAREN)?
    ;

dropDatabase
    : DROP (DATABASE | SCHEMA) (IF_ EXISTS)? databaseName (CASCADE | RESTRICT)?
    ;

createTable
    : CREATE EXTERNAL? TABLE (IF_ NOT EXISTS)? tableName
      LPAREN columnDefinition (COMMA columnDefinition)* RPAREN
      (USING dataSourceFormat)?
      (OPTIONS LPAREN propertyList RPAREN)?
      (PARTITIONED BY LPAREN partitionColumnList RPAREN)?
      (CLUSTERED BY LPAREN columnNameList RPAREN (SORTED BY LPAREN sortColumnList RPAREN)? INTO INTEGER_LITERAL BUCKETS)?
      (ROW FORMAT rowFormat)?
      (STORED AS dataSourceFormat)?
      (LOCATION STRING_LITERAL)?
      (COMMENT STRING_LITERAL)?
      (TBLPROPERTIES LPAREN propertyList RPAREN)?
    | CREATE TABLE (IF_ NOT EXISTS)? tableName
      USING dataSourceFormat
      (OPTIONS LPAREN propertyList RPAREN)?
      (PARTITIONED BY LPAREN columnNameList RPAREN)?
      (COMMENT STRING_LITERAL)?
      (AS selectStatement)?
    ;

dropTable
    : DROP TABLE (IF_ EXISTS)? tableName
    ;

alterTable
    : ALTER TABLE tableName RENAME TO tableName                              # renameTable
    | ALTER TABLE tableName ADD COLUMNS? LPAREN columnDefinition (COMMA columnDefinition)* RPAREN   # addColumns
    | ALTER TABLE tableName DROP COLUMN? columnName                            # dropColumn
    | ALTER TABLE tableName SET TBLPROPERTIES LPAREN propertyList RPAREN       # setTableProperties
    | ALTER TABLE tableName ADD (IF_ NOT EXISTS)? PARTITION partitionSpec      # addPartition
    | ALTER TABLE tableName DROP (IF_ EXISTS)? PARTITION partitionSpec         # dropPartition
    ;

createView
    : CREATE (OR REPLACE)? (GLOBAL? TEMPORARY)? VIEW (IF_ NOT EXISTS)? viewName
      (LPAREN columnNameList RPAREN)?
      (COMMENT STRING_LITERAL)?
      AS selectStatement
    ;

dropView
    : DROP VIEW (IF_ EXISTS)? viewName
    ;

// ============================================================
// DML Statements
// ============================================================

dmlStatement
    : withClause? selectStatement
    | insertStatement
    ;

withClause
    : WITH cteDefinition (COMMA cteDefinition)*
    ;

cteDefinition
    : cteName (LPAREN columnNameList RPAREN)? AS LPAREN selectStatement RPAREN
    ;

selectStatement
    : SELECT DISTINCT? selectElements
      (FROM tableSource (COMMA tableSource)*)?
      lateralViewClause*
      joinClause*
      whereClause?
      groupByClause?
      havingClause?
      windowClause?
      orderByClause?
      sortByClause?
      distributeByClause?
      clusterByClause?
      limitClause?
      ((UNION | INTERSECT | EXCEPT) ALL? selectStatement)?
    ;

insertStatement
    : INSERT (INTO | OVERWRITE) TABLE? tableName
      (PARTITION partitionSpec)?
      (LPAREN columnNameList RPAREN)?
      (selectStatement | VALUES LPAREN expressionList RPAREN (COMMA LPAREN expressionList RPAREN)*)
    ;

// ============================================================
// Utility Statements
// ============================================================

utilityStatement
    : useStatement
    | showStatement
    | describeStatement
    | explainStatement
    | cacheStatement
    | uncacheStatement
    | refreshStatement
    ;

useStatement
    : USE_ databaseName
    ;

showStatement
    : SHOW (DATABASES | SCHEMAS)
    | SHOW TABLES (IN databaseName)?
    | SHOW VIEWS (IN databaseName)?
    | SHOW COLUMNS (IN | FROM) tableName
    | SHOW PARTITIONS tableName partitionSpec?
    | SHOW FUNCTIONS
    | SHOW CREATE TABLE tableName
    ;

describeStatement
    : (DESCRIBE | DESC) (EXTENDED | FORMATTED)? tableName columnName?
    ;

explainStatement
    : EXPLAIN (EXTENDED)? sqlStatement
    ;

cacheStatement
    : CACHE LAZY? TABLE tableName (OPTIONS LPAREN propertyList RPAREN)? (AS selectStatement)?
    ;

uncacheStatement
    : UNCACHE TABLE (IF_ EXISTS)? tableName
    ;

refreshStatement
    : REFRESH TABLE tableName
    ;

// ============================================================
// Select Elements
// ============================================================

selectElements
    : STAR
    | selectElement (COMMA selectElement)*
    ;

selectElement
    : expression (AS? alias)?
    ;

// ============================================================
// Table Source & Joins
// ============================================================

tableSource
    : tableName (AS? alias)? tableSample?
    | LPAREN selectStatement RPAREN (AS? alias)?
    ;

tableSample
    : TABLESAMPLE LPAREN (expression (PERCENT | ROWS))? RPAREN
    ;

joinClause
    : joinType? JOIN tableSource (ON expression)?
    ;

joinType
    : INNER
    | LEFT OUTER?
    | RIGHT OUTER?
    | FULL OUTER?
    | CROSS
    | LEFT SEMI
    | LEFT ANTI
    ;

lateralViewClause
    : LATERAL VIEW functionCall alias AS? columnNameList
    ;

// ============================================================
// Clauses
// ============================================================

whereClause
    : WHERE expression
    ;

groupByClause
    : GROUP BY expressionList
    ;

havingClause
    : HAVING expression
    ;

orderByClause
    : ORDER BY orderByElement (COMMA orderByElement)*
    ;

sortByClause
    : SORT BY orderByElement (COMMA orderByElement)*
    ;

distributeByClause
    : DISTRIBUTE BY expressionList
    ;

clusterByClause
    : CLUSTER BY expressionList
    ;

orderByElement
    : expression (ASC | DESC)?
    ;

limitClause
    : LIMIT (expression | ALL)
    ;

windowClause
    : WINDOW namedWindow (COMMA namedWindow)*
    ;

namedWindow
    : identifier AS windowSpec
    ;

windowSpec
    : LPAREN
      (PARTITION BY expressionList)?
      (ORDER BY orderByElement (COMMA orderByElement)*)?
      windowFrame?
      RPAREN
    ;

windowFrame
    : (ROWS | RANGE) frameBound
    | (ROWS | RANGE) BETWEEN frameBound AND frameBound
    ;

frameBound
    : UNBOUNDED PRECEDING
    | UNBOUNDED FOLLOWING
    | CURRENT ROW
    | expression PRECEDING
    | expression FOLLOWING
    ;

// ============================================================
// Expressions
// ============================================================

expression
    : LPAREN expression RPAREN                                          # parenExpression
    | NOT expression                                                     # notExpression
    | expression AND expression                                          # andExpression
    | expression OR expression                                           # orExpression
    | expression comparisonOperator expression                           # comparisonExpression
    | expression IN LPAREN (expressionList | selectStatement) RPAREN     # inExpression
    | expression BETWEEN expression AND expression                       # betweenExpression
    | expression LIKE expression                                         # likeExpression
    | expression RLIKE expression                                        # rlikeExpression
    | expression REGEXP expression                                       # regexpExpression
    | expression IS NULL_                                                # isNullExpression
    | expression IS NOT NULL_                                            # isNotNullExpression
    | expression LBRACKET expression RBRACKET                            # arrayAccessExpression
    | expression DOT expression                                          # dotExpression
    | functionCall (OVER windowSpec)?                                     # functionCallExpression
    | caseExpression                                                     # caseExpr
    | expression (STAR | DIVIDE | MOD) expression                        # mulDivModExpression
    | expression (PLUS | MINUS) expression                               # addSubExpression
    | expression (AMPERSAND | PIPE | CARET) expression                   # bitwiseExpression
    | expression CONCAT expression                                       # concatExpression
    | CAST LPAREN expression AS dataType RPAREN                          # castExpression
    | structExpression                                                    # structExpr
    | arrayExpression                                                     # arrayExpr
    | mapExpression                                                       # mapExpr
    | literal                                                            # literalExpression
    | columnRef                                                          # columnRefExpression
    | STAR                                                               # starExpression
    ;

structExpression
    : STRUCT LPAREN (namedExpression (COMMA namedExpression)*)? RPAREN
    ;

arrayExpression
    : ARRAY LPAREN expressionList? RPAREN
    ;

mapExpression
    : MAP LPAREN (expression COMMA expression (COMMA expression COMMA expression)*)? RPAREN
    ;

namedExpression
    : expression (AS? alias)?
    ;

comparisonOperator
    : EQ | NEQ | LT | GT | LTE | GTE
    ;

caseExpression
    : CASE expression? (WHEN expression THEN expression)+ (ELSE expression)? END
    ;

functionCall
    : functionName LPAREN (DISTINCT? expressionList | STAR)? RPAREN
    ;

functionName
    : COUNT | SUM | AVG | MIN | MAX | CAST | COALESCE
    | IDENTIFIER
    ;

// ============================================================
// Column & Table definitions
// ============================================================

columnDefinition
    : columnName dataType (NOT NULL_)? (DEFAULT expression)? (COMMENT STRING_LITERAL)?
    ;

dataType
    : TINYINT
    | SMALLINT
    | INT
    | INTEGER
    | BIGINT
    | FLOAT
    | DOUBLE
    | DECIMAL (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | DEC (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | NUMERIC (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | STRING
    | CHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | VARCHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | BINARY
    | BOOLEAN
    | DATE
    | TIMESTAMP
    | INTERVAL
    | ARRAY LT dataType GT
    | MAP LT dataType COMMA dataType GT
    | STRUCT LT structField (COMMA structField)* GT
    | IDENTIFIER
    ;

structField
    : identifier COLON dataType (COMMENT STRING_LITERAL)?
    ;

// ============================================================
// Partition & Properties
// ============================================================

partitionSpec
    : LPAREN partitionVal (COMMA partitionVal)* RPAREN
    ;

partitionVal
    : identifier (EQ expression)?
    ;

partitionColumnList
    : columnDefinition (COMMA columnDefinition)*
    | columnNameList
    ;

sortColumnList
    : columnName (ASC | DESC)? (COMMA columnName (ASC | DESC)?)*
    ;

propertyList
    : property (COMMA property)*
    ;

property
    : STRING_LITERAL EQ? STRING_LITERAL
    | identifier EQ? STRING_LITERAL
    ;

dataSourceFormat
    : IDENTIFIER
    | STRING_LITERAL
    ;

rowFormat
    : DELIMITED (FIELDS TERMINATED BY STRING_LITERAL)? (LINES TERMINATED BY STRING_LITERAL)?
    | SERDE STRING_LITERAL (WITH SERDEPROPERTIES LPAREN propertyList RPAREN)?
    ;

// ============================================================
// Helpers
// ============================================================

columnNameList
    : columnName (COMMA columnName)*
    ;

expressionList
    : expression (COMMA expression)*
    ;

columnRef
    : (tableName DOT)? columnName
    | tableName DOT STAR
    ;

tableName
    : (databaseName DOT)? identifier
    ;

databaseName
    : identifier
    ;

viewName
    : (databaseName DOT)? identifier
    ;

columnName
    : identifier
    ;

indexName
    : identifier
    ;

cteName
    : identifier
    ;

alias
    : identifier
    | STRING_LITERAL
    ;

identifier
    : IDENTIFIER
    | BACKTICK_QUOTED_ID
    ;

literal
    : INTEGER_LITERAL
    | DECIMAL_LITERAL
    | STRING_LITERAL
    | NULL_
    | TRUE
    | FALSE
    | INTERVAL expression IDENTIFIER
    ;


