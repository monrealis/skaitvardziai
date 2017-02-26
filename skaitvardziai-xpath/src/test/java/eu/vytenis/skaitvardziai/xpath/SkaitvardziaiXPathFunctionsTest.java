package eu.vytenis.skaitvardziai.xpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public abstract class SkaitvardziaiXPathFunctionsTest {
	private final String defaultXsltResourceName = "saxon-transform.xsl";
	private final String transformerFactoryClassName;
	private final SourceTransformer sourceTransformer;

	public SkaitvardziaiXPathFunctionsTest(String transformerFactoryClassName, SourceTransformer sourceTransformer) {
		this.transformerFactoryClassName = transformerFactoryClassName;
		this.sourceTransformer = sourceTransformer;
	}

	protected TransformerFactory createFactory() throws Exception {
		return (TransformerFactory) Class.forName(transformerFactoryClassName).newInstance();
	}

	protected Source getXsltSource() {
		return new StreamSource(new StringReader(getXsltText()));
	}

	protected final String getXsltText() {
		return sourceTransformer.transform(tryGetXsltText());
	}

	private String tryGetXsltText() {
		try {
			return getXsltTextThrowsExc();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String getXsltTextThrowsExc() throws IOException {
		InputStream is = SkaitvardziaiXPathFunctionsTest.class.getResourceAsStream(defaultXsltResourceName);
		byte[] bytes = new byte[is.available()];
		assertEquals(bytes.length, is.read(bytes));
		is.close();
		return new String(bytes, "UTF-8");
	}

	// Patikrina, ar įvykdyta XSL transformaciją suformuoja tokį XML'ą, kokį reikia.
	// Po transformacijos gautas tekstas turi būti toks: faktinis_tekstas_1 : reikalingas_tekstas_1 ; faktinis_tekstas_2 : reikalingas_tekstas_2 ; ...
	protected void testXslt() throws Exception {
		StreamSource input = new StreamSource(new StringReader("<root />"));
		StringWriter w = new StringWriter();
		createFactory().newTransformer(getXsltSource()).transform(input, new StreamResult(w));
		String output = w.toString().trim();
		String[] lines = output.split("\\s*;\\s*");
		List<String> invalidLines = new ArrayList<String>();
		for (String line : lines) {
			String[] actualExpected = line.split("\\s*=\\s*");
			if (actualExpected.length != 2) {
				invalidLines.add(line);
			} else {
				String actual = actualExpected[0];
				String expected = actualExpected[1];
				if (!actual.equals(expected)) {
					invalidLines.add(line);
				}
			}
		}
		assertEquals(new ArrayList<String>(), invalidLines);
	}

	public static class DoNothingTransfer implements SourceTransformer {
		public String transform(String input) {
			return input;
		}
	}

	public static interface SourceTransformer {
		String transform(String input);
	}
}
