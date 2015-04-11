/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.middleheaven.collections.enumerable.AbstractSourceEnumerable;
import org.middleheaven.collections.enumerable.FiniteEnumerable;
import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;

/**
 * 
 */
public class SequenceEnumerableAdapter<T> extends AbstractSourceEnumerable<T> implements FiniteEnumerable<T>{

	private Sequence<T> sequence;

	/**
	 * Constructor.
	 * @param sequence
	 */
	public SequenceEnumerableAdapter(Sequence<T> sequence) {
		this.sequence = sequence;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> toSequence() {
		return sequence;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return sequence.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreciseEnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(sequence.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findFirst() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return sequence.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findLast() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return sequence.get(sequence.size() - 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return sequence.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return sequence.size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return sequence.reverse().iterator();
	}


}
