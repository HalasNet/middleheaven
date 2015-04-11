/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface Multiplicative <T extends Multiplicative<T, F, A> , F,  A extends MultiplicativeStructure<T,F,A>> extends AlgebricStructureElement<T,A>{ 

	public T times(F factor);

}
