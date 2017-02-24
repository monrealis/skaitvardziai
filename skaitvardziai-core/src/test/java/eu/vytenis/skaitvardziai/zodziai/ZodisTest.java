package eu.vytenis.skaitvardziai.zodziai;

import org.junit.Test;

import eu.vytenis.skaitvardziai.checks.Checks.NotModifiableException;

public class ZodisTest {

	@Test(expected = NotModifiableException.class)
	public void testUnmodifiablePagrindiniaiMotGimMap() {
		Zodziai.pagrindiniaiMotGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testPagrindiniaiVyrGimMap() {
		Zodziai.pagrindiniaiVyrGimMap.values().iterator().next().setUnmodifiable(false);

	}

	@Test(expected = NotModifiableException.class)
	public void testKuopiniaiMap() {
		Zodziai.kuopiniaiMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testDauginiaiMotGimMap() {
		Zodziai.dauginiaiMotGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testDauginiaiVyrGimMap() {
		Zodziai.dauginiaiVyrGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiVyrGimMap() {
		Zodziai.kelintiniaiVyrGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiIvVyrGimMap() {
		Zodziai.kelintiniaiIvVyrGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiMotGimMap() {
		Zodziai.kelintiniaiMotGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiIvMotGimMap() {
		Zodziai.kelintiniaiIvMotGimMap.values().iterator().next().setUnmodifiable(false);
	}

}
