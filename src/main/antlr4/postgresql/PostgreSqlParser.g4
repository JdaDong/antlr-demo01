parser grammar PostgreSqlParser;

options { tokenVocab=PostgreSqlLexer; }

@header {
package com.example.antlr.postgresql;
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
    ;

// ============================================================
// DDL Statements
// ============================================================

ddlStatement
    : createDatabase
    | createSchema
    | createTable
    | dropDatabase
    | dropTable
    | alterTable
    | createIndex
    | dropIndex
    ;

createDatabase
    : CREATE DATABASE (IF_ NOT EXISTS)? databaseName
    ;

createSchema
    : CREATE SCHEMA (IF_ NOT EXISTS)? schemaName
    ;

dropDatabase
    : DROP DATABASE (IF_ EXISTS)? databaseName
    ;

createTable
    : CREATE (TEMP | TEMPORARY | UNLOGGED)? TABLE (IF_ NOT EXISTS)? tableName
      LPAREN tableElement (COMMA tableElement)* RPAREN
      (INHERITS LPAREN tableName (COMMA tableName)* RPAREN)?
      (PARTITION BY partitionStrategy LPAREN columnName (COMMA columnName)* RPAREN)?
    ;

tableElement
    : columnDefinition
    | tableConstraint
    ;

dropTable
    : DROP TABLE (IF_ EXISTS)? tableName (CASCADE | RESTRICT)?
    ;

alterTable
    : ALTER TABLE (ONLY)? tableName alterTableAction (COMMA alterTableAction)*
    ;

alterTableAction
    : ADD COLUMN? (IF_ NOT EXISTS)? columnDefinition
    | DROP COLUMN? (IF_ EXISTS)? columnName (CASCADE | RESTRICT)?
    | ALTER COLUMN? columnName SET DEFAULT expression
    | ALTER COLUMN? columnName DROP DEFAULT
    | ADD tableConstraint
    | DROP CONSTRAINT constraintName (CASCADE | RESTRICT)?
    ;

createIndex
    : CREATE UNIQUE? INDEX CONCURRENTLY? (IF_ NOT EXISTS)? indexName?
      ON tableName (USING indexMethod)?
      LPAREN indexColumnList RPAREN
    ;

dropIndex
    : DROP INDEX CONCURRENTLY? (IF_ EXISTS)? indexName (CASCADE | RESTRICT)?
    ;

// ============================================================
// DML Statements
// ============================================================

dmlStatement
    : withClause? selectStatement
    | insertStatement
    | updateStatement
    | deleteStatement
    ;

withClause
    : WITH RECURSIVE? cteDefinition (COMMA cteDefinition)*
    ;

cteDefinition
    : cteName (LPAREN columnNameList RPAREN)? AS LPAREN selectStatement RPAREN
    ;

selectStatement
    : SELECT DISTINCT? selectElements
      (FROM tableSource (COMMA tableSource)*)?
      joinClause*
      whereClause?
      groupByClause?
      havingClause?
      orderByClause?
      limitClause?
      offsetClause?
      ((UNION | INTERSECT | EXCEPT) ALL? selectStatement)?
    ;

insertStatement
    : INSERT INTO tableName
      (LPAREN columnNameList RPAREN)?
      (VALUES LPAREN expressionList RPAREN (COMMA LPAREN expressionList RPAREN)*
      | selectStatement)
      (RETURNING selectElements)?
    ;

updateStatement
    : UPDATE (ONLY)? tableName (AS? alias)?
      SET updateAssignment (COMMA updateAssignment)*
      (FROM tableSource (COMMA tableSource)*)?
      whereClause?
      (RETURNING selectElements)?
    ;

deleteStatement
    : DELETE FROM (ONLY)? tableName (AS? alias)?
      (USING tableSource (COMMA tableSource)*)?
      whereClause?
      (RETURNING selectElements)?
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
    : tableName (AS? alias)?
    | LPAREN selectStatement RPAREN (AS? alias)?
    ;

joinClause
    : joinType? JOIN tableSource (ON expression | USING LPAREN columnNameList RPAREN)?
    ;

joinType
    : INNER
    | LEFT OUTER?
    | RIGHT OUTER?
    | FULL OUTER?
    | CROSS
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

orderByElement
    : expression (ASC | DESC)? (NULLS (FIRST | LAST))?
    ;

limitClause
    : LIMIT (expression | ALL)
    ;

offsetClause
    : OFFSET expression
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
    | expression ILIKE expression                                        # ilikeExpression
    | expression IS NULL_                                                # isNullExpression
    | expression IS NOT NULL_                                            # isNotNullExpression
    | expression CONCAT expression                                       # concatExpression
    | expression CAST_OP dataType                                        # pgCastExpression
    | expression ARROW expression                                        # jsonAccessExpression
    | expression DOUBLE_ARROW expression                                 # jsonTextAccessExpression
    | expression LBRACKET expression RBRACKET                            # arrayAccessExpression
    | functionCall                                                       # functionCallExpression
    | caseExpression                                                     # caseExpr
    | expression (STAR | DIVIDE | MOD) expression                        # mulDivModExpression
    | expression (PLUS | MINUS) expression                               # addSubExpression
    | CAST LPAREN expression AS dataType RPAREN                          # castExpression
    | literal                                                            # literalExpression
    | columnRef                                                          # columnRefExpression
    | STAR                                                               # starExpression
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
    : columnName dataType columnConstraint*
    ;

dataType
    : SMALLINT
    | INT
    | INTEGER
    | BIGINT
    | SERIAL
    | BIGSERIAL
    | SMALLSERIAL
    | REAL
    | FLOAT
    | DOUBLE PRECISION
    | DECIMAL (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | NUMERIC (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | CHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | VARCHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | TEXT
    | BOOLEAN
    | BOOL
    | DATE
    | TIMESTAMP
    | TIME
    | INTERVAL
    | JSON
    | JSONB
    | UUID
    | BYTEA
    | INET
    | CIDR
    | MACADDR
    | MONEY
    | XML
    | ARRAY
    | IDENTIFIER (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    ;

columnConstraint
    : (CONSTRAINT constraintName)?
      ( NOT? NULL_
      | DEFAULT expression
      | PRIMARY KEY
      | UNIQUE
      | CHECK LPAREN expression RPAREN
      | REFERENCES tableName (LPAREN columnName RPAREN)?
      )
    ;

tableConstraint
    : (CONSTRAINT constraintName)?
      ( PRIMARY KEY LPAREN columnNameList RPAREN
      | UNIQUE LPAREN columnNameList RPAREN
      | FOREIGN KEY LPAREN columnNameList RPAREN REFERENCES tableName LPAREN columnNameList RPAREN
      | CHECK LPAREN expression RPAREN
      )
    ;

partitionStrategy
    : RANGE | LIST | HASH
    ;

indexMethod
    : IDENTIFIER
    ;

// ============================================================
// Helpers
// ============================================================

updateAssignment
    : columnRef EQ expression
    ;

columnNameList
    : columnName (COMMA columnName)*
    ;

indexColumnList
    : indexColumn (COMMA indexColumn)*
    ;

indexColumn
    : columnName (ASC | DESC)? (NULLS (FIRST | LAST))?
    ;

expressionList
    : expression (COMMA expression)*
    ;

columnRef
    : identifier DOT identifier DOT identifier
    | identifier DOT identifier
    | identifier DOT STAR
    | identifier
    ;

tableName
    : (schemaName DOT)? identifier
    ;

databaseName
    : identifier
    ;

schemaName
    : identifier
    ;

columnName
    : identifier
    ;

indexName
    : identifier
    ;

constraintName
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
    | DOUBLE_QUOTED_ID
    | nonReservedKeyword
    ;

// 非保留关键字：可在标识符位置使用
nonReservedKeyword
    : KEY | INDEX | COLUMN | COMMENT
    | CASCADE | RESTRICT | CONSTRAINT
    | FIRST | LAST | ONLY
    | RANGE | LIST | HASH
    | PARTITION | USING | TABLESPACE
    | CONCURRENTLY | UNLOGGED
    | TEMP | TEMPORARY
    | RECURSIVE
    | JSON | JSONB | UUID | BYTEA | INET | CIDR | MACADDR | MONEY | XML
    ;

literal
    : INTEGER_LITERAL
    | DECIMAL_LITERAL
    | STRING_LITERAL
    | DOLLAR_STRING
    | NULL_
    | TRUE
    | FALSE
    ;
