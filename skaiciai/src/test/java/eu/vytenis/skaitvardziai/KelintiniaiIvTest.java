package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaitvardziai.SveikasSkaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


public class KelintiniaiIvTest {

	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Skaicius skaicius, Linksnis linksnis, Gimine gimine, boolean ivardziuotine) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			SveikasSkaicius sk = new SveikasSkaicius(number);
					
			
			FormaIrSkaiciai k = new FormaIrSkaiciai();
			k.setPoskyris(Poskyris.Kelintinis);
			k.setLinksnis(linksnis);
			k.setGimine(gimine);
			k.setSkaicius(skaicius);
			k.setSveikasSkaicius(new BigInteger(Long.toString(number)));
			k.setPradinisSveikasSkaicius(new BigInteger(Long.toString(number)));
			k.setIvardziuotine(ivardziuotine);
			try {
				Assert.assertEquals("Invalid text for " + number + ".", expected, sk.toString(k));
			} catch (Throwable t) {
				Assert.assertEquals("Invalid text for " + number + ".", expected, sk.toString(k));
			}
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
	public void testVirs1000VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantasis");
		s.put(1018L, "tūkstantis aštuonioliktasis");
		s.put(2000L, "du tūkstantasis");
		s.put(3500L, "trys tūkstančiai penki šimtasis");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiasis");
		s.put(10000L, "dešimt tūkstantasis");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiasis");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtasis");
		s.put(100000L, "šimtas tūkstantasis");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtasis");
		s.put(1000000L, "milijonasis");
		s.put(2000000L, "du milijonasis");
		s.put(235000000L, "du šimtai trisdešimt penki milijonasis");
		s.put(1000000000L, "milijardasis");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtasis");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtasis");

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
	
	@Test
	public void testVirs1000VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantoji");
		s.put(1018L, "tūkstantis aštuonioliktoji");
		s.put(2000L, "du tūkstantoji");
		s.put(3500L, "trys tūkstančiai penki šimtoji");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečioji");
		s.put(10000L, "dešimt tūkstantoji");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečioji");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtoji");
		s.put(100000L, "šimtas tūkstantoji");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtoji");
		s.put(1000000L, "milijonoji");
		s.put(2000000L, "du milijonoji");
		s.put(235000000L, "du šimtai trisdešimt penki milijonoji");
		s.put(1000000000L, "milijardoji");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtoji");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtoji");

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
	public void testVirs1000KVyr() {
		// Samplaikinių kelintinių skaitvardžių visada linksniuojamas tik paskutinis žodis, pvz.: penki šimtai aštuoniasdešimt penktas (... penkta), penki šimtai aštuoniasdešimt penkto (... penktos) ir t. t.
		// Žr. http://ualgiman.dtiltas.lt/skaitvardis.html
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantojo");
		s.put(1018L, "tūkstantis aštuonioliktojo");
		s.put(2000L, "du tūkstantojo");
		s.put(3500L, "trys tūkstančiai penki šimtojo");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiojo");
		s.put(10000L, "dešimt tūkstantojo");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiojo");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtojo");
		s.put(100000L, "šimtas tūkstantojo");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtojo");
		s.put(1000000L, "milijonojo");
		s.put(2000000L, "du milijonojo");
		s.put(235000000L, "du šimtai trisdešimt penki milijonojo");
		s.put(1000000000L, "milijardojo");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtojo");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtojo");

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
	
	@Test
	public void testVirs1000KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantosios");
		s.put(1018L, "tūkstantis aštuonioliktosios");
		s.put(2000L, "du tūkstantosios");
		s.put(3500L, "trys tūkstančiai penki šimtosios");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiosios");
		s.put(10000L, "dešimt tūkstantosios");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiosios");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtosios");
		s.put(100000L, "šimtas tūkstantosios");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtosios");
		s.put(1000000L, "milijonosios");
		s.put(2000000L, "du milijonosios");
		s.put(235000000L, "du šimtai trisdešimt penki milijonosios");
		s.put(1000000000L, "milijardosios");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtosios");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtosios");

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, true);
	}
	
	//-----------------------------------------------------------------------------------------
	
	@Test
	public void testNuo1Iki20DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		//s.put(0, "nulinysis");
		s.put(1, "pirmieji");
		s.put(2, "antrieji");
		s.put(3, "tretieji");
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
		s.put(963, "devyni šimtai šešiasdešimt tretieji");
		s.put(1000, "tūkstantieji");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, true);
	}
	
	@Test
	public void testVirs1000DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantieji");
		s.put(1018L, "tūkstantis aštuonioliktieji");
		s.put(2000L, "du tūkstantieji");
		s.put(3500L, "trys tūkstančiai penki šimtieji");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt tretieji");
		s.put(10000L, "dešimt tūkstantieji");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt tretieji");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtieji");
		s.put(100000L, "šimtas tūkstantieji");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtieji");
		s.put(1000000L, "milijonieji");
		s.put(2000000L, "du milijonieji");
		s.put(235000000L, "du šimtai trisdešimt penki milijonieji");
		s.put(1000000000L, "milijardieji");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtieji");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtieji");
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

	@Test
	public void testVirs1000DgsVMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantosios");
		s.put(1018L, "tūkstantis aštuonioliktosios");
		s.put(2000L, "du tūkstantosios");
		s.put(3500L, "trys tūkstančiai penki šimtosios");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiosios");
		s.put(10000L, "dešimt tūkstantosios");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiosios");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtosios");
		s.put(100000L, "šimtas tūkstantosios");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtosios");
		s.put(1000000L, "milijonosios");
		s.put(2000000L, "du milijonosios");
		s.put(235000000L, "du šimtai trisdešimt penki milijonosios");
		s.put(1000000000L, "milijardosios");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtosios");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtosios");

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
	public void testVirs1000DgsKVyr() {
		// Samplaikinių kelintinių skaitvardžių visada linksniuojamas tik paskutinis žodis, pvz.: penki šimtai aštuoniasdešimt penktas (... penkta), penki šimtai aštuoniasdešimt penkto (... penktos) ir t. t.
		// Žr. http://ualgiman.dtiltas.lt/skaitvardis.html
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantųjų");
		s.put(1018L, "tūkstantis aštuonioliktųjų");
		s.put(2000L, "du tūkstantųjų");
		s.put(3500L, "trys tūkstančiai penki šimtųjų");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiųjų");
		s.put(10000L, "dešimt tūkstantųjų");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiųjų");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtųjų");
		s.put(100000L, "šimtas tūkstantųjų");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtųjų");
		s.put(1000000L, "milijonųjų");
		s.put(2000000L, "du milijonųjų");
		s.put(235000000L, "du šimtai trisdešimt penki milijonųjų");
		s.put(1000000000L, "milijardųjų");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtųjų");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtųjų");

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
	
	@Test
	public void testVirs1000DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantųjų");
		s.put(1018L, "tūkstantis aštuonioliktųjų");
		s.put(2000L, "du tūkstantųjų");
		s.put(3500L, "trys tūkstančiai penki šimtųjų");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečiųjų");
		s.put(10000L, "dešimt tūkstantųjų");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečiųjų");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtųjų");
		s.put(100000L, "šimtas tūkstantųjų");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtųjų");
		s.put(1000000L, "milijonųjų");
		s.put(2000000L, "du milijonųjų");
		s.put(235000000L, "du šimtai trisdešimt penki milijonųjų");
		s.put(1000000000L, "milijardųjų");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtųjų");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtųjų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, true);
	}
}
