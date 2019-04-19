package pokemonaitests.game.moveusage;

import org.junit.Test;
import pokemonai.constants.*;
import pokemonai.data.moves.Generic90Move;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.*;
import pokemonai.game.events.DamageEvent;
import pokemonai.game.events.GameEvent;
import pokemonai.game.events.MoveUseEvent;
import pokemonai.teambuild.BuildPokemon;
import pokemonai.testing.DeterministicRandom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameDamageCalcTest {
  private static final BuildPokemon ALAKAZAM_BUILD =
      new BuildPokemon(
          Pokedex.getDexEntry("alakazam"),
          100,
          new MoveSet(),
          Nature.DOCILE,
          new HashMap<>(),
          new HashMap<>(),
          Optional.empty());

    private static final BuildPokemon BASTIODON_BUILD =
            new BuildPokemon(
                    Pokedex.getDexEntry("bastiodon"),
                    100,
                    new MoveSet(),
                    Nature.DOCILE,
                    new HashMap<>(),
                    new HashMap<>(),
                    Optional.empty());


    private static final BuildPokemon MAGNEZONE_BUILD =
            new BuildPokemon(
                    Pokedex.getDexEntry("magnezone"),
                    100,
                    new MoveSet(),
                    Nature.DOCILE,
                    new HashMap<>(),
                    new HashMap<>(),
                    Optional.empty());

  @Test
  public void shouldCalculateBaseDamageCorrectly() {
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    assertEquals(
        (int)
            new Game()
                .getBaseDamage(
                    alakazambattle,
                    bastiodonbattle,
                    new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                    false),
        75);

    alakazambattle.boost(Stat.SPA, 2);

    assertEquals(
        (int)
            new Game()
                .getBaseDamage(
                    alakazambattle,
                    bastiodonbattle,
                    new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                    false),
        149);
    bastiodonbattle.unboost(Stat.SPD, 2);

    assertEquals(
        (int)
            new Game()
                .getBaseDamage(
                    alakazambattle,
                    bastiodonbattle,
                    new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                    false),
        299);
  }

  @Test
  public void shouldProcessCritModifiersCorrectly() {
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                true,
                new DeterministicRandom(15))
            .damage,
        112);

    alakazambattle.unboost(Stat.SPA, 4);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                true,
                new DeterministicRandom(15))
            .damage,
        112);

    bastiodonbattle.boost(Stat.SPD, 4);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                true,
                new DeterministicRandom(15))
            .damage,
        112);

    assertTrue(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                true,
                new DeterministicRandom(15))
            .event
            .iscrit);
  }

  @Test
  public void shouldApplyTypeBonusCorrectly() {
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    DamageResult x2super =
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.WATER, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15));

    assertEquals(x2super.damage, 150);
    assertEquals(x2super.event.effectiveness, Effectiveness.SUPER);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.GROUND, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15))
            .damage,
        300);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.BUG, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15))
            .damage,
        37);

    DamageResult x4resist =
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.NORMAL, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15));

    assertEquals(x4resist.damage, 18);
    assertEquals(x4resist.event.effectiveness, Effectiveness.RESIST);

    assertEquals(
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15))
            .event
            .effectiveness,
        Effectiveness.NORMAL);

    DamageEvent immune =
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.POISON, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15))
            .event;

    assertEquals(immune.effectiveness, Effectiveness.IMMUNE);
    assertEquals(immune.damage, 0.0);
  }

  @Test
  public void shouldApplySTABCorrectly() {
    // 145 ATK 265 SPA
    FullPokemon magnezonebattle = new FullPokemon(MAGNEZONE_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    assertEquals(
        new Game()
            .getModifiedDamage(
                magnezonebattle,
                bastiodonbattle,
                new Generic90Move(PokeType.ELECTRIC, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15))
            .damage,
        109);
  }

  @Test
  public void shouldBuildCorrectEventChain() {
    BattleMove usedmove = new BattleMove(new Generic90Move(PokeType.FAIRY, MoveCategory.SPECIAL));

    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    alakazambattle.boost(BattleStat.CRITICALHIT, 3);
    ArrayList<GameEvent> eventChain = new Game().useMove(alakazambattle, bastiodonbattle, usedmove);
    MoveUseEvent moveEvent = (MoveUseEvent) eventChain.get(0);

    assertEquals(moveEvent.move, usedmove.baseMove);

    DamageEvent damageEvent = (DamageEvent) eventChain.get(1);
    assertEquals(damageEvent.effectiveness, Effectiveness.RESIST);
    assertTrue(damageEvent.hit);
    assertTrue(damageEvent.iscrit);
    assertEquals(usedmove.pp, 9);

    assertEquals((double) bastiodonbattle.getHp(), (1.0 - damageEvent.damage) * 230);
  }

  @Test
  public void DamageResultValuesShouldCorroborate() {
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    DamageResult x2super =
        new Game()
            .getModifiedDamage(
                alakazambattle,
                bastiodonbattle,
                new Generic90Move(PokeType.WATER, MoveCategory.SPECIAL),
                false,
                new DeterministicRandom(15));

    assertEquals(x2super.event.damage * bastiodonbattle.basePokemon.getStat(Stat.HP), 150.0);
  }

  @Test
  public void ShouldProperlyConstructEventPokemon() {
    BattleMove usedmove = new BattleMove(new Generic90Move(PokeType.PSYCHIC, MoveCategory.SPECIAL));
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(ALAKAZAM_BUILD, 0, 0);
    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(BASTIODON_BUILD, 1, 0);

    ArrayList<GameEvent> eventChain = new Game().useMove(alakazambattle, bastiodonbattle, usedmove);
    DamageEvent damageEvent = (DamageEvent) eventChain.get(1);
    EventPokemon containerUnderTest = new EventPokemon(bastiodonbattle);
    assertEquals(containerUnderTest.hp, 1.0 - damageEvent.damage);
  }
}
