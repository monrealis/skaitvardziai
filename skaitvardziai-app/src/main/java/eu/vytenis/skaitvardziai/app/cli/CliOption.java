package eu.vytenis.skaitvardziai.app.cli;

public enum CliOption {		
	Help("h", "help", false, "show help"),
	NoNewline("n", "no-newline", false, "do not output the trailing newline"),
	Form("f", "form", true, "numeral form"),
	Transform("t", "transform", false, "execute template transformation"),
	StartTag("s", "start-tag", true, "opening tag of template function (defaults to \"${\")"),
	EndTag("e", "end-tag", true, "closing tag of template function (defaults to \"}\""),
	InputEncoding("ie", "input-encoding", true, "input encoding (defaults to system default)"),
	OutputEncoding("oe", "output-encoding", true, "output encoding (defaults to system default)"),
	InputFile("i", "input", true, "input file (defaults to stdin)"),
	OutputFile("o", "output", true, "output file (defaults to stdoud)");;
	
	private String shortName;
	private String name;
	private boolean hasArg;
	private String description;
	
	private CliOption(String shortName, String name, boolean hasArg, String description) {
		this.shortName = shortName;
		this.name = name;
		this.hasArg = hasArg;
		this.description = description;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public boolean isHasArg() {
		return hasArg;
	}
	
	public static CliOption[] getOptionsForArgs() {
		return CliOption.values();
	}
}