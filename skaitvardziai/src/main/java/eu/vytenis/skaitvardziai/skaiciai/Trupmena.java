package eu.vytenis.skaitvardziai.skaiciai;

import java.math.BigDecimal;
import java.math.BigInteger;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Rusis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

public class Trupmena implements Comparable<Trupmena>, SkaitineReiksme {
	private BigInteger skaitiklis;
	private BigInteger vardiklis;
	
	public Trupmena(BigInteger skaitiklis, BigInteger vardiklis) {
		CheckUtil.checkNotNull("skaitiklis", skaitiklis);
		CheckUtil.checkNotNull("vardiklis", vardiklis);
		this.skaitiklis = skaitiklis;
		this.vardiklis = vardiklis;
	}
	
	public Trupmena(String skaitiklis, String vardiklis) {
		CheckUtil.checkNotNull("skaitiklis", skaitiklis);
		CheckUtil.checkNotNull("vardiklis", vardiklis);
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
		updateVardiklioLinksnis(vf, linksnis);
		updateVardiklioSkaicius(vf);
		
		return toString(linksnis, vf);
	}

	private Forma getVardiklioFormaIsskyriusSkaiciuIrLinksni() {
		Forma vf = new Forma();
		vf.setPoskyris(Poskyris.Kelintinis);
		vf.setRusis(Rusis.Iv);
		vf.setGimine(Gimine.M);
		return vf;
	}
	
	private void updateVardiklioLinksnis(Forma vardiklioForma, Linksnis skaitiklioLinksnis) {
		BigInteger skaitiklisAbs = SveikasisSkaicius.abs(skaitiklis);
		BigInteger liekana100 = skaitiklisAbs.mod(SveikasisSkaicius.HUNDRED);
		BigInteger liekana10 = skaitiklisAbs.mod(BigInteger.TEN);
		
		if (liekana10.equals(BigInteger.ZERO)) {
			vardiklioForma.setLinksnis(Linksnis.K); // pvz., nulis _dešimtųjų_ (ko?), dvidešimt _dešimštųjų_
		} else if (liekana100.compareTo(BigInteger.TEN) > 0 && liekana100.compareTo(SveikasisSkaicius.TWENTY) < 0) {
			vardiklioForma.setLinksnis(Linksnis.K); // pvz., vienuolika _dešimtųjų_ (ko?), dvylika _dešimtųjų_
		} else {
			vardiklioForma.setLinksnis(skaitiklioLinksnis); // pvz., viena _dešimtoji_ (kas?), dvidešimt viena _trečioji_
		}		
	}
	
	private void updateVardiklioSkaicius(Forma vardiklioForma) {
		BigInteger skaitiklisAbs = SveikasisSkaicius.abs(skaitiklis);
		BigInteger liekana100 = skaitiklisAbs.mod(SveikasisSkaicius.HUNDRED);
		BigInteger liekana10 = skaitiklisAbs.mod(BigInteger.TEN);
		
		if (liekana10.equals(BigInteger.ONE) && !liekana100.equals(SveikasisSkaicius.ELEVEN)) {
			vardiklioForma.setSkaicius(Skaicius.V); // pvz., viena _dešimtoji_ (vns), dvidešimt viena _dešimtoji_, bet vienuolika _dešimtųjų_ (dgs)
		} else {
			vardiklioForma.setSkaicius(Skaicius.D);
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
	
	public double toDouble() {
		BigDecimal s = new BigDecimal(skaitiklis);
		BigDecimal v = new BigDecimal(vardiklis);
		return s.divide(v).doubleValue();
	}

}
