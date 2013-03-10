package eu.vytenis.skaitvardziai.app.io;

import java.io.IOException;
import java.io.OutputStream;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class SystemIo {
	
	public static final String NEW_LINE = "\n";
	public static final String NO_NEW_LINE = "";

	private static ThreadLocal<OutputStream> systemOut = new ThreadLocal<OutputStream>();
	private static ThreadLocal<OutputStream> systemErr = new ThreadLocal<OutputStream>();
	
	public static void setOut(OutputStream out) {
		systemOut.set(out);
	}
	public static void setErr(OutputStream err) {
		systemErr.set(err);
	}
	
	public static void printErr(String text, String newLine) {
		OutputStream os = systemErr.get() != null ? systemErr.get() : System.err;
		writeString(os, text + newLine, null);
	}

	public static void printOut(String text, String newLine) {
		OutputStream os = systemOut.get() != null ? systemOut.get() : System.out;
		writeString(os, text + newLine, null);
	}
	
	private static void writeString(OutputStream output, String text, String encoding) {
		try {
			byte[] encodedText = encoding != null ? text.getBytes(encoding) : text.getBytes();
			output.write(encodedText);
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}


}
