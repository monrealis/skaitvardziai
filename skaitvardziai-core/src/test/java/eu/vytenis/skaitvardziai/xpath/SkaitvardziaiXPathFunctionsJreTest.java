package eu.vytenis.skaitvardziai.xpath;

import java.io.IOException;

import org.junit.Test;

/**
 * Standartinio OpenJDK/OracleJDK XSLT procesoriaus
 * {@link SkaitvardziaiXPathFunctionsTest} testas.
 */
public class SkaitvardziaiXPathFunctionsJreTest extends SkaitvardziaiXPathFunctionsTest {

	@Override
	protected String getXsltText() throws IOException {
		String r = super.getXsltText();
		r = r.replaceAll("java:", "xalan://");
		return r;
	}

	@Test
	public void testXslt() throws Exception {
		super.testXslt("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}

}
