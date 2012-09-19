package eu.vytenis.skaiciai.esybes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Skaicius {
	private long reiksme;
	
	public Skaicius(long reiksme) {
		this.reiksme = reiksme;
	}
	
	public long getReiksme() {
		return reiksme;
	}
	
	private void vienzenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
				
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
	
	private void dvizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		if (skaicius < 0 || skaicius > 100) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		if (skaicius < 10) {
			vienzenklis(zodziai, kontekstas);
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
			
			vienzenklis(zodziai, kontekstas.clone(vienetai));
			if (poskyris == Poskyris.Kelintinis && vienetai == 0) {
				zodziai.add(ZodzioInfo.getKelintinisVns(desimtys * 10));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(desimtys * 10));
			}
		}
	}
	
	private void trizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		if (skaicius < 0 || skaicius > 1000) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long dvizenklis = skaicius % 100;
		dvizenklis(zodziai, kontekstas.clone(dvizenklis));
		
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
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		if (skaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long sk = skaicius;
		trizenklis(zodziai, kontekstas.clone(skaicius % 1000));
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
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinisVns(1000));
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			}			
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V);
	}
	
	private String kuopinis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSkaicius();
		ZodzioInfo z = ZodzioInfo.getKuopinisVns(skaicius);
		if (z == null) {
			throw new IllegalArgumentException(skaicius + " is invalid value");
		}
		zodziai.add(z);		
		return ZodzioInfo.toString(zodziai, kontekstas);
		
	}
	
	public String toString(Poskyris poskyris, Linksnis linksnis) {
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Kuopinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		Kontekstas k = new Kontekstas();
		k.setLinksnis(linksnis);
		k.setPoskyris(poskyris);
		k.setSkaicius(reiksme);
		k.setPradinisSkaicius(reiksme);
		List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();		
		if (poskyris == Poskyris.Kuopinis) {
			// Kadangi skaičiai tik nuo 1 iki 9, neapsimoka skaičiuoti standartiškai ir keliuose metoduose daryti papildomus tikrinimus
			kuopinis(zodziai, k);
		} else {
			daugiazenklis(zodziai, k);			
		}
		Collections.reverse(zodziai);
		return ZodzioInfo.toString(zodziai, k);
	}
	
	public String toString(Linksnis linksnis) {
		return toString(Poskyris.Pagrindinis, linksnis);
	}

}
