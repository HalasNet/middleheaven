/**
 * 
 */
package org.middleheaven.instance;

/**
 * 
 */
public interface Instance<T> {

	public <V> V getFieldValue(String fieldName);
	public T cast();
}
