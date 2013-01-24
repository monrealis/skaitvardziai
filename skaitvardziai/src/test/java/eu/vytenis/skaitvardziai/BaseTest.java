package eu.vytenis.skaitvardziai;

import java.util.Map;
import java.util.Map.Entry;

import junit.framework.Assert;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.zodziai.Zodis;

/**
 * Bazinė klasė tekstiniams testams.
 *
 */
public abstract class BaseTest {

	// TODO dauginiams parinkti kitokius daiktavardžius, pavyzdžiui, "Joninės"
	/** Vyriškosios giminės daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_VYR_G = new Zodis("šuo", "šuns", "šuniui", "šunį", "šuniu", "šunyje", "šunie", "šunes", "šunų", "šunims", "šunis", "šunimis", "šunyse", "šunys!"); // šunes :)
	/** Moteriškosios giminės daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_MOT_G = new Zodis("katė", "katės", "katei", "katę", "kate", "katėje", "kate", "katės", "kačių", "katėms", "kates", "katėmis", "katėse", "katės!");

	
	protected String vyrVnsV() {
		return " " + DAIKT_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V));
	}

	protected String vyrVnsK() {
		return " " + DAIKT_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.K));
	}
	
	protected String vyrDgsV() {
		return " " + DAIKT_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.V));
	}
	
	protected String vyrDgsK() {
		return " " + DAIKT_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K));
	}
	
	protected String motVnsV() {
		return " " + DAIKT_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V));
	}

	protected String motVnsK() {
		return " " + DAIKT_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.K));
	}
	
	protected String motDgsV() {
		return " " + DAIKT_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.V));
	}
	
	protected String motDgsK() {
		return " " + DAIKT_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K));
	}

	
	/**
	 * Iš teksto pabaigos, jei yra, pašalina paskutinį daiktvardį. Jei nėra, nieko nepakeičia.
	 * Pvz., pagal "dvidešimt vienas" grąžina "dvidešimt vienas", o pagal "dvidešimt vienas šuo" - "dvidešimt vienas".
	 * @param text pradinis tekstas
	 * @return tekstas be daiktvardžio (bet kokios formos), jei yra
	 */
	protected String removeDaikt(String text) {
		Assert.assertNotNull(gimine);
		Zodis z = gimine == Gimine.V ? DAIKT_VYR_G : DAIKT_MOT_G;
		for (String s : z.getVisosFormos().values()) {
			if (text.endsWith(s)) {
				return text.substring(0, text.length() - s.length()).trim();
			}
		}
		return text;
	}
	
	/**
	 * Jei teksto pabaigoje yra daiktavardis, grąžina jo skaičių ir linksnį. Jei nėra - grąžina null.
	 * Pavyzdžiui, iš "dešimt šunų" grąžina "daugiskaitos kilmininkas".
	 * @param text pradinis tekstas
	 * @return daiktvardžio skaičius ir linksnis arba null
	 */
	protected SkaiciusIrLinksnis getDaiktSkaiciusIrLinksnis(String text) {
		for (Entry<SkaiciusIrLinksnis, String> e: DAIKT_VYR_G.getVisosFormos().entrySet()) {
			if (text.endsWith(e.getValue())) {				
				return e.getKey();
			}
		}
		return null;
	}
	
	protected Gimine gimine;
	protected Poskyris poskyris;
	
	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		Assert.assertNotNull(gimine);
		Assert.assertNotNull(poskyris);
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = removeDaikt(e.getValue());
			SkaiciusIrLinksnis sl = getDaiktSkaiciusIrLinksnis(e.getValue());
			long number = e.getKey().longValue();
			SveikasisSkaicius sk = new SveikasisSkaicius(number);
			
			Forma f = new Forma();
			f.setPoskyris(poskyris);
			f.setLinksnis(linksnis);
			f.setGimine(gimine);
			SkaiciusIrLinksnis actualSl = new SkaiciusIrLinksnis();
			actualSl.clear();
			String actual = sk.toString(f, actualSl);
			Assert.assertEquals(expected, actual);
			if (sl != null) {
				Assert.assertEquals("Invalid form. Expected '" + e.getValue() + "' for " + number, sl, actualSl);
			}
		}
	}
}


////
///
///
//