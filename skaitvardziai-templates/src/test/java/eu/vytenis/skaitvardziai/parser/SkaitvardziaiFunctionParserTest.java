package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private SimpleNode getChild(SimpleNode node, int id) {
		List<SimpleNode> r = getChildren(node, id);
		return !r.isEmpty() ? r.iterator().next() : null;
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
	public void testNoArgs() throws Exception {
		testParseCall(" f ( ) ", 0);
	}
	
	@Test
	public void testIntegerArgs() throws Exception {
		testParseCall(" f ( 1 ) ", 1);
		testParseCall(" f ( 1 , 2 ) ", 2);
		testParseCall(" f ( 1 , 2, 3 ) ", 3);
		
		testParseCall(" f ( - 1 ) ", 1);
		testParseCall(" f ( -1 , - 2 ) ", 2);
		testParseCall(" f ( -1 , -2, - 3 ) ", 3);
	}
	
	
	@Test
	public void testFractionArgs() throws Exception {
		testParseCall(" f ( 1 / 2 ) ", 1);
		testParseCall(" f ( 1 / 2 , 2 / 3 ) ", 2);
		testParseCall(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", 3);
		
		
		testParseCall(" f ( - 1 / 2 ) ", 1);
		testParseCall(" f ( -1 / - 2 ) ", 1);
		testParseCall(" f ( 1 / -2 ) ", 1);
	}
	
	@Test
	public void testStringArgs() throws Exception {
		testParseCall(" f ( 'a' ) ", 1);
		testParseCall(" f ( 'b,\"b' , 'cccc' ) ", 2);
		testParseCall(" f ( 'ddd' , 'eee', 'fff' ) ", 3);
		
		testParseCall(" f ( \"a\" ) ", 1);
		testParseCall(" f ( \"bb\" , \"cccc\" ) ", 2);
		testParseCall(" f ( \"ddd\" , \"eee\", \"fff\" ) ", 3);
		
	}
	
	private SimpleNode testParseCall(String functionCallText, int expectedArgumentsCount) throws ParseException {
		testParseCall(functionCallText, expectedArgumentsCount, true);
		return testParseCall(functionCallText, expectedArgumentsCount, false);
	}

	private SimpleNode testParseCall(String functionCallText, int expectedArgumentsCount, boolean removeWhitespace) throws ParseException {
		if (removeWhitespace) {
			functionCallText = functionCallText.replaceAll("\\s", "");			
		}
		SimpleNode call = new SkaitvardziaiFunctionParser(new StringReader(functionCallText)).FunctionCall();
		System.out.println();
		System.out.println(functionCallText);
		call.dump("");
		Object[] args = getArgs(call);
		Assert.assertEquals(expectedArgumentsCount, args.length);
		Assert.assertNotNull(functionCallText);
		return call;
	}


}
