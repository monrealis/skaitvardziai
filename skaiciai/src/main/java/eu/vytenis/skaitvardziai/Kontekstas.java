package eu.vytenis.skaitvardziai;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius) bei
 * patį skaičių.
 *
 */
public class Kontekstas implements Cloneable {
	/** Skaitvardžio poskyris. */
	private Poskyris poskyris = Poskyris.Pagrindinis;
	/** Skaitvardžio giminė. */
	private Gimine gimine = Gimine.V;
	/** Skaitvardžio skaičius. */
	private Skaicius skaicius = Skaicius.V;
	/** Skaitvardžio linksnis. */
	private Linksnis linksnis = Linksnis.V;
	/** Ar įvardžiuotinė forma.*/
	private boolean ivardziuotine = false;
	/** Skaičius, kurį bandoma parašyti, */
	private long pradinisSveikasSkaicius;
	/** Einamasis skaičius. Pvz., užrašinėjant skaičių 1002 pradinis skaičius gali būti 1002, o einamasis - 2.*/
	private long sveikasSkaicius;
	
	public Kontekstas() {
		
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
	public Linksnis getLinksnis() {
		return linksnis;
	}
	public boolean isIvardziuotine() {
		return ivardziuotine;
	}
	public void setIvardziuotine(boolean ivardziuotine) {
		this.ivardziuotine = ivardziuotine;
	}
	public void setLinksnis(Linksnis linksnis) {
		this.linksnis = linksnis;
	}
	public long getPradinisSveikasSkaicius() {
		return pradinisSveikasSkaicius;
	}
	public void setPradinisSveikasSkaicius(long pradinisSkaicius) {
		this.pradinisSveikasSkaicius = pradinisSkaicius;
	}
	public long getSveikasSkaicius() {
		return sveikasSkaicius;
	}
	public void setSveikasSkaicius(long skaicius) {
		this.sveikasSkaicius = skaicius;
	}
	
	public Skaicius getSkaicius() {
		return skaicius;
	}
	
	public void setSkaicius(Skaicius skaicius) {
		this.skaicius = skaicius;
	}
	
	/**
	 * Pakeičia poskyrį ir grąžina this objektą.
	 * @param poskyris naujas poskyris
	 * @return this
	 */
	public Kontekstas poskyris(Poskyris poskyris) {
		setPoskyris(poskyris);
		return this;
	}
	
	/**
	 * Pakeičia giminę ir grąžina this objektą
	 * @param gimine nauja giminė
	 * @return this
	 */
	public Kontekstas gimine(Gimine gimine) {
		setGimine(gimine);
		return this;
	}
	
	/**
	 * Pakeičia skaičių ir grąžina this objektą.
	 * @param naujasSkaicius naujas sveikasis skaičius
	 * @return this objektas
	 */
	public Kontekstas sveikasSkaicius(long naujasSkaicius) {
		setSveikasSkaicius(naujasSkaicius);
		return this;
	}

	/**
	 * Klonuoja objektą su visais jo laukais.
	 * @return klonas
	 */
	@Override
	public Kontekstas clone() {
		Kontekstas k;
		try {
			k = (Kontekstas) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return k;
	}

}
