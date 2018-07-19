package pokemonai.game.phase;

public class BlockingPhase extends GamePhase {
	public final PhaseName name;

	public BlockingPhase(PhaseName name) {
		this.name = name;
	}

	@Override
	public boolean isBlocking() {
		return true;
	}

	@Override
	public PhaseName name() {
		return this.name;
	}

}
