/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public class FilteredEnumerableStream<T> extends AbstractSimmetricOperationalEnumerable<T> {

	private Predicate<T> predicate;

	/**
	 * Constructor.
	 * @param enumerable
	 * @param predicate
	 */
	public FilteredEnumerableStream(AbstractBaseEnumerable<T> enumerable,Predicate<T> predicate) {
		super(enumerable);
		this.predicate = predicate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return new IterableBasedComputableEnumeratorSize(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return getSizeInfo().count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> decorateIterator(Iterator<T> iterator) {
		return new FilteredIterator<T>(iterator, predicate);
	}


}
