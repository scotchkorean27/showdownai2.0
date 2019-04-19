package pokemonai.game.events;

import pokemonai.constants.Effectiveness;
import pokemonai.constants.Stat;
import pokemonai.game.BattlePokemon;
import pokemonai.game.EventPokemon;

public class DamageEvent extends GameEvent {
	public final EventPokemon victim;
	public final boolean hit;
	public final boolean iscrit;
	public final double damage;
	public final Effectiveness effectiveness;
	private final EventType eventType;

	public DamageEvent(BattlePokemon victim, boolean hit, boolean iscrit, Effectiveness effectiveness, int damage, EventType eventType) {
		// Contains the victim's data BEFORE the damage event.
		this.victim = new EventPokemon(victim);
		this.hit = hit;
		this.iscrit = iscrit;
		this.effectiveness = effectiveness;
		this.damage = (double) damage / victim.getStat(Stat.HP);
		this.eventType = eventType;
	}

	@Override
	public EventType eventType() {
		return eventType;
	}
}
