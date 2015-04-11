/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;


/**
 * 
 */
class IterableEnumerableDecorator<T> extends AbstractIterableEnumerable<T> {

	private Iterable<T> iterable;
	
	IterableEnumerableDecorator(Iterable<T> iterable){
		this.iterable = iterable;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return iterable.iterator();
	}

	
	

}
