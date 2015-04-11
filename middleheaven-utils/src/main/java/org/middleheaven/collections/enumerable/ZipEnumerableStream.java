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
class ZipEnumerableStream<R, T, P> extends AbstractTransformationEnumerable<R, T> {


	private final Enumerable<P> right;
	private final BinaryFunction<R, T, P> zipFunction;
	
	public ZipEnumerableStream(AbstractBaseEnumerable<T> left, Enumerable<P> right, BinaryFunction<R, T, P> zipFunction){
		super(left);
		this.right = right;
		this.zipFunction = zipFunction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<R> iterator() {
		return new ZipIterator<T,P,R>(original().iterator(), right.iterator(), zipFunction);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return original().getSizeInfo().min(right.getSizeInfo());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return getSizeInfo().count();
	}
	
	private static class ZipIterator<T,P, R> implements Iterator<R>{

		
		private Iterator<T> left;
		private Iterator<P> right;
		private BinaryFunction<R,T, P> zipFunction;

		public ZipIterator(Iterator<T> left, Iterator<P> right, BinaryFunction<R, T, P> zipFunction){
			this.left = left;
			this.right = right;
			this.zipFunction = zipFunction;
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return left.hasNext() && right.hasNext();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public R next() {
			return  zipFunction.apply(left.next(), right.next());
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
	
	

}
