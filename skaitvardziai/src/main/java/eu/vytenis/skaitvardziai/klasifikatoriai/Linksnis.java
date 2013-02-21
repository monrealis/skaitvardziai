package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Lietuvių kalbos linksnių sąrašas.
 */
public enum Linksnis implements Aliased, FormosElementas {
	/** Vardininkas. */
	V("V", "Vardininkas"),
	/** Kilmininkas. */
	K("K", "Kilmininkas"),
	/** Naudininkas. */
	N("N", "Naudininkas"),
	/** Galininkas. */
	G("G", "Galininkas"),
	/** Įnagininkas. */
	I("Į", "Įnagininkas"),
	/** Vietininkas. */
	Vt("Vt", "Vietininkas"),
	/** Šauksmininkas. */
	S("Š", "Šauksmininkas");
	private String alias;
	private String longName;
	
	private Linksnis(String alias, String longName) {
		this.alias = alias;
		this.longName = longName;		
	}
	
	public String alias() {
		return alias;
	};
	
	public String longName() {
		return longName;
	}
}
