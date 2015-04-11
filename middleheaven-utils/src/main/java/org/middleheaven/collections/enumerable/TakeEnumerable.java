/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;

/**
 * 
 */
class TakeEnumerable<T> extends AbstractOperationalEnumerable<T> {

	private long maxCount;

	/**
	 * Constructor.
	 * @param count
	 * @param abstractEnumerable
	 */
	public TakeEnumerable(long maxCount, AbstractBaseEnumerable<T> original) {
		super(original);
		this.maxCount = maxCount;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new TakeIterator<T>(maxCount, original().iterator());
	}
	

	@Override
	public EnumerableSize getSizeInfo(){
		return original().getSizeInfo().min(IntPreciseEnumerableSize.of(maxCount));
	}
	
	protected Enumerable<T> boundSafeTake(long count){
		return new TakeEnumerable<T>(this.maxCount + count, original());
	}
	
	@Override
	public long count(){
		return getSizeInfo().count();
	}
	
	private static class TakeIterator<T> implements Iterator<T> {

		private long maxCount;
		private long count = -1;
		private Iterator<T> iterator;

		/**
		 * Constructor.
		 * @param maxCount
		 * @param iterator
		 */
		public TakeIterator(long maxCount, Iterator<T> iterator) {
			this.maxCount = maxCount;
			this.iterator = iterator;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			 return count + 1 < maxCount  && iterator.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T next() {
			count++;
			if (count > maxCount){
				throw new NoSuchElementException();
			}
			return iterator.next();
		}
		
	}

}
