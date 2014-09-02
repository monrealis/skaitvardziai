package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class FormosElementasFieldHandler<T extends FormosElementas> {
	private static final Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> fieldHandlers;

	static {
		Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> h = new HashMap<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>>();
		h.put(Linksnis.class, new FormosElementasFieldHandler.LinksnisHandler());
		h.put(Skaicius.class, new FormosElementasFieldHandler.SkaiciusHandler());
		h.put(Gimine.class, new FormosElementasFieldHandler.GimineHandler());
		h.put(Poskyris.class, new FormosElementasFieldHandler.PoskyrisHandler());
		h.put(Rusis.class, new FormosElementasFieldHandler.RusisHandler());
		fieldHandlers = Collections.unmodifiableMap(h);
	}

	public abstract void setField(Forma forma, T formosElementas);

	@SuppressWarnings("unchecked")
	public static <T extends FormosElementas> void setElementas(Forma forma, T elementas) {
		FormosElementasFieldHandler<T> h = (FormosElementasFieldHandler<T>) FormosElementasFieldHandler.fieldHandlers.get(elementas.getClass());
		h.setField(forma, elementas);
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
