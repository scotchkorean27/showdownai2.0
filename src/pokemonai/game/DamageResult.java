package pokemonai.game;

import pokemonai.game.events.DamageEvent;

public class DamageResult {
	public final DamageEvent event;
	public final int damage;

	public DamageResult(DamageEvent event, int damage) {
		this.event = event;
		this.damage = damage;
	}
}
