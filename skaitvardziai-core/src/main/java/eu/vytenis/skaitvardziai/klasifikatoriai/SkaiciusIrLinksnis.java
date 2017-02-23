package eu.vytenis.skaitvardziai.klasifikatoriai;

import java.util.Arrays;

import eu.vytenis.skaitvardziai.checks.Checks;
import eu.vytenis.skaitvardziai.checks.UnmodifiableCapable;
import eu.vytenis.skaitvardziai.util.Comparisons;

public class SkaiciusIrLinksnis implements Cloneable, Comparable<SkaiciusIrLinksnis>, UnmodifiableCapable {
	public static final SkaiciusIrLinksnis VNS_VARD = new SkaiciusIrLinksnis(Skaicius.V, Linksnis.V).unmodifiable();
	private Skaicius skaicius;
	private Linksnis linksnis;
	private boolean unmodifiable;

	public SkaiciusIrLinksnis(Skaicius skaicius, Linksnis linksnis) {
		this.skaicius = skaicius;
		this.linksnis = linksnis;
	}

	public Skaicius getSkaicius() {
		return skaicius;
	}

	@Deprecated
	public void setSkaicius(Skaicius skaicius) {
		Checks.checkCanModify("SkaiciusLinksnis", this);
		this.skaicius = skaicius;
	}

	public Linksnis getLinksnis() {
		return linksnis;
	}

	@Deprecated
	public void setLinksnis(Linksnis linksnis) {
		Checks.checkCanModify("SkaiciusLinksnis", this);
		this.linksnis = linksnis;
	}

	public boolean isUnmodifiable() {
		return unmodifiable;
	}

	public void setUnmodifiable(boolean unmodifiable) {
		Checks.checkCanModify("SkaiciusLinksnis", this);
		this.unmodifiable = unmodifiable;
	}

	public SkaiciusIrLinksnis unmodifiable() {
		setUnmodifiable(true);
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SkaiciusIrLinksnis)) {
			return false;
		}
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

	@Override
	public SkaiciusIrLinksnis clone() {
		try {
			return (SkaiciusIrLinksnis) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public int compareTo(SkaiciusIrLinksnis o) {
		return Comparisons.compareLists(Arrays.<Enum<?>> asList(skaicius, linksnis), Arrays.<Enum<?>> asList(o.skaicius, o.linksnis));
	}

	@Override
	public String toString() {
		String r = "";
		for (Object o : Arrays.<Object> asList(skaicius, linksnis)) {
			r = appendIfNotNull(r, o);
		}
		return r;
	}

	private String appendIfNotNull(String text, Object object) {
		if (object == null) {
			return text;
		}
		if (text.length() > 0) {
			text += " ";
		}
		text += object.toString();
		return text;
	}

}
