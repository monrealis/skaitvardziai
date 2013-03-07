package eu.vytenis.skaitvardziai.app.main;

import java.util.regex.Pattern;

public class Out {
	public static Out EMPTY = new Out("");

	private String text;
	
	public Out(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public Pattern toPattern() {
		return Pattern.compile(text);
	}
	
	@Override
	public String toString() {
		return text;
	}
}
