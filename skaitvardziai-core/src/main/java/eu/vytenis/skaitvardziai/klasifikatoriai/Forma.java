package eu.vytenis.skaitvardziai.klasifikatoriai;

public class Forma implements Cloneable {
	private Poskyris poskyris = Poskyris.Pagrindinis;
	private Gimine gimine = Gimine.V;
	private Skaicius skaicius = Skaicius.V;
	private Linksnis linksnis = Linksnis.V;
	private Rusis rusis = Rusis.P;

	public Forma() {

	}

	public Forma(Forma forma, Poskyris poskyris) {
		this.poskyris = poskyris;
		this.gimine = forma.gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;
	}

	public Forma(Forma forma, Gimine gimine) {
		this.poskyris = forma.poskyris;
		this.gimine = gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;
	}

	public Forma(Forma forma, Skaicius skaicius) {
		this.poskyris = forma.poskyris;
		this.gimine = forma.gimine;
		this.skaicius = skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;

	}

	public Forma(Forma forma, Linksnis linksnis) {
		this.poskyris = forma.poskyris;
		this.gimine = forma.gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = linksnis;
		this.rusis = forma.rusis;

	}

	public Forma(Forma forma, Rusis rusis) {
		this.poskyris = forma.poskyris;
		this.gimine = forma.gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = rusis;

	}

	public Poskyris getPoskyris() {
		return poskyris;
	}

	public Forma poskyris(Poskyris poskyris) {
		this.poskyris = poskyris;
		return this;
	}

	public Gimine getGimine() {
		return gimine;
	}

	public Forma gimine(Gimine gimine) {
		this.gimine = gimine;
		return this;
	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	public Forma skaicius(Skaicius skaicius) {
		this.skaicius = skaicius;
		return this;
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	public Forma linksnis(Linksnis linksnis) {
		this.linksnis = linksnis;
		return this;
	}

	public SkaiciusIrLinksnis toSkaiciusLinksnis() {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public Rusis getRusis() {
		return rusis;
	}

	public Forma rusis(Rusis rusis) {
		this.rusis = rusis;
		return this;
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
