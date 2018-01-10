package pokemonai.teambuild;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import pokemonai.data.moves.Move;
import pokemonai.data.pokedex.MoveSet;

// An immutable version of pokemonai.data.MoveSet.
// A wrapper around Collections.unmodifiableset.
// There is no doubt in my mind there is a better way to do this, but whatever.
public class UnmodifiableMoveSet implements Set<Move> {

	private final Set<Move> moveset;
	
	public UnmodifiableMoveSet(MoveSet moveset) {
		this.moveset = Collections.unmodifiableSet(new HashSet<>(moveset));
	}
	
	@Override
	public boolean add(Move e) {
		return this.moveset.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends Move> c) {
		return this.moveset.addAll(c);
	}

	@Override
	public void clear() {
		this.moveset.clear();
	}

	@Override
	public boolean contains(Object o) {
		return this.moveset.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return this.moveset.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return this.moveset.isEmpty();
	}

	@Override
	public Iterator<Move> iterator() {
		return this.moveset.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return this.moveset.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return this.moveset.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return this.moveset.retainAll(c);
	}

	@Override
	public int size() {
		return this.moveset.size();
	}

	@Override
	public Object[] toArray() {
		return this.moveset.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return this.moveset.toArray(a);
	}

}
