package pokemonaitests.game.moveusage;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import pokemonai.constants.Nature;
import pokemonai.data.moves.Struggle;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.BattleMove;
import pokemonai.game.FullPokemon;
import pokemonai.game.Game;
import pokemonai.game.events.DamageEvent;
import pokemonai.game.events.GameEvent;
import pokemonai.teambuild.BuildPokemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;

public class MoveRecoilTest {
  @Test
  public void shouldDealMaxHpRecoilDamage() {
    BattleMove usedMove = new BattleMove(new Struggle());

    BuildPokemon alakazambuild =
        new BuildPokemon(
            Pokedex.getDexEntry("alakazam"),
            100,
            new MoveSet(),
            Nature.DOCILE,
            new HashMap<>(),
            new HashMap<>(),
            Optional.empty());
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(alakazambuild, 0, 0);
    BuildPokemon bastiodonbuild =
        new BuildPokemon(
            Pokedex.getDexEntry("bastiodon"),
            100,
            new MoveSet(),
            Nature.DOCILE,
            new HashMap<>(),
            new HashMap<>(),
            Optional.empty());

    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(bastiodonbuild, 1, 0);
    ArrayList<GameEvent> struggle = new Game().useMove(alakazambattle, bastiodonbattle, usedMove);
    assertEquals(
        alakazambattle.getHp(),
        (int) (alakazambattle.getMaxHp() * (1 - usedMove.baseMove.maxHpRecoil().get())));
  }

  @Test
  public void shouldCreateRecoilDamageEvent() {
    BattleMove usedMove = new BattleMove(new Struggle());

    BuildPokemon alakazambuild =
        new BuildPokemon(
            Pokedex.getDexEntry("alakazam"),
            100,
            new MoveSet(),
            Nature.DOCILE,
            new HashMap<>(),
            new HashMap<>(),
            Optional.empty());
    // 105 ATK 275 SPA
    FullPokemon alakazambattle = new FullPokemon(alakazambuild, 0, 0);
    BuildPokemon bastiodonbuild =
        new BuildPokemon(
            Pokedex.getDexEntry("bastiodon"),
            100,
            new MoveSet(),
            Nature.DOCILE,
            new HashMap<>(),
            new HashMap<>(),
            Optional.empty());

    // 341 DEF 281 SPD
    FullPokemon bastiodonbattle = new FullPokemon(bastiodonbuild, 1, 0);
    ArrayList<GameEvent> struggle = new Game().useMove(alakazambattle, bastiodonbattle, usedMove);
    DamageEvent recoilEvent =
        (DamageEvent)
            struggle.stream()
                .filter(event -> event.eventType().equals(GameEvent.EventType.RECOIL))
                .collect(ImmutableList.toImmutableList())
                .get(0);
    assertEquals(
       recoilEvent.victim.id, alakazambattle.id);

    assertEquals(recoilEvent.damage, usedMove.baseMove.maxHpRecoil().get());
  }
}
