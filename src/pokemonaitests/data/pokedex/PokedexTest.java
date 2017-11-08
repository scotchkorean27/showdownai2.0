package pokemonaitests.data.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.constants.TypeChart;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.Pokedex;

class PokedexTest {

	@Test
	void shouldImportDexEntry() {
		DexEntry magneton = Pokedex.getDexEntry("magneton");
		assertEquals(magneton.num, 82);
		assertEquals(magneton.species, "Magneton");
		assertArrayEquals(magneton.types, new PokeType[] {PokeType.ELECTRIC, PokeType.STEEL});
		assertEquals(magneton.gender, 3);
		assertEquals(magneton.baseStats.get(Stat.HP), (Integer) 50);
		assertEquals(magneton.baseStats.get(Stat.ATK), (Integer) 60);
		assertEquals(magneton.baseStats.get(Stat.DEF), (Integer) 95);
		assertEquals(magneton.baseStats.get(Stat.SPA), (Integer) 120);
		assertEquals(magneton.baseStats.get(Stat.SPD), (Integer) 70);
		assertEquals(magneton.baseStats.get(Stat.SPE), (Integer) 70);
		assertArrayEquals(magneton.abilities, new String[] {"Magnet Pull", "Sturdy", "Analytic"});
		assertEquals(magneton.height, 1.0);
		assertEquals(magneton.weight, 60.0);
	}

}
