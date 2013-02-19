package eu.vytenis.skaitvardziai.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.SveikasisSkaicius;
import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.zodziai.ZodzioInfo;

public class SveikasisBuilder {

	private List<ZodzioInfo> zodziai = new ArrayList<ZodzioInfo>();
	/** Kokių poskyrių gali būti sveikųjų skaičių skaitvardžiai. */
	public static final List<Poskyris> SVEIKUJU_SKAICIU_NEKUOPINIU_SKAITV_POSKYRIAI = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis);
	/** Kokių poskyrių gali būti sveikųjų skaičių skaitvardžiai. */
	public static final List<Poskyris> SVEIKUJU_SKAICIU_SKAITV_POSKYRIAI = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis, Poskyris.Kuopinis);
	
	private Vienzenkliai vienzenkliai = new Vienzenkliai();
	private Dvizenkliai dvizenkliai = new Dvizenkliai();
	private Trizenkliai trizenkliai = new Trizenkliai();
	private Daugiazenkliai daugiazenkliai = new Daugiazenkliai();
	private Kuopiniai kuopiniai = new Kuopiniai();
	
	
	public List<ZodzioInfo> getZodziai() {
		return zodziai;
	}
	
	public void build(FormaIrSkaiciai forma) {
		Poskyris poskyris = forma.getForma().getPoskyris();
		if (poskyris == Poskyris.Kuopinis) {
			kuopiniai.buildKuopinis(forma);
		} else {
			daugiazenkliai.buildDaugiazenklis(forma);
		}
	}


	private class Vienzenkliai {

		private void buildVienzenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			BigInteger tikrasSkaicius = formaIrSkaiciai.getPradinisSveikasisSkaicius();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			boolean ivardziuotinis = formaIrSkaiciai.getForma().isIvardziuotine();
			Skaicius skaicius = formaIrSkaiciai.getForma().getSkaicius();
					
			CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, BigInteger.TEN);
			BuilderCheckUtil.checkSveikojoSkaiciausPoskyrisNekuopinis(poskyris);
			
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
		
	}
	
	private class Dvizenkliai {

		private void buildDvizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			boolean ivardziuotinis = formaIrSkaiciai.getForma().isIvardziuotine();
			Skaicius skaicius = formaIrSkaiciai.getForma().getSkaicius();
			
			CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, SveikasisSkaicius.HUNDRED);
			BuilderCheckUtil.checkSveikojoSkaiciausPoskyrisNekuopinis(poskyris);
			
			if (sveikasSkaicius.compareTo(BigInteger.TEN) < 0) {
				vienzenkliai.buildVienzenklis(formaIrSkaiciai);
			} else if (sveikasSkaicius.compareTo(SveikasisSkaicius.TWENTY) < 0) {
				if (poskyris == Poskyris.Kelintinis) {
					zodziai.add(ZodzioInfo.getKelintinis(sveikasSkaicius, skaicius, gimine, ivardziuotinis));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(sveikasSkaicius, Gimine.V));
				}
			} else {
				BigInteger vienetai = sveikasSkaicius.mod(BigInteger.TEN);
				sveikasSkaicius = sveikasSkaicius.divide(BigInteger.TEN);
				BigInteger desimtys = sveikasSkaicius.mod(BigInteger.TEN);
				
				vienzenkliai.buildVienzenklis(formaIrSkaiciai.clone().sveikasSkaicius(vienetai));
				if (poskyris == Poskyris.Kelintinis && vienetai.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getKelintinis(desimtys.multiply(BigInteger.TEN), skaicius, gimine, ivardziuotinis));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(desimtys.multiply(BigInteger.TEN), Gimine.V));
				}
			}
		}
		
	}
	
	private class Trizenkliai {

		private void buildTrizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Skaicius skaicius = formaIrSkaiciai.getForma().getSkaicius();
			
			CheckUtil.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, SveikasisSkaicius.THOUSAND);
			BuilderCheckUtil.checkSveikojoSkaiciausPoskyrisNekuopinis(poskyris);
			
			BigInteger dvizenklis = sveikasSkaicius.mod(SveikasisSkaicius.HUNDRED);
			dvizenkliai.buildDvizenklis(formaIrSkaiciai.clone().sveikasSkaicius(dvizenklis));
			
			BigInteger simtai = sveikasSkaicius.divide(SveikasisSkaicius.HUNDRED);
			BigInteger liekana = sveikasSkaicius.mod(SveikasisSkaicius.HUNDRED);
			if (simtai.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getKelintinisIv(SveikasisSkaicius.HUNDRED, skaicius, gimine));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(SveikasisSkaicius.HUNDRED, Gimine.V));
				}
				
			} else if (simtai.compareTo(BigInteger.ONE) > 0) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getKelintinisIv(SveikasisSkaicius.HUNDRED, skaicius, gimine).daugyba());
					zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); // ?
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(SveikasisSkaicius.HUNDRED, Gimine.V).daugyba());
					zodziai.add(ZodzioInfo.getPagrindinis(simtai, Gimine.V)); //?
				}
			}
		}
		
	}

	private class Daugiazenkliai {
		
		private void buildDaugiazenklis(FormaIrSkaiciai formaIrSkaiciai, BigInteger tukstancioLaipsnis) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Skaicius skaicius = formaIrSkaiciai.getForma().getSkaicius();		
		
			BuilderCheckUtil.checkSveikojoSkaiciausPoskyrisNekuopinis(poskyris);
			BuilderCheckUtil.checkPowerOfThousand(tukstancioLaipsnis);
			
			BigInteger sk = sveikasSkaicius;
			BigInteger tukstanciu = sk.divide(tukstancioLaipsnis);
			BigInteger tukstanciuLiekana = sveikasSkaicius.mod(tukstancioLaipsnis);
			if (tukstancioLaipsnis.compareTo(SveikasisSkaicius.THOUSAND) > 0) {
				buildDaugiazenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciuLiekana), tukstancioLaipsnis.divide(SveikasisSkaicius.THOUSAND));
			} else {
				trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciuLiekana));
			}
			
			if (tukstanciu.equals(BigInteger.ZERO)) {
				// nieko
			} else if (tukstanciu.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V));
				}			
			} else if (tukstanciu.compareTo(BigInteger.ONE) > 0 && tukstanciu.compareTo(SveikasisSkaicius.THOUSAND) < 0) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					zodziai.add(ZodzioInfo.getKelintinisIv(tukstancioLaipsnis, skaicius, gimine).daugyba());
					trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				} else {
					zodziai.add(ZodzioInfo.getPagrindinis(tukstancioLaipsnis, Gimine.V).daugyba());
					trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				}			
			} else {
				throw new UnsupportedOperationException(sveikasSkaicius + " is too big");
			}
		}

		private void buildDaugiazenklis(FormaIrSkaiciai forma) {
			BigInteger sveikasSkaicius = forma.getSveikasisSkaicius();
			BigInteger tikrasSkaicius = forma.getPradinisSveikasisSkaicius();
			Poskyris poskyris = forma.getForma().getPoskyris();
			if (!sveikasSkaicius.equals(tikrasSkaicius)) {
				throw new IllegalArgumentException();
			}
			BuilderCheckUtil.checkSveikojoSkaiciausPoskyrisNekuopinis(poskyris);
			daugiazenkliai.buildDaugiazenklis(forma, SveikasisSkaicius.BILLION);
		}
		
	}

	private class Kuopiniai {

		private String buildKuopinis(FormaIrSkaiciai formaIrSkaiciai) {
			if (formaIrSkaiciai.getForma().getPoskyris() != Poskyris.Kuopinis) {
				throw new IllegalArgumentException("forma.poskyris has invalid value " + formaIrSkaiciai.getForma().getPoskyris());
			}
			BigInteger skaicius = formaIrSkaiciai.getSveikasisSkaicius();
			ZodzioInfo z = ZodzioInfo.getKuopinis(skaicius);
			if (z == null) {
				throw new IllegalArgumentException(skaicius + " is invalid value");
			}
			zodziai.add(z);		
			return ZodzioInfo.toString(zodziai, formaIrSkaiciai);
			
		}
		
	}
	
	


}
