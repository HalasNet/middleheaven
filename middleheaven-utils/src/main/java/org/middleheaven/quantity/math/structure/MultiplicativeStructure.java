/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MultiplicativeStructure<T extends Multiplicative<T, F, A> , F , A extends MultiplicativeStructure<T,F,A>> extends AlgebricStructure<T, A> {

	public T multiply(T a , F factor);
}
