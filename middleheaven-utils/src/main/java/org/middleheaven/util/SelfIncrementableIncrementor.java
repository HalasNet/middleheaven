/**
 * 
 */
package org.middleheaven.util;

/**
 * 
 */
public class SelfIncrementableIncrementor<S extends SelfIncrementable<S>> implements Incrementor<S,S> {

	
	private boolean reversed = false;
	private S step;

	public SelfIncrementableIncrementor(S step){
		this.step = step;
	}
	
	private SelfIncrementableIncrementor(S step, boolean reversed){
		this.reversed = reversed;
		this.step = step;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public S increment(S object) {
		return reversed ? object.previousBy(step) : object.nextBy(step);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Incrementor<S, S> reverse() {
		return new SelfIncrementableIncrementor<S>(step,!reversed);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Incrementor<S, S> withStep(S step) {
		return new SelfIncrementableIncrementor<S>(step,reversed);
	}

}
