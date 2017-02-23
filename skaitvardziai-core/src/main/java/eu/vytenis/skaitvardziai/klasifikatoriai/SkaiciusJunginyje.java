package eu.vytenis.skaitvardziai.klasifikatoriai;

public class SkaiciusJunginyje {
	private final String tekstas;
	private final SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis;

	public SkaiciusJunginyje(String tekstas, SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis) {
		this.tekstas = tekstas;
		this.kitoZodzioSkaiciusIrLinksnis = kitoZodzioSkaiciusIrLinksnis;
	}

	public String getTekstas() {
		return tekstas;
	}

	public SkaiciusIrLinksnis getKitoZodzioSkaiciusIrLinksnis() {
		return kitoZodzioSkaiciusIrLinksnis;
	}
}
