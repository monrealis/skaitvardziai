package eu.vytenis.skaitvardziai.klasifikatoriai;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.vytenis.skaitvardziai.checks.Checks.NotModifiableException;

public class SkaiciusIrLinksnisTest {

	@Test
	public void testVnsVard() {
		assertTrue(SkaiciusIrLinksnis.VNS_VARD.isUnmodifiable());
	}

	@Test(expected = NotModifiableException.class)
	public void testVnsVardUnmodifiableUnmodifiable() {
		SkaiciusIrLinksnis.VNS_VARD.setUnmodifiable(false);
	}

}
