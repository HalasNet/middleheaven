/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MagmaAdditiveElement <T extends MagmaAdditiveElement<T, A> , A extends MagmaAdditive<T,A> > extends Incrementable<T, T , A>{

	
	public T plus(T other);
}
