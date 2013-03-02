package eu.vytenis.skaitvardziai.checks;

import org.junit.Assert;

import org.junit.Test;

public class RangeTest {

	@Test
	public void testToString() {
		Assert.assertEquals("(-infinity; infinity)", new Range<Integer>(null, false, null, false).toString());
		Assert.assertEquals("(-8; 8)", new Range<Integer>(-8, false, 8, false).toString());
		Assert.assertEquals("[-15; 15]", new Range<Integer>(-15, true, 15, true).toString());
	}
	
	@Test
	public void testSatisfiesMin() {
		Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
		Assert.assertEquals(false, closed.isSatisfiesMin(-2));
		Assert.assertEquals(true, closed.isSatisfiesMin(-1));
		Assert.assertEquals(true, closed.isSatisfiesMin(0));
		
		Range<Integer> open = new Range<Integer>(-1, false, 1, false);
		Assert.assertEquals(false, open.isSatisfiesMin(-2));
		Assert.assertEquals(false, open.isSatisfiesMin(-1));
		Assert.assertEquals(true, open.isSatisfiesMin(0));
		
		Range<Integer> infinity = new Range<Integer>(null, false, null, false);
		Assert.assertEquals(true, infinity.isSatisfiesMin(0));
	}
	
	@Test
	public void testSatisfiesMax() {
		Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
		Assert.assertEquals(true, closed.isSatisfiesMax(0));
		Assert.assertEquals(true, closed.isSatisfiesMax(1));
		Assert.assertEquals(false, closed.isSatisfiesMax(2));
		
		Range<Integer> open = new Range<Integer>(-1, false, 1, false);
		Assert.assertEquals(true, open.isSatisfiesMax(0));
		Assert.assertEquals(false, open.isSatisfiesMax(1));
		Assert.assertEquals(false, open.isSatisfiesMax(2));
		
		Range<Integer> infinity = new Range<Integer>(null, false, null, false);
		Assert.assertEquals(true, infinity.isSatisfiesMax(0));
	}

}
