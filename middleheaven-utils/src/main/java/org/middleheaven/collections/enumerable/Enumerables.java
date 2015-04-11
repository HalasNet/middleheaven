/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 
 */
public class Enumerables {

	
	private Enumerables(){} 
	
	/**
	 * @return
	 */
	public static <T> RandomAccessEnumerable<T> emptyEnumerable() {
		return EmptyEnumerable.getInstance();
	}

	public static <T> FiniteEnumerable<T> asEnumerable(T element){
		return new SingleEnumerable<T>(element);
	}
	
	/**
	 * Converts an array of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	@SafeVarargs
	public static <T> RandomAccessEnumerable<T> asEnumerable(T ... elements){
		return new ArrayEnumerable<T>(elements);
	}

	/**
	 * Converts an Iterable of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	public static <T> Enumerable<T> asEnumerable(Stream<? extends T> source){
		return new StreamEnumerableDecorator<T>(source);
	}
	
	/**
	 * Converts an Iterable of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	public static <T> Enumerable<T> asEnumerable(Iterable<? extends T> source){
		return decideBestSourceDecodator(source); 
	}

	
	/**
	 * Converts a List of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	public static <T> Enumerable<T> asEnumerable(Collection<? extends T> source){
		return decideBestSourceDecodator(source); 
	}
	
	private static <T> Enumerable<T> decideBestSourceDecodator(Iterable<? extends T> source){
		 if (source instanceof List){
			 return new ListEnumerable<T>((List)source);
		 } else if (source instanceof Collection){
			 return new CollectionEnumerableDecorator<T>((Collection)source);
		 }
	
		 return new IterableEnumerableDecorator<T>((Iterable<T>)source);
	}
	
	/**
	 * Converts a List of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	public static <T> FiniteEnumerable<T> asEnumerable(List<? extends T> elements){
		return new ListEnumerable<T>(elements);
	}


	/**
	 * Converts an array of objects in an {@link Enumerable}.
	 * @param elements
	 * @return
	 */
	public static <K, V> FiniteEnumerable<Map.Entry<K,V>> asEnumerable(Map<K,V> map){
		return new MapEnumerable<K,V>(map);
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	public static Enumerable<Integer> range(int start, int end) {
		return new RangeEnumerable(start, end);
	}
	
	public static Enumerable<Integer> random(int start, int end) {
		return random(start, end, new Random());
	}
	
	public static Enumerable<Integer> random(int start, int end, Random random) {
		return new IntRandomEnumerable(start, end, random);
	}
	
	public static Enumerable<Double> random(double start, double end) {
		return random(start, end, new Random());
	}
	
	public static Enumerable<Double> random(double start, double end, Random random) {
		return new DoubleRandomEnumerable(start, end, random);
	}
}
