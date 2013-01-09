package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

/**
 * Saxon XSLT procesoriaus {@link BaseSkaiciusXPathFunctionsTest} testas.
 */
public class SkaiciusXPathFunctionsSaxonTest extends BaseSkaiciusXPathFunctionsTest {
	
	public SkaiciusXPathFunctionsSaxonTest() {
		super("net.sf.saxon.TransformerFactoryImpl");
	}
	
	
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}
	

}
