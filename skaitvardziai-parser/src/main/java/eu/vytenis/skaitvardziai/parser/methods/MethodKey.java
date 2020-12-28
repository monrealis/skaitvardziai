package eu.vytenis.skaitvardziai.parser.methods;

import static java.util.Arrays.asList;

import java.util.List;

class MethodKey {
	private final String methodName;
	private final int parameterCount;

	public MethodKey(String methodName, int parameterCount) {
		this.methodName = methodName;
		this.parameterCount = parameterCount;
	}

	public String getMethodName() {
		return methodName;
	}

	public int getParameterCount() {
		return parameterCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MethodKey))
			return false;
		return toList().equals(((MethodKey) obj).toList());
	}

	@Override
	public int hashCode() {
		return toList().hashCode();
	}

	private List<?> toList() {
		return asList(methodName, parameterCount, getClass());
	}
}
