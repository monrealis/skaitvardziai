package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Sąsaja unikalioms klasifikatorių santrumpoms grąžinti
 * (kiekvienas klasifikatorius turi grąžinti unikalų savo pavadinimą).
 * Naudojama XPath funkcijose.
 *
 */
public interface Aliased {
	
	/**
	 * Grąžina unikalią santrumpą.
	 * @return santrumpa
	 */
	String alias();

}
