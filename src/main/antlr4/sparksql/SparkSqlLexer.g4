lexer grammar SparkSqlLexer;

@header {
package com.example.antlr.sparksql;
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
COLUMNS         : C O L U M N S;
INDEX           : I N D E X;
PRIMARY         : P R I M A R Y;
KEY             : K E Y;
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
SEMI            : S E M I;
ANTI            : A N T I;
ON              : O N;
AS              : A S;
IN              : I N;
BETWEEN         : B E T W E E N;
LIKE            : L I K E;
RLIKE           : R L I K E;
REGEXP          : R E G E X P;
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
WITH            : W I T H;
RECURSIVE       : R E C U R S I V E;
TRUE            : T R U E;
FALSE           : F A L S E;

// Aggregate functions
COUNT           : C O U N T;
SUM             : S U M;
AVG             : A V G;
MIN             : M I N;
MAX             : M A X;
CAST            : C A S T;
COALESCE        : C O A L E S C E;

// SparkSQL-specific keywords
USE_            : U S E;
SHOW            : S H O W;
DATABASES       : D A T A B A S E S;
SCHEMAS         : S C H E M A S;
TABLES          : T A B L E S;
VIEWS           : V I E W S;
FUNCTIONS       : F U N C T I O N S;
DESCRIBE        : D E S C R I B E;
EXTENDED        : E X T E N D E D;
FORMATTED       : F O R M A T T E D;
VIEW            : V I E W;
TEMPORARY       : T E M P O R A R Y;
GLOBAL          : G L O B A L;
EXTERNAL        : E X T E R N A L;
LOCATION        : L O C A T I O N;
STORED          : S T O R E D;
ROW             : R O W;
FORMAT          : F O R M A T;
DELIMITED       : D E L I M I T E D;
FIELDS          : F I E L D S;
TERMINATED      : T E R M I N A T E D;
LINES           : L I N E S;
TBLPROPERTIES   : T B L P R O P E R T I E S;
SERDEPROPERTIES : S E R D E P R O P E R T I E S;
SERDE           : S E R D E;
USING           : U S I N G;
OPTIONS         : O P T I O N S;
PARTITIONED     : P A R T I T I O N E D;
PARTITION       : P A R T I T I O N;
CLUSTERED       : C L U S T E R E D;
SORTED          : S O R T E D;
BUCKETS         : B U C K E T S;
OVERWRITE       : O V E R W R I T E;
DIRECTORY       : D I R E C T O R Y;
LOCAL           : L O C A L;
LATERAL         : L A T E R A L;
WINDOW          : W I N D O W;
OVER            : O V E R;
ROWS            : R O W S;
RANGE           : R A N G E;
UNBOUNDED       : U N B O U N D E D;
PRECEDING       : P R E C E D I N G;
FOLLOWING       : F O L L O W I N G;
CURRENT         : C U R R E N T;
PIVOT           : P I V O T;
UNPIVOT         : U N P I V O T;
FOR             : F O R;
TABLESAMPLE     : T A B L E S A M P L E;
PERCENT         : P E R C E N T;
DISTRIBUTE      : D I S T R I B U T E;
SORT            : S O R T;
CLUSTER         : C L U S T E R;
EXPLAIN         : E X P L A I N;
CACHE           : C A C H E;
UNCACHE         : U N C A C H E;
LAZY            : L A Z Y;
REFRESH         : R E F R E S H;
MSCK            : M S C K;
REPAIR          : R E P A I R;
RECOVER         : R E C O V E R;
PARTITIONS      : P A R T I T I O N S;
MAP             : M A P;
STRUCT          : S T R U C T;
ARRAY           : A R R A Y;
COMMENT         : C O M M E N T;
RENAME          : R E N A M E;
TO              : T O;
REPLACE         : R E P L A C E;
CASCADE         : C A S C A D E;
RESTRICT        : R E S T R I C T;

// Data types
TINYINT         : T I N Y I N T;
SMALLINT        : S M A L L I N T;
INT             : I N T;
INTEGER         : I N T E G E R;
BIGINT          : B I G I N T;
FLOAT           : F L O A T;
DOUBLE          : D O U B L E;
DECIMAL         : D E C I M A L;
DEC             : D E C;
NUMERIC         : N U M E R I C;
STRING          : S T R I N G;
CHAR            : C H A R;
VARCHAR         : V A R C H A R;
BINARY          : B I N A R Y;
BOOLEAN         : B O O L E A N;
DATE            : D A T E;
TIMESTAMP       : T I M E S T A M P;
INTERVAL        : I N T E R V A L;

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
EQ              : '=' | '==';
NEQ             : '!=' | '<>';
LT              : '<';
GT              : '>';
LTE             : '<=';
GTE             : '>=';
PLUS            : '+';
MINUS           : '-';
DIVIDE          : '/';
MOD             : '%';
AMPERSAND       : '&';
PIPE            : '|';
CARET           : '^';
TILDE           : '~';
CONCAT          : '||';
COLON           : ':';
BACKTICK        : '`';

// ============================================================
// Literals
// ============================================================

INTEGER_LITERAL
    : DIGIT+ ('L')?
    ;

DECIMAL_LITERAL
    : DIGIT+ '.' DIGIT* ('D' | 'BD')?
    | '.' DIGIT+ ('D' | 'BD')?
    ;

STRING_LITERAL
    : '\'' ( ~('\'' | '\\') | '\\' . | '\'\'' )* '\''
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
