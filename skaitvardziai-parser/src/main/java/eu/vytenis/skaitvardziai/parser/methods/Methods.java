package eu.vytenis.skaitvardziai.parser.methods;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.nodes.Nodes;
import eu.vytenis.skaitvardziai.parser.nodes.TreeConstants;
import eu.vytenis.skaitvardziai.parser.tree.ParseException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.Token;
import eu.vytenis.skaitvardziai.parser.tree.TreeParser;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class Methods {

	public static MethodInvocation getMethodInvocation(String methodInvocationText) {
		SimpleNode node = parse(methodInvocationText);
		return new MethodInvocation(getMethodName(node), getParameters(node));
	}
	
	private static String getMethodName(SimpleNode methodInvocationNode) {
		SimpleNode identifier = Nodes.getOnlyChild(methodInvocationNode, TreeConstants.getIdentifier());
		String name = ((Token) identifier.jjtGetValue()).image;
		return name;
	}
	
	private static Object[] getParameters(SimpleNode methodInvocationNode) {
		SimpleNode paramsNode = Nodes.getOnlyChild(methodInvocationNode, TreeConstants.getParameters());
		List<SimpleNode> parameterNodes = Nodes.getChildren(paramsNode, TreeConstants.getParameter());
		
		List<Object> r = new ArrayList<Object>();
		for (SimpleNode n : parameterNodes) {
			r.add(getParameter(n));
		}
		
		return r.toArray(new Object[] {});
	}
	
	private static Object getParameter(SimpleNode parameterNode) {
		SimpleNode childNode = (SimpleNode) parameterNode.jjtGetChild(0);
		String nodeName = childNode.toString();
		return parameterHandlers.get(nodeName).getValue(childNode);
	}

	private static final Map<String, ParameterHandler> parameterHandlers;
	static {
		Map<String, ParameterHandler> h = new HashMap<String, Methods.ParameterHandler>();
		h.put(TreeConstants.getNull(), new NullHandler());
		h.put(TreeConstants.getString(), new StringHandler());
		h.put(TreeConstants.getInteger(), new IntegerHandler());
		h.put(TreeConstants.getFraction(), new FractionHandler());
		parameterHandlers = Collections.unmodifiableMap(h);
	}

	
	private static interface ParameterHandler {
		Object getValue(SimpleNode node);
	}
	
	public static class StringHandler implements ParameterHandler {

		public String getValue(SimpleNode node) {
			String image = ((Token) node.jjtGetValue()).image;
			image = image.substring(1, image.length() - 1);
			return image;
		}
		
	}
	
	public static class NullHandler implements ParameterHandler {

		public Object getValue(SimpleNode node) {
			return null;
		}
		
	}
	
	public static class IntegerHandler implements ParameterHandler {

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
	
	public static class FractionHandler implements ParameterHandler {
		
		private IntegerHandler integerHandler = new IntegerHandler();

		public Object getValue(SimpleNode node) {
			List<SimpleNode> skaitiklisVardiklis = Nodes.getChildren(node, TreeConstants.getInteger());
			String s = integerHandler.getNumberString(skaitiklisVardiklis.get(0));
			String v = integerHandler.getNumberString(skaitiklisVardiklis.get(1));
			return new Trupmena(s, v);
		}
		
	}
	
	static SimpleNode parse(String methodInvocationText) {
		SimpleNode n;
		try {
			n = new TreeParser(new StringReader(methodInvocationText)).MethodInvocation();
		} catch (ParseException e) {
			throw new SkaitvardziaiParseException(e);
		}
		return n;
	}
	
	public static class SkaitvardziaiParseException extends SkaitvardziaiRuntimeException {
		
		private static final long serialVersionUID = -2523506465513363826L;

		public SkaitvardziaiParseException(Exception cause) {
			super(cause);
		}
		
	}

}
