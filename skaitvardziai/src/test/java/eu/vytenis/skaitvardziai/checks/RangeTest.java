package eu.vytenis.skaitvardziai.checks;

import junit.framework.Assert;

import org.junit.Test;

public class RangeTest {

	@Test
	public void testToString() {
		Assert.assertEquals("(-infinity; infinity)", new Range<Integer>(null, false, null, false).toString());
		Assert.assertEquals("(-8; 8)", new Range<Integer>(-8, false, 8, false).toString());
		Assert.assertEquals("[-15; 15]", new Range<Integer>(-15, true, 15, true).toString());
	}

}
