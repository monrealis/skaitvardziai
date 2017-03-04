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
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
import eu.vytenis.skaitvardziai.text.FormaParser;

public class EchoProcessor implements Processor {
	private final FormaParser parser = new FormaParser();
	private final CommandLine commandLine;
	private final SystemIo systemIo;
	private boolean inputFromSystemIn;
	private Reader reader;
	private String outputNewLineSeparator;
	private Forma forma;

	public EchoProcessor(CommandLine commandLine, SystemIo systemIo) {
		this.commandLine = commandLine;
		this.systemIo = systemIo;
	}

	public void process() {
		parseForma();
		calculateReader();
		calculateOutputNewLineSeparator();
		processInput();
	}

	private void parseForma() {
		if (isIn(Form, commandLine))
			forma = parser.parseForma(getValue(Form, commandLine));
		else
			forma = new Forma();
	}

	private void calculateReader() {
		inputFromSystemIn = commandLine.getArgs().length == 0;
		if (inputFromSystemIn) {
			reader = systemIo.createInReader();
			inputFromSystemIn = true;
		} else {
			createArgValuesReader();
		}
	}

	private void createArgValuesReader() {
		String s = "";
		for (String line : commandLine.getArgs())
			s += line + SystemIo.NEW_LINE;
		reader = new StringReader(s);
	}

	private void calculateOutputNewLineSeparator() {
		boolean noNewLine = isIn(NoNewline, commandLine) && !inputFromSystemIn;
		outputNewLineSeparator = !noNewLine ? SystemIo.NEW_LINE : SystemIo.NO_NEW_LINE;
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
		SkaitineReiksme sr = parser.parseSkaicius(line);
		if (sr instanceof SveikasisSkaicius)
			printSveikasis((SveikasisSkaicius) sr);
		else if (sr instanceof Trupmena)
			printTrupmena((Trupmena) sr);
		else
			throw new SkaitvardziaiRuntimeException();
	}

	private void printSveikasis(SveikasisSkaicius sveikasisSkaicius) {
		printOut(sveikasisSkaicius.toString(forma));
	}

	private void printTrupmena(Trupmena trupmena) {
		printOut(trupmena.toString(forma.getLinksnis()));
	}

	private void printOut(String text) {
		systemIo.printOut(text, outputNewLineSeparator);
	}
}
