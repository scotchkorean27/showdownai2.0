package pokemonai.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import pokemonai.agent.Agent;
import pokemonai.constants.BattleStat;
import pokemonai.constants.Effectiveness;
import pokemonai.constants.MoveCategory;
import pokemonai.constants.Stat;
import pokemonai.constants.TypeChart;
import pokemonai.data.moves.Move;
import pokemonai.game.events.DamageEvent;
import pokemonai.game.events.GameEvent;
import pokemonai.game.events.MoveUseEvent;
import pokemonai.game.phase.BlockingPhase;
import pokemonai.game.phase.GamePhase;
import pokemonai.game.phase.NonBlockingPhase;
import pokemonai.game.phase.PhaseName;
import pokemonai.teambuild.BuildPokemon;

public class Game {

	public List<Side> sides;
	public List<Agent> agents;
	public Deque<GamePhase> gameQueue;

	public Game() {
		this.sides = new ArrayList<>();
		this.agents = new ArrayList<>();
		this.gameQueue = new LinkedList<>();
	};

	public Game(Player p1, Player p2) {
		this.sides = new ArrayList<>();
		this.agents = new ArrayList<>();
		this.gameQueue = new LinkedList<>();

		int pid = 0;
		ArrayList<BattlePokemon> team1 = new ArrayList<>();
		for (BuildPokemon buildpoke : p1.team) {
			team1.add(new FullPokemon(buildpoke, 0, pid));
			pid++;
		}
		this.sides.add(new Side(team1, 0));
		this.agents.add(p1.agent);

		ArrayList<BattlePokemon> team2 = new ArrayList<>();
		for (BuildPokemon buildpoke : p2.team) {
			team2.add(new FullPokemon(buildpoke, 1, pid));
			pid++;
		}
		this.sides.add(new Side(team2, 1));
		this.agents.add(p2.agent);
	}

	public void SetupSingleTurn() {
		this.gameQueue.addLast(new BlockingPhase(PhaseName.DECISION));
		this.gameQueue.addLast(new NonBlockingPhase(PhaseName.SWITCH));
		this.gameQueue.addLast(new NonBlockingPhase(PhaseName.MOVE));
		this.gameQueue.addLast(new NonBlockingPhase(PhaseName.RESIDUAL));
	}

	// TODO: Implement this
	public Decision[] decisionPhase() {
		return null;
	}

	public Decision getDecisionFromSide() {
		return null;
	}

	// TODO: Implement this
	public void switchPhase() {

	}

	// TODO: Implement this
	public void movePhase() {

	}

	// TODO: Implement this
	public void residualPhase() {

	}

	// TODO: Clone and obfuscate
	public Game obfuscateAndClone(int side) {
		return null;
	}

	public ArrayList<GameEvent> useMove(BattlePokemon user, BattlePokemon target, BattleMove move) {
		ArrayList<GameEvent> eventChain = new ArrayList<>();
		eventChain.add(new MoveUseEvent(user, target, move.baseMove));
		move.pp--;
		if (!move.baseMove.category().equals(MoveCategory.STATUS)) {
			// TODO: Some moves and effects will modify accuracy. We need to implement this.
			double totalacc = (double) move.baseMove.accuracy() * user.getStatMult(BattleStat.ACCURACY)
					/ target.getStatMult(BattleStat.EVASION);
			if (new Random().nextDouble() <= totalacc) {
				// TODO: Things will modify this. We need to implement this
				boolean crit = new Random().nextDouble() <= user.getStatMult(BattleStat.CRITICALHIT);
				DamageResult damageEvent = this.getDamage(user, target, move.baseMove, crit);
				target.damage(damageEvent.damage);
				eventChain.add(damageEvent.event);
				// TODO: Add recoil
			} else {
				eventChain.add(DamageEvent.miss(target));
			}
		} else {
			// TODO: Status moves will likely kill me
		}
		return eventChain;
	}

	public DamageResult getDamage(BattlePokemon attacker, BattlePokemon defender, Move move,
			boolean isCrit) {
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
	public DamageResult getModifiedDamage(BattlePokemon attacker, BattlePokemon defender, Move move,
			boolean isCrit,
			Random rand) {
		int damage = (int) (Math.floor(getBaseDamage(attacker, defender, move, isCrit)) * (isCrit ? 1.5 : 1.0)
				* (((double) (rand.nextInt(16) + 85)) / 100));
		double stab = (Arrays.asList(attacker.getDexData().types).contains(move.type()) ? 1.5 : 1.0);
		double typemult = !move.type().equals(null)
				? TypeChart.getMultiplier(move.type(), defender.getDexData().types[0])
						* (defender.getDexData().types.length > 1
								? TypeChart.getMultiplier(move.type(), defender.getDexData().types[1])
								: 1.0)
				: 1.0;
		return new DamageResult(new DamageEvent(defender, true, isCrit, Effectiveness.getEffectiveness(typemult),
				(int) (damage * stab * typemult)), (int) (damage * stab * typemult));
	}

	// TODO: Figure out how to process base damage modifiers (Acrobatics, etc.)
	public double getBaseDamage(BattlePokemon attacker, BattlePokemon defender, Move move, boolean isCrit) {
		Stat attackingStat = Stat.ATK;
		Stat defendingStat = Stat.DEF;
		if (move.category().equals(MoveCategory.SPECIAL)) {
			attackingStat = Stat.SPA;
			defendingStat = Stat.SPD;
		}
		return ((((2 * attacker.getLevel() / 5) + 2) * move.baseDamage()
				* Math.floor(attacker.getStat(attackingStat)
						* (isCrit && attacker.getStatMult(attackingStat) < 1 ? 1 : attacker.getStatMult(attackingStat)))
				/ Math.floor(defender.getStat(defendingStat)
						* (isCrit && defender.getStatMult(defendingStat) > 1 ? 1
								: defender.getStatMult(defendingStat))))
				/ 50) + 2;
	}
}
