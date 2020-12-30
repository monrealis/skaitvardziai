parser grammar SkaitvardziaiFunctionParser;

options {
	tokenVocab = SkaitvardziaiFunctionLexer;
}

methodInvocation
:
	methodName BraceOpen arguments? BraceClose EOF
;

arguments
:
	argument
	(
		Comma argument
	)*
;

argument
:
	fractionLiteral
	| integerLiteral
	| stringLiteral
	| nullLiteral
;

fractionLiteral
:
	integerLiteral Slash integerLiteral
;

integerLiteral
:
	Minus? IntegerLiteral
;

stringLiteral
:
	SingleQuotedStringLiteral
	| DoubleQuotedStringLiteral
;

methodName
:
	Identifier
;

nullLiteral
:
	NullLiteral
;


