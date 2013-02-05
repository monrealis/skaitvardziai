package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.util.Arrays;

import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;
import eu.vytenis.skaitvardziai.util.CompareUtils;

/**
 * Skaičiaus (vienaskaita/daugiskaita) ir linksnio pora.
 *
 */
public class SkaiciusIrLinksnis implements Cloneable, Comparable<SkaiciusIrLinksnis>, UnmodifiableCapable {
	/** Skaičius (vienaskaita/daugiskaita). */
	private Skaicius skaicius;
	/** Linksnis. */
	private Linksnis linksnis;

	/** Ar skaičius ir linksnis neredaguojami? */
	private boolean unmodifiable;
	
	public SkaiciusIrLinksnis(Skaicius skaicius, Linksnis linksnis) {
		this.skaicius = skaicius;
		this.linksnis = linksnis;		
	}
	
	public Skaicius getSkaicius() {
		return skaicius;
	}
	
	public void setSkaicius(Skaicius skaicius) {
		this.skaicius = skaicius;
	}
	
	public Linksnis getLinksnis() {
		return linksnis;
	}
	
	public void setLinksnis(Linksnis linksnis) {
		this.linksnis = linksnis;
	}

	
	/** Žiūrėti: {@link UnmodifiableCapable#isUnmodifiable()}. */
	public boolean isUnmodifiable() {
		return unmodifiable;
	}
	
	/** Žiūrėti: {@link UnmodifiableCapable#setUnmodifiable(boolean)}. */
	public void setUnmodifiable(boolean unmodifiable) {
		this.unmodifiable = unmodifiable;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SkaiciusIrLinksnis)) {
			return false;
		}
		SkaiciusIrLinksnis o = (SkaiciusIrLinksnis) obj;
		return skaicius == o.skaicius && linksnis == o.linksnis;
	}
	
	@Override
	public int hashCode() {
		return (skaicius != null ? skaicius.hashCode() : 0) << 16 | (linksnis != null ? linksnis.hashCode() : 0);
	}
	
	@Override
	public SkaiciusIrLinksnis clone() {
		try {
			return (SkaiciusIrLinksnis) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Palygina pagal skaičių, paskui pagal linksnį.
	 * Žiūrėti: {@link Comparable#compareTo(Object)}	
	 */
	public int compareTo(SkaiciusIrLinksnis o) {
		return CompareUtils.compareLists(Arrays.<Enum<?>>asList(skaicius, linksnis), Arrays.<Enum<?>>asList(o.skaicius, o.linksnis));
	}
	
	@Override
	public String toString() {
		if (skaicius != null && linksnis != null) {
			return skaicius + " " + linksnis;
		} else if (skaicius != null) {
			return skaicius.toString();
		} else if (linksnis != null) {
			return linksnis.toString();
		} else {
			return "";
		}
	}

}
