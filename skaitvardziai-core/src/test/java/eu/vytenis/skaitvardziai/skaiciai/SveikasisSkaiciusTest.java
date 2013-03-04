package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigInteger;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaitvardziai.util.Numbers;

public class SveikasisSkaiciusTest {

	@Test
	public void testGetReiksme() {
		BigInteger b = new BigInteger("-10");
		Assert.assertEquals(b, new SveikasisSkaicius(b).getReiksme());
		Assert.assertEquals(b, new SveikasisSkaicius(new BigInteger(b.toString())).getReiksme());
		Assert.assertEquals(b, new SveikasisSkaicius(b.toString()).getReiksme());
		Assert.assertEquals(b, new SveikasisSkaicius(b.longValue()).getReiksme());
	}
	
	@Test
	public void testBigNumber() {
		new SveikasisSkaicius(Numbers.BILLION).toString();
		
	}

}
