package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeTest {
	private static final Range<Integer> closed = new Range<Integer>(-1, true, 2, true);
	private static final Range<Integer> open = new Range<Integer>(-1, false, 2, false);
	private static final Range<Integer> all = new Range<Integer>(null, false, null, false);

	@Test
	public void toStringFormatsRange() {
		assertEquals("(-infinity; infinity)", all.toString());
		assertEquals("(-1; 2)", open.toString());
		assertEquals("[-1; 2]", closed.toString());
	}
}
