package pokemonaitests.data.pokedex;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pokemonai.data.pokedex.Pokedex;

class PokedexTest {

	@Test
	void test() {
		new Pokedex().readPokedex();
	}

}
