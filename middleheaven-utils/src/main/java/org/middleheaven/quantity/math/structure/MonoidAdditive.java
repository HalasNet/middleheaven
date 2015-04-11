/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MonoidAdditive <T extends MonoidAdditiveElement<T, A>, A extends MonoidAdditive<T,A>> extends MagmaAdditive<T, A> {

	public T zero();
	
}
