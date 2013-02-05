package eu.vytenis.skaitvardziai.checks;

/**
 * Interfeisas, per kurį objektai gali būti pažymėti, kad jie negali būti redaguojami.
 */
public interface UnmodifiableCapable {
	
	/**
	 * Ar objekto būsena neredaguojama.
	 * @return ar neredaguojamas
	 */
	boolean isUnmodifiable();
	
	/**
	 * Pažymi, ar objektas yra neredaguojamas.
	 * @param unmodifiable ar neredaguojamas
	 */
	void setUnmodifiable(boolean unmodifiable);

}
