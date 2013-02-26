package eu.vytenis.skaitvardziai.zodziai;

import org.junit.Test;

import eu.vytenis.skaitvardziai.checks.CheckUtil.NotModifiableException;

public class ZodisTest {


	@Test(expected = NotModifiableException.class)
	public void testUnmodifiablePagrindiniaiMotGimMap() {
		Zodis.pagrindiniaiMotGimMap.values().iterator().next().setUnmodifiable(false);
	}
	
	@Test(expected = NotModifiableException.class)
	public void testPagrindiniaiVyrGimMap() {
		Zodis.pagrindiniaiVyrGimMap.values().iterator().next().setUnmodifiable(false);
		
	}
	
	@Test(expected = NotModifiableException.class)
	public void testKuopiniaiMap() {
		Zodis.kuopiniaiMap.values().iterator().next().setUnmodifiable(false);
	}
	
	@Test(expected = NotModifiableException.class)
	public void testDauginiaiMotGimMap() {
		Zodis.dauginiaiMotGimMap.values().iterator().next().setUnmodifiable(false);
	}
	
	@Test(expected = NotModifiableException.class)
	public void testDauginiaiVyrGimMap() {
		Zodis.dauginiaiVyrGimMap.values().iterator().next().setUnmodifiable(false);
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiVyrGimMap() {
		Zodis.kelintiniaiVyrGimMap.values().iterator().next().setUnmodifiable(false);	
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiIvVyrGimMap() {
		Zodis.kelintiniaiIvVyrGimMap.values().iterator().next().setUnmodifiable(false);	
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiMotGimMap() {
		Zodis.kelintiniaiMotGimMap.values().iterator().next().setUnmodifiable(false);	
	}

	@Test(expected = NotModifiableException.class)
	public void testKelintiniaiIvMotGimMap() {
		Zodis.kelintiniaiIvMotGimMap.values().iterator().next().setUnmodifiable(false);	
	}
}
