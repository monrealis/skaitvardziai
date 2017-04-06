package eu.vytenis.skaitvardziai.app.template;

import static eu.vytenis.skaitvardziai.app.cli.CliOption.EndTag;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.StartTag;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.getValue;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.isIn;
import static java.util.Collections.unmodifiableList;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.cli.CommandLine;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.SystemFiles;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class TemplateProcessor implements Processor {
	static final String DEFAULT_START_TAG = "${";
	static final String DEFAULT_END_TAG = "}";
	private final SystemIo systemIo;
	private String startTag = DEFAULT_START_TAG;
	private String endTag = DEFAULT_END_TAG;
	private Pattern instructionsPattern;
	private Reader reader;
	private String inputText;
	private List<TextSource> fragments;

	public TemplateProcessor(CommandLine commandLine, SystemIo systemIo) {
		this.systemIo = systemIo;
		if (isIn(StartTag, commandLine))
			startTag = getValue(StartTag, commandLine);
		if (isIn(EndTag, commandLine))
			endTag = getValue(EndTag, commandLine);
	}

	TemplateProcessor(String startTag, String endTag, String inputText, SystemFiles systemFiles) {
		this.startTag = startTag;
		this.endTag = endTag;
		this.inputText = inputText;
		this.systemIo = new SystemIo(systemFiles);
	}

	public void process() {
		createReader();
		createPattern();
		tryRead();
		collectFragments();
		write();
	}

	void createReader() {
		reader = systemIo.createInReader();
	}

	void tryRead() {
		try {
			read();
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	void read() throws IOException {
		StringWriter w = new StringWriter();
		char[] buffer = new char[1024];
		int count;
		while ((count = reader.read(buffer)) > 0)
			w.write(buffer, 0, count);
		inputText = w.toString();
	}

	void createPattern() {
		String patternText = Patterns.quote(startTag) + "(.*?)" + Patterns.quote(endTag);
		instructionsPattern = Pattern.compile(patternText, Pattern.MULTILINE | Pattern.DOTALL);
	}

	void collectFragments() {
		fragments = new ArrayList<TextSource>();
		Matcher m = instructionsPattern.matcher(inputText);
		int nextCharIndex = 0;
		while (m.find()) {
			addStaticFragmentIfNotEmpty(nextCharIndex, m.start());
			fragments.add(new MethodInvocationSource(m.group(1)));
			nextCharIndex = m.end();
		}
		addStaticFragmentIfNotEmpty(nextCharIndex, inputText.length());
	}

	private void addStaticFragmentIfNotEmpty(int firstIndex, int lastIndex) {
		if (lastIndex <= firstIndex)
			return;
		String text = inputText.substring(firstIndex, lastIndex);
		int idx = text.indexOf(startTag);
		if (idx >= 0)
			throw new TemplateParseException(String.format("Invalid text starting at index %d: %s", idx, startTag));
		fragments.add(new StringSource(text));
	}

	private void write() {
		for (TextSource s : fragments)
			systemIo.printOut(s.getText(), "");
	}

	public List<TextSource> getFragments() {
		return unmodifiableList(fragments);
	}

	public static class TemplateParseException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public TemplateParseException(String message) {
			super(message);
		}
	}
}
