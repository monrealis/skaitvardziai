package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigInteger;
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
	
	public void testVirst1000VBigIntegers(Gimine gimine) {
		boolean vyrg = gimine == Gimine.V;
		BigInteger e18 = Numbers.BILLION.multiply(Numbers.BILLION);
		BigInteger e9 = Numbers.BILLION;
		BigInteger e6 = Numbers.MILLION;
//		BigInteger e3 = Numbers.THOUSAND;
		
		Map<BigInteger, String> bigNumbers = new TreeMap<BigInteger, String>();
		Map<BigInteger, String> smallNumbers = new TreeMap<BigInteger, String>();
		
		
		bigNumbers.put(e9, vyrg ? "milijardasis" : "milijardoji");		
		
//		bigNumbers.put(BigInteger.valueOf(  1).multiply(e3).multiply(e9), "tūkstantis milijardų");
//		bigNumbers.put(BigInteger.valueOf(  5).multiply(e3).multiply(e9), "penki tūkstančiai milijardų");
//		bigNumbers.put(BigInteger.valueOf( 50).multiply(e3).multiply(e9), "penkiasdešimt tūkstančių milijardų");
//		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septyni šimtai trisdešimt du tūkstančiai milijardų");
//		
		bigNumbers.put(e18, vyrg ? "milijardas milijardasis" : "milijardas milijardoji");		
//		bigNumbers.put(BigInteger.valueOf(  1).multiply(e6).multiply(e18), "milijonas milijardų milijardų");
//		bigNumbers.put(BigInteger.valueOf(  2).multiply(e6).multiply(e18), "du milijonai milijardų milijardų");
//		bigNumbers.put(BigInteger.valueOf( 12).multiply(e6).multiply(e18), "dvylika milijonų milijardų milijardų");
//		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "du šimtai keturi tūkstančiai milijardų");
		
		smallNumbers.put(e6, vyrg ? "milijonasis" : "milijonoji");
//		String prefix = "šeši šimtai penkiasdešimt du milijonai aštuoni šimtai septyniasdešimt keturi tūkstančiai du šimtai ";
//		smallNumbers.put(new BigInteger("652874203"), prefix + "trys");
//		smallNumbers.put(new BigInteger("652874201"), prefix + (gimine == Gimine.V ? "vienas" : "viena"));
//		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimt");
		
		//Map<BigInteger, String> combinedNumbers = createCombinedNumbers(bigNumbers, smallNumbers);
		
		testSkaiciai(bigNumbers, Skaicius.V, Linksnis.V, gimine);
		testSkaiciai(smallNumbers, Skaicius.V, Linksnis.V, gimine);
		//testSkaiciai(combinedNumbers, Skaicius.V, Linksnis.V, gimine);
	}
	
	@Test
	public void testVirst1000VVyrBigIntegers() {
		testVirst1000VBigIntegers(Gimine.V);
	}
	
	@Test
	public void testVirst1000VMotBigIntegers() {
		testVirst1000VBigIntegers(Gimine.M);
	}

}
