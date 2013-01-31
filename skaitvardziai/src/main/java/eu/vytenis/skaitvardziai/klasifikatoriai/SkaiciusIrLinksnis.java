package eu.vytenis.skaitvardziai.klasifikatoriai;

import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;

/**
 * Skaičiaus (vienaskaita/daugiskaita) ir linksnio pora.
 *
 */
public class SkaiciusIrLinksnis implements Cloneable, UnmodifiableCapable {
	/** Skaičius (vienaskaita/daugiskaita). */
	private Skaicius skaicius = Skaicius.V;
	/** Linksnis. */
	private Linksnis linksnis = Linksnis.V;
	/** Ar skaičius ir linksnis neredaguojami? */
	private boolean unmodifiable;
	
	public SkaiciusIrLinksnis() {
		
	}
	
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
	
	public void clear() {
		skaicius = null;
		linksnis = null;
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
