/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Arrays;


/**
 * 
 */
public class ArrayEnumerable<T> extends AbstractIndexableEnumerable<T> implements RandomAccessEnumerable<T> {

	private T[] elements;

	/**
	 * Constructor.
	 * @param elements
	 */
	public ArrayEnumerable(T[] elements) {
		this.elements = elements;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return elements.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return elements.length == 0;
	}

	@Override
	public String toString(){
		return Arrays.toString(elements);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	protected T safeBoundGet(int index) {
		return elements[index];
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return elements.length == 1;
	}


	
}
