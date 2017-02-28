package eu.vytenis.skaitvardziai.checks;

public class Range<T extends Number & Comparable<T>> {
	private final RangeEnd<T> min;
	private final RangeEnd<T> max;

	public Range(T min, boolean inclusiveMin, T max, boolean inclusiveMax) {
		this.min = new RangeEnd<T>(min, inclusiveMin);
		this.max = new RangeEnd<T>(max, inclusiveMax);
	}

	@Override
	public String toString() {
		return min.getLeftString() + "; " + max.getRightString();
	}

	public boolean contains(T number) {
		Checks.checkNotNull("number", number);
		if (!isSatisfiesMin(number))
			return false;
		if (!isSatisfiesMax(number))
			return false;
		return true;
	}

	protected boolean isSatisfiesMin(T number) {
		if (min.isInfinity())
			return true;
		int comparison = number.compareTo(min.getValue());
		return min.isInclusive() ? comparison >= 0 : comparison > 0;
	}

	protected boolean isSatisfiesMax(T number) {
		if (max.isInfinity())
			return true;
		int comparison = number.compareTo(max.getValue());
		return max.isInclusive() ? comparison <= 0 : comparison < 0;
	}
}
