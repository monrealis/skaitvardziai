package eu.vytenis.skaitvardziai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaitvardziai.Kontekstas;
import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


public class KelintiniaiIvTest {

	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Skaicius skaicius, Linksnis linksnis, Gimine gimine, boolean ivardziuotine) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			SveikasisSkaicius sk = new SveikasisSkaicius(number);
					
			
			Kontekstas k = new Kontekstas();
			k.setPoskyris(Poskyris.Kelintinis);
			k.setLinksnis(linksnis);
			k.setGimine(gimine);
			k.setSkaicius(skaicius);
			k.setSveikasSkaicius(number);
			k.setPradinisSveikasSkaicius(number);
			k.setIvardziuotine(ivardziuotine);
			Assert.assertEquals("Invalid text for " + number + ".", expected, sk.toString(k));
		}
	}
	
	@Test
	public void testNuo1Iki20VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		//s.put(0, "nulinysis");
		s.put(1, "pirmasis");
		s.put(2, "antrasis");
		s.put(3, "trečiasis");
		s.put(4, "ketvirtasis");
		s.put(5, "penktasis");
		s.put(6, "šeštasis");
		s.put(7, "septintasis");
		s.put(8, "aštuntasis");
		s.put(9, "devintasis");
		s.put(10, "dešimtasis");
		s.put(11, "vienuoliktasis");
		s.put(12, "dvyliktasis");
		s.put(13, "tryliktasis");
		s.put(14, "keturioliktasis");
		s.put(15, "penkioliktasis");
		s.put(16, "šešioliktasis");
		s.put(17, "septynioliktasis");
		s.put(18, "aštuonioliktasis");
		s.put(19, "devynioliktasis");
		s.put(20, "dvidešimtasis");		
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo20Iki100VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmasis");
		s.put(22, "dvidešimt antrasis");
		s.put(24, "dvidešimt ketvirtasis");
		s.put(30, "trisdešimtasis");
		s.put(48, "keturiasdešimt aštuntasis");
		s.put(50, "penkiasdešimtasis");
		s.put(55, "penkiasdešimt penktasis");
		s.put(60, "šešiasdešimtasis");
		s.put(70, "septyniasdešimtasis");
		s.put(80, "aštuoniasdešimtasis");
		s.put(90, "devyniasdešimtasis");
		s.put(92, "devyniasdešimt antrasis");
		s.put(100, "šimtasis");
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo100Iki1000VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmasis");
		s.put(200, "du šimtasis");
		s.put(210, "du šimtai dešimtasis");
		s.put(300, "trys šimtasis");
		s.put(313, "trys šimtai tryliktasis");
		s.put(400, "keturi šimtasis");
		s.put(441, "keturi šimtai keturiasdešimt pirmasis");
		s.put(500, "penki šimtasis");
		s.put(582, "penki šimtai aštuoniasdešimt antrasis");
		s.put(600, "šeši šimtasis");
		s.put(691, "šeši šimtai devyniasdešimt pirmasis");
		s.put(700, "septyni šimtasis");
		s.put(715, "septyni šimtai penkioliktasis");
		s.put(800, "aštuoni šimtasis");
		s.put(862, "aštuoni šimtai šešiasdešimt antrasis");
		s.put(900, "devyni šimtasis");
		s.put(963, "devyni šimtai šešiasdešimt trečiasis");
		s.put(1000, "tūkstantasis");

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo1Iki20VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinė");
		s.put(1, "pirmoji");
		s.put(2, "antroji");
		s.put(3, "trečioji");
		s.put(4, "ketvirtoji");
		s.put(5, "penktoji");
		s.put(6, "šeštoji");
		s.put(7, "septintoji");
		s.put(8, "aštuntoji");
		s.put(9, "devintoji");
		s.put(10, "dešimtoji");
		s.put(11, "vienuoliktoji");
		s.put(12, "dvyliktoji");
		s.put(13, "tryliktoji");
		s.put(14, "keturioliktoji");
		s.put(15, "penkioliktoji");
		s.put(16, "šešioliktoji");
		s.put(17, "septynioliktoji");
		s.put(18, "aštuonioliktoji");
		s.put(19, "devynioliktoji");
		s.put(20, "dvidešimtoji");		
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, true);
	}
	
	@Test
	public void testNuo20Iki100VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmoji");
		s.put(22, "dvidešimt antroji");
		s.put(24, "dvidešimt ketvirtoji");
		s.put(30, "trisdešimtoji");
		s.put(48, "keturiasdešimt aštuntoji");
		s.put(50, "penkiasdešimtoji");
		s.put(55, "penkiasdešimt penktoji");
		s.put(60, "šešiasdešimtoji");
		s.put(70, "septyniasdešimtoji");
		s.put(80, "aštuoniasdešimtoji");
		s.put(90, "devyniasdešimtoji");
		s.put(92, "devyniasdešimt antroji");
		s.put(100, "šimtoji");
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, true);
	}
	
	@Test
	public void testNuo100Iki1000VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmoji");
		s.put(200, "du šimtoji");
		s.put(210, "du šimtai dešimtoji");
		s.put(300, "trys šimtoji");
		s.put(313, "trys šimtai tryliktoji");
		s.put(400, "keturi šimtoji");
		s.put(441, "keturi šimtai keturiasdešimt pirmoji");
		s.put(500, "penki šimtoji");
		s.put(582, "penki šimtai aštuoniasdešimt antroji");
		s.put(600, "šeši šimtoji");
		s.put(691, "šeši šimtai devyniasdešimt pirmoji");
		s.put(700, "septyni šimtoji");
		s.put(715, "septyni šimtai penkioliktoji");
		s.put(800, "aštuoni šimtoji");
		s.put(862, "aštuoni šimtai šešiasdešimt antroji");
		s.put(900, "devyni šimtoji");
		s.put(963, "devyni šimtai šešiasdešimt trečioji");
		s.put(1000, "tūkstantoji");

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, true);
	}

	///-------
	@Test
	public void testNuo1Iki20KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		//s.put(0, "nulinysis"); TODO
		s.put(1, "pirmojo");
		s.put(2, "antrojo");
		s.put(3, "trečiojo");
		s.put(4, "ketvirtojo");
		s.put(5, "penktojo");
		s.put(6, "šeštojo");
		s.put(7, "septintojo");
		s.put(8, "aštuntojo");
		s.put(9, "devintojo");
		s.put(10, "dešimtojo");
		s.put(11, "vienuoliktojo");
		s.put(12, "dvyliktojo");
		s.put(13, "tryliktojo");
		s.put(14, "keturioliktojo");
		s.put(15, "penkioliktojo");
		s.put(16, "šešioliktojo");
		s.put(17, "septynioliktojo");
		s.put(18, "aštuonioliktojo");
		s.put(19, "devynioliktojo");
		s.put(20, "dvidešimtojo");		
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo20Iki100KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmojo");
		s.put(22, "dvidešimt antrojo");
		s.put(24, "dvidešimt ketvirtojo");
		s.put(30, "trisdešimtojo");
		s.put(48, "keturiasdešimt aštuntojo");
		s.put(50, "penkiasdešimtojo");
		s.put(55, "penkiasdešimt penktojo");
		s.put(60, "šešiasdešimtojo");
		s.put(70, "septyniasdešimtojo");
		s.put(80, "aštuoniasdešimtojo");
		s.put(90, "devyniasdešimtojo");
		s.put(92, "devyniasdešimt antrojo");
		s.put(100, "šimtojo");
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo100Iki1000KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmojo");
		s.put(200, "du šimtojo");
		s.put(210, "du šimtai dešimtojo");
		s.put(300, "trys šimtojo");
		s.put(313, "trys šimtai tryliktojo");
		s.put(400, "keturi šimtojo");
		s.put(441, "keturi šimtai keturiasdešimt pirmojo");
		s.put(500, "penki šimtojo");
		s.put(582, "penki šimtai aštuoniasdešimt antrojo");
		s.put(600, "šeši šimtojo");
		s.put(691, "šeši šimtai devyniasdešimt pirmojo");
		s.put(700, "septyni šimtojo");
		s.put(715, "septyni šimtai penkioliktojo");
		s.put(800, "aštuoni šimtojo");
		s.put(862, "aštuoni šimtai šešiasdešimt antrojo");
		s.put(900, "devyni šimtojo");
		s.put(963, "devyni šimtai šešiasdešimt trečiojo");
		s.put(1000, "tūkstantojo");

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo1Iki20KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinės");
		s.put(1, "pirmosios");
		s.put(2, "antrosios");
		s.put(3, "trečiosios");
		s.put(4, "ketvirtosios");
		s.put(5, "penktosios");
		s.put(6, "šeštosios");
		s.put(7, "septintosios");
		s.put(8, "aštuntosios");
		s.put(9, "devintosios");
		s.put(10, "dešimtosios");
		s.put(11, "vienuoliktosios");
		s.put(12, "dvyliktosios");
		s.put(13, "tryliktosios");
		s.put(14, "keturioliktosios");
		s.put(15, "penkioliktosios");
		s.put(16, "šešioliktosios");
		s.put(17, "septynioliktosios");
		s.put(18, "aštuonioliktosios");
		s.put(19, "devynioliktosios");
		s.put(20, "dvidešimtosios");		
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, true);
	}
	
	@Test
	public void testNuo20Iki100KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmosios");
		s.put(22, "dvidešimt antrosios");
		s.put(24, "dvidešimt ketvirtosios");
		s.put(30, "trisdešimtosios");
		s.put(48, "keturiasdešimt aštuntosios");
		s.put(50, "penkiasdešimtosios");
		s.put(55, "penkiasdešimt penktosios");
		s.put(60, "šešiasdešimtosios");
		s.put(70, "septyniasdešimtosios");
		s.put(80, "aštuoniasdešimtosios");
		s.put(90, "devyniasdešimtosios");
		s.put(92, "devyniasdešimt antrosios");
		s.put(100, "šimtosios");
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, true);
	}
	
	@Test
	public void testNuo100Iki1000KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmosios");
		s.put(200, "du šimtosios");
		s.put(210, "du šimtai dešimtosios");
		s.put(300, "trys šimtosios");
		s.put(313, "trys šimtai tryliktosios");
		s.put(400, "keturi šimtosios");
		s.put(441, "keturi šimtai keturiasdešimt pirmosios");
		s.put(500, "penki šimtosios");
		s.put(582, "penki šimtai aštuoniasdešimt antrosios");
		s.put(600, "šeši šimtosios");
		s.put(691, "šeši šimtai devyniasdešimt pirmosios");
		s.put(700, "septyni šimtosios");
		s.put(715, "septyni šimtai penkioliktosios");
		s.put(800, "aštuoni šimtosios");
		s.put(862, "aštuoni šimtai šešiasdešimt antrosios");
		s.put(900, "devyni šimtosios");
		s.put(963, "devyni šimtai šešiasdešimt trečiosios");
		s.put(1000, "tūkstantosios");

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, true);
	}
	
	//-----------------------------------------------------------------------------------------
	
	@Test
	public void testNuo1Iki20DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		//s.put(0, "nulinysis");
		s.put(1, "pirmieji");
		s.put(2, "antrieji");
		s.put(3, "trečiieji");
		s.put(4, "ketvirtieji");
		s.put(5, "penktieji");
		s.put(6, "šeštieji");
		s.put(7, "septintieji");
		s.put(8, "aštuntieji");
		s.put(9, "devintieji");
		s.put(10, "dešimtieji");
		s.put(11, "vienuoliktieji");
		s.put(12, "dvyliktieji");
		s.put(13, "tryliktieji");
		s.put(14, "keturioliktieji");
		s.put(15, "penkioliktieji");
		s.put(16, "šešioliktieji");
		s.put(17, "septynioliktieji");
		s.put(18, "aštuonioliktieji");
		s.put(19, "devynioliktieji");
		s.put(20, "dvidešimtieji");		
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo20Iki100DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmieji");
		s.put(22, "dvidešimt antrieji");
		s.put(24, "dvidešimt ketvirtieji");
		s.put(30, "trisdešimtieji");
		s.put(48, "keturiasdešimt aštuntieji");
		s.put(50, "penkiasdešimtieji");
		s.put(55, "penkiasdešimt penktieji");
		s.put(60, "šešiasdešimtieji");
		s.put(70, "septyniasdešimtieji");
		s.put(80, "aštuoniasdešimtieji");
		s.put(90, "devyniasdešimtieji");
		s.put(92, "devyniasdešimt antrieji");
		s.put(100, "šimtieji");
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo100Iki1000DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmieji");
		s.put(200, "du šimtieji");
		s.put(210, "du šimtai dešimtieji");
		s.put(300, "trys šimtieji");
		s.put(313, "trys šimtai tryliktieji");
		s.put(400, "keturi šimtieji");
		s.put(441, "keturi šimtai keturiasdešimt pirmieji");
		s.put(500, "penki šimtieji");
		s.put(582, "penki šimtai aštuoniasdešimt antrieji");
		s.put(600, "šeši šimtieji");
		s.put(691, "šeši šimtai devyniasdešimt pirmieji");
		s.put(700, "septyni šimtieji");
		s.put(715, "septyni šimtai penkioliktieji");
		s.put(800, "aštuoni šimtieji");
		s.put(862, "aštuoni šimtai šešiasdešimt antrieji");
		s.put(900, "devyni šimtieji");
		s.put(963, "devyni šimtai šešiasdešimt trečiieji");
		s.put(1000, "tūkstantieji");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testNuo1Iki20DgsVMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinės");
		s.put(1, "pirmosios");
		s.put(2, "antrosios");
		s.put(3, "trečiosios");
		s.put(4, "ketvirtosios");
		s.put(5, "penktosios");
		s.put(6, "šeštosios");
		s.put(7, "septintosios");
		s.put(8, "aštuntosios");
		s.put(9, "devintosios");
		s.put(10, "dešimtosios");
		s.put(11, "vienuoliktosios");
		s.put(12, "dvyliktosios");
		s.put(13, "tryliktosios");
		s.put(14, "keturioliktosios");
		s.put(15, "penkioliktosios");
		s.put(16, "šešioliktosios");
		s.put(17, "septynioliktosios");
		s.put(18, "aštuonioliktosios");
		s.put(19, "devynioliktosios");
		s.put(20, "dvidešimtosios");		
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, true);
	}
	
	@Test
	public void testNuo20Iki100DgsVMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmosios");
		s.put(22, "dvidešimt antrosios");
		s.put(24, "dvidešimt ketvirtosios");
		s.put(30, "trisdešimtosios");
		s.put(48, "keturiasdešimt aštuntosios");
		s.put(50, "penkiasdešimtosios");
		s.put(55, "penkiasdešimt penktosios");
		s.put(60, "šešiasdešimtosios");
		s.put(70, "septyniasdešimtosios");
		s.put(80, "aštuoniasdešimtosios");
		s.put(90, "devyniasdešimtosios");
		s.put(92, "devyniasdešimt antrosios");
		s.put(100, "šimtosios");
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, true);
	}
	
	@Test
	public void testNuo100Iki1000DgsVMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmosios");
		s.put(200, "du šimtosios");
		s.put(210, "du šimtai dešimtosios");
		s.put(300, "trys šimtosios");
		s.put(313, "trys šimtai tryliktosios");
		s.put(400, "keturi šimtosios");
		s.put(441, "keturi šimtai keturiasdešimt pirmosios");
		s.put(500, "penki šimtosios");
		s.put(582, "penki šimtai aštuoniasdešimt antrosios");
		s.put(600, "šeši šimtosios");
		s.put(691, "šeši šimtai devyniasdešimt pirmosios");
		s.put(700, "septyni šimtosios");
		s.put(715, "septyni šimtai penkioliktosios");
		s.put(800, "aštuoni šimtosios");
		s.put(862, "aštuoni šimtai šešiasdešimt antrosios");
		s.put(900, "devyni šimtosios");
		s.put(963, "devyni šimtai šešiasdešimt trečiosios");
		s.put(1000, "tūkstantosios");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, true);
	}

	///-------
	@Test
	public void testNuo1Iki20DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		//s.put(0, "nulinysis"); TODO
		s.put(1, "pirmųjų");
		s.put(2, "antrųjų");
		s.put(3, "trečiųjų");
		s.put(4, "ketvirtųjų");
		s.put(5, "penktųjų");
		s.put(6, "šeštųjų");
		s.put(7, "septintųjų");
		s.put(8, "aštuntųjų");
		s.put(9, "devintųjų");
		s.put(10, "dešimtųjų");
		s.put(11, "vienuoliktųjų");
		s.put(12, "dvyliktųjų");
		s.put(13, "tryliktųjų");
		s.put(14, "keturioliktųjų");
		s.put(15, "penkioliktųjų");
		s.put(16, "šešioliktųjų");
		s.put(17, "septynioliktųjų");
		s.put(18, "aštuonioliktųjų");
		s.put(19, "devynioliktųjų");
		s.put(20, "dvidešimtųjų");		
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo20Iki100DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmųjų");
		s.put(22, "dvidešimt antrųjų");
		s.put(24, "dvidešimt ketvirtųjų");
		s.put(30, "trisdešimtųjų");
		s.put(48, "keturiasdešimt aštuntųjų");
		s.put(50, "penkiasdešimtųjų");
		s.put(55, "penkiasdešimt penktųjų");
		s.put(60, "šešiasdešimtųjų");
		s.put(70, "septyniasdešimtųjų");
		s.put(80, "aštuoniasdešimtųjų");
		s.put(90, "devyniasdešimtųjų");
		s.put(92, "devyniasdešimt antrųjų");
		s.put(100, "šimtųjų");
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo100Iki1000DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmųjų");
		s.put(200, "du šimtųjų");
		s.put(210, "du šimtai dešimtųjų");
		s.put(300, "trys šimtųjų");
		s.put(313, "trys šimtai tryliktųjų");
		s.put(400, "keturi šimtųjų");
		s.put(441, "keturi šimtai keturiasdešimt pirmųjų");
		s.put(500, "penki šimtųjų");
		s.put(582, "penki šimtai aštuoniasdešimt antrųjų");
		s.put(600, "šeši šimtųjų");
		s.put(691, "šeši šimtai devyniasdešimt pirmųjų");
		s.put(700, "septyni šimtųjų");
		s.put(715, "septyni šimtai penkioliktųjų");
		s.put(800, "aštuoni šimtųjų");
		s.put(862, "aštuoni šimtai šešiasdešimt antrųjų");
		s.put(900, "devyni šimtųjų");
		s.put(963, "devyni šimtai šešiasdešimt trečiųjų");
		s.put(1000, "tūkstantųjų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, true);
	}
	
	@Test
	public void testNuo1Iki20DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinių");
		s.put(1, "pirmųjų");
		s.put(2, "antrųjų");
		s.put(3, "trečiųjų");
		s.put(4, "ketvirtųjų");
		s.put(5, "penktųjų");
		s.put(6, "šeštųjų");
		s.put(7, "septintųjų");
		s.put(8, "aštuntųjų");
		s.put(9, "devintųjų");
		s.put(10, "dešimtųjų");
		s.put(11, "vienuoliktųjų");
		s.put(12, "dvyliktųjų");
		s.put(13, "tryliktųjų");
		s.put(14, "keturioliktųjų");
		s.put(15, "penkioliktųjų");
		s.put(16, "šešioliktųjų");
		s.put(17, "septynioliktųjų");
		s.put(18, "aštuonioliktųjų");
		s.put(19, "devynioliktųjų");
		s.put(20, "dvidešimtųjų");		
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, true);
	}
	
	@Test
	public void testNuo20Iki100DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmųjų");
		s.put(22, "dvidešimt antrųjų");
		s.put(24, "dvidešimt ketvirtųjų");
		s.put(30, "trisdešimtųjų");
		s.put(48, "keturiasdešimt aštuntųjų");
		s.put(50, "penkiasdešimtųjų");
		s.put(55, "penkiasdešimt penktųjų");
		s.put(60, "šešiasdešimtųjų");
		s.put(70, "septyniasdešimtųjų");
		s.put(80, "aštuoniasdešimtųjų");
		s.put(90, "devyniasdešimtųjų");
		s.put(92, "devyniasdešimt antrųjų");
		s.put(100, "šimtųjų");
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, true);
	}
	
	@Test
	public void testNuo100Iki1000DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmųjų");
		s.put(200, "du šimtųjų");
		s.put(210, "du šimtai dešimtųjų");
		s.put(300, "trys šimtųjų");
		s.put(313, "trys šimtai tryliktųjų");
		s.put(400, "keturi šimtųjų");
		s.put(441, "keturi šimtai keturiasdešimt pirmųjų");
		s.put(500, "penki šimtųjų");
		s.put(582, "penki šimtai aštuoniasdešimt antrųjų");
		s.put(600, "šeši šimtųjų");
		s.put(691, "šeši šimtai devyniasdešimt pirmųjų");
		s.put(700, "septyni šimtųjų");
		s.put(715, "septyni šimtai penkioliktųjų");
		s.put(800, "aštuoni šimtųjų");
		s.put(862, "aštuoni šimtai šešiasdešimt antrųjų");
		s.put(900, "devyni šimtųjų");
		s.put(963, "devyni šimtai šešiasdešimt trečiųjų");
		s.put(1000, "tūkstantųjų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, true);
	}
}
