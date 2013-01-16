package eu.vytenis.skaitvardziai.xpath;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Ivardziuotinis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

public class SkaitvardziaiTextParser {
	private static final Map<String, Object> symbols;
	static {
		Map<String, Object> t = new HashMap<String, Object>();
		addAll(t, Linksnis.values());
		addAll(t, Skaicius.values());
		addAll(t, Gimine.values());
		addAll(t, Poskyris.values());
		addAll(t, Ivardziuotinis.values());
		symbols = Collections.unmodifiableMap(t);
	}
	private static final SkaitvardziaiTextParser instance = new SkaitvardziaiTextParser();
	public static SkaitvardziaiTextParser get() {
		return instance;		
	}
	
	private static void addAll(Map<String, Object> symbols, Enum<? extends Aliased>[] values) {
		for (Enum<? extends Aliased> e : values) {
			if (!e.getClass().getSuperclass().equals(Enum.class)) {
				throw new IllegalStateException("Not implemented");
				// Nerealizuota logika parse() metode usedClasses kintamajam
			}
			Aliased aliased = (Aliased) e;
			String alias = aliased.alias();
			String[] aliases = {alias,
					!alias.equals(alias.toUpperCase()) ? alias.toUpperCase() : null,
					!alias.equals(alias.toLowerCase()) ? alias.toLowerCase() : null};
			for (String a : aliases) {
				if (a == null) {
					continue;
				}
				if (symbols.containsKey(a)) {
					throw new IllegalArgumentException(a + ": duplicate value. " + e.getClass().getSimpleName() + " and " + symbols.get(a).getClass().getSimpleName());					
				}
				symbols.put(a, e);
			}
		}
		
	}
	public Forma parseForma(String parametrai, List<Class<?>> supportedParams) {
		if (supportedParams == null) {
			supportedParams = Arrays.<Class<?>>asList(Linksnis.class, Skaicius.class, Gimine.class, Poskyris.class, Ivardziuotinis.class);
		}
		String[] params = parametrai.length() == 0 ? new String[]{} : parametrai.split("\\s*,\\s*");
		Forma r = new Forma();
		Map<Class<?>, Object> usedClasses = new HashMap<Class<?>, Object>();
		for (String param : params) {
			Object o = symbols.get(param);
			if (o != null && !supportedParams.contains(o.getClass())) {
				throw new SkaitvardziaiRuntimeException(param + " is not supported, because parameter class " + o.getClass() + " is not supported"); 
			}
			if (o == null) {
				throw new SkaitvardziaiRuntimeException(param + " is not supported");
			} else if (o instanceof Linksnis) {
				r.setLinksnis((Linksnis) o);
			} else if (o instanceof Skaicius) {
				r.setSkaicius((Skaicius) o);
			} else if (o instanceof Gimine) {
				r.setGimine((Gimine) o);
			} else if (o instanceof Poskyris) {
				r.setPoskyris((Poskyris) o);
			} else if (o instanceof Ivardziuotinis) {
				r.setIvardziuotine(true);
			}
			if (usedClasses.containsKey(o.getClass())) {
				Object i = usedClasses.get(o.getClass());
				throw new SkaitvardziaiRuntimeException(o.getClass() + " instance is used more than one time. Duplicate values: "
						+ o.getClass() + ": " + i + "; " + o.getClass() + ": " + o);
			}
			usedClasses.put(o.getClass(), o);
			
		}
		return r;
	}
	
	public SkaitineReiksme parseSkaicius(String skaicius) {
		Pattern tr = Pattern.compile("(\\d+)\\s*/\\s*(\\d+)");
		Matcher m = tr.matcher(skaicius);
		if (skaicius.matches("\\d+")) {
			return new SveikasisSkaicius(skaicius);
		} else if (m.matches()) {
			String sk = m.group(1);
			String v = m.group(2);
			return new Trupmena(sk, v);				
		} else {
			throw new IllegalArgumentException(skaicius + " is not a supported number");
		}
	}
}