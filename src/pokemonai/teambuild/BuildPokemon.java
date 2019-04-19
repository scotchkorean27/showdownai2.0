package pokemonai.teambuild;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import pokemonai.constants.Nature;
import pokemonai.constants.Stat;
import pokemonai.data.moves.Move;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;

import java.util.HashMap;
import java.util.Optional;

public class BuildPokemon {

    public final DexEntry dexData;
    public final int level;
    public final ImmutableSet<Move> moveset;
    public final Nature nature;
    public final ImmutableMap<Stat, Integer> ivs;
    public final ImmutableMap<Stat, Integer> evs;
    public final Optional<Integer> gender;

    // TODO: Implement and add Ability support
    public BuildPokemon(DexEntry dex, int level, MoveSet moveset, Nature nature, HashMap<Stat, Integer> ivs,
                        HashMap<Stat, Integer> evs, Optional<Integer> gender) {
        this.dexData = dex;
        this.level = level;
        this.moveset = ImmutableSet.copyOf(moveset);
        this.nature = nature;
        this.ivs = ImmutableMap.copyOf(ivs);
        this.evs = ImmutableMap.copyOf(evs);
        this.gender = gender;
    }

    public Integer getStat(Stat stat) {
        if (stat.equals(Stat.HP)) {
            return ((2 * this.dexData.baseStats.get(Stat.HP)
                    + (this.ivs.getOrDefault(Stat.HP, 0))
                    + (this.evs.getOrDefault(Stat.HP, 0) / 4)) * this.level / 100)
                    + this.level + 10;
        } else {
            return (int) ((Math
                    .floor((2 * this.dexData.baseStats.get(stat) + this.ivs.getOrDefault(stat, 0)
                            + (this.evs.getOrDefault(stat, 0) / 4)) * level / 100)
                    + 5) * (this.nature.getUp().equals(Optional.of(stat)) ? 1.1 : 1.0)
                    * (this.nature.getDown().equals(Optional.of(stat)) ? 0.9 : 1.0));
        }
    }
}
