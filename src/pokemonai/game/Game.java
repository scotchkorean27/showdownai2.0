package pokemonai.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Effectiveness;
import pokemonai.constants.MoveCategory;
import pokemonai.constants.Stat;
import pokemonai.constants.TypeChart;
import pokemonai.data.moves.Move;
import pokemonai.game.events.DamageEvent;
import pokemonai.game.events.GameEvent;
import pokemonai.game.events.MoveUseEvent;

public class Game {

	public Game() {

	}

	public ArrayList<GameEvent> useMove(BattlePokemon user, BattlePokemon target, Move move) {
		ArrayList<GameEvent> eventChain = new ArrayList<>();
		eventChain.add(new MoveUseEvent(user, target, move));
		if (!move.category().equals(MoveCategory.STATUS)) {
			// TODO: Some moves and effects will modify accuracy. We need to implement this.
			double totalacc = (double) move.accuracy() * user.getStatMult(BattleStat.ACCURACY)
					/ target.getStatMult(BattleStat.EVASION);
			if (new Random().nextDouble() <= totalacc) {
				// TODO: Things will modify this. We need to implement this
				boolean crit = new Random().nextDouble() <= user.getStatMult(BattleStat.CRITICALHIT);
				DamageEvent damageEvent = this.getDamage(user, target, move, crit);
				target.hp -= damageEvent.damage;
				eventChain.add(damageEvent);
			} else {
				eventChain.add(DamageEvent.miss(target));
			}
		} else {
			// TODO: Status moves will likely kill me
		}
		return eventChain;
	}

	public DamageEvent getDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit) {
		Random rand = new Random();
		return getModifiedDamage(attacker, defender, move, isCrit, rand);
	}

	/*
	 * Takes random as an argument in the name of testing
	 * 
	 * Modifiers to be implemented:
	 * 
	 * Targets: IF (and that's a big IF) doubles ever happen Weather: When we
	 * implement weather Badge: Moot point Critical: Implemented random: Implemented
	 * STAB: Implemented* Adaptability will require modification of this code Type:
	 * Implemented Burn: Add when statuses are added Other: Add as needed
	 */
	public DamageEvent getModifiedDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit,
			Random rand) {
		int damage = (int) (Math.floor(getBaseDamage(attacker, defender, move, isCrit)) * (isCrit ? 1.5 : 1.0)
				* (((double) (rand.nextInt(16) + 85)) / 100));
		double stab = (Arrays.asList(attacker.basePokemon.dexData.types).contains(move.type()) ? 1.5 : 1.0);
		double typemult = TypeChart.getMultiplier(move.type(), defender.basePokemon.dexData.types[0])
				* (defender.basePokemon.dexData.types.length > 1
						? TypeChart.getMultiplier(move.type(), defender.basePokemon.dexData.types[1])
						: 1.0);
		return new DamageEvent(defender, true, isCrit, Effectiveness.getEffectiveness(typemult),
				(int) (damage * stab * typemult), false);
	}

	// TODO: Figure out how to process base damage modifiers (Acrobatics, etc.)
	public double getBaseDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit) {
		Stat attackingStat = Stat.ATK;
		Stat defendingStat = Stat.DEF;
		if (move.category().equals(MoveCategory.SPECIAL)) {
			attackingStat = Stat.SPA;
			defendingStat = Stat.SPD;
		}
		return ((((2 * attacker.basePokemon.level / 5) + 2) * move.baseDamage()
				* Math.floor(attacker.basePokemon.getStat(attackingStat)
						* (isCrit && attacker.getStatMult(attackingStat) < 1 ? 1 : attacker.getStatMult(attackingStat)))
				/ Math.floor(defender.basePokemon.getStat(defendingStat)
						* (isCrit && defender.getStatMult(defendingStat) > 1 ? 1
								: defender.getStatMult(defendingStat))))
				/ 50) + 2;
	}
}
