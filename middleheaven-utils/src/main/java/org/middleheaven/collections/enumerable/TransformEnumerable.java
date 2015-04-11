/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.Function;


/**
 * Transforms each object in original stream from type T to type R.
 * @param <R> - transformed type
 * @param <T> - original type
 */
class TransformEnumerable<R, T> extends AbstractTransformationEnumerable<R, T> {

	private Function<R,T> function;

	/**
	 * Constructor.
	 * @param enumerable
	 * @param function
	 */
	public TransformEnumerable(AbstractBaseEnumerable<T> original, Function<R, T> function) {
		super(original);
		this.function = function;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<R> iterator() {
		return new TransformIterator<T, R>(original().iterator(), function);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<R> reverseIterator() {
		return new TransformIterator<>(original().reverseIterator(), function);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return original().getSizeInfo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return original().count();
	}
	
	private static class TransformIterator<T,R> implements Iterator<R>{

		private Iterator<T> original;
		private Function<R, T> function;
		
		public TransformIterator (Iterator<T> original, Function<R, T> function){
			this.original = original;
			this.function = function;
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return original.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public R next() {
			return function.apply(original.next());
		}
		
	}

	
}
