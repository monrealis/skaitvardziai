package eu.vytenis.skaitvardziai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;



public class PagrindiniaiMotTest {
	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			SveikasisSkaicius sk = new SveikasisSkaicius(number);
					
			Assert.assertEquals("Invalid text", expected, sk.toString(linksnis, Gimine.M));
		}
	}
	
	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulis");
		s.put(1, "viena");
		s.put(2, "dvi");
		s.put(3, "trys");
		s.put(4, "keturios");
		s.put(5, "penkios");
		s.put(6, "šešios");
		s.put(7, "septynios");
		s.put(8, "aštuonios");
		s.put(9, "devynios");
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
		s.put(21, "dvidešimt viena");
		s.put(22, "dvidešimt dvi");
		s.put(24, "dvidešimt keturios");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonios");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkios");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvi");
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas viena");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt viena");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dvi");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt viena");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dvi");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.V);
	}
	

	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulio");
		s.put(1, "vienos");
		s.put(2, "dviejų");
		s.put(3, "trijų");
		s.put(4, "keturių");
		s.put(5, "penkių");
		s.put(6, "šešių");
		s.put(7, "septynių");
		s.put(8, "aštuonių");
		s.put(9, "devynių");
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
		s.put(21, "dvidešimt vienos");
		s.put(22, "dvidešimt dviejų");
		s.put(24, "dvidešimt keturių");
		s.put(30, "trisdešimties");
		s.put(48, "keturiasdešimt aštuonių");
		s.put(50, "penkiasdešimties");
		s.put(55, "penkiasdešimt penkių");
		s.put(60, "šešiasdešimties");
		s.put(70, "septyniasdešimties");
		s.put(80, "aštuoniasdešimties");
		s.put(90, "devyniasdešimties");
		s.put(92, "devyniasdešimt dviejų");
		s.put(100, "šimto");
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimto vienos");
		s.put(200, "dviejų šimtų");
		s.put(210, "dviejų šimtų dešimties");
		s.put(300, "trijų šimtų");
		s.put(313, "trijų šimtų trylikos");
		s.put(400, "keturių šimtų");
		s.put(441, "keturių šimtų keturiasdešimt vienos");
		s.put(500, "penkių šimtų");
		s.put(582, "penkių šimtų aštuoniasdešimt dviejų");
		s.put(600, "šešių šimtų");
		s.put(691, "šešių šimtų devyniasdešimt vienos");
		s.put(700, "septynių šimtų");
		s.put(715, "septynių šimtų penkiolikos");
		s.put(800, "aštuonių šimtų");
		s.put(862, "aštuonių šimtų šešiasdešimt dviejų");
		s.put(900, "devynių šimtų");
		s.put(963, "devynių šimtų šešiasdešimt trijų");
		s.put(1000, "tūkstančio");

		testSkaiciai(s, Linksnis.K);
	}
	
	
	@Test
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliui");
		s.put(1, "vienai");
		s.put(2, "dviem");
		s.put(3, "trims");
		s.put(4, "keturioms");
		s.put(5, "penkioms");
		s.put(6, "šešioms");
		s.put(7, "septynioms");
		s.put(8, "aštuonioms");
		s.put(9, "devynioms");
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
		s.put(21, "dvidešimt vienai");
		s.put(22, "dvidešimt dviem");
		s.put(24, "dvidešimt keturioms");
		s.put(30, "trisdešimčiai");
		s.put(48, "keturiasdešimt aštuonioms");
		s.put(50, "penkiasdešimčiai");
		s.put(55, "penkiasdešimt penkioms");
		s.put(60, "šešiasdešimčiai");
		s.put(70, "septyniasdešimčiai");
		s.put(80, "aštuoniasdešimčiai");
		s.put(90, "devyniasdešimčiai");
		s.put(92, "devyniasdešimt dviem");
		s.put(100, "šimtui");
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo100Iki1000N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtui vienai");
		s.put(200, "dviem šimtams");
		s.put(210, "dviem šimtams dešimčiai");
		s.put(300, "trims šimtams");
		s.put(313, "trims šimtams trylikai");
		s.put(400, "keturiems šimtams");
		s.put(441, "keturiems šimtams keturiasdešimt vienai");
		s.put(500, "penkiems šimtams");
		s.put(582, "penkiems šimtams aštuoniasdešimt dviem");
		s.put(600, "šešiems šimtams");
		s.put(691, "šešiems šimtams devyniasdešimt vienai");
		s.put(700, "septyniems šimtams");
		s.put(715, "septyniems šimtams penkiolikai");
		s.put(800, "aštuoniems šimtams");
		s.put(862, "aštuoniems šimtams šešiasdešimt dviem");
		s.put(900, "devyniems šimtams");
		s.put(963, "devyniems šimtams šešiasdešimt trims");
		s.put(1000, "tūkstančiui");

		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo1Iki20G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulį");
		s.put(1, "vieną");
		s.put(2, "dvi");
		s.put(3, "tris");
		s.put(4, "keturias");
		s.put(5, "penkias");
		s.put(6, "šešias");
		s.put(7, "septynias");
		s.put(8, "aštuonias");
		s.put(9, "devynias");
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
		s.put(21, "dvidešimt vieną");
		s.put(22, "dvidešimt dvi");
		s.put(24, "dvidešimt keturias");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonias");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkias");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvi");
		s.put(100, "šimtą");
		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo100Iki1000G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtą vieną");
		s.put(200, "du šimtus");
		s.put(210, "du šimtus dešimt");
		s.put(300, "tris šimtus");
		s.put(313, "tris šimtus trylika");
		s.put(400, "keturis šimtus");
		s.put(441, "keturis šimtus keturiasdešimt vieną");
		s.put(500, "penkis šimtus");
		s.put(582, "penkis šimtus aštuoniasdešimt dvi");
		s.put(600, "šešis šimtus");
		s.put(691, "šešis šimtus devyniasdešimt vieną");
		s.put(700, "septynis šimtus");
		s.put(715, "septynis šimtus penkiolika");
		s.put(800, "aštuonis šimtus");
		s.put(862, "aštuonis šimtus šešiasdešimt dvi");
		s.put(900, "devynis šimtus");
		s.put(963, "devynis šimtus šešiasdešimt tris");
		s.put(1000, "tūkstantį");

		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo1Iki20I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliu");
		s.put(1, "viena");
		s.put(2, "dviem");
		s.put(3, "trimis");
		s.put(4, "keturiomis");
		s.put(5, "penkiomis");
		s.put(6, "šešiomis");
		s.put(7, "septyniomis");
		s.put(8, "aštuoniomis");
		s.put(9, "devyniomis");
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
		s.put(21, "dvidešimt viena");
		s.put(22, "dvidešimt dviem");
		s.put(24, "dvidešimt keturiomis");
		s.put(30, "trisdešimčia");
		s.put(48, "keturiasdešimt aštuoniomis");
		s.put(50, "penkiasdešimčia");
		s.put(55, "penkiasdešimt penkiomis");
		s.put(60, "šešiasdešimčia");
		s.put(70, "septyniasdešimčia");
		s.put(80, "aštuoniasdešimčia");
		s.put(90, "devyniasdešimčia");
		s.put(92, "devyniasdešimt dviem");
		s.put(100, "šimtu");
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo100Iki1000I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtu viena");
		s.put(200, "dviem šimtais");
		s.put(210, "dviem šimtais dešimčia");
		s.put(300, "trimis šimtais");
		s.put(313, "trimis šimtais trylika");
		s.put(400, "keturiais šimtais");
		s.put(441, "keturiais šimtais keturiasdešimt viena");
		s.put(500, "penkiais šimtais");
		s.put(582, "penkiais šimtais aštuoniasdešimt dviem");
		s.put(600, "šešiais šimtais");
		s.put(691, "šešiais šimtais devyniasdešimt viena");
		s.put(700, "septyniais šimtais");
		s.put(715, "septyniais šimtais penkiolika");
		s.put(800, "aštuoniais šimtais");
		s.put(862, "aštuoniais šimtais šešiasdešimt dviem");
		s.put(900, "devyniais šimtais");
		s.put(963, "devyniais šimtais šešiasdešimt trimis");
		s.put(1000, "tūkstančiu");

		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo1Iki20Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulyje");
		s.put(1, "vienoje");
		s.put(2, "dviejose");
		s.put(3, "trijose");
		s.put(4, "keturiose");
		s.put(5, "penkiose");
		s.put(6, "šešiose");
		s.put(7, "septyniose");
		s.put(8, "aštuoniose");
		s.put(9, "devyniose");
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
		s.put(21, "dvidešimt vienoje");
		s.put(22, "dvidešimt dviejose");
		s.put(24, "dvidešimt keturiose");
		s.put(30, "trisdešimtyje");
		s.put(48, "keturiasdešimt aštuoniose");
		s.put(50, "penkiasdešimtyje");
		s.put(55, "penkiasdešimt penkiose");
		s.put(60, "šešiasdešimtyje");
		s.put(70, "septyniasdešimtyje");
		s.put(80, "aštuoniasdešimtyje");
		s.put(90, "devyniasdešimtyje");
		s.put(92, "devyniasdešimt dviejose");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo100Iki1000Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vienoje");
		s.put(200, "dviejuose šimtuose");
		s.put(210, "dviejuose šimtuose dešimtyje");
		s.put(300, "trijuose šimtuose");
		s.put(313, "trijuose šimtuose trylikoje");
		s.put(400, "keturiuose šimtuose");
		s.put(441, "keturiuose šimtuose keturiasdešimt vienoje");
		s.put(500, "penkiuose šimtuose");
		s.put(582, "penkiuose šimtuose aštuoniasdešimt dviejose");
		s.put(600, "šešiuose šimtuose");
		s.put(691, "šešiuose šimtuose devyniasdešimt vienoje");
		s.put(700, "septyniuose šimtuose");
		s.put(715, "septyniuose šimtuose penkiolikoje");
		s.put(800, "aštuoniuose šimtuose");
		s.put(862, "aštuoniuose šimtuose šešiasdešimt dviejose");
		s.put(900, "devyniuose šimtuose");
		s.put(963, "devyniuose šimtuose šešiasdešimt trijose");
		s.put(1000, "tūkstantyje");

		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo1Iki20S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuli");
		s.put(1, "viena");
		s.put(2, "dvi");
		s.put(3, "trys");
		s.put(4, "keturios");
		s.put(5, "penkios");
		s.put(6, "šešios");
		s.put(7, "septynios");
		s.put(8, "aštuonios");
		s.put(9, "devynios");
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
		s.put(21, "dvidešimt viena");
		s.put(22, "dvidešimt dvi");
		s.put(24, "dvidešimt keturios");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonios");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkios");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvi");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testNuo100Iki1000S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte viena");
		s.put(200, "du šimtai");
		s.put(210, "du šimtai dešimt");
		s.put(300, "trys šimtai");
		s.put(313, "trys šimtai trylika");
		s.put(400, "keturi šimtai");
		s.put(441, "keturi šimtai keturiasdešimt viena");
		s.put(500, "penki šimtai");
		s.put(582, "penki šimtai aštuoniasdešimt dvi");
		s.put(600, "šeši šimtai");
		s.put(691, "šeši šimtai devyniasdešimt viena");
		s.put(700, "septyni šimtai");
		s.put(715, "septyni šimtai penkiolika");
		s.put(800, "aštuoni šimtai");
		s.put(862, "aštuoni šimtai šešiasdešimt dvi");
		s.put(900, "devyni šimtai");
		s.put(963, "devyni šimtai šešiasdešimt trys");
		s.put(1000, "tūkstanti");

		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testOutput() {
		System.out.println(new SveikasisSkaicius(2256));
		System.out.println(new SveikasisSkaicius(12256));
		/*for (int i = 10000; i <= 100000; i+=1000) {
			System.out.println(new SkaiciusXPathFunctions(i));
		}*/
	}
	
}
