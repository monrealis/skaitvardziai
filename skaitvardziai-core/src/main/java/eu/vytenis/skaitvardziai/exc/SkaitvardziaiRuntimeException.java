package eu.vytenis.skaitvardziai.exc;

/**
 * Skaitvardžių bibliotekos neprivaloma gaudyti klaida ({@link RuntimeException}). 
 */
public class SkaitvardziaiRuntimeException extends RuntimeException {

	/** Klasės versija. */
	private static final long serialVersionUID = 163382671521469010L;

	/** Žiūrėti: {@link RuntimeException#RuntimeException()}. */
	public SkaitvardziaiRuntimeException() {
		super();
	}

	/** Žiūrėti: {@link RuntimeException#RuntimeException()}. */
	public SkaitvardziaiRuntimeException(String message) {
		super(message);
	}
	
	/** Žiūrėti: {@link RuntimeException#RuntimeException(String, Throwable)}. */
	public SkaitvardziaiRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
   
	/** Žiūrėti: {@link RuntimeException#RuntimeException(Throwable)}. */
	public SkaitvardziaiRuntimeException(Throwable cause) {
		super(cause);
	}
}
