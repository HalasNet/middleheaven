/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.Function;

/**
 * 
 */
class FlattenEnumerableStream<R, T> extends AbstractTransformationEnumerable<R,T> {

	private Function<Enumerable<R>, T> function;

	/**
	 * Constructor.
	 * @param enumerable
	 * @param classifier
	 */
	public FlattenEnumerableStream(AbstractBaseEnumerable<T> original, Function<Enumerable<R>, T>  function) {
		super(original);
		this.function = function;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<R> iterator() {
		return new FlattenIterator<R, T>(original().iterator(), function);
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
	public long count() throws EnumerableSizeComputationException {
		return getSizeInfo().count();
	}

	
	private static class FlattenIterator<R,T> implements Iterator<R> {

		private Iterator<T> iterator;
		private Function<Enumerable<R>, T> generator;
		private Iterator<R> currentIterator;
		private Element<R> next;

		/**
		 * Constructor.
		 * @param iterator
		 * @param generator
		 */
		public FlattenIterator(Iterator<T> iterator,Function<Enumerable<R>, T> generator) {
			this.iterator = iterator;
			this.generator = generator;
		}

		private Element<R> fetchNext(){
			if (currentIterator == null){
				if (iterator.hasNext()){
					currentIterator = generator.apply(iterator.next()).iterator();
				} else {
					return Element.noSuchElement();
				}
			} 
		
			if (currentIterator.hasNext()){
				return new Element<R>(currentIterator.next());
			} else {
				currentIterator = null;
				return fetchNext();
			}
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			Element<R> item = fetchNext();
			if (item.isFound()){
				this.next = item;
				return true;
			} 
			return false;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public R next() {
			if (next == null){
				next = fetchNext();
			}
			R obj = next.object();
			next = null;
			return obj;
		}
		
	}
}
