package eu.vytenis.skaitvardziai.util;

import static eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParserConstants.ALL_ELEMENTS;
import static eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParserConstants.SVEIKASIS;
import static eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParserConstants.SYMBOLS;
import static eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParserConstants.TRUPMENA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class SkaitvardziaiTextParser {
	public Forma parseForma(String parametrai) {
		return parseForma(parametrai, ALL_ELEMENTS);
	}

	public Forma parseForma(String parametrai, List<Class<? extends FormosElementas>> supportedParams) {
		return parseForma(split(parametrai), supportedParams);
	}

	private String[] split(String text) {
		return text.length() == 0 ? new String[] {} : text.split("\\s*,\\s*");
	}

	private Forma parseForma(String[] parameters, List<Class<? extends FormosElementas>> supportedParameters) {
		Forma forma = new Forma();
		Map<Class<?>, FormosElementas> usedClasses = new HashMap<Class<?>, FormosElementas>();
		for (String parameter : parameters)
			forma = getWithUpdatedParameter(parameter, forma, usedClasses, supportedParameters);
		return forma;
	}

	private Forma getWithUpdatedParameter(String parameter, Forma forma, Map<Class<?>, FormosElementas> usedClasses,
			List<Class<? extends FormosElementas>> supportedParameters) {
		FormosElementas element = SYMBOLS.get(parameter);
		checkSupported(parameter, element, supportedParameters);
		addToUsedClassesIfNotUsed(usedClasses, element);
		forma = FormosElementasFieldHandler.withUpdatedElement(forma, element);
		return forma;
	}

	private void checkSupported(String parameter, FormosElementas element, List<Class<? extends FormosElementas>> supportedParameters) {
		if (element == null)
			throw new UnsupportedPartException(parameter);
		if (!supportedParameters.contains(element.getClass()))
			throw new UnsupportedPartException(parameter, element);
	}

	private void addToUsedClassesIfNotUsed(Map<Class<?>, FormosElementas> usedClasses, FormosElementas element) {
		if (usedClasses.containsKey(element.getClass())) {
			FormosElementas oldElement = usedClasses.get(element.getClass());
			throw new DuplicatePartException(element, oldElement);
		}
		usedClasses.put(element.getClass(), element);
	}

	// Use exceptions
	public SkaitineReiksme parseSkaicius(String skaicius) {
		SveikasisSkaicius sveikasis = parseSveikasisIfMatches(skaicius);
		if (sveikasis != null)
			return sveikasis;
		Trupmena trupmena = parseTrupmenaIfMatches(skaicius);
		if (trupmena != null)
			return trupmena;
		throw new InvalidNumberException(skaicius);
	}

	private SveikasisSkaicius parseSveikasisIfMatches(String sveikasisSkaicius) {
		Matcher svm = SVEIKASIS.matcher(sveikasisSkaicius);
		if (!svm.matches())
			return null;
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

	public static class UnsupportedPartException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public UnsupportedPartException(String parsedText) {
			this(parsedText, null);
		}

		public UnsupportedPartException(String parsedText, Object resultObject) {
			super(buildMessage(parsedText, resultObject));
		}

		private static String buildMessage(String parsedText, Object resultObject) {
			if (resultObject == null)
				return parsedText + " is not supported, because parameter class " + parsedText.getClass() + " is not supported";
			else
				return parsedText + " is not supported";
		}
	}

	public static class DuplicatePartException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public DuplicatePartException(FormosElementas newElement, FormosElementas existingElement) {
			super(buildMessage(newElement, existingElement));
		}

		private static String buildMessage(FormosElementas newElement, Object existingElement) {
			return newElement.getClass() + " instance is used more than one time. Duplicate values: " + newElement.getClass() + ": " + existingElement + "; "
					+ newElement.getClass() + ": " + newElement;
		}
	}

	public class InvalidNumberException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public InvalidNumberException(String invalidNumber) {
			super(invalidNumber + " is not a supported number");
		}
	}
}
