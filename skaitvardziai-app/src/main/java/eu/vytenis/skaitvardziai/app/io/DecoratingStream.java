package eu.vytenis.skaitvardziai.app.io;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DecoratingStream extends FilterOutputStream {
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	
	public DecoratingStream(OutputStream outputStream) {
		super(outputStream);
	}

	@Override
	public void write(int b) throws IOException {
		output.write(b);
		super.write(b);
	}

	public byte[] getBytes() {
		return output.toByteArray();
	}
}