/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface MagmaMultiplicative<T extends MagmaMultiplicativeElement<T, A>, A extends MagmaMultiplicative<T, A>> 
	extends AlgebricStructure<T, A> , MultiplicativeStructure<T, T, A>{

}
