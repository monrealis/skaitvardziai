package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RangeTest {
	private Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
	private Range<Integer> open = new Range<Integer>(-1, false, 1, false);
	private Range<Integer> all = new Range<Integer>(null, false, null, false);

	@Test
	public void toStringFormatsRange() {
		assertEquals("(-infinity; infinity)", all.toString());
		assertEquals("(-1; 1)", open.toString());
		assertEquals("[-1; 1]", closed.toString());
	}

	@Test
	public void testSatisfiesMin() {
		assertFalse(closed.isSatisfiesMin(-2));
		assertTrue(closed.isSatisfiesMin(-1));
		assertTrue(closed.isSatisfiesMin(0));
		assertFalse(open.isSatisfiesMin(-2));
		assertFalse(open.isSatisfiesMin(-1));
		assertTrue(open.isSatisfiesMin(0));
		assertTrue(all.isSatisfiesMin(0));
	}

	@Test
	public void testSatisfiesMax() {
		assertTrue(closed.isSatisfiesMax(0));
		assertTrue(closed.isSatisfiesMax(1));
		assertFalse(closed.isSatisfiesMax(2));
		assertTrue(open.isSatisfiesMax(0));
		assertFalse(open.isSatisfiesMax(1));
		assertFalse(open.isSatisfiesMax(2));
		assertTrue(all.isSatisfiesMax(0));
	}
}
