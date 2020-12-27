package eu.vytenis.skaitvardziai.parser.methods.antrl4;

import java.io.IOException;
import java.io.StringReader;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionLexer;
import eu.vytenis.skaitvardziai.parser.antlr4.SkaitvardziaiFunctionParser;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;

public abstract class Antlr4Methods extends Methods {
	public static Antlr4Methods createListenerBased() {
		return new ListenerBasedAntlr4Methods();
	}

	public static Antlr4Methods createStateless() {
		return new StatelessListenerBasedAntlr4Methods();
	}

	public static Antlr4Methods createVisitorBased() {
		return new VisitorBasedAntlr4Methods();
	}

	@Override
	public MethodInvocation getMethodInvocation(String methodInvocationText) {
		CommonTokenStream tokens = getTokenStream(methodInvocationText);
		try {
			SkaitvardziaiFunctionParser parser = createParser(tokens);
			return toMethodInvocation(parser);
		} catch (ParseCancellationException e) {
			throw new SkaitvardziaiParseException(e);
		}
	}

	private CommonTokenStream getTokenStream(String methodInvocationText) {
		CharStream stream = toCharStream(methodInvocationText);
		CommonTokenStream tokens = new CommonTokenStream(new SkaitvardziaiFunctionLexer(stream));
		return tokens;
	}

	SkaitvardziaiFunctionParser createParser(CommonTokenStream tokens) {
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

	abstract MethodInvocation toMethodInvocation(SkaitvardziaiFunctionParser parser);

}

class ListenerBasedAntlr4Methods extends Antlr4Methods {
	@Override
	MethodInvocation toMethodInvocation(SkaitvardziaiFunctionParser parser) {
		ParseTreeWalker walker = new ParseTreeWalker();
		MethodInvocationListener listener = new MethodInvocationListener();
		walker.walk(listener, parser.methodInvocation());
		return listener.toMethodInvocation();
	}
}

class StatelessListenerBasedAntlr4Methods extends Antlr4Methods {
	@Override
	MethodInvocation toMethodInvocation(SkaitvardziaiFunctionParser parser) {
		parser.setBuildParseTree(false);
		MethodInvocationListener listener = new MethodInvocationListener();
		parser.addParseListener(listener);
		parser.methodInvocation();
		return listener.toMethodInvocation();
	}
}

class VisitorBasedAntlr4Methods extends Antlr4Methods {
	@Override
	MethodInvocation toMethodInvocation(SkaitvardziaiFunctionParser parser) {
		MethodInvocationVisitor visitor = new MethodInvocationVisitor();
		parser.methodInvocation().accept(visitor);
		return visitor.toMethodInvocation();
	}
}
