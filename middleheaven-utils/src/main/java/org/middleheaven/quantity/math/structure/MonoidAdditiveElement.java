/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MonoidAdditiveElement<T extends MonoidAdditiveElement<T, A>, A extends MonoidAdditive<T, A>> extends MagmaAdditiveElement<T,A>{

	public boolean isZero();

}
