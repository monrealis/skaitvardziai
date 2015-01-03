package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

public class SkaitvardziaiXPathFunctionsJreTest extends SkaitvardziaiXPathFunctionsTest {
	@Override
	protected String getXsltText() {
		String r = super.getXsltText();
		r = r.replaceAll("java:", "xalan://");
		return r;
	}

	@Test
	public void testXslt() throws Exception {
		super.testXslt("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}
}
