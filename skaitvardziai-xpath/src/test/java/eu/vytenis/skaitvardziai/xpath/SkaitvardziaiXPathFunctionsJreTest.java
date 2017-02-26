package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

public class SkaitvardziaiXPathFunctionsJreTest extends SkaitvardziaiXPathFunctionsTest {
	public SkaitvardziaiXPathFunctionsJreTest() {
		super("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}

	@Override
	protected String getXsltText() {
		String r = super.getXsltText();
		r = r.replaceAll("java:", "xalan://");
		return r;
	}

	@Override
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}
}
