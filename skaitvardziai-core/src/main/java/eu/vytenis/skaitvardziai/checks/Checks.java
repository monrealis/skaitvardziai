package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class Checks {

	public static void checkNotNull(String name, Object object) {
		if (object == null) {
			throw new ObjectNullException(name);
		}		
	}

	public static void checkInclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}
		Range<BigInteger> range = new Range<BigInteger>(min, true, max, true);
		if (!range.contains(value)) {
			throw new InvalidRangeException(name, value, range);
		}
	}
	
	public static void checkExclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}		
		Range<BigInteger> range = new Range<BigInteger>(min, false, max, false);
		if (!range.contains(value)) {
			throw new InvalidRangeException(name, value, range);
		}
	}
	
	public static void checkMinInclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null) {
			return;
		}
		Range<BigInteger> range = new Range<BigInteger>(min, true, max, false);
		if (!range.contains(value)) {
			throw new InvalidRangeException(name, value, range);
		}
	}
	
	public static void checkCanModify(String name, UnmodifiableCapable unmodifiable) {
		if (unmodifiable == null) {
			return;
		}
		if (unmodifiable.isUnmodifiable()) {
			throw new NotModifiableException(name);
		}
	}
	
	public static void checkEqual(String firstName, String secondName, Object first, Object second) {
		boolean equal = first == null && second == null || first != null && first.equals(second);
		if (!equal) {
			throw new NotEqualException(Arrays.asList(firstName, secondName));
			
		}
	}

	public static void ensureUnmodifiable(Iterable<? extends UnmodifiableCapable> unmodifiables) {
		for (UnmodifiableCapable ic : unmodifiables) {
			if (!ic.isUnmodifiable()) {
				ic.setUnmodifiable(true);
			}
		}
	}

	
	public static class NotEqualException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = 8075649855292072831L;
		
		public NotEqualException(List<String> names) {
			super(names + " are not equal");			
		}
		
	}
	
	public static class ObjectNullException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = 363562983326179288L;
		
		public ObjectNullException(String objectName) {
			super(objectName + " cannot be null");			
		}
		
	}
	
	public static class InvalidRangeException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = -8245329095498329074L;
		
		private String objectName;
		private Number value;
		private Range<BigInteger> range;

		public InvalidRangeException(String objectName, Number value, Range<BigInteger> range) {
			super(buildMessage(objectName, value, range));
			
			this.objectName = objectName;
			this.value = value;
			this.range = range;
		}

		public String getObjectName() {
			return objectName;
		}
		
		public Number getValue() {
			return value;
		}
		
		public Range<BigInteger> getRange() {
			return range;
		}
		public static String buildMessage(String objectName, Number value, Range<BigInteger> range) {			
			String text = objectName + " = " + value + " must be in range " + range;
			return text;
		}
	}
	
	public static class NotModifiableException extends SkaitvardziaiRuntimeException {

		private static final long serialVersionUID = 3258850566232546106L;
		
		private String objectName;
		
		public NotModifiableException(String objectName) {
			super(objectName + " is read-only. Cannot modify read-only object");
			this.objectName = objectName;
		}
		
		public String getObjectName() {
			return objectName;
		}
		
	}
	
}
