package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Įvardžiuotinės formos požymis.
 *
 */
public enum Ivardziuotinis implements Aliased {
	/** Įvardžiuotinė forma. */
	Iv("Įv", "Įvardžiuotinis");
	private String alias;
	private String longName;
	
	private Ivardziuotinis(String alias, String longName) {
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