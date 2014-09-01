package eu.vytenis.skaitvardziai.skaiciai;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;


public class TrupmenosTest {
	private void testTrupmenos(Map<Trupmena, String> skaiciai, Linksnis linksnis) {
		for (Map.Entry<Trupmena, String> e : skaiciai.entrySet()) {
			String expected = e.getValue();
			Trupmena t = e.getKey();
			assertEquals("Invalid text", expected, t.toString(linksnis));
			if (t.getSkaitiklis().compareTo(BigInteger.ZERO) > 0) {
				Trupmena n = new Trupmena(t.getSkaitiklis().negate(), t.getVardiklis());
				assertEquals("Invalid text", "minus " + expected, n.toString(linksnis));
				
			}
		}
	}
	
	private Trupmena t(long skaitiklis, long vardiklis) {
		return new Trupmena(skaitiklis, vardiklis);
	}
	@Test
	public void testV() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nulis pirmųjų");
		s.put(t(1, 1), "viena pirmoji");
		s.put(t(2, 1), "dvi pirmosios");
		s.put(t(3, 1), "trys pirmosios");
		s.put(t(4, 1), "keturios pirmosios");
		s.put(t(5, 1), "penkios pirmosios");
		s.put(t(6, 1), "šešios pirmosios");
		s.put(t(7, 1), "septynios pirmosios");
		s.put(t(8, 1), "aštuonios pirmosios");
		s.put(t(9, 1), "devynios pirmosios");
		s.put(t(10, 1), "dešimt pirmųjų");
		s.put(t(11, 1), "vienuolika pirmųjų");
		s.put(t(12, 1), "dvylika pirmųjų");
		s.put(t(13, 1), "trylika pirmųjų");
		s.put(t(14, 1), "keturiolika pirmųjų");
		s.put(t(15, 1), "penkiolika pirmųjų");
		s.put(t(16, 1), "šešiolika pirmųjų");
		s.put(t(17, 1), "septyniolika pirmųjų");
		s.put(t(18, 1), "aštuoniolika pirmųjų");
		s.put(t(19, 1), "devyniolika pirmųjų");
		s.put(t(20, 1), "dvidešimt pirmųjų");		
		testTrupmenos(s, Linksnis.V);
	}
	
	@Test
	public void testK() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nulio pirmųjų");
		s.put(t(1, 1), "vienos pirmosios");
		s.put(t(2, 1), "dviejų pirmųjų");
		s.put(t(3, 1), "trijų pirmųjų");
		s.put(t(4, 1), "keturių pirmųjų");
		s.put(t(5, 1), "penkių pirmųjų");
		s.put(t(6, 1), "šešių pirmųjų");
		s.put(t(7, 1), "septynių pirmųjų");
		s.put(t(8, 1), "aštuonių pirmųjų");
		s.put(t(9, 1), "devynių pirmųjų");
		s.put(t(10, 1), "dešimties pirmųjų");
		s.put(t(11, 1), "vienuolikos pirmųjų");
		s.put(t(12, 1), "dvylikos pirmųjų");
		s.put(t(13, 1), "trylikos pirmųjų");
		s.put(t(14, 1), "keturiolikos pirmųjų");
		s.put(t(15, 1), "penkiolikos pirmųjų");
		s.put(t(16, 1), "šešiolikos pirmųjų");
		s.put(t(17, 1), "septyniolikos pirmųjų");
		s.put(t(18, 1), "aštuoniolikos pirmųjų");
		s.put(t(19, 1), "devyniolikos pirmųjų");
		s.put(t(20, 1), "dvidešimties pirmųjų");		
		testTrupmenos(s, Linksnis.K);
	}
	
	@Test
	public void testN() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nuliui pirmųjų");
		s.put(t(1, 1), "vienai pirmajai");
		s.put(t(2, 1), "dviem pirmosioms");
		s.put(t(3, 1), "trims pirmosioms");
		s.put(t(4, 1), "keturioms pirmosioms");
		s.put(t(5, 1), "penkioms pirmosioms");
		s.put(t(6, 1), "šešioms pirmosioms");
		s.put(t(7, 1), "septynioms pirmosioms");
		s.put(t(8, 1), "aštuonioms pirmosioms");
		s.put(t(9, 1), "devynioms pirmosioms");
		s.put(t(10, 1), "dešimčiai pirmųjų");
		s.put(t(11, 1), "vienuolikai pirmųjų");
		s.put(t(12, 1), "dvylikai pirmųjų");
		s.put(t(13, 1), "trylikai pirmųjų");
		s.put(t(14, 1), "keturiolikai pirmųjų");
		s.put(t(15, 1), "penkiolikai pirmųjų");
		s.put(t(16, 1), "šešiolikai pirmųjų");
		s.put(t(17, 1), "septyniolikai pirmųjų");
		s.put(t(18, 1), "aštuoniolikai pirmųjų");
		s.put(t(19, 1), "devyniolikai pirmųjų");
		s.put(t(20, 1), "dvidešimčiai pirmųjų");		
		testTrupmenos(s, Linksnis.N);
	}
	
	@Test
	public void testG() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nulį pirmųjų");
		s.put(t(1, 1), "vieną pirmąją");
		s.put(t(2, 1), "dvi pirmąsias");
		s.put(t(3, 1), "tris pirmąsias");
		s.put(t(4, 1), "keturias pirmąsias");
		s.put(t(5, 1), "penkias pirmąsias");
		s.put(t(6, 1), "šešias pirmąsias");
		s.put(t(7, 1), "septynias pirmąsias");
		s.put(t(8, 1), "aštuonias pirmąsias");
		s.put(t(9, 1), "devynias pirmąsias");
		s.put(t(10, 1), "dešimt pirmųjų");
		s.put(t(11, 1), "vienuolika pirmųjų");
		s.put(t(12, 1), "dvylika pirmųjų");
		s.put(t(13, 1), "trylika pirmųjų");
		s.put(t(14, 1), "keturiolika pirmųjų");
		s.put(t(15, 1), "penkiolika pirmųjų");
		s.put(t(16, 1), "šešiolika pirmųjų");
		s.put(t(17, 1), "septyniolika pirmųjų");
		s.put(t(18, 1), "aštuoniolika pirmųjų");
		s.put(t(19, 1), "devyniolika pirmųjų");
		s.put(t(20, 1), "dvidešimt pirmųjų");		
		testTrupmenos(s, Linksnis.G);
	}
	
	@Test
	public void testI() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nuliu pirmųjų");
		s.put(t(1, 1), "viena pirmąja");
		s.put(t(2, 1), "dviem pirmosiomis");
		s.put(t(3, 1), "trimis pirmosiomis");
		s.put(t(4, 1), "keturiomis pirmosiomis");
		s.put(t(5, 1), "penkiomis pirmosiomis");
		s.put(t(6, 1), "šešiomis pirmosiomis");
		s.put(t(7, 1), "septyniomis pirmosiomis");
		s.put(t(8, 1), "aštuoniomis pirmosiomis");
		s.put(t(9, 1), "devyniomis pirmosiomis");
		s.put(t(10, 1), "dešimčia pirmųjų");
		s.put(t(11, 1), "vienuolika pirmųjų");
		s.put(t(12, 1), "dvylika pirmųjų");
		s.put(t(13, 1), "trylika pirmųjų");
		s.put(t(14, 1), "keturiolika pirmųjų");
		s.put(t(15, 1), "penkiolika pirmųjų");
		s.put(t(16, 1), "šešiolika pirmųjų");
		s.put(t(17, 1), "septyniolika pirmųjų");
		s.put(t(18, 1), "aštuoniolika pirmųjų");
		s.put(t(19, 1), "devyniolika pirmųjų");
		s.put(t(20, 1), "dvidešimčia pirmųjų");		
		testTrupmenos(s, Linksnis.I);
	}
	
	@Test
	public void testVt() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nulyje pirmųjų");
		s.put(t(1, 1), "vienoje pirmojoje");
		s.put(t(2, 1), "dviejose pirmosiose");
		s.put(t(3, 1), "trijose pirmosiose");
		s.put(t(4, 1), "keturiose pirmosiose");
		s.put(t(5, 1), "penkiose pirmosiose");
		s.put(t(6, 1), "šešiose pirmosiose");
		s.put(t(7, 1), "septyniose pirmosiose");
		s.put(t(8, 1), "aštuoniose pirmosiose");
		s.put(t(9, 1), "devyniose pirmosiose");
		s.put(t(10, 1), "dešimtyje pirmųjų");
		s.put(t(11, 1), "vienuolikoje pirmųjų");
		s.put(t(12, 1), "dvylikoje pirmųjų");
		s.put(t(13, 1), "trylikoje pirmųjų");
		s.put(t(14, 1), "keturiolikoje pirmųjų");
		s.put(t(15, 1), "penkiolikoje pirmųjų");
		s.put(t(16, 1), "šešiolikoje pirmųjų");
		s.put(t(17, 1), "septyniolikoje pirmųjų");
		s.put(t(18, 1), "aštuoniolikoje pirmųjų");
		s.put(t(19, 1), "devyniolikoje pirmųjų");
		s.put(t(20, 1), "dvidešimtyje pirmųjų");		
		testTrupmenos(s, Linksnis.Vt);
	}
	
	@Test
	public void testS() {
		Map<Trupmena, String> s = new TreeMap<Trupmena, String>();
		s.put(t(0, 1), "nuli pirmųjų");
		s.put(t(1, 1), "viena pirmoji");
		s.put(t(2, 1), "dvi pirmosios");
		s.put(t(3, 1), "trys pirmosios");
		s.put(t(4, 1), "keturios pirmosios");
		s.put(t(5, 1), "penkios pirmosios");
		s.put(t(6, 1), "šešios pirmosios");
		s.put(t(7, 1), "septynios pirmosios");
		s.put(t(8, 1), "aštuonios pirmosios");
		s.put(t(9, 1), "devynios pirmosios");
		s.put(t(10, 1), "dešimt pirmųjų");
		s.put(t(11, 1), "vienuolika pirmųjų");
		s.put(t(12, 1), "dvylika pirmųjų");
		s.put(t(13, 1), "trylika pirmųjų");
		s.put(t(14, 1), "keturiolika pirmųjų");
		s.put(t(15, 1), "penkiolika pirmųjų");
		s.put(t(16, 1), "šešiolika pirmųjų");
		s.put(t(17, 1), "septyniolika pirmųjų");
		s.put(t(18, 1), "aštuoniolika pirmųjų");
		s.put(t(19, 1), "devyniolika pirmųjų");
		s.put(t(20, 1), "dvidešimt pirmųjų");		
		testTrupmenos(s, Linksnis.S);
	}
	

}
