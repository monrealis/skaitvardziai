package eu.vytenis.skaitvardziai;

/**
 * Skaitvardžių bibliotekos neprivaloma gaudyti klaida ({@link RuntimeException}). 
 */
public class SkaitvardziaiRuntimeException extends RuntimeException {

	/** Klasės versija. */
	private static final long serialVersionUID = 163382671521469010L;

	/**
	 * Sukuria su nurodytu klaidos pranešimu.
	 * @param message klaidos pranešimas
	 */
	public SkaitvardziaiRuntimeException(String message) {
		super(message);
	}
}
