/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.quantity.math.Int;

/**
 * 
 */
public abstract class AbstractComputableEnumerableSize extends AbstractEnumerableSize implements ComputableEnumerableSize{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Int getValue() {
		return doComputeSize();
	}

	protected abstract Int doComputeSize();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isInfinit() {
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasSameValue(EnumerableSize other) {
		return other instanceof CountableEnumerableSize && ((CountableEnumerableSize)other).getValue().equals(this.getValue()); 
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equals(EnumerableSize other) {
		if (other instanceof ComputableEnumerableSize){
			return ((ComputableEnumerableSize)other).getValue().equals(this.getValue());
		}
		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return doComputeSize().asLong();
	}
	
}
