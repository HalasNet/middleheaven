/**
 * 
 */
package org.middleheaven.collections.enumerable;


/**
 * 
 */
public abstract class AbstractSourceEnumerable<T> extends AbstractBaseEnumerable<T> {

	public abstract boolean isSingle();
	public abstract long count() throws InfiniteSizeException;


}
