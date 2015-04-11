/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;


/**
 * Ignores the first {@code skipCount} elements in the original stream
 */
class SkipEnumerable<T> extends AbstractOperationalEnumerable<T> {

	private long skip;
	
	public SkipEnumerable(long skip , AbstractBaseEnumerable<T> original){
		super(original);
		this.skip = skip;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new SkipIterator<T>(skip,original().iterator());
	}
	
	@Override
	public EnumerableSize getSizeInfo(){
		return original().getSizeInfo().subtract(IntPreciseEnumerableSize.of(skip));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Enumerable<T> boundSafeSkip(long count){
		return new SkipEnumerable<>(this.skip + count, this.original());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count()  {
		return getSizeInfo().count();
	}

	private static class SkipIterator<T> implements Iterator<T>{

		private Iterator<T> iterator;
		private boolean skipped = false;
		private long skip;
		/**
		 * Constructor.
		 * @param iterator
		 */
		public SkipIterator(long skip ,Iterator<T> iterator) {
			this.iterator = iterator;
			this.skip = skip;
		}

		/**
		 * 
		 */
		private void skipAll() {
			if (!skipped){
				for(int i=0; i < skip && iterator.hasNext();i++){
					iterator.next();
				}
				
				skipped = true;
			}
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			skipAll(); 
			return iterator.hasNext();
		}


		/**
		 * {@inheritDoc}
		 */
		@Override
		public T next() {
			skipAll(); 
			return iterator.next();
		}
		
	}
}
