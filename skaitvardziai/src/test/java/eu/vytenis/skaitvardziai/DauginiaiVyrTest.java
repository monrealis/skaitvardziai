package eu.vytenis.skaitvardziai;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


public class DauginiaiVyrTest extends BaseTest {
	
	public DauginiaiVyrTest() {
	}
	
	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		Forma f = new Forma();
		f.setSkaicius(Skaicius.V);
		f.setPoskyris(Poskyris.Dauginis);
		f.setGimine(Gimine.V);
		f.setLinksnis(linksnis);
		
		super.testSkaiciai(skaiciai, f);
	}

	@Test
	public void testNuo1Iki20V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneri" + vyrDgsV());
		s.put(2, "dveji" + vyrDgsV());
		s.put(3, "treji" + vyrDgsV());
		s.put(4, "ketveri" + vyrDgsV());
		s.put(5, "penkeri" + vyrDgsV());
		s.put(6, "šešeri" + vyrDgsV());
		s.put(7, "septyneri" + vyrDgsV());
		s.put(8, "aštuoneri" + vyrDgsV());
		s.put(9, "devyneri" + vyrDgsV());
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
		s.put(21, "dvidešimt vieneri" + vyrDgsV());
		s.put(22, "dvidešimt dveji" + vyrDgsV());
		s.put(24, "dvidešimt ketveri" + vyrDgsV());
		s.put(30, "trisdešimt" + vyrDgsK());
		s.put(48, "keturiasdešimt aštuoneri" + vyrDgsV());
		s.put(50, "penkiasdešimt" + vyrDgsK());
		s.put(55, "penkiasdešimt penkeri" + vyrDgsV());
		s.put(60, "šešiasdešimt" + vyrDgsK());
		s.put(70, "septyniasdešimt" + vyrDgsK());
		s.put(80, "aštuoniasdešimt" + vyrDgsK());
		s.put(90, "devyniasdešimt" + vyrDgsK());
		s.put(92, "devyniasdešimt dveji" + vyrDgsV());
		s.put(100, "šimtas" + vyrDgsK());
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000V() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vieneri" + vyrDgsV());
		s.put(200, "du šimtai" + vyrDgsK());
		s.put(210, "du šimtai dešimt" + vyrDgsK());
		s.put(300, "trys šimtai" + vyrDgsK());
		s.put(313, "trys šimtai trylika" + vyrDgsK());
		s.put(400, "keturi šimtai" + vyrDgsK());
		s.put(441, "keturi šimtai keturiasdešimt vieneri" + vyrDgsV());
		s.put(500, "penki šimtai" + vyrDgsK());
		s.put(582, "penki šimtai aštuoniasdešimt dveji" + vyrDgsV());
		s.put(600, "šeši šimtai" + vyrDgsK());
		s.put(691, "šeši šimtai devyniasdešimt vieneri" + vyrDgsV());
		s.put(700, "septyni šimtai" + vyrDgsK());
		s.put(715, "septyni šimtai penkiolika" + vyrDgsK());
		s.put(800, "aštuoni šimtai" + vyrDgsK());
		s.put(862, "aštuoni šimtai šešiasdešimt dveji" + vyrDgsV());
		s.put(900, "devyni šimtai" + vyrDgsK());
		s.put(963, "devyni šimtai šešiasdešimt treji" + vyrDgsV());
		s.put(1000, "tūkstantis" + vyrDgsK());

		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerių" + vyrDgsK());
		s.put(2, "dvejų" + vyrDgsK());
		s.put(3, "trejų" + vyrDgsK());
		s.put(4, "ketverių" + vyrDgsK());
		s.put(5, "penkerių" + vyrDgsK());
		s.put(6, "šešerių" + vyrDgsK());
		s.put(7, "septynerių" + vyrDgsK());
		s.put(8, "aštuonerių" + vyrDgsK());
		s.put(9, "devynerių" + vyrDgsK());
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
		s.put(21, "dvidešimt vienerių" + vyrDgsK());
		s.put(22, "dvidešimt dvejų" + vyrDgsK());
		s.put(24, "dvidešimt ketverių" + vyrDgsK());
		s.put(30, "trisdešimties" + vyrDgsK());
		s.put(48, "keturiasdešimt aštuonerių" + vyrDgsK());
		s.put(50, "penkiasdešimties" + vyrDgsK());
		s.put(55, "penkiasdešimt penkerių" + vyrDgsK());
		s.put(60, "šešiasdešimties" + vyrDgsK());
		s.put(70, "septyniasdešimties" + vyrDgsK());
		s.put(80, "aštuoniasdešimties" + vyrDgsK());
		s.put(90, "devyniasdešimties" + vyrDgsK());
		s.put(92, "devyniasdešimt dvejų" + vyrDgsK());
		s.put(100, "šimto" + vyrDgsK());
		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo100Iki1000K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimto vienerių" + vyrDgsK());
		s.put(200, "dviejų šimtų" + vyrDgsK());
		s.put(210, "dviejų šimtų dešimties" + vyrDgsK());
		s.put(300, "trijų šimtų" + vyrDgsK());
		s.put(313, "trijų šimtų trylikos" + vyrDgsK());
		s.put(400, "keturių šimtų" + vyrDgsK());
		s.put(441, "keturių šimtų keturiasdešimt vienerių" + vyrDgsK());
		s.put(500, "penkių šimtų" + vyrDgsK());
		s.put(582, "penkių šimtų aštuoniasdešimt dvejų" + vyrDgsK());
		s.put(600, "šešių šimtų" + vyrDgsK());
		s.put(691, "šešių šimtų devyniasdešimt vienerių" + vyrDgsK());
		s.put(700, "septynių šimtų" + vyrDgsK());
		s.put(715, "septynių šimtų penkiolikos" + vyrDgsK());
		s.put(800, "aštuonių šimtų" + vyrDgsK());
		s.put(862, "aštuonių šimtų šešiasdešimt dvejų" + vyrDgsK());
		s.put(900, "devynių šimtų" + vyrDgsK());
		s.put(963, "devynių šimtų šešiasdešimt trejų" + vyrDgsK());
		s.put(1000, "tūkstančio" + vyrDgsK());

		testSkaiciai(s, Linksnis.K);
	}
	
	@Test
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneriems");
		s.put(2, "dvejiems");
		s.put(3, "trejiems");
		s.put(4, "ketveriems");
		s.put(5, "penkeriems");
		s.put(6, "šešeriems");
		s.put(7, "septyneriems");
		s.put(8, "aštuoneriems");
		s.put(9, "devyneriems");
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
		s.put(21, "dvidešimt vieneriems");
		s.put(22, "dvidešimt dvejiems");
		s.put(24, "dvidešimt ketveriems");
		s.put(30, "trisdešimčiai");
		s.put(48, "keturiasdešimt aštuoneriems");
		s.put(50, "penkiasdešimčiai");
		s.put(55, "penkiasdešimt penkeriems");
		s.put(60, "šešiasdešimčiai");
		s.put(70, "septyniasdešimčiai");
		s.put(80, "aštuoniasdešimčiai");
		s.put(90, "devyniasdešimčiai");
		s.put(92, "devyniasdešimt dvejiems");
		s.put(100, "šimtui");
		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo100Iki1000N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtui vieneriems");
		s.put(200, "dviem šimtams");
		s.put(210, "dviem šimtams dešimčiai");
		s.put(300, "trims šimtams");
		s.put(313, "trims šimtams trylikai");
		s.put(400, "keturiems šimtams");
		s.put(441, "keturiems šimtams keturiasdešimt vieneriems");
		s.put(500, "penkiems šimtams");
		s.put(582, "penkiems šimtams aštuoniasdešimt dvejiems");
		s.put(600, "šešiems šimtams");
		s.put(691, "šešiems šimtams devyniasdešimt vieneriems");
		s.put(700, "septyniems šimtams");
		s.put(715, "septyniems šimtams penkiolikai");
		s.put(800, "aštuoniems šimtams");
		s.put(862, "aštuoniems šimtams šešiasdešimt dvejiems");
		s.put(900, "devyniems šimtams");
		s.put(963, "devyniems šimtams šešiasdešimt trejiems");
		s.put(1000, "tūkstančiui");

		testSkaiciai(s, Linksnis.N);
	}
	
	@Test
	public void testNuo1Iki20G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vienerius");
		s.put(2, "dvejus");
		s.put(3, "trejus");
		s.put(4, "ketverius");
		s.put(5, "penkerius");
		s.put(6, "šešerius");
		s.put(7, "septynerius");
		s.put(8, "aštuonerius");
		s.put(9, "devynerius");
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
		s.put(21, "dvidešimt vienerius");
		s.put(22, "dvidešimt dvejus");
		s.put(24, "dvidešimt ketverius");
		s.put(30, "trisdešimt");
		s.put(48, "keturiasdešimt aštuonerius");
		s.put(50, "penkiasdešimt");
		s.put(55, "penkiasdešimt penkerius");
		s.put(60, "šešiasdešimt");
		s.put(70, "septyniasdešimt");
		s.put(80, "aštuoniasdešimt");
		s.put(90, "devyniasdešimt");
		s.put(92, "devyniasdešimt dvejus");
		s.put(100, "šimtą");
		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo100Iki1000G() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtą vienerius");
		s.put(200, "du šimtus");
		s.put(210, "du šimtus dešimt");
		s.put(300, "tris šimtus");
		s.put(313, "tris šimtus trylika");
		s.put(400, "keturis šimtus");
		s.put(441, "keturis šimtus keturiasdešimt vienerius");
		s.put(500, "penkis šimtus");
		s.put(582, "penkis šimtus aštuoniasdešimt dvejus");
		s.put(600, "šešis šimtus");
		s.put(691, "šešis šimtus devyniasdešimt vienerius");
		s.put(700, "septynis šimtus");
		s.put(715, "septynis šimtus penkiolika");
		s.put(800, "aštuonis šimtus");
		s.put(862, "aštuonis šimtus šešiasdešimt dvejus");
		s.put(900, "devynis šimtus");
		s.put(963, "devynis šimtus šešiasdešimt trejus");
		s.put(1000, "tūkstantį");

		testSkaiciai(s, Linksnis.G);
	}
	
	@Test
	public void testNuo1Iki20I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneriais");
		s.put(2, "dvejais");
		s.put(3, "trejais");
		s.put(4, "ketveriais");
		s.put(5, "penkeriais");
		s.put(6, "šešeriais");
		s.put(7, "septyneriais");
		s.put(8, "aštuoneriais");
		s.put(9, "devyneriais");
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
		s.put(21, "dvidešimt vieneriais");
		s.put(22, "dvidešimt dvejais");
		s.put(24, "dvidešimt ketveriais");
		s.put(30, "trisdešimčia");
		s.put(48, "keturiasdešimt aštuoneriais");
		s.put(50, "penkiasdešimčia");
		s.put(55, "penkiasdešimt penkeriais");
		s.put(60, "šešiasdešimčia");
		s.put(70, "septyniasdešimčia");
		s.put(80, "aštuoniasdešimčia");
		s.put(90, "devyniasdešimčia");
		s.put(92, "devyniasdešimt dvejais");
		s.put(100, "šimtu");
		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo100Iki1000I() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtu vieneriais");
		s.put(200, "dviem šimtais");
		s.put(210, "dviem šimtais dešimčia");
		s.put(300, "trimis šimtais");
		s.put(313, "trimis šimtais trylika");
		s.put(400, "keturiais šimtais");
		s.put(441, "keturiais šimtais keturiasdešimt vieneriais");
		s.put(500, "penkiais šimtais");
		s.put(582, "penkiais šimtais aštuoniasdešimt dvejais");
		s.put(600, "šešiais šimtais");
		s.put(691, "šešiais šimtais devyniasdešimt vieneriais");
		s.put(700, "septyniais šimtais");
		s.put(715, "septyniais šimtais penkiolika");
		s.put(800, "aštuoniais šimtais");
		s.put(862, "aštuoniais šimtais šešiasdešimt dvejais");
		s.put(900, "devyniais šimtais");
		s.put(963, "devyniais šimtais šešiasdešimt trejais");
		s.put(1000, "tūkstančiu");

		testSkaiciai(s, Linksnis.I);
	}
	
	@Test
	public void testNuo1Iki20Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(1, "vieneriuose");
		s.put(2, "dvejuose");
		s.put(3, "trejuose");
		s.put(4, "ketveriuose");
		s.put(5, "penkeriuose");
		s.put(6, "šešeriuose");
		s.put(7, "septyneriuose");
		s.put(8, "aštuoneriuose");
		s.put(9, "devyneriuose");
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
		s.put(21, "dvidešimt vieneriuose");
		s.put(22, "dvidešimt dvejuose");
		s.put(24, "dvidešimt ketveriuose");
		s.put(30, "trisdešimtyje");
		s.put(48, "keturiasdešimt aštuoneriuose");
		s.put(50, "penkiasdešimtyje");
		s.put(55, "penkiasdešimt penkeriuose");
		s.put(60, "šešiasdešimtyje");
		s.put(70, "septyniasdešimtyje");
		s.put(80, "aštuoniasdešimtyje");
		s.put(90, "devyniasdešimtyje");
		s.put(92, "devyniasdešimt dvejuose");
		s.put(100, "šimte");
		testSkaiciai(s, Linksnis.Vt);
	}
	
	@Test
	public void testNuo100Iki1000Vt() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimte vieneriuose");
		s.put(200, "dviejuose šimtuose");
		s.put(210, "dviejuose šimtuose dešimtyje");
		s.put(300, "trijuose šimtuose");
		s.put(313, "trijuose šimtuose trylikoje");
		s.put(400, "keturiuose šimtuose");
		s.put(441, "keturiuose šimtuose keturiasdešimt vieneriuose");
		s.put(500, "penkiuose šimtuose");
		s.put(582, "penkiuose šimtuose aštuoniasdešimt dvejuose");
		s.put(600, "šešiuose šimtuose");
		s.put(691, "šešiuose šimtuose devyniasdešimt vieneriuose");
		s.put(700, "septyniuose šimtuose");
		s.put(715, "septyniuose šimtuose penkiolikoje");
		s.put(800, "aštuoniuose šimtuose");
		s.put(862, "aštuoniuose šimtuose šešiasdešimt dvejuose");
		s.put(900, "devyniuose šimtuose");
		s.put(963, "devyniuose šimtuose šešiasdešimt trejuose");
		s.put(1000, "tūkstantyje");

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
