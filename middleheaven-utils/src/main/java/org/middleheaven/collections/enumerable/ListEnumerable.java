/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;

/**
 * 
 */
class ListEnumerable<T> extends AbstractSourceEnumerable<T> implements FiniteEnumerable<T>{

    private List<T> list;

	/**
	 * Constructor.
	 * @param elements
	 */
	@SuppressWarnings("unchecked")
    ListEnumerable(List<? extends T> elements) {
		this.list = (List<T>) elements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return list.size() == 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreciseEnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(list.size());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return list.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return new ReverseListIterator<T>(list.listIterator(list.size()));
	}
	
	private static class ReverseListIterator<T> implements Iterator<T>{

		private ListIterator<T> listIterator;

		/**
		 * Constructor.
		 * @param listIterator
		 */
		public ReverseListIterator(ListIterator<T> listIterator) {
			this.listIterator = listIterator;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return listIterator.hasPrevious();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T next() {
			return listIterator.previous();
		}
		
	}

}
