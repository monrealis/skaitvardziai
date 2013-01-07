
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

public class SveikasisSkaicius {
	public static BigInteger THOUSAND = new BigInteger("1000");
	public static BigInteger HUNDRED = new BigInteger("100");
	public static BigInteger TWENTY = new BigInteger("20");
	public static BigInteger BILLION = new BigInteger("1000000000");
	
	private BigInteger reiksme;
	
	public SveikasisSkaicius(long reiksme) {
		this.reiksme = new BigInteger(Long.toString(reiksme));
	}
	
	public BigInteger getReiksme() {
		return reiksme;
	}
	
	private void checkPoskyris(Poskyris poskyris) {
		List<Poskyris> expected = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis);
		if (!expected.contains(poskyris)) {
			throw new IllegalArgumentException(poskyris + " is invalid: " + expected + " expected");
		}	
	}
	
	private void checkPowerOfThousand(BigInteger tukstancioLaipsnis) {
		BigInteger thousand = THOUSAND;
		List<BigInteger> expected = new ArrayList<BigInteger>();
		for (int i = 1; i <= 3; ++i) {
			expected.add(thousand.pow(i));
		}
		if (!expected.contains(tukstancioLaipsnis)) {
			throw new IllegalArgumentException();
		}
	}
	
	private void vienzenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		BigInteger sveikasSkaicius = forma.getSveikasSkaicius();
		BigInteger tikrasSkaicius = forma.getPradinisSveikasSkaicius();
		Gimine gimine = forma.getGimine();
		Poskyris poskyris = forma.getPoskyris();
		boolean ivardziuotinis = forma.isIvardziuotine();
		Skaicius skaicius = forma.getSkaicius();
				
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, BigInteger.TEN);
		checkPoskyris(poskyris);
		
		if (sveikasSkaicius.equals(BigInteger.ZERO) && tikrasSkaicius.equals(BigInteger.ZERO) || sveikasSkaicius.compareTo(BigInteger.ZERO) > 0) {
			if (poskyris == Poskyris.Pagrindinis) {
				if (sveikasSkaicius.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, Gimine.V));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, gimine));
				}
			} else if (poskyris == Poskyris.Dauginis) {
				zodziai.add(ZodzioInfo.getDauginis(sveikasSkaicius, gimine));
			} else if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinis(sveikasSkaicius, skaicius, gimine, (!sveikasSkaicius.equals(BigInteger.ZERO) ? ivardziuotinis : false)));
			} else {
				throw new IllegalArgumentException();
			}
			
		}
	}
	
	private void dvizenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		BigInteger sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		boolean ivardziuotinis = forma.isIvardziuotine();
		Skaicius skaicius = forma.getSkaicius();
		
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, HUNDRED);
		checkPoskyris(poskyris);
		
		if (sveikasSkaicius.compareTo(BigInteger.TEN) < 0) {
			vienzenklis(zodziai, forma);
		} else if (sveikasSkaicius.compareTo(TWENTY) < 0) {
			if (poskyris == Poskyris.Kelintinis) {
				zodziai.add(ZodzioInfo.getKelintinis(sveikasSkaicius, skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, Gimine.V));
			}
		} else {
			BigInteger vienetai = sveikasSkaicius.mod(BigInteger.TEN);
			sveikasSkaicius = sveikasSkaicius.divide(BigInteger.TEN);
			BigInteger desimtys = sveikasSkaicius.mod(BigInteger.TEN);
			
			vienzenklis(zodziai, forma.clone().sveikasSkaicius(vienetai));
			if (poskyris == Poskyris.Kelintinis && vienetai.equals(BigInteger.ZERO)) {
				zodziai.add(ZodzioInfo.getKelintinis(desimtys.multiply(BigInteger.TEN), skaicius, gimine, ivardziuotinis));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(desimtys.multiply(BigInteger.TEN), Gimine.V));
			}
		}
	}
	
	private void trizenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		BigInteger sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		Skaicius skaicius = forma.getSkaicius();
		
		CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, THOUSAND);
		checkPoskyris(poskyris);
		
		BigInteger dvizenklis = sveikasSkaicius.mod(HUNDRED);
		dvizenklis(zodziai, forma.clone().sveikasSkaicius(dvizenklis));
		
		BigInteger simtai = sveikasSkaicius.divide(HUNDRED);
		BigInteger liekana = sveikasSkaicius.mod(HUNDRED);
		if (simtai.equals(BigInteger.ONE)) {
			if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
				zodziai.add(ZodzioInfo.getKelintinisIv(HUNDRED, skaicius, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(HUNDRED, Gimine.V));
			}
			
		} else if (simtai.compareTo(BigInteger.ONE) > 0) {
			if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
				zodziai.add(ZodzioInfo.getKelintinisIv(HUNDRED, skaicius, gimine).daugyba());
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); // ?
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(HUNDRED, Gimine.V).daugyba());
				zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); //?
			}
		}
	}
	
	private void daugiazenklis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma, BigInteger tukstancioLaipsnis) {
		BigInteger sveikasSkaicius = forma.getSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		Gimine gimine = forma.getGimine();
		Skaicius skaicius = forma.getSkaicius();		

		checkPoskyris(poskyris);
		checkPowerOfThousand(tukstancioLaipsnis);
		
		BigInteger sk = sveikasSkaicius;
		BigInteger tukstanciu = sk.divide(tukstancioLaipsnis);
		BigInteger tukstanciuLiekana = sveikasSkaicius.mod(tukstancioLaipsnis);
		if (tukstancioLaipsnis.compareTo(THOUSAND) > 0) {
			daugiazenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciuLiekana), tukstancioLaipsnis.divide(THOUSAND));
		} else {
			trizenklis(zodziai, forma.clone().sveikasSkaicius(tukstanciuLiekana));
		}
		
		if (tukstanciu.equals(BigInteger.ZERO)) {
			// nieko
		} else if (tukstanciu.equals(BigInteger.ONE)) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
				zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine));
			} else {
				zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V));
			}			
		} else if (tukstanciu.compareTo(BigInteger.ONE) > 0 && tukstanciu.compareTo(THOUSAND) < 0) {
			if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
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
		BigInteger sveikasSkaicius = forma.getSveikasSkaicius();
		BigInteger tikrasSkaicius = forma.getPradinisSveikasSkaicius();
		Poskyris poskyris = forma.getPoskyris();
		//Gimine gimine = forma.getGimine();
		//Skaicius skaicius = forma.getSkaicius();
		if (!sveikasSkaicius.equals(tikrasSkaicius)) {
			throw new IllegalArgumentException();
		}
		checkPoskyris(poskyris);
		daugiazenklis(zodziai, forma, BILLION);
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}
	
	private String kuopinis(List<ZodzioInfo> zodziai, FormaIrSkaiciai forma) {
		if (forma.getPoskyris() != Poskyris.Kuopinis) {
			throw new IllegalArgumentException("forma.poskyris has invalid value " + forma.getPoskyris());
		}
		BigInteger skaicius = forma.getSveikasSkaicius();
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
