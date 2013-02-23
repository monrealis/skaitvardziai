package eu.vytenis.skaitvardziai.app;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DecoratingWriter extends OutputStreamWriter {		
	private StringBuilder text = new StringBuilder();
	
	public DecoratingWriter(OutputStream outputStream) {
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