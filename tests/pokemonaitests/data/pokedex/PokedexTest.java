package pokemonaitests.data.pokedex;

import org.junit.Test;
import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.Pokedex;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertArrayEquals;


class PokedexTest {

    @Test
    public void shouldImportDexEntry() {
        DexEntry magneton = Pokedex.getDexEntry("magneton");
        assertEquals(magneton.num, 82);
        assertEquals(magneton.species, "Magneton");
        assertArrayEquals(magneton.types, new PokeType[]{PokeType.ELECTRIC, PokeType.STEEL});
        assertEquals(magneton.gender, 3);
        assertEquals(magneton.baseStats.get(Stat.HP), (Integer) 50);
        assertEquals(magneton.baseStats.get(Stat.ATK), (Integer) 60);
        assertEquals(magneton.baseStats.get(Stat.DEF), (Integer) 95);
        assertEquals(magneton.baseStats.get(Stat.SPA), (Integer) 120);
        assertEquals(magneton.baseStats.get(Stat.SPD), (Integer) 70);
        assertEquals(magneton.baseStats.get(Stat.SPE), (Integer) 70);
        assertArrayEquals(magneton.abilities, new String[]{"Magnet Pull", "Sturdy", "Analytic"});
        assertEquals(magneton.height, 1.0);
        assertEquals(magneton.weight, 60.0);
    }
}
