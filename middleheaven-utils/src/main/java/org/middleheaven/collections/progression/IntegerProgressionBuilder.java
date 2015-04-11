/**
 * 
 */
package org.middleheaven.collections.progression;

/**
 * 
 */
public class IntegerProgressionBuilder {

	private int start;
	
	/**
	 * Constructor.
	 * @param start
	 */
	public IntegerProgressionBuilder(int start) {
		this.start = start;
	}

	
	public Progression<Integer, Integer> upTo(int end){
		if (start > end){
			throw new IllegalArgumentException("End cannot be before start");
		}
		return new IntegerLinearProgression(start, end, 1);
	}
}
