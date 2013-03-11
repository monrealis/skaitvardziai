package eu.vytenis.skaitvardziai.app;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.DecoratingStream;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;
import eu.vytenis.skaitvardziai.app.io.SystemIo;
import eu.vytenis.skaitvardziai.app.io.SystemOutputFiles;
import eu.vytenis.skaitvardziai.app.io.DecoratingStream.Mode;
import eu.vytenis.skaitvardziai.app.main.Main;

public class AppTest {

	protected static final Pattern EMPTY_PATTERN = Pattern.compile("^$");
	protected static final String UTF8 = "utf-8";
	protected static final String WIN1257 = "windows-1257";
	protected SystemIo systemIo;
	
	@Before
	public void before() {
		systemIo = new SystemIo();
	}
	
	protected SystemOutputFiles main(Object... args) {
		DecoratingStream out = new DecoratingStream(System.out, Mode.CollectOnly);
		DecoratingStream err = new DecoratingStream(System.err, Mode.CollectOnly);
		
		systemIo.setSystemOut(out);
		systemIo.setSystemErr(err);
		
		try {			
			return doMain(out, err, args);
		} finally {
			systemIo.setSystemOut(null);
			systemIo.setSystemErr(null);
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

	protected void assertOut(ExpectedOut out, Object... args) {
		assertOutErr(out, ExpectedOut.EMPTY, args);
	}

	protected void assertOutByIn(ExpectedOut out, String in, Object... args) {
		byte[] bytes = getInputAsBytes(in);
		System.setIn(new ByteArrayInputStream(bytes));
		assertOutErr(out, ExpectedOut.EMPTY, args);
	}

	private byte[] getInputAsBytes(String input) {
		try {
			return input.getBytes(systemIo.getInputCharset().name());
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
	
	@SuppressWarnings("unused")
	private void assertErr(ExpectedOut err, Object... args) {
		assertOutErr(ExpectedOut.EMPTY, err, args);
	}

	private void assertOutErr(ExpectedOut expectedOut, ExpectedOut expectedErr, Object... args) {
		SystemOutputFiles oe = main(args);
		Assert.assertEquals(expectedOut.getText(), oe.getOutText(systemIo.getOutputCharset()));
		if (expectedOut.getEncoding() != null) {
			Assert.assertArrayEquals(expectedOut.getTextEncoded(), oe.getOutEncoded());
		}
		Assert.assertEquals(expectedErr.getText(), oe.getErrText(systemIo.getOutputCharset()));
		if (expectedErr.getEncoding() != null) {
			Assert.assertArrayEquals(expectedErr.getTextEncoded(), oe.getErrEncoded());
		}
	}

	protected void assertOutMatches(ExpectedOut out, Object... args) {
		assertOutErrMatches(out.toPattern(), EMPTY_PATTERN, args);
	}

	@SuppressWarnings("unused")
	private void assertErrMatches(ExpectedOut err, Object... args) {
		assertOutErrMatches(EMPTY_PATTERN, err.toPattern(), args);
	}

	private void assertOutErrMatches(Pattern out, Pattern err, Object... args) {
		SystemOutputFiles oe = main(args);
		assertMatches(oe.getOutText(systemIo.getOutputCharset()), out);	
		assertMatches(oe.getErrText(systemIo.getOutputCharset()), err);
	}

	private void assertMatches(String text, Pattern pattern) {
		if (!pattern.matcher(text).matches()) {
			Assert.fail(text + "\ndoes not match\n" + pattern.pattern());
		}
	}

}