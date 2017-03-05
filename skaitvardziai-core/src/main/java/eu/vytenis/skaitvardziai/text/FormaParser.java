package eu.vytenis.skaitvardziai.text;

import static eu.vytenis.skaitvardziai.text.FormaParserConstants.ALL_ELEMENTS;
import static eu.vytenis.skaitvardziai.text.FormaParserConstants.SYMBOLS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;

public class FormaParser {
	public Forma parse(String parametrai) {
		return parse(parametrai, ALL_ELEMENTS);
	}

	public Forma parse(String parametrai, List<Class<? extends FormosElementas>> supportedParams) {
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
}
