package eu.vytenis.skaitvardziai.app.template;

import eu.vytenis.skaitvardziai.parser.Invoker;
import eu.vytenis.skaitvardziai.parser.Methods;
import eu.vytenis.skaitvardziai.parser.tree.SimpleNode;

public class FunctionInvocationSource implements TextSource {
	private String unparsedText;

	public FunctionInvocationSource(String unparsedText) {
		this.unparsedText = unparsedText;
	}

	public String getText() {
		SimpleNode f = Methods.parse(unparsedText);
		String function = Methods.getFunction(f);
		Object[] args = Methods.getArguments(f);

		Invoker i = new Invoker();
		i.addPublicStaticMethods(Functions.class, Functions.class);
		Object r = i.invoke(function, args);
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
		if (obj instanceof FunctionInvocationSource) {
			return ((FunctionInvocationSource) obj).getUnparsedText().equals(getUnparsedText());
		} else {
			return false;
		}
	}

}
