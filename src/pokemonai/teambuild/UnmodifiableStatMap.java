package pokemonai.teambuild;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pokemonai.constants.Stat;

// Dat boilerplate tho
public class UnmodifiableStatMap implements Map<Stat, Integer> {
	
	private final Map<Stat, Integer> statMap;
	
	public UnmodifiableStatMap(Map<Stat, Integer> statMap) {
		this.statMap = Collections.unmodifiableMap(new HashMap<>(statMap));
	}

	@Override
	public void clear() {
		statMap.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return statMap.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return statMap.containsValue(arg0);
	}

	@Override
	public Set<Entry<Stat, Integer>> entrySet() {
		return statMap.entrySet();
	}

	@Override
	public Integer get(Object arg0) {
		return statMap.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return statMap.isEmpty();
	}

	@Override
	public Set<Stat> keySet() {
		return statMap.keySet();
	}

	@Override
	public Integer put(Stat arg0, Integer arg1) {
		return statMap.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends Stat, ? extends Integer> arg0) {
		statMap.putAll(arg0);
	}

	@Override
	public Integer remove(Object arg0) {
		return statMap.remove(arg0);
	}

	@Override
	public int size() {
		return statMap.size();
	}

	@Override
	public Collection<Integer> values() {
		return statMap.values();
	}
}
