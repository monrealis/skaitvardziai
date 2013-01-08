package eu.vytenis.skaitvardziai.klasifikatoriai;


/**
 * Giminė.
 */
public enum Gimine {
	/** Vyriška giminė.*/
	V,
	/** Moteriška giminė.*/
	M;
	
	public String alias() {
		if (this == V) {
			return "vyr";
		} else if (this == M) {
			return "mot";
		} else {
			return name();
		}
	}

}
