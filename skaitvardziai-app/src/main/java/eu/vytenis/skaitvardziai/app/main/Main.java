package eu.vytenis.skaitvardziai.app.main;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import eu.vytenis.skaitvardziai.app.cli.CliOption;
import eu.vytenis.skaitvardziai.app.echo.EchoProcessor;
import eu.vytenis.skaitvardziai.app.exc.ShowHelpException;
import eu.vytenis.skaitvardziai.app.exc.ShowUsageException;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.app.template.TemplateProcessor;

/**
 * Komandinės eilutės interfeiso pagrindinė klasė.
 */
// TODO koduotes pridėti
public class Main {
	
	/** Nuskaityti komandinės eilutės parametrai. */
	private CommandLine commandLine;
	private Options options;

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
		
		Processor p;
		if (commandLine.hasOption(CliOption.Transform.getShortName())) {
			p = new TemplateProcessor();
		} else {
			p = new EchoProcessor(commandLine);
		}		
		p.process();
	}
	
	private void checkHelpOption() {
		if (commandLine.hasOption(CliOption.Help.getShortName())) {
			throw new ShowHelpException();
		}
	}

	private void parseCommandLine(String[] args) {
		CommandLineParser parser = new PosixParser();
		try {
			commandLine = parser.parse(options, args);
		} catch (ParseException e) {
			throw new ShowUsageException(e);
		}
	}


	private void createOptions() {
		options = new Options();
		for (CliOption o : CliOption.getOptionsForArgs()) {
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

}