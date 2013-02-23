package eu.vytenis.skaitvardziai.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;

public class BuilderCheckUtil {

	public static void checkPoskyris(String objectName, Poskyris poskyris, List<Poskyris> validPoskyriai) {
		if (!SveikasisBuilder.SVEIKUJU_SKAICIU_SKAITV_POSKYRIAI.contains(poskyris)) {
			throw new InvalidPoskyrisException(objectName, poskyris, validPoskyriai);
		}	
	}


	public static void checkPowerOfThousand(String objectName, BigInteger powerOfThousand) {
		BigInteger thousand = SveikasisSkaicius.THOUSAND;
		List<BigInteger> expected = new ArrayList<BigInteger>();
		for (int i = 1; i <= 3; ++i) {
			expected.add(thousand.pow(i));
		}
		if (!expected.contains(powerOfThousand)) {
			throw new InvalidPowerOfThousandException(objectName, powerOfThousand);
		}
	}
	
	public static class InvalidPoskyrisException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = -197717691898671729L;
		
		private String objectName;
		private Poskyris poskyris;
		private List<Poskyris> validPoskyriai;
		
		public InvalidPoskyrisException(String objectName, Poskyris poskyris, List<Poskyris> validPoskyriai) {
			super(objectName + " has invalid value " + poskyris + ". Expected one of " + validPoskyriai);
		}
		
		public String getObjectName() {
			return objectName;
		}
		
		public Poskyris getPoskyris() {
			return poskyris;
		}
		
		public List<Poskyris> getValidPoskyriai() {
			return validPoskyriai;
		}
		
	}
	
	public static class InvalidPowerOfThousandException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = -4914559686070433295L;

		private String objectName;
		private BigInteger invalidPowerOfThousand;
		
		public InvalidPowerOfThousandException(String objectName, BigInteger invalidPowerOfThousand) {
			super(objectName + " value " + invalidPowerOfThousand + " is not a power of thousand");
			this.objectName = objectName;
			this.invalidPowerOfThousand = invalidPowerOfThousand;
		}
		
		public String getObjectName() {
			return objectName;
		}

		public BigInteger getInvalidPowerOfThousand() {
			return invalidPowerOfThousand;
		}
		
	}

}
