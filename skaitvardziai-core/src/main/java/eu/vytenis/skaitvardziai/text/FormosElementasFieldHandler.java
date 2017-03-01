package eu.vytenis.skaitvardziai.text;

import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormosElementas;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

public abstract class FormosElementasFieldHandler<T extends FormosElementas> {
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
