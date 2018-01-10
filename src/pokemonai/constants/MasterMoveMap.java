package pokemonai.constants;

import java.util.HashMap;

import pokemonai.data.moves.Absorb;
import pokemonai.data.moves.Move;

public class MasterMoveMap {
	private static final HashMap<String, Move> masterMoveMap;
	
	static {
		masterMoveMap = new HashMap<String, Move>();
		masterMoveMap.put("absorb", new Absorb());
	}
	
	public static Move getMove(String movename) {
		if(masterMoveMap.containsKey(movename)) {
			return masterMoveMap.get(movename);
		}
		else {
			throw new UnsupportedOperationException(movename + " either does not exist or has not been implemented yet.");
		}
	}
}
