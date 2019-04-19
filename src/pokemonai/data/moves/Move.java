package pokemonai.data.moves;

import java.util.Optional;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.PokeType;

public abstract class Move {
    public abstract int baseDamage();

    public abstract int basePP();

    public abstract PokeType type();

    public abstract MoveCategory category();

    public abstract int accuracy();

    public abstract String name();

    public Optional<Double> recoil() {
        return Optional.empty();
    }

    public Optional<Double> maxHpRecoil() {
        return Optional.empty();
    }
}
