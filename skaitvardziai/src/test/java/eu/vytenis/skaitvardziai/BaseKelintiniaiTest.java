package eu.vytenis.skaitvardziai;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;


/**
 * Bazinė klasė tekstiniams kelintinių skaitvardžių testams.
 *
 */
public abstract class BaseKelintiniaiTest extends BaseTest {

	
	public BaseKelintiniaiTest() {
		poskyris = Poskyris.Kelintinis;
	}

	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Skaicius skaicius, Linksnis linksnis, Gimine gimine, boolean ivardziuotinis) {
		this.skaicius = skaicius;
		this.gimine = gimine;
		this.ivardziuotinis = ivardziuotinis;
		
		Map<Number, String> temp = new HashMap<Number, String>();
		for (Entry<? extends Number, String> e : skaiciai.entrySet()) {
			String value = e.getValue() + gimineSkaiciusLinksnis(gimine, new SkaiciusIrLinksnis(skaicius, linksnis));
			temp.put(e.getKey(), value);
		}
		skaiciai = temp;
		
		super.testSkaiciai(skaiciai, linksnis);
	}
	

}
