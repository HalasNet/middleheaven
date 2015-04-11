/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface IncrementableStructure<T extends Incrementable<T, I, A> , I , A extends IncrementableStructure<T,I,A>> extends AlgebricStructure<T, A> {

	public T plus(T a , I increment);
}
