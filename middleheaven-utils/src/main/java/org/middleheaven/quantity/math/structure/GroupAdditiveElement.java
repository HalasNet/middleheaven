package org.middleheaven.quantity.math.structure;

/**
 * Represent a mathematical Group Additive structure.
 * 
 * @param <T> the type of the element in the group.
 */
public interface GroupAdditiveElement<T extends GroupAdditiveElement<T, A> , A extends GroupAdditive<T,A> > extends MonoidAdditiveElement<T, A> , Decrementable<T,T,A> {

	/**
	 * Returns the sum of this object with the one specified.
	 * @param other the object to be added
	 * @return this + other
	 */
	public T plus(T other);
	
	/**
	 * Returns the additive inverse of this object.
	 * @return -this
	 */
	public T negate();
	
	/**
	 * Returns the sum of this object with the additive inverse of the one specified.
	 * @param other the object to be added
	 * @return this - other. 
	 */
	public T minus(T other);

}
