package eu.vytenis.skaitvardziai.parser;


public class MethodInvocation {
	private String methodName;
	private Object[] parameters;
	
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
