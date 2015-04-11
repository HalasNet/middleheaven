/**
 * 
 */
package org.middleheaven.collections.nw;

import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;

import org.middleheaven.collections.enumerable.Enumerable;
import org.middleheaven.collections.enumerable.Enumerables;
import org.middleheaven.collections.interval.Interval;
import org.middleheaven.collections.nw.Sequence.SequenceView;
import org.middleheaven.collections.progression.Progression;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public class EmptySequence<T> implements SequenceView<T>{

	@SuppressWarnings("rawtypes")
	private static EmptySequence ME = new EmptySequence();
	
	@SuppressWarnings("unchecked")
	public static <X> EmptySequence<X> getInstance(){
		return ME;
	}

	private Object[] emptyArray = new Object[0];
	
	private EmptySequence(){}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return 0;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> concat(T value) {
		return new SingleSequence<T>(value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> concat(Sequence<T> other) {
		return other;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		 return Collections.<T>emptySet().iterator();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryGet(int index) {
		return Optional.empty();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index) {
		throw new IndexOutOfBoundsException();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<org.middleheaven.collections.nw.Sequence.Entry<T>> entries() {
		 return Enumerables.emptyEnumerable();
	}


	/**
	 * {@inheritDoc}
	 * 
	 * Always returns false;
	 */
	@Override
	public boolean contains(Object object) {
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		return emptyArray;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public T[] toArray(T[] array) {
		if (array.length > 0){
			array[0] = null;
		}
		return array;
	}
	
	@SuppressWarnings("rawtypes")
	public boolean equals (Object obj){
		return obj instanceof Sequence && ((Sequence)obj).isEmpty();
	}

	public int hashCode(){
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> reverse() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> subSequence(int start) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> subSequence(int start, int length) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(Object obj) {
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(Object obj) {
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> sample(Progression<Integer, Integer> progression) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.middleheaven.collections.nw.Sequence.SequenceView<T> slice(Interval<Integer> interval) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator() {
		return Collections.<T>emptyList().listIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListIterator<T> listIterator(int initialPosition) {
		return Collections.<T>emptyList().listIterator(initialPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U> Sequence<U> map(Function<U, T> mapper) {
		return getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Sequence<T> filter(Predicate<T> filter) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <R, O> Sequence<R> zip(Sequence<O> other,BinaryFunction<R, T, O> object) {
		return getInstance();
	}

}
