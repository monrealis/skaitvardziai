package eu.vytenis.skaitvardziai.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;



public class PagrindiniaiVyrTest extends PagrindiniaiTest {

	public PagrindiniaiVyrTest() {
		setGimine(Gimine.V);
	}
	
	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulis" + vyrDgsK());
		s.put(1, "vienas" + vyrVnsV());
		s.put(2, "du" + vyrDgsV());
		s.put(3, "trys" + vyrDgsV());
		s.put(4, "keturi" + vyrDgsV());
		s.put(5, "penki" + vyrDgsV());
		s.put(6, "šeši" + vyrDgsV());
		s.put(7, "septyni" + vyrDgsV());
		s.put(8, "aštuoni" + vyrDgsV());
		s.put(9, "devyni" + vyrDgsV());
		s.put(10, "dešimt" + vyrDgsK());
		s.put(11, "vienuolika" + vyrDgsK());
		s.put(12, "dvylika" + vyrDgsK());
		s.put(13, "trylika" + vyrDgsK());
		s.put(14, "keturiolika" + vyrDgsK());
		s.put(15, "penkiolika" + vyrDgsK());
		s.put(16, "šešiolika" + vyrDgsK());
		s.put(17, "septyniolika" + vyrDgsK());
		s.put(18, "aštuoniolika" + vyrDgsK());
		s.put(19, "devyniolika" + vyrDgsK());
		s.put(20, "dvidešimt" + vyrDgsK());		
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo20Iki100V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vienas" + vyrVnsV());
		s.put(22, "dvidešimt du" + vyrDgsV());
		s.put(24, "dvidešimt keturi" + vyrDgsV());
		s.put(30, "trisdešimt" + vyrDgsK());
		s.put(48, "keturiasdešimt aštuoni" + vyrDgsV());
		s.put(50, "penkiasdešimt" + vyrDgsK());
		s.put(55, "penkiasdešimt penki" + vyrDgsV());
		s.put(60, "šešiasdešimt" + vyrDgsK());
		s.put(70, "septyniasdešimt" + vyrDgsK());
		s.put(80, "aštuoniasdešimt" + vyrDgsK());
		s.put(90, "devyniasdešimt" + vyrDgsK());
		s.put(92, "devyniasdešimt du" + vyrDgsV());
		s.put(100, "šimtas" + vyrDgsK());
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vienas" + vyrVnsV());
		s.put(200, "du šimtai" + vyrDgsK());
		s.put(210, "du šimtai dešimt" + vyrDgsK());
		s.put(300, "trys šimtai" + vyrDgsK());
		s.put(313, "trys šimtai trylika" + vyrDgsK());
		s.put(400, "keturi šimtai" + vyrDgsK());
		s.put(441, "keturi šimtai keturiasdešimt vienas" + vyrVnsV());
		s.put(500, "penki šimtai" + vyrDgsK());
		s.put(582, "penki šimtai aštuoniasdešimt du"+ vyrDgsV());
		s.put(600, "šeši šimtai" + vyrDgsK());
		s.put(691, "šeši šimtai devyniasdešimt vienas" + vyrVnsV());
		s.put(700, "septyni šimtai" + vyrDgsK());
		s.put(715, "septyni šimtai penkiolika" + vyrDgsK());
		s.put(800, "aštuoni šimtai" + vyrDgsK());
		s.put(862, "aštuoni šimtai šešiasdešimt du" + vyrDgsV());
		s.put(900, "devyni šimtai" + vyrDgsK());
		s.put(963, "devyni šimtai šešiasdešimt trys" + vyrDgsV());
		s.put(1000, "tūkstantis" + vyrDgsK());

		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testVirs1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstantis" + vyrDgsK());
		s.put(1018L, "tūkstantis aštuoniolika" + vyrDgsK());
		s.put(2000L, "du tūkstančiai" + vyrDgsK());
		s.put(3500L, "trys tūkstančiai penki šimtai" + vyrDgsK());
		s.put(9823L, "devyni tūkstančiai aštuoni šimtai dvidešimt trys" + vyrDgsV());
		s.put(10000L, "dešimt tūkstančių" + vyrDgsK());
		s.put(11053L, "vienuolika tūkstančių penkiasdešimt trys" + vyrDgsV());
		s.put(25420L, "dvidešimt penki tūkstančiai keturi šimtai dvidešimt" + vyrDgsK());
		s.put(100000L, "šimtas tūkstančių" + vyrDgsK());
		s.put(215024L, "du šimtai penkiolika tūkstančių dvidešimt keturi" + vyrDgsV());
		s.put(1000000L, "milijonas" + vyrDgsK());
		s.put(2000000L, "du milijonai" + vyrDgsK());
		s.put(235000000L, "du šimtai trisdešimt penki milijonai" + vyrDgsK());
		s.put(1000000000L, "milijardas" + vyrDgsK());
		s.put(184116790224L, "šimtas aštuoniasdešimt keturi milijardai šimtas šešiolika milijonų septyni šimtai devyniasdešimt tūkstančių du šimtai dvidešimt keturi" + vyrDgsV());
		s.put(584356792124L, "penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt keturi" + vyrDgsV());
		
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulio" + vyrDgsK());
		s.put(1, "vieno" + vyrVnsK());
		s.put(2, "dviejų" + vyrDgsK());
		s.put(3, "trijų" + vyrDgsK());
		s.put(4, "keturių" + vyrDgsK());
		s.put(5, "penkių" + vyrDgsK());
		s.put(6, "šešių" + vyrDgsK());
		s.put(7, "septynių" + vyrDgsK());
		s.put(8, "aštuonių" + vyrDgsK());
		s.put(9, "devynių" + vyrDgsK());
		s.put(10, "dešimties" + vyrDgsK());
		s.put(11, "vienuolikos" + vyrDgsK());
		s.put(12, "dvylikos" + vyrDgsK());
		s.put(13, "trylikos" + vyrDgsK());
		s.put(14, "keturiolikos" + vyrDgsK());
		s.put(15, "penkiolikos" + vyrDgsK());
		s.put(16, "šešiolikos" + vyrDgsK());
		s.put(17, "septyniolikos" + vyrDgsK());
		s.put(18, "aštuoniolikos" + vyrDgsK());
		s.put(19, "devyniolikos" + vyrDgsK());
		s.put(20, "dvidešimties" + vyrDgsK());		
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo20Iki100K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(21, "dvidešimt vieno" + vyrVnsK());
		s.put(22, "dvidešimt dviejų" + vyrDgsK());
		s.put(24, "dvidešimt keturių" + vyrDgsK());
		s.put(30, "trisdešimties" + vyrDgsK());
		s.put(48, "keturiasdešimt aštuonių" + vyrDgsK());
		s.put(50, "penkiasdešimties" + vyrDgsK());
		s.put(55, "penkiasdešimt penkių" + vyrDgsK());
		s.put(60, "šešiasdešimties" + vyrDgsK());
		s.put(70, "septyniasdešimties" + vyrDgsK());
		s.put(80, "aštuoniasdešimties" + vyrDgsK());
		s.put(90, "devyniasdešimties" + vyrDgsK());
		s.put(92, "devyniasdešimt dviejų" + vyrDgsK());
		s.put(100, "šimto" + vyrDgsK());
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimto vieno" + vyrVnsK());
		s.put(200, "dviejų šimtų" + vyrDgsK());
		s.put(210, "dviejų šimtų dešimties" + vyrDgsK());
		s.put(300, "trijų šimtų" + vyrDgsK());
		s.put(313, "trijų šimtų trylikos" + vyrDgsK());
		s.put(400, "keturių šimtų" + vyrDgsK());
		s.put(441, "keturių šimtų keturiasdešimt vieno" + vyrVnsK());
		s.put(500, "penkių šimtų" + vyrDgsK());
		s.put(582, "penkių šimtų aštuoniasdešimt dviejų" + vyrDgsK());
		s.put(600, "šešių šimtų" + vyrDgsK());
		s.put(691, "šešių šimtų devyniasdešimt vieno" + vyrVnsK());
		s.put(700, "septynių šimtų" + vyrDgsK());
		s.put(715, "septynių šimtų penkiolikos" + vyrDgsK());
		s.put(800, "aštuonių šimtų" + vyrDgsK());
		s.put(862, "aštuonių šimtų šešiasdešimt dviejų" + vyrDgsK());
		s.put(900, "devynių šimtų" + vyrDgsK());
		s.put(963, "devynių šimtų šešiasdešimt trijų" + vyrDgsK());
		s.put(1000, "tūkstančio" + vyrDgsK());

		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testVirs1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1000L, "tūkstančio" + vyrDgsK());
		s.put(1018L, "tūkstančio aštuoniolikos" + vyrDgsK());
		s.put(2000L, "dviejų tūkstančių" + vyrDgsK());
		s.put(3500L, "trijų tūkstančių penkių šimtų" + vyrDgsK());
		s.put(9823L, "devynių tūkstančių aštuonių šimtų dvidešimt trijų" + vyrDgsK());
		s.put(10000L, "dešimties tūkstančių" + vyrDgsK());
		s.put(11053L, "vienuolikos tūkstančių penkiasdešimt trijų" + vyrDgsK());
		s.put(25420L, "dvidešimt penkių tūkstančių keturių šimtų dvidešimties" + vyrDgsK());
		s.put(100000L, "šimto tūkstančių" + vyrDgsK());
		s.put(215024L, "dviejų šimtų penkiolikos tūkstančių dvidešimt keturių" + vyrDgsK());
		s.put(1000000L, "milijono" + vyrDgsK());
		s.put(2000000L, "dviejų milijonų" + vyrDgsK());
		s.put(235000000L, "dviejų šimtų trisdešimt penkių milijonų" + vyrDgsK());
		s.put(1000000000L, "milijardo" + vyrDgsK());
		s.put(184116790224L, "šimto aštuoniasdešimt keturių milijardų šimto šešiolikos milijonų septynių šimtų devyniasdešimties tūkstančių dviejų šimtų dvidešimt keturių" + vyrDgsK());
		s.put(584356792124L, "penkių šimtų aštuoniasdešimt keturių milijardų trijų šimtų penkiasdešimt šešių milijonų septynių šimtų devyniasdešimt dviejų tūkstančių šimto dvidešimt keturių" + vyrDgsK());

		testSkaiciai(s, Linksnis.K);
	}

	
	@Test
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliui");
		s.put(1, "vienam");
		s.put(2, "dviem");
		s.put(3, "trims");
		s.put(4, "keturiems");
		s.put(5, "penkiems");
		s.put(6, "šešiems");
		s.put(7, "septyniems");
		s.put(8, "aštuoniems");
		s.put(9, "devyniems");
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
		s.put(21, "dvidešimt vienam");
		s.put(22, "dvidešimt dviem");
		s.put(24, "dvidešimt keturiems");
		s.put(30, "trisdešimčiai");
		s.put(48, "keturiasdešimt aštuoniems");
		s.put(50, "penkiasdešimčiai");
		s.put(55, "penkiasdešimt penkiems");
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
		s.put(101, "šimtui vienam");
		s.put(200, "dviem šimtams");
		s.put(210, "dviem šimtams dešimčiai");
		s.put(300, "trims šimtams");
		s.put(313, "trims šimtams trylikai");
		s.put(400, "keturiems šimtams");
		s.put(441, "keturiems šimtams keturiasdešimt vienam");
		s.put(500, "penkiems šimtams");
		s.put(582, "penkiems šimtams aštuoniasdešimt dviem");
		s.put(600, "šešiems šimtams");
		s.put(691, "šešiems šimtams devyniasdešimt vienam");
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
		s.put(2, "du");
		s.put(3, "tris");
		s.put(4, "keturis");
		s.put(5, "penkis");
		s.put(6, "šešis");
		s.put(7, "septynis");
		s.put(8, "aštuonis");
		s.put(9, "devynis");
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
		s.put(22, "dvidešimt du");
		s.put(24, "dvidešimt keturis");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonis");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkis");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt du");
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
		s.put(582, "penkis šimtus aštuoniasdešimt du");
		s.put(600, "šešis šimtus");
		s.put(691, "šešis šimtus devyniasdešimt vieną");
		s.put(700, "septynis šimtus");
		s.put(715, "septynis šimtus penkiolika");
		s.put(800, "aštuonis šimtus");
		s.put(862, "aštuonis šimtus šešiasdešimt du");
		s.put(900, "devynis šimtus");
		s.put(963, "devynis šimtus šešiasdešimt tris");
		s.put(1000, "tūkstantį");

		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo1Iki20I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliu");
		s.put(1, "vienu");
		s.put(2, "dviem");
		s.put(3, "trimis");
		s.put(4, "keturiais");
		s.put(5, "penkiais");
		s.put(6, "šešiais");
		s.put(7, "septyniais");
		s.put(8, "aštuoniais");
		s.put(9, "devyniais");
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
		s.put(21, "dvidešimt vienu");
		s.put(22, "dvidešimt dviem");
		s.put(24, "dvidešimt keturiais");
		s.put(30, "trisdešimčia");
		s.put(48, "keturiasdešimt aštuoniais");
		s.put(50, "penkiasdešimčia");
		s.put(55, "penkiasdešimt penkiais");
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
		s.put(101, "šimtu vienu");
		s.put(200, "dviem šimtais");
		s.put(210, "dviem šimtais dešimčia");
		s.put(300, "trimis šimtais");
		s.put(313, "trimis šimtais trylika");
		s.put(400, "keturiais šimtais");
		s.put(441, "keturiais šimtais keturiasdešimt vienu");
		s.put(500, "penkiais šimtais");
		s.put(582, "penkiais šimtais aštuoniasdešimt dviem");
		s.put(600, "šešiais šimtais");
		s.put(691, "šešiais šimtais devyniasdešimt vienu");
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
		s.put(1, "viename");
		s.put(2, "dviejuose");
		s.put(3, "trijuose");
		s.put(4, "keturiuose");
		s.put(5, "penkiuose");
		s.put(6, "šešiuose");
		s.put(7, "septyniuose");
		s.put(8, "aštuoniuose");
		s.put(9, "devyniuose");
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
		s.put(21, "dvidešimt viename");
		s.put(22, "dvidešimt dviejuose");
		s.put(24, "dvidešimt keturiuose");
		s.put(30, "trisdešimtyje");
		s.put(48, "keturiasdešimt aštuoniuose");
		s.put(50, "penkiasdešimtyje");
		s.put(55, "penkiasdešimt penkiuose");
		s.put(60, "šešiasdešimtyje");
		s.put(70, "septyniasdešimtyje");
		s.put(80, "aštuoniasdešimtyje");
		s.put(90, "devyniasdešimtyje");
		s.put(92, "devyniasdešimt dviejuose");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo100Iki1000Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte viename");
		s.put(200, "dviejuose šimtuose");
		s.put(210, "dviejuose šimtuose dešimtyje");
		s.put(300, "trijuose šimtuose");
		s.put(313, "trijuose šimtuose trylikoje");
		s.put(400, "keturiuose šimtuose");
		s.put(441, "keturiuose šimtuose keturiasdešimt viename");
		s.put(500, "penkiuose šimtuose");
		s.put(582, "penkiuose šimtuose aštuoniasdešimt dviejuose");
		s.put(600, "šešiuose šimtuose");
		s.put(691, "šešiuose šimtuose devyniasdešimt viename");
		s.put(700, "septyniuose šimtuose");
		s.put(715, "septyniuose šimtuose penkiolikoje");
		s.put(800, "aštuoniuose šimtuose");
		s.put(862, "aštuoniuose šimtuose šešiasdešimt dviejuose");
		s.put(900, "devyniuose šimtuose");
		s.put(963, "devyniuose šimtuose šešiasdešimt trijuose");
		s.put(1000, "tūkstantyje");

		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo1Iki20S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuli");
		s.put(1, "vienas");
		s.put(2, "du");
		s.put(3, "trys");
		s.put(4, "keturi");
		s.put(5, "penki");
		s.put(6, "šeši");
		s.put(7, "septyni");
		s.put(8, "aštuoni");
		s.put(9, "devyni");
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
		s.put(21, "dvidešimt vienas");
		s.put(22, "dvidešimt du");
		s.put(24, "dvidešimt keturi");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuoni");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penki");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt du");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.S);
	}
	
	@Test
	public void testNuo100Iki1000S() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vienas");
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
		s.put(1000, "tūkstanti");

		testSkaiciai(s, Linksnis.S);
	}

	@Test
	public void testRastosKlaidos() {		
		Map<Long, String> skaiciai = new TreeMap<Long, String>();
		skaiciai.put(4561234568L, "keturi milijardai penki šimtai šešiasdešimt vienas milijonas du šimtai trisdešimt keturi tūkstančiai penki šimtai šešiasdešimt aštuoni");
		skaiciai.put(21000000L, "dvidešimt vienas milijonas");
		testSkaiciai(skaiciai, Linksnis.V);
	}

}
