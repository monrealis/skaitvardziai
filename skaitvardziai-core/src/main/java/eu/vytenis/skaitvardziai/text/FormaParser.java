package eu.vytenis.skaitvardziai.text;

import static eu.vytenis.skaitvardziai.text.FormaParserConstants.ALL_ELEMENTS;
import static eu.vytenis.skaitvardziai.text.FormaParserConstants.SVEIKASIS;
import static eu.vytenis.skaitvardziai.text.FormaParserConstants.SYMBOLS;
import static eu.vytenis.skaitvardziai.text.FormaParserConstants.TRUPMENA;

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

public class FormaParser {
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
			forma = getUpdatedCopy(parameter, forma, usedClasses, supportedParameters);
		return forma;
	}

	private Forma getUpdatedCopy(String parameter, Forma forma, Map<Class<?>, FormosElementas> usedClasses,
			List<Class<? extends FormosElementas>> supportedParameters) {
		FormosElementas element = SYMBOLS.get(parameter);
		checkSupported(parameter, element, supportedParameters);
		addToUsedClassesIfNotUsed(usedClasses, element);
		forma = FieldHandlers.getUpdatedCopy(forma, element);
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
		Parser[] parsers = {new SveikasisParser(), new TrupmenaParser()};
		for (Parser parser : parsers)
			try {
				return parser.parse(skaicius);
			} catch (RegexpDoesNotMatchException e) {
				continue;
			}
		throw new InvalidNumberException(skaicius);
	}

	private static abstract class Parser {
		protected void checkMatches(Matcher matcher) throws RegexpDoesNotMatchException {
			if (!matcher.matches())
				throw new RegexpDoesNotMatchException();
		}

		public abstract SkaitineReiksme parse(String skaicius) throws RegexpDoesNotMatchException;
	}

	private static class SveikasisParser extends Parser {
		@Override
		public SveikasisSkaicius parse(String sveikasisSkaicius) throws RegexpDoesNotMatchException {
			Matcher matcher = SVEIKASIS.matcher(sveikasisSkaicius);
			checkMatches(matcher);
			String sk = matcher.group(1).replaceAll("\\s*", "");
			return new SveikasisSkaicius(sk);
		}
	}

	private static class TrupmenaParser extends Parser {
		@Override
		public Trupmena parse(String trupmena) throws RegexpDoesNotMatchException {
			Matcher matcher = TRUPMENA.matcher(trupmena);
			checkMatches(matcher);
			String sk = matcher.group(1).replaceAll("\\s*", "");
			String v = matcher.group(2).replaceAll("\\s*", "");
			return new Trupmena(sk, v);
		}
	}

	private static class RegexpDoesNotMatchException extends Exception {
		private static final long serialVersionUID = 1;
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
