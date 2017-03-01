package eu.vytenis.skaitvardziai.text;

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
import eu.vytenis.skaitvardziai.text.FieldHandler.GimineHandler;
import eu.vytenis.skaitvardziai.text.FieldHandler.LinksnisHandler;
import eu.vytenis.skaitvardziai.text.FieldHandler.PoskyrisHandler;
import eu.vytenis.skaitvardziai.text.FieldHandler.RusisHandler;
import eu.vytenis.skaitvardziai.text.FieldHandler.SkaiciusHandler;

public class FieldHandlers {
	private static final Map<Class<? extends FormosElementas>, FieldHandler<? extends FormosElementas>> fieldHandlers;

	static {
		Map<Class<? extends FormosElementas>, FieldHandler<? extends FormosElementas>> handlers;
		handlers = new HashMap<Class<? extends FormosElementas>, FieldHandler<? extends FormosElementas>>();
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
}
