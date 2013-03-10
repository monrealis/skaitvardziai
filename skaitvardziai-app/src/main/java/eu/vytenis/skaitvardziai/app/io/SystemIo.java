package eu.vytenis.skaitvardziai.app.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemIo {
	
	public static final String NEW_LINE = "\n";
	public static final String NO_NEW_LINE = "";

	private static ThreadLocal<OutputStream> systemOut = new ThreadLocal<OutputStream>();
	private static ThreadLocal<OutputStream> systemErr = new ThreadLocal<OutputStream>();
	
	private Charset inputCharset = Charset.defaultCharset();
	private Charset outputCharset = Charset.defaultCharset();
	
	public void setOut(OutputStream out) {
		systemOut.set(out);
	}
	public void setErr(OutputStream err) {
		systemErr.set(err);
	}
	
	public void printErr(String text, String newLine) {
		OutputStream os = getErr();
		writeString(os, text + newLine);
	}
	private OutputStream getErr() {
		OutputStream os = systemErr.get() != null ? systemErr.get() : System.err;
		return os;
	}

	public void printOut(String text, String newLine) {
		OutputStream os = getOut();
		writeString(os, text + newLine);
	}
	private OutputStream getOut() {
		OutputStream os = systemOut.get() != null ? systemOut.get() : System.out;
		return os;
	}
	
	private void writeString(OutputStream output, String text) {
		ByteBuffer bb = outputCharset.encode(text);
		byte[] bytes = new byte[bb.remaining()];
		bb.get(bytes);
		try {
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

	public Reader createSystemInReader() {
		return new InputStreamReader(System.in, inputCharset);
		
		
	}

}
