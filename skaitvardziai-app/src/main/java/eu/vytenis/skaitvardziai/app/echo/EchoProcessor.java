package eu.vytenis.skaitvardziai.app.echo;

import static eu.vytenis.skaitvardziai.app.cli.CliOption.Form;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.NoNewline;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.getValue;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.isIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.cli.CommandLine;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksmeVisitor;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
import eu.vytenis.skaitvardziai.text.FormaParser;
import eu.vytenis.skaitvardziai.text.SkaitineReiksmeParser;

public class EchoProcessor implements Processor {
	private final CommandLine commandLine;
	private final SystemIo systemIo;
	private Reader reader;
	private String outputNewLineSeparator;
	private Forma forma;

	public EchoProcessor(CommandLine commandLine, SystemIo systemIo) {
		this.commandLine = commandLine;
		this.systemIo = systemIo;
	}

	public void process() {
		forma = parseForma();
		reader = calculateReader();
		outputNewLineSeparator = calculateOutputNewLineSeparator();
		processInput();
	}

	private Forma parseForma() {
		if (!isIn(Form, commandLine))
			return new Forma();
		return new FormaParser().parse(getValue(Form, commandLine));
	}

	private Reader calculateReader() {
		if (isInputFromSystemIn())
			return systemIo.createInReader();
		else
			return createArgValuesReader();
	}

	private Reader createArgValuesReader() {
		String s = "";
		for (String line : commandLine.getArgs())
			s += line + SystemIo.NEW_LINE;
		return new StringReader(s);
	}

	private String calculateOutputNewLineSeparator() {
		if (isInputFromSystemIn())
			return SystemIo.NEW_LINE;
		if (!isIn(NoNewline, commandLine))
			return SystemIo.NEW_LINE;
		return SystemIo.NO_NEW_LINE;
	}

	private void processInput() {
		try {
			tryProcessInput();
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	private void tryProcessInput() throws IOException {
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null)
			processInputText(line);
	}

	private void processInputText(String line) {
		SkaitineReiksme sr = new SkaitineReiksmeParser().parse(line);
		String text = sr.accept(new Formatter(sr, forma));
		printOut(text);
	}

	private void printOut(String text) {
		systemIo.printOut(text, outputNewLineSeparator);
	}

	private boolean isInputFromSystemIn() {
		return commandLine.getArgs().length == 0;
	}

	private static class Formatter implements SkaitineReiksmeVisitor<String> {
		private final SkaitineReiksme skaitineReiksme;
		private final Forma forma;

		private Formatter(SkaitineReiksme skaitineReiksme, Forma forma) {
			this.skaitineReiksme = skaitineReiksme;
			this.forma = forma;
		}

		public String visitSveikasisSkaicius() {
			return ((SveikasisSkaicius) skaitineReiksme).toString(forma);
		}

		public String visitTrupmena() {
			return ((Trupmena) skaitineReiksme).toString(forma.getLinksnis());
		}
	}
}
