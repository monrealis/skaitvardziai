
package eu.vytenis.skaiciai.esybes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SveikasSkaicius {
	private long reiksme;
	
	public SveikasSkaicius(long reiksme) {
		this.reiksme = reiksme;
	}
	
	public long getReiksme() {
		return reiksme;
	}
	
	private void vienzenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Gimine gimine = kontekstas.getGimine();
		Poskyris poskyris = kontekstas.getPoskyris();
		boolean ivardziuotinis = kontekstas.isIvardziuotine();
				
		if (skaicius < 0 || skaicius > 10) {
			throw new IllegalArgumentException();
		}
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}		
		if (skaicius == 0 && tikrasSkaicius == 0 || skaicius > 0) {
			if (poskyris == Poskyris.Pagrindinis) {
				if (skaicius == 0) {
					zodziai.add(ZodzioInfo.getPagrindinis(skaicius, Gimine.V));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(skaicius, gimine));
				}
			} else if (poskyris == Poskyris.Dauginis) {
				zodziai.add(ZodzioInfo.getDauginis(skaicius, gimine));
			} else if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinis(skaicius, gimine, (skaicius != 0 ? ivardziuotinis : false)));
			} else {
				throw new IllegalArgumentException();
			}
			
		}
	}
	
	private void dvizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
		boolean ivardziuotinis = kontekstas.isIvardziuotine();
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
				zodziai.add(ZodzioInfo.getKelintinis(skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(skaicius, Gimine.V));
			}
		} else {
			long vienetai = skaicius % 10;
			skaicius /= 10;
			long desimtys = skaicius % 10;
			
			vienzenklis(zodziai, kontekstas.clone(vienetai));
			if (poskyris == Poskyris.Kelintinis && vienetai == 0) {
				zodziai.add(ZodzioInfo.getKelintinis(desimtys * 10, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(desimtys * 10, Gimine.V));
			}
		}
	}
	
	private void trizenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
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
				zodziai.add(ZodzioInfo.getKelintinisIv(100, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(100, Gimine.V));
			}
			
		} else if (simtai > 1) {
			if (poskyris == Poskyris.Kelintinis && liekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(100, gimine));
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); // ?
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(100, Gimine.V));
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); //?
			}
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long skaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
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
				zodziai.add(ZodzioInfo.getKelintinisIv(1000, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(1000, Gimine.V));
			}			
		} else if (tukstanciu > 1 && tukstanciu < 100) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(1000, gimine));
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(1000, Gimine.V));
				dvizenklis(zodziai, kontekstas.clone(tukstanciu));
			}			
		} else {
			throw new UnsupportedOperationException();
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
	
	public String toString(Poskyris poskyris, Linksnis linksnis, Gimine gimine) {
		Kontekstas k = new Kontekstas();
		k.setLinksnis(linksnis);
		k.setPoskyris(poskyris);
		k.setSveikasSkaicius(reiksme);
		k.setPradinisSveikasSkaicius(reiksme);
		k.setGimine(gimine);
		return toString(k);
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
