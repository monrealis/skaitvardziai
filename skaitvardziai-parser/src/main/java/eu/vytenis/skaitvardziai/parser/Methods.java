package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.tree.ParseException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.Token;
import eu.vytenis.skaitvardziai.parser.tree.TreeParser;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


// TODO argumentus if funkcijos pavadinimą grąžinti vienoje struktūroje
public class Methods {

	public static Object[] getArguments(SimpleNode functionCallNode) {
		SimpleNode argsNode = Nodes.getOnlyChild(functionCallNode, TreeConstants.getArguments());
		List<SimpleNode> argNodes = Nodes.getChildren(argsNode, TreeConstants.getArgument());
		
		List<Object> r = new ArrayList<Object>();
		for (SimpleNode n : argNodes) {
			r.add(getArgument(n));
		}
		
		return r.toArray(new Object[] {});
	}
	
	public static String getFunction(SimpleNode functionCallNode) {
		SimpleNode identifier = Nodes.getOnlyChild(functionCallNode, TreeConstants.getIdentifier());
		String name = ((Token) identifier.jjtGetValue()).image;
		return name;
	}
	
	private static Object getArgument(SimpleNode argumentNode) {
		SimpleNode argumentChildNode = (SimpleNode) argumentNode.jjtGetChild(0);
		String nodeName = argumentChildNode.toString();
		return argHandlers.get(nodeName).getValue(argumentChildNode);
	}

	private static final Map<String, ArgHandler> argHandlers;
	static {
		Map<String, ArgHandler> h = new HashMap<String, Methods.ArgHandler>();
		h.put(TreeConstants.getNull(), new NullHandler());
		h.put(TreeConstants.getString(), new StringHandler());
		h.put(TreeConstants.getInteger(), new IntegerHandler());
		h.put(TreeConstants.getFraction(), new FractionHandler());
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
			SimpleNode minus = Nodes.getOnlyChild(node, TreeConstants.getMinus());
			SimpleNode unsigned = Nodes.getOnlyChild(node, TreeConstants.getUnsignedInteger());
			
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
			List<SimpleNode> skaitiklisVardiklis = Nodes.getChildren(node, TreeConstants.getInteger());
			String s = integerHandler.getNumberString(skaitiklisVardiklis.get(0));
			String v = integerHandler.getNumberString(skaitiklisVardiklis.get(1));
			return new Trupmena(s, v);
		}
		
	}
	
	public static SimpleNode parse(String functionCallText) {
		SimpleNode call;
		try {
			call = new TreeParser(new StringReader(functionCallText)).FunctionCall();
		} catch (ParseException e) {
			throw new SkaitvardziaiParseException(e);
		}
		return call;
	}
	
	public static class SkaitvardziaiParseException extends SkaitvardziaiRuntimeException {
		
		private static final long serialVersionUID = -2523506465513363826L;

		public SkaitvardziaiParseException(Exception cause) {
			super(cause);
		}
		
	}

}
