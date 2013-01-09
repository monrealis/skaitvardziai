package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaičius: vienaskaita, daugiskaita.
 *
 */
public enum Skaicius implements Aliased {
	/** Vienaskaita. */
	V,
	/** Daugiskaita. */
	D;
	
	public String alias() {
		if (this == V) {
			return "SV";
		} else if (this == D) {
			return "SD";
		} else {
			return name();
		}
	}

}
