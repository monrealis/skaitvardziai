package eu.vytenis.skaitvardziai.skaiciai;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skyrius;

public class PoskyrisTest extends SkaitvardziaiTest {

	@Test
	public void testSkyriuPoskyriai() {
		Set<Poskyris> kiekiniai = new HashSet<Poskyris>();
		Set<Poskyris> kelintiniai = new HashSet<Poskyris>();
		for (Poskyris poskyris : Poskyris.values()) {
			if (poskyris.getSkyrius() == Skyrius.Kelintinis) {
				kelintiniai.add(poskyris);
			} else if (poskyris.getSkyrius() == Skyrius.Kiekinis) {
				kiekiniai.add(poskyris);
			} else {
				throw new IllegalStateException();
			}
		}
		assertEquals(new HashSet<Skyrius>(Arrays.asList(Skyrius.Kiekinis, Skyrius.Kelintinis)), new HashSet<Skyrius>(Arrays.asList(Skyrius.values())));
		assertEquals(new HashSet<Poskyris>(Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kuopinis, Poskyris.Trupmeninis)), kiekiniai);
		assertEquals(new HashSet<Poskyris>(Arrays.asList(Poskyris.Kelintinis)), kelintiniai);
	}

}
