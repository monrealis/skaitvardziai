package eu.vytenis.skaitvardziai;

/**
 * Skaitvardžių bibliotekos neprivaloma gaudyti klaida ({@link RuntimeException}). 
 */
public class SkaitvardziaiRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 163382671521469010L;

	public SkaitvardziaiRuntimeException(String message) {
		super(message);
	}
}
