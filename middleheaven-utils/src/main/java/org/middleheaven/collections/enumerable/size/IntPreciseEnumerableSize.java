/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.InfiniteSizeException;
import org.middleheaven.quantity.math.Int;

/**
 * 
 */
public final class IntPreciseEnumerableSize extends AbstractEnumerableSize implements PreciseEnumerableSize {

	private static final IntPreciseEnumerableSize zero = new IntPreciseEnumerableSize(Int.valueOf(0L));
	
	public static IntPreciseEnumerableSize of(int size){
		 return size == 0? zero : new IntPreciseEnumerableSize(Int.valueOf(size));
	}
	
	public static IntPreciseEnumerableSize of(long size){
		 return size == 0? zero : new IntPreciseEnumerableSize(Int.valueOf(size));
	}
	
	public static IntPreciseEnumerableSize of(Int size){
		 return size.isZero() ? zero : new IntPreciseEnumerableSize(size);
	}

	private Int size;
	
	private IntPreciseEnumerableSize (Int size){
		this.size = size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Int getValue() {
		return size;
	}

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
	public int compareTo(PreciseEnumerableSize other) {
		return this.size.compareTo(other.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean equals(EnumerableSize other) {
		if (other instanceof PreciseEnumerableSize){
			return ((PreciseEnumerableSize)other).getValue().equals(this.getValue());
		}
		return false;
	}

	@Override
	public String toString(){
		return size.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() throws InfiniteSizeException {
		return size.asLong();
	}
}
