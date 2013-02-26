package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;

/**
 * Struktūra, apibūdinanti gramatinę skaitvardžio formą (poskyris, linksnis, giminė, skaičius).
 *
 */
public class Forma implements Cloneable, UnmodifiableCapable {
	private static final Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> fieldHandlers;
	static {
		Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> h
			= new HashMap<Class<? extends FormosElementas>, Forma.FormosElementasFieldHandler<? extends FormosElementas>>();
		h.put(Linksnis.class, new LinksnisHandler());
		h.put(Skaicius.class, new SkaiciusHandler());
		h.put(Gimine.class, new GimineHandler());
		h.put(Poskyris.class, new PoskyrisHandler());
		h.put(Rusis.class, new RusisHandler());
		
		fieldHandlers = Collections.unmodifiableMap(h);
		
	}
	private Poskyris poskyris = Poskyris.Pagrindinis;
	private Gimine gimine = Gimine.V;
	private Skaicius skaicius = Skaicius.V;
	private Linksnis linksnis = Linksnis.V;
	private Rusis rusis = Rusis.P;
	private boolean unmodifiable;
	
	public Poskyris getPoskyris() {
		return poskyris;
	}
	public void setPoskyris(Poskyris poskyris) {
		this.poskyris = poskyris;
	}
	public Gimine getGimine() {
		return gimine;
	}
	public void setGimine(Gimine gimine) {
		this.gimine = gimine;
	}
	public Skaicius getSkaicius() {
		return skaicius;
	}
	public void setSkaicius(Skaicius skaicius) {
		this.skaicius = skaicius;
	}
	public Linksnis getLinksnis() {
		return linksnis;
	}
	public void setLinksnis(Linksnis linksnis) {
		this.linksnis = linksnis;
	}
	
	public SkaiciusIrLinksnis toSkaiciusLinksnis() {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}
	
	public Rusis getRusis() {
		return rusis;
	}
	public void setRusis(Rusis rusis) {
		this.rusis = rusis;
	}	
	public boolean isUnmodifiable() {
		return unmodifiable;
	}	
	public void setUnmodifiable(boolean unmodifiable) {
		this.unmodifiable = unmodifiable;
	}	
	
	@SuppressWarnings("unchecked")
	public  <T extends FormosElementas>  void setElementas(T elementas) {
		FormosElementasFieldHandler<T> h = (FormosElementasFieldHandler<T>) fieldHandlers.get(elementas.getClass());
		h.setField(this, elementas);
	}
	
	/**
	 * Klonuoja objektą su visais jo laukais.
	 * @return klonas
	 */
	@Override
	public Forma clone() {
		Forma k;
		try {
			k = (Forma) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		return k;
	}
	
	
	
	public static abstract class FormosElementasFieldHandler<T extends FormosElementas> {
		public abstract void setField(Forma forma, T formosElementas);
				
	}
	
	public static class LinksnisHandler extends FormosElementasFieldHandler<Linksnis> {
		@Override
		public void setField(Forma forma, Linksnis linksnis) {
			forma.setLinksnis(linksnis);			
		}		
	}
	
	public static class SkaiciusHandler extends FormosElementasFieldHandler<Skaicius> {
		@Override
		public void setField(Forma forma, Skaicius skaicius) {
			forma.setSkaicius(skaicius);			
		}		
	}
	
	public static class GimineHandler extends FormosElementasFieldHandler<Gimine> {
		@Override
		public void setField(Forma forma, Gimine gimine) {
			forma.setGimine(gimine);			
		}
		
	}
	
	public static class PoskyrisHandler extends FormosElementasFieldHandler<Poskyris> {
		@Override
		public void setField(Forma forma, Poskyris poskyris) {
			forma.setPoskyris(poskyris);			
		}
		
	}
	
	public static class RusisHandler extends FormosElementasFieldHandler<Rusis> {
		@Override
		public void setField(Forma forma, Rusis rusis) {
			forma.setRusis(rusis);
		}
	}

}
