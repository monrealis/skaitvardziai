package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Skaicius;


public class Skaiciai {
	
	private void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			long number = e.getKey().longValue();
			Skaicius sk = new Skaicius(number);
					
			Assert.assertEquals("Invalid text", expected, sk.toString(linksnis));
		}
	}
	
	@Test
	public void testNuo1Iki20() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulis");
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
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo20Iki100() {
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
		s.put(100, "šimtas");
		testSkaiciai(s, Linksnis.V);
	}
	
	@Test
	public void testNuo100Iki1000() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(101, "šimtas vienas");
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
		s.put(1000, "tūkstantis");

		testSkaiciai(s, Linksnis.V);
	}
	

	
	@Test
	public void testNuo1Iki20K() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nulio");
		s.put(1, "vieno");
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
	public void testNuo1Iki20N() {
		Map<Number, String> s = new TreeMap<Number, String>();
		s.put(0, "nuliui");
		s.put(1, "vienam");
		s.put(2, "dviems");
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
	public void testOutput() {
		System.out.println(new Skaicius(2256));
		System.out.println(new Skaicius(12256));
		/*for (int i = 10000; i <= 100000; i+=1000) {
			System.out.println(new Skaicius(i));
		}*/
	}
	
}
