package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionLexer;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser.MethodInvocationContext;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;

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
}
