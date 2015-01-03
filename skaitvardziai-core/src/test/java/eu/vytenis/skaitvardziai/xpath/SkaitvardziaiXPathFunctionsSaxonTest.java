package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

public class SkaitvardziaiXPathFunctionsSaxonTest extends SkaitvardziaiXPathFunctionsTest {
	@Test
	public void testXslt() throws Exception {
		super.testXslt("net.sf.saxon.TransformerFactoryImpl");
	}
}
