package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.app.AppTest;
import eu.vytenis.skaitvardziai.app.io.SystemIo;

// TODO 2017-04-07 monrealis: Split TemplateProcessor to two classes
// or refactor in a nother way
// TODO 2017-04-10 monrealis: don't extend AppTest
public class TemplateProcessorTest extends AppTest {
	private String startTag;
	private String endTag;
	private String input;
	private Object[] outputs;

	@Before
	public void before() {
		startTag = TemplateProcessor.DEFAULT_START_TAG;
		endTag = TemplateProcessor.DEFAULT_END_TAG;
	}

	@Test
	public void empty() {
		input = "";
		outputs = new Object[] {};
		assertParsedFragments();
	}

	@Test
	public void method() {
		input = "${c}";
		outputs = new Object[] {method("c")};
		assertParsedFragments();
	}

	@Test
	public void methods() {
		input = "${}${b}${c}";
		outputs = new Object[] {method(""), method("b"), method("c")};
		assertParsedFragments();
	}

	@Test
	public void string() {
		input = "a\nb";
		outputs = new Object[] {string("a\nb")};
		assertParsedFragments();
	}

	@Test
	public void metohdLast() {
		input = "a${b}";
		outputs = new Object[] {string("a"), method("b")};
		assertParsedFragments();
	}

	@Test
	public void methodFirst() {
		input = "${a}b";
		outputs = new Object[] {method("a"), string("b")};
		assertParsedFragments();
	}

	@Test
	public void methodInTheMiddle() {
		input = "a${b}c";
		outputs = new Object[] {string("a"), method("b"), string("c")};
		assertParsedFragments();
	}

	@Test
	public void methodsInTheMiddle() {
		input = "a${}${'a'}b\nc";
		outputs = new Object[] {string("a"), method(""), method("'a'"), string("b\nc")};
		assertParsedFragments();
	}

	@Test
	public void nonDefaultTags() {
		startTag = "<?";
		endTag = "?>";
		input = "+<? c ?>-";
		outputs = new Object[] {string("+"), method(" c "), string("-")};
		assertParsedFragments();
	}

	private void assertParsedFragments() {
		systemFiles.setSystemIn(new ByteArrayInputStream(input.getBytes()));
		List<TextSource> fragments = split();
		assertArrayEquals(outputs, fragments.toArray());
	}

	private List<TextSource> split() {
		TemplateSplitter splitter = new TemplateSplitter(startTag, endTag, new SystemIo(systemFiles));
		List<TextSource> fragments = splitter.split();
		return fragments;
	}

	private StringSource string(String text) {
		return new StringSource(text);
	}

	private MethodInvocationSource method(String unparsedText) {
		return new MethodInvocationSource(unparsedText);
	}
}
