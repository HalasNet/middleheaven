/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;
import java.util.Random;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.SimpleInfinitEnumerableSize;

/**
 * 
 */
public class DoubleRandomEnumerable extends AbstractSourceEnumerable<Double> {


	private Random random;
	private double max;
	private double min;

	public DoubleRandomEnumerable(double min, double max,Random random){
		this.random = random;
		this.min = min;
		this.max = max;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public EnumerableSize getSizeInfo() {
		return new SimpleInfinitEnumerableSize();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<Double> iterator() {
		return new Iterator<Double>(){

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Double next() {
				return random.nextDouble() * (max-min) + min;
			}
			
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws EnumerableSizeComputationException {
		throw new InfiniteSizeException();
	}
}
