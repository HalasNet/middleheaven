/**
 * 
 */
package org.middleheaven.collections.enumerable;


/**
 * Represents a finite enumerable of elements that can be access by an index in constant time (O(1)).
 * Also the size can be access in contante time 
 */
public interface RandomAccessEnumerable<T> extends FiniteEnumerable<T> {

	public T getAt(long index);
	
	/**
	 * 
	 * Returns the size of the enumerable in constant (O(1)) time
	 */
	public long count();


}
