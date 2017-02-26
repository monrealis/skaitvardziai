package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

public class SkaitvardziaiXPathFunctionsSaxonTest extends SkaitvardziaiXPathFunctionsTest {
	public SkaitvardziaiXPathFunctionsSaxonTest() {
		super("net.sf.saxon.TransformerFactoryImpl");
	}

	@Override
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}
}
