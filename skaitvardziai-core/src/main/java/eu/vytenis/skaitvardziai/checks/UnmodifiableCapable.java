package eu.vytenis.skaitvardziai.checks;

/**
 * Interfeisas, per kurį objektai gali būti pažymėti, kad jie negali būti
 * redaguojami.
 */
public interface UnmodifiableCapable {

	boolean isUnmodifiable();

	void setUnmodifiable(boolean unmodifiable);

}
