grammar function;

methodInvocation: identifier '(' parameters? ')';

parameters: parameter ( ',' parameter )* ;
parameter: fractionLiteral | integerLiteral | STRING | nullLiteral;

integerLiteral: INTEGER;
fractionLiteral: INTEGER '/' INTEGER;

STRING: SINGLE_QUOTED_STRING | DOUBLE_QUOTED_STRING;
SINGLE_QUOTED_STRING:  '\'' (~["'"])* '\'';
DOUBLE_QUOTED_STRING: '"' (~["'"])* '"';

identifier: IDENTIFIER;
IDENTIFIER: LETTER (LETTER | DIGIT)*;
LETTER: [a-zA-Z];
DIGIT: [0-9];

INTEGER: [0-9]+;
nullLiteral: NULL;
NULL : 'null';

WS  :  [ \t\r\n]+ -> skip;

