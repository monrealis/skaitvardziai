package eu.vytenis.skaitvardziai.klasifikatoriai;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;

public class Forma implements Cloneable, UnmodifiableCapable {
	private Poskyris poskyris = Poskyris.Pagrindinis;
	private Gimine gimine = Gimine.V;
	private Skaicius skaicius = Skaicius.V;
	private Linksnis linksnis = Linksnis.V;
	private Rusis rusis = Rusis.P;
	private boolean unmodifiable;

	public Poskyris getPoskyris() {
		return poskyris;
	}

	public void setPoskyris(Poskyris poskyris) {
		Checks.checkCanModify("Forma", this);
		this.poskyris = poskyris;
	}

	public Gimine getGimine() {
		return gimine;
	}

	public void setGimine(Gimine gimine) {
		Checks.checkCanModify("Forma", this);
		this.gimine = gimine;
	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	public void setSkaicius(Skaicius skaicius) {
		Checks.checkCanModify("Forma", this);
		this.skaicius = skaicius;
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	public void setLinksnis(Linksnis linksnis) {
		Checks.checkCanModify("Forma", this);
		this.linksnis = linksnis;
	}

	public SkaiciusIrLinksnis toSkaiciusLinksnis() {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public Rusis getRusis() {
		return rusis;
	}

	public void setRusis(Rusis rusis) {
		Checks.checkCanModify("Forma", this);
		this.rusis = rusis;
	}

	public boolean isUnmodifiable() {
		return unmodifiable;
	}

	public void setUnmodifiable(boolean unmodifiable) {
		Checks.checkCanModify("Forma", this);
		this.unmodifiable = unmodifiable;
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
