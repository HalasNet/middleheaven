/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public interface GroupedElements<K, T> {

	
	public K getKey();
	public Enumerable<T> elements();
}
