package eu.vytenis.skaitvardziai.klasifikatoriai;

import org.junit.Assert;
import org.junit.Test;

import eu.vytenis.skaitvardziai.checks.Checks.NotModifiableException;

public class SkaiciusIrLinksnisTest {

	@Test
	public void testVnsVard() {
		Assert.assertTrue(SkaiciusIrLinksnis.VNS_VARD.isUnmodifiable());
	}
	
	@Test(expected = NotModifiableException.class)
	public void testVnsVardUnmodifiableSkaicius() {
		SkaiciusIrLinksnis.VNS_VARD.setSkaicius(Skaicius.V);
	}
	
	@Test(expected = NotModifiableException.class)
	public void testVnsVardUnmodifiableLinksnis() {
		SkaiciusIrLinksnis.VNS_VARD.setLinksnis(Linksnis.V);		
	}
	
	@Test(expected = NotModifiableException.class)
	public void testVnsVardUnmodifiableUnmodifiable() {
		SkaiciusIrLinksnis.VNS_VARD.setUnmodifiable(false);		
	}

}
