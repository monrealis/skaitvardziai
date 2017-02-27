package eu.vytenis.skaitvardziai.klasifikatoriai;

import static java.util.Arrays.asList;

import java.util.List;

import eu.vytenis.skaitvardziai.util.Comparisons;

public class SkaiciusIrLinksnis implements Comparable<SkaiciusIrLinksnis> {
	public static final SkaiciusIrLinksnis VNS_VARD = new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V);
	private final Skaicius skaicius;
	private final Linksnis linksnis;

	public SkaiciusIrLinksnis(Skaicius skaicius, Linksnis linksnis) {
		this.skaicius = skaicius;
		this.linksnis = linksnis;
	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SkaiciusIrLinksnis))
			return false;
		return toList().equals(((SkaiciusIrLinksnis) obj).toList());
	}

	@Override
	public int hashCode() {
		return toList().hashCode();
	}

	public SkaiciusIrLinksnis linksnis(Linksnis linksnis) {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public SkaiciusIrLinksnis skaicius(Skaicius skaicius) {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public int compareTo(SkaiciusIrLinksnis other) {
		return Comparisons.compareLists(toList(), other.toList());
	}

	private List<Object> toList() {
		return asList((Object) skaicius, linksnis);
	}

	@Override
	public String toString() {
		String r = "";
		for (Object o : toList())
			r = appendIfNotNull(r, o);
		return r;
	}

	private String appendIfNotNull(String text, Object object) {
		if (object == null)
			return text;
		if (text.length() > 0)
			text += " ";
		text += object.toString();
		return text;
	}
}
