package eu.vytenis.skaitvardziai.app.template;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;

public class TemplateProcessor implements Processor {

	private String openTag = "${";
	private String closeTag = "}";
	private Pattern instructionsPattern;
	
	private Reader reader;	
	private String inputText;	
	private List<Object> fragments;
	
	
	public void process() {
		createReader();
		createPattern();
		tryRead();
		collectFragments();
		write();
	}
	
	private void createReader() {
		reader = new InputStreamReader(System.in);
	}
	
	private void tryRead() {
		try {
			read();
		} catch (IOException e) {
			throw new RuntimeException(e); // TODO
		}
	}
	
	private void read() throws IOException {
		StringWriter w = new StringWriter();
		char[] buffer = new char[1024];
		int count;
		
		while ((count = reader.read(buffer)) > 0) {
			w.write(buffer, 0, count);
		}
		inputText = w.toString();
	}

	private void createPattern() {
		String patternText = Pattern.quote(openTag) + "(.*)" + Pattern.quote(closeTag);
		instructionsPattern = Pattern.compile(patternText, Pattern.MULTILINE | Pattern.DOTALL);
	}
	
	private void collectFragments() {
		fragments = new ArrayList<Object>();
		fragments.add(inputText);		
	}
	
	private void write() {
		for (Object o : fragments) {
			SystemIo.printOut(o.toString(), "");
		}
	}
	
}