package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.math.BigInteger;
import java.util.Stack;

import org.antlr.v4.runtime.tree.TerminalNode;

import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionBaseVisitor;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.FractionLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.IntegerLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.MethodNameContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.NullLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.StringLiteralContext;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

class MethodInvocationVisitor extends SkaitvardziaiFunctionBaseVisitor<Void> {
	private String name;
	private final Stack<Object> parameters = new Stack<Object>();

	@Override
	public Void visitMethodName(MethodNameContext ctx) {
		name = ctx.getText();
		return null;
	}

	@Override
	public Void visitStringLiteral(StringLiteralContext ctx) {
		if (ctx.SingleQuotedStringLiteral() != null)
			addParameter(unwrapString(ctx.SingleQuotedStringLiteral()));
		else if (ctx.DoubleQuotedStringLiteral() != null)
			addParameter(unwrapString(ctx.DoubleQuotedStringLiteral()));
		return null;
	}

	private String unwrapString(TerminalNode node) {
		String t = node.getText();
		return t.substring(1, t.length() - 1);
	}

	@Override
	public Void visitFractionLiteral(FractionLiteralContext ctx) {
		visitChildren(ctx);
		BigInteger v = pop();
		BigInteger s = pop();
		addParameter(new Trupmena(s, v));
		return null;
	}

	private BigInteger pop() {
		return ((SveikasisSkaicius) parameters.pop()).getReiksme();
	}

	@Override
	public Void visitNullLiteral(NullLiteralContext ctx) {
		addParameter(null);
		return null;
	}

	@Override
	public Void visitIntegerLiteral(IntegerLiteralContext ctx) {
		String sign = ctx.Minus() != null ? "-" : "";
		SveikasisSkaicius s = new SveikasisSkaicius(sign + ctx.IntegerLiteral().getText());
		addParameter(s);
		return null;
	}

	private void addParameter(Object parameter) {
		parameters.add(parameter);
	}

	MethodInvocation toMethodInvocation() {
		return new MethodInvocation(name, parameters.toArray());
	}
}