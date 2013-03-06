package eu.vytenis.skaitvardziai.skaiciai;

import java.util.Map;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

public abstract class DauginiaiTest extends SkaitvardziaiTest {
	
	protected Gimine gimine;
	
	protected void testSkaiciai(Map<? extends Number, String> skaiciai, Linksnis linksnis) {
		Forma f = new Forma();
		f.setSkaicius(Skaicius.V);
		f.setPoskyris(Poskyris.Dauginis);
		f.setGimine(gimine);
		f.setLinksnis(linksnis);
		
		super.testSkaiciai(skaiciai, f);
	}


}
