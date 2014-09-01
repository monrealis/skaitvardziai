package eu.vytenis.skaitvardziai.parser.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;

public class Nodes {

	public static List<SimpleNode> getChildren(SimpleNode node, String name) {
		if (node == null) {
			return Collections.emptyList();
		}
		List<SimpleNode> r = new ArrayList<SimpleNode>();
		for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
			SimpleNode n = (SimpleNode) node.jjtGetChild(i);
			if (n.toString().equals(name)) {
				r.add(n);
			}
		}
		return r;
	}

	public static SimpleNode getOnlyChild(SimpleNode node, String name) {
		List<SimpleNode> r = getChildren(node, name);
		if (r.size() > 1) {
			throw new MoreThanOneChildException();
		}
		return !r.isEmpty() ? r.iterator().next() : null;
	}

	public static class MoreThanOneChildException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = -949095261183514187L;
	}

}
