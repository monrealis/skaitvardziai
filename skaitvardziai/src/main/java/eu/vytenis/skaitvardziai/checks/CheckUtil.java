package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;

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
			throw new IllegalArgumentException(name + " cannot be null");
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
		if (min != null && max != null) {
			if (value.compareTo(min) < 0 || value.compareTo(max) > 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", " + max + "]");
			}
		} else if (min != null) {
			if (value.compareTo(min) < 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value.compareTo(max) > 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (infinity, " + max + "]");
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
		if (min != null && max != null) {
			if (value.compareTo(min) <= 0 || value.compareTo(max) >= 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (" + min + ", " + max + ")");
			}
		} else if (min != null) {
			if (value.compareTo(min) <= 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value.compareTo(max) >= 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (infinity, " + max + ")");
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
		if (min != null && max != null) {
			if (value.compareTo(min) < 0 || value.compareTo(max) >= 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", " + max + ")");
			}
		} else if (min != null) {
			if (value.compareTo(min) < 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value.compareTo(max) >= 0) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (infinity, " + max + "]");
			}
		}
	}
}