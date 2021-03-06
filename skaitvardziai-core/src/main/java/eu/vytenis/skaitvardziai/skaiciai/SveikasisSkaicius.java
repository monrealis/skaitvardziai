package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import eu.vytenis.skaitvardziai.builder.SveikasisBuilder;
import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.klasifikatoriai.SkaiciusIrLinksnis;
import eu.vytenis.skaitvardziai.zodziai.Zodis;
import eu.vytenis.skaitvardziai.zodziai.ZodisJunginyje;

public class SveikasisSkaicius implements SkaitineReiksme, Comparable<SveikasisSkaicius> {
	private final BigInteger reiksme;

	public SveikasisSkaicius(long reiksme) {
		this.reiksme = new BigInteger(Long.toString(reiksme));
	}

	public SveikasisSkaicius(String reiksme) {
		Checks.checkNotNull("reiksme", reiksme);
		this.reiksme = new BigInteger(reiksme);
	}

	public SveikasisSkaicius(BigInteger reiksme) {
		Checks.checkNotNull("reiksme", reiksme);
		this.reiksme = reiksme;
	}

	public BigInteger getReiksme() {
		return reiksme;
	}

	@Override
	public String toString() {
		return toString(Linksnis.V, Gimine.V);
	}

	public String toString(Linksnis linksnis, Gimine gimine) {
		return toString(Poskyris.Pagrindinis, linksnis, gimine);
	}

	public String toString(Poskyris poskyris, Linksnis linksnis, Gimine gimine) {
		return toString(poskyris, Skaicius.V, linksnis, gimine);
	}

	public String toString(Poskyris poskyris, Skaicius skaicius, Linksnis linksnis, Gimine gimine) {
		Forma f = new Forma(poskyris, gimine, skaicius, linksnis, Rusis.P);
		return toString(f);
	}

	public String toString(Forma forma) {
		return toZodisJunginyje(forma).getTekstas();
	}

	public SkaiciusIrLinksnis getKitoZodzioSkaiciusIrLinksnis(Forma forma) {
		return toZodisJunginyje(forma).getKitoZodzioSkaiciusIrLinksnis();
	}

	public TekstasJunginyje toZodisJunginyje(Forma forma) {
		FormaIrSkaiciai fs = new FormaIrSkaiciai(forma, abs(), abs());
		List<ZodisJunginyje> zodziai = getZodziai(fs);
		String unsigned = ZodisJunginyje.toString(zodziai, fs);
		String text = isNonNegative() ? unsigned : "minus " + unsigned;
		SkaiciusIrLinksnis formosSkaiciusIrLinksnis = new SkaiciusIrLinksnis(forma.getSkaicius(), forma.getLinksnis());
		SkaiciusIrLinksnis kitas = getKitoZodzioSkaiciusIrLinksnis(formosSkaiciusIrLinksnis, zodziai);
		TekstasJunginyje skaiciusJunginyje = new TekstasJunginyje(text, kitas);
		return skaiciusJunginyje;
	}

	private BigInteger abs() {
		return abs(reiksme);
	}

	public static BigInteger abs(BigInteger number) {
		return number.compareTo(BigInteger.ZERO) >= 0 ? number : number.negate();
	}

	private boolean isNonNegative() {
		return reiksme.compareTo(BigInteger.ZERO) >= 0;
	}

	private List<ZodisJunginyje> getZodziai(FormaIrSkaiciai fs) {
		SveikasisBuilder b = new SveikasisBuilder();
		b.build(fs);
		List<ZodisJunginyje> zodziai = b.getZodziai();
		Collections.reverse(zodziai);
		return zodziai;
	}

	private SkaiciusIrLinksnis getKitoZodzioSkaiciusIrLinksnis(SkaiciusIrLinksnis skaiciusIrLinksnis, List<ZodisJunginyje> zodziai) {
		Zodis paskutinis = zodziai.get(zodziai.size() - 1).getZodis();
		SkaiciusIrLinksnis kito = paskutinis.getKitasSkaiciusIrLinksnis().nvl(skaiciusIrLinksnis);
		return kito;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SveikasisSkaicius))
			return false;
		return reiksme.equals(((SveikasisSkaicius) obj).getReiksme());
	}

	@Override
	public int hashCode() {
		return reiksme.hashCode();
	}

	public int compareTo(SveikasisSkaicius o) {
		return reiksme.compareTo(o.getReiksme());
	}

	public <T> T accept(SkaitineReiksmeVisitor<T> visitor) {
		return visitor.visitSveikasisSkaicius();
	}
}
