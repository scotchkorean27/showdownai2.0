package pokemonai.testing;

import java.util.Random;

public class DeterministicRandom extends Random {
    private final int returnInt;

    public DeterministicRandom(int returnThisAlways) {
        returnInt = returnThisAlways;
    }

    @Override
    public int nextInt(int whatever) {
        return returnInt;
    }
}