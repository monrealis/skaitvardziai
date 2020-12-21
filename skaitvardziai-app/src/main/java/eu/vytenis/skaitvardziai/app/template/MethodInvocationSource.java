package eu.vytenis.skaitvardziai.app.template;

import eu.vytenis.skaitvardziai.external.Facade;
import eu.vytenis.skaitvardziai.parser.methods.Invoker;
import eu.vytenis.skaitvardziai.parser.methods.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.methods.Methods;

public class MethodInvocationSource implements TextSource {
	private final Methods methods = Methods.get();
	private final String unparsedText;

	public MethodInvocationSource(String unparsedText) {
		this.unparsedText = unparsedText;
	}

	public String getText() {
		Object r = getInvoker().invoke(getInvocation());
		return toString(r);
	}

	private Invoker getInvoker() {
		Invoker invoker = new Invoker();
		invoker.addPublicStaticMethods(Facade.class, Facade.class);
		return invoker;
	}

	private MethodInvocation getInvocation() {
		return methods.getMethodInvocation(unparsedText);
	}

	private String toString(Object optionalObject) {
		if (optionalObject == null)
			return null;
		return optionalObject.toString();
	}

	private String getUnparsedText() {
		return unparsedText;
	}

	@Override
	public int hashCode() {
		return unparsedText.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof MethodInvocationSource))
			return false;
		return ((MethodInvocationSource) obj).getUnparsedText().equals(getUnparsedText());
	}
}
