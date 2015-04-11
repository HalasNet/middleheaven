/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MonoidMultiplicative <T extends MonoidMultiplicativeElement<T , A>, A extends MonoidMultiplicative<T, A>> extends MagmaMultiplicative<T, A>{

	/**
	 * 
	 * @return the multiplication identity.
	 */
	public T one();
	
}
