package eu.vytenis.skaitvardziai.util;

import java.util.List;

/**
 * Pagalbinės operacijos objektams lyginti.
 */
public class CompareUtils {


	/**
	 * Palygina du objektus pagal {@link Comparable#compareTo(Object)}. null traktuojamas kaip mažesnis skaičius už minimalų.
	 * @param c1 pirmas objektas
	 * @param c2 antras objektas
	 * @return palyginimo rezultatas (teigiamas, neigiamas arba nulis)
	 */
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
	
	/**
	 * Palygina {@link Comparable} objektų sąrašus pagal pirmą nesutampantį objektą.
	 * Jei sąrašų visi elementai lygūs (iki minimalaus abiejų sąrašų ilgio), didesniu laikomas ilgesnis sąrašas
	 * @param list1 pirmas sąrašas
	 * @param list2 antras sąrašas
	 * @return palyginimo rezultatas (teigiamas, neigiamas arba nulis)
	 */
	public static <T1, T2> int compareLists(List<T1> list1, List<T2> list2) {
		int size = Math.min(list1.size(), list2.size());
		
		for (int i = 0; i < size; ++i) {
			@SuppressWarnings("unchecked")
			int r = compare((Comparable<Object>)list1.get(i), (Object) list2.get(i));
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
