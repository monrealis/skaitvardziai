package eu.vytenis.skaitvardziai.checks;

import junit.framework.Assert;

import org.junit.Test;

public class RangeEndTest {

	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testRangeEndInvalid() {
		new RangeEnd(null, true);
	}
	
	@Test
	public void testGetLeftString() {
		Assert.assertEquals("(-infinity", new RangeEnd(null, false).getLeftString());
		Assert.assertEquals("(-10", new RangeEnd(-10, false).getLeftString());
		Assert.assertEquals("(10", new RangeEnd(10, false).getLeftString());
		Assert.assertEquals("[-20", new RangeEnd(-20, true).getLeftString());
		Assert.assertEquals("[20", new RangeEnd(20, true).getLeftString());
	}
	
	


	@Test
	public void testGetRightString() {
		Assert.assertEquals("infinity)", new RangeEnd(null, false).getRightString());
		Assert.assertEquals("-10)", new RangeEnd(-10, false).getRightString());
		Assert.assertEquals("10)", new RangeEnd(10, false).getRightString());
		Assert.assertEquals("-20]", new RangeEnd(-20, true).getRightString());
		Assert.assertEquals("20]", new RangeEnd(20, true).getRightString());
	}

}
