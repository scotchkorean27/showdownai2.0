package pokemonai.data.moves;

import java.util.HashMap;

public class MasterMoveMap {
	private static final HashMap<String, Move> masterMoveMap;

	static {
		masterMoveMap = new HashMap<String, Move>();
		masterMoveMap.put("tackle", new Tackle());
	}

	public static Move getMove(String movename) {
		if (masterMoveMap.containsKey(movename)) {
			return masterMoveMap.get(movename);
		} else {
			throw new UnsupportedOperationException(
					movename + " either does not exist or has not been implemented yet.");
		}
	}
}
