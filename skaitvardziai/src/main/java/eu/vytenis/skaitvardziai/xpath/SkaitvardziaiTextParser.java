package eu.vytenis.skaitvardziai.xpath;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Ivardziuotinis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.util.LetterUtils;

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
			
			String[] names = {aliased.alias(), aliased.longName()};
			Set<String> aliases = new TreeSet<String>();
			for (String name : names) {				
				aliases.add(name);
				aliases.add(name.toUpperCase(LetterUtils.LT));
				aliases.add(name.toLowerCase(LetterUtils.LT));
				aliases.add(LetterUtils.translateLtToLatin(name));
				aliases.add(LetterUtils.translateLtToLatin(name.toLowerCase(LetterUtils.LT)));
				aliases.add(LetterUtils.translateLtToLatin(name.toUpperCase(LetterUtils.LT)));
			}
			
			for (String a : aliases) {
				if (symbols.containsKey(a)) {
					throw new IllegalArgumentException(a + ": duplicate value. " + e.getClass().getSimpleName() + " and " + symbols.get(a).getClass().getSimpleName());					
				}
				symbols.put(a, e);
			}
		}
		
	}
	public Forma parseForma(String parametrai, List<Class<?  extends FormosElementas>> supportedParams) {
		String[] params = parametrai.length() == 0 ? new String[]{} : parametrai.split("\\s*,\\s*");
		return parseForma(params, supportedParams);
	}
	
	@SuppressWarnings("unchecked")
	public Forma parseForma(String[] parameters, List<Class<? extends FormosElementas>> supportedParams) {
		if (supportedParams == null) {
			supportedParams = Arrays.<Class<? extends FormosElementas>>asList(Linksnis.class, Skaicius.class, Gimine.class, Poskyris.class, Ivardziuotinis.class);
		}
		Forma r = new Forma();
		Map<Class<?>, FormosElementas> usedClasses = new HashMap<Class<?>, FormosElementas>();
		for (String param : parameters) {
			Object o = symbols.get(param);
			if (o != null && !supportedParams.contains(o.getClass())) {
				throw new UnsupportedPartException(param, o); 
			}
			if (o == null) {
				throw new UnsupportedPartException(param, null);
			}
			
			FormosElementas fe = (FormosElementas) o;
			r.setElementas(fe);
			
			if (usedClasses.containsKey(fe.getClass())) {
				FormosElementas oldFe = usedClasses.get(fe.getClass());
				throw new DuplicatePartException(fe, oldFe);
			}
			usedClasses.put(fe.getClass(), fe);
			
		}
		return r;
	}
	
	public static class UnsupportedPartException extends SkaitvardziaiRuntimeException {
		/** Klasės versija. */
		private static final long serialVersionUID = 7783672469749826083L;
		
		/** Tekstas, kurį parsinant iškrito klaida. */
		private String parsedText;
		/** Po parsinimo gautas objektas (gali būti null). */
		private Object resultObject;		
		
		public UnsupportedPartException(String parsedText, Object resultObject) {
			super(buildMessage(parsedText, resultObject));
			this.parsedText = parsedText;
			this.resultObject = parsedText;
		}
		
		public String getParsedText() {
			return parsedText;
		}
		
		public Object getResultObject() {
			return resultObject;
		}
		
		private static String buildMessage(String parsedText, Object resultObject) {
			if (resultObject == null) {
				return parsedText + " is not supported, because parameter class " + parsedText.getClass() + " is not supported";
			} else {
				return parsedText + " is not supported";		
			}
		}
	}
	
	public static class DuplicatePartException extends SkaitvardziaiRuntimeException {

		/** Klasės versija. */
		private static final long serialVersionUID = 8618555191243325555L;
		
		private FormosElementas newElement;
		private FormosElementas existingElement;		
		
		public DuplicatePartException(FormosElementas newElement, FormosElementas existingElement) {
			super(buildMessage(newElement, existingElement));
			this.newElement = newElement;
			this.existingElement = existingElement;
		}
		
		public FormosElementas getNewElement() {
			return newElement;
		}
		
		public FormosElementas getExistingElement() {
			return existingElement;
		}
		
		private static String buildMessage(FormosElementas newElement, Object existingElement) {
			return newElement.getClass() + " instance is used more than one time. Duplicate values: "
					+ newElement.getClass() + ": " + existingElement + "; " + newElement.getClass() + ": " + newElement;
		}
		
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