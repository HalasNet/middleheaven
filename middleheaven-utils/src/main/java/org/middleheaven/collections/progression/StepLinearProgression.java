/**
 * 
 */
package org.middleheaven.collections.progression;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.util.StepIncrementable;

/**
 * 
 */
public class StepLinearProgression<S,T extends StepIncrementable<T, S>> implements StepProgression<T, S> {

	
	private T end;
	private T start;
	private S step;

	public StepLinearProgression(T start, T end, S step){
		this.start = start;
		this.end = end;
		this.step = step;
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
		return new ProgressionIterator<S,T>(start, end , step);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public S getStep() {
		return step;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StepProgression<T, S> step(S step) {
		return new StepLinearProgression<>(start, end , step);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> asEnumerable() {
		return Enumerables.asEnumerable(this);
	}
	
	private static class ProgressionIterator<S , T extends StepIncrementable<T, S>> implements Iterator<T> {

		private T current;
		private T end;
		private T start;
		private S step;

		public ProgressionIterator(T start, T end , S step){
			this.start = start;
			this.end = end;
			this.step = step;
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
			current = current == null ? start :  current.nextBy(step);
			return current;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove from a progression");
		}
		
	}


	

}
