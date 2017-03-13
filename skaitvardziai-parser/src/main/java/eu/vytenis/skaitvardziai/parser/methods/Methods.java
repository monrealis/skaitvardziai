package eu.vytenis.skaitvardziai.parser.methods;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.nodes.Nodes;
import eu.vytenis.skaitvardziai.parser.nodes.TreeConstants;
import eu.vytenis.skaitvardziai.parser.tree.ParseException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.Token;
import eu.vytenis.skaitvardziai.parser.tree.TreeParser;

public class Methods {
	public static MethodInvocation getMethodInvocation(String methodInvocationText) {
		SimpleNode node = parse(methodInvocationText);
		return new MethodInvocation(getMethodName(node), getParameters(node));
	}

	private static String getMethodName(SimpleNode methodInvocationNode) {
		SimpleNode identifier = Nodes.getOnlyChild(methodInvocationNode, TreeConstants.identifier());
		return ((Token) identifier.jjtGetValue()).image;
	}

	private static Object[] getParameters(SimpleNode methodInvocationNode) {
		SimpleNode node = Nodes.getOnlyChild(methodInvocationNode, TreeConstants.parameters());
		List<SimpleNode> parameterNodes = Nodes.getChildren(node, TreeConstants.parameter());
		List<Object> parameters = new ArrayList<Object>();
		for (SimpleNode n : parameterNodes)
			parameters.add(getParameter(n));
		return parameters.toArray(new Object[] {});
	}

	private static Object getParameter(SimpleNode parameterNode) {
		SimpleNode childNode = (SimpleNode) parameterNode.jjtGetChild(0);
		String nodeName = childNode.toString();
		return ParameterHandlers.byName(nodeName).getValue(childNode);
	}

	static SimpleNode parse(String methodInvocationText) {
		try {
			return new TreeParser(new StringReader(methodInvocationText)).MethodInvocation();
		} catch (ParseException e) {
			throw new SkaitvardziaiParseException(e);
		}
	}

	public static class SkaitvardziaiParseException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public SkaitvardziaiParseException(Exception cause) {
			super(cause);
		}
	}
}
