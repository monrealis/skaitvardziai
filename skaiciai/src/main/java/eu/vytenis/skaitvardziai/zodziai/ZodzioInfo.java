package eu.vytenis.skaitvardziai.zodziai;

import java.util.List;

import eu.vytenis.skaitvardziai.Kontekstas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

/**
 * Žodžio ir skaičiaus (vienaskaitos/daugiskaitos) dvejetas.
 *
 */
public class ZodzioInfo {
	/** Skaičius (vienaskaita/daugiskaita). */
	private Skaicius skaicius = Skaicius.V;
	/** Žodis. */
	private Zodis zodis;	
	
	public ZodzioInfo(Zodis zodis, Skaicius skaicius) {
		this.zodis = zodis;
		this.skaicius = skaicius;
	}
	
	public ZodzioInfo(Zodis zodis) {
		this.zodis = zodis;
	}
	
	public Skaicius getSkaicius() {
		return skaicius;
	}
	
	public Zodis getZodis() {
		return zodis;
	}
	
	public static ZodzioInfo getPagrindinis(long skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getPagrindinis(skaicius, gimine);
		return new ZodzioInfo(zodis);
	}
	
	public static ZodzioInfo getDauginis(long skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getDauginis(skaicius, gimine);
		return new ZodzioInfo(zodis);
	}
	
	public static ZodzioInfo getKelintinis(long sveikasSkaicius, Skaicius skaicius, Gimine gimine, boolean ivardziuotinis) {
		Zodis zodis = Zodis.getKelintinis(sveikasSkaicius, gimine, ivardziuotinis);
		return new ZodzioInfo(zodis, skaicius);
	}

	public static ZodzioInfo getKelintinisIv(long sveikasSkaicius, Skaicius skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getKelintinisIv(sveikasSkaicius, gimine);
		return new ZodzioInfo(zodis, skaicius);
	}

	
	public static ZodzioInfo getKuopinis(long skaicius) {
		Zodis zodis = Zodis.getKuopinis(skaicius);
		if (zodis == null) {
			return null;
		}
		return new ZodzioInfo(zodis);
	}
	
	/**
	 * Pagal perduotus parametrus ir atrinktų žodžių sąrašą suformuoja 
	 * skaitvardį iš vieno ar daugiau tinkamų formų nedalomų skaitvardžių.
	 * (pvz, "dvidešimt pirmą" iš žodžių "dvidešimt", "pirmas" ir parametrų "kelintinis, galininkas".
	 * @param zodziai žodžių sąrašas
	 * @param kontekstas parametrų objektas
	 * @return skaitvardis
	 */
	public static String toString(List<? extends ZodzioInfo> zodziai, Kontekstas kontekstas) {
		Linksnis linksnis = kontekstas.getLinksnis();
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
		boolean kelintinis = kontekstas.getPoskyris() == Poskyris.Kelintinis;
		Skaicius skaicius = kontekstas.getSkaicius();
		
		for (int i = 0; i < zodziai.size(); ++i) {
			ZodzioInfo dabartinis = zodziai.get(i);
			ZodzioInfo ankstesnis = i > 0 ? zodziai.get(i - 1) : null;
			boolean paskutinis = (i == (zodziai.size() - 1));

			if (pirmas) {
				pirmas = false;
			} else {
				r.append(" ");
			}
			
			Zodis zodis = dabartinis.getZodis();
			String s;
			if (zodis.isValdomas() && ankstesnis != null && !paskutinis && kelintinis) {
				SkaiciusIrLinksnis kitas = ankstesnis.getZodis().getKitas().clone();
				kitas.setLinksnis(Linksnis.V);
				s = zodis.toString(kitas); //pvz., "du _šimtai_ dešimtojo"
			} else if (zodis.isValdomas() && ankstesnis != null) {
				SkaiciusIrLinksnis kitas = ankstesnis.getZodis().getKitas().clone();
				if (kitas.getLinksnis() == null) {
					kitas.setLinksnis(linksnis);
				}
				s = zodis.toString(kitas); // pvz., "du _šimtai_", "keturi _šimtai_ keturiasdešimt vienas"
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
	
	@Override
	public String toString() {
		return zodis.toString();
	}

}
