package eu.vytenis.skaitvardziai.external;

import java.util.Arrays;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
import eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParser;

/**
 * Metodai, kuriuos kviečia išoriniai (ne JAVA) API.
 */
public class Facade {
	
	public static SkaiciusIrLinksnis DGS_K = new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K);

	public static String sveikasis(SveikasisSkaicius skaicius) {
		return sveikasis(skaicius, null);
	}
	
	public static String sveikasis(SveikasisSkaicius skaicius, String forma) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, null);
		return skaicius.toString(f);
	}
	
	public static String trupmena(Trupmena trupmena) {
		return trupmena(trupmena, null);
	}

	@SuppressWarnings("unchecked")
	public static String trupmena(Trupmena trupmena, String forma) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, Arrays.<Class<? extends FormosElementas>>asList(Linksnis.class));
		return trupmena.toString(f.getLinksnis());
	}
	
	public static String kiti(SveikasisSkaicius skaicius, String vns, String dgs, String dgsKilm) {
		return kiti(skaicius, null, vns, dgs, dgsKilm);				
	}

	public static String kiti(SveikasisSkaicius skaicius, String forma, String vns, String dgs, String dgsKilm) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, null);
		SkaiciusIrLinksnis kitas = new SkaiciusIrLinksnis(null, null);
		skaicius.toString(f, kitas);
		if (kitas.equals(DGS_K)) {
			if (dgsKilm != null && dgsKilm.length() > 0) {
				return dgsKilm;
			} else if (/*f.getSkaicius() == Skaicius.D && */f.getLinksnis() == Linksnis.K && dgs != null) {
				return dgs;
			} else {
				return null;
			}
		} else if (kitas.getSkaicius() == Skaicius.D) {
			return dgs;
		} else if (kitas.getSkaicius() == Skaicius.V) {
			return vns;
		} else {
			return null;
		}
	}
}
