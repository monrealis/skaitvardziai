package eu.vytenis.skaitvardziai.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.util.Numbers;

public class BuilderChecks {
	public static void checkPoskyris(String objectName, Poskyris poskyris, List<Poskyris> validPoskyriai) {
		if (validPoskyriai.contains(poskyris))
			return;
		throw new InvalidPoskyrisException(objectName, poskyris, validPoskyriai);
	}

	public static void checkPowerOfThousand(String objectName, BigInteger powerOfThousand) {
		BigInteger thousand = Numbers.THOUSAND;
		List<BigInteger> expected = new ArrayList<BigInteger>();
		for (int i = 1; i <= 3; ++i)
			expected.add(thousand.pow(i));
		if (!expected.contains(powerOfThousand))
			throw new InvalidPowerOfThousandException(objectName, powerOfThousand);
	}

	public static class InvalidPoskyrisException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;

		public InvalidPoskyrisException(String objectName, Poskyris poskyris, List<Poskyris> validPoskyriai) {
			super(objectName + " has invalid value " + poskyris + ". Expected one of " + validPoskyriai);
		}
	}

	public static class InvalidPowerOfThousandException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1L;

		public InvalidPowerOfThousandException(String objectName, BigInteger invalidPowerOfThousand) {
			super(objectName + " value " + invalidPowerOfThousand + " is not a power of thousand");
		}
	}
}
