package eu.vytenis.skaitvardziai.klasifikatoriai;


/**
 * Giminė.
 */
public enum Gimine implements Aliased, FormosElementas {
	/** Vyriška giminė.*/
	V("VG", "Vyriškas"),
	/** Moteriška giminė.*/
	M("MG", "Moteriškas");
	
	private String alias;
	private String longName;
	
	private Gimine(String alias, String longName) {
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
