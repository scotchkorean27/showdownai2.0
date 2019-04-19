package pokemonaitests.constants;

import org.junit.Test;
import pokemonai.constants.PokeType;
import pokemonai.constants.TypeChart;

import static junit.framework.TestCase.assertEquals;

class TypeChartTest {

    @Test
    public void shouldReturnSuperEffective() {
        assertEquals(TypeChart.getMultiplier(PokeType.FIRE, PokeType.GRASS), 2);
    }

    @Test
    public void shouldReturnNotVeryEffective() {
        assertEquals(TypeChart.getMultiplier(PokeType.FAIRY, PokeType.STEEL), 0.5);
    }

    @Test
    public void shouldReturnImmune() {
        assertEquals(TypeChart.getMultiplier(PokeType.POISON, PokeType.STEEL), 0);
    }
}
