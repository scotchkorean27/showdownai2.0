package pokemonai.game.events;

import pokemonai.data.moves.Move;
import pokemonai.game.BattlePokemon;
import pokemonai.game.EventPokemon;

public class MoveUseEvent extends GameEvent {
	public final Move move;
	public final EventPokemon user;
	public final EventPokemon target;

	public MoveUseEvent(BattlePokemon user, BattlePokemon target, Move move) {
		this.move = move;
		this.user = new EventPokemon(user);
		this.target = new EventPokemon(target);
	}
}
