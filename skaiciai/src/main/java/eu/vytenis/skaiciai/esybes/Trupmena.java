package eu.vytenis.skaiciai.esybes;

import java.util.Comparator;

public class Trupmena implements Comparable<Trupmena> {
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
		SveikasSkaicius s = new SveikasSkaicius(skaitiklis);
		SveikasSkaicius v = new SveikasSkaicius(vardiklis);
		
		Kontekstas k = new Kontekstas();
		k.setPoskyris(Poskyris.Kelintinis);
		k.setIvardziuotine(true);
		k.setGimine(Gimine.M);
		k.setSveikasSkaicius(vardiklis);
		k.setPradinisSveikasSkaicius(vardiklis);
		k.setSkaicius(Skaicius.D);
		
		long liekana100 = skaitiklis % 100;
		long liekana10 = skaitiklis % 10;
		if (liekana10 == 0) {
			k.setLinksnis(Linksnis.K); // pvz., nulis _dešimtųjų_ (ko?), dvidešimt _dešimštųjų_
		} else if (liekana100 > 10 && liekana100 < 20) {
			k.setLinksnis(Linksnis.K); // pvz., vienuolika _dešimtųjų_ (ko?), dvylika _dešimtųjų_
		} else if (liekana10 == 1) {
			k.setLinksnis(Linksnis.V); // pvz., viena _dešimtoji_ (kas?), dvidešimt viena _trečioji_
		}
		
		if (liekana10 == 1 && liekana100 != 11) {
			k.setSkaicius(Skaicius.V); // pvz., viena _dešimtoji_ (vns), dvidešimt viena _dešimtoji_, bet vienuolika _dešimtųjų_ (dgs)
		}
		
		
		return s.toString(Linksnis.V, Gimine.M) + " " + v.toString(k);
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
