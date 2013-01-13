package eu.vytenis.skaitvardziai.klasifikatoriai;


/**
 * Giminė.
 */
public enum Gimine implements Aliased {
	/** Vyriška giminė.*/
	V,
	/** Moteriška giminė.*/
	M;
	
	public String alias() {
		if (this == V) {
			return "VG";
		} else if (this == M) {
			return "MG";
		} else {
			return name();
		}
	}

}
