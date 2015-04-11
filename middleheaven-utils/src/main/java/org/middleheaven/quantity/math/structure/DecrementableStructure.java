/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface DecrementableStructure<T extends Decrementable<T, I, A> , I , A extends DecrementableStructure<T,I,A>> extends AlgebricStructure<T, A> {

	public T minus(T a , I increment);
}
