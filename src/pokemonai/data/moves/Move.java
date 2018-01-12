package pokemonai.data.moves;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.PokeType;

public abstract class Move {
	public abstract int baseDamage();
	public abstract int basePP();
	public abstract PokeType type();
	public abstract MoveCategory category();
	public abstract int accuracy();
}
