package pokemonai.data.pokedex;

import java.util.Arrays;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.teambuild.UnmodifiableStatMap;

public final class DexEntry {

	public final int num;
	public final String species;
	public final PokeType[] types;
	// 0 MF, 1 M, 2 F, 3 N
	public final int gender;
	public final UnmodifiableStatMap baseStats;
	public final String[] abilities;
	public final double height;
	public final double weight;

	// TODO: Add a provision for maxHP (Shedinja)
	public DexEntry(JSONObject dexJSON) {
		this.num = ((Long) dexJSON.get("num")).intValue();
		this.species = (String) dexJSON.get("species");
		Object[] types = ((JSONArray) dexJSON.get("types")).toArray();
		this.types = Arrays.stream(Arrays.copyOf(types, types.length, String[].class)).map(String::toUpperCase)
				.map(PokeType::valueOf).toArray(PokeType[]::new);
		if (dexJSON.containsKey("genderRatio")) {
			this.gender = 0;
		} else if (dexJSON.containsKey("gender")) {
			String gender = (String) dexJSON.get("gender");
			if (gender.equals("M")) {
				this.gender = 1;
			} else if (gender.equals("F")) {
				this.gender = 2;
			} else {
				this.gender = 3;
			}
		} else {
			this.gender = 0;
		}
		HashMap<Stat, Integer> statMap = new HashMap<>();
		JSONObject baseStats = (JSONObject) dexJSON.get("baseStats");
		for (Object s : baseStats.keySet()) {
			String statname = ((String) s).toUpperCase();
			statMap.put(Stat.valueOf(statname), ((Long) baseStats.get(s)).intValue());
		}
		this.baseStats = new UnmodifiableStatMap(statMap);
		Object[] abilities = ((JSONObject) dexJSON.get("abilities")).values().toArray();
		this.abilities = Arrays.copyOf(abilities, abilities.length, String[].class);
		this.height = ((Number) dexJSON.get("heightm")).doubleValue();
		this.weight = ((Number) dexJSON.get("weightkg")).doubleValue();
	}

}
