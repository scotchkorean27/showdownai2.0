package pokemonai.agent;

import java.util.List;
import java.util.Random;

import pokemonai.game.Decision;
import pokemonai.game.Game;

public class RandomAgent extends Agent {

	@Override
	public Decision decide(Game gameState, List<Decision> options, int sideid) {
		Random rand = new Random();
		return options.get(rand.nextInt(options.size()));
	}

	@Override
	public Decision forceSwitch(Game gameState, List<Decision> options, int sideid) {
		return this.decide(gameState, options, sideid);
	}

}
