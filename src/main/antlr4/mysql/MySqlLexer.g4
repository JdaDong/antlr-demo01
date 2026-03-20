lexer grammar MySqlLexer;

@header {
package com.example.antlr.mysql;
}

// ============================================================
// Keywords
// ============================================================

SELECT          : S E L E C T;
FROM            : F R O M;
WHERE           : W H E R E;
AND             : A N D;
OR              : O R;
NOT             : N O T;
INSERT          : I N S E R T;
INTO            : I N T O;
VALUES          : V A L U E S;
UPDATE          : U P D A T E;
SET             : S E T;
DELETE          : D E L E T E;
CREATE          : C R E A T E;
TABLE           : T A B L E;
DROP            : D R O P;
ALTER           : A L T E R;
ADD             : A D D;
COLUMN          : C O L U M N;
INDEX           : I N D E X;
PRIMARY         : P R I M A R Y;
KEY             : K E Y;
FOREIGN         : F O R E I G N;
REFERENCES      : R E F E R E N C E S;
UNIQUE          : U N I Q U E;
NULL_           : N U L L;
DEFAULT         : D E F A U L T;
AUTO_INCREMENT  : A U T O '_' I N C R E M E N T;
IF_             : I F;
EXISTS          : E X I S T S;
DATABASE        : D A T A B A S E;
USE_            : U S E;
SHOW            : S H O W;
DATABASES       : D A T A B A S E S;
TABLES          : T A B L E S;
DESCRIBE        : D E S C R I B E;
DESC            : D E S C;
ASC             : A S C;
ORDER           : O R D E R;
BY              : B Y;
GROUP           : G R O U P;
HAVING          : H A V I N G;
LIMIT           : L I M I T;
OFFSET          : O F F S E T;
JOIN            : J O I N;
INNER           : I N N E R;
LEFT            : L E F T;
RIGHT           : R I G H T;
OUTER           : O U T E R;
CROSS           : C R O S S;
ON              : O N;
AS              : A S;
IN              : I N;
BETWEEN         : B E T W E E N;
LIKE            : L I K E;
IS              : I S;
DISTINCT        : D I S T I N C T;
UNION           : U N I O N;
ALL             : A L L;
CASE            : C A S E;
WHEN            : W H E N;
THEN            : T H E N;
ELSE            : E L S E;
END             : E N D;
COUNT           : C O U N T;
SUM             : S U M;
AVG             : A V G;
MIN             : M I N;
MAX             : M A X;
CAST            : C A S T;
REPLACE         : R E P L A C E;
TRUNCATE        : T R U N C A T E;
COMMENT         : C O M M E N T;
ENGINE          : E N G I N E;
CHARSET         : C H A R S E T;
CHARACTER       : C H A R A C T E R;
COLLATE         : C O L L A T E;
UNSIGNED        : U N S I G N E D;
SIGNED          : S I G N E D;

// MySQL-specific keywords
TINYINT         : T I N Y I N T;
SMALLINT        : S M A L L I N T;
MEDIUMINT       : M E D I U M I N T;
INT             : I N T;
INTEGER         : I N T E G E R;
BIGINT          : B I G I N T;
FLOAT           : F L O A T;
DOUBLE          : D O U B L E;
DECIMAL         : D E C I M A L;
NUMERIC         : N U M E R I C;
CHAR            : C H A R;
VARCHAR         : V A R C H A R;
TEXT            : T E X T;
TINYTEXT        : T I N Y T E X T;
MEDIUMTEXT      : M E D I U M T E X T;
LONGTEXT        : L O N G T E X T;
BLOB            : B L O B;
DATE            : D A T E;
DATETIME        : D A T E T I M E;
TIMESTAMP       : T I M E S T A M P;
TIME            : T I M E;
BOOLEAN         : B O O L E A N;
BOOL            : B O O L;
ENUM            : E N U M;

TRUE            : T R U E;
FALSE           : F A L S E;

// ============================================================
// Symbols
// ============================================================

STAR            : '*';
COMMA           : ',';
DOT             : '.';
LPAREN          : '(';
RPAREN          : ')';
SEMICOLON       : ';';
EQ              : '=';
NEQ             : '!=' | '<>';
LT              : '<';
GT              : '>';
LTE             : '<=';
GTE             : '>=';
PLUS            : '+';
MINUS           : '-';
DIVIDE          : '/';
MOD             : '%';
BACKTICK        : '`';

// ============================================================
// Literals
// ============================================================

INTEGER_LITERAL
    : DIGIT+
    ;

DECIMAL_LITERAL
    : DIGIT+ '.' DIGIT*
    | '.' DIGIT+
    ;

STRING_LITERAL
    : '\'' ( ~('\'' | '\\') | '\\' . )* '\''
    | '"' ( ~('"' | '\\') | '\\' . )* '"'
    ;

// ============================================================
// Identifiers
// ============================================================

BACKTICK_QUOTED_ID
    : '`' ~'`'+ '`'
    ;

IDENTIFIER
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

// ============================================================
// Whitespace and Comments
// ============================================================

WS
    : [ \t\r\n]+ -> skip
    ;

LINE_COMMENT
    : ('--' | '#') ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;

// ============================================================
// Fragment rules
// ============================================================

fragment DIGIT : [0-9];
fragment A : [aA]; fragment B : [bB]; fragment C : [cC]; fragment D : [dD];
fragment E : [eE]; fragment F : [fF]; fragment G : [gG]; fragment H : [hH];
fragment I : [iI]; fragment J : [jJ]; fragment K : [kK]; fragment L : [lL];
fragment M : [mM]; fragment N : [nN]; fragment O : [oO]; fragment P : [pP];
fragment Q : [qQ]; fragment R : [rR]; fragment S : [sS]; fragment T : [tT];
fragment U : [uU]; fragment V : [vV]; fragment W : [wW]; fragment X : [xX];
fragment Y : [yY]; fragment Z : [zZ];
