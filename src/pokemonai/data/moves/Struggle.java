package pokemonai.data.moves;

import java.util.Optional;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.PokeType;

public class Struggle extends Move {

	@Override
	public int baseDamage() {
		return 50;
	}

	@Override
	public int basePP() {
		return 1;
	}

	@Override
	public PokeType type() {
		return null;
	}

	@Override
	public MoveCategory category() {
		return MoveCategory.PHYSICAL;
	}

	@Override
	public int accuracy() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String name() {
		return "Struggle";
	}

	@Override
	public Optional<Double> maxHpRecoil() {
		return Optional.of(0.25);
	}

}
