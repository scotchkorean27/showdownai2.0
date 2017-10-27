package pokemonai.game;

import java.util.ArrayList;
import java.util.HashMap;

import pokemonai.constants.Stat;

public class Side {
	ArrayList<Pokemon> team = new ArrayList();
	HashMap<Stat, Integer> stats = new HashMap();
	HashMap<Stat, Integer> boosts = new HashMap();
	
	public Side() {
	}
}
