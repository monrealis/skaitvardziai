package eu.vytenis.skaitvardziai.app.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemIo {
	public static final String NEW_LINE = "\n";
	public static final String NO_NEW_LINE = "";
	private static ThreadLocal<OutputStream> systemOut = new ThreadLocal<OutputStream>();
	private static ThreadLocal<OutputStream> systemErr = new ThreadLocal<OutputStream>();
	private OutputStream nonSystemOut;
	private InputStream nonSystemIn;
	private Charset inputCharset = Charset.defaultCharset();
	private Charset outputCharset = Charset.defaultCharset();

	public void setSystemOut(OutputStream out) {
		systemOut.set(out);
	}

	public void setSystemErr(OutputStream err) {
		systemErr.set(err);
	}

	public void restoreSystemOut() {
		systemOut.set(null);
	}

	public void restoreSystemErr() {
		systemErr.set(null);
	}

	public void printErr(String text, String newLine) {
		OutputStream os = getErr();
		writeString(os, text + newLine);
	}

	private OutputStream getErr() {
		if (systemErr.get() != null) {
			return systemErr.get();
		} else {
			return System.err;
		}
	}

	public void printOut(String text, String newLine) {
		OutputStream os = getOut();
		writeString(os, text + newLine);
	}

	private OutputStream getOut() {
		if (nonSystemOut != null) {
			return nonSystemOut;
		} else if (systemOut.get() != null) {
			return systemOut.get();
		} else {
			return System.out;
		}
	}

	private void writeString(OutputStream output, String text) {
		try {
			byte[] bytes = text.getBytes(outputCharset.name());
			output.write(bytes);
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	public Charset getInputCharset() {
		return inputCharset;
	}

	public Charset getOutputCharset() {
		return outputCharset;
	}

	public void setInputCharsetName(String charsetName) {
		inputCharset = Charset.forName(charsetName);
	}

	public void setOutputCharsetName(String charsetName) {
		outputCharset = Charset.forName(charsetName);
	}

	public Reader createInReader() {
		return new InputStreamReader(getIn(), inputCharset);
	}

	private InputStream getIn() {
		if (nonSystemIn != null) {
			return nonSystemIn;
		} else {
			return System.in;
		}
	}

	public void setInput(File file) {
		try {
			nonSystemIn = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	public void setOutput(File file) {
		try {
			nonSystemOut = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

}
