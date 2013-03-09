package eu.vytenis.skaitvardziai.parser.nodes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.nodes.Nodes;
import eu.vytenis.skaitvardziai.parser.nodes.TreeConstants;
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
		Assert.assertEquals(2, Nodes.getChildren(node, TreeConstants.getParameter()).size());
		Assert.assertEquals(0, Nodes.getChildren(node, TreeConstants.getParameters()).size());
		Assert.assertEquals(1, Nodes.getChildren(node, TreeConstants.getInteger()).size());
	}

	@Test
	public void testGetOnlyChild() {
		Assert.assertEquals((Object) null, Nodes.getOnlyChild(node, TreeConstants.getParameters()));
		Assert.assertEquals(node.jjtGetChild(2), Nodes.getOnlyChild(node, TreeConstants.getInteger()));
		Assert.assertFalse(node.jjtGetChild(1).equals(Nodes.getOnlyChild(node, TreeConstants.getInteger())));
	}
	
	@Test(expected = Nodes.MoreThanOneChildException.class)
	public void testGetOnlyChild_Fails() {
		Nodes.getOnlyChild(node, TreeConstants.getParameter());
	}

}
