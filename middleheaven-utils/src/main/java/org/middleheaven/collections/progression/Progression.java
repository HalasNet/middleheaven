/**
 * 
 */
package org.middleheaven.collections.progression;

import org.middleheaven.collections.enumerable.Enumerable;

/**
 * 
 */
public interface Progression<T, S> extends Iterable<T>{

	/**
	 * Does not return null
	 * @return
	 */
	public T getFirst(); 
	
	/**
	 * Does not return null
	 * @return
	 */
	public T getLast();
	
	public StepProgression<T, S> step(S step);
	
	public Enumerable<T> asEnumerable();

	
}
