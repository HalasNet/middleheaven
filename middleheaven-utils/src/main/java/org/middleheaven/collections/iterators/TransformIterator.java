/**
 * 
 */
package org.middleheaven.collections.iterators;

import java.util.Iterator;
import java.util.function.Function;

/**
 * 
 */
public class TransformIterator<T, R> implements Iterator<R> {

	

	public static <T, R> Iterator<R> transform (Iterator<T> original , Function<T, R> transform){
		return new TransformIterator<T,R>(original, transform);
	}

	private Iterator<T> original;
	private Function<T, R> transform;
	
	/**
	 * Constructor.
	 * @param original
	 * @param transform
	 */
	public TransformIterator(Iterator<T> original, Function<T, R> transform) {
		this.original = original;
		this.transform = transform;
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
	public R next() {
		return transform.apply(original.next());
	}

}
