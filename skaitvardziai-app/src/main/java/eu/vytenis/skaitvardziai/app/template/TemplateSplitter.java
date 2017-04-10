package eu.vytenis.skaitvardziai.app.template;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.template.TemplateProcessor.TemplateParseException;

class TemplateSplitter {
	private final String startTag;
	private final String endTag;
	private final SystemIo systemIo;
	private final List<TextSource> fragments = new ArrayList<TextSource>();
	private Pattern instructionsPattern;
	private Reader reader;
	private String inputText;

	public TemplateSplitter(String startTag, String endTag, SystemIo systemIo) {
		this.startTag = startTag;
		this.endTag = endTag;
		this.systemIo = systemIo;
	}

	private void createReader() {
		reader = systemIo.createInReader();
	}

	public List<TextSource> split() {
		createReader();
		createPattern();
		read();
		collectFragments();
		return fragments;
	}

	private void read() {
		try {
			tryRead();
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	private void tryRead() throws IOException {
		StringWriter w = new StringWriter();
		char[] buffer = new char[1024];
		int count;
		while ((count = reader.read(buffer)) > 0)
			w.write(buffer, 0, count);
		inputText = w.toString();
	}

	private void createPattern() {
		String patternText = Patterns.quote(startTag) + "(.*?)" + Patterns.quote(endTag);
		instructionsPattern = Pattern.compile(patternText, Pattern.MULTILINE | Pattern.DOTALL);
	}

	private void collectFragments() {
		Matcher matcher = instructionsPattern.matcher(inputText);
		int nextCharIndex = 0;
		while (matcher.find()) {
			addStaticFragmentIfNotEmpty(nextCharIndex, matcher.start());
			fragments.add(new MethodInvocationSource(matcher.group(1)));
			nextCharIndex = matcher.end();
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
}