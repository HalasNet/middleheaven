/**
 * 
 */
package org.middleheaven.quantity.math.structure;

import java.util.Random;

/**
 * 
 */
public interface Field<T extends FieldElement<T, A>, A extends Field<T,A>> extends ComutativeRing<T,A> , GroupMultiplicative<T, A> {

	
	/**
	 * The multiplication inverse.
	 * @return 1/ this.
	 */
	public T inverse(T x);
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public T fromNumber(Number n);

	/**
	 * Return fromNumber(Math.random);
	 * @return
	 */
	public T random();
	
	/**
	 * Return fromNumber(Random.next);
	 * @return
	 */
	public T random(Random random);

	/**
	 * @return
	 */
	public T conjugate(T a);
}
