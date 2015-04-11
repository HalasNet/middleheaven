/**
 * 
 */
package org.middleheaven.util.criteria;

import org.middleheaven.collections.interval.Interval;

/**
 * 
 */
public interface ComparableValueConstraint<E, V, B> extends ValueConstraint<E,V,B>{

	/**
	 * The attribute value is less than {@code value}. . 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B lt(V value);  
	
	/**
	 * The attribute value is greater than {@code value}. . 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B gt(V value);
	/**
	 * The attribute value is less than or equal to {@code value}. 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B le(V value);  
	/**
	 * The attribute value is greater than or equal to {@code value}. 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B ge(V value);
	/**
	 * The attribute value is contained in the given {@code interval}.
	 * @param interval interval where the attribute's value must be contained
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public <C extends Comparable<? super C>> B in(Interval<C> interval);
	/**
	 * The attribute value is equal or greater than {@code min} and less or equal than {@code max}.
	 * @param <V>
	 * @param min minimum value
	 * @param max maximum value
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public <C extends Comparable<? super C>> B bewteen(C min, C max);  
	
}
