package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;

import org.junit.Test;

public class ChecksTest {
	private static final String NAME = "name";

	@Test
	public void testCheckNotNull_Succeeds() {
		Checks.checkNotNull(NAME, 0);
	}

	@Test(expected = Checks.ObjectNullException.class)
	public void testCheckNotNull_Fails() {
		Checks.checkNotNull(NAME, null);
	}

	@Test
	public void testCheckInclusive_Succeeds() {
		Checks.checkInclusive(NAME, toBi(-10), toBi(-11), toBi(-9));
		Checks.checkInclusive(NAME, toBi(-11), toBi(-11), toBi(-9));
		Checks.checkInclusive(NAME, toBi(-9), toBi(-11), toBi(-9));
	}

	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testCheckInclusive_FailsMinusInfinity() {
		Checks.checkInclusive(NAME, toBi(-9), null, toBi(-9));
	}

	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testCheckInclusive_FailsPlusInfinity() {
		Checks.checkInclusive(NAME, toBi(-9), toBi(-9), null);
	}

	@Test(expected = Checks.InvalidRangeException.class)
	public void testCheckInclusive_FailsLess() {
		Checks.checkInclusive(NAME, toBi(-11), toBi(-10), toBi(-9));
	}

	@Test(expected = Checks.InvalidRangeException.class)
	public void testCheckInclusive_FailsMore() {
		Checks.checkInclusive(NAME, toBi(-9), toBi(-11), toBi(-10));
	}

	@Test
	public void testCheckExclusive_Succeeds() {
		Checks.checkExclusive(NAME, toBi(-10), toBi(-11), toBi(-9));
		Checks.checkExclusive(NAME, toBi(-10), null, toBi(-9));
		Checks.checkExclusive(NAME, toBi(-10), null, null);
	}

	@Test(expected = Checks.InvalidRangeException.class)
	public void testCheckExclusive_FailsLess() {
		Checks.checkExclusive(NAME, toBi(-11), toBi(-11), toBi(-9));
	}

	@Test(expected = Checks.InvalidRangeException.class)
	public void testCheckExclusive_FailsMore() {
		Checks.checkExclusive(NAME, toBi(-9), toBi(-11), toBi(-9));
	}

	@Test
	public void testCheckMinInclusive_Succeeds() {
		Checks.checkMinInclusive(NAME, toBi(-10), toBi(-10), toBi(-9));
		Checks.checkMinInclusive(NAME, toBi(-10), toBi(-10), null);
	}

	@Test(expected = Checks.InvalidRangeException.class)
	public void testCheckMinInclusive_FailsLess() {
		Checks.checkExclusive(NAME, toBi(-11), toBi(-10), toBi(-9));
	}

	@Test
	public void testEqual_Succeeds() {
		Checks.checkEqual(NAME, NAME, "a", "a");
	}

	@Test(expected = Checks.NotEqualException.class)
	public void testEqual_Fails() {
		Checks.checkEqual(NAME, NAME, "a", "b");
	}

	private BigInteger toBi(Number number) {
		return new BigInteger(number.toString());
	}
}
