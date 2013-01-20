package eu.vytenis.skaitvardziai.zodziai;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.util.BigIntegerToLongBridgeMap;


/**
 * Žodžio formų visais linksniais (vienaskaitoje ir/ar daugiskaitoje) rinkinys.
 *
 */
public class Zodis {
	/** Žurnalas. */
	private static final Logger logger = Logger.getLogger(Zodis.class.getName());
	
	/** Vienaskaitos ir daugiskaitos žodžiai pagal linksnius. */
	private Map<Skaicius, Map<Linksnis, String>> linksniaiPagalSkaicius = new HashMap<Skaicius, Map<Linksnis,String>>();
	
	
	/**
	 * Skaitvardyje iš kelių žodžių - koks po šio žodžio einančio kito žodžio skaičius ir linksnis. (pvz., dešimt _tūkstančių_ (dgs. kilm.), vienas tūkstantis (vns. vard.)).
	 * Jei linksnis nėra visada vienodas, o priklauso nuo konteksto, linksnis yra null
	 * (pvz., keturi _šimtai_, keturis _šimtus_ - aišku, kad antras žodis daugiskaita, bet linksnis gali būti bet koks).
	 */
	private SkaiciusIrLinksnis kitas = new SkaiciusIrLinksnis(Skaicius.V, null);
	
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
	
	/** Pagrindiniai skaitvardžiai (moteriškos giminės). */
	private static final Map<Long, Zodis> pagrindiniaiMotGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	/** Pagrindiniai skaitvardžiai (vyriškos giminės). */
	private static final Map<Long, Zodis> pagrindiniaiVyrGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	
	/** Kuopiniai skaitvardžiai.*/
	private static final Map<Long, Zodis> kuopiniai = new BigIntegerToLongBridgeMap<Long, Zodis>();
	
	/** Dauginiai skaitvardžiai (moteriškos giminės). */
	private static final Map<Long, Zodis> dauginiaiMotGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	/** Dauginiai skaitvardžiai (vyriškos giminės). */
	private static final Map<Long, Zodis> dauginiaiVyrGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	
	/** Neįvardžiuotiniai kelintiniai skaitvardžiai (vyršikos giminės). */
	private static final Map<Long, Zodis> kelintiniaiVyrGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	/** Įvardžiuotiniai kelintiniai skaitvardžiai (vyršikos giminės). */
	private static final Map<Long, Zodis> kelintiniaiIvVyrGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	/** Neįvardžiuotiniai kelintiniai skaitvardžiai (moteriškos giminės). */
	private static final Map<Long, Zodis> kelintiniaiMotGim = new BigIntegerToLongBridgeMap<Long, Zodis>();
	/** Įvardžiuotiniai kelintiniai skaitvardžiai (moteriškos giminės). */
	private static final Map<Long, Zodis> kelintiniaiIvMotGim = new BigIntegerToLongBridgeMap<Long, Zodis>();


	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS) {
		Map<Linksnis, String> vns = getLinksniaiPagalSkaicius(Skaicius.V, true);
		
		vns.put(Linksnis.V, vnsV);
		vns.put(Linksnis.K, vnsK);
		vns.put(Linksnis.N, vnsN);
		vns.put(Linksnis.G, vnsG);
		vns.put(Linksnis.I, vnsI);
		vns.put(Linksnis.Vt, vnsVt);
		vns.put(Linksnis.S, vnsS);
	}
	
	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS,
			String dgsV, String dgsK, String dgsN, String dgsG, String dgsI, String dgsVt, String dgsS) {
		Map<Linksnis, String> vns = getLinksniaiPagalSkaicius(Skaicius.V, true);
		Map<Linksnis, String> dgs = getLinksniaiPagalSkaicius(Skaicius.D, true);
		
		vns.put(Linksnis.V, vnsV);
		vns.put(Linksnis.K, vnsK);
		vns.put(Linksnis.N, vnsN);
		vns.put(Linksnis.G, vnsG);
		vns.put(Linksnis.I, vnsI);
		vns.put(Linksnis.Vt, vnsVt);
		vns.put(Linksnis.S, vnsS);
		
		dgs.put(Linksnis.V, dgsV);
		dgs.put(Linksnis.K, dgsK);
		dgs.put(Linksnis.N, dgsN);
		dgs.put(Linksnis.G, dgsG);
		dgs.put(Linksnis.I, dgsI);
		dgs.put(Linksnis.Vt, dgsVt);
		dgs.put(Linksnis.S, dgsS);
	}

	/**
	 * Grąžina lentelę [linksnis -> žodis].
	 * @param skaicius vienaskaita/daugiskaita
	 * @param sukurtiJeiReikia jei lentelės nėra, sukuria tuščią ir tada grąžina
	 * @return lentelė [linksnis -> žodis]
	 */
	private Map<Linksnis, String> getLinksniaiPagalSkaicius(Skaicius skaicius, boolean sukurtiJeiReikia) {
		Map<Linksnis, String> r = linksniaiPagalSkaicius.get(skaicius);
		if (r == null && sukurtiJeiReikia) {
			r = new HashMap<Linksnis, String>();
			linksniaiPagalSkaicius.put(skaicius, r);
		}
		return r;
	}
	
	
	public SkaiciusIrLinksnis getKitas() {
		return kitas;
	}
	
	private Zodis kitas(Skaicius kitasSkaicius, Linksnis kitasLinksnis) {
		kitas = new SkaiciusIrLinksnis(kitasSkaicius, kitasLinksnis);
		return this;
	}
	
	public Zodis kitasDgs() {
		return kitas(Skaicius.D, null);
	}
	public Zodis kitasDgsKilm() {
		return kitas(Skaicius.D, Linksnis.K);
	}
	
	public boolean isValdomas() {
		return valdomas;
	}

	
	public Zodis valdomas() {
		valdomas = true;
		return this;
	}
	
	public boolean isNekaitomasLinksniuojant() {
		return nekaitomasLinksniuojant;
	}
	
	public void setNekaitomasLinksniuojant(boolean nekaitomasLinksniuojant) {
		this.nekaitomasLinksniuojant = nekaitomasLinksniuojant;
	}
	
	public Zodis nekaitomasLinksniuojant() {
		setNekaitomasLinksniuojant(true);
		return this;
	}
	

	
	public String toString(SkaiciusIrLinksnis skaiciusIrLinksnis) {
		Map<Linksnis, String> z = getLinksniaiPagalSkaicius(skaiciusIrLinksnis.getSkaicius(), false);
		return z != null ? z.get(skaiciusIrLinksnis.getLinksnis()) : null;
	}
	
	public String toString() {
		return toString(new SkaiciusIrLinksnis());
	}



	static {
		pagrindiniaiMotGim.put(1L, new Zodis("viena", "vienos", "vienai", "vieną", "viena", "vienoje", "viena", "vienos", "vienų", "vienoms", "vienas", "vienomis", "vienose", "vienos")); // "vieni" - daugiau būdvardis, žr. http://ualgiman.dtiltas.lt/skaitvardis.html
		pagrindiniaiMotGim.put(2L, new Zodis("dvi", "dviejų", "dviem" /* geriau nei "dviems" */, "dvi", "dviem", "dviejose", "dvi").kitasDgs());
		pagrindiniaiMotGim.put(3L, new Zodis("trys", "trijų", "trims", "tris", "trimis", "trijose", "trys").kitasDgs());
		pagrindiniaiMotGim.put(4L, new Zodis("keturios", "keturių", "keturioms", "keturias", "keturiomis", "keturiose", "keturios").kitasDgs());
		pagrindiniaiMotGim.put(5L, new Zodis("penkios", "penkių", "penkioms", "penkias", "penkiomis", "penkiose", "penkios").kitasDgs());
		pagrindiniaiMotGim.put(6L, new Zodis("šešios", "šešių", "šešioms", "šešias", "šešiomis", "šešiose", "šešios").kitasDgs());
		pagrindiniaiMotGim.put(7L, new Zodis("septynios", "septynių", "septynioms", "septynias", "septyniomis", "septyniose", "septynios").kitasDgs());
		pagrindiniaiMotGim.put(8L, new Zodis("aštuonios", "aštuonių", "aštuonioms", "aštuonias", "aštuoniomis", "aštuoniose", "aštuonios").kitasDgs());
		pagrindiniaiMotGim.put(9L, new Zodis("devynios", "devynių", "devynioms", "devynias", "devyniomis", "devyniose", "devynios").kitasDgs());
		
		pagrindiniaiVyrGim.put(0L, new Zodis("nulis", "nulio", "nuliui", "nulį", "nuliu", "nulyje", "nuli").kitasDgsKilm());		
		pagrindiniaiVyrGim.put(1L, new Zodis("vienas", "vieno", "vienam", "vieną", "vienu", "viename", "vienas", "vieni", "vienų", "vieniems", "vienus", "vienais", "vienuose", "vieni")); // "vieni" - daugiau būdvardis, žr. http://ualgiman.dtiltas.lt/skaitvardis.html		
		pagrindiniaiVyrGim.put(2L, new Zodis("du", "dviejų", "dviem" /* geriau nei "dviems" */, "du", "dviem", "dviejuose", "du").kitasDgs());		
		pagrindiniaiVyrGim.put(3L, new Zodis("trys", "trijų", "trims", "tris", "trimis", "trijuose", "trys").kitasDgs());		
		pagrindiniaiVyrGim.put(4L, new Zodis("keturi", "keturių", "keturiems", "keturis", "keturiais", "keturiuose", "keturi").kitasDgs());		
		pagrindiniaiVyrGim.put(5L, new Zodis("penki", "penkių", "penkiems", "penkis", "penkiais", "penkiuose", "penki").kitasDgs());		
		pagrindiniaiVyrGim.put(6L, new Zodis("šeši", "šešių", "šešiems", "šešis", "šešiais", "šešiuose", "šeši").kitasDgs());		
		pagrindiniaiVyrGim.put(7L, new Zodis("septyni", "septynių", "septyniems", "septynis", "septyniais", "septyniuose", "septyni").kitasDgs());		
		pagrindiniaiVyrGim.put(8L, new Zodis("aštuoni", "aštuonių", "aštuoniems", "aštuonis", "aštuoniais", "aštuoniuose", "aštuoni").kitasDgs());		
		pagrindiniaiVyrGim.put(9L, new Zodis("devyni", "devynių", "devyniems", "devynis", "devyniais", "devyniuose", "devyni").kitasDgs());
		pagrindiniaiVyrGim.put(10L, new Zodis("dešimt", "dešimties", "dešimčiai", "dešimt", "dešimčia", "dešimtyje", "dešimt").kitasDgsKilm());	// http://ualgiman.dtiltas.lt/skaitvardis.html
		pagrindiniaiVyrGim.put(11L, new Zodis("vienuolika", "vienuolikos", "vienuolikai", "vienuolika", "vienuolika", "vienuolikoje", "vienuolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(12L, new Zodis("dvylika", "dvylikos", "dvylikai", "dvylika", "dvylika", "dvylikoje", "dvylika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(13L, new Zodis("trylika", "trylikos", "trylikai", "trylika", "trylika", "trylikoje", "trylika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(14L, new Zodis("keturiolika", "keturiolikos", "keturiolikai", "keturiolika", "keturiolika", "keturiolikoje", "keturiolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(15L, new Zodis("penkiolika", "penkiolikos", "penkiolikai", "penkiolika", "penkiolika", "penkiolikoje", "penkiolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(16L, new Zodis("šešiolika", "šešiolikos", "šešiolikai", "šešiolika", "šešiolika", "šešiolikoje", "šešiolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(17L, new Zodis("septyniolika", "septyniolikos", "septyniolikai", "septyniolika", "septyniolika", "septyniolikoje", "septyniolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(18L, new Zodis("aštuoniolika", "aštuoniolikos", "aštuoniolikai", "aštuoniolika", "aštuoniolika", "aštuoniolikoje", "aštuoniolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(19L, new Zodis("devyniolika", "devyniolikos", "devyniolikai", "devyniolika", "devyniolika", "devyniolikoje", "devyniolika").kitasDgsKilm());
		pagrindiniaiVyrGim.put(20L, new Zodis("dvidešimt", "dvidešimties", "dvidešimčiai", "dvidešimt", "dvidešimčia", "dvidešimtyje", "dvidešimt").kitasDgsKilm().nekaitomasLinksniuojant());		
		pagrindiniaiVyrGim.put(30L, new Zodis("trisdešimt", "trisdešimties", "trisdešimčiai", "trisdešimt", "trisdešimčia", "trisdešimtyje", "trisdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(40L, new Zodis("keturiasdešimt", "keturiasdešimties", "keturiasdešimčiai", "keturiasdešimt", "keturiasdešimčia", "keturiasdešimtyje", "keturiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(50L, new Zodis("penkiasdešimt", "penkiasdešimties", "penkiasdešimčiai", "penkiasdešimt", "penkiasdešimčia", "penkiasdešimtyje", "penkiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(60L, new Zodis("šešiasdešimt", "šešiasdešimties", "šešiasdešimčiai", "šešiasdešimt", "šešiasdešimčia", "šešiasdešimtyje", "šešiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(70L, new Zodis("septyniasdešimt", "septyniasdešimties", "septyniasdešimčiai", "septyniasdešimt", "septyniasdešimčia", "septyniasdešimtyje", "septyniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(80L, new Zodis("aštuoniasdešimt", "aštuoniasdešimties", "aštuoniasdešimčiai", "aštuoniasdešimt", "aštuoniasdešimčia", "aštuoniasdešimtyje", "aštuoniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		pagrindiniaiVyrGim.put(90L, new Zodis("devyniasdešimt", "devyniasdešimties", "devyniasdešimčiai", "devyniasdešimt", "devyniasdešimčia", "devyniasdešimtyje", "devyniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());		
		pagrindiniaiVyrGim.put(100L, new Zodis("šimtas", "šimto", "šimtui", "šimtą", "šimtu", "šimte", "šimte", "šimtai", "šimtų", "šimtams", "šimtus", "šimtais", "šimtuose", "šimtai").kitasDgsKilm().valdomas());		
		pagrindiniaiVyrGim.put(1000L, new Zodis("tūkstantis", "tūkstančio", "tūkstančiui", "tūkstantį", "tūkstančiu", "tūkstantyje", "tūkstanti", "tūkstančiai", "tūkstančių", "tūkstančiams", "tūkstančius", "tūkstančiais", "tūkstančiuose", "tūkstančiai").kitasDgsKilm().valdomas());
		pagrindiniaiVyrGim.put(1000000L, new Zodis("milijonas", "milijono", "milijonui", "milijoną", "milijonu", "milijone", "milijone", "milijonai", "milijonų", "milijonams", "milijonus", "milijonais", "milijonuose", "milijonai").kitasDgsKilm().valdomas());
		pagrindiniaiVyrGim.put(1000000000L, new Zodis("milijardas", "milijardo", "milijardui", "milijardą", "milijardu", "milijarde", "milijarde", "milijardai", "milijardų", "milijardams", "milijardus", "milijardais", "milijarduose", "milijardai").kitasDgsKilm().valdomas());
		
		kuopiniai.put(1L, new Zodis("vienetas", "vieneto", "vienetui", "vienetą", "vienetu", "vienete", "vienete").kitasDgsKilm()); // Ne visai tinka, bet tegul būna. Žr. http://ualgiman.dtiltas.lt/skaitvardis.html -  "Vienetas prie šios grupės prisijungia tik pagal gramatinius požymius..."
		kuopiniai.put(2L, new Zodis("dvejetas", "dvejeto", "dvejetui", "dvejetą", "dvejetu", "dvejete", "dvejete").kitasDgsKilm());
		kuopiniai.put(3L, new Zodis("trejetas", "trejeto", "trejetui", "trejetą", "trejetu", "trejete", "trejete").kitasDgsKilm());
		kuopiniai.put(4L, new Zodis("ketvertas", "ketverto", "ketvertui", "ketvertą", "ketvertu", "ketverte", "ketverte").kitasDgsKilm());
		kuopiniai.put(5L, new Zodis("penketas", "penketo", "penketui", "penketą", "penketu", "penkete", "penkete").kitasDgsKilm());
		kuopiniai.put(6L, new Zodis("šešetas", "šešeto", "šešetui", "šešetą", "šešetu", "šešete", "šešete").kitasDgsKilm());
		kuopiniai.put(7L, new Zodis("septynetas", "septyneto", "septynetui", "septynetą", "septynetu", "septynete", "septynete").kitasDgsKilm());
		kuopiniai.put(8L, new Zodis("aštuonetas", "aštuoneto", "aštuonetui", "aštuonetą", "aštuonetu", "aštuonete", "aštuonete").kitasDgsKilm());
		kuopiniai.put(9L, new Zodis("devynetas", "devyneto", "devynetui", "devynetą", "devynetu", "devynete", "devynete").kitasDgsKilm());
		
		dauginiaiMotGim.put(1L, new Zodis("vienerios", "vienerių", "vienerioms", "vienerias", "vieneriomis", "vieneriose", "vienerios")); // http://ualgiman.dtiltas.lt/skaitvardis.html
		dauginiaiMotGim.put(2L, new Zodis("dvejos", "dvejų", "dvejoms", "dvejas", "dvejomis", "dvejose", "dvejos"));
		dauginiaiMotGim.put(3L, new Zodis("trejos", "trejų", "trejoms", "trejas", "trejomis", "trejose", "trejos"));
		dauginiaiMotGim.put(4L, new Zodis("ketverios", "ketverių", "ketverioms", "ketverias", "ketveriomis", "ketveriose", "ketverios"));
		dauginiaiMotGim.put(5L, new Zodis("penkerios", "penkerių", "penkerioms", "penkerias", "penkeriomis", "penkeriose", "penkerios"));
		dauginiaiMotGim.put(6L, new Zodis("šešerios", "šešerių", "šešerioms", "šešerias", "šešeriomis", "šešeriose", "šešerios"));
		dauginiaiMotGim.put(7L, new Zodis("septynerios", "septynerių", "septynerioms", "septynerias", "septyneriomis", "septyneriose", "septynerios"));
		dauginiaiMotGim.put(8L, new Zodis("aštuonerios", "aštuonerių", "aštuonerioms", "aštuonerias", "aštuoneriomis", "aštuoneriose", "aštuonerios"));
		dauginiaiMotGim.put(9L, new Zodis("devynerios", "devynerių", "devynerioms", "devynerias", "devyneriomis", "devyneriose", "devynerios"));
		
		dauginiaiVyrGim.put(1L, new Zodis("vieneri", "vienerių", "vieneriems", "vienerius", "vieneriais", "vieneriuose", "vieneri")); // http://ualgiman.dtiltas.lt/skaitvardis.html
		dauginiaiVyrGim.put(2L, new Zodis("dveji", "dvejų", "dvejiems", "dvejus", "dvejais", "dvejuose", "dveji"));
		dauginiaiVyrGim.put(3L, new Zodis("treji", "trejų", "trejiems", "trejus", "trejais", "trejuose", "treji"));
		dauginiaiVyrGim.put(4L, new Zodis("ketveri", "ketverių", "ketveriems", "ketverius", "ketveriais", "ketveriuose", "ketveri"));
		dauginiaiVyrGim.put(5L, new Zodis("penkeri", "penkerių", "penkeriems", "penkerius", "penkeriais", "penkeriuose", "penkeri"));
		dauginiaiVyrGim.put(6L, new Zodis("šešeri", "šešerių", "šešeriems", "šešerius", "šešeriais", "šešeriuose", "šešeri"));
		dauginiaiVyrGim.put(7L, new Zodis("septyneri", "septynerių", "septyneriems", "septynerius", "septyneriais", "septyneriuose", "septyneri"));
		dauginiaiVyrGim.put(8L, new Zodis("aštuoneri", "aštuonerių", "aštuoneriems", "aštuonerius", "aštuoneriais", "aštuoneriuose", "aštuoneri"));
		dauginiaiVyrGim.put(9L, new Zodis("devyneri", "devynerių", "devyneriems", "devynerius", "devyneriais", "devyneriuose", "devyneri"));
		
		kelintiniaiVyrGim.put(0L, new Zodis("nulinis", "nulinio", "nuliniui", "nulinį", "nuliniu", "nulinyje", "nulini", "nuliniai", "nulinių", "nuliniams", "nulinius", "nuliniais", "nuliniuose", "nuliniai"));
		kelintiniaiVyrGim.put(1L, new Zodis("pirmas", "pirmo", "pirmam", "pirmą", "pirmu", "pirmame", "pirmas", "pirmi", "pirmų", "pirmiems", "pirmus", "pirmais", "pirmuose", "pirmi"));
		kelintiniaiVyrGim.put(2L, new Zodis("antras", "antro", "antram", "antrą", "antru", "antrame", "antras", "antri", "antrų", "antriems", "antrus", "antrais", "antruose", "antri"));
		kelintiniaiVyrGim.put(3L, new Zodis("trečias", "trečio", "trečiam", "trečią", "trečiu", "trečiame", "trečias", "treti", "trečių", "tretiems", "trečius", "trečiais", "trečiuose", "treti"));
		kelintiniaiVyrGim.put(4L, new Zodis("ketvirtas", "ketvirto", "ketvirtam", "ketvirtą", "ketvirtu", "ketvirtame", "ketvirtas", "ketvirti", "ketvirtų", "ketvirtiems", "ketvirtus", "ketvirtais", "ketvirtuose", "ketvirti"));
		kelintiniaiVyrGim.put(5L, new Zodis("penktas", "penkto", "penktam", "penktą", "penktu", "penktame", "penktas", "penkti", "penktų", "penktiems", "penktus", "penktais", "penktuose", "penkti"));
		kelintiniaiVyrGim.put(6L, new Zodis("šeštas", "šešto", "šeštam", "šeštą", "šeštu", "šeštame", "šeštas", "šešti", "šeštų", "šeštiems", "šeštus", "šeštiems", "šeštuose", "šešti"));
		kelintiniaiVyrGim.put(7L, new Zodis("septintas", "septinto", "septintam", "septintą", "septintu", "septintame", "septintas", "septinti", "septintų", "septintiems", "septintus", "septintiems", "septintuose", "septinti"));
		kelintiniaiVyrGim.put(8L, new Zodis("aštuntas", "aštunto", "aštuntam", "aštuntą", "aštuntu", "aštuntame", "aštuntas", "aštunti", "aštuntų", "aštuntiems", "aštuntus", "aštuntais", "aštuntuose", "aštunti"));
		kelintiniaiVyrGim.put(9L, new Zodis("devintas", "devinto", "devintam", "devintą", "devintu", "devintame", "devintas", "devinti", "devintų", "devintiems", "devintus", "devintais", "devintuose", "devinti"));
		kelintiniaiVyrGim.put(10L, new Zodis("dešimtas", "dešimto", "dešimtam", "dešimtą", "dešimtu", "dešimtame", "dešimtas", "dešimti", "dešimtų", "dešimtiems", "dešimtus", "dešimtais", "dešimtuose", "dešimti"));
		kelintiniaiVyrGim.put(11L, new Zodis("vienuoliktas", "vienuolikto", "vienuoliktam", "vienuoliktą", "vienuoliktu", "vienuoliktame", "vienuoliktas", "vienuolikti", "vienuoliktų", "vienuoliktiems", "vienuoliktus", "vienuoliktais", "vienuoliktuose", "vienuolikti"));
		kelintiniaiVyrGim.put(12L, new Zodis("dvyliktas", "dvylikto", "dvyliktam", "dvyliktą", "dvyliktu", "dvyliktame", "dvyliktas", "dvylikti", "dvyliktų", "dvyliktiems", "dvyliktus", "dvyliktais", "dvyliktuose", "dvylikti"));
		kelintiniaiVyrGim.put(13L, new Zodis("tryliktas", "trylikto", "tryliktam", "tryliktą", "tryliktu", "tryliktame", "tryliktas", "trylikti", "tryliktų", "tryliktiems", "tryliktus", "tryliktais", "tryliktuose", "trylikti"));
		kelintiniaiVyrGim.put(14L, new Zodis("keturioliktas", "keturiolikto", "keturioliktam", "keturioliktą", "keturioliktais", "keturioliktuose", "keturioliktas", "keturiolikti", "keturioliktų", "keturioliktiems", "keturioliktus", "keturioliktais", "keturioliktuose", "keturiolikti"));
		kelintiniaiVyrGim.put(15L, new Zodis("penkioliktas", "penkiolikto", "penkioliktam", "penkioliktą", "penkioliktu", "penkioliktame", "penkioliktas", "penkiolikti", "penkioliktų", "penkioliktiems", "penkioliktus", "penkioliktais", "penkioliktuose", "penkiolikti"));
		kelintiniaiVyrGim.put(16L, new Zodis("šešioliktas", "šešiolikto", "šešioliktam", "šešioliktą", "šešioliktu", "šešioliktame", "šešioliktas", "šešiolikti", "šešioliktų", "šešioliktiems", "šešioliktus", "šešioliktais", "šešioliktuose", "šešiolikti"));
		kelintiniaiVyrGim.put(17L, new Zodis("septynioliktas", "septyniolikto", "septynioliktam", "septynioliktą", "septynioliktu", "septynioliktame", "septynioliktas", "septyniolikti", "septynioliktų", "septynioliktiems", "septynioliktus", "septynioliktais", "septynioliktuose", "septyniolikti"));
		kelintiniaiVyrGim.put(18L, new Zodis("aštuonioliktas", "aštuoniolikto", "aštuonioliktam", "aštuonioliktą", "aštuonioliktu", "aštuonioliktame", "aštuonioliktas", "aštuoniolikti", "aštuonioliktų", "aštuonioliktiems", "aštuonioliktus", "aštuonioliktais", "aštuonioliktuose", "aštuoniolikti"));
		kelintiniaiVyrGim.put(19L, new Zodis("devynioliktas", "devyniolikto", "devynioliktam", "devynioliktą", "devynioliktu", "devynioliktame", "devynioliktas", "devyniolikti", "devynioliktų", "devynioliktiems", "devynioliktus", "devynioliktais", "devynioliktuose", "devyniolikti"));
		kelintiniaiVyrGim.put(20L, new Zodis("dvidešimtas", "dvidešimto", "dvidešimtam", "dvidešimtą", "dvidešimtu", "dvidešimtame", "dvidešimtas", "dvidešimti", "dvidešimtų", "dvidešimtiems", "dvidešimtus", "dvidešimtais", "dvidešimtuose", "dvidešimti"));		
		kelintiniaiVyrGim.put(30L, new Zodis("trisdešimtas", "trisdešimto", "trisdešimtam", "trisdešimtą", "trisdešimtu", "trisdešimtame", "trisdešimtas", "trisdešimti", "trisdešimtų", "trisdešimtiems", "trisdešimtus", "trisdešimtais", "trisdešimtuose", "trisdešimti"));
		kelintiniaiVyrGim.put(40L, new Zodis("keturiasdešimtas", "keturiasdešimto", "keturiasdešimtam", "keturiasdešimtą", "keturiasdešimtu", "keturiasdešimtame", "keturiasdešimtas", "keturiasdešimti", "keturiasdešimtų", "keturiasdešimtiems", "keturiasdešimtus", "keturiasdešimtais", "keturiasdešimtuose", "keturiasdešimti"));
		kelintiniaiVyrGim.put(50L, new Zodis("penkiasdešimtas", "penkiasdešimto", "penkiasdešimtam", "penkiasdešimtą", "penkiasdešimtu", "penkiasdešimtame", "penkiasdešimtas", "penkiasdešimti", "penkiasdešimtų", "penkiasdešimtiems", "penkiasdešimtus", "penkiasdešimtais", "penkiasdešimtuose", "penkiasdešimti"));
		kelintiniaiVyrGim.put(60L, new Zodis("šešiasdešimtas", "šešiasdešimto", "šešiasdešimtam", "šešiasdešimtą", "šešiasdešimtu", "šešiasdešimtame", "šešiasdešimtas", "šešiasdešimti", "šešiasdešimtų", "šešiasdešimtiems", "šešiasdešimtus", "šešiasdešimtais", "šešiasdešimtuose", "šešiasdešimti"));
		kelintiniaiVyrGim.put(70L, new Zodis("septyniasdešimtas", "septyniasdešimto", "septyniasdešimtam", "septyniasdešimtą", "septyniasdešimtu", "septyniasdešimtame", "septyniasdešimtas", "septyniasdešimti", "septyniasdešimtų", "septyniasdešimtiems", "septyniasdešimtus", "septyniasdešimtais", "septyniasdešimtuose", "septyniasdešimti"));
		kelintiniaiVyrGim.put(80L, new Zodis("aštuoniasdešimtas", "aštuoniasdešimto", "aštuoniasdešimtam", "aštuoniasdešimtą", "aštuoniasdešimtu", "aštuoniasdešimtame", "aštuoniasdešimtas", "aštuoniasdešimti", "aštuoniasdešimtų", "aštuoniasdešimtiems", "aštuoniasdešimtus", "aštuoniasdešimtais", "aštuoniasdešimtuose", "aštuoniasdešimti"));
		kelintiniaiVyrGim.put(90L, new Zodis("devyniasdešimtas", "devyniasdešimto", "devyniasdešimtam", "devyniasdešimtą", "devyniasdešimtu", "devyniasdešimtame", "devyniasdešimtas", "devyniasdešimti", "devyniasdešimtų", "devyniasdešimtiems", "devyniasdešimtus", "devyniasdešimtais", "devyniasdešimtuose", "devyniasdešimti"));		
		
		kelintiniaiMotGim.put(0L, new Zodis("nulinė", "nulinės", "nulinei", "nulinę", "nuline", "nulinėje", "nulinė", "nulinės", "nulinių", "nulinėms", "nulines", "nulinėmis", "nulinėse", "nulinės"));
		kelintiniaiMotGim.put(1L, new Zodis("pirma", "pirmos", "pirmai", "pirmą", "pirma", "pirmoje", "pirma", "pirmos", "pirmų", "pirmoms", "pirmas", "pirmomis", "pirmose", "pirmos"));
		kelintiniaiMotGim.put(2L, new Zodis("antra", "antros", "antrai", "antrą", "antra", "antroje", "antra", "antros", "antrų", "antroms", "antras", "antromis", "antrose", "antros"));
		kelintiniaiMotGim.put(3L, new Zodis("trečia", "trečios", "trečiai", "trečią", "trečia", "trečioje", "trečia", "trečios", "trečių", "trečioms", "trečias", "trečiomis", "trečiose", "trečios"));
		kelintiniaiMotGim.put(4L, new Zodis("ketvirta", "ketvirtos", "ketvirtai", "ketvirtą", "ketvirta", "ketvirtoje", "ketvirta", "ketvirtos", "ketvirtų", "ketvirtoms", "ketvirtas", "ketvirtomis", "ketvirtose", "ketvirtos"));
		kelintiniaiMotGim.put(5L, new Zodis("penkta", "penktos", "penktai", "penktą", "penkta", "penktoje", "penkta", "penktos", "penktų", "penktoms", "penktas", "penktomis", "penktose", "penktos"));
		kelintiniaiMotGim.put(6L, new Zodis("šešta", "šeštos", "šeštai", "šeštą", "šešta", "šeštoje", "šešta", "šeštos", "šeštų", "šeštoms", "šeštas", "šeštomis", "šeštose", "šeštos"));
		kelintiniaiMotGim.put(7L, new Zodis("septinta", "septintos", "septintai", "septintą", "septinta", "septintoje", "septinta", "septintos", "septintų", "septintai", "septintą", "septintomis", "septintose", "septintos"));
		kelintiniaiMotGim.put(8L, new Zodis("aštunta", "aštuntos", "aštuntai", "aštuntą", "aštunta", "aštuntoje", "aštunta", "aštuntos", "aštuntų", "aštuntoms", "aštuntas", "aštuntomis", "aštuntose", "aštuntos"));
		kelintiniaiMotGim.put(9L, new Zodis("devinta", "devintos", "devintai", "devintą", "devinta", "devintoje", "devinta", "devintos", "devintų", "devintoms", "devintas", "devintomis", "devintose", "devintos"));
		kelintiniaiMotGim.put(10L, new Zodis("dešimta", "dešimtos", "dešimtai", "dešimtą", "dešimta", "dešimtoje", "dešimta", "dešimtos", "dešimtų", "dešimtoms", "dešimtas", "dešimtomis", "dešimtose", "dešimtos"));
		kelintiniaiMotGim.put(11L, new Zodis("vienuolikta", "vienuoliktos", "vienuoliktai", "vienuoliktą", "vienuolikta", "vienuoliktoje", "vienuolikta", "vienuoliktos", "vienuoliktų", "vienuoliktoms", "vienuoliktas", "vienuoliktomis", "vienuoliktose", "vienuoliktos"));
		kelintiniaiMotGim.put(12L, new Zodis("dvylikta", "dvyliktos", "dvyliktai", "dvyliktą", "dvylikta", "dvyliktoje", "dvylikta", "dvyliktos", "dvyliktų", "dvyliktoms", "dvyliktas", "dvyliktomis", "dvyliktose", "dvyliktos"));
		kelintiniaiMotGim.put(13L, new Zodis("trylikta", "tryliktos", "tryliktai", "tryliktą", "trylikta", "tryliktoje", "trylikta", "tryliktos", "tryliktų", "tryliktoms", "tryliktas", "tryliktomis", "tryliktose", "tryliktas"));
		kelintiniaiMotGim.put(14L, new Zodis("keturiolikta", "keturioliktos", "keturioliktai", "keturioliktą", "keturiolikta", "keturioliktoje", "keturiolikta", "keturioliktos", "keturioliktų", "keturioliktoms", "keturioliktas", "keturioliktomis", "keturioliktose", "keturioliktos"));
		kelintiniaiMotGim.put(15L, new Zodis("penkiolikta", "penkioliktos", "penkioliktai", "penkioliktą", "penkiolikta", "penkioliktoje", "penkiolikta", "penkioliktos", "penkioliktų", "penkioliktoms", "penkioliktas", "penkioliktomis", "penkioliktose", "penkioliktos"));
		kelintiniaiMotGim.put(16L, new Zodis("šešiolikta", "šešioliktos", "šešioliktai", "šešioliktą", "šešiolikta", "šešioliktoje", "šešiolikta", "šešioliktos", "šešioliktų", "šešioliktoms", "šešioliktas", "šešioliktomis", "šešioliktose", "šešioliktos"));
		kelintiniaiMotGim.put(17L, new Zodis("septyniolikta", "septynioliktos", "septynioliktai", "septynioliktą", "septyniolikta", "septynioliktoje", "septyniolikta", "septynioliktos", "septynioliktų", "septynioliktoms", "septynioliktas", "septynioliktomis", "septynioliktose", "septynioliktos"));
		kelintiniaiMotGim.put(18L, new Zodis("aštuoniolikta", "aštuonioliktos", "aštuonioliktai", "aštuonioliktą", "aštuoniolikta", "aštuonioliktoje", "aštuoniolikta", "aštuonioliktos", "aštuonioliktų", "aštuonioliktoms", "aštuonioliktas", "aštuonioliktomis", "aštuonioliktose", "aštuonioliktos"));
		kelintiniaiMotGim.put(19L, new Zodis("devyniolikta", "devynioliktos", "devynioliktai", "devynioliktą", "devyniolikta", "devynioliktoje", "devyniolikta", "devynioliktos", "devynioliktų", "devynioliktoms", "devynioliktas", "devynioliktomis", "devynioliktose", "devynioliktos"));
		kelintiniaiMotGim.put(20L, new Zodis("dvidešimta", "dvidešimtos", "dvidešimtai", "dvidešimtą", "dvidešimta", "dvidešimtoje", "dvidešimta", "dvidešimtos", "dvidešimtų", "dvidešimtoms", "dvidešimtas", "dvidešimtomis", "dvidešimtose", "dvidešimtos"));		
		kelintiniaiMotGim.put(30L, new Zodis("trisdešimta", "trisdešimtos", "trisdešimtai", "trisdešimtą", "trisdešimta", "trisdešimtoje", "trisdešimta", "trisdešimtos", "trisdešimtų", "trisdešimtoms", "trisdešimtas", "trisdešimtomis", "trisdešimtose", "trisdešimtos"));
		kelintiniaiMotGim.put(40L, new Zodis("keturiasdešimta", "keturiasdešimtos", "keturiasdešimtai", "keturiasdešimtą", "keturiasdešimta", "keturiasdešimtoje", "keturiasdešimta", "keturiasdešimtos", "keturiasdešimtų", "keturiasdešimtoms", "keturiasdešimtas", "keturiasdešimtomis", "keturiasdešimtose", "keturiasdešimtos"));
		kelintiniaiMotGim.put(50L, new Zodis("penkiasdešimta", "penkiasdešimtos", "penkiasdešimtai", "penkiasdešimtą", "penkiasdešimta", "penkiasdešimtoje", "penkiasdešimta", "penkiasdešimtos", "penkiasdešimtų", "penkiasdešimtoms", "penkiasdešimtas", "penkiasdešimtomis", "penkiasdešimtose", "penkiasdešimtos"));
		kelintiniaiMotGim.put(60L, new Zodis("šešiasdešimta", "šešiasdešimtos", "šešiasdešimtai", "šešiasdešimtą", "šešiasdešimta", "šešiasdešimtoje", "šešiasdešimta", "šešiasdešimtos", "šešiasdešimtų", "šešiasdešimtoms", "šešiasdešimtas", "šešiasdešimtomis", "šešiasdešimtose", "šešiasdešimtos"));
		kelintiniaiMotGim.put(70L, new Zodis("septyniasdešimta", "septyniasdešimtos", "septyniasdešimtai", "septyniasdešimtą", "septyniasdešimta", "septyniasdešimtoje", "septyniasdešimta", "septyniasdešimtos", "septyniasdešimtų", "septyniasdešimtoms", "septyniasdešimtas", "septyniasdešimtomis", "septyniasdešimtose", "septyniasdešimtos"));
		kelintiniaiMotGim.put(80L, new Zodis("aštuoniasdešimta", "aštuoniasdešimtos", "aštuoniasdešimtai", "aštuoniasdešimtą", "aštuoniasdešimta", "aštuoniasdešimtoje", "aštuoniasdešimta", "aštuoniasdešimtos", "aštuoniasdešimtų", "aštuoniasdešimtoms", "aštuoniasdešimtas", "aštuoniasdešimtomis", "aštuoniasdešimtose", "aštuoniasdešimtos"));
		kelintiniaiMotGim.put(90L, new Zodis("devyniasdešimta", "devyniasdešimtos", "devyniasdešimtai", "devyniasdešimtą", "devyniasdešimta", "devyniasdešimtoje", "devyniasdešimta", "devyniasdešimtos", "devyniasdešimtų", "devyniasdešimtoms", "devyniasdešimtas", "devyniasdešimtomis", "devyniasdešimtose", "devyniasdešimtos"));
		
		kelintiniaiIvVyrGim.put(1L, new Zodis("pirmasis", "pirmojo", "pirmajam", "pirmąjį", "pirmuoju", "pirmajame", "pirmasis", "pirmieji", "pirmųjų", "pirmiesiems", "pirmuosius", "pirmaisiais", "pirmuosiuose", "pirmieji"));
		kelintiniaiIvVyrGim.put(2L, new Zodis("antrasis", "antrojo", "antrajam", "antrąjį", "antruoju", "antrajame", "antrasis", "antrieji", "antrųjų", "antriesiems", "antruosius", "antraisiais", "antruosiuose", "antrieji"));
		kelintiniaiIvVyrGim.put(3L, new Zodis("trečiasis", "trečiojo", "trečiajam", "trečiąjį", "trečiuoju", "trečiajame", "trečiasis", "tretieji", "trečiųjų", "tretiesiems", "trečiuosius", "trečiaisiais", "trečiuosiuose", "tretieji"));
		kelintiniaiIvVyrGim.put(4L, new Zodis("ketvirtasis", "ketvirtojo", "ketvirtajam", "ketvirtąjį", "ketvirtuoju", "ketvirtajame", "ketvirtasis", "ketvirtieji", "ketvirtųjų", "ketvirtiesiems", "ketvirtuosius", "ketvirtaisiais", "ketvirtuosiuose", "ketvirtieji"));
		kelintiniaiIvVyrGim.put(5L, new Zodis("penktasis", "penktojo", "penktajam", "penktąjį", "penktuoju", "penktajame", "penktasis", "penktieji", "penktųjų", "penktiesiems", "penktuosius", "penktaisiais", "penktuosiuose", "penktieji"));
		kelintiniaiIvVyrGim.put(6L, new Zodis("šeštasis", "šeštojo", "šeštajam", "šeštąjį", "šeštuoju", "šeštajame", "šeštasis", "šeštieji", "šeštųjų", "šeštiesiems", "šeštuosius", "šeštaisiais", "šeštuosiuose", "šeštieji"));
		kelintiniaiIvVyrGim.put(7L, new Zodis("septintasis", "septintojo", "septintajam", "septintąjį", "septintuoju", "septintajame", "septintasis", "septintieji", "septintųjų", "septintiesiems", "septintuosius", "septintaisiais", "septintuosiuose", "septintieji"));
		kelintiniaiIvVyrGim.put(8L, new Zodis("aštuntasis", "aštuntojo", "aštuntajam", "aštuntąjį", "aštuntuoju", "aštuntajame", "aštuntasis", "aštuntieji", "aštuntųjų", "aštuntiesiems", "aštuntuosius", "aštuntaisiais", "aštuntuosiuose", "aštuntieji"));
		kelintiniaiIvVyrGim.put(9L, new Zodis("devintasis", "devintojo", "devintajam", "devintąjį", "devintuoju", "devintajame", "devintasis", "devintieji", "devintųjų", "devintiesiems", "devintuosius", "devintaisiais", "devintuosiuose", "devintieji"));
		kelintiniaiIvVyrGim.put(10L, new Zodis("dešimtasis", "dešimtojo", "dešimtajam", "dešimtąjį", "dešimtuoju", "dešimtajame", "dešimtasis", "dešimtieji", "dešimtųjų", "dešimtiesiems", "dešimtuosius", "dešimtaisiais", "dešimtuosiuose", "dešimtieji"));
		kelintiniaiIvVyrGim.put(11L, new Zodis("vienuoliktasis", "vienuoliktojo", "vienuoliktajam", "vienuoliktąjį", "vienuoliktuoju", "vienuoliktajame", "vienuoliktasis", "vienuoliktieji", "vienuoliktųjų", "vienuoliktiesiems", "vienuoliktuosius", "vienuoliktaisiais", "vienuoliktuosiuose", "vienuoliktieji"));
		kelintiniaiIvVyrGim.put(12L, new Zodis("dvyliktasis", "dvyliktojo", "dvyliktajam", "dvyliktąjį", "dvyliktuoju", "dvyliktajame", "dvyliktasis", "dvyliktieji", "dvyliktųjų", "dvyliktiesiems", "dvyliktuosius", "dvyliktaisiais", "dvyliktuosiuose", "dvyliktieji"));
		kelintiniaiIvVyrGim.put(13L, new Zodis("tryliktasis", "tryliktojo", "tryliktajam", "tryliktąjį", "tryliktuoju", "tryliktajame", "tryliktasis", "tryliktieji", "tryliktųjų", "tryliktiesiems", "tryliktuosius", "tryliktaisiais", "tryliktuosiuose", "tryliktieji"));
		kelintiniaiIvVyrGim.put(14L, new Zodis("keturioliktasis", "keturioliktojo", "keturioliktajam", "keturioliktąjį", "keturioliktuoju", "keturioliktajame", "keturioliktasis", "keturioliktieji", "keturioliktųjų", "keturioliktiesiems", "keturioliktuosius", "keturioliktaisiais", "keturioliktuosiuose", "keturioliktieji"));
		kelintiniaiIvVyrGim.put(15L, new Zodis("penkioliktasis", "penkioliktojo", "penkioliktajam", "penkioliktąjį", "penkioliktuoju", "penkioliktajame", "penkioliktasis", "penkioliktieji", "penkioliktųjų", "penkioliktiesiems", "penkioliktuosius", "penkioliktaisiais", "penkioliktuosiuose", "penkioliktieji"));
		kelintiniaiIvVyrGim.put(16L, new Zodis("šešioliktasis", "šešioliktojo", "šešioliktajam", "šešioliktąjį", "šešioliktuoju", "šešioliktajame", "šešioliktasis", "šešioliktieji", "šešioliktųjų", "šešioliktiesiems", "šešioliktuosius", "šešioliktaisiais", "šešioliktuosiuose", "šešioliktieji"));
		kelintiniaiIvVyrGim.put(17L, new Zodis("septynioliktasis", "septynioliktojo", "septynioliktajam", "septynioliktąjį", "septynioliktuoju", "septynioliktajame", "septynioliktasis", "septynioliktieji", "septynioliktųjų", "septynioliktiesiems", "septynioliktuosius", "septynioliktaisiais", "septynioliktuosiuose", "septynioliktieji"));
		kelintiniaiIvVyrGim.put(18L, new Zodis("aštuonioliktasis", "aštuonioliktojo", "aštuonioliktajam", "aštuonioliktąjį", "aštuonioliktuoju", "aštuonioliktajame", "aštuonioliktasis", "aštuonioliktieji", "aštuonioliktųjų", "aštuonioliktiesiems", "aštuonioliktuosius", "aštuonioliktaisiais", "aštuonioliktuosiuose", "aštuonioliktieji"));
		kelintiniaiIvVyrGim.put(19L, new Zodis("devynioliktasis", "devynioliktojo", "devynioliktajam", "devynioliktąjį", "devynioliktuoju", "devynioliktajame", "devynioliktasis", "devynioliktieji", "devynioliktųjų", "devynioliktiesiems", "devynioliktuosius", "devynioliktaisiais", "devynioliktuosiuose", "devynioliktieji"));
		kelintiniaiIvVyrGim.put(20L, new Zodis("dvidešimtasis", "dvidešimtojo", "dvidešimtajam", "dvidešimtąjį", "dvidešimtuoju", "dvidešimtajame", "dvidešimtasis", "dvidešimtieji", "dvidešimtųjų", "dvidešimtiesiems", "dvidešimtuosius", "dvidešimtaisiais", "dvidešimtuosiuose", "dvidešimtieji"));		
		kelintiniaiIvVyrGim.put(30L, new Zodis("trisdešimtasis", "trisdešimtojo", "trisdešimtajam", "trisdešimtąjį", "trisdešimtuoju", "trisdešimtajame", "trisdešimtasis", "trisdešimtieji", "trisdešimtųjų", "trisdešimtiesiems", "trisdešimtuosius", "trisdešimtaisiais", "trisdešimtuosiuose", "trisdešimtieji"));
		kelintiniaiIvVyrGim.put(40L, new Zodis("keturiasdešimtasis", "keturiasdešimtojo", "keturiasdešimtajam", "keturiasdešimtąjį", "keturiasdešimtuoju", "keturiasdešimtajame", "keturiasdešimtasis", "keturiasdešimtieji", "keturiasdešimtųjų", "keturiasdešimtiesiems", "keturiasdešimtuosius", "keturiasdešimtaisiais", "keturiasdešimtuosiuose", "keturiasdešimtieji"));
		kelintiniaiIvVyrGim.put(50L, new Zodis("penkiasdešimtasis", "penkiasdešimtojo", "penkiasdešimtajam", "penkiasdešimtąjį", "penkiasdešimtuoju", "penkiasdešimtajame", "penkiasdešimtasis", "penkiasdešimtieji", "penkiasdešimtųjų", "penkiasdešimtiesiems", "penkiasdešimtuosius", "penkiasdešimtaisiais", "penkiasdešimtuosiuose", "penkiasdešimtieji"));
		kelintiniaiIvVyrGim.put(60L, new Zodis("šešiasdešimtasis", "šešiasdešimtojo", "šešiasdešimtajam", "šešiasdešimtąjį", "šešiasdešimtuoju", "šešiasdešimtajame", "šešiasdešimtasis", "šešiasdešimtieji", "šešiasdešimtųjų", "šešiasdešimtiesiems", "šešiasdešimtuosius", "šešiasdešimtaisiais", "šešiasdešimtuosiuose", "šešiasdešimtieji"));
		kelintiniaiIvVyrGim.put(70L, new Zodis("septyniasdešimtasis", "septyniasdešimtojo", "septyniasdešimtajam", "septyniasdešimtąjį", "septyniasdešimtuoju", "septyniasdešimtajame", "septyniasdešimtasis", "septyniasdešimtieji", "septyniasdešimtųjų", "septyniasdešimtiesiems", "septyniasdešimtuosius", "septyniasdešimtaisiais", "septyniasdešimtuosiuose", "septyniasdešimtieji"));
		kelintiniaiIvVyrGim.put(80L, new Zodis("aštuoniasdešimtasis", "aštuoniasdešimtojo", "aštuoniasdešimtajam", "aštuoniasdešimtąjį", "aštuoniasdešimtuoju", "aštuoniasdešimtajame", "aštuoniasdešimtasis", "aštuoniasdešimtieji", "aštuoniasdešimtųjų", "aštuoniasdešimtiesiems", "aštuoniasdešimtuosius", "aštuoniasdešimtaisiais", "aštuoniasdešimtuosiuose", "aštuoniasdešimtieji"));
		kelintiniaiIvVyrGim.put(90L, new Zodis("devyniasdešimtasis", "devyniasdešimtojo", "devyniasdešimtajam", "devyniasdešimtąjį", "devyniasdešimtuoju", "devyniasdešimtajame", "devyniasdešimtasis", "devyniasdešimtieji", "devyniasdešimtųjų", "devyniasdešimtiesiems", "devyniasdešimtuosius", "devyniasdešimtaisiais", "devyniasdešimtuosiuose", "devyniasdešimtieji"));
		kelintiniaiIvVyrGim.put(100L, new Zodis("šimtasis", "šimtojo", "šimtajam", "šimtąjį", "šimtuoju", "šimtajame", "šimtasis", "šimtieji", "šimtųjų", "šimtiesiems", "šimtuosius", "šimtaisiais", "šimtuosiuose", "šimtieji"));		
		kelintiniaiIvVyrGim.put(1000L, new Zodis("tūkstantasis", "tūkstantojo", "tūkstantajam", "tūkstantąjį", "tūkstantuoju", "tūkstantajame", "tūkstantasis", "tūkstantieji", "tūkstantųjų", "tūkstantiesiems", "tūkstantuosius", "tūkstantaisiais", "tūkstantuosiuose", "tūkstantieji"));
		kelintiniaiIvVyrGim.put(1000000L, new Zodis("milijonasis", "milijonojo", "milijonajam", "milijonąjį", "milijonuoju", "milijonajame", "milijonasis", "milijonieji", "milijonųjų", "milijoniesiems", "milijonuosius", "milijonaisiais", "milijonuosiuose", "milijonieji"));
		kelintiniaiIvVyrGim.put(1000000000L, new Zodis("milijardasis", "milijardojo", "milijardajam", "milijardąjį", "milijarduoju", "milijardajame", "milijardasis", "milijardieji", "milijardųjų", "milijardiesiems", "milijarduosius", "milijardaisiais", "milijarduosiuose", "milijardieji"));
		
		// TODO perskaityti (žemiau)
		kelintiniaiIvMotGim.put(1L, new Zodis("pirmoji", "pirmosios", "pirmajai", "pirmąją", "pirmąja", "pirmojoje", "pirmoji", "pirmosios", "pirmųjų", "pirmosioms", "pirmąsias", "pirmosiomis", "pirmosiose", "pirmosios"));
		kelintiniaiIvMotGim.put(2L, new Zodis("antroji", "antrosios", "antrajai", "antrąją", "antrąja", "antrojoje", "antroji", "antrosios", "antrųjų", "antrosioms", "antrąsias", "antrosiomis", "antrosiose", "antrosios"));
		kelintiniaiIvMotGim.put(3L, new Zodis("trečioji", "trečiosios", "trečiajai", "trečiąją", "trečiąja", "trečiojoje", "trečioji", "trečiosios", "trečiųjų", "trečiosioms", "trečiąsias", "trečiosiomis", "trečiosiose", "trečiosiose"));
		kelintiniaiIvMotGim.put(4L, new Zodis("ketvirtoji", "ketvirtosios", "ketvirtajai", "ketvirtąją", "ketvirtąja", "ketvirtojoje", "ketvirtoji", "ketvirtosios", "ketvirtųjų", "ketvirtosioms", "ketvirtąsias", "ketvirtosiomis", "ketvirtosiose", "ketvirtosios"));
		kelintiniaiIvMotGim.put(5L, new Zodis("penktoji", "penktosios", "penktajai", "penktąją", "penktąja", "penktojoje", "penktoji", "penktosios", "penktųjų", "penktosioms", "penktąsias", "penktosiomis", "penktosiose", "penktosios"));
		kelintiniaiIvMotGim.put(6L, new Zodis("šeštoji", "šeštosios", "šeštajai", "šeštąją", "šeštąja", "šeštojoje", "šeštoji", "šeštosios", "šeštųjų", "šeštosioms", "šeštąsias", "šeštosiomis", "šeštosiose", "šeštosios"));
		kelintiniaiIvMotGim.put(7L, new Zodis("septintoji", "septintosios", "septintjai", "septintąją", "septintąja", "septintojoje", "septintoji", "septintosios", "septintųjų", "septintosioms", "septintąsias", "septintosiomis", "septintosiose", "septintosios"));
		kelintiniaiIvMotGim.put(8L, new Zodis("aštuntoji", "aštuntosios", "aštuntajai", "aštuntąją", "aštuntąja", "aštuntojoje", "aštuntoji", "aštuntosios", "aštuntųjų", "aštuntosioms", "aštuntąsias", "aštuntosiomis", "aštuntosiose", "aštuntosios"));
		kelintiniaiIvMotGim.put(9L, new Zodis("devintoji", "devintosios", "devintajai", "devintąją", "devintąja", "devintojoje", "devintoji", "devintosios", "devintųjų", "devintosioms", "devintąsias", "devintosiomis", "devintosiose", "devintosios"));
		kelintiniaiIvMotGim.put(10L, new Zodis("dešimtoji", "dešimtosios", "dešimtajai", "dešimtąją", "dešimtąja", "dešimtojoje", "dešimtoji", "dešimtosios", "dešimtųjų", "dešimtosioms", "dešimtąsias", "dešimtosiomis", "dešimtosiose", "dešimtosios"));
		kelintiniaiIvMotGim.put(11L, new Zodis("vienuoliktoji", "vienuoliktosios", "vienuoliktajai", "vienuoliktąją", "vienuoliktąja", "vienuoliktojoje", "vienuoliktoji", "vienuoliktosios", "vienuoliktųjų", "vienuoliktosioms", "vienuoliktąsias", "vienuoliktosiomis", "vienuoliktosiose", "vienuoliktosios"));
		kelintiniaiIvMotGim.put(12L, new Zodis("dvyliktoji", "dvyliktosios", "dvyliktajai", "dvyliktąją", "dvyliktąja", "dvyliktojoje", "dvyliktoji", "dvyliktosios", "dvyliktųjų", "dvyliktosioms", "dvyliktąsias", "dvyliktosiomis", "dvyliktosiose", "dvyliktosios"));
		kelintiniaiIvMotGim.put(13L, new Zodis("tryliktoji", "tryliktosios", "tryliktajai", "tryliktąją", "tryliktąja", "tryliktojoje", "tryliktoji", "tryliktosios", "tryliktųjų", "tryliktosioms", "tryliktąsias", "tryliktosiomis", "tryliktosiose", "tryliktosios"));
		kelintiniaiIvMotGim.put(14L, new Zodis("keturioliktoji", "keturioliktosios", "keturioliktajai", "keturioliktąją", "keturioliktąja", "keturioliktojoje", "keturioliktoji", "keturioliktosios", "keturioliktųjų", "keturioliktosioms", "keturioliktąsias", "keturioliktosiomis", "keturioliktosiose", "keturioliktosios"));
		kelintiniaiIvMotGim.put(15L, new Zodis("penkioliktoji", "penkioliktosios", "penkioliktajai", "penkioliktąją", "penkioliktąja", "penkioliktojoje", "penkioliktoji", "penkioliktosios", "penkioliktųjų", "penkioliktosioms", "penkioliktąsias", "penkioliktosiomis", "penkioliktosiose", "penkioliktosios"));
		kelintiniaiIvMotGim.put(16L, new Zodis("šešioliktoji", "šešioliktosios", "šešioliktajai", "šešioliktąją", "šešioliktąja", "šešioliktojoje", "šešioliktoji", "šešioliktosios", "šešioliktųjų", "šešioliktosioms", "šešioliktąsias", "šešioliktosiomis", "šešioliktosiose", "šešioliktosios"));
		kelintiniaiIvMotGim.put(17L, new Zodis("septynioliktoji", "septynioliktosios", "septynioliktajai", "septynioliktąją", "septynioliktąja", "septynioliktojoje", "septynioliktoji", "septynioliktosios", "septynioliktųjų", "septynioliktosioms", "septynioliktąsias", "septynioliktosiomis", "septynioliktosiose", "septynioliktosios"));
		kelintiniaiIvMotGim.put(18L, new Zodis("aštuonioliktoji", "aštuonioliktosios", "aštuonioliktajai", "aštuonioliktąją", "aštuonioliktąja", "aštuonioliktojoje", "aštuonioliktoji", "aštuonioliktosios", "aštuonioliktųjų", "aštuonioliktosioms", "aštuonioliktąsias", "aštuonioliktosiomis", "aštuonioliktosiose", "aštuonioliktosios"));
		kelintiniaiIvMotGim.put(19L, new Zodis("devynioliktoji", "devynioliktosios", "devynioliktajai", "devynioliktąją", "devynioliktąja", "devynioliktojoje", "devynioliktoji", "devynioliktosios", "devynioliktųjų", "devynioliktosioms", "devynioliktąsias", "devynioliktosiomis", "devynioliktosiose", "devynioliktosios"));
		kelintiniaiIvMotGim.put(20L, new Zodis("dvidešimtoji", "dvidešimtosios", "dvidešimtajai", "dvidešimtąją", "dvidešimtąja", "dvidešimtojoje", "dvidešimtoji", "dvidešimtosios", "dvidešimtųjų", "dvidešimtosioms", "dvidešimtąsias", "dvidešimtosiomis", "dvidešimtojoje", "dvidešimtosios"));		
		kelintiniaiIvMotGim.put(30L, new Zodis("trisdešimtoji", "trisdešimtosios", "trisdešimtajai", "trisdešimtąją", "trisdešimtąja", "trisdešimtojoje", "trisdešimtoji", "trisdešimtosios", "trisdešimtųjų", "trisdešimtosioms", "trisdešimtąsias", "trisdešimtosiomis", "trisdešimtojoje", "trisdešimtosios"));
		kelintiniaiIvMotGim.put(40L, new Zodis("keturiasdešimtoji", "keturiasdešimtosios", "keturiasdešimtajai", "keturiasdešimtąją", "keturiasdešimtąja", "keturiasdešimtojoje", "keturiasdešimtoji", "keturiasdešimtosios", "keturiasdešimtųjų", "keturiasdešimtosioms", "keturiasdešimtąsias", "keturiasdešimtosiomis", "keturiasdešimtojoje", "keturiasdešimtosios"));
		kelintiniaiIvMotGim.put(50L, new Zodis("penkiasdešimtoji", "penkiasdešimtosios", "penkiasdešimtajai", "penkiasdešimtąją", "penkiasdešimtąja", "penkiasdešimtojoje", "penkiasdešimtoji", "penkiasdešimtosios", "penkiasdešimtųjų", "penkiasdešimtosioms", "penkiasdešimtąsias", "penkiasdešimtosiomis", "penkiasdešimtojoje", "penkiasdešimtosios"));
		kelintiniaiIvMotGim.put(60L, new Zodis("šešiasdešimtoji", "šešiasdešimtosios", "šešiasdešimtajai", "šešiasdešimtąją", "šešiasdešimtąja", "šešiasdešimtojoje", "šešiasdešimtoji", "šešiasdešimtosios", "šešiasdešimtųjų", "šešiasdešimtosioms", "šešiasdešimtąsias", "šešiasdešimtosiomis", "šešiasdešimtojoje", "šešiasdešimtosios"));
		kelintiniaiIvMotGim.put(70L, new Zodis("septyniasdešimtoji", "septyniasdešimtosios", "septyniasdešimtajai", "septyniasdešimtąją", "septyniasdešimtąja", "septyniasdešimtojoje", "septyniasdešimtoji", "septyniasdešimtosios", "septyniasdešimtųjų", "septyniasdešimtosioms", "septyniasdešimtąsias", "septyniasdešimtosiomis", "septyniasdešimtojoje", "septyniasdešimtosios"));
		kelintiniaiIvMotGim.put(80L, new Zodis("aštuoniasdešimtoji", "aštuoniasdešimtosios", "aštuoniasdešimtajai", "aštuoniasdešimtąją", "aštuoniasdešimtąja", "aštuoniasdešimtojoje", "aštuoniasdešimtoji", "aštuoniasdešimtosios", "aštuoniasdešimtųjų", "aštuoniasdešimtosioms", "aštuoniasdešimtąsias", "aštuoniasdešimtosiomis", "aštuoniasdešimtojoje", "aštuoniasdešimtosios"));
		kelintiniaiIvMotGim.put(90L, new Zodis("devyniasdešimtoji", "devyniasdešimtosios", "devyniasdešimtajai", "devyniasdešimtąją", "devyniasdešimtąja", "devyniasdešimtojoje", "devyniasdešimtoji", "devyniasdešimtosios", "devyniasdešimtųjų", "devyniasdešimtosioms", "devyniasdešimtąsias", "devyniasdešimtosiomis", "devyniasdešimtojoje", "devyniasdešimtosios"));
		kelintiniaiIvMotGim.put(100L, new Zodis("šimtoji", "šimtosios", "šimtajai", "šimtąją", "šimtąja", "šimtojoje", "šimtoji", "šimtosios", "šimtųjų", "šimtosioms", "šimtąsias", "šimtosiomis", "šimtojoje", "šimtosios"));		
		kelintiniaiIvMotGim.put(1000L, new Zodis("tūkstantoji", "tūkstantosios", "tūkstantajai", "tūkstantąją", "tūkstantąja", "tūkstantojoje", "tūkstantoji", "tūkstantosios", "tūkstantųjų", "tūkstantosioms", "tūkstantąsias", "tūkstantosiomis", "tūkstantojoje", "tūkstantosios"));
		kelintiniaiIvMotGim.put(1000000L, new Zodis("milijonoji", "milijonosios", "milijonajai", "milijonąją", "milijonąja", "milijonojoje", "milijonoji", "milijonosios", "milijonųjų", "milijonosioms", "milijonąsias", "milijonosiomis", "milijonojoje", "milijonosios"));
		kelintiniaiIvMotGim.put(1000000000L, new Zodis("milijardoji", "milijardosios", "milijardajai", "milijardąją", "milijardąja", "milijardojoje", "milijardoji", "milijardosios", "milijardųjų", "milijardosioms", "milijardąsias", "milijardosiomis", "milijardojoje", "milijardosios"));
	}
	
	public static Zodis getPagrindinis(BigInteger skaicius, Gimine gimine) {
		if (gimine == Gimine.V) {
			return pagrindiniaiVyrGim.get(skaicius);
		} else {
			return pagrindiniaiMotGim.get(skaicius);
		}
		
	}	
	
	public static Zodis getKuopinis(BigInteger skaicius) {
		Zodis z = kuopiniai.get(skaicius);
		if (z == null) {
			throw new IllegalArgumentException("Illegal argument " + skaicius);
		}
		if (skaicius.equals(BigInteger.ONE)) {
			logger.warning(z + " naudojamas kaip kuopinis skaitvardis");
			// žr. http://ualgiman.dtiltas.lt/skaitvardis.html, kodėl nelabai tinka
		}
		return z;
	}
	
	public static Zodis getDauginis(BigInteger skaicius, Gimine gimine) {
		if (gimine == Gimine.V) {
			return dauginiaiVyrGim.get(skaicius);
		} else {
			return dauginiaiMotGim.get(skaicius);
		}
	}	
	
	public static Zodis getKelintinis(BigInteger skaicius, Gimine gimine, boolean ivardziuotinis) {
		if (gimine == Gimine.M) {
			if (ivardziuotinis) {
				return kelintiniaiIvMotGim.get(skaicius);
			} else {
				return kelintiniaiMotGim.get(skaicius);
			}
		} else {
			if (ivardziuotinis) {
				return kelintiniaiIvVyrGim.get(skaicius);
			} else {
				return kelintiniaiVyrGim.get(skaicius);
			}
		}
	}	
	public static Zodis getKelintinisIv(BigInteger skaicius, Gimine gimine) {
		if (gimine == Gimine.M) {
			return kelintiniaiIvMotGim.get(skaicius);
		} else {
			return kelintiniaiIvVyrGim.get(skaicius);
		}
	}	
}
