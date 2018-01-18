package pokemonai.data.moves;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.PokeType;

public class Tackle extends Move {
	public int baseDamage() {
		return 40;
	}

	@Override
	public int basePP() {
		return 35;
	}

	@Override
	public PokeType type() {
		return PokeType.NORMAL;
	}

	@Override
	public MoveCategory category() {
		return MoveCategory.PHYSICAL;
	}

	@Override
	public int accuracy() {
		return 100;
	}

}
