/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.iterators.StackReverseIterator;


/**
 * 
 */
public abstract class AbstractIterableEnumerable<T> extends AbstractSourceEnumerable<T> {

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
	public boolean isSingle() {
		Iterator<T> it = this.iterator();
		if (it.hasNext()){
			it.next();
			return !it.hasNext();
		}
		return false; // isEmpty
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
	protected Iterator<T> reverseIterator() {
		return new StackReverseIterator<>(this.iterator());
	}
}
