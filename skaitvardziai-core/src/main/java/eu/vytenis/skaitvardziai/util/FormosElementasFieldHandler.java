package eu.vytenis.skaitvardziai.util;

import static java.util.Collections.unmodifiableMap;

import java.util.HashMap;
import java.util.Map;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

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

	public static <T extends FormosElementas> Forma withUpdatedElement(Forma forma, T elementas) {
		return fieldHandlers.get(elementas.getClass()).setUncastedField(forma, elementas);
	}

	private final Class<T> type;

	public FormosElementasFieldHandler(Class<T> type) {
		this.type = type;
	}

	protected Forma setUncastedField(Forma forma, Object formosElementas) {
		return setField(forma, type.cast(formosElementas));
	}

	protected abstract Forma setField(Forma forma, T formosElementas);

	public static class LinksnisHandler extends FormosElementasFieldHandler<Linksnis> {
		public LinksnisHandler() {
			super(Linksnis.class);
		}

		@Override
		protected Forma setField(Forma forma, Linksnis linksnis) {
			return forma.linksnis(linksnis);
		}
	}

	public static class SkaiciusHandler extends FormosElementasFieldHandler<Skaicius> {
		public SkaiciusHandler() {
			super(Skaicius.class);
		}

		@Override
		protected Forma setField(Forma forma, Skaicius skaicius) {
			return forma.skaicius(skaicius);
		}
	}

	public static class GimineHandler extends FormosElementasFieldHandler<Gimine> {
		public GimineHandler() {
			super(Gimine.class);
		}

		@Override
		protected Forma setField(Forma forma, Gimine gimine) {
			return forma.gimine(gimine);
		}
	}

	public static class PoskyrisHandler extends FormosElementasFieldHandler<Poskyris> {
		public PoskyrisHandler() {
			super(Poskyris.class);
		}

		@Override
		protected Forma setField(Forma forma, Poskyris poskyris) {
			return forma.poskyris(poskyris);
		}
	}

	public static class RusisHandler extends FormosElementasFieldHandler<Rusis> {
		public RusisHandler() {
			super(Rusis.class);
		}

		@Override
		protected Forma setField(Forma forma, Rusis rusis) {
			return forma.rusis(rusis);
		}
	}
}
