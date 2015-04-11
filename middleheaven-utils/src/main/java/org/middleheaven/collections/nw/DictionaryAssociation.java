/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;

/**
 * 
 */
public class DictionaryAssociation<K,V> implements EditableAssociation<K, V> {

	
	private Map<K, V> map;

	/**
	 * Constructor.
	 * @param map
	 */
	public DictionaryAssociation(Map<K, V> map) {
		this.map = map;
	}

	public static <X,T> Association<X,T> with(X x , T t){
		Map<X,T> map = new HashMap<>();
		map.put(x, t);
		return new DictionaryAssociation<>(map);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Association<K, V> associate(K key, V value) {
		Map<K,V> map = new HashMap<>(this.map);
		map.put(key,value);
		return new DictionaryAssociation<>(map);
	}
	
	public static <X,T> DictionaryAssociation<X,T> from(Association<X,T> other){
		Map<X,T> map  = new HashMap<X,T>();
	
		for (Association.Entry<X, T> pair : other.entries()){
			map.put(pair.getKey(), pair.getValue());
		}
		
		return new DictionaryAssociation<X,T>(map);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<V> tryGet(K key) {
		return Optional.of(map.get(key));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public V get(K key) {
		return map.get(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return map.size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void set(K key, V value) {
		map.put(key, value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<Association.Entry<K, V>> entries() {
		return Enumerables.asEnumerable(map.entrySet()).map(PairAssociationEntry::fromMapEntry);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<org.middleheaven.collections.nw.Association.Entry<K, V>> iterator() {
		return entries().iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean contains(Object object) {
		if( object instanceof Association.Entry)
		{
			Association.Entry entry = (Association.Entry)object;
			Object value = this.map.get(entry.getKey());
			return value == null ? value == entry.getValue() : value.equals(entry.getValue()); 
			
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}



}
