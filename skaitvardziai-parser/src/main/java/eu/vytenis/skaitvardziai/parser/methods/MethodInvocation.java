package eu.vytenis.skaitvardziai.parser.methods;

public class MethodInvocation {
	private final String methodName;
	private final Object[] parameters;

	public MethodInvocation(String methodName, Object... parameters) {
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
