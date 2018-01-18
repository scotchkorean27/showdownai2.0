package pokemonaitests.teambuild;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pokemonai.constants.Nature;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.teambuild.BuildPokemon;

public class BuildPokemonTest {
	@Test
	void shouldImportDexEntry() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		HashMap<Stat, Integer> ivs = new HashMap<>();
		ivs.put(Stat.HP, 24);
		ivs.put(Stat.ATK, 12);
		ivs.put(Stat.DEF, 30);
		ivs.put(Stat.SPA, 16);
		ivs.put(Stat.SPD, 23);
		ivs.put(Stat.SPE, 5);
		HashMap<Stat, Integer> evs = new HashMap<>();
		evs.put(Stat.HP, 74);
		evs.put(Stat.ATK, 190);
		evs.put(Stat.DEF, 91);
		evs.put(Stat.SPA, 48);
		evs.put(Stat.SPD, 84);
		evs.put(Stat.SPE, 23);
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT, ivs, evs,
				Optional.empty());
		assertEquals(garchompbuild.getStat(Stat.HP).intValue(), 289);
		assertEquals(garchompbuild.getStat(Stat.ATK).intValue(), 278);
		assertEquals(garchompbuild.getStat(Stat.DEF).intValue(), 193);
		assertEquals(garchompbuild.getStat(Stat.SPA).intValue(), 135);
		assertEquals(garchompbuild.getStat(Stat.SPD).intValue(), 171);
		assertEquals(garchompbuild.getStat(Stat.SPE).intValue(), 171);
	}
}
