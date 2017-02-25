package eu.vytenis.skaitvardziai.klasifikatoriai;

public class Forma implements Cloneable {
	private Poskyris poskyris = Poskyris.Pagrindinis;
	private Gimine gimine = Gimine.V;
	private Skaicius skaicius = Skaicius.V;
	private Linksnis linksnis = Linksnis.V;
	private Rusis rusis = Rusis.P;

	public Forma() {

	}

	public Poskyris getPoskyris() {
		return poskyris;
	}

	public void setPoskyris(Poskyris poskyris) {
		this.poskyris = poskyris;
	}

	public Gimine getGimine() {
		return gimine;
	}

	public void setGimine(Gimine gimine) {
		this.gimine = gimine;
	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	public void setSkaicius(Skaicius skaicius) {
		this.skaicius = skaicius;
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	public void setLinksnis(Linksnis linksnis) {
		this.linksnis = linksnis;
	}

	public SkaiciusIrLinksnis toSkaiciusLinksnis() {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public Rusis getRusis() {
		return rusis;
	}

	public void setRusis(Rusis rusis) {
		this.rusis = rusis;
	}

	@Override
	public Forma clone() {
		try {
			return (Forma) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
