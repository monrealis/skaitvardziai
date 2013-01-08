package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaičius: vienaskaita, daugiskaita.
 *
 */
public enum Skaicius {
	/** Vienaskaita. */
	V,
	/** Daugiskaita. */
	D;
	
	public String alias() {
		if (this == V) {
			return "vns";
		} else if (this == D) {
			return "dgs";
		} else {
			return name();
		}
	}

}
