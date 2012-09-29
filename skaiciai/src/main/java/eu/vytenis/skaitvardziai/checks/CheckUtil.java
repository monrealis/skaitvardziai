package eu.vytenis.skaitvardziai.checks;

/**
 * Operacijoms reikšmėms validuoti.
 *
 */
public class CheckUtil {

	/**
	 * Patikrina, ar skaičius priklauso intervalui [min, max].
	 * @param name kintamojo pavadinimas (matomas klaidos pranešime)
	 * @param value reikšmė (neprivalomas)
	 * @param min minimali reikšmė (neprivalomas)
	 * @param max maksimali reikšmė (neprivalomas)
	 */
	public static void checkInclusive(String name, Long value, Long min, Long max) {
		if (value == null) {
			return;
		}
		if (min != null && max != null) {
			if (value < min || value > max) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", " + max + "]");
			}
		} else if (min != null) {
			if (value < min) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value > max) {
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
	public static void checkExclusive(String name, Long value, Long min, Long max) {
		if (value == null) {
			return;
		}
		if (min != null && max != null) {
			if (value <= min || value >= max) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (" + min + ", " + max + ")");
			}
		} else if (min != null) {
			if (value <= min) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value >= max) {
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
	public static void checkMinInclusive(String name, Long value, Long min, Long max) {
		if (value == null) {
			return;
		}
		if (min != null && max != null) {
			if (value < min || value >= max) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", " + max + ")");
			}
		} else if (min != null) {
			if (value < min) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range [" + min + ", infinity)");
			}
		} else if (max != null) {
			if (value >= max) {
				throw new IllegalArgumentException(name + " = " + value + " must be in range (infinity, " + max + "]");
			}
		}
	}
}
