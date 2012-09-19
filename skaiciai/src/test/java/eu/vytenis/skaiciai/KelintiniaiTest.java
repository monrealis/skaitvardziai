package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Gimine;
import eu.vytenis.skaiciai.esybes.Kontekstas;
import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Poskyris;
import eu.vytenis.skaiciai.esybes.Skaicius;

public class KelintiniaiTest {

	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis, Gimine gimine) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			Skaicius sk = new Skaicius(number);
					
			
			Kontekstas k = new Kontekstas();
			k.setPoskyris(Poskyris.Kelintinis);
			k.setLinksnis(linksnis);
			k.setGimine(gimine);
			k.setSkaicius(number);
			k.setPradinisSkaicius(number);
			Assert.assertEquals("Invalid text for " + number + ".", expected, sk.toString(k));
		}
	}
	
	@Test
	public void testNuo1Iki20VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinis");
		s.put(1, "pirmas");
		s.put(2, "antras");
		s.put(3, "trečias");
		s.put(4, "ketvirtas");
		s.put(5, "penktas");
		s.put(6, "šeštas");
		s.put(7, "septintas");
		s.put(8, "aštuntas");
		s.put(9, "devintas");
		s.put(10, "dešimtas");
		s.put(11, "vienuoliktas");
		s.put(12, "dvyliktas");
		s.put(13, "tryliktas");
		s.put(14, "keturioliktas");
		s.put(15, "penkioliktas");
		s.put(16, "šešioliktas");
		s.put(17, "septynioliktas");
		s.put(18, "aštuonioliktas");
		s.put(19, "devynioliktas");
		s.put(20, "dvidešimtas");		
		testSkaiciai(s, Linksnis.V, Gimine.V);
	}
	
	@Test
	public void testNuo20Iki100VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmas");
		s.put(22, "dvidešimt antras");
		s.put(24, "dvidešimt ketvirtas");
		s.put(30, "trisdešimtas");
		s.put(48, "keturiasdešimt aštuntas");
		s.put(50, "penkiasdešimtas");
		s.put(55, "penkiasdešimt penktas");
		s.put(60, "šešiasdešimtas");
		s.put(70, "septyniasdešimtas");
		s.put(80, "aštuoniasdešimtas");
		s.put(90, "devyniasdešimtas");
		s.put(92, "devyniasdešimt antras");
		s.put(100, "šimtasis");
		testSkaiciai(s, Linksnis.V, Gimine.V);
	}
	
	@Test
	public void testNuo100Iki1000VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmas");
		s.put(200, "du šimtasis");
		s.put(210, "du šimtai dešimtas");
		s.put(300, "trys šimtasis");
		s.put(313, "trys šimtai tryliktas");
		s.put(400, "keturi šimtasis");
		s.put(441, "keturi šimtai keturiasdešimt pirmas");
		s.put(500, "penki šimtasis");
		s.put(582, "penki šimtai aštuoniasdešimt antras");
		s.put(600, "šeši šimtasis");
		s.put(691, "šeši šimtai devyniasdešimt pirmas");
		s.put(700, "septyni šimtasis");
		s.put(715, "septyni šimtai penkioliktas");
		s.put(800, "aštuoni šimtasis");
		s.put(862, "aštuoni šimtai šešiasdešimt antras");
		s.put(900, "devyni šimtasis");
		s.put(963, "devyni šimtai šešiasdešimt trečias");
		s.put(1000, "tūkstantasis");

		testSkaiciai(s, Linksnis.V, Gimine.V);
	}
	
	@Test
	public void testNuo1Iki20VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinė");
		s.put(1, "pirma");
		s.put(2, "antra");
		s.put(3, "trečia");
		s.put(4, "ketvirta");
		s.put(5, "penkta");
		s.put(6, "šešta");
		s.put(7, "septinta");
		s.put(8, "aštunta");
		s.put(9, "devinta");
		s.put(10, "dešimta");
		s.put(11, "vienuolikta");
		s.put(12, "dvylikta");
		s.put(13, "trylikta");
		s.put(14, "keturiolikta");
		s.put(15, "penkiolikta");
		s.put(16, "šešiolikta");
		s.put(17, "septyniolikta");
		s.put(18, "aštuoniolikta");
		s.put(19, "devyniolikta");
		s.put(20, "dvidešimta");		
		testSkaiciai(s, Linksnis.V, Gimine.M);
	}
	
	@Test
	public void testNuo20Iki100VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirma");
		s.put(22, "dvidešimt antra");
		s.put(24, "dvidešimt ketvirta");
		s.put(30, "trisdešimta");
		s.put(48, "keturiasdešimt aštunta");
		s.put(50, "penkiasdešimta");
		s.put(55, "penkiasdešimt penkta");
		s.put(60, "šešiasdešimta");
		s.put(70, "septyniasdešimta");
		s.put(80, "aštuoniasdešimta");
		s.put(90, "devyniasdešimta");
		s.put(92, "devyniasdešimt antra");
		s.put(100, "šimtoji");
		testSkaiciai(s, Linksnis.V, Gimine.M);
	}
	
	@Test
	public void testNuo100Iki1000VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirma");
		s.put(200, "du šimtoji");
		s.put(210, "du šimtai dešimta");
		s.put(300, "trys šimtoji");
		s.put(313, "trys šimtai trylikta");
		s.put(400, "keturi šimtoji");
		s.put(441, "keturi šimtai keturiasdešimt pirma");
		s.put(500, "penki šimtoji");
		s.put(582, "penki šimtai aštuoniasdešimt antra");
		s.put(600, "šeši šimtoji");
		s.put(691, "šeši šimtai devyniasdešimt pirma");
		s.put(700, "septyni šimtoji");
		s.put(715, "septyni šimtai penkiolikta");
		s.put(800, "aštuoni šimtoji");
		s.put(862, "aštuoni šimtai šešiasdešimt antra");
		s.put(900, "devyni šimtoji");
		s.put(963, "devyni šimtai šešiasdešimt trečia");
		s.put(1000, "tūkstantoji");

		testSkaiciai(s, Linksnis.V, Gimine.M);
	}
}
