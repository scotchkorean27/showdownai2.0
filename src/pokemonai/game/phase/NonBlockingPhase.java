package pokemonai.game.phase;

public class NonBlockingPhase extends GamePhase {

	public final PhaseName name;

	public NonBlockingPhase(PhaseName name) {
		this.name = name;
	}

	@Override
	public boolean isBlocking() {
		return false;
	}

	@Override
	public PhaseName name() {
		return this.name;
	}

}
