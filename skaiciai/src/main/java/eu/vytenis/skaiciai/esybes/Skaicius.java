package eu.vytenis.skaiciai.esybes;

import java.util.ArrayList;
import java.util.Arrays;
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
	
	private void vienzenklis(long skaicius, List<ZodzioInfo> zodziai, Poskyris poskyris, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 10) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}		
		if (skaicius == 0 && tikrasSkaicius == 0 || skaicius > 0) {
			if (poskyris == Poskyris.Pagrindinis) {
				zodziai.add(ZodzioInfo.getPagrindinisVns(skaicius));
			} else if (poskyris == Poskyris.Dauginis) {
				zodziai.add(ZodzioInfo.getDauginisVns(skaicius));
			} else if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinisVns(skaicius));
			} else {
				throw new IllegalArgumentException();
			}
			
		}
	}
	
	private void dvizenklis(long skaicius, List<ZodzioInfo> zodziai, Poskyris poskyris, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 100) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		if (skaicius < 10) {
			vienzenklis(skaicius, zodziai, poskyris, tikrasSkaicius);
		} else if (skaicius < 20) {
			if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinisVns(skaicius));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(skaicius));
			}
		} else {
			long vienetai = skaicius % 10;
			skaicius /= 10;
			long desimtys = skaicius % 10;
			
			vienzenklis(vienetai, zodziai, poskyris, tikrasSkaicius);
			if (poskyris == Poskyris.Kelintinis && vienetai == 0) {
				zodziai.add(ZodzioInfo.getKelintinisVns(desimtys * 10));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(desimtys * 10));
			}
		}
	}
	
	private void trizenklis(long skaicius, List<ZodzioInfo> zodziai, Poskyris poskyris, long tikrasSkaicius) {
		if (skaicius < 0 || skaicius > 1000) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long dvizenklis = skaicius % 100;
		dvizenklis(dvizenklis, zodziai, poskyris, tikrasSkaicius);
		
		long simtai = skaicius / 100;
		long liekana = skaicius % 100;
		if (simtai == 1) {
			if (poskyris == Poskyris.Kelintinis && liekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIvVns(100));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(100));
			}
			
		} else if (simtai > 1) {
			if (poskyris == Poskyris.Kelintinis && liekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIvVns(100));
				zodziai.add(ZodzioInfo.getPagrindinisVns(simtai));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(100));
				zodziai.add(ZodzioInfo.getPagrindinisVns(simtai));
			}
		}
	}
	
	private void daugiazenklis(long skaicius, List<ZodzioInfo> zodziai, Poskyris poskyris, long tikrasSkaicius) {
		if (skaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long sk = skaicius;
		trizenklis(skaicius % 1000, zodziai, poskyris, sk);
		long tukstanciu = sk / 1000;
		long tukstanciuLiekana = skaicius % 1000;
		
		if (tukstanciu == 0) {
			// nieko
		} else if (tukstanciu == 1) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIvVns(1000));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(1000));
			}			
		} else if (tukstanciu > 1 && tukstanciu < 100) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIvVns(1000));
				dvizenklis(tukstanciu, zodziai, poskyris, sk);
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(1000));
				dvizenklis(tukstanciu, zodziai, poskyris, sk);
			}			
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V);
	}
	
	private String kuopinis(Linksnis linksnis, long skaicius) {
		Zodis z = Zodis.getKuopinis(skaicius);
		if (z == null) {
			throw new IllegalArgumentException(skaicius + " is invalid value");
		}
		List<ZodzioInfo> zodziai = Arrays.asList(new ZodzioInfo(true, z))	;		
		return ZodzioInfo.toString(zodziai, linksnis);
		
	}
	
	public String toString(Poskyris poskyris, Linksnis linksnis) {
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Kuopinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		if (poskyris == Poskyris.Kuopinis) {
			return kuopinis(linksnis, skaicius);
		} else {
			List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();		
			daugiazenklis(skaicius, zodziai, poskyris, skaicius);			
			Collections.reverse(zodziai);
			return ZodzioInfo.toString(zodziai, linksnis);
		}
	}
	
	public String toString(Linksnis linksnis) {
		return toString(Poskyris.Pagrindinis, linksnis);
	}

}
