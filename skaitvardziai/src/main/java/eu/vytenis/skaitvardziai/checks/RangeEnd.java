package eu.vytenis.skaitvardziai.checks;

import eu.vytenis.skaitvardziai.SkaitvardziaiRuntimeException;

public class RangeEnd {
	private static final String MINUS = "-";
	private static final String INFINITY = "infinity";
	private static final String OPEN_SYMBOL_LEFT = "(";
	private static final String CLOSED_SYMBOL_LEFT = "[";
	private static final String OPEN_SYMBOL_RIGHT = ")";
	private static final String CLOSED_SYMBOL_RIGHT= "]";

	private Number value;
	private boolean inclusive;

	public RangeEnd(Number value, boolean inclusive) {
		if (value == null && inclusive) {
			throw new InvalidRangeException();
		}
		this.value = value;
		this.inclusive = inclusive;
	}

	
	public Number getValue() {
		return value;
	}
	
	public boolean isInclusive() {
		return inclusive;
	}
	
	public String getLeftString() {
		return (isInclusiveAndNotNull() ? CLOSED_SYMBOL_LEFT : OPEN_SYMBOL_LEFT)
				+ (getValue() == null ? MINUS : "")
				+ getValueString();
	}
	
	public String getRightString() {
		return getValueString()
				+ (isInclusiveAndNotNull() ? CLOSED_SYMBOL_RIGHT : OPEN_SYMBOL_RIGHT);
	}
	
	private String getValueString() {
		return value != null ? value.toString() : INFINITY;
	}
	
	
	private boolean isInclusiveAndNotNull() {
		return value != null && isInclusive();			
	}
	
	public class InvalidRangeException extends SkaitvardziaiRuntimeException {

		/** Klasės versija. */
		private static final long serialVersionUID = 2933652317892072250L;
		
	}
}