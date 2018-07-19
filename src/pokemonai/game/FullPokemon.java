package pokemonai.game;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;
import pokemonai.teambuild.BuildPokemon;

public class FullPokemon implements BattlePokemon {
	// The fact that this reference can change drives me insane
	public BuildPokemon basePokemon;
	public final Map<Stat, Integer> boosts;
	public final Map<BattleStat, Integer> secondaryBoosts;
	public final Set<BattleMove> moves;
	private int hp;
	public final int side;
	public final int id;

	public FullPokemon(BuildPokemon basePokemon, int side, int id) {
		this.basePokemon = basePokemon;
		this.boosts = new HashMap<>();
		this.secondaryBoosts = new HashMap<>();
		this.moves = basePokemon.moveset.stream().map(item -> new BattleMove(item)).collect(Collectors.toSet());
		this.hp = this.basePokemon.getStat(Stat.HP);
		this.id = id;
		this.side = side;
	}

	public FullPokemon(FullPokemon original) {
		this.basePokemon = original.basePokemon;
		this.boosts = new HashMap<>(original.boosts);
		this.secondaryBoosts = new HashMap<>(original.secondaryBoosts);
		this.moves = original.moves.stream().map(item -> new BattleMove(item)).collect(Collectors.toSet());
		this.hp = original.hp;
		this.side = original.side;
		this.id = original.id;
	}

	public double getStatMult(Stat stat) {
		if (!this.boosts.containsKey(stat)) {
			return 1;
		} else {
			double coeffvar = Math.abs(this.boosts.get(stat)) + 2;
			if (this.boosts.get(stat) > 0) {
				return coeffvar / 2;
			} else {
				return 2 / coeffvar;
			}
		}
	}

	public double getStatMult(BattleStat stat) {
		if (stat.equals(BattleStat.CRITICALHIT)) {
			if (!this.secondaryBoosts.containsKey(BattleStat.CRITICALHIT)
					|| this.secondaryBoosts.get(BattleStat.CRITICALHIT) <= 0) {
				return (double) 1 / 24;
			} else {
				if (this.secondaryBoosts.get(BattleStat.CRITICALHIT) == 1) {
					return (double) 1 / 8;
				} else if (this.secondaryBoosts.get(BattleStat.CRITICALHIT) == 2) {
					return (double) 1 / 2;
				} else {
					return 1;
				}
			}
		} else {
			if (!this.secondaryBoosts.containsKey(stat)) {
				return 1;
			} else {
				double coeffvar = Math.abs(this.secondaryBoosts.get(stat)) + 3;
				if (this.secondaryBoosts.get(stat) > 0) {
					return coeffvar / 3;
				} else {
					return 3 / coeffvar;
				}
			}
		}
	}

	public void boost(Stat stat, int boostamt) {
		if (!this.boosts.containsKey(stat)) {
			this.boosts.put(stat, 0);
		}
		this.boosts.put(stat, this.boosts.get(stat) + boostamt);
		if (this.boosts.get(stat) > 6) {
			this.boosts.put(stat, 6);
		} else if (this.boosts.get(stat) < -6) {
			this.boosts.put(stat, -6);
		}
	}

	public void boost(BattleStat stat, int boostamt) {
		if (!this.secondaryBoosts.containsKey(stat)) {
			this.secondaryBoosts.put(stat, 0);
		}
		this.secondaryBoosts.put(stat, this.secondaryBoosts.get(stat) + boostamt);
		if (this.secondaryBoosts.get(stat) > 6) {
			this.secondaryBoosts.put(stat, 6);
		} else if (this.secondaryBoosts.get(stat) < -6) {
			this.secondaryBoosts.put(stat, -6);
		}
	}

	public void unboost(Stat stat, int boostamt) {
		if (!this.boosts.containsKey(stat)) {
			this.boosts.put(stat, 0);
		}
		this.boosts.put(stat, this.boosts.get(stat) - boostamt);
		if (this.boosts.get(stat) > 6) {
			this.boosts.put(stat, 6);
		} else if (this.boosts.get(stat) < -6) {
			this.boosts.put(stat, -6);
		}
	}

	public void unboost(BattleStat stat, int boostamt) {
		if (!this.secondaryBoosts.containsKey(stat)) {
			this.secondaryBoosts.put(stat, 0);
		}
		this.secondaryBoosts.put(stat, this.secondaryBoosts.get(stat) - boostamt);
		if (this.secondaryBoosts.get(stat) > 6) {
			this.secondaryBoosts.put(stat, 6);
		} else if (this.secondaryBoosts.get(stat) < -6) {
			this.secondaryBoosts.put(stat, -6);
		}
	}

	public int getHp() {
		return this.hp;
	}

	public void damage(int damage) {
		this.hp -= damage;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public int getSide() {
		return this.side;
	}

	@Override
	public int getMaxHp() {
		return this.basePokemon.getStat(Stat.HP);
	}

	@Override
	public int getStat(Stat stat) {
		return this.basePokemon.getStat(stat);
	}

	@Override
	public int getLevel() {
		return this.basePokemon.level;
	}

	@Override
	public DexEntry getDexData() {
		return this.basePokemon.dexData;
	}

	@Override
	public BattlePokemon deepCopy() {
		return new FullPokemon(this);
	}
}
