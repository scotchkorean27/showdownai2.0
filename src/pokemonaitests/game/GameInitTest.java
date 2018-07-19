package pokemonaitests.game;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pokemonai.constants.Nature;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.Game;
import pokemonai.game.Player;
import pokemonai.teambuild.BuildPokemon;

public class GameInitTest {

	@Test
	void ShouldInitializePokemonIdsProperly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		ArrayList<BuildPokemon> team = new ArrayList<>();
		team.add(alakazambuild);
		Player p1 = new Player(null, team);
		Game game = new Game(p1, p1);
		assertNotEquals(game.sides.get(0).team.get(0).getId(), game.sides.get(1).team.get(0).getId());
		assertNotEquals(game.sides.get(0).team.get(0).getSide(), game.sides.get(1).team.get(0).getSide());
		assertEquals(game.sides.get(0).sideid, 0);
		assertEquals(game.sides.get(1).sideid, 1);
	}

	@Test
	void ShouldInitializeActivePokemonProperly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		ArrayList<BuildPokemon> team = new ArrayList<>();
		team.add(alakazambuild);
		Player p1 = new Player(null, team);
		Game game = new Game(p1, p1);
		assertEquals(game.sides.get(0).team.get(0), game.sides.get(0).active);
		assertEquals(game.sides.get(1).team.get(0), game.sides.get(1).active);
	}
}
