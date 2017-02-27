package eu.vytenis.skaitvardziai.util;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

class SkaitvardziaiTextParserConstants {
	public static List<Class<? extends FormosElementas>> ALL_ELEMENTS = createAllElements();
	public static final Map<String, Aliased> SYMBOLS = createSymbols();
	public static final Pattern SVEIKASIS;
	public static final Pattern TRUPMENA;

	static {
		String sv = "( [-]? \\d+) ";
		String tr = sv + "/" + sv;
		SVEIKASIS = Pattern.compile(sv.replaceAll(" ", "\\\\s*"));
		TRUPMENA = Pattern.compile(tr.replaceAll(" ", "\\\\s*"));
	}

	private static List<Class<? extends FormosElementas>> createAllElements() {
		List<Class<? extends FormosElementas>> all = new ArrayList<Class<? extends FormosElementas>>();
		all.add(Linksnis.class);
		all.add(Skaicius.class);
		all.add(Gimine.class);
		all.add(Poskyris.class);
		all.add(Rusis.class);
		return unmodifiableList(all);
	}

	private static Map<String, Aliased> createSymbols() {
		Map<String, Aliased> symbols = new HashMap<String, Aliased>();
		addSymbolsToMap(symbols, Linksnis.values());
		addSymbolsToMap(symbols, Skaicius.values());
		addSymbolsToMap(symbols, Gimine.values());
		addSymbolsToMap(symbols, Poskyris.values());
		addSymbolsToMap(symbols, Rusis.values());
		return unmodifiableMap(symbols);
	}

	private static void addSymbolsToMap(Map<String, Aliased> symbols, Aliased[] values) {
		for (Aliased aliased : values)
			for (String alias : createAliases(new String[] {aliased.alias(), aliased.longName()}))
				addAliasToMap(aliased, alias, symbols);
	}

	private static void addAliasToMap(Aliased aliased, String alias, Map<String, Aliased> symbols) {
		if (symbols.containsKey(alias)) {
			String message = alias + ": duplicate value. " + aliased.getClass().getSimpleName() + " and " + symbols.get(alias).getClass().getSimpleName();
			throw new IllegalArgumentException(message);
		}
		symbols.put(alias, aliased);
	}

	private static Set<String> createAliases(String[] names) {
		Set<String> aliases = new TreeSet<String>();
		for (String name : names)
			addNameToAliases(aliases, name);
		return aliases;
	}

	private static void addNameToAliases(Set<String> aliases, String name) {
		aliases.add(name);
		aliases.add(name.toUpperCase(Letters.LT));
		aliases.add(name.toLowerCase(Letters.LT));
		aliases.add(Letters.translateLithuanianToLatin(name));
		aliases.add(Letters.translateLithuanianToLatin(name.toLowerCase(Letters.LT)));
		aliases.add(Letters.translateLithuanianToLatin(name.toUpperCase(Letters.LT)));
	}
}
