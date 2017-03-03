package eu.vytenis.skaitvardziai.app.cli;

import org.apache.commons.cli.CommandLine;

public class CliOptions {
	public static boolean isIn(CliOption option, CommandLine commandLine) {
		return commandLine.hasOption(option.getShortName());
	}

	public static String getValue(CliOption option, CommandLine commandLine) {
		return commandLine.getOptionValue(option.getShortName());
	}

	public static CliOption[] getOptionsForArgs() {
		return CliOption.values();
	}

}
