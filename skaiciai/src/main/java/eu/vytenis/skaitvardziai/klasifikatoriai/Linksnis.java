package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Lietuvių kalbos linksnių sąrašas.
 */
public enum Linksnis implements Aliased {
	/** Vardininkas. */
	V,
	/** Kilmininkas. */
	K,
	/** Naudininkas. */
	N,
	/** Galininkas. */
	G,
	/** Įnagininkas. */
	I,
	/** Vietininkas. */
	Vt,
	/** Šauksmininkas. */
	S;
	
	public String alias() {
		return name();
	};
}
