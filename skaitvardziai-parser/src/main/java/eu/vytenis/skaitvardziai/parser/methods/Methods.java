package eu.vytenis.skaitvardziai.parser.methods;

import static eu.vytenis.skaitvardziai.parser.nodes.Nodes.getChildren;
import static eu.vytenis.skaitvardziai.parser.nodes.Nodes.getOnlyChild;
import static eu.vytenis.skaitvardziai.parser.nodes.TreeConstants.identifier;
import static eu.vytenis.skaitvardziai.parser.nodes.TreeConstants.parameter;
import static eu.vytenis.skaitvardziai.parser.nodes.TreeConstants.parameters;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.tree.ParseException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.Token;
import eu.vytenis.skaitvardziai.parser.tree.TreeParser;

public class Methods {
	public static MethodInvocation getMethodInvocation(String methodInvocationText) {
		SimpleNode methodInvocationNode = parse(methodInvocationText);
		return new MethodInvocation(getMethodName(methodInvocationNode), getParameters(methodInvocationNode));
	}

	private static SimpleNode parse(String methodInvocationText) {
		try {
			return new TreeParser(new StringReader(methodInvocationText)).MethodInvocation();
		} catch (ParseException e) {
			throw new SkaitvardziaiParseException(e);
		}
	}

	private static String getMethodName(SimpleNode methodInvocationNode) {
		SimpleNode identifier = getOnlyChild(methodInvocationNode, identifier());
		return ((Token) identifier.jjtGetValue()).image;
	}

	private static Object[] getParameters(SimpleNode methodInvocationNode) {
		SimpleNode container = getOnlyChild(methodInvocationNode, parameters());
		List<SimpleNode> parameterNodes = getChildren(container, parameter());
		List<Object> parameters = new ArrayList<Object>();
		for (SimpleNode n : parameterNodes)
			parameters.add(getParameterValue(n));
		return parameters.toArray(new Object[] {});
	}

	private static Object getParameterValue(SimpleNode parameterNode) {
		SimpleNode childNode = (SimpleNode) parameterNode.jjtGetChild(0);
		String nodeName = childNode.toString();
		return ParameterHandlers.byName(nodeName).getValue(childNode);
	}

	public static class SkaitvardziaiParseException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public SkaitvardziaiParseException(Exception cause) {
			super(cause);
		}
	}
}
