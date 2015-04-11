/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;

/**
 * 
 */
public interface Association<K,V>  extends Assortment<Association.Entry<K, V>> {

	public Enumerable<Entry<K, V>> entries(); 
	public Optional<V> tryGet(K key);
	public V get(K key);
	public Association<K,V> associate(K key , V value);
	public boolean containsKey(Object key);
	public boolean containsValue(Object value);
	
	public default V getOrDefault(K key , V defaultValue){
		return tryGet(key).orElse(defaultValue);
	}

	public interface Entry<K, V> {

		public K getKey();
		public V getValue();

	}

}
