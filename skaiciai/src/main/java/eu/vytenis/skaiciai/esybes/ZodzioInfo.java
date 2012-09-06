package eu.vytenis.skaiciai.esybes;

import java.util.Iterator;

public class ZodzioInfo {
	private boolean vns;
	private Zodis zodis;
	private boolean rodytiTikPirma;
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
	
	public boolean isRodytiTikPirma() {
		return rodytiTikPirma;
	}
	
	public void setRodytiTikPirma(boolean rodytiTikPaskutini) {
		this.rodytiTikPirma = rodytiTikPaskutini;
	}
	
	public ZodzioInfo rodytiTikPirma(long skaicius, long reiksme) {
		setRodytiTikPirma(skaicius == reiksme);
		return this;
	}
	
	public static ZodzioInfo getVns(long skaicius) {
		Zodis zodis = Zodis.get(skaicius);
		return new ZodzioInfo(true, zodis);
	}
	
	public static ZodzioInfo getDgs(long skaicius) {
		Zodis zodis = Zodis.get(skaicius);
		return new ZodzioInfo(false, zodis);
	}
	
	public static String toString(Iterable<? extends ZodzioInfo> zodziai) {
		StringBuilder r = new StringBuilder();
		Iterator<? extends ZodzioInfo> it = zodziai.iterator();
		boolean pirmas = true;
		boolean vns = true;
		Linksnis linksnis = Linksnis.V;
		while (it.hasNext()) {
			ZodzioInfo zodzioInfo = it.next();
			if (zodzioInfo.isRodytiTikPirma() && !pirmas) {
				pirmas = false;
				continue;
			}
			if (pirmas) {
				pirmas = false;
			} else {
				r.append(" ");
			}
			
			Zodis zodis = zodzioInfo.getZodis();
			String s;
			if (zodis.isValdomas()) {
				s = zodis.toString(vns, linksnis);
			} else {
				s = zodis.toString();
			}
			r.append(s);
			
			vns = zodis.isKitasVns();
			linksnis = zodis.getKitasLinksnis();
			
		}
		return r.toString();		
	}

}
