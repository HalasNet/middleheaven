/**
 * 
 */
package org.middleheaven.collections;

import org.middleheaven.collections.iterators.IndexBasedIterator;

/**
 * 
 */
public class ReverseArrayIterator<T> extends IndexBasedIterator<T> {

	private T[] array;

	/**
	 * Constructor.
	 * @param array
	 */
	public ReverseArrayIterator(T[] array) {
		this.array = array;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T getObject(int index) {
		if (index < 0 || index >= size ()){
			throw new IndexOutOfBoundsException();
		}
		return array[array.length - 1 - index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected int size() {
		return array.length;
	}


}
