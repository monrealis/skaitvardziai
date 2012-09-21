package eu.vytenis.skaiciai;

import junit.framework.Assert;

import org.junit.Test;

import eu.vytenis.skaiciai.esybes.Gimine;
import eu.vytenis.skaiciai.esybes.Linksnis;
import eu.vytenis.skaiciai.esybes.Poskyris;
import eu.vytenis.skaiciai.esybes.Skaicius;

public class KuopiniaiTest {
	
	private void assertKuopinis(long skaicius, Linksnis linksnis, String tekstas) {
		Assert.assertEquals(tekstas, new Skaicius(skaicius).toString(Poskyris.Kuopinis, linksnis, Gimine.V));
	}
	@Test
	public void testKuopiniai() {
		Linksnis l = Linksnis.V;
		assertKuopinis(1, l, "vienetas");
		assertKuopinis(2, l, "dvejetas");
		assertKuopinis(3, l, "trejetas");
		assertKuopinis(4, l, "ketvertas");
		assertKuopinis(5, l, "penketas");
		assertKuopinis(6, l, "šešetas");
		assertKuopinis(7, l, "septynetas");
		assertKuopinis(8, l, "aštuonetas");
		assertKuopinis(9, l, "devynetas");
		
		l = Linksnis.K;
		assertKuopinis(1, l, "vieneto");
		assertKuopinis(2, l, "dvejeto");
		assertKuopinis(3, l, "trejeto");
		assertKuopinis(4, l, "ketverto");
		assertKuopinis(5, l, "penketo");
		assertKuopinis(6, l, "šešeto");
		assertKuopinis(7, l, "septyneto");
		assertKuopinis(8, l, "aštuoneto");
		assertKuopinis(9, l, "devyneto");
		
		l = Linksnis.N;
		assertKuopinis(1, l, "vienetui");
		assertKuopinis(2, l, "dvejetui");
		assertKuopinis(3, l, "trejetui");
		assertKuopinis(4, l, "ketvertui");
		assertKuopinis(5, l, "penketui");
		assertKuopinis(6, l, "šešetui");
		assertKuopinis(7, l, "septynetui");
		assertKuopinis(8, l, "aštuonetui");
		assertKuopinis(9, l, "devynetui");
		
		l = Linksnis.G;
		assertKuopinis(1, l, "vienetą");
		assertKuopinis(2, l, "dvejetą");
		assertKuopinis(3, l, "trejetą");
		assertKuopinis(4, l, "ketvertą");
		assertKuopinis(5, l, "penketą");
		assertKuopinis(6, l, "šešetą");
		assertKuopinis(7, l, "septynetą");
		assertKuopinis(8, l, "aštuonetą");
		assertKuopinis(9, l, "devynetą");
		
		l = Linksnis.I;
		assertKuopinis(1, l, "vienetu");
		assertKuopinis(2, l, "dvejetu");
		assertKuopinis(3, l, "trejetu");
		assertKuopinis(4, l, "ketvertu");
		assertKuopinis(5, l, "penketu");
		assertKuopinis(6, l, "šešetu");
		assertKuopinis(7, l, "septynetu");
		assertKuopinis(8, l, "aštuonetu");
		assertKuopinis(9, l, "devynetu");
		
		l = Linksnis.Vt;
		assertKuopinis(1, l, "vienete");
		assertKuopinis(2, l, "dvejete");
		assertKuopinis(3, l, "trejete");
		assertKuopinis(4, l, "ketverte");
		assertKuopinis(5, l, "penkete");
		assertKuopinis(6, l, "šešete");
		assertKuopinis(7, l, "septynete");
		assertKuopinis(8, l, "aštuonete");
		assertKuopinis(9, l, "devynete");
		
		l = Linksnis.S;
		assertKuopinis(1, l, "vienete");
		assertKuopinis(2, l, "dvejete");
		assertKuopinis(3, l, "trejete");
		assertKuopinis(4, l, "ketverte");
		assertKuopinis(5, l, "penkete");
		assertKuopinis(6, l, "šešete");
		assertKuopinis(7, l, "septynete");
		assertKuopinis(8, l, "aštuonete");
		assertKuopinis(9, l, "devynete");
		
	}
	
	@Test
	public void testNegalimiSkaiciai() {
		for (long l = -1000L; l <= 1000; ++l) {
			Skaicius s = new Skaicius(l);
			if (l >= 1 && l <= 9) {
				for (Linksnis linksnis : Linksnis.values()) {
					s.toString(Poskyris.Kuopinis, linksnis, Gimine.V);
				}
			} else {
				try {
					s.toString(Poskyris.Kuopinis, Linksnis.V, Gimine.V);
					Assert.fail();
				} catch (IllegalArgumentException e) {
					// OK
				}
			}
			
		}
	}
}
