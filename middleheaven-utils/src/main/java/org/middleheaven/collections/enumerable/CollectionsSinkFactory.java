/**
 * 
 */
package org.middleheaven.collections.enumerable;

/**
 * 
 */
public class CollectionsSinkFactory<T> implements SinkFactory<T, CollectionsSink<T>>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CollectionsSink<T> createFrom(Enumerable<T> other) {
		return new CollectionsSink<T>(other);
	}

}
