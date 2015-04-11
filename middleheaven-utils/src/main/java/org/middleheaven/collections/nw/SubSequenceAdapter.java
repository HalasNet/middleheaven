/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;

import org.middleheaven.collections.nw.Sequence.SequenceView;

/**
 * 
 */
public class SubSequenceAdapter<T> extends AbstractSequence<T> implements SequenceView<T> {

	
	private int length;
	private int start;
	private Sequence<T> original;

	public SubSequenceAdapter(int start, int length, Sequence<T> original){
		this.start = start;
		this.length = length;
		this.original = original;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return length ==0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T boundSafeGet(int index) {
		return original.get(start + index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		return new SubListIterator<T>(this.original.listIterator(start + initialPosition), start, length );
	}

	
}
