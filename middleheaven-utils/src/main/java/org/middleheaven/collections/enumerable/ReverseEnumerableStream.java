/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.Optional;

import org.middleheaven.collections.enumerable.size.EnumerableSize;

/**
 * 
 */
class ReverseEnumerableStream<T> extends AbstractOperationalEnumerable<T>  {


	/**
	 * Constructor.
	 * @param enumerable
	 * @param predicate
	 */
	public ReverseEnumerableStream(AbstractBaseEnumerable<T> original) {
		super(original);
	}
	
	public T findFirst(){
		return original().findLast();
	}
	
	public T findLast(){
		return original().findFirst();
	}
	
	public Optional<T> tryFirst(){
		return original().tryLast();
	}
	
	public Optional<T> tryLast(){
		return original().tryFirst();
	}
	
	public Enumerable<T> reverse(){
		return original();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return original().reverseIterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return original().iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return original().getSizeInfo(); // it's the same size
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return original().count();
	}


}
