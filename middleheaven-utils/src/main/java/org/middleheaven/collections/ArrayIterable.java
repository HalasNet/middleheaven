/**
 * 
 */
package org.middleheaven.collections;

import java.util.Iterator;

import org.middleheaven.collections.iterators.ArrayIteratorAdapter;

/**
 * 
 */
final class ArrayIterable<T> implements Iterable<T> {

	private final T[] array;

	public ArrayIterable(T[] array){
		this.array = array;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return ArrayIteratorAdapter.adapt(array);
	}

}
