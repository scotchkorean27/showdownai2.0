package pokemonai.teambuild;

import java.util.HashMap;
import java.util.Optional;

import pokemonai.constants.Nature;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;

public class BuildPokemon {

	public final DexEntry dexData;
	public final int level;
	public final UnmodifiableMoveSet moveset;
	public final Nature nature;
	public final UnmodifiableStatMap ivs;
	public final UnmodifiableStatMap evs;
	public final Optional<Integer> gender;

	// TODO: Implement and add Ability support
	public BuildPokemon(DexEntry dex, int level, MoveSet moveset, Nature nature, HashMap<Stat, Integer> ivs,
			HashMap<Stat, Integer> evs, Optional<Integer> gender) {
		this.dexData = dex;
		this.level = level;
		this.moveset = new UnmodifiableMoveSet(moveset);
		this.nature = nature;
		this.ivs = new UnmodifiableStatMap(ivs);
		this.evs = new UnmodifiableStatMap(evs);
		this.gender = gender;
	}

	public Integer getStat(Stat stat) {
		if (stat.equals(Stat.HP)) {
			return (int) ((2 * this.dexData.baseStats.get(Stat.HP)
					+ (this.ivs.containsKey(Stat.HP) ? this.ivs.get(Stat.HP) : 0)
					+ ((this.evs.containsKey(Stat.HP) ? this.evs.get(Stat.HP) : 0) / 4)) * this.level / 100)
					+ this.level + 10;
		} else {
			return (int) ((Math
					.floor((2 * this.dexData.baseStats.get(stat) + (this.ivs.containsKey(stat) ? this.ivs.get(stat) : 0)
							+ ((this.evs.containsKey(stat) ? this.evs.get(stat) : 0) / 4)) * level / 100)
					+ 5) * (this.nature.getUp().equals(Optional.of(stat)) ? 1.1 : 1.0)
					* (this.nature.getDown().equals(Optional.of(stat)) ? 0.9 : 1.0));
		}
	}
}
