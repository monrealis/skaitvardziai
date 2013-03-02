package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Assert;

import org.junit.Test;

public class ChecksTest {

	private static final String NAME = "name";
	
	private Unmodifiable UNMODIFIABLE = new Unmodifiable(true);
	private Unmodifiable MODIFIABLE = new Unmodifiable(false);
	
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
	public void testCheckCanModify_Success() {
		Checks.checkCanModify(NAME, MODIFIABLE);
	}
	
	@Test(expected = Checks.NotModifiableException.class)
	public void testCheckCanModify_Fails() {
		Checks.checkCanModify(NAME, UNMODIFIABLE);
	}

	@Test
	public void testEnsureUnmodifiable() {
		Checks.ensureUnmodifiable(Arrays.asList(UNMODIFIABLE, MODIFIABLE));
		
		Assert.assertEquals(true, UNMODIFIABLE.isUnmodifiable());
		Assert.assertEquals(true, MODIFIABLE.isUnmodifiable());
	}

	private BigInteger toBi(Number number) {
		if (number == null) {
			return null;
		}
		return new BigInteger(number.toString());
	}
	
	private static class Unmodifiable implements UnmodifiableCapable {
		private boolean unmodifiable;
		
		public Unmodifiable(boolean unmodifiable) {
			this.unmodifiable = unmodifiable;
		}

		public boolean isUnmodifiable() {
			return unmodifiable;
		}

		public void setUnmodifiable(boolean unmodifiable) {
			Checks.checkCanModify("SkaiciusLinksnis", this);
			this.unmodifiable = unmodifiable;			
		}
		
	}
}
