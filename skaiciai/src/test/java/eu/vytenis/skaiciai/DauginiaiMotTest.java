package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.klasifikatoriai.Gimine;
import eu.vytenis.skaiciai.klasifikatoriai.Linksnis;
import eu.vytenis.skaiciai.klasifikatoriai.Poskyris;


public class DauginiaiMotTest {
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			SveikasSkaicius sk = new SveikasSkaicius(number);
					
			Assert.assertEquals("Invalid text", expected, sk.toString(Poskyris.Dauginis, linksnis, Gimine.M));
		}
	}
	
	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerios");
		s.put(2, "dvejos");
		s.put(3, "trejos");
		s.put(4, "ketverios");
		s.put(5, "penkerios");
		s.put(6, "šešerios");
		s.put(7, "septynerios");
		s.put(8, "aštuonerios");
		s.put(9, "devynerios");
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
		s.put(21, "dvidešimt vienerios");
		s.put(22, "dvidešimt dvejos");
		s.put(24, "dvidešimt ketverios");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonerios");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkerios");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvejos");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vienerios");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienerios");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dvejos");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienerios");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dvejos");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trejos");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerių");
		s.put(2, "dvejų");
		s.put(3, "trejų");
		s.put(4, "ketverių");
		s.put(5, "penkerių");
		s.put(6, "šešerių");
		s.put(7, "septynerių");
		s.put(8, "aštuonerių");
		s.put(9, "devynerių");
		s.put(10, "dešimties");
		s.put(11, "vienuolikos");
		s.put(12, "dvylikos");
		s.put(13, "trylikos");
		s.put(14, "keturiolikos");
		s.put(15, "penkiolikos");
		s.put(16, "šešiolikos");
		s.put(17, "septyniolikos");
		s.put(18, "aštuoniolikos");
		s.put(19, "devyniolikos");
		s.put(20, "dvidešimties");		
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo20Iki100K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vienerių");
		s.put(22, "dvidešimt dvejų");
		s.put(24, "dvidešimt ketverių");
		s.put(30, "trisdešimties");
		s.put(48, "keturiasdešimt aštuonerių");
		s.put(50, "penkiasdešimties");
		s.put(55, "penkiasdešimt penkerių");
		s.put(60, "šešiasdešimties");
		s.put(70, "septyniasdešimties");
		s.put(80, "aštuoniasdešimties");
		s.put(90, "devyniasdešimties");
		s.put(92, "devyniasdešimt dvejų");
		s.put(100, "šimto");
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimto vienerių");
		s.put(200, "dviejų šimtų");
		s.put(210, "dviejų šimtų dešimties");
		s.put(300, "trijų šimtų");
		s.put(313, "trijų šimtų trylikos");
		s.put(400, "keturių šimtų");
		s.put(441, "keturių šimtų keturiasdešimt vienerių");
		s.put(500, "penkių šimtų");
		s.put(582, "penkių šimtų aštuoniasdešimt dvejų");
		s.put(600, "šešių šimtų");
		s.put(691, "šešių šimtų devyniasdešimt vienerių");
		s.put(700, "septynių šimtų");
		s.put(715, "septynių šimtų penkiolikos");
		s.put(800, "aštuonių šimtų");
		s.put(862, "aštuonių šimtų šešiasdešimt dvejų");
		s.put(900, "devynių šimtų");
		s.put(963, "devynių šimtų šešiasdešimt trejų");
		s.put(1000, "tūkstančio");

		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerioms");
		s.put(2, "dvejoms");
		s.put(3, "trejoms");
		s.put(4, "ketverioms");
		s.put(5, "penkerioms");
		s.put(6, "šešerioms");
		s.put(7, "septynerioms");
		s.put(8, "aštuonerioms");
		s.put(9, "devynerioms");
		s.put(10, "dešimčiai");
		s.put(11, "vienuolikai");
		s.put(12, "dvylikai");
		s.put(13, "trylikai");
		s.put(14, "keturiolikai");
		s.put(15, "penkiolikai");
		s.put(16, "šešiolikai");
		s.put(17, "septyniolikai");
		s.put(18, "aštuoniolikai");
		s.put(19, "devyniolikai");
		s.put(20, "dvidešimčiai");		
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo20Iki100N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vienerioms");
		s.put(22, "dvidešimt dvejoms");
		s.put(24, "dvidešimt ketverioms");
		s.put(30, "trisdešimčiai");
		s.put(48, "keturiasdešimt aštuonerioms");
		s.put(50, "penkiasdešimčiai");
		s.put(55, "penkiasdešimt penkerioms");
		s.put(60, "šešiasdešimčiai");
		s.put(70, "septyniasdešimčiai");
		s.put(80, "aštuoniasdešimčiai");
		s.put(90, "devyniasdešimčiai");
		s.put(92, "devyniasdešimt dvejoms");
		s.put(100, "šimtui");
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo100Iki1000N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtui vienerioms");
		s.put(200, "dviem šimtams");
		s.put(210, "dviem šimtams dešimčiai");
		s.put(300, "trims šimtams");
		s.put(313, "trims šimtams trylikai");
		s.put(400, "keturiems šimtams");
		s.put(441, "keturiems šimtams keturiasdešimt vienerioms");
		s.put(500, "penkiems šimtams");
		s.put(582, "penkiems šimtams aštuoniasdešimt dvejoms");
		s.put(600, "šešiems šimtams");
		s.put(691, "šešiems šimtams devyniasdešimt vienerioms");
		s.put(700, "septyniems šimtams");
		s.put(715, "septyniems šimtams penkiolikai");
		s.put(800, "aštuoniems šimtams");
		s.put(862, "aštuoniems šimtams šešiasdešimt dvejoms");
		s.put(900, "devyniems šimtams");
		s.put(963, "devyniems šimtams šešiasdešimt trejoms");
		s.put(1000, "tūkstančiui");

		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo1Iki20G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerias");
		s.put(2, "dvejas");
		s.put(3, "trejas");
		s.put(4, "ketverias");
		s.put(5, "penkerias");
		s.put(6, "šešerias");
		s.put(7, "septynerias");
		s.put(8, "aštuonerias");
		s.put(9, "devynerias");
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
		s.put(21, "dvidešimt vienerias");
		s.put(22, "dvidešimt dvejas");
		s.put(24, "dvidešimt ketverias");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonerias");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkerias");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvejas");
		s.put(100, "šimtą");
		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo100Iki1000G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtą vienerias");
		s.put(200, "du šimtus");
		s.put(210, "du šimtus dešimt");
		s.put(300, "tris šimtus");
		s.put(313, "tris šimtus trylika");
		s.put(400, "keturis šimtus");
		s.put(441, "keturis šimtus keturiasdešimt vienerias");
		s.put(500, "penkis šimtus");
		s.put(582, "penkis šimtus aštuoniasdešimt dvejas");
		s.put(600, "šešis šimtus");
		s.put(691, "šešis šimtus devyniasdešimt vienerias");
		s.put(700, "septynis šimtus");
		s.put(715, "septynis šimtus penkiolika");
		s.put(800, "aštuonis šimtus");
		s.put(862, "aštuonis šimtus šešiasdešimt dvejas");
		s.put(900, "devynis šimtus");
		s.put(963, "devynis šimtus šešiasdešimt trejas");
		s.put(1000, "tūkstantį");

		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo1Iki20I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneriomis");
		s.put(2, "dvejomis");
		s.put(3, "trejomis");
		s.put(4, "ketveriomis");
		s.put(5, "penkeriomis");
		s.put(6, "šešeriomis");
		s.put(7, "septyneriomis");
		s.put(8, "aštuoneriomis");
		s.put(9, "devyneriomis");
		s.put(10, "dešimčia");
		s.put(11, "vienuolika");
		s.put(12, "dvylika");
		s.put(13, "trylika");
		s.put(14, "keturiolika");
		s.put(15, "penkiolika");
		s.put(16, "šešiolika");
		s.put(17, "septyniolika");
		s.put(18, "aštuoniolika");
		s.put(19, "devyniolika");
		s.put(20, "dvidešimčia");		
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo20Iki100I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneriomis");
		s.put(22, "dvidešimt dvejomis");
		s.put(24, "dvidešimt ketveriomis");
		s.put(30, "trisdešimčia");
		s.put(48, "keturiasdešimt aštuoneriomis");
		s.put(50, "penkiasdešimčia");
		s.put(55, "penkiasdešimt penkeriomis");
		s.put(60, "šešiasdešimčia");
		s.put(70, "septyniasdešimčia");
		s.put(80, "aštuoniasdešimčia");
		s.put(90, "devyniasdešimčia");
		s.put(92, "devyniasdešimt dvejomis");
		s.put(100, "šimtu");
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo100Iki1000I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtu vieneriomis");
		s.put(200, "dviem šimtais");
		s.put(210, "dviem šimtais dešimčia");
		s.put(300, "trimis šimtais");
		s.put(313, "trimis šimtais trylika");
		s.put(400, "keturiais šimtais");
		s.put(441, "keturiais šimtais keturiasdešimt vieneriomis");
		s.put(500, "penkiais šimtais");
		s.put(582, "penkiais šimtais aštuoniasdešimt dvejomis");
		s.put(600, "šešiais šimtais");
		s.put(691, "šešiais šimtais devyniasdešimt vieneriomis");
		s.put(700, "septyniais šimtais");
		s.put(715, "septyniais šimtais penkiolika");
		s.put(800, "aštuoniais šimtais");
		s.put(862, "aštuoniais šimtais šešiasdešimt dvejomis");
		s.put(900, "devyniais šimtais");
		s.put(963, "devyniais šimtais šešiasdešimt trejomis");
		s.put(1000, "tūkstančiu");

		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo1Iki20Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneriose");
		s.put(2, "dvejose");
		s.put(3, "trejose");
		s.put(4, "ketveriose");
		s.put(5, "penkeriose");
		s.put(6, "šešeriose");
		s.put(7, "septyneriose");
		s.put(8, "aštuoneriose");
		s.put(9, "devyneriose");
		s.put(10, "dešimtyje");
		s.put(11, "vienuolikoje");
		s.put(12, "dvylikoje");
		s.put(13, "trylikoje");
		s.put(14, "keturiolikoje");
		s.put(15, "penkiolikoje");
		s.put(16, "šešiolikoje");
		s.put(17, "septyniolikoje");
		s.put(18, "aštuoniolikoje");
		s.put(19, "devyniolikoje");
		s.put(20, "dvidešimtyje");		
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo20Iki100Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieneriose");
		s.put(22, "dvidešimt dvejose");
		s.put(24, "dvidešimt ketveriose");
		s.put(30, "trisdešimtyje");
		s.put(48, "keturiasdešimt aštuoneriose");
		s.put(50, "penkiasdešimtyje");
		s.put(55, "penkiasdešimt penkeriose");
		s.put(60, "šešiasdešimtyje");
		s.put(70, "septyniasdešimtyje");
		s.put(80, "aštuoniasdešimtyje");
		s.put(90, "devyniasdešimtyje");
		s.put(92, "devyniasdešimt dvejose");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo100Iki1000Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vieneriose");
		s.put(200, "dviejuose šimtuose");
		s.put(210, "dviejuose šimtuose dešimtyje");
		s.put(300, "trijuose šimtuose");
		s.put(313, "trijuose šimtuose trylikoje");
		s.put(400, "keturiuose šimtuose");
		s.put(441, "keturiuose šimtuose keturiasdešimt vieneriose");
		s.put(500, "penkiuose šimtuose");
		s.put(582, "penkiuose šimtuose aštuoniasdešimt dvejose");
		s.put(600, "šešiuose šimtuose");
		s.put(691, "šešiuose šimtuose devyniasdešimt vieneriose");
		s.put(700, "septyniuose šimtuose");
		s.put(715, "septyniuose šimtuose penkiolikoje");
		s.put(800, "aštuoniuose šimtuose");
		s.put(862, "aštuoniuose šimtuose šešiasdešimt dvejose");
		s.put(900, "devyniuose šimtuose");
		s.put(963, "devyniuose šimtuose šešiasdešimt trejose");
		s.put(1000, "tūkstantyje");

		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo1Iki20S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerios");
		s.put(2, "dvejos");
		s.put(3, "trejos");
		s.put(4, "ketverios");
		s.put(5, "penkerios");
		s.put(6, "šešerios");
		s.put(7, "septynerios");
		s.put(8, "aštuonerios");
		s.put(9, "devynerios");
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
		s.put(21, "dvidešimt vienerios");
		s.put(22, "dvidešimt dvejos");
		s.put(24, "dvidešimt ketverios");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonerios");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkerios");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvejos");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testNuo100Iki1000S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vienerios");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt vienerios");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dvejos");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt vienerios");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dvejos");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trejos");
		s.put(1000, "tūkstanti");

		testSkaiciai(s, Linksnis.S);
	}

}
