package eu.vytenis.skaitvardziai.util;

import java.util.List;

public class Comparisons {

	public static <T> int compare(Comparable<T> c1, T c2) {
		if (c1 == null && c2 == null) {
			return 0;
		} else if (c1 == null) {
			return -1;
		} else if (c2 == null) {
			return 1;
		} else {
			return c1.compareTo(c2);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T1, T2> int compareLists(List<T1> list1, List<T2> list2) {
		int size = Math.min(list1.size(), list2.size());
		for (int i = 0; i < size; ++i) {
			int r = compare((Comparable<Object>) list1.get(i), (Object) list2.get(i));
			if (r != 0) {
				return r;
			}
		}
		if (list1.size() < list2.size()) {
			return -1;
		} else if (list1.size() > list2.size()) {
			return 1;
		} else {
			return 0;
		}
	}

}
