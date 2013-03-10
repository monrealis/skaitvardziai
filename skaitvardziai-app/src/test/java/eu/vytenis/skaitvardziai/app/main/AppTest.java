package eu.vytenis.skaitvardziai.app.main;

import java.io.ByteArrayInputStream;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;

import eu.vytenis.skaitvardziai.app.io.DecoratingStream;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.io.SystemOutputFiles;

public class AppTest {

	protected static final Pattern EMPTY_PATTERN = Pattern.compile("^$");
	protected SystemIo systemIo;
	
	@Before
	public void before() {
		systemIo = new SystemIo();
	}
	
	private SystemOutputFiles main(Object... args) {
		DecoratingStream out = new DecoratingStream(System.out);
		DecoratingStream err = new DecoratingStream(System.err);
		
		systemIo.setOut(out);
		systemIo.setErr(err);
		
		try {			
			return doMain(out, err, args);
		} finally {
			systemIo.setOut(null);
			systemIo.setErr(null);
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

	private void assertOutErr(Out expectedOut, Out expectedErr, Object... args) {
		SystemOutputFiles oe = main(args);
		Assert.assertEquals(expectedOut.getText(), oe.getOutText());
		if (expectedOut.getEncoding() != null) {
			Assert.assertArrayEquals(expectedOut.getTextEncoded(), oe.getOutEncoded());
		}
		Assert.assertEquals(expectedErr.getText(), oe.getErrText());
		if (expectedErr.getEncoding() != null) {
			Assert.assertArrayEquals(expectedErr.getTextEncoded(), oe.getErrEncoded());
		}
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