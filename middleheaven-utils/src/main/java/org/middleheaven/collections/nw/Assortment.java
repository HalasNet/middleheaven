/**
 * 
 */
package org.middleheaven.collections.nw;

/**
 * A miscellaneous, unordered , unindexed, collection of objects
 * 
 */
public interface Assortment<T> extends Iterable<T> , Countable {

	public boolean contains(Object object);
}
