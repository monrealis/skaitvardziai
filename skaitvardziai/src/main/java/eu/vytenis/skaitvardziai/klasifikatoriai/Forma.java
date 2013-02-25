package eu.vytenis.skaitvardziai.klasifikatoriai;

import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;

/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius).
 *
 */
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
	public Rusis getRusis() {
		return rusis;
	}
	public void setRusis(Rusis rusis) {
		this.rusis = rusis;
	}	
	public boolean isUnmodifiable() {
		return unmodifiable;
	}	
	public void setUnmodifiable(boolean unmodifiable) {
		this.unmodifiable = unmodifiable;
	}	
	public void setElementas(FormosElementas elementas) {
		if (elementas instanceof Linksnis) {
			setLinksnis((Linksnis) elementas);
		} else if (elementas instanceof Skaicius) {
			setSkaicius((Skaicius) elementas);
		} else if (elementas instanceof Gimine) {
			setGimine((Gimine) elementas);
		} else if (elementas instanceof Poskyris) {
			setPoskyris((Poskyris) elementas);
		} else if (elementas instanceof Rusis) {
			setRusis((Rusis) elementas);
		}
	}
	
	/**
	 * Klonuoja objektą su visais jo laukais.
	 * @return klonas
	 */
	@Override
	public Forma clone() {
		Forma k;
		try {
			k = (Forma) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return k;
	}

}
