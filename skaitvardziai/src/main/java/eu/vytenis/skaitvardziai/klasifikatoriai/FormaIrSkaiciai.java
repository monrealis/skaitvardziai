package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.math.BigInteger;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.checks.ImmutableCapable;


/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius) bei
 * patį pradinį ir einamąjį skaičius.
 *
 */
public class FormaIrSkaiciai implements Cloneable, ImmutableCapable {
	
	/** Gramatinė forma. */
	private Forma forma = new Forma();
	/** Skaičius, kurį bandoma parašyti, */
	private BigInteger pradinisSveikasisSkaicius;
	/** Einamasis skaičius. Pvz., užrašinėjant skaičių 1002 pradinis skaičius gali būti 1002, o einamasis - 2.*/
	private BigInteger sveikasisSkaicius;
	
	/** Ar žodis neredaguojamas? */
	private boolean immutable;
	
	public FormaIrSkaiciai() {
		
	}
	
	public FormaIrSkaiciai(Forma forma) {
		CheckUtil.checkNotNull("forma", forma);
		this.forma = forma;
	}
	
	public Forma getForma() {
		return forma;
	}

	public BigInteger getPradinisSveikasisSkaicius() {
		return pradinisSveikasisSkaicius;
	}
	public void setPradinisSveikasisSkaicius(BigInteger pradinisSkaicius) {
		this.pradinisSveikasisSkaicius = pradinisSkaicius;
	}
	public BigInteger getSveikasisSkaicius() {
		return sveikasisSkaicius;
	}
	public void setSveikasisSkaicius(BigInteger skaicius) {
		this.sveikasisSkaicius = skaicius;
	}

	/**
	 * Pakeičia poskyrį ir grąžina this objektą.
	 * @param poskyris naujas poskyris
	 * @return this
	 */
	public FormaIrSkaiciai poskyris(Poskyris poskyris) {
		getForma().setPoskyris(poskyris);
		return this;
	}
	
	/**
	 * Pakeičia giminę ir grąžina this objektą
	 * @param gimine nauja giminė
	 * @return this
	 */
	public FormaIrSkaiciai gimine(Gimine gimine) {
		getForma().setGimine(gimine);
		return this;
	}
	
	/**
	 * Pakeičia skaičių ir grąžina this objektą.
	 * @param naujasSkaicius naujas sveikasis skaičius
	 * @return this objektas
	 */
	public FormaIrSkaiciai sveikasSkaicius(BigInteger naujasSkaicius) {
		setSveikasisSkaicius(naujasSkaicius);
		return this;
	}
	
	public boolean isImmutable() {
		return immutable;
	}
	
	public void setImmutable(boolean immutable) {
		this.immutable = immutable;		
	}

	/**
	 * Klonuoja objektą su visais jo laukais.
	 * @return klonas
	 */
	@Override
	public FormaIrSkaiciai clone() {
		FormaIrSkaiciai fs;
		try {
			fs = (FormaIrSkaiciai) super.clone();
			fs.forma = forma.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return fs;
	}

}
