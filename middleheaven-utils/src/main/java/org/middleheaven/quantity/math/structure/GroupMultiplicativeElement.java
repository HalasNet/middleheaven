package org.middleheaven.quantity.math.structure;

/**
 * Represent a mathematical Group Additive structure.
 * 
 * @param <T> the type of the element in the group.
 */
public interface GroupMultiplicativeElement<T extends GroupMultiplicativeElement<T, A> , A extends GroupMultiplicative<T,A> > extends MonoidMultiplicativeElement<T, A>{

	/**
	 * Returns the sum of this object with the one specified.
	 * @param other the object to be added
	 * @return this + other
	 */
	public T over(T other);
	
	
}
