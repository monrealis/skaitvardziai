package eu.vytenis.skaitvardziai.checks;

import static eu.vytenis.skaitvardziai.checks.Inclusive.Exclusive;
import static eu.vytenis.skaitvardziai.checks.Inclusive.Inclusive;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeEndTest {
	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void throwsExceptionIfInvalid() {
		new RangeEnd<Integer>(null, Inclusive);
	}

	@Test
	public void formatsLeftString() {
		assertEquals("(-infinity", new RangeEnd<Integer>(null, Exclusive).getLeftString());
		assertEquals("(-10", new RangeEnd<Integer>(-10, Exclusive).getLeftString());
		assertEquals("(10", new RangeEnd<Integer>(10, Exclusive).getLeftString());
		assertEquals("[-20", new RangeEnd<Integer>(-20, Inclusive).getLeftString());
		assertEquals("[20", new RangeEnd<Integer>(20, Inclusive).getLeftString());
	}

	@Test
	public void formatsRightString() {
		assertEquals("infinity)", new RangeEnd<Integer>(null, Exclusive).getRightString());
		assertEquals("-10)", new RangeEnd<Integer>(-10, Exclusive).getRightString());
		assertEquals("10)", new RangeEnd<Integer>(10, Exclusive).getRightString());
		assertEquals("-20]", new RangeEnd<Integer>(-20, Inclusive).getRightString());
		assertEquals("20]", new RangeEnd<Integer>(20, Inclusive).getRightString());
	}
}
