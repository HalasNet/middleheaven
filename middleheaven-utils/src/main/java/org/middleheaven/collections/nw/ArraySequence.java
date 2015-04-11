/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.ListIterator;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public final class ArraySequence<T> extends AbstractEditableSequence<T>  {

	@SafeVarargs
	 static <X> Sequence<X> of (X ... values){
		return new ArraySequence<X>(values);
	}
	
	public static <X> ArraySequence<X> ofLength(int length) {
		return new ArraySequence<X>(new Object[length]);
	}

	private Object[] values;
	
	/**
	 * Constructor.
	 * @param values
	 */
	public ArraySequence(Object[] values) {
		this.values = values;
	}
	
	public ArraySequence(Sequence<T> other) {
		this.values = other.toArray();
	}

	
	public String toString(){
		return Arrays.toString(values);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int size() {
		return values.length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return values.length == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected T boundSafeGet(int index) {
		return (T)values[index];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void boundSafeSet(int index, T value) {
		values[index] = value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> concat(T value) {
		 Object[] newArray =  new Object[values.length + 1];
		 System.arraycopy(values, 0, newArray, 0, values.length);
		 newArray[newArray.length - 1] = value;
		 return new ArraySequence<T>(newArray);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort(Comparator<T> comparator) {
		Arrays.sort(values, (a,b) -> comparator.compare((T)a, (T)b));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return CollectionUtils.duplicateArray(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		
		int length = values.length;
		if (array.length < length){
			array = CollectionUtils.ensureExactLength(array, length);
		} 
		
		System.arraycopy(values, 0, array, 0, length);
		
		if (array.length > length){
			array[length] = null;
		}
		
		return array;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		return listIterator(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		 return new ArrayListIterator<T>(initialPosition, values);
	}

	

	


	



}
