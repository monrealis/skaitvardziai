package eu.vytenis.skaitvardziai.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		Assert.assertEquals(3, parentAndChildInvoker.getMethods().size());		
		Assert.assertEquals(2, childInvoker.getMethods().size());
	}
	
	@Test(expected = Invoker.DuplicateMethodException.class)
	public void testAddPublicStaticMethods_Duplicate() {
		childInvoker.addPublicStaticMethods(ChildClass.class, ChildClass.class);
	}

	@Test
	public void testInvoke_Success() {
		Assert.assertEquals("child-f1", parentAndChildInvoker.invoke("f1", new Object[] {}));
		Assert.assertEquals(null, parentAndChildInvoker.invoke("f11", new Object[] {}));
		Assert.assertEquals(25, parentAndChildInvoker.invoke("f2", new Object[] {10, 15}));
	}
	
	@Test(expected = Invoker.MethodNotFoundException.class)
	public void testInvoke_FailedNoMethodName() {
		parentAndChildInvoker.invoke("x", new Object[] {});
	}
	
	@Test(expected = Invoker.MethodNotFoundException.class)
	public void testInvoke_FailedNoMethodParams() {
		parentAndChildInvoker.invoke("f2", new Object[] {15});
	}

	@Test(expected = Invoker.MethodInvocationException.class)
	public void testInvoke_FailedInvocationInvalidTypes() {
		parentAndChildInvoker.invoke("f2", new Object[] {"a", "b"});
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
		
		public static void f11() {			
		}
		
		protected static void f12() {
			
		}
	}

}
