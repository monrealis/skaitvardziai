package eu.vytenis.skaitvardziai.builder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.util.Numbers;
import eu.vytenis.skaitvardziai.zodziai.Zodis;
import eu.vytenis.skaitvardziai.zodziai.ZodisJunginyje;
import eu.vytenis.skaitvardziai.zodziai.Zodziai;

public class SveikasisBuilder {
	public static final List<Poskyris> SVEIKUJU_SKAICIU_NEKUOPINIU_SKAITV_POSKYRIAI = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis,
			Poskyris.Kelintinis);
	public static final List<Poskyris> SVEIKUJU_SKAICIU_SKAITV_POSKYRIAI = Arrays.asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis,
			Poskyris.Kuopinis);
	private List<ZodisJunginyje> zodziai = new ArrayList<ZodisJunginyje>();
	private Vienzenkliai vienzenkliai = new Vienzenkliai();
	private Dvizenkliai dvizenkliai = new Dvizenkliai();
	private Trizenkliai trizenkliai = new Trizenkliai();
	private Daugiazenkliai daugiazenkliai = new Daugiazenkliai();
	private Kuopiniai kuopiniai = new Kuopiniai();

	public void build(FormaIrSkaiciai forma) {
		Poskyris poskyris = forma.getForma().getPoskyris();
		if (poskyris == Poskyris.Kuopinis) {
			kuopiniai.buildKuopinis(forma);
		} else {
			daugiazenkliai.buildDaugiazenklis(forma);
		}
	}

	public List<ZodisJunginyje> getZodziai() {
		return zodziai;
	}

	private class Daugiazenkliai {

		private void buildDaugiazenklis(FormaIrSkaiciai forma) {
			Checks.checkEqual("forma.getSveikasisSkaicius", "forma.pradinisSveikasisSkaicius", forma.getSveikasisSkaicius(),
					forma.getPradinisSveikasisSkaicius());
			BuilderChecks.checkPoskyris("forma.poskyris", forma.getForma().getPoskyris(), SVEIKUJU_SKAICIU_NEKUOPINIU_SKAITV_POSKYRIAI);
			daugiazenkliai.buildDaugiazenklis(forma, Numbers.BILLION);
		}

		private void buildDaugiazenklis(FormaIrSkaiciai formaIrSkaiciai, BigInteger tukstancioLaipsnis) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			BuilderChecks.checkPowerOfThousand("tukstancioLaipsnis", tukstancioLaipsnis);
			BigInteger sk = sveikasSkaicius;
			BigInteger tukstanciu = sk.divide(tukstancioLaipsnis);
			BigInteger tukstanciuLiekana = sveikasSkaicius.mod(tukstancioLaipsnis);
			if (tukstancioLaipsnis.compareTo(Numbers.THOUSAND) > 0) {
				buildDaugiazenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciuLiekana), tukstancioLaipsnis.divide(Numbers.THOUSAND));
			} else {
				trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciuLiekana));
			}
			if (tukstanciu.equals(BigInteger.ZERO)) {
				// nieko
			} else if (tukstanciu.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					zodziai.add(getSuma(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
				} else {
					zodziai.add(getSuma(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
				}
			} else if (tukstanciu.compareTo(BigInteger.ONE) > 0 && tukstanciu.compareTo(Numbers.THOUSAND) < 0) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					zodziai.add(getDaugyba(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
					trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				} else {
					zodziai.add(getDaugyba(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
					trizenkliai.buildTrizenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				}
			} else {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					zodziai.add(getDaugyba(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
					buildDaugiazenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis), tukstancioLaipsnis);
				} else {
					zodziai.add(getDaugyba(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
					buildDaugiazenklis(formaIrSkaiciai.clone().sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis), tukstancioLaipsnis);
				}
			}

		}

	}

	private class Trizenkliai {

		private void buildTrizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Checks.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, Numbers.THOUSAND);
			BigInteger dvizenklis = sveikasSkaicius.mod(Numbers.HUNDRED);
			dvizenkliai.buildDvizenklis(formaIrSkaiciai.clone().sveikasSkaicius(dvizenklis));
			BigInteger simtai = sveikasSkaicius.divide(Numbers.HUNDRED);
			BigInteger liekana = sveikasSkaicius.mod(Numbers.HUNDRED);
			if (simtai.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
					zodziai.add(getSuma(Zodziai.getKelintinis(Numbers.HUNDRED, gimine, Rusis.Iv)));
				} else {
					zodziai.add(getSuma(Zodziai.getPagrindinis(Numbers.HUNDRED, Gimine.V)));
				}
			} else if (simtai.compareTo(BigInteger.ONE) > 0) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
					zodziai.add(getDaugyba(Zodziai.getKelintinis(Numbers.HUNDRED, gimine, Rusis.Iv)));
					zodziai.add(getSuma(Zodziai.getPagrindinis(simtai, Gimine.V)));
				} else {
					zodziai.add(getDaugyba(Zodziai.getPagrindinis(Numbers.HUNDRED, Gimine.V)));
					zodziai.add(getSuma(Zodziai.getPagrindinis(simtai, Gimine.V)));
				}
			}
		}

	}

	private class Dvizenkliai {

		private void buildDvizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Rusis rusis = formaIrSkaiciai.getForma().getRusis();
			Checks.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, Numbers.HUNDRED);
			if (sveikasSkaicius.compareTo(BigInteger.TEN) < 0) {
				vienzenkliai.buildVienzenklis(formaIrSkaiciai);
			} else if (sveikasSkaicius.compareTo(Numbers.TWENTY) < 0) {
				if (poskyris == Poskyris.Kelintinis) {
					zodziai.add(getSuma(Zodziai.getKelintinis(sveikasSkaicius, gimine, rusis)));
				} else {
					zodziai.add(getSuma(Zodziai.getPagrindinis(sveikasSkaicius, Gimine.V)));
				}
			} else {
				BigInteger vienetai = sveikasSkaicius.mod(BigInteger.TEN);
				sveikasSkaicius = sveikasSkaicius.divide(BigInteger.TEN);
				BigInteger desimtys = sveikasSkaicius.mod(BigInteger.TEN);
				vienzenkliai.buildVienzenklis(formaIrSkaiciai.clone().sveikasSkaicius(vienetai));
				if (poskyris == Poskyris.Kelintinis && vienetai.equals(BigInteger.ZERO)) {
					zodziai.add(getSuma(Zodziai.getKelintinis(desimtys.multiply(BigInteger.TEN), gimine, rusis)));
				} else {
					zodziai.add(getSuma(Zodziai.getPagrindinis(desimtys.multiply(BigInteger.TEN), Gimine.V)));
				}
			}
		}

	}

	private class Vienzenkliai {

		private void buildVienzenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			BigInteger tikrasSkaicius = formaIrSkaiciai.getPradinisSveikasisSkaicius();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Rusis rusis = formaIrSkaiciai.getForma().getRusis();
			Checks.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, BigInteger.TEN);
			if (sveikasSkaicius.equals(BigInteger.ZERO) && tikrasSkaicius.equals(BigInteger.ZERO) || sveikasSkaicius.compareTo(BigInteger.ZERO) > 0) {
				if (poskyris == Poskyris.Pagrindinis) {
					if (sveikasSkaicius.equals(BigInteger.ZERO)) {
						zodziai.add(getSuma(Zodziai.getPagrindinis(sveikasSkaicius, Gimine.V)));
					} else {
						zodziai.add(getSuma(Zodziai.getPagrindinis(sveikasSkaicius, gimine)));
					}
				} else if (poskyris == Poskyris.Dauginis) {
					zodziai.add(getSuma(Zodziai.getDauginis(sveikasSkaicius, gimine)));
				} else if (poskyris == Poskyris.Kelintinis) {
					zodziai.add(getSuma(Zodziai.getKelintinis(sveikasSkaicius, gimine, (!sveikasSkaicius.equals(BigInteger.ZERO) ? rusis : Rusis.P))));
				}
			}
		}

	}

	private class Kuopiniai {

		private String buildKuopinis(FormaIrSkaiciai formaIrSkaiciai) {
			BuilderChecks.checkPoskyris("formaIrSkaiciai.poskyris", formaIrSkaiciai.getForma().getPoskyris(), Arrays.asList(Poskyris.Kuopinis));
			BigInteger skaicius = formaIrSkaiciai.getSveikasisSkaicius();
			zodziai.add(getSuma(Zodziai.getKuopinis(skaicius)));
			return ZodisJunginyje.toString(zodziai, formaIrSkaiciai);
		}

	}

	private ZodisJunginyje getSuma(Zodis zodis) {
		return new ZodisJunginyje(zodis, false);
	}

	private ZodisJunginyje getDaugyba(Zodis zodis) {
		return new ZodisJunginyje(zodis, true);
	}

}
