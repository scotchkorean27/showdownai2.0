package pokemonai.game;

import java.util.List;

import pokemonai.agent.Agent;
import pokemonai.teambuild.BuildPokemon;

public class Player {
	public final Agent agent;
	public final List<BuildPokemon> team;

	public Player(Agent agent, List<BuildPokemon> team) {
		this.agent = agent;
		this.team = team;
	}
}
