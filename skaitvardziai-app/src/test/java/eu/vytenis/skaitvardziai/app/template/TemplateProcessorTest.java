package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.app.io.SystemFiles;
import eu.vytenis.skaitvardziai.app.io.SystemIo;

public class TemplateProcessorTest {
	private String startTag = TemplateProcessor.DEFAULT_START_TAG;
	private String endTag = TemplateProcessor.DEFAULT_END_TAG;
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	private SystemFiles systemFiles = new SystemFiles();
	private String input;

	@Before
	public void before() {
		systemFiles.setSystemOut(output);
	}

	@Test
	public void run() {
		input = "-${sveikasis(1)}+";
		process();
		assertEquals("-vienas+", output.toString());
	}

	private void process() {
		systemFiles.setSystemIn(new ByteArrayInputStream(input.getBytes()));
		TemplateProcessor p = new TemplateProcessor(startTag, endTag, new SystemIo(systemFiles));
		p.process();
	}
}
