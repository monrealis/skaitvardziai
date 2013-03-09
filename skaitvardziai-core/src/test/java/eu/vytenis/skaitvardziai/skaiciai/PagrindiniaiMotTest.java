package eu.vytenis.skaitvardziai.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;



public class PagrindiniaiMotTest extends PagrindiniaiTest {
	
	public PagrindiniaiMotTest() {
		setGimine(Gimine.M);
	}

	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulis" + motDgsK());
		s.put(1, "viena" + motVnsV());
		s.put(2, "dvi" + motDgsV());
		s.put(3, "trys" + motDgsV());
		s.put(4, "keturios" + motDgsV());
		s.put(5, "penkios" + motDgsV());
		s.put(6, "šešios" + motDgsV());
		s.put(7, "septynios" + motDgsV());
		s.put(8, "aštuonios" + motDgsV());
		s.put(9, "devynios" + motDgsV());
		s.put(10, "dešimt" + motDgsK());
		s.put(11, "vienuolika" + motDgsK());
		s.put(12, "dvylika" + motDgsK());
		s.put(13, "trylika" + motDgsK());
		s.put(14, "keturiolika" + motDgsK());
		s.put(15, "penkiolika" + motDgsK());
		s.put(16, "šešiolika" + motDgsK());
		s.put(17, "septyniolika" + motDgsK());
		s.put(18, "aštuoniolika" + motDgsK());
		s.put(19, "devyniolika" + motDgsK());
		s.put(20, "dvidešimt" + motDgsK());
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo20Iki100V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt viena" + motVnsV());
		s.put(22, "dvidešimt dvi" + motDgsV());
		s.put(24, "dvidešimt keturios" + motDgsV());
		s.put(30, "trisdešimt" + motDgsK());
		s.put(48, "keturiasdešimt aštuonios" + motDgsV());
		s.put(50, "penkiasdešimt" + motDgsK());
		s.put(55, "penkiasdešimt penkios" + motDgsV());
		s.put(60, "šešiasdešimt" + motDgsK());
		s.put(70, "septyniasdešimt" + motDgsK());
		s.put(80, "aštuoniasdešimt" + motDgsK());
		s.put(90, "devyniasdešimt" + motDgsK());
		s.put(92, "devyniasdešimt dvi" + motDgsV());
		s.put(100, "šimtas" + motDgsK());
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas viena" + motVnsV());
		s.put(200, "du šimtai" + motDgsK());
		s.put(210, "du šimtai dešimt" + motDgsK());
		s.put(300, "trys šimtai" + motDgsK());
		s.put(313, "trys šimtai trylika" + motDgsK());
		s.put(400, "keturi šimtai" + motDgsK());
		s.put(441, "keturi šimtai keturiasdešimt viena" + motVnsV());
		s.put(500, "penki šimtai" + motDgsK());
		s.put(582, "penki šimtai aštuoniasdešimt dvi" + motDgsV());
		s.put(600, "šeši šimtai" + motDgsK());
		s.put(691, "šeši šimtai devyniasdešimt viena" + motVnsV());
		s.put(700, "septyni šimtai" + motDgsK());
		s.put(715, "septyni šimtai penkiolika" + motDgsK());
		s.put(800, "aštuoni šimtai" + motDgsK());
		s.put(862, "aštuoni šimtai šešiasdešimt dvi" + motDgsV());
		s.put(900, "devyni šimtai" + motDgsK());
		s.put(963, "devyni šimtai šešiasdešimt trys" + motDgsV());
		s.put(1000, "tūkstantis" + motDgsK());

		testSkaiciai(s, Linksnis.V);
	}
	

	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulio" + motDgsK());
		s.put(1, "vienos" + motVnsK());
		s.put(2, "dviejų" + motDgsK());
		s.put(3, "trijų" + motDgsK());
		s.put(4, "keturių" + motDgsK());
		s.put(5, "penkių" + motDgsK());
		s.put(6, "šešių" + motDgsK());
		s.put(7, "septynių" + motDgsK());
		s.put(8, "aštuonių" + motDgsK());
		s.put(9, "devynių" + motDgsK());
		s.put(10, "dešimties" + motDgsK());
		s.put(11, "vienuolikos" + motDgsK());
		s.put(12, "dvylikos" + motDgsK());
		s.put(13, "trylikos" + motDgsK());
		s.put(14, "keturiolikos" + motDgsK());
		s.put(15, "penkiolikos" + motDgsK());
		s.put(16, "šešiolikos" + motDgsK());
		s.put(17, "septyniolikos" + motDgsK());
		s.put(18, "aštuoniolikos" + motDgsK());
		s.put(19, "devyniolikos" + motDgsK());
		s.put(20, "dvidešimties" + motDgsK());		
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo20Iki100K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vienos" + motVnsK());
		s.put(22, "dvidešimt dviejų" + motDgsK());
		s.put(24, "dvidešimt keturių" + motDgsK());
		s.put(30, "trisdešimties" + motDgsK());
		s.put(48, "keturiasdešimt aštuonių" + motDgsK());
		s.put(50, "penkiasdešimties" + motDgsK());
		s.put(55, "penkiasdešimt penkių" + motDgsK());
		s.put(60, "šešiasdešimties" + motDgsK());
		s.put(70, "septyniasdešimties" + motDgsK());
		s.put(80, "aštuoniasdešimties" + motDgsK());
		s.put(90, "devyniasdešimties" + motDgsK());
		s.put(92, "devyniasdešimt dviejų" + motDgsK());
		s.put(100, "šimto");
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimto vienos" + motVnsK());
		s.put(200, "dviejų šimtų" + motDgsK());
		s.put(210, "dviejų šimtų dešimties" + motDgsK());
		s.put(300, "trijų šimtų" + motDgsK());
		s.put(313, "trijų šimtų trylikos" + motDgsK());
		s.put(400, "keturių šimtų" + motDgsK());
		s.put(441, "keturių šimtų keturiasdešimt vienos" + motVnsK());
		s.put(500, "penkių šimtų" + motDgsK());
		s.put(582, "penkių šimtų aštuoniasdešimt dviejų" + motDgsK());
		s.put(600, "šešių šimtų" + motDgsK());
		s.put(691, "šešių šimtų devyniasdešimt vienos" + motVnsK());
		s.put(700, "septynių šimtų" + motDgsK());
		s.put(715, "septynių šimtų penkiolikos" + motDgsK());
		s.put(800, "aštuonių šimtų" + motDgsK());
		s.put(862, "aštuonių šimtų šešiasdešimt dviejų" + motDgsK());
		s.put(900, "devynių šimtų" + motDgsK());
		s.put(963, "devynių šimtų šešiasdešimt trijų" + motDgsK());
		s.put(1000, "tūkstančio" + motDgsK());

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

}
