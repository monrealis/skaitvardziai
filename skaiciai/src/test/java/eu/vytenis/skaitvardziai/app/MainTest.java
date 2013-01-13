package eu.vytenis.skaitvardziai.app;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.junit.Assert;
import org.junit.Test;

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
			}
			return new OutAndErr(out.getText(), err.getText());
		} finally {
			Main.setOut(null);
			Main.setErr(null);
		}
	}
	
	private void assertOutErr(String out, String err, Object... args) {
		OutAndErr oe = main(1);
 		Assert.assertEquals(out, oe.getOut());
		Assert.assertEquals(err, oe.getErr());
	}

	@Test
	public void testOneArg() {
		assertOutErr("vienas\n", "", 1);
		
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
