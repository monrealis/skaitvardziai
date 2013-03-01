package eu.vytenis.skaitvardziai.skaiciai;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;


/**
 * Bazinė klasė tekstiniams kelintinių skaitvardžių testams.
 *
 */
public abstract class BaseKelintiniaiTest extends BaseTest {

	
	private Rusis rusis;
	
	public BaseKelintiniaiTest() {
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
	

}
