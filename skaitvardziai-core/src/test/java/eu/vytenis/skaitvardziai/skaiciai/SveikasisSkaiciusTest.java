package eu.vytenis.skaitvardziai.skaiciai;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

import eu.vytenis.skaitvardziai.util.Numbers;

public class SveikasisSkaiciusTest {

	@Test
	public void testGetReiksme() {
		BigInteger b = new BigInteger("-10");
		assertEquals(b, new SveikasisSkaicius(b).getReiksme());
		assertEquals(b, new SveikasisSkaicius(new BigInteger(b.toString())).getReiksme());
		assertEquals(b, new SveikasisSkaicius(b.toString()).getReiksme());
		assertEquals(b, new SveikasisSkaicius(b.longValue()).getReiksme());
	}
	
	@Test
	public void testBigNumber() {
		new SveikasisSkaicius(Numbers.BILLION).toString();
		
	}

}
