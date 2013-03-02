package eu.vytenis.skaitvardziai.app;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;


// TODO parašyti testų
public class MainTest {
	
	private static final Pattern EMPTY_PATTERN = Pattern.compile("^$");

	private SystemOutputFiles main(Object... args) {
		DecoratingWriter out = new DecoratingWriter(System.out);
		DecoratingWriter err = new DecoratingWriter(System.err);
		
		SystemIo.setOut(out);
		SystemIo.setErr(err);
		
		try {			
			return doMain(out, err, args);
		} finally {
			SystemIo.setOut(null);
			SystemIo.setErr(null);
		}
	}

	private SystemOutputFiles doMain(DecoratingWriter out, DecoratingWriter err, Object... args) {
		String[] params = getMainArgs(args);
		Main.main(params);
		return new SystemOutputFiles(out.getText(), err.getText());
	}

	private String[] getMainArgs(Object... args) {
		String[] params = new String[args.length];
		for (int i = 0; i < args.length; ++i) {
			params[i] = args[i] != null ? args[i].toString() : null;
		}
		return params;
	}
	
	@Test	
	public void testHelp() {
		Pattern p = Pattern.compile("(?ms)^usage:.*Prints text that represents given number[\n\r]+$");
		
		assertOutMatches(p, "-h");
		assertOutMatches(p, "--help");
		assertOutMatches(p, "--help", "-h");
	}
	
	@Test
	public void testUsage() {
		Pattern p = Pattern.compile("(?ms)^usage:.*\n$");
		assertOutMatches(p, "-X", 1);
	}

	@Test
	public void testOneArg() {
		assertOut("vienas\n", 1);
		assertOut("šimtas dešimt\n", 110);
		
		assertOut("vienas", "-n", 1);
		assertOut("šimtas dešimt", "--no-newline", 110);
		
		assertOut("vieno", "-f", "K", "-n", 1);		
	}
	
	private void assertOut(String out, Object... args) {
		assertOutErr(out, "", args);
	}
	
	@SuppressWarnings("unused")
	private void assertErr(String err, Object... args) {
		assertOutErr("", err, args);
	}
	
	private void assertOutErr(String out, String err, Object... args) {
		SystemOutputFiles oe = main(args);
 		Assert.assertEquals(out, oe.getOut());
		Assert.assertEquals(err, oe.getErr());
	}
	
	private void assertOutMatches(Pattern out, Object... args) {
		assertOutErrMatches(out, EMPTY_PATTERN, args);
	}
	
	@SuppressWarnings("unused")
	private void assertErrMatches(Pattern err, Object... args) {
		assertOutErrMatches(EMPTY_PATTERN, err, args);
	}
	
	private void assertOutErrMatches(Pattern out, Pattern err, Object... args) {
		SystemOutputFiles oe = main(args);
		assertMatches(oe.getOut(), out);	
		assertMatches(oe.getErr(), err);
	}
	
	private void assertMatches(String text, Pattern pattern) {
		if (!pattern.matcher(text).matches()) {
			Assert.fail(text + "\ndoes not match\n" + pattern.pattern());
		}
	}

}
