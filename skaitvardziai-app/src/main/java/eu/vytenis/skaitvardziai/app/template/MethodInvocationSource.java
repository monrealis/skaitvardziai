package eu.vytenis.skaitvardziai.app.template;

import eu.vytenis.skaitvardziai.external.Facade;
import eu.vytenis.skaitvardziai.parser.Invoker;
import eu.vytenis.skaitvardziai.parser.MethodInvocation;
import eu.vytenis.skaitvardziai.parser.Methods;

public class MethodInvocationSource implements TextSource {
	private String unparsedText;

	public MethodInvocationSource(String unparsedText) {
		this.unparsedText = unparsedText;
	}

	public String getText() {
		MethodInvocation mi = Methods.getMethodInvocation(unparsedText);

		Invoker i = new Invoker();
		i.addPublicStaticMethods(Facade.class, Facade.class);
		Object r = i.invoke(mi);
		return r != null ? r.toString() : null;
	}

	public String getUnparsedText() {
		return unparsedText;
	}

	@Override
	public int hashCode() {
		return unparsedText.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MethodInvocationSource) {
			return ((MethodInvocationSource) obj).getUnparsedText().equals(getUnparsedText());
		} else {
			return false;
		}
	}

}
