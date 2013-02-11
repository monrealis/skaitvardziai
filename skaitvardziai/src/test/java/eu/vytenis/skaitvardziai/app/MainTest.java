package eu.vytenis.skaitvardziai.app;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;


// TODO parašyti testų
public class MainTest {

	private OutAndErr main(Object... args) {
		DecoratingOutWriter out = new DecoratingOutWriter(System.out);
		DecoratingOutWriter err = new DecoratingOutWriter(System.err);
		Main.setOut(out);
		Main.setErr(err);
		
		try {
			String[] params = new String[args.length];
			for (int i = 0; i < args.length; ++i) {
				params[i] = args[i] != null ? args[i].toString() : null;
			}
			try {
				Main.main(params);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			return new OutAndErr(out.getText(), err.getText());
		} finally {
			Main.setOut(null);
			Main.setErr(null);
		}
	}
	
	private void assertOutErr(String out, String err, Object... args) {
		OutAndErr oe = main(args);
 		Assert.assertEquals(out, oe.getOut());
		Assert.assertEquals(err, oe.getErr());
	}
	
	private void assertOutErrRegExp(String out, String err, Object... args) {
		OutAndErr oe = main(args);
		if (!oe.getOut().matches(out)) {
			Assert.fail(out + "\ndoes not match\n" + oe.getOut());
		}
		if (!oe.getErr().matches(err)) {
			Assert.fail(err + "\ndoes not match\n" + oe.getErr());
		}
	}
	
	@Test	
	public void testHelp() {
		assertOutErrRegExp("(?ms)^usage:.*Prints text that represents given number\n$", "^$", "-h");
		assertOutErrRegExp("(?ms)^usage:.*Prints text that represents given number\n$", "^$", "--help");
		assertOutErrRegExp("(?ms)^usage:.*Prints text that represents given number\n$", "^$", "--help", "-h");
	}
	
	@Test
	public void testUsage() {
		assertOutErrRegExp("(?ms)^usage:.*\n$", "^$", 1, 1);
	}

	@Test
	public void testOneArg() {
		assertOutErr("vienas\n", "", 1);
		assertOutErr("šimtas dešimt\n", "", 110);
		
		assertOutErr("vienas", "", "-n", 1);
		assertOutErr("šimtas dešimt", "", "--no-newline", 110);
		
		assertOutErr("vieno", "", "-f", "K", "-n", 1);
		
	}
	
	public class OutAndErr {
		
		private String out;
		private String err;
		
		public OutAndErr(String out, String err) {
			this.out = out;
			this.err = err;			
		}
		
		public String getOut() {
			return out;
		}
		
		public String getErr() {
			return err;
		}
		
	}
	
	public static class DecoratingOutWriter extends OutputStreamWriter {
		
		private StringBuilder text = new StringBuilder();
		public DecoratingOutWriter(OutputStream outputStream) {
			super(outputStream);
		}
		
		@Override
		public void write(char[] cbuf) throws IOException {
			text.append(cbuf);
			super.write(cbuf);
		}
		
		@Override
		public void write(String str) throws IOException {
			text.append(str);
			super.write(str);
		}
		
		public String getText() {
			return text.toString();
		}
	}

}
