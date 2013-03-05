package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.util.Numbers;

public abstract class PagrindiniaiTest extends BaseTest {

	private Gimine gimine;
	
	public void setGimine(Gimine gimine) {
		this.gimine = gimine;
	}

	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		Forma f = new Forma();
		f.setSkaicius(Skaicius.V);
		f.setPoskyris(Poskyris.Pagrindinis);
		f.setGimine(gimine);
		f.setLinksnis(linksnis);
		
		super.testSkaiciai(skaiciai, f);
	}
	
	@Test
	public void testVirst1000VBigIntegers() {
		Checks.checkNotNull("gimine", gimine);
		
		BigInteger e18 = Numbers.BILLION.multiply(Numbers.BILLION);
		BigInteger e9 = Numbers.BILLION;
		BigInteger e6 = Numbers.MILLION;
		BigInteger e3 = Numbers.THOUSAND;
		
		Map<BigInteger, String> bigNumbers = new TreeMap<BigInteger, String>();
		Map<BigInteger, String> smallNumbers = new TreeMap<BigInteger, String>();
		
		bigNumbers.put(e9, "milijardas");		
		
		bigNumbers.put(BigInteger.valueOf(  1).multiply(e3).multiply(e9), "tūkstantis milijardų");
		bigNumbers.put(BigInteger.valueOf(  5).multiply(e3).multiply(e9), "penki tūkstančiai milijardų");
		bigNumbers.put(BigInteger.valueOf( 50).multiply(e3).multiply(e9), "penkiasdešimt tūkstančių milijardų");
		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septyni šimtai trisdešimt du tūkstančiai milijardų");
		
		bigNumbers.put(e18, "milijardas milijardų");		
		bigNumbers.put(BigInteger.valueOf(  1).multiply(e6).multiply(e18), "milijonas milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(  2).multiply(e6).multiply(e18), "du milijonai milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf( 12).multiply(e6).multiply(e18), "dvylika milijonų milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "du šimtai keturi tūkstančiai milijardų");
		
		smallNumbers.put(e6, "milijonas");
		String prefix = "šeši šimtai penkiasdešimt du milijonai aštuoni šimtai septyniasdešimt keturi tūkstančiai du šimtai ";
		smallNumbers.put(new BigInteger("652874203"), prefix + "trys");
		smallNumbers.put(new BigInteger("652874201"), prefix + (gimine == Gimine.V ? "vienas" : "viena"));
		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimt");
		
		Map<BigInteger, String> combinedNumbers = createCombinedNumbers(bigNumbers, smallNumbers);
		
		testSkaiciai(bigNumbers, Linksnis.V);
		testSkaiciai(smallNumbers, Linksnis.V);
		testSkaiciai(combinedNumbers, Linksnis.V);
	}
}
