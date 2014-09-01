package eu.vytenis.skaitvardziai.app.template;

public class Patterns {
	private static final String START_QUOTE = "\\Q";
	private static final String END_QUOTE = "\\E";
	private static final String ESCAPED_END_QUOTE = "\\\\E";

	// GCJ nėra metodo Pattern.quote(), realizuojame patys
	public static String quote(String regexp) {
		if (!regexp.contains(END_QUOTE)) {
			return wrapWithQuotes(regexp);
		} else {
			return quoteEscapingEndQuotes(regexp);
		}
	}

	private static String wrapWithQuotes(String regexp) {
		return START_QUOTE + regexp + END_QUOTE;
	}

	private static String quoteEscapingEndQuotes(String regexp) {
		String r = START_QUOTE;
		int nextEndQuoteIndex;
		int currentEndQuoteIndex = 0;
		while ((nextEndQuoteIndex = regexp.indexOf(END_QUOTE, currentEndQuoteIndex)) >= 0) {
			r += regexp.substring(currentEndQuoteIndex, nextEndQuoteIndex);
			currentEndQuoteIndex = nextEndQuoteIndex + END_QUOTE.length();
			r += END_QUOTE + ESCAPED_END_QUOTE + START_QUOTE;
		}
		r += regexp.substring(currentEndQuoteIndex);
		r += END_QUOTE;
		return r;
	}

}
