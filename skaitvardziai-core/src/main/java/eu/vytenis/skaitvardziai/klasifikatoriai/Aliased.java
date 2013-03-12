package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Sąsaja unikalioms klasifikatorių santrumpoms grąžinti
 * (kiekvienas klasifikatorius turi grąžinti unikalų savo pavadinimą).
 * Naudojama XPath funkcijose.
 * Realizuojančios klasės turi grąžinti skirtingiems objektams unikalias reikšmes.
 */
public interface Aliased {
	
	String alias();
	
	String longName();

}
