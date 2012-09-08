package eu.vytenis.skaiciai.esybes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Skaicius {
	private long skaicius;
	public Skaicius(long skaicius) {
		this.skaicius = skaicius;
	}
	
	public long getSkaicius() {
		return skaicius;
	}
	
	private void dvizenklis(long skaicius, List<ZodzioInfo> zodziai) {
		if (skaicius < 0 || skaicius > 100) {
			throw new IllegalArgumentException();
		}
		if (skaicius < 20) {
			zodziai.add(ZodzioInfo.getVns(skaicius).rodytiTikPirma(skaicius, 0));
		} else {
			long vienetai = skaicius % 10;
			skaicius /= 10;
			long desimtys = skaicius % 10;
			zodziai.add(ZodzioInfo.getVns(vienetai).rodytiTikPirma(vienetai, 0));
			zodziai.add(ZodzioInfo.getVns(desimtys * 10));
		}
	}
	
	private void trizenklis(long skaicius, List<ZodzioInfo> zodziai) {
		if (skaicius < 0 || skaicius > 1000) {
			throw new IllegalArgumentException();
		}
		dvizenklis(skaicius % 100, zodziai);
		long simtai = skaicius / 100;
		if (simtai == 1) {
			zodziai.add(ZodzioInfo.getVns(100));
		} else if (simtai > 1) {
			//zodziai.add(ZodzioInfo.getDgs(100));
			zodziai.add(ZodzioInfo.getVns(100));
			zodziai.add(ZodzioInfo.getVns(simtai));
		}
		
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V);
	}
	
	public String toString(Linksnis linksnis) {
		List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();
		long s = skaicius;
		trizenklis(skaicius % 1000, zodziai);
		s /= 1000;
		long tukstanciai = s % 1000;
		
		if (tukstanciai == 1) {
			zodziai.add(ZodzioInfo.getVns(1000));
		} else if (tukstanciai > 1) {
			//zodziai.add(ZodzioInfo.getDgs(1000));
			zodziai.add(ZodzioInfo.getVns(1000));
			dvizenklis(tukstanciai, zodziai);
		}
		
		Collections.reverse(zodziai);
		return ZodzioInfo.toString(zodziai, linksnis);
	}

}
