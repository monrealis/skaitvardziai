package eu.vytenis.skaitvardziai.util;

import static java.util.Collections.unmodifiableMap;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Letters {
	public static final Locale LT = new Locale("lt");
	private static final Map<Character, Character> latinByLt = unmodifiableMap(createLatinByLt());

	private static Map<Character, Character> createLatinByLt() {
		char[] lt = "ąčęėįšųūžĄČĘĖĮŠŲŪŽ".toCharArray();
		char[] en = "aceeisuuzACEEISUUZ".toCharArray();
		Map<Character, Character> latinByLtMap = new TreeMap<Character, Character>();
		for (int i = 0; i < lt.length; ++i)
			latinByLtMap.put(lt[i], en[i]);
		return latinByLtMap;
	}

	public static String translateLithuanianToLatin(String text) {
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; ++i)
			if (latinByLt.get(chars[i]) != null)
				chars[i] = latinByLt.get(chars[i]);
		return new String(chars);
	}
}
