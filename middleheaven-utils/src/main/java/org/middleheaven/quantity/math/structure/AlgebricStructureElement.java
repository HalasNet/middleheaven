/**
 * 
 */
package org.middleheaven.quantity.math.structure;

/**
 * 
 */
public interface AlgebricStructureElement<T extends AlgebricStructureElement<T, A> , A extends AlgebricStructure<T, A> > {

	/**
	 * 
	 * @return the GroupAdditive structure
	 */
	public A getAlgebricStructure();
	
}
