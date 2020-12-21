package eu.vytenis.skaitvardziai.parser.methods;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.methods.jjt.JjtMethods;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class MethodsTest {
	private Methods methods = new JjtMethods();
	private Object Null = null;

	@Test
	public void withoutParameters() {
		assertInvocations(" f ( ) ", "f");
	}

	@Test
	public void withNullParameters() {
		assertInvocations(" f ( null ) ", "f", Null);
		assertInvocations(" f ( null , null ) ", "f", Null, Null);
		assertInvocations(" f ( null , null , null ) ", "f", Null, Null, Null);
	}

	@Test
	public void withIntegerParameters() {
		assertInvocations(" f ( 1 ) ", "f", getSv(1));
		assertInvocations(" f ( 1 , 2 ) ", "f", getSv(1), getSv(2));
		assertInvocations(" f ( 1 , 2, 3 ) ", "f", getSv(1), getSv(2), getSv(3));
		assertInvocations(" f ( - 1 ) ", "f", getSv(-1));
		assertInvocations(" f ( -1 , - 2 ) ", "f", getSv(-1), getSv(-2));
		assertInvocations(" f ( -1 , -2, - 3 ) ", "f", getSv(-1), getSv(-2), getSv(-3));
	}

	@Test
	public void withFractionParameters() {
		assertInvocations(" f ( 1 / 2 ) ", "f", getTr(1, 2));
		assertInvocations(" f ( 1 / 2 , 2 / 3 ) ", "f", getTr(1, 2), getTr(2, 3));
		assertInvocations(" f ( 1 / 4 , 2 / 10, 3 / 2 ) ", "f", getTr(1, 4), getTr(2, 10), getTr(3, 2));
		assertInvocations(" f ( - 1 / 2 ) ", "f", getTr(-1, 2));
		assertInvocations(" f ( -1 / - 2 ) ", "f", getTr(-1, -2));
		assertInvocations(" f ( 1 / -2 ) ", "f", getTr(1, -2));
	}

	@Test
	public void testStringParams() {
		assertInvocations(" f ( 'a' ) ", "f", "a");
		assertInvocations(" f ( 'b,\"b' , 'cccc' ) ", "f", "b,\"b", "cccc");
		assertInvocations(" f ( 'ddd' , 'eee', 'fff' ) ", "f", "ddd", "eee", "fff");
		assertInvocations(" f ( \"a\" ) ", "f", "a");
		assertInvocations(" f ( \"bb\" , \"cccc\" ) ", "f", "bb", "cccc");
		assertInvocations(" f ( \"ddd\" , \"eee\", \"fff\" ) ", "f", "ddd", "eee", "fff");
	}

	private void assertInvocations(String text, String expectedMethod, Object... expectedParameters) {
		assertInvocation(text, expectedMethod, expectedParameters);
		assertInvocation(text.replaceAll("\\s+", ""), expectedMethod, expectedParameters);
	}

	private void assertInvocation(String methodInvocationText, String expectedMethodName,
			Object... expectedParameterValues) {
		MethodInvocation i = methods.getMethodInvocation(methodInvocationText);
		assertEquals(expectedMethodName, i.getMethodName());
		assertEquals(expectedParameterValues.length, i.getParameters().length);
		assertArrayEquals(expectedParameterValues, i.getParameters());
		assertNotNull(methodInvocationText);
	}

	private SveikasisSkaicius getSv(Number number) {
		return new SveikasisSkaicius(number.toString());
	}

	private Trupmena getTr(Number skaitiklis, Number vardiklis) {
		return new Trupmena(skaitiklis.toString(), vardiklis.toString());
	}
}
