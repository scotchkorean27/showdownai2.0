package pokemonai.game;

// An immutable object for storing information about an event.
public class EventPokemon {
	public final double hp;
	public final int side;
	public final int id;
	// public final Status status;
	
	public EventPokemon(BattlePokemon base) {
		this.hp = (double) base.getHp() / base.getMaxHp();
		this.side = base.getSide();
		this.id = base.getId();
	}
}
