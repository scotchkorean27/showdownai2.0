package pokemonai.tests.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokemonai.constants.MoveName;
import pokemonai.constants.PokeType;
import pokemonai.constants.TypeChart;

class TypeChartTest {

	@Test
	void shouldReturnSuperEffective() {
		assertEquals(TypeChart.getMultiplier(PokeType.FIRE, PokeType.GRASS), 2);
	}
	
	@Test
	void shouldReturnNotVeryEffective() {
		assertEquals(TypeChart.getMultiplier(PokeType.FAIRY, PokeType.STEEL), 0.5);
	}
	
	@Test
	void shouldReturnImmune() {
		assertEquals(TypeChart.getMultiplier(PokeType.POISON, PokeType.STEEL), 0);
	}
	
	@Test
	void enumTest() {
		MoveName.ABSORB.implementation.;
	}
}
