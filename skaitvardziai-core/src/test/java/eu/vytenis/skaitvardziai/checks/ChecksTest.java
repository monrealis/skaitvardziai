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
	public void testCheckMinInclusive_Succeeds() {
		Checks.checkMinInclusive(NAME, toBi(-10), toBi(-10), toBi(-9));
		Checks.checkMinInclusive(NAME, toBi(-10), toBi(-10), null);
	}

	@Test
	public void checkEqual_passes() {
		Checks.checkEqual(NAME, NAME, "a", "a");
		Checks.checkEqual(NAME, NAME, null, null);
	}

	@Test(expected = Checks.NotEqualException.class)
	public void checkEqual_FailsNotEqual() {
		Checks.checkEqual(NAME, NAME, "a", "b");
	}

	@Test(expected = Checks.NotEqualException.class)
	public void checkEqual_FailsOneNull() {
		Checks.checkEqual(NAME, NAME, null, "b");
	}

	private BigInteger toBi(Number number) {
		return new BigInteger(number.toString());
	}
}
