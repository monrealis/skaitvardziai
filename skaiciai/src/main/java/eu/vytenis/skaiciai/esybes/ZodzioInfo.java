package eu.vytenis.skaiciai.esybes;

import java.util.List;

public class ZodzioInfo {
	private Skaicius skaicius = Skaicius.V;
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
	
	public static String toString(List<? extends ZodzioInfo> zodziai, Kontekstas kontekstas) {
		Linksnis linksnis = kontekstas.getLinksnis();
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
		boolean kelintinis = kontekstas.getPoskyris() == Poskyris.Kelintinis;
		Skaicius skaicius = kontekstas.getSkaicius();
		boolean vns = skaicius == Skaicius.V;
		
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
				s = zodis.toString(ankstesnis.getZodis().isKitasVns(), Linksnis.V); //pvz., "du _šimtai_ dešimtojo"
			} else if (zodis.isValdomas() && ankstesnis != null) {
				s = zodis.toString(ankstesnis.getZodis().isKitasVns(), linksnis); // pvz., "du _šimtai_", "keturi _šimtai_ keturiasdešimt vienas"
			} else if (!paskutinis && zodis.isNekaitomasLinksniuojant()) {
				s = zodis.toString(true, Linksnis.V); // pvz, "_dvidešimt_ dviejų"
			} else if (!paskutinis && kelintinis) {
				s = zodis.toString(true, Linksnis.V); // pvz., "_šimtas_ pirmojo"
			} else {
				s = zodis.toString(vns, linksnis); // pvz., "dvidešimt _vieną_"
			}
			r.append(s);
			
			//vns = zodis.isKitasVns();
		}
		return r.toString();		
	}
	
	@Override
	public String toString() {
		return zodis.toString();
	}

}
