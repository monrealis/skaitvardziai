package eu.vytenis.skaitvardziai.app.main;

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

import eu.vytenis.skaitvardziai.app.exc.ShowHelpException;
import eu.vytenis.skaitvardziai.app.exc.ShowUsageException;
import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
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
	
	private boolean inputFromSystemIn;
	private String outputNewLineSeparator;
	

	public static void main(String[] args) {
		Main main = new Main();
		main.doMain(args);
		
	}
	
	public void doMain(String[] args) {
		try {
			tryMain(args);
		} catch (ShowUsageException e) {
			usage();
		} catch (ShowHelpException e) {
			help();
		}
	}
	
	private void tryMain(String[] args) {
		createOptions();		
		parseCommandLine(args);
		
		checkHelpOption();
		
		parseForma();		
		calculateReader();
		calculateOutputNewLineSeparator();
		
		processInput();
	}
	
	private void checkHelpOption() {
		if (commandLine.hasOption("h")) {
			throw new ShowHelpException();
		}
		
	}

	private void parseForma() {
		if (commandLine.hasOption("f")) {
			String formParam = commandLine.getOptionValue("f");
			forma = SkaitvardziaiTextParser.get().parseForma(formParam, null);
		} else {
			forma = new Forma();
		}
	}

	private void calculateReader() {
		inputFromSystemIn = commandLine.getArgs().length == 0;
		if (inputFromSystemIn) {
			reader = new InputStreamReader(System.in);
			inputFromSystemIn = true;
		} else {
			createArgValuesReader();
		}
	}

	private void calculateOutputNewLineSeparator() {
		boolean noNewLine = commandLine.hasOption("n") && !inputFromSystemIn;
		outputNewLineSeparator = !noNewLine ? SystemIo.NEW_LINE : SystemIo.NO_NEW_LINE;
	}

	private void parseCommandLine(String[] args) {
		CommandLineParser parser = new PosixParser();
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException e) {
			throw new ShowUsageException(e);
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
		for (Option o : Option.getOptionsForArgs()) {
			options.addOption(o.getShortName(), o.getName(), o.isHasArg(), o.getDescription());
		}
	}
	
	private void help() {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printHelp(new PrintWriter(out), 80, "java -jar main.jar", "Parameters", options, 2, 2, "Prints text that represents given number", true);
		SystemIo.printOut(out.toString(), SystemIo.NO_NEW_LINE);
	}
	
	public void usage() {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printUsage(new PrintWriter(out), 80, "java -jar main.jar", options);
		SystemIo.printOut(out.toString(), SystemIo.NO_NEW_LINE);
	}

	
	private void processInput() {
		try {
			tryProcessInput(outputNewLineSeparator);
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	private void tryProcessInput(String newLine) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			processInputText(newLine, line);
		}
	}

	private void processInputText(String newLine, String line) {
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
	
	public enum Option {		
		Help("h", "help", false, "show help"),
		NoNewline("n", "no-newline", false, "do not output the trailing newline"),
		Form("f", "form", true, "numeral form");
		
		private String shortName;
		private String name;
		private boolean hasArg;
		private String description;
		
		private Option(String shortName, String name, boolean hasArg, String description) {
			this.shortName = shortName;
			this.name = name;
			this.hasArg = hasArg;
			this.description = description;
		}
		
		public String getShortName() {
			return shortName;
		}
		
		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
		
		public boolean isHasArg() {
			return hasArg;
		}
		
		public static Option[] getOptionsForArgs() {
			return new Option[] {Help, NoNewline, Form};
		}

	}

}
