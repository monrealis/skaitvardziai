package eu.vytenis.skaiciai.klasifikatoriai;

/**
 * Skaitvardžio skyrius.
 */
public enum Poskyris {
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

}
