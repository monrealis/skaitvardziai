package eu.vytenis.skaitvardziai.klasifikatoriai;

/**
 * Skaičiaus (vienaskaita/daugiskaita) ir linksnio pora.
 *
 */
public class SkaiciusIrLinksnis implements Cloneable {
	/** Skaičius (vienaskaita/daugiskaita). */
	private Skaicius skaicius = Skaicius.V;
	/** Linksnis. */
	private Linksnis linksnis = Linksnis.V;
	
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
	
	@Override
	public SkaiciusIrLinksnis clone() {
		try {
			return (SkaiciusIrLinksnis) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
