package eu.vytenis.skaitvardziai.xpath;

import java.io.IOException;

import org.junit.Test;


/**
 * XALAN XSLT procesoriaus {@link BaseSkaiciusXPathFunctionsTest} testas.
 */
public class SkaiciusXPathFunctionsXalanTest extends BaseSkaiciusXPathFunctionsTest {
	
	@Override
	protected String getXsltText() throws IOException {
		String r = super.getXsltText();
		r = r.replaceAll("java:", "xalan://");
		return r;
	}
	
	@Test
	public void testXsltProcessor() throws Exception {
		super.testXslt("org.apache.xalan.processor.TransformerFactoryImpl");
	}
	
	@Test
	public void testXsltTraxProcessor() throws Exception {
		super.testXslt("org.apache.xalan.xsltc.trax.TransformerFactoryImpl");
	}
	
	@Test
	public void testXsltTraxSmartProcessor() throws Exception {
		super.testXslt("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl");
	}


}
