
package eu.vytenis.skaiciai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaiciai.klasifikatoriai.Gimine;
import eu.vytenis.skaiciai.klasifikatoriai.Linksnis;
import eu.vytenis.skaiciai.klasifikatoriai.Poskyris;
import eu.vytenis.skaiciai.klasifikatoriai.Skaicius;
import eu.vytenis.skaiciai.zodziai.ZodzioInfo;

public class SveikasSkaicius {
	private long reiksme;
	
	public SveikasSkaicius(long reiksme) {
		this.reiksme = reiksme;
	}
	
	public long getReiksme() {
		return reiksme;
	}
	
	private void vienzenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Gimine gimine = kontekstas.getGimine();
		Poskyris poskyris = kontekstas.getPoskyris();
		boolean ivardziuotinis = kontekstas.isIvardziuotine();
		Skaicius skaicius = kontekstas.getSkaicius();
				
		if (sveikasSkaicius < 0 || sveikasSkaicius > 10) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}		
		if (sveikasSkaicius == 0 && tikrasSkaicius == 0 || sveikasSkaicius > 0) {
			if (poskyris == Poskyris.Pagrindinis) {
				if (sveikasSkaicius == 0) {
					zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, Gimine.V));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, gimine));
				}
			} else if (poskyris == Poskyris.Dauginis) {
				zodziai.add(ZodzioInfo.getDauginis(sveikasSkaicius, gimine));
			} else if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinis(sveikasSkaicius, skaicius, gimine, (sveikasSkaicius != 0 ? ivardziuotinis : false)));
			} else {
				throw new IllegalArgumentException();
			}
			
		}
	}
	
	private void dvizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
		boolean ivardziuotinis = kontekstas.isIvardziuotine();
		Skaicius skaicius = kontekstas.getSkaicius();
		
		if (sveikasSkaicius < 0 || sveikasSkaicius > 100) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		if (sveikasSkaicius < 10) {
			vienzenklis(zodziai, kontekstas);
		} else if (sveikasSkaicius < 20) {
			if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinis(sveikasSkaicius, skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, Gimine.V));
			}
		} else {
			long vienetai = sveikasSkaicius % 10;
			sveikasSkaicius /= 10;
			long desimtys = sveikasSkaicius % 10;
			
			vienzenklis(zodziai, kontekstas.clone(vienetai));
			if (poskyris == Poskyris.Kelintinis && vienetai == 0) {
				zodziai.add(ZodzioInfo.getKelintinis(desimtys * 10, skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(desimtys * 10, Gimine.V));
			}
		}
	}
	
	private void trizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
		Skaicius skaicius = kontekstas.getSkaicius();
		
		if (sveikasSkaicius < 0 || sveikasSkaicius > 1000) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long dvizenklis = sveikasSkaicius % 100;
		dvizenklis(zodziai, kontekstas.clone(dvizenklis));
		
		long simtai = sveikasSkaicius / 100;
		long liekana = sveikasSkaicius % 100;
		if (simtai == 1) {
			if (poskyris == Poskyris.Kelintinis && liekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(100, skaicius, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(100, Gimine.V));
			}
			
		} else if (simtai > 1) {
			if (poskyris == Poskyris.Kelintinis && liekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(100, skaicius, gimine));
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); // ?
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(100, Gimine.V));
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); //?
			}
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
		Skaicius skaicius = kontekstas.getSkaicius();
		
		if (sveikasSkaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		long sk = sveikasSkaicius;
		trizenklis(zodziai, kontekstas.clone(sveikasSkaicius % 1000));
		long tukstanciu = sk / 1000;
		long tukstanciuLiekana = sveikasSkaicius % 1000;
		
		if (tukstanciu == 0) {
			// nieko
		} else if (tukstanciu == 1) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(1000, skaicius, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(1000, Gimine.V));
			}			
		} else if (tukstanciu > 1 && tukstanciu < 100) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(1000, skaicius, gimine));
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(1000, Gimine.V));
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			}			
		} else {
			throw new UnsupportedOperationException(sveikasSkaicius + " is too big");
		}
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}
	
	private String kuopinis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSveikasSkaicius();
		ZodzioInfo z = ZodzioInfo.getKuopinis(skaicius);
		if (z == null) {
			throw new IllegalArgumentException(skaicius + " is invalid value");
		}
		zodziai.add(z);		
		return ZodzioInfo.toString(zodziai, kontekstas);
		
	}
	
	public String toString(Poskyris poskyris, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		Kontekstas k = new Kontekstas();
		k.setSkaicius(skaicius);
		k.setLinksnis(linksnis);
		k.setPoskyris(poskyris);
		k.setSveikasSkaicius(reiksme);
		k.setPradinisSveikasSkaicius(reiksme);
		k.setGimine(gimine);
		return toString(k);
	}
	
	public String toString(Poskyris poskyris, Linksnis linksnis, Gimine gimine) {
		return toString(poskyris, Skaicius.V, linksnis, gimine);
	}
	
	// TODO gal statinis gali būti?
	public String toString(Kontekstas kontekstas) {
		Poskyris poskyris = kontekstas.getPoskyris();
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Kuopinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();		
		if (poskyris == Poskyris.Kuopinis) {
			// Kadangi skaičiai tik nuo 1 iki 9, neapsimoka skaičiuoti standartiškai ir keliuose metoduose daryti papildomus tikrinimus
			kuopinis(zodziai, kontekstas);
		} else {
			daugiazenklis(zodziai, kontekstas);			
		}
		Collections.reverse(zodziai);
		return ZodzioInfo.toString(zodziai, kontekstas);
	}
	
	public String toString(Linksnis linksnis, Gimine gimine) {
		return toString(Poskyris.Pagrindinis, linksnis, gimine);
	}

}
