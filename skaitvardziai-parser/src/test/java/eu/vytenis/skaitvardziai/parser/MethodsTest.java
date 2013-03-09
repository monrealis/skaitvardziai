package eu.vytenis.skaitvardziai.parser;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


public class MethodsTest {
	
	@Test
	public void testNoArgs() {
		testParseCall(" f ( ) ", "f", new Object[] {});
	}
	
	@Test
	public void testNullArgs() {
		testParseCall(" f ( null ) ", "f",  new Object[] {null});
		testParseCall(" f ( null , null ) ", "f",  new Object[] {null, null});
		testParseCall(" f ( null , null , null ) ", "f",  new Object[] {null, null, null});
	}
	
	
	@Test
	public void testIntegerArgs() {
		testParseCall(" f ( 1 ) ", "f", new Object[] {getSv(1)});
		testParseCall(" f ( 1 , 2 ) ", "f", new Object[] {getSv(1), getSv(2)});
		testParseCall(" f ( 1 , 2, 3 ) ", "f", new Object[] {getSv(1), getSv(2), getSv(3)});
		
		testParseCall(" f ( - 1 ) ", "f", new Object[] {getSv(-1)});
		testParseCall(" f ( -1 , - 2 ) ", "f", new Object[] {getSv(-1), getSv(-2)});
		testParseCall(" f ( -1 , -2, - 3 ) ", "f", new Object[] {getSv(-1), getSv(-2), getSv(-3)});
	}
	
	
	@Test
	public void testFractionArgs() {
		testParseCall(" f ( 1 / 2 ) ", "f", new Object[] {getTr(1, 2)});
		testParseCall(" f ( 1 / 2 , 2 / 3 ) ", "f", new Object[] {getTr(1, 2), getTr(2, 3)});
		testParseCall(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", "f", new Object[] {getTr(1, 4), getTr(2, 10), getTr(3, 2)});
		
		
		testParseCall(" f ( - 1 / 2 ) ", "f", new Object[] {getTr(-1, 2)});
		testParseCall(" f ( -1 / - 2 ) ", "f", new Object[] {getTr(-1, -2)});
		testParseCall(" f ( 1 / -2 ) ", "f", new Object[] {getTr(1, -2)});
	}
	
	@Test
	public void testStringArgs() {
		testParseCall(" f ( 'a' ) ", "f", new Object[] {"a"});
		testParseCall(" f ( 'b,\"b' , 'cccc' ) ", "f", new Object[] {"b,\"b", "cccc"});
		testParseCall(" f ( 'ddd' , 'eee', 'fff' ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
		testParseCall(" f ( \"a\" ) ", "f", new Object[] {"a"});
		testParseCall(" f ( \"bb\" , \"cccc\" ) ", "f", new Object[] {"bb", "cccc"});
		testParseCall(" f ( \"ddd\" , \"eee\", \"fff\" ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
	}
	
	private SimpleNode testParseCall(String methodCallText, String expectedMethodName, Object[] expectedArgumentValues) {
		testParseCall(methodCallText, expectedMethodName, expectedArgumentValues, true);
		return testParseCall(methodCallText, expectedMethodName, expectedArgumentValues, false);
	}

	private SimpleNode testParseCall(String methodCallText, String expectedMethodName, Object[] expectedArgumentValues, boolean removeWhitespace) {
		if (removeWhitespace) {
			methodCallText = methodCallText.replaceAll("\\s", "");			
		}
		SimpleNode call = Methods.parse(methodCallText);
		System.out.println();
		System.out.println(methodCallText);
		call.dump("");
		
		String name = Methods.getMethodName(call);
		Object[] args = Methods.getArguments(call);
		Assert.assertEquals(expectedMethodName, name);
		Assert.assertEquals(expectedArgumentValues.length, args.length);
		Assert.assertArrayEquals(expectedArgumentValues, args);
		Assert.assertNotNull(methodCallText);
		return call;
	}
	
	private SveikasisSkaicius getSv(Number number) {
		return new SveikasisSkaicius(number.toString());		
	}
	
	private Trupmena getTr(Number skaitiklis, Number vardiklis) {
		return new Trupmena(skaitiklis.toString(), vardiklis.toString());
	}


}
