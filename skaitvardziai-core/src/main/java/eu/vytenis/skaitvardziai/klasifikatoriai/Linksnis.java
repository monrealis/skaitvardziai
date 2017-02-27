package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Linksnis implements Aliased, FormosElementas {
	// @formatter:off
	V("V", "Vardininkas"),
	K("K", "Kilmininkas"),
	N("N", "Naudininkas"),
	G("G", "Galininkas"),
	I("Į", "Įnagininkas"),
	Vt("Vt", "Vietininkas"),
	S("Š", "Šauksmininkas");
	// @formatter:on
	private final String alias;
	private final String longName;

	private Linksnis(String alias, String longName) {
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
