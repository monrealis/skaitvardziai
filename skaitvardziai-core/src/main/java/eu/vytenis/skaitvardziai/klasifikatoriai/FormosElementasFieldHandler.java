package eu.vytenis.skaitvardziai.klasifikatoriai;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

public abstract class FormosElementasFieldHandler<T extends FormosElementas> {
	private static final Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> fieldHandlers;

	static {
		Map<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>> handlers;
		handlers = new HashMap<Class<? extends FormosElementas>, FormosElementasFieldHandler<? extends FormosElementas>>();
		handlers.put(Linksnis.class, new LinksnisHandler());
		handlers.put(Skaicius.class, new SkaiciusHandler());
		handlers.put(Gimine.class, new GimineHandler());
		handlers.put(Poskyris.class, new PoskyrisHandler());
		handlers.put(Rusis.class, new RusisHandler());
		fieldHandlers = unmodifiableMap(handlers);
	}

	@SuppressWarnings("unchecked")
	public static <T extends FormosElementas> Forma setElementas(Forma forma, T elementas) {
		FormosElementasFieldHandler<T> h = (FormosElementasFieldHandler<T>) fieldHandlers.get(elementas.getClass());
		return h.setField(forma, elementas);
	}

	public abstract Forma setField(Forma forma, T formosElementas);

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
