grammar function;

methodInvocation: identifier '(' parameters? ')';

parameters: parameter (',' parameter)*;
parameter: fractionLiteral | integerLiteral | stringLiteral | nullLiteral;

fractionLiteral: integerLiteral '/' integerLiteral;
integerLiteral: MINUS? INTEGER;

stringLiteral: SINGLE_QUOTED_STRING | DOUBLE_QUOTED_STRING;
SINGLE_QUOTED_STRING: '\'' SINGLE_QUOTED_STRING_CONTENT '\'';
DOUBLE_QUOTED_STRING: '"' DOUBLE_QUOTED_STRING_CONTENT '"';

identifier: IDENTIFIER;

fragment LETTER: [a-zA-Z];
fragment DIGIT: [0-9];
fragment SINGLE_QUOTED_STRING_CONTENT : (~['])*;
fragment DOUBLE_QUOTED_STRING_CONTENT : (~["])*;

MINUS: '-';
INTEGER: DIGIT+;
nullLiteral: NULL;
NULL: 'null';
IDENTIFIER: LETTER (LETTER | DIGIT)*;
WS: [ \t\r\n]+ -> skip;
