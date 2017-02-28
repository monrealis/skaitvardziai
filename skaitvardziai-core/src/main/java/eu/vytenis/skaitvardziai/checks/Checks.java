package eu.vytenis.skaitvardziai.checks;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import eu.vytenis.skaitvardziai.exc.SkaitvardziaiRuntimeException;

public class Checks {
	public static void checkNotNull(String name, Object object) {
		if (object == null)
			throw new ObjectNullException(name);
	}

	public static void checkMinInclusive(String name, BigInteger value, BigInteger min, BigInteger max) {
		if (value == null)
			return;
		Range<BigInteger> range = new Range<BigInteger>(min, true, max, false);
		if (!range.contains(value))
			throw new InvalidRangeException(name, value, range);
	}

	public static void checkEqual(String firstName, String secondName, Object first, Object second) {
		boolean equal = first == null && second == null || first != null && first.equals(second);
		if (!equal)
			throw new NotEqualException(Arrays.asList(firstName, secondName));
	}

	public static class NotEqualException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public NotEqualException(List<String> names) {
			super(names + " are not equal");
		}

	}

	public static class ObjectNullException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public ObjectNullException(String objectName) {
			super(objectName + " cannot be null");
		}
	}

	public static class InvalidRangeException extends SkaitvardziaiRuntimeException {
		private static final long serialVersionUID = 1;

		public InvalidRangeException(String objectName, Number value, Range<BigInteger> range) {
			super(buildMessage(objectName, value, range));
		}

		public static String buildMessage(String objectName, Number value, Range<BigInteger> range) {
			String text = objectName + " = " + value + " must be in range " + range;
			return text;
		}
	}
}
