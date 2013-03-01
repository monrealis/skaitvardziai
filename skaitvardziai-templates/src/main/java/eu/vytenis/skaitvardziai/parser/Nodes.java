package eu.vytenis.skaitvardziai.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParserTreeConstants;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class Nodes {

	public static List<SimpleNode> getChildren(SimpleNode node, int id) {
		if (node == null) {
			return Collections.emptyList();
		}
		List<SimpleNode> r = new ArrayList<SimpleNode>();
		for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
			SimpleNode n = (SimpleNode) node.jjtGetChild(i);
			if (n.toString().equals(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[id])) {
				r.add(n);
			}
		}
		return r;		
	}

	public static SimpleNode getOnlyChild(SimpleNode node, int id) {
		List<SimpleNode> r = getChildren(node, id);
		if (r.size() > 1) {
			throw new MoreThanOneChildException();
		}
		return !r.isEmpty() ? r.iterator().next() : null;
	}

	public static class MoreThanOneChildException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = -949095261183514187L;
		
	}
	
	
}
