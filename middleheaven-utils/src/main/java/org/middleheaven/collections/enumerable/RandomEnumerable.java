/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.middleheaven.collections.enumerable.size.CountableEnumerableSize;
import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;


/**
 * 
 */
class RandomEnumerable<T> extends AbstractOperationalEnumerable<T> {

	private long samplesCount;
   private Random random;
	
	public RandomEnumerable(long samplesCount , AbstractBaseEnumerable<T> original, Random random){
		super(original);
		this.samplesCount = samplesCount;
		this.random = random;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see http://en.wikipedia.org/wiki/Reservoir_sampling
	 * @see http://gregable.com/2007/10/reservoir-sampling.html
	 */
	@Override
	public Iterator<T> iterator() {
		List<T> reservoir = new ArrayList<T>((int)samplesCount);
		
		int iterationCount =0;
		Iterator<T> iterator = original().iterator();
		while ( iterationCount < samplesCount && iterator.hasNext()){
			reservoir.add(iterator.next());
			iterationCount++;
		}
		
		while (iterator.hasNext()){
			T t = iterator.next();
			
		    int replaceIndex = (int) Math.floor((double) iterationCount * random.nextDouble());
		    // if the index exists in the reservoire
		    if (replaceIndex < samplesCount) {
		    	reservoir.set(replaceIndex, t);
		    }

			iterationCount++;
		}
		
		return reservoir.iterator();
	}
	
	@Override
	public CountableEnumerableSize getSizeInfo(){
		return IntPreciseEnumerableSize.of(samplesCount);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return samplesCount == 1L;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws EnumerableSizeComputationException {
		return samplesCount;
	}
	

}
