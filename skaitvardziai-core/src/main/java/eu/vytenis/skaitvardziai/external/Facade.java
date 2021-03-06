package eu.vytenis.skaitvardziai.external;

import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
import eu.vytenis.skaitvardziai.text.FormaParser;

/**
 * Metodai, kuriuos kviečia išoriniai (ne JAVA) API.
 */
public class Facade {
	private static final List<Class<? extends FormosElementas>> LINKSNIS = Arrays.<Class<? extends FormosElementas>> asList(Linksnis.class);
	private static final SkaiciusIrLinksnis DGS_K = new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K);

	public static String sveikasis(SveikasisSkaicius skaicius) {
		return sveikasis(skaicius, "");
	}

	public static String sveikasis(SveikasisSkaicius skaicius, String forma) {
		Forma f = new FormaParser(nullToEmpty(forma)).parse();
		return skaicius.toString(f);
	}

	public static String trupmena(Trupmena trupmena) {
		return trupmena(trupmena, "");
	}

	public static String trupmena(Trupmena trupmena, String forma) {
		Forma f = new FormaParser(nullToEmpty(forma), LINKSNIS).parse();
		return trupmena.toString(f.getLinksnis());
	}

	public static String kiti(SveikasisSkaicius skaicius, String vns, String dgs, String dgsKilm) {
		return kiti(skaicius, "", vns, dgs, dgsKilm);
	}

	public static String kiti(SveikasisSkaicius skaicius, String forma, String vns, String dgs, String dgsKilm) {
		Forma f = new FormaParser(nullToEmpty(forma)).parse();
		SkaiciusIrLinksnis kitas = skaicius.getKitoZodzioSkaiciusIrLinksnis(f);
		if (kitas.equals(DGS_K))
			return getKitasIfNextDgsK(dgs, dgsKilm, f.getLinksnis());
		else if (kitas.getSkaicius() == Skaicius.D)
			return dgs;
		else if (kitas.getSkaicius() == Skaicius.V)
			return vns;
		else
			return null;
	}

	private static String getKitasIfNextDgsK(String dgs, String dgsKilm, Linksnis linksnis) {
		if (nullToEmpty(dgsKilm).length() > 0)
			return dgsKilm;
		else if (linksnis == Linksnis.K && dgs != null)
			return dgs;
		else
			return null;
	}

	private static String nullToEmpty(String forma) {
		if (forma == null)
			forma = "";
		return forma;
	}
}
