package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.math.BigInteger;


/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius) bei
 * patį pradinį ir einamąjį skaičius.
 *
 */
// TODO išskaidyti į formą ir skaičius
public class FormaIrSkaiciai implements Cloneable {
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
	private BigInteger pradinisSveikasSkaicius;
	/** Einamasis skaičius. Pvz., užrašinėjant skaičių 1002 pradinis skaičius gali būti 1002, o einamasis - 2.*/
	private BigInteger sveikasSkaicius;
	
	public FormaIrSkaiciai() {
		
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
	public BigInteger getPradinisSveikasSkaicius() {
		return pradinisSveikasSkaicius;
	}
	public void setPradinisSveikasSkaicius(BigInteger pradinisSkaicius) {
		this.pradinisSveikasSkaicius = pradinisSkaicius;
	}
	public BigInteger getSveikasSkaicius() {
		return sveikasSkaicius;
	}
	public void setSveikasSkaicius(BigInteger skaicius) {
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
	public FormaIrSkaiciai poskyris(Poskyris poskyris) {
		setPoskyris(poskyris);
		return this;
	}
	
	/**
	 * Pakeičia giminę ir grąžina this objektą
	 * @param gimine nauja giminė
	 * @return this
	 */
	public FormaIrSkaiciai gimine(Gimine gimine) {
		setGimine(gimine);
		return this;
	}
	
	/**
	 * Pakeičia skaičių ir grąžina this objektą.
	 * @param naujasSkaicius naujas sveikasis skaičius
	 * @return this objektas
	 */
	public FormaIrSkaiciai sveikasSkaicius(BigInteger naujasSkaicius) {
		setSveikasSkaicius(naujasSkaicius);
		return this;
	}

	/**
	 * Klonuoja objektą su visais jo laukais.
	 * @return klonas
	 */
	@Override
	public FormaIrSkaiciai clone() {
		FormaIrSkaiciai k;
		try {
			k = (FormaIrSkaiciai) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return k;
	}

}
