package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaitvardžio skyrius.
 */
public enum Poskyris implements Aliased {
	Pagrindinis(Skyrius.Kiekinis),
	Dauginis(Skyrius.Kiekinis),
	Kuopinis(Skyrius.Kiekinis),
	Trupmeninis(Skyrius.Kiekinis),
	Kelintinis(Skyrius.Kelintinis);
	
	private Skyrius skyrius;
	
	private Poskyris(Skyrius skyrius) {
		this.skyrius = skyrius;
	}
	
	public Skyrius getSkyrius() {
		return skyrius;
	}
	
	public String alias() {
		if (this == Pagrindinis) {
			return "PP";
		} else if (this == Dauginis) {
			return "PD";
		} else if (this == Kuopinis) {
			return "PK";
		} else if (this == Trupmeninis) {
			return "PT";
		} else if (this == Kelintinis) {
			return "Kl";
		} else {
			return name();
		}
	}

}
