package eu.vytenis.skaitvardziai.app.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.cli.CommandLine;

import eu.vytenis.skaitvardziai.app.cli.CliOption;
import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.skaiciai.SkaitineReiksme;
import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;
import eu.vytenis.skaitvardziai.util.SkaitvardziaiTextParser;

public class EchoProcessor implements Processor {
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
		if (commandLine.hasOption(CliOption.Form.getShortName())) {
			String formParam = commandLine.getOptionValue(CliOption.Form.getShortName());
			forma = SkaitvardziaiTextParser.get().parseForma(formParam, null);
		} else {
			forma = new Forma();
		}
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
		StringBuilder b = new StringBuilder();
		for (String line : commandLine.getArgs()) {
			b.append(line).append(SystemIo.NEW_LINE);
		}
		reader = new StringReader(b.toString());
	}		

	private void calculateOutputNewLineSeparator() {
		boolean noNewLine = commandLine.hasOption(CliOption.NoNewline.getShortName()) && !inputFromSystemIn;
		outputNewLineSeparator = !noNewLine ? SystemIo.NEW_LINE : SystemIo.NO_NEW_LINE;
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
			systemIo.printOut(ss.toString(forma), newLine);
		} else if (sr instanceof Trupmena) {
			Trupmena tr = (Trupmena) sr;
			systemIo.printOut(tr.toString(forma.getLinksnis()), newLine);
		} else {
			throw new SkaitvardziaiRuntimeException();
		}
	}


	
}