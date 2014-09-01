package eu.vytenis.skaitvardziai.app.exc;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class ShowUsageException extends SkaitvardziaiRuntimeException {
	private static final long serialVersionUID = 6985518190949376628L;

	public ShowUsageException(Throwable cause) {
		super(cause);
	}

}
