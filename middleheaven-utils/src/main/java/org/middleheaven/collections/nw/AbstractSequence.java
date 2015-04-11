/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.middleheaven.collections.CollectionUtils;
import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.collections.iterators.IndexBasedIterator;
import org.middleheaven.collections.iterators.UnmodifiableIterator;
import org.middleheaven.collections.progression.Progression;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public abstract class AbstractSequence<T> implements Sequence<T> {

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
		return new AbstractSequenceListIterator(initialPosition);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SequenceView<T> subSequence(int start) {
		if (start < 0 && start >= size()){
			throw new IndexOutOfBoundsException();
		}
		return subSequence(start, this.size() - start);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> subSequence(int start, int length) {
		if (start < 0 && start >= size()){
			throw new IndexOutOfBoundsException();
		}
		if (start + length > size()){
			throw new IllegalArgumentException("Not enought elements");
		}
		return subSequence(start, length);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object obj) {
		for (Sequence.Entry<T> t : this.entries()){
			if (obj == null ? t.getValue() == null : obj.equals(t.getValue())){
				return t.getIndex();
			}
		}
		return -1;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object obj) {
		for (Sequence.Entry<T> t : this.reverse().entries()){
			if (obj == null ? t.getValue() == null : obj.equals(t.getValue())){
				return this.size() - t.getIndex() - 1;
			}
		}
		return -1;
	}
	
	public Sequence<T> reverse(){
		return new ReverseSequence<T>(this);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryGet(int index) {
		if (index < 0 && index >= size()){
			return Optional.empty();
		}
		return Optional.of(boundSafeGet(index));
	}

	public T get(int index) {
		if (index < 0 && index >= size()){
			throw new IndexOutOfBoundsException();
		}
		return boundSafeGet(index);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SequenceView<T> sample(Progression<Integer, Integer> progression) {
		int[] indexMapping = new int[size()];
		int count = 0;
		for (Integer pos : progression){
			indexMapping[count++] = pos.intValue();
			if (count == indexMapping.length){
				break;
			}
		}
		
		return new IndexMappedSequenceView<T>(this, indexMapping, count);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SequenceView<T> slice(Interval<Integer> range) {
		throw new UnsupportedOperationException("Not implememented yet");
	}
	
	/**
	 * @param index
	 * @return
	 */
	protected abstract T boundSafeGet(int index);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return new UnmodifiableIterator<>(baseIterator());
	}

	/**
	 * @return
	 */
	protected  Iterator<T> baseIterator(){
		return new SequenceIteratorByIndex<T>(this);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(Object object) {
		 for (Object obj : this){
			 if (obj == object || (obj != null && obj.equals(object))){
				 return true;
			 }
		 }
		 return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[this.size()];
		
		int index = 0;
		for (T t : this){ // used iterator so works with both RandomAccessSequence and not.
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
		for (T t : this){ // used iterator so works with both RandomAccessSequence and not.
			array[index++] = t;
		}
		
		if (array.length > length){
			array[length] = null;
		}
		
		return array;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder("[");
		
		for (T t : this){
			builder.append(t==null ? "null" : t.toString());
		}
		return builder.append("]").toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U> Sequence<U> map(Function<U, T> mapper) {
		return new DefferedSequence<U>(this.asEnumerable().map(mapper));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> filter(Predicate<T> filter) {
		return new DefferedSequence<T>(this.asEnumerable().filter(filter));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R, O> Sequence<R> zip(Sequence<O> other,BinaryFunction<R, T, O> mapper) {
		return new DefferedSequence<R>(this.asEnumerable().zip(other.asEnumerable(), mapper));
	}
	
	public boolean equals(Object other){
		return other instanceof Sequence && equals((Sequence)other);
	}
	
	@SuppressWarnings("rawtypes")
	public final boolean equals(Sequence other){
		if (this.size() != other.size()){
			return false;
		}
		Iterator a = this.iterator();
		Iterator b = other.iterator();
		while (a.hasNext() && b.hasNext()){
			Object n = a.next();
			if ( n == null ? b.next() != null : !n.equals(b.next())){
				return false;
			}
		}
		return true;
	}
	
	public final int hashCode(){
		return this.size();
	}

	private static class SequenceIteratorByIndex<X> extends IndexBasedIterator<X>{

		private AbstractSequence<X> original;

		public SequenceIteratorByIndex(AbstractSequence<X> original){
			this.original = original;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		protected int size() {
			return original.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected X getObject(int index) {
			return original.boundSafeGet(index);
		}

		
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<Sequence.Entry<T>> entries() {
		return Enumerables.asEnumerable(new IteratorIterableAdapter<Sequence.Entry<T>>(new IndexBasedIterator<Sequence.Entry<T>>(){

			@Override
			protected int size() {
				return  AbstractSequence.this.size();
			}

			@Override
			protected Sequence.Entry<T> getObject(int index) {
				return new Sequence.Entry<T>(){


					@Override
					public T getValue() {
						return AbstractSequence.this.boundSafeGet(index);
					}

					@Override
					public int getIndex() {
						return index;
					}

				};
			}

		}));
	}
	
	private class AbstractSequenceListIterator implements ListIterator<T>{

		
		private int position;

		private AbstractSequenceListIterator(int position){
			this.position = position;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void add(T obj) {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return position + 1 < size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasPrevious() {
			return position - 1 >= 0;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T next() {
			position++;
			if (position >= size()){
				throw new NoSuchElementException();
			}
			return (T)boundSafeGet(position);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int nextIndex() {
			return position + 1;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T previous() {
			position--;
			if (position < 0){
				throw new NoSuchElementException();
			}
			return (T)boundSafeGet(position);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int previousIndex() {
			return position - 1;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();
		}
		
	}
}
