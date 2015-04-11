/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.BinaryFunction;

/**
 * 
 */
public class TransformedWithEnumerable<R, T > extends AbstractTransformationEnumerable<R, T> {

	private BinaryFunction<R, T, Long> function;

	/**
	 * Constructor.
	 * @param original
	 * @param function
	 */
	public TransformedWithEnumerable(AbstractBaseEnumerable<T> original,BinaryFunction<R, T,Long> function) {
		super(original);
		this.function = function;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<R> iterator() {
		return new TransformIterator<T,R>(original().iterator(), function);
	}

	private static class TransformIterator<T,R> implements Iterator<R>{

		private Iterator<T> original;
		private BinaryFunction<R, T, Long> function;
		private long index = -1;
		public TransformIterator (Iterator<T> original, BinaryFunction<R, T, Long> function){
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
			return function.apply(original.next(), ++index);
		}
		
	}

}
