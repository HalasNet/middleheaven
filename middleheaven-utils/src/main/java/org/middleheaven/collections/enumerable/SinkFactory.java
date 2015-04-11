/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public interface SinkFactory<T, S extends Sink<T>> {

	
	public S createFrom(Enumerable<T> other);
}
