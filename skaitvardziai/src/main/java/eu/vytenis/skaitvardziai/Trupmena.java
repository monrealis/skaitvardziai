package eu.vytenis.skaitvardziai;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;

import eu.vytenis.skaitvardziai.checks.CheckUtil;
import eu.vytenis.skaitvardziai.klasifikatoriai.Forma;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
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
		SveikasisSkaicius s = new SveikasisSkaicius(skaitiklis);
		SveikasisSkaicius v = new SveikasisSkaicius(vardiklis);
		
		Forma vf = new Forma();
		vf.setPoskyris(Poskyris.Kelintinis);
		vf.setIvardziuotine(true);
		vf.setGimine(Gimine.M);
		vf.setSkaicius(Skaicius.D);
		
		BigInteger liekana100 = skaitiklis.mod(SveikasisSkaicius.HUNDRED);
		BigInteger liekana10 = skaitiklis.mod(BigInteger.TEN);
		if (liekana10.equals(BigInteger.ZERO)) {
			vf.setLinksnis(Linksnis.K); // pvz., nulis _dešimtųjų_ (ko?), dvidešimt _dešimštųjų_
		} else if (liekana100.compareTo(BigInteger.TEN) > 0 && liekana100.compareTo(SveikasisSkaicius.TWENTY) < 0) {
			vf.setLinksnis(Linksnis.K); // pvz., vienuolika _dešimtųjų_ (ko?), dvylika _dešimtųjų_
		} else {
			vf.setLinksnis(linksnis); // pvz., viena _dešimtoji_ (kas?), dvidešimt viena _trečioji_
		}
		
		if (liekana10.equals(BigInteger.ONE) && !liekana100.equals(SveikasisSkaicius.ELEVEN)) {
			vf.setSkaicius(Skaicius.V); // pvz., viena _dešimtoji_ (vns), dvidešimt viena _dešimtoji_, bet vienuolika _dešimtųjų_ (dgs)
		}
		
		
		return s.toString(linksnis, Gimine.M) + " " + v.toString(vf);
	}
	
	public double toDouble() {
		BigDecimal s = new BigDecimal(skaitiklis);
		BigDecimal v = new BigDecimal(vardiklis);
		return s.divide(v).doubleValue();
	}
	
	public int compareTo(Trupmena o) {
		return new Double(toDouble()).compareTo(o.toDouble());
	}
	
	// TODO realizuoti LinksnisHolder ir pagal jį grąžinti toString
	// TODO įvardžiuotinių vienaskaita / daugiskaita

	public static class SkaitiklisComparer implements Comparator<Trupmena> {

		public int compare(Trupmena o1, Trupmena o2) {
			return o1.getSkaitiklis().compareTo(o2.getSkaitiklis());
		}
	}
	
	public static class VardiklisComparer implements Comparator<Trupmena> {

		public int compare(Trupmena o1, Trupmena o2) {
			return o1.getVardiklis().compareTo(o2.getVardiklis());
		}
	}
}
