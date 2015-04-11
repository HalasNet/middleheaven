/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

/**
 * Operates over the orginal stream in a way that is the same for direct iteration and reverted iteration
 */
abstract class AbstractSimmetricOperationalEnumerable<T> extends
		AbstractOperationalEnumerable<T> {

	/**
	 * Constructor.
	 * @param original
	 */
	protected AbstractSimmetricOperationalEnumerable(
			AbstractBaseEnumerable<T> original) {
		super(original);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterator<T> iterator() {
		return decorateIterator(original().iterator());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Iterator<T> reverseIterator() {
		return decorateIterator(original().reverseIterator());
	}

	/**
	 * @param reverseIterator
	 * @return
	 */
	protected abstract Iterator<T> decorateIterator(Iterator<T> iterator);

}
