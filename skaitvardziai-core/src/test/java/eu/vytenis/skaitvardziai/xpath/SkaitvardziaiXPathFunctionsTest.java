package eu.vytenis.skaitvardziai.xpath;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class SkaitvardziaiXPathFunctionsTest {
	private String defaultXsltResourceName = "saxon-transform.xsl";

	protected TransformerFactory getTransformerFactory(String transformerFactoryClassName) throws Exception {
		return (TransformerFactory) Class.forName(transformerFactoryClassName).newInstance();
	}

	protected Source getXsltSource() {
		return new StreamSource(new StringReader(getXsltText()));
	}
	
	protected String getXsltText() {
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
	protected void testXslt(String transformerFactoryClassName) throws Exception {
		StreamSource input = new StreamSource(new StringReader("<root />"));
		StringWriter w = new StringWriter();
		getTransformerFactory(transformerFactoryClassName).newTransformer(getXsltSource()).transform(input, new StreamResult(w));
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
}
