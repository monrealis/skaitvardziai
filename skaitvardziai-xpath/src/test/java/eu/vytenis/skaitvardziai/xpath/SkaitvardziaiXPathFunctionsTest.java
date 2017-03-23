package eu.vytenis.skaitvardziai.xpath;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

// Patikrina, ar įvykdyta XSL transformaciją suformuoja tokį XML'ą, kokį reikia.
// Po transformacijos gautas tekstas turi būti toks: faktinis_tekstas_1 : reikalingas_tekstas_1 ; faktinis_tekstas_2 : reikalingas_tekstas_2 ; ...
@RunWith(Parameterized.class)
public class SkaitvardziaiXPathFunctionsTest {
	private final String defaultXsltResourceName = "saxon-transform.xsl";
	private final String transformerFactoryClassName;
	private final SourceTransformer sourceTransformer;
	private String invalidLines = "";
	private String expectedValues = "";
	private String actualValues = "";

	public SkaitvardziaiXPathFunctionsTest(String transformerFactoryClassName, SourceTransformer sourceTransformer) {
		this.transformerFactoryClassName = transformerFactoryClassName;
		this.sourceTransformer = sourceTransformer;
	}

	@Test
	public void allLinesArePairsOfActualAndExpected() throws TransformerException {
		collect();
		assertEquals("", invalidLines);
	}

	@Test
	public void xpathFunctionCallsReturnExpected() throws TransformerException {
		collect();
		assertEquals(expectedValues, actualValues);
	}

	private void collect() {
		String output = transform();
		for (String line : output.split("\\s*;\\s*"))
			collectLine(line, line.split("\\s*=\\s*"));
	}

	private void collectLine(String line, String[] actualExpected) {
		if (actualExpected.length != 2) {
			invalidLines += line + "\n";
			return;
		}
		actualValues += actualExpected[0] + "\n";
		expectedValues += actualExpected[1] + "\n";
	}

	private String transform() {
		try {
			return tryTransform();
		} catch (TransformerException e) {
			throw new RuntimeException(e);
		}
	}

	private String tryTransform() throws TransformerException {
		StreamSource input = new StreamSource(new StringReader("<root />"));
		StringWriter w = new StringWriter();
		createFactory().newTransformer(getXsltSource()).transform(input, new StreamResult(w));
		return w.toString().trim();
	}

	protected TransformerFactory createFactory() {
		try {
			return tryCreateFactory();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private TransformerFactory tryCreateFactory() throws Exception {
		return (TransformerFactory) Class.forName(transformerFactoryClassName).newInstance();
	}

	protected Source getXsltSource() {
		return new StreamSource(new StringReader(transformXsltText()));
	}

	protected final String transformXsltText() {
		return sourceTransformer.transform(getXsltText());
	}

	private String getXsltText() {
		try {
			return tryGetXsltText();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private String tryGetXsltText() throws IOException {
		InputStream is = SkaitvardziaiXPathFunctionsTest.class.getResourceAsStream(defaultXsltResourceName);
		byte[] bytes = new byte[is.available()];
		assertEquals(bytes.length, is.read(bytes));
		is.close();
		return new String(bytes, "UTF-8");
	}

	@Parameterized.Parameters(name = "{index}: {0}")
	public static List<Object[]> testCases() {
		List<Object[]> r = new ArrayList<Object[]>();
		r.add(createTestCase("net.sf.saxon.TransformerFactoryImpl", new DoNothingTransformer()));
		r.add(createTestCase("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl", new XalanSourceTransformer()));
		r.add(createTestCase("org.apache.xalan.processor.TransformerFactoryImpl", new XalanSourceTransformer()));
		r.add(createTestCase("org.apache.xalan.xsltc.trax.TransformerFactoryImpl", new XalanSourceTransformer()));
		r.add(createTestCase("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl", new XalanSourceTransformer()));
		return r;
	}

	private static Object[] createTestCase(String transformerFactoryClassName, SourceTransformer sourceTransformer) {
		return new Object[] {transformerFactoryClassName, sourceTransformer};
	}

	private static final class XalanSourceTransformer implements SourceTransformer {
		public String transform(String input) {
			return input.replaceAll("java:", "xalan://");
		}
	}

	private static class DoNothingTransformer implements SourceTransformer {
		public String transform(String input) {
			return input;
		}
	}

	private static interface SourceTransformer {
		String transform(String input);
	}
}
