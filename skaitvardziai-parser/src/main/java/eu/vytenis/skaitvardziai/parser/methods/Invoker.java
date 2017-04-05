package eu.vytenis.skaitvardziai.parser.methods;

import static eu.vytenis.skaitvardziai.parser.methods.MethodKeys.getKey;
import static java.util.Collections.unmodifiableList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

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
		if (getKey(existingMethod).equals(getKey(newMethod)))
			throw new DuplicateMethodException(existingMethod, newMethod);
	}

	public List<Method> getMethods() {
		return unmodifiableList(methods);
	}

	public Object invoke(MethodInvocation invocation) {
		try {
			return getMethodOrFail(invocation.toKey()).invoke(null, invocation.getParameters());
		} catch (IllegalAccessException e) {
			throw new MethodInvocationException(e);
		} catch (IllegalArgumentException e) {
			throw new MethodInvocationException(e);
		} catch (InvocationTargetException e) {
			throw new MethodInvocationException(e);
		}
	}

	private Method getMethodOrFail(MethodKey key) {
		for (Method m : methods)
			if (getKey(m).equals(key))
				return m;
		throw new MethodNotFoundException(key.getMethodName(), key.getParameterCount());

	}

	public static class DuplicateMethodException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;
		private final Method method1;
		private final Method method2;

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
		private final String methodName;
		private final int parameterCount;

		public MethodNotFoundException(String methodName, int parameterCount) {
			super("Method not found. Name: " + methodName + ". Parameters: " + parameterCount);
			this.methodName = methodName;
			this.parameterCount = parameterCount;
		}

		public String getMethodName() {
			return methodName;
		}

		public int getParameterCount() {
			return parameterCount;
		}
	}

	public static class MethodInvocationException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;

		public MethodInvocationException(Exception nested) {
			super(nested);
		}
	}
}
