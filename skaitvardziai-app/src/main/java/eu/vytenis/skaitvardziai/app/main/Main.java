package eu.vytenis.skaitvardziai.app.main;

import static eu.vytenis.skaitvardziai.app.cli.CliOption.Help;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.InputEncoding;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.InputFile;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.OutputEncoding;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.OutputFile;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.Transform;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.getOptionsForArgs;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.getValue;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.isIn;

import java.io.File;
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
import eu.vytenis.skaitvardziai.app.io.Charsets;
import eu.vytenis.skaitvardziai.app.io.SystemFiles;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.app.template.TemplateProcessor;
import eu.vytenis.skaitvardziai.checks.Checks;

public class Main {
	private CommandLine commandLine;
	private Options options;
	private final SystemFiles systemFiles;
	private final SystemIo systemIo;

	public static void main(String[] args) {
		Main main = new Main(new SystemFiles());
		main.doMain(args);
	}

	public Main(SystemFiles systemFiles) {
		Checks.checkNotNull("systemFiles", systemFiles);
		this.systemFiles = systemFiles;
		this.systemIo = new SystemIo(systemFiles);
	}

	public Charsets doMain(String[] args) {
		try {
			tryMain(args);
		} catch (ShowUsageException e) {
			usage();
		} catch (ShowHelpException e) {
			help();
		}
		return systemIo;
	}

	private void tryMain(String[] args) {
		createOptions();
		parseCommandLine(args);
		checkHelpOption();
		buildSystemIo();
		createProcessor().process();
	}

	private void checkHelpOption() {
		if (isIn(Help, commandLine))
			throw new ShowHelpException();
	}

	private void buildSystemIo() {
		if (isIn(OutputEncoding, commandLine))
			systemIo.setOutputCharsetName(getValue(OutputEncoding, commandLine));
		if (isIn(InputEncoding, commandLine))
			systemIo.setInputCharsetName(getValue(InputEncoding, commandLine));
		if (isIn(OutputFile, commandLine))
			systemFiles.setOutput(new File(getValue(OutputFile, commandLine)));
		if (isIn(InputFile, commandLine))
			systemFiles.setInput(new File(getValue(InputFile, commandLine)));
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
		for (CliOption o : getOptionsForArgs())
			options.addOption(o.getShortName(), o.getName(), o.isHasArgument(), o.getDescription());
	}

	private Processor createProcessor() {
		if (isIn(Transform, commandLine))
			return new TemplateProcessor(commandLine, systemIo);
		else
			return new EchoProcessor(commandLine, systemIo);
	}

	private void help() {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printHelp(new PrintWriter(out), 80, "skaiciai", "Parameters", options, 2, 2, "Prints text that represents given number", true);
		systemIo.printOut(out.toString(), "");
	}

	public void usage() {
		HelpFormatter f = new HelpFormatter();
		StringWriter out = new StringWriter();
		f.printUsage(new PrintWriter(out), 80, "java -jar main.jar", options);
		systemIo.printOut(out.toString(), "");
	}

	public SystemIo getSystemIo() {
		return systemIo;
	}
}
