package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TemplateProcessorTest {

	@Test
	public void testCollectFragments() {
		TemplateProcessor p = new TemplateProcessor();
		
		p.inputText = "a${}${'a'}b\nc";
		p.createPattern();
		p.collectFragments();
		
		Object[] expected = {
				new StringSource("a"),
				new FunctionInvocationSource(""),
				new FunctionInvocationSource("'a'"),
				new StringSource("b\nc")
		};
		assertArrayEquals(expected, p.fragments.toArray());
		
	}
}
