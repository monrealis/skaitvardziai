package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Stack;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionBaseListener;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionLexer;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.FractionLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.IntegerLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.MethodInvocationContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.MethodNameContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.NullLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.StringLiteralContext;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class Antlr4Methods extends Methods {
	@Override
	public MethodInvocation getMethodInvocation(String methodInvocationText) {
		CommonTokenStream tokens = getTokenStream(methodInvocationText);
		SkaitvardziaiFunctionParser parser = createParser(tokens);
		MethodInvocationContext methodInvocation = parser.methodInvocation();
		return toMethodInvocation(methodInvocation);
	}

	private CommonTokenStream getTokenStream(String methodInvocationText) {
		CharStream stream = toCharStream(methodInvocationText);
		CommonTokenStream tokens = new CommonTokenStream(new SkaitvardziaiFunctionLexer(stream));
		return tokens;
	}

	private SkaitvardziaiFunctionParser createParser(CommonTokenStream tokens) {
		SkaitvardziaiFunctionParser parser = new SkaitvardziaiFunctionParser(tokens);
		parser.setErrorHandler(new BailErrorStrategy());
		return parser;
	}

	private CodePointCharStream toCharStream(String methodInvocationText) {
		try {
			return CharStreams.fromReader(new StringReader(methodInvocationText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private MethodInvocation toMethodInvocation(MethodInvocationContext methodInvocation) {
		ParseTreeWalker walker = new ParseTreeWalker();
		MethodInvocationBuilder listener = new MethodInvocationBuilder();
		walker.walk(listener, methodInvocation);
		return listener.toMethodInvocation();
	}

	private static class MethodInvocationBuilder extends SkaitvardziaiFunctionBaseListener {
		private String name;
		private final Stack<Object> parameters = new Stack<Object>();

		@Override
		public void exitMethodName(MethodNameContext ctx) {
			name = ctx.getText();
		}

		@Override
		public void exitStringLiteral(StringLiteralContext ctx) {
			if (ctx.SingleQuotedStringLiteral() != null)
				addParameter(unwrapString(ctx.SingleQuotedStringLiteral()));
			else if (ctx.DoubleQuotedStringLiteral() != null)
				addParameter(unwrapString(ctx.DoubleQuotedStringLiteral()));
		}

		private String unwrapString(TerminalNode node) {
			String t = node.getText();
			return t.substring(1, t.length() - 1);
		}

		@Override
		public void exitFractionLiteral(FractionLiteralContext ctx) {
			BigInteger v = pop();
			BigInteger s = pop();
			addParameter(new Trupmena(s, v));
		}

		private BigInteger pop() {
			return ((SveikasisSkaicius) parameters.pop()).getReiksme();
		}

		@Override
		public void exitNullLiteral(NullLiteralContext ctx) {
			addParameter(null);
		}

		@Override
		public void exitIntegerLiteral(IntegerLiteralContext ctx) {
			String sign = ctx.Minus() != null ? "-" : "";
			SveikasisSkaicius s = new SveikasisSkaicius(sign + ctx.IntegerLiteral().getText());
			addParameter(s);
		}

		private void addParameter(Object parameter) {
			parameters.add(parameter);
		}

		private MethodInvocation toMethodInvocation() {
			return new MethodInvocation(name, parameters.toArray());
		}
	}
}
