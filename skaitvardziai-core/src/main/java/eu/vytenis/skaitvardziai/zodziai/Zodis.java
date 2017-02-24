package eu.vytenis.skaitvardziai.zodziai;

import static java.util.Collections.unmodifiableMap;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;

/**
 * Žodžio formų visais linksniais (vienaskaitoje ir/ar daugiskaitoje) rinkinys.
 *
 */
public class Zodis {
	/** Vienaskaitos ir daugiskaitos žodžiai pagal linksnius. */
	private final Map<SkaiciusIrLinksnis, String> formos = new TreeMap<SkaiciusIrLinksnis, String>();

	/**
	 * Skaitvardyje iš kelių žodžių - koks po šio žodžio einančio kito žodžio skaičius ir linksnis. (pvz., dešimt _tūkstančių_ (dgs. kilm.), vienas tūkstantis
	 * (vns. vard.)). Jei linksnis nėra visada vienodas, o priklauso nuo konteksto, linksnis yra null (pvz., keturi _šimtai_, keturis _šimtus_ - aišku, kad
	 * antras žodis daugiskaita, bet linksnis gali būti bet koks).
	 */
	private final SkaiciusIrLinksnis kitas;
	private final KitasSkaiciusIrLinksnis kitasSkaiciusIrLinksnis;

	/** Skaitvardyje iš kelių žodžių - ar šio žodžio forma priklauso nuo ankstesnio žodžio (pvz., vienas _šimtas_, du _šimtai_, dešimt _tūkstančių_). */
	private final Valdomas valdomas;

	/**
	 * Požymis, ar žodis nekaitomas linksniuojant, išskyrus tą atvejį, kai jis yra skaičiaus iš kelių žodžių paskutinė dalis.
	 * 
	 * Pvz.: du šimtai dvidešimt du, dviejų šimtų dvidešimt dviejų. Bet.: du šimta dvidešimt, dviejų šimtų dvidešimties.
	 * 
	 */
	private final Kaitomas kaitomasLinksniuojant;

	// Lentelės [skaičius -> nedalomas skaitvardis (iš vieno žodžio)].
	public Zodis(Skaicius skaicius, String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS) {
		Checks.checkNotNull("skaicius", skaicius);
		Checks.checkNotNull("vnsV", vnsV);
		Checks.checkNotNull("vnsK", vnsK);
		Checks.checkNotNull("vnsN", vnsN);
		Checks.checkNotNull("vnsG", vnsG);
		Checks.checkNotNull("vnsI", vnsI);
		Checks.checkNotNull("vnsVt", vnsVt);
		Checks.checkNotNull("vnsS", vnsS);

		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.V), vnsV);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.K), vnsK);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.N), vnsN);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.G), vnsG);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.I), vnsI);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.Vt), vnsVt);
		formos.put(new SkaiciusIrLinksnis(skaicius, Linksnis.S), vnsS);

		kitas = new SkaiciusIrLinksnis(null, null);
		kitasSkaiciusIrLinksnis = KitasSkaiciusIrLinksnis.Null;
		valdomas = Valdomas.Nevaldomas;
		kaitomasLinksniuojant = Kaitomas.Kaitomas;
	}

	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS) {
		this(Skaicius.V, vnsV, vnsK, vnsN, vnsG, vnsI, vnsVt, vnsS);
	}

	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, //
			String vnsS, String dgsV, String dgsK, String dgsN, String dgsG, String dgsI, String dgsVt, String dgsS) {
		Checks.checkNotNull("vnsV", vnsV);
		Checks.checkNotNull("vnsK", vnsK);
		Checks.checkNotNull("vnsN", vnsN);
		Checks.checkNotNull("vnsG", vnsG);
		Checks.checkNotNull("vnsI", vnsI);
		Checks.checkNotNull("vnsVt", vnsVt);
		Checks.checkNotNull("vnsS", vnsS);

		Checks.checkNotNull("dgsV", dgsV);
		Checks.checkNotNull("dgsK", dgsK);
		Checks.checkNotNull("dgsN", dgsN);
		Checks.checkNotNull("dgsG", dgsG);
		Checks.checkNotNull("dgsI", dgsI);
		Checks.checkNotNull("dgsVt", dgsVt);
		Checks.checkNotNull("dgsS", dgsS);

		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V), vnsV);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.K), vnsK);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.N), vnsN);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.G), vnsG);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.I), vnsI);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.Vt), vnsVt);
		formos.put(new SkaiciusIrLinksnis(Skaicius.V, Linksnis.S), vnsS);

		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.V), dgsV);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K), dgsK);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.N), dgsN);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.G), dgsG);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.I), dgsI);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.Vt), dgsVt);
		formos.put(new SkaiciusIrLinksnis(Skaicius.D, Linksnis.S), dgsS);

		kitas = new SkaiciusIrLinksnis(null, null);
		kitasSkaiciusIrLinksnis = KitasSkaiciusIrLinksnis.Null;
		valdomas = Valdomas.Nevaldomas;
		kaitomasLinksniuojant = Kaitomas.Kaitomas;
	}

	private Zodis(Zodis zodis, Kaitomas kaitomasLinksniuojant) {
		this.formos.putAll(zodis.formos);
		this.kitas = zodis.kitas;
		this.kitasSkaiciusIrLinksnis = zodis.kitasSkaiciusIrLinksnis;
		this.valdomas = zodis.valdomas;
		this.kaitomasLinksniuojant = kaitomasLinksniuojant;
	}

	private Zodis(Zodis zodis, SkaiciusIrLinksnis kitas, KitasSkaiciusIrLinksnis kitasSkaiciusIrLinksnis) {
		this.formos.putAll(zodis.formos);
		this.kitas = kitas;
		this.kitasSkaiciusIrLinksnis = kitasSkaiciusIrLinksnis;
		this.valdomas = zodis.valdomas;
		this.kaitomasLinksniuojant = zodis.kaitomasLinksniuojant;
	}

	private Zodis(Zodis zodis, Valdomas valdomas) {
		this.formos.putAll(zodis.formos);
		this.kitas = zodis.kitas;
		this.kitasSkaiciusIrLinksnis = zodis.kitasSkaiciusIrLinksnis;
		this.valdomas = valdomas;
		this.kaitomasLinksniuojant = zodis.kaitomasLinksniuojant;
	}

	public SkaiciusIrLinksnis getKitas() {
		return kitas;
	}

	public KitasSkaiciusIrLinksnis getKitasSkaiciusIrLinksnis() {
		return kitasSkaiciusIrLinksnis;
	}

	public Zodis kitasDgs() {
		return kitas(Skaicius.D, null, KitasSkaiciusIrLinksnis.Dgs);
	}

	public Zodis kitasDgsKilm() {
		return kitas(Skaicius.D, Linksnis.K, KitasSkaiciusIrLinksnis.DgsKilm);
	}

	private Zodis kitas(Skaicius kitasSkaicius, Linksnis kitasLinksnis, KitasSkaiciusIrLinksnis kitasSkaiciusIrLinksnis) {
		SkaiciusIrLinksnis kitas = new SkaiciusIrLinksnis(kitasSkaicius, kitasLinksnis);
		return new Zodis(this, kitas, kitasSkaiciusIrLinksnis);
	}

	public boolean isValdomas() {
		return valdomas == Valdomas.Valdomas;
	}

	public Zodis valdomas() {
		return new Zodis(this, Valdomas.Valdomas);
	}

	public boolean isNekaitomasLinksniuojant() {
		return kaitomasLinksniuojant == Kaitomas.Nekaitomas;
	}

	public Zodis nekaitomasLinksniuojant() {
		return new Zodis(this, Kaitomas.Nekaitomas);
	}

	public Map<SkaiciusIrLinksnis, String> getVisosFormos() {
		return unmodifiableMap(formos);
	}

	public String toString(SkaiciusIrLinksnis skaiciusIrLinksnis) {
		return formos.get(skaiciusIrLinksnis);
	}

	@Override
	public String toString() {
		SkaiciusIrLinksnis sl = new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V);
		String r = toString(sl);
		if (r == null) {
			// Jei žodis neturi vienaskaitos vardininko, grąžiname daugiskaitos vardininką (pvz., marškiniai)
			return toString(sl.withSkaicius(Skaicius.D));
		}
		return r;
	}

	public static class WordNotFoundException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = -3871360931584928662L;

		public WordNotFoundException(Poskyris poskyris, BigInteger skaicius) {
			super(poskyris + " word not found for number " + skaicius);
		}
	}

	public enum Valdomas {
		Nevaldomas, Valdomas
	}

	public enum Kaitomas {
		Nekaitomas, Kaitomas
	}

	// TODO panaudoti vietoj SkaiciusIrLinksnis
	public enum KitasSkaiciusIrLinksnis {
		Null {
			@Override
			public SkaiciusIrLinksnis nvl(SkaiciusIrLinksnis zodzioSkaiciusIrLinksnis) {
				return zodzioSkaiciusIrLinksnis;
			}
		},
		Dgs {
			@Override
			public SkaiciusIrLinksnis nvl(SkaiciusIrLinksnis zodzioSkaiciusIrLinksnis) {
				return new SkaiciusIrLinksnis(Skaicius.D, zodzioSkaiciusIrLinksnis.getLinksnis());
			}
		},
		DgsKilm {
			@Override
			public SkaiciusIrLinksnis nvl(SkaiciusIrLinksnis zodzioSkaiciusIrLinksnis) {
				return new SkaiciusIrLinksnis(Skaicius.D, Linksnis.K);
			}
		};

		// Pavadinti kaip nors
		public abstract SkaiciusIrLinksnis nvl(SkaiciusIrLinksnis zodzioSkaiciusIrLinksnis);

	}
}
