/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Collection;
import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;

/**
 * 
 */
public class CollectionEnumerableDecorator<T> extends AbstractSourceEnumerable<T> implements FiniteEnumerable<T> {

	private Collection<T> collection;

	/**
	 * Constructor.
	 * @param elements
	 */
	public CollectionEnumerableDecorator(Collection<? extends T> elements) {
		this.collection = (Collection<T>) elements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return collection.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return collection.iterator();
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreciseEnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(collection.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return collection.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return collection.size() == 1;
	}



}
