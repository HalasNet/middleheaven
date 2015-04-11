/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;

/**
 * 
 */
public class SubListIterator<T> implements ListIterator<T> {

	private ListIterator<T> original;
	private int length;
	private int start;

	/**
	 * Constructor.
	 * @param listIterator
	 * @param length
	 */
	public SubListIterator(ListIterator<T> original, int start, int length) {
		this.original = original;
		this.length = length;
		this.start = start;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(T e) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return original.hasNext();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasPrevious() {
		return original.hasPrevious();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
	  return original.next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int nextIndex() {
		 return original.previousIndex() - start;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T previous() {
		return original.previous();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int previousIndex() {
		 return original.previousIndex() - start;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(T e) {
		throw new UnsupportedOperationException();
	}

}
