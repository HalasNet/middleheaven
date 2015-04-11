/**
 * 
 */
package org.middleheaven.collections.progression;

/**
 * 
 */
public interface Progressable<T> {

	
	public Progression<T, T> upTo(T other);
}
