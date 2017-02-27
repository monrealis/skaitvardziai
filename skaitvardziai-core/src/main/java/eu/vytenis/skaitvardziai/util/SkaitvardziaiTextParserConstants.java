package eu.vytenis.skaitvardziai.util;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

class SkaitvardziaiTextParserConstants {
	static List<Class<? extends FormosElementas>> ALL_ELEMENTS = createAllElements();
	static final Map<String, Object> SYMBOLS = createSymbols();

	private static List<Class<? extends FormosElementas>> createAllElements() {
		List<Class<? extends FormosElementas>> all = new ArrayList<Class<? extends FormosElementas>>();
		all.add(Linksnis.class);
		all.add(Skaicius.class);
		all.add(Gimine.class);
		all.add(Poskyris.class);
		all.add(Rusis.class);
		return unmodifiableList(all);
	}

	private static Map<String, Object> createSymbols() {
		Map<String, Object> symbols = new HashMap<String, Object>();
		addSymbolsToMap(symbols, Linksnis.values());
		addSymbolsToMap(symbols, Skaicius.values());
		addSymbolsToMap(symbols, Gimine.values());
		addSymbolsToMap(symbols, Poskyris.values());
		addSymbolsToMap(symbols, Rusis.values());
		return unmodifiableMap(symbols);
	}

	private static void addSymbolsToMap(Map<String, Object> symbols, Aliased[] values) {
		for (Aliased aliased : values) {
			String[] names = {aliased.alias(), aliased.longName()};
			Set<String> aliases = new TreeSet<String>();
			for (String name : names) {
				aliases.add(name);
				aliases.add(name.toUpperCase(Letters.LT));
				aliases.add(name.toLowerCase(Letters.LT));
				aliases.add(Letters.translateLithuanianToLatin(name));
				aliases.add(Letters.translateLithuanianToLatin(name.toLowerCase(Letters.LT)));
				aliases.add(Letters.translateLithuanianToLatin(name.toUpperCase(Letters.LT)));
			}
			for (String a : aliases) {
				if (symbols.containsKey(a)) {
					throw new IllegalArgumentException(
							a + ": duplicate value. " + aliased.getClass().getSimpleName() + " and " + symbols.get(a).getClass().getSimpleName());
				}
				symbols.put(a, aliased);
			}
		}
	}
}
