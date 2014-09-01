package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeEndTest {

	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testRangeEndInvalid() {
		new RangeEnd<Integer>(null, true);
	}
	
	@Test
	public void testGetLeftString() {
		assertEquals("(-infinity", new RangeEnd<Integer>(null, false).getLeftString());
		assertEquals("(-10", new RangeEnd<Integer>(-10, false).getLeftString());
		assertEquals("(10", new RangeEnd<Integer>(10, false).getLeftString());
		assertEquals("[-20", new RangeEnd<Integer>(-20, true).getLeftString());
		assertEquals("[20", new RangeEnd<Integer>(20, true).getLeftString());
	}
	
	


	@Test
	public void testGetRightString() {
		assertEquals("infinity)", new RangeEnd<Integer>(null, false).getRightString());
		assertEquals("-10)", new RangeEnd<Integer>(-10, false).getRightString());
		assertEquals("10)", new RangeEnd<Integer>(10, false).getRightString());
		assertEquals("-20]", new RangeEnd<Integer>(-20, true).getRightString());
		assertEquals("20]", new RangeEnd<Integer>(20, true).getRightString());
	}

}
