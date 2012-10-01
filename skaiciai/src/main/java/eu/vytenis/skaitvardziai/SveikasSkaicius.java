
package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.zodziai.ZodzioInfo;

public class SveikasSkaicius {
	private long reiksme;
	
	public SveikasSkaicius(long reiksme) {
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
	
	private void vienzenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		long sveikasSkaicius = forma.getSveikasSkaicius();
		long tikrasSkaicius = forma.getPradinisSveikasSkaicius();
		Gimine gimine = forma.getGimine();
		Poskyris poskyris = forma.getPoskyris();
		boolean ivardziuotinis = forma.isIvardziuotine();
		Skaicius skaicius = forma.getSkaicius();
				
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, 0L, 10L);
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
	
	private void dvizenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		long sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		boolean ivardziuotinis = forma.isIvardziuotine();
		Skaicius skaicius = forma.getSkaicius();
		
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, 0L, 100L);
		checkPoskyris(poskyris);
		
		if (sveikasSkaicius < 10) {
			vienzenklis(zodziai, forma);
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
			
			vienzenklis(zodziai, forma.clone().sveikasSkaicius(vienetai));
			if (poskyris == Poskyris.Kelintinis && vienetai == 0) {
				zodziai.add(ZodzioInfo.getKelintinis(desimtys * 10, skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(desimtys * 10, Gimine.V));
			}
		}
	}
	
	private void trizenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		long sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		Skaicius skaicius = forma.getSkaicius();
		
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, 0L, 1000L);
		checkPoskyris(poskyris);
		
		long dvizenklis = sveikasSkaicius % 100;
		dvizenklis(zodziai, forma.clone().sveikasSkaicius(dvizenklis));
		
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
				zodziai.add(ZodzioInfo.getKelintinisIv(100, skaicius, gimine).daugyba());
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); // ?
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(100, Gimine.V).daugyba());
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); //?
			}
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma, long tukstancioLaipsnis) {
		long sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		Skaicius skaicius = forma.getSkaicius();		

		checkPoskyris(poskyris);
		checkPowerOfThousand(tukstancioLaipsnis);
		
		long sk = sveikasSkaicius;
		long tukstanciu = sk / tukstancioLaipsnis;
		long tukstanciuLiekana = sveikasSkaicius % tukstancioLaipsnis;
		if (tukstancioLaipsnis > 1000L) {
			daugiazenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciuLiekana), tukstancioLaipsnis / 1000);
		} else {
			trizenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciuLiekana));
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
				zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine).daugyba());
				trizenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V).daugyba());
				trizenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
			}			
		} else {
			throw new UnsupportedOperationException(sveikasSkaicius + " is too big");
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		long sveikasSkaicius = forma.getSveikasSkaicius();
		long tikrasSkaicius = forma.getPradinisSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		//Gimine gimine = forma.getGimine();
		//Skaicius skaicius = forma.getSkaicius();
		if (sveikasSkaicius != tikrasSkaicius) {
			throw new IllegalArgumentException();
		}
		checkPoskyris(poskyris);
		daugiazenklis(zodziai, forma, 1000 * 1000 * 1000);
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}
	
	private String kuopinis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		if (forma.getPoskyris() != Poskyris.Kuopinis) {
			throw new IllegalArgumentException("forma.poskyris has invalid value " + forma.getPoskyris());
		}
		long skaicius = forma.getSveikasSkaicius();
		ZodzioInfo z = ZodzioInfo.getKuopinis(skaicius);
		if (z == null) {
			throw new IllegalArgumentException(skaicius + " is invalid value");
		}
		zodziai.add(z);		
		return ZodzioInfo.toString(zodziai, forma);
		
	}
	
	public String toString(Poskyris poskyris, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		FormaIrSkaiciai k = new FormaIrSkaiciai();
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
	public String toString(FormaIrSkaiciai forma) {
		Poskyris poskyris = forma.getPoskyris();
		if (!Arrays.asList(Poskyris.Pagrindinis, Poskyris.Kuopinis, Poskyris.Dauginis, Poskyris.Kelintinis).contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + "");
		}
		List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();		
		if (poskyris == Poskyris.Kuopinis) {
			// Kadangi skaičiai tik nuo 1 iki 9, neapsimoka skaičiuoti standartiškai ir keliuose metoduose daryti papildomus tikrinimus
			kuopinis(zodziai, forma);
		} else {
			daugiazenklis(zodziai, forma);			
		}
		Collections.reverse(zodziai);
		return ZodzioInfo.toString(zodziai, forma);
	}
	
	public String toString(Linksnis linksnis, Gimine gimine) {
		return toString(Poskyris.Pagrindinis, linksnis, gimine);
	}

}
