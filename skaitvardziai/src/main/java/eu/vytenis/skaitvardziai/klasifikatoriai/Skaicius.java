package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaičius: vienaskaita, daugiskaita.
 *
 */
public enum Skaicius implements Aliased {
	/** Vienaskaita. */
	V("SV", "Vienaskaita"),
	/** Daugiskaita. */
	D("SD", "Daugiskaita");
	private String alias;
	private String longName;
	
	private Skaicius(String alias, String longName) {
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
