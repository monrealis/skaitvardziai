package eu.vytenis.skaiciai.esybes;

import java.util.List;

public class ZodzioInfo {
	private boolean vns; // TODO turbūt nereikalingas laukas, o gal ir visa klasė?
	private Zodis zodis;	
	
	public ZodzioInfo(boolean vns, Zodis zodis) {
		this.vns = vns;
		this.zodis = zodis;
	}
	
	public boolean isVns() {
		return vns;
	}
	
	public Zodis getZodis() {
		return zodis;
	}
	
	public static ZodzioInfo getPagrindinisVns(long skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getPagrindinis(skaicius, gimine);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getPagrindinisDgs(long skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getPagrindinis(skaicius, gimine);
		return new ZodzioInfo(false, zodis);
	}
	
	public static ZodzioInfo getDauginisVns(long skaicius) {
		Zodis zodis = Zodis.getDauginis(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getDauginisDgs(long skaicius) {
		Zodis zodis = Zodis.getDauginis(skaicius);
		return new ZodzioInfo(false, zodis);
	}
	
	public static ZodzioInfo getKelintinisVns(long skaicius, Gimine gimine, boolean ivardziuotinis) {
		Zodis zodis = Zodis.getKelintinis(skaicius, gimine, ivardziuotinis);
		return new ZodzioInfo(true, zodis);
	}

	public static ZodzioInfo getKelintinisIvVns(long skaicius, Gimine gimine) {
		Zodis zodis = Zodis.getKelintinisIv(skaicius, gimine);
		return new ZodzioInfo(true, zodis);
	}

	
	public static ZodzioInfo getKuopinisVns(long skaicius) {
		Zodis zodis = Zodis.getKuopinis(skaicius);
		if (zodis == null) {
			return null;
		}
		return new ZodzioInfo(true, zodis);
	}
	
	public static String toString(List<? extends ZodzioInfo> zodziai, Kontekstas kontekstas) {
		Linksnis linksnis = kontekstas.getLinksnis();
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
		boolean kelintinis = kontekstas.getPoskyris() == Poskyris.Kelintinis;
		//boolean vns = true;
		
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
				s = zodis.toString(true, linksnis); // pvz., "dvidešimt _vieną_"
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
