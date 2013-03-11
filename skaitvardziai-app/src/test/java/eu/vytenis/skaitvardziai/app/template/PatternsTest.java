package eu.vytenis.skaitvardziai.app.template;

import org.junit.Assert;
import org.junit.Test;

public class PatternsTest {

	@Test
	public void testSimple() {
		Assert.assertEquals("\\Qtest\\E", Patterns.quote("test"));
		Assert.assertEquals("\\Q\\\\E", Patterns.quote("\\"));
		Assert.assertEquals("\\Q\\E\\\\E\\Q\\E", Patterns.quote("\\E"));
	}

}
