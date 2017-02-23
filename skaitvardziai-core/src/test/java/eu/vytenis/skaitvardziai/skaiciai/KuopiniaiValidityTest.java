package eu.vytenis.skaitvardziai.skaiciai;

import static java.lang.Math.abs;
import static org.junit.Assert.fail;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.zodziai.Zodis;

public class KuopiniaiValidityTest {
	@Test
	public void onlyPositiveOrNegativeOneToNineSupported() {
		for (long l = -1000L; l <= 1000; ++l)
			expectNotToThrowExceptionIfValid(new SveikasisSkaicius(l));
	}

	private void expectNotToThrowExceptionIfValid(SveikasisSkaicius s) {
		if (isValidKuopinis(s.getReiksme().longValue()))
			expectNotToThrowException(s);
		else
			expectToThrowException(s);
	}

	private boolean isValidKuopinis(long reiksme) {
		return abs(reiksme) >= 1 && abs(reiksme) <= 9;
	}

	private void expectNotToThrowException(SveikasisSkaicius s) {
		for (Linksnis linksnis : Linksnis.values())
			s.toString(Poskyris.Kuopinis, linksnis, Gimine.V);
	}

	private void expectToThrowException(SveikasisSkaicius s) {
		try {
			String text = s.toString(Poskyris.Kuopinis, Linksnis.V, Gimine.V);
			fail(text);
		} catch (Zodis.WordNotFoundException e) {
			// OK
		}
	}
}
