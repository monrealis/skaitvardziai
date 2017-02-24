package eu.vytenis.skaitvardziai.zodziai;

import static java.util.Collections.unmodifiableMap;

import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;
import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.log.Log;
import eu.vytenis.skaitvardziai.log.LogFactory;

/**
 * Žodžio formų visais linksniais (vienaskaitoje ir/ar daugiskaitoje) rinkinys.
 *
 */
// TODO
// @formatter:off
public class Zodis implements UnmodifiableCapable {
	/** Žurnalas. */
	static final Log log = LogFactory.getLog(Zodis.class);
	
	/** Vienaskaitos ir daugiskaitos žodžiai pagal linksnius. */
	private Map<SkaiciusIrLinksnis, String> formos = new TreeMap<SkaiciusIrLinksnis, String>();
	
	/** Ar žodis neredaguojamas? */
	private boolean unmodifiable;
	
	
	/**
	 * Skaitvardyje iš kelių žodžių - koks po šio žodžio einančio kito žodžio skaičius ir linksnis. (pvz., dešimt _tūkstančių_ (dgs. kilm.), vienas tūkstantis (vns. vard.)).
	 * Jei linksnis nėra visada vienodas, o priklauso nuo konteksto, linksnis yra null
	 * (pvz., keturi _šimtai_, keturis _šimtus_ - aišku, kad antras žodis daugiskaita, bet linksnis gali būti bet koks).
	 */
	private SkaiciusIrLinksnis kitas = new SkaiciusIrLinksnis(null, null);
	
	/** Skaitvardyje iš kelių žodžių - ar šio žodžio forma priklauso nuo ankstesnio žodžio (pvz., vienas _šimtas_, du _šimtai_, dešimt _tūkstančių_). */
	private boolean valdomas = false;
	
	/**
	 * Požymis, ar žodis nekaitomas linksniuojant, išskyrus tą atvejį,
	 * kai jis yra skaičiaus iš kelių žodžių paskutinė dalis.
	 * 
	 * Pvz.: du šimtai dvidešimt du, dviejų šimtų dvidešimt dviejų.
	 * Bet.: du šimta dvidešimt, dviejų šimtų dvidešimties.
	 * 
	 */
	private boolean nekaitomasLinksniuojant;
	
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
	}
	
	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS) {
		this(Skaicius.V, vnsV, vnsK, vnsN, vnsG, vnsI, vnsVt, vnsS);
	}
	
	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS,
			String dgsV, String dgsK, String dgsN, String dgsG, String dgsI, String dgsVt, String dgsS) {
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
	}
	
	
	public SkaiciusIrLinksnis getKitas() {
		return kitas;
	}
	
	public Zodis kitasDgs() {
		return kitas(Skaicius.D, null);
	}
	
	public Zodis kitasDgsKilm() {
		return kitas(Skaicius.D, Linksnis.K);
	}
	
	private Zodis kitas(Skaicius kitasSkaicius, Linksnis kitasLinksnis) {
		Checks.checkCanModify("Zodis", this);
		kitas = new SkaiciusIrLinksnis(kitasSkaicius, kitasLinksnis);
		return this;
	}
	
	public boolean isValdomas() {
		return valdomas;
	}

	
	public Zodis valdomas() {
		Checks.checkCanModify("Zodis", this);
		valdomas = true;
		return this;
	}
	
	public boolean isNekaitomasLinksniuojant() {
		return nekaitomasLinksniuojant;
	}
	
	public void setNekaitomasLinksniuojant(boolean nekaitomasLinksniuojant) {
		Checks.checkCanModify("Zodis", this);
		this.nekaitomasLinksniuojant = nekaitomasLinksniuojant;
	}
	
	public Zodis nekaitomasLinksniuojant() {
		setNekaitomasLinksniuojant(true);
		return this;
	}
	
	/** Žiūrėti: {@link UnmodifiableCapable#isUnmodifiable()}. */
	public boolean isUnmodifiable() {
		return unmodifiable;
	}
	
	/** Žiūrėti: {@link UnmodifiableCapable#setUnmodifiable(boolean)}. */
	public void setUnmodifiable(boolean unmodifiable) {
		Checks.checkCanModify("Zodis", this);
		this.unmodifiable = unmodifiable;
	}
	
	/**
	 * Grąžina lentelę [skaičius ir linksnis (pvz., vns vard.) -> skaitvardis].
	 * @return lentelė
	 */
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
	
}
