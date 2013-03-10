package eu.vytenis.skaitvardziai.app.io;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class DecoratingStream extends FilterOutputStream {
	private ByteArrayOutputStream output = new ByteArrayOutputStream();
	private String encoding;
	
	public DecoratingStream(OutputStream outputStream, String encoding) {
		super(outputStream);
		this.encoding = encoding;
	}

	@Override
	public void write(int b) throws IOException {
		output.write(b);
		super.write(b);
	}

	public String getText() {
		try {
			return encoding != null ? new String(getBytes(), encoding) : new String(getBytes());
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
	
	public byte[] getBytes() {
		return output.toByteArray();
	}
}