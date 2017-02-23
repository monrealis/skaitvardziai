package eu.vytenis.skaitvardziai.skaiciai;

import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

public class TekstasJunginyje {
	private final String tekstas;
	private final SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis;

	public TekstasJunginyje(String tekstas, SkaiciusIrLinksnis kitoZodzioSkaiciusIrLinksnis) {
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
