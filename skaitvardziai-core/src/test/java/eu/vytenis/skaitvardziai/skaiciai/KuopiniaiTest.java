package eu.vytenis.skaitvardziai.skaiciai;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

@RunWith(Parameterized.class)
public class KuopiniaiTest {
	private static final List<Object[]> TEST_CASES = new ArrayList<Object[]>();
	private static final SkaiciusIrLinksnis DGS_K = new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K);
	private final long skaicius;
	private final Linksnis linksnis;
	private final String tekstas;

	public KuopiniaiTest(long skaicius, Linksnis linksnis, String tekstas) {
		this.skaicius = skaicius;
		this.linksnis = linksnis;
		this.tekstas = tekstas;
	}

	@Parameters
	public static List<Object[]> cases() {
		addTestCase(1, Linksnis.V, "vienetas");
		addTestCase(2, Linksnis.V, "dvejetas");
		addTestCase(3, Linksnis.V, "trejetas");
		addTestCase(4, Linksnis.V, "ketvertas");
		addTestCase(5, Linksnis.V, "penketas");
		addTestCase(6, Linksnis.V, "šešetas");
		addTestCase(7, Linksnis.V, "septynetas");
		addTestCase(8, Linksnis.V, "aštuonetas");
		addTestCase(9, Linksnis.V, "devynetas");
		addTestCase(1, Linksnis.K, "vieneto");
		addTestCase(2, Linksnis.K, "dvejeto");
		addTestCase(3, Linksnis.K, "trejeto");
		addTestCase(4, Linksnis.K, "ketverto");
		addTestCase(5, Linksnis.K, "penketo");
		addTestCase(6, Linksnis.K, "šešeto");
		addTestCase(7, Linksnis.K, "septyneto");
		addTestCase(8, Linksnis.K, "aštuoneto");
		addTestCase(9, Linksnis.K, "devyneto");
		addTestCase(1, Linksnis.N, "vienetui");
		addTestCase(2, Linksnis.N, "dvejetui");
		addTestCase(3, Linksnis.N, "trejetui");
		addTestCase(4, Linksnis.N, "ketvertui");
		addTestCase(5, Linksnis.N, "penketui");
		addTestCase(6, Linksnis.N, "šešetui");
		addTestCase(7, Linksnis.N, "septynetui");
		addTestCase(8, Linksnis.N, "aštuonetui");
		addTestCase(9, Linksnis.N, "devynetui");
		addTestCase(1, Linksnis.G, "vienetą");
		addTestCase(2, Linksnis.G, "dvejetą");
		addTestCase(3, Linksnis.G, "trejetą");
		addTestCase(4, Linksnis.G, "ketvertą");
		addTestCase(5, Linksnis.G, "penketą");
		addTestCase(6, Linksnis.G, "šešetą");
		addTestCase(7, Linksnis.G, "septynetą");
		addTestCase(8, Linksnis.G, "aštuonetą");
		addTestCase(9, Linksnis.G, "devynetą");
		addTestCase(1, Linksnis.I, "vienetu");
		addTestCase(2, Linksnis.I, "dvejetu");
		addTestCase(3, Linksnis.I, "trejetu");
		addTestCase(4, Linksnis.I, "ketvertu");
		addTestCase(5, Linksnis.I, "penketu");
		addTestCase(6, Linksnis.I, "šešetu");
		addTestCase(7, Linksnis.I, "septynetu");
		addTestCase(8, Linksnis.I, "aštuonetu");
		addTestCase(9, Linksnis.I, "devynetu");
		addTestCase(1, Linksnis.Vt, "vienete");
		addTestCase(2, Linksnis.Vt, "dvejete");
		addTestCase(3, Linksnis.Vt, "trejete");
		addTestCase(4, Linksnis.Vt, "ketverte");
		addTestCase(5, Linksnis.Vt, "penkete");
		addTestCase(6, Linksnis.Vt, "šešete");
		addTestCase(7, Linksnis.Vt, "septynete");
		addTestCase(8, Linksnis.Vt, "aštuonete");
		addTestCase(9, Linksnis.Vt, "devynete");
		addTestCase(1, Linksnis.S, "vienete");
		addTestCase(2, Linksnis.S, "dvejete");
		addTestCase(3, Linksnis.S, "trejete");
		addTestCase(4, Linksnis.S, "ketverte");
		addTestCase(5, Linksnis.S, "penkete");
		addTestCase(6, Linksnis.S, "šešete");
		addTestCase(7, Linksnis.S, "septynete");
		addTestCase(8, Linksnis.S, "aštuonete");
		addTestCase(9, Linksnis.S, "devynete");
		return TEST_CASES;
	}

	private static void addTestCase(long skaicius, Linksnis linksnis, String tekstas) {
		Object[] parameters = {skaicius, linksnis, tekstas};
		TEST_CASES.add(parameters);
	}

	@Test
	public void matchesExpected() {
		TekstasJunginyje zj = sveikasisSkaicius().toZodisJunginyje(forma());
		assertEquals(tekstas, zj.getTekstas());
		assertEquals(DGS_K, zj.getKitoZodzioSkaiciusIrLinksnis());
	}

	private SveikasisSkaicius sveikasisSkaicius() {
		return new SveikasisSkaicius(skaicius);
	}

	private Forma forma() {
		Forma f = new Forma();
		f.poskyris(Poskyris.Kuopinis);
		f.linksnis(linksnis);
		f.gimine(Gimine.V);
		return f;
	}
}
