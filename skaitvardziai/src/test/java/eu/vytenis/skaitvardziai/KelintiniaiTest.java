package eu.vytenis.skaitvardziai;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


public class KelintiniaiTest extends BaseKelintiniaiTest {
	

	public KelintiniaiTest() {
		poskyris = Poskyris.Kelintinis;
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
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, false);
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

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, false);
	}
	
	@Test
	public void testVirs1000VVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantasis");
		s.put(1018L, "tūkstantis aštuonioliktas");
		s.put(2000L, "du tūkstantasis");
		s.put(3500L, "trys tūkstančiai penki šimtasis");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečias");
		s.put(10000L, "dešimt tūkstantasis");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečias");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtas");
		s.put(100000L, "šimtas tūkstantasis");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtas");
		s.put(1000000L, "milijonasis");
		s.put(2000000L, "du milijonasis");
		s.put(235000000L, "du šimtai trisdešimt penki milijonasis");
		s.put(1000000000L, "milijardasis");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtas");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtas");

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.V, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, false);
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

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, false);
	}
	
	@Test
	public void testVirs1000VMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantoji");
		s.put(1018L, "tūkstantis aštuoniolikta");
		s.put(2000L, "du tūkstantoji");
		s.put(3500L, "trys tūkstančiai penki šimtoji");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečia");
		s.put(10000L, "dešimt tūkstantoji");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečia");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimta");
		s.put(100000L, "šimtas tūkstantoji");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirta");
		s.put(1000000L, "milijonoji");
		s.put(2000000L, "du milijonoji");
		s.put(235000000L, "du šimtai trisdešimt penki milijonoji");
		s.put(1000000000L, "milijardoji");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirta");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirta");

		testSkaiciai(s, Skaicius.V, Linksnis.V, Gimine.M, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, false);
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

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testVirs1000KVyr() {
		// Samplaikinių kelintinių skaitvardžių visada linksniuojamas tik paskutinis žodis, pvz.: penki šimtai aštuoniasdešimt penktas (... penkta), penki šimtai aštuoniasdešimt penkto (... penktos) ir t. t.
		// Žr. http://ualgiman.dtiltas.lt/skaitvardis.html
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantojo");
		s.put(1018L, "tūkstantis aštuoniolikto");
		s.put(2000L, "du tūkstantojo");
		s.put(3500L, "trys tūkstančiai penki šimtojo");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečio");
		s.put(10000L, "dešimt tūkstantojo");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečio");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimto");
		s.put(100000L, "šimtas tūkstantojo");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirto");
		s.put(1000000L, "milijonojo");
		s.put(2000000L, "du milijonojo");
		s.put(235000000L, "du šimtai trisdešimt penki milijonojo");
		s.put(1000000000L, "milijardojo");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirto");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirto");

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.V, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, false);
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
		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, false);
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

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testVirs1000KMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantosios");
		s.put(1018L, "tūkstantis aštuonioliktos");
		s.put(2000L, "du tūkstantosios");
		s.put(3500L, "trys tūkstančiai penki šimtosios");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečios");
		s.put(10000L, "dešimt tūkstantosios");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečios");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtos");
		s.put(100000L, "šimtas tūkstantosios");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtos");
		s.put(1000000L, "milijonosios");
		s.put(2000000L, "du milijonosios");
		s.put(235000000L, "du šimtai trisdešimt penki milijonosios");
		s.put(1000000000L, "milijardosios");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtos");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtos");

		testSkaiciai(s, Skaicius.V, Linksnis.K, Gimine.M, false);
	}
	
	
	//-----------------------------------------------------------------------------------------
	
	@Test
	public void testNuo1Iki20DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliniai");
		s.put(1, "pirmi");
		s.put(2, "antri");
		s.put(3, "treti");
		s.put(4, "ketvirti");
		s.put(5, "penkti");
		s.put(6, "šešti");
		s.put(7, "septinti");
		s.put(8, "aštunti");
		s.put(9, "devinti");
		s.put(10, "dešimti");
		s.put(11, "vienuolikti");
		s.put(12, "dvylikti");
		s.put(13, "trylikti");
		s.put(14, "keturiolikti");
		s.put(15, "penkiolikti");
		s.put(16, "šešiolikti");
		s.put(17, "septyniolikti");
		s.put(18, "aštuoniolikti");
		s.put(19, "devyniolikti");
		s.put(20, "dvidešimti");		
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, false);
	}
	
	@Test
	public void testNuo20Iki100DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmi");
		s.put(22, "dvidešimt antri");
		s.put(24, "dvidešimt ketvirti");
		s.put(30, "trisdešimti");
		s.put(48, "keturiasdešimt aštunti");
		s.put(50, "penkiasdešimti");
		s.put(55, "penkiasdešimt penkti");
		s.put(60, "šešiasdešimti");
		s.put(70, "septyniasdešimti");
		s.put(80, "aštuoniasdešimti");
		s.put(90, "devyniasdešimti");
		s.put(92, "devyniasdešimt antri");
		s.put(100, "šimtieji");
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, false);
	}
	
	@Test
	public void testNuo100Iki1000DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmi");
		s.put(200, "du šimtieji");
		s.put(210, "du šimtai dešimti");
		s.put(300, "trys šimtieji");
		s.put(313, "trys šimtai trylikti");
		s.put(400, "keturi šimtieji");
		s.put(441, "keturi šimtai keturiasdešimt pirmi");
		s.put(500, "penki šimtieji");
		s.put(582, "penki šimtai aštuoniasdešimt antri");
		s.put(600, "šeši šimtieji");
		s.put(691, "šeši šimtai devyniasdešimt pirmi");
		s.put(700, "septyni šimtieji");
		s.put(715, "septyni šimtai penkiolikti");
		s.put(800, "aštuoni šimtieji");
		s.put(862, "aštuoni šimtai šešiasdešimt antri");
		s.put(900, "devyni šimtieji");
		s.put(963, "devyni šimtai šešiasdešimt treti");
		s.put(1000, "tūkstantieji");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, false);
	}
	
	@Test
	public void testVirs1000DgsVVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantieji");
		s.put(1018L, "tūkstantis aštuoniolikti");
		s.put(2000L, "du tūkstantieji");
		s.put(3500L, "trys tūkstančiai penki šimtieji");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt treti");
		s.put(10000L, "dešimt tūkstantieji");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt treti");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimti");
		s.put(100000L, "šimtas tūkstantieji");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirti");
		s.put(1000000L, "milijonieji");
		s.put(2000000L, "du milijonieji");
		s.put(235000000L, "du šimtai trisdešimt penki milijonieji");
		s.put(1000000000L, "milijardieji");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirti");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirti");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.V, false);
	}
	
	@Test
	public void testNuo1Iki20DgsVMot() {
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
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, false);
	}
	
	@Test
	public void testNuo20Iki100DgsVMot() {
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
		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, false);
	}
	
	@Test
	public void testNuo100Iki1000DgsVMot() {
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

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, false);
	}
	
	@Test
	public void testVirs1000DgsVMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantosios");
		s.put(1018L, "tūkstantis aštuonioliktos");
		s.put(2000L, "du tūkstantosios");
		s.put(3500L, "trys tūkstančiai penki šimtosios");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečios");
		s.put(10000L, "dešimt tūkstantosios");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečios");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtos");
		s.put(100000L, "šimtas tūkstantosios");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtos");
		s.put(1000000L, "milijonosios");
		s.put(2000000L, "du milijonosios");
		s.put(235000000L, "du šimtai trisdešimt penki milijonosios");
		s.put(1000000000L, "milijardosios");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtos");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtos");

		testSkaiciai(s, Skaicius.D, Linksnis.V, Gimine.M, false);
	}
	
	///-------	
	@Test
	public void testNuo1Iki20DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinių");
		s.put(1, "pirmų");
		s.put(2, "antrų");
		s.put(3, "trečių");
		s.put(4, "ketvirtų");
		s.put(5, "penktų");
		s.put(6, "šeštų");
		s.put(7, "septintų");
		s.put(8, "aštuntų");
		s.put(9, "devintų");
		s.put(10, "dešimtų");
		s.put(11, "vienuoliktų");
		s.put(12, "dvyliktų");
		s.put(13, "tryliktų");
		s.put(14, "keturioliktų");
		s.put(15, "penkioliktų");
		s.put(16, "šešioliktų");
		s.put(17, "septynioliktų");
		s.put(18, "aštuonioliktų");
		s.put(19, "devynioliktų");
		s.put(20, "dvidešimtų");		
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo20Iki100DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmų");
		s.put(22, "dvidešimt antrų");
		s.put(24, "dvidešimt ketvirtų");
		s.put(30, "trisdešimtų");
		s.put(48, "keturiasdešimt aštuntų");
		s.put(50, "penkiasdešimtų");
		s.put(55, "penkiasdešimt penktų");
		s.put(60, "šešiasdešimtų");
		s.put(70, "septyniasdešimtų");
		s.put(80, "aštuoniasdešimtų");
		s.put(90, "devyniasdešimtų");
		s.put(92, "devyniasdešimt antrų");
		s.put(100, "šimtųjų");
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo100Iki1000DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmų");
		s.put(200, "du šimtųjų");
		s.put(210, "du šimtai dešimtų");
		s.put(300, "trys šimtųjų");
		s.put(313, "trys šimtai tryliktų");
		s.put(400, "keturi šimtųjų");
		s.put(441, "keturi šimtai keturiasdešimt pirmų");
		s.put(500, "penki šimtųjų");
		s.put(582, "penki šimtai aštuoniasdešimt antrų");
		s.put(600, "šeši šimtųjų");
		s.put(691, "šeši šimtai devyniasdešimt pirmų");
		s.put(700, "septyni šimtųjų");
		s.put(715, "septyni šimtai penkioliktų");
		s.put(800, "aštuoni šimtųjų");
		s.put(862, "aštuoni šimtai šešiasdešimt antrų");
		s.put(900, "devyni šimtųjų");
		s.put(963, "devyni šimtai šešiasdešimt trečių");
		s.put(1000, "tūkstantųjų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testVirs1000DgsKVyr() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantųjų");
		s.put(1018L, "tūkstantis aštuonioliktų");
		s.put(2000L, "du tūkstantųjų");
		s.put(3500L, "trys tūkstančiai penki šimtųjų");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečių");
		s.put(10000L, "dešimt tūkstantųjų");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečių");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtų");
		s.put(100000L, "šimtas tūkstantųjų");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtų");
		s.put(1000000L, "milijonųjų");
		s.put(2000000L, "du milijonųjų");
		s.put(235000000L, "du šimtai trisdešimt penki milijonųjų");
		s.put(1000000000L, "milijardųjų");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtų");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.V, false);
	}
	
	@Test
	public void testNuo1Iki20DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulinių");
		s.put(1, "pirmų");
		s.put(2, "antrų");
		s.put(3, "trečių");
		s.put(4, "ketvirtų");
		s.put(5, "penktų");
		s.put(6, "šeštų");
		s.put(7, "septintų");
		s.put(8, "aštuntų");
		s.put(9, "devintų");
		s.put(10, "dešimtų");
		s.put(11, "vienuoliktų");
		s.put(12, "dvyliktų");
		s.put(13, "tryliktų");
		s.put(14, "keturioliktų");
		s.put(15, "penkioliktų");
		s.put(16, "šešioliktų");
		s.put(17, "septynioliktų");
		s.put(18, "aštuonioliktų");
		s.put(19, "devynioliktų");
		s.put(20, "dvidešimtų");		
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testNuo20Iki100DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt pirmų");
		s.put(22, "dvidešimt antrų");
		s.put(24, "dvidešimt ketvirtų");
		s.put(30, "trisdešimtų");
		s.put(48, "keturiasdešimt aštuntų");
		s.put(50, "penkiasdešimtų");
		s.put(55, "penkiasdešimt penktų");
		s.put(60, "šešiasdešimtų");
		s.put(70, "septyniasdešimtų");
		s.put(80, "aštuoniasdešimtų");
		s.put(90, "devyniasdešimtų");
		s.put(92, "devyniasdešimt antrų");
		s.put(100, "šimtųjų");
		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testNuo100Iki1000DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas pirmų");
		s.put(200, "du šimtųjų");
		s.put(210, "du šimtai dešimtų");
		s.put(300, "trys šimtųjų");
		s.put(313, "trys šimtai tryliktų");
		s.put(400, "keturi šimtųjų");
		s.put(441, "keturi šimtai keturiasdešimt pirmų");
		s.put(500, "penki šimtųjų");
		s.put(582, "penki šimtai aštuoniasdešimt antrų");
		s.put(600, "šeši šimtųjų");
		s.put(691, "šeši šimtai devyniasdešimt pirmų");
		s.put(700, "septyni šimtųjų");
		s.put(715, "septyni šimtai penkioliktų");
		s.put(800, "aštuoni šimtųjų");
		s.put(862, "aštuoni šimtai šešiasdešimt antrų");
		s.put(900, "devyni šimtųjų");
		s.put(963, "devyni šimtai šešiasdešimt trečių");
		s.put(1000, "tūkstantųjų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, false);
	}
	
	@Test
	public void testVirs1000DgsKMot() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantųjų");
		s.put(1018L, "tūkstantis aštuonioliktų");
		s.put(2000L, "du tūkstantųjų");
		s.put(3500L, "trys tūkstančiai penki šimtųjų");
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trečių");
		s.put(10000L, "dešimt tūkstantųjų");
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trečių");
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimtų");
		s.put(100000L, "šimtas tūkstantųjų");
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt ketvirtų");
		s.put(1000000L, "milijonųjų");
		s.put(2000000L, "du milijonųjų");
		s.put(235000000L, "du šimtai trisdešimt penki milijonųjų");
		s.put(1000000000L, "milijardųjų");
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt ketvirtų");
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt ketvirtų");

		testSkaiciai(s, Skaicius.D, Linksnis.K, Gimine.M, false);
	}
}
