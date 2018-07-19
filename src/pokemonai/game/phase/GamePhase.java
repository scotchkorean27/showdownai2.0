package pokemonai.game.phase;

public abstract class GamePhase {
	public abstract boolean isBlocking();

	public abstract PhaseName name();
}
