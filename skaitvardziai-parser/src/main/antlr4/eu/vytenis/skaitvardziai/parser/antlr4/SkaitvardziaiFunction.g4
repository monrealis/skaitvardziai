grammar SkaitvardziaiFunction;

methodInvocation: methodName '(' arguments? ')' EOF;

arguments: argument (',' argument)*;
argument: fractionLiteral | integerLiteral | stringLiteral | nullLiteral;

fractionLiteral: integerLiteral '/' integerLiteral;
integerLiteral: Minus? IntegerLiteral;

stringLiteral: SingleQuotedStringLiteral | DoubleQuotedStringLiteral;
SingleQuotedStringLiteral: '\'' SingleQuotedStringCharacters '\'';
DoubleQuotedStringLiteral: '"' DoubleQuotedStringCharacters '"';

methodName: Identifier;
nullLiteral: NullLiteral;


Minus: '-';
IntegerLiteral: Digit+;

NullLiteral: 'null';
Identifier: Letter (Letter | Digit)*;

fragment Letter: [a-zA-Z];
fragment Digit: [0-9];
fragment SingleQuotedStringCharacters : (~['])*;
fragment DoubleQuotedStringCharacters : (~["])*;

WS: [ \t\r\n]+ -> skip;
