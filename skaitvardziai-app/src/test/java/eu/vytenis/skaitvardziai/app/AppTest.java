package eu.vytenis.skaitvardziai.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.app.DecoratingStream.Mode;
import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.Charsets;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;
import eu.vytenis.skaitvardziai.app.io.SystemFiles;
import eu.vytenis.skaitvardziai.app.main.Main;

public abstract class AppTest {
	protected static final Pattern EMPTY_PATTERN = Pattern.compile("^$");
	protected static final String UTF8 = "utf-8";
	protected static final String WIN1257 = "windows-1257";
	protected SystemFiles systemFiles = new SystemFiles();
	protected Charsets charsets;
	protected SystemOutputFiles systemOutputFiles;
	private InputStream in = System.in;

	protected void assertOut(ExpectedOut out, Object... args) {
		assertOutErr(out, ExpectedOut.EMPTY, args);
	}

	protected void assertOutByIn(ExpectedOut out, String in, Object... args) {
		byte[] bytes = getInputAsBytes(in);
		this.in = new ByteArrayInputStream(bytes);
		assertOutErr(out, ExpectedOut.EMPTY, args);
	}

	private byte[] getInputAsBytes(String input) {
		try {
			return input.getBytes(Charset.defaultCharset().name());
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}

	@SuppressWarnings("unused")
	private void assertErr(ExpectedOut err, Object... args) {
		assertOutErr(ExpectedOut.EMPTY, err, args);
	}

	private void assertOutErr(ExpectedOut expectedOut, ExpectedOut expectedErr, Object... args) {
		main(args);
		SystemOutputFiles oe = systemOutputFiles;
		assertEquals(expectedOut.getText(), oe.getOutText(charsets.getOutputCharset()));
		if (expectedOut.getEncoding() != null)
			assertArrayEquals(expectedOut.getTextEncoded(), oe.getOutEncoded());
		assertEquals(expectedErr.getText(), oe.getErrText(charsets.getOutputCharset()));
		if (expectedErr.getEncoding() != null)
			assertArrayEquals(expectedErr.getTextEncoded(), oe.getErrEncoded());
	}

	protected void assertOutMatches(ExpectedOut out, Object... args) {
		assertOutErrMatches(out.toPattern(), EMPTY_PATTERN, args);
	}

	private void assertOutErrMatches(Pattern out, Pattern err, Object... args) {
		main(args);
		SystemOutputFiles oe = systemOutputFiles;
		assertMatches(oe.getOutText(charsets.getOutputCharset()), out);
		assertMatches(oe.getErrText(charsets.getOutputCharset()), err);
	}

	private void assertMatches(String text, Pattern pattern) {
		if (!pattern.matcher(text).matches())
			fail(text + "\ndoes not match\n" + pattern.pattern());
	}

	protected void main(Object... args) {
		DecoratingStream out = new DecoratingStream(System.out, Mode.CollectOnly);
		DecoratingStream err = new DecoratingStream(System.err, Mode.CollectOnly);
		systemFiles.setSystemOut(out);
		systemFiles.setSystemErr(err);
		systemFiles.setSystemIn(in);
		try {
			doMain(out, err, args);
		} finally {
			systemFiles.restoreSystemOut();
			systemFiles.restoreSystemErr();
			systemFiles.restoreSystemIn();
		}
	}

	private void doMain(DecoratingStream out, DecoratingStream err, Object... args) {
		String[] params = getMainArgs(args);
		Main main = new Main(systemFiles);
		charsets = main.doMain(params);
		systemOutputFiles = new SystemOutputFiles(out, err);
	}

	private String[] getMainArgs(Object... args) {
		String[] params = new String[args.length];
		for (int i = 0; i < args.length; ++i)
			params[i] = args[i] != null ? args[i].toString() : null;
		return params;
	}
}
