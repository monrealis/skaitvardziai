package eu.vytenis.skaitvardziai.app;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import eu.vytenis.skaitvardziai.checks.Checks;

public class DecoratingStream extends FilterOutputStream {
	private Mode mode;
	private ByteArrayOutputStream output = new ByteArrayOutputStream();

	public DecoratingStream(OutputStream outputStream, Mode mode) {
		super(outputStream);
		Checks.checkNotNull("mode", mode);
		this.mode = mode;
	}

	@Override
	public void write(int b) throws IOException {
		output.write(b);
		if (mode == Mode.CollectAndWriteToSink) {
			super.write(b);
		}
	}

	public byte[] getCollectedBytes() {
		return output.toByteArray();
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return mode;
	}

	public enum Mode {
		CollectOnly, CollectAndWriteToSink
	}

}
