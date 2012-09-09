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
	
	private void vienzenklis(long skaicius, List<ZodzioInfo> zodziai, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 10) {
			throw new IllegalArgumentException();
		}
		if (skaicius == 0 && tikrasSkaicius == 0 || skaicius > 0) {
			zodziai.add(ZodzioInfo.getVns(skaicius));
		}
	}
	
	private void dvizenklis(long skaicius, List<ZodzioInfo> zodziai, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 100) {
			throw new IllegalArgumentException();
		}
		if (skaicius < 10) {
			vienzenklis(skaicius, zodziai, tikrasSkaicius);
		} else if (skaicius < 20) {
			zodziai.add(ZodzioInfo.getVns(skaicius));
		} else {
			long vienetai = skaicius % 10;
			skaicius /= 10;
			long desimtys = skaicius % 10;
			
			vienzenklis(vienetai, zodziai, tikrasSkaicius);
			zodziai.add(ZodzioInfo.getVns(desimtys * 10));
		}
	}
	
	private void trizenklis(long skaicius, List<ZodzioInfo> zodziai, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 1000) {
			throw new IllegalArgumentException();
		}
		long dvizenklis = skaicius % 100;
		dvizenklis(dvizenklis, zodziai, tikrasSkaicius);
		
		long simtai = skaicius / 100;
		if (simtai == 1) {
			zodziai.add(ZodzioInfo.getVns(100));
		} else if (simtai > 1) {
			//zodziai.add(ZodzioInfo.getDgs(100));
			zodziai.add(ZodzioInfo.getVns(100));
			zodziai.add(ZodzioInfo.getVns(simtai));
		}
	}
	
	private void daugiazenklis(long skaicius, List<ZodzioInfo> zodziai, long tikrasSkaicius) {
		if (skaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		long sk = skaicius;
		trizenklis(skaicius % 1000, zodziai, sk);
		sk /= 1000;
		long tukstanciai = sk % 1000;
		
		if (tukstanciai == 1) {
			zodziai.add(ZodzioInfo.getVns(1000));
		} else if (tukstanciai > 1) {
			//zodziai.add(ZodzioInfo.getDgs(1000));
			zodziai.add(ZodzioInfo.getVns(1000));
			dvizenklis(tukstanciai, zodziai, sk);
		}
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V);
	}
	
	public String toString(Linksnis linksnis) {
		List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();		
		daugiazenklis(skaicius, zodziai, skaicius);			
		Collections.reverse(zodziai);
		return ZodzioInfo.toString(zodziai, linksnis);
	}

}
