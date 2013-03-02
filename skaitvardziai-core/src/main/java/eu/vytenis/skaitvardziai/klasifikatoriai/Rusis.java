package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Įvardžiuotinės formos požymis.
 *
 */
public enum Rusis implements Aliased, FormosElementas {
	/** Paprastoji (neįvardžiuotinė) forma. */
	P("P", "Paprastasis"),
	/** Įvardžiuotinė forma. */
	Iv("Įv", "Įvardžiuotinis");	
	
	private String alias;
	private String longName;
	
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