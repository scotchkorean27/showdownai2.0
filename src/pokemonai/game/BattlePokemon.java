package pokemonai.game;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;

public interface BattlePokemon {
	int getId();

	int getSide();

	double getStatMult(Stat stat);

	double getStatMult(BattleStat stat);

	void boost(Stat stat, int boostamt);

	void boost(BattleStat stat, int boostamt);

	void unboost(Stat stat, int boostamt);

	void unboost(BattleStat stat, int boostamt);

	int getHp();

	int getMaxHp();

	void damage(int damage);

	int getStat(Stat stat);

	int getLevel();

	DexEntry getDexData();

	BattlePokemon deepCopy();
}
