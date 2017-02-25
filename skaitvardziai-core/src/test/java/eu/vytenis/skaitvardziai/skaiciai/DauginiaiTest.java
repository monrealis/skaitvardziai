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

public abstract class DauginiaiTest extends SkaitvardziaiTest {

	protected Gimine gimine;

	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		Forma f = new Forma();
		f.skaicius(Skaicius.V);
		f.poskyris(Poskyris.Dauginis);
		f.gimine(gimine);
		f.linksnis(linksnis);
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
		bigNumbers.put(BigInteger.valueOf(1).multiply(e3).multiply(e9), "tūkstantis milijardų");
		bigNumbers.put(BigInteger.valueOf(5).multiply(e3).multiply(e9), "penki tūkstančiai milijardų");
		bigNumbers.put(BigInteger.valueOf(50).multiply(e3).multiply(e9), "penkiasdešimt tūkstančių milijardų");
		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septyni šimtai trisdešimt du tūkstančiai milijardų");
		bigNumbers.put(e18, "milijardas milijardų");
		bigNumbers.put(BigInteger.valueOf(1).multiply(e6).multiply(e18), "milijonas milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(2).multiply(e6).multiply(e18), "du milijonai milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(12).multiply(e6).multiply(e18), "dvylika milijonų milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "du šimtai keturi tūkstančiai milijardų");
		smallNumbers.put(e6, "milijonas");
		String prefix = "šeši šimtai penkiasdešimt du milijonai aštuoni šimtai septyniasdešimt keturi tūkstančiai du šimtai ";
		smallNumbers.put(new BigInteger("652874203"), prefix + (gimine == Gimine.V ? "treji" : "trejos"));
		smallNumbers.put(new BigInteger("652874201"), prefix + (gimine == Gimine.V ? "vieneri" : "vienerios"));
		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimt");
		Map<BigInteger, String> combinedNumbers = createCombinedNumbers(bigNumbers, smallNumbers);
		testSkaiciai(bigNumbers, Linksnis.V);
		testSkaiciai(smallNumbers, Linksnis.V);
		testSkaiciai(combinedNumbers, Linksnis.V);
	}

	@Test
	public void testVirst1000KBigIntegers() {
		Checks.checkNotNull("gimine", gimine);
		BigInteger e18 = Numbers.BILLION.multiply(Numbers.BILLION);
		BigInteger e9 = Numbers.BILLION;
		BigInteger e6 = Numbers.MILLION;
		BigInteger e3 = Numbers.THOUSAND;
		Map<BigInteger, String> bigNumbers = new TreeMap<BigInteger, String>();
		Map<BigInteger, String> smallNumbers = new TreeMap<BigInteger, String>();
		bigNumbers.put(e9, "milijardo");
		bigNumbers.put(BigInteger.valueOf(1).multiply(e3).multiply(e9), "tūkstančio milijardų");
		bigNumbers.put(BigInteger.valueOf(5).multiply(e3).multiply(e9), "penkių tūkstančių milijardų");
		bigNumbers.put(BigInteger.valueOf(50).multiply(e3).multiply(e9), "penkiasdešimties tūkstančių milijardų");
		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septynių šimtų trisdešimt dviejų tūkstančių milijardų");
		bigNumbers.put(e18, "milijardo milijardų");
		bigNumbers.put(BigInteger.valueOf(1).multiply(e6).multiply(e18), "milijono milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(2).multiply(e6).multiply(e18), "dviejų milijonų milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(12).multiply(e6).multiply(e18), "dvylikos milijonų milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "dviejų šimtų keturių tūkstančių milijardų");
		smallNumbers.put(e6, "milijono");
		String prefix = "šešių šimtų penkiasdešimt dviejų milijonų aštuonių šimtų septyniasdešimt keturių tūkstančių dviejų šimtų ";
		smallNumbers.put(new BigInteger("652874203"), prefix + "trejų");
		smallNumbers.put(new BigInteger("652874201"), prefix + "vienerių");
		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimties");
		Map<BigInteger, String> combinedNumbers = createCombinedNumbers(bigNumbers, smallNumbers);
		testSkaiciai(bigNumbers, Linksnis.K);
		testSkaiciai(smallNumbers, Linksnis.K);
		testSkaiciai(combinedNumbers, Linksnis.K);
	}

	@Test
	public void testVirst1000NBigIntegers() {
		Checks.checkNotNull("gimine", gimine);
		BigInteger e18 = Numbers.BILLION.multiply(Numbers.BILLION);
		BigInteger e9 = Numbers.BILLION;
		BigInteger e6 = Numbers.MILLION;
		BigInteger e3 = Numbers.THOUSAND;
		Map<BigInteger, String> bigNumbers = new TreeMap<BigInteger, String>();
		Map<BigInteger, String> smallNumbers = new TreeMap<BigInteger, String>();
		bigNumbers.put(e9, "milijardui");
		bigNumbers.put(BigInteger.valueOf(1).multiply(e3).multiply(e9), "tūkstančiui milijardų");
		bigNumbers.put(BigInteger.valueOf(5).multiply(e3).multiply(e9), "penkiems tūkstančiams milijardų");
		bigNumbers.put(BigInteger.valueOf(50).multiply(e3).multiply(e9), "penkiasdešimčiai tūkstančių milijardų");
		bigNumbers.put(BigInteger.valueOf(732).multiply(e3).multiply(e9), "septyniems šimtams trisdešimt dviem tūkstančiams milijardų");
		bigNumbers.put(e18, "milijardui milijardų");
		bigNumbers.put(BigInteger.valueOf(1).multiply(e6).multiply(e18), "milijonui milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(2).multiply(e6).multiply(e18), "dviem milijonams milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(12).multiply(e6).multiply(e18), "dvylikai milijonų milijardų milijardų");
		bigNumbers.put(BigInteger.valueOf(204).multiply(e3).multiply(e9), "dviem šimtams keturiems tūkstančiams milijardų");
		smallNumbers.put(e6, "milijonui");
		String prefix = "šešiems šimtams penkiasdešimt dviem milijonams aštuoniems šimtams septyniasdešimt keturiems tūkstančiams dviem šimtams ";
		smallNumbers.put(new BigInteger("652874203"), prefix + (gimine == Gimine.V ? "trejiems" : "trejoms"));
		smallNumbers.put(new BigInteger("652874201"), prefix + (gimine == Gimine.V ? "vieneriems" : "vienerioms"));
		smallNumbers.put(new BigInteger("652874250"), prefix + "penkiasdešimčiai");
		Map<BigInteger, String> combinedNumbers = createCombinedNumbers(bigNumbers, smallNumbers);
		testSkaiciai(bigNumbers, Linksnis.N);
		testSkaiciai(smallNumbers, Linksnis.N);
		testSkaiciai(combinedNumbers, Linksnis.N);
	}

}
