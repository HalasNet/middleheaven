/**
 * 
 */
package org.middleheaven.collections.progression;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.quantity.time.DiscreteIncrementable;
import org.middleheaven.util.SelfIncrementable;

/**
 * 
 */
public class LinearProgression<T extends SelfIncrementable<T>> implements Progression<T, T> {

	
	private T end;
	private T start;

	public LinearProgression(T start, T end){
		this.start = start;
		this.end = end;
	}
	
	public T getFirst(){
		return start;
	}
	
	public T getLast(){
		return end;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new ProgressionIterator<T>(start, end);
	}

	private static class ProgressionIterator<T extends DiscreteIncrementable<T>> implements Iterator<T> {

		private T current;
		private T end;
		private T start;

		public ProgressionIterator(T start, T end){
			this.start = start;
			this.end = end;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return current == null || !current.equals(end);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T next() {
			current = current == null ? start :  current.next();
			return current;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove from a progression");
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public  StepProgression<T, T> step(T step) {
		return new StepLinearProgression<T, T>(start, end, step);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> asEnumerable() {
		return Enumerables.asEnumerable(this);
	}


}
