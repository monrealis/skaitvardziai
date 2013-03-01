package eu.vytenis.skaitvardziai.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParserTreeConstants;
import eu.vytenis.parser.Token;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class Arguments {

	public static Object[] getArguments(SimpleNode functionCallNode) {
		SimpleNode argsNode = Nodes.getOnlyChild(functionCallNode, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENTS);
		List<SimpleNode> argNodes = Nodes.getChildren(argsNode, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENT);
		
		List<Object> r = new ArrayList<Object>();
		for (SimpleNode n : argNodes) {
			r.add(getArgument(n));
		}
		
		return r.toArray(new Object[] {});
	}
	
	
	private static Object getArgument(SimpleNode argumentNode) {
		SimpleNode argumentChildNode = (SimpleNode) argumentNode.jjtGetChild(0);
		String nodeName = argumentChildNode.toString();
		return argHandlers.get(nodeName).getValue(argumentChildNode);
	}

	private static final Map<String, ArgHandler> argHandlers;
	static {
		Map<String, ArgHandler> h = new HashMap<String, Arguments.ArgHandler>();
		h.put(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[SkaitvardziaiFunctionParserTreeConstants.JJTNULL], new NullHandler());
		h.put(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[SkaitvardziaiFunctionParserTreeConstants.JJTSTRING], new StringHandler());
		h.put(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER], new IntegerHandler());
		h.put(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[SkaitvardziaiFunctionParserTreeConstants.JJTFRACTION], new FractionHandler());
		argHandlers = Collections.unmodifiableMap(h);
	}

	
	private static interface ArgHandler {
		Object getValue(SimpleNode node);
	}
	
	public static class StringHandler implements ArgHandler {

		public String getValue(SimpleNode node) {
			String image = ((Token) node.jjtGetValue()).image;
			image = image.substring(1, image.length() - 1);
			return image;
		}
		
	}
	
	public static class NullHandler implements ArgHandler {

		public Object getValue(SimpleNode node) {
			return null;
		}
		
	}
	
	public static class IntegerHandler implements ArgHandler {

		public SveikasisSkaicius getValue(SimpleNode node) {
			String number = getNumberString(node);
			return new SveikasisSkaicius(number);
		}

		private String getNumberString(SimpleNode node) {
			SimpleNode minus = Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTMINUS);
			SimpleNode unsigned = Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTUNSIGNEDINTEGER);
			
			String number = ((Token) unsigned.jjtGetValue()).image;
			if (minus != null) {
				number = "-" + number;
			}
			return number;
		}
		
	}
	
	public static class FractionHandler implements ArgHandler {
		
		private IntegerHandler integerHandler = new IntegerHandler();

		public Object getValue(SimpleNode node) {
			List<SimpleNode> skaitiklisVardiklis = Nodes.getChildren(node, SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER);
			String s = integerHandler.getNumberString(skaitiklisVardiklis.get(0));
			String v = integerHandler.getNumberString(skaitiklisVardiklis.get(1));
			return new Trupmena(s, v);
		}
		
	}
	
	

}
