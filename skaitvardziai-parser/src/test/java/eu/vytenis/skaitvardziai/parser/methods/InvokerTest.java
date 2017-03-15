package eu.vytenis.skaitvardziai.parser.methods;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class InvokerTest {
	private Invoker parentAndChildInvoker = new Invoker();
	private Invoker childInvoker = new Invoker();

	@Before
	public void before() {
		parentAndChildInvoker.addPublicStaticMethods(ChildClass.class, ParentClass.class);
		childInvoker.addPublicStaticMethods(ChildClass.class, ChildClass.class);
	}

	@Test
	public void publicStaticMethodsAdded() {
		assertEquals(0, new Invoker().getMethods().size());
		assertEquals(4, parentAndChildInvoker.getMethods().size());
		assertEquals(3, childInvoker.getMethods().size());
	}

	@Test(expected = Invoker.DuplicateMethodException.class)
	public void duplicateMethodsNotAllowed() {
		childInvoker.addPublicStaticMethods(ChildClass.class, ChildClass.class);
	}

	@Test
	public void invokes() {
		assertEquals("child-f1", parentAndChildInvoker.invoke(new MethodInvocation("f1")));
		assertEquals("f11", parentAndChildInvoker.invoke(new MethodInvocation("f11")));
		assertEquals(25, parentAndChildInvoker.invoke(new MethodInvocation("f2", 10, 15)));
	}

	@Test
	public void invokesOverloaded() {
		assertEquals("f11", parentAndChildInvoker.invoke(new MethodInvocation("f11")));
		assertEquals("f11-1", parentAndChildInvoker.invoke(new MethodInvocation("f11", "a")));
	}

	@Test(expected = Invoker.MethodNotFoundException.class)
	public void invokeFailsIfMethodNotFound() {
		parentAndChildInvoker.invoke(new MethodInvocation("x"));
	}

	@Test(expected = Invoker.MethodNotFoundException.class)
	public void invokeFailsBecauseOfInvalidNumberOfArguments() {
		parentAndChildInvoker.invoke(new MethodInvocation("f2", 15));
	}

	@Test(expected = Invoker.MethodInvocationException.class)
	public void invokeFailsBecauseOfInvalidTypesOfArguments() {
		parentAndChildInvoker.invoke(new MethodInvocation("f2", "a", "b"));
	}

	public static class ParentClass {
		public static String f1() {
			return "parent-f1";
		}

		public static int f2(int first, int second) {
			return first + second;
		}

		protected static void f3() {
		}

		public void f4() {
		}

		protected void f5() {
		}
	}

	public static class ChildClass extends ParentClass {
		public static String f1() {
			return "child-f1";
		}

		public static String f11() {
			return "f11";
		}

		public static String f11(String param) {
			return "f11-1";
		}

		protected static String f12() {
			return "f12";
		}
	}
}
