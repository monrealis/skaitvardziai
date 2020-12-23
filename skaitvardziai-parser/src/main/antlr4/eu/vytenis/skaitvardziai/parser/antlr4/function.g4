grammar function;

methodInvocation: identifier '(' parameters? ')';

parameters: parameter (',' parameter)*;
parameter: fractionLiteral | integerLiteral | STRING | nullLiteral;

fractionLiteral: integerLiteral '/' integerLiteral;
integerLiteral: MINUS? INTEGER;

STRING: SINGLE_QUOTED_STRING | DOUBLE_QUOTED_STRING;
SINGLE_QUOTED_STRING:  '\'' (~["'"])* '\'';
DOUBLE_QUOTED_STRING: '"' (~["'"])* '"';

identifier: IDENTIFIER;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];

MINUS: '-';
INTEGER: DIGIT+;
nullLiteral: NULL;
NULL : 'null';
IDENTIFIER: LETTER (LETTER | DIGIT)*;

WS  :  [ \t\r\n]+ -> skip;
