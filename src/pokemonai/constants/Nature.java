package pokemonai.constants;

import java.util.Optional;

public enum Nature {
	
	HARDY(Optional.empty(), Optional.empty()),
	LONELY(Optional.of(Stat.ATK), Optional.of(Stat.DEF)),
	BRAVE(Optional.of(Stat.ATK), Optional.of(Stat.SPE)),
	ADAMANT(Optional.of(Stat.ATK), Optional.of(Stat.SPA)),
	NAUGHTY(Optional.of(Stat.ATK), Optional.of(Stat.SPD)),
	BOLD(Optional.of(Stat.DEF), Optional.of(Stat.ATK)),
	DOCILE(Optional.empty(), Optional.empty()),
	RELAXED(Optional.of(Stat.DEF), Optional.of(Stat.SPE)),
	IMPISH(Optional.of(Stat.DEF), Optional.of(Stat.SPA)),
	LAX(Optional.of(Stat.DEF), Optional.of(Stat.SPD)),
	TIMID(Optional.of(Stat.SPE), Optional.of(Stat.ATK)),
	HASTY(Optional.of(Stat.SPE), Optional.of(Stat.DEF)),
	SERIOUS(Optional.empty(), Optional.empty()),
	JOLLY(Optional.of(Stat.SPE), Optional.of(Stat.SPA)),
	NAIVE(Optional.of(Stat.SPE), Optional.of(Stat.SPD)),
	MODEST(Optional.of(Stat.SPA), Optional.of(Stat.ATK)),
	MILD(Optional.of(Stat.SPA), Optional.of(Stat.DEF)),
	QUIET(Optional.of(Stat.SPA), Optional.of(Stat.SPE)),
	BASHFUL(Optional.empty(), Optional.empty()),
	RASH(Optional.of(Stat.SPA), Optional.of(Stat.SPD)),
	CALM(Optional.of(Stat.SPD), Optional.of(Stat.ATK)),
	GENTLE(Optional.of(Stat.SPD), Optional.of(Stat.DEF)),
	SASSY(Optional.of(Stat.SPD), Optional.of(Stat.SPE)),
	CAREFUL(Optional.of(Stat.SPD), Optional.of(Stat.SPA)),
	QUIRKY(Optional.empty(), Optional.empty());
	
	private final Optional<Stat> up;
	private final Optional<Stat> down;
	
	Nature(Optional<Stat> up, Optional<Stat> down) {
		this.up = up;
		this.down = down;
	}
	
	Optional<Stat> getUp() {
		return this.up;
	}
	
	Optional<Stat> getDown() {
		return this.down;
	}
}

