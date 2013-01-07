package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;


/**
 * Standartinio OpenJDK/OracleJDK XSLT procesoriaus {@link BaseSkaiciusXPathFunctionsTest} testas.
 */
public class SkaiciusXPathFunctionsJreTest extends BaseSkaiciusXPathFunctionsTest {
	
	public SkaiciusXPathFunctionsJreTest() {
		super("xalan-transform.xsl", "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
	}
	
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}

}
