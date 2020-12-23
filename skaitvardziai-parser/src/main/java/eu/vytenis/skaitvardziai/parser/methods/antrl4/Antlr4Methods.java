package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import eu.vytenis.skaitvardziai.parser.antlr4.functionBaseListener;
import eu.vytenis.skaitvardziai.parser.antlr4.functionLexer;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser;
import eu.vytenis.skaitvardziai.parser.antlr4.functionParser.MethodInvocationContext;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;

public class Antlr4Methods extends Methods {
	@Override
	public MethodInvocation getMethodInvocation(String methodInvocationText) {
		CharStream s = toCharStream(methodInvocationText);
		CommonTokenStream tokens = new CommonTokenStream(new functionLexer(s));
		MethodInvocationContext methodInvocation = new functionParser(tokens).methodInvocation();
		ParseTreeWalker walker = new ParseTreeWalker();
		ParseTreeListener listener = new MethodInvocationBuilder();
		walker.walk(listener, methodInvocation);
		return null;
	}

	private CodePointCharStream toCharStream(String methodInvocationText) {
		try {
			return CharStreams.fromReader(new StringReader(methodInvocationText));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class MethodInvocationBuilder extends functionBaseListener {
	}
}
