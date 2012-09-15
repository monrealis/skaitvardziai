package eu.vytenis.skaiciai.esybes;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class Zodis {

	private static final Logger logger = Logger.getLogger(Zodis.class.getName());
	
	private Map<Linksnis, String> vienaskaita = new HashMap<Linksnis, String>();
	private Map<Linksnis, String> daugiskaita = new HashMap<Linksnis, String>();
	
	private boolean kitasVns = true;
	private Linksnis kitasLinksnis = Linksnis.V;
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
	
	private static final Map<Long, Zodis> skaiciaiMotGim = new HashMap<Long, Zodis>();
	private static final Map<Long, Zodis> skaiciai = new HashMap<Long, Zodis>();
	
	private static final Map<Long, Zodis> kuopiniai = new HashMap<Long, Zodis>();
	
	private static final Map<Long, Zodis> dauginiaiMotGim = new HashMap<Long, Zodis>();
	private static final Map<Long, Zodis> dauginiai = new HashMap<Long, Zodis>();
	
	private static final Map<Long, Zodis> kelintiniai = new HashMap<Long, Zodis>();
	private static final Map<Long, Zodis> kelintiniaiIv = new HashMap<Long, Zodis>(); // įvardžiuotinės formos
	
	
	
	public Zodis() {
		
	}
	
	public Zodis(String vnsV) {
		vienaskaita.put(Linksnis.V, vnsV);
	}
	
	public Zodis(String vnsV, String dgsV) {
		vienaskaita.put(Linksnis.V, vnsV);
		daugiskaita.put(Linksnis.V, dgsV);
	}
	
	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS) {
		vienaskaita.put(Linksnis.V, vnsV);
		vienaskaita.put(Linksnis.K, vnsK);
		vienaskaita.put(Linksnis.N, vnsN);
		vienaskaita.put(Linksnis.G, vnsG);
		vienaskaita.put(Linksnis.I, vnsI);
		vienaskaita.put(Linksnis.Vt, vnsVt);
		vienaskaita.put(Linksnis.S, vnsS);
	}
	
	public Zodis(String vnsV, String vnsK, String vnsN, String vnsG, String vnsI, String vnsVt, String vnsS,
			String dgsV, String dgsK, String dgsN, String dgsG, String dgsI, String dgsVt, String dgsS) {
		vienaskaita.put(Linksnis.V, vnsV);
		vienaskaita.put(Linksnis.K, vnsK);
		vienaskaita.put(Linksnis.N, vnsN);
		vienaskaita.put(Linksnis.G, vnsG);
		vienaskaita.put(Linksnis.I, vnsI);
		vienaskaita.put(Linksnis.Vt, vnsVt);
		vienaskaita.put(Linksnis.S, vnsS);
		
		daugiskaita.put(Linksnis.V, dgsV);
		daugiskaita.put(Linksnis.K, dgsK);
		daugiskaita.put(Linksnis.N, dgsN);
		daugiskaita.put(Linksnis.G, dgsG);
		daugiskaita.put(Linksnis.I, dgsI);
		daugiskaita.put(Linksnis.Vt, dgsVt);
		daugiskaita.put(Linksnis.S, dgsS);
	}
	
	public String getVienaskaita(Linksnis linksnis) {
		return vienaskaita.get(linksnis);
	}
	
	
	public boolean isKitasVns() {
		return kitasVns;
	}

	public void setKitasVns(boolean kitasVns) {
		this.kitasVns = kitasVns;
	}

	public Linksnis getKitasLinksnis() {
		return kitasLinksnis;
	}

	public void setKitasLinksnis(Linksnis kitasLinksnis) {
		this.kitasLinksnis = kitasLinksnis;
	}
	
	public Zodis kitas(boolean kitasVns, Linksnis kitasLinksnis) {
		setKitasVns(kitasVns);
		setKitasLinksnis(kitasLinksnis);
		return this;
	}
	
	public Zodis kitasDgsVard() {
		return kitas(false, Linksnis.V);
	}
	public Zodis kitasDgsKilm() {
		return kitas(false, Linksnis.K);
	}
	
	public boolean isValdomas() {
		return valdomas;
	}
	
	public void setValdomas(boolean valdomas) {
		this.valdomas = valdomas;
	}
	
	public Zodis valdomas() {
		setValdomas(true);
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
	

	public String toString(boolean vns) {
		return (vns ? vienaskaita : daugiskaita).get(Linksnis.V);
	}
	
	public String toString(boolean vns, Linksnis linksnis) {
		return (vns ? vienaskaita : daugiskaita).get(linksnis);
	}
	
	public String toString() {
		return getVienaskaita(Linksnis.V);
	}



	static {
		// TODO moteriška giminė (pvz., penki ir penkios)
		// TODO žodžio konstruktorius kviečiamas, perduodant vienaskaitines reikšmes, nors iš tikrųjų yra daugiskaitinės
		
		skaiciaiMotGim.put(1L, new Zodis("viena", "vienos", "vienai", "vieną", "viena", "vienoje", "viena", "vienos", "vienų", "vienoms", "vienas", "vienomis", "vienose", "vienos")); // "vieni" - daugiau būdvardis, žr. http://ualgiman.dtiltas.lt/skaitvardis.html
		skaiciaiMotGim.put(2L, new Zodis("dvi", "dviejų", "dviems", "dvi", "dviem", "dviejose", "dvi").kitasDgsVard());
		skaiciaiMotGim.put(3L, new Zodis("trys", "trijų", "trims", "tris", "trimis", "trijose", "trys").kitasDgsVard());
		skaiciaiMotGim.put(4L, new Zodis("keturios", "keturių", "keturioms", "keturias", "keturiomis", "keturiose", "keturios").kitasDgsVard());
		skaiciaiMotGim.put(5L, new Zodis("penkios", "penkių", "penkioms", "penkias", "penkiomis", "penkiose", "penkios").kitasDgsVard());
		skaiciaiMotGim.put(6L, new Zodis("šešios", "šešių", "šešioms", "šešias", "šešiomis", "šešiose", "šešios").kitasDgsVard());
		skaiciaiMotGim.put(7L, new Zodis("septynios", "septynių", "septynioms", "septynias", "septyniomis", "septyniose", "septynios").kitasDgsVard());
		skaiciaiMotGim.put(8L, new Zodis("aštuonios", "aštuonių", "aštuonioms", "aštuonias", "aštuoniomis", "aštuoniose", "aštuonios").kitasDgsVard());
		skaiciaiMotGim.put(9L, new Zodis("devynios", "devynių", "devynioms", "devynias", "devyniomis", "devyniose", "devynios").kitasDgsVard());
		
		skaiciai.put(0L, new Zodis("nulis", "nulio", "nuliui", "nulį", "nuliu", "nulyje", "nuli").kitasDgsKilm());		
		skaiciai.put(1L, new Zodis("vienas", "vieno", "vienam", "vieną", "vienu", "viename", "vienas", "vieni", "vienų", "vieniems", "vienus", "vienais", "vienuose", "vieni")); // "vieni" - daugiau būdvardis, žr. http://ualgiman.dtiltas.lt/skaitvardis.html		
		skaiciai.put(2L, new Zodis("du", "dviejų", "dviem" /* geriau nei "dviems" */, "du", "dviem", "dviejuose", "du").kitasDgsVard());		
		skaiciai.put(3L, new Zodis("trys", "trijų", "trims", "tris", "trimis", "trijuose", "trys").kitasDgsVard());		
		skaiciai.put(4L, new Zodis("keturi", "keturių", "keturiems", "keturis", "keturiais", "keturiuose", "keturi").kitasDgsVard());		
		skaiciai.put(5L, new Zodis("penki", "penkių", "penkiems", "penkis", "penkiais", "penkiuose", "penki").kitasDgsVard());		
		skaiciai.put(6L, new Zodis("šeši", "šešių", "šešiems", "šešis", "šešiais", "šešiuose", "šeši").kitasDgsVard());		
		skaiciai.put(7L, new Zodis("septyni", "septynių", "septyniems", "septynis", "septyniais", "septyniuose", "septyni").kitasDgsVard());		
		skaiciai.put(8L, new Zodis("aštuoni", "aštuonių", "aštuoniems", "aštuonis", "aštuoniais", "aštuoniuose", "aštuoni").kitasDgsVard());		
		skaiciai.put(9L, new Zodis("devyni", "devynių", "devyniems", "devynis", "devyniais", "devyniuose", "devyni").kitasDgsVard());
		skaiciai.put(10L, new Zodis("dešimt", "dešimties", "dešimčiai", "dešimt", "dešimčia", "dešimtyje", "dešimt").kitasDgsKilm());	// http://ualgiman.dtiltas.lt/skaitvardis.html
		skaiciai.put(11L, new Zodis("vienuolika", "vienuolikos", "vienuolikai", "vienuolika", "vienuolika", "vienuolikoje", "vienuolika").kitasDgsKilm());
		skaiciai.put(12L, new Zodis("dvylika", "dvylikos", "dvylikai", "dvylika", "dvylika", "dvylikoje", "dvylika").kitasDgsKilm());
		skaiciai.put(13L, new Zodis("trylika", "trylikos", "trylikai", "trylika", "trylika", "trylikoje", "trylika").kitasDgsKilm());
		skaiciai.put(14L, new Zodis("keturiolika", "keturiolikos", "keturiolikai", "keturiolika", "keturiolika", "keturiolikoje", "keturiolika").kitasDgsKilm());
		skaiciai.put(15L, new Zodis("penkiolika", "penkiolikos", "penkiolikai", "penkiolika", "penkiolika", "penkiolikoje", "penkiolika").kitasDgsKilm());
		skaiciai.put(16L, new Zodis("šešiolika", "šešiolikos", "šešiolikai", "šešiolika", "šešiolika", "šešiolikoje", "šešiolika").kitasDgsKilm());
		skaiciai.put(17L, new Zodis("septyniolika", "septyniolikos", "septyniolikai", "septyniolika", "septyniolika", "septyniolikoje", "septyniolika").kitasDgsKilm());
		skaiciai.put(18L, new Zodis("aštuoniolika", "aštuoniolikos", "aštuoniolikai", "aštuoniolika", "aštuoniolika", "aštuoniolikoje", "aštuoniolika").kitasDgsKilm());
		skaiciai.put(19L, new Zodis("devyniolika", "devyniolikos", "devyniolikai", "devyniolika", "devyniolika", "devyniolikoje", "devyniolika").kitasDgsKilm());
		skaiciai.put(20L, new Zodis("dvidešimt", "dvidešimties", "dvidešimčiai", "dvidešimt", "dvidešimčia", "dvidešimtyje", "dvidešimt").kitasDgsKilm().nekaitomasLinksniuojant());		
		skaiciai.put(30L, new Zodis("trisdešimt", "trisdešimties", "trisdešimčiai", "trisdešimt", "trisdešimčia", "trisdešimtyje", "trisdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(40L, new Zodis("keturiasdešimt", "keturiasdešimties", "keturiasdešimčiai", "keturiasdešimt", "keturiasdešimčia", "keturiasdešimtyje", "keturiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(50L, new Zodis("penkiasdešimt", "penkiasdešimties", "penkiasdešimčiai", "penkiasdešimt", "penkiasdešimčia", "penkiasdešimtyje", "penkiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(60L, new Zodis("šešiasdešimt", "šešiasdešimties", "šešiasdešimčiai", "šešiasdešimt", "šešiasdešimčia", "šešiasdešimtyje", "šešiasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(70L, new Zodis("septyniasdešimt", "septyniasdešimties", "septyniasdešimčiai", "septyniasdešimt", "septyniasdešimčia", "septyniasdešimtyje", "septyniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(80L, new Zodis("aštuoniasdešimt", "aštuoniasdešimties", "aštuoniasdešimčiai", "aštuoniasdešimt", "aštuoniasdešimčia", "aštuoniasdešimtyje", "aštuoniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());
		skaiciai.put(90L, new Zodis("devyniasdešimt", "devyniasdešimties", "devyniasdešimčiai", "devyniasdešimt", "devyniasdešimčia", "devyniasdešimtyje", "devyniasdešimt").kitasDgsKilm().nekaitomasLinksniuojant());		
		skaiciai.put(100L, new Zodis("šimtas", "šimto", "šimtui", "šimtą", "šimtu", "šimte", "šimte", "šimtai", "šimtų", "šimtams", "šimtus", "šimtais", "šimtuose", "šimtai").kitasDgsKilm().valdomas());		
		skaiciai.put(1000L, new Zodis("tūkstantis", "tūkstančio", "tūkstančiui", "tūkstantį", "tūkstančiu", "tūkstantyje", "tūkstanti", "tūkstančiai", "tūkstančių", "tūkstančiams", "tūkstančius", "tūkstančiais", "tūkstančiuose", "tūkstančiai").kitasDgsKilm().valdomas());
		skaiciai.put(1000000L, new Zodis("milijonas", "milijono", "milijonui", "milijoną", "milijonu", "milijone", "milijone", "milijonai", "milijonų", "milijonams", "milijonus", "milijonais", "milijonuose", "milijonai").kitasDgsKilm().valdomas());
		skaiciai.put(1000000000L, new Zodis("milijardas", "milijardo", "milijardui", "milijardą", "milijardu", "milijarde", "milijarde", "milijardai", "milijardų", "milijardams", "milijardus", "milijardais", "milijarduose", "milijardai").kitasDgsKilm().valdomas());
		
		kuopiniai.put(1L, new Zodis("vienetas", "vieneto", "vienetui", "vienetą", "vienetu", "vienete", "vienete")); // Ne visai tinka, bet tegul būna
		kuopiniai.put(2L, new Zodis("dvejetas", "dvejeto", "dvejetui", "dvejetą", "dvejetu", "dvejete", "dvejete"));
		kuopiniai.put(3L, new Zodis("trejetas", "trejeto", "trejetui", "trejetą", "trejetu", "trejete", "trejete"));
		kuopiniai.put(4L, new Zodis("ketvertas", "ketverto", "ketvertui", "ketvertą", "ketvertu", "ketverte", "ketverte"));
		kuopiniai.put(5L, new Zodis("penketas", "penketo", "penketui", "penketą", "penketu", "penkete", "penkete"));
		kuopiniai.put(6L, new Zodis("šešetas", "šešeto", "šešetui", "šešetą", "šešetu", "šešete", "šešete"));
		kuopiniai.put(7L, new Zodis("septynetas", "septyneto", "septynetui", "septynetą", "septynetu", "septynete", "septynete"));
		kuopiniai.put(8L, new Zodis("aštuonetas", "aštuoneto", "aštuonetui", "aštuonetą", "aštuonetu", "aštuonete", "aštuonete"));
		kuopiniai.put(9L, new Zodis("devynetas", "devyneto", "devynetui", "devynetą", "devynetu", "devynete", "devynete"));
		
		dauginiaiMotGim.put(1L, new Zodis("vienerios", "vienerių", "vienerioms", "vienerias", "vieneriomis", "vieneriose", "vienerios")); // http://ualgiman.dtiltas.lt/skaitvardis.html
		dauginiaiMotGim.put(2L, new Zodis("dvejos", "dvejų", "dvejoms", "dvejas", "dvejomis", "dvejose", "dvejos"));
		dauginiaiMotGim.put(3L, new Zodis("trejos", "trejų", "trejoms", "trejas", "trejomis", "trejose", "trejos"));
		dauginiaiMotGim.put(4L, new Zodis("ketverios", "ketverių", "ketverioms", "ketverias", "ketveriomis", "ketveriose", "ketverios"));
		dauginiaiMotGim.put(5L, new Zodis("penkerios", "penkerių", "penkerioms", "penkerias", "penkeriomis", "penkeriose", "penkerios"));
		dauginiaiMotGim.put(6L, new Zodis("šešerios", "šešerių", "šešerioms", "šešerias", "šešeriomis", "šešeriose", "šešerios"));
		dauginiaiMotGim.put(7L, new Zodis("septynerios", "septynerių", "septynerioms", "septynerias", "septyneriomis", "septyneriose", "septynerios"));
		dauginiaiMotGim.put(8L, new Zodis("aštuonerios", "aštuonerių", "aštuonerioms", "aštuonerias", "aštuoneriomis", "aštuoneriose", "aštuonerios"));
		dauginiaiMotGim.put(9L, new Zodis("devynerios", "devynerių", "devynerioms", "devynerias", "devyneriomis", "devyneriose", "devynerios"));
		
		dauginiai.put(1L, new Zodis("vieneri", "vienerių", "vieneriems", "vienerius", "vieneriais", "vieneriuose", "vieneri")); // http://ualgiman.dtiltas.lt/skaitvardis.html
		dauginiai.put(2L, new Zodis("dveji", "dvejų", "dvejiems", "dvejus", "dvejais", "dvejuose", "dveji"));
		dauginiai.put(3L, new Zodis("treji", "trejų", "trejiems", "trejus", "trejais", "trejuose", "treji"));
		dauginiai.put(4L, new Zodis("ketveri", "ketverių", "ketveriems", "ketverius", "ketveriais", "ketveriuose", "ketveri"));
		dauginiai.put(5L, new Zodis("penkeri", "penkerių", "penkeriems", "penkerius", "penkeriais", "penkeriuose", "penkeri"));
		dauginiai.put(6L, new Zodis("šešeri", "šešerių", "šešeriems", "šešerius", "šešeriais", "šešeriuose", "šešeri"));
		dauginiai.put(7L, new Zodis("septyneri", "septynerių", "septyneriems", "septynerius", "septyneriais", "septyneriuose", "septyneri"));
		dauginiai.put(8L, new Zodis("aštuoneri", "aštuonerių", "aštuoneriems", "aštuonerius", "aštuoneriais", "aštuoneriuose", "aštuoneri"));
		dauginiai.put(9L, new Zodis("devyneri", "devynerių", "devyneriems", "devynerius", "devyneriais", "devyneriuose", "devyneri"));
		
		// TODO
		kelintiniai.put(0L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(1L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(2L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(3L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(4L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(5L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(6L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(7L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(8L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(9L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(10L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(11L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(12L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(13L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(14L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(15L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(16L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(17L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(18L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniai.put(19L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		
		kelintiniaiIv.put(0L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(1L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(2L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(3L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(4L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(5L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(6L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(7L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(8L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(9L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(10L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(11L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(12L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(13L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(14L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(15L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(16L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(17L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(18L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
		kelintiniaiIv.put(19L, new Zodis("", "", "", "", "", "", "", "", "", "", "", "", "", ""));
	}
	
	public static Zodis getPagrindinis(long skaicius) {
		return skaiciai.get(skaicius);
	}	
	
	public static Zodis getKuopinis(long skaicius) {
		Zodis z = kuopiniai.get(skaicius);
		if (z == null) {
			throw new IllegalArgumentException("Illegal argument " + skaicius);
		}
		if (skaicius == 1) {
			logger.warning(z + " naudojamas kaip kuopinis skaitvardis");
		}
		return z;
	}
	
	public static Zodis getDauginis(long skaicius) {
		return dauginiai.get(skaicius);
	}	
}
