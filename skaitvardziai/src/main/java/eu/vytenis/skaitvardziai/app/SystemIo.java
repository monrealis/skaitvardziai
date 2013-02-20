package eu.vytenis.skaitvardziai.app;

import java.io.IOException;
import java.io.Writer;

public class SystemIo {
	
	public static final String NEW_LINE = "\n";
	public static final String NO_NEW_LINE = "";

	private static ThreadLocal<Writer> systemOut = new ThreadLocal<Writer>();
	private static ThreadLocal<Writer> systemErr = new ThreadLocal<Writer>();
	
	public static void setOut(Writer writer) {
		systemOut.set(writer);
	}
	public static void setErr(Writer writer) {
		systemErr.set(writer);
	}
	
	public static void printErr(String text, String newLine) throws IOException {
		Writer w = systemErr.get();
		if (w != null) {
			w.write(text + newLine);
		} else {
			System.err.print(text + newLine);
		}
	}
	
	public static void printOut(String text, String newLine) throws IOException {
		Writer w = systemOut.get();
		if (w != null) {
			w.write(text + newLine);
		} else {
			System.out.print(text + newLine);
		}
	}


}
