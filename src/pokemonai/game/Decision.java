package pokemonai.game;

public class Decision {

	public boolean ismove;
	public String detail;
	public int optionHashCode;

	public Decision(BattleMove move) {
		this.ismove = true;
		this.detail = move.baseMove.name();
		this.optionHashCode = move.hashCode();
	}

	public Decision(FullPokemon pokemon) {
		this.ismove = false;
		this.detail = pokemon.basePokemon.dexData.species;
		this.optionHashCode = pokemon.hashCode();
	}
}
