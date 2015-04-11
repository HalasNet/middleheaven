/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public class IntegersSinkFactory implements SinkFactory<Integer,IntegersSink> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IntegersSink createFrom(Enumerable<Integer> other) {
		return new IntegersSink(other);
	}

}
