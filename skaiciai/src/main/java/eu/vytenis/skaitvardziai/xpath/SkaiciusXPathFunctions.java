package eu.vytenis.skaitvardziai.xpath;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;


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
		long r = Long.parseLong(("" + skaicius).toString());
		return new SveikasisSkaicius(r).toString();
	}
	
	
	/**
	 * Grąžina trupmeninio skaičiaus (paprastosios trupmenos) vardininką.
	 * @param skaitiklis skaitiklis
	 * @param vardiklis vardiklis
	 * @return tekstinė skaitvardžio reikšmė
	 */
	public static String trupmena(int skaitiklis, int vardiklis) {
		return new Trupmena(skaitiklis, vardiklis).toString();
	}

}
