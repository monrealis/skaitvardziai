package eu.vytenis.skaitvardziai.app.io;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;

public class ExpectedOut {
	public static ExpectedOut EMPTY = new ExpectedOut("");

	private String encoding;
	private String text;
	
	public ExpectedOut(String text) {
		this.text = text;
	}
	
	public ExpectedOut(String text, String encoding) {
		this.text = text;
		this.encoding = encoding;
	}
	
	public String getText() {
		return text;
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public byte[] getTextEncoded() {
		try {
			return getText().getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new SkaitvardziaiIOException(e);
		}
	}
	
	public Pattern toPattern() {
		return Pattern.compile(text);
	}
	
	@Override
	public String toString() {
		return text;
	}
}
