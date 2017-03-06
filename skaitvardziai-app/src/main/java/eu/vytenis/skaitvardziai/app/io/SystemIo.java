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

// TODO 2017-03-07 separate IO from parameters 
public class SystemIo {
	private OutputStream systemOut = System.out;
	private OutputStream systemErr = System.err;
	private InputStream systemIn = System.in;
	private Charset inputCharset = Charset.defaultCharset();
	private Charset outputCharset = Charset.defaultCharset();

	public SystemIo() {
	}

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

	public void printErr(String text, String newLine) {
		OutputStream os = getErr();
		writeString(os, text + newLine);
	}

	private OutputStream getErr() {
		return systemErr;
	}

	public void printOut(String text, String newLine) {
		OutputStream os = getOut();
		writeString(os, text + newLine);
	}

	private OutputStream getOut() {
		return systemOut;
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
		return systemIn;
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
