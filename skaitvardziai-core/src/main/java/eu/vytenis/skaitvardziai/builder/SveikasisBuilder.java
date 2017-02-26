package eu.vytenis.skaitvardziai.builder;

import static java.util.Arrays.asList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.util.Numbers;
import eu.vytenis.skaitvardziai.zodziai.Zodis;
import eu.vytenis.skaitvardziai.zodziai.ZodisJunginyje;
import eu.vytenis.skaitvardziai.zodziai.ZodisJunginyje.Indelis;
import eu.vytenis.skaitvardziai.zodziai.Zodziai;

public class SveikasisBuilder {
	public static final List<Poskyris> SVEIKUJU_NEKUOPINIU_POSKYRIAI = asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis);
	public static final List<Poskyris> SVEIKUJU_POSKYRIAI = asList(Poskyris.Pagrindinis, Poskyris.Dauginis, Poskyris.Kelintinis, Poskyris.Kuopinis);
	private List<ZodisJunginyje> zodziai = new ArrayList<ZodisJunginyje>();
	private Vienzenkliai vienzenkliai = new Vienzenkliai(zodziai);
	private Dvizenkliai dvizenkliai = new Dvizenkliai(zodziai);
	private Trizenkliai trizenkliai = new Trizenkliai(zodziai);
	private Daugiazenkliai daugiazenkliai = new Daugiazenkliai(zodziai);
	private Kuopiniai kuopiniai = new Kuopiniai(zodziai);

	public void build(FormaIrSkaiciai forma) {
		Poskyris poskyris = forma.getForma().getPoskyris();
		if (poskyris == Poskyris.Kuopinis)
			kuopiniai.buildKuopinis(forma);
		else
			daugiazenkliai.buildDaugiazenklis(forma);
	}

	public List<ZodisJunginyje> getZodziai() {
		return zodziai;
	}

	private static class Builder {
		private final List<ZodisJunginyje> zodziai;

		protected Builder(List<ZodisJunginyje> zodziai) {
			this.zodziai = zodziai;
		}

		protected void add(ZodisJunginyje zodisJunginyje) {
			zodziai.add(zodisJunginyje);
		}

		protected ZodisJunginyje suma(Zodis zodis) {
			return new ZodisJunginyje(zodis, Indelis.Suma);
		}

		protected ZodisJunginyje daugyba(Zodis zodis) {
			return new ZodisJunginyje(zodis, Indelis.Daugyba);
		}
	}

	private class Daugiazenkliai extends Builder {
		public Daugiazenkliai(List<ZodisJunginyje> zodziai) {
			super(zodziai);
		}

		private void buildDaugiazenklis(FormaIrSkaiciai forma) {
			Checks.checkEqual("forma.getSveikasisSkaicius", "forma.pradinisSveikasisSkaicius", forma.getSveikasisSkaicius(),
					forma.getPradinisSveikasisSkaicius());
			BuilderChecks.checkPoskyris("forma.poskyris", forma.getForma().getPoskyris(), SVEIKUJU_NEKUOPINIU_POSKYRIAI);
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
			if (tukstancioLaipsnis.compareTo(Numbers.THOUSAND) > 0)
				buildDaugiazenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciuLiekana), tukstancioLaipsnis.divide(Numbers.THOUSAND));
			else
				trizenkliai.buildTrizenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciuLiekana));
			if (tukstanciu.equals(BigInteger.ZERO)) {
				// nieko
			} else if (tukstanciu.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO))
					add(suma(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
				else
					add(suma(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
			} else if (tukstanciu.compareTo(BigInteger.ONE) > 0 && tukstanciu.compareTo(Numbers.THOUSAND) < 0) {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					add(daugyba(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
					trizenkliai.buildTrizenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				} else {
					add(daugyba(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
					trizenkliai.buildTrizenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis).gimine(Gimine.V));
				}
			} else {
				if (poskyris == Poskyris.Kelintinis && tukstanciuLiekana.equals(BigInteger.ZERO)) {
					add(daugyba(Zodziai.getKelintinis(tukstancioLaipsnis, gimine, Rusis.Iv)));
					buildDaugiazenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis), tukstancioLaipsnis);
				} else {
					add(daugyba(Zodziai.getPagrindinis(tukstancioLaipsnis, Gimine.V)));
					buildDaugiazenklis(formaIrSkaiciai.sveikasSkaicius(tukstanciu).poskyris(Poskyris.Pagrindinis), tukstancioLaipsnis);
				}
			}
		}
	}

	private class Trizenkliai extends Builder {
		public Trizenkliai(List<ZodisJunginyje> zodziai) {
			super(zodziai);
		}

		private void buildTrizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Checks.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, Numbers.THOUSAND);
			BigInteger dvizenklis = sveikasSkaicius.mod(Numbers.HUNDRED);
			dvizenkliai.buildDvizenklis(formaIrSkaiciai.sveikasSkaicius(dvizenklis));
			BigInteger simtai = sveikasSkaicius.divide(Numbers.HUNDRED);
			BigInteger liekana = sveikasSkaicius.mod(Numbers.HUNDRED);
			if (simtai.equals(BigInteger.ONE)) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO))
					add(suma(Zodziai.getKelintinis(Numbers.HUNDRED, gimine, Rusis.Iv)));
				else
					add(suma(Zodziai.getPagrindinis(Numbers.HUNDRED, Gimine.V)));
			} else if (simtai.compareTo(BigInteger.ONE) > 0) {
				if (poskyris == Poskyris.Kelintinis && liekana.equals(BigInteger.ZERO)) {
					add(daugyba(Zodziai.getKelintinis(Numbers.HUNDRED, gimine, Rusis.Iv)));
					add(suma(Zodziai.getPagrindinis(simtai, Gimine.V)));
				} else {
					add(daugyba(Zodziai.getPagrindinis(Numbers.HUNDRED, Gimine.V)));
					add(suma(Zodziai.getPagrindinis(simtai, Gimine.V)));
				}
			}
		}
	}

	private class Dvizenkliai extends Builder {
		public Dvizenkliai(List<ZodisJunginyje> zodziai) {
			super(zodziai);
		}

		private void buildDvizenklis(FormaIrSkaiciai formaIrSkaiciai) {
			BigInteger sveikasSkaicius = formaIrSkaiciai.getSveikasisSkaicius();
			Poskyris poskyris = formaIrSkaiciai.getForma().getPoskyris();
			Gimine gimine = formaIrSkaiciai.getForma().getGimine();
			Rusis rusis = formaIrSkaiciai.getForma().getRusis();
			Checks.checkMinInclusive("forma.sveikasSkaicius", sveikasSkaicius, BigInteger.ZERO, Numbers.HUNDRED);
			if (sveikasSkaicius.compareTo(BigInteger.TEN) < 0) {
				vienzenkliai.buildVienzenklis(formaIrSkaiciai);
			} else if (sveikasSkaicius.compareTo(Numbers.TWENTY) < 0) {
				if (poskyris == Poskyris.Kelintinis)
					add(suma(Zodziai.getKelintinis(sveikasSkaicius, gimine, rusis)));
				else
					add(suma(Zodziai.getPagrindinis(sveikasSkaicius, Gimine.V)));
			} else {
				BigInteger vienetai = sveikasSkaicius.mod(BigInteger.TEN);
				sveikasSkaicius = sveikasSkaicius.divide(BigInteger.TEN);
				BigInteger desimtys = sveikasSkaicius.mod(BigInteger.TEN);
				vienzenkliai.buildVienzenklis(formaIrSkaiciai.sveikasSkaicius(vienetai));
				if (poskyris == Poskyris.Kelintinis && vienetai.equals(BigInteger.ZERO)) {
					add(suma(Zodziai.getKelintinis(desimtys.multiply(BigInteger.TEN), gimine, rusis)));
				} else {
					add(suma(Zodziai.getPagrindinis(desimtys.multiply(BigInteger.TEN), Gimine.V)));
				}
			}
		}

	}

	private class Vienzenkliai extends Builder {
		public Vienzenkliai(List<ZodisJunginyje> zodziai) {
			super(zodziai);
		}

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
						add(suma(Zodziai.getPagrindinis(sveikasSkaicius, Gimine.V)));
					} else {
						add(suma(Zodziai.getPagrindinis(sveikasSkaicius, gimine)));
					}
				} else if (poskyris == Poskyris.Dauginis) {
					add(suma(Zodziai.getDauginis(sveikasSkaicius, gimine)));
				} else if (poskyris == Poskyris.Kelintinis) {
					add(suma(Zodziai.getKelintinis(sveikasSkaicius, gimine, (!sveikasSkaicius.equals(BigInteger.ZERO) ? rusis : Rusis.P))));
				}
			}
		}
	}

	private class Kuopiniai extends Builder {
		public Kuopiniai(List<ZodisJunginyje> zodziai) {
			super(zodziai);
		}

		private String buildKuopinis(FormaIrSkaiciai formaIrSkaiciai) {
			BuilderChecks.checkPoskyris("formaIrSkaiciai.poskyris", formaIrSkaiciai.getForma().getPoskyris(), asList(Poskyris.Kuopinis));
			BigInteger skaicius = formaIrSkaiciai.getSveikasisSkaicius();
			add(suma(Zodziai.getKuopinis(skaicius)));
			return ZodisJunginyje.toString(zodziai, formaIrSkaiciai);
		}
	}
}
