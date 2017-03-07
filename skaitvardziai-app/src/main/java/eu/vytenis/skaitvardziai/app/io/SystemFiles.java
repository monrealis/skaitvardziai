package eu.vytenis.skaitvardziai.app.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemFiles {
	private OutputStream systemOut = System.out;
	private OutputStream systemErr = System.err;
	private InputStream systemIn = System.in;

	public void setSystemOut(OutputStream out) {
		systemOut = out;
	}

	public void setSystemErr(OutputStream err) {
		systemErr = err;
	}

	public void setSystemIn(InputStream in) {
		systemIn = in;
	}

	public void restoreSystemOut() {
		systemOut = System.out;
	}

	public void restoreSystemErr() {
		systemErr = System.err;
	}

	public void restoreSystemIn() {
		systemIn = System.in;
	}

	public OutputStream getErr() {
		return systemErr;
	}

	public InputStream getIn() {
		return systemIn;
	}

	public OutputStream getOut() {
		return systemOut;
	}

	public void setInput(File file) {
		try {
			systemIn = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	public void setOutput(File file) {
		try {
			systemOut = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
}
