/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.stream.Stream;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.iterators.StackReverseIterator;

/**
 * 
 */
 class StreamEnumerableDecorator<T> extends AbstractSourceEnumerable<T> {

	private Stream<T> stream;

	/**
	 * Constructor.
	 * @param stream
	 */
	@SuppressWarnings("unchecked")
	public StreamEnumerableDecorator(Stream<? extends T> stream) {
		this.stream = (Stream<T>) stream;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return stream.iterator();
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
		return stream.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return stream.count() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return new StackReverseIterator<T>(this.iterator());
	}


}
