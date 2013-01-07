
package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.zodziai.ZodzioInfo;

public class SveikasisSkaicius {
	private long reiksme;
	
	public SveikasisSkaicius(long reiksme) {
		this.reiksme = reiksme;
	}
	
	public long getReiksme() {
		return reiksme;
	}
	
	private void checkPoskyris(Poskyris poskyris) {
		List<Poskyris> expected = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis);
		if (!expected.contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + " is invalid: " + expected + " expected");
		}	
	}
	
	private void checkPowerOfThousand(long tukstancioLaipsnis) {
		BigInteger thousand = new BigInteger("1000");
		List<Long> expected = new ArrayList<Long>();
		for (int i = 1; i <= 3; ++i) {
			expected.add(thousand.pow(i).longValue());
		}
		if (!expected.contains(tukstancioLaipsnis)) {
			throw new IllegalArgumentException();
		}
	}
	
	private void vienzenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Gimine gimine = kontekstas.getGimine();
		Poskyris poskyris = kontekstas.getPoskyris();
		boolean ivardziuotinis = kontekstas.isIvardziuotine();
		Skaicius skaicius = kontekstas.getSkaicius();
				
		CheckUtil.checkMinInclusive("kontekstas.sveikasSkaicius", sveikasSkaicius, 0L, 10L);
		checkPoskyris(poskyris);
		
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
		
		CheckUtil.checkMinInclusive("kontekstas.sveikasSkaicius", sveikasSkaicius, 0L, 100L);
		checkPoskyris(poskyris);
		
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
		
		CheckUtil.checkMinInclusive("kontekstas.sveikasSkaicius", sveikasSkaicius, 0L, 1000L);
		checkPoskyris(poskyris);
		
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
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas, long tukstancioLaipsnis) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		Gimine gimine = kontekstas.getGimine();
		Skaicius skaicius = kontekstas.getSkaicius();		

		checkPoskyris(poskyris);
		checkPowerOfThousand(tukstancioLaipsnis);
		
		long sk = sveikasSkaicius;
		long tukstanciu = sk / tukstancioLaipsnis;
		long tukstanciuLiekana = sveikasSkaicius % tukstancioLaipsnis;
		if (tukstancioLaipsnis > 1000L) {
			daugiazenklis(zodziai, kontekstas.clone(tukstanciuLiekana), tukstancioLaipsnis / 1000);
		} else {
			trizenklis(zodziai, kontekstas.clone(tukstanciuLiekana));
		}
		
		if (tukstanciu == 0) {
			// nieko
		} else if (tukstanciu == 1) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V));
			}			
		} else if (tukstanciu > 1 && tukstanciu < 1000) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana == 0) {
				zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine));
				trizenklis(zodziai, kontekstas.clone(tukstanciu));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V));
				trizenklis(zodziai, kontekstas.clone(tukstanciu));
			}			
		} else {
			throw new UnsupportedOperationException(sveikasSkaicius + " is too big");
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		long sveikasSkaicius = kontekstas.getSveikasSkaicius();
		long tikrasSkaicius = kontekstas.getPradinisSveikasSkaicius();
		Poskyris poskyris = kontekstas.getPoskyris();
		//Gimine gimine = kontekstas.getGimine();
		//SkaiciusXPathFunctions skaicius = kontekstas.getSkaicius();
		if (sveikasSkaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		checkPoskyris(poskyris);
		daugiazenklis(zodziai, kontekstas, 1000 * 1000 * 1000);
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}
	
	private String kuopinis(List<ZodzioInfo> zodziai, Kontekstas kontekstas) {
		if (kontekstas.getPoskyris() != Poskyris.Kuopinis) {
			throw new IllegalArgumentException("kontekstas.poskyris has invalid value " + kontekstas.getPoskyris());
		}
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
