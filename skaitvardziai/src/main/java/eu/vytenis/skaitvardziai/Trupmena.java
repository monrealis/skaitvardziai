package eu.vytenis.skaitvardziai;

import java.math.BigInteger;
import java.util.Comparator;

import eu.vytenis.skaitvardziai.klasifikatoriai.FormaIrSkaiciai;
import eu.vytenis.skaitvardziai.klasifikatoriai.Gimine;
import eu.vytenis.skaitvardziai.klasifikatoriai.Linksnis;
import eu.vytenis.skaitvardziai.klasifikatoriai.Poskyris;
import eu.vytenis.skaitvardziai.klasifikatoriai.Skaicius;

public class Trupmena implements Comparable<Trupmena>, SkaitineReiksme {
	private long skaitiklis;
	private long vardiklis;
	
	public Trupmena(long skaitiklis, long vardiklis) {
		this.skaitiklis = skaitiklis;
		this.vardiklis = vardiklis;
	}
	
	public long getSkaitiklis() {
		return skaitiklis;
	}
	
	public long getVardiklis() {
		return vardiklis;
	}
	
	@Override
	public String toString() {
		return toString(Linksnis.V);
	}
	
	public String toString(Linksnis linksnis) {
		SveikasisSkaicius s = new SveikasisSkaicius(skaitiklis);
		SveikasisSkaicius v = new SveikasisSkaicius(vardiklis);
		
		FormaIrSkaiciai vfs = new FormaIrSkaiciai();
		vfs.setPoskyris(Poskyris.Kelintinis);
		vfs.setIvardziuotine(true);
		vfs.setGimine(Gimine.M);
		vfs.setSveikasisSkaicius(new BigInteger(Long.toString(vardiklis)));
		vfs.setPradinisSveikasisSkaicius(new BigInteger(Long.toString(vardiklis)));
		vfs.setSkaicius(Skaicius.D);
		
		long liekana100 = skaitiklis % 100;
		long liekana10 = skaitiklis % 10;
		if (liekana10 == 0) {
			vfs.setLinksnis(Linksnis.K); // pvz., nulis _dešimtųjų_ (ko?), dvidešimt _dešimštųjų_
		} else if (liekana100 > 10 && liekana100 < 20) {
			vfs.setLinksnis(Linksnis.K); // pvz., vienuolika _dešimtųjų_ (ko?), dvylika _dešimtųjų_
		} else {
			vfs.setLinksnis(linksnis); // pvz., viena _dešimtoji_ (kas?), dvidešimt viena _trečioji_
		}
		
		if (liekana10 == 1 && liekana100 != 11) {
			vfs.setSkaicius(Skaicius.V); // pvz., viena _dešimtoji_ (vns), dvidešimt viena _dešimtoji_, bet vienuolika _dešimtųjų_ (dgs)
		}
		
		
		return s.toString(linksnis, Gimine.M) + " " + v.toString(vfs);
	}
	
	public double toDouble() {
		return ((double) skaitiklis) / vardiklis;
	}
	
	public int compareTo(Trupmena o) {
		return new Double(toDouble()).compareTo(o.toDouble());
	}
	
	// TODO realizuoti komparatorius (pagal skaitiklį, vardiklį)
	// TODO realizuoti LinksnisHolder ir pagal jį grąžinti toString
	// TODO įvardžiuotinių vienaskaita / daugiskaita

	public static class SkaitiklisComparer implements Comparator<Trupmena> {

		public int compare(Trupmena o1, Trupmena o2) {
			return new Long(o1.getSkaitiklis()).compareTo(o2.getSkaitiklis());
		}
	}
	
	public static class VardiklisComparer implements Comparator<Trupmena> {

		public int compare(Trupmena o1, Trupmena o2) {
			return new Long(o1.getVardiklis()).compareTo(o2.getVardiklis());
		}
	}
}
