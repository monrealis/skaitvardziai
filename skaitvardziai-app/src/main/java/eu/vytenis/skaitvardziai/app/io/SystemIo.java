package eu.vytenis.skaitvardziai.app.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemIo {
	private Charset inputCharset = Charset.defaultCharset();
	private Charset outputCharset = Charset.defaultCharset();
	private final SystemFiles systemFiles;

	public SystemIo(SystemFiles systemFiles) {
		this.systemFiles = systemFiles;
	}

	public void printErr(String text, String newLine) {
		OutputStream os = getErr();
		writeString(os, text + newLine);
	}

	private OutputStream getErr() {
		return systemFiles.getErr();
	}

	public void printOut(String text, String newLine) {
		OutputStream os = getOut();
		writeString(os, text + newLine);
	}

	private OutputStream getOut() {
		return systemFiles.getOut();
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
		return systemFiles.getIn();
	}
	
	public SystemFiles getSystemFiles() {
		return systemFiles;
	}
}
