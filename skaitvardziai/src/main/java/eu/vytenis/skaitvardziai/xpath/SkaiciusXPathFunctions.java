package eu.vytenis.skaitvardziai.xpath;

import java.util.Arrays;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;


/**
 * XPath funkcijų klasė XSLT procesoriams.
 * Kol kas palaikomas standartinis JRE procesorius (padarytas iš XALAN) ir Saxon procesorius.
 *
 */
public class SkaiciusXPathFunctions {
	
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
	public static String sveikasis(int skaicius, String parametrai) {
		if (parametrai == null) {
			parametrai = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(parametrai, null);
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
	public static String trupmena(int skaitiklis, int vardiklis, String parametrai) {
		if (parametrai == null) {
			parametrai = "";
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(parametrai, Arrays.<Class<?>>asList(Linksnis.class));
		return new Trupmena(skaitiklis, vardiklis).toString(f.getLinksnis());
	}

}
