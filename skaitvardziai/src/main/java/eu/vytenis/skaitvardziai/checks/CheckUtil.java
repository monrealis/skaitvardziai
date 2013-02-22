package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;

import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;

/**
 * Operacijoms reikšmėms validuoti.
 *
 */
public class CheckUtil {

	/**
	 * Patikrina, ar objektas nėra null. Jei null, meta {@link IllegalArgumentException}.
	 * @param name lauko pavadinimas (naudojamas klaidos pranešime)
	 * @param object objektas, tikrinamas ar ne null
	 */
	public static void checkNotNull(String name, Object object) {
		if (object == null) {
			throw new ObjectNullException(name);
		}
		
	}
	/**
	 * Patikrina, ar skaičius priklauso intervalui [min, max].
	 * @param name kintamojo pavadinimas (matomas klaidos pranešime)
	 * @param value reikšmė (neprivalomas)
	 * @param min minimali reikšmė (neprivalomas)
	 * @param max maksimali reikšmė (neprivalomas)
	 */
	public static void checkInclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}
		Range range = new Range(min, true, max, true);
		InvalidRangeException exc = new InvalidRangeException(name, value, range);		
		// TODO iškelti tikrinimus į Range
		if (min != null && max != null) {
			if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
				throw exc;
			}
		} else if (min != null) {
			if (value.compareTo(min) < 0) {
				throw exc;
			}
		} else if (max != null) {
			if (value.compareTo(max) > 0) {
				throw exc;
			}
		}
	}
	
	/**
	 * Patikrina, ar skaičius priklauso intervalui (min, max).
	 * @param name kintamojo pavadinimas (matomas klaidos pranešime)
	 * @param value reikšmė (neprivalomas)
	 * @param min minimali reikšmė (neprivalomas)
	 * @param max maksimali reikšmė (neprivalomas)
	 */
	public static void checkExclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}
		InvalidRangeException exc = new InvalidRangeException(name, value, new Range(min, false, max, false));
		if (min != null && max != null) {
			if (value.compareTo(min) <= 0 || value.compareTo(max) >= 0) {
				throw exc;
			}
		} else if (min != null) {
			if (value.compareTo(min) <= 0) {
				throw exc;
			}
		} else if (max != null) {
			if (value.compareTo(max) >= 0) {
				throw exc;
			}
		}
	}
	
	/**
	 * Patikrina, ar skaičius priklauso intervalui [min, max).
	 * @param name kintamojo pavadinimas (matomas klaidos pranešime)
	 * @param value reikšmė (neprivalomas)
	 * @param min minimali reikšmė (neprivalomas)
	 * @param max maksimali reikšmė (neprivalomas)
	 */
	public static void checkMinInclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}
		Range range = new Range(min, true, max, false);
		InvalidRangeException exc = new InvalidRangeException(name, value, range);
		if (min != null && max != null) {
			if (value.compareTo(min) < 0 || value.compareTo(max) >= 0) {
				throw exc;
			}
		} else if (min != null) {
			if (value.compareTo(min) < 0) {
				throw exc;
			}
		} else if (max != null) {
			if (value.compareTo(max) >= 0) {
				throw exc;
			}
		}
	}
	
	/**
	 * Patikrina, ar objektą galima koreguoti.
	 * @param name objekto pavadinimas (klaidos pranešimui)
	 * @param unmodifiable objetas
	 */
	public static void checkCanModify(String name, UnmodifiableCapable unmodifiable) {
		if (unmodifiable == null) {
			return;
		}
		if (unmodifiable.isUnmodifiable()) {
			throw new IllegalStateException(name + " is read-only. Cannot modify read-only object");
		}
	}
	
	/**
	 * Peržiūri perduotus objektus ir, jei objektai yra modifikuojami, padaro juos nemodifikuojamais.
	 * @param unmodifiables objektų sąrašas
	 */
	public static void ensureUnmodifiable(Iterable<? extends UnmodifiableCapable> unmodifiables) {
		for (UnmodifiableCapable ic : unmodifiables) {
			if (!ic.isUnmodifiable()) {
				ic.setUnmodifiable(true);
			}
		}
	}
	
	
	public static class ObjectNullException extends SkaitvardziaiRuntimeException {

		/** Klasės versija. */
		private static final long serialVersionUID = 363562983326179288L;
		
		public ObjectNullException(String objectName) {
			super(objectName + " cannot be null");			
		}
		
	}
	
	public static class InvalidRangeException extends SkaitvardziaiRuntimeException {

		/** Klasės versija. */
		private static final long serialVersionUID = -8245329095498329074L;
		
		private String objectName;
		private Number value;
		private Range range;

		public InvalidRangeException(String objectName, Number value, Range range) {
			super(buildMessage(objectName, value, range));
			
			this.objectName = objectName;
			this.value = value;
			this.range = range;
		}

		public String getObjectName() {
			return objectName;
		}
		
		public Number getValue() {
			return value;
		}
		
		public Range getRange() {
			return range;
		}
		public static String buildMessage(String objectName, Number value, Range range) {			
			String text = objectName + " = " + value + " must be in range " + range;
			return text;
		}
	}
	
	public static class NotModifiableException extends SkaitvardziaiRuntimeException {

		/** Klasės versija. */
		private static final long serialVersionUID = 3258850566232546106L;
		
		private String objectName;
		
		public NotModifiableException(String objectName) {
			super(objectName + " is read-only. Cannot modify read-only object");
			this.objectName = objectName;
		}
		
		public String getObjectName() {
			return objectName;
		}
		
	}
	
}
