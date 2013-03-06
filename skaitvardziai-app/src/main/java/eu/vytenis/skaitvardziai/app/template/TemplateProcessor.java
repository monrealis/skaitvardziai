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
		List<Integer> from = new ArrayList<Integer>();
		List<Integer> to = new ArrayList<Integer>();
		List<String> calls = new ArrayList<String>();
		
		Matcher m = instructionsPattern.matcher(inputText);
		while (m.find()) {
			from.add(m.start());
			to.add(m.end());
			calls.add(m.group(1));
		}
		fragments.add(new StringSource(inputText));		
	}
	
	void write() {
		for (TextSource s : fragments) {
			SystemIo.printOut(s.getText(), "");
		}
	}
	
}