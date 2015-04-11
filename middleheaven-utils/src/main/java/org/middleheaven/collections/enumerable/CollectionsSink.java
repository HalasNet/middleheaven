/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.middleheaven.collections.nw.ResizableSequence;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.util.function.Function;

/**
 * 
 */
public class CollectionsSink<T> implements Sink<T>{

	private Enumerable<T> enumerable;

	/**
	 * Constructor.
	 * @param other
	 */
	public CollectionsSink(Enumerable<T> enumerable) {
		this.enumerable = enumerable;
	}
	
	public Sequence<T> sequence(){
		return Sequences.of(list());
	}
	
	public ResizableSequence<T> sequence(ResizableSequence<T> sequence){
		for(T t : enumerable){
			sequence.add(t);
		}
		return sequence;
	}
	
	public List<T> list(){
		return list(new ArrayList<>());
	}

	public Set<T> set(){
		return set(new HashSet<>());
	}
	
	public <K> Map<K,T> map(Function<K, T> extractKey){
		return map(extractKey, new HashMap<>());
	}
	
	public <K,V> Map<K,V> map(Function<K, T> extractKey, Function<V, T> extractValue ){
		return map(extractKey, extractValue, new HashMap<>());
	}
	
	public List<T> list(List<T> it){
		for(T t : enumerable){
			it.add(t);
		}
		return it;
	}

	public Set<T> set(Set<T> it){
		for(T t : enumerable){
			it.add(t);
		}
		return it;
	}
	
	public Collection<T> collection(Collection<T> it) {
		for(T t : enumerable){
			it.add(t);
		}
		return it;
	}
	
	public <K> Map<K, T> map(Function<K, T> extractKey,Map<K,T> it){
		for(T t : enumerable){
			it.put(extractKey.apply(t), t);
		}
		return it;
	}

	
	public <K,V> Map<K,V> map(Function<K, T> extractKey, Function<V, T> extractValue , Map<K,V> it){
		for(T t : enumerable){
			it.put(extractKey.apply(t), extractValue.apply(t));
		}
		return it;
	}


}
