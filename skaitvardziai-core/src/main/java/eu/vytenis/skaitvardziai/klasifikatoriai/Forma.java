package eu.vytenis.skaitvardziai.klasifikatoriai;

public class Forma implements Cloneable {
	private final Poskyris poskyris;
	private final Gimine gimine;
	private final Skaicius skaicius;
	private final Linksnis linksnis;
	private final Rusis rusis;

	public Forma() {
		poskyris = Poskyris.Pagrindinis;
		gimine = Gimine.V;
		skaicius = Skaicius.V;
		linksnis = Linksnis.V;
		rusis = Rusis.P;
	}

	public Forma(Poskyris poskyris, Gimine gimine, Skaicius skaicius, Linksnis linksnis, Rusis rusis) {
		this.poskyris = poskyris;
		this.gimine = gimine;
		this.skaicius = skaicius;
		this.linksnis = linksnis;
		this.rusis = rusis;
	}

	private Forma(Forma forma, Poskyris poskyris) {
		this.poskyris = poskyris;
		this.gimine = forma.gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;
	}

	private Forma(Forma forma, Gimine gimine) {
		this.poskyris = forma.poskyris;
		this.gimine = gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;
	}

	private Forma(Forma forma, Skaicius skaicius) {
		this.poskyris = forma.poskyris;
		this.gimine = forma.gimine;
		this.skaicius = skaicius;
		this.linksnis = forma.linksnis;
		this.rusis = forma.rusis;

	}

	private Forma(Forma forma, Linksnis linksnis) {
		this.poskyris = forma.poskyris;
		this.gimine = forma.gimine;
		this.skaicius = forma.skaicius;
		this.linksnis = linksnis;
		this.rusis = forma.rusis;

	}

	private Forma(Forma forma, Rusis rusis) {
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
		return new Forma(this, poskyris);
	}

	public Gimine getGimine() {
		return gimine;
	}

	public Forma gimine(Gimine gimine) {
		return new Forma(this, gimine);

	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	public Forma skaicius(Skaicius skaicius) {
		return new Forma(this, skaicius);
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	public Forma linksnis(Linksnis linksnis) {
		return new Forma(this, linksnis);
	}

	public SkaiciusIrLinksnis toSkaiciusLinksnis() {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public Rusis getRusis() {
		return rusis;
	}

	public Forma rusis(Rusis rusis) {
		return new Forma(this, rusis);

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
