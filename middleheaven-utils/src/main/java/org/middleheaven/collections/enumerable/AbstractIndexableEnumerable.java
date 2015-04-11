/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;
import org.middleheaven.collections.iterators.IndexBasedIterator;
import org.middleheaven.collections.iterators.ReverseIndexBasedIterator;

/**
 * 
 */
public abstract class AbstractIndexableEnumerable<T> extends AbstractSourceEnumerable<T> implements FiniteEnumerable<T>{

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public final PreciseEnumerableSize getSizeInfo(){
		return IntPreciseEnumerableSize.of(size());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findFirst() {
		if(!this.isEmpty()){
			return getAt(0);
		}
		throw new NoSuchElementException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findLast() {
		if(!this.isEmpty()){
			return getAt(this.size() - 1);
		}
		throw new NoSuchElementException();
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Optional<T> tryFirst() {
		return isEmpty() ? Optional.empty() : Optional.of(getAt(0));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Optional<T> tryLast() {
		return isEmpty() ? Optional.empty() : Optional.of(getAt(size() - 1));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final T getAt(long index) {
		if (index <0 || index >=  count() || index > Integer.MAX_VALUE || index < Integer.MIN_VALUE) {
			throw new IndexOutOfBoundsException();
		}
		return safeBoundGet((int)index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final long count() {
		return size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}
	
	public final T getAt (int index){
		if (index <0 || index >=  count()) {
			throw new IndexOutOfBoundsException();
		}
		return safeBoundGet(index);
	}
	protected abstract T safeBoundGet (int index);
	protected abstract int size ();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new IndexableIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> reverseIterator() {
		return new ReverseIterator();
	}

	
	public class ReverseIterator extends ReverseIndexBasedIterator<T>{

		/**
		 * Constructor.
		 */
		public ReverseIterator() {
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected int size() {
			return AbstractIndexableEnumerable.this.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		protected T getObject(int index) {
			return AbstractIndexableEnumerable.this.safeBoundGet(index);
		}
		
	}
	public class IndexableIterator extends IndexBasedIterator<T>{


		/**
		 * Constructor.
		 * @param abstractIndexableEnumerable
		 */
		public IndexableIterator() {
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected T getObject(int index) {
			return AbstractIndexableEnumerable.this.getAt(index);
		}


		/**
		 * {@inheritDoc}
		 */
		@Override
		protected int size() {
			return AbstractIndexableEnumerable.this.size();
		}
		
	} 
}
