package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.util.Stack;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import eu.vytenis.skaitvardziai.parser.antlr4.functionBaseListener;
import eu.vytenis.skaitvardziai.parser.antlr4.functionLexer;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.FractionLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.IdentifierContext;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.IntegerLiteralContext;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.MethodInvocationContext;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.NullLiteralContext;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class Antlr4Methods extends Methods {
	@Override
	public MethodInvocation getMethodInvocation(String methodInvocationText) {
		CharStream s = toCharStream(methodInvocationText);
		CommonTokenStream tokens = new CommonTokenStream(new functionLexer(s));
		System.out.println();
		new functionLexer(toCharStream(methodInvocationText)).getAllTokens()
				.forEach(t -> System.out.println("*" + t.getText()));
		functionParser parser = new functionParser(tokens);
		// parser.setErrorHandler(new BailErrorStrategy());
		MethodInvocationContext methodInvocation = parser.methodInvocation();
		ParseTreeWalker walker = new ParseTreeWalker();
		MethodInvocationBuilder listener = new MethodInvocationBuilder();
		walker.walk(listener, methodInvocation);
		return listener.toMethodInvocation();
	}

	private CodePointCharStream toCharStream(String methodInvocationText) {
		try {
			return CharStreams.fromReader(new StringReader(methodInvocationText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class MethodInvocationBuilder extends functionBaseListener {
		private String name;
		private Stack<Object> parameters = new Stack<Object>();

		@Override
		public void exitIdentifier(IdentifierContext ctx) {
			name = ctx.getText();
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
			String sign = ctx.MINUS() != null ? "-" : "";
			SveikasisSkaicius s = new SveikasisSkaicius(sign + ctx.INTEGER().getText());
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
