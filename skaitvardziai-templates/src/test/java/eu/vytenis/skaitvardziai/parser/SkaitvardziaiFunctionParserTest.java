package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.parser.ParseException;
import eu.vytenis.parser.SimpleNode;
import eu.vytenis.parser.SkaitvardziaiFunctionParser;
import eu.vytenis.parser.SkaitvardziaiFunctionParserTreeConstants;


// TODO sutvarkyti, iškelti į src/main
public class SkaitvardziaiFunctionParserTest {
	
	private List<SimpleNode> getChildren(SimpleNode node, int id) {
		List<SimpleNode> r = new ArrayList<SimpleNode>();
		for (int i = 0; i < node.jjtGetNumChildren(); ++i) {
			SimpleNode n = (SimpleNode) node.jjtGetChild(i);
			if (n.toString().equals(SkaitvardziaiFunctionParserTreeConstants.jjtNodeName[id])) {
				r.add(n);
			}
		}
		return r;		
	}
	
	private SimpleNode getChild(SimpleNode node, int id) {
		List<SimpleNode> r = getChildren(node, id);
		return r != null ? r.iterator().next() : null;
	}
	
	private Object[] getArgs(SimpleNode call) {
		SimpleNode argsNode = getChild(call, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENTS);
		List<SimpleNode> argNodes = getChildren(argsNode, SkaitvardziaiFunctionParserTreeConstants.JJTARGUMENT);
		
		List<Object> r = new ArrayList<Object>();
		for (SimpleNode n : argNodes) {
			r.add(n);
		}
		
		return r.toArray(new Object[] {});
	}
	
	
	
	@Test
	public void testFunctionCalls() throws ParseException {
		SimpleNode n;
		n = parse("ff(1)", 1);
		n = parse(" ff(1)", 1);
		n = parse("ff(1) ", 1);
		n = parse(" ff(1) ", 1);;
		n = parse("ff(1, 1)", 2);
		
		n = parse("ff( 'a')", 1);
		n = parse("ff(\"b\" )", 1);
		
		n = parse("ff( 2 / 3 )", 1);
		
		n = n != null ? n : null;
		
		
		
	}

	private SimpleNode parse(String text, int argsCount) throws ParseException {
		SimpleNode call = new SkaitvardziaiFunctionParser(new StringReader(text)).FunctionCall();
		System.out.println();
		System.out.println(text);
		call.dump("");
		Object[] args = getArgs(call);
		Assert.assertEquals(argsCount, args.length);
		Assert.assertNotNull(text);
		return call;		
	}


}
