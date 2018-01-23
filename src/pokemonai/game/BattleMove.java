package pokemonai.game;

import pokemonai.data.moves.Move;

public class BattleMove {
	public final Move baseMove;
	public int pp;

	public BattleMove(Move baseMove, int pp) {
		this.baseMove = baseMove;
		this.pp = pp;
	}

	public BattleMove(Move baseMove) {
		this.baseMove = baseMove;
		this.pp = baseMove.basePP();
	}

	public BattleMove(BattleMove original) {
		this.baseMove = original.baseMove;
		this.pp = original.pp;
	}

	public boolean equals(BattleMove other) {
		return this.baseMove.equals(other.baseMove) && this.pp == other.pp;
	}
}
