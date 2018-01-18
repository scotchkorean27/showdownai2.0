package pokemonai.data.moves;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.PokeType;

public class Generic90Move extends Move {

	private final PokeType type;
	private final MoveCategory category;

	public Generic90Move(PokeType type, MoveCategory category) {
		this.type = type;
		this.category = category;
	}

	@Override
	public int baseDamage() {
		return 90;
	}

	@Override
	public int basePP() {
		return 10;
	}

	@Override
	public PokeType type() {
		return this.type;
	}

	@Override
	public MoveCategory category() {
		return this.category;
	}

	@Override
	public int accuracy() {
		return 100;
	}
}
