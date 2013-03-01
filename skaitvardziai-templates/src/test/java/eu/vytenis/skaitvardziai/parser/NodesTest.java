package eu.vytenis.skaitvardziai.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParserTreeConstants;

public class NodesTest {
	
	private SimpleNode node;	
	
	@Before
	public void before() {
		SimpleNode n = createArgsNode();
		n.jjtAddChild(createArgNode(), 0);
		n.jjtAddChild(createArgNode(), 1);
		n.jjtAddChild(createIntNode(), 2);
		n.jjtGetChild(1).jjtAddChild(createArgNode(), 0);
		node = n;
	}

	private SimpleNode createIntNode() {
		return new SimpleNode(SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER);
	}

	private SimpleNode createArgNode() {
		return new SimpleNode(SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENT);
	}

	private SimpleNode createArgsNode() {
		return new SimpleNode(SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENTS);
	}

	@Test
	public void testGetChildren() {
		Assert.assertEquals(2, Nodes.getChildren(node, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENT).size());
		Assert.assertEquals(0, Nodes.getChildren(node, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENTS).size());
		Assert.assertEquals(1, Nodes.getChildren(node, SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER).size());
	}

	@Test
	public void testGetOnlyChild() {
		Assert.assertEquals((Object) null, Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENTS));
		Assert.assertEquals(node.jjtGetChild(2), Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER));
		Assert.assertFalse(node.jjtGetChild(1).equals(Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTINTEGER)));
	}
	
	@Test(expected = Nodes.MoreThanOneChildException.class)
	public void testGetOnlyChild_Fails() {
		Nodes.getOnlyChild(node, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENT);
	}

}
