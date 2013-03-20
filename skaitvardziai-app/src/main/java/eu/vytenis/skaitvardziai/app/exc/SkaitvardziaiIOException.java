package eu.vytenis.skaitvardziai.app.exc;

import java.io.IOException;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class SkaitvardziaiIOException extends SkaitvardziaiRuntimeException {

	private static final long serialVersionUID = -5573285542788716881L;
	
	public SkaitvardziaiIOException(IOException cause) {
		super(cause);
	}
	
	


}
