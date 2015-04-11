/**
 * 
 */
package org.middleheaven.collections.iterators;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 */
public class StackReverseIterator<T> implements Iterator<T> {

	private Iterator<T> reversed;
	private Iterator<T> original;

	/**
	 * Constructor.
	 * @param enumerable
	 */
	public StackReverseIterator(Iterator<T> original) {
		this.original = original;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		ensureReversed();
		return reversed.hasNext();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T next() {
		ensureReversed();
		return reversed.next();
	}


	private void ensureReversed() {
		if (reversed == null){
			LinkedList<T> deque = new LinkedList<T>();
			while(original.hasNext()){
				deque.addFirst(original.next());
			}
			reversed = deque.iterator();
		}
	}


	
}
