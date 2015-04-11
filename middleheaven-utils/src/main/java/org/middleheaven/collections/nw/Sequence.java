/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.collections.progression.Progression;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public interface Sequence<T> extends Assortment<T> {

	/**
	 * 
	 */
	public interface Entry<T> {

		public int getIndex();
		public T getValue();
	}

	/**
	 * Marks a sequence as being back up by a "real" sequence.
	 * @param <T>
	 */
	public interface SequenceView<T> extends Sequence<T>{}
	
	
	public SequenceView<T> subSequence(int start);
	public SequenceView<T> subSequence(int start, int length);
	
	public int indexOf(Object obj);
	public int lastIndexOf(Object obj);
	
	public Sequence<T> reverse();
	
	public Optional<T> tryGet(int index); 
	public T get(int index);
	public Enumerable<Entry<T>> entries();
	public SequenceView<T> sample(Progression<Integer, Integer> range);
	public SequenceView<T> slice(Interval<Integer> range);
	
	public default Sequence<T> concat(T value) {
		return new CompositeSequence<>(this).append(new SingleSequence<>(value));
	}

	public default Sequence<T> concat(Sequence<T> other) {
		return new CompositeSequence<>(this).append(other);
	}
	
	public Object[] toArray();
	public T[] toArray(T[] array );
	
	public default Association<Integer, T> asAssociation() {
		 return new SequenceAssociationAdapter<T> (this);
	}
	
	public default Enumerable<T> asEnumerable() {
		 return new SequenceEnumerableAdapter<T> (this);
	}

	/**
	 * @return
	 */
	public default T getFirst() {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return get(0);
	}

	/**
	 * @return
	 */
	public default T getLast() {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return get(this.size() - 1);
	}
	
	/**
	 * @return
	 */
	public default Optional<T> tryFirst() {
		return this.isEmpty() ? Optional.empty() : Optional.of(get(0));
	}

	/**
	 * @return
	 */
	public default Optional<T> tryLast() {
		return this.isEmpty()? Optional.empty() : Optional.of(get(this.size() - 1));
	}
	/**
	 * @return
	 */
	public ListIterator<T> listIterator();
	/**
	 * @return
	 */
	public ListIterator<T> listIterator(int initialPosition);
	/**
	 * @param object
	 * @return
	 */
	public <U> Sequence<U> map(Function<U,T> mapper);
	/**
	 * @param object
	 * @return
	 */
	public Sequence<T> filter(Predicate<T> filter);
	/**
	 * @param b
	 * @param object
	 * @return
	 */
	public <R, O> Sequence<R> zip(Sequence<O> other, BinaryFunction<R, T, O> object);
}
