/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.middleheaven.collections.enumerable.size.IntPreciseEnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.collections.nw.Sequences;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.BinaryOperator;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
class EmptyEnumerable<T> implements RandomAccessEnumerable<T>, FiniteEnumerable<T>{

	
	@SuppressWarnings("rawtypes")
	private static final EmptyEnumerable ME = new EmptyEnumerable();

	@SuppressWarnings("unchecked")
	public static <X> EmptyEnumerable<X> getInstance(){
		return ME;
	}
	
	private EmptyEnumerable(){}
	
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
	public PreciseEnumerableSize getSizeInfo() {
		return IntPreciseEnumerableSize.of(0);
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
	public Optional<T> tryFirst() {
		return Optional.empty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> sort(Comparator<T> comparable) {
		return this;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> sort() {
		return this;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean every(Predicate<T> predicate) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean any(Predicate<T> predicate) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> filter(Predicate<T> predicate) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> map(Function<C, T> Function) {
		return getInstance();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> map(BinaryFunction<C, T, Long> Function) {
		return getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> mapAll(Function<Enumerable<C>, T> Function) {
		return getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T reduce(T seed, BinaryOperator<T> operator) {
		return seed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> C mapReduce(C seed, Function<Enumerable<C>, T> Function,
			BinaryOperator<C> operator) {
		return seed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U> Enumerable<U> cast(Class<U> newType) {
		return ME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryLast() {
		return Optional.empty();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> skip(long count) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> concat(Enumerable<? extends T> other) {
		return (Enumerable<T>) other;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <X extends T> Enumerable<T> concat(X element) {
		return (Enumerable<T>) Enumerables.asEnumerable(element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> distinct() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> reverse() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, R> Enumerable<R> zip(Enumerable<P> other,BinaryFunction<R,T, P> zipfunction) {
		return getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> take(long count) {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getAt(long index) {
		throw new IndexOutOfBoundsException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSingle() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findFirst() throws NoSuchElementException {
		throw new NoSuchElementException();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findLast() throws NoSuchElementException {
		throw new NoSuchElementException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<GroupedElements<C, T>> groupBy(Function<C, T> Function) {
		return getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <P, R> Optional<Enumerable<R>> zipExact(Enumerable<P> other,BinaryFunction<R, T, P> zipfunction) {
		if (other.isEmpty()){
			return Optional.of(getInstance());
		}
		throw new NotSameSizeException();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> compact() {
		return this;
	}








}
