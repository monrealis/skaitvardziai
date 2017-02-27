package eu.vytenis.skaitvardziai.klasifikatoriai;

public enum Gimine implements Aliased, FormosElementas {
	V("VG", "Vyriškas"), M("MG", "Moteriškas");
	private final String alias;
	private final String longName;

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
