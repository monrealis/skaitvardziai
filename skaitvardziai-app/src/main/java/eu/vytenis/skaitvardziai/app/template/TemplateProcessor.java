package eu.vytenis.skaitvardziai.app.template;

import static eu.vytenis.skaitvardziai.app.cli.CliOption.EndTag;
import static eu.vytenis.skaitvardziai.app.cli.CliOption.StartTag;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.getValue;
import static eu.vytenis.skaitvardziai.app.cli.CliOptions.isIn;
import static java.util.Collections.unmodifiableList;

import java.util.List;

import org.apache.commons.cli.CommandLine;

import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.processors.Processor;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class TemplateProcessor implements Processor {
	public static final String DEFAULT_START_TAG = "${";
	public static final String DEFAULT_END_TAG = "}";
	private final SystemIo systemIo;
	private final String startTag;
	private final String endTag;
	private List<TextSource> fragments;

	public TemplateProcessor(CommandLine commandLine, SystemIo systemIo) {
		this.systemIo = systemIo;
		startTag = isIn(StartTag, commandLine) ? getValue(StartTag, commandLine) : DEFAULT_START_TAG;
		endTag = isIn(EndTag, commandLine) ? getValue(EndTag, commandLine) : DEFAULT_END_TAG;
	}

	public TemplateProcessor(String startTag, String endTag, SystemIo systemIo) {
		this.startTag = startTag;
		this.endTag = endTag;
		this.systemIo = systemIo;
	}

	public void process() {
		TemplateSplitter parser = new TemplateSplitter(startTag, endTag, systemIo);
		fragments = parser.split();
		write();
	}

	void write() {
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
