package eu.vytenis.skaiciai.esybes;

import java.util.List;

public class ZodzioInfo {
	private boolean vns;
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
	
	public static ZodzioInfo getPagrindinisVns(long skaicius) {
		Zodis zodis = Zodis.getPagrindinis(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getPagrindinisDgs(long skaicius) {
		Zodis zodis = Zodis.getPagrindinis(skaicius);
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
	
	public static ZodzioInfo getKelintinisVns(long skaicius) {
		Zodis zodis = Zodis.getKelintinis(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getKelintinisDgs(long skaicius) {
		Zodis zodis = Zodis.getKelintinis(skaicius);
		return new ZodzioInfo(false, zodis);
	}
	
	public static ZodzioInfo getKelintinisIvVns(long skaicius) {
		Zodis zodis = Zodis.getKelintinisIv(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getKelintinisIvDgs(long skaicius) {
		Zodis zodis = Zodis.getKelintinisIv(skaicius);
		return new ZodzioInfo(false, zodis);
	}
	
	public static String toString(List<? extends ZodzioInfo> zodziai, Linksnis linksnis) {
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
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
			if (zodis.isValdomas() && ankstesnis != null) {
				s = zodis.toString(ankstesnis.getZodis().isKitasVns(), linksnis);
			} else if (!paskutinis && zodis.isNekaitomasLinksniuojant()) {
				s = zodis.toString(true, Linksnis.V);
			} else {
				s = zodis.toString(true, linksnis);
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
