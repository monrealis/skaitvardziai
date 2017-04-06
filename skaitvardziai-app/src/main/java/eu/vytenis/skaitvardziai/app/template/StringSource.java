package eu.vytenis.skaitvardziai.app.template;

public class StringSource implements TextSource {
	private final String text;

	public StringSource(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public int hashCode() {
		return text.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StringSource))
			return false;
		StringSource other = (StringSource) obj;
		return other.getText().equals(getText());
	}
}
