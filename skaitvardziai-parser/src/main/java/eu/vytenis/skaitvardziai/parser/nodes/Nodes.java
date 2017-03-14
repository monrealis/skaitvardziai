package eu.vytenis.skaitvardziai.parser.nodes;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;

public class Nodes {
	public static List<SimpleNode> getChildren(SimpleNode optionalParent, String name) {
		if (optionalParent == null)
			return emptyList();
		return getChildrenByName(optionalParent, name);
	}

	private static List<SimpleNode> getChildrenByName(SimpleNode parent, String name) {
		List<SimpleNode> children = new ArrayList<SimpleNode>();
		for (int i = 0; i < parent.jjtGetNumChildren(); ++i)
			if (parent.jjtGetChild(i).toString().equals(name))
				children.add((SimpleNode) parent.jjtGetChild(i));
		return children;
	}

	public static SimpleNode getOnlyChild(SimpleNode node, String name) {
		List<SimpleNode> r = getChildren(node, name);
		if (r.size() > 1)
			throw new MoreThanOneChildException();
		if (r.isEmpty())
			return null;
		return r.iterator().next();
	}

	public static class MoreThanOneChildException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;
	}
}
