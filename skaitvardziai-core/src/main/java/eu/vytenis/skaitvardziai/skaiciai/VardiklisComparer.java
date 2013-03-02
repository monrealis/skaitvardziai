package eu.vytenis.skaitvardziai.skaiciai;

import java.util.Comparator;

public class VardiklisComparer implements Comparator<Trupmena> {

	public int compare(Trupmena o1, Trupmena o2) {
		return o1.getVardiklis().compareTo(o2.getVardiklis());
	}
}