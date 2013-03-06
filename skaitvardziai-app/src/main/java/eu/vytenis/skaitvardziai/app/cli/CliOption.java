package eu.vytenis.skaitvardziai.app.cli;

public enum CliOption {		
	Help("h", "help", false, "show help"),
	NoNewline("n", "no-newline", false, "do not output the trailing newline"),
	Form("f", "form", true, "numeral form"),
	Transform("t", "transform", false, "execute template transformation");
	
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
		return new CliOption[] {Help, NoNewline, Form, Transform};
	}
}