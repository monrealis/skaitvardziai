package eu.vytenis.skaitvardziai.xpath;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


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
		long r = Long.parseLong(("" + skaicius).toString());
		FormaIrSkaiciai fs = Utils.parse(parametrai, null);
		fs.setSveikasisSkaicius(new BigInteger(skaicius + ""));
		fs.setPradinisSveikasisSkaicius(new BigInteger(skaicius + ""));
		return new SveikasisSkaicius(r).toString(fs);
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
		FormaIrSkaiciai fs = Utils.parse(parametrai, Arrays.<Class<?>>asList(Linksnis.class));
		return new Trupmena(skaitiklis, vardiklis).toString(fs.getLinksnis());
	}
	
	
	private static class Utils {
		private static final Map<String, Object> symbols;
		static {
			Map<String, Object> t = new HashMap<String, Object>();
			addAll(t, Linksnis.values());
			addAll(t, Skaicius.values());
			addAll(t, Gimine.values());
			addAll(t, Poskyris.values());
			symbols = Collections.unmodifiableMap(t);
		}
		private static void addAll(Map<String, Object> symbols, Enum<?>[] values) {
			for (Enum<?> e : values) {
				String name;
				if (e instanceof Skaicius) {
					name = ((Skaicius) e).alias();
				} else if (e instanceof Gimine) {
					name = ((Gimine) e).alias();
				} else {
					name = e.name();
				}
				if (symbols.containsKey(name)) {
					throw new IllegalArgumentException(e.name() + ": duplicate value");					
				}
				symbols.put(name, e);
			}
			
		}
		public static FormaIrSkaiciai parse(String parametrai, List<Class<?>> supportedParams) {
			if (supportedParams == null) {
				supportedParams = Arrays.<Class<?>>asList(Linksnis.class, Skaicius.class, Gimine.class, Poskyris.class);
			}
			String[] params = parametrai.isEmpty() ? new String[]{} : parametrai.split("\\s*,\\s*");
			FormaIrSkaiciai r = new FormaIrSkaiciai();
			for (String param : params) {
				Object o = symbols.get(param);
				if (o != null && !supportedParams.contains(o.getClass())) {
					throw new IllegalArgumentException(param + " is not supported, because parameter class " + o.getClass() + " is not supported"); 
				}
				if (o == null) {
					throw new IllegalArgumentException(param + " is not supported");
				} else if (o instanceof Linksnis) {
					r.setLinksnis((Linksnis) o);
				} else if (o instanceof Skaicius) {
					r.setSkaicius((Skaicius) o);
				} else if (o instanceof Gimine) {
					r.setGimine((Gimine) o);
				} else if (o instanceof Poskyris) {
					r.setPoskyris((Poskyris) o);
				}
			}
			return r;
		}
	}

}
