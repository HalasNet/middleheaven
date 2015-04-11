/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Sink;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.collections.progression.Progression;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public class DefferedSequence<T> implements Sequence<T> , org.middleheaven.collections.nw.Sequence.SequenceView<T>{

	private Enumerable<T> enumerable;

	/**
	 * Constructor.
	 * @param map
	 */
	public DefferedSequence(Enumerable<T> enumerable) {
		this.enumerable = enumerable;
	}

	public boolean equals(Object other){
		return other instanceof Sequence && equals((Sequence)other);
	}
	
	@SuppressWarnings("rawtypes")
	public final boolean equals(Sequence other){
	
		Iterator a = enumerable.iterator();
		Iterator b = other.iterator();
		while (a.hasNext()){
			if (b.hasNext()){
				Object n = a.next();
				if ( n == null ? b.next() != null : !n.equals(b.next())){
					return false;
				}
			} else {
				return false;
			}
		}
		
		return !b.hasNext();
	}
	
	public final int hashCode(){
		return this.size();
	}
	
	public String toString(){
		return "[" + enumerable.map( t -> t == null ? "null" : t.toString()).into(Sink.ofStrings()).join(",") + "]";
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object object) {
		return enumerable.any( obj -> obj == null ? object == null : obj.equals(object));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return enumerable.iterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return (int) enumerable.count();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return enumerable.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> subSequence(int start) {
		return new DefferedSequence<T>(this.enumerable.skip(start));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> subSequence(int start, int length) {
		return new DefferedSequence<T>(this.enumerable.skip(start).take(length));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object obj) {
		int index = -1;
		for (T t : enumerable){
			index++;
			if (t == null ? obj == null : t.equals(obj)){
				return index;
			}
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object obj) {
		int index = this.size();
		for (T t : enumerable.reverse()){
			index--;
			if (t == null ? obj == null : t.equals(obj)){
				return index;
			}
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> reverse() {
		return new DefferedSequence<T>(this.enumerable.reverse());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryGet(int index) {
		return this.enumerable.skip(index).tryFirst();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index) {
		return this.enumerable.skip(index).findFirst();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<org.middleheaven.collections.nw.Sequence.Entry<T>> entries() {
		return this.enumerable.map( (t , i) ->  new Entry<T>(i.intValue(), t));
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		
		int index = 0;
		for (T t : enumerable){ // used iterator so works with both RandomAccessSequence and not.
			array[index++] = t;
		}
		
		return array;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		int length = this.size();
		if (array.length < length){
			array = CollectionUtils.ensureExactLength(array, length);
		} 
		
		int index = 0;
		for (T t : enumerable){ // used iterator so works with both RandomAccessSequence and not.
			array[index++] = t;
		}
		
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
		return enumerable.into(Sink.collections()).sequence().listIterator(initialPosition);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> sample(Progression<Integer, Integer> range) {
		return enumerable.into(Sink.collections()).sequence().sample(range);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> slice(Interval<Integer> range) {
		return enumerable.into(Sink.collections()).sequence().slice(range);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U> Sequence<U> map(Function<U, T> mapper) {
		return new DefferedSequence<U>(this.enumerable.map(mapper));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> filter(Predicate<T> filter) {
		return new DefferedSequence<T>(this.enumerable.filter(filter));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R, O> Sequence<R> zip(Sequence<O> other,BinaryFunction<R, T, O> mapper) {
		return new DefferedSequence<R>(this.enumerable.zip(other.asEnumerable() , mapper));
	}

	
	private static class Entry<T> implements Sequence.Entry<T> {

		private int index;
		private T value;

		public Entry(int index, T value) {
			super();
			this.index = index;
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int getIndex() {
			return index;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T getValue() {
			return value;
		}
		
	}
}
