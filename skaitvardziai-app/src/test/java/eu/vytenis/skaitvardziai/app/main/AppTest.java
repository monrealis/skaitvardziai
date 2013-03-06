package eu.vytenis.skaitvardziai.app.main;

import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;

import org.junit.Assert;

import eu.vytenis.skaitvardziai.app.io.DecoratingWriter;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.io.SystemOutputFiles;

public class AppTest {

	protected static final Pattern EMPTY_PATTERN = Pattern.compile("^$");

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

	protected void assertOut(String out, Object... args) {
		assertOutErr(out, "", args);
	}

	protected void assertOutByIn(String out, String in, Object... args) {
		System.setIn(new ByteArrayInputStream(in.getBytes()));
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

	protected void assertOutMatches(Pattern out, Object... args) {
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