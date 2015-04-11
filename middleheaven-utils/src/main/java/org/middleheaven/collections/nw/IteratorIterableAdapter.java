/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Iterator;

/**
 * 
 */
final class IteratorIterableAdapter<T> implements Iterable<T> {

	private final Iterator<T> iterator;
	
	
	public static <X> IteratorIterableAdapter<X> from(Iterator<X> iterator){
		return new IteratorIterableAdapter<X>(iterator);
	}
	
	 IteratorIterableAdapter(Iterator<T> iterator){
		this.iterator = iterator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return iterator;
	}

}
