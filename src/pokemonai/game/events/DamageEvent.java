package pokemonai.game.events;

import pokemonai.constants.Effectiveness;
import pokemonai.game.BattlePokemon;

public class DamageEvent extends GameEvent {
	public final BattlePokemon victim;
	public final boolean hit;
	public final boolean iscrit;
	public final int damage;
	public final Effectiveness effectiveness;
	public final boolean damageispercentage;

	public DamageEvent(BattlePokemon victim, boolean hit, boolean iscrit, Effectiveness effectiveness, int damage,
			boolean damageispercentage) {
		this.victim = victim;
		this.hit = hit;
		this.iscrit = iscrit;
		this.effectiveness = effectiveness;
		this.damage = damage;
		this.damageispercentage = damageispercentage;
	}

	public static DamageEvent miss(BattlePokemon target) {
		return new DamageEvent(target, false, false, null, 0, false);
	}
}
