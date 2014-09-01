package eu.vytenis.skaitvardziai.parser.methods;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


public class MethodsTest {
	
	@Test
	public void testNoParams() {
		assertMethodInvocations(" f ( ) ", "f", new Object[] {});
	}
	
	@Test
	public void testNullParams() {
		assertMethodInvocations(" f ( null ) ", "f",  new Object[] {null});
		assertMethodInvocations(" f ( null , null ) ", "f",  new Object[] {null, null});
		assertMethodInvocations(" f ( null , null , null ) ", "f",  new Object[] {null, null, null});
	}
	
	
	@Test
	public void testIntegerParams() {
		assertMethodInvocations(" f ( 1 ) ", "f", new Object[] {getSv(1)});
		assertMethodInvocations(" f ( 1 , 2 ) ", "f", new Object[] {getSv(1), getSv(2)});
		assertMethodInvocations(" f ( 1 , 2, 3 ) ", "f", new Object[] {getSv(1), getSv(2), getSv(3)});
		
		assertMethodInvocations(" f ( - 1 ) ", "f", new Object[] {getSv(-1)});
		assertMethodInvocations(" f ( -1 , - 2 ) ", "f", new Object[] {getSv(-1), getSv(-2)});
		assertMethodInvocations(" f ( -1 , -2, - 3 ) ", "f", new Object[] {getSv(-1), getSv(-2), getSv(-3)});
	}
	
	
	@Test
	public void testFractionParams() {
		assertMethodInvocations(" f ( 1 / 2 ) ", "f", new Object[] {getTr(1, 2)});
		assertMethodInvocations(" f ( 1 / 2 , 2 / 3 ) ", "f", new Object[] {getTr(1, 2), getTr(2, 3)});
		assertMethodInvocations(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", "f", new Object[] {getTr(1, 4), getTr(2, 10), getTr(3, 2)});
		
		
		assertMethodInvocations(" f ( - 1 / 2 ) ", "f", new Object[] {getTr(-1, 2)});
		assertMethodInvocations(" f ( -1 / - 2 ) ", "f", new Object[] {getTr(-1, -2)});
		assertMethodInvocations(" f ( 1 / -2 ) ", "f", new Object[] {getTr(1, -2)});
	}
	
	@Test
	public void testStringParams() {
		assertMethodInvocations(" f ( 'a' ) ", "f", new Object[] {"a"});
		assertMethodInvocations(" f ( 'b,\"b' , 'cccc' ) ", "f", new Object[] {"b,\"b", "cccc"});
		assertMethodInvocations(" f ( 'ddd' , 'eee', 'fff' ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
		assertMethodInvocations(" f ( \"a\" ) ", "f", new Object[] {"a"});
		assertMethodInvocations(" f ( \"bb\" , \"cccc\" ) ", "f", new Object[] {"bb", "cccc"});
		assertMethodInvocations(" f ( \"ddd\" , \"eee\", \"fff\" ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
	}
	
	private SimpleNode assertMethodInvocations(String methodInvocationText, String expectedMethodName, Object[] expectedParameterValues) {
		assertMethodInvocation(methodInvocationText, expectedMethodName, expectedParameterValues);
		methodInvocationText = methodInvocationText.replaceAll("\\s", "");
		return assertMethodInvocation(methodInvocationText, expectedMethodName, expectedParameterValues);
	}

	private SimpleNode assertMethodInvocation(String methodInvocationText, String expectedMethodName, Object[] expectedParameterValues) {
		SimpleNode node = Methods.parse(methodInvocationText);
		//System.out.println();
		//System.out.println(methodInvocationText);
		//node.dump("");
		
		MethodInvocation i = Methods.getMethodInvocation(methodInvocationText);
		assertEquals(expectedMethodName, i.getMethodName());
		assertEquals(expectedParameterValues.length, i.getParameters().length);
		assertArrayEquals(expectedParameterValues, i.getParameters());
		assertNotNull(methodInvocationText);
		return node;
	}
	
	private SveikasisSkaicius getSv(Number number) {
		return new SveikasisSkaicius(number.toString());		
	}
	
	private Trupmena getTr(Number skaitiklis, Number vardiklis) {
		return new Trupmena(skaitiklis.toString(), vardiklis.toString());
	}


}
