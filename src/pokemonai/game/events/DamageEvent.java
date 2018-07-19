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

	public DamageEvent(BattlePokemon victim, boolean hit, boolean iscrit, Effectiveness effectiveness, int damage) {
		// Contains the victim's data BEFORE the damage event.
		this.victim = new EventPokemon(victim);
		this.hit = hit;
		this.iscrit = iscrit;
		this.effectiveness = effectiveness;
		this.damage = (double) damage / victim.getStat(Stat.HP);
	}

	public static DamageEvent miss(BattlePokemon target) {
		return new DamageEvent(target, false, false, null, 0);
	}
}
