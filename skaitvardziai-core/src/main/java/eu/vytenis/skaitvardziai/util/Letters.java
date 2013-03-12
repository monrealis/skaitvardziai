package eu.vytenis.skaitvardziai.util;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class Letters {
	
	public static final Locale LT = new Locale("lt");
	private static final Map<Character, Character> latinByLt;
	static {
		Map<Character, Character> m = new TreeMap<Character, Character>();
		char[] lt = "ąčęėįšųūžĄČĘĖĮŠŲŪŽ".toCharArray();
		char[] en = "aceeisuuzACEEISUUZ".toCharArray();
		for (int i = 0; i < lt.length; ++i) {
			m.put(lt[i], en[i]);
		}
		latinByLt = Collections.unmodifiableMap(m);
	}
	
	public static String translateLtToLatin(String text) {
		if (text == null) {
			return null;
		}
		char[] chars = text.toCharArray();
		for (int i = 0; i < chars.length; ++i) {
			Character c = latinByLt.get(chars[i]);
			if (c != null) {
				chars[i] = c;
			}
			
		}
		return new String(chars);		
	}

}
