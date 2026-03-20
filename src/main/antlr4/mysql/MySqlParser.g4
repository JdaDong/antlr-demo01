parser grammar MySqlParser;

options { tokenVocab=MySqlLexer; }

@header {
package com.example.antlr.mysql;
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
    | showStatement
    | useStatement
    | describeStatement
    ;

// ============================================================
// DDL Statements
// ============================================================

ddlStatement
    : createDatabase
    | createTable
    | dropDatabase
    | dropTable
    | alterTable
    | truncateTable
    | createIndex
    | dropIndex
    ;

createDatabase
    : CREATE DATABASE (IF_ NOT EXISTS)? databaseName
    ;

dropDatabase
    : DROP DATABASE (IF_ EXISTS)? databaseName
    ;

createTable
    : CREATE TABLE (IF_ NOT EXISTS)? tableName
      LPAREN columnDefinition (COMMA columnDefinition)* (COMMA tableConstraint)* RPAREN
      tableOptions?
    ;

dropTable
    : DROP TABLE (IF_ EXISTS)? tableName
    ;

alterTable
    : ALTER TABLE tableName alterTableAction (COMMA alterTableAction)*
    ;

alterTableAction
    : ADD COLUMN? columnDefinition
    | DROP COLUMN? columnName
    | ALTER COLUMN? columnName SET DEFAULT expression
    ;

truncateTable
    : TRUNCATE TABLE? tableName
    ;

createIndex
    : CREATE UNIQUE? INDEX indexName ON tableName LPAREN indexColumnList RPAREN
    ;

dropIndex
    : DROP INDEX indexName ON tableName
    ;

// ============================================================
// DML Statements
// ============================================================

dmlStatement
    : selectStatement
    | insertStatement
    | updateStatement
    | deleteStatement
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
      (UNION ALL? selectStatement)?
    ;

insertStatement
    : INSERT INTO? tableName
      (LPAREN columnNameList RPAREN)?
      (VALUES LPAREN expressionList RPAREN (COMMA LPAREN expressionList RPAREN)*
      | selectStatement)
    ;

updateStatement
    : UPDATE tableName SET updateAssignment (COMMA updateAssignment)* whereClause?
    ;

deleteStatement
    : DELETE FROM tableName whereClause?
    ;

// ============================================================
// Show / Use / Describe
// ============================================================

showStatement
    : SHOW DATABASES
    | SHOW TABLES
    ;

useStatement
    : USE_ databaseName
    ;

describeStatement
    : (DESCRIBE | DESC) tableName
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
    : joinType? JOIN tableSource (ON expression)?
    ;

joinType
    : INNER
    | LEFT OUTER?
    | RIGHT OUTER?
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
    : expression (ASC | DESC)?
    ;

limitClause
    : LIMIT expression (COMMA expression)?
    | LIMIT expression OFFSET expression
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
    | expression IS NULL_                                                # isNullExpression
    | expression IS NOT NULL_                                            # isNotNullExpression
    | functionCall                                                       # functionCallExpression
    | caseExpression                                                     # caseExpr
    | expression (STAR | DIVIDE | MOD) expression                        # mulDivModExpression
    | expression (PLUS | MINUS) expression                               # addSubExpression
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
    : COUNT | SUM | AVG | MIN | MAX | CAST | REPLACE
    | IDENTIFIER
    ;

// ============================================================
// Column & Table definitions
// ============================================================

columnDefinition
    : columnName dataType columnConstraint*
    ;

dataType
    : TINYINT (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | SMALLINT (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | MEDIUMINT (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | INT (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | INTEGER (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | BIGINT (LPAREN INTEGER_LITERAL RPAREN)? UNSIGNED?
    | FLOAT (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | DOUBLE (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | DECIMAL (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | NUMERIC (LPAREN INTEGER_LITERAL (COMMA INTEGER_LITERAL)? RPAREN)?
    | CHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | VARCHAR (LPAREN INTEGER_LITERAL RPAREN)?
    | TEXT
    | TINYTEXT
    | MEDIUMTEXT
    | LONGTEXT
    | BLOB
    | DATE
    | DATETIME
    | TIMESTAMP
    | TIME
    | BOOLEAN
    | BOOL
    | ENUM LPAREN STRING_LITERAL (COMMA STRING_LITERAL)* RPAREN
    ;

columnConstraint
    : NOT? NULL_
    | DEFAULT expression
    | PRIMARY KEY
    | UNIQUE
    | AUTO_INCREMENT
    | COMMENT STRING_LITERAL
    ;

tableConstraint
    : PRIMARY KEY LPAREN indexColumnList RPAREN
    | UNIQUE KEY? indexName? LPAREN indexColumnList RPAREN
    | FOREIGN KEY LPAREN columnNameList RPAREN REFERENCES tableName LPAREN columnNameList RPAREN
    | INDEX indexName? LPAREN indexColumnList RPAREN
    | KEY indexName? LPAREN indexColumnList RPAREN
    ;

tableOptions
    : tableOption+
    ;

tableOption
    : ENGINE EQ? IDENTIFIER
    | DEFAULT? CHARSET EQ? IDENTIFIER
    | DEFAULT? CHARACTER SET EQ? IDENTIFIER
    | COLLATE EQ? IDENTIFIER
    | COMMENT EQ? STRING_LITERAL
    | AUTO_INCREMENT EQ? INTEGER_LITERAL
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
    : columnName (LPAREN INTEGER_LITERAL RPAREN)? (ASC | DESC)? 
      (COMMA columnName (LPAREN INTEGER_LITERAL RPAREN)? (ASC | DESC)?)*
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

columnName
    : identifier
    ;

indexName
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
    ;
