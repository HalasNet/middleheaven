/**
 * 
 */
package org.middleheaven.collections.nw;

/**
 * 
 */
public interface EditableAssociation<K, V> extends Association<K,V> {

	public void set(K key, V value);
	
}
