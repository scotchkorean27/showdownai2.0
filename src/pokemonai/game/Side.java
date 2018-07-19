package pokemonai.game;

import java.util.List;

public class Side {
	public final List<BattlePokemon> team;
	public final int sideid;
	public BattlePokemon active;

	public Side(List<BattlePokemon> team, int sideid) {
		this.team = team;
		this.sideid = sideid;
		this.active = this.team.get(0);
	}
}
