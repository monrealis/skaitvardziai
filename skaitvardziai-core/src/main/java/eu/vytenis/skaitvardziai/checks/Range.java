package eu.vytenis.skaitvardziai.checks;

public class Range<T extends Number & Comparable<T>> {
	private RangeEnd<T> min;
	private RangeEnd<T> max;

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
		
		boolean satisfiesLeft = isSatisfiesMin(number);
		boolean satisfiesRight = isSatisfiesMax(number);
		return satisfiesLeft && satisfiesRight;		
	}

	protected boolean isSatisfiesMin(T number) {
		boolean satisfiesLeft = min.getValue() == null
				|| !min.isInclusive() && number.compareTo(min.getValue()) > 0
				|| min.isInclusive() && number.compareTo(min.getValue()) >= 0;
		return satisfiesLeft;
	}
	
	protected boolean isSatisfiesMax(T number) {
		boolean satisfiesRight = max.getValue() == null
						|| !max.isInclusive() && number.compareTo(max.getValue()) < 0
						|| max.isInclusive() && number.compareTo(max.getValue()) <= 0;
		return satisfiesRight;
	}
}