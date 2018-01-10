package pokemonai.teambuild;

import java.util.ArrayList;
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
	
	public BuildPokemon(DexEntry dex, int level, MoveSet moveset, Nature nature, HashMap<Stat, Integer> ivs, HashMap<Stat, Integer> evs, Optional<Integer> gender) {
		this.dexData = dex;
		this.level = level;
		this.moveset = new UnmodifiableMoveSet(moveset);
		this.nature = nature;
		this.ivs = new UnmodifiableStatMap(ivs);
		this.evs = new UnmodifiableStatMap(evs);
		this.gender = gender;
	}
}
