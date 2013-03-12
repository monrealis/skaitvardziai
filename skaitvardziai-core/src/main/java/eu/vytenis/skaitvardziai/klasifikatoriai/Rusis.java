package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Rusis implements Aliased, FormosElementas {
	P("P", "Paprastasis"),
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