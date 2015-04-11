/**
 * 
 */
package org.middleheaven.collections.progression;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.interval.Interval;

/**
 * 
 */
public class IntegerLinearProgression implements StepProgression<Integer, Integer> {

	
	private int end;
	private int start;
	private int step;

	public IntegerLinearProgression(int start, int end, int step){
		this.start = start;
		this.end = end;
		this.step = step;
	}
	
	public Integer getFirst(){
		return start;
	}
	
	public Integer getLast(){
		return end;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getStep() {
		return step;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Integer> iterator() {
		return new ProgressionIterator(start, end, step);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public StepProgression<Integer, Integer> step(Integer step) {
		return new IntegerLinearProgression(start, end , step);
	}

	
	private static class ProgressionIterator implements Iterator<Integer> {

		private int current;
		private int end;
		private int step;
		
		public ProgressionIterator(int start, int end, int step){
			this.end = end;
			this.current = start - step;
			this.step = step;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return current != end;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Integer next() {
			current += step;
			return Integer.valueOf(current);
		}
		
		public void remove() {
			throw new UnsupportedOperationException("Cannot remove from a progression");
		}
		
	}




	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<Integer> asEnumerable() {
		return Enumerables.asEnumerable(this);
	}




}
