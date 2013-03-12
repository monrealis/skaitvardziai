package eu.vytenis.skaitvardziai.exc;

public class SkaitvardziaiRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 163382671521469010L;

	public SkaitvardziaiRuntimeException() {
	}

	public SkaitvardziaiRuntimeException(String message) {
		super(message);
	}
	
	public SkaitvardziaiRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
   
	public SkaitvardziaiRuntimeException(Throwable cause) {
		super(cause);
	}
}
