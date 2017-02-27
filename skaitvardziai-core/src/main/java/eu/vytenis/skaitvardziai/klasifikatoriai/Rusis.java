package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Rusis implements Aliased, FormosElementas {
	P("P", "Paprastasis"), //
	Iv("Įv", "Įvardžiuotinis");
	private final String alias;
	private final String longName;

	private Rusis(String alias, String longName) {
		this.alias = alias;
		this.longName = longName;
	}

	public String alias() {
		return alias;
	}

	public String longName() {
		return longName;
	}
}
