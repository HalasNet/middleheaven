/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface Ring<T extends RingElement<T, A>, A extends Ring<T, A>> extends GroupAdditive<T, A> , MonoidMultiplicative<T, A>{


}
