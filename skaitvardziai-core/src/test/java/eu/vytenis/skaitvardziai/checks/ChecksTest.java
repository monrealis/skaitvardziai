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
