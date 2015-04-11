/**
 * 
 */
package org.middleheaven.collections.enumerable.size;

import org.middleheaven.collections.enumerable.EnumerableSizeComputationException;


/**
 * Represent the information regarding the size of the Enumerable.
 * As an Enumerable can be an infinit stream of objects, a primitive like int or long cannot account for that size information.
 * Information abount size should not be used in algorithms, and if is the specific subtypes must be accounted for.
 */
public interface EnumerableSize {

	public boolean isInfinit();

	/**
	 * Returns the minimum size comparing this to other.
	 * @param other
	 * @return this if its size is less thant the other size.
	 */
	public EnumerableSize min(EnumerableSize other);

	/**
	 * @param size
	 * @return
	 */
	public EnumerableSize add(EnumerableSize size);

	/**
	 * Subtract the sizes to a non negative result.
	 * The result shouble equivalent to max(0, this.value - other.value)
	 * @param of
	 * @return
	 */
	public EnumerableSize subtract(EnumerableSize other);
	
	
	public boolean hasSameValue(EnumerableSize other);

	/**
	 * @return
	 */
	public long count() throws EnumerableSizeComputationException;

}
