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
	
	public static ZodzioInfo getVns(long skaicius) {
		Zodis zodis = Zodis.get(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getDgs(long skaicius) {
		Zodis zodis = Zodis.get(skaicius);
		return new ZodzioInfo(false, zodis);
	}
	
	public static String toString(List<? extends ZodzioInfo> zodziai, Linksnis linksnis) {
		StringBuilder r = new StringBuilder();
		boolean pirmas = true;
		boolean vns = true;
		
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
				s = zodis.toString(vns, ankstesnis.getZodis().getKitasLinksnis());
			} else {
				s = zodis.toString(true, paskutinis ? linksnis : Linksnis.V);
			}
			r.append(s);
			
			vns = zodis.isKitasVns();
			
		}
		return r.toString();		
	}
	
	@Override
	public String toString() {
		return zodis.toString();
	}

}
