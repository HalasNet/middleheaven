/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MonoidMultiplicativeElement<T extends MonoidMultiplicativeElement<T, A>, A extends MonoidMultiplicative<T, A>> extends MagmaMultiplicativeElement<T,A> {

	public boolean isOne();
}
