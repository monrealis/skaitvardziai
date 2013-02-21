package eu.vytenis.skaitvardziai.app;

import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;

public class ExitSilentlyException extends SkaitvardziaiRuntimeException {

	/** Klasės versija. */
	private static final long serialVersionUID = 6985518190949376628L;
	
	public ExitSilentlyException(Throwable cause) {
		super(cause);
	}

}
