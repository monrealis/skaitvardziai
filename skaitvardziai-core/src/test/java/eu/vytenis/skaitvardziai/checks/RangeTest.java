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
		assertFalse(closed.contains(-2));
		assertTrue(closed.contains(-1));
		assertTrue(closed.contains(0));
		assertFalse(open.contains(-2));
		assertFalse(open.contains(-1));
		assertTrue(open.contains(0));
		assertTrue(all.contains(0));
	}

	@Test
	public void testSatisfiesMax() {
		assertTrue(closed.contains(0));
		assertTrue(closed.contains(1));
		assertFalse(closed.contains(2));
		assertTrue(open.contains(0));
		assertFalse(open.contains(1));
		assertFalse(open.contains(2));
		assertTrue(all.contains(0));
	}
}
