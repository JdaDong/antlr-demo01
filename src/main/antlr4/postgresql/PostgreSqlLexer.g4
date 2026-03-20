lexer grammar PostgreSqlLexer;

@header {
package com.example.antlr.postgresql;
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
IF_             : I F;
EXISTS          : E X I S T S;
DATABASE        : D A T A B A S E;
SCHEMA          : S C H E M A;
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
FULL            : F U L L;
ON              : O N;
AS              : A S;
IN              : I N;
BETWEEN         : B E T W E E N;
LIKE            : L I K E;
ILIKE           : I L I K E;
IS              : I S;
DISTINCT        : D I S T I N C T;
UNION           : U N I O N;
INTERSECT       : I N T E R S E C T;
EXCEPT          : E X C E P T;
ALL             : A L L;
CASE            : C A S E;
WHEN            : W H E N;
THEN            : T H E N;
ELSE            : E L S E;
END             : E N D;
ASC             : A S C;
DESC            : D E S C;
RETURNING       : R E T U R N I N G;
WITH            : W I T H;
RECURSIVE       : R E C U R S I V E;
CASCADE         : C A S C A D E;
RESTRICT        : R E S T R I C T;
CONSTRAINT      : C O N S T R A I N T;
CHECK           : C H E C K;
NULLS           : N U L L S;
FIRST           : F I R S T;
LAST            : L A S T;

// Aggregate functions
COUNT           : C O U N T;
SUM             : S U M;
AVG             : A V G;
MIN             : M I N;
MAX             : M A X;
CAST            : C A S T;
COALESCE        : C O A L E S C E;

TRUE            : T R U E;
FALSE           : F A L S E;

// PostgreSQL-specific keywords
SERIAL          : S E R I A L;
BIGSERIAL       : B I G S E R I A L;
SMALLSERIAL     : S M A L L S E R I A L;
RETURNING_KW    : R E T U R N I N G;
CONCURRENTLY    : C O N C U R R E N T L Y;
USING           : U S I N G;
TABLESPACE      : T A B L E S P A C E;
INHERITS        : I N H E R I T S;
PARTITION       : P A R T I T I O N;
RANGE           : R A N G E;
LIST            : L I S T;
HASH            : H A S H;
TEMP            : T E M P;
TEMPORARY       : T E M P O R A R Y;
UNLOGGED        : U N L O G G E D;
ONLY            : O N L Y;

// Data types
SMALLINT        : S M A L L I N T;
INT             : I N T;
INTEGER         : I N T E G E R;
BIGINT          : B I G I N T;
REAL            : R E A L;
FLOAT           : F L O A T;
DOUBLE          : D O U B L E;
PRECISION       : P R E C I S I O N;
DECIMAL         : D E C I M A L;
NUMERIC         : N U M E R I C;
CHAR            : C H A R;
VARCHAR         : V A R C H A R;
TEXT            : T E X T;
BOOLEAN         : B O O L E A N;
BOOL            : B O O L;
DATE            : D A T E;
TIMESTAMP       : T I M E S T A M P;
TIME            : T I M E;
INTERVAL        : I N T E R V A L;
JSON            : J S O N;
JSONB           : J S O N B;
UUID            : U U I D;
ARRAY           : A R R A Y;
BYTEA           : B Y T E A;
INET            : I N E T;
CIDR            : C I D R;
MACADDR         : M A C A D D R;
MONEY           : M O N E Y;
XML             : X M L;

// ============================================================
// Symbols
// ============================================================

STAR            : '*';
COMMA           : ',';
DOT             : '.';
LPAREN          : '(';
RPAREN          : ')';
LBRACKET        : '[';
RBRACKET        : ']';
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
CONCAT          : '||';
CAST_OP         : '::';
ARROW           : '->';
DOUBLE_ARROW    : '->>';

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
    : '\'' ( ~('\'' | '\\') | '\\' . | '\'\'' )* '\''
    ;

DOLLAR_STRING
    : '$$' .*? '$$'
    ;

// ============================================================
// Identifiers
// ============================================================

DOUBLE_QUOTED_ID
    : '"' ~'"'+ '"'
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
    : '--' ~[\r\n]* -> skip
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
