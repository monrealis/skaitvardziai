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
	private static final Map<Long, Zodis> kelintiniaiMotGim = new HashMap<Long, Zodis>();
	private static final Map<Long, Zodis> kelintiniaiIvMotGim = new HashMap<Long, Zodis>();
	
	
	
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
		
		// TODO perskaityti
		kelintiniai.put(0L, new Zodis("nulinis", "nulinio", "nuliniui", "nulinį", "nuliniu", "nulinyje", "nulini", "nuliniai", "nulinių", "nuliniams", "nulinius", "nuliniais", "nuliniuose", "nuliniai"));
		kelintiniai.put(1L, new Zodis("pirmas", "pirmo", "pirmam", "pirmą", "pirmu", "pirmame", "pirmas", "pirmi", "pirmų", "pirmiems", "pirmus", "pirmais", "pirmuose", "pirmi"));
		kelintiniai.put(2L, new Zodis("antras", "antro", "antram", "antrą", "antru", "antrame", "antras", "antri", "antrų", "antriems", "antrus", "antrais", "antruose", "antri"));
		kelintiniai.put(3L, new Zodis("trečias", "trečio", "trečiam", "trečią", "trečiu", "trečiame", "trečias", "treti", "trečių", "trečiems", "trečius", "trečiais", "trečiuose", "treti"));
		kelintiniai.put(4L, new Zodis("ketvirtas", "ketvirto", "ketvirtam", "ketvirtą", "ketvirtu", "ketvirtame", "ketvirtas", "ketvirti", "ketvirtų", "ketvirtiems", "ketvirtus", "ketvirtais", "ketvirtuose", "ketvirti"));
		kelintiniai.put(5L, new Zodis("penktas", "penkto", "penktam", "penktą", "penktu", "penktame", "penktas", "penkti", "penktų", "penktiems", "penktus", "penktais", "penktuose", "penkti"));
		kelintiniai.put(6L, new Zodis("šeštas", "šešto", "šeštam", "šeštą", "šeštu", "šeštame", "šeštas", "šešti", "šeštų", "šeštiems", "šeštus", "šeštiems", "šeštuose", "šešti"));
		kelintiniai.put(7L, new Zodis("septintas", "septinto", "septintam", "septintą", "septintu", "septintame", "septintas", "septinti", "septintų", "septintiems", "septintus", "septintiems", "septintuose", "septinti"));
		kelintiniai.put(8L, new Zodis("aštuntas", "aštunto", "aštuntam", "aštuntą", "aštuntu", "aštuntame", "aštuntas", "aštunti", "aštuntų", "aštuntiems", "aštuntus", "aštuntais", "aštuntuose", "aštunti"));
		kelintiniai.put(9L, new Zodis("devintas", "devinto", "devintam", "devintą", "devintu", "devintame", "devintas", "devinti", "devintų", "devintiems", "devintus", "devintais", "devintuose", "devinti"));
		kelintiniai.put(10L, new Zodis("dešimtas", "dešimto", "dešimtam", "dešimtą", "dešimtu", "dešimtame", "dešimtas", "dešimti", "dešimtų", "dešimtiems", "dešimtus", "dešimtais", "dešimtuose", "dešimti"));
		kelintiniai.put(11L, new Zodis("vienuoliktas", "vienuolikto", "vienuoliktam", "vienuoliktą", "vienuoliktu", "vienuoliktame", "vienuoliktas", "vienuolikti", "vienuoliktų", "vienuoliktiems", "vienuoliktus", "vienuoliktais", "vienuoliktuose", "vienuolikti"));
		kelintiniai.put(12L, new Zodis("dvyliktas", "dvylikto", "dvyliktam", "dvyliktą", "dvyliktu", "dvyliktame", "dvyliktas", "dvylikti", "dvyliktų", "dvyliktiems", "dvyliktus", "dvyliktais", "dvyliktuose", "dvylikti"));
		kelintiniai.put(13L, new Zodis("tryliktas", "trylikto", "tryliktam", "tryliktą", "tryliktu", "tryliktame", "tryliktas", "trylikti", "tryliktų", "tryliktiems", "tryliktus", "tryliktais", "tryliktuose", "trylikti"));
		kelintiniai.put(14L, new Zodis("keturioliktas", "keturiolikto", "keturioliktam", "keturioliktą", "keturioliktais", "keturioliktuose", "keturioliktas", "keturiolikti", "keturioliktų", "keturioliktiems", "keturioliktus", "keturioliktais", "keturioliktuose", "keturiolikti"));
		kelintiniai.put(15L, new Zodis("penkioliktas", "penkiolikto", "penkioliktam", "penkioliktą", "penkioliktu", "penkioliktame", "penkioliktas", "penkiolikti", "penkioliktų", "penkioliktiems", "penkioliktus", "penkioliktais", "penkioliktuose", "penkiolikti"));
		kelintiniai.put(16L, new Zodis("šešioliktas", "šešiolikto", "šešioliktam", "šešioliktą", "šešioliktu", "šešioliktame", "šešioliktas", "šešiolikti", "šešioliktų", "šešioliktiems", "šešioliktus", "šešioliktais", "šešioliktuose", "šešiolikti"));
		kelintiniai.put(17L, new Zodis("septynioliktas", "septyniolikto", "septynioliktam", "septynioliktą", "septynioliktu", "septynioliktame", "septynioliktas", "septyniolikti", "septynioliktų", "septynioliktiems", "septynioliktus", "septynioliktais", "septynioliktuose", "septyniolikti"));
		kelintiniai.put(18L, new Zodis("aštuonioliktas", "aštuoniolikto", "aštuonioliktam", "aštuonioliktą", "aštuonioliktu", "aštuonioliktame", "aštuonioliktas", "aštuoniolikti", "aštuonioliktų", "aštuonioliktiems", "aštuonioliktus", "aštuonioliktais", "aštuonioliktuose", "aštuoniolikti"));
		kelintiniai.put(19L, new Zodis("devynioliktas", "devyniolikto", "devynioliktam", "devynioliktą", "devynioliktu", "devynioliktame", "devynioliktas", "devyniolikti", "devynioliktų", "devynioliktiems", "devynioliktus", "devynioliktais", "devynioliktuose", "devyniolikti"));
		kelintiniai.put(20L, new Zodis("dvidešimtas", "dvidešimto", "dvidešimtam", "dvidešimtą", "dvidešimtu", "dvidešimtame", "dvidešimtas", "dvidešimti", "dvidešimtų", "dvidešimtiems", "dvidešimtus", "dvidešimtais", "dvidešimtuose", "dvidešimti"));		
		kelintiniai.put(30L, new Zodis("trisdešimtas", "trisdešimto", "trisdešimtam", "trisdešimtą", "trisdešimtu", "trisdešimtame", "trisdešimtas", "trisdešimti", "trisdešimtų", "trisdešimtiems", "trisdešimtus", "trisdešimtais", "trisdešimtuose", "trisdešimti"));
		kelintiniai.put(40L, new Zodis("keturiasdešimtas", "keturiasdešimto", "keturiasdešimtam", "keturiasdešimtą", "keturiasdešimtu", "keturiasdešimtame", "keturiasdešimtas", "keturiasdešimti", "keturiasdešimtų", "keturiasdešimtiems", "keturiasdešimtus", "keturiasdešimtais", "keturiasdešimtuose", "keturiasdešimti"));
		kelintiniai.put(50L, new Zodis("penkiasdešimtas", "penkiasdešimto", "penkiasdešimtam", "penkiasdešimtą", "penkiasdešimtu", "penkiasdešimtame", "penkiasdešimtas", "penkiasdešimti", "penkiasdešimtų", "penkiasdešimtiems", "penkiasdešimtus", "penkiasdešimtais", "penkiasdešimtuose", "penkiasdešimti"));
		kelintiniai.put(60L, new Zodis("šešiasdešimtas", "šešiasdešimto", "šešiasdešimtam", "šešiasdešimtą", "šešiasdešimtu", "šešiasdešimtame", "šešiasdešimtas", "šešiasdešimti", "šešiasdešimtų", "šešiasdešimtiems", "šešiasdešimtus", "šešiasdešimtais", "šešiasdešimtuose", "šešiasdešimti"));
		kelintiniai.put(70L, new Zodis("septyniasdešimtas", "septyniasdešimto", "septyniasdešimtam", "septyniasdešimtą", "septyniasdešimtu", "septyniasdešimtame", "septyniasdešimtas", "septyniasdešimti", "septyniasdešimtų", "septyniasdešimtiems", "septyniasdešimtus", "septyniasdešimtais", "septyniasdešimtuose", "septyniasdešimti"));
		kelintiniai.put(80L, new Zodis("aštuoniasdešimtas", "aštuoniasdešimto", "aštuoniasdešimtam", "aštuoniasdešimtą", "aštuoniasdešimtu", "aštuoniasdešimtame", "aštuoniasdešimtas", "aštuoniasdešimti", "aštuoniasdešimtų", "aštuoniasdešimtiems", "aštuoniasdešimtus", "aštuoniasdešimtais", "aštuoniasdešimtuose", "aštuoniasdešimti"));
		kelintiniai.put(90L, new Zodis("devyniasdešimtas", "devyniasdešimto", "devyniasdešimtam", "devyniasdešimtą", "devyniasdešimtu", "devyniasdešimtame", "devyniasdešimtas", "devyniasdešimti", "devyniasdešimtų", "devyniasdešimtiems", "devyniasdešimtus", "devyniasdešimtais", "devyniasdešimtuose", "devyniasdešimti"));		
		
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
				
		kelintiniaiIv.put(1L, new Zodis("pirmasis", "pirmojo", "pirmajam", "pirmąjį", "pirmuoju", "pirmajame", "pirmasis", "pirmieji", "pirmųjų", "pirmiesiems", "pirmuosius", "pirmaisiais", "pirmuosiuose", "pirmieji"));
		kelintiniaiIv.put(2L, new Zodis("antrasis", "antrojo", "antrajam", "antrąjį", "antruoju", "antrajame", "antrasis", "antrieji", "antrųjų", "antriesiems", "antruosius", "antraisiais", "antruosiuose", "antrieji"));
		kelintiniaiIv.put(3L, new Zodis("trečiasis", "trečiojo", "trečiajam", "trečiąjį", "trečiuoju", "trečiajame", "trečiasis", "trečiieji", "trečiųjų", "trečiiesiems", "trečiuosius", "trečiaisiais", "trečiuosiuose", "trečieji"));
		kelintiniaiIv.put(4L, new Zodis("ketvirtasis", "ketvirtojo", "ketvirtajam", "ketvirtąjį", "ketvirtuoju", "ketvirtajame", "ketvirtasis", "ketvirtieji", "ketvirtųjų", "ketvirtiesiems", "ketvirtuosius", "ketvirtaisiais", "ketvirtuosiuose", "ketvirtieji"));
		kelintiniaiIv.put(5L, new Zodis("penktasis", "penktojo", "penktajam", "penktąjį", "penktuoju", "penktajame", "penktasis", "penktieji", "penktųjų", "penktiesiems", "penktuosius", "penktaisiais", "penktuosiuose", "penktieji"));
		kelintiniaiIv.put(6L, new Zodis("šeštasis", "šeštojo", "šeštajam", "šeštąjį", "šeštuoju", "šeštajame", "šeštasis", "šeštieji", "šeštųjų", "šeštiesiems", "šeštuosius", "šeštaisiais", "šeštuosiuose", "šeštieji"));
		kelintiniaiIv.put(7L, new Zodis("septintasis", "septintojo", "septintajam", "septintąjį", "septintuoju", "septintajame", "septintasis", "septintieji", "septintųjų", "septintiesiems", "septintuosius", "septintaisiais", "septintuosiuose", "septintieji"));
		kelintiniaiIv.put(8L, new Zodis("aštuntasis", "aštuntojo", "aštuntajam", "aštuntąjį", "aštuntuoju", "aštuntajame", "aštuntasis", "aštuntieji", "aštuntųjų", "aštuntiesiems", "aštuntuosius", "aštuntaisiais", "aštuntuosiuose", "aštuntieji"));
		kelintiniaiIv.put(9L, new Zodis("devintasis", "devintojo", "devintajam", "devintąjį", "devintuoju", "devintajame", "devintasis", "devintieji", "devintųjų", "devintiesiems", "devintuosius", "devintaisiais", "devintuosiuose", "devintieji"));
		kelintiniaiIv.put(10L, new Zodis("dešimtasis", "dešimtojo", "dešimtajam", "dešimtąjį", "dešimtuoju", "dešimtajame", "dešimtasis", "dešimtieji", "dešimtųjų", "dešimtiesiems", "dešimtuosius", "dešimtaisiais", "dešimtuosiuose", "dešimtieji"));
		kelintiniaiIv.put(11L, new Zodis("vienuoliktasis", "vienuoliktojo", "vienuoliktajam", "vienuoliktąjį", "vienuoliktuoju", "vienuoliktajame", "vienuoliktasis", "vienuoliktieji", "vienuoliktųjų", "vienuoliktiesiems", "vienuoliktuosius", "vienuoliktaisiais", "vienuoliktuosiuose", "vienuoliktieji"));
		kelintiniaiIv.put(12L, new Zodis("dvyliktasis", "dvyliktojo", "dvyliktajam", "dvyliktąjį", "dvyliktuoju", "dvyliktajame", "dvyliktasis", "dvyliktieji", "dvyliktųjų", "dvyliktiesiems", "dvyliktuosius", "dvyliktaisiais", "dvyliktuosiuose", "dvyliktieji"));
		kelintiniaiIv.put(13L, new Zodis("tryliktasis", "tryliktojo", "tryliktajam", "tryliktąjį", "tryliktuoju", "tryliktajame", "tryliktasis", "tryliktieji", "tryliktųjų", "tryliktiesiems", "tryliktuosius", "tryliktaisiais", "tryliktuosiuose", "tryliktieji"));
		kelintiniaiIv.put(14L, new Zodis("keturioliktasis", "keturioliktojo", "keturioliktajam", "keturioliktąjį", "keturioliktuoju", "keturioliktajame", "keturioliktasis", "keturioliktieji", "keturioliktųjų", "keturioliktiesiems", "keturioliktuosius", "keturioliktaisiais", "keturioliktuosiuose", "keturioliktieji"));
		kelintiniaiIv.put(15L, new Zodis("penkioliktasis", "penkioliktojo", "penkioliktajam", "penkioliktąjį", "penkioliktuoju", "penkioliktajame", "penkioliktasis", "penkioliktieji", "penkioliktųjų", "penkioliktiesiems", "penkioliktuosius", "penkioliktaisiais", "penkioliktuosiuose", "penkioliktieji"));
		kelintiniaiIv.put(16L, new Zodis("šešioliktasis", "šešioliktojo", "šešioliktajam", "šešioliktąjį", "šešioliktuoju", "šešioliktajame", "šešioliktasis", "šešioliktieji", "šešioliktųjų", "šešioliktiesiems", "šešioliktuosius", "šešioliktaisiais", "šešioliktuosiuose", "šešioliktieji"));
		kelintiniaiIv.put(17L, new Zodis("septynioliktasis", "septynioliktojo", "septynioliktajam", "septynioliktąjį", "septynioliktuoju", "septynioliktajame", "septynioliktasis", "septynioliktieji", "septynioliktųjų", "septynioliktiesiems", "septynioliktuosius", "septynioliktaisiais", "septynioliktuosiuose", "septynioliktieji"));
		kelintiniaiIv.put(18L, new Zodis("aštuonioliktasis", "aštuonioliktojo", "aštuonioliktajam", "aštuonioliktąjį", "aštuonioliktuoju", "aštuonioliktajame", "aštuonioliktasis", "aštuonioliktieji", "aštuonioliktųjų", "aštuonioliktiesiems", "aštuonioliktuosius", "aštuonioliktaisiais", "aštuonioliktuosiuose", "aštuonioliktieji"));
		kelintiniaiIv.put(19L, new Zodis("devynioliktasis", "devynioliktojo", "devynioliktajam", "devynioliktąjį", "devynioliktuoju", "devynioliktajame", "devynioliktasis", "devynioliktieji", "devynioliktųjų", "devynioliktiesiems", "devynioliktuosius", "devynioliktaisiais", "devynioliktuosiuose", "devynioliktieji"));
		kelintiniaiIv.put(20L, new Zodis("dvidešimtasis", "dvidešimtojo", "dvidešimtajam", "dvidešimtąjį", "dvidešimtuoju", "dvidešimtajame", "dvidešimtasis", "dvidešimtieji", "dvidešimtojo", "dvidešimtiesiems", "dvidešimtuosius", "dvidešimtaisiais", "dvidešimtuosiuose", "dvidešimtieji"));		
		kelintiniaiIv.put(30L, new Zodis("trisdešimtasis", "trisdešimtojo", "trisdešimtajam", "trisdešimtąjį", "trisdešimtuoju", "trisdešimtajame", "trisdešimtasis", "trisdešimtieji", "trisdešimtojo", "trisdešimtiesiems", "trisdešimtuosius", "trisdešimtaisiais", "trisdešimtuosiuose", "trisdešimtieji"));
		kelintiniaiIv.put(40L, new Zodis("keturiasdešimtasis", "keturiasdešimtojo", "keturiasdešimtajam", "keturiasdešimtąjį", "keturiasdešimtuoju", "keturiasdešimtajame", "keturiasdešimtasis", "keturiasdešimtieji", "keturiasdešimtojo", "keturiasdešimtiesiems", "keturiasdešimtuosius", "keturiasdešimtaisiais", "keturiasdešimtuosiuose", "keturiasdešimtieji"));
		kelintiniaiIv.put(50L, new Zodis("penkiasdešimtasis", "penkiasdešimtojo", "penkiasdešimtajam", "penkiasdešimtąjį", "penkiasdešimtuoju", "penkiasdešimtajame", "penkiasdešimtasis", "penkiasdešimtieji", "penkiasdešimtojo", "penkiasdešimtiesiems", "penkiasdešimtuosius", "penkiasdešimtaisiais", "penkiasdešimtuosiuose", "penkiasdešimtieji"));
		kelintiniaiIv.put(60L, new Zodis("šešiasdešimtasis", "šešiasdešimtojo", "šešiasdešimtajam", "šešiasdešimtąjį", "šešiasdešimtuoju", "šešiasdešimtajame", "šešiasdešimtasis", "šešiasdešimtieji", "šešiasdešimtojo", "šešiasdešimtiesiems", "šešiasdešimtuosius", "šešiasdešimtaisiais", "šešiasdešimtuosiuose", "šešiasdešimtieji"));
		kelintiniaiIv.put(70L, new Zodis("septyniasdešimtasis", "septyniasdešimtojo", "septyniasdešimtajam", "septyniasdešimtąjį", "septyniasdešimtuoju", "septyniasdešimtajame", "septyniasdešimtasis", "septyniasdešimtieji", "septyniasdešimtojo", "septyniasdešimtiesiems", "septyniasdešimtuosius", "septyniasdešimtaisiais", "septyniasdešimtuosiuose", "septyniasdešimtieji"));
		kelintiniaiIv.put(80L, new Zodis("aštuoniasdešimtasis", "aštuoniasdešimtojo", "aštuoniasdešimtajam", "aštuoniasdešimtąjį", "aštuoniasdešimtuoju", "aštuoniasdešimtajame", "aštuoniasdešimtasis", "aštuoniasdešimtieji", "aštuoniasdešimtojo", "aštuoniasdešimtiesiems", "aštuoniasdešimtuosius", "aštuoniasdešimtaisiais", "aštuoniasdešimtuosiuose", "aštuoniasdešimtieji"));
		kelintiniaiIv.put(90L, new Zodis("devyniasdešimtasis", "devyniasdešimtojo", "devyniasdešimtajam", "devyniasdešimtąjį", "devyniasdešimtuoju", "devyniasdešimtajame", "devyniasdešimtasis", "devyniasdešimtieji", "devyniasdešimtojo", "devyniasdešimtiesiems", "devyniasdešimtuosius", "devyniasdešimtaisiais", "devyniasdešimtuosiuose", "devyniasdešimtieji"));
		kelintiniaiIv.put(100L, new Zodis("šimtasis", "šimtojo", "šimtajam", "šimtąjį", "šimtuoju", "šimtajame", "šimtasis", "šimtieji", "šimtojo", "šimtiesiems", "šimtuosius", "šimtaisiais", "šimtuosiuose", "šimtieji"));		
		kelintiniaiIv.put(1000L, new Zodis("tūkstantasis", "tūkstantojo", "tūkstantajam", "tūkstantąjį", "tūkstantuoju", "tūkstantajame", "tūkstantasis", "tūkstantieji", "tūkstantojo", "tūkstantiesiems", "tūkstantuosius", "tūkstantaisiais", "tūkstantuosiuose", "tūkstantieji"));
		kelintiniaiIv.put(1000000L, new Zodis("milijonasis", "milijonojo", "milijonajam", "milijonąjį", "milijonuoju", "milijonajame", "milijonasis", "milijonieji", "milijonojo", "milijoniesiems", "milijonuosius", "milijonaisiais", "milijonuosiuose", "milijonieji"));
		kelintiniaiIv.put(1000000000L, new Zodis("milijardasis", "milijardojo", "milijardajam", "milijardąjį", "milijarduoju", "milijardajame", "milijardasis", "milijardieji", "milijardojo", "milijardiesiems", "milijarduosius", "milijardaisiais", "milijarduosiuose", "milijardieji"));
		
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
		kelintiniaiIvMotGim.put(19L, new Zodis("devynioliktoji", "devynioliktosios", "devynioliktajai", "devynioliktąją", "devynioliktąja", "devynioliktojoje", "devynioliktoji", "devynioliktosios", "devynioliktųkų", "devynioliktosioms", "devynioliktąsias", "devynioliktosiomis", "devynioliktosiose", "devynioliktosios"));
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
	
	public static Zodis getKelintinis(long skaicius) {
		return kelintiniai.get(skaicius);
	}	
	public static Zodis getKelintinisIv(long skaicius) {
		return kelintiniai.get(skaicius);
	}	
}
