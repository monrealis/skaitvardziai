package eu.vytenis.skaitvardziai.app.exc;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class ShowUsageException extends SkaitvardziaiRuntimeException {
	private static final long serialVersionUID = 1;

	public ShowUsageException(Throwable cause) {
		super(cause);
	}
}
