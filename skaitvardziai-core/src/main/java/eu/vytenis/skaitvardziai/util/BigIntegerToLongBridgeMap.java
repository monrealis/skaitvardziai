package eu.vytenis.skaitvardziai.util;

import java.math.BigInteger;
import java.util.HashMap;

/*
 * Perduotą  BigInteger objektą get() ir containsKey() metoduose traktuoja
 * kaip Long objektą. Naudojama, kad nereiktų labai daug kodo keisti.
 */
public class BigIntegerToLongBridgeMap<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1;

	@Override
	public boolean containsKey(Object key) {
		if (key instanceof BigInteger)
			return super.containsKey(((BigInteger) key).longValue());
		return super.containsKey(key);
	}

	@Override
	public V get(Object key) {
		if (key instanceof BigInteger)
			return super.get(((BigInteger) key).longValue());
		return super.get(key);
	}
}
