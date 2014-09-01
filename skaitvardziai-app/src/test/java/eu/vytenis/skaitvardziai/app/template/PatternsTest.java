package eu.vytenis.skaitvardziai.app.template;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatternsTest {

	@Test
	public void testSimple() {
		assertEquals("\\Qtest\\E", Patterns.quote("test"));
		assertEquals("\\Q\\\\E", Patterns.quote("\\"));
		assertEquals("\\Q\\E\\\\E\\Q\\E", Patterns.quote("\\E"));
	}

}
