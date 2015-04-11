/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.List;

/**
 * 
 */
public class Sequences {
	
	@SafeVarargs
	public static <T> Sequence<T> of(T ... values){
		if (values.length == 0){
			return  EmptySequence.getInstance();
		} else if (values.length == 1){
			return  new SingleSequence<>(values[0]);
		}
		return ArraySequence.of(values);
	}
	
	public static <T> Sequence<T> of(List<T> values){
		if (values.isEmpty()){
			return  EmptySequence.getInstance();
		} else if (values.size() == 1){
			return  new SingleSequence<>(values.get(0));
		}
		return new ListSequence<T>(values);
	}
	
	public static <T> Sequence<T> of(T value){
		return new SingleSequence<T>(value);
	}
	
	public static <T> Sequence<T> empty(){
		return  EmptySequence.getInstance();
	}
	
	public static <T> Sequence<T> ofLength(int length){
		return new ListSequence<T>(length);
	}
	
	public static <T> Sequence<T> copies(int length, T value){
		return new CopiesSequence<T>(value,length);
	}
}
