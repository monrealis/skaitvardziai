package eu.vytenis.skaitvardziai.parser.methods;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.methods.Methods;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


public class MethodsTest {
	
	@Test
	public void testNoArgs() {
		assertMethodInvocations(" f ( ) ", "f", new Object[] {});
	}
	
	@Test
	public void testNullArgs() {
		assertMethodInvocations(" f ( null ) ", "f",  new Object[] {null});
		assertMethodInvocations(" f ( null , null ) ", "f",  new Object[] {null, null});
		assertMethodInvocations(" f ( null , null , null ) ", "f",  new Object[] {null, null, null});
	}
	
	
	@Test
	public void testIntegerArgs() {
		assertMethodInvocations(" f ( 1 ) ", "f", new Object[] {getSv(1)});
		assertMethodInvocations(" f ( 1 , 2 ) ", "f", new Object[] {getSv(1), getSv(2)});
		assertMethodInvocations(" f ( 1 , 2, 3 ) ", "f", new Object[] {getSv(1), getSv(2), getSv(3)});
		
		assertMethodInvocations(" f ( - 1 ) ", "f", new Object[] {getSv(-1)});
		assertMethodInvocations(" f ( -1 , - 2 ) ", "f", new Object[] {getSv(-1), getSv(-2)});
		assertMethodInvocations(" f ( -1 , -2, - 3 ) ", "f", new Object[] {getSv(-1), getSv(-2), getSv(-3)});
	}
	
	
	@Test
	public void testFractionArgs() {
		assertMethodInvocations(" f ( 1 / 2 ) ", "f", new Object[] {getTr(1, 2)});
		assertMethodInvocations(" f ( 1 / 2 , 2 / 3 ) ", "f", new Object[] {getTr(1, 2), getTr(2, 3)});
		assertMethodInvocations(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", "f", new Object[] {getTr(1, 4), getTr(2, 10), getTr(3, 2)});
		
		
		assertMethodInvocations(" f ( - 1 / 2 ) ", "f", new Object[] {getTr(-1, 2)});
		assertMethodInvocations(" f ( -1 / - 2 ) ", "f", new Object[] {getTr(-1, -2)});
		assertMethodInvocations(" f ( 1 / -2 ) ", "f", new Object[] {getTr(1, -2)});
	}
	
	@Test
	public void testStringArgs() {
		assertMethodInvocations(" f ( 'a' ) ", "f", new Object[] {"a"});
		assertMethodInvocations(" f ( 'b,\"b' , 'cccc' ) ", "f", new Object[] {"b,\"b", "cccc"});
		assertMethodInvocations(" f ( 'ddd' , 'eee', 'fff' ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
		assertMethodInvocations(" f ( \"a\" ) ", "f", new Object[] {"a"});
		assertMethodInvocations(" f ( \"bb\" , \"cccc\" ) ", "f", new Object[] {"bb", "cccc"});
		assertMethodInvocations(" f ( \"ddd\" , \"eee\", \"fff\" ) ", "f", new Object[] {"ddd", "eee", "fff"});
		
	}
	
	private SimpleNode assertMethodInvocations(String methodInvocationText, String expectedMethodName, Object[] expectedArgumentValues) {
		assertMethodInvocation(methodInvocationText, expectedMethodName, expectedArgumentValues);
		methodInvocationText = methodInvocationText.replaceAll("\\s", "");
		return assertMethodInvocation(methodInvocationText, expectedMethodName, expectedArgumentValues);
	}

	private SimpleNode assertMethodInvocation(String methodInvocationText, String expectedMethodName, Object[] expectedArgumentValues) {
		SimpleNode node = Methods.parse(methodInvocationText);
		System.out.println();
		System.out.println(methodInvocationText);
		node.dump("");
		
		MethodInvocation i = Methods.getMethodInvocation(methodInvocationText);
		Assert.assertEquals(expectedMethodName, i.getMethodName());
		Assert.assertEquals(expectedArgumentValues.length, i.getParameters().length);
		Assert.assertArrayEquals(expectedArgumentValues, i.getParameters());
		Assert.assertNotNull(methodInvocationText);
		return node;
	}
	
	private SveikasisSkaicius getSv(Number number) {
		return new SveikasisSkaicius(number.toString());		
	}
	
	private Trupmena getTr(Number skaitiklis, Number vardiklis) {
		return new Trupmena(skaitiklis.toString(), vardiklis.toString());
	}


}
