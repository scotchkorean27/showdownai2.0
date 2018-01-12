package pokemonaitests.game;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.Nature;
import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.data.moves.Generic90Move;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.BattlePokemon;
import pokemonai.game.Game;
import pokemonai.teambuild.BuildPokemon;

class GameTest {

	@Test
	void shouldCalculateBaseDamageCorrectly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(), Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(), Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 75);
		alakazambattle.boost(Stat.SPA, 2);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 149);
		bastiodonbattle.unboost(Stat.SPD, 2);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 299);
	}
	
	class DeterministicRandom extends Random {
		@Override
		public int nextInt(int whatever) {
			return 15;
		}
	}

	@Test
	void shouldProcessCritModifiersCorrectly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(), Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(), Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild);
		assertEquals((int) new Game().getModifiedDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()), 112);
		alakazambattle.unboost(Stat.SPA, 4);
		assertEquals((int) new Game().getModifiedDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()), 112);
		bastiodonbattle.boost(Stat.SPD, 4);
		assertEquals((int) new Game().getModifiedDamage(alakazambattle, bastiodonbattle, new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()), 112);
	}
	
	@Test
	void shouldApplyTypeBonusCorrectly() {
		
	}
	
	@Test
	void shouldApplySTABCorrectly() {
		
	}
}
