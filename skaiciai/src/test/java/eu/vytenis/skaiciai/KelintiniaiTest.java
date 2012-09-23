package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Gimine;
import eu.vytenis.skaiciai.esybes.Kontekstas;
import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Poskyris;
import eu.vytenis.skaiciai.esybes.SveikasSkaicius;

public class KelintiniaiTest {

	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis, Gimine gimine, boolean ivardziuotine) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			SveikasSkaicius sk = new SveikasSkaicius(number);
					
			
			Kontekstas k = new Kontekstas();
			k.setPoskyris(Poskyris.Kelintinis);
			k.setLinksnis(linksnis);
			k.setGimine(gimine);
			k.setIvardziuotine(ivardziuotine);
			k.setSveikasSkaicius(number);
			k.setPradinisSveikasSkaicius(number);
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
		testSkaiciai(s, Linksnis.V, Gimine.V, false);
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
		testSkaiciai(s, Linksnis.V, Gimine.V, false);
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

		testSkaiciai(s, Linksnis.V, Gimine.V, false);
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
		testSkaiciai(s, Linksnis.V, Gimine.M, false);
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
		testSkaiciai(s, Linksnis.V, Gimine.M, false);
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

		testSkaiciai(s, Linksnis.V, Gimine.M, false);
	}
	
	///-------	
	@Test
	public void testNuo1Iki20KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinio");
		s.put(1, "pirmo");
		s.put(2, "antro");
		s.put(3, "trečio");
		s.put(4, "ketvirto");
		s.put(5, "penkto");
		s.put(6, "šešto");
		s.put(7, "septinto");
		s.put(8, "aštunto");
		s.put(9, "devinto");
		s.put(10, "dešimto");
		s.put(11, "vienuolikto");
		s.put(12, "dvylikto");
		s.put(13, "trylikto");
		s.put(14, "keturiolikto");
		s.put(15, "penkiolikto");
		s.put(16, "šešiolikto");
		s.put(17, "septyniolikto");
		s.put(18, "aštuoniolikto");
		s.put(19, "devyniolikto");
		s.put(20, "dvidešimto");		
		testSkaiciai(s, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo20Iki100KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmo");
		s.put(22, "dvidešimt antro");
		s.put(24, "dvidešimt ketvirto");
		s.put(30, "trisdešimto");
		s.put(48, "keturiasdešimt aštunto");
		s.put(50, "penkiasdešimto");
		s.put(55, "penkiasdešimt penkto");
		s.put(60, "šešiasdešimto");
		s.put(70, "septyniasdešimto");
		s.put(80, "aštuoniasdešimto");
		s.put(90, "devyniasdešimto");
		s.put(92, "devyniasdešimt antro");
		s.put(100, "šimtojo");
		testSkaiciai(s, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo100Iki1000KVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmo");
		s.put(200, "du šimtojo");
		s.put(210, "du šimtai dešimto");
		s.put(300, "trys šimtojo");
		s.put(313, "trys šimtai trylikto");
		s.put(400, "keturi šimtojo");
		s.put(441, "keturi šimtai keturiasdešimt pirmo");
		s.put(500, "penki šimtojo");
		s.put(582, "penki šimtai aštuoniasdešimt antro");
		s.put(600, "šeši šimtojo");
		s.put(691, "šeši šimtai devyniasdešimt pirmo");
		s.put(700, "septyni šimtojo");
		s.put(715, "septyni šimtai penkiolikto");
		s.put(800, "aštuoni šimtojo");
		s.put(862, "aštuoni šimtai šešiasdešimt antro");
		s.put(900, "devyni šimtojo");
		s.put(963, "devyni šimtai šešiasdešimt trečio");
		s.put(1000, "tūkstantojo");

		testSkaiciai(s, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo1Iki20KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinės");
		s.put(1, "pirmos");
		s.put(2, "antros");
		s.put(3, "trečios");
		s.put(4, "ketvirtos");
		s.put(5, "penktos");
		s.put(6, "šeštos");
		s.put(7, "septintos");
		s.put(8, "aštuntos");
		s.put(9, "devintos");
		s.put(10, "dešimtos");
		s.put(11, "vienuoliktos");
		s.put(12, "dvyliktos");
		s.put(13, "tryliktos");
		s.put(14, "keturioliktos");
		s.put(15, "penkioliktos");
		s.put(16, "šešioliktos");
		s.put(17, "septynioliktos");
		s.put(18, "aštuonioliktos");
		s.put(19, "devynioliktos");
		s.put(20, "dvidešimtos");		
		testSkaiciai(s, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testNuo20Iki100KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmos");
		s.put(22, "dvidešimt antros");
		s.put(24, "dvidešimt ketvirtos");
		s.put(30, "trisdešimtos");
		s.put(48, "keturiasdešimt aštuntos");
		s.put(50, "penkiasdešimtos");
		s.put(55, "penkiasdešimt penktos");
		s.put(60, "šešiasdešimtos");
		s.put(70, "septyniasdešimtos");
		s.put(80, "aštuoniasdešimtos");
		s.put(90, "devyniasdešimtos");
		s.put(92, "devyniasdešimt antros");
		s.put(100, "šimtosios");
		testSkaiciai(s, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testNuo100Iki1000KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmos");
		s.put(200, "du šimtosios");
		s.put(210, "du šimtai dešimtos");
		s.put(300, "trys šimtosios");
		s.put(313, "trys šimtai tryliktos");
		s.put(400, "keturi šimtosios");
		s.put(441, "keturi šimtai keturiasdešimt pirmos");
		s.put(500, "penki šimtosios");
		s.put(582, "penki šimtai aštuoniasdešimt antros");
		s.put(600, "šeši šimtosios");
		s.put(691, "šeši šimtai devyniasdešimt pirmos");
		s.put(700, "septyni šimtosios");
		s.put(715, "septyni šimtai penkioliktos");
		s.put(800, "aštuoni šimtosios");
		s.put(862, "aštuoni šimtai šešiasdešimt antros");
		s.put(900, "devyni šimtosios");
		s.put(963, "devyni šimtai šešiasdešimt trečios");
		s.put(1000, "tūkstantosios");

		testSkaiciai(s, Linksnis.K, Gimine.M, false);
	}
}
