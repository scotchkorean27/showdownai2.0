package pokemonai.game;

import java.util.ArrayList;

public class Side {
	ArrayList<BattlePokemon> team = new ArrayList();
	public final int sideid;

	public Side(ArrayList<BattlePokemon> team, int sideid) {
		this.team = team;
		this.sideid = sideid;
	}
}
