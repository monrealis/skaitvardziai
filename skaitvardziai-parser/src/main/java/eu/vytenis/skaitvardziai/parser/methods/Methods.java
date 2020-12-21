package eu.vytenis.skaitvardziai.parser.methods;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.parser.methods.jjt.JjtMethods;

public abstract class Methods {
	public static Methods get() {
		return new JjtMethods();
	}

	public abstract MethodInvocation getMethodInvocation(String methodInvocationText);

	public static class SkaitvardziaiParseException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public SkaitvardziaiParseException(Exception cause) {
			super(cause);
		}
	}
}
