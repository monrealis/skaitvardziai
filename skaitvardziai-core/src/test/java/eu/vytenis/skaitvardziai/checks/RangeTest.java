package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeTest {

	@Test
	public void testToString() {
		assertEquals("(-infinity; infinity)", new Range<Integer>(null, false, null, false).toString());
		assertEquals("(-8; 8)", new Range<Integer>(-8, false, 8, false).toString());
		assertEquals("[-15; 15]", new Range<Integer>(-15, true, 15, true).toString());
	}
	
	@Test
	public void testSatisfiesMin() {
		Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
		assertEquals(false, closed.isSatisfiesMin(-2));
		assertEquals(true, closed.isSatisfiesMin(-1));
		assertEquals(true, closed.isSatisfiesMin(0));
		
		Range<Integer> open = new Range<Integer>(-1, false, 1, false);
		assertEquals(false, open.isSatisfiesMin(-2));
		assertEquals(false, open.isSatisfiesMin(-1));
		assertEquals(true, open.isSatisfiesMin(0));
		
		Range<Integer> infinity = new Range<Integer>(null, false, null, false);
		assertEquals(true, infinity.isSatisfiesMin(0));
	}
	
	@Test
	public void testSatisfiesMax() {
		Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
		assertEquals(true, closed.isSatisfiesMax(0));
		assertEquals(true, closed.isSatisfiesMax(1));
		assertEquals(false, closed.isSatisfiesMax(2));
		
		Range<Integer> open = new Range<Integer>(-1, false, 1, false);
		assertEquals(true, open.isSatisfiesMax(0));
		assertEquals(false, open.isSatisfiesMax(1));
		assertEquals(false, open.isSatisfiesMax(2));
		
		Range<Integer> infinity = new Range<Integer>(null, false, null, false);
		assertEquals(true, infinity.isSatisfiesMax(0));
	}

}
