package eu.vytenis.skaitvardziai.util;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Laikina klasė, kurią reiks išmest laikui bėgant.
 * Perduotą {@link BigInteger} objektą {@link #get(Object)} ir {@link #containsKey(Object)} metoduose traktuoja
 * kaip {@link Long} objektą. Naudojama, kad nereiktų labai daug kodo keisti.
 *
 * @param <K> lentelės rakto tipas
 * @param <V> lentelės reikšmės tipas
 */
public class BigIntegerToLongBridgeMap<K, V> extends HashMap<K, V>{

	private static final long serialVersionUID = 7302600248335755583L;
	
	@Override
	public boolean containsKey(Object key) {
		if (key instanceof BigInteger) {
			return super.containsKey(((BigInteger) key).longValue());
		}
		return super.containsKey(key);
	}
	
	@Override
	public V get(Object key) {
		if (key instanceof BigInteger) {
			return super.get(((BigInteger) key).longValue());
		}
		return super.get(key);
	}
	

}
