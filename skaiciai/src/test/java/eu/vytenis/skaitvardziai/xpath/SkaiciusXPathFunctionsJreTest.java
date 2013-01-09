package eu.vytenis.skaitvardziai.xpath;

import java.io.IOException;

import org.junit.Test;


/**
 * Standartinio OpenJDK/OracleJDK XSLT procesoriaus {@link BaseSkaiciusXPathFunctionsTest} testas.
 */
public class SkaiciusXPathFunctionsJreTest extends BaseSkaiciusXPathFunctionsTest {
	
	public SkaiciusXPathFunctionsJreTest() {
		super("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}
	
	@Override
	protected String getXsltText() throws IOException {
		String r = super.getXsltText();
		r = r.replaceAll("java:", "xalan://");
		return r;
	}
	
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}

}
