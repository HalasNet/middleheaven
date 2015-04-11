/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface ScalableElement<S, R> {

	/**
	 * Multiplication by a scalar
	 * @param a scalar value
	 * @return this multiplied by the <code>scalar</code>. 
	 * 
	 */
	public R times(S scalar);
}
