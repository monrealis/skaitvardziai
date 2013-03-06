package eu.vytenis.skaitvardziai.zodziai;

import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

/**
 * Žodžio ir skaičiaus (vienaskaitos/daugiskaitos) dvejetas.
 *
 */
public class ZodisJunginyje {
	/** Žodis. */
	private Zodis zodis;	
	
	/**
	 * Ar žodžio indėlis indėlis į skaičių, lyginant su ankstesniu žodžiu, yra gautas daugybos būdu (true), ar sumos (true)?
	 * Daugybos pvz.: tūkstantis šimtų.
	 * Sumos pvz.: tūkstantis šimtas.
	 * Konkretus pvz.:
	 * 584356792124L - penki šimtai aštuoniasdešimt keturi milijardai trys šimtai penkiasdešimt šeši milijonai septyni šimtai devyniasdešimt du tūkstančiai šimtas dvidešimt keturi.
	 * Kandangi nėra skaičiaus "vienas", "šimtas" yra pridedamas prie 584356792000L, jei būtų žodis "vienas", šimtas būtų padauginamas iš prieš tai einančio žodžio - "vienas x šimtas". 
	 * 
	 */
	private boolean daugyba = false;
	
	public ZodisJunginyje(Zodis zodis) {
		Checks.checkNotNull("zodis", zodis);
		this.zodis = zodis;
	}
	
	public Zodis getZodis() {
		return zodis;
	}
	
	/**
	 * Pagal perduotus parametrus ir atrinktų žodžių sąrašą suformuoja 
	 * skaitvardį iš vieno ar daugiau tinkamų formų nedalomų skaitvardžių.
	 * (pvz, "dvidešimt pirmą" iš žodžių "dvidešimt", "pirmas" ir parametrų "kelintinis, galininkas".
	 * @param zodziai žodžių sąrašas
	 * @param formaSkaiciai parametrų objektas
	 * @return skaitvardis
	 */
	public static String toString(List<? extends ZodisJunginyje> zodziai, FormaIrSkaiciai formaSkaiciai) {
		StringBuilder r = new StringBuilder();
		boolean kelintinis = formaSkaiciai.getForma().getPoskyris() == Poskyris.Kelintinis;
		
		SkaiciusIrLinksnis skaiciusLinksnis = formaSkaiciai.getForma().toSkaiciusLinksnis();
		
		for (int i = 0; i < zodziai.size(); ++i) {
			ZodisJunginyje dabartinis = zodziai.get(i);
			ZodisJunginyje ankstesnis = i > 0 ? zodziai.get(i - 1) : null;
			ZodisJunginyje kitas = i < zodziai.size() - 1 ? zodziai.get(i + 1) : null;
			
			boolean paskutinisJunginyje = kitas == null;
			boolean paskutinisFragmente = (kitas == null || kitas.isDaugyba());

			Zodis zodis = dabartinis.getZodis();
			String s;
			
			if (zodis.isValdomas() && ankstesnis != null && dabartinis.isDaugyba()) {
				SkaiciusIrLinksnis sl = ankstesnis.getZodis().getKitas().clone();
				if (sl.getLinksnis() == null) {
					sl.setLinksnis(kelintinis ? Linksnis.V : skaiciusLinksnis.getLinksnis());
					// pvz. (kelintinis == true),  "du _šimtai_ dešimtojo"
					// pvz. (kelintinis == false), "du _šimtai_", "keturi _šimtai_ keturiasdešimt vienas", "keturis _šimtus_ keturiasdešimt vieną"
				}
				s = zodis.toString(sl);
			} else {
				boolean vv;
				if (kelintinis) {
					vv = !paskutinisJunginyje; // pvz., "_šimtas_ pirmojo"
				} else {
					vv = !paskutinisFragmente && zodis.isNekaitomasLinksniuojant(); // pvz, "_dvidešimt_ dviejų"
				}		
				s = zodis.toString(vv ? SkaiciusIrLinksnis.VNS_VARD : skaiciusLinksnis);  // pvz. (vnsVard = false), "dvidešimt _vieną_"
			}
			r.append(i > 0 ? " " : "").append(s);
		}
		return r.toString();		
	}
	
	public boolean isDaugyba() {
		return daugyba;
	}
	
	public void setDaugyba(boolean daugyba) {
		this.daugyba = daugyba;
	}
	
	public ZodisJunginyje daugyba() {
		setDaugyba(true);
		return this;
	}
	@Override
	public String toString() {
		return zodis.toString() + " " + daugyba;
	}

}