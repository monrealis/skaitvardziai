package eu.vytenis.skaiciai;

import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Skaicius;


public class Skaiciai {
	
	private void testSkaiciai(Map<? extends Number, String> skaiciai) {
		for (Map.Entry<? extends Number, String> e : skaiciai.entrySet()) {
			Assert.assertEquals(e.getValue(), new Skaicius(e.getKey().longValue()).toString());
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
		testSkaiciai(s);
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
		testSkaiciai(s);
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

		testSkaiciai(s);
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
