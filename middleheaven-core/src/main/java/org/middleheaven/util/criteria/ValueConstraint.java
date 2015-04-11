/**
 * 
 */
package org.middleheaven.util.criteria;

import java.util.Collection;

/**
 * 
 */
public interface ValueConstraint<E, V, B> {

	/**
	 * The attribute value equals {@code value}. . 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B eq(V value);  
	
	/**
	 * The attribute value is {@code null} 
	 * @param value value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B isNull();
	
	/**
	 * The attribute value is contained in the {@code values} collection.
	 * @param values value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B in(Collection<V> values);
	
	/**
	 * The attribute value is contained in one of the given {@code values}.
	 * @param values value to be compared
	 * @return CriteriaBuilder<T> with the added constraint.
	 */
	public B in(V ... values);
	
	/**
	 * 
	 * @return negates the constraint selected next. 
	 */
	public ValueConstraint<E,V, B> not();
	
}
