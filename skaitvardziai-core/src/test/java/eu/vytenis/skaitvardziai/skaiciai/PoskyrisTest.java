package eu.vytenis.skaitvardziai.skaiciai;

import static eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris.Dauginis;
import static eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris.Kuopinis;
import static eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris.Pagrindinis;
import static eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris.Trupmeninis;
import static eu.vytenis.skaitvardziai.klasifikatoriai.Skyrius.Kiekinis;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skyrius;

public class PoskyrisTest {
	private Map<Skyrius, Set<Poskyris>> poskyriai = new HashMap<Skyrius, Set<Poskyris>>();

	@Before
	public void before() {
		prepareEmptySets();
	}

	private void prepareEmptySets() {
		for (Skyrius skyrius : Skyrius.values())
			poskyriai.put(skyrius, new HashSet<Poskyris>());
	}

	@Test
	public void skyriuPoskyriaiAsExpected() {
		fillPoskyriai();
		assertEquals(set(Pagrindinis, Dauginis, Kuopinis, Trupmeninis), poskyriai.get(Kiekinis));
		assertEquals(set(Poskyris.Kelintinis), poskyriai.get(Skyrius.Kelintinis));
	}

	private void fillPoskyriai() {
		for (Poskyris poskyris : Poskyris.values())
			poskyriai.get(poskyris.getSkyrius()).add(poskyris);
	}

	private Set<Poskyris> set(Poskyris... poskyriai) {
		return new HashSet<Poskyris>(asList(poskyriai));
	}
}
