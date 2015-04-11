/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 */
public class ArrayListIterator<T> implements ListIterator<T> {

	private int position;
	private Object[] array;

	/**
	 * Constructor.
	 * @param initialPosition
	 */
	public ArrayListIterator(int initialPosition, Object[] array) {
		this.position = initialPosition;
		this.array = array;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(T obj) {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return position + 1 < array.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasPrevious() {
		return position - 1 >= 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
		position++;
		if (position >= array.length){
			throw new NoSuchElementException();
		}
		return (T)array[position];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int nextIndex() {
		return position + 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T previous() {
		position--;
		if (position < 0){
			throw new NoSuchElementException();
		}
		return (T)array[position];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int previousIndex() {
		return position - 1;
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
	public void set(T obj) {
		array[position] = obj;
	}

}
