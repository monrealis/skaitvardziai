package eu.vytenis.skaitvardziai.app.template;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class TemplateProcessor implements Processor {

	String openTag = "${";
	String closeTag = "}";
	Pattern instructionsPattern;
	
	Reader reader;	
	String inputText;	
	List<TextSource> fragments;
	
	
	public void process() {
		createReader();
		createPattern();
		tryRead();
		collectFragments();
		write();
	}
	
	void createReader() {
		reader = new InputStreamReader(System.in);
	}
	
	void tryRead() {
		try {
			read();
		} catch (IOException e) {
			throw new RuntimeException(e); // TODO
		}
	}
	
	void read() throws IOException {
		StringWriter w = new StringWriter();
		char[] buffer = new char[1024];
		int count;
		
		while ((count = reader.read(buffer)) > 0) {
			w.write(buffer, 0, count);
		}
		inputText = w.toString();
	}

	void createPattern() {
		String patternText = Pattern.quote(openTag) + "(.*?)" + Pattern.quote(closeTag);
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
		if (lastIndex > firstIndex) {
			String text = inputText.substring(firstIndex, lastIndex);
			int idx = text.indexOf(openTag);
			if (idx >= 0) {				
				throw new TemplateParseException(String.format("Invalid text starting at index %d: %s", idx, openTag));
			}
			fragments.add(new StringSource(text));
		}		
	}
	
	void write() {
		for (TextSource s : fragments) {
			SystemIo.printOut(s.getText(), "");
		}
	}
	
	public static class TemplateParseException extends SkaitvardziaiRuntimeException {
		
		private static final long serialVersionUID = -2523506465513363826L;

		public TemplateParseException(String message) {
			super(message);
		}
		
	}
	
}