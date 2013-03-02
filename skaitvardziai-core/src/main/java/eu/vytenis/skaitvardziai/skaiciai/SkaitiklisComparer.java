package eu.vytenis.skaitvardziai.skaiciai;

import java.util.Comparator;

public class SkaitiklisComparer implements Comparator<Trupmena> {

	public int compare(Trupmena o1, Trupmena o2) {
		return o1.getSkaitiklis().compareTo(o2.getSkaitiklis());
	}
}