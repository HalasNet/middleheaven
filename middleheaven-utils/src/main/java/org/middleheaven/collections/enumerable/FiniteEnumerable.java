/**
 * 
 */
package org.middleheaven.collections.enumerable;

import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;

/**
 * Represent an Enumerable in finite size. The size is between 0 and Integer.MaxValue.
 */
public interface FiniteEnumerable<T> extends Enumerable<T> {

	public PreciseEnumerableSize getSizeInfo();
}
