package eu.vytenis.skaitvardziai.xpath;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class SkaitvardziaiXPathFunctionsXalanTest extends SkaitvardziaiXPathFunctionsTest {
	public SkaitvardziaiXPathFunctionsXalanTest(String transformerFactoryClassName) {
		super(transformerFactoryClassName, new SourceTransformer() {
			public String transform(String input) {
				return input.replaceAll("java:", "xalan://");
			}
		});
	}

	@Parameterized.Parameters(name = "{index}: {0}")
	public static List<Object[]> x() {
		List<Object[]> r = new ArrayList<Object[]>();
		r.add(createParams("org.apache.xalan.processor.TransformerFactoryImpl"));
		r.add(createParams("org.apache.xalan.xsltc.trax.TransformerFactoryImpl"));
		r.add(createParams("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl"));
		return r;
	}

	private static Object[] createParams(String transformerFactoryClassName) {
		return new Object[] {transformerFactoryClassName};
	}

	@Override
	@Test
	public void testXslt() throws Exception {
		super.testXslt();
	}
}
