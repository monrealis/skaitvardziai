package eu.vytenis.skaitvardziai.xpath;

import eu.vytenis.skaitvardziai.external.Facade;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

/**
 * XPath funkcijų klasė XSLT procesoriams. Palaikomas standartinis Sun JRE procesorius (padarytas iš XALAN), XALAN ir Saxon procesoriai.
 */
public class SkaitvardziaiXPathFunctions {
	public static String sveikasis(int skaicius) {
		return Facade.sveikasis(new SveikasisSkaicius(skaicius));
	}

	public static String sveikasis(int skaicius, String forma) {
		return Facade.sveikasis(new SveikasisSkaicius(skaicius), forma);
	}

	public static String trupmena(int skaitiklis, int vardiklis) {
		return Facade.trupmena(new Trupmena(skaitiklis, vardiklis));
	}

	public static String trupmena(int skaitiklis, int vardiklis, String forma) {
		return Facade.trupmena(new Trupmena(skaitiklis, vardiklis), forma);
	}

	public static String kiti(int skaicius, String vns, String dgs, String dgsKilm) {
		return Facade.kiti(new SveikasisSkaicius(skaicius), vns, dgs, dgsKilm);
	}

	public static String kiti(int skaicius, String forma, String vns, String dgs, String dgsKilm) {
		return Facade.kiti(new SveikasisSkaicius(skaicius), forma, vns, dgs, dgsKilm);
	}
}
