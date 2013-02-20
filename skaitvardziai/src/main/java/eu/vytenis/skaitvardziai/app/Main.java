package eu.vytenis.skaitvardziai.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import eu.vytenis.skaitvardziai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.Trupmena;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.xpath.SkaitvardziaiTextParser;

/**
 * Komandinės eilutės interfeiso pagrindinė klasė.
 */
public class Main {
	
	/** Nuskaityti komandinės eilutės parametrai. */
	private CommandLine commandLine;
	/** Įvedamų duomenų šaltinis (įvedami skaičiai). */
	private Reader reader;
	private Forma forma;
	private Options options;

	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.doMain(args);
		
	}
	
	public void doMain(String[] args) throws IOException {
		try {
			tryMain(args);
		} catch (ExitSilentlyException e) {
			usage();
		}
	}
	
	private void tryMain(String[] args) throws IOException, ExitSilentlyException {
		createOptions();		
		
		parseCommandLine(args);
		
		if (commandLine.hasOption("h")) {
			help();
			return;
		}

		parseForma();
		
		boolean readSysIn = commandLine.getArgs().length == 0;
		if (readSysIn) {
			reader = new InputStreamReader(System.in);
			readSysIn = true;
		} else {
			createArgValuesReader();
		}
		boolean noNewLine = commandLine.hasOption("n") && !readSysIn;
		String newLine = !noNewLine ? SystemIo.NEW_LINE : SystemIo.NO_NEW_LINE;
		
		processInput(newLine);
	}

	private void parseCommandLine(String[] args) throws IOException, ExitSilentlyException {
		CommandLineParser parser = new PosixParser();
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException e) {
			throw new ExitSilentlyException(e);
		}
	}

	private void createArgValuesReader() {
		StringBuilder b = new StringBuilder();
		for (String line : commandLine.getArgs()) {
			b.append(line).append(SystemIo.NEW_LINE);
		}
		reader = new StringReader(b.toString());
	}

	private void createOptions() {
		options = new Options();
		
		options.addOption("h", "help", false, "show help");
		options.addOption("n", "no-newline", false, "do not output the trailing newline");
		options.addOption("f", "form", true, "numeral form");
	}
	
	private void help() throws IOException {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printHelp(new PrintWriter(out), 80, "java -jar main.jar", "Parameters", options, 2, 2, "Prints text that represents given number", true);
		SystemIo.printOut(out.toString(), SystemIo.NO_NEW_LINE);
	}
	
	public void usage() throws IOException {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printUsage(new PrintWriter(out), 80, "java -jar main.jar", options);
		SystemIo.printOut(out.toString(), SystemIo.NO_NEW_LINE);
	}
	

	private void parseForma() {
		if (commandLine.hasOption("f")) {
			String formParam = commandLine.getOptionValue("f");
			forma = SkaitvardziaiTextParser.get().parseForma(formParam, null);
		} else {
			forma = new Forma();
		}
	}
	
	private void processInput(String newLine) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			processInputText(newLine, line);
		}
	}

	private void processInputText(String newLine, String line) throws IOException {
		SkaitineReiksme sr = SkaitvardziaiTextParser.get().parseSkaicius(line);
		if (sr instanceof SveikasisSkaicius) {
			SveikasisSkaicius ss = (SveikasisSkaicius) sr;
			SystemIo.printOut(ss.toString(forma), newLine);
		} else if (sr instanceof Trupmena) {
			SystemIo.printOut(((Trupmena) sr).toString(forma.getLinksnis()), newLine);
		} else {
			throw new IllegalArgumentException();
		}
	}

}
