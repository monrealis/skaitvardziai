package eu.vytenis.skaitvardziai.xpath;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
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
		Forma f = Utils.parse(parametrai, null);
		return new SveikasisSkaicius(r).toString(f);
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
		Forma f = Utils.parse(parametrai, Arrays.<Class<?>>asList(Linksnis.class));
		return new Trupmena(skaitiklis, vardiklis).toString(f.getLinksnis());
	}
	
	
	public static class Utils {
		private static final Map<String, Object> symbols;
		static {
			Map<String, Object> t = new HashMap<String, Object>();
			addAll(t, Linksnis.values());
			addAll(t, Skaicius.values());
			addAll(t, Gimine.values());
			addAll(t, Poskyris.values());
			symbols = Collections.unmodifiableMap(t);
		}
		private static void addAll(Map<String, Object> symbols, Enum<? extends Aliased>[] values) {
			for (Enum<? extends Aliased> e : values) {
				Aliased a = (Aliased) e;
				String alias = a.alias();
				if (symbols.containsKey(alias)) {
					throw new IllegalArgumentException(alias + ": duplicate value. "
							+ e.getClass().getSimpleName() + " and " + symbols.get(alias).getClass().getSimpleName());					
				}
				symbols.put(alias, e);
			}
			
		}
		public static Forma parse(String parametrai, List<Class<?>> supportedParams) {
			if (supportedParams == null) {
				supportedParams = Arrays.<Class<?>>asList(Linksnis.class, Skaicius.class, Gimine.class, Poskyris.class);
			}
			String[] params = parametrai.isEmpty() ? new String[]{} : parametrai.split("\\s*,\\s*");
			Forma r = new Forma();
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
		
		public static SkaitineReiksme parse(String skaicius) {
			Pattern tr = Pattern.compile("(\\d+)\\s*/\\s*(\\d+)");
			Matcher m = tr.matcher(skaicius);
			if (skaicius.matches("\\d+")) {
				return new SveikasisSkaicius(Long.parseLong(skaicius));
			} else if (m.matches()) {
				long sk = Long.parseLong(m.group(1));
				long v = Long.parseLong(m.group(2));
				return new Trupmena(sk, v);				
			} else {
				throw new IllegalArgumentException(skaicius + " is not a supported number");
			}
		}
	}

}
