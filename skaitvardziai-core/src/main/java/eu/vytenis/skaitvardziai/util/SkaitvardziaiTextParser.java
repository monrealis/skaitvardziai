package eu.vytenis.skaitvardziai.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Aliased;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementasFieldHandler;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class SkaitvardziaiTextParser {
	@SuppressWarnings("unchecked")
	private static List<Class<? extends FormosElementas>> ALL_PARAMETERS = Arrays.<Class<? extends FormosElementas>> asList(Linksnis.class, Skaicius.class,
			Gimine.class, Poskyris.class, Rusis.class);
	private static final Map<String, Object> symbols;

	static {
		Map<String, Object> t = new HashMap<String, Object>();
		addAll(t, Linksnis.values());
		addAll(t, Skaicius.values());
		addAll(t, Gimine.values());
		addAll(t, Poskyris.values());
		addAll(t, Rusis.values());
		symbols = Collections.unmodifiableMap(t);
	}

	private static void addAll(Map<String, Object> symbols, Enum<? extends Aliased>[] values) {
		for (Enum<?> e : values) {
			if (!e.getClass().getSuperclass().equals(Enum.class)) {
				throw new IllegalStateException("Not implemented");
				// Nerealizuota logika parse() metode usedClasses kintamajam
			}
			Aliased aliased = (Aliased) e;
			String[] names = {aliased.alias(), aliased.longName()};
			Set<String> aliases = new TreeSet<String>();
			for (String name : names) {
				aliases.add(name);
				aliases.add(name.toUpperCase(Letters.LT));
				aliases.add(name.toLowerCase(Letters.LT));
				aliases.add(Letters.translateLtToLatin(name));
				aliases.add(Letters.translateLtToLatin(name.toLowerCase(Letters.LT)));
				aliases.add(Letters.translateLtToLatin(name.toUpperCase(Letters.LT)));
			}
			for (String a : aliases) {
				if (symbols.containsKey(a)) {
					throw new IllegalArgumentException(
							a + ": duplicate value. " + e.getClass().getSimpleName() + " and " + symbols.get(a).getClass().getSimpleName());
				}
				symbols.put(a, e);
			}
		}

	}

	public Forma parseForma(String parametrai) {
		return parseForma(parametrai, ALL_PARAMETERS);
	}

	public Forma parseForma(String parametrai, List<Class<? extends FormosElementas>> supportedParams) {
		String[] params = parametrai.length() == 0 ? new String[] {} : parametrai.split("\\s*,\\s*");
		return parseForma(params, supportedParams);
	}

	private Forma parseForma(String[] parameters, List<Class<? extends FormosElementas>> supportedParams) {
		Forma forma = new Forma();
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
			forma = FormosElementasFieldHandler.setElementas(forma, fe);
			if (usedClasses.containsKey(fe.getClass())) {
				FormosElementas oldFe = usedClasses.get(fe.getClass());
				throw new DuplicatePartException(fe, oldFe);
			}
			usedClasses.put(fe.getClass(), fe);
		}
		return forma;
	}

	public static class UnsupportedPartException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 7783672469749826083L;
		private String parsedText;
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
			return newElement.getClass() + " instance is used more than one time. Duplicate values: " + newElement.getClass() + ": " + existingElement + "; "
					+ newElement.getClass() + ": " + newElement;
		}

	}

	private static final Pattern SVEIKASIS;
	private static final Pattern TRUPMENA;

	static {
		String sv = "( [-]? \\d+) ";
		String tr = sv + "/" + sv;
		sv = sv.replaceAll(" ", "\\\\s*");
		tr = tr.replaceAll(" ", "\\\\s*");
		SVEIKASIS = Pattern.compile(sv);
		TRUPMENA = Pattern.compile(tr);
	}

	public SkaitineReiksme parseSkaicius(String skaicius) {
		SkaitineReiksme r = parseSveikasisIfMatches(skaicius);
		if (r == null) {
			r = parseTrupmenaIfMatches(skaicius);
		}
		if (r == null) {
			throw new InvalidNumberException(skaicius);
		}
		return r;
	}

	private SveikasisSkaicius parseSveikasisIfMatches(String sveikasisSkaicius) {
		Matcher svm = SVEIKASIS.matcher(sveikasisSkaicius);
		if (!svm.matches()) {
			return null;
		}
		String sk = svm.group(1).replaceAll("\\s*", "");
		return new SveikasisSkaicius(sk);

	}

	private Trupmena parseTrupmenaIfMatches(String trupmena) {
		Matcher trm = TRUPMENA.matcher(trupmena);
		if (!trm.matches())
			return null;
		String sk = trm.group(1).replaceAll("\\s*", "");
		String v = trm.group(2).replaceAll("\\s*", "");
		return new Trupmena(sk, v);
	}

	public class InvalidNumberException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = -1195581620844776553L;

		public InvalidNumberException(String invalidNumber) {
			super(invalidNumber + " is not a supported number");
		}

	}

}
