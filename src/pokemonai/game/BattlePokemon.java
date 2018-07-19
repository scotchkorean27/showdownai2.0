package pokemonai.game;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;

public interface BattlePokemon {
	public int getId();

	public int getSide();

	public double getStatMult(Stat stat);

	public double getStatMult(BattleStat stat);

	public void boost(Stat stat, int boostamt);

	public void boost(BattleStat stat, int boostamt);

	public void unboost(Stat stat, int boostamt);

	public void unboost(BattleStat stat, int boostamt);

	public int getHp();

	public int getMaxHp();

	public void damage(int damage);

	public int getStat(Stat stat);

	public int getLevel();

	public DexEntry getDexData();

	public BattlePokemon deepCopy();
}
