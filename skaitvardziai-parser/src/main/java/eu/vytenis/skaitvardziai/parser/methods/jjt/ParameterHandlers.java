package eu.vytenis.skaitvardziai.parser.methods.jjt;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.skaitvardziai.parser.nodes.Nodes;
import eu.vytenis.skaitvardziai.parser.nodes.TreeConstants;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.Token;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

class ParameterHandlers {
	private static final Map<String, ParameterHandler> HANDLERS_BY_NAME = unmodifiableMap(createHandlersByName());

	private static Map<String, ParameterHandler> createHandlersByName() {
		Map<String, ParameterHandler> byName = new HashMap<String, ParameterHandler>();
		byName.put(TreeConstants.Null(), new NullHandler());
		byName.put(TreeConstants.string(), new StringHandler());
		byName.put(TreeConstants.integer(), new IntegerHandler());
		byName.put(TreeConstants.fraction(), new FractionHandler());
		return byName;
	}

	public static ParameterHandler byName(String nodeName) {
		return HANDLERS_BY_NAME.get(nodeName);
	}

	public static class StringHandler implements ParameterHandler {
		public String getValue(SimpleNode node) {
			String image = ((Token) node.jjtGetValue()).image;
			return image.substring(1, image.length() - 1);
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
			SimpleNode minus = Nodes.getOnlyChild(node, TreeConstants.minus());
			SimpleNode unsigned = Nodes.getOnlyChild(node, TreeConstants.unsignedInteger());
			String number = ((Token) unsigned.jjtGetValue()).image;
			if (minus != null)
				return "-" + number;
			else
				return number;
		}
	}

	public static class FractionHandler implements ParameterHandler {
		private IntegerHandler integerHandler = new IntegerHandler();

		public Object getValue(SimpleNode node) {
			List<SimpleNode> skaitiklisVardiklis = Nodes.getChildren(node, TreeConstants.integer());
			String s = integerHandler.getNumberString(skaitiklisVardiklis.get(0));
			String v = integerHandler.getNumberString(skaitiklisVardiklis.get(1));
			return new Trupmena(s, v);
		}
	}
}
