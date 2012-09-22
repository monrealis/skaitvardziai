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
		Skaicius s = new Skaicius(skaitiklis);
		Skaicius v = new Skaicius(vardiklis);
		
		Kontekstas k = new Kontekstas();
		k.setPoskyris(Poskyris.Kelintinis);
		k.setIvardziuotine(true);
		k.setGimine(Gimine.M);
		k.setSkaicius(vardiklis);
		k.setPradinisSkaicius(vardiklis);
		
		long liekana = vardiklis / 100;
		if (liekana == 0) {
			
		} else if (liekana == 1) {
			
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
