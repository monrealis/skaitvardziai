
package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.builder.BuilderCheckUtil;
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
import eu.vytenis.skaitvardziai.zodziai.ZodzioInfo;

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
	
	public String toString(Poskyris poskyris, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		Forma f = new Forma();
		f.setSkaicius(skaicius);
		f.setLinksnis(linksnis);
		f.setPoskyris(poskyris);
		f.setGimine(gimine);
		return toString(f);
	}
	
	public String toString(Poskyris poskyris, Linksnis linksnis, Gimine gimine) {
		return toString(poskyris, Skaicius.V, linksnis, gimine);
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
		SkaiciusIrLinksnis r = new SkaiciusIrLinksnis(null, null);
		
		boolean nonNegative = reiksme.compareTo(BigInteger.ZERO) >= 0;
		BigInteger abs =  nonNegative ? reiksme : reiksme.negate();
		
		FormaIrSkaiciai fs = new FormaIrSkaiciai(forma);
		fs.setSveikasisSkaicius(abs);
		fs.setPradinisSveikasisSkaicius(abs);		
		
		Poskyris poskyris = forma.getPoskyris();
		BuilderCheckUtil.checkSveikojoSkaiciausPoskyris(poskyris);

		SveikasisBuilder b = new SveikasisBuilder();
		b.build(fs);
		List<ZodzioInfo> zodziai = b.getZodziai();
		
		Zodis paskutinis = zodziai.iterator().next().getZodis();
		SkaiciusIrLinksnis kitas = paskutinis.getKitas();
		r.setSkaicius(kitas.getSkaicius() != null ? kitas.getSkaicius() : forma.getSkaicius());
		r.setLinksnis(kitas.getLinksnis() != null ? kitas.getLinksnis() : forma.getLinksnis());
		
		Collections.reverse(zodziai);
		String text = ZodzioInfo.toString(zodziai, fs);
		if (skaiciusIrLinksnis != null) {
			skaiciusIrLinksnis.setLinksnis(r.getLinksnis());
			skaiciusIrLinksnis.setSkaicius(r.getSkaicius());
		}
		if (!nonNegative) {
			text = "minus " + text;
		}
		return text;
	}
	
	public String toString(Linksnis linksnis, Gimine gimine) {
		return toString(Poskyris.Pagrindinis, linksnis, gimine);
	}

}
