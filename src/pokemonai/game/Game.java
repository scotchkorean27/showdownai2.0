package pokemonai.game;

import java.util.Arrays;
import java.util.Random;

import pokemonai.constants.MoveCategory;
import pokemonai.constants.Stat;
import pokemonai.constants.TypeChart;
import pokemonai.data.moves.Move;

public class Game {
	
	public Game() {
		
	}
	
	public double getDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit) {
		Random rand = new Random();
		return getModifiedDamage(attacker, defender, move, isCrit, rand);
	}
	
	/* Takes random as an argument in the name of testing
	 * 
	 * Modifiers to be implemented:
	 * 
	 * Targets: IF (and that's a big IF) doubles ever happen
	 * Weather: When we implement weather
	 * Badge: Moot point
	 * Critical: Implemented
	 * random: Implemented
	 * STAB: Implemented* Adaptability will require modification of this code
	 * Type: Implemented
	 * Burn: Add when statuses are added
	 * Other: Add as needed
	 */
	public double getModifiedDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit, Random rand) {
		return Math.floor(getBaseDamage(attacker, defender, move, isCrit))
				* (isCrit ? 1.5 : 1.0) 
				* (((double) (rand.nextInt(16) + 85)) / 100)
				* (Arrays.asList(attacker.basePokemon.dexData.types).contains(move.type()) ? 1.5 : 1.0)
				* TypeChart.getMultiplier(move.type(), defender.basePokemon.dexData.types[0])
				* (defender.basePokemon.dexData.types.length > 1 ? TypeChart.getMultiplier(move.type(), defender.basePokemon.dexData.types[1]) : 1.0);
	}
	
	// TODO: Figure out how to process base damage modifiers (Acrobatics, etc.)
	public double getBaseDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit) {
		Stat attackingStat = Stat.ATK;
		Stat defendingStat = Stat.DEF;
		if (move.category().equals(MoveCategory.SPECIAL)) {
			attackingStat = Stat.SPA;
			defendingStat = Stat.SPD;
		}
		return ((((2 * attacker.basePokemon.level / 5) + 2) 
				* move.baseDamage()
				* Math.floor(attacker.basePokemon.getStat(attackingStat) * (isCrit && attacker.getStatMult(attackingStat) < 1 ? 1 : attacker.getStatMult(attackingStat)))
				/ Math.floor(defender.basePokemon.getStat(defendingStat) * (isCrit && defender.getStatMult(defendingStat) > 1 ? 1 : defender.getStatMult(defendingStat))))
				 / 50) + 2;
	}
}
