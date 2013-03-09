package eu.vytenis.skaitvardziai.parser.methods;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.parser.methods.Invoker;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;

public class InvokerTest {

	private Invoker parentAndChildInvoker;
	private Invoker childInvoker;
	
	@Before
	public void before() {
		parentAndChildInvoker = new Invoker();
		childInvoker = new Invoker();
		
		parentAndChildInvoker.addPublicStaticMethods(ChildClass.class, ParentClass.class);
		childInvoker.addPublicStaticMethods(ChildClass.class, ChildClass.class);
	}
	@Test
	public void testAddPublicStaticMethods() {
		Assert.assertEquals(0, new Invoker().getMethods().size());		
		Assert.assertEquals(4, parentAndChildInvoker.getMethods().size());		
		Assert.assertEquals(3, childInvoker.getMethods().size());
	}
	
	@Test(expected = Invoker.DuplicateMethodException.class)
	public void testAddPublicStaticMethods_Duplicate() {
		childInvoker.addPublicStaticMethods(ChildClass.class, ChildClass.class);
	}

	@Test
	public void testInvoke_Success() {
		Assert.assertEquals("child-f1", parentAndChildInvoker.invoke(new MethodInvocation("f1")));
		Assert.assertEquals("f11", parentAndChildInvoker.invoke(new MethodInvocation("f11")));
		Assert.assertEquals(25, parentAndChildInvoker.invoke(new MethodInvocation("f2", 10, 15)));
	}
	
	@Test
	public void testInvoke_OverloadedMethodsSuccess() {
		Assert.assertEquals("f11", parentAndChildInvoker.invoke(new MethodInvocation("f11")));
		Assert.assertEquals("f11-1", parentAndChildInvoker.invoke(new MethodInvocation("f11", "a")));
	}
	
	@Test(expected = Invoker.MethodNotFoundException.class)
	public void testInvoke_FailedNoMethodName() {
		parentAndChildInvoker.invoke(new MethodInvocation("x"));
	}
	
	@Test(expected = Invoker.MethodNotFoundException.class)
	public void testInvoke_FailedNoMethodParams() {
		parentAndChildInvoker.invoke(new MethodInvocation("f2", 15));
	}

	@Test(expected = Invoker.MethodInvocationException.class)
	public void testInvoke_FailedInvocationInvalidTypes() {
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
		
		public static String f11(String arg) {
			return "f11-1";
		}
		
		protected static String f12() {
			return "f12";
		}

	}

}
