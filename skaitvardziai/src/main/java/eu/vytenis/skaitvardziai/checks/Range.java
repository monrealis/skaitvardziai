package eu.vytenis.skaitvardziai.checks;

public class Range {
	private RangeEnd min;
	private RangeEnd max;

	public Range(Number min, boolean inclusiveMin, Number max, boolean inclusiveMax) {
		this.min = new RangeEnd(min, inclusiveMin);
		this.max = new RangeEnd(max, inclusiveMax);
	}
	
	@Override
	public String toString() {
		return min.getLeftString() + "; " + max.getRightString();
	}
}