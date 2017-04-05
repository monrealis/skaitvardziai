package eu.vytenis.skaitvardziai.parser.methods;

import java.lang.reflect.Method;

public class MethodKeys {
	public static MethodKey getKey(Method method) {
		return new MethodKey(method.getName(), method.getParameterTypes().length);
	}
}
