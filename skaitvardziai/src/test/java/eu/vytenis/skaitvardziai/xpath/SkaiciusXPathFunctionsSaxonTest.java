package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

/**
 * Saxon XSLT procesoriaus {@link BaseSkaiciusXPathFunctionsTest} testas.
 */
public class SkaiciusXPathFunctionsSaxonTest extends BaseSkaiciusXPathFunctionsTest {
	
	@Test
	public void testXslt() throws Exception {
		super.testXslt("net.sf.saxon.TransformerFactoryImpl");
	}
	

}
