package eu.vytenis.skaitvardziai.checks;

public class Range<T extends Number & Comparable<?>> {
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
}