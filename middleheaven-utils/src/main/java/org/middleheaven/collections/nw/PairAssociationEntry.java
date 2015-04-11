/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Map;

/**
 * 
 */
public final class PairAssociationEntry<K,V> implements Association.Entry<K, V>{

	private final K key;
	private final V value;

	public static <X,T> Association.Entry<X,T> fromMapEntry(Map.Entry<X, T> entry){
		return new PairAssociationEntry<X,T>(entry.getKey(), entry.getValue());
	}
	
	public static <X,T> Association.Entry<X,T> of(X key, T value){
		return new PairAssociationEntry<X,T>(key, value);
	}
	
	public PairAssociationEntry(K key, V value){
		this.key = key;
		this.value = value;
	}
	
	public K getKey(){
		return key;
	}
	
	public V getValue(){
		return value;
	}
}
