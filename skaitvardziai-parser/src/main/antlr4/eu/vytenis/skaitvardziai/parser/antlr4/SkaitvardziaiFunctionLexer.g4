lexer grammar SkaitvardziaiFunctionLexer;

BraceOpen
:
	'('
;

BraceClose
:
	')'
;

Comma
:
	','
;

Slash
:
	'/'
;

SingleQuotedStringLiteral
:
	'\'' SingleQuotedStringCharacters '\''
;

DoubleQuotedStringLiteral
:
	'"' DoubleQuotedStringCharacters '"'
;

Minus
:
	'-'
;

IntegerLiteral
:
	Digit+
;

NullLiteral
:
	'null'
;

Identifier
:
	Letter
	(
		Letter
		| Digit
	)*
;

fragment
Letter
:
	[a-zA-Z]
;

fragment
Digit
:
	[0-9]
;

fragment
SingleQuotedStringCharacters
:
	(
		~[']
	)*
;

fragment
DoubleQuotedStringCharacters
:
	(
		~["]
	)*
;

WS
:
	[ \t\r\n]+ -> skip
;