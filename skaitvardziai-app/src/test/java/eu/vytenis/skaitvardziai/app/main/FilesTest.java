package eu.vytenis.skaitvardziai.app.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.exc.SkaitvardziaiIOException;
import eu.vytenis.skaitvardziai.app.io.ExpectedOut;

public class FilesTest extends AppTest {

	@Test
	public void echoToFileSucceeds() {
		String out = generateFilename(Direction.Output, "echoToFileSucceeds");
		main(10, 20, "-o", out);
		String output = tryLoadFile(out, Charset.defaultCharset());
		Assert.assertEquals("dešimt\ndvidešimt\n", output);
	}
	
	@Test
	public void echoFromFileSucceeds() {
		String in = generateFilename(Direction.Input, "echoFromFileSucceeds");
		trySaveFile(in, Charset.defaultCharset(), "10\n20\n");
		assertOut(new ExpectedOut("dešimt\ndvidešimt\n"), "-i", in);
	}

	@Test
	public void echoFromFileToFileSucceeds() {
		String in = generateFilename(Direction.Input, "echoFromFileToFileSucceeds");
		trySaveFile(in, Charset.defaultCharset(), "10\n20\n");
		
		String out = generateFilename(Direction.Output, "echoFromFileToFileSucceeds");
		main(10, 20, "-i", in, "-o", out);
		String output = tryLoadFile(out, Charset.defaultCharset());
		
		Assert.assertEquals("dešimt\ndvidešimt\n", output);
	}
	
	@Test
	public void templateFromFileToFileSucceeds() {
		String in = generateFilename(Direction.Input, "templateFromFileToFileSucceeds");
		trySaveFile(in, Charset.defaultCharset(), "Šiandien yra sausio ${sveikasis(1, 'Kl,MG,Iv')}.");
		
		String out = generateFilename(Direction.Output, "templateFromFileToFileSucceeds");
		main(10, 20, "-t", "-i", in, "-o", out);
		String output = tryLoadFile(out, Charset.defaultCharset());
		
		Assert.assertEquals("Šiandien yra sausio pirmoji.", output);		
	}
	
	
	private String generateFilename(Direction direction, String text) {
		return String.format("../skaitvardziai-app/target/test-%s-%s-%s.txt", direction, text, UUID.randomUUID());
	}
	
	private String tryLoadFile(String filename, Charset charset) {
		try {
			return loadFile(filename, charset);
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}		
	}
	
	private String loadFile(String filename, Charset charset) throws IOException {
		InputStream is = new FileInputStream(filename);
		byte[] bytes = new byte[is.available()];
		is.read(bytes);
		is.close();
		return new String(bytes, charset);
	}
	
	private void trySaveFile(String filename, Charset charset, String content) {
		try {
			saveFile(filename, charset, content);
		} catch (IOException e) {
			throw new SkaitvardziaiIOException(e);
		}		
	}
	
	private void saveFile(String filename, Charset charset, String content) throws IOException {
		OutputStream os = new FileOutputStream(filename);
		OutputStreamWriter w = new OutputStreamWriter(os, charset);
		w.write(content);
		w.close();
	}
	
	public enum Direction {
		Input, Output;
	}

}
