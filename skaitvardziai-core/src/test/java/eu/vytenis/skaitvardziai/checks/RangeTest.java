package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeTest {
	private static final Range<Integer> closed = new Range<Integer>(-1, Inclusive.Inclusive, 2, Inclusive.Inclusive);
	private static final Range<Integer> open = new Range<Integer>(-1, Inclusive.Exclusive, 2, Inclusive.Exclusive);
	private static final Range<Integer> all = new Range<Integer>(null, Inclusive.Exclusive, null, Inclusive.Exclusive);

	@Test
	public void toStringFormatsRange() {
		assertEquals("(-infinity; infinity)", all.toString());
		assertEquals("(-1; 2)", open.toString());
		assertEquals("[-1; 2]", closed.toString());
	}
}
