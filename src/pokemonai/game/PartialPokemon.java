package pokemonai.game;

import pokemonai.constants.BattleStat;
import pokemonai.constants.Stat;
import pokemonai.data.pokedex.DexEntry;

/**
 * 
 * @author Admin
 *
 *         Obscured: moves, BuildPokemon base, hp
 */
public class PartialPokemon implements BattlePokemon {

	public PartialPokemon(FullPokemon base) {
	}

	public PartialPokemon(PartialPokemon base) {

	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSide() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStatMult(Stat stat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getStatMult(BattleStat stat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void boost(Stat stat, int boostamt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void boost(BattleStat stat, int boostamt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unboost(Stat stat, int boostamt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unboost(BattleStat stat, int boostamt) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getHp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void damage(int damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxHp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStat(Stat stat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DexEntry getDexData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BattlePokemon deepCopy() {
		// TODO Auto-generated method stub
		return null;
	}
}
