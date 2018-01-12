package pokemonai.game;

import java.util.HashMap;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Stat;
import pokemonai.teambuild.BuildPokemon;

public class BattlePokemon {
	BuildPokemon basePokemon;
	HashMap<Stat, Integer> boosts = new HashMap<>();
	HashMap<BattleStat, Integer> secondaryBoosts = new HashMap<>();
	int hp;
	
	public BattlePokemon(BuildPokemon basePokemon) {
		this.basePokemon = basePokemon;
		this.hp = this.basePokemon.getStat(Stat.HP);
	}
	
	public double getStatMult(Stat stat) {
		if (!this.boosts.containsKey(stat)) {
			return 1;
		}
		else {
			double coeffvar = Math.abs(this.boosts.get(stat)) + 2;
			if (this.boosts.get(stat) > 0) {
				return coeffvar / 2;
			}
			else {
				return 2 / coeffvar;
			}
		}
	}
	
	public double getStatMult(BattleStat stat) {
		if (!this.secondaryBoosts.containsKey(stat)) {
			return 1;
		}
		else {
			double coeffvar = Math.abs(this.secondaryBoosts.get(stat)) + 3;
			if (this.secondaryBoosts.get(stat) > 0) {
				return coeffvar / 3;
			}
			else {
				return 3 / coeffvar;
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
		}
		else if(this.boosts.get(stat) < -6) {
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
		}
		else if(this.secondaryBoosts.get(stat) < -6) {
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
		}
		else if(this.boosts.get(stat) < -6) {
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
		}
		else if(this.secondaryBoosts.get(stat) < -6) {
			this.secondaryBoosts.put(stat, -6);
		}
	}
}
