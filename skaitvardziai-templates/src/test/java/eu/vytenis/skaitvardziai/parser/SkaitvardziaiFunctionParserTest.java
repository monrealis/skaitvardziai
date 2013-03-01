package eu.vytenis.skaitvardziai.parser;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.tree.ParseException;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.parser.tree.TreeParser;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


// TODO sutvarkyti, iškelti į src/main
public class SkaitvardziaiFunctionParserTest {
	
	@Test
	public void testNoArgs() throws Exception {
		testParseCall(" f ( ) ", new Object[] {});
	}
	
	@Test
	public void testNullArgs() throws Exception {
		testParseCall(" f ( null ) ", new Object[] {null});
		testParseCall(" f ( null , null ) ", new Object[] {null, null});
		testParseCall(" f ( null , null , null ) ", new Object[] {null, null, null});
	}
	
	
	@Test
	public void testIntegerArgs() throws Exception {
		testParseCall(" f ( 1 ) ", new Object[] {getSv(1)});
		testParseCall(" f ( 1 , 2 ) ", new Object[] {getSv(1), getSv(2)});
		testParseCall(" f ( 1 , 2, 3 ) ", new Object[] {getSv(1), getSv(2), getSv(3)});
		
		testParseCall(" f ( - 1 ) ", new Object[] {getSv(-1)});
		testParseCall(" f ( -1 , - 2 ) ", new Object[] {getSv(-1), getSv(-2)});
		testParseCall(" f ( -1 , -2, - 3 ) ", new Object[] {getSv(-1), getSv(-2), getSv(-3)});
	}
	
	
	@Test
	public void testFractionArgs() throws Exception {
		testParseCall(" f ( 1 / 2 ) ", new Object[] {getTr(1, 2)});
		testParseCall(" f ( 1 / 2 , 2 / 3 ) ", new Object[] {getTr(1, 2), getTr(2, 3)});
		testParseCall(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", new Object[] {getTr(1, 4), getTr(2, 10), getTr(3, 2)});
		
		
		testParseCall(" f ( - 1 / 2 ) ", new Object[] {getTr(-1, 2)});
		testParseCall(" f ( -1 / - 2 ) ", new Object[] {getTr(-1, -2)});
		testParseCall(" f ( 1 / -2 ) ", new Object[] {getTr(1, -2)});
	}
	
	@Test
	public void testStringArgs() throws Exception {
		testParseCall(" f ( 'a' ) ", new Object[] {"a"});
		testParseCall(" f ( 'b,\"b' , 'cccc' ) ", new Object[] {"b,\"b", "cccc"});
		testParseCall(" f ( 'ddd' , 'eee', 'fff' ) ", new Object[] {"ddd", "eee", "fff"});
		
		testParseCall(" f ( \"a\" ) ", new Object[] {"a"});
		testParseCall(" f ( \"bb\" , \"cccc\" ) ", new Object[] {"bb", "cccc"});
		testParseCall(" f ( \"ddd\" , \"eee\", \"fff\" ) ", new Object[] {"ddd", "eee", "fff"});
		
	}
	
	private SimpleNode testParseCall(String functionCallText, Object[] expectedArgumentValues) throws ParseException {
		testParseCall(functionCallText, expectedArgumentValues, true);
		return testParseCall(functionCallText, expectedArgumentValues, false);
	}

	private SimpleNode testParseCall(String functionCallText, Object[] expectedArgumentValues, boolean removeWhitespace) throws ParseException {
		if (removeWhitespace) {
			functionCallText = functionCallText.replaceAll("\\s", "");			
		}
		SimpleNode call = new TreeParser(new StringReader(functionCallText)).FunctionCall();
		System.out.println();
		System.out.println(functionCallText);
		call.dump("");
		Object[] args = Arguments.getArguments(call);
		Assert.assertEquals(expectedArgumentValues.length, args.length);
		Assert.assertArrayEquals(expectedArgumentValues, args);
		Assert.assertNotNull(functionCallText);
		return call;
	}
	
	private SveikasisSkaicius getSv(Number number) {
		return new SveikasisSkaicius(number.toString());		
	}
	
	private Trupmena getTr(Number skaitiklis, Number vardiklis) {
		return new Trupmena(skaitiklis.toString(), vardiklis.toString());
	}


}
