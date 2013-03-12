package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Skaicius implements Aliased, FormosElementas {
	V("SV", "Vienaskaita"),
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
