package eu.vytenis.skaitvardziai.parser.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.TreeParserTreeConstants;

public class NodesTest {
	private SimpleNode node;

	@Before
	public void before() {
		SimpleNode n = createParametersNode();
		n.jjtAddChild(createParameterNode(), 0);
		n.jjtAddChild(createParameterNode(), 1);
		n.jjtAddChild(createIntNode(), 2);
		n.jjtGetChild(1).jjtAddChild(createParameterNode(), 0);
		node = n;
	}

	private SimpleNode createIntNode() {
		return new SimpleNode(TreeParserTreeConstants.JJTINTEGER);
	}

	private SimpleNode createParameterNode() {
		return new SimpleNode(TreeParserTreeConstants.JJTPARAMETER);
	}

	private SimpleNode createParametersNode() {
		return new SimpleNode(TreeParserTreeConstants.JJTPARAMETERS);
	}

	@Test
	public void testGetChildren() {
		assertEquals(2, Nodes.getChildren(node, TreeConstants.parameter()).size());
		assertEquals(0, Nodes.getChildren(node, TreeConstants.parameters()).size());
		assertEquals(1, Nodes.getChildren(node, TreeConstants.integer()).size());
	}

	@Test
	public void testGetOnlyChild() {
		assertEquals((Object) null, Nodes.getOnlyChild(node, TreeConstants.parameters()));
		assertEquals(node.jjtGetChild(2), Nodes.getOnlyChild(node, TreeConstants.integer()));
		assertFalse(node.jjtGetChild(1).equals(Nodes.getOnlyChild(node, TreeConstants.integer())));
	}

	@Test(expected = Nodes.MoreThanOneChildException.class)
	public void testGetOnlyChild_Fails() {
		Nodes.getOnlyChild(node, TreeConstants.parameter());
	}

}
