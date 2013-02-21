package eu.vytenis.skaitvardziai.xpath;

import java.util.Arrays;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;


/**
 * XPath funkcijų klasė XSLT procesoriams.
 * Kol kas palaikomas standartinis JRE procesorius (padarytas iš XALAN) ir Saxon procesorius.
 *
 */
public class SkaiciusXPathFunctions {
	
	/** Daugiskaitos kilmininkas. */
	public static SkaiciusIrLinksnis DGS_K = new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K);
	
	/**
	 * Grąžina kiekinio sveikojo skaičiaus vardininką.
	 * @param skaicius sveikasis skaičius
	 * @return tekstinė skaitvardžio reikšmė
	 */
	public static String sveikasis(int skaicius) {
		return sveikasis(skaicius, null);
	}
	
	/**
	 * Grąžina kiekinio sveikojo skaičiaus vardininką.
	 * @param skaicius sveikasis skaičius
	 * @return tekstinė skaitvardžio reikšmė
	 */
	public static String sveikasis(int skaicius, String forma) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, null);
		return new SveikasisSkaicius(Integer.toString(skaicius)).toString(f);
	}
	
	
	/**
	 * Grąžina trupmeninio skaičiaus (paprastosios trupmenos) vardininką.
	 * @param skaitiklis skaitiklis
	 * @param vardiklis vardiklis
	 * @return tekstinė skaitvardžio reikšmė
	 */
	public static String trupmena(int skaitiklis, int vardiklis) {
		return trupmena(skaitiklis, vardiklis, null);
	}
	
	/**
	 * Grąžina trupmeninio skaičiaus (paprastosios trupmenos) vardininką.
	 * @param skaitiklis skaitiklis
	 * @param vardiklis vardiklis
	 * @return tekstinė skaitvardžio reikšmė
	 */
	@SuppressWarnings("unchecked")
	public static String trupmena(int skaitiklis, int vardiklis, String forma) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, Arrays.<Class<? extends FormosElementas>>asList(Linksnis.class));
		return new Trupmena(skaitiklis, vardiklis).toString(f.getLinksnis());
	}
	
	
	/**
	 * Grąžina po skaitvardžio einančius tinkamos formos kitus žodžius.
	 * @param skaicius skaičius
	 * @param forma
	 * @param vns vienaskaitos atitinkamas linksnis (pavyzdžiui, "geltonas šuo", "geltonam šuniui")
	 * @param dgs daugiskaitos atitinkamas linksnis (pavyzdžiui, "geltoni šunys", "geltoniems šunims")
	 * @param dgsKilm daugiskaitos kilmininkas (pavyzdžiui, "geltonų šunų")
	 * @return reikiamos formos žodžiai
	 */
	public static String kiti(int skaicius, String vns, String dgs, String dgsKilm) {
		return kiti(skaicius, null, vns, dgs, dgsKilm);				
	}
	
	/**
	 * Grąžina po skaitvardžio einančius tinkamos formos kitus žodžius.
	 * @param skaicius skaičius
	 * @param forma
	 * @param vns vienaskaitos atitinkamas linksnis (pavyzdžiui, "geltonas šuo", "geltonam šuniui")
	 * @param dgs daugiskaitos atitinkamas linksnis (pavyzdžiui, "geltoni šunys", "geltoniems šunims")
	 * @param dgsKilm daugiskaitos kilmininkas (pavyzdžiui, "geltonų šunų")
	 * @return reikiamos formos žodžiai
	 */
	public static String kiti(int skaicius, String forma, String vns, String dgs, String dgsKilm) {
		if (forma == null) {
			forma = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(forma, null);
		SkaiciusIrLinksnis kitas = new SkaiciusIrLinksnis(null, null);
		new SveikasisSkaicius(Integer.toString(skaicius)).toString(f, kitas);
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
