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
class IntRandomEnumerable extends AbstractSourceEnumerable<Integer> {

	private Random random;
	private int max;
	private int min;

	public IntRandomEnumerable(int min, int max,Random random){
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
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>(){

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public Integer next() {
				return random.nextInt(max) + min;
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
