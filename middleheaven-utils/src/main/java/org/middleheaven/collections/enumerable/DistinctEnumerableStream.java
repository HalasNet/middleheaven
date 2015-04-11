/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;


/**
 * 
 */
public class DistinctEnumerableStream<T> extends FilteredEnumerableStream<T> {

	/**
	 * Constructor.
	 * @param enumerable
	 * @param predicate
	 */
	public DistinctEnumerableStream(AbstractBaseEnumerable<T> enumerable) {
		super(enumerable,  null); // never used
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> decorateIterator(Iterator<T> iterator) {
		return new FilteredIterator<T>(iterator, new DistinctPredicate<T>()); // must create a new predicate each time
	}
}
