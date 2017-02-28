package eu.vytenis.skaitvardziai.checks;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class RangeEnd<T extends Number & Comparable<?>> {
	private static final String INFINITY = "infinity";
	private final T value;
	private final boolean inclusive;

	public RangeEnd(T value, boolean inclusive) {
		if (value == null && inclusive)
			throw new InvalidRangeException();
		this.value = value;
		this.inclusive = inclusive;
	}

	public T getValue() {
		return value;
	}

	public boolean isInclusive() {
		return inclusive;
	}

	public String getLeftString() {
		if (isInfinity())
			return "(-" + INFINITY;
		String beginning = inclusive ? "[" : "(";
		return beginning + value.toString();
	}

	public String getRightString() {
		if (isInfinity())
			return INFINITY + ")";
		String end = inclusive ? "]" : ")";
		return value.toString() + end;
	}

	public boolean isInfinity() {
		return value == null;
	}

	public static class InvalidRangeException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;
	}
}
