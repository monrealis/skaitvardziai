package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Linksnis implements Aliased, FormosElementas {
	V("V", "Vardininkas"),
	K("K", "Kilmininkas"),
	N("N", "Naudininkas"),
	G("G", "Galininkas"),
	I("Į", "Įnagininkas"),
	Vt("Vt", "Vietininkas"),
	S("Š", "Šauksmininkas");
	
	private String alias;
	private String longName;
	
	private Linksnis(String alias, String longName) {
		this.alias = alias;
		this.longName = longName;		
	}
	
	public String alias() {
		return alias;
	};
	
	public String longName() {
		return longName;
	}
}
