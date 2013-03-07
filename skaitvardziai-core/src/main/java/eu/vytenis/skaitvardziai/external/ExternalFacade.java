package eu.vytenis.skaitvardziai.external;

import eu.vytenis.skaitvardziai.skaiciai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.skaiciai.Trupmena;


/**
 * Metodai, kuriuos kviečia išoriniai (ne JAVA) API.
 */
public class ExternalFacade {
	
	public static String sveikasis(SveikasisSkaicius sveikasis) {
		return sveikasis.toString();
	}
	
	public static String trupmena(Trupmena trupmena) {
		return trupmena.toString();
		
	}
}
