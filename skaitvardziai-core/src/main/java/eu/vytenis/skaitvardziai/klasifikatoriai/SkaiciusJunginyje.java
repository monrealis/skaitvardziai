package eu.vytenis.skaitvardziai.klasifikatoriai;

public class SkaiciusJunginyje {
	private final String skaicius;
	private final SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis;

	public SkaiciusJunginyje(String skaicius, SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis) {
		this.skaicius = skaicius;
		this.kitoZodzioSkaiciusIrLinksnis = kitoZodzioSkaiciusIrLinksnis;
	}

	public String getSkaicius() {
		return skaicius;
	}

	public SkaiciusIrLinksnis getKitoZodzioSkaiciusIrLinksnis() {
		return kitoZodzioSkaiciusIrLinksnis;
	}
}
