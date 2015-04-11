/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Collections;
import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;

/**
 * 
 */
class SingleEnumerable<T> extends AbstractSourceEnumerable<T> implements FiniteEnumerable<T>{

	private T element;
	
	/**
	 * Constructor.
	 * @param element
	 */
	public SingleEnumerable(T element) {
		this.element=  element;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return Collections.singleton(element).iterator();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Iterator<T> reverseIterator() {
		return Collections.singleton(element).iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PreciseEnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(1);
	}
	
	protected Enumerable<T> boundSafeSkip(long count){
		return EmptyEnumerable.getInstance();
	}

	protected Enumerable<T> boundSafeTake(long count){
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return 1;
	}



}
