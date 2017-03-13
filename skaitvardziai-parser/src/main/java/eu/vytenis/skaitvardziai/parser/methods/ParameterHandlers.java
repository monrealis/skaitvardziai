package eu.vytenis.skaitvardziai.parser.methods;

import java.util.Collections;
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
	static final Map<String, ParameterHandler> HANDLERS_BY_NAME;
	static {
		Map<String, ParameterHandler> h = new HashMap<String, ParameterHandler>();
		h.put(TreeConstants.Null(), new NullHandler());
		h.put(TreeConstants.string(), new StringHandler());
		h.put(TreeConstants.integer(), new IntegerHandler());
		h.put(TreeConstants.fraction(), new FractionHandler());
		HANDLERS_BY_NAME = Collections.unmodifiableMap(h);
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
			SimpleNode minus = Nodes.getOnlyChild(node, TreeConstants.minus());
			SimpleNode unsigned = Nodes.getOnlyChild(node, TreeConstants.unsignedInteger());
			String number = ((Token) unsigned.jjtGetValue()).image;
			if (minus != null)
				number = "-" + number;
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
