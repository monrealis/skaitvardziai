package eu.vytenis.skaitvardziai.checks;

class Range<T extends Number & Comparable<T>> {
	private final RangeEnd<T> min;
	private final RangeEnd<T> max;

	public Range(T min, Inclusive inclusiveMin, T max, Inclusive inclusiveMax) {
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

	private boolean isSatisfiesMin(T number) {
		if (min.isInfinity())
			return true;
		int comparison = number.compareTo(min.getValue());
		return min.isInclusive() ? comparison >= 0 : comparison > 0;
	}

	private boolean isSatisfiesMax(T number) {
		if (max.isInfinity())
			return true;
		int comparison = number.compareTo(max.getValue());
		return max.isInclusive() ? comparison <= 0 : comparison < 0;
	}
}
