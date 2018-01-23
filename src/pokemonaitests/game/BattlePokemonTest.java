package pokemonaitests.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import pokemonai.constants.BattleStat;
import pokemonai.constants.MoveCategory;
import pokemonai.constants.Nature;
import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.data.moves.Generic90Move;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.BattleMove;
import pokemonai.game.BattlePokemon;
import pokemonai.teambuild.BuildPokemon;

public class BattlePokemonTest {
	@Test
	void shouldReturnStatMult1WhenNoBoosts() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 1);
	}

	@Test
	void shouldReturnPositiveStatBoosts() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 1.5);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2.5);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 3);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 3.5);
		garchompbattle.boost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 4);
	}

	@Test
	void shouldReturnNegativeStatBoosts() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 3);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 4);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 5);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 6);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 7);
		garchompbattle.unboost(Stat.ATK, 1);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 8);
	}

	@Test
	void shouldNotBoostStatPastNeg6() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.unboost(Stat.ATK, 7);
		assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 8);
	}

	@Test
	void shouldReturnPositiveSecStatBoosts() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 4 / 3);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 5 / 3);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 6 / 3);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 7 / 3);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 8 / 3);
		garchompbattle.boost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 9 / 3);
	}

	@Test
	void shouldReturnNegativeSecStatBoosts() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 4);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 5);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 6);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 7);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 8);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 3 / 9);
	}

	@Test
	void shouldProperlyDeepCopy() {
		DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
		BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
				new HashMap<Stat, Integer>(), new HashMap<Stat, Integer>(), Optional.empty());
		BattlePokemon garchompbattle = new BattlePokemon(garchompbuild, 0, 0);
		garchompbattle.unboost(BattleStat.ACCURACY, 1);
		garchompbattle.unboost(Stat.ATK, 1);
		garchompbattle.moves.add(new BattleMove(new Generic90Move(PokeType.FAIRY, MoveCategory.SPECIAL)));
		BattlePokemon garchompclone = new BattlePokemon(garchompbattle);
		assertEquals(garchompbattle.basePokemon, garchompclone.basePokemon);
		assertEquals(garchompbattle.boosts, garchompclone.boosts);
		assertNotSame(garchompbattle.boosts, garchompclone.boosts);
		assertEquals(garchompbattle.secondaryBoosts, garchompclone.secondaryBoosts);
		assertNotSame(garchompbattle.secondaryBoosts, garchompclone.secondaryBoosts);
		assertNotSame(garchompbattle.moves, garchompclone.moves);
		BattleMove originalMove = garchompbattle.moves.iterator().next();
		BattleMove clonedMove = garchompclone.moves.iterator().next();
		assertEquals(originalMove.baseMove, clonedMove.baseMove);
		assertEquals(originalMove.pp, clonedMove.pp);
		assertNotSame(originalMove, clonedMove);
		assertEquals(garchompbattle.hp, garchompclone.hp);
		assertEquals(garchompbattle.side, garchompclone.side);
		assertEquals(garchompbattle.id, garchompclone.id);
	}
}
