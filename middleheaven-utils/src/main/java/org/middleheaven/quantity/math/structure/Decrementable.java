/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface Decrementable <T extends Decrementable<T, I, A> , I, A extends DecrementableStructure<T,I,A>> extends AlgebricStructureElement<T,A>{ 

	public T minus(I other);
}
