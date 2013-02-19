package eu.vytenis.skaitvardziai.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;

public class BuilderCheckUtil {

	public static void checkSveikojoSkaiciausPoskyris(Poskyris poskyris) {
		if (!SveikasisBuilder.SVEIKUJU_SKAICIU_SKAITV_POSKYRIAI.contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + " is invalid: " + SveikasisBuilder.SVEIKUJU_SKAICIU_SKAITV_POSKYRIAI + " expected");
		}	
	}

	public static void checkSveikojoSkaiciausPoskyrisNekuopinis(Poskyris poskyris) {
		if (!SveikasisBuilder.SVEIKUJU_SKAICIU_NEKUOPINIU_SKAITV_POSKYRIAI.contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + " is invalid: " + SveikasisBuilder.SVEIKUJU_SKAICIU_NEKUOPINIU_SKAITV_POSKYRIAI + " expected");
		}	
	}

	public static void checkPowerOfThousand(BigInteger tukstancioLaipsnis) {
		BigInteger thousand = SveikasisSkaicius.THOUSAND;
		List<BigInteger> expected = new ArrayList<BigInteger>();
		for (int i = 1; i <= 3; ++i) {
			expected.add(thousand.pow(i));
		}
		if (!expected.contains(tukstancioLaipsnis)) {
			throw new IllegalArgumentException();
		}
	}

}
