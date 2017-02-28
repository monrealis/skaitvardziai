package eu.vytenis.skaitvardziai.checks;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class RangeTest {
	private static final Range<Integer> closed = new Range<Integer>(-1, true, 1, true);
	private static final Range<Integer> open = new Range<Integer>(-1, false, 1, false);
	private static final Range<Integer> all = new Range<Integer>(null, false, null, false);
	private final Range<Integer> range;
	private final int value;
	private final boolean expectedContains;

	@Parameters
	public static List<Object[]> testCases() {
		List<Object[]> cases = new ArrayList<Object[]>();
		cases.add(createCase(closed, -2, false));
		cases.add(createCase(closed, -1, true));
		cases.add(createCase(closed, 0, true));
		cases.add(createCase(closed, 1, true));
		cases.add(createCase(closed, 2, false));
		cases.add(createCase(open, -1, false));
		cases.add(createCase(open, 0, true));
		cases.add(createCase(open, 1, false));
		cases.add(createCase(all, 0, true));
		return cases;
	}

	public RangeTest(Range<Integer> range, int value, boolean expectedContains) {
		this.range = range;
		this.value = value;
		this.expectedContains = expectedContains;
	}

	private static Object[] createCase(Range<Integer> range, int value, boolean expectedContains) {
		return new Object[] {range, value, expectedContains};
	}

	@Test
	public void toStringFormatsRange() {
		assertEquals("(-infinity; infinity)", all.toString());
		assertEquals("(-1; 1)", open.toString());
		assertEquals("[-1; 1]", closed.toString());
	}

	@Test
	public void contains() {
		assertEquals(expectedContains, range.contains(value));
	}
}
