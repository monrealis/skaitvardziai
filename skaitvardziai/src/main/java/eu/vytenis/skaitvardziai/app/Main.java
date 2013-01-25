package eu.vytenis.skaitvardziai.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiTextParser;

// TODO suformuoti vykdomąjį JAR
public class Main {
	
	private static ThreadLocal<Writer> systemOut = new ThreadLocal<Writer>();
	private static ThreadLocal<Writer> systemErr = new ThreadLocal<Writer>();
	
	public static void setOut(Writer writer) {
		systemOut.set(writer);
	}

	public static void setErr(Writer writer) {
		systemErr.set(writer);
	}
	
	
	
	@SuppressWarnings("unused")
	private static void errPrint(String text, boolean appendNewLine) throws IOException {
		Writer w = systemErr.get();
		if (w != null) {
			w.write(text);
			if (appendNewLine) {
				w.write("\n");				
			}
		} else {
			System.err.print(text);
			if (appendNewLine) {
				System.err.println();
			}
		}
	}
	
	private static void outPrint(String text,  boolean appendNewLine) throws IOException {
		Writer w = systemOut.get();
		if (w != null) {
			w.write(text);
			if (appendNewLine) {
				w.write("\n");
			}
		} else {
			System.out.print(text);
			if (appendNewLine) {
				System.out.println();
			}
		}
	}
	
	private static PrintWriter outPrintWriter() {
		Writer w = systemOut.get();
		if (w != null) {
			return new PrintWriter(w);
		} else {
			return new PrintWriter(System.out);
		}
		
	}
	
	private static PrintWriter errPrintWriter() {
		Writer w = systemOut.get();
		if (w != null) {
			return new PrintWriter(w);
		} else {
			return new PrintWriter(System.err);
		}
	}
	
	private static void help(Options options) throws IOException {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printHelp(new PrintWriter(out), 80, "java -jar main.jar", "Parameters", options, 2, 2, "Prints text that represents given number", true);
		outPrint(out.toString(), false);
	}
	
	private static void usage(Options options) throws IOException {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printUsage(new PrintWriter(out), 80, "java -jar main.jar", options);
		outPrint(out.toString(), false);
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		Options options = new Options();
		options.addOption("h", "help", false, "show help");
		options.addOption("n", false, "do not output the trailing newline");
		
		CommandLineParser parser = new PosixParser();
		CommandLine cli = parser.parse(options, args);
		if (cli.hasOption("h")) {
			help(options);
			return;
		}
		
		Reader r = null;
		boolean readSysIn = false;
		if (cli.getArgs().length == 0) {
			r = new InputStreamReader(System.in);
			readSysIn = true;
		} else if (cli.getArgs().length == 1) {
			r = new StringReader(cli.getArgs()[0]);
		} else {
			usage(options);
			return;
		}
		boolean noNewLine = cli.hasOption("n") && !readSysIn;
		List<String> params = new ArrayList<String>();
		for (Option o : cli.getOptions()) {
			if (Arrays.asList("h", "n").contains(o.getOpt())) {
				continue;
			}
			params.add(o.getArgName());			
		}
		Forma f = SkaitvardziaiTextParser.get().parseForma(params.toArray(new String[]{}), null);
		BufferedReader br = new BufferedReader(r);
		String line;
		while ((line = br.readLine()) != null) {
			SkaitineReiksme sr = SkaitvardziaiTextParser.get().parseSkaicius(line);
			if (sr instanceof SveikasisSkaicius) {
				SveikasisSkaicius ss = (SveikasisSkaicius) sr;
				outPrint(ss.toString(f), !noNewLine);
			} else if (sr instanceof Trupmena) {
				outPrint(((Trupmena) sr).toString(f.getLinksnis()), !noNewLine);
			} else {
				throw new IllegalArgumentException();
			}
		}
	}

}
