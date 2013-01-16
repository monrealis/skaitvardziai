package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Įvardžiuotinės formos požymis.
 *
 */
public enum Ivardziuotinis implements Aliased {
	/** Įvardžiuotinė forma. */
	Iv;
	public String alias() {
		if (this == Iv) {
			return "Iv";
		} else {
			return name();
		}
	};
}