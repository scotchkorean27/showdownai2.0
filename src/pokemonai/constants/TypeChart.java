package pokemonai.constants;

public final class TypeChart {
	static double[][] typechart = new double[][] {
		{1,1,1,1,1,1,1,1,1,1,1,1,0.5,0,1,1,0.5,1},
		{1,0.5,0.5,1,2,2,1,1,1,1,1,2,0.5,1,0.5,1,2,1},
		{1,2,0.5,1,0.5,1,1,1,2,1,1,1,2,1,0.5,1,1,1},
		{1,1,2,0.5,0.5,1,1,1,0,2,1,1,1,1,0.5,1,1,1},
		{1,0.5,2,1,0.5,1,1,0.5,2,0.5,1,0.5,2,1,0.5,1,0.5,1},
		{1,0.5,0.5,1,2,0.5,1,1,2,2,1,1,1,1,2,1,0.5,1},
		{2,1,1,1,1,2,1,0.5,1,0.5,0.5,0.5,2,0,1,2,2,0.5},
		{1,1,1,1,2,1,1,0.5,0.5,1,1,1,0.5,0.5,1,1,0,2},
		{1,2,1,2,0.5,1,1,2,1,0,1,0.5,2,1,1,1,2,1},
		{1,1,1,0.5,2,1,2,1,1,1,1,2,0.5,1,1,1,0.5,1},
		{1,1,1,1,1,1,2,2,1,1,0.5,1,1,1,1,0,0.5,1},
		{1,0.5,1,1,2,1,0.5,0.5,1,0.5,2,1,1,0.5,1,2,0.5,0.5},
		{1,2,1,1,1,2,0.5,1,0.5,2,1,2,1,1,1,1,0.5,1},
		{0,1,1,1,1,1,1,1,1,1,2,1,1,2,1,0.5,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,0.5,0},
		{1,1,1,1,1,1,0.5,1,1,1,2,1,1,2,1,0.5,1,0.5},
		{1,0.5,0.5,0.5,1,2,1,1,1,1,1,1,2,1,1,1,0.5,2},
		{1,0.5,1,1,1,1,2,0.5,1,1,1,1,1,1,2,2,0.5,1}
	};
	
	public static double getMultiplier(PokeType attacking, PokeType defending) {
		return typechart[attacking.ordinal()][defending.ordinal()];
	}
}
