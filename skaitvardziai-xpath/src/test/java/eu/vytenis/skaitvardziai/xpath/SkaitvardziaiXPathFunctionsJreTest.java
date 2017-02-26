package eu.vytenis.skaitvardziai.xpath;

import org.junit.Test;

public class SkaitvardziaiXPathFunctionsJreTest extends SkaitvardziaiXPathFunctionsTest {
	public SkaitvardziaiXPathFunctionsJreTest() {
		super("com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl", new SourceTransformer() {
			public String transform(String input) {
				return input.replaceAll("java:", "xalan://");
			}
		});
	}

	@Override
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}
}
