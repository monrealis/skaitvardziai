package eu.vytenis.skaitvardziai.app;

import java.io.IOException;

import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;

public class SkaitvardziaiIOException extends SkaitvardziaiRuntimeException {

	/** Klasės versija.*/
	private static final long serialVersionUID = -5573285542788716881L;
	
	public SkaitvardziaiIOException(IOException cause) {
		super(cause);
	}
	
	


}
