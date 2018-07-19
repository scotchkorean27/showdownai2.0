package pokemonai.agent;

import java.util.List;
import java.util.concurrent.Callable;

import pokemonai.game.Decision;
import pokemonai.game.Game;

public abstract class Agent implements Callable<Decision> {
	private Game gameState;
	private List<Decision> options;
	private int sideid;
	private RequestType requesttype;
	private boolean injectedBeforeCall;
	
	public void injectParameters(Game gameState, List<Decision> options, int sideid, RequestType requestType) {
		this.gameState = gameState;
		this.options = options;
		this.sideid = sideid;
		this.requesttype = requestType;
		this.injectedBeforeCall = true;
	}

	@Override
	public Decision call() throws Exception {
		this.injectedBeforeCall = false;
		if (injectedBeforeCall) {
			throw new Exception(
					"Parameters were not injected before call!  Call injectParameters(gameState, options, sideid, requestType) before invoking call.");
		}
		if (this.requesttype.equals(RequestType.MOVE)) {
			return this.decide(this.gameState, this.options, this.sideid);
		}
		else {
			return this.forceSwitch(this.gameState, this.options, this.sideid);
		}
	}

	public abstract Decision decide(Game gameState, List<Decision> options, int sideid);

	public abstract Decision forceSwitch(Game gameState, List<Decision> options, int sideid);
}
