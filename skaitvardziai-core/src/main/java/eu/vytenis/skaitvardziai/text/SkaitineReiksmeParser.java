package eu.vytenis.skaitvardziai.text;

import static eu.vytenis.skaitvardziai.text.FormaParserConstants.SVEIKASIS;
import static eu.vytenis.skaitvardziai.text.FormaParserConstants.TRUPMENA;

import java.util.regex.Matcher;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;

public class SkaitineReiksmeParser {
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

	public class InvalidNumberException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public InvalidNumberException(String invalidNumber) {
			super(invalidNumber + " is not a supported number");
		}
	}
}
