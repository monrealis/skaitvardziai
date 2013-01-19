package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaitvardžio skyrius.
 */
public enum Poskyris implements Aliased {
	Pagrindinis(Skyrius.Kiekinis, "PP", "Pagrindinis"),
	Dauginis(Skyrius.Kiekinis, "PD", "Dauginis"),
	Kuopinis(Skyrius.Kiekinis, "PK", "Kuopinis"),
	Trupmeninis(Skyrius.Kiekinis, "PT", "Trupmeninis"),
	Kelintinis(Skyrius.Kelintinis, "Kl", "Kelintinis");
	
	private Skyrius skyrius;
	private String alias;
	private String longName;
	
	private Poskyris(Skyrius skyrius, String alias, String longName) {
		this.skyrius = skyrius;
		this.alias = alias;
		this.longName = longName;
	}
	
	public Skyrius getSkyrius() {
		return skyrius;
	}
	
	public String alias() {
		return alias;
	}
	
	public String longName() {
		return longName;
	}

}