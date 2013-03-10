package eu.vytenis.skaitvardziai.app.main;

import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;

import org.junit.Assert;

import eu.vytenis.skaitvardziai.app.io.DecoratingStream;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.io.SystemOutputFiles;

public class AppTest {

	protected static final Pattern EMPTY_PATTERN = Pattern.compile("^$");
	
	protected String outputEncoding;

	private SystemOutputFiles main(Object... args) {
		DecoratingStream out = new DecoratingStream(System.out, outputEncoding);
		DecoratingStream err = new DecoratingStream(System.err, outputEncoding);
		
		SystemIo.setOut(out);
		SystemIo.setErr(err);
		
		try {			
			return doMain(out, err, args);
		} finally {
			SystemIo.setOut(null);
			SystemIo.setErr(null);
		}
	}

	private SystemOutputFiles doMain(DecoratingStream out, DecoratingStream err, Object... args) {
		String[] params = getMainArgs(args);
		Main.main(params);
		return new SystemOutputFiles(out, err);
	}

	private String[] getMainArgs(Object... args) {
		String[] params = new String[args.length];
		for (int i = 0; i < args.length; ++i) {
			params[i] = args[i] != null ? args[i].toString() : null;
		}
		return params;
	}

	protected void assertOut(Out out, Object... args) {
		assertOutErr(out, Out.EMPTY, args);
	}

	protected void assertOutByIn(Out out, String in, Object... args) {
		System.setIn(new ByteArrayInputStream(in.getBytes()));
		assertOutErr(out, Out.EMPTY, args);
	}
	
	@SuppressWarnings("unused")
	private void assertErr(Out err, Object... args) {
		assertOutErr(Out.EMPTY, err, args);
	}

	private void assertOutErr(Out out, Out err, Object... args) {
		SystemOutputFiles oe = main(args);
		Assert.assertEquals(out.getText(), oe.getOutText());
		if (out.getEncoding() != null) {
			Assert.assertArrayEquals(out.getEncodedText(), oe.getOutText().getBytes());
		}
		Assert.assertEquals(err.getText(), oe.getErrText());
	}

	protected void assertOutMatches(Out out, Object... args) {
		assertOutErrMatches(out.toPattern(), EMPTY_PATTERN, args);
	}

	@SuppressWarnings("unused")
	private void assertErrMatches(Out err, Object... args) {
		assertOutErrMatches(EMPTY_PATTERN, err.toPattern(), args);
	}

	private void assertOutErrMatches(Pattern out, Pattern err, Object... args) {
		SystemOutputFiles oe = main(args);
		assertMatches(oe.getOutText(), out);	
		assertMatches(oe.getErrText(), err);
	}

	private void assertMatches(String text, Pattern pattern) {
		if (!pattern.matcher(text).matches()) {
			Assert.fail(text + "\ndoes not match\n" + pattern.pattern());
		}
	}

}