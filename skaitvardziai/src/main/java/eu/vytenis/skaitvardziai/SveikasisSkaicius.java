
package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.builder.SveikasisBuilder;
import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.zodziai.Zodis;
import eu.vytenis.skaitvardziai.zodziai.ZodisJunginyje;

public class SveikasisSkaicius implements SkaitineReiksme {
	
	/** {@link BigInteger} skaičius 11. */
	public static BigInteger ELEVEN = new BigInteger("11");
	/** {@link BigInteger} skaičius 20. */
	public static BigInteger TWENTY = new BigInteger("20");
	/** {@link BigInteger} skaičius 100. */
	public static BigInteger HUNDRED = new BigInteger("100");
	/** {@link BigInteger} skaičius 1000. */
	public static BigInteger THOUSAND = new BigInteger("1000");
	/** {@link BigInteger} skaičius 1000 000 0000. */
	public static BigInteger BILLION = new BigInteger("1000000000");
	
	/** Skaitinė reikšmė (sveikasis skaičius). */
	private BigInteger reiksme;
	
	public SveikasisSkaicius(long reiksme) {
		this.reiksme = new BigInteger(Long.toString(reiksme));
	}
	
	public SveikasisSkaicius(String reiksme) {
		CheckUtil.checkNotNull("reiksme", reiksme);
		this.reiksme = new BigInteger(reiksme);
	}  
	
	public SveikasisSkaicius(BigInteger reiksme) {
		CheckUtil.checkNotNull("reiksme", reiksme);
		this.reiksme = reiksme;
	}
	
	public BigInteger getReiksme() {
		return reiksme;
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}

	public String toString(Linksnis linksnis, Gimine gimine) {
		return toString(Poskyris.Pagrindinis, linksnis, gimine);
	}
	
	public String toString(Poskyris poskyris, Linksnis linksnis, Gimine gimine) {
		return toString(poskyris, Skaicius.V, linksnis, gimine);
	}
	
	public String toString(Poskyris poskyris, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		Forma f = new Forma();
		f.setSkaicius(skaicius);
		f.setLinksnis(linksnis);
		f.setPoskyris(poskyris);
		f.setGimine(gimine);
		return toString(f);
	}
	
	public String toString(Forma forma) {
		return toString(forma, null);
	}
	
	/**
	 * Grąžina skaitvardžio tekstą.
	 * @param forma gramatinė skaitvardžio forma 
	 * @param skaiciusIrLinksnis jei ne null, metodas užpildo (grąžina rezultatą), kokia bus kito žodžio gramatinė forma (pvz, jei grąžina "dvi", tai kitas žodis bus daugiskaitos vardininkas) 
	 * @return skaitvardis (tekstas)
	 */
	public String toString(Forma forma, SkaiciusIrLinksnis skaiciusIrLinksnis) {
		BigInteger abs = getAbsReiksme();
		FormaIrSkaiciai fs = new FormaIrSkaiciai(forma, abs, abs);
		List<ZodisJunginyje> zodziai = getZodziai(fs);
		
		String text = ZodisJunginyje.toString(zodziai, fs);
		if (!isNonNegative()) {
			text = "minus " + text;
		}
		updateSkaiciusIrLinksnis(forma, skaiciusIrLinksnis, zodziai);
		return text;
	}
	
	private BigInteger getAbsReiksme() {
		BigInteger abs = isNonNegative() ? reiksme : reiksme.negate();
		return abs;
	}
	private boolean isNonNegative() {
		return reiksme.compareTo(BigInteger.ZERO) >= 0;
	}

	private List<ZodisJunginyje> getZodziai(FormaIrSkaiciai fs) {
		SveikasisBuilder b = new SveikasisBuilder();
		b.build(fs);
		List<ZodisJunginyje> zodziai = b.getZodziai();
		Collections.reverse(zodziai);
		return zodziai;
	}

	private void updateSkaiciusIrLinksnis(Forma forma, SkaiciusIrLinksnis skaiciusIrLinksnis, List<ZodisJunginyje> zodziai) {
		SkaiciusIrLinksnis r = new SkaiciusIrLinksnis(null, null);
		
		Zodis paskutinis = zodziai.get(zodziai.size() - 1).getZodis();
		SkaiciusIrLinksnis kitas = paskutinis.getKitas();
		r.setSkaicius(kitas.getSkaicius() != null ? kitas.getSkaicius() : forma.getSkaicius());
		r.setLinksnis(kitas.getLinksnis() != null ? kitas.getLinksnis() : forma.getLinksnis());
		if (skaiciusIrLinksnis != null) {
			skaiciusIrLinksnis.setLinksnis(r.getLinksnis());
			skaiciusIrLinksnis.setSkaicius(r.getSkaicius());
		}
	}


}
