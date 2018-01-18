package pokemonaitests.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Effectiveness;
import pokemonai.constants.MoveCategory;
import pokemonai.constants.Nature;
import pokemonai.constants.PokeType;
import pokemonai.constants.Stat;
import pokemonai.data.moves.Generic90Move;
import pokemonai.data.moves.Move;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.BattlePokemon;
import pokemonai.game.Game;
import pokemonai.game.events.DamageEvent;
import pokemonai.game.events.GameEvent;
import pokemonai.game.events.MoveUseEvent;
import pokemonai.teambuild.BuildPokemon;

class GameTest {

	@Test
	void shouldCalculateBaseDamageCorrectly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild, 0);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild, 1);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 75);
		alakazambattle.boost(Stat.SPA, 2);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 149);
		bastiodonbattle.unboost(Stat.SPD, 2);
		assertEquals((int) new Game().getBaseDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false), 299);
	}

	class DeterministicRandom extends Random {
		private static final long serialVersionUID = 1L;

		@Override
		public int nextInt(int whatever) {
			return 15;
		}
	}

	@Test
	void shouldProcessCritModifiersCorrectly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild, 0);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild, 1);
		assertEquals(new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()).damage,
				112);
		alakazambattle.unboost(Stat.SPA, 4);
		assertEquals(new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()).damage,
				112);
		bastiodonbattle.boost(Stat.SPD, 4);
		assertEquals(new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()).damage,
				112);
		assertEquals(new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), true, new DeterministicRandom()).iscrit,
				true);
	}

	@Test
	void shouldApplyTypeBonusCorrectly() {
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild, 0);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild, 1);
		DamageEvent x2super = new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.WATER, MoveCategory.SPECIAL), false, new DeterministicRandom());
		assertEquals(x2super.damage, 150);
		assertEquals(x2super.effectiveness, Effectiveness.SUPER);
		assertEquals(new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.GROUND, MoveCategory.SPECIAL), false, new DeterministicRandom()).damage,
				300);
		assertEquals(
				new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
						new Generic90Move(PokeType.BUG, MoveCategory.SPECIAL), false, new DeterministicRandom()).damage,
				37);
		DamageEvent x4resist = new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.NORMAL, MoveCategory.SPECIAL), false, new DeterministicRandom());
		assertEquals(x4resist.damage, 18);
		assertEquals(x4resist.effectiveness, Effectiveness.RESIST);
		assertEquals(
				new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
						new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false,
						new DeterministicRandom()).effectiveness,
				Effectiveness.NORMAL);
		DamageEvent immune = new Game().getModifiedDamage(alakazambattle, bastiodonbattle,
				new Generic90Move(PokeType.POISON, MoveCategory.SPECIAL), false,
				new DeterministicRandom());
		assertEquals(immune.effectiveness, Effectiveness.IMMUNE);
		assertEquals(immune.damage, 0);
	}

	@Test
	void shouldApplySTABCorrectly() {
		BuildPokemon magnezonebuild = new BuildPokemon(Pokedex.getDexEntry("magnezone"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 145 ATK 265 SPA
		BattlePokemon magnezonebattle = new BattlePokemon(magnezonebuild, 0);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild, 1);
		assertEquals(new Game().getModifiedDamage(magnezonebattle, bastiodonbattle,
				new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL), false, new DeterministicRandom()).damage,
				109);
	}

	@Test
	void shouldBuildCorrectEventChain() {
		Move usedmove = new Generic90Move(PokeType.FAIRY, MoveCategory.SPECIAL);
		BuildPokemon alakazambuild = new BuildPokemon(Pokedex.getDexEntry("alakazam"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 105 ATK 275 SPA
		BattlePokemon alakazambattle = new BattlePokemon(alakazambuild, 0);
		BuildPokemon bastiodonbuild = new BuildPokemon(Pokedex.getDexEntry("bastiodon"), 100, new MoveSet(),
				Nature.DOCILE, new HashMap<>(), new HashMap<>(), Optional.empty());
		// 341 DEF 281 SPD
		BattlePokemon bastiodonbattle = new BattlePokemon(bastiodonbuild, 1);
		alakazambattle.boost(BattleStat.CRITICALHIT, 3);
		ArrayList<GameEvent> eventChain = new Game().useMove(alakazambattle, bastiodonbattle, usedmove);
		MoveUseEvent moveEvent = (MoveUseEvent) eventChain.get(0);
		assertEquals(moveEvent.move, usedmove);
		assertEquals(moveEvent.user, alakazambattle);
		assertEquals(moveEvent.target, bastiodonbattle);
		DamageEvent damageEvent = (DamageEvent) eventChain.get(1);
		assertEquals(damageEvent.damageispercentage, false);
		assertEquals(damageEvent.effectiveness, Effectiveness.RESIST);
		assertEquals(damageEvent.hit, true);
		assertEquals(damageEvent.iscrit, true);
		assertEquals(damageEvent.victim, bastiodonbattle);
	}

}
