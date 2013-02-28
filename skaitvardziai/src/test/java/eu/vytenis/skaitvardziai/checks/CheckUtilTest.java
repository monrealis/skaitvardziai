package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;
import java.util.Arrays;

import junit.framework.Assert;

import org.junit.Test;

public class CheckUtilTest {

	private static final String NAME = "name";
	
	private Unmodifiable UNMODIFIABLE = new Unmodifiable(true);
	private Unmodifiable MODIFIABLE = new Unmodifiable(false);
	
	@Test
	public void testCheckNotNull_Succeeds() {
		CheckUtil.checkNotNull(NAME, 0);
	}
	
	@Test(expected = CheckUtil.ObjectNullException.class)
	public void testCheckNotNull_Fails() {
		CheckUtil.checkNotNull(NAME, null);
	}

	@Test
	public void testCheckInclusive_Succeeds() {
		CheckUtil.checkInclusive(NAME, toBi(-10), toBi(-11), toBi(-9));
		CheckUtil.checkInclusive(NAME, toBi(-11), toBi(-11), toBi(-9));
		CheckUtil.checkInclusive(NAME, toBi(-9), toBi(-11), toBi(-9));
	}
	
	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testCheckInclusive_FailsMinusInfinity() {
		CheckUtil.checkInclusive(NAME, toBi(-9), null, toBi(-9));		
	}
	
	@Test(expected = RangeEnd.InvalidRangeException.class)
	public void testCheckInclusive_FailsPlusInfinity() {
		CheckUtil.checkInclusive(NAME, toBi(-9), toBi(-9), null);		
	}
	
	@Test(expected = CheckUtil.InvalidRangeException.class)
	public void testCheckInclusive_FailsLess() {
		CheckUtil.checkInclusive(NAME, toBi(-11), toBi(-10), toBi(-9));		
	}
	
	@Test(expected = CheckUtil.InvalidRangeException.class)
	public void testCheckInclusive_FailsMore() {
		CheckUtil.checkInclusive(NAME, toBi(-9), toBi(-11), toBi(-10));		
	}

	@Test
	public void testCheckExclusive_Succeeds() {
		CheckUtil.checkExclusive(NAME, toBi(-10), toBi(-11), toBi(-9));
		CheckUtil.checkExclusive(NAME, toBi(-10), null, toBi(-9));
		CheckUtil.checkExclusive(NAME, toBi(-10), null, null);
	}

	@Test(expected = CheckUtil.InvalidRangeException.class)
	public void testCheckExclusive_FailsLess() {
		CheckUtil.checkExclusive(NAME, toBi(-11), toBi(-11), toBi(-9));
	}
	
	@Test(expected = CheckUtil.InvalidRangeException.class)
	public void testCheckExclusive_FailsMore() {
		CheckUtil.checkExclusive(NAME, toBi(-9), toBi(-11), toBi(-9));
	}
	
	@Test
	public void testCheckMinInclusive_Succeeds() {
		CheckUtil.checkMinInclusive(NAME, toBi(-10), toBi(-10), toBi(-9));
		CheckUtil.checkMinInclusive(NAME, toBi(-10), toBi(-10), null);
	}
	
	@Test(expected = CheckUtil.InvalidRangeException.class)
	public void testCheckMinInclusive_FailsLess() {
		CheckUtil.checkExclusive(NAME, toBi(-11), toBi(-10), toBi(-9));
	}

	@Test
	public void testCheckCanModify_Success() {
		CheckUtil.checkCanModify(NAME, MODIFIABLE);
	}
	
	@Test(expected = CheckUtil.NotModifiableException.class)
	public void testCheckCanModify_Fails() {
		CheckUtil.checkCanModify(NAME, UNMODIFIABLE);
	}

	@Test
	public void testEnsureUnmodifiable() {
		CheckUtil.ensureUnmodifiable(Arrays.asList(UNMODIFIABLE, MODIFIABLE));
		
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
			CheckUtil.checkCanModify("SkaiciusLinksnis", this);
			this.unmodifiable = unmodifiable;			
		}
		
	}
}
