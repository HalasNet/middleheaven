/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface Incrementable <T extends Incrementable<T, I, A> , I, A extends IncrementableStructure<T,I,A>> extends AlgebricStructureElement<T,A>{ 

	public T plus(I increment);
}
