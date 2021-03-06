package eu.vytenis.skaitvardziai.parser.methods;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import eu.vytenis.skaitvardziai.parser.methods.antrl4.Antlr4Methods;
import eu.vytenis.skaitvardziai.parser.methods.jjt.JjtMethods;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

@RunWith(Parameterized.class)
public class MethodsTest {
	private final Methods methods;
	private final Object Null = null;

	public MethodsTest(Methods methods) {
		this.methods = methods;
	}

	@Parameters
	public static Collection<Object[]> data() {
		List<Object> r = new ArrayList<>();
		r.add(new JjtMethods());
		r.add(Antlr4Methods.createListenerBased());
		r.add(Antlr4Methods.createVisitorBased());
		r.add(Antlr4Methods.createStateless());
		return r.stream().map(p -> new Object[] { p }).collect(toList());
	}

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
