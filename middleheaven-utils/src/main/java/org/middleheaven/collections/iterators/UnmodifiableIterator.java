/**
 * 
 */
package org.middleheaven.collections.iterators;

import java.util.Iterator;

/**
 * 
 */
public class UnmodifiableIterator<T> implements Iterator<T> {

	
	private Iterator<T> other;

	public UnmodifiableIterator(Iterator<T> other){
		this.other = other;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return other.hasNext();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
		return other.next();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException("Cannot remove from an umodifialbe iterator");
	}

}
