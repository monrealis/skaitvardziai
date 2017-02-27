package eu.vytenis.skaitvardziai.klasifikatoriai;

import static java.util.Arrays.asList;

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
		SkaiciusIrLinksnis o = (SkaiciusIrLinksnis) obj;
		return skaicius == o.skaicius && linksnis == o.linksnis;
	}

	@Override
	public int hashCode() {
		return (skaicius != null ? skaicius.hashCode() : 0) << 16 | (linksnis != null ? linksnis.hashCode() : 0);
	}

	public SkaiciusIrLinksnis withLinksnis(Linksnis linksnis) {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public SkaiciusIrLinksnis withSkaicius(Skaicius skaicius) {
		return new SkaiciusIrLinksnis(skaicius, linksnis);
	}

	public int compareTo(SkaiciusIrLinksnis o) {
		return Comparisons.compareLists(asList((Object) skaicius, linksnis), asList((Object) o.skaicius, o.linksnis));
	}

	@Override
	public String toString() {
		String r = "";
		for (Object o : asList((Object) skaicius, linksnis))
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
