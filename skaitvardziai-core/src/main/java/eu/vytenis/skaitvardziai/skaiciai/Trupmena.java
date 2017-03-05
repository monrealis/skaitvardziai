package eu.vytenis.skaitvardziai.skaiciai;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;
import eu.vytenis.skaitvardziai.util.Numbers;

public class Trupmena implements Comparable<Trupmena>, SkaitineReiksme {
	private final BigInteger skaitiklis;
	private final BigInteger vardiklis;

	public Trupmena(BigInteger skaitiklis, BigInteger vardiklis) {
		Checks.checkNotNull("skaitiklis", skaitiklis);
		Checks.checkNotNull("vardiklis", vardiklis);
		this.skaitiklis = skaitiklis;
		this.vardiklis = vardiklis;
	}

	public Trupmena(String skaitiklis, String vardiklis) {
		Checks.checkNotNull("skaitiklis", skaitiklis);
		Checks.checkNotNull("vardiklis", vardiklis);
		this.skaitiklis = new BigInteger(skaitiklis);
		this.vardiklis = new BigInteger(vardiklis);
	}

	public Trupmena(long skaitiklis, long vardiklis) {
		this.skaitiklis = new BigInteger(Long.toString(skaitiklis));
		this.vardiklis = new BigInteger(Long.toString(vardiklis));
	}

	public BigInteger getSkaitiklis() {
		return skaitiklis;
	}

	public BigInteger getVardiklis() {
		return vardiklis;
	}

	@Override
	public String toString() {
		return toString(Linksnis.V);
	}

	public String toString(Linksnis linksnis) {
		Forma vf = getVardiklioFormaIsskyriusSkaiciuIrLinksni();
		Forma vfl = withUpdatedVardiklioLinksnis(vf, linksnis);
		Forma vfs = withUpdateVardiklioSkaicius(vfl);
		return toString(linksnis, vfs);
	}

	private Forma getVardiklioFormaIsskyriusSkaiciuIrLinksni() {
		Forma vf = new Forma(Poskyris.Kelintinis, Gimine.M, Skaicius.V, Linksnis.V, Rusis.Iv);
		return vf;
	}

	private Forma withUpdatedVardiklioLinksnis(Forma vardiklioForma, Linksnis skaitiklioLinksnis) {
		BigInteger skaitiklisAbs = SveikasisSkaicius.abs(skaitiklis);
		BigInteger liekana100 = skaitiklisAbs.mod(Numbers.HUNDRED);
		BigInteger liekana10 = skaitiklisAbs.mod(BigInteger.TEN);
		if (liekana10.equals(BigInteger.ZERO)) {
			return vardiklioForma.linksnis(Linksnis.K);
			// pvz., nulis _dešimtųjų_ (ko?), dvidešimt _dešimštųjų_
		} else if (liekana100.compareTo(BigInteger.TEN) > 0 && liekana100.compareTo(Numbers.TWENTY) < 0) {
			return vardiklioForma.linksnis(Linksnis.K);
			// pvz., vienuolika _dešimtųjų_ (ko?), dvylika _dešimtųjų_
		} else {
			return vardiklioForma.linksnis(skaitiklioLinksnis);
			// pvz., viena _dešimtoji_ (kas?), dvidešimt viena _trečioji_
		}
	}

	private Forma withUpdateVardiklioSkaicius(Forma vardiklioForma) {
		BigInteger skaitiklisAbs = SveikasisSkaicius.abs(skaitiklis);
		BigInteger liekana100 = skaitiklisAbs.mod(Numbers.HUNDRED);
		BigInteger liekana10 = skaitiklisAbs.mod(BigInteger.TEN);
		if (liekana10.equals(BigInteger.ONE) && !liekana100.equals(Numbers.ELEVEN)) {
			return vardiklioForma.skaicius(Skaicius.V);
			// pvz., viena _dešimtoji_ (vns), dvidešimt viena _dešimtoji_, bet
			// vienuolika _dešimtųjų_ (dgs)
		} else {
			return vardiklioForma.skaicius(Skaicius.D);
		}
	}

	private String toString(Linksnis skaitiklioLinksnis, Forma vardiklioForma) {
		SveikasisSkaicius s = new SveikasisSkaicius(skaitiklis);
		SveikasisSkaicius v = new SveikasisSkaicius(vardiklis);
		return s.toString(skaitiklioLinksnis, Gimine.M) + " " + v.toString(vardiklioForma);
	}

	public int compareTo(Trupmena o) {
		return new Double(toDouble()).compareTo(o.toDouble());
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Trupmena))
			return false;
		return toList().equals(((Trupmena) obj).toList());
	}

	@Override
	public int hashCode() {
		return toList().hashCode();
	}

	private List<?> toList() {
		return asList(skaitiklis, vardiklis);
	}

	public double toDouble() {
		BigDecimal r = toBigDecimal();
		return r.doubleValue();
	}

	private BigDecimal toBigDecimal() {
		BigDecimal s = new BigDecimal(skaitiklis);
		BigDecimal v = new BigDecimal(vardiklis);
		BigDecimal r = s.divide(v);
		return r;
	}

	public <T> T accept(SkaitineReiksmeVisitor<T> visitor) {
		return visitor.visitTrupmena();
	}
}
