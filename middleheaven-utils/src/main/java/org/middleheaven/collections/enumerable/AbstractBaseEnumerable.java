/**
 * 
 */
package org.middleheaven.collections.enumerable;

import java.lang.reflect.ParameterizedType;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.collections.enumerable.size.PreciseEnumerableSize;
import org.middleheaven.collections.iterators.StackReverseIterator;
import org.middleheaven.collections.nw.ListSequence;
import org.middleheaven.collections.nw.Sequence;
import org.middleheaven.quantity.math.Int;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.BinaryOperator;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * 
 */
public abstract class AbstractBaseEnumerable<T> implements Enumerable<T> {

	
	@SuppressWarnings("unchecked")
	protected Class<T> getGenericClass () {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	public int hashCode(){
		return 0;
	}
		
	public boolean equals(Object other ){
		if (this == other){
			return true;
		}
		return other instanceof Enumerable && equals((Enumerable<? extends T>)other);
	}

	public boolean equals(Enumerable<? extends T> other ){
		if (this == other){
			return true;
		}
		Iterator<T> a = this.iterator();
		Iterator<? extends T> b = other.iterator();
		
		if(a.hasNext() && b.hasNext()){
			do {
				
				if (!a.next().equals(b.next())){
					return false;
				}
			} while (a.hasNext() && b.hasNext());
			return true;
		} else {
			return false;
		}
	}
	
	public <P, R> Enumerable<R> zip(Enumerable<P> other, BinaryFunction<R, T, P> zipfunction) {
		return new ZipEnumerableStream<>(this, other, zipfunction);
	}
	
	public <P, R> Optional<Enumerable<R>> zipExact(Enumerable<P> other,  BinaryFunction<R, T, P> zipfunction){
		if (!this.getSizeInfo().equals(other.getSizeInfo())){
			return Optional.empty();
		}
		return Optional.of(new ZipEnumerableStream<>(this, other, zipfunction));
	}
	
	public Enumerable<T> reverse(){
		return new ReverseEnumerableStream<T>(this);
	}

	protected Iterator<T> reverseIterator(){
		return new StackReverseIterator<T>(this.iterator());
	}


	public Sequence<T> toSequence() {
		EnumerableSize size = this.getSizeInfo();
		ListSequence<T> listSequence;
		
		if (size.isInfinit()){
			throw new IllegalStateException("An infinite Enumerable cannot be converted to a Sequence");
		} else if (size instanceof UnpredictableEnumerableSize){
			throw new IllegalStateException("An unpredictable size Enumerable cannot be convert to a Sequence");
		} else if (size instanceof PreciseEnumerableSize){
			Int maxSize = ((PreciseEnumerableSize) size).getValue();

			if (!maxSize.isInteger()){
				throw new IllegalStateException("An Enumerable with size greater than Integer.MaxValue cannot be converted to a Sequence");
			}
			listSequence = new ListSequence<>(maxSize.asInteger());
		}  else {
			listSequence = new ListSequence<>(10); // standard value for computable size (grows as it goes)
		}
		
		for (T t : this){
			listSequence.add(t);
		}
		return listSequence;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public <U> Enumerable<U> cast(Class<U> newType){
		return map( t -> newType.cast(t));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> compact(){
		return filter( t -> t != null);
	}
	
	public boolean isEmpty(){
		return !this.iterator().hasNext();
	}	
	
	public boolean isSingle() {
		Iterator<T> it = this.iterator();
		if (it.hasNext()){
			it.next();
			return !it.hasNext();
		}
		return false; // isEmpty
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Enumerable<T> sort() {
		return sort(new Comparator<T>(){

			@Override
			public int compare(T a, T b) {
				return ((Comparable)a).compareTo(((Comparable)b));
			}
			
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public  Enumerable<T> sort(Comparator<T> comparator) {
		
		if (this.getSizeInfo().isInfinit()){
			throw new IllegalStateException("Cannot sort an inifite enumerable");
		}
		
		return new SortedEnumerable<T>(this, comparator);
		
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean every(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		while (it.hasNext()){
			if (!predicate.test(it.next())){
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean any(Predicate<T> predicate){
		return !filter(predicate).isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findFirst() throws NoSuchElementException {
		return this.iterator().next(); // TODO unit test this
	}
	

	@Override
	public T findLast() throws NoSuchElementException{
		return this.reverseIterator().next();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Optional<T> tryFirst() {
		Iterator<T> it = this.iterator();
		return it.hasNext() ? Optional.ofNullable(it.next()) : Optional.empty();
	}
	
	@Override
	public  Optional<T>  tryLast(){
		Iterator<T> it =this.reverseIterator();
		return it.hasNext() ? Optional.ofNullable(it.next()) : Optional.empty();
	}


	// stream 
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> filter(Predicate<T> predicate) {
		return new FilteredEnumerableStream<T>(this, predicate);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> map(Function<C, T> Function) {
		return new TransformEnumerable<C,T>(this, Function);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> map(BinaryFunction<C,T, Long> function){
		return new TransformedWithEnumerable<C, T>(this, function);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<C> mapAll(Function<Enumerable<C>, T> function) {
		return new FlattenEnumerableStream<C, T>( this, function);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> Enumerable<GroupedElements<C, T>> groupBy(Function<C, T> classificator) {
		return new GroupByEnumerable<C, T>(this, classificator );
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumerable<T> distinct(){
		return new DistinctEnumerableStream<T>(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Enumerable<T> skip(long count) {
		if (count < 0){
			throw new IllegalArgumentException("Count is negative");
		} else if (count ==0){
			return this;
		} else {
			return boundSafeSkip(count);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Enumerable<T> take(long count) {
		if (count < 0){
			throw new IllegalArgumentException("Count is negative");
		} else if (count == 0){
			return EmptyEnumerable.getInstance();
		} else {
			return boundSafeTake(count);
		}
	}
	
	/**
	 * @param count a greather than zero value of elements to skip
	 * @return
	 */
	protected Enumerable<T> boundSafeTake(long count){
		return new TakeEnumerable<T>(count, this);
	}

	/**
	 * @param count a greather than zero value of elements to skip
	 * @return
	 */
	protected Enumerable<T> boundSafeSkip(long count) {
		return new SkipEnumerable<T>(count, this);
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T reduce(T seed , BinaryOperator<T> operator) {

		T result = seed;
		for (T t: this){
			result = operator.apply(result, t);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <C> C mapReduce(C seed, Function<Enumerable<C>, T> transform, BinaryOperator<C> operator) {
		return mapAll(transform).reduce(seed, operator);
	}
	
	public Enumerable<T> concat(Enumerable<? extends T> other){
		return ComposedEnumerable.compose(this,other);
	}
	
	public <X extends T> Enumerable<T> concat(X element){
		return concat(new SingleEnumerable<T>(element));
	}


	public Enumerable<T> random(long count){
		return new RandomEnumerable<T>(count, this, new Random());
	}

	public Enumerable<T> random(long count,Random random){
		return new RandomEnumerable<T>(count, this, random);
	}

}
