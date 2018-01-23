package pokemonai.game;

import pokemonai.constants.Stat;

// An immutable object for storing information about an event.
public class EventPokemon {
	public final double hp;
	public final int side;
	public final int id;
	// public final Status status;
	
	public EventPokemon(BattlePokemon base) {
		this.hp = (double) base.hp / base.basePokemon.getStat(Stat.HP);
		this.side = base.side;
		this.id = base.id;
	}
}
