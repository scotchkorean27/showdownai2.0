package pokemonaitests.game;

import org.junit.Test;
import pokemonai.constants.*;
import pokemonai.data.moves.Generic90Move;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.data.pokedex.MoveSet;
import pokemonai.data.pokedex.Pokedex;
import pokemonai.game.BattleMove;
import pokemonai.game.FullPokemon;
import pokemonai.teambuild.BuildPokemon;

import java.util.HashMap;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotSame;

public class BattlePokemonTest {
    @Test
    public void shouldReturnStatMult1WhenNoBoosts() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 1);
        assertEquals(garchompbattle.getStatMult(BattleStat.ACCURACY), (double) 1);
    }

    @Test
    public void shouldReturnPositiveStatBoosts() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), 1.5);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), 2.5);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 3);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), 3.5);
        garchompbattle.boost(Stat.ATK, 1);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 4);
    }

    @Test
    public void shouldReturnNegativeStatBoosts() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
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
    public void shouldNotBoostStatPastNeg6() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
        garchompbattle.unboost(Stat.ATK, 7);
        assertEquals(garchompbattle.getStatMult(Stat.ATK), (double) 2 / 8);
    }

    @Test
    public void shouldReturnPositiveSecStatBoosts() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
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
    public void shouldReturnNegativeSecStatBoosts() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
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
    public void shouldProperlyDeepCopy() {
        DexEntry garchompdex = Pokedex.getDexEntry("garchomp");
        BuildPokemon garchompbuild = new BuildPokemon(garchompdex, 78, new MoveSet(), Nature.ADAMANT,
                new HashMap<>(), new HashMap<>(), Optional.empty());
        FullPokemon garchompbattle = new FullPokemon(garchompbuild, 0, 0);
        garchompbattle.unboost(BattleStat.ACCURACY, 1);
        garchompbattle.unboost(Stat.ATK, 1);
        garchompbattle.moves.add(new BattleMove(new Generic90Move(PokeType.FAIRY, MoveCategory.SPECIAL)));
        FullPokemon garchompclone = (FullPokemon) garchompbattle.deepCopy();
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
        assertEquals(garchompbattle.getHp(), garchompclone.getHp());
        assertEquals(garchompbattle.side, garchompclone.side);
        assertEquals(garchompbattle.id, garchompclone.id);
    }
}
