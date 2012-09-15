package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Poskyris;
import eu.vytenis.skaiciai.esybes.Skaicius;

public class DauginiaiTest {
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			Skaicius sk = new Skaicius(number);
					
			Assert.assertEquals("Invalid text", expected, sk.toString(Poskyris.Dauginis, linksnis));
		}
	}
	
	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo20Iki100V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vieneri");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dveji");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vieneri");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dveji");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt treji");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo20Iki100K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienas");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt du");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienas");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt du");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo20Iki100N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo100Iki1000N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienas");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt du");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienas");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt du");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo1Iki20G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo20Iki100G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo100Iki1000G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienas");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt du");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienas");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt du");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo1Iki20I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo20Iki100I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo100Iki1000I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienas");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt du");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienas");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt du");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo1Iki20Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo20Iki100Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo100Iki1000Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienas");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt du");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienas");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt du");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo1Iki20S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri");
		s.put(2, "dveji");
		s.put(3, "treji");
		s.put(4, "ketveri");
		s.put(5, "penkeri");
		s.put(6, "šešeri");
		s.put(7, "septyneri");
		s.put(8, "aštuoneri");
		s.put(9, "devyneri");
		s.put(10, "dešimt");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimt");		
		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testNuo20Iki100S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneri");
		s.put(22, "dvidešimt dveji");
		s.put(24, "dvidešimt ketveri");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoneri");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkeri");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dveji");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testNuo100Iki1000S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vieneri");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vieneri");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dveji");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vieneri");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dveji");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt treji");
		s.put(1000, "tūkstanti");

		testSkaiciai(s, Linksnis.S);
	}

}
