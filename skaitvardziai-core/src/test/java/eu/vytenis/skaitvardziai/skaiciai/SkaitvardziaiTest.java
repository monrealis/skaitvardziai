package eu.vytenis.skaitvardziai.skaiciai;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.zodziai.Zodis;

/**
 * Bazinė klasė tekstiniams testams.
 *
 */
public abstract class SkaitvardziaiTest {
	/** Vyriškosios giminės daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_VYR_G = new Zodis("šuo", "šuns", "šuniui", "šunį", "šuniu", "šunyje", "šunie", "šunes", "šunų", "šunims", "šunis",
			"šunimis", "šunyse", "šunys!"); // šunes :)
	/** Moteriškosios giminės daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_MOT_G = new Zodis("žema pilis", "žemos pilies", "žemai piliai", "žemą pilį", "žema pilimi", "žemoje pilyje", "žema pilie",
			"žemos pilys", "žemų pilių", "žemoms pilims", "žemas pilis", "žemomis pilimis", "žemose pilyse", "žemos pilys!");
	// du žodžiai - kad būtų unikalios reikšmės
	/** Vyriškosios giminės daugiskaitinis daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_DGS_VYR_G = new Zodis(Skaicius.D, "metai", "metų", "metams", "metus", "metais", "metuose", "metai!");
	/** Moteriškosios giminės daugiskaitinis daiktavardis su visais linksniais. */
	public static final Zodis DAIKT_DGS_MOT_G = new Zodis(Skaicius.D, "metai", "metų", "metams", "metus", "metais", "metuose", "metai!");

	static {
		for (Zodis z : Arrays.asList(DAIKT_VYR_G, DAIKT_MOT_G, DAIKT_DGS_VYR_G, DAIKT_DGS_MOT_G)) {
			Collection<String> v = z.getVisosFormos().values();
			int size = v.size();
			int uniqueSize = new HashSet<String>(v).size();
			assertEquals("Not all words unique for '" + z + "'", size, uniqueSize);
		}
	}

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

	protected String dVyrDgsV() {
		return " " + DAIKT_DGS_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.V));
	}

	protected String dVyrDgsK() {
		return " " + DAIKT_DGS_VYR_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K));
	}

	protected String dMotDgsV() {
		return " " + DAIKT_DGS_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.V));
	}

	protected String dMotDgsK() {
		return " " + DAIKT_DGS_MOT_G.toString(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K));
	}

	protected String gimineSkaiciusLinksnis(Gimine gimine, SkaiciusIrLinksnis skaiciusLinksnis) {
		Zodis z;
		if (gimine == Gimine.V) {
			z = DAIKT_VYR_G;
		} else if (gimine == Gimine.M) {
			z = DAIKT_MOT_G;
		} else {
			throw new IllegalArgumentException();
		}
		String r = z.getVisosFormos().get(skaiciusLinksnis);
		if (r == null) {
			throw new IllegalArgumentException();
		}
		return " " + r;
	}

	/**
	 * Iš teksto pabaigos, jei yra, pašalina paskutinį daiktvardį. Jei nėra,
	 * nieko nepakeičia. Pvz., pagal "dvidešimt vienas" grąžina
	 * "dvidešimt vienas", o pagal "dvidešimt vienas šuo" - "dvidešimt vienas".
	 * 
	 * @param text
	 *            pradinis tekstas
	 * @return tekstas be daiktvardžio (bet kokios formos), jei yra
	 */
	protected TekstasJunginyje removeDaikt(String text, Gimine gimine) {
		List<Zodis> zodziai = (gimine == Gimine.V) ? Arrays.asList(DAIKT_VYR_G, DAIKT_DGS_VYR_G) : Arrays.asList(DAIKT_MOT_G, DAIKT_DGS_MOT_G);
		for (Zodis z : zodziai) {
			for (Entry<SkaiciusIrLinksnis, String> e : z.getVisosFormos().entrySet()) {
				if (text.endsWith(e.getValue())) {
					String substring = text.substring(0, text.length() - e.getValue().length()).trim();
					return new TekstasJunginyje(substring, e.getKey());
				}
			}
		}
		return new TekstasJunginyje(text, null);
	}

	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Forma forma) {
		assertNotNull(forma);
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			tryTestSkaicius(forma, e.getKey(), e.getValue());
		}
	}

	private void tryTestSkaicius(Forma forma, Number number, String expectedValue) {
		try {
			testSkaicius(forma, number, expectedValue);
		} catch (Exception e) {
			throw new RuntimeException("Number " + number, e);
		}
	}

	private void testSkaicius(Forma forma, Number number, String expectedValue) {
		TekstasJunginyje zj = removeDaikt(expectedValue, forma.getGimine());
		SkaiciusIrLinksnis sl = zj.getKitoZodzioSkaiciusIrLinksnis();
		String expected = zj.getTekstas();
		SveikasisSkaicius sk = new SveikasisSkaicius(number.toString());
		TekstasJunginyje zodisJunginyje = sk.toZodisJunginyje(forma);
		String actual = zodisJunginyje.getTekstas();
		assertEquals(number + ": comparation failed", expected, actual);
		if (sl != null && (sl.getSkaicius() != null || sl.getLinksnis() != null)) {
			assertEquals(number + ": invalid form. Expected '" + expectedValue + "'", sl, zodisJunginyje.getKitoZodzioSkaiciusIrLinksnis());
		}
		if (sk.getReiksme().compareTo(BigInteger.ZERO) > 0) {
			String minusActual = new SveikasisSkaicius(sk.getReiksme().negate()).toString(forma);
			assertEquals("minus " + expected, minusActual);
		}
	}

	protected Map<BigInteger, String> createCombinedNumbers(Map<BigInteger, String> bigNumbers, Map<BigInteger, String> smallNumbers) {
		Map<BigInteger, String> combinedNumbers = new HashMap<BigInteger, String>();
		for (Entry<BigInteger, String> be : bigNumbers.entrySet()) {
			for (Entry<BigInteger, String> se : smallNumbers.entrySet()) {
				combinedNumbers.put(be.getKey().add(se.getKey()), be.getValue() + " " + se.getValue());
			}
		}
		return combinedNumbers;
	}

}
