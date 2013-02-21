package eu.vytenis.skaitvardziai.klasifikatoriai;

import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;

/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius).
 *
 */
public class Forma implements Cloneable, UnmodifiableCapable {
	/** Skaitvardžio poskyris. */
	private Poskyris poskyris = Poskyris.Pagrindinis;
	/** Skaitvardžio giminė. */
	private Gimine gimine = Gimine.V;
	/** Skaitvardžio skaičius. */
	private Skaicius skaicius = Skaicius.V;
	/** Skaitvardžio linksnis. */
	private Linksnis linksnis = Linksnis.V;
	/** Ar įvardžiuotinė forma.*/
	private boolean ivardziuotine = false; // TODO padaryti enum
	/** Ar forma neredaguojama? */
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
	public boolean isIvardziuotine() {
		return ivardziuotine;
	}
	public void setIvardziuotine(boolean ivardziuotine) {
		this.ivardziuotine = ivardziuotine;
	}
	
	/** Žiūrėti: {@link UnmodifiableCapable#isUnmodifiable()}. */
	public boolean isUnmodifiable() {
		return unmodifiable;
	}
	
	/** Žiūrėti: {@link UnmodifiableCapable#setUnmodifiable(boolean)}. */
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
		} else if (elementas instanceof Ivardziuotinis) {
			setIvardziuotine(true);
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
