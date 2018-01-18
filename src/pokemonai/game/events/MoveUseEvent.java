package pokemonai.game.events;

import pokemonai.data.moves.Move;
import pokemonai.game.BattlePokemon;

public class MoveUseEvent extends GameEvent {
	public final Move move;
	public final BattlePokemon user;
	public final BattlePokemon target;
	
	public MoveUseEvent(BattlePokemon user, BattlePokemon target, Move move) {
		this.move = move;
		this.user = user;
		this.target = target;
	}
}
