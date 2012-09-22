package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Trupmena;

public class TrupmenosTest {
	private void testTrupmenos(Map<Trupmena, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<Trupmena, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			Trupmena t = e.getKey();
			Assert.assertEquals("Invalid text", expected, t.toString(linksnis));
		}
	}
	
	private Trupmena t(long skaitiklis, long vardiklis) {
		return new Trupmena(skaitiklis, vardiklis);
	}
	@Test
	public void testV() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nulis pirmųjų");
		s.put(t(1, 1), "viena pirmoji");
		s.put(t(2, 1), "dvi pirmosios");
		s.put(t(3, 1), "trys pirmosios");
		s.put(t(4, 1), "keturios pirmosios");
		s.put(t(5, 1), "penkios pirmosios");
		s.put(t(6, 1), "šešios pirmosios");
		s.put(t(7, 1), "septynios pirmosios");
		s.put(t(8, 1), "aštuonios pirmosios");
		s.put(t(9, 1), "devynios pirmosios");
		s.put(t(10, 1), "dešimt pirmųjų");
		s.put(t(11, 1), "vienuolika pirmųjų");
		s.put(t(12, 1), "dvylika pirmųjų");
		s.put(t(13, 1), "trylika pirmųjų");
		s.put(t(14, 1), "keturiolika pirmųjų");
		s.put(t(15, 1), "penkiolika pirmųjų");
		s.put(t(16, 1), "šešiolika pirmųjų");
		s.put(t(17, 1), "septyniolika pirmųjų");
		s.put(t(18, 1), "aštuoniolika pirmųjų");
		s.put(t(19, 1), "devyniolika pirmųjų");
		s.put(t(20, 1), "dvidešimt pirmųjų");		
		testTrupmenos(s, Linksnis.V);
	}

}
