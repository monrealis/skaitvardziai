package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.util.Numbers;


/**
 * Bazinė klasė tekstiniams kelintinių skaitvardžių testams.
 *
 */
public abstract class KelintiniaiTest extends SkaitvardziaiTest {

	
	private Rusis rusis;
	
	public KelintiniaiTest() {
	}
	
	protected void setRusis(Rusis rusis) {
		this.rusis = rusis;
	}

	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		Forma f = new Forma();
		f.setPoskyris(Poskyris.Kelintinis);
		f.setGimine(gimine);
		f.setSkaicius(skaicius);
		f.setLinksnis(linksnis);
		f.setRusis(rusis);
		
		Map<Number, String> temp = new HashMap<Number, String>();
		for (Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String value = e.getValue() + gimineSkaiciusLinksnis(gimine, new SkaiciusIrLinksnis(skaicius, linksnis));
			temp.put(e.getKey(), value);
		}
		skaiciai = temp;
		
		super.testSkaiciai(skaiciai, f);
	}
	
	@Test
	public void testVirst1000VVyrBigIntegers() {
		testVirst1000VBigIntegers(Gimine.V);
	}
	
	@Test
	public void testVirst1000VMotBigIntegers() {
		testVirst1000VBigIntegers(Gimine.M);
	}
	
	@SuppressWarnings("unchecked")
	public void testVirst1000VBigIntegers(Gimine gimine) {
		BigInteger e18 = Numbers.BILLION.multiply(Numbers.BILLION);
		BigInteger e9 = Numbers.BILLION;
		BigInteger e6 = Numbers.MILLION;
		BigInteger e3 = Numbers.THOUSAND;
		
		Map<BigInteger, String> bigNumbers = new TreeMap<BigInteger, String>();
		Map<BigInteger, String> smallNumbers = new TreeMap<BigInteger, String>();
		
		bigNumbers.put(e9, "milijardasis");		
		
		bigNumbers.put(BigInteger.valueOf(  1).multiply(e3).multiply(e9), "tūkstantis milijardasis");
		bigNumbers.put(BigInteger.valueOf(  5).multiply(e3).multiply(e9), "penki tūkstančiai milijardasis");
		bigNumbers.put(BigInteger.valueOf( 50).multiply(e3).multiply(e9), "penkiasdešimt tūkstančių milijardasis");
		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septyni šimtai trisdešimt du tūkstančiai milijardasis");
//		
		bigNumbers.put(e18, "milijardas milijardasis");		
		bigNumbers.put(BigInteger.valueOf(  1).multiply(e6).multiply(e18), "milijonas milijardų milijardasis");
		bigNumbers.put(BigInteger.valueOf(  2).multiply(e6).multiply(e18), "du milijonai milijardų milijardasis");
		bigNumbers.put(BigInteger.valueOf( 12).multiply(e6).multiply(e18), "dvylika milijonų milijardų milijardasis");
		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "du šimtai keturi tūkstančiai milijardasis");
		
		smallNumbers.put(e6, "milijonasis");
		String prefix = "šeši šimtai penkiasdešimt du milijonai aštuoni šimtai septyniasdešimt keturi tūkstančiai du šimtai ";
		smallNumbers.put(new BigInteger("652874203"), prefix + "trečiasis");
		smallNumbers.put(new BigInteger("652874201"), prefix + "pirmasis");
		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimtasis");
		
		Map<BigInteger, String> combinedNumbers = new HashMap<BigInteger, String>();
		combinedNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9).add(new BigInteger("652874250")), "du šimtai keturi tūkstančiai milijardų " + prefix + "penkiasdešimtasis");
			
		for (Map<BigInteger, String> map: Arrays.<Map<BigInteger, String>>asList(bigNumbers, smallNumbers, combinedNumbers)) {
			if (gimine == Gimine.M) {
				replaceInValues(map, "milijardasis", "milijardoji");
				replaceInValues(map, "milijonasis", "milijonoji");
				replaceInValues(map, "trečiasis", "trečioji");
				replaceInValues(map, "pirmasis", "pirmoji");
				replaceInValues(map, "penkiasdešimtasis", "penkiasdešimtoji");
			}
			if (rusis == Rusis.P) {
				replaceInValues(map, "trečiasis", "trečias");
				replaceInValues(map, "pirmasis", "pirmas");
				replaceInValues(map, "penkiasdešimtasis", "penkiasdešimtas");
				replaceInValues(map, "trečioji", "trečia");
				replaceInValues(map, "pirmoji", "pirma");
				replaceInValues(map, "penkiasdešimtoji", "penkiasdešimta");
			}
		}
		
		testSkaiciai(bigNumbers, Skaicius.V, Linksnis.V, gimine);
		testSkaiciai(smallNumbers, Skaicius.V, Linksnis.V, gimine);
		testSkaiciai(combinedNumbers, Skaicius.V, Linksnis.V, gimine);
	}
	
	private void replaceInValues(Map<?, String> map, String regex, String replacement) {
		for (Entry<?, String> e : map.entrySet()) {
			String v = e.getValue();
			v = v.replaceAll(regex, replacement);
			e.setValue(v);
		}
	}

}
