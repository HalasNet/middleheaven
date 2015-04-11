/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MagmaMultiplicativeElement<T extends MagmaMultiplicativeElement<T, A>, A extends MagmaMultiplicative<T, A>> 
	extends AlgebricStructureElement<T,A> , Multiplicative<T, T, A>{

}
