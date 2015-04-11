/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Iterator;

import org.middleheaven.collections.enumerable.size.AbstractComputableEnumerableSize;
import org.middleheaven.quantity.math.Int;

/**
 * 
 */
public class IterableBasedComputableEnumeratorSize extends
		AbstractComputableEnumerableSize {

	
	private Iterable<?> iterable;
	
	public IterableBasedComputableEnumeratorSize(Iterable<?> iterable){
		this.iterable = iterable;	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Int doComputeSize() {
		int count =0;
		Iterator it = iterable.iterator();
		while(it.hasNext()){
			it.next();
			count++;
		}
		return Int.valueOf(count);
	}


}
