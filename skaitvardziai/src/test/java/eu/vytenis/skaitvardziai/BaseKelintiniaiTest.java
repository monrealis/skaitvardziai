package eu.vytenis.skaitvardziai;

import java.util.Map;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;


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
		super.testSkaiciai(skaiciai, linksnis);
	}
	

}
