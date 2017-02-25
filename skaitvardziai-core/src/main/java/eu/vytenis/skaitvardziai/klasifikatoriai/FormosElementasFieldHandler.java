package eu.vytenis.skaitvardziai.klasifikatoriai;

import static java.util.Collections.unmodifiableMap;

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
		fieldHandlers = unmodifiableMap(h);
	}

	public abstract Forma setField(Forma forma, T formosElementas);

	@SuppressWarnings("unchecked")
	public static <T extends FormosElementas> Forma setElementas(Forma forma, T elementas) {
		FormosElementasFieldHandler<T> h = (FormosElementasFieldHandler<T>) FormosElementasFieldHandler.fieldHandlers.get(elementas.getClass());
		return h.setField(forma, elementas);
	}

	public static class LinksnisHandler extends FormosElementasFieldHandler<Linksnis> {
		@Override
		public Forma setField(Forma forma, Linksnis linksnis) {
			return forma.linksnis(linksnis);
		}
	}

	public static class SkaiciusHandler extends FormosElementasFieldHandler<Skaicius> {
		@Override
		public Forma setField(Forma forma, Skaicius skaicius) {
			return forma.skaicius(skaicius);
		}
	}

	public static class GimineHandler extends FormosElementasFieldHandler<Gimine> {
		@Override
		public Forma setField(Forma forma, Gimine gimine) {
			return forma.gimine(gimine);
		}
	}

	public static class PoskyrisHandler extends FormosElementasFieldHandler<Poskyris> {
		@Override
		public Forma setField(Forma forma, Poskyris poskyris) {
			return forma.poskyris(poskyris);
		}
	}

	public static class RusisHandler extends FormosElementasFieldHandler<Rusis> {
		@Override
		public Forma setField(Forma forma, Rusis rusis) {
			return forma.rusis(rusis);
		}
	}
}
