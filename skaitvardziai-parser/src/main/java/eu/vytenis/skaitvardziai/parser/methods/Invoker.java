package eu.vytenis.skaitvardziai.parser.methods;

import static java.util.Collections.unmodifiableList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

// TODO 2017-03-18 add method name + parameter count value object
public class Invoker {
	private List<Method> methods = new ArrayList<Method>();

	public void addPublicStaticMethods(Class<?> type, Class<?> biggestAncestorTypeOfDeclaringClass) {
		Checks.checkNotNull("type", type);
		Checks.checkNotNull("mostGeneralType", biggestAncestorTypeOfDeclaringClass);
		List<Method> existingMethods = new ArrayList<Method>(methods);
		List<Method> newMethods = new ArrayList<Method>();
		for (Method m : type.getMethods())
			addIfPublicStatic(biggestAncestorTypeOfDeclaringClass, m, existingMethods, newMethods);
		methods.addAll(newMethods);
	}

	private void addIfPublicStatic(Class<?> biggestAncestorTypeOfDeclaringClass, Method method, List<Method> existingMethods, List<Method> newMethods) {
		if (!Modifier.isStatic(method.getModifiers()))
			return;
		if (!biggestAncestorTypeOfDeclaringClass.isAssignableFrom(method.getDeclaringClass()))
			return;
		checkCanAddMethod(existingMethods, method);
		existingMethods.add(method);
		newMethods.add(method);
	}

	private void checkCanAddMethod(Iterable<Method> existingMethods, Method newMethod) {
		for (Method m : existingMethods)
			checkDuplicateMethodDoesNotExist(m, newMethod);
	}

	private void checkDuplicateMethodDoesNotExist(Method existingMethod, Method newMethod) {
		if (!newMethod.getName().equals(existingMethod.getName()))
			return;
		if (newMethod.getParameterTypes().length != existingMethod.getParameterTypes().length)
			return;
		throw new DuplicateMethodException(existingMethod, newMethod);
	}

	public List<Method> getMethods() {
		return unmodifiableList(methods);
	}

	public Object invoke(MethodInvocation invocation) {
		try {
			return getMethodOrFail(invocation.getMethodName(), invocation.getParameters()).invoke(null, invocation.getParameters());
		} catch (IllegalAccessException e) {
			throw new MethodInvocationException(e);
		} catch (IllegalArgumentException e) {
			throw new MethodInvocationException(e);
		} catch (InvocationTargetException e) {
			throw new MethodInvocationException(e);
		}
	}

	private Method getMethodOrFail(String methodName, Object[] parameters) {
		for (Method m : methods)
			if (m.getName().equals(methodName) && m.getParameterTypes().length == parameters.length)
				return m;
		throw new MethodNotFoundException(methodName, parameters);

	}

	public static class DuplicateMethodException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 5765776642702346476L;
		private Method method1;
		private Method method2;

		public DuplicateMethodException(Method method1, Method method2) {
			super("Methods duplicate each other: " + method1 + ", " + method2);
			this.method1 = method1;
			this.method2 = method2;
		}

		public Method getMethod1() {
			return method1;
		}

		public Method getMethod2() {
			return method2;
		}
	}

	public static class MethodNotFoundException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;
		private String methodName;
		private Object[] parameters;

		public MethodNotFoundException(String methodName, Object[] parameters) {
			super("Method not found. Name: " + methodName + ". Parameters: " + Arrays.asList(parameters));
			this.methodName = methodName;
			this.parameters = parameters;
		}

		public String getMethodName() {
			return methodName;
		}

		public Object[] getParameters() {
			return parameters;
		}
	}

	public static class MethodInvocationException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;

		public MethodInvocationException(Exception nested) {
			super(nested);
		}
	}
}
