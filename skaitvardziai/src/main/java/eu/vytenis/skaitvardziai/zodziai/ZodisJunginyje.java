package eu.vytenis.skaitvardziai.zodziai;

import java.util.List;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

/**
 * Žodžio ir skaičiaus (vienaskaitos/daugiskaitos) dvejetas.
 *
 */
// TODO išmesti arba pervadinti
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
		CheckUtil.checkNotNull("zodis", zodis);
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
		Linksnis linksnis = formaSkaiciai.getForma().getLinksnis();
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
		boolean kelintinis = formaSkaiciai.getForma().getPoskyris() == Poskyris.Kelintinis;
		Skaicius skaicius = formaSkaiciai.getForma().getSkaicius();
		
		for (int i = 0; i < zodziai.size(); ++i) {
			ZodisJunginyje dabartinis = zodziai.get(i);
			ZodisJunginyje ankstesnis = i > 0 ? zodziai.get(i - 1) : null;
			ZodisJunginyje kitas = i < zodziai.size() - 1 ? zodziai.get(i + 1) : null;
			boolean paskutinis = kitas == null
					|| !kelintinis && kitas != null && kitas.isDaugyba();
					// TODO turbūt parašyti kažkokią sąlyga panašią į
					// kelintinis && kitas == null ?? 

			if (pirmas) {
				pirmas = false;
			} else {
				r.append(" ");
			}
			
			Zodis zodis = dabartinis.getZodis();
			String s;
			if (zodis.isValdomas() && ankstesnis != null && !paskutinis && kelintinis && dabartinis.isDaugyba()) {
				SkaiciusIrLinksnis kitoSkaiciusLinksnis = ankstesnis.getZodis().getKitas().clone();
				if (kitoSkaiciusLinksnis.getLinksnis() == null) {
					kitoSkaiciusLinksnis.setLinksnis(Linksnis.V);
				}
				s = zodis.toString(kitoSkaiciusLinksnis); //pvz., "du _šimtai_ dešimtojo"
			} else if (zodis.isValdomas() && ankstesnis != null && dabartinis.isDaugyba()) {
				SkaiciusIrLinksnis kitoSkaiciusLinksnis = ankstesnis.getZodis().getKitas().clone();
				if (kitoSkaiciusLinksnis.getLinksnis() == null) {
					kitoSkaiciusLinksnis.setLinksnis(linksnis);
				}
				s = zodis.toString(kitoSkaiciusLinksnis); // pvz., "du _šimtai_", "keturi _šimtai_ keturiasdešimt vienas"
			} else if (!paskutinis && zodis.isNekaitomasLinksniuojant()) {
				s = zodis.toString(); // pvz, "_dvidešimt_ dviejų"
			} else if (!paskutinis && kelintinis) {
				s = zodis.toString(); // pvz., "_šimtas_ pirmojo"
			} else {
				s = zodis.toString(new SkaiciusIrLinksnis(skaicius, linksnis)); // pvz., "dvidešimt _vieną_"
			}
			r.append(s);
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
