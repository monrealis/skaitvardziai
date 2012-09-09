package eu.vytenis.skaiciai.esybes;

import java.util.HashMap;
import java.util.Map;

public class Zodis {

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
		
		skaiciaiMotGim.put(1L, new Zodis("viena", "vienos", "vienai", "vieną", "viena", "vienoje", "viena"));
		skaiciaiMotGim.put(2L, new Zodis("dvi", "dviejų", "dviems", "dvi", "dviem", "dviejose", "dvi").kitasDgsVard());
		skaiciaiMotGim.put(3L, new Zodis("trys", "trijų", "trims", "tris", "trimis", "trijose", "trys").kitasDgsVard());
		skaiciaiMotGim.put(4L, new Zodis("keturios", "keturių", "keturioms", "keturias", "keturiomis", "keturiose", "keturios").kitasDgsVard());
		skaiciaiMotGim.put(5L, new Zodis("penkios", "penkių", "penkioms", "penkias", "penkiomis", "penkiose", "penkios").kitasDgsVard());
		skaiciaiMotGim.put(6L, new Zodis("šešios", "šešių", "šešioms", "šešias", "šešiomis", "šešiose", "šešios").kitasDgsVard());
		skaiciaiMotGim.put(7L, new Zodis("septynios", "septynių", "septynioms", "septynias", "septyniomis", "septyniose", "septynios").kitasDgsVard());
		skaiciaiMotGim.put(8L, new Zodis("aštuonios", "aštuonių", "aštuonioms", "aštuonias", "aštuoniomis", "aštuoniose", "aštuonios").kitasDgsVard());
		skaiciaiMotGim.put(9L, new Zodis("devynios", "devynių", "devynioms", "devynias", "devyniomis", "devyniose", "devynios").kitasDgsVard());
		
		skaiciai.put(0L, new Zodis("nulis", "nulio", "nuliui", "nulį", "nuliu", "nulyje", "nuli").kitasDgsKilm());		
		skaiciai.put(1L, new Zodis("vienas", "vieno", "vienam", "vieną", "vienu", "viename", "vienas"));		
		skaiciai.put(2L, new Zodis("du", "dviejų", "dviems", "du", "dviem", "dviejuose", "du").kitasDgsVard());		
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
	}
	
	public static Zodis get(long skaicius) {
		return skaiciai.get(skaicius);
	}	

}
