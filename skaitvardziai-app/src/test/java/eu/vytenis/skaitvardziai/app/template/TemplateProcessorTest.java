package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;

public class TemplateProcessorTest extends AppTest {
	private String startTag;
	private String endTag;

	@Before
	public void before() {
		startTag = TemplateProcessor.DEFAULT_START_TAG;
		endTag = TemplateProcessor.DEFAULT_END_TAG;
	}

	@Test
	public void testCollectFragments() {
		String input;
		Object[] output;
		input = "a${}${'a'}b\nc";
		output = new Object[] {ss("a"), fs(""), fs("'a'"), ss("b\nc")};
		assertParsedFragments(input, output);
		input = "${c}";
		output = new Object[] {fs("c")};
		assertParsedFragments(input, output);
		input = "${}${b}${c}";
		output = new Object[] {fs(""), fs("b"), fs("c")};
		assertParsedFragments(input, output);
		input = "";
		output = new Object[] {};
		assertParsedFragments(input, output);
		input = "a\nb";
		output = new Object[] {ss("a\nb")};
		assertParsedFragments(input, output);
		input = "a${b}";
		output = new Object[] {ss("a"), fs("b")};
		assertParsedFragments(input, output);
		input = "a${b}c";
		output = new Object[] {ss("a"), fs("b"), ss("c")};
		assertParsedFragments(input, output);
		input = "${a}b";
		output = new Object[] {fs("a"), ss("b")};
		assertParsedFragments(input, output);
	}

	@Test
	public void testCollectFragmentsUsingOtherTags() {
		String input;
		Object[] output;
		startTag = "<?";
		endTag = "?>";
		input = "+<? c ?>-";
		output = new Object[] {ss("+"), fs(" c "), ss("-")};
		assertParsedFragments(input, output);
	}

	private void assertParsedFragments(String text, Object[] expected) {
		TemplateProcessor p = new TemplateProcessor(startTag, endTag, text, systemIo);
		p.createPattern();
		p.collectFragments();
		assertArrayEquals(expected, p.getFragments().toArray());
	}

	private StringSource ss(String text) {
		return new StringSource(text);
	}

	private MethodInvocationSource fs(String unparsedText) {
		return new MethodInvocationSource(unparsedText);
	}

}
