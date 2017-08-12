package org.middleheaven.collections.enumerable;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.middleheaven.collections.enumerable.size.EnumerableSize;
import org.middleheaven.util.function.BinaryFunction;
import org.middleheaven.util.function.BinaryOperator;
import org.middleheaven.util.function.Function;
import org.middleheaven.util.function.Predicate;

/**
 * Represents an sequence of objects that's still iterable and permits to query for the sequence size.
 * In addition provides several methods for manipulating the data in the collection. 
 *
 * @param <T>
 */
public interface Enumerable<T> extends Iterable<T>{

	/**
	 * Cast all elements to a new type.
	 * If the element is not of the given type an ClassCastExceptio will be throw when the Enumerable is iterated.
	 * @param newType
	 * @return
	 */
	public <U> Enumerable<U> cast(Class<U> newType);

	/**
	 * Removes all {@code null} elements in the enumerable.
	 * Equivalent to this.filter( it -> it != null)
	 * 
	 * @return a new stream with no {@code null}s in it.
	 */
	public Enumerable<T> compact();

	/**
	 * Constructs a Sequence with the elements of this enumerable.
	 * @return
	 */
	//public Sequence<T> toSequence();

	/**
	 * 
	 * @return the number of elements in {@code this}.
	 */
	public EnumerableSize getSizeInfo();
	
	/**
	 * Counts the elements in the Enumerable.
	 * The same as map( o -> 1).reduce(0, (a,b) -> (a + b));
	 * 
	 * @return
	 * @throws InfiniteSizeException if the enumerable cannot be counted
	 */
	public long count() throws EnumerableSizeComputationException;


	/**
	 * 
	 * @return <code>true</code> if the size is zero, <code>false</code> otherwise.
	 */
	public boolean isEmpty();

	/**
	 * @return true if the enumerable contains only one element
	 */
	public boolean isSingle();

	/**
	 * @return the fist element in the {@link Enumerable} or throws a {@link NoSuchElementException} if is empty.
	 */
	public T findFirst() throws NoSuchElementException;


	/**
	 * @return the last element in the {@link Enumerable} or throws a {@link NoSuchElementException} if is empty.
	 */
	public T findLast() throws NoSuchElementException;

	/**
	 * @return the fist element in the {@link Enumerable} or <code>null</code> if the {@link Enumerable} is empty.
	 */
	public Optional<T> tryFirst();

	/**
	 * Returns the last element in the {@link Enumerable}.
	 * The same as this.reverse().getFirst();
	 * @return the last element in the {@link Enumerable} or <code>null</code> if the {@link Enumerable} is empty.
	 */
	public Optional<T>  tryLast();

	/**
	 * Returns an enumerable in the order atributed by the given comparator.
	 * @param comparable
	 * @return
	 * @throws IllegalStateException if the stream is infinite
	 */
	public Enumerable<T> sort (Comparator<T> comparable) throws IllegalStateException;

	/**
	 * Returns an enumerable in the natural  order of T. If T is not a Comparable a ClassCastExceptio will be thrown when the termination method executes;
	 * @param comparable
	 * @return
	 * @throws IllegalStateException if the stream is infinite
	 */
	public Enumerable<T> sort () throws IllegalStateException;


	/**
	 * Returns <code>true</code> if all items match the predicate.
	 * 
	 * @param predicate the classifier with the matching rule.
	 * @return <code>true</code> if all items match the predicate, <code>false</code> otherwise.
	 */
	public boolean every(Predicate<T> predicate);

	/**
	 * Returns true if any item matchs the predicate.
	 * The same as !filter(predicate).isEmpty();
	 * 
	 *  This is a short-circuit operation. 
	 *  
	 * @param predicate the predicate with the matching rule.
	 * @return <code>true</code> if any item matchs the predicate, <code>false</code> otherwise.
	 */
	public boolean any(Predicate<T> predicate);

	/**
	 * finds all items matching the classifier
	 * @param predicate the classifier with the matching rule.
	 * @return finds all items matching the classifier
	 */
	public Enumerable<T> filter(Predicate<T> predicate);

	/**
	 * Collect the return value of calling a classifier on each item in a collection into a new collection.
	 * If the classifier returns {@code null} for a value, it will not be added to the result collection.
	 * 
	 * @param <C> the result type.
	 * @param classifier classifier the classifier with the matching rule.
	 * @return the collect itens.
	 */
	public <C> Enumerable<C> map(Function<C,T> Function);

	/**
	 * Collect the return value of calling a classifier on each item in a collection into a new collection, passing the index correspondent with the iteration index.
	 * If the classifier returns {@code null} for a value, it will not be added to the result collection.
	 * 
	 * @param <C> the result type.
	 * @param classifier classifier the classifier with the matching rule.
	 * @return the collect itens.
	 */
	public <C> Enumerable<C> map(BinaryFunction<C,T, Long> Function);

	public <C> Enumerable<C> mapAll(Function<Enumerable<C>,T> Function);

	/**
	 *
	 * @param seed
	 * @param operator
	 * @return
	 */
	public T reduce(T seed, BinaryOperator<T> operator);

	public <C> C mapReduce(C seed, Function<Enumerable<C>,T> transform, BinaryOperator<C> operator);


	public <C> Enumerable<GroupedElements<C,T>> groupBy(Function<C,T> Function);


	/**
	 * Delivers the enumerable to a skink created by the given factory.
	 * @param factory
	 * @return
	 */
	public default <S extends Sink<T>, F extends SinkFactory<T, S>> S into(F factory){
		return factory.createFrom(this);
	}

	/**
	 * Creates a new Enumerable that will not iterate over the {@code count} first elements on this enumerable.
	 * 
	 * skip(0) is equivalent to the original enumerable.
	 * 
	 * @param count - quantity of items not to return ( to skip)
	 * @return
	 * @throws IllegalArgumentException - if count is less than zero;
	 */
	public Enumerable<T> skip(long count) throws IllegalArgumentException;

	/**
	 * Creates a new Enumerable that will only iterate over the first {@code count} elements on this enumerable. 
	 * More accuratly the resulting Enumerable will iterate Math.min(this.count(), count) elements, however this.count() is never invoked.
	 * 
	 * @param count - quantity of items to return
	 * @return
	 * @throws IllegalArgumentException - if count is less than zero;
	 */
	public Enumerable<T> take(long count) throws IllegalArgumentException;


	/**
	 * Creates a new Enumerable that will iterator over the elements in this enumerable and then on the given enumerable.
	 * @param methods
	 * @return
	 */
	public Enumerable<T> concat(Enumerable<? extends T> other);

	/**
	 * @param phrase
	 * @return
	 */
	public <X extends T> Enumerable<T> concat(X element);

	/**
	 * Return only distinct values
	 * @return
	 */
	public Enumerable<T> distinct();

	/**
	 * Creates an enumerable that transverses the elements in this enumerable in reverse order (from last to first)
	 * @return
	 */
	public Enumerable<T> reverse();


	/**
	 * Created a new Enumerable of a result of paring each a element of this {@link Enumerable} and each element {@link Enumerable}.
	 * The resulting enumerable as the same size as the shortess Enumerable being zipped
	 * @param other
	 * @param zipfunction - transforms the values of T and P into R
	 */
	public <P, R> Enumerable<R> zip(Enumerable<P> other, BinaryFunction<R, T, P> zipfunction);

	public <P, R> Optional<Enumerable<R>> zipExact(Enumerable<P> other,  BinaryFunction<R, T, P> zipfunction);


	public default List<T> toList(){
		return this.into(Sink.collections()).list();
	}

}
