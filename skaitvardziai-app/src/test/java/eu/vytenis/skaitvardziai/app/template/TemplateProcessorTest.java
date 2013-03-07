package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class TemplateProcessorTest {

	@Test
	public void testCollectFragments() {
		String input;
		Object[] output;
		
		input = "a${}${'a'}b\nc";
		output = new Object[] {ss("a"), fs(""), fs("'a'"), ss("b\nc")};
		testParsedFragments(input, output);
		
		input = "${c}";
		output = new Object[] {fs("c")};
		testParsedFragments(input, output);
		
		input = "${}${b}${c}";
		output = new Object[] {fs(""), fs("b"), fs("c")};
		testParsedFragments(input, output);
		
		input = "";
		output = new Object[] {};
		testParsedFragments(input, output);
		
		input = "a\nb";
		output = new Object[] {ss("a\nb")};
		testParsedFragments(input, output);
		
		input = "a${b}";
		output = new Object[] {ss("a"), fs("b")};
		testParsedFragments(input, output);
		
		input = "a${b}c";
		output = new Object[] {ss("a"), fs("b"), ss("c")};
		testParsedFragments(input, output);
		
		input = "${a}b";
		output = new Object[] {fs("a"), ss("b")};
		testParsedFragments(input, output);
	}
	
	private void testParsedFragments(String text, Object[] expected) {
		TemplateProcessor p = new TemplateProcessor();
		p.inputText = text;
		p.createPattern();
		p.collectFragments();		
		assertArrayEquals(expected, p.fragments.toArray());		
	}
	
	
	private StringSource ss(String text) {
		return new StringSource(text);
	}
	
	private FunctionInvocationSource fs(String unparsedText) {
		return new FunctionInvocationSource(unparsedText);
	}
}
