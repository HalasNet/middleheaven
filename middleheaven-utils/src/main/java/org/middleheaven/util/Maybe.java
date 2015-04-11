/**
 * 
 */
package org.middleheaven.util;

import org.middleheaven.util.function.BinaryOperator;
import org.middleheaven.util.function.Function;

/**
 * Represents a placeholder for, a possibly <code>null</code> value.
 * The use of {@link Maybe} helps to avoid {@link NullPointerException}.
 */
public class Maybe<T> {

	protected T value;
	
	/**
	 * Creates a {@link Maybe} for the given value.
	 * @param value the given value.
	 * @return  a {@link Maybe} for the given value.
	 */
	public static <M> Maybe<M> of(M value){
		if (value instanceof Maybe){
			return (Maybe<M>) value;
		} else if (value instanceof String){
			return (Maybe<M>)of((String)value);
		} else if (value instanceof CharSequence){
			return (Maybe<M>)of((CharSequence)value);
		}
		
		return new Maybe<M>(value);
	}
	
	public static <M> Maybe<M> of(Maybe<M> other){
		return other;
	}

	public static <S extends CharSequence>   MaybeCharSequence<S> of(S value){
		return value == null || value.length() == 0 || value.toString().trim().length() == 0 
				? new MaybeCharSequence<S>(null)
				: new MaybeCharSequence<S>(value);
	}
	
	/**
	 * 
	 * @return a {@link Maybe} instance thar represents an absent (<code>null</code>) value.
	 */
	public static <M> Maybe<M> absent(){
		return new Maybe<M>(null);
	}
	
	/**
	 * Constructor.
	 * @param value
	 */
	protected Maybe(T value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return <code>true</code> if the value is absent, <code>false</code> otherwise.
	 */
	public boolean isAbsent(){
		return value == null;
	}
	
	/**
	 * 
	 * @return <code>true</code> if the value is present, <code>false</code> otherwise.
	 */
	public boolean isPresent(){
		return value != null;
	}
	
	/**
	 * returns the inner value. throws exception is the value is absent.
	 * This method never returns <code>null</code>.
	 * 
	 * @return the inner value. throws exception is the value is absent.
	 * @throws IllegalStateException it the value is absent.
	 */
	public T get(){
		if (this.isAbsent()){
			throw new IllegalStateException("Value is absent");
		}
		return value;
	}
	
	/**
	 * If this value is absent, return {@code other}
	 * @param other the other value to return
	 * @return this value is absent, return {@code other}, otherwise, return the value returned by this.get().
	 */
	public T or (T other){
		return this.isAbsent() ? other : value;
	}
	
	/**
	 * If this value is absent, return {@code other}, otherwise returns {@code this}.
	 * @param other other the other value to return
	 * @return this value is absent, return {@code other}, otherwise, return {@code this}.
	 */
	public Maybe<T> or (Maybe<T> other){
		return this.isAbsent() ? other : this;
	}
	
	public Maybe<T> alsoAbsent (Function<Boolean, T> Function){
		return (this.isAbsent() || Function.apply(this.value).booleanValue()) ? Maybe.<T>absent() :  this;
	}
	
	/**
	 * If this value is absent returns an absent value maybe, otherwise applies the Function and returns a maybe of the result
	 * @param Function
	 * @return
	 */
	public <R> Maybe<R> map (Function<R, T> Function){
		return this.isAbsent() ? Maybe.<R>absent() : of(Function.apply(this.value));
	}
	
	/**
	 * If this value is absent returns an absent value maybe, otherwise applies the Function and returns a maybe of the result
	 * @param Function
	 * @return
	 */
	public <R> Maybe<R> flatMap (Function<Maybe<R>, T> Function){
		return this.isAbsent() ? Maybe.<R>absent() : Function.apply(this.value);
	}
	
	/**
	 * Applies an operator on the values of this and other. If any of the values is absent the result is absent.
	 * @param other
	 * @param operator
	 * @return
	 */
	public Maybe<T> apply(Maybe<T> other , BinaryOperator<T> operator){
		return this.isAbsent() || other.isAbsent() ? Maybe.absent() : of(operator.apply(this.value, other.value));
	}
	
	public boolean is(T other){
		return other != null && this.isPresent() && this.value.equals(other);
	}
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Maybe) && equalsMaybe(Maybe.class.cast(obj)); 
	}
	
	
	private boolean equalsMaybe(Maybe<T> other) {
		return this.value == null ? other.value == null : this.value.equals(other.value);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode(){
		return this.value == null ? 0: value.hashCode();
	}
	
	public String toString(){
		return String.valueOf(value);
	}

	/**
	 * If the value present is of the given type, return a maybe with the type casted, otherwise return an absent value.
	 * @param otherType
	 */
	public <U> Maybe<U> maybeCast(Class<U> otherType) {
		if (this.isAbsent()){
			return Maybe.absent();
		} else {
			return otherType.isInstance(this.value) ? Maybe.of(otherType.cast(this.value)) : Maybe.absent();
		}
	}

	

}
